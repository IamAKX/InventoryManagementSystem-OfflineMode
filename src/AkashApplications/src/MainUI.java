/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.src;


import AkashApplications.DB.DbUtils;
import AkashApplications.DB.ResetDataToDefault;
import AkashApplications.DB.StockResultSet;
import AkashApplications.DB.UpdateStock;
import AkashApplications.JsonParser.CustomJsonParser;
import AkashApplications.downloadBarcode.HttpDownloadUtility;
import AkashApplications.networkmanager.ClientAccess;
import AkashApplications.networkmanager.SaveClient;
import AkashApplications.networkmanager.SearchProduct;
import AkashApplications.networkmanager.SendBulkRequest;
import AkashApplications.networkmanager.SendRequest;
import AkashApplications.networkmanager.ServerConstants;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


/**
 *
 * @author akash
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form MainUI
     */
    
    public MainUI() {
        initComponents();
        
        setPanelVisibility(StockPane);
        setProgressBarVisibility();
        
        
        BulkItemTable.getColumnModel().getColumn(6).setMaxWidth(0);
        BulkItemTable.getColumnModel().getColumn(6).setMinWidth(0);
        BulkItemTable.getColumnModel().getColumn(6).setPreferredWidth(0);
        
        ClientTable.getColumnModel().getColumn(5).setMaxWidth(0);
        ClientTable.getColumnModel().getColumn(5).setMinWidth(0);
        ClientTable.getColumnModel().getColumn(5).setPreferredWidth(0);
        
        FavouriteClientTable.getColumnModel().getColumn(4).setMaxWidth(0);
        FavouriteClientTable.getColumnModel().getColumn(4).setMinWidth(0);
        FavouriteClientTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        
        FavouriteClientTable.getColumnModel().getColumn(5).setMaxWidth(0);
        FavouriteClientTable.getColumnModel().getColumn(5).setMinWidth(0);
        FavouriteClientTable.getColumnModel().getColumn(5).setPreferredWidth(0);
        
        printTable.getColumnModel().getColumn(3).setMaxWidth(0);
        printTable.getColumnModel().getColumn(3).setMinWidth(0);
        printTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        printTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        printTable.getColumnModel().getColumn(1).setPreferredWidth(320);
        
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dtm = (DefaultTableModel)ClientTable.getModel();
                int row = ClientTable.getSelectedRow();
                String content = ""+(String) dtm.getValueAt(row, 1)+"\n"+
                        (String) dtm.getValueAt(row, 2)+"\n"+
                        (String) dtm.getValueAt(row, 4);
                StringSelection selection = new StringSelection(content);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
        });
        popupMenu.add(copyItem);
        ClientTable.setComponentPopupMenu(popupMenu);
        
        JPopupMenu popupMenu1 = new JPopupMenu();
        JMenuItem copyItem1 = new JMenuItem("Copy");
        copyItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dtm = (DefaultTableModel)FavouriteClientTable.getModel();
                int row = FavouriteClientTable.getSelectedRow();
                String content = ""+(String) dtm.getValueAt(row, 1)+"\n"+
                        (String) dtm.getValueAt(row, 2)+"\n"+
                        (String) dtm.getValueAt(row, 4);
                StringSelection selection = new StringSelection(content);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
        });
        popupMenu1.add(copyItem1);
        FavouriteClientTable.setComponentPopupMenu(popupMenu1);
        
        
        
        JPopupMenu popupMenudelete = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dtm = (DefaultTableModel)StockSummaryTable.getModel();
                int row = StockSummaryTable.getSelectedRow();
                String pid = (String) dtm.getValueAt(row, 1);
                System.out.println(pid+"fddddd");
                String query = "DELETE FROM STOCK WHERE PID='"+pid+"'";
                int res = UpdateStock.update(query);
