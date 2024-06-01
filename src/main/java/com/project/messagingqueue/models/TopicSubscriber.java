package com.project.messagingqueue.models;

import com.project.messagingqueue.publicAPIs.ISubscriber;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TopicSubscriber {

    Integer id;

    AtomicInteger offset;

    ISubscriber subscriber;

    Topic topic;

    public TopicSubscriber(ISubscriber subscriber, Topic topic) {
        this.offset = new AtomicInteger(0);
        this.subscriber = subscriber;
        this.topic = topic;

    }
}
