package com.project.messagingqueue.handlers;
import com.project.messagingqueue.models.Topic;
import com.project.messagingqueue.models.TopicSubscriber;
import com.project.messagingqueue.publicAPIs.ISubscriber;

import java.util.Map;


public class TopicHandler {

    Topic topic;
    Map<Integer, SubscriberWorker> subscriberWorkers;
    Map<Integer,PublishWorker> publishWorkers;

    public TopicHandler(Topic topic) {
        this.topic = topic;
    }

    public void publish() {
        for(TopicSubscriber topicSubscriber : topic.getTopicSubscribers()) {
            startSubsriberWorker(topicSubscriber);
        }
    }

    public void startSubsriberWorker(TopicSubscriber topicSubscriber) {
        Integer subscriberID = topicSubscriber.getSubscriber().getId();
        if(!subscriberWorkers.containsKey(subscriberID)) {
            SubscriberWorker subscriberWorker = new SubscriberWorker(topicSubscriber);
            subscriberWorkers.put(subscriberID,subscriberWorker);
            new Thread(subscriberWorker).start();
        }

        SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberID);
        subscriberWorker.WakeUpIfNeeded();

    }




}
