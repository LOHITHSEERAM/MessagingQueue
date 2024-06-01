package com.project.messagingqueue.publicAPIs;

import com.project.messagingqueue.models.Message;

public interface ISubscriber {

    public Integer getId();
    public void consume(Message message);
}
