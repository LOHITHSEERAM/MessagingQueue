package com.project.messagingqueue.handlers;

import com.project.messagingqueue.exception.RetryLimitExhaustedException;
import com.project.messagingqueue.models.Message;
import com.project.messagingqueue.models.Topic;
import com.project.messagingqueue.strategies.RetryStrategy;

public class PublishWorker {

    RetryStrategy retryStrategy;

    PublishWorker(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }
    public void publish(Topic topic, Message message, TopicHandler topicHandler) {
        try {
            retryStrategy.publish(topic,message,topicHandler);
        } catch (RetryLimitExhaustedException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
