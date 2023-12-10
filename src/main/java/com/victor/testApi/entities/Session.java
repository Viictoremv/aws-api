package com.victor.testApi.entities;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.sql.Timestamp;
import java.time.Instant;

@DynamoDbBean
public class Session {

    private String id;
    private int fecha;
    private int alumnoId;
    private boolean active;
    private String sessionString;

    public void setFecha(int fecha) {
        Instant instant = Instant.now();
        this.fecha = (int) instant.getEpochSecond();
    }

    public boolean isActive() {
        return active;
    }

    private String amigo;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @DynamoDbAttribute("fecha")
    public int getFecha() {
        return fecha;
    }

    @DynamoDbAttribute("alumnoId")
    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }
    @DynamoDbAttribute("active")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    @DynamoDbAttribute("sessionString")
    public String getSessionString() {
        return sessionString;
    }

    public void setSessionString(String sessionString) {
        this.sessionString = sessionString;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", fecha=" + fecha +
                ", alumnoId=" + alumnoId +
                ", active=" + active +
                ", sessionString='" + sessionString + '\'' +
                '}';
    }
}
