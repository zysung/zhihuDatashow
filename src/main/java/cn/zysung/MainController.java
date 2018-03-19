package cn.zysung;

import cn.zysung.service.UserService;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zysung on 2017/12/12.
 */

@SpringBootApplication
@MapperScan("cn.zysung.mapper")
@EnableCaching
public class MainController {


    public static void main(String[] args) {
        SpringApplication.run(MainController.class,args);
    }
}
