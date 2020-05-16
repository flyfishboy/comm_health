package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Bmi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BmiRepository extends JpaRepository<Bmi,Integer> {
    Page<Bmi> findAllByAccount(Pageable pageable, String account);
    List<Bmi> findAllByAccount(String account);
    Page<Bmi> findByAccountAndTimeLike(Pageable pageable,String account,String time);
}
