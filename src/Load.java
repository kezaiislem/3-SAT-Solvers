/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISLEM
 */
public class Load {

    public static List<Clause> clauses;
    public static List<Litteral> litterals;
    public static Litteral litteral;
    public static Clause clause;
    public static Sat sat;

    // Parametres
    public static int VAR_NUM; // nombre des Varriables
    public static int CLAUSE_NUMBER;
    public static final int DEFAULT_TIMEOUT = 60000;
    public static long TOTAL_TIME = 0;
    public static long startTime;

    public static void main(String[] args) {
        
        GeneticAlgo geneticAlgo;

        for (int x = 1; x <= 10; x++) {
            
            sat = LoadSat("/res/uf75-325/uf75-0" + x + ".cnf");
            geneticAlgo = new GeneticAlgo();
            startTime = System.currentTimeMillis();

            while (geneticAlgo.getPopulation().getBestFitness() < CLAUSE_NUMBER && System.currentTimeMillis() - startTime < DEFAULT_TIMEOUT) {
                geneticAlgo.crossOver();
                geneticAlgo.mutate();
            }
            
            printSolution(x, geneticAlgo);
            
        }
        System.out.println("Total Time : " + TOTAL_TIME + "ms");
        System.out.println("--------------------------------------------");
    }

    public static void printSolution(int iteration, GeneticAlgo geneticAlgo) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        TOTAL_TIME += elapsedTime;
        System.out.println(iteration + " : " + geneticAlgo.getBest().toString() + "\n" + geneticAlgo.getBest().getFitness());
        System.out.println("Time : " + elapsedTime + "ms");
        System.out.println("--------------------------------------------");
    }

    // Chargement d'un fichier
    public static Sat LoadSat(String fileName) {

        //Initilisation des Listes
        clauses = new ArrayList<>();

        //Juste une varriable tmp
        int tmpParseInt;

        try {

            InputStream is = new FileInputStream(System.getProperty("user.dir") + fileName);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            String[] lit;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("c") || line.startsWith("%") || line.startsWith("0") || line.equals("")) {
                } else {
                    if (line.startsWith("p")) {
                        line = line.replace("p cnf ", "");
                        line = line.replaceFirst(" ", "");
                        lit = line.split(" ");
                        VAR_NUM = Integer.parseInt(lit[0]);
                        CLAUSE_NUMBER = Integer.parseInt(lit[1]);
                        continue;
                    }
                    litterals = new ArrayList<Litteral>();
                    if (first) {
                        line = line.replaceFirst(" ", "");
                        first = false;
                    }
                    lit = line.split(" ");
                    for (int i = 0; i < 3; i++) {
                        tmpParseInt = Integer.parseInt(lit[i]);
                        if (tmpParseInt > 0) {
                            litteral = new Litteral(tmpParseInt, 1);
                        } else {
                            litteral = new Litteral(-tmpParseInt, -1);
                        }
                        litterals.add(litteral);
                    }
                    clause = new Clause(litterals);
                    clauses.add(clause);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new Sat(clauses);
    }
}
