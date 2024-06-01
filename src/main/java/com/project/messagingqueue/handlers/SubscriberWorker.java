package com.project.messagingqueue.handlers;

import com.project.messagingqueue.models.Message;
import com.project.messagingqueue.models.TopicSubscriber;


public class SubscriberWorker implements Runnable {

    final TopicSubscriber topicSubscriber;

    public SubscriberWorker(TopicSubscriber topicSubscriber) {
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        do {
            synchronized (topicSubscriber) {
                int curOffset = topicSubscriber.getOffset().get();
                while (curOffset >= topicSubscriber.getTopic().getMessages().size()) {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Message message = topicSubscriber.getTopic().getMessages().get(curOffset);
                    topicSubscriber.getSubscriber().consume(message);

                    topicSubscriber.getOffset().compareAndSet(curOffset, curOffset + 1);
                }
            }
        } while (true);
    }

    public void WakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }

}
