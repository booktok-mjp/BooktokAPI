package com.marlisajp.booktokapi.message;

import com.marlisajp.booktokapi.thread.Thread;
import com.marlisajp.booktokapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        return messageRepository.save(message);
    }

    public List<Message> getThreadMessages(Thread thread) {
        return messageRepository.findByThreadId(thread.getId());
    }
}

