package com.example.seniya_v2.repository;

import com.example.seniya_v2.common.enums.payment.Status;
import com.example.seniya_v2.entity.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<Pass, Long> {
    List<Pass> findAllByUserUsernameAndPaymentStatus(String username, Status status);
}
