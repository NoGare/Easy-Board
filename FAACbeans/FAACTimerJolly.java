package FAACbeans;

import FAAClib.FAAC_Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdesktop.swingx.JXDatePicker;

public class FAACTimerJolly extends JDialog {
   private JButton jBtCancel;
   private JButton jBtOk;
   private JCheckBox jCbEnableInt_1;
   private JCheckBox jCbEnableInt_2;
   private JCheckBox jCbEnableInt_3;
   private JCheckBox jCbEnableInt_4;
   private JCheckBox jCbEnableInt_5;
   private JCheckBox jCbEnableInt_6;
   private JLabel jLEndLabel_1;
   private JLabel jLEndLabel_2;
   private JLabel jLEndLabel_3;
   private JLabel jLEndLabel_4;
   private JLabel jLEndLabel_5;
   private JLabel jLEndLabel_6;
   private JLabel jLStartLabel_1;
   private JLabel jLStartLabel_2;
   private JLabel jLStartLabel_3;
   private JLabel jLStartLabel_4;
   private JLabel jLStartLabel_5;
   private JLabel jLStartLabel_6;
   private JPanel jPanel1;
   private JPanel jPanel2;
   private JPanel jPanel3;
   private JPanel jPanel4;
   private JPanel jPanel5;
   private JPanel jPanel6;
   private JXDatePicker jXDPdateEnd_1;
   private JXDatePicker jXDPdateEnd_2;
   private JXDatePicker jXDPdateEnd_3;
   private JXDatePicker jXDPdateEnd_4;
   private JXDatePicker jXDPdateEnd_5;
   private JXDatePicker jXDPdateEnd_6;
   private JXDatePicker jXDPdateStart_1;
   private JXDatePicker jXDPdateStart_2;
   private JXDatePicker jXDPdateStart_3;
   private JXDatePicker jXDPdateStart_4;
   private JXDatePicker jXDPdateStart_5;
   private JXDatePicker jXDPdateStart_6;
   private FAAC_Timer boardTimer;
   ResourceBundle bundle;
   private boolean isOk;

   public FAACTimerJolly(Frame parent, boolean modal, FAAC_Timer boardTimer) {
      super(parent, modal);
      this.initComponents();
      this.setLocationRelativeTo(parent);
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACTimerJolly");
      this.boardTimer = boardTimer;
      this.jXDPdateStart_1.getEditor().setEditable(false);
      this.jXDPdateStart_1.getEditor().setBackground(SystemColor.text);
      this.jXDPdateEnd_1.getEditor().setEditable(false);
      this.jXDPdateEnd_1.getEditor().setBackground(SystemColor.text);
      this.jXDPdateStart_2.getEditor().setEditable(false);
      this.jXDPdateStart_2.getEditor().setBackground(SystemColor.text);
      this.jXDPdateEnd_2.getEditor().setEditable(false);
      this.jXDPdateEnd_2.getEditor().setBackground(SystemColor.text);
      this.jXDPdateStart_3.getEditor().setEditable(false);
      this.jXDPdateStart_3.getEditor().setBackground(SystemColor.text);
      this.jXDPdateEnd_3.getEditor().setEditable(false);
      this.jXDPdateEnd_3.getEditor().setBackground(SystemColor.text);
      this.jXDPdateStart_4.getEditor().setEditable(false);
      this.jXDPdateStart_4.getEditor().setBackground(SystemColor.text);
      this.jXDPdateEnd_4.getEditor().setEditable(false);
      this.jXDPdateEnd_4.getEditor().setBackground(SystemColor.text);
      this.jXDPdateStart_5.getEditor().setEditable(false);
      this.jXDPdateStart_5.getEditor().setBackground(SystemColor.text);
      this.jXDPdateEnd_5.getEditor().setEditable(false);
      this.jXDPdateEnd_5.getEditor().setBackground(SystemColor.text);
      this.jXDPdateStart_6.getEditor().setEditable(false);
      this.jXDPdateStart_6.getEditor().setBackground(SystemColor.text);
      this.jXDPdateEnd_6.getEditor().setEditable(false);
      this.jXDPdateEnd_6.getEditor().setBackground(SystemColor.text);
      this.isOk = false;
      this.init();
   }

