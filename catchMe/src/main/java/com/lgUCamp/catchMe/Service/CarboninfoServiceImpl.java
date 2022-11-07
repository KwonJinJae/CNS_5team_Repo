package com.lgUCamp.catchMe.Service;

import com.lgUCamp.catchMe.DTO.CarbonInfoDTO;
import com.lgUCamp.catchMe.Mapper.CarbonInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarboninfoServiceImpl implements CarbonInfoService{

    @Autowired
    CarbonInfoMapper carbonInfoMapper;

    public ArrayList<CarbonInfoDTO> carbonInfoList(){

        return carbonInfoMapper.carbonInfoList();
    }
}