//                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put("id",pid);
//                String reply = new SendBulkRequest(hashMap, new ServerConstants(radioRuby.isSelected()).DELETE_STOCK).serverResponse();
//                System.err.println(pid+"\n"+reply);
//                CustomJsonParser cjp = new CustomJsonParser(reply);
                if(res!=-999)
                    new ViewAllSummaryProgress().execute();
                else
                    JOptionPane.showMessageDialog(null, "Failed to delete stock");
            }
        });
        popupMenudelete.add(deleteItem);
        StockSummaryTable.setComponentPopupMenu(popupMenudelete);
        
        
        setdate(printDate);
        printInvoiceNote.setText(setDeliveryNote());
        
        setTitle(buildTitle()+" - Stock");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
    
    
    public String buildTitle()
    {
        String TITLE = "Inventory Management (OFFLINE MODE) ";
        return TITLE;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        AddSingleItemPane = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        productIDAddSingle = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        designNameAddSingle = new javax.swing.JTextField();
        rackNoAddSingle = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        subRackNoAddSingle = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        qtyAddSingle = new javax.swing.JTextField();
        AddSingleProgress = new javax.swing.JProgressBar();
        AddSingleProgressReport = new javax.swing.JLabel();
        saveAddSingle = new javax.swing.JButton();
        barcodeAddSingle = new javax.swing.JLabel();
        generateBarcodeAddSingle = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        textureAddSingle = new javax.swing.JTextField();
        StockSummaryPane = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        productIDSSummary = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        designNameSSummary = new javax.swing.JTextField();
        searchBtnSSummary = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        StockSummaryTable = new javax.swing.JTable();
        StockSummaryProgress = new javax.swing.JProgressBar();
        StockSummaryProgressReport = new javax.swing.JLabel();
        viewAllBtnSSummary = new javax.swing.JButton();
        updateStockSummary = new javax.swing.JButton();
        ConfirmUpdateCB = new javax.swing.JCheckBox();
        AddBulkItemPane = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        productIDAddBulk = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        designNameAddBulk = new javax.swing.JTextField();
        qtyAddBulk = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        subRackNoAddBulk = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rackNoAddBulk = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        barcodeAddBulk = new javax.swing.JLabel();
        addBtnAddBulk = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        BulkItemTable = new javax.swing.JTable();
        progressReportAddBulk = new javax.swing.JLabel();
        progressBarAddBulk = new javax.swing.JProgressBar();
        saveAllBtnAddBulk = new javax.swing.JButton();
        textureAddBulk = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        StockPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        productID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rackNo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        subRackNo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        designName = new javax.swing.JTextField();
        deleteStockBtn = new javax.swing.JButton();
        editUpdateBtn = new javax.swing.JButton();
        mainUiProgress = new javax.swing.JProgressBar();
        mainUiProgressReport = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        texture = new javax.swing.JTextField();
        AddClient = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        ClientName = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        ClientComapny = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ClientAddress = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        ClientPhone = new javax.swing.JTextField();
        ClientAdd = new javax.swing.JButton();
        ClientDelete = new javax.swing.JButton();
        ClientFavouriteCheckbox = new javax.swing.JCheckBox();
        ClientUpdate = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ClientTable = new javax.swing.JTable();
        clientViewAll = new javax.swing.JButton();
        clientSearchBtn = new javax.swing.JButton();
        ClientID = new javax.swing.JLabel();
        PrintChalanPanel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        printBuyerDetail = new javax.swing.JTextArea();
        printInvoiceNote = new javax.swing.JLabel();
        printDate = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        printProductId = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        printQty = new javax.swing.JTextField();
        printAddBtn = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        printTable = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        printPrintBtn = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        paymentType = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        FavouriteClientPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        FavouriteClientTable = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        FavClientName = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        FavClientComapny = new javax.swing.JTextField();
        FavSearchBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OFFLINE MODE Inventory Management");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddSingleItemPane.setPreferredSize(new java.awt.Dimension(810, 540));

        jLabel8.setText("Product ID");

        productIDAddSingle.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                productIDAddSingleInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productIDAddSingle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                productIDAddSingleKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productIDAddSingleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productIDAddSingleKeyReleased(evt);
            }
        });

        jLabel9.setText("Design Name");

        rackNoAddSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rackNoAddSingleActionPerformed(evt);
            }
        });

        jLabel10.setText("Sub Rack Number");

        jLabel11.setText("Rack Number");
        jLabel11.setPreferredSize(new java.awt.Dimension(73, 15));

        jLabel12.setText("Stock Quantity");

        AddSingleProgress.setIndeterminate(true);

        AddSingleProgressReport.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        AddSingleProgressReport.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        saveAddSingle.setText("Save");
        saveAddSingle.setEnabled(false);
        saveAddSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAddSingleActionPerformed(evt);
            }
        });

        generateBarcodeAddSingle.setText("Generate Barcode");
        generateBarcodeAddSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateBarcodeAddSingleActionPerformed(evt);
            }
        });

        jLabel19.setText("Texture");
        jLabel19.setPreferredSize(new java.awt.Dimension(73, 15));

        textureAddSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textureAddSingleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddSingleItemPaneLayout = new javax.swing.GroupLayout(AddSingleItemPane);
        AddSingleItemPane.setLayout(AddSingleItemPaneLayout);
        AddSingleItemPaneLayout.setHorizontalGroup(
            AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddSingleItemPaneLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddSingleItemPaneLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddSingleItemPaneLayout.createSequentialGroup()
                                .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AddSingleItemPaneLayout.createSequentialGroup()
                                        .addComponent(rackNoAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(subRackNoAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(qtyAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AddSingleItemPaneLayout.createSequentialGroup()
                                        .addComponent(productIDAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(designNameAddSingle)))
                                .addGap(29, 29, 29))
                            .addGroup(AddSingleItemPaneLayout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(textureAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddSingleItemPaneLayout.createSequentialGroup()
                        .addComponent(generateBarcodeAddSingle)
                        .addGap(18, 18, 18)
                        .addComponent(saveAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AddSingleProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddSingleProgressReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(AddSingleItemPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barcodeAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddSingleItemPaneLayout.setVerticalGroup(
            AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSingleItemPaneLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(productIDAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddSingleItemPaneLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(designNameAddSingle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rackNoAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subRackNoAddSingle)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qtyAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textureAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(AddSingleItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveAddSingle)
                    .addComponent(generateBarcodeAddSingle))
                .addGap(30, 30, 30)
                .addComponent(AddSingleProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddSingleProgressReport, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barcodeAddSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        getContentPane().add(AddSingleItemPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel6.setText("Product ID");

        productIDSSummary.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                productIDSSummaryInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productIDSSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productIDSSummaryActionPerformed(evt);
            }
        });
        productIDSSummary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                productIDSSummaryKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productIDSSummaryKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productIDSSummaryKeyReleased(evt);
            }
        });

        jLabel7.setText("Design Name");

        searchBtnSSummary.setText("Search");
        searchBtnSSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnSSummaryActionPerformed(evt);
            }
        });

        StockSummaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL. No.", "Product ID", "Design", "Rack", "Sub Rack", "Texture", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StockSummaryTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(StockSummaryTable);
        StockSummaryTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        StockSummaryProgress.setIndeterminate(true);

        StockSummaryProgressReport.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        viewAllBtnSSummary.setText("View All");
        viewAllBtnSSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllBtnSSummaryActionPerformed(evt);
            }
        });

        updateStockSummary.setText("Update");
        updateStockSummary.setEnabled(false);
        updateStockSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockSummaryActionPerformed(evt);
            }
        });

        ConfirmUpdateCB.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        ConfirmUpdateCB.setText("  Confirm updating the products");
        ConfirmUpdateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmUpdateCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StockSummaryPaneLayout = new javax.swing.GroupLayout(StockSummaryPane);
        StockSummaryPane.setLayout(StockSummaryPaneLayout);
        StockSummaryPaneLayout.setHorizontalGroup(
            StockSummaryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockSummaryPaneLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(productIDSSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(designNameSSummary)
                .addGap(37, 37, 37))
            .addGroup(StockSummaryPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StockSummaryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StockSummaryProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StockSummaryProgressReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(StockSummaryPaneLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(ConfirmUpdateCB)
                        .addGap(40, 40, 40)
                        .addComponent(updateStockSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewAllBtnSSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(searchBtnSSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        StockSummaryPaneLayout.setVerticalGroup(
            StockSummaryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockSummaryPaneLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(StockSummaryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StockSummaryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(productIDSSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StockSummaryPaneLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(StockSummaryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(designNameSSummary, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addGroup(StockSummaryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBtnSSummary)
                    .addGroup(StockSummaryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateStockSummary)
                        .addComponent(ConfirmUpdateCB))
                    .addComponent(viewAllBtnSSummary))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(StockSummaryProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StockSummaryProgressReport, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(StockSummaryPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 540));

        AddBulkItemPane.setPreferredSize(new java.awt.Dimension(810, 540));

        jLabel13.setText("Product ID");

        productIDAddBulk.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                productIDAddBulkInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productIDAddBulk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                productIDAddBulkKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productIDAddBulkKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productIDAddBulkKeyReleased(evt);
            }
        });

        jLabel14.setText("Design Name");

        jLabel15.setText("Stock Quantity");

        jLabel16.setText("Sub Rack Number");

        rackNoAddBulk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rackNoAddBulkActionPerformed(evt);
            }
        });

        jLabel17.setText("Rack Number");
        jLabel17.setPreferredSize(new java.awt.Dimension(73, 15));

        addBtnAddBulk.setText("Add");
        addBtnAddBulk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnAddBulkActionPerformed(evt);
            }
        });

        BulkItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Design", "Rack", "Sub Rack", "Texture", "Quantity", "Barcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(BulkItemTable);
        if (BulkItemTable.getColumnModel().getColumnCount() > 0) {
            BulkItemTable.getColumnModel().getColumn(6).setResizable(false);
            BulkItemTable.getColumnModel().getColumn(6).setPreferredWidth(0);
        }

        progressReportAddBulk.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        saveAllBtnAddBulk.setText("Save All");
        saveAllBtnAddBulk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAllBtnAddBulkActionPerformed(evt);
            }
        });

        textureAddBulk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textureAddBulkActionPerformed(evt);
            }
        });

        jLabel18.setText("Texture");
        jLabel18.setPreferredSize(new java.awt.Dimension(73, 15));

        javax.swing.GroupLayout AddBulkItemPaneLayout = new javax.swing.GroupLayout(AddBulkItemPane);
        AddBulkItemPane.setLayout(AddBulkItemPaneLayout);
        AddBulkItemPaneLayout.setHorizontalGroup(
            AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                        .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(progressReportAddBulk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(progressBarAddBulk, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                            .addComponent(saveAllBtnAddBulk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddBulkItemPaneLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                        .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textureAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addBtnAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addComponent(barcodeAddBulk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                        .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                                .addComponent(productIDAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(designNameAddBulk))
                            .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                                .addComponent(rackNoAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subRackNoAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qtyAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(35, 35, 35))
        );
        AddBulkItemPaneLayout.setVerticalGroup(
            AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(productIDAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(designNameAddBulk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rackNoAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subRackNoAddBulk)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qtyAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barcodeAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddBulkItemPaneLayout.createSequentialGroup()
                        .addGroup(AddBulkItemPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textureAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBtnAddBulk)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(saveAllBtnAddBulk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progressBarAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressReportAddBulk, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(AddBulkItemPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setText("Product ID");

        productID.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                productIDInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        productID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                productIDKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productIDKeyReleased(evt);
            }
        });

        jLabel3.setText("Rack Number");
        jLabel3.setPreferredSize(new java.awt.Dimension(73, 15));

        rackNo.setEnabled(false);
        rackNo.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                rackNoInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        rackNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rackNoActionPerformed(evt);
            }
        });
        rackNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rackNoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rackNoKeyReleased(evt);
            }
        });

        jLabel1.setText("Sub Rack Number");

        subRackNo.setEnabled(false);

        jLabel4.setText("Stock Quantity");

        qty.setEnabled(false);

        jLabel5.setText("Design Name");

        designName.setEnabled(false);

        deleteStockBtn.setText("Delete");
        deleteStockBtn.setEnabled(false);
        deleteStockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStockBtnActionPerformed(evt);
            }
        });

        editUpdateBtn.setText("Edit");
        editUpdateBtn.setEnabled(false);
        editUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUpdateBtnActionPerformed(evt);
            }
        });

        mainUiProgress.setIndeterminate(true);

        mainUiProgressReport.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        mainUiProgressReport.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        searchBtn.setText("Search");
        searchBtn.setEnabled(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jLabel20.setText("Texture");
        jLabel20.setPreferredSize(new java.awt.Dimension(73, 15));

        texture.setEnabled(false);
        texture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StockPaneLayout = new javax.swing.GroupLayout(StockPane);
        StockPane.setLayout(StockPaneLayout);
        StockPaneLayout.setHorizontalGroup(
            StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockPaneLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StockPaneLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(texture, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(StockPaneLayout.createSequentialGroup()
                        .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(StockPaneLayout.createSequentialGroup()
                                .addComponent(rackNo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subRackNo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(StockPaneLayout.createSequentialGroup()
                                .addComponent(productID, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(designName)))
                        .addGap(41, 41, 41))))
            .addGroup(StockPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(editUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(deleteStockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainUiProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainUiProgressReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        StockPaneLayout.setVerticalGroup(
            StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockPaneLayout.createSequentialGroup()
                .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StockPaneLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StockPaneLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(designName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rackNo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                    .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subRackNo)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(texture, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116)
                .addGroup(StockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteStockBtn)
                    .addComponent(editUpdateBtn)
                    .addComponent(searchBtn))
                .addGap(30, 30, 30)
                .addComponent(mainUiProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainUiProgressReport, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(StockPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 540));

        AddClient.setPreferredSize(new java.awt.Dimension(810, 540));

        jLabel21.setText("Name");

        ClientName.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                ClientNameInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ClientName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ClientNameKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClientNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ClientNameKeyReleased(evt);
            }
        });

        jLabel22.setText("Company");

        ClientComapny.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                ClientComapnyInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ClientComapny.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ClientComapnyKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClientComapnyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ClientComapnyKeyReleased(evt);
            }
        });

        jLabel24.setText("Address");

        ClientAddress.setColumns(20);
        ClientAddress.setRows(5);
        jScrollPane1.setViewportView(ClientAddress);

        jLabel23.setText("Phone Number");

        ClientPhone.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                ClientPhoneInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        ClientPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ClientPhoneKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClientPhoneKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ClientPhoneKeyReleased(evt);
            }
        });

        ClientAdd.setText("Add");
        ClientAdd.setEnabled(false);
        ClientAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientAddActionPerformed(evt);
            }
        });

        ClientDelete.setText("Delete");
        ClientDelete.setEnabled(false);
        ClientDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientDeleteActionPerformed(evt);
            }
        });

        ClientFavouriteCheckbox.setText("  Mark as favourite");

        ClientUpdate.setText("Update");
        ClientUpdate.setEnabled(false);
        ClientUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientUpdateActionPerformed(evt);
            }
        });

        ClientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Client Name", "Company", "Contact", "Address", "Favourite", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ClientTable.setColumnSelectionAllowed(true);
        ClientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClientTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ClientTable);
        ClientTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (ClientTable.getColumnModel().getColumnCount() > 0) {
            ClientTable.getColumnModel().getColumn(5).setResizable(false);
        }

        clientViewAll.setText("View All");
        clientViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientViewAllActionPerformed(evt);
            }
        });

        clientSearchBtn.setText("Search");
        clientSearchBtn.setEnabled(false);
        clientSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientSearchBtnActionPerformed(evt);
            }
        });
        clientSearchBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                clientSearchBtnKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout AddClientLayout = new javax.swing.GroupLayout(AddClient);
        AddClient.setLayout(AddClientLayout);
        AddClientLayout.setHorizontalGroup(
            AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddClientLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(AddClientLayout.createSequentialGroup()
                        .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(AddClientLayout.createSequentialGroup()
                                .addComponent(ClientName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(ClientComapny, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddClientLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(AddClientLayout.createSequentialGroup()
                                            .addComponent(jLabel23)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(ClientPhone))
                                        .addGroup(AddClientLayout.createSequentialGroup()
                                            .addComponent(ClientFavouriteCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(61, 61, 61)
                                            .addComponent(ClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addGroup(AddClientLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(clientViewAll)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(clientSearchBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ClientUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ClientDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ClientAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(19, 19, 19))
        );
        AddClientLayout.setVerticalGroup(
            AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddClientLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClientComapny, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(AddClientLayout.createSequentialGroup()
                            .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ClientFavouriteCheckbox)
                                .addComponent(ClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(AddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ClientAdd)
                                .addComponent(ClientDelete)
                                .addComponent(ClientUpdate)
                                .addComponent(clientViewAll)
                                .addComponent(clientSearchBtn)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(AddClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        PrintChalanPanel.setPreferredSize(new java.awt.Dimension(810, 540));

        jLabel25.setText("Buyer Name and Address");

        printBuyerDetail.setColumns(20);
        printBuyerDetail.setRows(5);
        jScrollPane6.setViewportView(printBuyerDetail);

        printInvoiceNote.setText("00000");

        jLabel31.setText("Product ID");

        printProductId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                printProductIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                printProductIdKeyReleased(evt);
            }
        });

        jLabel32.setText("Quantity");

        printQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                printQtyKeyReleased(evt);
            }
        });

        printAddBtn.setText("Add");
        printAddBtn.setEnabled(false);
        printAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printAddBtnActionPerformed(evt);
            }
        });

        printTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sl. No.", "Description of Good", "Qty", "productId"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        printTable.setFocusTraversalPolicyProvider(true);
        jScrollPane8.setViewportView(printTable);
        if (printTable.getColumnModel().getColumnCount() > 0) {
            printTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jLabel29.setText("Payment method");

        printPrintBtn.setText("Print");
        printPrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPrintBtnActionPerformed(evt);
            }
        });

        jLabel34.setText("Delivery Note : ");

        paymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cash", "credit" }));

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PrintChalanPanelLayout = new javax.swing.GroupLayout(PrintChalanPanel);
        PrintChalanPanel.setLayout(PrintChalanPanelLayout);
        PrintChalanPanelLayout.setHorizontalGroup(
            PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintChalanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrintChalanPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane8)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintChalanPanelLayout.createSequentialGroup()
                        .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PrintChalanPanelLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintChalanPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(printDate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PrintChalanPanelLayout.createSequentialGroup()
                                        .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(paymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(printInvoiceNote, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(PrintChalanPanelLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printQty, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(printAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrintChalanPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(printPrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PrintChalanPanelLayout.setVerticalGroup(
            PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintChalanPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrintChalanPanelLayout.createSequentialGroup()
                        .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(printDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(printInvoiceNote)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(paymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PrintChalanPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addGap(32, 32, 32)))
                .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(printQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(printAddBtn)
                    .addComponent(jButton1))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PrintChalanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printPrintBtn)
                    .addComponent(jButton2))
                .addGap(27, 27, 27))
        );

        getContentPane().add(PrintChalanPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        FavouriteClientPanel.setPreferredSize(new java.awt.Dimension(810, 540));

        FavouriteClientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Client Name", "Company", "Contact", "Address", "Favourite", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        FavouriteClientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FavouriteClientTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(FavouriteClientTable);
        FavouriteClientTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (FavouriteClientTable.getColumnModel().getColumnCount() > 0) {
            FavouriteClientTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel26.setText("Name");

        FavClientName.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                FavClientNameInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        FavClientName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FavClientNameKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FavClientNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FavClientNameKeyReleased(evt);
            }
        });

        jLabel27.setText("Company");

        FavClientComapny.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                FavClientComapnyInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        FavClientComapny.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FavClientComapnyKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FavClientComapnyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FavClientComapnyKeyReleased(evt);
            }
        });

        FavSearchBtn.setText("Search");
        FavSearchBtn.setEnabled(false);
        FavSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FavSearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FavouriteClientPanelLayout = new javax.swing.GroupLayout(FavouriteClientPanel);
        FavouriteClientPanel.setLayout(FavouriteClientPanelLayout);
        FavouriteClientPanelLayout.setHorizontalGroup(
            FavouriteClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FavouriteClientPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(FavouriteClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FavouriteClientPanelLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FavClientName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FavClientComapny, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FavSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        FavouriteClientPanelLayout.setVerticalGroup(
            FavouriteClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FavouriteClientPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(FavouriteClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FavClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FavClientComapny, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FavSearchBtn))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(FavouriteClientPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenu1.setText("File");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Print Chalan");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);
        jMenu1.add(jSeparator1);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("About");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setText("Exit");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Stock");

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Stock");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Stock Summary");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Add Single Item");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Add Bulk Item ");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Client");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Client Record");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Favourite Client");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Data");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Reset ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rackNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rackNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rackNoActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void productIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_productIDKeyTyped

    private void productIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == 10)
        {
            editUpdateBtn.setEnabled(true);
            new FetchStockResult().execute();
            
        }
        else
            editUpdateBtn.setEnabled(false);
    }//GEN-LAST:event_productIDKeyPressed

    private void productIDInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_productIDInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_productIDInputMethodTextChanged

    private void productIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDKeyReleased
        // TODO add your handling code here:
        if(productID.getText().trim().length() == 0)
        {
            searchBtn.setEnabled(false);
            deleteStockBtn.setEnabled(false);
        }
        else
        {
            searchBtn.setEnabled(true);
            deleteStockBtn.setEnabled(true);
        }
    }//GEN-LAST:event_productIDKeyReleased

    private void productIDSSummaryInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_productIDSSummaryInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDSSummaryInputMethodTextChanged

    private void productIDSSummaryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDSSummaryKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDSSummaryKeyTyped

    private void productIDSSummaryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDSSummaryKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDSSummaryKeyPressed

    private void productIDSSummaryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDSSummaryKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDSSummaryKeyReleased

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        setTitle(buildTitle() + " - Stock");
        setPanelVisibility(StockPane);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        setTitle(buildTitle() + " - Stock Summary");
        setPanelVisibility(StockSummaryPane);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void productIDAddSingleInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_productIDAddSingleInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDAddSingleInputMethodTextChanged

    private void productIDAddSingleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDAddSingleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDAddSingleKeyTyped

    private void productIDAddSingleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDAddSingleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDAddSingleKeyPressed

    private void productIDAddSingleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDAddSingleKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDAddSingleKeyReleased

    private void rackNoAddSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rackNoAddSingleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rackNoAddSingleActionPerformed

    private void productIDAddBulkInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_productIDAddBulkInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDAddBulkInputMethodTextChanged

    private void productIDAddBulkKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDAddBulkKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDAddBulkKeyTyped

    private void productIDAddBulkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDAddBulkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDAddBulkKeyPressed

    private void productIDAddBulkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productIDAddBulkKeyReleased
        barcodeAddBulk.setIcon(null);
    }//GEN-LAST:event_productIDAddBulkKeyReleased

    private void rackNoAddBulkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rackNoAddBulkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rackNoAddBulkActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        setTitle(buildTitle() + " - Add Bulk Product");
        setPanelVisibility(AddBulkItemPane);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        setTitle(buildTitle() + " - Add Single Product");
        setPanelVisibility(AddSingleItemPane);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void editUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUpdateBtnActionPerformed
        // TODO add your handling code here:
        if(editUpdateBtn.getText().equalsIgnoreCase("Edit"))
        {
            designName.setEnabled(true);
            rackNo.setEnabled(true);
            subRackNo.setEnabled(true);
            texture.setEnabled(true);
            qty.setEnabled(true);
            editUpdateBtn.setText("Update");
        }
        else if(editUpdateBtn.getText().equalsIgnoreCase("Update"))
        {
            
                String query = "UPDATE STOCK SET DESIGN='"+designName.getText()+"',RACK='"+rackNo.getText()+"',SUBRACK='"+subRackNo.getText()+"',TEXTURE='"+texture.getText()+"',QUANTITY= "+qty.getText()+" WHERE PID='"+productID.getText()+"'";
//                HashMap<String,String> map = new HashMap<String,String>()
//                map.put("pid",productID.getText());
//                map.put("design",designName.getText());
//                map.put("rack",rackNo.getText());
//                map.put("subrack",subRackNo.getText());
//                map.put("texture",texture.getText());
//                map.put("qty",qty.getText());
//                
//                String s = new SendRequest(mainUiProgressReport, mainUiProgress, map, new ServerConstants(radioRuby.isSelected()).UPDATE_PRODUCT).serverResponse();
//                System.err.println(s);
//                CustomJsonParser parser = new CustomJsonParser(s);
                int res = UpdateStock.update(query);
                System.err.println(res);
                if(res!=-999)
                {
                    designName.setEnabled(false);
                    rackNo.setEnabled(false);
                    subRackNo.setEnabled(false);
                    texture.setEnabled(false);
                    qty.setEnabled(false);
                    editUpdateBtn.setText("Edit");
                    mainUiProgress.setValue(0);
                    mainUiProgressReport.setText("Updated successfully");
                    
                    designName.setText("");
                    //new FetchStockResult().execute();
                    
                    productID.setText("");
                    designName.setText("");
                    rackNo.setText("");
                    subRackNo.setText("");
                    texture.setText("");
                    qty.setText("");
                    productID.requestFocusInWindow();
                }
                else
                {
                    mainUiProgress.setValue(0);
                    mainUiProgressReport.setText("Failed to update");
                }
            
        }
            
    }//GEN-LAST:event_editUpdateBtnActionPerformed

    private void generateBarcodeAddSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateBarcodeAddSingleActionPerformed
        // TODO add your handling code here:
        String pId = productIDAddSingle.getText();
        String design = designNameAddSingle.getText();
        String rack = rackNoAddSingle.getText();
        String subRack = subRackNoAddSingle.getText();
        String qty = qtyAddSingle.getText();
        String desc = textureAddSingle.getText();
        if(pId.equals(""))
            JOptionPane.showMessageDialog(null,"Enter Product ID","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(design.equals(""))
                JOptionPane.showMessageDialog(null,"Enter Design name","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(rack.equals(""))
                JOptionPane.showMessageDialog(null,"Enter rack number","Alert",JOptionPane.WARNING_MESSAGE);
            else
                if(!validateNumberInput(rack))
                    JOptionPane.showMessageDialog(null,"Enter valid rack number","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(subRack.equals(""))
                JOptionPane.showMessageDialog(null,"Enter sub rack number","Alert",JOptionPane.WARNING_MESSAGE);
            else
                if(!validateNumberInput(subRack))
                    JOptionPane.showMessageDialog(null,"Enter valid sub rack number","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(qty.equals(""))
                JOptionPane.showMessageDialog(null,"Enter stock quantity","Alert",JOptionPane.WARNING_MESSAGE);
            else
                if(!validateNumberInput(qty))
                    JOptionPane.showMessageDialog(null,"Enter valid stock quantity count","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(desc.equals(""))
                JOptionPane.showMessageDialog(null,"Enter product description","Alert",JOptionPane.WARNING_MESSAGE);
        else
            {
            try {
                if(new GenerateBarcode(pId, pId).encode())
                {
                    barcodeAddSingle.setIcon(null);
                    ImageIcon iconLogo = new ImageIcon(new ImageIcon("Barcodes" + File.separator + pId+".jpg").getImage().getScaledInstance(barcodeAddSingle.getWidth(), barcodeAddSingle.getHeight(), Image.SCALE_SMOOTH));
                    barcodeAddSingle.setIcon(iconLogo);
                    saveAddSingle.setEnabled(true);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Failed to generate barcode\n\nError : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            }
    }//GEN-LAST:event_generateBarcodeAddSingleActionPerformed

    private void saveAddSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAddSingleActionPerformed
        new AddSingleItemProgress().execute();
    }//GEN-LAST:event_saveAddSingleActionPerformed

    class AddSingleItemProgress extends SwingWorker<Integer, String>
    {

        @Override
        protected Integer doInBackground() throws Exception {
            AddSingleProgress.setVisible(true);
            
                    try {
                        // TODO add your handling code here:

                        String pId = productIDAddSingle.getText();
                        String design = designNameAddSingle.getText();
                        String rack = rackNoAddSingle.getText();
                        String subRack = subRackNoAddSingle.getText();
                        String qty = qtyAddSingle.getText();
                        String texture = textureAddSingle.getText();

                        
                        
            String query = "INSERT INTO STOCK(PID, DESIGN, RACK, SUBRACK, TEXTURE, QUANTITY, BARCODE) VALUES "+
                                "('"+pId+"','"+design+"','"+rack+"','"+subRack+"','"+texture+"',"+qty+",'"+GenerateBarcode.convertToBase64("Barcodes" + File.separator + pId+".jpg")+"')";            
//                        
//                        
//
//                        HashMap<String,String> map = new HashMap<String,String>();
//                        map.put("pid",pId);
//                        map.put("design",design);
//                        map.put("rack",rack);
//                        map.put("subrack",subRack);
//                        map.put("texture",texture);
//                        map.put("qty",qty);
//                        map.put("barcode",GenerateBarcode.convertToBase64("Barcodes" + File.separator + pId+".jpg"));
//                        
//                        
//                        
//                        String s = new SendRequest(AddSingleProgressReport,AddSingleProgress,map,new ServerConstants(radioRuby.isSelected()).ADD_PRODUCT).serverResponse();
//                        System.err.println(s);
//                        CustomJsonParser parser = new CustomJsonParser(s);

                        int res = UpdateStock.update(query);
                        if(res!=-999)
                        {

                            AddSingleProgressReport.setText("");

                            productIDAddSingle.setText("");
                            designNameAddSingle.setText("");
                            rackNoAddSingle.setText("");
                            subRackNoAddSingle.setText("");
                            qtyAddSingle.setText("");
                            textureAddSingle.setText("");
                            saveAddSingle.setEnabled(false);
                            barcodeAddSingle.setIcon(null);
                        }
                        else
                        {

                            AddSingleProgressReport.setText("Failed to insert");
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
            AddSingleProgress.setVisible(false);
            return 0;
        }
        
    }
    
    private void addBtnAddBulkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnAddBulkActionPerformed
        // TODO add your handling code here:
        progressReportAddBulk.setText("Generating and saving barcode...");
        
        String pId = productIDAddBulk.getText();
        String design = designNameAddBulk.getText();
        String rack = rackNoAddBulk.getText();
        String subRack = subRackNoAddBulk.getText();
        String qty = qtyAddBulk.getText();
        String desc = textureAddBulk.getText();
        if(pId.equals(""))
            JOptionPane.showMessageDialog(null,"Enter Product ID","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(design.equals(""))
                JOptionPane.showMessageDialog(null,"Enter Design name","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(rack.equals(""))
                JOptionPane.showMessageDialog(null,"Enter rack number","Alert",JOptionPane.WARNING_MESSAGE);
            else
                if(!validateNumberInput(rack))
                    JOptionPane.showMessageDialog(null,"Enter valid rack number","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(subRack.equals(""))
                JOptionPane.showMessageDialog(null,"Enter sub rack number","Alert",JOptionPane.WARNING_MESSAGE);
            else
                if(!validateNumberInput(subRack))
                    JOptionPane.showMessageDialog(null,"Enter valid sub rack number","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(qty.equals(""))
                JOptionPane.showMessageDialog(null,"Enter stock quantity","Alert",JOptionPane.WARNING_MESSAGE);
            else
                if(!validateNumberInput(qty))
                    JOptionPane.showMessageDialog(null,"Enter valid stock quantity count","Alert",JOptionPane.WARNING_MESSAGE);
        else
            if(desc.equals(""))
                JOptionPane.showMessageDialog(null,"Enter product description","Alert",JOptionPane.WARNING_MESSAGE);
        else
            {
            try {
                if(new GenerateBarcode(pId, pId).encode())
                {
                    barcodeAddBulk.setIcon(null);
                    ImageIcon iconLogo = new ImageIcon(new ImageIcon("Barcodes" + File.separator + pId+".jpg").getImage().getScaledInstance(barcodeAddBulk.getWidth(), barcodeAddBulk.getHeight(), Image.SCALE_SMOOTH));
                    barcodeAddBulk.setIcon(iconLogo);
                    
                    DefaultTableModel model = (DefaultTableModel) BulkItemTable.getModel();
                    Object[] row = {pId,design,rack,subRack,desc,qty,GenerateBarcode.convertToBase64("Barcodes" + File.separator + pId+".jpg")};
                    model.addRow(row);
                    
                    productIDAddBulk.setText("");
                    designNameAddBulk.setText("");
                    rackNoAddBulk.setText("");
                    subRackNoAddBulk.setText("");
                    qtyAddBulk.setText("");
                    textureAddBulk.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Failed to generate barcode\n\nError : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
         }
        progressReportAddBulk.setText("");
        
    }//GEN-LAST:event_addBtnAddBulkActionPerformed

    private void textureAddSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textureAddSingleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textureAddSingleActionPerformed

    private void textureAddBulkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textureAddBulkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textureAddBulkActionPerformed

    private void textureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textureActionPerformed

    private void saveAllBtnAddBulkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAllBtnAddBulkActionPerformed
        // TODO add your handling code here:
       new SaveAllBulkItemProgress().execute();
    }//GEN-LAST:event_saveAllBtnAddBulkActionPerformed

    class SaveAllBulkItemProgress extends SwingWorker<Integer, String>
    {

        @Override
        protected Integer doInBackground() throws Exception {
            
            ArrayList<ProductModel> list = new ArrayList<>();
            progressBarAddBulk.setIndeterminate(true);
            progressReportAddBulk.setText("Preparing Table");
            for(int i=0; i<BulkItemTable.getRowCount(); i++)
            {
                try {
                    list.add(new ProductModel(String.valueOf(BulkItemTable.getValueAt(i, 0)),
                            String.valueOf(BulkItemTable.getValueAt(i, 1)),
                            String.valueOf(BulkItemTable.getValueAt(i, 2)),
                            String.valueOf(BulkItemTable.getValueAt(i, 3)),
                            String.valueOf(BulkItemTable.getValueAt(i, 4)),
                            String.valueOf(BulkItemTable.getValueAt(i, 5)),
                            String.valueOf(BulkItemTable.getValueAt(i, 6))));


                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
            progressBarAddBulk.setIndeterminate(false);
            progressReportAddBulk.setText("Requestings server...");
            int length = list.size();

            for(int i = 0; i < length; i++ )
            {
                try {

                    ProductModel pm = list.get(i);
                    
                    String query = "INSERT INTO STOCK(PID, DESIGN, RACK, SUBRACK, TEXTURE, QUANTITY, BARCODE) VALUES "+
                                "('"+pm.getPid()+"','"+pm.getDesign()+"','"+pm.getRack()+"','"+pm.getSubrack()+"','"+pm.getTexture()+"',"+pm.getQty()+",'"+pm.getBarcode()+"')";

                    int res = UpdateStock.update(query);

//                    HashMap<String,String> map = new HashMap<>();
//                    map.put("pid",pm.getPid());
//                    map.put("design",pm.getDesign());
//                    map.put("rack",pm.getRack());
//                    map.put("subrack",pm.getSubrack());
//                    map.put("texture",pm.getTexture());
//                    map.put("qty",pm.getQty());
//                    map.put("barcode",pm.getBarcode());
                    
                    progressReportAddBulk.setText("Saving "+pm.getDesign()+"... ("+(i+1)+" of "+length+")");
                    
//                    String s = new SendBulkRequest(map,new ServerConstants(radioRuby.isSelected()).ADD_PRODUCT).serverResponse();
//                    CustomJsonParser jsonParser = new CustomJsonParser(s);

                    progressBarAddBulk.setValue(i*100/length);
                    //System.err.println(jsonParser);
                    if(res==-999)
                    {
                        JOptionPane.showMessageDialog(null,"Data entry was interrupted");
                        break;
                    }
                } catch (Exception ex) {

                }
            }
            DefaultTableModel tableModel = (DefaultTableModel) BulkItemTable.getModel();
            while(tableModel.getRowCount() > 0)
             {
                 for(int i = 0; i < tableModel.getRowCount(); i++)
                 {
                     tableModel.removeRow(i);
                 }
             }
            progressBarAddBulk.setValue(100);
             progressReportAddBulk.setText("All products were saved successfully...");
            
            
            
            return 0;
        }
        
    }
    
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        new FetchStockResult().execute();
    }//GEN-LAST:event_searchBtnActionPerformed
    class FetchStockResult extends SwingWorker<Integer, String>
    {
        

        @Override
        protected Integer doInBackground() throws Exception {
            mainUiProgress.setVisible(true);
            fetchSingleResult();
            mainUiProgress.setVisible(false);
            return 0;
        }
        
    }
    
    private void rackNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rackNoKeyTyped
        // TODO add your handling code here:
       
    }//GEN-LAST:event_rackNoKeyTyped

    private void rackNoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_rackNoInputMethodTextChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_rackNoInputMethodTextChanged

    private void searchBtnSSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnSSummaryActionPerformed
        new SearchStockProgress().execute();
    }//GEN-LAST:event_searchBtnSSummaryActionPerformed

    class SearchStockProgress extends SwingWorker<Integer, String>
    {

        @Override
        protected Integer doInBackground() throws Exception {
            StockSummaryProgress.setVisible(true);
            StockSummaryProgress.setIndeterminate(true);
            StockSummaryProgressReport.setText("Searching products...");
            
            
            DefaultTableModel tableModel = (DefaultTableModel) StockSummaryTable.getModel();
            ArrayList<ProductModel> list = new ArrayList<>();
            int i = 0;
            while(tableModel.getRowCount() > 0)
            {
                for(i = 0; i < tableModel.getRowCount(); i++)
                {
                    tableModel.removeRow(i);
                }
            }
            
            String pID = productIDSSummary.getText();
            String design = designNameSSummary.getText();
            
            if(pID.length()==0)
                pID = null;
            
            if(design.length()==0)
                design = null;
            System.out.println(pID+"---"+design);
            
            String query = "";
            if(pID == null && design!=null)
            {
                query = "SELECT ID,PID AS ProductID,DESIGN,RACK,SUBRACK,TEXTURE,QUANTITY FROM STOCK WHERE DESIGN LIKE '%"+design+"%'";
                System.err.println(query);
            }
            else
                if(pID!=null && design==null)
                {
                    query = "SELECT ID,PID AS ProductID,DESIGN,RACK,SUBRACK,TEXTURE,QUANTITY FROM STOCK WHERE PID LIKE '%"+pID+"%'";
                    System.err.println(query);
                }
            else
                if(pID==null && design==null)
                {
                    query = "SELECT ID,PID AS ProductID,DESIGN,RACK,SUBRACK,TEXTURE,QUANTITY FROM STOCK";
                    System.err.println(query);
                }
            else
                {
                    query = "SELECT ID,PID AS ProductID,DESIGN,RACK,SUBRACK,TEXTURE,QUANTITY FROM STOCK WHERE PID LIKE '%"+pID+"%' AND DESIGN LIKE '%"+design+"%'";
                    System.err.println(query);
                }
            
            ResultSet rs = StockResultSet.fetchStock(query);
            if(rs.next())
                StockSummaryTable.setModel(DbUtils.resultSetToTableModel(rs));
            else
                JOptionPane.showMessageDialog(null, "Failed to fetch product", "Information", JOptionPane.INFORMATION_MESSAGE);
            rs.close();
            
            
//            HashMap<String,String> map = new HashMap<>()
//            map.put("pid", pID);
//            map.put("design", design);
//
//            list = new SearchProduct(map, new ServerConstants(radioRuby.isSelected()).SEARCH_PRODUCT).allResult();
//            if(list.size() > 0)
//            {
//                i = 0;
//                while(i < list.size())
//                {
//                    tableModel.insertRow(i, new Object[]{
//                                        String.valueOf(i+1),
//                                        list.get(i).getPid(),
//                                        list.get(i).getDesign(),
//                                        list.get(i).getRack(),
//                                        list.get(i).getSubrack(),
//                                        list.get(i).getTexture(),
//                                        list.get(i).getQty()
//                                        });
//                    i++;
//                }
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null, "Failed to fetch product", "Information", JOptionPane.INFORMATION_MESSAGE);
//            }
            StockSummaryProgress.setVisible(false);
            StockSummaryProgress.setIndeterminate(false);
            StockSummaryProgressReport.setText("");
            return 0;
        }
        
    }
    private void viewAllBtnSSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllBtnSSummaryActionPerformed
        new ViewAllSummaryProgress().execute();
    }//GEN-LAST:event_viewAllBtnSSummaryActionPerformed

    class ViewAllSummaryProgress extends SwingWorker<Integer, String>
    {

        @Override
        protected Integer doInBackground() throws Exception {
            
            StockSummaryProgress.setVisible(true);
            StockSummaryProgress.setIndeterminate(true);
            StockSummaryProgressReport.setText("Fetching products...");
            DefaultTableModel tableModel = (DefaultTableModel) StockSummaryTable.getModel();
            ArrayList<ProductModel> list = new ArrayList<>();
            int i = 0;
            while(tableModel.getRowCount() > 0)
            {
                for(i = 0; i < tableModel.getRowCount(); i++)
                {
                    tableModel.removeRow(i);
                }
            }
            
            String query = "SELECT ID,PID AS ProductID,DESIGN,RACK,SUBRACK,TEXTURE,QUANTITY FROM STOCK";
            ResultSet rs = StockResultSet.fetchStock(query);
            if(rs.next())
                StockSummaryTable.setModel(DbUtils.resultSetToTableModel(rs));
            else
                JOptionPane.showMessageDialog(null, "Failed to fetch product", "Information", JOptionPane.INFORMATION_MESSAGE);
            rs.close();
            
//            HashMap<String,String> map = new HashMap<>();
//            map.put("pid", "");
//            map.put("design", "");
//
//            list = new SearchProduct(map, new ServerConstants(radioRuby.isSelected()).SEARCH_PRODUCT).allResult();
//            if(list.size() > 0)
//            {
//                i = 0;
//                while(i < list.size())
//                {
//                    tableModel.insertRow(i, new Object[]{
//                                        String.valueOf(i+1),
//                                        list.get(i).getPid(),
//                                        list.get(i).getDesign(),
//                                        list.get(i).getRack(),
//                                        list.get(i).getSubrack(),
//                                        list.get(i).getTexture(),
//                                        list.get(i).getQty()
//                                        });
//                    i++;
//                }
//            }
////            else
////            {
////                JOptionPane.showMessageDialog(null, "Failed to fetch product", "Information", JOptionPane.INFORMATION_MESSAGE);
////            }

            StockSummaryProgress.setVisible(false);
            StockSummaryProgress.setIndeterminate(false);
            StockSummaryProgressReport.setText("");
            return 0;
        }
        
    }
    
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        setPanelVisibility(AddClient);
        setTitle(buildTitle() + " - Client Record");
        fetchClients("", "");
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        setPanelVisibility(FavouriteClientPanel);
        setTitle(buildTitle() + " - Favourite Client");
        fetchFavouriteClients("", "");
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void clientViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientViewAllActionPerformed
        // TODO add your handling code here:
        fetchClients("", "");
    }//GEN-LAST:event_clientViewAllActionPerformed

    private void ClientUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientUpdateActionPerformed
        // TODO add your handling code here:
        String id = ClientID.getText();
        if(id.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Click on the client from the below table you want to update.", "Alert",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String name = ClientName.getText();
        String comapny = ClientComapny.getText();
        String address = ClientAddress.getText();
        String phoneNo = ClientPhone.getText();
        String favoirite = ClientFavouriteCheckbox.isSelected() ? "Yes" : "No";
        if(name.equals(""))
        JOptionPane.showMessageDialog(null,"Enter Client name","Alert",JOptionPane.WARNING_MESSAGE);
        else
        if(comapny.equals(""))
        JOptionPane.showMessageDialog(null,"Enter Company name","Alert",JOptionPane.WARNING_MESSAGE);
        else
        if(!phoneNo.equals("") && (!validateNumberInput(phoneNo) || !(phoneNo.length()>=10 && phoneNo.length()<=12)))
        JOptionPane.showMessageDialog(null,"Enter valid phone","Alert",JOptionPane.WARNING_MESSAGE);
        else
        {
            String query = "UPDATE CLIENT SET NAME='"+name+"',COMPANY='"+comapny+"',PHONE='"+phoneNo+"',ADDRESS='"+address+"',FAVOURITE='"+favoirite+"' WHERE ID="+id;
            
            int res = UpdateStock.update(query);
            
//            HashMap<String,String> hm = new HashMap<>();
//            hm.put("id", id);
//            hm.put("name", name);
//            hm.put("company", comapny);
//            hm.put("contact", phoneNo);
//            hm.put("address", address);
//            hm.put("favourite", favoirite);
//            String serverReply = new SaveClient(hm, new ServerConstants(radioRuby.isSelected()).UPDATE_CLIENT).serverResponse();
//
//            CustomJsonParser parser = new CustomJsonParser(serverReply);
            if(res!=-999)
            {
                fetchClients("","");
                ClientUpdate.setEnabled(false);
                ClientDelete.setEnabled(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed to update client");
            }

        }

        
    }//GEN-LAST:event_ClientUpdateActionPerformed

    private void ClientDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientDeleteActionPerformed
      String id = ClientID.getText();
        if(id.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Click on the client from the below table you want to update.", "Alert",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String name = ClientName.getText();
        String comapny = ClientComapny.getText();
        String address = ClientAddress.getText();
        String phoneNo = ClientPhone.getText();
        String favoirite = ClientFavouriteCheckbox.isSelected() ? "Yes" : "No";
        if(name.equals(""))
        JOptionPane.showMessageDialog(null,"Enter Client name","Alert",JOptionPane.WARNING_MESSAGE);
        else
        if(comapny.equals(""))
        JOptionPane.showMessageDialog(null,"Enter Company name","Alert",JOptionPane.WARNING_MESSAGE);
        else
        if(!phoneNo.equals("") && (!validateNumberInput(phoneNo) || !(phoneNo.length()>=10 && phoneNo.length()<=12)))
        JOptionPane.showMessageDialog(null,"Enter valid phone","Alert",JOptionPane.WARNING_MESSAGE);
        else
        {
            String query = "DELETE FROM CLIENT WHERE ID="+id;
            
            int res = UpdateStock.update(query);
//
//            HashMap<String,String> hm = new HashMap<>();
//            hm.put("id", id);
//            hm.put("name", name);
//            hm.put("company", comapny);
//            hm.put("contact", phoneNo);
//            hm.put("address", address);
//            hm.put("favourite", favoirite);
//            String serverReply = new SaveClient(hm, new ServerConstants(radioRuby.isSelected()).DELETE_CLIENT).serverResponse();
//
//            CustomJsonParser parser = new CustomJsonParser(serverReply);
            if(res!=-999)
            {
                fetchClients("","");
                ClientUpdate.setEnabled(false);
                ClientDelete.setEnabled(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed to delete client");
            }

        }

    }//GEN-LAST:event_ClientDeleteActionPerformed

    private void ClientAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientAddActionPerformed
        String name = ClientName.getText();
        String comapny = ClientComapny.getText();
        String address = ClientAddress.getText();
        String phoneNo = ClientPhone.getText();
        String favoirite = ClientFavouriteCheckbox.isSelected() ? "Yes" : "No";
        if(name.equals(""))
        JOptionPane.showMessageDialog(null,"Enter Client name","Alert",JOptionPane.WARNING_MESSAGE);
        else
        if(comapny.equals(""))
        JOptionPane.showMessageDialog(null,"Enter Company name","Alert",JOptionPane.WARNING_MESSAGE);
        else
        if(!phoneNo.equals("") && (!validateNumberInput(phoneNo) || !(phoneNo.length()>=10 && phoneNo.length()<=12)))
        JOptionPane.showMessageDialog(null,"Enter valid phone","Alert",JOptionPane.WARNING_MESSAGE);
        else
        {
            
            String query = "INSERT INTO CLIENT(NAME, COMPANY, PHONE, ADDRESS, FAVOURITE) VALUES ('"+name+"','"+comapny+"','"+phoneNo+"','"+address+"','"+favoirite+"')";
            int res = UpdateStock.update(query);
//            
//            HashMap<String,String> hm = new HashMap<>();
//            hm.put("name", name);
//            hm.put("company", comapny);
//            hm.put("contact", phoneNo);
//            hm.put("address", address);
//            hm.put("favourite", favoirite);
//            String serverReply = new SaveClient(hm, new ServerConstants(radioRuby.isSelected()).SAVE_CLIENT).serverResponse();
//
//            CustomJsonParser parser = new CustomJsonParser(serverReply);
            if(res!=-999)
            {
                fetchClients("","");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed to add client");
            }

        }

    }//GEN-LAST:event_ClientAddActionPerformed

    private void ClientPhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientPhoneKeyReleased

    }//GEN-LAST:event_ClientPhoneKeyReleased

    private void ClientPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientPhoneKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientPhoneKeyPressed

    private void ClientPhoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientPhoneKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientPhoneKeyTyped

    private void ClientPhoneInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ClientPhoneInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientPhoneInputMethodTextChanged

    private void ClientComapnyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientComapnyKeyReleased
        if(ClientName.getText().trim().length()>0 && ClientComapny.getText().trim().length()>0)
        {
            ClientAdd.setEnabled(true);
            
        }
        else
        {
            ClientAdd.setEnabled(false);
            
        }
        
        if(ClientName.getText().trim().length()>0 || ClientComapny.getText().trim().length()>0)
            clientSearchBtn.setEnabled(true);
        else
            clientSearchBtn.setEnabled(false);
    }//GEN-LAST:event_ClientComapnyKeyReleased

    private void ClientComapnyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientComapnyKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientComapnyKeyPressed

    private void ClientComapnyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientComapnyKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientComapnyKeyTyped

    private void ClientComapnyInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ClientComapnyInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientComapnyInputMethodTextChanged

    private void ClientNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientNameKeyReleased
        if(ClientName.getText().trim().length()>0 && ClientComapny.getText().trim().length()>0)
        {
            ClientAdd.setEnabled(true);
        }
        else
        {
            ClientAdd.setEnabled(false);
        }
        
        if(ClientName.getText().trim().length()>0 || ClientComapny.getText().trim().length()>0)
            clientSearchBtn.setEnabled(true);
        else
            clientSearchBtn.setEnabled(false);
        
    }//GEN-LAST:event_ClientNameKeyReleased

    private void ClientNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientNameKeyPressed

    private void ClientNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientNameKeyTyped

    }//GEN-LAST:event_ClientNameKeyTyped

    private void ClientNameInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ClientNameInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientNameInputMethodTextChanged

    private void clientSearchBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientSearchBtnKeyReleased
        
    }//GEN-LAST:event_clientSearchBtnKeyReleased

    private void clientSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientSearchBtnActionPerformed
        // TODO add your handling code here:
        fetchClients(ClientName.getText(), ClientComapny.getText());
    }//GEN-LAST:event_clientSearchBtnActionPerformed

    private void ClientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClientTableMouseClicked
        // TODO add your handling code here:
        ClientUpdate.setEnabled(true);
        ClientDelete.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel) ClientTable.getModel();
        int selectedRowIndex = ClientTable.getSelectedRow();
        
        ClientName.setText((String) model.getValueAt(selectedRowIndex, 1));
        ClientComapny.setText((String) model.getValueAt(selectedRowIndex, 2));
        ClientPhone.setText((String) model.getValueAt(selectedRowIndex, 3));
        ClientAddress.setText((String) model.getValueAt(selectedRowIndex, 4));
        ClientFavouriteCheckbox.setSelected(String.valueOf(model.getValueAt(selectedRowIndex, 5)).equalsIgnoreCase("Yes"));
        ClientID.setText(String.valueOf(model.getValueAt(selectedRowIndex, 0)));
    }//GEN-LAST:event_ClientTableMouseClicked

    private void FavouriteClientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FavouriteClientTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_FavouriteClientTableMouseClicked

    private void FavClientNameInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_FavClientNameInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_FavClientNameInputMethodTextChanged

    private void FavClientNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FavClientNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_FavClientNameKeyTyped

    private void FavClientNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FavClientNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FavClientNameKeyPressed

    private void FavClientNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FavClientNameKeyReleased
        // TODO add your handling code here:
        if(!FavClientName.getText().equals("") || !FavClientComapny.getText().equals(""))
            FavSearchBtn.setEnabled(true);
        else
            FavSearchBtn.setEnabled(false);
    }//GEN-LAST:event_FavClientNameKeyReleased

    private void FavClientComapnyInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_FavClientComapnyInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_FavClientComapnyInputMethodTextChanged

    private void FavClientComapnyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FavClientComapnyKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_FavClientComapnyKeyTyped

    private void FavClientComapnyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FavClientComapnyKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FavClientComapnyKeyPressed

    private void FavClientComapnyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FavClientComapnyKeyReleased
        if(!FavClientName.getText().equals("") || !FavClientComapny.getText().equals(""))
            FavSearchBtn.setEnabled(true);
        else
            FavSearchBtn.setEnabled(false);
    }//GEN-LAST:event_FavClientComapnyKeyReleased

    private void FavSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FavSearchBtnActionPerformed
        String name = FavClientName.getText().trim();
        String company = FavClientComapny.getText().trim();
        fetchFavouriteClients(name,company);
        FavClientComapny.setText("");
        FavClientName.setText("");
        FavSearchBtn.setEnabled(false);
    }//GEN-LAST:event_FavSearchBtnActionPerformed

    private void rackNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rackNoKeyReleased
        if(rackNo.getText().trim().length() > 0)
            editUpdateBtn.setEnabled(true);
        else
            editUpdateBtn.setEnabled(false);
    }//GEN-LAST:event_rackNoKeyReleased

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        setTitle(buildTitle() + " - Print Chalan");
        setPanelVisibility(PrintChalanPanel);
        printBuyerDetail.requestFocusInWindow();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void printAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printAddBtnActionPerformed
        // TODO add your handling code here:
        if(!validateNumberInput(printQty.getText().trim()))
            JOptionPane.showMessageDialog(null,"Enter valid quantity","Alert",JOptionPane.WARNING_MESSAGE);
        else
            {
            try {
                String query = "SELECT PID,DESIGN,TEXTURE FROM STOCK WHERE PID='"+printProductId.getText()+"'";
                
                ResultSet rs = StockResultSet.fetchStock(query);
//                HashMap<String,String> map = new HashMap<>();
//                map.put("pid",printProductId.getText());
//                ProductModel model = new SearchProduct(map, new ServerConstants(radioRuby.isSelected()).GET_PRODUCT).specificResult(printProductId.getText());
                if(!rs.next())
                {
                    JOptionPane.showMessageDialog(null,"No prodct with ID "+printProductId.getText()+" was found in stock.\nPlease verify the product ID.","Alert",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    String pid = rs.getString("PID");
                    String design = rs.getString("DESIGN");
                    String texturee = rs.getString("TEXTURE");
                    rs.close();
                    
                    System.err.println(pid+"  "+design+"  "+texturee);
                    query = "UPDATE STOCK SET QUANTITY= QUANTITY- "+printQty.getText()+" WHERE PID= '"+printProductId.getText()+"'";
                    int res = UpdateStock.update(query);
                    
//                    HashMap<String,String> map1 = new HashMap<>();
//                    map1.put("id", printProductId.getText());
//                    map1.put("qty",printQty.getText());
//                    String reply = new SendBulkRequest(map1, new ServerConstants(radioRuby.isSelected()).DECREMENT_STOCK).serverResponse();
//                    System.err.println(reply);
//                    CustomJsonParser parser = new CustomJsonParser(reply);
                    
                    
                    if(res!=-999)
                    {
                        DefaultTableModel dtm = (DefaultTableModel) printTable.getModel();
                        Object[] row = {String.valueOf(dtm.getRowCount()+1), design+" ("+texturee+")", printQty.getText(),pid};
                        dtm.addRow(row);

                        printProductId.setText("");
                        printQty.setText("");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Failed to update stock at server","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
            
    }//GEN-LAST:event_printAddBtnActionPerformed

    private void printProductIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_printProductIdKeyReleased
        // TODO add your handling code here:
        if(printProductId.getText().isEmpty() || printQty.getText().isEmpty())
            printAddBtn.setEnabled(false);
        else
            printAddBtn.setEnabled(true);
    }//GEN-LAST:event_printProductIdKeyReleased

    private void printQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_printQtyKeyReleased
        if(printProductId.getText().isEmpty() || printQty.getText().isEmpty())
            printAddBtn.setEnabled(false);
        else
            printAddBtn.setEnabled(true);
    }//GEN-LAST:event_printQtyKeyReleased

    private void printPrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPrintBtnActionPerformed
        try {
            // TODO add your handling code here:
            
            new GenerateInvoice(printBuyerDetail.getText(),printInvoiceNote.getText(), printDate.getText(),printTable,paymentType.getSelectedItem().toString(),"(Original - Buyer's Copy)").printInvoice(true);
            new GenerateInvoice(printBuyerDetail.getText(),printInvoiceNote.getText(), printDate.getText(),printTable,paymentType.getSelectedItem().toString(),"(Duplicate - Seller's Copy)").printInvoice(true);
            new GenerateInvoice(printBuyerDetail.getText(),printInvoiceNote.getText(), printDate.getText(),printTable,paymentType.getSelectedItem().toString(),"(Triplicate Copy)").printInvoice(true);
            resetTable(printTable);
            printBuyerDetail.setText("");
            printBuyerDetail.requestFocusInWindow();
            new InvoiceNoteManager().setProperty();
            printInvoiceNote.setText(setDeliveryNote());
            
        } catch (InvalidFormatException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printPrintBtnActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Inventory Management - Release v1.0.1\n" +
                                            "Managing stocks and client for PLYWOOD MANOR\n" +
                                            "\n" +
                                            "- Feature (FEB 2017)\n" +
                                            "\t\t    * Add/View Stock details\n" +
                                            "\t\t    * Maintain client record\n" +
                                            "\t\t    * Generate/Download product ID barcode\n" +
                                            "\n" +
                                            "- Designed and Developed by\n" +
                                            "\t\t    AKASH GIRI\n" +
                                            "\t\t    \u00a9 Akash Application\n" +
                                            "\t\t    akx.sonu@gmail.com", "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            resetTable(printTable);
            printProductId.setText("");
            printQty.setText("");
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new GenerateInvoice(printBuyerDetail.getText(),printInvoiceNote.getText(), printDate.getText(),printTable,paymentType.getSelectedItem().toString(),"(Original - Buyer's Copy)").printInvoice(false);
            new GenerateInvoice(printBuyerDetail.getText(),printInvoiceNote.getText(), printDate.getText(),printTable,paymentType.getSelectedItem().toString(),"(Duplicate - Seller's Copy)").printInvoice(false);
            new GenerateInvoice(printBuyerDetail.getText(),printInvoiceNote.getText(), printDate.getText(),printTable,paymentType.getSelectedItem().toString(),"(Triplicate Copy)").printInvoice(false);
            resetTable(printTable);
            printBuyerDetail.setText("");
            printBuyerDetail.requestFocusInWindow();
            new InvoiceNoteManager().setProperty();
            printInvoiceNote.setText(setDeliveryNote());
            
        } catch (InvalidFormatException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void updateStockSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockSummaryActionPerformed
        // TODO add your handling code here:
        new UpdateFromStockPane().execute();
    }//GEN-LAST:event_updateStockSummaryActionPerformed

    private void printProductIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_printProductIdKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == 10)
            printQty.requestFocusInWindow();
    }//GEN-LAST:event_printProductIdKeyPressed

    private void ConfirmUpdateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmUpdateCBActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) StockSummaryTable.getModel();
        if(model.getRowCount()<1)
        {
            JOptionPane.showMessageDialog(null, "No product listed in the table. Click on View All or Search product you want to update.","Alert",JOptionPane.WARNING_MESSAGE);
            if(ConfirmUpdateCB.isSelected())
            {
                ConfirmUpdateCB.setSelected(false);
                updateStockSummary.setEnabled(false);
            }
            return;
        }
        if(ConfirmUpdateCB.isSelected())
        {
            updateStockSummary.setEnabled(true);
        }
        else
        {
            updateStockSummary.setEnabled(false);
        }
    }//GEN-LAST:event_ConfirmUpdateCBActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "All the stock quantity are going to be reset to default. Are you sure?", "Alert", JOptionPane.WARNING_MESSAGE);
        
        if(input==0)
        {
           new ResetDataToDefault().execute();
           JOptionPane.showMessageDialog(null, "Data restored successfully", "Done", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void productIDSSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productIDSSummaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productIDSSummaryActionPerformed

    private void deleteStockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockBtnActionPerformed
        // TODO add your handling code here:
        
        String query = "DELETE FROM STOCK WHERE PID = '"+productID.getText()+"'";
        int res = UpdateStock.update(query);
        System.err.println(res);
        if(res==1)
        {
            
            JOptionPane.showMessageDialog(null, "Data deleted successfully");
        }
        else
        {
            
            JOptionPane.showMessageDialog(null, "No data was found");
        }
    }//GEN-LAST:event_deleteStockBtnActionPerformed

    class UpdateFromStockPane extends SwingWorker<Integer, String>
    {

        @Override
        protected Integer doInBackground() throws Exception {
           StockSummaryProgress.setIndeterminate(false);
           StockSummaryProgress.setVisible(true);
            DefaultTableModel tableModel = (DefaultTableModel) StockSummaryTable.getModel();
            int size = tableModel.getRowCount();
            boolean failed = false;
            String failedProducts = "";
           for(int i=0;i<size;i++)
           {
               StockSummaryProgress.setValue((i*100)/size);
               StockSummaryProgressReport.setText("Updating products "+(i+1)+" of "+size+" ...");
               String query = "UPDATE STOCK SET DESIGN='"+tableModel.getValueAt(i, 2)+"',RACK='"+tableModel.getValueAt(i, 3)+"',SUBRACK='"+tableModel.getValueAt(i, 4)+"',TEXTURE='"+tableModel.getValueAt(i, 5)+"',QUANTITY= "+tableModel.getValueAt(i, 6)+" WHERE PID='"+tableModel.getValueAt(i, 1)+"'";
               
               int res = UpdateStock.update(query);
               
               if(res==-999)
                {
                    failed = true;
                    JOptionPane.showMessageDialog(null, "Internal Server Error");
                    failedProducts+= (String) tableModel.getValueAt(i, 1)+", ";
                    break;
                }

//               HashMap<String,String> map = new HashMap<>();
//               map.put("pid", );
//                map.put("design",);
//                map.put("rack",);
//                map.put("subrack",);
//                map.put("texture",);
//                map.put("qty",);
//                String s = new SendBulkRequest(map, new ServerConstants(radioRuby.isSelected()).UPDATE_PRODUCT).serverResponse();
//                System.out.println(s);
//                CustomJsonParser parser = new CustomJsonParser(s);
//                if(!parser.getStatus())
//                {
//                    failed = true;
//                    JOptionPane.showMessageDialog(null, "Internal Server Error : \n"+parser.getReason(), "Server Error", JOptionPane.ERROR_MESSAGE);
//                    failedProducts+= (String) tableModel.getValueAt(i, 1)+", ";
//                }
                StockSummaryProgressReport.setText("");
           }
            
           StockSummaryProgress.setIndeterminate(false);
           StockSummaryProgress.setVisible(false);
           if(failed)
           {
               JOptionPane.showMessageDialog(null, "Following product were failed to update:\n"+failedProducts, "Failed to update", JOptionPane.WARNING_MESSAGE);
           }
            return 0; 
        }

        
    }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddBulkItemPane;
    private javax.swing.JPanel AddClient;
    private javax.swing.JPanel AddSingleItemPane;
    private javax.swing.JProgressBar AddSingleProgress;
    private javax.swing.JLabel AddSingleProgressReport;
    private javax.swing.JTable BulkItemTable;
    private javax.swing.JButton ClientAdd;
    private javax.swing.JTextArea ClientAddress;
    private javax.swing.JTextField ClientComapny;
    private javax.swing.JButton ClientDelete;
    private javax.swing.JCheckBox ClientFavouriteCheckbox;
    private javax.swing.JLabel ClientID;
    private javax.swing.JTextField ClientName;
    private javax.swing.JTextField ClientPhone;
    private javax.swing.JTable ClientTable;
    private javax.swing.JButton ClientUpdate;
    private javax.swing.JCheckBox ConfirmUpdateCB;
    private javax.swing.JTextField FavClientComapny;
    private javax.swing.JTextField FavClientName;
    private javax.swing.JButton FavSearchBtn;
    private javax.swing.JPanel FavouriteClientPanel;
    private javax.swing.JTable FavouriteClientTable;
    private javax.swing.JPanel PrintChalanPanel;
    private javax.swing.JPanel StockPane;
    private javax.swing.JPanel StockSummaryPane;
    private javax.swing.JProgressBar StockSummaryProgress;
    private javax.swing.JLabel StockSummaryProgressReport;
    private javax.swing.JTable StockSummaryTable;
    private javax.swing.JButton addBtnAddBulk;
    private javax.swing.JLabel barcodeAddBulk;
    private javax.swing.JLabel barcodeAddSingle;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clientSearchBtn;
    private javax.swing.JButton clientViewAll;
    private javax.swing.JButton deleteStockBtn;
    private javax.swing.JTextField designName;
    private javax.swing.JTextField designNameAddBulk;
    private javax.swing.JTextField designNameAddSingle;
    private javax.swing.JTextField designNameSSummary;
    private javax.swing.JButton editUpdateBtn;
    private javax.swing.JButton generateBarcodeAddSingle;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JProgressBar mainUiProgress;
    private javax.swing.JLabel mainUiProgressReport;
    private javax.swing.JComboBox<String> paymentType;
    private javax.swing.JButton printAddBtn;
    private javax.swing.JTextArea printBuyerDetail;
    private javax.swing.JTextField printDate;
    private javax.swing.JLabel printInvoiceNote;
    private javax.swing.JButton printPrintBtn;
    private javax.swing.JTextField printProductId;
    private javax.swing.JTextField printQty;
    private javax.swing.JTable printTable;
    private javax.swing.JTextField productID;
    private javax.swing.JTextField productIDAddBulk;
    private javax.swing.JTextField productIDAddSingle;
    private javax.swing.JTextField productIDSSummary;
    private javax.swing.JProgressBar progressBarAddBulk;
    private javax.swing.JLabel progressReportAddBulk;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qtyAddBulk;
    private javax.swing.JTextField qtyAddSingle;
    private javax.swing.JTextField rackNo;
    private javax.swing.JTextField rackNoAddBulk;
    private javax.swing.JTextField rackNoAddSingle;
    private javax.swing.JButton saveAddSingle;
    private javax.swing.JButton saveAllBtnAddBulk;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton searchBtnSSummary;
    private javax.swing.JTextField subRackNo;
    private javax.swing.JTextField subRackNoAddBulk;
    private javax.swing.JTextField subRackNoAddSingle;
    private javax.swing.JTextField texture;
    private javax.swing.JTextField textureAddBulk;
    private javax.swing.JTextField textureAddSingle;
    private javax.swing.JButton updateStockSummary;
    private javax.swing.JButton viewAllBtnSSummary;
    // End of variables declaration//GEN-END:variables

    private void setPanelVisibility(JPanel currentPanel) {
        StockPane.setVisible(false);
        StockSummaryPane.setVisible(false);
        AddSingleItemPane.setVisible(false);
        AddBulkItemPane.setVisible(false);
        AddClient.setVisible(false);
        FavouriteClientPanel.setVisible(false);
        PrintChalanPanel.setVisible(false);
        currentPanel.setVisible(true);
    }

    private boolean validateNumberInput(String num) {
        boolean isValid = false;
        for(char c : num.toCharArray())
            if(c < '0' || c > '9')
            {
                if(c != '.')
                    return isValid;
            }
        isValid = true;
        return isValid;
    }

    private void fetchSingleResult() {
        try {
            String pID = productID.getText().trim().equals("") ? " " : productID.getText();
            String design = " ";
            
//        HashMap<String,String> map = new HashMap<>();
//        map.put("pid", pID);
//        map.put("design", pID);
//    
//        ProductModel model = new SearchProduct(map, new ServerConstants(radioRuby.isSelected()).GET_PRODUCT).specificResult(pID);

            String query = "SELECT * FROM STOCK WHERE PID = '"+pID+"'";
            ResultSet rs = StockResultSet.fetchStock(query);
            if(!rs.next())
            {
                JOptionPane.showMessageDialog(null, "No product found with ID "+pID, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                productID.setText(rs.getString("PID"));
                designName.setText(rs.getString("DESIGN"));
                rackNo.setText(rs.getString("RACK"));
                subRackNo.setText(rs.getString("SUBRACK"));
                qty.setText(rs.getString("QUANTITY"));
                texture.setText(rs.getString("TEXTURE"));
                editUpdateBtn.setEnabled(true);

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fetchClients(String name, String company) {
        DefaultTableModel tableModel = (DefaultTableModel) ClientTable.getModel();
        //ArrayList<ClientModel> list = new ArrayList<>();
        int i = 0;
        while(tableModel.getRowCount() > 0)
        {
            for(i = 0; i < tableModel.getRowCount(); i++)
            {
                tableModel.removeRow(i);
            }
        }
        String query;
        if(name.length()==0 && company.length()==0)
            query = "SELECT * FROM CLIENT";
        else
            if(name.length()!=0 && company.length()==0)
                query = "SELECT * FROM CLIENT WHERE NAME LIKE '%"+name+"%'";
            else
                if(name.length()==0 && company.length()!=0)
                    query = "SELECT * FROM CLIENT WHERE COMPANY LIKE '%"+company+"%'";	
                else
                    query = "SELECT * FROM CLIENT WHERE NAME LIKE '%"+name+"%' OR COMPANY LIKE '%"+company+"%'";	
        
        //String query = "SELECT NAME,COMPANY,PHONE,ADDRESS,FAVOURITE,ID FROM CLIENT WHERE NAME LIKE '%"+name+"%' OR COMPANY LIKE '%"+company+"%'";	
        
        ResultSet rs  = StockResultSet.fetchStock(query);
        
        try {
            
            if(rs.next())
            {
                ClientTable.setModel(DbUtils.resultSetToTableModel(rs));
                System.err.println(query);
                rs.close();
            }
            else
                JOptionPane.showMessageDialog(null, "No saved client was found", "Information", JOptionPane.INFORMATION_MESSAGE);
        
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        
//        HashMap<String,String> map = new HashMap<>()
//        map.put("name", name);
//        map.put("company", company);
//        
//        list = new ClientAccess(map, new ServerConstants(radioRuby.isSelected()).FETCH_CLIENT).allResult();
//        if(list.size() > 0)
//        {
//            i = 0;
//            while(i < list.size())
//            {
//                tableModel.insertRow(i, new Object[]{
//                                    list.get(i).getName(),
//                                    list.get(i).getCompany(),
//                                    list.get(i).getContact(),
//                                    list.get(i).getAddress(),
//                                    list.get(i).getFavourite(),
//                                    list.get(i).getId()
//                                    });
//                i++;
//            }
//            
            ClientName.setText("");
            ClientAddress.setText("");
            ClientPhone.setText("");
            ClientComapny.setText("");
            ClientID.setText("");
            ClientFavouriteCheckbox.setSelected(false);
//        }
//        else
//        {
//            JOptionPane.showMessageDialog(null, "No saved client was found", "Information", JOptionPane.INFORMATION_MESSAGE);
//        }
        
    }
    
    private void fetchFavouriteClients(String name, String company) {
        DefaultTableModel tableModel = (DefaultTableModel) FavouriteClientTable.getModel();
        ArrayList<ClientModel> list = new ArrayList<>();
        int i = 0;
        
        while(tableModel.getRowCount() > 0)
        {
            for(i = 0; i < tableModel.getRowCount(); i++)
            {
                tableModel.removeRow(i);
            }
        }
        
        
        String query;
        if(name.length()==0 && company.length()==0)
            query = "SELECT * FROM CLIENT";
        else
            if(name.length()!=0 && company.length()==0)
                query = "SELECT * FROM CLIENT WHERE NAME LIKE '%"+name+"%'";
            else
                if(name.length()==0 && company.length()!=0)
                    query = "SELECT * FROM CLIENT WHERE COMPANY LIKE '%"+company+"%'";	
                else
                    query = "SELECT * FROM CLIENT WHERE NAME LIKE '%"+name+"%' OR COMPANY LIKE '%"+company+"%'";	
        
        //String query = "SELECT NAME,COMPANY,PHONE,ADDRESS,FAVOURITE,ID FROM CLIENT WHERE NAME LIKE '%"+name+"%' OR COMPANY LIKE '%"+company+"%'";	
        
        ResultSet rs  = StockResultSet.fetchStock(query);
        
        try {
            
            if(rs.next())
            {
                FavouriteClientTable.setModel(DbUtils.resultSetToTableModel(rs));
                
                rs.close();
            }
            else
                JOptionPane.showMessageDialog(null, "No saved client was found", "Information", JOptionPane.INFORMATION_MESSAGE);
        
        } catch (SQLException ex) {
            System.err.println(ex);
        }
//        
//        HashMap<String,String> map = new HashMap<>();
//        map.put("name", name);
//        map.put("company", company);
//        
//        list = new ClientAccess(map, new ServerConstants(radioRuby.isSelected()).FETCH_CLIENT).allResult();
//        if(list.size() > 0)
//        {
//            i = 0;
//            while(i < list.size())
//            {
//                if(!list.get(i).getFavourite().equalsIgnoreCase("Yes"))
//                {
//                    i++;
//                    continue;
//                }
//                tableModel.insertRow(i, new Object[]{
//                                    list.get(i).getName(),
//                                    list.get(i).getCompany(),
//                                    list.get(i).getContact(),
//                                    list.get(i).getAddress(),
//                                    list.get(i).getFavourite(),
//                                    list.get(i).getId()
//                                    });
//                i++;
//            }
//            
//        }
//        if(tableModel.getRowCount() == 0)
//        {
//            JOptionPane.showMessageDialog(null, "No saved client was found", "Information", JOptionPane.INFORMATION_MESSAGE);
//        }
        
    }

    private void setdate(JTextField printDate) {
         Date today = new Date();
         SimpleDateFormat parseFormat = new SimpleDateFormat("dd-MM-yyyy (EEEE)");
         String date = parseFormat.format(today);
         printDate.setText(date);
         System.out.println(date);
    }
    
    private String setDeliveryNote()
    {

        String s= "";
        try {
            s += new InvoiceNoteManager().getProperty();
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }    

    private void resetTable(JTable printTable) {
        DefaultTableModel tableModel = (DefaultTableModel) printTable.getModel();
        int i = 0;
        
        while(tableModel.getRowCount() > 0)
        {
            for(i = 0; i < tableModel.getRowCount(); i++)
            {
                tableModel.removeRow(i);
            }
        }
    }

    private void setProgressBarVisibility() {
        mainUiProgress.setVisible(false);
        AddSingleProgress.setVisible(false);
        StockSummaryProgress.setVisible(false);
    }
    
    
}
