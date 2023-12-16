package word_stat;

import javax.swing.*;
import java.io.File;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class gui2 extends javax.swing.JFrame {

    public gui2() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        browse = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        processing = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36));
        jLabel1.setForeground(new java.awt.Color(0, 109, 255));
        jLabel1.setText("Project2 Word Statistics");

        browse.setBackground(new java.awt.Color(0, 109, 255));
        browse.setFont(new java.awt.Font("Tahoma", 0, 18));
        browse.setText("Browse");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });

        // ... (Other GUI components)

        pack();
    }

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {
         JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    int option = fileChooser.showOpenDialog(this); // Use 'this' as the parent component
    if (option == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String selectedPath = selectedFile.getAbsolutePath();

        // Instantiate the search class
        Path_get fileSearch = new Path_get();
        fileSearch.crawlFold(selectedPath);

        // Get the collected files and their statistics
        List<String> collectedFiles = fileSearch.getFiles();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing table data
        
        for (String fileName : collectedFiles) {
            // Create a row with file information
            File file = new File(fileName);
            model.addRow(new Object[]{
                    file.getName(), // File name
                    "", "", "", "", "", "" // Placeholders for statistics
            });
        }
    } else {
        JOptionPane.showMessageDialog(this, "No file selected.");
    }

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton browse;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton processing;
    // End of variables declaration
}
