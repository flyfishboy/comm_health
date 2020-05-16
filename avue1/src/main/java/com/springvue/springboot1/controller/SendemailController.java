package com.springvue.springboot1.controller;

import com.springvue.springboot1.entity.Sendemail;
import com.springvue.springboot1.repository.SendemailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class SendemailController {
    @Autowired
    private SendemailRepository sendemailRepository;

    @PostMapping("/findAllByAccount/{page}/{size}") //个人查询动态分页
    public Page<Sendemail> findAllByAccount(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                            @RequestBody Sendemail sendemail) {
        PageRequest request = PageRequest.of(page, size);
        return sendemailRepository.findAllByAccount(request,sendemail.getAccount());
    }

    @PostMapping("/findAllByTime/{page}/{size}") //通过时间模糊查找
    public Page<Sendemail> findAllByAccountTimeLike(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                              @RequestBody Sendemail sendemail) {
        PageRequest request = PageRequest.of(page, size);
        return sendemailRepository.findByAccountAndTimeLike(request,sendemail.getAccount(),"%"+sendemail.getTime()+"%");
    }

    @PostMapping("/find_excel") //excel表格打印
    public List<Sendemail> find_excel(@RequestBody Sendemail sendemail){
        List<Sendemail> list = sendemailRepository.findAllByAccount(sendemail.getAccount());
        return list;
    }

    @PostMapping("/save") //添加
    public String save(@RequestBody Sendemail sendemail){
        Sendemail result = sendemailRepository.save(sendemail);
        if(result != null){
            return "success";
        }else {
            return "false";
        }
    }

    @DeleteMapping("/deleteById/{id}") //删除
    public void deleteById(@PathVariable("id") Integer id){
        sendemailRepository.deleteById(id);
    }
}