   private void init() {
      Calendar dateTmp = GregorianCalendar.getInstance();
      dateTmp.clear();
      int iDay = this.boardTimer.getJollyIntervals()[0].getStartDay();
      int iMonth = this.boardTimer.getJollyIntervals()[0].getStartMonth();
      int iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      Date date = dateTmp.getTime();
      this.jXDPdateStart_1.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[0].getEndDay();
      iMonth = this.boardTimer.getJollyIntervals()[0].getEndMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateEnd_1.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[1].getStartDay();
      iMonth = this.boardTimer.getJollyIntervals()[1].getStartMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateStart_2.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[1].getEndDay();
      iMonth = this.boardTimer.getJollyIntervals()[1].getEndMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateEnd_2.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[2].getStartDay();
      iMonth = this.boardTimer.getJollyIntervals()[2].getStartMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateStart_3.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[2].getEndDay();
      iMonth = this.boardTimer.getJollyIntervals()[2].getEndMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateEnd_3.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[3].getStartDay();
      iMonth = this.boardTimer.getJollyIntervals()[3].getStartMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateStart_4.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[3].getEndDay();
      iMonth = this.boardTimer.getJollyIntervals()[3].getEndMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateEnd_4.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[4].getStartDay();
      iMonth = this.boardTimer.getJollyIntervals()[4].getStartMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateStart_5.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[4].getEndDay();
      iMonth = this.boardTimer.getJollyIntervals()[4].getEndMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateEnd_5.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[5].getStartDay();
      iMonth = this.boardTimer.getJollyIntervals()[5].getStartMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateStart_6.setDate(date);
      dateTmp.clear();
      iDay = this.boardTimer.getJollyIntervals()[5].getEndDay();
      iMonth = this.boardTimer.getJollyIntervals()[5].getEndMonth();
      iYear = Calendar.getInstance().get(1);
      dateTmp.set(5, iDay);
      dateTmp.set(2, iMonth - 1);
      dateTmp.set(1, iYear);
      date = dateTmp.getTime();
      this.jXDPdateEnd_6.setDate(date);
      this.jCbEnableInt_1.setSelected(!this.boardTimer.getProgTimer().get01_DisableJollyInterval_1());
      this.jCbEnableInt_2.setSelected(!this.boardTimer.getProgTimer().get02_DisableJollyInterval_2());
      this.jCbEnableInt_3.setSelected(!this.boardTimer.getProgTimer().get03_DisableJollyInterval_3());
      this.jCbEnableInt_4.setSelected(!this.boardTimer.getProgTimer().get04_DisableJollyInterval_4());
      this.jCbEnableInt_5.setSelected(!this.boardTimer.getProgTimer().get05_DisableJollyInterval_5());
      this.jCbEnableInt_6.setSelected(!this.boardTimer.getProgTimer().get06_DisableJollyInterval_6());
      this.jCbEnableInt_1StateChanged(null);
      this.jCbEnableInt_2StateChanged(null);
      this.jCbEnableInt_3StateChanged(null);
      this.jCbEnableInt_4StateChanged(null);
      this.jCbEnableInt_5StateChanged(null);
      this.jCbEnableInt_6StateChanged(null);
   }

