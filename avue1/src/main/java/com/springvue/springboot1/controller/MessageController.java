package com.springvue.springboot1.controller;

import com.springvue.springboot1.entity.Admin;
import com.springvue.springboot1.entity.Member;
import com.springvue.springboot1.entity.Message;
import com.springvue.springboot1.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/message")
public class MessageController {
   @Autowired
    private MessageRepository messageRepository;

   @GetMapping("/findAll/{page}/{size}")
    public Page<Message> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest request = PageRequest.of(page, size);
        return messageRepository.findAll(request);
    }

    //模糊查找
    @PostMapping("/findByHealthLike/{page}/{size}")
    public Page<Message> findByHealthLike(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                          @RequestBody Message message) {
        PageRequest request = PageRequest.of(page, size);
        return messageRepository.findByHealthLike(request,"%"+message.getHealth()+"%");
    }

    //推荐3条和用户健康相关的小常识
    @PostMapping("/find")
    public List<Message> find(@RequestBody Message message) {
       List<Message> list = messageRepository.findAllByHealth(message.getHealth());
        List<Message> list2 = new ArrayList<>();
        int[] Ret = new int[4];
        int num = 0;
        int count = 0;
        int flag = 0;
        while(count <3 ){
            Random random = new Random();
            num = Math.abs(random.nextInt(list.size()));
            for(int i =0;i<count;i++){
                if(Ret[i] == num){
                    flag = 1;
                    break;
                }else{
                    flag = 0;
                }
            }
            if(flag == 0){
                Ret[count] = num;
                list2.add(list.get(num));
                count++;
            }
        }
       return list2;
    }

    @PostMapping("/save") //添加
    public String save(@RequestBody Message message){
        Message result = messageRepository.save(message);
        if(result != null){
            return "success";
        }else {
            return "false";
        }
    }

    @DeleteMapping("/deleteById/{id}") //删除
    public void deleteById(@PathVariable("id") Integer id){
        messageRepository.deleteById(id);
    }
}
