package com.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
* @ClassName: LiuDeHuaProxy
* @Description: 这个代理类负责生成刘德华的代理人
* @author:  
* @date:  
*/ 
public class LiuDeHuaProxy {
    //设计一个类变量记住代理类要代理的目标对象
    private Person ldh = new LiuDeHua();
    
    /**
     * 设计一个方法生成代理对象
     * @Method: getProxy
     * @Description: 这个方法返回刘德华的代理对象：Person person = LiuDeHuaProxy.getProxy();//得到一个代理对象
     * @Anthor:孤傲苍狼
     *
     * @return 某个对象的代理对象
     */ 
     public Person getProxy() {
         //使用Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)返回某个对象的代理对象
         return (Person) Proxy.newProxyInstance(
                 LiuDeHuaProxy.class.getClassLoader(),
                 ldh.getClass().getInterfaces(),
                 new InvocationHandler() {
             /**
              * InvocationHandler接口只定义了一个invoke方法，因此对于这样的接口，我们不用单独去定义一个类来实现该接口，
              * 而是直接使用一个匿名内部类来实现该接口，new InvocationHandler() {}就是针对InvocationHandler接口的匿名实现类
              * 在invoke方法编码指定返回的代理对象干的工作
              * proxy : 把代理对象自己传递进来 
              * method：把代理对象当前调用的方法传递进来 
              * args:把方法参数传递进来
              * 
              * 当调用代理对象的person.sing("冰雨");或者 person.dance("江南style");方法时，
              * 实际上执行的都是invoke方法里面的代码，
              * 因此我们可以在invoke方法中使用method.getName()就可以知道当前调用的是代理对象的哪个方法
              */
             @Override
             public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {
                 //如果调用的是代理对象的sing方法
                 if (method.getName().equals("sing")) {
                     System.out.println("我是他的经纪人，要找他唱歌得先给十万块钱！！");
                     //已经给钱了，经纪人自己不会唱歌，就只能找刘德华去唱歌！
                     return method.invoke(ldh, args); //代理对象调用真实目标对象的sing方法去处理用户请求
                 }
                 //如果调用的是代理对象的dance方法
                 if (method.getName().equals("dance")) {
                     System.out.println("我是他的经纪人，要找他跳舞得先给二十万块钱！！");
                     //已经给钱了，经纪人自己不会唱歌，就只能找刘德华去跳舞！
                     return method.invoke(ldh, args);//代理对象调用真实目标对象的dance方法去处理用户请求
                 }
                 return null;
             }
         });
     }
}
