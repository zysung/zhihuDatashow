package cn.zysung.service.impl;

import cn.zysung.mapper.UserMapper;
import cn.zysung.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> selectTop10Thanks() {
        return userMapper.selectTop10Thanks();
    }

    @Override
    public int getUserCount() {
        return userMapper.getUserCount();
    }
}
