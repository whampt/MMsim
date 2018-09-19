import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 * This class tests the program.
 * 
 *
 */
public class Test {
   /**
    * This main method tests everything
    * @param args
    * @throws IOException
    */
    public static void main(String[] args) throws IOException {
        
//        FileWriter file = new FileWriter("Tests.txt");
    	FileWriter file = new FileWriter("C:\\Users\\jpd\\portfolioExpansionProject\\memManSim\\src\\Tests.txt");
    	BufferedWriter bw = new BufferedWriter(file);
        
        // This will test reference set sizes 10-30
        for (int i = 10; i <= 50; i += 5) {
            
            int badO = 0;
            int goodO = 0;
            int greatO = 0;
            
            int badLRU = 0;
            int goodLRU = 0;
            int greatLRU = 0;
            
            int badRandom = 0;
            int goodRandom = 0;
            int greatRandom = 0;
            
            // Create 100 different reference strings to test for each algorithm
            // and each type of string
            for (int j = 0; j < 100; j++) {
                
                shortProgram bad = new shortProgram(false);
                shortProgram good = new shortProgram(true);
                shortProgram great = new shortProgram((byte) 100);
                great.shuffle();
                   
                badO += Optimal.Optimal(bad, i);
                goodO += Optimal.Optimal(good, i);
                greatO += Optimal.Optimal(great, i);
                
                badLRU += LRU.lru(bad, i);
                goodLRU += LRU.lru(good, i);
                greatLRU += LRU.lru(great, i);
                
                RandomAlgorithm rb = new RandomAlgorithm(bad, i);
                RandomAlgorithm rg = new RandomAlgorithm(good, i);
                RandomAlgorithm rgr = new RandomAlgorithm(great, i);
                badRandom += rb.simulateDemandPagingRandomAlgorithm();
                goodRandom += rg.simulateDemandPagingRandomAlgorithm();
                greatRandom += rgr.simulateDemandPagingRandomAlgorithm();
            }
            
            // Get the averages of each algorithm and their respective programs
            badO = badO/1000;
            goodO = goodO/1000;
            greatO = greatO/1000;
            badLRU = badLRU/1000;
            goodLRU = goodLRU/1000;
            greatLRU = greatLRU/1000;
            badRandom = badRandom/1000;
            goodRandom = goodRandom/1000;
            greatRandom = greatRandom/1000;
            
            bw.write("Optimal, bad, set size " + i + ": " + badO);
            bw.newLine();
            bw.write("Optimal, good, set size " + i + ": " + goodO);
            bw.newLine();
            bw.write("Optimal, great, set size " + i + ": " + greatO);
            bw.newLine();
            bw.newLine();
            
            bw.write("LRUA, bad, set size " + i + ": " + badLRU);
            bw.newLine();
            bw.write("LRUA, good, set size " + i + ": " + goodLRU);
            bw.newLine();
            bw.write("LRUA, great, set size " + i + ": " + greatLRU);
            bw.newLine();
            bw.newLine();
            
            bw.write("Random, bad, set size " + i + ": " + badRandom);
            bw.newLine();
            bw.write("Random, good, set size " + i + ": " + goodRandom);
            bw.newLine();
            bw.write("Random, great, set size " + i + ": " + greatRandom);
            bw.newLine();
            bw.newLine();
            bw.newLine();
        }
        
        bw.close();
        System.out.println("Done!");
    }
}