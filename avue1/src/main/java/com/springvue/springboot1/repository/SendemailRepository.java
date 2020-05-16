package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Sendemail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SendemailRepository extends JpaRepository<Sendemail,Integer> {
    Page<Sendemail> findAllByAccount(Pageable pageable, String account);
    List<Sendemail> findAllByAccount(String account);
    Page<Sendemail> findByAccountAndTimeLike(Pageable pageable,String account,String time);
}
