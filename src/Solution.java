
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ISLEM
 */
public class Solution {

    private int[] values;
    private int fitness;

    public Solution() {
        Random random = new Random();
        values = new int[Load.VAR_NUM];
        for (int i = 0; i < Load.VAR_NUM; i++) {
            values[i] = random.nextInt(2);
        }
        fitness = Load.sat.satisfiedClauses(values);
    }
    
    public Solution(int[] values) {
        this.values = values.clone();
        fitness = Load.sat.satisfiedClauses(values);
    }

    /**
     * @return the values
     */
    public int[] getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(int[] values) {
        this.values = values;
    }

    /**
     * @return the fitness
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * @param fitness the fitness to set
     */
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
    
    public String toString(){
        return Arrays.toString(values);
    }

}
