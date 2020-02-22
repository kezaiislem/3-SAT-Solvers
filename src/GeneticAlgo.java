
import java.util.ArrayList;
import java.util.List;
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
public class GeneticAlgo {

    private static final int PERMANENT_SOLS = 0;
    private static final double MUTATION_CHANCE = .05;
    private static final double CROSSOVER_CHANCE = .95;
    private static final int SELECTION_SIZE = 30;
    private Solution best = null;

    private Population population;

    public GeneticAlgo() {
        this.population = new Population();
        best = this.population.getSolutions().get(0);
        //population.printPopulation();
    }

    public void crossOver() {
        List<Solution> newpop = new ArrayList<>();
        Solution selectionA, selectionB;
        for (int i = 0; i < PERMANENT_SOLS; i++) {
            newpop.add(getPopulation().getSolutions().get(i));
        }
        for (int i = PERMANENT_SOLS; i < Population.POPULATION_SIZE; i++) {
            if (Math.random() < CROSSOVER_CHANCE) {
                selectionA = this.selectSolutions().get(0);
                selectionB = this.selectSolutions().get(0);
                newpop.add(this.crossOverSolution(selectionA, selectionB));
            } else {
                newpop.add(population.getSolutions().get(i));
            }
        }
        getPopulation().setSolutions(newpop);
    }

    public void mutate() {
        List<Solution> newpop = new ArrayList<>();
        for (int i = 0; i < PERMANENT_SOLS; i++) {
            newpop.add(getPopulation().getSolutions().get(i));
        }
        for (int i = PERMANENT_SOLS; i < Population.POPULATION_SIZE; i++) {
            newpop.add(this.mutateSolution(population.getSolutions().get(i)));
        }
        getPopulation().setSolutions(newpop);
        if(getPopulation().getSolutions().get(0).getFitness()>best.getFitness()){
            best = getPopulation().getSolutions().get(0);
        }
    }

    public Solution crossOverSolution(Solution sol1, Solution sol2) {
        int[] values = new int[Load.VAR_NUM];
        for (int i = 0; i < Load.VAR_NUM; i++) {
            if (Math.random() < 0.5) {
                values[i] = sol1.getValues()[i];
            } else {
                values[i] = sol2.getValues()[i];
            }
        }
        return new Solution(values);
    }

    public Solution mutateSolution(Solution sol) {
        int[] values = new int[Load.VAR_NUM];
        for (int i = 0; i < Load.VAR_NUM; i++) {
            if (Math.random() < MUTATION_CHANCE) {
                if (sol.getValues()[i] == 0) {
                    values[i] = 1;
                } else {
                    values[i] = 0;
                }
                //values[i] = (sol.getValues()[i] + 1 < 2) ? sol.getValues()[i] + 1 : 0;
            } else {
                values[i] = sol.getValues()[i];
            }
        }
        return new Solution(values);
    }

    public List<Solution> selectSolutions() {
        List<Solution> sols = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < SELECTION_SIZE; i++) {
            Solution s = getPopulation().getSolutions().get(random.nextInt(Population.POPULATION_SIZE));
            if (!sols.contains(s)) {
                sols.add(s);
            } else {
                i--;
            }
        }
        sols.sort(new FitnessSort());
        return sols;
    }

    /**
     * @return the population
     */
    public Population getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(Population population) {
        this.population = population;
    }
    
    public Solution getBest() {
        return this.best;
    }

}
