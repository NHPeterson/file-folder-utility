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
public class FileFolderUtilityGUI extends javax.swing.JFrame
{
    public List<String[]> folderNames = new ArrayList<>();
    public List<File> fileList = new ArrayList<>(),
                        fileList2 = new ArrayList<>(),
                        fileList3 = new ArrayList<>(),
                        fileList4 = new ArrayList<>();
    public String fileSep = File.separator;
    public DefaultListModel listModel = new DefaultListModel();
    public DefaultListModel listModel2 = new DefaultListModel();
    public DefaultListModel listModel3 = new DefaultListModel();
    public DefaultListModel listModel4 = new DefaultListModel();
    /*
     * Creates new form FileFolderUtilityGUI
     */

    public FileFolderUtilityGUI()
    {
        initComponents();
        
        fileNameList.setTransferHandler(new TransferHandler()
        {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support)
            {
                if (!support.isDrop())
                {
                    return false;
                }
                if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
                {
                    return false;
                }
                return true;
            }
            @Override
            public boolean importData(TransferHandler.TransferSupport support)
            {
                errorLabel.setText("");
                if (!canImport(support))
                {
                    return false;
                }
                Transferable tr = support.getTransferable();
                try
                {
                    List<File> l = (List<File>)tr.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File f : l)
                    {
                        String[] s = makeFolderNames(f.getName());
                        if (s == null)
                        {
                            continue;
                        }
                        fileList.add(f);
                        listModel.addElement(f.getName());
                        folderNames.add(s);
                        for (String item : s)
                        {
                            folderNameArea.append(item + "\n");
                        }
                    }
                }
                catch (UnsupportedFlavorException | IOException e)
                {
                    return false;
                }
                return true;
            }
        });
        
        fileNameList2.setTransferHandler(new TransferHandler()
        {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support)
            {
                if (!support.isDrop())
                {
                    return false;
                }
                if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
                {
                    return false;
                }
                return true;
            }
            @Override
            public boolean importData(TransferHandler.TransferSupport support)
            {
                if (!canImport(support))
                {
                    return false;
                }
                Transferable tr = support.getTransferable();
                try
                {
                    List<File> l = (List<File>)tr.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File f : l)
                    {
                        fileList2.add(f);
                        listModel2.addElement(f.getName());
                    }
                } catch (UnsupportedFlavorException | IOException e)
                {
                    return false;
                }
                errorLabel.setText("");
                return true;
            }
        });
        
        fileNameList3.setTransferHandler(new TransferHandler()
        {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support)
            {
                if (!support.isDrop())
                {
                    return false;
                }
                if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
                {
                    return false;
                }
                return true;
            }
            @Override
            public boolean importData(TransferHandler.TransferSupport support)
            {
                if (!canImport(support))
                {
                    return false;
                }
                Transferable tr = support.getTransferable();
                try
                {
                    List<File> l = (List<File>)tr.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File f : l)
                    {
                        fileList3.add(f);
                        listModel3.addElement(f.getName());
                    }
                } catch (UnsupportedFlavorException | IOException e)
                {
                    return false;
                }
                errorLabel2.setText("");
                return true;
            }
        });
        
        fileNameList4.setTransferHandler(new TransferHandler()
        {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support)
            {
                if (!support.isDrop())
                {
                    return false;
                }
                if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
                {
                    return false;
                }
                return true;
            }
            @Override
            public boolean importData(TransferHandler.TransferSupport support)
            {
                if (!canImport(support))
                {
                    return false;
                }
                Transferable tr = support.getTransferable();
                try
                {
                    List<File> l = (List<File>)tr.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File f : l)
                    {
                        fileList4.add(f);
                        listModel4.addElement(f.getName());
                    }
                } catch (UnsupportedFlavorException | IOException e)
                {
                    return false;
                }
                errorLabel2.setText("");
                return true;
            }
        });
    }

    public String[] makeFolderNames(String folderNameInput)
    {
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

        if (folderNameInput == null || folderNameInput.isEmpty())
        {
            errorLabel.setText("One or more files is invalid");
            return null;
        }
        if (folderNameInput.indexOf('.') > 0)
        {
            folderNameInput = folderNameInput.substring(0, folderNameInput.lastIndexOf('.'));
        }
        for (int i = 0; i<folderNameInput.length(); i++)
        {
            if (bIOFN1set != true && folderNameInput.charAt(i) >= '0' && folderNameInput.charAt(i) <= '9')
            {
                begIndexOfFileNum1 = i;
                bIOFN1set = true;
                continue;
            }
            if (bIOFN1set == true && eIOFN1set != true && (folderNameInput.charAt(i) < '0' || '9' < folderNameInput.charAt(i)))
            {
                endIndexOfFileNum1 = i;
                eIOFN1set = true;
                endOfFolderName = folderNameInput.substring(i);
            }
            if (bIOFN1set == true && eIOFN1set == true && fileRange != true && folderNameInput.charAt(i) == '-')
            {
                fileRange = true;
                continue;
            }
            if (fileRange == true)
            {
                if (bIOFNNset != true && '0' <= folderNameInput.charAt(i) && folderNameInput.charAt(i) <= '9')
                {
                    begIndexOfFileNumN = i;
                    bIOFNNset = true;
                    continue;
                }
                if (bIOFNNset == true && eIOFNNset != true && (folderNameInput.charAt(i) < '0' || '9' < folderNameInput.charAt(i)))
                {
                    endIndexOfFileNumN = i;
                    eIOFNNset = true;
                    endOfFolderName = folderNameInput.substring(i);
                    break;
                }
            }
        }
        if (!bIOFN1set)
        {
            return new String[] {folderNameInput};
        }
        folderStr1 = folderNameInput.substring(begIndexOfFileNum1, endIndexOfFileNum1);
        folderStrN = folderNameInput.substring(begIndexOfFileNumN, endIndexOfFileNumN);
        if (folderStr1.length() > folderStrN.length())
        {
            int lengthDiff = folderStr1.length() - folderStrN.length();
            String lengthFix = folderStr1.substring(0, lengthDiff);
            folderStrN = lengthFix + folderStrN;
        }
        fileNum1 = Integer.parseInt(folderStr1);
        fileNumN = Integer.parseInt(folderStrN);
        int fileNumDiff = fileNumN - fileNum1 + 1;
        if (!limitCheck.isSelected() && fileNumDiff > 100)
        {
            errorLabel.setText("Cannot create more than 100 folders");
            return null;
        }
        String[] folderNameN = new String[fileNumDiff];
        for (int j = 0; j < fileNumDiff; j++)
        {
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
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
        limitCheck = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        fileNameList3 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        fileNameList4 = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        clearButton2 = new javax.swing.JButton();
        copyButton = new javax.swing.JButton();
        overwriteCheck = new javax.swing.JCheckBox();
        errorLabel2 = new javax.swing.JLabel();
        copyCheck = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("File-Folder Utility");
        setName("fileFolderUtilityFrame"); // NOI18N

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
        fileNameList.setVisibleRowCount(7);
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
        fileNameList2.setVisibleRowCount(7);
        jScrollPane2.setViewportView(fileNameList2);

        jLabel4.setText("Drag and drop other files here:");

        limitCheck.setText("Remove 100-folder limit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(limitCheck)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clearButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(createButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jScrollPane2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(88, 88, 88))
                                    .addComponent(jScrollPane1))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(limitCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton)
                    .addComponent(createButton))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Create", jPanel1);

        fileNameList3.setModel(listModel3);
        fileNameList3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fileNameList3.setVisibleRowCount(7);
        jScrollPane4.setViewportView(fileNameList3);

        fileNameList4.setModel(listModel4);
        fileNameList4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fileNameList4.setVisibleRowCount(7);
        jScrollPane5.setViewportView(fileNameList4);

        jLabel5.setText("Drag and drop files to copy here:");

        jLabel6.setText("Drag and drop folders to copy to here:");

        clearButton2.setText("Clear");
        clearButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton2ActionPerformed(evt);
            }
        });

        copyButton.setText("Copy");
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });

        overwriteCheck.setText("Allow files to be overwritten");

        errorLabel2.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        copyCheck.setText("Keep original files after copying");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(clearButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(copyButton))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(overwriteCheck)
                                    .addComponent(copyCheck))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copyCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(overwriteCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton2)
                    .addComponent(copyButton))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Copy", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (fileList.size() > 0)
        {
            for (int i = 0; i < fileList.size(); i++)
            {
                File file = fileList.get(i);
                for (String s : folderNames.get(i))
                {
                    Path dir = Paths.get(file.getParent() + fileSep + s);
                    if (Files.exists(dir))
                    {
                        errorLabel.setText("One or more folders already exist");
                    }
                    else
                    {
                        try
                        {
                            Files.createDirectory(dir);
                            if (s.equals(folderNames.get(i)[folderNames.get(i).length - 1]))
                            {
                                Files.move(file.toPath(), dir.resolve(file.toPath().getFileName()));
                            }
                            else
                            {
                                Files.copy(file.toPath(), dir.resolve(file.toPath().getFileName()));
                            }
                            if (fileList2.size() > 0)
                            {
                                for (File f2 : fileList2)
                                {
                                    if (fileList.size() == i + 1 && s.equals(folderNames.get(i)[folderNames.get(i).length - 1]))
                                    {
                                        Files.move(f2.toPath(), dir.resolve(f2.toPath().getFileName()));
                                    }
                                    else
                                    {
                                        Files.copy(f2.toPath(), dir.resolve(f2.toPath().getFileName()));
                                    }
                                }
                            }
                        }
                        catch (IOException e)
                        {
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
        }
        else
        {
            errorLabel.setText("Nothing to create");
        }
    }                                            

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        folderNames.clear();
        fileList.clear();
        fileList2.clear();
        listModel.clear();
        listModel2.clear();
        folderNameArea.setText("");
        errorLabel.setText("");
        limitCheck.setSelected(false);
    }                                           

    private void clearButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        fileList3.clear();
        fileList4.clear();
        listModel3.clear();
        listModel4.clear();
        errorLabel2.setText("");
        overwriteCheck.setSelected(false);
        copyCheck.setSelected(false);
    }                                            

    private void copyButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if (fileList3.size() > 0 && fileList4.size() > 0)
        {
            for (int i = 0; i < fileList3.size(); i++)
            {
                File file = fileList3.get(i);
                for (int j = 0; j < fileList4.size(); j++)
                {
                    File f2 = fileList4.get(j);
                    if (overwriteCheck.isSelected())
                    {
                        try
                        {
                            if (j == fileList4.size() - 1 && !copyCheck.isSelected())
                            {
                                Files.move(file.toPath(), f2.toPath().resolve(file.toPath().getFileName()), StandardCopyOption.REPLACE_EXISTING);
                            }
                            else
                            {
                                Files.copy(file.toPath(), f2.toPath().resolve(file.toPath().getFileName()), StandardCopyOption.REPLACE_EXISTING);
                            }
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try
                        {
                            if (j == fileList4.size() - 1 && !copyCheck.isSelected())
                            {
                                Files.move(file.toPath(), f2.toPath().resolve(file.toPath().getFileName()));
                            }
                            else
                            {
                                Files.copy(file.toPath(), f2.toPath().resolve(file.toPath().getFileName()));
                            }
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                            errorLabel2.setText("One or more files already exist in the folders");
                        }
                    }
                }
            }
            fileList3.clear();
            fileList4.clear();
            listModel3.clear();
            listModel4.clear();
        }
        else
        {
            errorLabel2.setText("Either files or folders are missing");
        }
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
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
            java.util.logging.Logger.getLogger(FileFolderUtilityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileFolderUtilityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileFolderUtilityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileFolderUtilityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new FileFolderUtilityGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton clearButton;
    private javax.swing.JButton clearButton2;
    private javax.swing.JButton copyButton;
    private javax.swing.JCheckBox copyCheck;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel errorLabel2;
    private javax.swing.JList<String> fileNameList;
    private javax.swing.JList<String> fileNameList2;
    private javax.swing.JList<String> fileNameList3;
    private javax.swing.JList<String> fileNameList4;
    private javax.swing.JTextArea folderNameArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox limitCheck;
    private javax.swing.JCheckBox overwriteCheck;
    // End of variables declaration                   
}
