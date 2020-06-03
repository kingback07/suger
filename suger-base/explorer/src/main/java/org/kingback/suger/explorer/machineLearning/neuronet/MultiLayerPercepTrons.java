package org.kingback.suger.explorer.machineLearning.neuronet;

import org.kingback.suger.explorer.machineLearning.singleneuro.HiddenLayer;
import org.kingback.suger.explorer.machineLearning.util.InputParam;
import org.kingback.suger.explorer.machineLearning.util.PredictResultInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kingback.suger.explorer.machineLearning.util.ActivationFunction.softmax;

/**
 * MultiLayerPercepTrons(MLP) 多层感知器
 * 对隐藏层的输出结果执行激活函数操作分类
 */
public class MultiLayerPercepTrons {
    int finalOutputNum;

    Map<Integer, Double> classfiedInfo = new HashMap<>();

    List<HiddenLayer> hiddenLayers = new ArrayList<>();//隐藏层可能会由多个隐藏层节点构成

    List<InputParam> trainList = new ArrayList<>();
    Map<Integer, double[]> trainRes = new HashMap<>();

    private int trainSize = 0;
    private int minibatch_size = 10;//默认设置小批量数据大小为10条一组

    private double[][] Wkj;
    private double[] Bk;
    //    private int nIn;
//    private int nOut;
    private int nHiddenOut;
    private double learningRate;

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public MultiLayerPercepTrons(int nIn, int nOut, int singlehiddenOut, int batchSize) {
        HiddenLayer hiddenLayer = new HiddenLayer(nIn, singlehiddenOut, null, null, null, null);
        this.hiddenLayers.add(hiddenLayer);
//        this.nIn = nIn;
//        this.nOut = nOut;
        finalOutputNum = nOut;
        nHiddenOut = singlehiddenOut;

        Wkj = new double[nHiddenOut][nOut];
        Bk = new double[nOut];

        minibatch_size = batchSize;
    }

    //激活结果
    public double[] activeResult(double[] x) {
        double[] preActivation = new double[finalOutputNum];
        for (int k = 0; k < finalOutputNum; k++) {

            for (int j = 0; j < nHiddenOut; j++) {
                preActivation[k] += Wkj[j][k] * x[j];
            }

            preActivation[k] += Bk[k];  // linear output
        }
        return softmax(preActivation, finalOutputNum);
    }

    //根据隐藏层的输出结果，使用激活函数获取预测结果
    private PredictResultInfo predict(double[] hiddenOutput) {

        double[] result = activeResult(hiddenOutput);
        double maxp = 0.;
        int idx = 0;
        for (int k = 0; k < finalOutputNum; k++) {
            classfiedInfo.put(k, result[k]);
            if (result[k] > maxp) {
                maxp = result[k];
                idx = k;
            }
        }

        for (int i = 0; i < finalOutputNum; i++) {
            if (i == idx) {
                PredictResultInfo res = new PredictResultInfo(i, classfiedInfo.get(i));
                return res;
            }
        }

        return null;
    }

    //对外暴露对输入数据的predict功能
    public Map<Integer, PredictResultInfo> getPredict(List<InputParam> inputParams) {
        Map<Integer, PredictResultInfo> results = new HashMap<>();
        //TODO: use HiddenLayer to tranform inputParam to HiddenOutput
        for (InputParam param : inputParams) {
            double[] hiddenOut = hiddenLayers.get(0).output(param.getFeatureVector());//第一层HiddenLayer转换
            if (hiddenLayers.size() > 1) {
                //如果隐藏层数大于1，则迭代执行隐藏层的输入转换
                for (int deep = 1; deep < hiddenLayers.size(); deep++) {
                    hiddenOut = hiddenLayers.get(deep).output(hiddenOut);
                }
            }
            PredictResultInfo pres = predict(hiddenOut);
            results.put(param.getUnitCode(), pres);
        }
        return results;
    }

    /**
     * 添加隐藏层
     *
     * @param nIn  入参维度
     * @param nOut 输出维度
     * @return
     */
    public boolean addHiddenLayer(int nIn, int nOut, String layerType) {
        boolean isAdd = true;
        int lastHiddenIdx = hiddenLayers.size() - 1;
        HiddenLayer preHiddenLayer = hiddenLayers.get(lastHiddenIdx);

        if (nIn != preHiddenLayer.getClassfiedNum()) {
            //输入维度与前一层输出不一致则不能添加
            return false;
        }

        //TODO：初始化HiddenLayer，加入到HiddenLayers中
        if (null == layerType) {
            HiddenLayer hdlyr = new HiddenLayer(nIn, nOut, null, null, null, null);
            hiddenLayers.add(hdlyr);
        }


        return isAdd;
    }

