package word_stat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Path_get {
    private List<String> allFiles = new ArrayList<>();
    private String mainDirectoryPath;
   

    public List<String> getFiles() {
        return allFiles;
    }

    public void crawlFold(String path) {
        File folder = new File(path);

        if (!folder.exists()) {
            System.out.println("The specified directory does not exist.");
            return;
        }

        if (!folder.isDirectory()) {
            System.out.println("The provided path is not a directory.");
            return;
        }

        if (mainDirectoryPath == null) {
            mainDirectoryPath = folder.getAbsolutePath();
        }

        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    allFiles.add(file.getAbsolutePath());
                } else if (file.isDirectory() && GUI.Checked==1) {
                    crawlFold(file.getAbsolutePath());
                }
            }
        } else {
            System.out.println("The specified directory is empty.");
        }
    }

    public String getMainDirectoryPath() {
        return mainDirectoryPath;
    }
}


