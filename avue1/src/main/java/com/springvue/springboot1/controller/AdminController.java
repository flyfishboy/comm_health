package com.springvue.springboot1.controller;


import com.springvue.springboot1.ResponseBean;
import com.springvue.springboot1.entity.Admin;
import com.springvue.springboot1.entity.Picture;
import com.springvue.springboot1.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/findAll/{page}/{size}") //分页
    public Page<Admin> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest request = PageRequest.of(page, size);
        return adminRepository.findAll(request);
    }

    @PostMapping("/findAllByName/{page}/{size}") //模糊查找
    public Page<Admin> findAllByNameLike(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                          @RequestBody Admin admin) {
        System.out.println(admin);
        PageRequest request = PageRequest.of(page, size);
        return adminRepository.findByNameLike(request,"%"+admin.getName()+"%");
    }

    @PostMapping("/login") //登录
    public ResponseBean Login(@RequestBody Admin admin) {

        List<Admin> list = adminRepository.findAllByAccountAndPassword(admin.getAccount(), admin.getPassword());
        if (list.size() != 0) {
            return new ResponseBean(200,"登录成功",list);
        }else {
            return new ResponseBean(400, "登录失败","");
        }

    }


    @PostMapping("/save") //添加
    public String save(@RequestBody Admin admin){
        Admin result = adminRepository.save(admin);
        if(result != null){
            return "success";
        }else {
            return "false";
        }
    }

    @GetMapping("/findById/{id}")
    public Admin findById(@PathVariable("id") Integer id){

        return adminRepository.findById(id).get();
    }

    @GetMapping("/find_excel")
    public List<Admin> find_excel(){
        List<Admin> list = adminRepository.findAll();
        return list;
    }

    @PutMapping("/update_a") //修改
    public String update(@RequestBody Admin admin) {
        Admin result = adminRepository.save(admin);
        if (result != null) {
            return "success";
        } else {
            return "false";
        }
    }
    @DeleteMapping("/deleteById/{id}") //删除
    public void deleteById(@PathVariable("id") Integer id){
        adminRepository.deleteById(id);
    }

}
