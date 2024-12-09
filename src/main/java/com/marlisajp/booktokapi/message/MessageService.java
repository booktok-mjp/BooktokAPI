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

    public Message createMessage(String content, Thread thread, User user) {
        Message message = new Message();
        message.setContent(content);
        message.setThread(thread);
        message.setUser(user);
        return messageRepository.save(message);
    }

    public List<Message> getThreadMessages(Thread thread) {
        return messageRepository.findByThreadId(thread.getId());
    }
}

