package word_stat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class thread_creation {
  
    
    public static WordStatRunnable createThreads(String path) throws InterruptedException {
        List<Thread> threadOfFiles = new ArrayList<>();
        WordStatRunnable wordStatRunnable = null; // Initialize it to null

        Path_get folderCrawler = new Path_get();
        folderCrawler.crawlFold(path);

        for (String textFileName : folderCrawler.getFiles()) {
            WordStatRunnable t = new WordStatRunnable(textFileName);
            threadOfFiles.add(new Thread(t));
            wordStatRunnable = t; // Assign the last created WordStatRunnable
        }

        for (Thread t : threadOfFiles) {
            t.start();  
        }
        
//        for (Thread t : threadOfFiles) {
//            try {
//                t.join();
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }

       

        return wordStatRunnable;
    }
 
}

