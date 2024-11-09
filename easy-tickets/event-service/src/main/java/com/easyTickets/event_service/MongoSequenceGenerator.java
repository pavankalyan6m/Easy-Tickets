package com.easyTickets.event_service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoSequenceGenerator {

    @Autowired
    private MongoTemplate mongoTemplate;

    public int generateSequence(String seqName) {
        // Fetch the current sequence from the "id_sequences" collection
        Document seqDocument = mongoTemplate.getCollection("id_sequences").find(new Document("_id", seqName)).first();

        if (seqDocument == null) {
            // If the sequence doesn't exist, create it with an initial value of 1
            seqDocument = new Document("_id", seqName).append("seq", 1);
            mongoTemplate.getCollection("id_sequences").insertOne(seqDocument);
            return 1;
        } else {
            // Increment the sequence and return the next value
            int seqValue = seqDocument.getInteger("seq");
            mongoTemplate.getCollection("id_sequences")
                    .updateOne(new Document("_id", seqName), new Document("$set", new Document("seq", seqValue + 1)));
            return seqValue + 1;
        }
    }
}
