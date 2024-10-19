package FAACbeans;

import FAAClib.E124_Settings;
import FAAClib.E145_Settings;
import FAAClib.FAAC_Settings;
import FAAClib.KeyValue;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CfgWizardPanel2 extends JPanel {
   private final FAAC_Settings boardSettings;
   private String boardModel;
   private ButtonGroup btGrpNbEngines;
   private JComboBox jCbMotorType;
   private JLabel jLbDelayClose;
   private JLabel jLbForce;
   private JLabel jLbForce1;
   private JLabel jLbForce2;
   private JLabel jLbMotor1;
   private JLabel jLbMotor2;
   private JLabel jLbNbMotor;
   private JLabel jLbStep;
   private JLabel jLbStep1;
   private JLabel jLbType;
   private JLabel jLbmmss;
   private JPanel jPfigureStep1;
   private JRadioButton jRbNb1;
   private JRadioButton jRbNb2;
   private JSlider jSliderForce1;
   private JSlider jSliderForce2;
   private JSpinner jSpinnerDelayClose;
   ResourceBundle bundle;

   public CfgWizardPanel2(FAAC_Settings boardSettings, String boardModel) {
      this.initComponents();
      FAAC_Utilities.listenForManualEditIntSpinner(
         this.jSpinnerDelayClose,
         ((SpinnerNumberModel)this.jSpinnerDelayClose.getModel()).getMinimum(),
         ((SpinnerNumberModel)this.jSpinnerDelayClose.getModel()).getMaximum()
      );
      this.boardSettings = boardSettings;
      this.boardModel = boardModel;
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/CfgWizardPanel2");
      this.jCbMotorType.removeAllItems();
      if (this.boardModel.compareToIgnoreCase("E145") == 0) {
         KeyValue kvTmp = new KeyValue(0, this.bundle.getString("typeE145_Battente"));
         this.jCbMotorType.addItem(kvTmp);
         kvTmp = new KeyValue(1, this.bundle.getString("typeE145_Sliding"));
         this.jCbMotorType.addItem(kvTmp);
         if (boardSettings.getMotor1Type() == E145_Settings.MotorTypeScorrevole) {
            kvTmp = this.getjCbMotorTypeItemFromKey(boardSettings.getMotor1Type());
            this.jCbMotorType.setSelectedItem(kvTmp);
         } else {
            kvTmp = this.getjCbMotorTypeItemFromKey(0);
            this.jCbMotorType.setSelectedItem(kvTmp);
         }

         File fileToParse = new File(E145_Settings.defFile);
         boardSettings.loadDefaultPrgFileForBoard(fileToParse, "E145");
      } else {
         KeyValue kvTmp = new KeyValue(0, E124_Settings.MotorTypeStr0_NoFAAC);
         this.jCbMotorType.addItem(kvTmp);
         kvTmp = new KeyValue(1, E124_Settings.MotorTypeStr1_413_415);
         this.jCbMotorType.addItem(kvTmp);
         kvTmp = new KeyValue(2, E124_Settings.MotorTypeStr2_391);
         this.jCbMotorType.addItem(kvTmp);
         kvTmp = new KeyValue(3, E124_Settings.MotorTypeStr3_S700_S800);
         this.jCbMotorType.addItem(kvTmp);
         kvTmp = new KeyValue(4, E124_Settings.MotorTypeStr4_418);
         this.jCbMotorType.addItem(kvTmp);
         kvTmp = new KeyValue(5, E124_Settings.MotorTypeStr5_450H);
         this.jCbMotorType.addItem(kvTmp);
         kvTmp = new KeyValue(6, E124_Settings.MotorTypeStr6_S800H_ENC);
         this.jCbMotorType.addItem(kvTmp);
         int motorType = boardSettings.getMotor1Type();
         File fileToParse = new File(E124_Settings.getDefFileForMotor(motorType));
         boardSettings.loadDefaultPrgFileForBoard(fileToParse, "E124");
      }

      this.initPanel();
   }

   public final void initPanel() {
      int force1 = this.boardSettings.getForceOpenMotor1();
      int force2 = this.boardSettings.getForceOpenMotor2();
      if (this.boardModel.compareToIgnoreCase("E145") == 0) {
         if (this.boardSettings.getMotor1Type() == 1) {
            KeyValue kvTmp = this.getjCbMotorTypeItemFromKey(1);
            this.jCbMotorType.setSelectedItem(kvTmp);
         } else {
            KeyValue kvTmp = this.getjCbMotorTypeItemFromKey(0);
            this.jCbMotorType.setSelectedItem(kvTmp);
         }
      } else {
         KeyValue kvTmp = this.getjCbMotorTypeItemFromKey(this.boardSettings.getMotor1Type());
         this.jCbMotorType.setSelectedItem(kvTmp);
      }

      if (this.boardSettings.getProgrammingFlag().get00_SingleMotor()) {
         this.jRbNb1.setSelected(true);
         this.jRbNb2.setSelected(false);
         this.jSpinnerDelayClose.setEnabled(false);
      } else {
         this.jRbNb1.setSelected(false);
         this.jRbNb2.setSelected(true);
         this.jSpinnerDelayClose.setEnabled(true);
      }

      int iRitAntaClose_s = this.boardSettings.getTimeDelayCloseAnta_sec();
      this.jSpinnerDelayClose.setValue(iRitAntaClose_s);
      this.jSliderForce1.setValue(force1);
      this.jLbForce1.setText(String.valueOf(force1));
      this.jLbForce2.setText(String.valueOf(force2));
      this.jSliderForce2.setValue(force2);
   }

   private KeyValue getjCbMotorTypeItemFromKey(int key) {
      new KeyValue();

      for (int i = 0; i < this.jCbMotorType.getItemCount(); i++) {
         KeyValue kvTmp = (KeyValue)this.jCbMotorType.getItemAt(i);
         if (Integer.parseInt(kvTmp.getKey().toString()) == key) {
            return kvTmp;
         }
      }

      return null;
   }

   private void initComponents() {
      this.btGrpNbEngines = new ButtonGroup();
      this.jPfigureStep1 = new JPanel();
      this.jLbStep1 = new JLabel();
      this.jLbStep = new JLabel();
      this.jLbNbMotor = new JLabel();
      this.jRbNb1 = new JRadioButton();
      this.jRbNb2 = new JRadioButton();
      this.jLbForce = new JLabel();
      this.jSliderForce1 = new JSlider();
      this.jLbMotor1 = new JLabel();
      this.jLbMotor2 = new JLabel();
      this.jSliderForce2 = new JSlider();
      this.jLbForce1 = new JLabel();
      this.jLbForce2 = new JLabel();
      this.jLbDelayClose = new JLabel();
      this.jSpinnerDelayClose = new JSpinner();
      this.jLbmmss = new JLabel();
      this.jLbType = new JLabel();
      this.jCbMotorType = new JComboBox();
      this.setName("CfgWizardPanel2");
      this.setPreferredSize(new Dimension(450, 272));
      this.jPfigureStep1.setName("jPfigureStep1");
      this.jLbStep1.setIcon(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/wizard_1.png")));
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/CfgWizardPanel2");
      this.jLbStep1.setText(bundle.getString("CfgWizardPanel2.jLbStep1.text"));
      this.jLbStep1.setName("jLbStep1");
      GroupLayout jPfigureStep1Layout = new GroupLayout(this.jPfigureStep1);
      this.jPfigureStep1.setLayout(jPfigureStep1Layout);
      jPfigureStep1Layout.setHorizontalGroup(
         jPfigureStep1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPfigureStep1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLbStep1).addContainerGap(-1, 32767))
      );
      jPfigureStep1Layout.setVerticalGroup(
         jPfigureStep1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPfigureStep1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLbStep1).addContainerGap(-1, 32767))
      );
      this.jLbStep.setFont(new Font("Tahoma", 1, 14));
      this.jLbStep.setText(bundle.getString("CfgWizardPanel2.jLbStep.text"));
      this.jLbStep.setName("jLbStep");
      this.jLbNbMotor.setText(bundle.getString("CfgWizardPanel2.jLbNbMotor.text"));
      this.jLbNbMotor.setName("jLbNbMotor");
      this.btGrpNbEngines.add(this.jRbNb1);
      this.jRbNb1.setText(bundle.getString("CfgWizardPanel2.jRbNb1.text"));
      this.jRbNb1.setName("jRbNb1");
      this.jRbNb1.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel2.this.jRbNb1StateChanged(evt);
         }
      });
      this.btGrpNbEngines.add(this.jRbNb2);
      this.jRbNb2.setText(bundle.getString("CfgWizardPanel2.jRbNb2.text"));
      this.jRbNb2.setName("jRbNb2");
      this.jRbNb2.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel2.this.jRbNb2StateChanged(evt);
         }
      });
      this.jLbForce.setText(bundle.getString("CfgWizardPanel2.jLbForce.text"));
      this.jLbForce.setName("jLbForce");
      this.jSliderForce1.setMaximum(50);
      this.jSliderForce1.setMinimum(1);
      this.jSliderForce1.setValue(25);
      this.jSliderForce1.setName("jSliderForce1");
      this.jSliderForce1.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel2.this.jSliderForce1StateChanged(evt);
         }
      });
      this.jLbMotor1.setText(bundle.getString("CfgWizardPanel2.jLbMotor1.text"));
      this.jLbMotor1.setName("jLbMotor1");
      this.jLbMotor2.setText(bundle.getString("CfgWizardPanel2.jLbMotor2.text"));
      this.jLbMotor2.setName("jLbMotor2");
      this.jSliderForce2.setMaximum(50);
      this.jSliderForce2.setMinimum(1);
      this.jSliderForce2.setValue(25);
      this.jSliderForce2.setName("jSliderForce2");
      this.jSliderForce2.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel2.this.jSliderForce2StateChanged(evt);
         }
      });
      this.jLbForce1.setText(bundle.getString("CfgWizardPanel2.jLbForce1.text"));
      this.jLbForce1.setName("jLbForce1");
      this.jLbForce2.setText(bundle.getString("CfgWizardPanel2.jLbForce2.text"));
      this.jLbForce2.setName("jLbForce2");
      this.jLbDelayClose.setText(bundle.getString("CfgWizardPanel2.jLbDelayClose.text"));
      this.jLbDelayClose.setName("jLbDelayClose");
      this.jSpinnerDelayClose.setModel(new SpinnerNumberModel(0, 0, 90, 1));
      this.jSpinnerDelayClose.setToolTipText(bundle.getString("CfgWizardPanel2.jSpinnerDelayClose.toolTipText"));
      this.jSpinnerDelayClose.setName("jSpinnerDelayClose");
      this.jSpinnerDelayClose.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel2.this.jSpinnerDelayCloseStateChanged(evt);
         }
      });
      this.jLbmmss.setText(bundle.getString("CfgWizardPanel2.jLbmmss.text"));
      this.jLbmmss.setName("jLbmmss");
      this.jLbType.setText(bundle.getString("CfgWizardPanel2.jLbType.text"));
      this.jLbType.setName("jLbType");
      this.jCbMotorType.setName("jCbMotorType");
      this.jCbMotorType.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent evt) {
            CfgWizardPanel2.this.jCbMotorTypeItemStateChanged(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addComponent(this.jPfigureStep1, -2, -1, -2)
                  .addGap(31, 31, 31)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jLbStep)
                        .addComponent(this.jLbType)
                        .addComponent(this.jCbMotorType, -2, 164, -2)
                        .addComponent(this.jLbNbMotor)
                        .addGroup(layout.createSequentialGroup().addComponent(this.jRbNb1).addGap(18, 18, 18).addComponent(this.jRbNb2))
                        .addComponent(this.jLbDelayClose)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addComponent(this.jLbMotor2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jSliderForce2, -2, 171, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbForce2)
                        )
                        .addGroup(
                           layout.createSequentialGroup()
                              .addComponent(this.jSpinnerDelayClose, -2, 70, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbmmss)
                        )
                        .addComponent(this.jLbForce)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addComponent(this.jLbMotor1)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jSliderForce1, -2, 171, -2)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbForce1)
                        )
                  )
                  .addContainerGap(69, 32767)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jPfigureStep1, -2, -1, -2)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addContainerGap()
                              .addComponent(this.jLbStep)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbType)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jCbMotorType, -2, -1, -2)
                              .addGap(7, 7, 7)
                              .addComponent(this.jLbNbMotor)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jRbNb1).addComponent(this.jRbNb2))
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbForce)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.CENTER)
                                    .addComponent(this.jSliderForce1, -2, -1, -2)
                                    .addComponent(this.jLbMotor1)
                                    .addComponent(this.jLbForce1)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.CENTER)
                                    .addComponent(this.jSliderForce2, -2, -1, -2)
                                    .addComponent(this.jLbMotor2)
                                    .addComponent(this.jLbForce2)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbDelayClose)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.CENTER).addComponent(this.jSpinnerDelayClose, -2, -1, -2).addComponent(this.jLbmmss)
                              )
                        )
                  )
                  .addContainerGap(-1, 32767)
            )
      );
      this.getAccessibleContext().setAccessibleName(bundle.getString("CfgWizardPanel2.AccessibleContext.accessibleName"));
   }

   private void jRbNb1StateChanged(ChangeEvent evt) {
      if (this.jRbNb1.isSelected()) {
         this.boardSettings.getProgrammingFlag().set00_SingleMotor(true);
         this.jSliderForce2.setEnabled(false);
         this.jSpinnerDelayClose.setEnabled(false);
      }
   }

   private void jRbNb2StateChanged(ChangeEvent evt) {
      if (this.jRbNb2.isSelected()) {
         this.boardSettings.getProgrammingFlag().set00_SingleMotor(false);
         this.jSliderForce2.setEnabled(true);
         this.jSpinnerDelayClose.setEnabled(true);
      }
   }

   private void jSliderForce1StateChanged(ChangeEvent evt) {
      if (this.jSliderForce1.isEnabled()) {
         int force1 = this.jSliderForce1.getValue();
         String level = String.valueOf(force1);
         this.jLbForce1.setText(level);
         this.boardSettings.setForceOpenMotor1(force1);
         this.boardSettings.setForceCloseMotor1(force1);
      }
   }

   private void jSliderForce2StateChanged(ChangeEvent evt) {
      if (this.jSliderForce2.isEnabled()) {
         int force2 = this.jSliderForce2.getValue();
         String level = String.valueOf(force2);
         this.jLbForce2.setText(level);
         this.boardSettings.setForceOpenMotor2(force2);
         this.boardSettings.setForceCloseMotor2(force2);
      }
   }

   private void jSpinnerDelayCloseStateChanged(ChangeEvent evt) {
      if (this.jSpinnerDelayClose.isEnabled()) {
         this.boardSettings.setTimeDelayCloseAnta_sec(Integer.parseInt(this.jSpinnerDelayClose.getValue().toString()));
      }
   }

   private void battenteSelected() {
      int force1 = 25;
      int force2 = 25;
      this.jSliderForce1.setValue(force1);
      this.jLbForce1.setText(String.valueOf(force1));
      this.jLbForce2.setText(String.valueOf(force2));
      this.jSliderForce2.setValue(force2);
      this.boardSettings.setForceOpenMotor1(force1);
      this.boardSettings.setForceCloseMotor1(force1);
      this.boardSettings.setForceOpenMotor2(force2);
      this.boardSettings.setForceCloseMotor2(force2);
      this.boardSettings.setMotor1Type(E145_Settings.MotorTypeBattente);
      this.boardSettings.setMotor2Type(E145_Settings.MotorTypeBattente);
   }

   private void scorrSelected() {
      int force1 = 50;
      int force2 = 50;
      this.jSliderForce1.setValue(force1);
      this.jLbForce1.setText(String.valueOf(force1));
      this.jLbForce2.setText(String.valueOf(force2));
      this.jSliderForce2.setValue(force2);
      this.boardSettings.setForceOpenMotor1(force1);
      this.boardSettings.setForceCloseMotor1(force1);
      this.boardSettings.setForceOpenMotor2(force2);
      this.boardSettings.setForceCloseMotor2(force2);
      this.boardSettings.setMotor1Type(E145_Settings.MotorTypeScorrevole);
      this.boardSettings.setMotor2Type(E145_Settings.MotorTypeScorrevole);
      this.boardSettings.setFinecorsaOpenAnta1(1);
      this.boardSettings.setFinecorsaOpenAnta2(1);
      this.boardSettings.setFinecorsaCloseAnta1(1);
      this.boardSettings.setFinecorsaCloseAnta2(1);
   }

   private void jCbMotorTypeItemStateChanged(ItemEvent evt) {
      if (this.boardModel.compareTo("E145") == 0) {
         if (this.jCbMotorType.getSelectedItem() != null) {
            if (Integer.parseInt(((KeyValue)this.jCbMotorType.getSelectedItem()).getKey().toString()) == 0) {
               this.battenteSelected();
            } else {
               this.scorrSelected();
            }
         }
      } else if (this.jCbMotorType.getSelectedItem() != null) {
         int motorType = Integer.parseInt(((KeyValue)this.jCbMotorType.getSelectedItem()).getKey().toString());
         File fileToParse = new File(E124_Settings.getDefFileForMotor(motorType));
         this.boardSettings.loadDefaultPrgFileForBoard(fileToParse, "E124");
         if (this.boardSettings.getMotorsNb() == 1) {
            this.jRbNb1.setSelected(true);
         } else {
            this.jRbNb2.setSelected(true);
         }

         int force1 = this.boardSettings.getForceOpenMotor1();
         int force2 = this.boardSettings.getForceOpenMotor2();
         this.jSliderForce1.setValue(force1);
         this.jLbForce1.setText(String.valueOf(force1));
         this.jLbForce2.setText(String.valueOf(force2));
         this.jSliderForce2.setValue(force2);
         this.boardSettings.setForceOpenMotor1(force1);
         this.boardSettings.setForceCloseMotor1(force1);
         this.boardSettings.setForceOpenMotor2(force2);
         this.boardSettings.setForceCloseMotor2(force2);
         int iRitAntaClose_s = this.boardSettings.getTimeDelayCloseAnta_sec();
         this.jSpinnerDelayClose.setValue(iRitAntaClose_s);
         if (this.boardModel.compareTo("E124") == 0 && (this.jCbMotorType.getSelectedIndex() == 3 || this.jCbMotorType.getSelectedIndex() == 5)) {
            this.boardSettings.setEncoderMotor1(1);
            this.boardSettings.setEncoderMotor2(1);
         }
      }
   }
}
