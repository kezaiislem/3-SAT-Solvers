
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ISLEM
 */
public class Population {

    public static final int POPULATION_SIZE = 75;

    private List<Solution> solutions;
    private int populationSize;

    public Population() {
        solutions = new ArrayList<>();
        this.populationSize = POPULATION_SIZE;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            solutions.add(new Solution());
        }
        sortPopulation();
    }

    public void sortPopulation() {
        solutions.sort(new FitnessSort());
    }

    /**
     * @return the solutions
     */
    public List<Solution> getSolutions() {
        return solutions;
    }

    /**
     * @param solutions the solutions to set
     */
    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
        sortPopulation();
    }

    /*public void printPopulation() {
        for (Solution s : solutions) {
            System.out.println("--------------------------------------------");
            System.out.println(Arrays.toString(s.getValues()) + " f = " + s.getFitness());
        }
    }*/
    
    public int getBestFitness(){
        return solutions.get(0).getFitness();
    }

}
