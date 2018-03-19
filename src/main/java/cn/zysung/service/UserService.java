package cn.zysung.service;

import cn.zysung.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> selectTop10Thanks();
    Map<String,Long> selectSexNums();
    Map<String,Long> selectTop10Education();
    int getUserCount();
}
