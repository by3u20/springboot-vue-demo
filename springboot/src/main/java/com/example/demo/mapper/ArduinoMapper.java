package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.Arduino;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArduinoMapper extends BaseMapper<Arduino> {

    List<Arduino> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
}
