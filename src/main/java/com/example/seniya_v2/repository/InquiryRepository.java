package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.Inquiry;
import com.example.seniya_v2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> getInquiriesByUser(User user);
}
