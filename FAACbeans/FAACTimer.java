package FAACbeans;

import FAAClib.Debug;
import FAAClib.FAAC_Monitor;
import FAAClib.FAAC_Protocol;
import FAAClib.FAAC_Settings;
import FAAClib.FAAC_Timer;
import FAAClib.FAAC_frames;
import FAAClib.FilePathManager;
import FAAClib.timerReportDataSource;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import quickTable.AttributiveCellRenderer;
import quickTable.AttributiveCellTableModel;
import quickTable.CellAttribute;
import quickTable.CellSpan;
import quickTable.ColoredCell;
import quickTable.MultiSpanCellTable;

public class FAACTimer extends JPanel {
   private final Color selectionColor = Color.getHSBColor(200.0F, 40.0F, 60.0F);
   private final Color color01_closed = Color.RED;
   private final Color color02_openA = Color.GREEN;
   private final Color color03_openB = Color.ORANGE;
   private final Color color04_esclIn1 = Color.PINK;
   private final Color color05_esclIn1In2 = Color.MAGENTA;
   private final Color color06_esclRadio = Color.BLUE;
   private final Color color07_esclRadioDec = Color.GRAY;
   private final Color color08_esclRadioXf = Color.LIGHT_GRAY;
   private final Color color09_out1 = Color.YELLOW;
   private final Color color10_out2 = Color.CYAN;
   private JMenuItem delEventPopup;
   private JButton jBtDelEvent;
   private JButton jBtExportOnFile;
   private JButton jBtImportFromFile;
   private JButton jBtModEvent;
   private JButton jBtNewEvent;
   private JButton jBtPrintTimerOnPdf;
   private JButton jBtReadTimerFromBoard;
   private JButton jBtReset;
   private JButton jBtSetJollyIntervals;
   private JButton jBtWriteOnBoard;
   private JCheckBox jChbEnableTimerOnBoard;
   private JFileChooser jFileChooserOpenTmrFile;
   private JFileChooser jFileChooserPrintTimerPdf;
   private JFileChooser jFileChooserSaveTmrFile;
   private JLabel jLBModTimer;
   private JLayeredPane jLayeredPaneButtons;
   private JPanel jPSettings;
   private JPanel jPanelModify;
   private JPanel jPanelReadTimer;
   private JPanel jPanelWriteTimer;
   private JScrollPane jScrollPaneTimer;
   private MultiSpanCellTable jTtimer;
   private JMenuItem modEventPopup;
   private JMenuItem newEventPopup;
   private JPopupMenu popupMenuTimer;
   Color color;
   private boolean[] isTimerToBeWrittenOnBoard;
   private boolean[] isTimerToBeReadOnBoard;
   private boolean isProgTimerToBeReadFromBoard;
   private boolean isProgTimerToBeWrittenOnBoard;
   FAAC_Timer boardTimer;
   CellAttribute cellAtt;
   ResourceBundle bundle;
   FAAC_Timer.TimerEvent[][] timerEventMatrix_6rangesx8days;
   private boolean isBoardConnected;
   private Calendar newDate;
   FAAC_Monitor boardMonitor;
   private boolean isDateUpdateCmdRequested;
   FAAC_Settings boardSettings;
   private BackgroundWorkDialog backgroundWorkDialog;
   Font plainFont;
   Font boldFont;
   private ImageIcon ledIcoOff;
   private ImageIcon ledIcoOn;
   EventListenerList listenersFromBoard = new EventListenerList();
   private boolean isModTimer;
   private String timerImageForReport;
   private String boardModel;
   Frame parentForDlgs;

   public void updateLocale() {
      this.initComponentsForLocalization();
      this.jFileChooserSaveTmrFile.setDialogTitle(this.bundle.getString("exportTmrOnFile"));
      this.jFileChooserOpenTmrFile.setDialogTitle(this.bundle.getString("openTmrFile"));
      String monday = this.bundle.getString("monday");
      String tuesday = this.bundle.getString("tuesday");
      String wednesday = this.bundle.getString("wednesday");
      String thursday = this.bundle.getString("thursday");
      String friday = this.bundle.getString("friday");
      String saturday = this.bundle.getString("saturday");
      String sunday = this.bundle.getString("sunday");
      String jolly = this.bundle.getString("jolly");
      this.jTtimer.getColumnModel().getColumn(1).setHeaderValue(sunday);
      this.jTtimer.getColumnModel().getColumn(2).setHeaderValue(monday);
      this.jTtimer.getColumnModel().getColumn(3).setHeaderValue(tuesday);
      this.jTtimer.getColumnModel().getColumn(4).setHeaderValue(wednesday);
      this.jTtimer.getColumnModel().getColumn(5).setHeaderValue(thursday);
      this.jTtimer.getColumnModel().getColumn(6).setHeaderValue(friday);
      this.jTtimer.getColumnModel().getColumn(7).setHeaderValue(saturday);
      this.jTtimer.getColumnModel().getColumn(8).setHeaderValue(jolly);
      this.repaint();
   }

