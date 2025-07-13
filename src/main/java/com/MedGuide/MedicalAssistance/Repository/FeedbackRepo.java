package com.MedGuide.MedicalAssistance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MedGuide.MedicalAssistance.Entity.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

}
