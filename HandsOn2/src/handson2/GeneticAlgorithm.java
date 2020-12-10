package handson2;

import java.util.Random;

/**
 *
 * @author javicuc
 */
public class GeneticAlgorithm {
    
    private double  mutationRate;
    private double  crossOverRate;
    private boolean elitismValue;
    private static byte[] solution;
    private Population population;

    public GeneticAlgorithm(String candidateSolution){
        solution = new byte[candidateSolution.length()];
        for(int i = 0; i < candidateSolution.length(); i++){
            String character = candidateSolution.substring(i, i + 1);
            solution[i] = Byte.parseByte(character);
        }
    }
    
    public void initPopulation(int populationSize, boolean elitismValue, double crossOverRate, double mutationRate){
        this.elitismValue   = elitismValue;
        this.crossOverRate  = crossOverRate;
        this.mutationRate   = mutationRate;
        this.population     = new Population(populationSize, getLength());
    }
    
    public void runAlgorithm(){
        int generationCount = 1;
        while(population.getFittest().getFitness() < getSolutionFitness()){
            System.out.println("Generation: " + generationCount);
            System.out.println("Fittest individual: " + population.getFittest().getFitness() + ", Chromosome: " + population.getFittest());
            System.out.println("Total population fitness: " + population.getPopulationFitness());
            System.out.println("Total fitness: " + population.getPopulationFitness());
            System.out.println("------------------------------------------------");
            population = evaluatePopulation(population);
            generationCount++;
        }
        System.out.println("------------------------------------------------");
        System.out.println("Generation: " + generationCount + ", Solution: " + population.getFittest());
    }
    
    private Population evaluatePopulation(Population populationEv){
        Population newPopulation = new Population();
        int index = 0;
        if(elitismValue) {
            newPopulation.getIndividuals().add(0, populationEv.getFittest());
            index = 1;
            System.out.println("Elitism is " + elitismValue);
        }     
        for(int i = index; i < populationEv.getSize(); i++){
            //Select parents
            Individual indiv1 = rouletteWheel(populationEv);
            Individual indiv2 = rouletteWheel(populationEv);
            
            Individual newIndiv = crossOver(indiv1, indiv2);
            
            mutate(newIndiv);
            newPopulation.getIndividuals().add(i, newIndiv);
        }
        return newPopulation;
    }
    
    private Individual crossOver(Individual ind1, Individual ind2) {
        Individual individualCo = new Individual(getLength(), false); 
        for (int i = 0; i < getLength(); i++) {
            double rate = Math.random();
            if(rate <= getCrossOverRate()){
                individualCo.setGene(ind1.getGene(i), i);
            }
            else{
                individualCo.setGene(ind2.getGene(i), i);
            }
        }
        return individualCo;
    }

    private void mutate(Individual individual) {
        for(int i = 0; i < individual.getLength(); i++) {
            if(Math.random() <= mutationRate) {
                byte gene = (byte) Math.round(Math.random());
                individual.setGene(gene, i);
            }
        }
        System.out.println(individual);
    }
    
    private Individual rouletteWheel(Population populationR){
        float totalScore = 0;
        float runningScore = 0;
        
        for(int i = 0; i < populationR.getSize(); i++){
            totalScore += populationR.getIndividual(i).getFitness();
        }
        
        //System.out.println("totalScore = " + totalScore);
        Random r = new Random();
        float rnd = (float) r.nextInt((int) totalScore);
        //System.out.println("random = " + rnd);
        for(int i = 0; i < populationR.getIndividuals().size(); i++){
            //System.out.println(rnd + " >= " + runningScore + " && " + rnd + " <= " + (runningScore + populationR.getIndividual(i).getFitness(solution)));
            if(rnd >= runningScore && rnd <= runningScore + populationR.getIndividual(i).getFitness()){
                return populationR.getIndividual(i);
            }
            runningScore += populationR.getIndividual(i).getFitness();
            //System.out.println(runningScore);
        }
        //To solve conflicts
        return populationR.getIndividual(0);
    }
    
    protected static int calculateFitness(Individual individual){
        int fitness = 0;
        for(int i = 0; i < individual.getLength() && i < getLength(); i++){
            if(individual.getGene(i) == solution[i]){
                fitness++;
            }
        }
        return fitness;
    }
    
    public double getCrossOverRate() {
        return crossOverRate;
    }
    
    public static int getLength(){
        return solution.length;
    }
    
    public int getSolutionFitness(){
        return solution.length;
    }
}
