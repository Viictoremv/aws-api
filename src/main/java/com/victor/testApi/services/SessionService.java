package com.victor.testApi.services;

import com.victor.testApi.entities.Session;
import com.victor.testApi.helpers.SessionStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

import java.util.*;
import java.util.function.Consumer;

@Service
public class SessionService {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final SessionStringGenerator sessionStringGenerator;
    private final String tableName = "sesiones-alumnos";

    @Autowired
    public SessionService(DynamoDbEnhancedClient dynamoDbEnhancedClient, SessionStringGenerator sessionStringGenerator){
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        this.sessionStringGenerator = sessionStringGenerator;
    }

    public String createSession(int id){
        String sessionString;
        Session session = new Session();
        session.setId(UUID.randomUUID().toString());
        session.setFecha(8);
        session.setAlumnoId(id);
        session.setActive(true);
        sessionString = sessionStringGenerator.generateSessionString();
        session.setSessionString(sessionString);
        DynamoDbTable<Session> sessionsTable = getSessionTable();
        sessionsTable.putItem(session);
        return sessionString;
    }

    public boolean isValidSession(int id, String sessionString){
        Session session = getSession(id, sessionString);
        if(session != null){
            return session.getActive();
        }
        return false;
    }
    private DynamoDbTable<Session> getSessionTable(){
        return dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(Session.class));
    }

    private Session getSession(int id, String sessionString){
        Session session;
        DynamoDbTable<Session> sessionsTable = getSessionTable();
        PageIterable<Session> allSessions = sessionsTable.scan(createSessionStringScan(sessionString));
        findSession(id, sessionString, allSessions);
        return findSession(id, sessionString, allSessions);
    }

    private Session findSession(int id, String sessionString, PageIterable<Session> allSessions){
        Session session = null;
        for (Page<Session> page : allSessions) {
            for (Session sessionItem : page.items()) {
                if (sessionItem.getSessionString().equals(sessionString) && sessionItem.getAlumnoId() == id) {
                    session = sessionItem;
                    break;
                }
            }
        }
        return session;
    }

    private ScanEnhancedRequest createSessionStringScan(String sessionString){
        return ScanEnhancedRequest.builder()
                .filterExpression(createSessionStringExpression(sessionString))
                .build();
    }
    private Expression createSessionStringExpression(String sessionString){
        return Expression.builder()
                .expression("#attr = :value")
                .putExpressionName("#attr", "sessionString")
                .putExpressionValue(":value", AttributeValue.builder().s(sessionString).build())
                .build();
    }
}
