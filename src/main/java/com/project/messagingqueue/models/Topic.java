package com.project.messagingqueue.models;

import com.project.messagingqueue.publicAPIs.IPublisher;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {

    Integer Id;

    String TopicName;

    List<Message> messages;

    List<TopicSubscriber> topicSubscribers;

    List<TopicPublisher> topicPublishers;

    public Topic(String topicName) {
        messages = new ArrayList<>();
        topicSubscribers = new ArrayList<>();
        topicPublishers = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addTopicSubscriber(TopicSubscriber topicSubscriber) {
        topicSubscribers.add(topicSubscriber);
    }

    public void addTopicPublished(TopicPublisher topicPublisher) {
        topicPublishers.add(topicPublisher);
    }
}
