package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    List<Member> findAllByAccountAndPassword(String account , String password);
    Page<Member> findByNameLike(Pageable pageable, String name);
    List<Member> findAllByAccount(String account);
    List<Member> findAll();



}
