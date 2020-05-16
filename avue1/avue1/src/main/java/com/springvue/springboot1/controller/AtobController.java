package com.springvue.springboot1.controller;


import com.springvue.springboot1.entity.Admin;
import com.springvue.springboot1.entity.Atob;
import com.springvue.springboot1.entity.Member;
import com.springvue.springboot1.repository.AtobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atob")
public class AtobController {
    @Autowired
    private AtobRepository atobRepository;

    //查询当前登录用户发消息的对象
    @PostMapping("/findAllByAccounta/{page}/{size}") //分页
    public Page<Atob> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@RequestBody Atob atob) {
        PageRequest request = PageRequest.of(page, size);
        return atobRepository.findAllByAccounta(request,atob.getAccounta());
    }
    //查询其他用户发给当前用户的消息
    @PostMapping("/findAllByAccountb/{page}/{size}") //分页
    public Page<Atob> findAll2(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@RequestBody Atob atob) {
        PageRequest request = PageRequest.of(page, size);
        return atobRepository.findAllByAccountb(request,atob.getAccountb());
    }

    //模糊查找历史记录里的nameb
    @PostMapping("/findAllByName/{page}/{size}")
    public Page<Atob> findAllByNameLike(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                          @RequestBody Atob atob) {
        PageRequest request = PageRequest.of(page, size);
        return atobRepository.findByNamebLike(request,"%"+atob.getNameb()+"%");
    }

    //模糊查找消息提示里的Namea
    @PostMapping("/findAllByNamea/{page}/{size}")
    public Page<Atob> findAllByNameLike2(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                        @RequestBody Atob atob) {
        PageRequest request = PageRequest.of(page, size);
        return atobRepository.findByNameaLike(request,"%"+atob.getNamea()+"%");
    }

    @PostMapping("/save") //添加
    public String save(@RequestBody Atob atob){
        Atob result = atobRepository.save(atob);
       return "success";
    }

    //查询数据库有没有已经存在，存在则不添加，不存在则添加
    @PostMapping("/find")
    public String findByAccount2(@RequestBody Atob atob){
        List<Atob> list = atobRepository.findAllByAccountaAndAccountb(atob.getAccounta(),atob.getAccountb());
        if(list.size() != 0){
            return "false";
        }else {

            Atob result = atobRepository.save(atob);
            return "success";
        }
    }

}
