package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("cron")
@Data

public class Cron {
    @TableId(type = IdType.AUTO)
    private Integer cronId;
    private String sundayCron;
    private String mondayCron;
    private String tuesdayCron;
    private String wednesdayCron;
    private String thursdayCron;
    private String fridayCron;
    private String saturdayCron;


}