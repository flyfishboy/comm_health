package com.springvue.springboot1.controller;


import com.springvue.springboot1.entity.Admin;
import com.springvue.springboot1.entity.Atob;
import com.springvue.springboot1.entity.Commroom;
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

    //查询其他用户发给当前用户的消息,消息提示
    @PostMapping("/findAllByBadge/{page}/{size}") //分页
    public Page<Atob> findAllByBadge(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@RequestBody Atob atob) {
        PageRequest request = PageRequest.of(page, size);
        return atobRepository.findAllByAccountbAndBadge(request,atob.getAccountb(),atob.getBadge());
    }

    //模糊查找历史记录里的nameb
    @PostMapping("/findAllByName2/{page}/{size}")
    public Page<Atob> findAllByNameLike3(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                        @RequestBody Atob atob) {
        PageRequest request = PageRequest.of(page, size);
        return atobRepository.findByAccountaAndNamebLike(request,atob.getAccounta(),"%"+atob.getNameb()+"%");
    }

    //模糊查找消息提示里的Namea
    @PostMapping("/findAllByNamea/{page}/{size}")
    public Page<Atob> findAllByNameLike2(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                        @RequestBody Atob atob) {
        PageRequest request = PageRequest.of(page, size);
        return atobRepository.findByAccountbAndNameaLike(request,atob.getAccountb(),"%"+atob.getNamea()+"%");
    }

    @PostMapping("/save") //添加
    public String save(@RequestBody Atob atob){
        Atob result = atobRepository.save(atob);
       return "success";
    }

    //
    @PostMapping("/saveBadge")
    public String saveBadge(@RequestBody Atob atob){
        List<Atob> list = atobRepository.findAllByIdAndAccountaAndAccountb(atob.getId(),atob.getAccounta(),atob.getAccountb());
        atob.setTime(atob.getTime());
        atob.setBadge(atob.getBadge());
        Atob result = atobRepository.save(atob);
            return "success";
    }

    //聊天历史记录，保存用户，如果为新用户则新增，否则为修改Badge
    @PostMapping("/saveBadge2")
    public String saveBadge2(@RequestBody Atob atob){
        List<Atob> list = atobRepository.findAllByIdAndAccountaAndAccountb(atob.getId(),atob.getAccounta(),atob.getAccountb());
        if(list.size() != 0){
            return "false";
        }else {
            atob.setBadge(atob.getBadge());
            atob.setTime(atob.getTime());
            Atob result = atobRepository.save(atob);
            return "success";
        }

    }

    //删除两个人的聊天内容
    @PostMapping("/deleteByAB") //删除
    public void deleteByAB(@RequestBody Atob atob){
        atobRepository.deleteByAccountaAndAccountb(atob.getAccounta(),atob.getAccountb());
    }

    //删除历史记录
    @PostMapping("/deleteById/{id}") //删除
    public void deleteById(@PathVariable("id") Integer id){
        atobRepository.deleteById(id);
    }

}
