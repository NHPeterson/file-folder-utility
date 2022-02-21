/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nathan;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.awt.datatransfer.*;
import java.util.*;

/**
 *
 * @author Nathan Peterson
 */
public class FolderCreatorGUI extends javax.swing.JFrame {
    public List<String[]> folderNames = new ArrayList<>();
    public List<File> fileList = new ArrayList<>(),
                        fileList2 = new ArrayList<>();
    public String fileSep = File.separator;
    public DefaultListModel listModel = new DefaultListModel();
    public DefaultListModel listModel2 = new DefaultListModel();
    /*
     * Creates new form FolderCreatorGUI
     */

    public FolderCreatorGUI() {
        initComponents();
        
        fileNameList.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                if (!support.isDrop()) {
                    return false;
                }
                if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    return false;
                }
                return true;
            }
            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                errorLabel.setText("");
                if (!canImport(support)) {
                    return false;
                }
                Transferable tr = support.getTransferable();
                try {
                    List<File> l = (List<File>)tr.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File f : l) {
                        String[] s = makeFolderNames(f.getName());
                        if (s == null) {
                            continue;
                        }
                        fileList.add(f);
                        listModel.addElement(f.getName());
                        folderNames.add(s);
                        for (String item : s) {
                            folderNameArea.append(item + "\n");
                        }
                    }
                } catch (UnsupportedFlavorException | IOException e) {
                    return false;
                }
                return true;
            }
        });
        
        fileNameList2.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                if (!support.isDrop()) {
                    return false;
                }
                if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    return false;
                }
                return true;
            }
            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }
                Transferable tr = support.getTransferable();
                try {
                    List<File> l = (List<File>)tr.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File f : l) {
                        fileList2.add(f);
                        listModel2.addElement(f.getName());
                    }
                } catch (UnsupportedFlavorException | IOException e) {
                    return false;
                }
                errorLabel.setText("");
                return true;
            }
        });
    }

        public String[] makeFolderNames(String f11) {
            String folderStr1 = "",
                    folderStrN = "",
                    endOfFolderName = "";
            int begIndexOfFileNum1 = 0,
                endIndexOfFileNum1 = 0,
                begIndexOfFileNumN = 0,
                endIndexOfFileNumN = 0,
                fileNum1 = 0,
                fileNumN = 0;
            boolean bIOFN1set = false,
                    eIOFN1set = false,
                    bIOFNNset = false,
                    eIOFNNset = false,
                    fileRange = false;

            if (f11 == null || f11.isEmpty()) {
                errorLabel.setText("One or more files is invalid");
                return null;
            }
            if (f11.indexOf('.') > 0) {
                f11 = f11.substring(0, f11.lastIndexOf('.'));
            }
            for (int i = 0; i<f11.length(); i++) {
                if (bIOFN1set != true && f11.charAt(i) >= '0' && f11.charAt(i) <= '9') {
                    begIndexOfFileNum1 = i;
                    bIOFN1set = true;
                    continue;
                }
                if (bIOFN1set == true && eIOFN1set != true && (f11.charAt(i) < '0' || '9' < f11.charAt(i))) {
                    endIndexOfFileNum1 = i;
                    eIOFN1set = true;
                    endOfFolderName = f11.substring(i);
                }
                if (bIOFN1set == true && eIOFN1set == true && fileRange != true && f11.charAt(i) == '-') {
                    fileRange = true;
                    continue;
                }
                if (fileRange == true) {
                    if (bIOFNNset != true && '0' <= f11.charAt(i) && f11.charAt(i) <= '9') {
                        begIndexOfFileNumN = i;
                        bIOFNNset = true;
                        continue;
                    }
                    if (bIOFNNset == true && eIOFNNset != true && (f11.charAt(i) < '0' || '9' < f11.charAt(i))) {
                        endIndexOfFileNumN = i;
                        eIOFNNset = true;
                        endOfFolderName = f11.substring(i);
                        break;
                    }
                }
            }
            if (!bIOFN1set) {
                return new String[] {f11};
            }
            folderStr1 = f11.substring(begIndexOfFileNum1, endIndexOfFileNum1);
            folderStrN = f11.substring(begIndexOfFileNumN, endIndexOfFileNumN);
            if (folderStr1.length() > folderStrN.length()) {
                int lengthDiff = folderStr1.length() - folderStrN.length();
                String lengthFix = folderStr1.substring(0, lengthDiff);
                folderStrN = lengthFix + folderStrN;
            }
            fileNum1 = Integer.parseInt(folderStr1);
            fileNumN = Integer.parseInt(folderStrN);
            int fileNumDiff = fileNumN - fileNum1 + 1;
            if (fileNumDiff > 300) {
                errorLabel.setText("Cannot create more than 300 folders");
                return null;
            }
            String[] folderNameN = new String[fileNumDiff];
            for (int j = 0; j < fileNumDiff; j++) {
                folderNameN[j] = (fileNum1 + j) + endOfFolderName;
            }
            return folderNameN;
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        folderNameArea = new javax.swing.JTextArea();
        createButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        fileNameList = new javax.swing.JList<>();
        errorLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileNameList2 = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Folder Creator");
        setName("folderCreatorFrame"); // NOI18N

        folderNameArea.setEditable(false);
        folderNameArea.setColumns(20);
        folderNameArea.setRows(5);
        jScrollPane1.setViewportView(folderNameArea);

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        fileNameList.setModel(listModel);
        fileNameList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(fileNameList);

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel1.setText("Drag and drop PO's and SO's here:");

        jLabel2.setText("Folders that will be created:");

        jLabel3.setText("PO's and SO's will be copied to their respective folders. Other files will be copied to all folders.");
        jLabel3.setToolTipText("");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        fileNameList2.setModel(listModel2);
        fileNameList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(fileNameList2);

        jLabel4.setText("Drag and drop other files here:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel4)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(clearButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        if (fileList.size() > 0) {
            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);
                for (String s : folderNames.get(i)) {
                    Path dir = Paths.get(file.getParent() + fileSep + s);
                    if (Files.exists(dir)) {
                        errorLabel.setText("One or more folders already exist");
                    } else {
                        try {
                            Files.createDirectory(dir);
                            if (s.equals(folderNames.get(i)[folderNames.get(i).length - 1])) {
                                Files.move(file.toPath(), dir.resolve(file.toPath().getFileName()));
                            } else {
                                Files.copy(file.toPath(), dir.resolve(file.toPath().getFileName()));
                            }
                            if (fileList2.size() > 0) {
                                for (File f2 : fileList2)
                                    if (fileList.size() == i + 1 && s.equals(folderNames.get(i)[folderNames.get(i).length - 1])) {
                                        Files.move(f2.toPath(), dir.resolve(f2.toPath().getFileName()));
                                    } else {
                                        Files.copy(f2.toPath(), dir.resolve(f2.toPath().getFileName()));
                                    }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            folderNames.clear();
            fileList.clear();
            fileList2.clear();
            listModel.clear();
            listModel2.clear();
            folderNameArea.setText("");
        } else {
            errorLabel.setText("Nothing to create");
        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        folderNames.clear();
        fileList.clear();
        fileList2.clear();
        listModel.clear();
        listModel2.clear();
        folderNameArea.setText("");
        errorLabel.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("System".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FolderCreatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FolderCreatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FolderCreatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FolderCreatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FolderCreatorGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JList<String> fileNameList;
    private javax.swing.JList<String> fileNameList2;
    private javax.swing.JTextArea folderNameArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
