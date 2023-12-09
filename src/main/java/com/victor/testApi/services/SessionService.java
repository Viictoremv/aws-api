package com.victor.testApi.services;

import com.victor.testApi.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class SessionService {
    DynamoDbEnhancedClient dynamoDbEnhancedClient;

    @Autowired
    public SessionService(DynamoDbEnhancedClient dynamoDbEnhancedClient){
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        DynamoDbTable<Session> tb = dynamoDbEnhancedClient.table("sesiones-alumnos", TableSchema.fromBean(Session.class));
        tb.describeTable();
    }
}
