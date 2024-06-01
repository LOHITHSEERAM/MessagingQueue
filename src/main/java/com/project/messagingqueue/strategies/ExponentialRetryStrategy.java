package com.project.messagingqueue.strategies;

import com.project.messagingqueue.exception.RetryLimitExhaustedException;
import com.project.messagingqueue.handlers.TopicHandler;
import com.project.messagingqueue.models.Message;
import com.project.messagingqueue.models.Topic;
import lombok.Getter;

@Getter
public class ExponentialRetryStrategy implements RetryStrategy {

    int maxAttempts = 5;

    @Override
    public void publish(Topic topic, Message message, TopicHandler topicHandler) throws InterruptedException, RetryLimitExhaustedException {
        int currentAttempts = 0;
        int previousSleepMillis = 500;
        while (currentAttempts < maxAttempts) {
            try {
                currentAttempts += 1;
                topic.addMessage(message);
                new Thread(topicHandler::publish).start();
                return;
            } catch (Exception e) {
                previousSleepMillis *= 2;
                Thread.sleep(previousSleepMillis);
            }
        }
        throw new RetryLimitExhaustedException();

    }
}
