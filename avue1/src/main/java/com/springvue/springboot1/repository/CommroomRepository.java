package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Commroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommroomRepository extends JpaRepository<Commroom,Integer> {
    Page<Commroom> findAllByAccounta(Pageable pageable, String accounta);
    List<Commroom> findAllByAccounta(String accounta);
    List<Commroom> findAllByAccountaAndAccountb(String accounta,String accountb);
    @Transactional
    void deleteByAccountaAndAccountb(String accounta,String accountb);


}
