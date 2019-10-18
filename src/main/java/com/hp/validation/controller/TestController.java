package com.hp.validation.controller;

import com.hp.validation.model.Student;
import com.hp.validation.model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 测试Controller
 *
 * @author hupan
 * @date 2019/10/18
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @ResponseBody
    @GetMapping("/valid")
    public Result test(@Valid Student student) {
        return Result.success();
    }

}
