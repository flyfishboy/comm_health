package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Page<Admin> findAll(Pageable pageable);
    List<Admin> findAllByAccountAndPassword(String account ,String password);
    Page<Admin> findByNameLike(Pageable pageable, String name);
}
