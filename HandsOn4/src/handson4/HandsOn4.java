/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handson4;

/**
 *
 * @author javicuc
 */
public class HandsOn4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm(32, 100);
        ga.initPopulation(6, false, 0.5, 0.025);
        ga.runAlgorithm();
    }
    
}
