package com.springvue.springboot1.repository;


import com.springvue.springboot1.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {
    List<Reply> findByRid(Integer rid);
}
