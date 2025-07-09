package com.example.seniya_v2.mapper;

import com.example.seniya_v2.dto.trainer.response.PopularTrainerResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PopularTrainerMapper {
    PopularTrainerResponseDto findPopularTrainer();
}
