package com.springvue.springboot1.controller;

import com.springvue.springboot1.entity.Admin;
import com.springvue.springboot1.entity.Bmi;
import com.springvue.springboot1.entity.Member;
import com.springvue.springboot1.entity.Picture;
import com.springvue.springboot1.repository.BmiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bmi")
public class BmiController  {
    @Autowired
    private BmiRepository bmiRepository;

    @PostMapping("/findAllByAccount/{page}/{size}") //个人查询动态分页
    public Page<Bmi> findAllByAccount(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                          @RequestBody Bmi bmi) {
        PageRequest request = PageRequest.of(page, size);
        return bmiRepository.findAllByAccount(request,bmi.getAccount());
    }

    @PostMapping("/findAllByTime/{page}/{size}") //通过时间模糊查找
    public Page<Bmi> findAllByAccountTimeLike(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                         @RequestBody Bmi bmi) {
//        System.out.println(bmi);
        PageRequest request = PageRequest.of(page, size);
        return bmiRepository.findByAccountAndTimeLike(request,bmi.getAccount(),"%"+bmi.getTime()+"%");
    }

    @PostMapping("/find_excel") //excel表格打印
    public List<Bmi> find_excel(@RequestBody Bmi bmi){
        List<Bmi> list = bmiRepository.findAllByAccount(bmi.getAccount());
        return list;
    }

    @PostMapping("/save") //添加
    public String save(@RequestBody Bmi bmi){
        Bmi result = bmiRepository.save(bmi);
        if(result != null){
            return "success";
        }else {
            return "false";
        }
    }

    @DeleteMapping("/deleteById/{id}") //删除
    public void deleteById(@PathVariable("id") Integer id){
        bmiRepository.deleteById(id);
    }
}
