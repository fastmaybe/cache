package com.springboot.cache.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {
    public static void main(String[] args) {
//        Star targetStar = new TargetStar();
//        Star star = (Star) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), TargetStar.class.getInterfaces(), (proxy, method, args1) -> {
//            if (method.getName().equals("sing")) {
//                System.out.println("唱");
//            } else {
//                System.out.println("跳");
//            }
//            Object invoke = method.invoke(targetStar, args1);
//            System.out.println(invoke);
//            return invoke;
//        });
//        star.sing();
//        star.dance();


        Enhancer enhancer= new Enhancer();
        Star star = (Star) enhancer.create(TargetStar.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object invoke = methodProxy.invokeSuper(o, objects);
                return invoke;
            }
        });
        star.sing();
    }
}
