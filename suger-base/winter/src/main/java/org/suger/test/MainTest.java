package org.suger.test;

import org.suger.test.domain.Car;
import org.suger.winter.annotation.ComponetScan;
import org.suger.winter.bean.AnnotaionApplicationContext;

/**
 * summer 配置入口类
 * Created on 2021-02-03
 */
@ComponetScan("org.suger.test.domain")
public class MainTest {
    public static void main(String[] args) {
        AnnotaionApplicationContext annotaionApplicationContext = new AnnotaionApplicationContext(MainTest.class);
        Car car = (Car) annotaionApplicationContext.getBean("Ferrari");
        car.showCarDes();
    }
}
