package com.example.repository;


import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    // Find all messages where postedBy equals the given Integer accountId
    List<Message> findByPostedBy(Integer postedBy);
}
