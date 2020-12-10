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
public class Individual {
    private int length;
    private byte[] chromosome;
    private int fitness;
    
    public Individual(int length, boolean setChromosome){
        this.length = length;
        this.chromosome = new byte[length];
        if(setChromosome){
            for (int i = 0; i < chromosome.length; i++) {
                byte gene = (byte) Math.round(Math.random());
                chromosome[i] = gene;
            }
        }
    }
    
    public void setGene(byte x, int index){
        this.chromosome[index] = x;
    }
    
    public void setLength(int length){
        this.length = length;
    }
    
    public int getLength(){
        return this.length;
    }
    
    public void setChromosome(int length){
        this.chromosome = new byte[length];
    }
    
    public byte[] getChromosome(){
        return this.chromosome;
    }
    
    public byte getGene(int index){
        return this.chromosome[index];
    }
    
    public int getFitness(){
        if(fitness == 0){
            fitness = GeneticAlgorithm.calculateFitness(this);
        }
        return fitness;
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < chromosome.length; i++) {
            geneString += getGene(i) + "|";
        }
        return geneString;
    }
}
