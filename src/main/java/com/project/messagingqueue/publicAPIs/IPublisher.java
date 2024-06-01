package com.project.messagingqueue.publicAPIs;

import com.project.messagingqueue.models.Message;
import com.project.messagingqueue.models.Topic;

public interface IPublisher {

    public void publish(Topic topic, Message message);
}
