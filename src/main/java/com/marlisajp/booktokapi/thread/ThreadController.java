package com.marlisajp.booktokapi.thread;

import com.marlisajp.booktokapi.auth.AuthUtil;
import com.marlisajp.booktokapi.message.Message;
import com.marlisajp.booktokapi.message.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/threads")
public class ThreadController {

    private final ThreadService threadService;
    private final MessageService messageService;
    private final AuthUtil authUtil;

    public ThreadController(ThreadService threadService,MessageService messageService, AuthUtil authUtil) {
        this.messageService = messageService;
        this.threadService = threadService;
        this.authUtil = authUtil;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Thread>> getAllThreads() {
        return ResponseEntity.ok(threadService.getAllThreads());
    }

    @GetMapping("/me")
    public ResponseEntity<List<Thread>> getAllThreadsByUserId(@RequestHeader("Authorization") String token) {
        Long authenticatedUserId = authUtil.getAuthenticatedUserId(token);

        if(authenticatedUserId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Thread> threads = threadService.getAllThreadsByUserId(authenticatedUserId);
        return ResponseEntity.ok(threads);
    }

    @PostMapping
    public ResponseEntity<Thread> createThread(@RequestHeader("Authorization") String token, @RequestBody Thread thread) {
        Long authenticatedUserId = authUtil.getAuthenticatedUserId(token);

        if(authenticatedUserId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Thread createdThread = threadService.createThread(thread.getTitle(), thread.getSubject(), thread.getBook());
        return ResponseEntity.ok(createdThread);
    }

    @PutMapping("/{threadId}")
    public ResponseEntity<Thread> addMessageToThread(@RequestHeader("Authorization") String token, @RequestBody Message message, @PathVariable Long threadId) {
        // authenticate user
        Long authenticatedUserId = authUtil.getAuthenticatedUserId(token);

        if(authenticatedUserId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // find thread
        Optional<Thread> threadOptional = threadService.getThreadById(threadId);

        if(threadOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Thread thread = threadOptional.get();

        // create and add message to thread
        Message createdMessage = messageService.createMessage(message.getContent());
        Thread updatedThread = threadService.addMessageToThread(createdMessage, thread);

        // return updated thread
        return ResponseEntity.ok(updatedThread);
    }
}
