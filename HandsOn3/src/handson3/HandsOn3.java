/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handson3;

/**
 *
 * @author javicuc
 */
public class HandsOn3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm("11111");
        ga.initPopulation(4, false, 0.95, 0.01);
        ga.runAlgorithm();
    }
    
}