    public void train() {
        //对于训练集数据较多时首先分割成小批量数据集
        List<InputParam[]> batchs = new ArrayList<>();

        for (int i = 1; i <= trainSize; i++) {
            int batchIdx = (i - 1) / minibatch_size;
            if (batchIdx + 1 > batchs.size()) {
                int curlength = trainSize - batchIdx * minibatch_size;
                int arraySize = curlength > minibatch_size ? minibatch_size : curlength;
                InputParam[] newbatch = new InputParam[arraySize];
                batchs.add(newbatch);
            }
            InputParam[] batch = batchs.get(batchIdx);
            int numIdx = i <= minibatch_size ? i - 1 : (i - 1) % minibatch_size;
            batch[numIdx] = trainList.get(i - 1);
        }

        //小批量执行训练，每次更新输出层W参数，并将输出层参数传入隐藏层，调整隐藏层参数
        double[][] gradW = new double[nHiddenOut][finalOutputNum];
        double[] gradB = new double[finalOutputNum];
        for (InputParam[] minidata : batchs) {
            int size = minidata.length;
            double[][] input = new double[size][nHiddenOut];
            double[][] trainPredict = new double[size][finalOutputNum];
            double[][] t = new double[size][finalOutputNum];
            //遍历小批量数据集，执行迭代操作,填充输入集和输出结果以及对应的实际结果
            int datasize = 0;
            for (int idx = 0; idx < minidata.length; idx++) {
                datasize++;
                if (minidata[idx] == null) {
                    break;
                }
                int unitCode = minidata[idx].getUnitCode();
                double[] X = minidata[idx].getFeatureVector();
                //获取输入到隐藏层的输出
                double[] hidOut = hiddenLayers.get(0).forward(X);
                hiddenLayers.get(0).initTrainData(datasize);
                hiddenLayers.get(0).trainData[idx] = X;
                //多层神经网络下，需要依次经过多层隐藏层来依次获取结果
                if (hiddenLayers.size() > 1) {
                    int hidx = 1;
                    while (hidx < hiddenLayers.size()) {
                        hiddenLayers.get(hidx).initTrainData(datasize);
                        hiddenLayers.get(hidx).trainData[idx] = hidOut;
                        hidOut = hiddenLayers.get(hidx).forward(hidOut);
                        hidx++;
                    }
                }
                input[idx] = hidOut;
                trainPredict[idx] = activeResult(hidOut);
                t[idx] = trainRes.get(unitCode);
            }

            //对小批量参数执行偏差计算和梯度计算，计算结果反馈给上一级隐藏层，如果有多层隐藏层，则执行多级反馈。
            double[][] E = new double[size][finalOutputNum];
            for (int i = 0; i < size; i++) {
                for (int k = 0; k < finalOutputNum; k++) {
                    double delta = trainPredict[i][k] - t[i][k];//计算每个种类对应的实际偏差
                    E[i][k] = delta;
                    for (int j = 0; j < nHiddenOut; j++) {
                        double Xj = input[i][j];
                        gradW[j][k] += delta * Xj;
                    }
                    gradB[k] += delta;
                }
            }
            //更新输出层参数
            for (int j = 0; j < finalOutputNum; j++) {
                for (int i = 0; i < nHiddenOut; i++) {
                    Wkj[i][j] -= learningRate * gradW[i][j] / size;
                }
                Bk[j] -= learningRate * gradB[j] / size;
            }

            //把E和当前W，B参数传回到隐藏层，更新输出层最近一层隐藏层的参数
            HiddenLayer hiddenLayer = hiddenLayers.get(hiddenLayers.size() - 1);
            double[][] delta = hiddenLayer.backward(Wkj, input, E, learningRate);
            //如果存在多层隐藏层，迭代向前更新参数
            if (hiddenLayers.size() > 1) {
                HiddenLayer curLayer = hiddenLayer;
                double[][] curDelta = delta;
                for (int hidx = hiddenLayers.size() - 2; hidx >= 0; hidx--) {
                    HiddenLayer hLayer = hiddenLayers.get(hidx);
                    double[][] D = hLayer.backward(curLayer, curDelta, learningRate);
                    curLayer = hLayer;
                    curDelta = D;
                }
            }
        }

    }

    /**
     * 添加训练集数据
     *
     * @param vector 训练集样本特征
     * @param res    训练集对应分类结果
     */
    public void inputTrainData(double[] vector, double[] res) {
        trainSize++;
        InputParam inputParam = new InputParam(trainSize, vector);
        trainList.add(inputParam);
        trainRes.put(trainSize, res);
    }


}
