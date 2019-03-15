package com.github.lyrric;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SimpleTest {

    @Value("${test.value}")
    private String test;

    @Test
    public void test(){
        System.out.println("1");
    }
}
