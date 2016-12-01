package com.gurukula.poc;

import org.testng.Assert;

public class TestHelloWorld {
    public void testAddition() {
        Assert.assertEquals((int) MyApp.add(1, 1), 2);
    }
}

class MyApp {

    public static Integer add(int a, int b) {
        return a + b;
    }

}
