package FAACbeans;

import FAAClib.E124_Remote;
import FAAClib.E145_Remote;
import FAAClib.FAAC_Commands;
import FAAClib.FAAC_Monitor;
import FAAClib.FAAC_Protocol;
import FAAClib.FAAC_Remote;
import FAAClib.FAAC_Settings;
import FAAClib.FAAC_frames;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class FAACRemote extends JPanel {
   private final int columnPosition = 0;
   private final int columnStatus = 1;
   private final int columnRadioCode = 2;
   private final int columnChannel = 3;
   private final int columnCodType = 4;
   private final int columnEnabled = 5;
   private final int tablePageSize = 50;
   private static final ResourceBundle bundleStatic = ResourceBundle.getBundle("FAACbeans/resources/FAACRemote");
   private ImageIcon iconOff;
   private ImageIcon iconOn;
   private ButtonGroup btnGrpOpenClosedList;
   private JButton jBtAcqCh1;
   private JButton jBtAcqCh2;
   private JButton jBtDeleteAllRemote;
   private JButton jBtDeleteSelRemote;
   private JButton jBtExportRadOnFile;
   private JButton jBtImportRadFromFile;
   private JButton jBtNext;
   private JButton jBtPrevious;
   private JButton jBtReadFromBoard;
   private JButton jBtSaveToCsvFile;
   private JButton jBtWriteOnBoard;
   private JCheckBox jCbTypeDS;
   private JCheckBox jCbTypeRC;
   private JCheckBox jCbTypeSH;
   private JCheckBox jChbEnabled;
   private JFileChooser jFileChooserOpenRadFile;
   private JFileChooser jFileChooserSaveRadFile;
   private JFileChooser jFileChooserSaveToCsv;
   private JLabel jLBModRemote;
   private JLabel jLabel1;
   private JLabel jLabel2;
   private JLabel jLabel4;
   private JLayeredPane jLayeredPane1;
   private JLabel jLbChannel;
   private JLabel jLbChannelLabel;
   private JLabel jLbCode;
   private JLabel jLbCoding;
   private JLabel jLbCodingLabel;
   private JLabel jLbNumCodes;
   private JLabel jLbPageNb;
   private JLabel jLbPos;
   private JLabel jLbPosCodeLabel;
   private JLabel jLbPosLabel;
   private JLabel jLbRemoteActive;
   private JLabel jLbTotPage;
   private JPanel jPanel1;
   private JPanel jPanel3;
   private JPanel jPanelCurrentCode;
   private JPanel jPanelEdit;
   private JPanel jPanelRead;
   private JPanel jPanelTypeEnable;
   private JPanel jPanelWrite;
   private JRadioButton jRbListClosed;
   private JRadioButton jRbListOpen;
   private JScrollPane jScrollPaneRemote;
   private JTable jTRemote;
   private ResourceBundle bundle;
   private boolean isRemoteToBeReadOnBoard;
   private FAAC_Remote boardRemote;
   private boolean isBoardConnected;
   private ImageIcon statusIcoOff;
   private ImageIcon statusIcoOn;
   private boolean[] isRemoteCodeToBeReadOnBoard;
   private boolean[] isRemoteCodeToBeWrittenOnBoard;
   private boolean radioReadFlag;
   private boolean radioWriteFlag;
   private boolean isProgRadioToBeReadFromBoard;
   private boolean isProgRadioToBeWrittenOnBoard;
   private FAACRemote.RemoteTableModel remModel;
   JButton upButton;
   JButton downButton;
   private int currentActiveCodeIndex;
   private int nbCodesMem;
   private int lastActiveCodeIndex;
   private FAAC_Monitor boardMonitor;
   private FAAC_Settings boardSettings;
   private BackgroundWorkDialog backgroundWorkDialog;
   private FAAC_Commands boardCommands;
   private boolean isNoMovCmdRequested;
   EventListenerList listenersFromBoard;
   private boolean isModRemote;
   private String boardModel;
   private boolean isTableAlreadyInit;
   Frame parentForDlgs;
   private boolean isSomeCodeDeleted;
   private boolean isSomeCodeReadFromFile;
   private boolean isSomeCodeModified;

   public void updateLocale() {
      this.initComponentsForLocalization();
      this.jFileChooserSaveRadFile.setDialogTitle(this.bundle.getString("exportRadOnFile"));
      this.jFileChooserOpenRadFile.setDialogTitle(this.bundle.getString("openRadFile"));
      this.jFileChooserSaveRadFile.setDialogTitle(this.bundle.getString("SaveCsvOnFile"));
      this.upButton.setToolTipText(this.bundle.getString("pagePrevious"));
      this.downButton.setToolTipText(this.bundle.getString("pageNext"));
      this.repaint();
   }

   public void setParentForDialogs(Frame parent) {
      this.parentForDlgs = parent;
   }

   public FAACRemote() {
      this.initComponents();
      this.parentForDlgs = null;
      this.isSomeCodeDeleted = false;
      this.isSomeCodeReadFromFile = false;
      this.isSomeCodeModified = false;
      this.jLBModRemote.setVisible(false);
      this.isModRemote = false;
      this.listenersFromBoard = new EventListenerList();
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACRemote");
      this.isTableAlreadyInit = false;
      this.jFileChooserSaveRadFile.setCurrentDirectory(new File("./"));
      this.jFileChooserSaveRadFile.setDialogType(1);
      this.jFileChooserSaveRadFile.setDialogTitle(this.bundle.getString("exportRadOnFile"));
      this.jFileChooserSaveRadFile.setFileFilter(new FileNameExtensionFilter("rad files", "rad"));
      this.jFileChooserOpenRadFile.setCurrentDirectory(new File("./"));
      this.jFileChooserOpenRadFile.setDialogType(0);
      this.jFileChooserOpenRadFile.setDialogTitle(this.bundle.getString("openRadFile"));
      this.jFileChooserOpenRadFile.setFileFilter(new FileNameExtensionFilter("rad files", "rad"));
      this.jFileChooserSaveToCsv.setCurrentDirectory(new File("./"));
      this.jFileChooserSaveToCsv.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));
      this.jFileChooserSaveToCsv.setDialogType(1);
      this.jFileChooserSaveToCsv.setDialogTitle(this.bundle.getString("SaveCsvOnFile"));
      this.isNoMovCmdRequested = false;
      this.isRemoteToBeReadOnBoard = false;
      this.currentActiveCodeIndex = 0;
      this.lastActiveCodeIndex = 0;
      String tmp = this.bundle.getString("statusOffIco");
      this.iconOff = new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + tmp));
      this.statusIcoOff = new ImageIcon(this.iconOff.getImage());
      String tmpOn = this.bundle.getString("statusOnIco");
      this.iconOn = new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + tmpOn));
      this.statusIcoOn = new ImageIcon(this.iconOn.getImage());
      this.isProgRadioToBeReadFromBoard = false;
      this.isProgRadioToBeWrittenOnBoard = false;
      this.initPanelCurrentCode();
   }

   private void initPanelCurrentCode() {
      this.jLbPos.setText("");
      this.jLbCode.setText("");
      this.jLbChannel.setText("");
      this.jLbCoding.setText("");
      this.jChbEnabled.setEnabled(false);
      this.jChbEnabled.setSelected(false);
   }

   public void DisableBoardCommunication() {
      this.jBtReadFromBoard.setEnabled(false);
      this.jBtWriteOnBoard.setEnabled(false);
   }

   public void EnableBoardCommunication() {
      this.jBtReadFromBoard.setEnabled(true);
      this.jBtWriteOnBoard.setEnabled(true);
   }

   public void refreshPanelCurrentCode(int currentActiveCodeIndex) {
      this.currentActiveCodeIndex = currentActiveCodeIndex;
      if (currentActiveCodeIndex != this.lastActiveCodeIndex && currentActiveCodeIndex > 0) {
         FAAC_Remote.RadioCode[] codes = this.boardRemote.getRadioCodeArray();
         FAAC_Remote.RadioCode codeTmp = codes[currentActiveCodeIndex];
         this.jLbPos.setText(String.valueOf(currentActiveCodeIndex));
         String codeType = codeTmp.getCode2().getCodingType();
         this.jLbCode.setText(codeTmp.getCode1().getSerialCode(codeType));
         if (codeTmp.getCode1().get31_Channel_0ch1_1ch2()) {
            this.jLbChannel.setText("2");
         } else {
            this.jLbChannel.setText("1");
         }

         this.jLbCoding.setText(codeType);
         this.jChbEnabled.setEnabled(true);
         this.jChbEnabled.setSelected(!codeTmp.getCode2().get31_1disabled0enabled());
         this.jPanelCurrentCode.repaint();
         this.lastActiveCodeIndex = currentActiveCodeIndex;
      }

      if (currentActiveCodeIndex == 0) {
         this.jLbRemoteActive.setIcon(this.iconOff);
      } else {
         this.jLbRemoteActive.setIcon(this.iconOn);
      }
   }

   public void initRemoteTable() {
      this.remModel = new FAACRemote.RemoteTableModel();
      this.jTRemote.setName("jTRemote");
      this.jTRemote.setModel(this.remModel);
      this.upButton = new JButton(new FAACRemote.ArrowIcon(0));
      this.upButton.setEnabled(false);
      this.jBtPrevious.setEnabled(false);
      this.downButton = new JButton(new FAACRemote.ArrowIcon(1));
      this.upButton.setToolTipText(this.bundle.getString("pagePrevious"));
      this.downButton.setToolTipText(this.bundle.getString("pageNext"));
      if (this.remModel.getPageCount() <= 1) {
         this.downButton.setEnabled(false);
         this.jBtNext.setEnabled(false);
      }

      this.jLbPageNb.setText(String.valueOf(this.remModel.getPageOffset() + 1));
      this.jLbTotPage.setText(String.valueOf(this.remModel.getPageCount()));
      this.upButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
            FAACRemote.this.remModel.pageUp();
            FAACRemote.this.jLbPageNb.setText(String.valueOf(FAACRemote.this.remModel.getPageOffset() + 1));
            FAACRemote.this.jLbTotPage.setText(String.valueOf(FAACRemote.this.remModel.getPageCount()));
            if (FAACRemote.this.remModel.getPageOffset() == 0) {
               FAACRemote.this.upButton.setEnabled(false);
               FAACRemote.this.jBtPrevious.setEnabled(false);
            }

            FAACRemote.this.downButton.setEnabled(true);
            FAACRemote.this.jBtNext.setEnabled(true);
         }
      });
      this.downButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
            FAACRemote.this.remModel.pageDown();
            FAACRemote.this.jLbPageNb.setText(String.valueOf(FAACRemote.this.remModel.getPageOffset() + 1));
            FAACRemote.this.jLbTotPage.setText(String.valueOf(FAACRemote.this.remModel.getPageCount()));
            if (FAACRemote.this.remModel.getPageOffset() == FAACRemote.this.remModel.getPageCount() - 1) {
               FAACRemote.this.downButton.setEnabled(false);
               FAACRemote.this.jBtNext.setEnabled(false);
            }

            FAACRemote.this.upButton.setEnabled(true);
            FAACRemote.this.jBtPrevious.setEnabled(true);
         }
      });
      this.jScrollPaneRemote.setVerticalScrollBarPolicy(22);
      this.jScrollPaneRemote.setHorizontalScrollBarPolicy(32);
      this.jScrollPaneRemote.setCorner("UPPER_RIGHT_CORNER", this.upButton);
      this.jScrollPaneRemote.setCorner("LOWER_RIGHT_CORNER", this.downButton);
      JComboBox codeType = new JComboBox<>(new String[]{"SLH", "RC", "DS"});
      TableColumn codeColumn = this.jTRemote.getColumnModel().getColumn(4);
      codeColumn.setCellEditor(new DefaultCellEditor(codeType));
      JComboBox channel = new JComboBox<>(new String[]{"1", "2"});
      TableColumn channelColumn = this.jTRemote.getColumnModel().getColumn(3);
      channelColumn.setCellEditor(new DefaultCellEditor(channel));
      TableColumn col = this.jTRemote.getColumnModel().getColumn(3);
      TableColumn colCod = this.jTRemote.getColumnModel().getColumn(4);
      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
      dtcr.setHorizontalAlignment(0);
      col.setCellRenderer(dtcr);
      colCod.setCellRenderer(dtcr);
      this.jTRemote.getColumnModel().getColumn(3).setResizable(true);
      this.jTRemote.getColumnModel().getColumn(4).setResizable(true);
      this.jTRemote.getColumnModel().getColumn(0).setResizable(true);
      this.jTRemote.getColumnModel().getColumn(1).setResizable(true);
      this.jTRemote.getColumnModel().getColumn(5).setResizable(true);
      this.jTRemote.getColumnModel().removeColumn(this.jTRemote.getColumnModel().getColumn(1));
   }

   public void selectRadioCodeInTable(int iRadioCode) {
      int selectingRow = iRadioCode - 1;
      int iPage = selectingRow / 50;
      int iRowInPage = selectingRow % 50;
      this.remModel.setPageOffset(iPage);
      this.jTRemote.setRowSelectionInterval(iRowInPage, iRowInPage);
      this.jLbPageNb.setText(String.valueOf(this.remModel.getPageOffset() + 1));
      this.jLbTotPage.setText(String.valueOf(this.remModel.getPageCount()));
      if (this.remModel.getPageCount() >= 1) {
         if (this.remModel.getPageOffset() == 0) {
            this.upButton.setEnabled(false);
            this.jBtPrevious.setEnabled(false);
         } else {
            this.upButton.setEnabled(true);
            this.jBtPrevious.setEnabled(true);
         }

         if (this.remModel.getPageOffset() == this.remModel.getPageCount() - 1) {
            this.downButton.setEnabled(false);
            this.jBtNext.setEnabled(false);
         } else {
            this.downButton.setEnabled(true);
            this.jBtNext.setEnabled(true);
         }
      }
   }

   public void setRemoteInfo(
      FAAC_Remote boardRemote,
      FAAC_Monitor boardMonitor,
      FAAC_Settings boardSettings,
      boolean isBoardConnected,
      BackgroundWorkDialog backgroundWorkDialog,
      FAAC_Commands boardCommands,
      String boardModel
   ) {
      this.boardRemote = boardRemote;
      this.boardSettings = boardSettings;
      this.boardMonitor = boardMonitor;
      this.isBoardConnected = isBoardConnected;
      this.backgroundWorkDialog = backgroundWorkDialog;
      this.boardCommands = boardCommands;
      this.boardModel = boardModel;
      if (boardModel.compareToIgnoreCase("E145") == 0) {
         this.isRemoteCodeToBeReadOnBoard = new boolean[E145_Remote.getNbRadioCode()];
         this.isRemoteCodeToBeWrittenOnBoard = new boolean[E145_Remote.getNbRadioCode()];
      } else {
         this.isRemoteCodeToBeReadOnBoard = new boolean[E124_Remote.getNbRadioCode()];
         this.isRemoteCodeToBeWrittenOnBoard = new boolean[E124_Remote.getNbRadioCode()];
      }

      if (!this.isIsTableAlreadyInit()) {
         this.initRemoteTable();
         this.isTableAlreadyInit = true;
      }

      this.jRbListClosed.setSelected(boardRemote.getProgRadio().get00_ClosedList());
      this.jRbListOpen.setSelected(!boardRemote.getProgRadio().get00_ClosedList());
   }

   public void setRemoteInfoWithTableRefresh(
      FAAC_Remote boardRemote,
      FAAC_Monitor boardMonitor,
      FAAC_Settings boardSettings,
      boolean isBoardConnected,
      BackgroundWorkDialog backgroundWorkDialog,
      FAAC_Commands boardCommands,
      String boardModel
   ) {
      this.boardRemote = boardRemote;
      this.boardSettings = boardSettings;
      this.boardMonitor = boardMonitor;
      this.isBoardConnected = isBoardConnected;
      this.backgroundWorkDialog = backgroundWorkDialog;
      this.boardCommands = boardCommands;
      this.boardModel = boardModel;
      if (boardModel.compareToIgnoreCase("E145") == 0) {
         this.isRemoteCodeToBeReadOnBoard = new boolean[E145_Remote.getNbRadioCode()];
         this.isRemoteCodeToBeWrittenOnBoard = new boolean[E145_Remote.getNbRadioCode()];
      } else {
         this.isRemoteCodeToBeReadOnBoard = new boolean[E124_Remote.getNbRadioCode()];
         this.isRemoteCodeToBeWrittenOnBoard = new boolean[E124_Remote.getNbRadioCode()];
      }

      this.initRemoteTable();
      this.initRadioProgInfo();
   }

   public void initRadioProgInfo() {
      this.jRbListClosed.setSelected(this.boardRemote.getProgRadio().get00_ClosedList());
      this.jRbListOpen.setSelected(!this.boardRemote.getProgRadio().get00_ClosedList());
      this.jCbTypeDS.setSelected(!this.boardRemote.getProgRadio().get01_DisableDS());
      this.jCbTypeRC.setSelected(!this.boardRemote.getProgRadio().get02_DisableRC());
      this.jCbTypeSH.setSelected(!this.boardRemote.getProgRadio().get03_DisableSLH());
   }

   public void initPanelRemote() {
      this.jBtAcqCh1.setEnabled(this.isBoardConnected);
      this.jBtAcqCh2.setEnabled(this.isBoardConnected);
      int currentActive = 0;
      if (this.isBoardConnected) {
         currentActive = this.boardRemote.getCurrentActiveCodeIndex();
      }

      this.jLbNumCodes.setText(String.valueOf(this.boardRemote.getNbCodesInMemory()));
      FAAC_Remote.RadioCode[] radioCodes = this.boardRemote.getRadioCodeArray();
      int totalRow = this.remModel.getRealRowCount();

      for (int iRow = 0; iRow < totalRow; iRow++) {
         int iCode = iRow + 1;
         if (radioCodes[iCode] != null) {
            FAAC_Remote.RadioCode codeTmp = radioCodes[iCode];
            FAACRemote.Record record = new FAACRemote.Record();
            if (!this.boardRemote.isNotNullCode(iCode)) {
               record.setValueAt(null, 0);
               record.setValueAt(null, 1);
               record.setValueAt(null, 2);
               record.setValueAt(null, 2);
               record.setValueAt(null, 3);
               record.setValueAt(null, 4);
               record.setValueAt(null, 5);
            } else {
               record.setValueAt(codeTmp.getIndexRadioCode(), 0);
               if (codeTmp.getIndexRadioCode() == currentActive) {
                  record.setValueAt(this.statusIcoOn, 1);
               } else {
                  record.setValueAt(this.statusIcoOff, 1);
               }

               String codeType = codeTmp.getCode2().getCodingType();
               record.setValueAt(codeTmp.getCode1().getSerialCode(codeType), 2);
               if (codeTmp.getCode1().get31_Channel_0ch1_1ch2()) {
                  record.setValueAt(2, 3);
               } else {
                  record.setValueAt(1, 3);
               }

               record.setValueAt(codeTmp.getCode2().getCodingType(), 4);
               record.setValueAt(!codeTmp.getCode2().get31_1disabled0enabled(), 5);
            }

            this.remModel.setValueAt(record, iRow);
         }
      }

      this.jTRemote.revalidate();
      this.jTRemote.repaint();
   }

   public boolean getIsRemoteToBeReadOnBoard() {
      return this.isRemoteToBeReadOnBoard;
   }

   public void setIsRemoteToBeReadOnBoard(boolean isRemoteToBeReadOnBoard) {
      this.isRemoteToBeReadOnBoard = isRemoteToBeReadOnBoard;
   }

   public boolean[] getIsRemoteCodeToBeReadOnBoard() {
      return this.isRemoteCodeToBeReadOnBoard;
   }

   public void setIsRemoteCodeToBeReadOnBoard(boolean[] isRemoteCodeToBeReadOnBoard) {
      this.isRemoteCodeToBeReadOnBoard = isRemoteCodeToBeReadOnBoard;
   }

   public boolean[] getIsRemoteCodeToBeWrittenOnBoard() {
      return this.isRemoteCodeToBeWrittenOnBoard;
   }

   public void setIsRemoteCodeToBeWrittenOnBoard(boolean[] isRemoteCodeToBeWrittenOnBoard) {
      this.isRemoteCodeToBeWrittenOnBoard = isRemoteCodeToBeWrittenOnBoard;
   }

   public int getCurrentActiveCodeIndex() {
      return this.currentActiveCodeIndex;
   }

   public void setCurrentActiveCodeIndex(int currentActiveCodeIndex) {
      this.currentActiveCodeIndex = currentActiveCodeIndex;
      if (currentActiveCodeIndex != this.lastActiveCodeIndex) {
         if (this.lastActiveCodeIndex > 0) {
            this.remModel.setValueAt(this.statusIcoOff, this.lastActiveCodeIndex - 1, 1);
            this.remModel.fireTableRowsUpdated(this.lastActiveCodeIndex - 1, this.lastActiveCodeIndex - 1);
         }

         if (currentActiveCodeIndex > 0) {
            this.remModel.setValueAt(this.statusIcoOn, currentActiveCodeIndex - 1, 1);
            this.selectRadioCodeInTable(currentActiveCodeIndex);
            this.jLbRemoteActive.setIcon(this.statusIcoOn);
         } else {
            this.jLbRemoteActive.setIcon(this.statusIcoOff);
         }

         this.lastActiveCodeIndex = currentActiveCodeIndex;
      }
   }

   public int getNbCodesMem() {
      return this.nbCodesMem;
   }

   public void setNbCodesMem(int nbCodesMem) {
      this.nbCodesMem = nbCodesMem;
      this.jLbNumCodes.setText(String.valueOf(nbCodesMem));
   }

   public boolean isIsModRemote() {
      return this.isModRemote;
   }

   public void setIsModRemote(boolean isModRemote) {
      this.isModRemote = isModRemote;
   }

   public boolean isIsProgRadioToBeReadFromBoard() {
      return this.isProgRadioToBeReadFromBoard;
   }

   public void setIsProgRadioToBeReadFromBoard(boolean isProgRadioToBeReadFromBoard) {
      this.isProgRadioToBeReadFromBoard = isProgRadioToBeReadFromBoard;
   }

   public boolean isIsProgRadioToBeWrittenOnBoard() {
      return this.isProgRadioToBeWrittenOnBoard;
   }

   public void setIsProgRadioToBeWrittenOnBoard(boolean isProgRadioToBeWrittenOnBoard) {
      this.isProgRadioToBeWrittenOnBoard = isProgRadioToBeWrittenOnBoard;
   }

   public boolean isIsTableAlreadyInit() {
      return this.isTableAlreadyInit;
   }

   public void setIsTableAlreadyInit(boolean isTableAlreadyInit) {
      this.isTableAlreadyInit = isTableAlreadyInit;
   }

   private void initComponents() {
      this.jFileChooserSaveRadFile = new JFileChooser();
      this.jFileChooserOpenRadFile = new JFileChooser();
      this.jFileChooserSaveToCsv = new JFileChooser();
      this.btnGrpOpenClosedList = new ButtonGroup();
      this.jScrollPaneRemote = new JScrollPane();
      this.jTRemote = new JTable();
      this.jLabel1 = new JLabel();
      this.jLbNumCodes = new JLabel();
      this.jLabel2 = new JLabel();
      this.jLbPageNb = new JLabel();
      this.jLabel4 = new JLabel();
      this.jLbTotPage = new JLabel();
      this.jBtPrevious = new JButton();
      this.jBtNext = new JButton();
      this.jLayeredPane1 = new JLayeredPane();
      this.jPanel1 = new JPanel();
      this.jBtAcqCh1 = new JButton();
      this.jBtAcqCh2 = new JButton();
      this.jPanelWrite = new JPanel();
      this.jBtWriteOnBoard = new JButton();
      this.jBtExportRadOnFile = new JButton();
      this.jPanelRead = new JPanel();
      this.jBtReadFromBoard = new JButton();
      this.jBtImportRadFromFile = new JButton();
      this.jPanelEdit = new JPanel();
      this.jBtDeleteSelRemote = new JButton();
      this.jBtDeleteAllRemote = new JButton();
      this.jLBModRemote = new JLabel();
      this.jPanel3 = new JPanel();
      this.jRbListOpen = new JRadioButton();
      this.jRbListClosed = new JRadioButton();
      this.jBtSaveToCsvFile = new JButton();
      this.jPanelTypeEnable = new JPanel();
      this.jCbTypeDS = new JCheckBox();
      this.jCbTypeRC = new JCheckBox();
      this.jCbTypeSH = new JCheckBox();
      this.jPanelCurrentCode = new JPanel();
      this.jLbRemoteActive = new JLabel();
      this.jLbPosLabel = new JLabel();
      this.jLbPos = new JLabel();
      this.jLbPosCodeLabel = new JLabel();
      this.jLbCode = new JLabel();
      this.jLbChannelLabel = new JLabel();
      this.jLbChannel = new JLabel();
      this.jChbEnabled = new JCheckBox();
      this.jLbCodingLabel = new JLabel();
      this.jLbCoding = new JLabel();
      this.jFileChooserSaveRadFile.setName("jFileChooserSaveRadFile");
      this.jFileChooserOpenRadFile.setName("jFileChooserOpenRadFile");
      this.jFileChooserSaveToCsv.setName("jFileChooserSaveToCsv");
      this.setName("FAACRemote");
      this.setPreferredSize(new Dimension(755, 530));
      this.setRequestFocusEnabled(false);
      this.jScrollPaneRemote.setMinimumSize(new Dimension(0, 0));
      this.jScrollPaneRemote.setName("jScrollPaneRemote");
      this.jScrollPaneRemote.setPreferredSize(new Dimension(556, 523));
      this.jScrollPaneRemote.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent evt) {
            FAACRemote.this.jScrollPaneRemoteComponentResized(evt);
         }
      });
      this.jTRemote
         .setModel(
            new DefaultTableModel(
               new Object[][]{
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null},
                  {null, null, null, null, null, null}
               },
               new String[]{"Posizione", "Stato", "Codice radio", "Canale", "Tipo di codifica", "Abilitato"}
            ) {
               Class[] types = new Class[]{Object.class, Object.class, Object.class, Integer.class, Object.class, Boolean.class};
               boolean[] canEdit = new boolean[]{false, false, false, false, false, true};

               @Override
               public Class getColumnClass(int columnIndex) {
                  return this.types[columnIndex];
               }

               @Override
               public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return this.canEdit[columnIndex];
               }
            }
         );
      this.jTRemote.setFillsViewportHeight(true);
      this.jTRemote.setGridColor(new Color(153, 153, 153));
      this.jTRemote.setMaximumSize(new Dimension(32767, 32767));
      this.jTRemote.setName("jTRemote");
      this.jTRemote.setPreferredSize(new Dimension(215, 1000));
      this.jTRemote.setRowHeight(20);
      this.jTRemote.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jTRemoteMousePressed(evt);
         }
      });
      this.jTRemote.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent evt) {
            FAACRemote.this.jTRemoteKeyPressed(evt);
         }
      });
      this.jScrollPaneRemote.setViewportView(this.jTRemote);
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACRemote");
      if (this.jTRemote.getColumnModel().getColumnCount() > 0) {
         this.jTRemote.getColumnModel().getColumn(0).setMinWidth(60);
         this.jTRemote.getColumnModel().getColumn(0).setPreferredWidth(60);
         this.jTRemote.getColumnModel().getColumn(0).setMaxWidth(60);
         this.jTRemote.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("FAACRemote.jTRemote.columnModel.title5"));
         this.jTRemote.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("FAACRemote.jTRemote.columnModel.title4"));
         this.jTRemote.getColumnModel().getColumn(2).setPreferredWidth(120);
         this.jTRemote.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("FAACRemote.jTRemote.columnModel.title0"));
         this.jTRemote.getColumnModel().getColumn(3).setMinWidth(60);
         this.jTRemote.getColumnModel().getColumn(3).setPreferredWidth(60);
         this.jTRemote.getColumnModel().getColumn(3).setMaxWidth(60);
         this.jTRemote.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("FAACRemote.jTRemote.columnModel.title1"));
         this.jTRemote.getColumnModel().getColumn(4).setMinWidth(120);
         this.jTRemote.getColumnModel().getColumn(4).setPreferredWidth(120);
         this.jTRemote.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("FAACRemote.jTRemote.columnModel.title2"));
         this.jTRemote.getColumnModel().getColumn(5).setMinWidth(60);
         this.jTRemote.getColumnModel().getColumn(5).setPreferredWidth(60);
         this.jTRemote.getColumnModel().getColumn(5).setMaxWidth(60);
         this.jTRemote.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("FAACRemote.jTRemote.columnModel.title4_1"));
      }

      this.jTRemote.getAccessibleContext().setAccessibleName(bundle.getString("FAACRemote.jTRemote.AccessibleContext.accessibleName"));
      this.jLabel1.setHorizontalAlignment(4);
      this.jLabel1.setText(bundle.getString("FAACRemote.jLabel1.text"));
      this.jLabel1.setName("jLabel1");
      this.jLbNumCodes.setText(bundle.getString("FAACRemote.jLbNumCodes.text"));
      this.jLbNumCodes.setName("jLbNumCodes");
      this.jLabel2.setText(bundle.getString("FAACRemote.jLabel2.text"));
      this.jLabel2.setName("jLabel2");
      this.jLbPageNb.setText(bundle.getString("FAACRemote.jLbPageNb.text"));
      this.jLbPageNb.setName("jLbPageNb");
      this.jLabel4.setText(bundle.getString("FAACRemote.jLabel4.text"));
      this.jLabel4.setName("jLabel4");
      this.jLbTotPage.setText(bundle.getString("FAACRemote.jLbTotPage.text"));
      this.jLbTotPage.setName("jLbTotPage");
      this.jBtPrevious.setText(bundle.getString("FAACRemote.jBtPrevious.text"));
      this.jBtPrevious.setName("jBtPrevious");
      this.jBtPrevious.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtPreviousMousePressed(evt);
         }
      });
      this.jBtNext.setText(bundle.getString("FAACRemote.jBtNext.text"));
      this.jBtNext.setName("jBtNext");
      this.jBtNext.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtNextMousePressed(evt);
         }
      });
      this.jLayeredPane1.setName("jLayeredPane1");
      this.jPanel1.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACRemote.jPanel1.border.title")));
      this.jPanel1.setName("jPanel1");
      this.jBtAcqCh1.setText(bundle.getString("FAACRemote.jBtAcqCh1.text"));
      this.jBtAcqCh1.setToolTipText(bundle.getString("FAACRemote.jBtAcqCh1.toolTipText"));
      this.jBtAcqCh1.setName("jBtAcqCh1");
      this.jBtAcqCh1.setPreferredSize(new Dimension(180, 31));
      this.jBtAcqCh1.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtAcqCh1MousePressed(evt);
         }
      });
      this.jBtAcqCh2.setText(bundle.getString("FAACRemote.jBtAcqCh2.text"));
      this.jBtAcqCh2.setToolTipText(bundle.getString("FAACRemote.jBtAcqCh2.toolTipText"));
      this.jBtAcqCh2.setName("jBtAcqCh2");
      this.jBtAcqCh2.setPreferredSize(new Dimension(180, 23));
      this.jBtAcqCh2.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtAcqCh2MousePressed(evt);
         }
      });
      GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
      this.jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jBtAcqCh1, -2, 180, -2).addGap(4, 4, 4))
            .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addComponent(this.jBtAcqCh2, -2, 180, -2).addContainerGap())
      );
      jPanel1Layout.linkSize(0, this.jBtAcqCh1, this.jBtAcqCh2);
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel1Layout.createSequentialGroup()
                  .addComponent(this.jBtAcqCh1, -2, 25, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtAcqCh2, -2, 25, -2)
                  .addGap(28, 28, 28)
            )
      );
      this.jLayeredPane1.add(this.jPanel1);
      this.jPanel1.setBounds(0, 340, 196, 80);
      this.jPanelWrite.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACRemote.jPanelWrite.border.title")));
      this.jPanelWrite.setName("jPanelWrite");
      this.jBtWriteOnBoard.setText(bundle.getString("FAACRemote.jBtWriteOnBoard.text"));
      this.jBtWriteOnBoard.setName("jBtWriteOnBoard");
      this.jBtWriteOnBoard.setPreferredSize(new Dimension(180, 23));
      this.jBtWriteOnBoard.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtWriteOnBoardMousePressed(evt);
         }
      });
      this.jBtExportRadOnFile.setText(bundle.getString("FAACRemote.jBtExportRadOnFile.text"));
      this.jBtExportRadOnFile.setName("jBtExportRadOnFile");
      this.jBtExportRadOnFile.setPreferredSize(new Dimension(180, 23));
      this.jBtExportRadOnFile.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtExportRadOnFileMousePressed(evt);
         }
      });
      GroupLayout jPanelWriteLayout = new GroupLayout(this.jPanelWrite);
      this.jPanelWrite.setLayout(jPanelWriteLayout);
      jPanelWriteLayout.setHorizontalGroup(
         jPanelWriteLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelWriteLayout.createSequentialGroup().addComponent(this.jBtExportRadOnFile, -2, 180, -2).addGap(4, 4, 4))
            .addGroup(Alignment.TRAILING, jPanelWriteLayout.createSequentialGroup().addComponent(this.jBtWriteOnBoard, -2, 180, -2).addContainerGap())
      );
      jPanelWriteLayout.linkSize(0, this.jBtExportRadOnFile, this.jBtWriteOnBoard);
      jPanelWriteLayout.setVerticalGroup(
         jPanelWriteLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelWriteLayout.createSequentialGroup()
                  .addComponent(this.jBtExportRadOnFile, -2, 25, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtWriteOnBoard, -2, 25, -2)
                  .addContainerGap()
            )
      );
      this.jLayeredPane1.add(this.jPanelWrite);
      this.jPanelWrite.setBounds(0, 260, 196, 80);
      this.jPanelRead.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACRemote.jPanelRead.border.title")));
      this.jPanelRead.setName("jPanelRead");
      this.jBtReadFromBoard.setText(bundle.getString("FAACRemote.jBtReadFromBoard.text"));
      this.jBtReadFromBoard.setName("jBtReadFromBoard");
      this.jBtReadFromBoard.setPreferredSize(new Dimension(180, 23));
      this.jBtReadFromBoard.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtReadFromBoardMousePressed(evt);
         }
      });
      this.jBtImportRadFromFile.setText(bundle.getString("FAACRemote.jBtImportRadFromFile.text"));
      this.jBtImportRadFromFile.setName("jBtImportRadFromFile");
      this.jBtImportRadFromFile.setPreferredSize(new Dimension(180, 23));
      this.jBtImportRadFromFile.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtImportRadFromFileMousePressed(evt);
         }
      });
      GroupLayout jPanelReadLayout = new GroupLayout(this.jPanelRead);
      this.jPanelRead.setLayout(jPanelReadLayout);
      jPanelReadLayout.setHorizontalGroup(
         jPanelReadLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelReadLayout.createSequentialGroup()
                  .addGroup(
                     jPanelReadLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jBtReadFromBoard, -2, 180, -2)
                        .addComponent(this.jBtImportRadFromFile, -2, 180, -2)
                  )
                  .addGap(12, 12, 12)
            )
      );
      jPanelReadLayout.linkSize(0, this.jBtImportRadFromFile, this.jBtReadFromBoard);
      jPanelReadLayout.setVerticalGroup(
         jPanelReadLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelReadLayout.createSequentialGroup()
                  .addComponent(this.jBtImportRadFromFile, -2, 25, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtReadFromBoard, -2, 25, -2)
                  .addContainerGap()
            )
      );
      this.jLayeredPane1.add(this.jPanelRead);
      this.jPanelRead.setBounds(0, 180, 196, 80);
      this.jPanelEdit.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACRemote.jPanelEdit.border.title")));
      this.jPanelEdit.setName("jPanelEdit");
      this.jPanelEdit.setPreferredSize(new Dimension(158, 122));
      this.jBtDeleteSelRemote.setText(bundle.getString("FAACRemote.jBtDeleteSelRemote.text"));
      this.jBtDeleteSelRemote.setName("jBtDeleteSelRemote");
      this.jBtDeleteSelRemote.setPreferredSize(new Dimension(180, 25));
      this.jBtDeleteSelRemote.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtDeleteSelRemoteMousePressed(evt);
         }
      });
      this.jBtDeleteAllRemote.setText(bundle.getString("FAACRemote.jBtDeleteAllRemote.text"));
      this.jBtDeleteAllRemote.setName("jBtDeleteAllRemote");
      this.jBtDeleteAllRemote.setPreferredSize(new Dimension(180, 25));
      this.jBtDeleteAllRemote.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtDeleteAllRemoteMousePressed(evt);
         }
      });
      GroupLayout jPanelEditLayout = new GroupLayout(this.jPanelEdit);
      this.jPanelEdit.setLayout(jPanelEditLayout);
      jPanelEditLayout.setHorizontalGroup(
         jPanelEditLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(this.jBtDeleteAllRemote, -2, -1, -2)
            .addComponent(this.jBtDeleteSelRemote, -2, 180, -2)
      );
      jPanelEditLayout.linkSize(0, this.jBtDeleteAllRemote, this.jBtDeleteSelRemote);
      jPanelEditLayout.setVerticalGroup(
         jPanelEditLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPanelEditLayout.createSequentialGroup()
                  .addComponent(this.jBtDeleteSelRemote, -2, 25, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtDeleteAllRemote, -2, 25, -2)
                  .addGap(6, 6, 6)
            )
      );
      this.jLayeredPane1.add(this.jPanelEdit);
      this.jPanelEdit.setBounds(0, 102, 196, 80);
      this.jLBModRemote.setFont(new Font("Tahoma", 1, 11));
      this.jLBModRemote.setForeground(new Color(255, 51, 0));
      this.jLBModRemote.setText(bundle.getString("FAACRemote.jLBModRemote.text"));
      this.jLBModRemote.setName("jLBModRemote");
      this.jLayeredPane1.add(this.jLBModRemote);
      this.jLBModRemote.setBounds(20, 490, 160, 40);
      this.jPanel3.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACRemote.jPanel3.border.title")));
      this.jPanel3.setEnabled(false);
      this.jPanel3.setName("jPanel3");
      this.btnGrpOpenClosedList.add(this.jRbListOpen);
      this.jRbListOpen.setSelected(true);
      this.jRbListOpen.setText(bundle.getString("FAACRemote.jRbListOpen.text"));
      this.jRbListOpen.setEnabled(false);
      this.jRbListOpen.setName("jRbListOpen");
      this.jRbListOpen.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent evt) {
            FAACRemote.this.jRbListOpenMouseClicked(evt);
         }
      });
      this.btnGrpOpenClosedList.add(this.jRbListClosed);
      this.jRbListClosed.setText(bundle.getString("FAACRemote.jRbListClosed.text"));
      this.jRbListClosed.setEnabled(false);
      this.jRbListClosed.setName("jRbListClosed");
      this.jRbListClosed.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent evt) {
            FAACRemote.this.jRbListClosedMouseClicked(evt);
         }
      });
      GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
      this.jPanel3.setLayout(jPanel3Layout);
      jPanel3Layout.setHorizontalGroup(
         jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel3Layout.createSequentialGroup()
                  .addComponent(this.jRbListOpen)
                  .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                  .addComponent(this.jRbListClosed)
                  .addContainerGap()
            )
      );
      jPanel3Layout.setVerticalGroup(
         jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel3Layout.createSequentialGroup()
                  .addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jRbListOpen).addComponent(this.jRbListClosed))
                  .addContainerGap(-1, 32767)
            )
      );
      this.jLayeredPane1.add(this.jPanel3);
      this.jPanel3.setBounds(0, 0, 196, 50);
      this.jBtSaveToCsvFile.setText(bundle.getString("FAACRemote.jBtSaveToCsvFile.text"));
      this.jBtSaveToCsvFile.setName("jBtSaveToCsvFile");
      this.jBtSaveToCsvFile.setPreferredSize(new Dimension(180, 23));
      this.jBtSaveToCsvFile.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jBtSaveToCsvFileMousePressed(evt);
         }
      });
      this.jLayeredPane1.add(this.jBtSaveToCsvFile);
      this.jBtSaveToCsvFile.setBounds(10, 460, 180, 25);
      this.jPanelTypeEnable.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACRemote.jPanelTypeEnable.border.title")));
      this.jPanelTypeEnable.setName("jPanelTypeEnable");
      this.jCbTypeDS.setText(bundle.getString("FAACRemote.jCbTypeDS.text"));
      this.jCbTypeDS.setToolTipText(bundle.getString("FAACRemote.jCbTypeDS.toolTipText"));
      this.jCbTypeDS.setName("jCbTypeDS");
      this.jCbTypeDS.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jCbTypeDSMousePressed(evt);
         }
      });
      this.jCbTypeRC.setText(bundle.getString("FAACRemote.jCbTypeRC.text"));
      this.jCbTypeRC.setToolTipText(bundle.getString("FAACRemote.jCbTypeRC.toolTipText"));
      this.jCbTypeRC.setName("jCbTypeRC");
      this.jCbTypeRC.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jCbTypeRCMousePressed(evt);
         }
      });
      this.jCbTypeSH.setText(bundle.getString("FAACRemote.jCbTypeSH.text"));
      this.jCbTypeSH.setToolTipText(bundle.getString("FAACRemote.jCbTypeSH.toolTipText"));
      this.jCbTypeSH.setName("jCbTypeSH");
      this.jCbTypeSH.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jCbTypeSHMousePressed(evt);
         }
      });
      GroupLayout jPanelTypeEnableLayout = new GroupLayout(this.jPanelTypeEnable);
      this.jPanelTypeEnable.setLayout(jPanelTypeEnableLayout);
      jPanelTypeEnableLayout.setHorizontalGroup(
         jPanelTypeEnableLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelTypeEnableLayout.createSequentialGroup()
                  .addComponent(this.jCbTypeDS)
                  .addGap(18, 18, 18)
                  .addComponent(this.jCbTypeRC)
                  .addGap(18, 18, 18)
                  .addComponent(this.jCbTypeSH)
                  .addContainerGap(-1, 32767)
            )
      );
      jPanelTypeEnableLayout.setVerticalGroup(
         jPanelTypeEnableLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelTypeEnableLayout.createSequentialGroup()
                  .addGroup(
                     jPanelTypeEnableLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.jCbTypeDS)
                        .addComponent(this.jCbTypeRC)
                        .addComponent(this.jCbTypeSH)
                  )
                  .addContainerGap(-1, 32767)
            )
      );
      this.jLayeredPane1.add(this.jPanelTypeEnable);
      this.jPanelTypeEnable.setBounds(0, 50, 196, 50);
      this.jPanelCurrentCode.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACRemote.jPanelCurrentCode.border.title")));
      this.jPanelCurrentCode.setName("jPanelCurrentCode");
      this.jLbRemoteActive.setIcon(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/statusOff.png")));
      this.jLbRemoteActive.setText(bundle.getString("FAACRemote.jLbRemoteActive.text"));
      this.jLbRemoteActive.setToolTipText(bundle.getString("FAACRemote.jLbRemoteActive.toolTipText"));
      this.jLbRemoteActive.setHorizontalTextPosition(0);
      this.jLbRemoteActive.setName("jLbRemoteActive");
      this.jLbRemoteActive.setVerticalTextPosition(1);
      this.jLbPosLabel.setText(bundle.getString("FAACRemote.jLbPosLabel.text"));
      this.jLbPosLabel.setName("jLbPosLabel");
      this.jLbPos.setText(bundle.getString("FAACRemote.jLbPos.text"));
      this.jLbPos.setName("jLbPos");
      this.jLbPosCodeLabel.setText(bundle.getString("FAACRemote.jLbPosCodeLabel.text"));
      this.jLbPosCodeLabel.setName("jLbPosCodeLabel");
      this.jLbCode.setText(bundle.getString("FAACRemote.jLbCode.text"));
      this.jLbCode.setName("jLbCode");
      this.jLbChannelLabel.setText(bundle.getString("FAACRemote.jLbChannelLabel.text"));
      this.jLbChannelLabel.setName("jLbChannelLabel");
      this.jLbChannel.setText(bundle.getString("FAACRemote.jLbChannel.text"));
      this.jLbChannel.setName("jLbChannel");
      this.jChbEnabled.setText(bundle.getString("FAACRemote.jChbEnabled.text"));
      this.jChbEnabled.setEnabled(false);
      this.jChbEnabled.setName("jChbEnabled");
      this.jChbEnabled.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACRemote.this.jChbEnabledMousePressed(evt);
         }
      });
      this.jChbEnabled.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            FAACRemote.this.jChbEnabledStateChanged(evt);
         }
      });
      this.jLbCodingLabel.setText(bundle.getString("FAACRemote.jLbCodingLabel.text"));
      this.jLbCodingLabel.setName("jLbCodingLabel");
      this.jLbCoding.setText(bundle.getString("FAACRemote.jLbCoding.text"));
      this.jLbCoding.setName("jLbCoding");
      GroupLayout jPanelCurrentCodeLayout = new GroupLayout(this.jPanelCurrentCode);
      this.jPanelCurrentCode.setLayout(jPanelCurrentCodeLayout);
      jPanelCurrentCodeLayout.setHorizontalGroup(
         jPanelCurrentCodeLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelCurrentCodeLayout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(this.jLbRemoteActive)
                  .addGap(7, 7, 7)
                  .addComponent(this.jLbPosLabel)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jLbPos)
                  .addGap(10, 10, 10)
                  .addComponent(this.jLbChannelLabel)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jLbChannel, -2, 15, -2)
                  .addGap(10, 10, 10)
                  .addComponent(this.jLbPosCodeLabel)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jLbCode, -1, -1, 32767)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jLbCodingLabel)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jLbCoding, -2, 43, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jChbEnabled, -2, 73, -2)
                  .addContainerGap()
            )
      );
      jPanelCurrentCodeLayout.setVerticalGroup(
         jPanelCurrentCodeLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelCurrentCodeLayout.createSequentialGroup()
                  .addGroup(
                     jPanelCurrentCodeLayout.createParallelGroup(Alignment.CENTER)
                        .addComponent(this.jLbRemoteActive, -1, -1, 32767)
                        .addComponent(this.jLbPosLabel)
                        .addComponent(this.jLbPos)
                        .addComponent(this.jLbChannelLabel)
                        .addComponent(this.jLbChannel)
                        .addComponent(this.jLbPosCodeLabel)
                        .addComponent(this.jLbCode)
                        .addComponent(this.jLbCodingLabel)
                        .addComponent(this.jLbCoding)
                        .addComponent(this.jChbEnabled)
                  )
                  .addGap(8, 8, 8)
            )
      );
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                           Alignment.TRAILING,
                           layout.createSequentialGroup()
                              .addGap(23, 23, 23)
                              .addComponent(this.jLabel2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbPageNb)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLabel4)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbTotPage)
                              .addPreferredGap(ComponentPlacement.UNRELATED)
                              .addComponent(this.jBtPrevious)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jBtNext)
                              .addGap(18, 18, 18)
                              .addComponent(this.jLabel1, -1, -1, 32767)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbNumCodes, -2, 32, -2)
                        )
                        .addGroup(
                           Alignment.TRAILING,
                           layout.createSequentialGroup()
                              .addGroup(
                                 layout.createParallelGroup(Alignment.TRAILING)
                                    .addComponent(this.jPanelCurrentCode, Alignment.LEADING, -1, -1, 32767)
                                    .addComponent(this.jScrollPaneRemote, -1, 0, 32767)
                              )
                              .addPreferredGap(ComponentPlacement.UNRELATED)
                        )
                  )
                  .addComponent(this.jLayeredPane1, -2, 198, -2)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGap(0, 0, 0)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jLayeredPane1, -1, 530, 32767)
                        .addGroup(
                           Alignment.TRAILING,
                           layout.createSequentialGroup()
                              .addComponent(this.jPanelCurrentCode, -2, 48, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jScrollPaneRemote, -1, 429, 32767)
                              .addPreferredGap(ComponentPlacement.UNRELATED)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.BASELINE)
                                    .addComponent(this.jLabel2)
                                    .addComponent(this.jLbPageNb)
                                    .addComponent(this.jLabel4)
                                    .addComponent(this.jLbTotPage)
                                    .addComponent(this.jLabel1)
                                    .addComponent(this.jLbNumCodes, -2, 14, -2)
                                    .addComponent(this.jBtPrevious)
                                    .addComponent(this.jBtNext)
                              )
                              .addGap(13, 13, 13)
                        )
                  )
            )
      );
   }

   private void prepareBackgroundWorkRadReading() {
      int maxValueProgressBar = FAAC_Remote.getNbRadioCode() + 1;
      this.backgroundWorkDialog.setMaximumLevel(maxValueProgressBar);
      this.backgroundWorkDialog.setLocationRelativeTo(this);
      this.backgroundWorkDialog.setTitle(this.bundle.getString("readRadFromBoard"));
      this.backgroundWorkDialog.setMsg(this.bundle.getString("readRadFromBoardOngoing"));
      this.backgroundWorkDialog.reset();
      this.backgroundWorkDialog.setValueLevel(1);
      this.backgroundWorkDialog.setOperationOngoing(true);
   }

   public void readRadioFromBoardMethod() {
      this.prepareBackgroundWorkRadReading();

      for (int i = 0; i < FAAC_Remote.getNbRadioCode(); i++) {
         this.getIsRemoteCodeToBeReadOnBoard()[i] = true;
      }

      this.radioReadFlag = true;
      this.isProgRadioToBeReadFromBoard = true;
      this.backgroundWorkDialog.setVisible(true);
      this.radioReadFlag = false;
   }

   public boolean areRadioToBeRead() {
      return this.radioReadFlag;
   }

   public void setRadioToBeRead(boolean flag) {
      this.radioReadFlag = flag;
   }

   public boolean areRadioToBeWritten() {
      return this.radioWriteFlag;
   }

   public void setRadioToBeWritten(boolean flag) {
      this.radioWriteFlag = flag;
   }

   private void notifyToActionListeners(Object[] listeners) {
      int numListeners = listeners.length;

      for (int i = 0; i < numListeners; i += 2) {
         if (listeners[i] == ActionListener.class) {
            ((ActionListener)listeners[i + 1]).actionPerformed(null);
         }
      }
   }

   private void prepareBackgroundWorkRadWriting(int maxValueProgressBar) {
      this.backgroundWorkDialog.setMaximumLevel(maxValueProgressBar);
      this.backgroundWorkDialog.setLocationRelativeTo(this);
      this.backgroundWorkDialog.setTitle(this.bundle.getString("writeRadOnBoard"));
      this.backgroundWorkDialog.setMsg(this.bundle.getString("writeRadOnBoardOngoing"));
      this.backgroundWorkDialog.reset();
      this.backgroundWorkDialog.setOperationOngoing(true);
   }

   public void writeRadioOnBoardMethod() {
      FAAC_Remote.RadioCode[] radioCodes = this.boardRemote.getRadioCodeArray();
      int counter = 0;

      for (int i = 0; i < FAAC_Remote.getNbRadioCode(); i++) {
         if (radioCodes[i].isIsModified()) {
            counter++;
         }
      }

      if (counter <= 0 && !this.isSomeCodeReadFromFile) {
         this.prepareBackgroundWorkRadWriting(1);
      } else {
         this.prepareBackgroundWorkRadWriting(counter + 1);
      }

      for (int ix = 0; ix < FAAC_Remote.getNbRadioCode(); ix++) {
         if (radioCodes[ix].isIsModified()) {
            this.getIsRemoteCodeToBeWrittenOnBoard()[ix] = true;
         } else {
            this.getIsRemoteCodeToBeWrittenOnBoard()[ix] = false;
         }
      }

      this.isProgRadioToBeWrittenOnBoard = true;
      this.radioWriteFlag = true;
      this.backgroundWorkDialog.setVisible(true);
      this.radioWriteFlag = false;
   }

   private void jScrollPaneRemoteComponentResized(ComponentEvent evt) {
   }

   private void deleteSelectedRadio() {
      int[] selectedRows = this.jTRemote.getSelectedRows();
      if (selectedRows != null && selectedRows.length != 0) {
         int reply = JOptionPane.showConfirmDialog(this, this.bundle.getString("delSelectedWarningMsg"), this.bundle.getString("Attention"), 0);
         if (reply == 0) {
            for (int iRow = 0; iRow < selectedRows.length; iRow++) {
               int codeIndexToBeDeleted = selectedRows[iRow] + 1;
               FAAC_Remote.RadioCode codeToBeDeleted = this.boardRemote.getRadioCodeArray()[codeIndexToBeDeleted];
               if (codeToBeDeleted != null) {
                  if (this.boardRemote.isNotNullCode(codeIndexToBeDeleted)) {
                     codeToBeDeleted.delete();
                  }

                  this.isSomeCodeDeleted = true;
               }
            }

            this.jLBModRemote.setVisible(true);
            this.isModRemote = true;
            this.initPanelRemote();
            this.jTRemote.clearSelection();
         }
      } else {
         JOptionPane.showMessageDialog(this, this.bundle.getString("noCodeSelected_message"), this.bundle.getString("Attention"), 2);
      }
   }

   private void deleteAll() {
      int reply = JOptionPane.showConfirmDialog(this, this.bundle.getString("delAllWarningMsg"), this.bundle.getString("Attention"), 0);
      if (reply == 0) {
         for (int iRow = 0; iRow < FAAC_Remote.getNbRadioCode() - 1; iRow++) {
            int codeIndexToBeDeleted = iRow + 1;
            FAAC_Remote.RadioCode codeToBeDeleted = this.boardRemote.getRadioCodeArray()[codeIndexToBeDeleted];
            if (codeToBeDeleted != null) {
               if (this.boardRemote.isNotNullCode(codeIndexToBeDeleted)) {
                  codeToBeDeleted.deleteLocal();
               }

               this.isSomeCodeDeleted = true;
            }
         }

         this.boardCommands.getNoMovCmdArg().set07_zeroMemRadio(true);
         this.initPanelRemote();
         this.jTRemote.clearSelection();
      }
   }

   private void generateRadFile(File fileName, String file_password) {
      byte[] fileBytes = FAAC_frames.getBytesForRadFile(file_password, this.boardRemote, this.boardMonitor);

      try {
         FileOutputStream fos = new FileOutputStream(fileName);
         fos.write(fileBytes);
         fos.close();
         JOptionPane.showMessageDialog(this, this.bundle.getString("createRadFileOkMsg"), this.bundle.getString("Information"), 1);
         this.jLBModRemote.setVisible(false);
         this.isSomeCodeDeleted = false;
         this.isModRemote = false;
      } catch (IOException var6) {
         Logger.getLogger(FAACTimer.class.getName()).log(Level.SEVERE, null, var6);
      }
   }

   public void exportRadioOnFile() {
      PasswordInsertDialogFile pswFile = new PasswordInsertDialogFile(null, true);
      String file_password = pswFile.getPassword(this.boardSettings.getPassword(), this.isBoardConnected);
      if (file_password != "") {
         String suggestedName = FAAC_frames.FILENAME_RAD_E145_noExt;
         if (this.boardModel.compareToIgnoreCase("E124") == 0) {
            suggestedName = FAAC_frames.FILENAME_RAD_E124_noExt;
         }

         this.jFileChooserSaveRadFile.setSelectedFile(new File(suggestedName));
         JFrame fr = new JFrame();
         fr.setIconImage(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + this.bundle.getString("saveIcon"))).getImage());
         int actionDialog = this.jFileChooserSaveRadFile.showSaveDialog(fr);
         if (actionDialog == 0) {
            String relativeSelectedFilename = this.jFileChooserSaveRadFile.getSelectedFile().getName();
            if (this.boardModel.compareToIgnoreCase("E145") == 0
               && relativeSelectedFilename.compareToIgnoreCase(FAAC_frames.FILENAME_RAD_E145_wExt) != 0
               && relativeSelectedFilename.compareToIgnoreCase(FAAC_frames.FILENAME_RAD_E145_noExt) != 0) {
               JOptionPane.showMessageDialog(this, this.bundle.getString("changeSuggestedFilename_message"), this.bundle.getString("Attention"), 2);
            }

            File fileName;
            if (relativeSelectedFilename.contains("." + FAAC_frames.FILE_EXT_RAD)) {
               fileName = new File(this.jFileChooserSaveRadFile.getSelectedFile().getPath());
            } else {
               fileName = new File(this.jFileChooserSaveRadFile.getSelectedFile() + "." + FAAC_frames.FILE_EXT_RAD);
            }

            if (fileName != null) {
               if (fileName.exists()) {
                  actionDialog = JOptionPane.showConfirmDialog(this, this.bundle.getString("replaceFileMsg"));

                  while (actionDialog == 1) {
                     actionDialog = this.jFileChooserSaveRadFile.showSaveDialog(fr);
                     if (actionDialog == 0) {
                        if (relativeSelectedFilename.contains("." + FAAC_frames.FILE_EXT_RAD)) {
                           fileName = new File(this.jFileChooserSaveRadFile.getSelectedFile().getPath());
                        } else {
                           fileName = new File(this.jFileChooserSaveRadFile.getSelectedFile() + "." + FAAC_frames.FILE_EXT_RAD);
                        }

                        if (fileName.exists()) {
                           actionDialog = JOptionPane.showConfirmDialog(this, this.bundle.getString("replaceFileMsg"));
                        }
                     }
                  }

                  if (actionDialog == 0) {
                     this.generateRadFile(fileName, file_password);
                  }

                  return;
               }

               this.generateRadFile(fileName, file_password);
            }
         }
      }
   }

   private int parseRadFile(File fileName, String filePassword) {
      long lFileLenght = fileName.length();
      long lExpectedFileLenght = (long)this.boardRemote.getRadFileLenght_inBytes();
      int iRes;
      if (lFileLenght > lExpectedFileLenght) {
         iRes = 1;
      } else if (lFileLenght < lExpectedFileLenght) {
         iRes = 2;
      } else {
         iRes = 0;
      }

      byte[] readBytes = new byte[(int)lFileLenght];

      try {
         FileInputStream fis = new FileInputStream(fileName);

         for (int i = 0; i < (int)lFileLenght; i++) {
            readBytes[i] = (byte)fis.read();
         }

         fis.close();
      } catch (IOException var16) {
         Logger.getLogger(FAACTimer.class.getName()).log(Level.SEVERE, null, var16);
      }

      boolean bCheck = false;

      try {
         bCheck = FAAC_Protocol.CheckRadFile(readBytes, filePassword, this.boardModel);
      } catch (Exception var15) {
         Logger.getLogger(FAACTimer.class.getName()).log(Level.SEVERE, null, var15);
      }

      if (!bCheck) {
         JOptionPane.showMessageDialog(this, this.bundle.getString("fileContentNotOk"), this.bundle.getString("Attention"), 0);
         return -1;
      } else {
         boolean bPswOk = FAAC_frames.parseRadFile(readBytes, this.boardRemote, filePassword);
         if (!bPswOk) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("filePswNotOk"), this.bundle.getString("Attention"), 0);
            return 3;
         } else {
            FAAC_Remote.RadioCode[] array = this.boardRemote.getRadioCodeArray();

            for (int iRemIndex = 1; iRemIndex < FAAC_Remote.getNbRadioCode(); iRemIndex++) {
               FAAC_Remote.RadioCode code = this.boardRemote.getRadioCodeArray()[iRemIndex];
               if (code.isIsModified()) {
                  this.isSomeCodeReadFromFile = true;
               }
            }

            this.jLBModRemote.setVisible(false);
            this.isModRemote = false;
            return iRes;
         }
      }
   }

   public void importRadFromFile() {
      JFrame fr = new JFrame();
      fr.setIconImage(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + this.bundle.getString("openIcon"))).getImage());
      int actionDialog = this.jFileChooserOpenRadFile.showOpenDialog(fr);
      if (actionDialog == 0) {
         File fileToParse = this.jFileChooserOpenRadFile.getSelectedFile();
         String relativeFilename = fileToParse.getName();
         if (!relativeFilename.contains("." + FAAC_frames.FILE_EXT_RAD)) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("fileTmrFormatNotOk"), this.bundle.getString("Attention"), 0);
            return;
         }

         long lFileLenght = fileToParse.length();
         long lExpectedFileLenght = (long)this.boardRemote.getRadFileLenght_inBytes();
         PasswordInsertDialog pswInsertDialog = new PasswordInsertDialog(null, true);
         pswInsertDialog.setVisible(true);
         boolean isPasswordInserted = pswInsertDialog.getIsPswInserted();
         if (!isPasswordInserted) {
            return;
         }

         String filePassword = pswInsertDialog.getPassword();
         int iRes = this.parseRadFile(fileToParse, filePassword);
         if (iRes == 0) {
            this.initPanelRemote();
            JOptionPane.showMessageDialog(this, this.bundle.getString("importRadFileOkMsg"), this.bundle.getString("Information"), 1);
         } else if (iRes == 1) {
            this.initPanelRemote();
            JOptionPane.showMessageDialog(this, this.bundle.getString("importRadFileOkMsgLongerLengh"), this.bundle.getString("Information"), 1);
         } else if (iRes == 2) {
            this.initPanelRemote();
            JOptionPane.showMessageDialog(this, this.bundle.getString("importRadFileOkMsgSmallerLengh"), this.bundle.getString("Information"), 1);
         }
      }
   }

   public void saveRadioToCsv() {
      JFrame fr = new JFrame();
      fr.setIconImage(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + this.bundle.getString("saveIcon"))).getImage());
      int actionDialog = this.jFileChooserSaveToCsv.showSaveDialog(fr);
      if (actionDialog == 0) {
         String relativeSelectedFilename = this.jFileChooserSaveToCsv.getSelectedFile().getName().toLowerCase();
         File fileName;
         if (relativeSelectedFilename.contains(".csv")) {
            fileName = new File(this.jFileChooserSaveToCsv.getSelectedFile().getPath());
         } else {
            fileName = new File(this.jFileChooserSaveToCsv.getSelectedFile() + ".csv");
         }

         if (fileName != null) {
            if (fileName.exists()) {
               actionDialog = JOptionPane.showConfirmDialog(this, this.bundle.getString("replaceFileMsg"));

               while (actionDialog == 1) {
                  actionDialog = this.jFileChooserSaveToCsv.showSaveDialog(fr);
                  if (actionDialog == 0) {
                     if (relativeSelectedFilename.contains(".csv")) {
                        fileName = new File(this.jFileChooserSaveToCsv.getSelectedFile().getPath());
                     } else {
                        fileName = new File(this.jFileChooserSaveToCsv.getSelectedFile() + ".csv");
                     }

                     if (fileName.exists()) {
                        actionDialog = JOptionPane.showConfirmDialog(this, this.bundle.getString("replaceFileMsg"));
                     }
                  }
               }

               if (actionDialog == 0) {
                  this.generateCsvFile(fileName);
               }

               return;
            }

            this.generateCsvFile(fileName);
         }
      }
   }

   private void jTRemoteKeyPressed(KeyEvent evt) {
      char c = evt.getKeyChar();
      int keycode = evt.getKeyCode();
      if (keycode == 127 || keycode == 8) {
         this.deleteSelectedRadio();
      }
   }

   private void jBtPreviousMousePressed(MouseEvent evt) {
      if (this.jBtPrevious.isEnabled()) {
         this.remModel.pageUp();
         this.jLbPageNb.setText(String.valueOf(this.remModel.getPageOffset() + 1));
         this.jLbTotPage.setText(String.valueOf(this.remModel.getPageCount()));
         if (this.remModel.getPageOffset() == 0) {
            this.upButton.setEnabled(false);
            this.jBtPrevious.setEnabled(false);
         }

         this.downButton.setEnabled(true);
         this.jBtNext.setEnabled(true);
      }
   }

   private void jBtNextMousePressed(MouseEvent evt) {
      if (this.jBtNext.isEnabled()) {
         this.remModel.pageDown();
         this.jLbPageNb.setText(String.valueOf(this.remModel.getPageOffset() + 1));
         this.jLbTotPage.setText(String.valueOf(this.remModel.getPageCount()));
         if (this.remModel.getPageOffset() == this.remModel.getPageCount() - 1) {
            this.downButton.setEnabled(false);
            this.jBtNext.setEnabled(false);
         }

         this.upButton.setEnabled(true);
         this.jBtPrevious.setEnabled(true);
      }
   }

   private void jBtDeleteSelRemoteMousePressed(MouseEvent evt) {
      if (this.jBtDeleteSelRemote.isEnabled()) {
         this.deleteSelectedRadio();
      }
   }

   private void jBtDeleteAllRemoteMousePressed(MouseEvent evt) {
      if (this.jBtDeleteAllRemote.isEnabled()) {
         this.deleteAll();
      }
   }

   private void jBtImportRadFromFileMousePressed(MouseEvent evt) {
      this.boardRemote.emptyRadio();
      this.initPanelRemote();
      this.initPanelCurrentCode();
      if (this.jBtImportRadFromFile.isEnabled()) {
         this.importRadFromFile();
      }
   }

   private void jBtReadFromBoardMousePressed(MouseEvent evt) {
      this.boardRemote.emptyRadio();
      this.initPanelRemote();
      this.initPanelCurrentCode();
      this.lastActiveCodeIndex = -1;
      if (this.jBtReadFromBoard.isEnabled()) {
         if (this.isBoardConnected) {
            this.readRadioFromBoardMethod();
         } else {
            Object[] listeners = this.listenersFromBoard.getListenerList();
            this.notifyToActionListeners(listeners);
         }
      }
   }

   private void jBtExportRadOnFileMousePressed(MouseEvent evt) {
      if (this.jBtExportRadOnFile.isEnabled()) {
         this.exportRadioOnFile();
      }
   }

   private void jBtWriteOnBoardMousePressed(MouseEvent evt) {
      if (this.jBtWriteOnBoard.isEnabled()) {
         if (this.isBoardConnected) {
            this.writeRadioOnBoardMethod();
         } else {
            JOptionPane.showMessageDialog(this, this.bundle.getString("NotConnected_message"), this.bundle.getString("Attention"), 2);
         }
      }
   }

   private void jBtAcqCh1MousePressed(MouseEvent evt) {
      if (this.jBtAcqCh1.isEnabled()) {
         WarningMessageNoMovCmd optionPane = new WarningMessageNoMovCmd(
            this.parentForDlgs, true, this.bundle.getString("acqCh1WarningMsg"), "AcqRadio1", this.boardCommands
         );
         optionPane.setVisible(true);
      }
   }

   private void jBtAcqCh2MousePressed(MouseEvent evt) {
      if (this.jBtAcqCh2.isEnabled()) {
         WarningMessageNoMovCmd optionPane = new WarningMessageNoMovCmd(
            this.parentForDlgs, true, this.bundle.getString("acqCh2WarningMsg"), "AcqRadio2", this.boardCommands
         );
         optionPane.setVisible(true);
      }
   }

   private void jBtSaveToCsvFileMousePressed(MouseEvent evt) {
      if (this.jBtSaveToCsvFile.isEnabled()) {
         this.saveRadioToCsv();
      }
   }

   private void jRbListOpenMouseClicked(MouseEvent evt) {
   }

   private void jRbListClosedMouseClicked(MouseEvent evt) {
   }

   private void jChbEnabledStateChanged(ChangeEvent evt) {
   }

   private void jTRemoteMousePressed(MouseEvent evt) {
      int iRow = this.jTRemote.getSelectedRow();
      int iColumn = this.jTRemote.getSelectedColumn();
      FAAC_Remote.RadioCode[] radioCodes = this.boardRemote.getRadioCodeArray();
      if (iColumn == 4 && this.remModel.getValueAt(iRow, 5) != null) {
         Boolean bEnabled = (Boolean)this.remModel.getValueAt(iRow, 5);
         radioCodes[iRow + 1].getCode2().set31_1disabled0enabled(bEnabled);
         radioCodes[iRow + 1].setIsModified(true);
         this.isSomeCodeModified = true;
         this.jLBModRemote.setVisible(true);
         if (this.jLbPos.getText().equals(String.valueOf(iRow + 1))) {
            this.jChbEnabled.setSelected(!bEnabled);
         }
      }
   }

   private void jChbEnabledMousePressed(MouseEvent evt) {
      String strIndexCode = this.jLbPos.getText();
      int indexCode = Integer.parseInt(strIndexCode);
      FAAC_Remote.RadioCode[] radioCodes = this.boardRemote.getRadioCodeArray();
      FAAC_Remote.RadioCode currentCode = radioCodes[indexCode];
      boolean isCurrentlyEnabled = !currentCode.getCode2().get31_1disabled0enabled();
      if (!this.jChbEnabled.isSelected() && !isCurrentlyEnabled || this.jChbEnabled.isSelected() && isCurrentlyEnabled) {
         currentCode.getCode2().set31_1disabled0enabled(this.jChbEnabled.isSelected());
         currentCode.setIsModified(true);
         this.isSomeCodeModified = true;
         this.jLBModRemote.setVisible(true);
         this.initPanelRemote();
      }
   }

   private void jCbTypeDSMousePressed(MouseEvent evt) {
      this.boardRemote.getProgRadio().set01_DisableDS(this.jCbTypeDS.isSelected());
      this.jLBModRemote.setVisible(true);
      this.isModRemote = true;
      this.initPanelRemote();
   }

   private void jCbTypeRCMousePressed(MouseEvent evt) {
      this.boardRemote.getProgRadio().set02_DisableRC(this.jCbTypeRC.isSelected());
      this.jLBModRemote.setVisible(true);
      this.isModRemote = true;
      this.initPanelRemote();
   }

   private void jCbTypeSHMousePressed(MouseEvent evt) {
      this.boardRemote.getProgRadio().set03_DisableSLH(this.jCbTypeSH.isSelected());
      this.jLBModRemote.setVisible(true);
      this.isModRemote = true;
      this.initPanelRemote();
   }

   private void generateCsvFile(File fileName) {
      try {
         FileWriter writer = new FileWriter(fileName);
         writer.append(this.bundle.getString("position"));
         writer.append(',');
         writer.append(this.bundle.getString("radioCode"));
         writer.append(',');
         writer.append(this.bundle.getString("channel"));
         writer.append(',');
         writer.append(this.bundle.getString("codeType"));
         writer.append(',');
         writer.append(this.bundle.getString("enabled"));
         writer.append(',');
         writer.append(this.bundle.getString("name"));
         writer.append(',');
         writer.append(this.bundle.getString("surname"));
         writer.append(',');
         writer.append(this.bundle.getString("notes"));
         writer.append('\n');
         FAAC_Remote.RadioCode[] radioCodeArray = this.boardRemote.getRadioCodeArray();

         for (int i = 1; i < FAAC_Remote.getNbRadioCode(); i++) {
            if (this.boardRemote.isNotNullCode(i)) {
               writer.append(String.valueOf(radioCodeArray[i].getIndexRadioCode()));
               writer.append(',');
               String codingType = radioCodeArray[i].getCode2().getCodingType();
               writer.append(radioCodeArray[i].getCode1().getSerialCode(codingType));
               writer.append(',');
               writer.append(radioCodeArray[i].getCode1().get31_Channel_0ch1_1ch2() ? "2" : "1");
               writer.append(',');
               writer.append(codingType);
               writer.append(',');
               writer.append(radioCodeArray[i].getCode2().get31_1disabled0enabled() ? this.bundle.getString("no") : this.bundle.getString("yes"));
               writer.append(',');
               writer.append("");
               writer.append(',');
               writer.append("");
               writer.append(',');
               writer.append("");
               writer.append('\n');
            }
         }

         writer.flush();
         writer.close();
         JOptionPane.showMessageDialog(this, this.bundle.getString("saveCsvFileOkMsg"), this.bundle.getString("Information"), 1);
      } catch (IOException var6) {
         Logger.getLogger(FAACLogs.class.getName()).log(Level.SEVERE, null, var6);
      }
   }

   public void setjLBModRemoteVisible(boolean value) {
      this.jLBModRemote.setVisible(value);
      if (!value) {
         this.isSomeCodeDeleted = false;
         this.isSomeCodeReadFromFile = false;
         this.isSomeCodeModified = false;
      }
   }

   public void addListenerFromBoard(ActionListener listener) {
      this.listenersFromBoard.add(ActionListener.class, listener);
   }

   public void removeListenerFromBoard(ActionListener listener) {
      this.listenersFromBoard.remove(ActionListener.class, listener);
   }

   public boolean getIsNoMovCmdRequested() {
      this.isNoMovCmdRequested = this.boardCommands.getNoMovCmdArg().isSomeBitsHigh();
      return this.isNoMovCmdRequested;
   }

   public void setIsNoMovCmdRequested(boolean isNoMovCmdRequested) {
      this.isNoMovCmdRequested = isNoMovCmdRequested;
   }

   private void initComponentsForLocalization() {
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACRemote");
      this.jTRemote.getColumnModel().getColumn(0).setHeaderValue(this.bundle.getString("FAACRemote.jTRemote.columnModel.title5"));
      this.jTRemote.getColumnModel().getColumn(1).setHeaderValue(this.bundle.getString("FAACRemote.jTRemote.columnModel.title4"));
      this.jTRemote.getColumnModel().getColumn(2).setHeaderValue(this.bundle.getString("FAACRemote.jTRemote.columnModel.title0"));
      this.jTRemote.getColumnModel().getColumn(3).setHeaderValue(this.bundle.getString("FAACRemote.jTRemote.columnModel.title1"));
      this.jTRemote.getColumnModel().getColumn(4).setHeaderValue(this.bundle.getString("FAACRemote.jTRemote.columnModel.title2"));
      this.jLabel1.setText(this.bundle.getString("FAACRemote.jLabel1.text"));
      this.jLabel2.setText(this.bundle.getString("FAACRemote.jLabel2.text"));
      this.jLabel4.setText(this.bundle.getString("FAACRemote.jLabel4.text"));
      this.jBtPrevious.setText(this.bundle.getString("FAACRemote.jBtPrevious.text"));
      this.jBtNext.setText(this.bundle.getString("FAACRemote.jBtNext.text"));
      this.jPanel1.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACRemote.jPanel1.border.title")));
      this.jBtAcqCh1.setText(this.bundle.getString("FAACRemote.jBtAcqCh1.text"));
      this.jBtAcqCh1.setToolTipText(this.bundle.getString("FAACRemote.jBtAcqCh1.toolTipText"));
      this.jBtAcqCh2.setText(this.bundle.getString("FAACRemote.jBtAcqCh2.text"));
      this.jBtAcqCh2.setToolTipText(this.bundle.getString("FAACRemote.jBtAcqCh2.toolTipText"));
      this.jPanelWrite.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACRemote.jPanelWrite.border.title")));
      this.jBtWriteOnBoard.setText(this.bundle.getString("FAACRemote.jBtWriteOnBoard.text"));
      this.jBtExportRadOnFile.setText(this.bundle.getString("FAACRemote.jBtExportRadOnFile.text"));
      this.jPanelRead.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACRemote.jPanelRead.border.title")));
      this.jBtReadFromBoard.setText(this.bundle.getString("FAACRemote.jBtReadFromBoard.text"));
      this.jBtImportRadFromFile.setText(this.bundle.getString("FAACRemote.jBtImportRadFromFile.text"));
      this.jPanelEdit.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACRemote.jPanelEdit.border.title")));
      this.jBtDeleteSelRemote.setText(this.bundle.getString("FAACRemote.jBtDeleteSelRemote.text"));
      this.jBtDeleteAllRemote.setText(this.bundle.getString("FAACRemote.jBtDeleteAllRemote.text"));
      this.jLBModRemote.setText(this.bundle.getString("FAACRemote.jLBModRemote.text"));
      this.jPanel3.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACRemote.jPanel3.border.title")));
      this.jRbListOpen.setText(this.bundle.getString("FAACRemote.jRbListOpen.text"));
      this.jRbListClosed.setText(this.bundle.getString("FAACRemote.jRbListClosed.text"));
      this.jBtSaveToCsvFile.setText(this.bundle.getString("FAACRemote.jBtSaveToCsvFile.text"));
      this.jPanelTypeEnable.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACRemote.jPanelTypeEnable.border.title")));
      this.jCbTypeDS.setText(this.bundle.getString("FAACRemote.jCbTypeDS.text"));
      this.jCbTypeDS.setToolTipText(this.bundle.getString("FAACRemote.jCbTypeDS.toolTipText"));
      this.jCbTypeRC.setText(this.bundle.getString("FAACRemote.jCbTypeRC.text"));
      this.jCbTypeRC.setToolTipText(this.bundle.getString("FAACRemote.jCbTypeRC.toolTipText"));
      this.jCbTypeSH.setText(this.bundle.getString("FAACRemote.jCbTypeSH.text"));
      this.jCbTypeSH.setToolTipText(this.bundle.getString("FAACRemote.jCbTypeSH.toolTipText"));
      this.jPanelCurrentCode.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACRemote.jPanelCurrentCode.border.title")));
      this.jLbRemoteActive.setText(this.bundle.getString("FAACRemote.jLbRemoteActive.text"));
      this.jLbRemoteActive.setToolTipText(this.bundle.getString("FAACRemote.jLbRemoteActive.toolTipText"));
      this.jLbPosLabel.setText(this.bundle.getString("FAACRemote.jLbPosLabel.text"));
      this.jLbPosCodeLabel.setText(this.bundle.getString("FAACRemote.jLbPosCodeLabel.text"));
      this.jLbChannelLabel.setText(this.bundle.getString("FAACRemote.jLbChannelLabel.text"));
      this.jChbEnabled.setText(this.bundle.getString("FAACRemote.jChbEnabled.text"));
      this.jLbCodingLabel.setText(this.bundle.getString("FAACRemote.jLbCodingLabel.text"));
   }

   class ArrowIcon implements Icon {
      public static final int UP = 0;
      public static final int DOWN = 1;
      private int direction;
      private Polygon pagePolygon = new Polygon(new int[]{2, 4, 4, 10, 10, 2}, new int[]{4, 4, 2, 2, 12, 12}, 6);
      private int[] arrowX = new int[]{4, 9, 6};
      private Polygon arrowUpPolygon = new Polygon(this.arrowX, new int[]{10, 10, 4}, 3);
      private Polygon arrowDownPolygon = new Polygon(this.arrowX, new int[]{6, 6, 11}, 3);

      public ArrowIcon(int which) {
         this.direction = which;
      }

      @Override
      public int getIconWidth() {
         return 14;
      }

      @Override
      public int getIconHeight() {
         return 14;
      }

      @Override
      public void paintIcon(Component c, Graphics g, int x, int y) {
         g.setColor(Color.black);
         this.pagePolygon.translate(x, y);
         g.drawPolygon(this.pagePolygon);
         this.pagePolygon.translate(-x, -y);
         if (this.direction == 0) {
            this.arrowUpPolygon.translate(x, y);
            g.fillPolygon(this.arrowUpPolygon);
            this.arrowUpPolygon.translate(-x, -y);
         } else {
            this.arrowDownPolygon.translate(x, y);
            g.fillPolygon(this.arrowDownPolygon);
            this.arrowDownPolygon.translate(-x, -y);
         }
      }
   }

   public static class Record {
      static String[] headers = new String[]{
         FAACRemote.bundleStatic.getString("position"),
         FAACRemote.bundleStatic.getString("status"),
         FAACRemote.bundleStatic.getString("radioCode"),
         FAACRemote.bundleStatic.getString("channel"),
         FAACRemote.bundleStatic.getString("codeType"),
         FAACRemote.bundleStatic.getString("enabled")
      };
      static int counter;
      Object[] data;

      public Record() {
         this.data = new Object[]{null, null, null, null, null, null};
      }

      public Record(Object position, Object ledStateIco, String radioCode, int channel, String codeType, boolean enabled) {
         this.data = new Object[]{position, ledStateIco, radioCode, channel, codeType, enabled};
      }

      public void setValueAt(Object obj, int i) {
         this.data[i] = obj;
      }

      public Object getValueAt(int i) {
         return this.data[i];
      }

      public static String getColumnName(int i) {
         return headers[i];
      }

      public static int getColumnCount() {
         return headers.length;
      }
   }

   public class RemoteTableModel extends AbstractTableModel {
      protected int pageSize;
      protected int pageOffset;
      protected FAACRemote.Record[] data;
      Class[] types = new Class[]{Object.class, Object.class, Object.class, Integer.class, Object.class, Boolean.class};
      boolean[] canEdit = new boolean[]{false, false, false, false, false, true};

      public RemoteTableModel(int numRows, int size) {
         this.data = new FAACRemote.Record[numRows];
         this.pageSize = size;

         for (int i = 0; i < this.data.length; i++) {
            this.data[i] = new FAACRemote.Record();
         }

         this.fireTableRowsInserted(0, numRows - 1);
      }

      public RemoteTableModel() {
         int nbCodes;
         if (FAACRemote.this.boardModel.compareToIgnoreCase("E145") == 0) {
            nbCodes = E145_Remote.getNbRadioCode();
         } else {
            nbCodes = E124_Remote.getNbRadioCode();
         }

         int numRows = nbCodes - 1;
         int size = 50;
         this.data = new FAACRemote.Record[numRows];
         this.pageSize = size;

         for (int i = 0; i < this.data.length; i++) {
            this.data[i] = new FAACRemote.Record();
         }

         this.fireTableRowsInserted(0, numRows - 1);
      }

      @Override
      public int getRowCount() {
         return Math.min(this.pageSize, this.data.length);
      }

      @Override
      public int getColumnCount() {
         return FAACRemote.Record.getColumnCount();
      }

      @Override
      public Object getValueAt(int row, int col) {
         int realRow = row + this.pageOffset * this.pageSize;
         return this.data[realRow].getValueAt(col);
      }

      public void setValueAt(FAACRemote.Record record, int realRow) {
         this.data[realRow] = record;
      }

      @Override
      public void setValueAt(Object obj, int realRow, int col) {
         this.data[realRow].setValueAt(obj, col);
      }

      @Override
      public String getColumnName(int col) {
         return FAACRemote.Record.getColumnName(col);
      }

      public int getPageOffset() {
         return this.pageOffset;
      }

      public int getPageCount() {
         return (int)Math.ceil((double)this.data.length / (double)this.pageSize);
      }

      public int getRealRowCount() {
         return this.data.length;
      }

      public int getPageSize() {
         return this.pageSize;
      }

      public void setPageSize(int s) {
         if (s != this.pageSize) {
            int oldPageSize = this.pageSize;
            this.pageSize = s;
            this.pageOffset = oldPageSize * this.pageOffset / this.pageSize;
            this.fireTableDataChanged();
         }
      }

      public int getCurrentPageOffset() {
         return this.pageOffset;
      }

      public void setPageOffset(int pageOffsetIn) {
         this.pageOffset = pageOffsetIn;
         this.fireTableDataChanged();
      }

      public void pageDown() {
         if (this.pageOffset < this.getPageCount() - 1) {
            this.pageOffset++;
            this.fireTableDataChanged();
         }
      }

      public void pageUp() {
         if (this.pageOffset > 0) {
            this.pageOffset--;
            this.fireTableDataChanged();
         }
      }

      public JScrollPane createPagingScrollPaneForTable(JTable jt) {
         JScrollPane jsp = new JScrollPane(jt);
         TableModel tmodel = jt.getModel();
         if (!(tmodel instanceof FAACRemote.RemoteTableModel)) {
            return jsp;
         } else {
            final FAACRemote.RemoteTableModel model = (FAACRemote.RemoteTableModel)tmodel;
            final JButton upButton = new JButton(FAACRemote.this.new ArrowIcon(0));
            upButton.setEnabled(false);
            final JButton downButton = new JButton(FAACRemote.this.new ArrowIcon(1));
            if (model.getPageCount() <= 1) {
               downButton.setEnabled(false);
            }

            upButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent ae) {
                  model.pageUp();
                  if (model.getPageOffset() == 0) {
                     upButton.setEnabled(false);
                  }

                  downButton.setEnabled(true);
               }
            });
            downButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent ae) {
                  model.pageDown();
                  if (model.getPageOffset() == model.getPageCount() - 1) {
                     downButton.setEnabled(false);
                  }

                  upButton.setEnabled(true);
               }
            });
            jsp.setVerticalScrollBarPolicy(22);
            jsp.setHorizontalScrollBarPolicy(32);
            jsp.setCorner("UPPER_RIGHT_CORNER", upButton);
            jsp.setCorner("LOWER_RIGHT_CORNER", downButton);
            return jsp;
         }
      }

      @Override
      public Class getColumnClass(int c) {
         return c == 1 ? ImageIcon.class : this.types[c];
      }

      @Override
      public boolean isCellEditable(int rowIndex, int columnIndex) {
         return this.canEdit[columnIndex];
      }
   }
}
