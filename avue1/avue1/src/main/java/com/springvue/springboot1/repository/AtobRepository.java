package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Atob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtobRepository extends JpaRepository<Atob,Integer> {
    List<Atob> findAllByAccountaAndAccountb(String accounta,String accountb);
    Page<Atob> findByNamebLike(Pageable pageable, String nameb);
    Page<Atob> findByNameaLike(Pageable pageable, String namea);
    Page<Atob> findAllByAccounta(Pageable pageable, String accounta);
    Page<Atob> findAllByAccountb(Pageable pageable, String accountb);
}
