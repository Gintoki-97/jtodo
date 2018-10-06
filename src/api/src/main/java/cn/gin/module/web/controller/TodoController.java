package cn.gin.module.web.controller;

import cn.gin.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constants.Path.CTRL_TODO)
public class TodoController {

    @GetMapping({Constants.Symbol.EMPTY, Constants.Path.CTRL_TODO_INDEX})
    public String index() {

        return "todo";
    }

}
