package com.springboot.cache.proxy;

public class TargetStar implements Star {
    @Override
    public void sing() {
        System.out.println("sing sing sing");
    }

    @Override
    public int dance() {
        return 0;
    }
}
