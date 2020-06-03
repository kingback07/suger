package org.kingback.suger.explorer.machineLearning.singleneuro;

import java.util.Random;
import java.util.function.DoubleFunction;


import static org.kingback.suger.explorer.machineLearning.util.ActivationFunction.*;
import static org.kingback.suger.explorer.machineLearning.util.RandomGenerator.*;


public class HiddenLayer implements IHiddenLayer {

    public int nIn;
    public int nOut;
    public double[][] W;
    public double[] b;
    public Random rng;
    public DoubleFunction<Double> activation;
    public DoubleFunction<Double> dactivation;

    public double[][] trainData;

    public void initTrainData(int size) {
        trainData = new double[size][nIn];
    }


    public HiddenLayer(int nIn, int nOut, double[][] W, double[] b, Random rng, String activation) {

        if (rng == null) rng = new Random(1234);  // seed random

        if (W == null) {

            W = new double[nIn][nOut];
            double w_ = 1. / nIn;

            for (int j = 0; j < nOut; j++) {
                for (int i = 0; i < nIn; i++) {
                    double wp = uniform(-w_, w_, rng);
//                    System.out.println(wp);
                    W[i][j] = wp;  // initialize W with uniform distribution
                }
            }

        }

        if (b == null) b = new double[nOut];

        this.nIn = nIn;
        this.nOut = nOut;
        this.W = W;
        this.b = b;
        this.rng = rng;

        if (activation == "sigmoid" || activation == null) {

            this.activation = (double x) -> sigmoid(x);
            this.dactivation = (double x) -> dsigmoid(x);

        } else if (activation == "tanh") {

            this.activation = (double x) -> tanh(x);
            this.dactivation = (double x) -> dtanh(x);

        } else if (activation == " ") {

            this.activation = (double x) -> ReLU(x);
            this.dactivation = (double x) -> dReLU(x);

        } else {
            throw new IllegalArgumentException("activation function not supported");
        }

    }

    public double[] output(double[] x) {

        double[] y = new double[nOut];

        for (int j = 0; j < nOut; j++) {
            double preActivation_ = 0.;

            for (int i = 0; i < nIn; i++) {
                preActivation_ += W[i][j] * x[i];
            }
            preActivation_ += b[j];

            y[j] = activation.apply(preActivation_);
        }

        return y;
    }

    @Override
    public int getClassfiedNum() {
        return nOut;
    }

    @Override
    public int getInputD() {
        return nIn;
    }

    @Override
    public double[][] getWoi() {
        return W;
    }

    @Override
    public double[][] getTrainingData() {
        return trainData;
    }

    public double[] forward(double[] x) {
        return output(x);
    }

    @Override
    public double[][] backward(double[][] wNext, double[][] hiddenOut, double[][] delta, double learningRate) {

        double[][] dZ = new double[trainData.length][nOut];  // backpropagation error

        double[][] grad_W = new double[nIn][nOut];
        double[] grad_b = new double[nOut];

        // train with SGD
        // calculate backpropagation error to get gradient of W, b
        for (int n = 0; n < trainData.length; n++) {
            for (int j = 0; j < nOut; j++) {

                for (int k = 0; k < delta[0].length; k++) {  // k < ( nOut of prelayer )
                    dZ[n][j] += wNext[j][k] * delta[n][k];
                }
                dZ[n][j] *= dactivation.apply(hiddenOut[n][j]);


                for (int i = 0; i < nIn; i++) {
                    grad_W[i][j] += dZ[n][j] * trainData[n][i];
                }

                grad_b[j] += dZ[n][j];
            }
        }

        // update params
        for (int j = 0; j < nOut; j++) {
            for (int i = 0; i < nIn; i++) {
                W[i][j] -= learningRate * grad_W[i][j];
            }
            b[j] -= learningRate * grad_b[j];
        }

        return dZ;
    }

    @Override
    public double[][] backward(IHiddenLayer hlayer, double[][] delta, double learningRate) {
        double[][] wNext = hlayer.getWoi();
        double[][] hiddenOut = hlayer.getTrainingData();
        return backward(wNext, hiddenOut, delta, learningRate);
    }


}
