package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Calendar;
import java.util.List;

@Controller("uploadFileController")
public class UploadFileController {
    @Value("${UPLOAD_DIR}")
    public String uploadDir;
    @Autowired
    private ImageService imageService;
    @GetMapping("/upload-file")
    public String uploadFile() {
        return "upload-file";
    }
    //add image - post
    @PostMapping("/add")

    public String ImagePost(@RequestParam("image") MultipartFile file) {
        try {
            Calendar calander = Calendar.getInstance();
            int year = calander.get(Calendar.YEAR); //lấy ve nam
            int month = calander.get(Calendar.MONTH) + 1; // lấy về tháng
            String newDir = month + "_" + year; //7_2023
            //check xem thư mục đã tồn tại chưa
            File folder = new File(uploadDir + newDir);
            if (!folder.exists() || folder.isFile()) {
                folder.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(uploadDir + newDir + File.separator + System.currentTimeMillis() + file.getOriginalFilename());
            fileOutputStream.write(file.getBytes());
            byte[] bytes = file.getBytes();
            Blob blob = new SerialBlob(bytes);
            Image image = new Image();
            image.setImage(blob);
            imageService.create(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    // display image
    @GetMapping("/file")
    public ResponseEntity<byte[]> serveFile(@RequestParam("id") long id){
        try {
            Image image = imageService.viewById(id);
            byte[] imageBytes = null;
            imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //view all image
    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("upload-file");
        List<Image> imageList = imageService.viewAll(); 
        modelAndView.addObject("imageList", imageList);
        return modelAndView;
    }
    // add image - get
    @GetMapping("/add")
    public ModelAndView addImage(){
        return new ModelAndView("addImage");
    }



}
