package com.victor.testApi.services;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

@Service
public class NotificationService {

    private final SnsClient snsClient;
    private final String topicName = "Topic-alumnos";

    public NotificationService(){
        this.snsClient = SnsClient.create();
    }

    public void createNotification(String name, String lastName, double promedio){
        String message = name + " " + lastName + "\nCalificación: " + promedio;
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .subject("Clickea para recuperar tu contreseña")
                    .targetArn(getArn())
                    .build();

            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    private String getArn() {
        String topicArn = "";

        ListTopicsResponse listTopicsResponse = snsClient.listTopics();

        // Iterate through the topics and check for the specific ARN
        for (Topic topic : listTopicsResponse.topics()) {
            String currentTopicArn = topic.topicArn();
            String currentTopicName = extractTopicName(currentTopicArn);
            if (currentTopicName.equals(topicName)) {
                topicArn = currentTopicArn;
                break;
            }
        }
        return topicArn;
    }

    private String extractTopicName(String topicArn) {
        int lastIndex = topicArn.lastIndexOf(':');
        return topicArn.substring(lastIndex + 1);
    }
}