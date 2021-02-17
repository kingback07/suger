package org.suger.test.domain;

import org.suger.winter.annotation.Componet;

/**
 * @author wangyang09
 * Created on 2021-02-03
 */
@Componet("Ferrari")
public class FerrariCar implements Car {

    private Engine engine;
    private Wheel wheel;

    private final String carBrand = "FALARI";

    public void showCarDes() {
        String description =
                String.format("car brand is %s,the engine type is %s,and the wheel use %s", carBrand, engine.engineType(), wheel.wheelBrand());
        System.out.println(description);
    }

}
