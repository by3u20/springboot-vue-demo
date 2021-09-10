package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Cron;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CronMapper extends BaseMapper<Cron> {

    @Select(value = "select ${dayOfWeek} from cron")
    public List<String> getCron(@Param("dayOfWeek") String dayOfWeek);
}
