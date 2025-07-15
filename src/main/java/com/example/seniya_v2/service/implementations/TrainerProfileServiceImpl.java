package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.common.constants.ResponseCode;
import com.example.seniya_v2.common.constants.ResponseMessage;
import com.example.seniya_v2.common.enums.uploadFile.TargetType;
import com.example.seniya_v2.dto.ResponseDto;
import com.example.seniya_v2.dto.trainer.request.TrainerProfileRequestDto;
import com.example.seniya_v2.dto.trainer.request.UpdateTrainerProfileRequestDto;
import com.example.seniya_v2.dto.trainer.response.CertificateResponseDto;
import com.example.seniya_v2.dto.trainer.response.PopularTrainerResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerProfileCreateResponseDto;
import com.example.seniya_v2.dto.trainer.response.TrainerProfileResponseDto;
import com.example.seniya_v2.entity.Certificate;
import com.example.seniya_v2.entity.TrainerProfile;
import com.example.seniya_v2.entity.UploadFile;
import com.example.seniya_v2.entity.User;
import com.example.seniya_v2.mapper.PopularTrainerMapper;
import com.example.seniya_v2.repository.TrainerProfileRepository;
import com.example.seniya_v2.repository.UploadFileRepository;
import com.example.seniya_v2.repository.UserRepository;
import com.example.seniya_v2.service.TrainerProfileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerProfileServiceImpl implements TrainerProfileService {
    private final UserRepository userRepository;
    private final TrainerProfileRepository trainerProfileRepository;
    private final PopularTrainerMapper popularTrainerMapper;

    private final UploadFileRepository fileRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public ResponseDto<TrainerProfileCreateResponseDto> createProfile(String username, TrainerProfileRequestDto dto, MultipartFile file) throws NoPermissionException, IOException {
        TrainerProfileCreateResponseDto responseDto = null;

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        String roleName = user.getRole().getRoleName();

        if (!roleName.equals("TRAINER")) {
            throw new NoPermissionException(ResponseMessage.NO_PERMISSION);
        }

        TrainerProfile trainer = trainerProfileRepository.findByUser(user);

        if (!(trainer == null)) {
            throw new IllegalArgumentException(ResponseMessage.FAILED);
        }

        TrainerProfile newTrainerProfile = TrainerProfile.builder()
                .user(user)
                .specialty(dto.getSpecialty())
                .experienceYears(dto.getExperienceYears())
                .description(dto.getDescription())
                .build();

        List<Certificate> certificates = dto.getCertificates().stream()
                .map(certDto -> Certificate.builder()
                        .certificate(certDto.getCertificate())
                        .certificationDate(certDto.getCertificationDate())
                        .trainerProfile(newTrainerProfile)
                        .build()
                ).collect(Collectors.toList());

        newTrainerProfile.setCertificates(certificates);

        trainerProfileRepository.save(newTrainerProfile);

        List<CertificateResponseDto> certDtos = newTrainerProfile.getCertificates().stream()
                .map(cert -> CertificateResponseDto.builder()
                        .certificate(cert.getCertificate())
                        .certificationDate(cert.getCertificationDate())
                        .build()
                ).collect(Collectors.toList());

        if (file != null && !file.isEmpty()) {
            saveFile(file, newTrainerProfile.getTrainerId(), TargetType.TRAINER_PROFILE);
        }

        String profileImageUrl = null;
        Optional<UploadFile> profileUrl = fileRepo.findFirstByTargetIdAndTargetType(newTrainerProfile.getTrainerId(), TargetType.TRAINER_PROFILE);
        if (profileUrl.isPresent()) {
            profileImageUrl = profileUrl.get().getFilePath();
        }

        responseDto = TrainerProfileCreateResponseDto.builder()
                .name(newTrainerProfile.getUser().getName())
                .specialty(newTrainerProfile.getSpecialty())
                .certificates(certDtos)
                .experienceYears(newTrainerProfile.getExperienceYears())
                .description(newTrainerProfile.getDescription())
                .profileImageUrl(profileImageUrl)
                .createdAt(newTrainerProfile.getCreatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    @Override
    public ResponseDto<TrainerProfileResponseDto> getTrainerProfile(String username) throws NoPermissionException {
        TrainerProfileResponseDto responseDto = null;

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));
        String roleName = user.getRole().getRoleName();
        if (!roleName.equals("TRAINER")) {
            throw new NoPermissionException(ResponseMessage.NO_PERMISSION);
        }

        TrainerProfile trainer = trainerProfileRepository.findByUser(user);

        List<CertificateResponseDto> certDtos = trainer.getCertificates().stream()
                .map(cert -> CertificateResponseDto.builder()
                        .certificate(cert.getCertificate())
                        .certificationDate(cert.getCertificationDate())
                        .build())
                .collect(Collectors.toList());

        String profileImageUrl = null;

        Optional<UploadFile> profileUrl = fileRepo.findFirstByTargetIdAndTargetType(trainer.getTrainerId(), TargetType.TRAINER_PROFILE);

        if (profileUrl.isPresent()) {
            profileImageUrl = profileUrl.get().getFilePath();
        }
        responseDto = TrainerProfileResponseDto.builder()
                .name(trainer.getUser().getName())
                .specialty(trainer.getSpecialty())
                .certificates(certDtos)
                .experienceYears(trainer.getExperienceYears())
                .description(trainer.getDescription())
                .profileImageUrl(profileImageUrl)
                .createdAt(trainer.getCreatedAt())
                .updatedAt(trainer.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    @Override
    public ResponseDto<TrainerProfileResponseDto> updateProfile(String username, UpdateTrainerProfileRequestDto dto, MultipartFile file) throws NoPermissionException, IOException {
        TrainerProfileResponseDto responseDto = null;

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.USER_NOT_FOUND));

        String roleName = user.getRole().getRoleName();

        if (!roleName.equals("TRAINER")) {
            throw new NoPermissionException(ResponseMessage.NO_PERMISSION);
        }

        TrainerProfile trainer = trainerProfileRepository.findByUser(user);

        trainer.getCertificates().clear();
        List<Certificate> certificates = dto.getCertificates().stream()
                .map(certification -> Certificate.builder()
                        .certificate(certification.getCertificate())
                        .certificationDate(certification.getCertificationDate())
                        .trainerProfile(trainer)
                        .build())
                .collect(Collectors.toList());

        trainer.getCertificates().addAll(certificates);
        trainer.setSpecialty(dto.getSpecialty());
        trainer.setExperienceYears(dto.getExperienceYears());
        trainer.setDescription(dto.getDescription());
        trainerProfileRepository.save(trainer);

        String profileImageUrl = null;
        Optional<UploadFile> existingProfileImage = fileRepo.findFirstByTargetIdAndTargetType(trainer.getTrainerId(), TargetType.TRAINER_PROFILE);

        if (file != null && !file.isEmpty()) {
            existingProfileImage.ifPresent(oldFile -> {
                File oldPhysicalFile = new File(uploadDir + File.separator + oldFile.getFileName());
                if (oldPhysicalFile.exists()) {
                    oldPhysicalFile.delete();
                }
                fileRepo.delete(oldFile);
            });
            saveFile(file, trainer.getTrainerId(), TargetType.TRAINER_PROFILE);
            Optional<UploadFile> newFile = fileRepo.findFirstByTargetIdAndTargetType(trainer.getTrainerId(), TargetType.TRAINER_PROFILE);
            if (newFile.isPresent()) {
                profileImageUrl = newFile.get().getFilePath();
            }
        } else if (dto.isRemoveProfileImage()) {
            existingProfileImage.ifPresent(oldFile -> {
                File oldPhysicalFile = new File(uploadDir + File.separator + oldFile.getFileName());
                if (oldPhysicalFile.exists()) {
                    oldPhysicalFile.delete();
                }
                fileRepo.delete(oldFile);
            });
            profileImageUrl = null;
        } else {
            profileImageUrl = existingProfileImage.map(UploadFile::getFilePath).orElse(null);
        }
        trainerProfileRepository.save(trainer);
        List<CertificateResponseDto> certDtos = trainer.getCertificates().stream()
                .map(cert -> CertificateResponseDto.builder()
                        .certificate(cert.getCertificate())
                        .certificationDate(cert.getCertificationDate())
                        .build())
                .collect(Collectors.toList());

        responseDto = TrainerProfileResponseDto.builder()
                .name(trainer.getUser().getName())
                .specialty(trainer.getSpecialty())
                .certificates(certDtos)
                .experienceYears(trainer.getExperienceYears())
                .description(trainer.getDescription())
                .profileImageUrl(profileImageUrl)
                .createdAt(trainer.getCreatedAt())
                .updatedAt(trainer.getUpdatedAt())
                .build();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    @Override
    public ResponseDto<PopularTrainerResponseDto> popularTrainer() {
        PopularTrainerResponseDto responseDto = null;

        responseDto = popularTrainerMapper.findPopularTrainer();

        return ResponseDto.success(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, responseDto).getBody();
    }

    private void saveFile(MultipartFile file, Long targetId, TargetType type) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String original = file.getOriginalFilename();
        String uuid = UUID.randomUUID() + "_" + original;
        file.transferTo(new File(uploadDir + "/" + uuid));

        UploadFile uf = UploadFile.builder()
                .originalName(original)
                .fileName(uuid)
                .filePath("/files/" + uuid)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .targetId(targetId)
                .targetType(type)
                .build();
        fileRepo.save(uf);
    }
}
