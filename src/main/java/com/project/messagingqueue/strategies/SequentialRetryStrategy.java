package com.project.messagingqueue.strategies;

import com.project.messagingqueue.handlers.TopicHandler;
import com.project.messagingqueue.models.Message;
import com.project.messagingqueue.models.Topic;

public class SequentialRetryStrategy implements RetryStrategy {

    @Override
    public void publish(Topic topic, Message message, TopicHandler topicHandler) {


    }
}
