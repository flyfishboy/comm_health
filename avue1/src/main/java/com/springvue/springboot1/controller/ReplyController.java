package com.springvue.springboot1.controller;

import com.springvue.springboot1.entity.Reply;
import com.springvue.springboot1.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;

    @PostMapping("/save") //添加
    public String save(@RequestBody Reply reply){
        Reply result = replyRepository.save(reply);
        if(result != null){
            return "success";
        }else {
            return "false";
        }
    }

    @GetMapping("/find/{rid}") //查询回复内容
    public List<Reply> find(@PathVariable("rid") Integer rid) {

        List<Reply> list = replyRepository.findByRid(rid);
        return list;
    }

}
