package com.marlisajp.booktokapi.thread;

import com.marlisajp.booktokapi.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThreadService {

    @Autowired
    private ThreadRepository threadRepository;

    public List<Thread> getAllThreads() {
        return threadRepository.findAll();
    }

    public List<Thread> getAllThreadsByUserId(Long userId) {
        return threadRepository.findByUserId(userId);
    }

    public Thread createThread(String title, String subject, String book) {
        Thread thread = new Thread();
        thread.setTitle(title);
        thread.setSubject(subject);
        thread.setBook(book);
        return threadRepository.save(thread);
    }

    public Optional<Thread> getThreadById(Long threadId) {
        return threadRepository.findById(threadId);
    }

    public Thread addMessageToThread(Message message, Thread thread) {
        thread.getMessages().add(message);
        message.setThread(thread);
        return threadRepository.save(thread);
    }
}
