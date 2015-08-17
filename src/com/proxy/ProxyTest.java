package com.proxy;


public class ProxyTest {

    public static void main(String[] args) {
        LiuDeHuaProxy proxy = new LiuDeHuaProxy();
        //获得代理对象
        Person person = proxy.getProxy();
        //调用代理对象的sing方法
        String retValue = person.sing("冰雨");
        System.out.println(retValue);
        //调用代理对象的dance方法
        String value = person.dance("江南style");
        System.out.println(value);
    }

}
