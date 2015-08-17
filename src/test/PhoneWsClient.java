package test;

import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;

public class PhoneWsClient {

    public static void main(String[] args) {
      //创建一个MobileCodeWS工厂
      MobileCodeWS factory = new MobileCodeWS();
      //根据工厂创建一个MobileCodeWSSoap对象
      MobileCodeWSSoap mobileCodeWSSoap = factory.getMobileCodeWSSoap();
      //调用WebService提供的getMobileCodeInfo方法查询手机号码的归属地
      String searchResult = mobileCodeWSSoap.getMobileCodeInfo("13770623471", null);
      System.out.println(searchResult);

    }

}
