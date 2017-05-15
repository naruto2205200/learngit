package com.example.util;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by naruto on 2017/5/15.
 */
@Configuration
public class MybatisConfiguration {
    public PageHelper pageHelper(){
        PageHelper pageHelper =new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
