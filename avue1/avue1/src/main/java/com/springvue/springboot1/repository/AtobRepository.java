package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Atob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AtobRepository extends JpaRepository<Atob,Integer> {
    List<Atob> findAllByAccountaAndAccountb(String accounta,String accountb);
    List<Atob> findAllByIdAndAccountaAndAccountb(Integer id,String accounta,String accountb);
    Page<Atob> findByNamebLike(Pageable pageable, String nameb);
    Page<Atob> findByAccountbAndNameaLike(Pageable pageable, String accountb,String namea);
    Page<Atob> findByAccountaAndNamebLike(Pageable pageable,String accounta, String nameb);
    Page<Atob> findAllByAccounta(Pageable pageable, String accounta);
    Page<Atob> findAllByAccountb(Pageable pageable, String accountb);
    Page<Atob> findAllByAccountbAndBadge(Pageable pageable, String accountb,String badge);
    Page<Atob> findAllByAccountaAndAccountb(Pageable pageable,String accounta, String accountb);
    @Transactional
    void deleteByAccountaAndAccountb(String accounta,String accountb);
}
