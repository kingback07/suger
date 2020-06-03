package org.kingback.suger.explorer.machineLearning.util;

public class PredictResultInfo {

    private int clsIndex;//分类编码
    private double percentage;//回归概率

    public int getClsIndex() {
        return clsIndex;
    }

    public PredictResultInfo(int idx, double p) {
        clsIndex = idx;
        percentage = p;
    }

    @Override
    public String toString() {

        String status = clsIndex == 0 ? "正常" : "异常";
        return String.format("预测结果，状态:%s,概率:%.2f", status, percentage);


    }
}
