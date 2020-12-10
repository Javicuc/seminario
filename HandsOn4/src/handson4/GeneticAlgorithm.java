/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handson4;

import java.util.Random;

/**
 *
 * @author javicuc
 */
public class GeneticAlgorithm {
    
    private double  mutationRate;
    private double  crossOverRate;
    private boolean elitismValue;
    private static int solution;
    private Population population;
    private final int maxPorcentage;

    public GeneticAlgorithm(int candidateSolution, int porcentage){
        this.solution = candidateSolution;
        this.maxPorcentage = porcentage;
    }
    
    public void initPopulation(int populationSize, boolean elitismValue, double crossOverRate, double mutationRate){
        this.elitismValue  = elitismValue;
        this.crossOverRate = crossOverRate;
        this.mutationRate  = mutationRate;
        this.population    = new Population(populationSize, getLength());
    }
    
    public void runAlgorithm(){
        int generationCount = 1;
        while(population.getFittest().getFitness() != getSolutionFitness()){
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
                int gene = (int) (Math.random()*10);
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
        int a = individual.getGene(0);
        int b = individual.getGene(1);
        int c = individual.getGene(2);
        int d = individual.getGene(3);
        int e = individual.getGene(4);
        int f = individual.getGene(5);
        //a + 2b - 3c + d + 4e + f = 30
        int equationSolve = a + (2*b) - (3*c) + d + (4*e) + f;
        System.out.println("equation = " + equationSolve);
        int fitness = 100 - Math.abs(equationSolve - solution);
        System.out.println("fitness = " + fitness);
        
        return fitness;
    }
    
    public double getCrossOverRate() {
        return crossOverRate;
    }
    
    public int getSolutionFitness(){
        return this.maxPorcentage;
    }
    
    public int getLength(){
        return 6;
    }
}