   public FAACTimer() {
      this.isTimerToBeWrittenOnBoard = new boolean[8];
      this.isTimerToBeReadOnBoard = new boolean[8];

      for (int i = 0; i < 8; i++) {
         this.isTimerToBeWrittenOnBoard[i] = false;
         this.isTimerToBeReadOnBoard[i] = false;
      }

      this.parentForDlgs = null;
      this.initComponents();
      this.timerImageForReport = "";
      this.jLBModTimer.setVisible(false);
      this.isModTimer = false;
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACTimer");
      String tmp = this.bundle.getString("alarmLedOffIco");
      ImageIcon iconOff = new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + tmp));
      this.ledIcoOff = new ImageIcon(iconOff.getImage());
      String tmpOn = this.bundle.getString("alarmLedOnIco");
      ImageIcon iconOn = new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + tmpOn));
      this.ledIcoOn = new ImageIcon(iconOn.getImage());
      this.jFileChooserSaveTmrFile.setCurrentDirectory(new File("./"));
      this.jFileChooserSaveTmrFile.setDialogType(1);
      this.jFileChooserSaveTmrFile.setDialogTitle(this.bundle.getString("exportTmrOnFile"));
      this.jFileChooserSaveTmrFile.setFileFilter(new FileNameExtensionFilter("tmr files", "tmr"));
      this.jFileChooserOpenTmrFile.setCurrentDirectory(new File("./"));
      this.jFileChooserOpenTmrFile.setDialogType(0);
      this.jFileChooserOpenTmrFile.setDialogTitle(this.bundle.getString("openTmrFile"));
      this.jFileChooserOpenTmrFile.setFileFilter(new FileNameExtensionFilter("tmr files", "tmr"));
      this.jFileChooserPrintTimerPdf.setCurrentDirectory(new File("./"));
      this.jFileChooserPrintTimerPdf.setDialogType(1);
      this.jFileChooserPrintTimerPdf.setDialogTitle(this.bundle.getString("exportTimerPdf"));
      this.jFileChooserPrintTimerPdf.setFileFilter(new FileNameExtensionFilter("pdf files", "pdf"));
      this.isBoardConnected = false;
      this.jTtimer.setName("jTtimer");
      String monday = this.bundle.getString("monday");
      String tuesday = this.bundle.getString("tuesday");
      String wednesday = this.bundle.getString("wednesday");
      String thursday = this.bundle.getString("thursday");
      String friday = this.bundle.getString("friday");
      String saturday = this.bundle.getString("saturday");
      String sunday = this.bundle.getString("sunday");
      String jolly = this.bundle.getString("jolly");
      AttributiveCellTableModel ml = new AttributiveCellTableModel(
         new Object[][]{
            {"0:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"1:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"2:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"3:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"4:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"5:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"6:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"7:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"8:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"9:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"10:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"11:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"12:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"13:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"14:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"15:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"16:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"17:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"18:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"19:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"20:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"21:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"22:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null},
            {"23:00", null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null}
         },
         new String[]{"", sunday, monday, tuesday, wednesday, thursday, friday, saturday, jolly}
      ) {
         Class[] types = new Class[]{
            Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
         };

         @Override
         public Class getColumnClass(int c) {
            return c != 0 ? FAAC_Timer.TimerEvent.class : this.types[c];
         }
      };
      this.cellAtt = ml.getCellAttribute();
      this.jTtimer.setModel(ml);
      this.jTtimer.setCellSelectionEnabled(true);
      this.jTtimer.setSelectionMode(1);
      this.jTtimer.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
      this.jTtimer.setRowMargin(1);
      this.jTtimer.getColumnModel().setColumnMargin(1);
      this.jTtimer.setSelectionBackground(this.selectionColor);
      DefaultListSelectionModel model = (DefaultListSelectionModel)this.jTtimer.getColumnModel().getSelectionModel();
      model.setSelectionMode(0);
      int[] cols = new int[]{0};

      for (int iRows = 0; iRows < 47; iRows += 2) {
         int[] rows = new int[]{iRows, iRows + 1};
         ((CellSpan)this.cellAtt).combine(rows, cols);
         this.jTtimer.revalidate();
         this.jTtimer.repaint();
      }

      this.jTtimer.revalidate();
      this.jTtimer.repaint();
      this.isProgTimerToBeReadFromBoard = false;
      this.isProgTimerToBeWrittenOnBoard = false;
   }

   public void setParentForDialogs(Frame parent) {
      this.parentForDlgs = parent;
   }

   public void setTimerInfo(
      FAAC_Timer boardTimer,
      FAAC_Monitor boardMonitor,
      FAAC_Settings boardSettings,
      boolean isBoardConnected,
      BackgroundWorkDialog backgroundWorkDialog,
      String boardModel
   ) {
      this.boardTimer = boardTimer;
      this.isBoardConnected = isBoardConnected;
      this.boardMonitor = boardMonitor;
      this.boardSettings = boardSettings;
      this.backgroundWorkDialog = backgroundWorkDialog;
      this.boardModel = boardModel;
      this.setIsDateUpdateCmdRequested(false);
      this.setNewDate(GregorianCalendar.getInstance());
      this.getNewDate().clear();
      int iMinutes = boardMonitor.getMinutes();
      int iHour = boardMonitor.getHour();
      int iDay = boardMonitor.getDay();
      int iMonth = boardMonitor.getMonth();
      int iYear = boardMonitor.getYear();
      this.getNewDate().set(12, iMinutes);
      this.getNewDate().set(11, iHour);
      this.getNewDate().set(5, iDay);
      this.getNewDate().set(2, iMonth - 1);
      this.getNewDate().set(1, 2000 + iYear);
   }

   public void DisableBoardCommunication() {
      this.jBtReadTimerFromBoard.setEnabled(false);
      this.jBtWriteOnBoard.setEnabled(false);
   }

   public void EnableBoardCommunication() {
      this.jBtReadTimerFromBoard.setEnabled(true);
      this.jBtWriteOnBoard.setEnabled(true);
   }

   private void updateTimerEventsDescriptions() {
      for (int iCol = 0; iCol < 8; iCol++) {
         for (int iRow = 0; iRow < 6; iRow++) {
            int value = this.timerEventMatrix_6rangesx8days[iRow][iCol].getValueFunction();
            switch (value) {
               case 0:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr00_noFunction"));
                  break;
               case 1:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr01_closed"));
                  break;
               case 2:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr02_openA"));
                  break;
               case 3:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr03_openB"));
                  break;
               case 4:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr04_esclIn1"));
                  break;
               case 5:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr05_esclIn1In2"));
                  break;
               case 6:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr06_esclRadio"));
                  break;
               case 7:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr07_esclRadioDec"));
                  break;
               case 8:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr08_esclRadioXf"));
                  break;
               case 9:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr09_out1"));
                  break;
               case 10:
                  this.timerEventMatrix_6rangesx8days[iRow][iCol].setDescription(this.bundle.getString("timerDescr10_out2"));
            }
         }
      }
   }

   private FAAC_Timer.TimerEvent fromRowsIndexesToRoundedRange(int[] selectedRows, int col) {
      FAAC_Timer.TimerEvent eventRes = new FAAC_Timer.TimerEvent(col);
      int startRowIndex = selectedRows[0];
      int endRowIndex = selectedRows[selectedRows.length - 1];
      Calendar startDate = GregorianCalendar.getInstance();
      startDate.clear();
      startDate.add(12, startRowIndex * 30);
      eventRes.setBeginDateRound(startDate);
      eventRes.setBeginHourRound(startDate.get(11));
      eventRes.setBeginMinuteRound(startDate.get(12));
      eventRes.setBeginDateExact(startDate);
      eventRes.setBeginHourExact(startDate.get(11));
      eventRes.setBeginMinuteExact(startDate.get(12));
      Calendar endDate = GregorianCalendar.getInstance();
      endDate.clear();
      endDate.add(12, (endRowIndex + 1) * 30 - 1);
      eventRes.setEndDateRound(endDate);
      eventRes.setEndHourRound(endDate.get(11));
      eventRes.setEndMinuteRound(endDate.get(12));
      eventRes.setEndDateExact(endDate);
      eventRes.setEndHourExact(endDate.get(11));
      eventRes.setEndMinuteExact(endDate.get(12));
      return eventRes;
   }

   private int[] fromRoundedRangeToRowsIndexes(FAAC_Timer.TimerEvent timerEventToDraw) {
      int beginHour = timerEventToDraw.getBeginHourRound();
      int beginMin = timerEventToDraw.getBeginMinuteRound();
      int indexRowBegin = (beginHour * 2 * 30 + beginMin) / 30;
      int endHour = timerEventToDraw.getEndHourRound();
      int endMinute = timerEventToDraw.getEndMinuteRound();
      int indexRowEnd = (endHour * 2 * 30 + endMinute + 1) / 30;
      int rowsSize;
      if (indexRowEnd == 0) {
         int var11 = 47;
         rowsSize = var11 - indexRowBegin + 1;
      } else {
         rowsSize = indexRowEnd - indexRowBegin;
      }

      int[] rows = new int[rowsSize];

      for (int iGrp = 0; iGrp < rowsSize; iGrp++) {
         rows[iGrp] = indexRowBegin + iGrp;
      }

      return rows;
   }

   private Color getColorForEvent(FAAC_Timer.TimerEvent event) {
      Color colorTmp = Color.WHITE;
      int iEventType = event.getValueFunction();
      switch (iEventType) {
         case 0:
            colorTmp = Color.WHITE;
            break;
         case 1:
            colorTmp = this.color01_closed;
            break;
         case 2:
            colorTmp = this.color02_openA;
            break;
         case 3:
            colorTmp = this.color03_openB;
            break;
         case 4:
            colorTmp = this.color04_esclIn1;
            break;
         case 5:
            colorTmp = this.color05_esclIn1In2;
            break;
         case 6:
            colorTmp = this.color06_esclRadio;
            break;
         case 7:
            colorTmp = this.color07_esclRadioDec;
            break;
         case 8:
            colorTmp = this.color08_esclRadioXf;
            break;
         case 9:
            colorTmp = this.color09_out1;
            break;
         case 10:
            colorTmp = this.color10_out2;
      }

      return colorTmp;
   }

   private void drawEvent(FAAC_Timer.TimerEvent event, int[] rows, int[] cols) {
      this.color = this.getColorForEvent(event);
      int iRowsToMerge = rows.length;
      LinkedList<Integer> rowList = new LinkedList<>();

      for (int i = 0; i < iRowsToMerge; i++) {
         int[] iSpanArray = ((CellSpan)this.cellAtt).getSpan(rows[i], cols[0]);
         if (iSpanArray[0] == 1) {
            FAAC_Timer.TimerEvent alreadyEvent = (FAAC_Timer.TimerEvent)this.jTtimer.getValueAt(rows[i], cols[0]);
            if (alreadyEvent == null) {
               rowList.add(rows[i]);
            }
         }
      }

      int[] updateRows = new int[rowList.size()];
      if (rowList.size() > 0 && rowList.size() != iRowsToMerge) {
         for (int ix = 0; ix < rowList.size(); ix++) {
            updateRows[ix] = rowList.get(ix);
         }

         ((CellSpan)this.cellAtt).combine(updateRows, cols);
         this.changeColor(updateRows, cols, (ColoredCell)this.cellAtt, this.color);
      } else {
         ((CellSpan)this.cellAtt).combine(rows, cols);
         this.changeColor(rows, cols, (ColoredCell)this.cellAtt, this.color);
      }

      this.jTtimer.revalidate();
      this.jTtimer.repaint();
      if (event.getValueFunction() != 0) {
         if (rowList.size() > 0 && rowList.size() != iRowsToMerge) {
            this.jTtimer.setValueAt(event, updateRows[0], cols[0]);
         } else {
            this.jTtimer.setValueAt(event, rows[0], cols[0]);
         }
      }

      this.jTtimer.clearSelection();
      this.jTtimer.revalidate();
      this.jTtimer.repaint();
   }

   public void initDateInPanelTimer() {
      this.jChbEnableTimerOnBoard.setSelected(this.boardTimer.getProgTimer().get00_TimerEnabled());
   }

   public void initPanelTimer() {
      this.jChbEnableTimerOnBoard.setSelected(this.boardTimer.getProgTimer().get00_TimerEnabled());
      this.timerEventMatrix_6rangesx8days = this.boardTimer.getTimerEventMatrix_6rangesx8days();
      this.updateTimerEventsDescriptions();
      int[] rows = new int[48];
      int[] cols = new int[8];

      for (int iRow = 0; iRow < rows.length; iRow++) {
         for (int iCol = 0; iCol < cols.length; iCol++) {
            rows[iRow] = iRow;
            cols[iCol] = iCol + 1;
            ((CellSpan)this.cellAtt).split(iRow, iCol + 1);
            this.jTtimer.setValueAt(null, rows[iRow], cols[iCol]);
         }
      }

      this.changeColor(rows, cols, (ColoredCell)this.cellAtt, this.jTtimer.getBackground());

      for (int iCol = 0; iCol < 8; iCol++) {
         for (int iRow = 0; iRow < 6; iRow++) {
            FAAC_Timer.TimerEvent eventTmp = this.timerEventMatrix_6rangesx8days[iRow][iCol];
            if (eventTmp.getValueFunction() != 0) {
               int[] colsEventToMerge = new int[]{iCol + 1};
               int[] rowsEventToMerge = this.fromRoundedRangeToRowsIndexes(eventTmp);
               this.drawEvent(eventTmp, rowsEventToMerge, colsEventToMerge);
            }
         }
      }

      this.jTtimer.clearSelection();
      this.jTtimer.revalidate();
      this.jTtimer.repaint();
   }

   private void changeColor(int[] rows, int[] columns, ColoredCell cellAtt, Color color) {
      if (rows != null && columns != null) {
         if (rows.length >= 1 && columns.length >= 1) {
            if (color != null) {
               cellAtt.setBackground(color, rows, columns);
            }

            this.jTtimer.clearSelection();
            this.jTtimer.revalidate();
            this.jTtimer.repaint();
         }
      }
   }

   private void initComponentsForLocalization() {
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACTimer");
      this.newEventPopup.setText(this.bundle.getString("FAACTimer.newEventPopup.text"));
      this.modEventPopup.setText(this.bundle.getString("FAACTimer.modEventPopup.text"));
      this.delEventPopup.setText(this.bundle.getString("FAACTimer.delEventPopup.text"));
      this.jFileChooserSaveTmrFile.setApproveButtonToolTipText(this.bundle.getString("FAACTimer.jFileChooserSaveTmrFile.approveButtonToolTipText"));
      this.jFileChooserSaveTmrFile.setDialogTitle(this.bundle.getString("FAACTimer.jFileChooserSaveTmrFile.dialogTitle"));
      this.jFileChooserOpenTmrFile.setApproveButtonText(this.bundle.getString("FAACTimer.jFileChooserOpenTmrFile.approveButtonText"));
      this.jFileChooserOpenTmrFile.setApproveButtonToolTipText(this.bundle.getString("FAACTimer.jFileChooserOpenTmrFile.approveButtonToolTipText"));
      this.jFileChooserOpenTmrFile.setDialogTitle(this.bundle.getString("FAACTimer.jFileChooserOpenTmrFile.dialogTitle"));
      this.jPSettings.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACTimer.jPSettings.border.title")));
      this.jBtSetJollyIntervals.setText(this.bundle.getString("FAACTimer.jBtSetJollyIntervals.text"));
      this.jChbEnableTimerOnBoard.setText(this.bundle.getString("FAACTimer.jChbEnableTimerOnBoard.text"));
      this.jPanelWriteTimer.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACTimer.jPanelWriteTimer.border.title")));
      this.jBtWriteOnBoard.setText(this.bundle.getString("FAACTimer.jBtWriteOnBoard.text"));
      this.jBtExportOnFile.setText(this.bundle.getString("FAACTimer.jBtExportOnFile.text"));
      this.jPanelReadTimer.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACTimer.jPanelReadTimer.border.title")));
      this.jBtReadTimerFromBoard.setText(this.bundle.getString("FAACTimer.jBtReadTimerFromBoard.text"));
      this.jBtImportFromFile.setText(this.bundle.getString("FAACTimer.jBtImportFromFile.text"));
      this.jBtReset.setText(this.bundle.getString("FAACTimer.jBtReset.text"));
      this.jPanelModify.setBorder(BorderFactory.createTitledBorder(this.bundle.getString("FAACTimer.jPanelModify.border.title")));
      this.jBtNewEvent.setText(this.bundle.getString("FAACTimer.jBtNewEvent.text"));
      this.jBtModEvent.setText(this.bundle.getString("FAACTimer.jBtModEvent.text"));
      this.jBtDelEvent.setText(this.bundle.getString("FAACTimer.jBtDelEvent.text"));
      this.jLBModTimer.setText(this.bundle.getString("FAACTimer.jLBModTimer.text"));
      this.jBtPrintTimerOnPdf.setText(this.bundle.getString("FAACTimer.jBtPrintTimerOnPdf.text"));
   }

   private void initComponents() {
      this.popupMenuTimer = new JPopupMenu();
      this.newEventPopup = new JMenuItem();
      this.modEventPopup = new JMenuItem();
      this.delEventPopup = new JMenuItem();
      this.jFileChooserSaveTmrFile = new JFileChooser();
      this.jFileChooserOpenTmrFile = new JFileChooser();
      this.jFileChooserPrintTimerPdf = new JFileChooser();
      this.jScrollPaneTimer = new JScrollPane();
      this.jTtimer = new MultiSpanCellTable();
      this.jLayeredPaneButtons = new JLayeredPane();
      this.jPSettings = new JPanel();
      this.jBtSetJollyIntervals = new JButton();
      this.jChbEnableTimerOnBoard = new JCheckBox();
      this.jPanelWriteTimer = new JPanel();
      this.jBtWriteOnBoard = new JButton();
      this.jBtExportOnFile = new JButton();
      this.jPanelReadTimer = new JPanel();
      this.jBtReadTimerFromBoard = new JButton();
      this.jBtImportFromFile = new JButton();
      this.jBtReset = new JButton();
      this.jPanelModify = new JPanel();
      this.jBtNewEvent = new JButton();
      this.jBtModEvent = new JButton();
      this.jBtDelEvent = new JButton();
      this.jLBModTimer = new JLabel();
      this.jBtPrintTimerOnPdf = new JButton();
      this.popupMenuTimer.setLightWeightPopupEnabled(false);
      this.popupMenuTimer.setName("popupMenuTimer");
      this.popupMenuTimer.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent evt) {
            FAACTimer.this.popupMenuTimerMouseClicked(evt);
         }
      });
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACTimer");
      this.newEventPopup.setText(bundle.getString("FAACTimer.newEventPopup.text"));
      this.newEventPopup.setName("newEventPopup");
      this.newEventPopup.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            FAACTimer.this.newEventPopupActionPerformed(evt);
         }
      });
      this.popupMenuTimer.add(this.newEventPopup);
      this.modEventPopup.setText(bundle.getString("FAACTimer.modEventPopup.text"));
      this.modEventPopup.setName("modEventPopup");
      this.modEventPopup.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            FAACTimer.this.modEventPopupActionPerformed(evt);
         }
      });
      this.popupMenuTimer.add(this.modEventPopup);
      this.delEventPopup.setText(bundle.getString("FAACTimer.delEventPopup.text"));
      this.delEventPopup.setName("delEventPopup");
      this.delEventPopup.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            FAACTimer.this.delEventPopupActionPerformed(evt);
         }
      });
      this.popupMenuTimer.add(this.delEventPopup);
      this.jFileChooserSaveTmrFile.setApproveButtonToolTipText(bundle.getString("FAACTimer.jFileChooserSaveTmrFile.approveButtonToolTipText"));
      this.jFileChooserSaveTmrFile.setDialogTitle(bundle.getString("FAACTimer.jFileChooserSaveTmrFile.dialogTitle"));
      this.jFileChooserSaveTmrFile.setDialogType(1);
      this.jFileChooserSaveTmrFile.setName("jFileChooserSaveTmrFile");
      this.jFileChooserOpenTmrFile.setApproveButtonText(bundle.getString("FAACTimer.jFileChooserOpenTmrFile.approveButtonText"));
      this.jFileChooserOpenTmrFile.setApproveButtonToolTipText(bundle.getString("FAACTimer.jFileChooserOpenTmrFile.approveButtonToolTipText"));
      this.jFileChooserOpenTmrFile.setDialogTitle(bundle.getString("FAACTimer.jFileChooserOpenTmrFile.dialogTitle"));
      this.jFileChooserOpenTmrFile.setName("jFileChooserOpenTmrFile");
      this.jFileChooserPrintTimerPdf.setName("jFileChooserPrintTimerPdf");
      this.setName("FAACTimer");
      this.setPreferredSize(new Dimension(755, 543));
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent evt) {
            FAACTimer.this.formComponentResized(evt);
         }
      });
      this.jScrollPaneTimer.setName("jScrollPaneTimer");
      this.jScrollPaneTimer.setPreferredSize(new Dimension(575, 530));
      this.jTtimer.setFont(new Font("Tahoma", 0, 10));
      this.jTtimer
         .setModel(
            new DefaultTableModel(
               new Object[][]{
                  {"0:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"1:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"2:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"3:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"4:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"5:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"6:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"7:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"8:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"9:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"10:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"11:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"12:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"13:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"14:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"15:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"16:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"17:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"18:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"19:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"20:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"21:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"22:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null},
                  {"23:00", null, null, null, null, null, null, null, null},
                  {null, null, null, null, null, null, null, null, null}
               },
               new String[]{"", "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica", "Jolly"}
            ) {
               boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};

               @Override
               public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return this.canEdit[columnIndex];
               }
            }
         );
      this.jTtimer.setCellSelectionEnabled(true);
      this.jTtimer.setComponentPopupMenu(this.popupMenuTimer);
      this.jTtimer.setFillsViewportHeight(true);
      this.jTtimer.setGridColor(new Color(153, 153, 153));
      this.jTtimer.setMaximumSize(new Dimension(32767, 32767));
      this.jTtimer.setName("jTtimer");
      this.jTtimer.setPreferredSize(new Dimension(569, 500));
      this.jTtimer.setRowHeight(10);
      this.jTtimer.getTableHeader().setReorderingAllowed(false);
      this.jTtimer.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent evt) {
            FAACTimer.this.jTtimerKeyPressed(evt);
         }
      });
      this.jScrollPaneTimer.setViewportView(this.jTtimer);
      this.jTtimer.getColumnModel().getSelectionModel().setSelectionMode(1);
      if (this.jTtimer.getColumnModel().getColumnCount() > 0) {
         this.jTtimer.getColumnModel().getColumn(0).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title0"));
         this.jTtimer.getColumnModel().getColumn(1).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title1"));
         this.jTtimer.getColumnModel().getColumn(2).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title2"));
         this.jTtimer.getColumnModel().getColumn(3).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title3"));
         this.jTtimer.getColumnModel().getColumn(4).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title4"));
         this.jTtimer.getColumnModel().getColumn(5).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title5"));
         this.jTtimer.getColumnModel().getColumn(6).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title6"));
         this.jTtimer.getColumnModel().getColumn(7).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title7"));
         this.jTtimer.getColumnModel().getColumn(8).setResizable(false);
         this.jTtimer.getColumnModel().getColumn(8).setHeaderValue(bundle.getString("FAACTimer.jTtimer.columnModel.title8"));
      }

      this.jTtimer.getAccessibleContext().setAccessibleName(bundle.getString("FAACTimer.jTtimer.AccessibleContext.accessibleName"));
      this.jLayeredPaneButtons.setName("jLayeredPaneButtons");
      this.jPSettings.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimer.jPSettings.border.title")));
      this.jPSettings.setName("jPSettings");
      this.jBtSetJollyIntervals.setText(bundle.getString("FAACTimer.jBtSetJollyIntervals.text"));
      this.jBtSetJollyIntervals.setName("jBtSetJollyIntervals");
      this.jBtSetJollyIntervals.setPreferredSize(new Dimension(174, 23));
      this.jBtSetJollyIntervals.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtSetJollyIntervalsMousePressed(evt);
         }
      });
      this.jChbEnableTimerOnBoard.setText(bundle.getString("FAACTimer.jChbEnableTimerOnBoard.text"));
      this.jChbEnableTimerOnBoard.setName("jChbEnableTimerOnBoard");
      this.jChbEnableTimerOnBoard.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jChbEnableTimerOnBoardMousePressed(evt);
         }
      });
      GroupLayout jPSettingsLayout = new GroupLayout(this.jPSettings);
      this.jPSettings.setLayout(jPSettingsLayout);
      jPSettingsLayout.setHorizontalGroup(
         jPSettingsLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPSettingsLayout.createSequentialGroup().addContainerGap().addComponent(this.jChbEnableTimerOnBoard).addContainerGap(60, 32767))
            .addComponent(this.jBtSetJollyIntervals, -2, 0, 32767)
      );
      jPSettingsLayout.setVerticalGroup(
         jPSettingsLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPSettingsLayout.createSequentialGroup()
                  .addGap(0, 0, 0)
                  .addComponent(this.jChbEnableTimerOnBoard)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtSetJollyIntervals, -2, 31, -2)
                  .addContainerGap()
            )
      );
      this.jLayeredPaneButtons.add(this.jPSettings);
      this.jPSettings.setBounds(10, 0, 175, 90);
      this.jPanelWriteTimer.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimer.jPanelWriteTimer.border.title")));
      this.jPanelWriteTimer.setName("jPanelWriteTimer");
      this.jBtWriteOnBoard.setText(bundle.getString("FAACTimer.jBtWriteOnBoard.text"));
      this.jBtWriteOnBoard.setName("jBtWriteOnBoard");
      this.jBtWriteOnBoard.setPreferredSize(new Dimension(174, 23));
      this.jBtWriteOnBoard.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtWriteOnBoardMousePressed(evt);
         }
      });
      this.jBtExportOnFile.setText(bundle.getString("FAACTimer.jBtExportOnFile.text"));
      this.jBtExportOnFile.setName("jBtExportOnFile");
      this.jBtExportOnFile.setPreferredSize(new Dimension(174, 23));
      this.jBtExportOnFile.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtExportOnFileMousePressed(evt);
         }
      });
      GroupLayout jPanelWriteTimerLayout = new GroupLayout(this.jPanelWriteTimer);
      this.jPanelWriteTimer.setLayout(jPanelWriteTimerLayout);
      jPanelWriteTimerLayout.setHorizontalGroup(
         jPanelWriteTimerLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(this.jBtExportOnFile, -1, 159, 32767)
            .addComponent(this.jBtWriteOnBoard, -2, 0, 32767)
      );
      jPanelWriteTimerLayout.setVerticalGroup(
         jPanelWriteTimerLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelWriteTimerLayout.createSequentialGroup()
                  .addComponent(this.jBtExportOnFile, -2, 31, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtWriteOnBoard, -2, 31, -2)
                  .addContainerGap(-1, 32767)
            )
      );
      this.jLayeredPaneButtons.add(this.jPanelWriteTimer);
      this.jPanelWriteTimer.setBounds(10, 220, 175, 100);
      this.jPanelReadTimer.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimer.jPanelReadTimer.border.title")));
      this.jPanelReadTimer.setName("jPanelReadTimer");
      this.jBtReadTimerFromBoard.setText(bundle.getString("FAACTimer.jBtReadTimerFromBoard.text"));
      this.jBtReadTimerFromBoard.setName("jBtReadTimerFromBoard");
      this.jBtReadTimerFromBoard.setPreferredSize(new Dimension(174, 23));
      this.jBtReadTimerFromBoard.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtReadTimerFromBoardMousePressed(evt);
         }
      });
      this.jBtImportFromFile.setText(bundle.getString("FAACTimer.jBtImportFromFile.text"));
      this.jBtImportFromFile.setName("jBtImportFromFile");
      this.jBtImportFromFile.setPreferredSize(new Dimension(174, 23));
      this.jBtImportFromFile.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtImportFromFileMousePressed(evt);
         }
      });
      this.jBtReset.setText(bundle.getString("FAACTimer.jBtReset.text"));
      this.jBtReset.setName("jBtReset");
      this.jBtReset.setPreferredSize(new Dimension(174, 23));
      this.jBtReset.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtResetMousePressed(evt);
         }
      });
      GroupLayout jPanelReadTimerLayout = new GroupLayout(this.jPanelReadTimer);
      this.jPanelReadTimer.setLayout(jPanelReadTimerLayout);
      jPanelReadTimerLayout.setHorizontalGroup(
         jPanelReadTimerLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelReadTimerLayout.createParallelGroup(Alignment.CENTER)
                  .addComponent(this.jBtImportFromFile, -2, 162, -2)
                  .addComponent(this.jBtReset, -2, 0, 32767)
                  .addComponent(this.jBtReadTimerFromBoard, -2, 0, 32767)
            )
      );
      jPanelReadTimerLayout.setVerticalGroup(
         jPanelReadTimerLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelReadTimerLayout.createSequentialGroup()
                  .addComponent(this.jBtReset, -2, 29, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtImportFromFile, -2, 29, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtReadTimerFromBoard, -2, 29, -2)
                  .addContainerGap(-1, 32767)
            )
      );
      this.jLayeredPaneButtons.add(this.jPanelReadTimer);
      this.jPanelReadTimer.setBounds(10, 90, 175, 130);
      this.jPanelModify.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimer.jPanelModify.border.title")));
      this.jPanelModify.setName("jPanelModify");
      this.jBtNewEvent.setText(bundle.getString("FAACTimer.jBtNewEvent.text"));
      this.jBtNewEvent.setName("jBtNewEvent");
      this.jBtNewEvent.setPreferredSize(new Dimension(174, 23));
      this.jBtNewEvent.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtNewEventMousePressed(evt);
         }
      });
      this.jBtModEvent.setText(bundle.getString("FAACTimer.jBtModEvent.text"));
      this.jBtModEvent.setName("jBtModEvent");
      this.jBtModEvent.setPreferredSize(new Dimension(174, 23));
      this.jBtModEvent.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtModEventMousePressed(evt);
         }
      });
      this.jBtDelEvent.setText(bundle.getString("FAACTimer.jBtDelEvent.text"));
      this.jBtDelEvent.setName("jBtDelEvent");
      this.jBtDelEvent.setPreferredSize(new Dimension(174, 23));
      this.jBtDelEvent.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtDelEventMousePressed(evt);
         }
      });
      GroupLayout jPanelModifyLayout = new GroupLayout(this.jPanelModify);
      this.jPanelModify.setLayout(jPanelModifyLayout);
      jPanelModifyLayout.setHorizontalGroup(
         jPanelModifyLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(this.jBtNewEvent, -1, 159, 32767)
            .addComponent(this.jBtModEvent, -2, 0, 32767)
            .addComponent(this.jBtDelEvent, -2, 0, 32767)
      );
      jPanelModifyLayout.setVerticalGroup(
         jPanelModifyLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelModifyLayout.createSequentialGroup()
                  .addComponent(this.jBtNewEvent, -2, 29, -2)
                  .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                  .addComponent(this.jBtModEvent, -2, 31, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtDelEvent, -2, 29, -2)
                  .addGap(16, 16, 16)
            )
      );
      this.jLayeredPaneButtons.add(this.jPanelModify);
      this.jPanelModify.setBounds(10, 327, 175, 130);
      this.jLBModTimer.setFont(new Font("Tahoma", 1, 11));
      this.jLBModTimer.setForeground(new Color(255, 51, 0));
      this.jLBModTimer.setText(bundle.getString("FAACTimer.jLBModTimer.text"));
      this.jLBModTimer.setName("jLBModTimer");
      this.jLayeredPaneButtons.add(this.jLBModTimer);
      this.jLBModTimer.setBounds(20, 490, 160, 40);
      this.jLayeredPaneButtons.setLayer(this.jLBModTimer, JLayeredPane.PALETTE_LAYER);
      this.jBtPrintTimerOnPdf.setText(bundle.getString("FAACTimer.jBtPrintTimerOnPdf.text"));
      this.jBtPrintTimerOnPdf.setName("jBtPrintTimerOnPdf");
      this.jBtPrintTimerOnPdf.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimer.this.jBtPrintTimerOnPdfMousePressed(evt);
         }
      });
      this.jLayeredPaneButtons.add(this.jBtPrintTimerOnPdf);
      this.jBtPrintTimerOnPdf.setBounds(20, 460, 159, 30);
      this.jLayeredPaneButtons.setLayer(this.jBtPrintTimerOnPdf, JLayeredPane.PALETTE_LAYER);
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               layout.createSequentialGroup()
                  .addComponent(this.jScrollPaneTimer, -1, 563, 32767)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jLayeredPaneButtons, -2, 186, -2)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addComponent(this.jLayeredPaneButtons, -1, 543, 32767)
            .addComponent(this.jScrollPaneTimer, -1, 543, 32767)
      );
   }

   private void popupMenuTimerMouseClicked(MouseEvent evt) {
      FAACTimerDetail detail = new FAACTimerDetail(this.parentForDlgs, true, this.boardTimer, false, null);
      detail.setLocationRelativeTo(this.parentForDlgs);
      detail.setVisible(true);
   }

   private void formComponentResized(ComponentEvent evt) {
      Dimension dim = this.jTtimer.getSize();
      this.jTtimer.setRowHeight(dim.height / 50);
   }

   private void prepareBackgroundWorkTmrReading() {
      int maxValueProgressBar = 9;
      this.backgroundWorkDialog.setMaximumLevel(maxValueProgressBar);
      this.backgroundWorkDialog.setLocationRelativeTo(this);
      this.backgroundWorkDialog.setTitle(this.bundle.getString("readTmrFromBoard"));
      this.backgroundWorkDialog.setMsg(this.bundle.getString("readTmrFromBoardOngoing"));
      this.backgroundWorkDialog.reset();
      this.backgroundWorkDialog.setOperationOngoing(true);
   }

   public void readTimerFromBoardMethod() {
      this.prepareBackgroundWorkTmrReading();

      for (int i = 0; i < 8; i++) {
         this.isTimerToBeReadOnBoard[i] = true;
      }

      this.isProgTimerToBeReadFromBoard = true;
      this.backgroundWorkDialog.setVisible(true);
   }

   private void notifyToActionListeners(Object[] listeners) {
      int numListeners = listeners.length;

      for (int i = 0; i < numListeners; i += 2) {
         if (listeners[i] == ActionListener.class) {
            ((ActionListener)listeners[i + 1]).actionPerformed(null);
         }
      }
   }

   public void writeTimerOnBoardMethod() {
      this.prepareBackgroundWorkTmrWriting();

      for (int i = 0; i < 8; i++) {
         this.isTimerToBeWrittenOnBoard[i] = true;
      }

      this.isProgTimerToBeWrittenOnBoard = true;
      this.backgroundWorkDialog.setVisible(true);
   }

   private void prepareBackgroundWorkTmrWriting() {
      int maxValueProgressBar = 9;
      this.backgroundWorkDialog.setMaximumLevel(maxValueProgressBar);
      this.backgroundWorkDialog.setLocationRelativeTo(this);
      this.backgroundWorkDialog.setTitle(this.bundle.getString("writeTmrOnBoard"));
      this.backgroundWorkDialog.setMsg(this.bundle.getString("writeTmrOnBoardOngoing"));
      this.backgroundWorkDialog.reset();
      this.backgroundWorkDialog.setOperationOngoing(true);
   }

   private void newEventPopupActionPerformed(ActionEvent evt) {
      int[] cols = this.jTtimer.getSelectedColumns();
      int[] rows = this.jTtimer.getSelectedRows();
      FAACTimerDetail detail;
      if (cols != null && rows != null && cols.length != 0 && rows.length != 0) {
         new FAAC_Timer.TimerEvent(cols[0] - 1);
         FAAC_Timer.TimerEvent newEvent = this.fromRowsIndexesToRoundedRange(rows, cols[0] - 1);
         detail = new FAACTimerDetail(this.parentForDlgs, true, this.boardTimer, false, newEvent);
         detail.setLocationRelativeTo(this.parentForDlgs);
         detail.setVisible(true);
      } else {
         detail = new FAACTimerDetail(this.parentForDlgs, true, this.boardTimer, false, null);
         detail.setLocationRelativeTo(this.parentForDlgs);
         detail.setVisible(true);
      }

      if (detail.isIsOk()) {
         this.jLBModTimer.setVisible(true);
         this.isModTimer = true;
      }

      this.initPanelTimer();
   }

   private void modEventPopupActionPerformed(ActionEvent evt) {
      int[] cols = this.jTtimer.getSelectedColumns();
      int[] rows = this.jTtimer.getSelectedRows();
      if (cols != null && rows != null && cols.length != 0 && rows.length != 0) {
         FAAC_Timer.TimerEvent eventToBeMod = (FAAC_Timer.TimerEvent)this.jTtimer.getValueAt(rows[0], cols[0]);
         if (eventToBeMod == null) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("selectedTheEventToModify_message"), this.bundle.getString("Attention"), 2);
         } else {
            FAACTimerDetail detail = new FAACTimerDetail(this.parentForDlgs, true, this.boardTimer, true, eventToBeMod);
            detail.setLocationRelativeTo(this.parentForDlgs);
            detail.setVisible(true);
            if (detail.isIsOk()) {
               this.jLBModTimer.setVisible(true);
               this.isModTimer = true;
            }

            ((CellSpan)this.cellAtt).split(rows[0], cols[0]);
            this.jTtimer.setValueAt(null, rows[0], cols[0]);
            this.initPanelTimer();
         }
      } else {
         JOptionPane.showMessageDialog(this, this.bundle.getString("noEventSelected_message"), this.bundle.getString("Attention"), 2);
      }
   }

   private void deleteSelectedEvent() {
      int[] cols = this.jTtimer.getSelectedColumns();
      int[] rows = this.jTtimer.getSelectedRows();
      if (cols != null && rows != null && cols.length != 0 && rows.length != 0) {
         FAAC_Timer.TimerEvent eventToBeDel = (FAAC_Timer.TimerEvent)this.jTtimer.getValueAt(rows[0], cols[0]);
         if (eventToBeDel == null) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("selectedTheEventToDelete_message"), this.bundle.getString("Attention"), 2);
         } else {
            eventToBeDel.setValueFunction(0);
            ((CellSpan)this.cellAtt).split(rows[0], cols[0]);
            this.jLBModTimer.setVisible(true);
            this.isModTimer = true;
            this.initPanelTimer();
         }
      } else {
         JOptionPane.showMessageDialog(this, this.bundle.getString("noEventSelected_message"), this.bundle.getString("Attention"), 2);
      }
   }

   private void delEventPopupActionPerformed(ActionEvent evt) {
      this.deleteSelectedEvent();
   }

   private void generateTmrFile(File fileName, String file_password) {
      byte[] fileBytes = FAAC_frames.getBytesForTmrFile(file_password, this.boardTimer, this.boardMonitor);

      try {
         FileOutputStream fos = new FileOutputStream(fileName);
         fos.write(fileBytes);
         fos.close();
         JOptionPane.showMessageDialog(this, this.bundle.getString("exportTmrFileOkMsg"), this.bundle.getString("Information"), 1);
         this.jLBModTimer.setVisible(false);
         this.isModTimer = false;
      } catch (IOException var6) {
         Logger.getLogger(FAACTimer.class.getName()).log(Level.SEVERE, null, var6);
      }
   }

   public void exportTimerOnFile() {
      PasswordInsertDialogFile pswFile = new PasswordInsertDialogFile(null, true);
      String file_password = pswFile.getPassword(this.boardSettings.getPassword(), this.isBoardConnected);
      if (file_password != "") {
         String suggestedName = FAAC_frames.FILENAME_TMR_E145_noExt;
         if (this.boardModel.compareToIgnoreCase("E124") == 0) {
            suggestedName = FAAC_frames.FILENAME_TMR_E124_noExt;
         }

         this.jFileChooserSaveTmrFile.setSelectedFile(new File(suggestedName));
         JFrame fr = new JFrame();
         fr.setIconImage(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + this.bundle.getString("saveIcon"))).getImage());
         int actionDialog = this.jFileChooserSaveTmrFile.showSaveDialog(fr);
         if (actionDialog == 0) {
            String relativeSelectedFilename = this.jFileChooserSaveTmrFile.getSelectedFile().getName();
            if (this.boardModel.compareToIgnoreCase("E145") == 0
               && relativeSelectedFilename.compareToIgnoreCase(FAAC_frames.FILENAME_TMR_E145_wExt) != 0
               && relativeSelectedFilename.compareToIgnoreCase(FAAC_frames.FILENAME_TMR_E145_noExt) != 0) {
               JOptionPane.showMessageDialog(this, this.bundle.getString("changeSuggestedFilename_message"), this.bundle.getString("Attention"), 2);
            }

            File fileName;
            if (relativeSelectedFilename.contains("." + FAAC_frames.FILE_EXT_TMR)) {
               fileName = new File(this.jFileChooserSaveTmrFile.getSelectedFile().getPath());
            } else {
               fileName = new File(this.jFileChooserSaveTmrFile.getSelectedFile() + "." + FAAC_frames.FILE_EXT_TMR);
            }

            if (fileName != null) {
               if (fileName.exists()) {
                  actionDialog = JOptionPane.showConfirmDialog(this, this.bundle.getString("replaceFileMsg"));

                  while (actionDialog == 1) {
                     actionDialog = this.jFileChooserSaveTmrFile.showSaveDialog(fr);
                     if (actionDialog == 0) {
                        if (relativeSelectedFilename.contains("." + FAAC_frames.FILE_EXT_TMR)) {
                           fileName = new File(this.jFileChooserSaveTmrFile.getSelectedFile().getPath());
                        } else {
                           fileName = new File(this.jFileChooserSaveTmrFile.getSelectedFile() + "." + FAAC_frames.FILE_EXT_TMR);
                        }

                        if (fileName.exists()) {
                           actionDialog = JOptionPane.showConfirmDialog(this, this.bundle.getString("replaceFileMsg"));
                        }
                     }
                  }

                  if (actionDialog == 0) {
                     this.generateTmrFile(fileName, file_password);
                  }

                  return;
               }

               this.generateTmrFile(fileName, file_password);
            }
         }
      }
   }

   private boolean parseTmrFile(File fileName, String filePassword) {
      byte[] readBytes = new byte[FAAC_frames.FILE_TMR_LENGTH];

      try {
         FileInputStream fis = new FileInputStream(fileName);

         for (int i = 0; i < FAAC_frames.FILE_TMR_LENGTH; i++) {
            readBytes[i] = (byte)fis.read();
         }

         fis.close();
      } catch (IOException var8) {
         Logger.getLogger(FAACTimer.class.getName()).log(Level.SEVERE, null, var8);
      }

      boolean bCheck = false;

      try {
         bCheck = FAAC_Protocol.CheckTmrFile(readBytes, filePassword, this.boardModel);
      } catch (Exception var7) {
         Logger.getLogger(FAACTimer.class.getName()).log(Level.SEVERE, null, var7);
      }

      if (!bCheck) {
         JOptionPane.showMessageDialog(this, this.bundle.getString("fileContentNotOk"), this.bundle.getString("Attention"), 0);
         return false;
      } else {
         boolean pswOk = FAAC_frames.parseTmrFile(readBytes, this.boardTimer, filePassword);
         if (!pswOk) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("filePswNotOk"), this.bundle.getString("Attention"), 0);
            return false;
         } else {
            this.jLBModTimer.setVisible(false);
            this.isModTimer = false;
            return true;
         }
      }
   }

   public void importTmrFromFile() {
      JFrame fr = new JFrame();
      fr.setIconImage(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + this.bundle.getString("openIcon"))).getImage());
      int actionDialog = this.jFileChooserOpenTmrFile.showOpenDialog(fr);
      if (actionDialog == 0) {
         File fileToParse = this.jFileChooserOpenTmrFile.getSelectedFile();
         String relativeFilename = fileToParse.getName();
         if (!relativeFilename.contains("." + FAAC_frames.FILE_EXT_TMR)) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("fileTmrFormatNotOk"), this.bundle.getString("Attention"), 0);
            return;
         }

         PasswordInsertDialog pswInsertDialog = new PasswordInsertDialog(null, true);
         pswInsertDialog.setVisible(true);
         boolean isPasswordInserted = pswInsertDialog.getIsPswInserted();
         if (!isPasswordInserted) {
            return;
         }

         String filePassword = pswInsertDialog.getPassword();
         boolean bRes = this.parseTmrFile(fileToParse, filePassword);
         if (bRes) {
            this.initPanelTimer();
            JOptionPane.showMessageDialog(this, this.bundle.getString("importTmrFileOkMsg"), this.bundle.getString("Information"), 1);
         }
      }
   }

   private void jTtimerKeyPressed(KeyEvent evt) {
      char c = evt.getKeyChar();
      int keycode = evt.getKeyCode();
      if (keycode == 127 || keycode == 8) {
         this.deleteSelectedEvent();
      }
   }

   private void jBtResetMousePressed(MouseEvent evt) {
      int iRes = JOptionPane.showConfirmDialog(this, this.bundle.getString("NewDefault_question"), this.bundle.getString("Attention"), 0);
      if (iRes == 0 && this.jBtReset.isEnabled()) {
         FAAC_Timer newTimer = new FAAC_Timer();
         this.boardTimer.setTimerEventMatrix_6rangesx8days(newTimer.getTimerEventMatrix_6rangesx8days());
         this.jLBModTimer.setVisible(false);
         this.isModTimer = false;
         this.initPanelTimer();
      }
   }

   private void jBtImportFromFileMousePressed(MouseEvent evt) {
      if (this.jBtImportFromFile.isEnabled()) {
         this.importTmrFromFile();
      }
   }

   private void jBtReadTimerFromBoardMousePressed(MouseEvent evt) {
      if (this.jBtReadTimerFromBoard.isEnabled()) {
         if (this.isBoardConnected) {
            this.readTimerFromBoardMethod();
         } else {
            Object[] listeners = this.listenersFromBoard.getListenerList();
            this.notifyToActionListeners(listeners);
         }
      }
   }

   private void jBtExportOnFileMousePressed(MouseEvent evt) {
      if (this.jBtExportOnFile.isEnabled()) {
         this.exportTimerOnFile();
      }
   }

   private void jBtWriteOnBoardMousePressed(MouseEvent evt) {
      if (this.jBtWriteOnBoard.isEnabled()) {
         if (this.isBoardConnected) {
            this.writeTimerOnBoardMethod();
         } else {
            JOptionPane.showMessageDialog(this, this.bundle.getString("NotConnected_message"), this.bundle.getString("Attention"), 2);
         }
      }
   }

   private void jBtNewEventMousePressed(MouseEvent evt) {
      if (this.jBtNewEvent.isEnabled()) {
         FAACTimerDetail detail = new FAACTimerDetail(this.parentForDlgs, true, this.boardTimer, false, null);
         detail.setLocationRelativeTo(this.parentForDlgs);
         detail.setVisible(true);
         if (detail.isIsOk()) {
            this.jLBModTimer.setVisible(true);
            this.isModTimer = true;
         }

         this.initPanelTimer();
      }
   }

   private void jBtModEventMousePressed(MouseEvent evt) {
      if (this.jBtModEvent.isEnabled()) {
         int[] cols = this.jTtimer.getSelectedColumns();
         int[] rows = this.jTtimer.getSelectedRows();
         if (cols == null || rows == null || cols.length == 0 || rows.length == 0) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("noEventSelected_message"), this.bundle.getString("Attention"), 2);
            return;
         }

         FAAC_Timer.TimerEvent eventToBeMod = (FAAC_Timer.TimerEvent)this.jTtimer.getValueAt(rows[0], cols[0]);
         if (eventToBeMod == null) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("selectedTheEventToModify_message"), this.bundle.getString("Attention"), 2);
            return;
         }

         FAACTimerDetail detail = new FAACTimerDetail(this.parentForDlgs, true, this.boardTimer, true, eventToBeMod);
         detail.setLocationRelativeTo(this.parentForDlgs);
         detail.setVisible(true);
         if (detail.isIsOk()) {
            this.jLBModTimer.setVisible(true);
            this.isModTimer = true;
         }

         ((CellSpan)this.cellAtt).split(rows[0], cols[0]);
         this.jTtimer.setValueAt(null, rows[0], cols[0]);
         this.initPanelTimer();
      }
   }

   private void jBtDelEventMousePressed(MouseEvent evt) {
      if (this.jBtDelEvent.isEnabled()) {
         int[] cols = this.jTtimer.getSelectedColumns();
         int[] rows = this.jTtimer.getSelectedRows();
         if (cols == null || rows == null || cols.length == 0 || rows.length == 0) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("noEventSelected_message"), this.bundle.getString("Attention"), 2);
            return;
         }

         FAAC_Timer.TimerEvent eventToBeDel = (FAAC_Timer.TimerEvent)this.jTtimer.getValueAt(rows[0], cols[0]);
         if (eventToBeDel == null) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("selectedTheEventToDelete_message"), this.bundle.getString("Attention"), 2);
            return;
         }

         eventToBeDel.setValueFunction(0);
         ((CellSpan)this.cellAtt).split(rows[0], cols[0]);
         this.jLBModTimer.setVisible(true);
         this.isModTimer = true;
         this.initPanelTimer();
      }
   }

   private void jBtPrintTimerOnPdfMousePressed(MouseEvent evt) {
      if (this.jBtPrintTimerOnPdf.isEnabled()) {
         this.printTimerInPdf();
      }
   }

   private void jBtSetJollyIntervalsMousePressed(MouseEvent evt) {
      FAACTimerJolly jolly = new FAACTimerJolly(this.parentForDlgs, true, this.boardTimer);
      jolly.setLocationRelativeTo(this.parentForDlgs);
      jolly.setVisible(true);
      if (jolly.isIsOk()) {
         this.jLBModTimer.setVisible(true);
         this.isModTimer = true;
      }

      this.initPanelTimer();
   }

   private void jChbEnableTimerOnBoardMousePressed(MouseEvent evt) {
      this.boardTimer.getProgTimer().set00_TimerEnabled(!this.jChbEnableTimerOnBoard.isSelected());
      this.jLBModTimer.setVisible(true);
      this.isModTimer = true;
   }

   public void printTimerInPdf() {
      JFrame fr = new JFrame();
      fr.setIconImage(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/" + this.bundle.getString("saveIcon"))).getImage());
      int actionDialog = this.jFileChooserPrintTimerPdf.showSaveDialog(fr);
      if (actionDialog == 0) {
         String relativeSelectedFilename = this.jFileChooserPrintTimerPdf.getSelectedFile().getName();
         File fileName;
         if (relativeSelectedFilename.contains(".pdf")) {
            fileName = new File(this.jFileChooserPrintTimerPdf.getSelectedFile().getPath());
         } else {
            fileName = new File(this.jFileChooserPrintTimerPdf.getSelectedFile() + ".pdf");
         }

         if (fileName != null) {
            if (fileName.exists()) {
               actionDialog = JOptionPane.showConfirmDialog(this, this.bundle.getString("replaceFileMsg"));

               while (actionDialog == 1) {
                  actionDialog = this.jFileChooserPrintTimerPdf.showSaveDialog(fr);
                  if (actionDialog == 0) {
                     if (relativeSelectedFilename.contains(".pdf")) {
                        fileName = new File(this.jFileChooserPrintTimerPdf.getSelectedFile().getPath());
                     } else {
                        fileName = new File(this.jFileChooserPrintTimerPdf.getSelectedFile() + ".pdf");
                     }

                     if (fileName.exists()) {
                        actionDialog = JOptionPane.showConfirmDialog(this, this.bundle.getString("replaceFileMsg"));
                     }
                  }
               }

               if (actionDialog == 0) {
                  this.generatePdfTimerReport(fileName);
               }

               return;
            }

            this.generatePdfTimerReport(fileName);
         }
      }
   }

   private void generatePdfTimerReport(File fileName) {
      String parentPath = fileName.getParent();
      int w = this.jScrollPaneTimer.getWidth();
      int h = this.jScrollPaneTimer.getHeight();
      BufferedImage image = new BufferedImage(w, h, 1);
      Graphics2D g2 = image.createGraphics();
      g2.setPaint(Color.WHITE);
      g2.fillRect(0, 0, w, h);
      this.jScrollPaneTimer.paint(g2);
      g2.dispose();

      try {
         this.timerImageForReport = parentPath + "/boardTimerTmp.png";
         File file = new File(this.timerImageForReport);
         ImageIO.write(image, "png", file);
      } catch (IOException var16) {
      }

      try {
         Map parameters = new HashMap();
         parameters.put("ReportTitle", "Timer E145");
         ResourceBundle bundleReport = ResourceBundle.getBundle("FAACbeans/timerReport");
         parameters.put("REPORT_RESOURCE_BUNDLE", bundleReport);
         String path = FilePathManager.getApplicationDir();
         String logoName = this.bundle.getString("logoForReport");
         parameters.put("LOGO", path + "/" + logoName);
         Debug.printlnStatic(path + "/" + logoName);
         String imageBoardName = this.timerImageForReport;
         parameters.put("TIMER_IMAGE", imageBoardName);
         Debug.printlnStatic(imageBoardName);
         String report = path + "timerReport.jasper";
         JasperPrint print = JasperFillManager.fillReport(report, parameters, new timerReportDataSource());
         JasperExportManager.exportReportToPdfFile(print, fileName.getCanonicalPath());
         File pdfFile = new File(fileName.getCanonicalPath());
         if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(pdfFile);
         }
      } catch (Exception var15) {
         Logger.getLogger(FAACTimer.class.getName()).log(Level.SEVERE, null, var15);
      }
   }

   public void addListenerFromBoard(ActionListener listener) {
      this.listenersFromBoard.add(ActionListener.class, listener);
   }

   public void removeListenerFromBoard(ActionListener listener) {
      this.listenersFromBoard.remove(ActionListener.class, listener);
   }

   public boolean[] getIsTimerToBeWrittenOnBoard() {
      return this.isTimerToBeWrittenOnBoard;
   }

   public void setIsTimerToBeWrittenOnBoard(boolean[] isTimerToBeWrittenOnBoard) {
      this.isTimerToBeWrittenOnBoard = isTimerToBeWrittenOnBoard;
   }

   public boolean[] getIsTimerToBeReadOnBoard() {
      return this.isTimerToBeReadOnBoard;
   }

   public void setIsTimerToBeReadOnBoard(boolean[] isTimerToBeReadOnBoard) {
      this.isTimerToBeReadOnBoard = isTimerToBeReadOnBoard;
   }

   public Calendar getNewDate() {
      return this.newDate;
   }

   public void setNewDate(Calendar newDate) {
      this.newDate = newDate;
   }

   public boolean getIsDateUpdateCmdRequested() {
      return this.isDateUpdateCmdRequested;
   }

   public void setIsDateUpdateCmdRequested(boolean isDateUpdateCmdRequested) {
      this.isDateUpdateCmdRequested = isDateUpdateCmdRequested;
   }

   public void setjLBModTimerVisible(boolean value) {
      this.jLBModTimer.setVisible(value);
   }

   public boolean isIsModTimer() {
      return this.isModTimer;
   }

   public void setIsModTimer(boolean isModTimer) {
      this.isModTimer = isModTimer;
   }

   public boolean isIsProgTimerToBeReadFromBoard() {
      return this.isProgTimerToBeReadFromBoard;
   }

   public void setIsProgTimerToBeReadFromBoard(boolean isProgTimerToBeReadFromBoard) {
      this.isProgTimerToBeReadFromBoard = isProgTimerToBeReadFromBoard;
   }

   public boolean isIsProgTimerToBeWrittenOnBoard() {
      return this.isProgTimerToBeWrittenOnBoard;
   }

   public void setIsProgTimerToBeWrittenOnBoard(boolean isProgTimerToBeWrittenOnBoard) {
      this.isProgTimerToBeWrittenOnBoard = isProgTimerToBeWrittenOnBoard;
   }
}
