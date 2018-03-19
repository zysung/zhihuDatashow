package cn.zysung.service.impl;

import cn.zysung.entity.User;
import cn.zysung.mapper.UserMapper;
import cn.zysung.service.RedisService;
import cn.zysung.service.UserService;
import cn.zysung.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    @Cacheable(value = "thanksTop10")
    public List<User> selectTop10Thanks() {
//        List<User> users = null;
//        if((users = (List<User>) redisService.get("thanksTop10"))==null){
//
//            List<User> thanks = userMapper.selectTop10Thanks();
//            redisService.set("thanksTop10", thanks);
//        }
//        logger.info("set redis");
//        return users;
            return userMapper.selectTop10Thanks();
    }

    @Override
    @Cacheable(value = "selectSexNums")
    public Map<String, Long> selectSexNums() {
        List<Map<Object,Object>> listMap = userMapper.selectSexNums();
        Map<String,Long> sexMap = new HashMap<String,Long>();

        for (Map<Object,Object> map: listMap
             ) {
            String sexType = (String) map.get("sex");
            long number = (Long) map.get("count");
            if(sexType == null || "".equals(sexType)){
                sexType = "nowritesex";
            }
            logger.info(sexType+":"+number);
            sexMap.put(sexType, number);
        }
        return sexMap;
    }

    @Override
    @Cacheable(value = "userCount")
    public int getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    @Cacheable(value = "selectTop10Edu")
    public Map<String, Long> selectTop10Education() {
        List<Map<Object,Object>> listMap = userMapper.selectTop10Education();
        Map<String,Long> eduMap = new TreeMap<String,Long>() ;


        for (Map<Object,Object> map: listMap
                ) {
           String edu = (String)map.get("education");
           Long count = (Long) map.get("count");
           eduMap.put(edu,count);
        }
        eduMap = MapUtil.sortMapByValueDesc(eduMap);

        return eduMap;
    }
}
