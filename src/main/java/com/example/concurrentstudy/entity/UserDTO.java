package com.example.concurrentstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Project: concurrentstudy
 * @Description:
 * @Author: renxiang
 * @Date: 2022/11/15 0:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;


}
