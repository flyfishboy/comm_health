package com.springvue.springboot1.controller;

import com.springvue.springboot1.ResponseBean;
import com.springvue.springboot1.entity.Picture;
import com.springvue.springboot1.entity.Reply;
import com.springvue.springboot1.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class PictureController {
    @Autowired
    private PictureRepository pictureRepository;

    @PostMapping("/person") //查询个人
    public ResponseBean Login(@RequestBody Picture picture) {

        List<Picture> list = pictureRepository.findAllByAccount(picture.getAccount());
        if (list.size() != 0) {
            return new ResponseBean(200,"查询成功",list);
        }else {
            return new ResponseBean(400, "查询失败","");
        }
    }


    @GetMapping("/findAll/{page}/{size}") //分页
    public Page<Picture> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageRequest request = PageRequest.of(page, size);
        return pictureRepository.findAll(request);
    }

    @PostMapping("/findAllByAccount/{page}/{size}") //个人查询动态分页
    public Page<Picture> findAllByAccount(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                          @RequestBody Picture picture) {
        PageRequest request = PageRequest.of(page, size);
        return pictureRepository.findAllByAccount(request,picture.getAccount());
    }

    @GetMapping("/find") //查找视频
    public List<Picture> find(@RequestBody Picture picture) {

        List<Picture> list = pictureRepository.findAllById(picture.getId());
        return list;
    }
//    @GetMapping("/find/{id}") //查询回复内容
//    public List<Picture> find(@PathVariable("id") Integer id) {
//        List<Picture> list = pictureRepository.findAllById(id);
//        return list;
//    }

        @GetMapping("/find/{vurl}") //查询视频内容
        public List<Picture> find(@PathVariable("vurl") String vurl) {
        List<Picture> list = pictureRepository.findByVurl(vurl);
        return list;
    }


    @PostMapping("/save") //添加
    public String save(@RequestBody Picture picture){
        Picture result = pictureRepository.save(picture);
        if(result != null){
            return "success";
        }else {
            return "false";
        }
    }


    @GetMapping("/findById/{id}")
    public Picture findById(@PathVariable("id") Integer id){

        return pictureRepository.findById(id).get();
    }

    @PutMapping("/update_a") //修改
    public String update(@RequestBody Picture picture) {
        Picture result = pictureRepository.save(picture);
        if (result != null) {
            return "success";
        } else {
            return "false";
        }
    }
    @DeleteMapping("/deleteById/{id}") //删除
    public void deleteById(@PathVariable("id") Integer id){
        pictureRepository.deleteById(id);
    }
}
