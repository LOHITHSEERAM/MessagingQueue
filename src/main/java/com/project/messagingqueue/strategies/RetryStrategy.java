package com.project.messagingqueue.strategies;

import com.project.messagingqueue.exception.RetryLimitExhaustedException;
import com.project.messagingqueue.handlers.TopicHandler;
import com.project.messagingqueue.models.Message;
import com.project.messagingqueue.models.Topic;

public interface RetryStrategy {




    void publish(Topic topic, Message message, TopicHandler topicHandler) throws InterruptedException, RetryLimitExhaustedException;
}
