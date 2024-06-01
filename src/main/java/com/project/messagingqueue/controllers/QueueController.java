package com.project.messagingqueue.controllers;

import com.project.messagingqueue.handlers.PublishWorker;
import com.project.messagingqueue.handlers.TopicHandler;
import com.project.messagingqueue.models.Message;
import com.project.messagingqueue.models.Topic;
import com.project.messagingqueue.models.TopicSubscriber;
import com.project.messagingqueue.publicAPIs.ISubscriber;
import com.project.messagingqueue.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class QueueController {

    PublishWorker publishWorker;
    TopicService topicService;
    Map<Integer,TopicHandler> topicProcessors;

    @Autowired
    public QueueController(TopicService topicService) {
        this.topicService = topicService;
        topicProcessors = new HashMap<>();
    }

    public Topic createTopic(String topicName) {

        Topic topic = new Topic(topicName);
        TopicHandler topicHandler = new TopicHandler(topic);
        topicProcessors.put(topic.getId(), topicHandler);
        return topic;
    }

    public void subscribeTopic(Topic topic, ISubscriber subscriber) {
        TopicSubscriber topicSubscriber = new TopicSubscriber(subscriber,topic);
        topic.addTopicSubscriber(topicSubscriber);
    }

    public void publishToTopic(Topic topic, Message message) {

        publishWorker.publish(topic, message , topicProcessors.get(topic.getId()));
    }

    public void ResetOffsetTo(ISubscriber subscriber, Topic topic,Integer newOffset) {

        for(TopicSubscriber topicSubscriber : topic.getTopicSubscribers()) {
            if(Objects.equals(topicSubscriber.getSubscriber().getId(), subscriber.getId())) {
                topicSubscriber.getOffset().set(newOffset);
                new Thread(()->topicProcessors.get(topic.getId()).startSubsriberWorker(topicSubscriber)).start();
                break;
            }

        }
    }

}
