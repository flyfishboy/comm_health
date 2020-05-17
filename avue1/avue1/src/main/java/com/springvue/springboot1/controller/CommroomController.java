package com.springvue.springboot1.controller;

import com.springvue.springboot1.entity.Commroom;
import com.springvue.springboot1.entity.Member;
import com.springvue.springboot1.repository.CommroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class CommroomController {
    @Autowired
    private CommroomRepository commroomRepository;

    @PostMapping("/findAll")
    public List<Commroom> find(){
        List<Commroom> list = commroomRepository.findAll();
        return list;
    }

    @PostMapping("/find") //查询聊天双方的数据
    public List<Commroom> findab(@RequestBody Commroom commroom){
        List<Commroom> list = commroomRepository.findAllByAccountaAndAccountb(commroom.getAccounta(),commroom.getAccountb());
        return list;
    }

    @PostMapping("/save") //同时添加两个数据
    public String save(@RequestBody Commroom commroom){

        Commroom result = commroomRepository.save(commroom);
            return "success";

    }
    @PostMapping("/save2") //同时添加两个数据，这个数据是调换后的，便于查询
    public String save2(@RequestBody Commroom commroom){

        String aa = commroom.getAccounta();
        String bb = commroom.getAccountb();
        String contenta= commroom.getContenta();
        String contentb= commroom.getContentb();


        commroom.setAccountb(aa);
        commroom.setAccounta(bb);
        commroom.setContentb(contenta);
        commroom.setContenta(contentb);

        Commroom result2 = commroomRepository.save(commroom);
        return "success";

    }

    //删除两个人的聊天内容
    @PostMapping("/deleteByAB") //删除
    public void deleteByAB(@RequestBody Commroom commroom){

        commroomRepository.deleteByAccountaAndAccountb(commroom.getAccounta(),commroom.getAccountb());
    }
    //删除两个人的聊天内容
    @DeleteMapping("/deleteById/{id}") //删除
    public void deleteById(@PathVariable("id") Integer id){
        commroomRepository.deleteById(id);
    }
}
