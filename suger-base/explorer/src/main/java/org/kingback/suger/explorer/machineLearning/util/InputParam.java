package org.kingback.suger.explorer.machineLearning.util;

/**
 * 输入变量定义
 */
public class InputParam {

    private int unitCode;//定义入参的唯一标示
    double[] featureVector;//特征向量

    public InputParam(int code, double[] vector) {
        unitCode = code;
        int featureD = vector.length;
        featureVector = new double[featureD];
        for (int i = 0; i < featureD; i++) {
            featureVector[i] = vector[i];
        }
    }

    public int getUnitCode() {
        return unitCode;
    }

    public double[] getFeatureVector() {
        return featureVector;
    }
}
