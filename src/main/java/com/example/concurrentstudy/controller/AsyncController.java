package com.example.concurrentstudy.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.concurrentstudy.entity.UserDTO;
import com.example.concurrentstudy.service.AsyncService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 18:03
 */
@RestController()
@RequestMapping("/123")
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/a")
    private void hello() {
        asyncService.testAsync(1000, 0);
    }

    @GetMapping("/b")
    private void testEvent() {
        asyncService.testEvent();
    }

    @PostMapping("/c")
    private void test(@RequestBody UserDTO request) throws InterruptedException, JSONException {
        asyncService.test(request);
    }

    @PostMapping("/d")
    private void testJson(@RequestBody JSONObject request) throws InterruptedException, JSONException {
        asyncService.testJson(request);
    }

}
