package com.gurukula;

import org.testng.annotations.*;
import org.testng.Assert;

public class TestHelloWorld {
    @Test
    public void testAddition() {
        Assert.assertEquals((int) MyApp.add(1, 1), 2);
    }
}

class MyApp {

    public static Integer add(int a, int b) {
        return a + b;
    }

}
