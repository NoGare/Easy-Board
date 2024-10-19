package FAACbeans;

import FAAClib.Debug;
import FAAClib.FAAC_Logs;
import FAAClib.KeyValue;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class FAACLogs extends JPanel {
   public final int tablePageSize = 64;
   public final int columnDate = 0;
   public final int columnEvent1 = 1;
   public final int columnEvent2 = 2;
   public final int columnEvent3 = 3;
   private static final ResourceBundle bundleStatic = ResourceBundle.getBundle("FAACbeans/resources/FAACLogs");
   private JButton jBtExportOnFile;
   private JButton jBtNext;
   private JButton jBtPrevious;
   private JButton jBtReadLogFromBoard;
   private JFileChooser jFileChooserSaveToCsv;
   private JLabel jLabelOf;
   private JLabel jLabelPage;
   private JLabel jLbPageNb;
   private JLabel jLbTotPage;
   private JPanel jPanelExportOnFile;
   private JPanel jPanelReadFromBoard;
   private JScrollPane jScrollPaneLogs;
   private JToggleButton jTBtRealtimeLog;
   private JTable jTLogs;
   private boolean[] isLogToBeReadOnBoard;
   FAAC_Logs boardLogs;
   ResourceBundle bundle;
   FAACLogs.LogsTableModel logsModel;
   JButton upButton;
   JButton downButton;
   private boolean isBoardConnected;
   private boolean isFwVersionSupported;
   private boolean isRealTimeLogRequested;
   private boolean isAllLogsReadFromBoard;
   private BackgroundWorkDialog backgroundWorkDialog;
   Frame parentForDlgs;

   public void setParentForDialogs(Frame parent) {
      this.parentForDlgs = parent;
   }

   public void updateLocale() {
      this.initComponentsForLocalization();
      this.upButton.setToolTipText(this.bundle.getString("pagePrevious"));
      this.downButton.setToolTipText(this.bundle.getString("pageNext"));
      this.repaint();
   }

   public FAACLogs() {
      this.initComponents();
      this.jTBtRealtimeLog.setVisible(false);
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACLogs");
      this.jTLogs.setAutoCreateRowSorter(true);
      this.isRealTimeLogRequested = false;
      this.isAllLogsReadFromBoard = false;
      this.jFileChooserSaveToCsv.setCurrentDirectory(new File("./"));
      this.jFileChooserSaveToCsv.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));
   }

   public void setLogsInfo(FAAC_Logs boardLogs, boolean isBoardConnected, boolean isFwVersionSupported, BackgroundWorkDialog backgroundWorkDialog) {
      this.boardLogs = boardLogs;
      this.isBoardConnected = isBoardConnected;
      this.isFwVersionSupported = isFwVersionSupported;
      this.backgroundWorkDialog = backgroundWorkDialog;
      this.isLogToBeReadOnBoard = new boolean[boardLogs.getNbLogsOnBoard()];
      this.jTLogs.setName("jTLogs");
      this.logsModel = new FAACLogs.LogsTableModel();
      this.jTLogs.setModel(this.logsModel);
      this.upButton = new JButton(new FAACLogs.ArrowIcon(0));
      this.upButton.setEnabled(false);
      this.jBtPrevious.setEnabled(false);
      this.downButton = new JButton(new FAACLogs.ArrowIcon(1));
      this.upButton.setToolTipText(this.bundle.getString("pagePrevious"));
      this.downButton.setToolTipText(this.bundle.getString("pageNext"));
      if (this.logsModel.getPageCount() <= 1) {
         this.downButton.setEnabled(false);
         this.jBtNext.setEnabled(false);
      }

      this.jLbPageNb.setText(String.valueOf(this.logsModel.getPageOffset() + 1));
      this.jLbTotPage.setText(String.valueOf(this.logsModel.getPageCount()));
      this.upButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
            FAACLogs.this.logsModel.pageUp();
            FAACLogs.this.jLbPageNb.setText(String.valueOf(FAACLogs.this.logsModel.getPageOffset() + 1));
            FAACLogs.this.jLbTotPage.setText(String.valueOf(FAACLogs.this.logsModel.getPageCount()));
            if (FAACLogs.this.logsModel.getPageOffset() == 0) {
               FAACLogs.this.upButton.setEnabled(false);
               FAACLogs.this.jBtPrevious.setEnabled(false);
            }

            FAACLogs.this.downButton.setEnabled(true);
            FAACLogs.this.jBtNext.setEnabled(true);
         }
      });
      this.downButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
            FAACLogs.this.logsModel.pageDown();
            FAACLogs.this.jLbPageNb.setText(String.valueOf(FAACLogs.this.logsModel.getPageOffset() + 1));
            FAACLogs.this.jLbTotPage.setText(String.valueOf(FAACLogs.this.logsModel.getPageCount()));
            if (FAACLogs.this.logsModel.getPageOffset() == FAACLogs.this.logsModel.getPageCount() - 1) {
               FAACLogs.this.downButton.setEnabled(false);
               FAACLogs.this.jBtNext.setEnabled(false);
            }

            FAACLogs.this.upButton.setEnabled(true);
            FAACLogs.this.jBtPrevious.setEnabled(true);
         }
      });
      this.jScrollPaneLogs.setVerticalScrollBarPolicy(22);
      this.jScrollPaneLogs.setHorizontalScrollBarPolicy(32);
      this.jScrollPaneLogs.setCorner("UPPER_RIGHT_CORNER", this.upButton);
      this.jScrollPaneLogs.setCorner("LOWER_RIGHT_CORNER", this.downButton);
      this.jTLogs.setAutoCreateRowSorter(false);
      this.jTLogs.setRowSorter(null);
   }

   public void initPanelLogs() {
      this.jBtReadLogFromBoard.setEnabled(this.isBoardConnected && this.isFwVersionSupported);
      this.jTBtRealtimeLog.setEnabled(this.isBoardConnected && this.isFwVersionSupported && this.isAllLogsReadFromBoard);
      LinkedList<FAAC_Logs.Log> logList = this.boardLogs.getLogList();
      int totalRow = this.logsModel.getRealRowCount();

      for (int iRow = 0; iRow < totalRow; iRow++) {
         if (iRow < logList.size()) {
            FAAC_Logs.Log logTmp = logList.get(iRow);
            if (logList.get(iRow) != null && !logList.get(iRow).isNullLog()) {
               FAACLogs.Record record = new FAACLogs.Record();
               record.setValueAt(logTmp.getLogDate(), 0);
               KeyValue kvEvent1 = this.getEventKeyValueFromKey(logTmp.getEvent1(), logTmp);
               record.setValueAt(kvEvent1, 1);
               KeyValue kvEvent2 = this.getEventKeyValueFromKey(logTmp.getEvent2(), logTmp);
               record.setValueAt(kvEvent2, 2);
               KeyValue kvEvent3 = this.getEventKeyValueFromKey(logTmp.getEvent3(), logTmp);
               record.setValueAt(kvEvent3, 3);
               this.logsModel.setValueAt(record, iRow);
               this.logsModel.fireTableRowsUpdated(iRow, iRow);
            }
         }
      }

      this.jTLogs.revalidate();
      this.jTLogs.repaint();
   }

   public void DisableBoardCommunication() {
      this.jBtReadLogFromBoard.setEnabled(false);
   }

   public void EnableBoardCommunication() {
      this.jBtReadLogFromBoard.setEnabled(true);
   }

   public KeyValue getEventKeyValueFromKey(int keyOfEvent, FAAC_Logs.Log log) {
      KeyValue kvEvent = new KeyValue();
      String str = "";
      switch (keyOfEvent) {
         case -128:
            str = "IN128_OPENA";
            break;
         case -127:
            str = "IN129_OPENB";
            break;
         case -126:
            str = "IN130_OPENAA";
            break;
         case -125:
            str = "IN131_OPENBA";
            break;
         case -124:
            str = "IN132_OPENAprio";
            break;
         case -123:
            str = "IN133_OPENBprio";
            break;
         case -122:
            str = "IN134_EmergOpen";
            break;
         case -121:
            str = "IN135_EmergClose";
            break;
         case -120:
            str = "IN136_Timer";
            break;
         case -119:
            str = "IN137_SicurOpenCmd";
            break;
         case -118:
            str = "IN138_SicurClose";
            break;
         case -117:
            str = "IN139_SicurOpen";
            break;
         case -116:
            str = "IN140_IndipMov";
            break;
         case -115:
            str = "IN141_FinePausaA";
            break;
         case -114:
            str = "IN142_Close";
            break;
         case -113:
            str = "IN143_ClosePrio";
         case -112:
         case -110:
         case -88:
         case -87:
         case -85:
         case -84:
         case -83:
         case -82:
         case -81:
         case -80:
         case -79:
         case -78:
         case -77:
         case -76:
         case -75:
         case -74:
         case -73:
         case -72:
         case -71:
         case -70:
         case -69:
         case -68:
         case -67:
         case -66:
         case -65:
         case -64:
         case -63:
         case -62:
         case -61:
         case -60:
         case -59:
         case -58:
         case -57:
         case -56:
         case -55:
         case -54:
         case -53:
         case -52:
         case -51:
         case -50:
         case -49:
         case -48:
         case -47:
         case -46:
         case -45:
         case -44:
         case -43:
         case -42:
         case -41:
         case -40:
         case -39:
         case -38:
         case -37:
         case -36:
         case -35:
         case -34:
         case -33:
         case -32:
         case -31:
         case -30:
         case -29:
         case -28:
         case -27:
         case -26:
         case -25:
         case -24:
         case -23:
         case -22:
         case -21:
         case -20:
         case -19:
         case -18:
         case -17:
         case -16:
         case -15:
         case -14:
         case -13:
         case -12:
         case -11:
         case -10:
         case -9:
         case -8:
         case -7:
         case -6:
         case -5:
         case -4:
         case -3:
         case -2:
         case 0:
         default:
            break;
         case -111:
            str = "IN145_PrenotClose";
            break;
         case -109:
            str = "IN147_TimerProg";
            break;
         case -108:
            str = "IN148_Stop";
            break;
         case -107:
            str = "IN149_TypeIn1";
            break;
         case -106:
            str = "IN150_TypeIn2";
            break;
         case -105:
            str = "IN151_TypeIn3";
            break;
         case -104:
            str = "IN152_TypeIn4";
            break;
         case -103:
            str = "IN153_TypeIn5";
            break;
         case -102:
            str = "IN154_Error";
            break;
         case -101:
            str = "IN155_TypeRadio1Dec";
            break;
         case -100:
            str = "IN156_TypeRadio2Dec";
            break;
         case -99:
            str = "IN157_TypeRadio1Xf";
            break;
         case -98:
            str = "IN158_TypeRadio2Xf";
            break;
         case -97:
            str = "IN159_TypeExt";
            break;
         case -96:
            str = "IN160_TypeBus";
            break;
         case -95:
            str = "IN161_TypeFinecorsa";
            break;
         case -94:
            str = "IN162_TypeEmerg";
            break;
         case -93:
            str = "IN163_NoClose";
            break;
         case -92:
            str = "IN164_NoOpen";
            break;
         case -91:
            str = "IN165_CoastOpen";
            break;
         case -90:
            str = "IN166_CoastClose";
            break;
         case -89:
            str = "IN167_CoastOpenClose";
            break;
         case -86:
            str = "IN170_Intrusion";
            break;
         case -1:
            str = "IN255_FineMov";
            break;
         case 1:
            str = "E01_ACCENSIONE_SCHEDA";
            break;
         case 2:
            str = "E02_SCHEDA_GUASTA";
            break;
         case 3:
            str = "E03_PASSAGGIO_A_BATTERIA";
            break;
         case 4:
            str = "E04_PASSAGGIO_A_RETE";
            break;
         case 5:
            str = "E05_INIZIO_APERTURA";
            break;
         case 6:
            str = "E06_INIZIO_CHIUSURA";
            break;
         case 7:
            str = "E07_OSTACOLO1";
            break;
         case 8:
            str = "E08_CC_BUS2EASY";
            break;
         case 9:
            str = "E09_ERRORE_FAILSAFE";
            break;
         case 10:
            str = "E10_TIME_OUT";
            break;
         case 11:
            str = "E11_ERROR_FC1";
            break;
         case 12:
            str = "E12_ERROR_FC2";
            break;
         case 13:
            str = "E13_ERROR_2EASY";
            break;
         case 14:
            str = "E14_REQ_ASSISTENZA";
            break;
         case 15:
            str = "E15_RESET_NUM_CICLI";
            break;
         case 16:
            str = "E16_LOCK1_CC";
            break;
         case 17:
            str = "E17_LOCK2_CC";
            break;
         case 18:
            str = "E18_RADIO_FULL";
            break;
         case 19:
            str = "E19_TIMER_ON";
            break;
         case 20:
            str = "E20_TIMER_OFF";
            break;
         case 21:
            str = "E21_TEACH_RADIO1";
            break;
         case 22:
            str = "E22_TEACH_RADIO2";
            break;
         case 23:
            str = "E23_ERROR_ENCODER1";
            break;
         case 24:
            str = "E24_ERROR_ENCODER2";
            break;
         case 25:
            str = "E25_ERROR_CKS_SETUP";
            break;
         case 26:
            str = "E26_SAVE_PROG";
            break;
         case 27:
            str = "E27_2EASY_ISCRIZIONE";
            break;
         case 28:
            str = "E28_APERTO";
            break;
         case 29:
            str = "E29_CHIUSO";
            break;
         case 30:
            str = "E30_PAUSA";
            break;
         case 31:
            str = "E31_FERMO";
            break;
         case 32:
            str = "E32_INIZIO_APERTURA_INDIP1";
            break;
         case 33:
            str = "E33_INIZIO_CHIUSURA_INDIP1";
            break;
         case 34:
            str = "E34_INIZIO_APERTURA_INDIP2";
            break;
         case 35:
            str = "E35_INIZIO_CHIUSURA_INDIP2";
            break;
         case 36:
            str = "E36_INIZIO_HOLDPOSITION";
            break;
         case 37:
            str = "E37_SETUP_START";
            break;
         case 38:
            str = "E38_SETUP_END";
            break;
         case 39:
            str = "E39_SETUP_ABORTED";
            break;
         case 40:
            str = "E40_ACCAVALLAMENTO_ANTA";
            break;
         case 41:
            str = "E41_IN_SLEEP";
            break;
         case 42:
            str = "E42_VACC_FAIL";
            break;
         case 43:
            str = "E43_CONFIG_ERROR";
            break;
         case 44:
            str = "E44_BLOCCO_TERMICO";
            break;
         case 45:
            str = "E45_RADIO_CLEAR";
            break;
         case 46:
            str = "E46_BUS_APPELLO_ERROR";
            break;
         case 47:
            str = "E47_2EASY_FAILSAFE";
            break;
         case 48:
            str = "E48_FAIL_MOTOR1";
            break;
         case 49:
            str = "E49_FAIL_MOTOR2";
            break;
         case 50:
            str = "E50_DEEP_SLEEP";
            break;
         case 51:
            str = "E51_PARTIAL_OPEN";
            break;
         case 52:
            str = "E52_PARTIAL_CLOSE";
            break;
         case 53:
            str = "E53_UL_OSTACOLO";
      }

      kvEvent.setKey(keyOfEvent);
      if (!str.equals("")) {
         String strTmp = this.bundle.getString(str);
         if (keyOfEvent == 1) {
            String fwVersion = new String(new char[]{log.getLogSwVersion_SW1(), '.', log.getLogSwVersion_SW2()});
            strTmp = strTmp + " SW:" + fwVersion;
         }

         kvEvent.setValue(strTmp);
      } else {
         kvEvent.setValue(str);
      }

      return kvEvent;
   }

   private void initComponents() {
      this.jFileChooserSaveToCsv = new JFileChooser();
      this.jScrollPaneLogs = new JScrollPane();
      this.jTLogs = new JTable();
      this.jPanelExportOnFile = new JPanel();
      this.jBtExportOnFile = new JButton();
      this.jPanelReadFromBoard = new JPanel();
      this.jBtReadLogFromBoard = new JButton();
      this.jTBtRealtimeLog = new JToggleButton();
      this.jLabelPage = new JLabel();
      this.jLbPageNb = new JLabel();
      this.jLabelOf = new JLabel();
      this.jLbTotPage = new JLabel();
      this.jBtPrevious = new JButton();
      this.jBtNext = new JButton();
      this.jFileChooserSaveToCsv.setName("jFileChooserSaveToCsv");
      this.setName("FAACLogs");
      this.setPreferredSize(new Dimension(755, 530));
      this.jScrollPaneLogs.setName("jScrollPaneLogs");
      this.jScrollPaneLogs.setPreferredSize(new Dimension(546, 485));
      this.jTLogs
         .setModel(
            new DefaultTableModel(
               new Object[][]{
                  {"2010-10-13 12:00", "Allerta", "Accavallamento anta", null},
                  {"2010-10-10 19:57", "Errore", "Scheda guasta", null},
                  {"2010-10-11 11:40", "Info", "Accensione scheda", null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null}
               },
               new String[]{"Data e ora", "Evento 1", "Evento 2", "Evento 3"}
            ) {
               boolean[] canEdit = new boolean[]{false, false, false, false};

               @Override
               public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return this.canEdit[columnIndex];
               }
            }
         );
      this.jTLogs.setFillsViewportHeight(true);
      this.jTLogs.setGridColor(new Color(153, 153, 153));
      this.jTLogs.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
      this.jTLogs.setName("jTLogs");
      this.jTLogs.setRowHeight(20);
      this.jScrollPaneLogs.setViewportView(this.jTLogs);
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACLogs");
      if (this.jTLogs.getColumnModel().getColumnCount() > 0) {
         this.jTLogs.getColumnModel().getColumn(0).setPreferredWidth(70);
         this.jTLogs.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("FAACLogs.jTLogs.columnModel.title0"));
         this.jTLogs.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("FAACLogs.jTLogs.columnModel.title1"));
         this.jTLogs.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("FAACLogs.jTLogs.columnModel.title2"));
         this.jTLogs.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("FAACLogs.jTLogs.columnModel.title3"));
      }

      this.jTLogs.getAccessibleContext().setAccessibleName(bundle.getString("FAACLogs.jTLogs.AccessibleContext.accessibleName"));
      this.jPanelExportOnFile.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACLogs.jPanelExportOnFile.border.title")));
      this.jPanelExportOnFile.setName("jPanelExportOnFile");
      this.jBtExportOnFile.setText(bundle.getString("FAACLogs.jBtExportOnFile.text"));
      this.jBtExportOnFile.setName("jBtExportOnFile");
      this.jBtExportOnFile.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACLogs.this.jBtExportOnFileMousePressed(evt);
         }
      });
      GroupLayout jPanelExportOnFileLayout = new GroupLayout(this.jPanelExportOnFile);
      this.jPanelExportOnFile.setLayout(jPanelExportOnFileLayout);
      jPanelExportOnFileLayout.setHorizontalGroup(
         jPanelExportOnFileLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelExportOnFileLayout.createSequentialGroup().addContainerGap().addComponent(this.jBtExportOnFile, -1, 145, 32767).addContainerGap())
      );
      jPanelExportOnFileLayout.setVerticalGroup(
         jPanelExportOnFileLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanelExportOnFileLayout.createSequentialGroup().addComponent(this.jBtExportOnFile).addContainerGap(-1, 32767))
      );
      this.jPanelReadFromBoard.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACLogs.jPanelReadFromBoard.border.title")));
      this.jPanelReadFromBoard.setName("jPanelReadFromBoard");
      this.jBtReadLogFromBoard.setText(bundle.getString("FAACLogs.jBtReadLogFromBoard.text"));
      this.jBtReadLogFromBoard.setName("jBtReadLogFromBoard");
      this.jBtReadLogFromBoard.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACLogs.this.jBtReadLogFromBoardMousePressed(evt);
         }
      });
      this.jTBtRealtimeLog.setText(bundle.getString("FAACLogs.jTBtRealtimeLog.text"));
      this.jTBtRealtimeLog.setEnabled(false);
      this.jTBtRealtimeLog.setName("jTBtRealtimeLog");
      this.jTBtRealtimeLog.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACLogs.this.jTBtRealtimeLogMousePressed(evt);
         }
      });
      GroupLayout jPanelReadFromBoardLayout = new GroupLayout(this.jPanelReadFromBoard);
      this.jPanelReadFromBoard.setLayout(jPanelReadFromBoardLayout);
      jPanelReadFromBoardLayout.setHorizontalGroup(
         jPanelReadFromBoardLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelReadFromBoardLayout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     jPanelReadFromBoardLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jTBtRealtimeLog, Alignment.TRAILING, -1, 145, 32767)
                        .addComponent(this.jBtReadLogFromBoard, Alignment.TRAILING, -1, 145, 32767)
                  )
                  .addContainerGap()
            )
      );
      jPanelReadFromBoardLayout.setVerticalGroup(
         jPanelReadFromBoardLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelReadFromBoardLayout.createSequentialGroup()
                  .addComponent(this.jBtReadLogFromBoard)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jTBtRealtimeLog)
                  .addContainerGap(-1, 32767)
            )
      );
      this.jLabelPage.setText(bundle.getString("FAACLogs.jLabelPage.text"));
      this.jLabelPage.setName("jLabelPage");
      this.jLbPageNb.setText(bundle.getString("FAACLogs.jLbPageNb.text"));
      this.jLbPageNb.setName("jLbPageNb");
      this.jLabelOf.setText(bundle.getString("FAACLogs.jLabelOf.text"));
      this.jLabelOf.setName("jLabelOf");
      this.jLbTotPage.setText(bundle.getString("FAACLogs.jLbTotPage.text"));
      this.jLbTotPage.setName("jLbTotPage");
      this.jBtPrevious.setText(bundle.getString("FAACLogs.jBtPrevious.text"));
      this.jBtPrevious.setName("jBtPrevious");
      this.jBtPrevious.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACLogs.this.jBtPreviousMousePressed(evt);
         }
      });
      this.jBtNext.setText(bundle.getString("FAACLogs.jBtNext.text"));
      this.jBtNext.setName("jBtNext");
      this.jBtNext.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACLogs.this.jBtNextMousePressed(evt);
         }
      });
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
                              .addComponent(this.jScrollPaneLogs, -1, 558, 32767)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jPanelExportOnFile, -2, -1, -2)
                                    .addComponent(this.jPanelReadFromBoard, -1, -1, 32767)
                              )
                        )
                        .addGroup(
                           layout.createSequentialGroup()
                              .addContainerGap()
                              .addComponent(this.jLabelPage)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbPageNb)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLabelOf)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbTotPage)
                              .addPreferredGap(ComponentPlacement.UNRELATED)
                              .addComponent(this.jBtPrevious)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jBtNext)
                        )
                  )
                  .addContainerGap()
            )
      );
      layout.linkSize(0, this.jPanelExportOnFile, this.jPanelReadFromBoard);
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addComponent(this.jPanelReadFromBoard, -2, 88, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jPanelExportOnFile, -2, -1, -2)
                        )
                        .addComponent(this.jScrollPaneLogs, -1, 490, 32767)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.jLabelPage)
                        .addComponent(this.jLbPageNb)
                        .addComponent(this.jLabelOf)
                        .addComponent(this.jLbTotPage)
                        .addComponent(this.jBtPrevious)
                        .addComponent(this.jBtNext)
                  )
                  .addContainerGap()
            )
      );
      this.getAccessibleContext().setAccessibleName(bundle.getString("FAACLogs.AccessibleContext.accessibleName"));
   }

   private void prepareBackgroundWorkLogReading() {
      int maxValueProgressBar = this.boardLogs.getNbLogsOnBoard();
      this.backgroundWorkDialog.setMaximumLevel(maxValueProgressBar);
      this.backgroundWorkDialog.setLocationRelativeTo(this);
      this.backgroundWorkDialog.setTitle(this.bundle.getString("readLogFromBoard"));
      this.backgroundWorkDialog.setMsg(this.bundle.getString("readLogFromBoardOngoing"));
      this.backgroundWorkDialog.reset();
      this.backgroundWorkDialog.setValueLevel(1);
      this.backgroundWorkDialog.setOperationOngoing(true);
   }

   private void jBtReadLogFromBoardMousePressed(MouseEvent evt) {
      if (this.jBtReadLogFromBoard.isEnabled()) {
         this.prepareBackgroundWorkLogReading();

         for (int i = 0; i < this.boardLogs.getNbLogsInMemory(); i++) {
            this.isLogToBeReadOnBoard[i] = true;
            Debug.printlnStatic("Set to true isLogToBeReadOnBoard for index " + String.valueOf(i));
         }

         this.backgroundWorkDialog.setVisible(true);
      }
   }

   private void jTBtRealtimeLogMousePressed(MouseEvent evt) {
   }

   private void jBtExportOnFileMousePressed(MouseEvent evt) {
      if (this.jBtExportOnFile.isEnabled()) {
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
   }

   private void jBtPreviousMousePressed(MouseEvent evt) {
      if (this.jBtPrevious.isEnabled()) {
         this.logsModel.pageUp();
         this.jLbPageNb.setText(String.valueOf(this.logsModel.getPageOffset() + 1));
         this.jLbTotPage.setText(String.valueOf(this.logsModel.getPageCount()));
         if (this.logsModel.getPageOffset() == 0) {
            this.upButton.setEnabled(false);
            this.jBtPrevious.setEnabled(false);
         }

         this.downButton.setEnabled(true);
         this.jBtNext.setEnabled(true);
      }
   }

   private void jBtNextMousePressed(MouseEvent evt) {
      if (this.jBtNext.isEnabled()) {
         this.logsModel.pageDown();
         this.jLbPageNb.setText(String.valueOf(this.logsModel.getPageOffset() + 1));
         this.jLbTotPage.setText(String.valueOf(this.logsModel.getPageCount()));
         if (this.logsModel.getPageOffset() == this.logsModel.getPageCount() - 1) {
            this.downButton.setEnabled(false);
            this.jBtNext.setEnabled(false);
         }

         this.upButton.setEnabled(true);
         this.jBtPrevious.setEnabled(true);
      }
   }

   private void generateCsvFile(File fileName) {
      try {
         FileWriter writer = new FileWriter(fileName);
         writer.append(this.bundle.getString("date"));
         writer.append(',');
         writer.append(this.bundle.getString("event") + "1");
         writer.append(',');
         writer.append(this.bundle.getString("event") + "2");
         writer.append(',');
         writer.append(this.bundle.getString("event") + "3");
         writer.append('\n');
         LinkedList<FAAC_Logs.Log> logList = this.boardLogs.getLogList();

         for (int iLog = 0; iLog < this.boardLogs.getNbLogsInMemory(); iLog++) {
            FAAC_Logs.Log logTmp = logList.get(iLog);
            if (!logTmp.isNullLog()) {
               writer.append(logTmp.getLogDate().toString());
               writer.append(',');
               KeyValue kvEvent1 = this.getEventKeyValueFromKey(logTmp.getEvent1(), logTmp);
               writer.append(kvEvent1.getValue());
               writer.append(',');
               KeyValue kvEvent2 = this.getEventKeyValueFromKey(logTmp.getEvent2(), logTmp);
               writer.append(kvEvent2.getValue());
               writer.append(',');
               KeyValue kvEvent3 = this.getEventKeyValueFromKey(logTmp.getEvent3(), logTmp);
               writer.append(kvEvent3.getValue());
               writer.append('\n');
            }
         }

         writer.flush();
         writer.close();
         JOptionPane.showMessageDialog(this, this.bundle.getString("sevaLogFileOkMsg"), this.bundle.getString("Information"), 1);
      } catch (IOException var9) {
         Logger.getLogger(FAACLogs.class.getName()).log(Level.SEVERE, null, var9);
      }
   }

   public boolean[] getIsLogToBeReadOnBoard() {
      return this.isLogToBeReadOnBoard;
   }

   public void setIsLogToBeReadOnBoard(boolean[] isLogToBeReadOnBoard) {
      this.isLogToBeReadOnBoard = isLogToBeReadOnBoard;
   }

   public boolean isIsRealTimeLogRequested() {
      return this.isRealTimeLogRequested;
   }

   public void setIsRealTimeLogRequested(boolean isRealTimeLogRequested) {
      this.isRealTimeLogRequested = isRealTimeLogRequested;
   }

   public boolean isIsAllLogsReadFromBoard() {
      return this.isAllLogsReadFromBoard;
   }

   public void setIsAllLogsReadFromBoard(boolean isAllLogsReadFromBoard) {
      this.isAllLogsReadFromBoard = isAllLogsReadFromBoard;
      this.jTBtRealtimeLog.setEnabled(this.isBoardConnected && this.isAllLogsReadFromBoard);
   }

   private void initComponentsForLocalization() {
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACLogs");
      this.jTLogs.getColumnModel().getColumn(0).setHeaderValue(this.bundle.getString("FAACLogs.jTLogs.columnModel.title0"));
      this.jTLogs.getColumnModel().getColumn(1).setHeaderValue(this.bundle.getString("FAACLogs.jTLogs.columnModel.title1"));
      this.jTLogs.getColumnModel().getColumn(2).setHeaderValue(this.bundle.getString("FAACLogs.jTLogs.columnModel.title2"));
      this.jTLogs.getColumnModel().getColumn(3).setHeaderValue(this.bundle.getString("FAACLogs.jTLogs.columnModel.title3"));
      this.jPanelExportOnFile.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACLogs.jPanelExportOnFile.border.title")));
      this.jBtExportOnFile.setText(this.bundle.getString("FAACLogs.jBtExportOnFile.text"));
      this.jPanelReadFromBoard.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACLogs.jPanelReadFromBoard.border.title")));
      this.jBtReadLogFromBoard.setText(this.bundle.getString("FAACLogs.jBtReadLogFromBoard.text"));
      this.jTBtRealtimeLog.setText(this.bundle.getString("FAACLogs.jTBtRealtimeLog.text"));
      this.jLabelPage.setText(this.bundle.getString("FAACLogs.jLabelPage.text"));
      this.jLabelOf.setText(this.bundle.getString("FAACLogs.jLabelOf.text"));
      this.jBtPrevious.setText(this.bundle.getString("FAACLogs.jBtPrevious.text"));
      this.jBtNext.setText(this.bundle.getString("FAACLogs.jBtNext.text"));
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

   public class LogsTableModel extends AbstractTableModel {
      protected int pageSize;
      protected int pageOffset;
      protected FAACLogs.Record[] data;
      Class[] types = new Class[]{Object.class, Object.class, Object.class, Object.class};
      boolean[] canEdit = new boolean[]{false, false, false, false};

      public LogsTableModel(int numRows, int size) {
         this.data = new FAACLogs.Record[numRows];
         this.pageSize = size;

         for (int i = 0; i < this.data.length; i++) {
            this.data[i] = new FAACLogs.Record();
         }

         this.fireTableRowsInserted(0, numRows - 1);
      }

      public LogsTableModel() {
         this(FAAC_Logs.GetNbLogsOnBoard(), 64);
      }

      @Override
      public int getRowCount() {
         return Math.min(this.pageSize, this.data.length);
      }

      @Override
      public int getColumnCount() {
         return FAACLogs.Record.getColumnCount();
      }

      @Override
      public Object getValueAt(int row, int col) {
         int realRow = row + this.pageOffset * this.pageSize;
         return this.data[realRow].getValueAt(col);
      }

      public void setValueAt(FAACLogs.Record record, int realRow) {
         this.data[realRow] = record;
      }

      @Override
      public void setValueAt(Object obj, int realRow, int col) {
         this.data[realRow].setValueAt(obj, col);
      }

      @Override
      public String getColumnName(int col) {
         return FAACLogs.Record.getColumnName(col);
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
         if (!(tmodel instanceof FAACLogs.LogsTableModel)) {
            return jsp;
         } else {
            final FAACLogs.LogsTableModel model = (FAACLogs.LogsTableModel)tmodel;
            final JButton upButton = new JButton(FAACLogs.this.new ArrowIcon(0));
            upButton.setEnabled(false);
            final JButton downButton = new JButton(FAACLogs.this.new ArrowIcon(1));
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
         return this.types[c];
      }

      @Override
      public boolean isCellEditable(int rowIndex, int columnIndex) {
         return this.canEdit[columnIndex];
      }
   }

   public static class Record {
      static String[] headers = new String[]{
         FAACLogs.bundleStatic.getString("date"),
         FAACLogs.bundleStatic.getString("event1"),
         FAACLogs.bundleStatic.getString("event2"),
         FAACLogs.bundleStatic.getString("event3")
      };
      static int counter;
      Object[] data;

      public Record() {
         this.data = new Object[]{null, null, null, null};
      }

      public Record(Object date, Object event1, Object event2, Object event3) {
         this.data = new Object[]{date, event1, event2, event3};
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
}
