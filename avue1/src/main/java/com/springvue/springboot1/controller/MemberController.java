package com.springvue.springboot1.controller;

import com.springvue.springboot1.ResponseBean;
import com.springvue.springboot1.entity.Admin;
import com.springvue.springboot1.entity.Member;
import com.springvue.springboot1.entity.Reply;
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

    //分页
    @GetMapping("/findAll/{page}/{size}")
    public Page<Member> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest request = PageRequest.of(page, size);
        return memberRepository.findAll(request);
    }

    //模糊查找
    @PostMapping("/findAllByName/{page}/{size}")
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
    @GetMapping("/findById/{id}")
    public Member findById(@PathVariable("id") Integer id){
        return memberRepository.findById(id).get();
    }

    // 个人中心-个人信息
    @GetMapping("/findByAccount3/{account}")
    public List<Member> findByAccount3(@PathVariable("account") String account){
        List<Member> list = memberRepository.findAllByAccount(account);
        return list;
    }

    //查询所有用户数据
    @PostMapping("/findAll2")
    public List<Member> find(){
        List<Member> list = memberRepository.findAll();
        return list;
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

    // 个人中心-个人信息
    @PostMapping("/findAllByAccount/{account}")
    public List<Member> findByAccount(@PathVariable("account") String account){
        List<Member> list = memberRepository.findAllByAccount(account);
        return list;
    }

    // 注册验证
    @PostMapping("/findAllByAccount2/{account}")
    public String findByAccount2(@PathVariable("account") String account){
        List<Member> list = memberRepository.findAllByAccount(account);
        if(list.size() != 0){
            return "success";
        }else {
            return "false";
        }
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
