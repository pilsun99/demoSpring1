package niit.com.vn.demospring1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

@Controller("uploadFileController")
public class UploadFileController {
    @Value("${UPLOAD_DIR}")
    public String uploadDir;
    @GetMapping("/upload-file")
    public String uploadFile(){
        return "upload-file";
    }
    @PostMapping("/upload-file-handler")

    public  String uploadFileHandler(@RequestParam("img")MultipartFile file){
        try {
            Calendar calander = Calendar.getInstance();
            int year = calander.get(Calendar.YEAR); //lấy ve nam
            int month = calander.get(Calendar.MONTH) + 1; // lấy về tháng
            String newDir = month + "_" + year; //7_2023
            //check xem thư mục đã tồn tại chưa
            File folder = new File(uploadDir + newDir);
            if (!folder.exists()|| folder.isFile()){
                folder.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(uploadDir + newDir + File.separator + System.currentTimeMillis()+ file.getOriginalFilename());
            fileOutputStream.write(file.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:upload-file";
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> serveFile(@RequestParam("file-name") String fileName){
        try {
            FileInputStream fileInputStream = new FileInputStream(uploadDir + "7_2023/1690174035179Screenshot_20221221_111656.png");
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
