package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Commroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommroomRepository extends JpaRepository<Commroom,Integer> {
    Page<Commroom> findAllByAccounta(Pageable pageable, String accounta);
    List<Commroom> findAllByAccounta(String accounta);
    Page<Commroom> findByAccountaAndTimeLike(Pageable pageable,String account,String time);
    List<Commroom> findAllByAccountaAndAccountb(String accounta,String accountb);
}
