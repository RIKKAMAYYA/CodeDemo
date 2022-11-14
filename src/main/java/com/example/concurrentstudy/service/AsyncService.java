package com.example.concurrentstudy.service;

import com.alibaba.fastjson.JSONObject;
import com.example.concurrentstudy.entity.UserDTO;
import org.json.JSONException;

import java.util.concurrent.Future;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/14 15:50
 */
public interface AsyncService {
    Future<Integer> testAsync(int num, int sum);

    void testEvent();

    void test(UserDTO request) throws InterruptedException, JSONException;

    void testJson(JSONObject request) throws InterruptedException;

}
