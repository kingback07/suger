package org.kingback.suger.explorer.machineLearning.singleneuro;

/**
 * 隐藏神经元接口，包含主要隐藏层的功能
 */
public interface IHiddenLayer {

    //针对输入的结果输出，后向传递
    double[] forward(double[] input);

    //提供结构供后项反馈误差值，并依据此误差值计算梯度
    double[][] backward(double[][] wNext, double[][] hiddenOut, double[][] delta, double learningRate);

    //提供接口供误差反馈，用于多层隐藏层
    double[][] backward(IHiddenLayer hlayer, double[][] delta, double learningRate);

    //获取隐藏层的输出
    double[] output(double[] input);

    //获取分类结果数
    int getClassfiedNum();

    //获取入参维度
    int getInputD();

    //获取隐藏层的线性参数
    double[][] getWoi();

    //获取训练集数据
    double[][] getTrainingData();


}

