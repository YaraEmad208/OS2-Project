package word_stat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class thread_creation {
  
    
    public static void createThreads(String path) throws InterruptedException {
        List<Thread> threadOfFiles = new ArrayList<>();
        

        Path_get folderCrawler = new Path_get();
        folderCrawler.crawlFold(path);

        for (String textFileName : folderCrawler.getFiles()) {
            WordStatRunnable t = new WordStatRunnable(textFileName);
            threadOfFiles.add(new Thread(t));
           
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

       

    
    }
 
}

