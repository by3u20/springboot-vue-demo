package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("arduino")
@Data

public class Arduino {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String temperature;


    public Arduino(String temp) {
        this.temperature = temp;

    }

}