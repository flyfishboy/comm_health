package com.springvue.springboot1.repository;

import com.springvue.springboot1.entity.Admin;
import com.springvue.springboot1.entity.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture,Integer> {
    Page<Picture> findAll(Pageable pageable);
    List<Picture> findAllByAccount(String account);
    Page<Picture> findAllByAccount(Pageable pageable,String account);
    List<Picture> findAllById(Integer id);
    List<Picture> findByVurl(String vurl);

}
