package com.seek.client.context.client.infrastructure.repository;

import com.seek.client.context.client.domain.Client;
import com.seek.client.context.client.domain.ClientPublisher;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Repository
public class SqsClientPublisher implements ClientPublisher {
    private final SqsAsyncClient sqsAsyncClient;
    private final String QUE_URL = "https://sqs.us-east-1.amazonaws.com/939431056947/seek-client";

    public SqsClientPublisher(SqsAsyncClient sqsAsyncClient) {
        this.sqsAsyncClient = sqsAsyncClient;
    }

    @Override
    public void send(Client client) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(QUE_URL)
                .messageBody(String.valueOf(client.getId()))
                .build();

        sqsAsyncClient.sendMessage(request)
                .thenApply(SendMessageResponse::messageId)
                .thenApply(id -> "Mensaje enviado con ID: " + id)
                .exceptionally(e -> "Error enviando mensaje: " + e.getMessage());
    }
}
