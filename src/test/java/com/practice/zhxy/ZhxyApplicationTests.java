package com.practice.zhxy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhxyApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    @Test
    void test1(){
        int i = 0;
        int a =1;
        switch (a){
            case 1: i=9;

        }
        Assert.assertEquals(i,9);
    }

}
