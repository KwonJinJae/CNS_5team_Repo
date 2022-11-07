package com.lgUCamp.catchMe.Mapper;

import com.lgUCamp.catchMe.DTO.CarbonInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CarbonInfoMapper {

    ArrayList<CarbonInfoDTO> carbonInfoList();
}
