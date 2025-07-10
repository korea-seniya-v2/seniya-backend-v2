package com.example.seniya_v2.repository;

import com.example.seniya_v2.entity.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("SELECT n FROM Notice n ORDER BY n.pinned DESC, n.createdAt DESC")
    List<Notice> findNoticesWithPriority(Pageable pageable);
}
