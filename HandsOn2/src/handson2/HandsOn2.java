package handson2;

/**
 *
 * @author javicuc
 */
public class HandsOn2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        GeneticAlgorithm ga = new GeneticAlgorithm("1111111111");
        ga.initPopulation(6, false, 0.015, 0.5);
        ga.runAlgorithm();
    }
}
