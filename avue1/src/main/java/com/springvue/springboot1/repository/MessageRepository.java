package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    Page<Message> findByHealthLike(Pageable pageable, String health);
    Page<Message> findAll(Pageable pageable);
    List<Message> findAllByHealth(String health);
}
