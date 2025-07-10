package com.example.seniya_v2.service.implementations;

import com.example.seniya_v2.dto.participation.response.ParticipationCancelResponseDto;
import com.example.seniya_v2.dto.participation.response.ParticipationInfoResponseDto;
import com.example.seniya_v2.dto.participation.response.ParticipationResponseDto;
import com.example.seniya_v2.entity.Participation;
import com.example.seniya_v2.repository.ParticipationRepository;
import com.example.seniya_v2.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ParticipationResponseDto> getMyParticipation(String username) {

        List<Participation> participations = participationRepository.findAllByUser_Username(username);

        return participations.stream()
                .map(p -> new ParticipationResponseDto(
                        p.getParticipationId(),
                        p.getCourse().getCategory().name(),
                        p.getCourse().getTitle(),
                        p.getCourse().getDescription(),
                        p.getCourse().getTrainerProfile().getUser().getName(),
                        p.getCourse().getDate().toLocalDate(),
                        p.getCourse().getStartTime(),
                        p.getCourse().getEndTime(),
                        p.getCourse().getRoom()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ParticipationInfoResponseDto getParticipationInfo(String username, Long participationId) {

        Optional<Participation> participationOpt = participationRepository.findByParticipationIdAndUser_Username(participationId, username);

        Participation participation = participationOpt
                .orElseThrow(() -> new IllegalArgumentException("해당 수업 신청 정보를 찾을 수 없습니다."));

        return new ParticipationInfoResponseDto(
                participation.getCourse().getTitle(),
                participation.getCourse().getDescription(),
                participation.getCourse().getTrainerProfile().getUser().getName(),
                participation.getCourse().getDate().toLocalDate(),
                participation.getCourse().getStartTime(),
                participation.getCourse().getEndTime(),
                participation.getCourse().getRoom()
        );
    }

    @Override
    public ParticipationCancelResponseDto cancelParticipation(String username, Long participationId) {

        Optional<Participation> participationOpt = participationRepository.findByParticipationIdAndUser_Username(participationId, username);

        Participation participation = participationOpt
                .orElseThrow(() -> new IllegalArgumentException("해당 수업 신청 정보를 찾을 수 없습니다."));

        ParticipationInfoResponseDto info = new ParticipationInfoResponseDto(
                participation.getCourse().getTitle(),
                participation.getCourse().getDescription(),
                participation.getCourse().getTrainerProfile().getUser().getName(),
                participation.getCourse().getDate().toLocalDate(),
                participation.getCourse().getStartTime(),
                participation.getCourse().getEndTime(),
                participation.getCourse().getRoom()
        );

        participationRepository.delete(participation);

        return new ParticipationCancelResponseDto("수업이 성공적으로 취소되었습니다.", info);
    }
}