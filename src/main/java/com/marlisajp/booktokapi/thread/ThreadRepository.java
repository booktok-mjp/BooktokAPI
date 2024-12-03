package com.marlisajp.booktokapi.thread;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    List<Thread> findByUserId(Long userId);
}
