package com.example.seniya_v2.entity;

import com.example.seniya_v2.common.enums.uploadFile.TargetType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "upload_files")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UploadFile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_file_id", nullable = false)
    private Long uploadFileId;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "file_size", nullable = false)
    private long fileSize;

    private Long  targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;
}
