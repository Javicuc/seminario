package seminario.HandsOn1;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.LinkedHashMap;

public class SimpleLinearRegression extends Agent {

  public LinkedHashMap<Integer, Integer> dataSet = new LinkedHashMap<Integer, Integer>();
  private SimpleLinearRegressionGui myGui;

  public float ySum;
  public float xSum;
  public float xySum;
  public float xExp2Sum;
  public float yExp2Sum;

  protected void setup() {
    System.out.println("Agent " + getLocalName() + " started.");

    this.dataSet.put(23, 651);
    this.dataSet.put(26, 762);
    this.dataSet.put(30, 856);
    this.dataSet.put(34, 1063);
    this.dataSet.put(43, 1190);
    this.dataSet.put(48, 1298);
    this.dataSet.put(52, 1421);
    this.dataSet.put(57, 1440);
    this.dataSet.put(58, 1518);

    this.ySum = 0;
    this.xSum = 0;
    this.xySum = 0;
    this.xExp2Sum = 0;
    this.yExp2Sum = 0;

    addBehaviour(new MyOneShotBehaviour(this));
    myGui = new SimpleLinearRegressionGui(this);
		myGui.showGui();
  }

  public String getPrediction(int x) {
    if (getBeta0() != 0 && getBeta1() != 0) {
      return getBeta0() + " + " + " (" + getBeta1() + " * " + x + ") = " + calculatePrediction(x);
    }
    return "No existe beta0 o beta1";
  }

  private double calculatePrediction(int x) {
    return getBeta0() + (getBeta1() * x);
  }

  public double getBeta0() {
    double res = ((this.ySum * this.xExp2Sum) - (this.xSum * this.xySum))
        / (this.dataSet.size() * this.xExp2Sum - Math.pow(this.xSum, 2));
    return res;
  }

  public double getBeta1() {
    double res = ((this.dataSet.size() * this.xySum) - (this.xSum * this.ySum))
        / ((this.dataSet.size() * this.xExp2Sum) - Math.pow(this.xSum, 2));
    return res;
  }

  private class MyOneShotBehaviour extends OneShotBehaviour {

    SimpleLinearRegression slr;
    
    public MyOneShotBehaviour(SimpleLinearRegression simpleRegression){
      this.slr = simpleRegression;
    }

    public void action() {
      System.out.println("Agent's action method executed");

      int x = 0;
      int y = 0;
      int i = 1;
      float xy = 0;
      float xExp2 = 0;
      float yExp2 = 0;

      System.out.println("|   x   |   y   |   xy   |   x^2   |   y^2   |");
      for (Integer key : slr.dataSet.keySet()) {
        x = key;
        y = slr.dataSet.get(key);
        System.out.print("   " + x + "      " + y);
        // Get xy
        xy = x * y;
        System.out.print("   " + xy);
        // Get x^2
        xExp2 = (float) Math.pow(x, 2);
        System.out.print("    " + xExp2);
        // Get y^2
        yExp2 = (float) Math.pow(y, 2);
        System.out.println("    " + yExp2);
        // Save SUMs
        slr.xSum += x;
        slr.ySum += y;
        slr.xySum += xy;
        slr.xExp2Sum += xExp2;
        slr.yExp2Sum += yExp2;
        i++;
        System.out.println("---------------------------------------------------");
      }
      System.out.println("Sumatoria de x:   " + slr.xSum);
      System.out.println("Sumatoria de y:   " + slr.ySum);
      System.out.println("Sumatoria de xy:  " + slr.xySum);
      System.out.println("Sumatoria de x^2: " + slr.xExp2Sum);
      System.out.println("Sumatoria de y^2: " + slr.yExp2Sum);
      System.out.println("Beta 0: " + slr.getBeta0());
      System.out.println("Beta 1: " + slr.getBeta1());
    }

    public done(){

    }

    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    }
  }
}
