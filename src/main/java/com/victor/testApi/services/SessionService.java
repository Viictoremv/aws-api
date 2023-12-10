package com.victor.testApi.services;

import com.victor.testApi.entities.Session;
import com.victor.testApi.helpers.SessionString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.UUID;

@Service
public class SessionService {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final SessionString sessionString;
    private final String tableName = "sesiones-alumnos";

    @Autowired
    public SessionService(DynamoDbEnhancedClient dynamoDbEnhancedClient, SessionString sessionString){
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        this.sessionString = sessionString;
    }

    public void createSession(int id){
        Session session = new Session();
        session.setId(UUID.randomUUID().toString());
        session.setFecha(8);
        session.setAlumnoId(id);
        session.setActive(true);
        session.setSessionString(sessionString.generateSessionString());
        DynamoDbTable<Session> sessionsTable = getSessionTable();
        sessionsTable.putItem(session);
    }

    private DynamoDbTable<Session> getSessionTable(){
        return dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(Session.class));
    }
}
