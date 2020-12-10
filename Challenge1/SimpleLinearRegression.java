package seminario.Challenge1;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.LinkedHashMap;

public class SimpleLinearRegression extends Agent {

  public LinkedHashMap<Integer, Integer> dataSet = new LinkedHashMap<Integer, Integer>();
  private SimpleLinearRegressionGui myGui;

  private double theta0;
  private double theta1;

  private final double alpha = 0.0001;
  private final int maxI = 9000;
  private int i = 0;
  private final double tolerance = 1e-11;

  protected void setup() {
    System.out.println("Agent " + getLocalName() + " started.");

    this.dataSet.put(36, 31);
    this.dataSet.put(28, 29);
    this.dataSet.put(35, 34);
    this.dataSet.put(39, 35);
    this.dataSet.put(30, 29);
    this.dataSet.put(30, 30);
    this.dataSet.put(31, 30);
    this.dataSet.put(38, 38);
    this.dataSet.put(36, 34);
    this.dataSet.put(38, 33);
    this.dataSet.put(29, 29);
    this.dataSet.put(26, 26);

    addBehaviour(new MyOneShotBehaviour(this));
    myGui = new SimpleLinearRegressionGui(this);
    myGui.showGui();
  }

  public void gradientDescent() {
    do {
      this.theta1 -= alpha * calculeTheta1();
      this.theta0 -= alpha * calculeTheta0();
      i++;

      if (i % 100 == 0) {
        System.out.println("Iteration " + i + ", Theta0" + this.theta0 + ", Theta1=" + this.theta1);
      }
      if (i > maxI)
        break;
    } while (Math.abs(theta1) > this.tolerance || Math.abs(theta0) > this.tolerance);
    System.out.println("Theta0" + this.theta0 + ", Theta1=" + this.theta1);
  }

  private double calculeTheta1() {
    double sum = 0;
    int x;
    int y;
    for (Integer key : dataSet.keySet()) {
      x = key;
      y = dataSet.get(key);
      sum += (y - calculatePrediction(x)) * x;
    }
    return -2 * sum / dataSet.size();
  }

  public String getPrediction(int x){
    return this.theta1 + " * " + x + " + " + this.theta0 + " = " + calculatePrediction(x);
  }

  private double calculatePrediction(double x) {
    return this.theta1 * x + this.theta0;
  }

  private double calculeTheta0() {
    double sum = 0;
    int x;
    int y;
    for (Integer key : dataSet.keySet()) {
      x = key;
      y = dataSet.get(key);
      sum += y - calculatePrediction(x);
    }
    return -2 * sum / dataSet.size();
  }

  private class MyOneShotBehaviour extends OneShotBehaviour {

    SimpleLinearRegression slr;

    public MyOneShotBehaviour(SimpleLinearRegression simpleRegression) {
      this.slr = simpleRegression;
    }

    public void action() {
      System.out.println("Agent's action method executed");
      this.slr.gradientDescent();
    }

    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    }
  }
}
