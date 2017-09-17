package com.github.msy.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by l.zhao on 17-9-17.
 * Desp TODO.
 */
@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping(value = "index", method= RequestMethod.GET)
    public String index() {
        JSONObject json = new JSONObject();
        json.put("total", 2);
        json.put("code", 200);
        JSONObject action = new JSONObject();
        action.put("id", 1);
        action.put("region", "aaa");

        JSONObject action2 = new JSONObject();
        action2.put("id", 11);
        action2.put("_parentId", 1);
        action2.put("region", "abc");

        json.put("rows", Arrays.asList(action, action2));

        return json.toJSONString();
    }
}
