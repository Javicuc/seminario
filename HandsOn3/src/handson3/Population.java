/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handson3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javicuc
 */
public class Population {
    
    private List<Individual> listIndividuals;
    private int populationFitness;
    
    public Population(){
        this.listIndividuals = new ArrayList<>();
        this.populationFitness = 0;
    }
    
    public Population(int size, int lenghtChromosome){
        this.populationFitness = 0;
        this.listIndividuals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Individual newIndividual = new Individual(lenghtChromosome, true);
            System.out.println(newIndividual);
            this.listIndividuals.add(i, newIndividual);
        }
    }
    
    public void setIndividual(Individual individual){
        this.listIndividuals.add(individual);
    }
    public Individual getIndividual(int index){
        return this.listIndividuals.get(index);
    }

    public List<Individual> getIndividuals() {
        return listIndividuals;
    }
    
    public Individual getFittest(){
        Individual fittest = this.listIndividuals.get(0);
        for(int i = 0; i < getSize(); i++){
            if(fittest.getFitness() <= getIndividual(i).getFitness()){
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }
    
    public int getPopulationFitness(){
        for(Individual i : this.listIndividuals){
            this.populationFitness += i.getFitness();
        }
        return this.populationFitness;
    }
    
    public int getSize(){
        return this.listIndividuals.size();
    }
}
