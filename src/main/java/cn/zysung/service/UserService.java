package cn.zysung.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String,Object>> selectTop10Thanks();
    int getUserCount();
}
