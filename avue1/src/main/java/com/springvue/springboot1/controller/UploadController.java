package com.springvue.springboot1.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.springvue.springboot1.entity.Upload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RestController
@CrossOrigin
public class UploadController {
    @Autowired
    private FastFileStorageClient storageClient;
    Map<String,Object> result = new HashMap<>();
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String ,Object> upload(@RequestParam("myFile") MultipartFile myFile) throws IOException {
        String extension = FilenameUtils.getExtension(myFile.getOriginalFilename());
        //将要上传的文件存入FastDFS
        StorePath sp=storageClient.uploadFile("group1",myFile.getInputStream(),myFile.getSize(),extension);
        //StorePath sp = storageClient.uploadFile(myFile.getInputStream(),myFile.getSize(), FilenameUtils.getExtension(myFile.getOriginalFilename()),null);
        Upload upload = new Upload();
        upload.setName("group1");
        upload.setFilename(myFile.getOriginalFilename());
        upload.setFilepath(sp.getPath());
        //fileUrlService.addFileUrl(fileUrl);
        System.out.println(upload);
        System.out.println(sp.getFullPath());
        result.put("data",sp.getFullPath());
        return result;
    }
}
