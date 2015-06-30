package com.payit.components.mongodb.dao

import com.mongodb.{DuplicateKeyException, DBObject}
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.{MongoDB, MongoCollection}
import com.payit.components.core.models.Timestamps
import com.payit.components.mongodb.models.MongoModel
import com.payit.components.specs.SpecScope
import org.joda.time.DateTime
import org.specs2.execute.{Result, AsResult}
import org.specs2.mutable.Specification
import org.specs2.specification.AroundEach

class CrudDAOSpec extends Specification with AroundEach { spec =>

  sequential

  val conn = MongoConnection("localhost", 27017)
  val db: MongoDB = conn("payit-specs")
  val modelCollection: MongoCollection = db("spec_crud_dao")
  modelCollection.createIndex(MongoDBObject("name" -> 1), MongoDBObject("unique" -> true))

  case class SpecModel(name: String, timestamps: Timestamps = Timestamps(), id: ObjectId = new ObjectId)
    extends MongoModel[SpecModel]
  {

    def withUpdatedAt(updatedAt: DateTime) = copy(timestamps = timestamps.updated(updatedAt))

  }

  trait SpecCrudDAOScope extends CrudDAO[SpecModel] with SpecScope {

    protected val db: MongoDB = spec.db
    protected val collection: MongoCollection = modelCollection
    protected val mapper: MongoObjectMapper[SpecModel] = new MongoObjectMapper[SpecModel] {
      def fromDBObject(dbo: DBObject): SpecModel = SpecModel(
        name = dbo.as[String]("name"),
        timestamps(dbo),
        id = dbo.as[ObjectId]("_id")
      )
      def asDBObject(model: SpecModel): DBObject = MongoDBObject(
        "_id" -> model.id,
        "name" -> model.name,
        Timestamps -> timestamps(model)
      )
    }

  }

  trait ExistingModelScope extends SpecCrudDAOScope {

    val now = DateTime.now()
    val existingModel = SpecModel("Testing", Timestamps(now, now))
    modelCollection.insert(mapper.asDBObject(existingModel))

  }

  trait InsertedModelScope extends SpecCrudDAOScope {

    val now = DateTime.now()
    val insertedModel = insert(SpecModel("Testing", Timestamps(now, now.plusDays(1))))

  }

  trait UpdatedModelScope extends InsertedModelScope {

    val updatedModel = update(insertedModel.copy(name = "UpdatedName"))

  }

  trait DeletedModelScope extends ExistingModelScope {
    delete(existingModel.id)
  }

  def around[T : AsResult](t: => T): Result = {
    try { AsResult(t) } finally { db.getCollectionNames().foreach { c =>
        if (!c.startsWith("system")) db(c).remove(MongoDBObject.empty)
      }
    }
  }

  "findById" >> {
    "when model exists for given id" >> {
      "it should return expected model" in new ExistingModelScope {
        findById(existingModel.id) must beSome(existingModel)
      }
    }
    "when model does not exists for given id" >> {
      "it should return None" in new ExistingModelScope {
        findById(new ObjectId) must beNone
      }
    }
  }

  "insert" >> {
    "when id is NULL" >> {
      "it should result in an error" in new SpecCrudDAOScope {
        insert(SpecModel("Testing", Timestamps(), null)) must throwA[RuntimeException](message = "because id is null")
      }
    }
    "when successful" >> {
      "it should ensure updatedAt matches createdAt" in new InsertedModelScope {
        insertedModel.timestamps.updatedAt must_== insertedModel.timestamps.createdAt
      }
      "it should have inserted a new record in the db" in new InsertedModelScope {
        findById(insertedModel.id) must not(beNone)
      }
    }
    "when a record already exists for the unique name of the model" >> {
      "it should throw an exception instead of requiring getLastError" in new InsertedModelScope {
        insert(SpecModel("Testing")) must throwA[DuplicateKeyException]
      }
    }
  }

  "update" >> {
    "when successful" >> {
      "it should have updated the updatedAt date time" in new UpdatedModelScope {
        updatedModel.timestamps.updatedAt.getMillis must be_>(updatedModel.timestamps.createdAt.getMillis)
      }
      "it should have updated the name of the model" in new UpdatedModelScope {
        updatedModel.name must_== "UpdatedName"
      }
    }
    "when updating the name to an already existing unique value" >> {
      "it should throw an exception instead of requiring getLastError" in new SpecCrudDAOScope {
        modelCollection.insert(mapper.asDBObject(SpecModel("Testing1")))
        var existingModel = SpecModel("Testing2")
        modelCollection.insert(mapper.asDBObject(existingModel))
        update(existingModel.copy(name = "Testing1")) must throwA[DuplicateKeyException]
      }
    }
  }

  "delete" >> {
    "when a record exists for a given id" >> {
      "it should remove that record" in new DeletedModelScope {
        modelCollection.findOneByID(existingModel.id) must beNone
      }
    }
    "when a record does not exist for a given id" >> {
      "it should result in a call that does not produce an error" in new SpecCrudDAOScope {
        delete(new ObjectId)
      }
    }
  }

}