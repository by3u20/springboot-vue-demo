package com.example.demo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Arduino;
import com.example.demo.mapper.ArduinoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/arduino")

public class ArduinoController {

    @Resource
    ArduinoMapper ArduinoMapper;

    @GetMapping
    public List<?> save(){
        QueryWrapper<Arduino> temper = new QueryWrapper<>();
        temper.select("temperature");
        List<Map<String,Object>> userList = ArduinoMapper.selectMaps(temper);   //choose column data
        userList.forEach(System.out::println);
//        List<Arduino> res = ArduinoMapper.selectList(null);//choose all data
        return userList;
    }


    @GetMapping(value = "/{dateTime}/{switchSignal}/{accSignal}")
    public String getTime(@PathVariable("dateTime") String dateTime,
                          @PathVariable("switchSignal")  String switchSignal,
                          @PathVariable("accSignal")  String accSignal) {
//        System.out.println(dateTime);
        Date date = DateUtil.parse(dateTime);
        String format = DateUtil.format(date, "yyyy-MM-dd   HH:mm:ss");
        System.out.println(format);
        System.out.print("The opened switch number is ");
        System.out.println(switchSignal);
        System.out.print("The Accelerometer Status is ");
        System.out.println(accSignal);
        return "#1";
    }

//    @GetMapping(value="/{temp}")
//    public String addUser(@PathVariable String temp) {
//        Arduino arduino = new Arduino(temp);
//        System.out.println("Temperature is:"+temp);
//        ArduinoMapper.insert(arduino);
//        return "Add successfully";
//    }

}