   private void initComponents() {
      this.jBtOk = new JButton();
      this.jBtCancel = new JButton();
      this.jPanel1 = new JPanel();
      this.jLStartLabel_1 = new JLabel();
      this.jLEndLabel_1 = new JLabel();
      this.jXDPdateStart_1 = new JXDatePicker();
      this.jXDPdateEnd_1 = new JXDatePicker();
      this.jCbEnableInt_1 = new JCheckBox();
      this.jPanel2 = new JPanel();
      this.jLStartLabel_2 = new JLabel();
      this.jLEndLabel_2 = new JLabel();
      this.jXDPdateStart_2 = new JXDatePicker();
      this.jXDPdateEnd_2 = new JXDatePicker();
      this.jCbEnableInt_2 = new JCheckBox();
      this.jPanel3 = new JPanel();
      this.jLStartLabel_3 = new JLabel();
      this.jLEndLabel_3 = new JLabel();
      this.jXDPdateStart_3 = new JXDatePicker();
      this.jXDPdateEnd_3 = new JXDatePicker();
      this.jCbEnableInt_3 = new JCheckBox();
      this.jPanel4 = new JPanel();
      this.jLStartLabel_4 = new JLabel();
      this.jLEndLabel_4 = new JLabel();
      this.jXDPdateStart_4 = new JXDatePicker();
      this.jXDPdateEnd_4 = new JXDatePicker();
      this.jCbEnableInt_4 = new JCheckBox();
      this.jPanel5 = new JPanel();
      this.jLStartLabel_5 = new JLabel();
      this.jLEndLabel_5 = new JLabel();
      this.jXDPdateStart_5 = new JXDatePicker();
      this.jXDPdateEnd_5 = new JXDatePicker();
      this.jCbEnableInt_5 = new JCheckBox();
      this.jPanel6 = new JPanel();
      this.jLStartLabel_6 = new JLabel();
      this.jLEndLabel_6 = new JLabel();
      this.jXDPdateStart_6 = new JXDatePicker();
      this.jXDPdateEnd_6 = new JXDatePicker();
      this.jCbEnableInt_6 = new JCheckBox();
      this.setDefaultCloseOperation(2);
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACTimerJolly");
      this.setTitle(bundle.getString("FAACTimerJolly.title"));
      this.setName("Form");
      this.jBtOk.setText(bundle.getString("FAACTimerJolly.jBtOk.text"));
      this.jBtOk.setName("jBtOk");
      this.jBtOk.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimerJolly.this.jBtOkMousePressed(evt);
         }
      });
      this.jBtCancel.setText(bundle.getString("FAACTimerJolly.jBtCancel.text"));
      this.jBtCancel.setName("jBtCancel");
      this.jBtCancel.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimerJolly.this.jBtCancelMousePressed(evt);
         }
      });
      this.jPanel1.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimerJolly.jPanel1.border.title")));
      this.jPanel1.setName("jPanel1");
      this.jLStartLabel_1.setHorizontalAlignment(4);
      this.jLStartLabel_1.setText(bundle.getString("FAACTimerJolly.jLStartLabel_1.text"));
      this.jLStartLabel_1.setName("jLStartLabel_1");
      this.jLEndLabel_1.setHorizontalAlignment(4);
      this.jLEndLabel_1.setText(bundle.getString("FAACTimerJolly.jLEndLabel_1.text"));
      this.jLEndLabel_1.setName("jLEndLabel_1");
      this.jXDPdateStart_1.setName("jXDPdateStart_1");
      this.jXDPdateEnd_1.setBackground(new Color(255, 0, 204));
      this.jXDPdateEnd_1.setName("jXDPdateEnd_1");
      this.jCbEnableInt_1.setText(bundle.getString("FAACTimerJolly.jCbEnableInt_1.text"));
      this.jCbEnableInt_1.setName("jCbEnableInt_1");
      this.jCbEnableInt_1.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            FAACTimerJolly.this.jCbEnableInt_1StateChanged(evt);
         }
      });
      GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
      this.jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel1Layout.createSequentialGroup()
                  .addGroup(
                     jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                           jPanel1Layout.createSequentialGroup()
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jLStartLabel_1, -2, 71, -2)
                                    .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLEndLabel_1, -2, 63, -2))
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                    .addComponent(this.jXDPdateEnd_1, -1, 161, 32767)
                                    .addComponent(this.jXDPdateStart_1, -1, 161, 32767)
                              )
                        )
                        .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jCbEnableInt_1))
                  )
                  .addContainerGap()
            )
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel1Layout.createSequentialGroup()
                  .addComponent(this.jCbEnableInt_1)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLStartLabel_1).addComponent(this.jXDPdateStart_1, -2, -1, -2)
                  )
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateEnd_1, -2, -1, -2).addComponent(this.jLEndLabel_1))
            )
      );
      this.jPanel2.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimerJolly.jPanel2.border.title")));
      this.jPanel2.setName("jPanel2");
      this.jPanel2.setPreferredSize(new Dimension(264, 110));
      this.jLStartLabel_2.setHorizontalAlignment(4);
      this.jLStartLabel_2.setText(bundle.getString("FAACTimerJolly.jLStartLabel_2.text"));
      this.jLStartLabel_2.setName("jLStartLabel_2");
      this.jLEndLabel_2.setHorizontalAlignment(4);
      this.jLEndLabel_2.setText(bundle.getString("FAACTimerJolly.jLEndLabel_2.text"));
      this.jLEndLabel_2.setName("jLEndLabel_2");
      this.jXDPdateStart_2.setForeground(new Color(102, 255, 0));
      this.jXDPdateStart_2.setName("jXDPdateStart_2");
      this.jXDPdateEnd_2.setName("jXDPdateEnd_2");
      this.jCbEnableInt_2.setText(bundle.getString("FAACTimerJolly.jCbEnableInt_2.text"));
      this.jCbEnableInt_2.setName("jCbEnableInt_2");
      this.jCbEnableInt_2.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            FAACTimerJolly.this.jCbEnableInt_2StateChanged(evt);
         }
      });
      GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
      this.jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(
         jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel2Layout.createSequentialGroup()
                  .addGroup(
                     jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                           Alignment.TRAILING,
                           jPanel2Layout.createSequentialGroup()
                              .addGap(18, 18, 18)
                              .addComponent(this.jLEndLabel_2, -2, 63, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateEnd_2, -1, 153, 32767)
                        )
                        .addGroup(
                           Alignment.TRAILING,
                           jPanel2Layout.createSequentialGroup()
                              .addContainerGap()
                              .addComponent(this.jLStartLabel_2, -2, 71, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateStart_2, -1, 153, 32767)
                        )
                        .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jCbEnableInt_2))
                  )
                  .addContainerGap()
            )
      );
      jPanel2Layout.setVerticalGroup(
         jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPanel2Layout.createSequentialGroup()
                  .addComponent(this.jCbEnableInt_2)
                  .addPreferredGap(ComponentPlacement.RELATED, 7, 32767)
                  .addGroup(
                     jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateStart_2, -2, -1, -2).addComponent(this.jLStartLabel_2)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateEnd_2, -2, -1, -2).addComponent(this.jLEndLabel_2))
            )
      );
      this.jPanel3.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimerJolly.jPanel3.border.title")));
      this.jPanel3.setName("jPanel3");
      this.jPanel3.setPreferredSize(new Dimension(264, 110));
      this.jLStartLabel_3.setHorizontalAlignment(4);
      this.jLStartLabel_3.setText(bundle.getString("FAACTimerJolly.jLStartLabel_3.text"));
      this.jLStartLabel_3.setName("jLStartLabel_3");
      this.jLEndLabel_3.setHorizontalAlignment(4);
      this.jLEndLabel_3.setText(bundle.getString("FAACTimerJolly.jLEndLabel_3.text"));
      this.jLEndLabel_3.setName("jLEndLabel_3");
      this.jXDPdateStart_3.setName("jXDPdateStart_3");
      this.jXDPdateEnd_3.setName("jXDPdateEnd_3");
      this.jCbEnableInt_3.setText(bundle.getString("FAACTimerJolly.jCbEnableInt_3.text"));
      this.jCbEnableInt_3.setName("jCbEnableInt_3");
      this.jCbEnableInt_3.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            FAACTimerJolly.this.jCbEnableInt_3StateChanged(evt);
         }
      });
      GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
      this.jPanel3.setLayout(jPanel3Layout);
      jPanel3Layout.setHorizontalGroup(
         jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel3Layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     jPanel3Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jCbEnableInt_3)
                        .addGroup(
                           jPanel3Layout.createSequentialGroup()
                              .addComponent(this.jLStartLabel_3, -2, 71, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateStart_3, -1, 153, 32767)
                        )
                        .addGroup(
                           Alignment.TRAILING,
                           jPanel3Layout.createSequentialGroup()
                              .addGap(8, 8, 8)
                              .addComponent(this.jLEndLabel_3, -2, 63, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateEnd_3, -1, 153, 32767)
                        )
                  )
                  .addContainerGap()
            )
      );
      jPanel3Layout.setVerticalGroup(
         jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPanel3Layout.createSequentialGroup()
                  .addComponent(this.jCbEnableInt_3)
                  .addPreferredGap(ComponentPlacement.RELATED, 7, 32767)
                  .addGroup(
                     jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateStart_3, -2, -1, -2).addComponent(this.jLStartLabel_3)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateEnd_3, -2, -1, -2).addComponent(this.jLEndLabel_3))
            )
      );
      this.jPanel4.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimerJolly.jPanel4.border.title")));
      this.jPanel4.setName("jPanel4");
      this.jPanel4.setPreferredSize(new Dimension(264, 110));
      this.jLStartLabel_4.setHorizontalAlignment(4);
      this.jLStartLabel_4.setText(bundle.getString("FAACTimerJolly.jLStartLabel_4.text"));
      this.jLStartLabel_4.setName("jLStartLabel_4");
      this.jLEndLabel_4.setHorizontalAlignment(4);
      this.jLEndLabel_4.setText(bundle.getString("FAACTimerJolly.jLEndLabel_4.text"));
      this.jLEndLabel_4.setName("jLEndLabel_4");
      this.jXDPdateStart_4.setName("jXDPdateStart_4");
      this.jXDPdateEnd_4.setName("jXDPdateEnd_4");
      this.jCbEnableInt_4.setText(bundle.getString("FAACTimerJolly.jCbEnableInt_4.text"));
      this.jCbEnableInt_4.setName("jCbEnableInt_4");
      this.jCbEnableInt_4.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            FAACTimerJolly.this.jCbEnableInt_4StateChanged(evt);
         }
      });
      GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
      this.jPanel4.setLayout(jPanel4Layout);
      jPanel4Layout.setHorizontalGroup(
         jPanel4Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel4Layout.createSequentialGroup()
                  .addGroup(
                     jPanel4Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                           Alignment.TRAILING,
                           jPanel4Layout.createSequentialGroup()
                              .addGap(18, 18, 18)
                              .addComponent(this.jLEndLabel_4, -2, 63, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateEnd_4, -1, 153, 32767)
                        )
                        .addGroup(
                           jPanel4Layout.createSequentialGroup()
                              .addContainerGap()
                              .addComponent(this.jLStartLabel_4, -2, 71, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateStart_4, -1, 153, 32767)
                        )
                        .addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(this.jCbEnableInt_4))
                  )
                  .addContainerGap()
            )
      );
      jPanel4Layout.setVerticalGroup(
         jPanel4Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPanel4Layout.createSequentialGroup()
                  .addComponent(this.jCbEnableInt_4)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addGroup(
                     jPanel4Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLStartLabel_4).addComponent(this.jXDPdateStart_4, -2, -1, -2)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateEnd_4, -2, -1, -2).addComponent(this.jLEndLabel_4))
            )
      );
      this.jPanel5.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimerJolly.jPanel5.border.title")));
      this.jPanel5.setName("jPanel5");
      this.jPanel5.setPreferredSize(new Dimension(264, 110));
      this.jLStartLabel_5.setHorizontalAlignment(4);
      this.jLStartLabel_5.setText(bundle.getString("FAACTimerJolly.jLStartLabel_5.text"));
      this.jLStartLabel_5.setName("jLStartLabel_5");
      this.jLEndLabel_5.setHorizontalAlignment(4);
      this.jLEndLabel_5.setText(bundle.getString("FAACTimerJolly.jLEndLabel_5.text"));
      this.jLEndLabel_5.setName("jLEndLabel_5");
      this.jXDPdateStart_5.setName("jXDPdateStart_5");
      this.jXDPdateEnd_5.setName("jXDPdateEnd_5");
      this.jCbEnableInt_5.setText(bundle.getString("FAACTimerJolly.jCbEnableInt_5.text"));
      this.jCbEnableInt_5.setName("jCbEnableInt_5");
      this.jCbEnableInt_5.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            FAACTimerJolly.this.jCbEnableInt_5StateChanged(evt);
         }
      });
      GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
      this.jPanel5.setLayout(jPanel5Layout);
      jPanel5Layout.setHorizontalGroup(
         jPanel5Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel5Layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     jPanel5Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                           jPanel5Layout.createSequentialGroup()
                              .addComponent(this.jLStartLabel_5, -2, 71, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateStart_5, -1, 153, 32767)
                        )
                        .addGroup(
                           Alignment.TRAILING,
                           jPanel5Layout.createSequentialGroup()
                              .addGap(2, 2, 2)
                              .addComponent(this.jLEndLabel_5, -2, 63, -2)
                              .addPreferredGap(ComponentPlacement.UNRELATED)
                              .addComponent(this.jXDPdateEnd_5, -1, 153, 32767)
                        )
                        .addComponent(this.jCbEnableInt_5)
                  )
                  .addContainerGap()
            )
      );
      jPanel5Layout.setVerticalGroup(
         jPanel5Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPanel5Layout.createSequentialGroup()
                  .addComponent(this.jCbEnableInt_5)
                  .addPreferredGap(ComponentPlacement.RELATED, 7, 32767)
                  .addGroup(
                     jPanel5Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateStart_5, -2, -1, -2).addComponent(this.jLStartLabel_5)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateEnd_5, -2, -1, -2).addComponent(this.jLEndLabel_5))
            )
      );
      this.jPanel6.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimerJolly.jPanel6.border.title")));
      this.jPanel6.setName("jPanel6");
      this.jPanel6.setPreferredSize(new Dimension(264, 110));
      this.jLStartLabel_6.setHorizontalAlignment(4);
      this.jLStartLabel_6.setText(bundle.getString("FAACTimerJolly.jLStartLabel_6.text"));
      this.jLStartLabel_6.setName("jLStartLabel_6");
      this.jLEndLabel_6.setHorizontalAlignment(4);
      this.jLEndLabel_6.setText(bundle.getString("FAACTimerJolly.jLEndLabel_6.text"));
      this.jLEndLabel_6.setName("jLEndLabel_6");
      this.jXDPdateStart_6.setName("jXDPdateStart_6");
      this.jXDPdateEnd_6.setName("jXDPdateEnd_6");
      this.jCbEnableInt_6.setText(bundle.getString("FAACTimerJolly.jCbEnableInt_6.text"));
      this.jCbEnableInt_6.setName("jCbEnableInt_6");
      this.jCbEnableInt_6.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            FAACTimerJolly.this.jCbEnableInt_6StateChanged(evt);
         }
      });
      GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
      this.jPanel6.setLayout(jPanel6Layout);
      jPanel6Layout.setHorizontalGroup(
         jPanel6Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel6Layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     jPanel6Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jCbEnableInt_6)
                        .addGroup(
                           jPanel6Layout.createSequentialGroup()
                              .addComponent(this.jLStartLabel_6, -2, 71, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateStart_6, -1, 153, 32767)
                        )
                        .addGroup(
                           Alignment.TRAILING,
                           jPanel6Layout.createSequentialGroup()
                              .addGap(8, 8, 8)
                              .addComponent(this.jLEndLabel_6, -2, 63, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jXDPdateEnd_6, -1, 153, 32767)
                        )
                  )
                  .addContainerGap()
            )
      );
      jPanel6Layout.setVerticalGroup(
         jPanel6Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPanel6Layout.createSequentialGroup()
                  .addComponent(this.jCbEnableInt_6)
                  .addPreferredGap(ComponentPlacement.RELATED, 7, 32767)
                  .addGroup(
                     jPanel6Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateStart_6, -2, -1, -2).addComponent(this.jLStartLabel_6)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jXDPdateEnd_6, -2, -1, -2).addComponent(this.jLEndLabel_6))
            )
      );
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(this.jPanel3, -1, -1, 32767)
                        .addComponent(this.jPanel2, -1, -1, 32767)
                        .addComponent(this.jPanel1, -1, -1, 32767)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jPanel6, -2, -1, -2).addComponent(this.jPanel5, -2, -1, -2))
                        .addComponent(this.jPanel4, -2, -1, -2)
                  )
                  .addContainerGap()
            )
            .addGroup(
               Alignment.TRAILING,
               layout.createSequentialGroup()
                  .addContainerGap(412, 32767)
                  .addComponent(this.jBtOk)
                  .addGap(18, 18, 18)
                  .addComponent(this.jBtCancel)
                  .addContainerGap()
            )
      );
      layout.linkSize(0, this.jPanel4, this.jPanel5, this.jPanel6);
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel4, -2, -1, -2).addComponent(this.jPanel1, -2, -1, -2))
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel2, -1, -1, 32767).addComponent(this.jPanel5, -2, -1, -2))
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addComponent(this.jPanel6, -2, -1, -2)
                              .addPreferredGap(ComponentPlacement.UNRELATED)
                              .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jBtCancel).addComponent(this.jBtOk))
                        )
                        .addComponent(this.jPanel3, -2, -1, -2)
                  )
                  .addContainerGap()
            )
      );
      this.pack();
   }

   private void jBtOkMousePressed(MouseEvent evt) {
      Calendar calTmp = Calendar.getInstance();
      calTmp.clear();
      Date dateTmp = this.jXDPdateStart_1.getDate();
      calTmp.setTime(dateTmp);
      int iDay = calTmp.get(5);
      int iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[0].setStartDay(iDay);
      this.boardTimer.getJollyIntervals()[0].setStartMonth(iMonth + 1);
      dateTmp = this.jXDPdateEnd_1.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[0].setEndDay(iDay);
      this.boardTimer.getJollyIntervals()[0].setEndMonth(iMonth + 1);
      dateTmp = this.jXDPdateStart_2.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[1].setStartDay(iDay);
      this.boardTimer.getJollyIntervals()[1].setStartMonth(iMonth + 1);
      dateTmp = this.jXDPdateEnd_2.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[1].setEndDay(iDay);
      this.boardTimer.getJollyIntervals()[1].setEndMonth(iMonth + 1);
      dateTmp = this.jXDPdateStart_3.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[2].setStartDay(iDay);
      this.boardTimer.getJollyIntervals()[2].setStartMonth(iMonth + 1);
      dateTmp = this.jXDPdateEnd_3.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[2].setEndDay(iDay);
      this.boardTimer.getJollyIntervals()[2].setEndMonth(iMonth + 1);
      dateTmp = this.jXDPdateStart_4.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[3].setStartDay(iDay);
      this.boardTimer.getJollyIntervals()[3].setStartMonth(iMonth + 1);
      dateTmp = this.jXDPdateEnd_4.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[3].setEndDay(iDay);
      this.boardTimer.getJollyIntervals()[3].setEndMonth(iMonth + 1);
      dateTmp = this.jXDPdateStart_5.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[4].setStartDay(iDay);
      this.boardTimer.getJollyIntervals()[4].setStartMonth(iMonth + 1);
      dateTmp = this.jXDPdateEnd_5.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[4].setEndDay(iDay);
      this.boardTimer.getJollyIntervals()[4].setEndMonth(iMonth + 1);
      dateTmp = this.jXDPdateStart_6.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[5].setStartDay(iDay);
      this.boardTimer.getJollyIntervals()[5].setStartMonth(iMonth + 1);
      dateTmp = this.jXDPdateEnd_6.getDate();
      calTmp.setTime(dateTmp);
      iDay = calTmp.get(5);
      iMonth = calTmp.get(2);
      this.boardTimer.getJollyIntervals()[5].setEndDay(iDay);
      this.boardTimer.getJollyIntervals()[5].setEndMonth(iMonth + 1);
      this.boardTimer.getProgTimer().set01_DisableJollyInterval_1(!this.jCbEnableInt_1.isSelected());
      this.boardTimer.getProgTimer().set02_DisableJollyInterval_2(!this.jCbEnableInt_2.isSelected());
      this.boardTimer.getProgTimer().set03_DisableJollyInterval_3(!this.jCbEnableInt_3.isSelected());
      this.boardTimer.getProgTimer().set04_DisableJollyInterval_4(!this.jCbEnableInt_4.isSelected());
      this.boardTimer.getProgTimer().set05_DisableJollyInterval_5(!this.jCbEnableInt_5.isSelected());
      this.boardTimer.getProgTimer().set06_DisableJollyInterval_6(!this.jCbEnableInt_6.isSelected());
      this.isOk = true;
      this.setVisible(false);
   }

   private void jBtCancelMousePressed(MouseEvent evt) {
      this.isOk = false;
      this.setVisible(false);
   }

   private void jCbEnableInt_1StateChanged(ChangeEvent evt) {
      if (this.jCbEnableInt_1.isSelected()) {
         this.jXDPdateStart_1.setEnabled(true);
         this.jXDPdateEnd_1.setEnabled(true);
      } else {
         this.jXDPdateStart_1.setEnabled(false);
         this.jXDPdateEnd_1.setEnabled(false);
      }
   }

   private void jCbEnableInt_2StateChanged(ChangeEvent evt) {
      if (this.jCbEnableInt_2.isSelected()) {
         this.jXDPdateStart_2.setEnabled(true);
         this.jXDPdateEnd_2.setEnabled(true);
      } else {
         this.jXDPdateStart_2.setEnabled(false);
         this.jXDPdateEnd_2.setEnabled(false);
      }
   }

   private void jCbEnableInt_3StateChanged(ChangeEvent evt) {
      if (this.jCbEnableInt_3.isSelected()) {
         this.jXDPdateStart_3.setEnabled(true);
         this.jXDPdateEnd_3.setEnabled(true);
      } else {
         this.jXDPdateStart_3.setEnabled(false);
         this.jXDPdateEnd_3.setEnabled(false);
      }
   }

   private void jCbEnableInt_4StateChanged(ChangeEvent evt) {
      if (this.jCbEnableInt_4.isSelected()) {
         this.jXDPdateStart_4.setEnabled(true);
         this.jXDPdateEnd_4.setEnabled(true);
      } else {
         this.jXDPdateStart_4.setEnabled(false);
         this.jXDPdateEnd_4.setEnabled(false);
      }
   }

   private void jCbEnableInt_5StateChanged(ChangeEvent evt) {
      if (this.jCbEnableInt_5.isSelected()) {
         this.jXDPdateStart_5.setEnabled(true);
         this.jXDPdateEnd_5.setEnabled(true);
      } else {
         this.jXDPdateStart_5.setEnabled(false);
         this.jXDPdateEnd_5.setEnabled(false);
      }
   }

   private void jCbEnableInt_6StateChanged(ChangeEvent evt) {
      if (this.jCbEnableInt_6.isSelected()) {
         this.jXDPdateStart_6.setEnabled(true);
         this.jXDPdateEnd_6.setEnabled(true);
      } else {
         this.jXDPdateStart_6.setEnabled(false);
         this.jXDPdateEnd_6.setEnabled(false);
      }
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
            FAACTimerJolly dialog = new FAACTimerJolly(new JFrame(), true, new FAAC_Timer());
            dialog.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  System.exit(0);
               }
            });
            dialog.setVisible(true);
         }
      });
   }

   public boolean isIsOk() {
      return this.isOk;
   }

   public void setIsOk(boolean isOk) {
      this.isOk = isOk;
   }
}
