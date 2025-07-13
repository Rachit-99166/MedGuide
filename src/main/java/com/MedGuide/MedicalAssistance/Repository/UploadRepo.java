package com.MedGuide.MedicalAssistance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MedGuide.MedicalAssistance.Entity.Upload;

@Repository
public interface UploadRepo extends JpaRepository<Upload, Integer> {

}
