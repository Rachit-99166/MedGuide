package com.MedGuide.MedicalAssistance.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MedGuide.MedicalAssistance.Entity.Upload;
import com.MedGuide.MedicalAssistance.Repository.UploadRepo;

@Service
public class UploadService {

    @Autowired
    private UploadRepo uploadRepository;

    public void saveFile(MultipartFile multipartFile) throws IOException {
        Upload upload = new Upload();
        upload.setFileName(multipartFile.getOriginalFilename());
        upload.setFile(multipartFile.getBytes());
        uploadRepository.save(upload);
    }
}
