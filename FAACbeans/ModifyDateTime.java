package FAACbeans;

import java.awt.Color;
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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXDatePicker;

public class ModifyDateTime extends JDialog {
   private JButton jBCancel;
   private JButton jBOk;
   private JLabel jLDateLabel;
   private JLabel jLTimeLabel;
   private JLabel jLbFormatErr;
   private JSpinner jSpinnerTime;
   private JXDatePicker jXDPdate;
   private int iMinutes;
   private int iHour;
   private int iDay;
   private int iDayWeek;
   private int iMonth;
   private int iYear;
   private Calendar calendar;
   private boolean isDateChanged;

   public ModifyDateTime(Frame parent, boolean modal, Calendar calInit) {
      super(parent, modal);
      this.initComponents();
      FAAC_Utilities.setSpinnerHourMinModel(this.jSpinnerTime, 23, 59);
      JFormattedTextField ftfTmp = ((DefaultEditor)this.jSpinnerTime.getEditor()).getTextField();
      this.jXDPdate.getEditor().setEditable(false);
      this.jXDPdate.getEditor().setBackground(SystemColor.text);
      this.setLocationRelativeTo(parent);
      this.calendar = calInit;
      this.isDateChanged = false;
      this.jLbFormatErr.setEnabled(false);
      this.init();
   }

   private void init() {
      Date date = this.calendar.getTime();
      this.iMinutes = this.calendar.get(12);
      this.iHour = this.calendar.get(11);
      this.iDay = this.calendar.get(5);
      this.iDayWeek = this.calendar.get(7);
      this.iMonth = this.calendar.get(2);
      this.iYear = this.calendar.get(1);
      this.jXDPdate.setDate(date);
      this.jSpinnerTime.setValue(date);
   }

   private void initComponents() {
      this.jBOk = new JButton();
      this.jBCancel = new JButton();
      this.jLDateLabel = new JLabel();
      this.jXDPdate = new JXDatePicker();
      this.jLTimeLabel = new JLabel();
      this.jSpinnerTime = new JSpinner();
      this.jLbFormatErr = new JLabel();
      this.setDefaultCloseOperation(2);
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/ModifyDateTime");
      this.setTitle(bundle.getString("ModifyDateTime.title"));
      this.setName("ModifyDateTime");
      this.setResizable(false);
      this.jBOk.setText(bundle.getString("ModifyDateTime.jBOk.text"));
      this.jBOk.setName("jBOk");
      this.jBOk.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            ModifyDateTime.this.jBOkMousePressed(evt);
         }
      });
      this.jBCancel.setText(bundle.getString("ModifyDateTime.jBCancel.text"));
      this.jBCancel.setName("jBCancel");
      this.jBCancel.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            ModifyDateTime.this.jBCancelMousePressed(evt);
         }
      });
      this.jLDateLabel.setHorizontalAlignment(4);
      this.jLDateLabel.setText(bundle.getString("ModifyDateTime.jLDateLabel.text"));
      this.jLDateLabel.setName("jLDateLabel");
      this.jXDPdate.setName("jXDPdate");
      this.jLTimeLabel.setHorizontalAlignment(4);
      this.jLTimeLabel.setText(bundle.getString("ModifyDateTime.jLTimeLabel.text"));
      this.jLTimeLabel.setName("jLTimeLabel");
      this.jSpinnerTime.setName("jSpinnerTime");
      this.jLbFormatErr.setForeground(Color.red);
      this.jLbFormatErr.setText(bundle.getString("ModifyDateTime.jLbFormatErr.text"));
      this.jLbFormatErr.setName("jLbFormatErr");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               layout.createSequentialGroup()
                  .addGroup(
                     layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addContainerGap()
                              .addGroup(
                                 layout.createParallelGroup(Alignment.TRAILING)
                                    .addComponent(this.jLbFormatErr)
                                    .addGroup(
                                       layout.createSequentialGroup()
                                          .addComponent(this.jBOk)
                                          .addPreferredGap(ComponentPlacement.RELATED)
                                          .addComponent(this.jBCancel)
                                    )
                              )
                        )
                        .addGroup(
                           layout.createSequentialGroup()
                              .addGroup(
                                 layout.createParallelGroup(Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup().addComponent(this.jLDateLabel, -1, 80, 32767).addGap(1, 1, 1))
                                    .addGroup(
                                       layout.createSequentialGroup()
                                          .addContainerGap()
                                          .addComponent(this.jLTimeLabel, -2, 55, -2)
                                          .addPreferredGap(ComponentPlacement.RELATED)
                                    )
                              )
                              .addGroup(
                                 layout.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(this.jSpinnerTime)
                                    .addComponent(this.jXDPdate, -1, -1, 32767)
                              )
                        )
                  )
                  .addGap(27, 27, 27)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLDateLabel).addComponent(this.jXDPdate, -2, -1, -2))
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jSpinnerTime, -2, -1, -2).addComponent(this.jLTimeLabel))
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jLbFormatErr)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jBCancel).addComponent(this.jBOk))
                  .addGap(11, 11, 11)
            )
      );
      this.getAccessibleContext().setAccessibleName(bundle.getString("ModifyDateTime.AccessibleContext.accessibleName"));
      this.pack();
   }

   private void jBOkMousePressed(MouseEvent evt) {
      this.isDateChanged = true;
      Calendar calTmp = Calendar.getInstance();
      calTmp.clear();
      JFormattedTextField ftfTmp = ((DefaultEditor)this.jSpinnerTime.getEditor()).getTextField();
      String txt = ftfTmp.getText();
      int[] res = new int[2];
      boolean bRes = FAAC_Utilities.parseStr_HH_mm(txt, 23, 59, res);
      if (!bRes) {
         String sugg = "23:59";
         ((DefaultEditor)this.jSpinnerTime.getEditor()).getTextField().setText(sugg);
         ((DefaultEditor)this.jSpinnerTime.getEditor()).getTextField().setBackground(Color.red);
         this.jLbFormatErr.setEnabled(true);
      } else {
         if (this.jLbFormatErr.isVisible()) {
            ((DefaultEditor)this.jSpinnerTime.getEditor()).getTextField().setBackground(SystemColor.activeCaption);
            this.jLbFormatErr.setEnabled(false);
         }

         Date dateTmp = (Date)this.jSpinnerTime.getValue();
         calTmp.setTime(dateTmp);
         calTmp.set(12, res[0]);
         calTmp.set(11, res[1]);
         int iyear = calTmp.get(1);
         this.iMinutes = calTmp.get(12);
         this.iHour = calTmp.get(11);
         calTmp.clear();
         dateTmp = this.jXDPdate.getDate();
         calTmp.setTime(dateTmp);
         this.iDay = calTmp.get(5);
         this.iDayWeek = calTmp.get(7);
         this.iMonth = calTmp.get(2);
         this.iYear = calTmp.get(1);
         this.calendar.clear();
         this.calendar.set(this.iYear, this.iMonth, this.iDay, this.iHour, this.iMinutes, 0);
         this.setVisible(false);
      }
   }

   private void jBCancelMousePressed(MouseEvent evt) {
      this.isDateChanged = false;
      this.setVisible(false);
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
            ModifyDateTime dialog = new ModifyDateTime(new JFrame(), true, GregorianCalendar.getInstance());
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

   public Calendar getCalendar() {
      return this.calendar;
   }

   public void setCalendar(Calendar calendar) {
      this.calendar = calendar;
      this.init();
   }

   public boolean getIsDateChanged() {
      return this.isDateChanged;
   }

   public void setIsDateChanged(boolean isDateChanged) {
      this.isDateChanged = isDateChanged;
   }
}
