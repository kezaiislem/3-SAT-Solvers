
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ISLEM
 */
class FitnessSort implements Comparator<Solution> 
{ 

    @Override
    public int compare(Solution o1, Solution o2) {
        return o2.getFitness() - o1.getFitness();
    }
    
} 
