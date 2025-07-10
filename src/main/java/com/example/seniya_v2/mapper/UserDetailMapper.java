package com.example.seniya_v2.mapper;

import com.example.seniya_v2.dto.admin.user.response.GetUserDetailFlatRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDetailMapper {
    List<GetUserDetailFlatRow> getUserDetail(@Param("userId") Long userId);
}
