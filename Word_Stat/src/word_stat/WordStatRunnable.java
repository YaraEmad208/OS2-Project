/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package word_stat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class WordStatRunnable  implements Runnable {
//    Semaphore sem;
//    private GUI gui;
    private String path;
    private static String longest = "";
    private static String shortest ="this_is_test_string";
    private static int totalWords = 0;
    private static String global_shortest ="this_is_test_string";
    private static String global_longest = "";
    private static int countIs = 0;
    private static int countAre = 0;
    private static int countYou = 0;
    private static Semaphore sem = new Semaphore(1);
    // Create the main list
    public static List<List<Object>> mainList = new ArrayList<>();
  
    public WordStatRunnable(String path) {
        this.path = path;
    }

    public static synchronized String getLongestWord() {
        return global_longest;
    }

    public static synchronized String getShortestWord() {
        return global_shortest;
    }

    public static synchronized int getTotalWords() {
        return totalWords;
    }

    public static synchronized int getCountIs() {
        return countIs;
    }

    public static synchronized int getCountAre() {
        return countAre;
    }

    public static synchronized int getCountYou() {
        return countYou;
    }

   @Override
    public void run() {
        File file = new File(path);
        String[] words;
        String s;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            sem.acquire();  // t1 , t2 

            while ((s = br.readLine()) != null) {
                words = s.split("\\s+"); //is space
                totalWords=totalWords+words.length;
                    for (String word : words) {
                    if(word.length()>1&& !word.equals("is") && !word.equals("you") && !word.equals("are")  ){
                        if(shortest.length()>word.length()){
                          shortest=word;
                        }
                        if(global_shortest.length()>=shortest.length()){
                           global_shortest=shortest;
                        }
                    }

                    if (word.length() > longest.length()) {
                        longest = word;
                       
                    }
                     if(global_shortest.length()<=longest.length()){
                           global_longest=longest;
                        }

                    if (word.equals("is")) {
                        countIs++;
                    } else if (word.equals("are")) {
                        countAre++;
                    } else if (word.equals("you")) {
                        countYou++;
                    }
                    //
                }
            }
        } catch (IOException e) {
            Logger.getLogger(WordStatRunnable.class.getName()).log(Level.SEVERE, null, e);
        } catch (InterruptedException ex) { 
            Logger.getLogger(WordStatRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
    // Create a list to insert
    List<Object> newList = new ArrayList<>();
    // Using the File class
    File files = new File(path);
    String fileName = files.getName();
    newList.add(fileName);
    newList.add(WordStatRunnable.getTotalWords());
    newList.add(WordStatRunnable.getCountIs());
    newList.add(WordStatRunnable.getCountAre());
    newList.add(WordStatRunnable.getCountYou());
    newList.add(longest);
    newList.add(shortest);
    // Add the new list to the mainList
    mainList.add(newList);

      // Reset shared variables
        totalWords = 0;
        countIs = 0;
        countAre = 0;
        countYou = 0;
        shortest = "this_is_test_string";
        longest="";
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WordStatRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
             System.out.println("word_stat.WordStatRunnable.run()");
             sem.release(); // Release the semaphore here to minimize the locked time
        }
        System.out.println("THE LONGEST WORD is " + WordStatRunnable.getLongestWord());
        System.out.println("THE SHORTEST WORD is " + WordStatRunnable.getShortestWord());
        // Assuming rowIndex and columnIndex specify the position in the table where you want to display these values
    
    }    
 }
 