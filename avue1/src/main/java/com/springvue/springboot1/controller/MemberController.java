package com.springvue.springboot1.controller;

import com.springvue.springboot1.ResponseBean;
import com.springvue.springboot1.entity.Member;
import com.springvue.springboot1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/findAll/{page}/{size}") //分页
    public Page<Member> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest request = PageRequest.of(page, size);
        return memberRepository.findAll(request);
    }

    @PostMapping("/findAllByName/{page}/{size}") //模糊查找
    public Page<Member> findAllByNameLike(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                         @RequestBody Member member) {
        PageRequest request = PageRequest.of(page, size);
        return memberRepository.findByNameLike(request,"%"+member.getName()+"%");
    }

    @PostMapping("/login")
    public ResponseBean Login(@RequestBody Member member){
        List<Member> list = memberRepository.findAllByAccountAndPassword(member.getAccount(),member.getPassword());
        if(list.size() != 0){
            return new ResponseBean(200 ,"登录成功" , list);
        }else{
            return new ResponseBean(400 , "登录失败" ,"");
        }
    }

    @PostMapping("/save") //添加
    public String save(@RequestBody Member member){
        Member result = memberRepository.save(member);
        if(result != null){
            return "success";
        }else {
            return "false";
        }
    }

    @GetMapping("/findById/{id}")
    public Member findById(@PathVariable("id") Integer id){

        return memberRepository.findById(id).get();
    }

    @GetMapping("/find_excel") //excel表格打印
    public List<Member> find_excel(){
        List<Member> list = memberRepository.findAll();
        return list;
    }

    @PutMapping("/update_a") //修改
    public String update(@RequestBody Member member) {
        Member result = memberRepository.save(member);
        if (result != null) {
            return "success";
        } else {
            return "false";
        }
    }
    @DeleteMapping("/deleteById/{id}") //删除
    public void deleteById(@PathVariable("id") Integer id){
        memberRepository.deleteById(id);
    }

}
