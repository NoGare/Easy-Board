package FAACbeans;

import FAAClib.FAAC_Timer;
import FAAClib.KeyValue;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FAACTimerDetail extends JDialog {
   private JButton jBtCancel;
   private JButton jBtOk;
   private JComboBox jCbTimerFunction;
   private JCheckBox jChbAll;
   private JCheckBox jChbDay_0;
   private JCheckBox jChbDay_1;
   private JCheckBox jChbDay_2;
   private JCheckBox jChbDay_3;
   private JCheckBox jChbDay_4;
   private JCheckBox jChbDay_5;
   private JCheckBox jChbDay_6;
   private JCheckBox jChbDay_7;
   private JCheckBox jCheckBox1;
   private JLabel jLtimeEndLabel;
   private JLabel jLtimeStartLabel;
   private JLabel jLworkingModeLabel;
   private JSpinner jSpTimeEnd;
   private JSpinner jSpTimeStart;
   private JPanel jpDays;
   private FAAC_Timer boardTimer;
   ResourceBundle bundle;
   private boolean modifyFlag;
   private FAAC_Timer.TimerEvent eventToBeMod;
   private FAAC_Timer.TimerEvent defaultEvent;
   private boolean isOk;

   public FAACTimerDetail(Frame parent, boolean modal, FAAC_Timer boardTimer, boolean mod, FAAC_Timer.TimerEvent eventToBeMod) {
      super(parent, modal);
      this.initComponents();
      this.setLocationRelativeTo(parent);
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACTimerDetail");
      this.boardTimer = boardTimer;
      this.modifyFlag = mod;
      this.eventToBeMod = eventToBeMod;
      this.isOk = false;
      FAAC_Utilities.setSpinnerHourMinModel(this.jSpTimeStart, 23, 59);
      FAAC_Utilities.disableSpinnerEditing(this.jSpTimeStart);
      FAAC_Utilities.setSpinnerHourMinModel(this.jSpTimeEnd, 23, 59);
      FAAC_Utilities.disableSpinnerEditing(this.jSpTimeEnd);
      this.jCbTimerFunction.removeAllItems();
      KeyValue kvTmp = new KeyValue(0, this.bundle.getString("timerDescr00_noFunction"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(1, this.bundle.getString("timerDescr01_closed"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(2, this.bundle.getString("timerDescr02_openA"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(3, this.bundle.getString("timerDescr03_openB"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(4, this.bundle.getString("timerDescr04_esclIn1"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(5, this.bundle.getString("timerDescr05_esclIn1In2"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(6, this.bundle.getString("timerDescr06_esclRadio"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(7, this.bundle.getString("timerDescr07_esclRadioDec"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(8, this.bundle.getString("timerDescr08_esclRadioXf"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(9, this.bundle.getString("timerDescr09_out1"));
      this.jCbTimerFunction.addItem(kvTmp);
      kvTmp = new KeyValue(10, this.bundle.getString("timerDescr10_out2"));
      this.jCbTimerFunction.addItem(kvTmp);
      if (mod) {
         this.initDialog(eventToBeMod);
      } else if (eventToBeMod == null) {
         this.defaultEvent = new FAAC_Timer.TimerEvent(0);
         Calendar calTmp = GregorianCalendar.getInstance();
         calTmp.clear();
         calTmp.add(11, 8);
         calTmp.add(12, 30);
         this.defaultEvent.setBeginDateExact(calTmp);
         Calendar calTmp2 = GregorianCalendar.getInstance();
         calTmp2.clear();
         calTmp2.add(11, 12);
         calTmp2.add(12, 30);
         this.defaultEvent.setEndDateExact(calTmp2);
         this.defaultEvent.setValueFunction(0);
         this.defaultEvent.setDescription("timerDescr00_noFunction");
         this.initDialog(this.defaultEvent);
      } else {
         this.initDialog(eventToBeMod);
      }
   }

   private void initDialog(FAAC_Timer.TimerEvent event) {
      this.jSpTimeStart.setValue(event.getBeginDateExact().getTime());
      this.jSpTimeEnd.setValue(event.getEndDateExact().getTime());
      KeyValue kvTmp = this.getjCbTimerFunctionItemFromKey(event.getValueFunction());
      this.jCbTimerFunction.setSelectedItem(kvTmp);
      switch (event.getIndexDay()) {
         case 0:
            this.jChbDay_0.setSelected(true);
            break;
         case 1:
            this.jChbDay_1.setSelected(true);
            break;
         case 2:
            this.jChbDay_2.setSelected(true);
            break;
         case 3:
            this.jChbDay_3.setSelected(true);
            break;
         case 4:
            this.jChbDay_4.setSelected(true);
            break;
         case 5:
            this.jChbDay_5.setSelected(true);
            break;
         case 6:
            this.jChbDay_6.setSelected(true);
            break;
         case 7:
            this.jChbDay_7.setSelected(true);
      }
   }

   private KeyValue getjCbTimerFunctionItemFromKey(int key) {
      new KeyValue();

      for (int i = 0; i < this.jCbTimerFunction.getItemCount(); i++) {
         KeyValue kvTmp = (KeyValue)this.jCbTimerFunction.getItemAt(i);
         if (Integer.parseInt(kvTmp.getKey().toString()) == key) {
            return kvTmp;
         }
      }

      return null;
   }

   private void initComponents() {
      this.jCheckBox1 = new JCheckBox();
      this.jSpTimeStart = new JSpinner();
      this.jLtimeStartLabel = new JLabel();
      this.jSpTimeEnd = new JSpinner();
      this.jLtimeEndLabel = new JLabel();
      this.jCbTimerFunction = new JComboBox();
      this.jLworkingModeLabel = new JLabel();
      this.jpDays = new JPanel();
      this.jChbDay_0 = new JCheckBox();
      this.jChbDay_1 = new JCheckBox();
      this.jChbDay_2 = new JCheckBox();
      this.jChbDay_3 = new JCheckBox();
      this.jChbDay_4 = new JCheckBox();
      this.jChbDay_5 = new JCheckBox();
      this.jChbDay_6 = new JCheckBox();
      this.jChbDay_7 = new JCheckBox();
      this.jChbAll = new JCheckBox();
      this.jBtOk = new JButton();
      this.jBtCancel = new JButton();
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACTimerDetail");
      this.jCheckBox1.setText(bundle.getString("FAACTimerDetail.jCheckBox1.text"));
      this.jCheckBox1.setName("jCheckBox1");
      this.setDefaultCloseOperation(2);
      this.setTitle(bundle.getString("FAACTimerDetail.title"));
      this.setIconImage(null);
      this.setName("FAACTimerDetail");
      this.setResizable(false);
      this.jSpTimeStart.setModel(new SpinnerDateModel());
      this.jSpTimeStart.setName("jSpTimeStart");
      this.jLtimeStartLabel.setHorizontalAlignment(4);
      this.jLtimeStartLabel.setText(bundle.getString("FAACTimerDetail.jLtimeStartLabel.text"));
      this.jLtimeStartLabel.setName("jLtimeStartLabel");
      this.jSpTimeEnd.setModel(new SpinnerDateModel());
      this.jSpTimeEnd.setName("jSpTimeEnd");
      this.jLtimeEndLabel.setHorizontalAlignment(4);
      this.jLtimeEndLabel.setText(bundle.getString("FAACTimerDetail.jLtimeEndLabel.text"));
      this.jLtimeEndLabel.setName("jLtimeEndLabel");
      this.jCbTimerFunction
         .setModel(
            new DefaultComboBoxModel<>(
               new String[]{
                  "Nessuna azione",
                  "Sempre chiuso",
                  "Open A",
                  "Open B",
                  "Escludi ingresso 1",
                  "Escludi ingressi 1 e 2",
                  "Escludi radio",
                  "Escludi radio dec",
                  "Escludi radio XF",
                  "Attiva uscita 1",
                  "Attiva uscita 2"
               }
            )
         );
      this.jCbTimerFunction.setLightWeightPopupEnabled(false);
      this.jCbTimerFunction.setName("jCbTimerFunction");
      this.jLworkingModeLabel.setHorizontalAlignment(4);
      this.jLworkingModeLabel.setText(bundle.getString("FAACTimerDetail.jLworkingModeLabel.text"));
      this.jLworkingModeLabel.setName("jLworkingModeLabel");
      this.jpDays.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACTimerDetail.jpDays.border.title")));
      this.jpDays.setName("jpDays");
      this.jChbDay_0.setText(bundle.getString("FAACTimerDetail.jChbDay_0.text"));
      this.jChbDay_0.setName("jChbDay_0");
      this.jChbDay_1.setText(bundle.getString("FAACTimerDetail.jChbDay_1.text"));
      this.jChbDay_1.setName("jChbDay_1");
      this.jChbDay_2.setText(bundle.getString("FAACTimerDetail.jChbDay_2.text"));
      this.jChbDay_2.setName("jChbDay_2");
      this.jChbDay_3.setText(bundle.getString("FAACTimerDetail.jChbDay_3.text"));
      this.jChbDay_3.setName("jChbDay_3");
      this.jChbDay_4.setText(bundle.getString("FAACTimerDetail.jChbDay_4.text"));
      this.jChbDay_4.setName("jChbDay_4");
      this.jChbDay_5.setText(bundle.getString("FAACTimerDetail.jChbDay_5.text"));
      this.jChbDay_5.setName("jChbDay_5");
      this.jChbDay_6.setText(bundle.getString("FAACTimerDetail.jChbDay_6.text"));
      this.jChbDay_6.setName("jChbDay_6");
      this.jChbDay_7.setText(bundle.getString("FAACTimerDetail.jChbDay_7.text"));
      this.jChbDay_7.setName("jChbDay_7");
      this.jChbAll.setText(bundle.getString("FAACTimerDetail.jChbAll.text"));
      this.jChbAll.setToolTipText(bundle.getString("FAACTimerDetail.jChbAll.toolTipText"));
      this.jChbAll.setName("jChbAll");
      this.jChbAll.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            FAACTimerDetail.this.jChbAllStateChanged(evt);
         }
      });
      GroupLayout jpDaysLayout = new GroupLayout(this.jpDays);
      this.jpDays.setLayout(jpDaysLayout);
      jpDaysLayout.setHorizontalGroup(
         jpDaysLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jpDaysLayout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     jpDaysLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(
                           jpDaysLayout.createSequentialGroup()
                              .addComponent(this.jChbDay_0)
                              .addPreferredGap(ComponentPlacement.RELATED, 42, 32767)
                              .addComponent(this.jChbAll)
                              .addGap(70, 70, 70)
                        )
                        .addGroup(jpDaysLayout.createSequentialGroup().addComponent(this.jChbDay_1).addContainerGap(207, 32767))
                        .addGroup(
                           jpDaysLayout.createSequentialGroup()
                              .addGroup(
                                 jpDaysLayout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jChbDay_2)
                                    .addComponent(this.jChbDay_3)
                                    .addComponent(this.jChbDay_4)
                                    .addComponent(this.jChbDay_5)
                                    .addComponent(this.jChbDay_6)
                                    .addComponent(this.jChbDay_7)
                              )
                              .addContainerGap(193, 32767)
                        )
                  )
            )
      );
      jpDaysLayout.setVerticalGroup(
         jpDaysLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jpDaysLayout.createSequentialGroup()
                  .addGroup(jpDaysLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.jChbDay_0).addComponent(this.jChbAll))
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jChbDay_1)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jChbDay_2)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jChbDay_3)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jChbDay_4)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jChbDay_5)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jChbDay_6)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jChbDay_7)
                  .addContainerGap(-1, 32767)
            )
      );
      this.jBtOk.setText(bundle.getString("FAACTimerDetail.jBtOk.text"));
      this.jBtOk.setName("jBtOk");
      this.jBtOk.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimerDetail.this.jBtOkMousePressed(evt);
         }
      });
      this.jBtCancel.setText(bundle.getString("FAACTimerDetail.jBtCancel.text"));
      this.jBtCancel.setName("jBtCancel");
      this.jBtCancel.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACTimerDetail.this.jBtCancelMousePressed(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jpDays, -1, -1, 32767)
                        .addGroup(
                           Alignment.TRAILING,
                           layout.createSequentialGroup().addComponent(this.jBtOk).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jBtCancel)
                        )
                        .addGroup(
                           layout.createSequentialGroup()
                              .addGroup(
                                 layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jLtimeStartLabel, Alignment.TRAILING, -1, 73, 32767)
                                    .addComponent(this.jLtimeEndLabel, Alignment.TRAILING, -1, 73, 32767)
                                    .addComponent(this.jLworkingModeLabel, Alignment.TRAILING, -1, 73, 32767)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jCbTimerFunction, -2, 209, -2)
                                    .addGroup(
                                       layout.createParallelGroup(Alignment.TRAILING, false)
                                          .addComponent(this.jSpTimeEnd, Alignment.LEADING, 0, 0, 32767)
                                          .addComponent(this.jSpTimeStart, Alignment.LEADING, -2, 71, 32767)
                                    )
                              )
                        )
                  )
                  .addContainerGap()
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGap(16, 16, 16)
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLtimeStartLabel).addComponent(this.jSpTimeStart, -2, -1, -2))
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLtimeEndLabel).addComponent(this.jSpTimeEnd, -2, -1, -2))
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLworkingModeLabel).addComponent(this.jCbTimerFunction, -2, -1, -2)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jpDays, -2, -1, -2)
                  .addPreferredGap(ComponentPlacement.RELATED, 11, 32767)
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jBtCancel).addComponent(this.jBtOk))
                  .addContainerGap()
            )
      );
      this.getAccessibleContext().setAccessibleName(bundle.getString("FAACTimerDetail.AccessibleContext.accessibleName"));
      this.pack();
   }

   private void saveEventModifications() {
      int iDayIndex = this.eventToBeMod.getIndexDay();
      int value = Integer.parseInt(((KeyValue)this.jCbTimerFunction.getSelectedItem()).getKey().toString());
      String description = ((KeyValue)this.jCbTimerFunction.getSelectedItem()).getValue().toString();
      Date dateStartExact = (Date)this.jSpTimeStart.getValue();
      Calendar calStart = Calendar.getInstance();
      calStart.setTime(dateStartExact);
      Date dateEndExact = (Date)this.jSpTimeEnd.getValue();
      Calendar calEnd = Calendar.getInstance();
      calEnd.setTime(dateEndExact);
      boolean toBeDel = false;
      switch (iDayIndex) {
         case 0:
            if (!this.jChbDay_0.isSelected()) {
               toBeDel = true;
            }
            break;
         case 1:
            if (!this.jChbDay_1.isSelected()) {
               toBeDel = true;
            }
            break;
         case 2:
            if (!this.jChbDay_2.isSelected()) {
               toBeDel = true;
            }
            break;
         case 3:
            if (!this.jChbDay_3.isSelected()) {
               toBeDel = true;
            }
            break;
         case 4:
            if (!this.jChbDay_4.isSelected()) {
               toBeDel = true;
            }
            break;
         case 5:
            if (!this.jChbDay_5.isSelected()) {
               toBeDel = true;
            }
            break;
         case 6:
            if (!this.jChbDay_6.isSelected()) {
               toBeDel = true;
            }
            break;
         case 7:
            if (!this.jChbDay_7.isSelected()) {
               toBeDel = true;
            }
      }

      if (toBeDel) {
         this.eventToBeMod.setValueFunction(0);
      } else {
         boolean bCanModify = this.boardTimer.canModifyEvent(iDayIndex, calStart, calEnd, this.eventToBeMod);
         if (!bCanModify) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("OverlappingEvent_message"), this.bundle.getString("Attention"), 2);
            return;
         }

         this.eventToBeMod.setValueFunction(value);
         this.eventToBeMod.setDescription(description);
         this.eventToBeMod.setBeginDateExact(calStart);
         this.eventToBeMod.setEndDateExact(calEnd);
         this.eventToBeMod.ExactTimerRange2RoundedTimerRange();
      }

      boolean bDay0Full = false;
      boolean bDay0Overlap = false;
      if (iDayIndex != 0 && this.jChbDay_0.isSelected()) {
         boolean bCanAdd_0 = this.boardTimer.canAddEvent(0, calStart, calEnd);
         if (bCanAdd_0) {
            boolean bRes = this.boardTimer.AddEvent(0, calStart, calEnd, value, description);
            if (!bRes) {
               bDay0Full = true;
            }
         } else {
            bDay0Overlap = true;
         }
      }

      boolean bDay1Full = false;
      boolean bDay1Overlap = false;
      if (iDayIndex != 1 && this.jChbDay_1.isSelected()) {
         boolean bCanAdd_1 = this.boardTimer.canAddEvent(1, calStart, calEnd);
         if (bCanAdd_1) {
            boolean bRes = this.boardTimer.AddEvent(1, calStart, calEnd, value, description);
            if (!bRes) {
               bDay1Full = true;
            }
         } else {
            bDay1Overlap = true;
         }
      }

      boolean bDay2Full = false;
      boolean bDay2Overlap = false;
      if (iDayIndex != 2 && this.jChbDay_2.isSelected()) {
         boolean bCanAdd_2 = this.boardTimer.canAddEvent(2, calStart, calEnd);
         if (bCanAdd_2) {
            boolean bRes = this.boardTimer.AddEvent(2, calStart, calEnd, value, description);
            if (!bRes) {
               bDay2Full = true;
            }
         } else {
            bDay2Overlap = true;
         }
      }

      boolean bDay3Full = false;
      boolean bDay3Overlap = false;
      if (iDayIndex != 3 && this.jChbDay_3.isSelected()) {
         boolean bCanAdd_3 = this.boardTimer.canAddEvent(3, calStart, calEnd);
         if (bCanAdd_3) {
            boolean bRes = this.boardTimer.AddEvent(3, calStart, calEnd, value, description);
            if (!bRes) {
               bDay3Full = true;
            }
         } else {
            bDay3Overlap = true;
         }
      }

      boolean bDay4Full = false;
      boolean bDay4Overlap = false;
      if (iDayIndex != 4 && this.jChbDay_4.isSelected()) {
         boolean bCanAdd_4 = this.boardTimer.canAddEvent(4, calStart, calEnd);
         if (bCanAdd_4) {
            boolean bRes = this.boardTimer.AddEvent(4, calStart, calEnd, value, description);
            if (!bRes) {
               bDay4Full = true;
            }
         } else {
            bDay4Overlap = true;
         }
      }

      boolean bDay5Full = false;
      boolean bDay5Overlap = false;
      if (iDayIndex != 5 && this.jChbDay_5.isSelected()) {
         boolean bCanAdd_5 = this.boardTimer.canAddEvent(5, calStart, calEnd);
         if (bCanAdd_5) {
            boolean bRes = this.boardTimer.AddEvent(5, calStart, calEnd, value, description);
            if (!bRes) {
               bDay5Full = true;
            }
         } else {
            bDay5Overlap = true;
         }
      }

      boolean bDay6Full = false;
      boolean bDay6Overlap = false;
      if (iDayIndex != 6 && this.jChbDay_6.isSelected()) {
         boolean bCanAdd_6 = this.boardTimer.canAddEvent(6, calStart, calEnd);
         if (bCanAdd_6) {
            boolean bRes = this.boardTimer.AddEvent(6, calStart, calEnd, value, description);
            if (!bRes) {
               bDay6Full = true;
            }
         } else {
            bDay6Overlap = true;
         }
      }

      boolean bDay7Full = false;
      boolean bDay7Overlap = false;
      if (iDayIndex != 7 && this.jChbDay_7.isSelected()) {
         boolean bCanAdd_7 = this.boardTimer.canAddEvent(7, calStart, calEnd);
         if (bCanAdd_7) {
            boolean bRes = this.boardTimer.AddEvent(7, calStart, calEnd, value, description);
            if (!bRes) {
               bDay7Full = true;
            }
         } else {
            bDay7Overlap = true;
         }
      }

      StringBuilder strBld = new StringBuilder("");
      if (bDay0Full) {
         strBld.append(this.bundle.getString("fullDay0_message"));
      }

      if (bDay1Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay1_message"));
      }

      if (bDay2Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay2_message"));
      }

      if (bDay3Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay3_message"));
      }

      if (bDay4Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay4_message"));
      }

      if (bDay5Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay5_message"));
      }

      if (bDay6Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay6_message"));
      }

      if (bDay7Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay7_message"));
      }

      if (bDay0Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay0_message"));
      }

      if (bDay1Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay1_message"));
      }

      if (bDay2Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay2_message"));
      }

      if (bDay3Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay3_message"));
      }

      if (bDay4Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay4_message"));
      }

      if (bDay5Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay5_message"));
      }

      if (bDay6Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay6_message"));
      }

      if (bDay7Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay7_message"));
      }

      if (strBld.toString().length() > 0) {
         JOptionPane.showMessageDialog(this, this.bundle.getString("errorModMsg") + "\n" + strBld.toString(), this.bundle.getString("Attention"), 2);
      }
   }

   private void addEvent() {
      int value = Integer.parseInt(((KeyValue)this.jCbTimerFunction.getSelectedItem()).getKey().toString());
      String description = ((KeyValue)this.jCbTimerFunction.getSelectedItem()).getValue().toString();
      Date dateStartExact = (Date)this.jSpTimeStart.getValue();
      Calendar calStart = Calendar.getInstance();
      calStart.setTime(dateStartExact);
      Date dateEndExact = (Date)this.jSpTimeEnd.getValue();
      Calendar calEnd = Calendar.getInstance();
      calEnd.setTime(dateEndExact);
      boolean bDay0Full = false;
      boolean bDay0Overlap = false;
      if (this.jChbDay_0.isSelected()) {
         boolean bCanAdd_0 = this.boardTimer.canAddEvent(0, calStart, calEnd);
         if (bCanAdd_0) {
            boolean bRes = this.boardTimer.AddEvent(0, calStart, calEnd, value, description);
            if (!bRes) {
               bDay0Full = true;
            }
         } else {
            bDay0Overlap = true;
         }
      }

      boolean bDay1Full = false;
      boolean bDay1Overlap = false;
      if (this.jChbDay_1.isSelected()) {
         boolean bCanAdd_1 = this.boardTimer.canAddEvent(1, calStart, calEnd);
         if (bCanAdd_1) {
            boolean bRes = this.boardTimer.AddEvent(1, calStart, calEnd, value, description);
            if (!bRes) {
               bDay1Full = true;
            }
         } else {
            bDay1Overlap = true;
         }
      }

      boolean bDay2Full = false;
      boolean bDay2Overlap = false;
      if (this.jChbDay_2.isSelected()) {
         boolean bCanAdd_2 = this.boardTimer.canAddEvent(2, calStart, calEnd);
         if (bCanAdd_2) {
            boolean bRes = this.boardTimer.AddEvent(2, calStart, calEnd, value, description);
            if (!bRes) {
               bDay2Full = true;
            }
         } else {
            bDay2Overlap = true;
         }
      }

      boolean bDay3Full = false;
      boolean bDay3Overlap = false;
      if (this.jChbDay_3.isSelected()) {
         boolean bCanAdd_3 = this.boardTimer.canAddEvent(3, calStart, calEnd);
         if (bCanAdd_3) {
            boolean bRes = this.boardTimer.AddEvent(3, calStart, calEnd, value, description);
            if (!bRes) {
               bDay3Full = true;
            }
         } else {
            bDay3Overlap = true;
         }
      }

      boolean bDay4Full = false;
      boolean bDay4Overlap = false;
      if (this.jChbDay_4.isSelected()) {
         boolean bCanAdd_4 = this.boardTimer.canAddEvent(4, calStart, calEnd);
         if (bCanAdd_4) {
            boolean bRes = this.boardTimer.AddEvent(4, calStart, calEnd, value, description);
            if (!bRes) {
               bDay4Full = true;
            }
         } else {
            bDay4Overlap = true;
         }
      }

      boolean bDay5Full = false;
      boolean bDay5Overlap = false;
      if (this.jChbDay_5.isSelected()) {
         boolean bCanAdd_5 = this.boardTimer.canAddEvent(5, calStart, calEnd);
         if (bCanAdd_5) {
            boolean bRes = this.boardTimer.AddEvent(5, calStart, calEnd, value, description);
            if (!bRes) {
               bDay5Full = true;
            }
         } else {
            bDay5Overlap = true;
         }
      }

      boolean bDay6Full = false;
      boolean bDay6Overlap = false;
      if (this.jChbDay_6.isSelected()) {
         boolean bCanAdd_6 = this.boardTimer.canAddEvent(6, calStart, calEnd);
         if (bCanAdd_6) {
            boolean bRes = this.boardTimer.AddEvent(6, calStart, calEnd, value, description);
            if (!bRes) {
               bDay6Full = true;
            }
         } else {
            bDay6Overlap = true;
         }
      }

      boolean bDay7Full = false;
      boolean bDay7Overlap = false;
      if (this.jChbDay_7.isSelected()) {
         boolean bCanAdd_7 = this.boardTimer.canAddEvent(7, calStart, calEnd);
         if (bCanAdd_7) {
            boolean bRes = this.boardTimer.AddEvent(7, calStart, calEnd, value, description);
            if (!bRes) {
               bDay7Full = true;
            }
         } else {
            bDay7Overlap = true;
         }
      }

      StringBuilder strBld = new StringBuilder("");
      if (bDay0Full) {
         strBld.append(this.bundle.getString("fullDay0_message"));
      }

      if (bDay1Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay1_message"));
      }

      if (bDay2Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay2_message"));
      }

      if (bDay3Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay3_message"));
      }

      if (bDay4Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay4_message"));
      }

      if (bDay5Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay5_message"));
      }

      if (bDay6Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay6_message"));
      }

      if (bDay7Full) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("fullDay7_message"));
      }

      if (bDay0Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay0_message"));
      }

      if (bDay1Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay1_message"));
      }

      if (bDay2Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay2_message"));
      }

      if (bDay3Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay3_message"));
      }

      if (bDay4Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay4_message"));
      }

      if (bDay5Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay5_message"));
      }

      if (bDay6Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay6_message"));
      }

      if (bDay7Overlap) {
         if (strBld.toString().length() > 0) {
            strBld.append("\n");
         }

         strBld.append(this.bundle.getString("overlapDay7_message"));
      }

      if (strBld.toString().length() > 0) {
         JOptionPane.showMessageDialog(this, this.bundle.getString("errorAddMsg") + "\n" + strBld.toString(), this.bundle.getString("Attention"), 2);
      }
   }

   private void jChbAllStateChanged(ChangeEvent evt) {
      if (this.jChbAll.isSelected()) {
         Component[] cbDays = this.jpDays.getComponents();

         for (int i = 0; i < cbDays.length; i++) {
            if (cbDays[i] instanceof JCheckBox && !cbDays[i].getName().equals("jChbAll")) {
               ((JCheckBox)cbDays[i]).setSelected(this.jChbAll.isSelected());
            }
         }
      }
   }

   private void jBtOkMousePressed(MouseEvent evt) {
      if (this.modifyFlag) {
         this.saveEventModifications();
      } else {
         this.addEvent();
      }

      this.isOk = true;
      this.setVisible(false);
   }

   private void jBtCancelMousePressed(MouseEvent evt) {
      this.isOk = false;
      this.setVisible(false);
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
            FAACTimerDetail dialog = new FAACTimerDetail(new JFrame(), true, new FAAC_Timer(), true, null);
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
