package org.kingback.suger.explorer.machineLearning;

import org.kingback.suger.explorer.machineLearning.neuronet.MultiLayerPercepTrons;
import org.kingback.suger.explorer.machineLearning.util.InputParam;
import org.kingback.suger.explorer.machineLearning.util.PredictResultInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestNN {

    public static void main(String[] args) {

//        Random rnd = new Random(100);
//        rnd.nextInt();
//        for (int i = 0; i < 6; i++) {
//            System.out.println(rnd.nextDouble());
//        }

        double[][] train = {
                {0.45, 0.2},
                {0.66, 0.3},
                {1, 0.5},//
                {0.73, 0.2},
                {0.12, 2},
                {0.30, 5},
                {0.35, 0.2},
                {0.40, 0.4},
                {0.45, 0.2},
                {0.50, 0.1},
                {0.55, 0.6},//
                {0.60, 0.3},
                {0.65, 0.2},
                {0.70, 0.15},
                {0.75, 0.3},
                {0.80, 0.1},
                {0.85, 0.4},
                {0.90, 0.3},//
                {0.95, 0.2},
                {1, 0.2}//
//                {0., 0.},
//                {0., 1.},
//                {1., 0.},
//                {1., 1.}
        };

        double[][] res = {
                {1, 0},
                {1, 0},
                {0, 1},
                {1, 0},
                {1, 0},
                {1, 0},
                {1, 0},
                {1, 0},
                {1, 0},
                {1, 0},
                {0, 1},
                {1, 0},
                {1, 0},
                {1, 0},
                {1, 0},
                {1, 0},
                {1, 0},
                {0, 1},
                {1, 0},
                {0, 1},
//                {1, 0},
//                {0, 1},
//                {0, 1},
//                {1, 0}
        };

        MultiLayerPercepTrons multiLayerPercepTrons = new MultiLayerPercepTrons(2, 2, 3, 1);
//        multiLayerPercepTrons.addHiddenLayer(3, 3, null);
        for (int i = 0; i < 20; i++) {
            multiLayerPercepTrons.inputTrainData(train[i], res[i]);
        }
        int epochs = 1000;
        double learningRate = 0.1;
        multiLayerPercepTrons.setLearningRate(learningRate);
        for (int epoch = 0; epoch < epochs; epoch++) {
            multiLayerPercepTrons.train();
//            learningRate *= 0.95;
//            multiLayerPercepTrons.setLearningRate(learningRate);
        }

        InputParam inputParam = new InputParam(1331, new double[]{0.54, 0.43});
        List<InputParam> test = new ArrayList<>();
        test.add(inputParam);
        Map<Integer, PredictResultInfo> preInfo = multiLayerPercepTrons.getPredict(test);
        System.out.println(preInfo.toString());


        //构造混淆矩阵
        int patterns = 2;
        int[][] confusionMatrix = new int[patterns][patterns];
        double accuracy = 0.;
        double[] precision = new double[patterns];
        double[] recall = new double[patterns];

        int test_N = 6;
        double[][] test_src = {
                {0.9, 0.3},//
                {0.4, 0.3},
                {1, 0.2},//
                {0.7, 0.2},
                {0.3, 0.5},
                {0.44, 0.21}
        };
        //初始化测试结果集
        Integer[][] test_T = new Integer[][]{
                {0, 1},//
                {1, 0},
                {0, 1},//
                {1, 0},
                {1, 0},
                {1, 0},
        };
        //使用模型对测试输入执行预测，预测结果放置于预测集predicted_T中
        Integer[][] predicted_T = new Integer[test_N][2];
        List<InputParam> testSet = new ArrayList<>();
        for (int x = 0; x < test_N; x++) {
            InputParam input = new InputParam(x, test_src[x]);
            testSet.add(input);
            System.out.println("编号:" + x + ",预测值" + test_src[x][0] * 100 + ",与预测值差值比率:" + test_src[x][1] * 100 + "%");
        }
        Map<Integer, PredictResultInfo> testPredictRes = multiLayerPercepTrons.getPredict(testSet);
        testPredictRes.forEach((k, v) -> {
            System.out.println("编号:" + k + ",结果--》" + v.toString());
            if (v.getClsIndex() == 0) {
                predicted_T[k][0] = 1;
                predicted_T[k][1] = 0;
            } else {
                predicted_T[k][0] = 0;
                predicted_T[k][1] = 1;
            }
        });


        for (int i = 0; i < test_N; i++) {
            int predicted_ = Arrays.asList(predicted_T[i]).indexOf(1);
            int actual_ = Arrays.asList(test_T[i]).indexOf(1);

            confusionMatrix[actual_][predicted_] += 1;
        }

        for (int i = 0; i < patterns; i++) {
            double col_ = 0.;
            double row_ = 0.;

            for (int j = 0; j < patterns; j++) {

                if (i == j) {
                    accuracy += confusionMatrix[i][j];
                    precision[i] += confusionMatrix[j][i];
                    recall[i] += confusionMatrix[i][j];
                }

                col_ += confusionMatrix[j][i];
                row_ += confusionMatrix[i][j];
            }
            precision[i] /= col_;
            recall[i] /= row_;
        }

        accuracy /= test_N;

        System.out.println("--------------------");
        System.out.println("简单神经网络对预测值与对比率的判断指标");
        System.out.println("--------------------");
        System.out.printf("准确度: %.1f %%\n", accuracy * 100);
        System.out.println("异常值精准率:");
        for (int i = 0; i < patterns; i++) {
            System.out.printf(" class % d: %.1f %%\n", i + 1, precision[i] * 100);
        }
        System.out.println("异常值召回率:");
        for (int i = 0; i < patterns; i++) {
            System.out.printf(" class %d: %.1f %%\n", i + 1, recall[i] * 100);
        }

    }

}
