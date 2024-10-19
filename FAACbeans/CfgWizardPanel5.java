package FAACbeans;

import FAAClib.FAAC_Settings;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CfgWizardPanel5 extends JPanel {
   private final FAAC_Settings boardSettings;
   private String boardModel;
   private ButtonGroup btnGrpEffEnerg;
   private JButton jBtLogicDetails;
   private JComboBox jCBLogic;
   private JLabel jLPauseTimeOpenA;
   private JLabel jLPauseTimeOpenB;
   private JLabel jLabel6;
   private JLabel jLbEffEnergTooltip;
   private JLabel jLbLogic;
   private JLabel jLbLogicDescription;
   private JLabel jLbPercentage;
   private JLabel jLbPercentage1;
   private JLabel jLbTitleStep4;
   private JLabel jLbmmss;
   private JLabel jLbmmss_2;
   private JPanel jPanel1;
   private JPanel jPanel2;
   private JRadioButton jRbNone;
   private JRadioButton jRbPartial;
   private JRadioButton jRbTotal;
   private JSpinner jSpOpenBPercentage;
   private JSpinner jSpPauseTimeOpenA;
   private JSpinner jSpPauseTimeOpenB;
   ResourceBundle bundle;
   private int lastNbMotorNbSelected;

   public CfgWizardPanel5(FAAC_Settings boardSettings, String boardModel) {
      this.initComponents();
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/CfgWizardPanel5");
      this.boardSettings = boardSettings;
      this.boardModel = boardModel;
      FAAC_Utilities.setSpinnerMinSecModel(this.jSpPauseTimeOpenA, 9, 50);
      FAAC_Utilities.disableSpinnerEditing(this.jSpPauseTimeOpenA);
      FAAC_Utilities.setSpinnerMinSecModel(this.jSpPauseTimeOpenB, 9, 50);
      FAAC_Utilities.disableSpinnerEditing(this.jSpPauseTimeOpenB);
      this.lastNbMotorNbSelected = boardSettings.getMotorsNb();
      if (this.lastNbMotorNbSelected == 1) {
         this.jSpOpenBPercentage.setValue(50);
      } else {
         this.jSpOpenBPercentage.setValue(100);
      }

      FAAC_Utilities.listenForManualEditIntSpinner(
         this.jSpOpenBPercentage,
         ((SpinnerNumberModel)this.jSpOpenBPercentage.getModel()).getMinimum(),
         ((SpinnerNumberModel)this.jSpOpenBPercentage.getModel()).getMaximum()
      );
      if (boardModel.compareToIgnoreCase("E124") == 0) {
         this.jRbPartial.setText(this.bundle.getString("batteryOnly"));
         this.jRbTotal.setText(this.bundle.getString("always"));
      }

      this.initPanel();
   }

   public final void initPanel() {
      String logic = this.boardSettings.getLogicFlag().getLogic();
      if (logic.compareToIgnoreCase("CU") != 0) {
         this.jCBLogic.setSelectedItem(logic);
      } else {
         this.jCBLogic.setSelectedItem("A");
      }

      this.updateLogicDescription();
      int motorNb = this.boardSettings.getMotorsNb();
      if (this.lastNbMotorNbSelected != motorNb) {
         this.lastNbMotorNbSelected = motorNb;
         if (this.lastNbMotorNbSelected == 1) {
            this.jSpOpenBPercentage.setValue(50);
         } else {
            this.jSpOpenBPercentage.setValue(100);
         }
      }

      if (motorNb == 1) {
         SpinnerNumberModel model = (SpinnerNumberModel)this.jSpOpenBPercentage.getModel();
         model.setMaximum(80);
         this.jSpOpenBPercentage.setModel(model);
         if (this.boardSettings.getPartialOpeningOpenB() > 80) {
            this.boardSettings.setPartialOpeningOpenB(80);
         }

         this.jSpOpenBPercentage.setValue(this.boardSettings.getPartialOpeningOpenB());
      } else {
         SpinnerNumberModel model = (SpinnerNumberModel)this.jSpOpenBPercentage.getModel();
         model.setMaximum(100);
         this.jSpOpenBPercentage.setModel(model);
         this.jSpOpenBPercentage.setValue(this.boardSettings.getPartialOpeningOpenB());
      }

      Calendar dateTmp = GregorianCalendar.getInstance();
      dateTmp.clear();
      dateTmp.add(13, this.boardSettings.getTimePauseA_sec());
      this.jSpPauseTimeOpenA.setValue(dateTmp.getTime());
      dateTmp.clear();
      dateTmp.add(13, this.boardSettings.getTimePauseB_sec());
      this.jSpPauseTimeOpenB.setValue(dateTmp.getTime());
      FAAC_Settings.ProgrammingFlag prog = this.boardSettings.getProgrammingFlag();
      if (this.boardModel.compareToIgnoreCase("E124") == 0) {
         if (!prog.get06_Sleep()) {
            this.jRbNone.setSelected(true);
         } else if (prog.get15_BatterySleep()) {
            this.jRbPartial.setSelected(true);
         } else {
            this.jRbTotal.setSelected(true);
         }
      } else if (!prog.get06_Sleep()) {
         this.jRbNone.setSelected(true);
      } else if (prog.get11_SleepVacc()) {
         this.jRbPartial.setSelected(true);
      } else {
         this.jRbTotal.setSelected(true);
      }
   }

   private void initComponents() {
      this.btnGrpEffEnerg = new ButtonGroup();
      this.jPanel1 = new JPanel();
      this.jLabel6 = new JLabel();
      this.jLPauseTimeOpenA = new JLabel();
      this.jLbPercentage = new JLabel();
      this.jCBLogic = new JComboBox();
      this.jLbLogicDescription = new JLabel();
      this.jLbTitleStep4 = new JLabel();
      this.jLbLogic = new JLabel();
      this.jBtLogicDetails = new JButton();
      this.jSpPauseTimeOpenB = new JSpinner();
      this.jSpPauseTimeOpenA = new JSpinner();
      this.jLPauseTimeOpenB = new JLabel();
      this.jSpOpenBPercentage = new JSpinner();
      this.jLbmmss_2 = new JLabel();
      this.jLbmmss = new JLabel();
      this.jLbPercentage1 = new JLabel();
      this.jPanel2 = new JPanel();
      this.jRbNone = new JRadioButton();
      this.jRbPartial = new JRadioButton();
      this.jRbTotal = new JRadioButton();
      this.jLbEffEnergTooltip = new JLabel();
      this.setName("Form");
      this.setPreferredSize(new Dimension(450, 272));
      this.jPanel1.setName("CfgWizardPanel5");
      this.jLabel6.setIcon(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/wizard_4.png")));
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/CfgWizardPanel5");
      this.jLabel6.setText(bundle.getString("CfgWizardPanel5.jLabel6.text"));
      this.jLabel6.setName("jLabel6");
      this.jLPauseTimeOpenA.setText(bundle.getString("CfgWizardPanel5.jLPauseTimeOpenA.text"));
      this.jLPauseTimeOpenA.setName("jLPauseTimeOpenA");
      this.jLbPercentage.setText(bundle.getString("CfgWizardPanel5.jLbPercentage.text"));
      this.jLbPercentage.setName("jLbPercentage");
      this.jCBLogic.setModel(new DefaultComboBoxModel<>(new String[]{"A", "A1", "AP", "At", "b", "bC", "C", "E", "EP", "S", "SA", "SP"}));
      this.jCBLogic.setName("jCBLogic");
      this.jCBLogic.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent evt) {
            CfgWizardPanel5.this.jCBLogicItemStateChanged(evt);
         }
      });
      this.jLbLogicDescription.setText(bundle.getString("CfgWizardPanel5.jLbLogicDescription.text"));
      this.jLbLogicDescription.setName("jLbLogicDescription");
      this.jLbTitleStep4.setFont(new Font("Tahoma", 1, 14));
      this.jLbTitleStep4.setText(bundle.getString("CfgWizardPanel5.jLbTitleStep4.text"));
      this.jLbTitleStep4.setName("jLbTitleStep4");
      this.jLbLogic.setText(bundle.getString("CfgWizardPanel5.jLbLogic.text"));
      this.jLbLogic.setName("jLbLogic");
      this.jBtLogicDetails.setText(bundle.getString("CfgWizardPanel5.jBtLogicDetails.text"));
      this.jBtLogicDetails.setName("jBtLogicDetails");
      this.jBtLogicDetails.setOpaque(false);
      this.jBtLogicDetails.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            CfgWizardPanel5.this.jBtLogicDetailsMousePressed(evt);
         }
      });
      this.jSpPauseTimeOpenB.setEnabled(false);
      this.jSpPauseTimeOpenB.setName("jSpPauseTimeOpenB");
      this.jSpPauseTimeOpenB.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel5.this.jSpPauseTimeOpenBStateChanged(evt);
         }
      });
      this.jSpPauseTimeOpenA.setModel(new SpinnerDateModel());
      this.jSpPauseTimeOpenA.setEnabled(false);
      this.jSpPauseTimeOpenA.setName("jSpPauseTimeOpenA");
      this.jSpPauseTimeOpenA.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel5.this.jSpPauseTimeOpenAStateChanged(evt);
         }
      });
      this.jLPauseTimeOpenB.setText(bundle.getString("CfgWizardPanel5.jLPauseTimeOpenB.text"));
      this.jLPauseTimeOpenB.setName("jLPauseTimeOpenB");
      this.jSpOpenBPercentage.setModel(new SpinnerNumberModel(20, 0, 100, 1));
      this.jSpOpenBPercentage.setName("jSpOpenBPercentage");
      this.jSpOpenBPercentage.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel5.this.jSpOpenBPercentageStateChanged(evt);
         }
      });
      this.jLbmmss_2.setText(bundle.getString("CfgWizardPanel5.jLbmmss_2.text"));
      this.jLbmmss_2.setName("jLbmmss_2");
      this.jLbmmss.setText(bundle.getString("CfgWizardPanel5.jLbmmss.text"));
      this.jLbmmss.setName("jLbmmss");
      this.jLbPercentage1.setText(bundle.getString("CfgWizardPanel5.jLbPercentage1.text"));
      this.jLbPercentage1.setToolTipText(bundle.getString("CfgWizardPanel5.jLbPercentage1.toolTipText"));
      this.jLbPercentage1.setName("jLbPercentage1");
      this.jPanel2.setBorder(BorderFactory.createTitledBorder(bundle.getString("CfgWizardPanel5.jPanel2.border.title")));
      this.jPanel2.setName("jPanel2");
      this.btnGrpEffEnerg.add(this.jRbNone);
      this.jRbNone.setText(bundle.getString("CfgWizardPanel5.jRbNone.text"));
      this.jRbNone.setName("jRbNone");
      this.jRbNone.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent evt) {
            CfgWizardPanel5.this.jRbNoneMouseEntered(evt);
         }

         @Override
         public void mouseExited(MouseEvent evt) {
            CfgWizardPanel5.this.jRbNoneMouseExited(evt);
         }
      });
      this.jRbNone.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel5.this.jRbNoneStateChanged(evt);
         }
      });
      this.btnGrpEffEnerg.add(this.jRbPartial);
      this.jRbPartial.setText(bundle.getString("CfgWizardPanel5.jRbPartial.text"));
      this.jRbPartial.setName("jRbPartial");
      this.jRbPartial.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent evt) {
            CfgWizardPanel5.this.jRbPartialMouseEntered(evt);
         }

         @Override
         public void mouseExited(MouseEvent evt) {
            CfgWizardPanel5.this.jRbPartialMouseExited(evt);
         }
      });
      this.jRbPartial.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel5.this.jRbPartialStateChanged(evt);
         }
      });
      this.btnGrpEffEnerg.add(this.jRbTotal);
      this.jRbTotal.setText(bundle.getString("CfgWizardPanel5.jRbTotal.text"));
      this.jRbTotal.setName("jRbTotal");
      this.jRbTotal.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent evt) {
            CfgWizardPanel5.this.jRbTotalMouseEntered(evt);
         }

         @Override
         public void mouseExited(MouseEvent evt) {
            CfgWizardPanel5.this.jRbTotalMouseExited(evt);
         }
      });
      this.jRbTotal.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel5.this.jRbTotalStateChanged(evt);
         }
      });
      this.jLbEffEnergTooltip.setFont(new Font("Tahoma", 0, 9));
      this.jLbEffEnergTooltip.setText(bundle.getString("CfgWizardPanel5.jLbEffEnergTooltip.text"));
      this.jLbEffEnergTooltip.setName("jLbEffEnergTooltip");
      GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
      this.jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(
         jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel2Layout.createSequentialGroup()
                  .addGroup(
                     jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jRbNone).addComponent(this.jRbPartial).addComponent(this.jRbTotal)
                  )
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jLbEffEnergTooltip, -1, 174, 32767)
                  .addContainerGap()
            )
      );
      jPanel2Layout.setVerticalGroup(
         jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel2Layout.createSequentialGroup()
                  .addComponent(this.jRbNone)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jRbPartial)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jRbTotal)
                  .addContainerGap(9, 32767)
            )
            .addComponent(this.jLbEffEnergTooltip, -1, 78, 32767)
      );
      GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
      this.jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel1Layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(this.jLabel6)
                  .addGap(18, 18, 18)
                  .addGroup(
                     jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jLbTitleStep4)
                        .addGroup(
                           jPanel1Layout.createSequentialGroup()
                              .addComponent(this.jLbLogic)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jCBLogic, -2, 70, -2)
                              .addGap(4, 4, 4)
                              .addComponent(this.jBtLogicDetails, -1, -1, 32767)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbLogicDescription, -1, 104, 32767)
                        )
                        .addGroup(
                           jPanel1Layout.createSequentialGroup()
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                    .addComponent(this.jLbPercentage1)
                                    .addGroup(
                                       jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                          .addComponent(this.jLPauseTimeOpenA)
                                          .addComponent(this.jLPauseTimeOpenB)
                                    )
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
                                    .addComponent(this.jSpOpenBPercentage, -1, 72, 32767)
                                    .addComponent(this.jSpPauseTimeOpenB, Alignment.LEADING, -1, 72, 32767)
                                    .addComponent(this.jSpPauseTimeOpenA, Alignment.LEADING, -2, 72, -2)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jLbPercentage)
                                    .addComponent(this.jLbmmss_2)
                                    .addComponent(this.jLbmmss)
                              )
                        )
                        .addComponent(this.jPanel2, -2, -1, -2)
                  )
                  .addContainerGap()
            )
      );
      jPanel1Layout.linkSize(0, this.jSpPauseTimeOpenA, this.jSpPauseTimeOpenB);
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanel1Layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(this.jLabel6, Alignment.LEADING)
                        .addGroup(
                           Alignment.LEADING,
                           jPanel1Layout.createSequentialGroup()
                              .addComponent(this.jLbTitleStep4)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.CENTER)
                                    .addComponent(this.jLbLogic)
                                    .addComponent(this.jCBLogic, -2, -1, -2)
                                    .addComponent(this.jBtLogicDetails)
                                    .addComponent(this.jLbLogicDescription, -2, 35, -2)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.CENTER)
                                    .addComponent(this.jLPauseTimeOpenA)
                                    .addComponent(this.jSpPauseTimeOpenA, -2, -1, -2)
                                    .addComponent(this.jLbmmss)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.CENTER)
                                    .addComponent(this.jLPauseTimeOpenB)
                                    .addComponent(this.jSpPauseTimeOpenB, -2, -1, -2)
                                    .addComponent(this.jLbmmss_2)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 jPanel1Layout.createParallelGroup(Alignment.CENTER)
                                    .addComponent(this.jLbPercentage1)
                                    .addComponent(this.jLbPercentage)
                                    .addComponent(this.jSpOpenBPercentage, -2, -1, -2)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jPanel2, -1, -1, 32767)
                        )
                  )
                  .addContainerGap(-1, 32767)
            )
      );
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767))
      );
      this.jPanel1.getAccessibleContext().setAccessibleName(bundle.getString("CfgWizardPanel5.jPanel1.AccessibleContext.accessibleName"));
   }

   private void updateLogicDescription() {
      if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("A") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_A"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("AP") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_AP"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("AT") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_AT"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("B") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_B"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("BC") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_BC"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("C") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_C"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("E") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_E"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("EP") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_EP"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("CU") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_CU"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("S") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_S"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("SA") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_SA"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("SP") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_SP"));
      } else if (((String)this.jCBLogic.getSelectedItem()).compareToIgnoreCase("A1") == 0) {
         this.jLbLogicDescription.setText(this.bundle.getString("logic_A1"));
      }
   }

   private void jCBLogicItemStateChanged(ItemEvent evt) {
      this.updateLogicDescription();
      String selected = (String)this.jCBLogic.getSelectedItem();
      if (selected.compareToIgnoreCase("A") != 0
         && selected.compareToIgnoreCase("AP") != 0
         && selected.compareToIgnoreCase("AT") != 0
         && selected.compareToIgnoreCase("A1") != 0
         && selected.compareToIgnoreCase("S") != 0
         && selected.compareToIgnoreCase("SA") != 0
         && selected.compareToIgnoreCase("SP") != 0) {
         this.jSpPauseTimeOpenA.setEnabled(false);
         this.jSpPauseTimeOpenB.setEnabled(false);
      } else {
         this.jSpPauseTimeOpenA.setEnabled(true);
         this.jSpPauseTimeOpenB.setEnabled(true);
      }

      if (evt.getStateChange() == 1
         && (selected.compareToIgnoreCase("B") == 0 || selected.compareToIgnoreCase("C") == 0 || selected.compareToIgnoreCase("BC") == 0)) {
         JOptionPane.showMessageDialog(this, this.bundle.getString("logicB_C_BC_message"), this.bundle.getString("Attention"), 2);
      }

      if (selected.toUpperCase().compareTo("A") == 0) {
         this.boardSettings.getLogicFlag().setLogicA();
      } else if (selected.toUpperCase().compareTo("A1") == 0) {
         this.boardSettings.getLogicFlag().setLogicA1();
      } else if (selected.toUpperCase().compareTo("AT") == 0) {
         this.boardSettings.getLogicFlag().setLogicAT();
      } else if (selected.toUpperCase().compareTo("AP") == 0) {
         this.boardSettings.getLogicFlag().setLogicAP();
      } else if (selected.toUpperCase().compareTo("B") == 0) {
         this.boardSettings.getLogicFlag().setLogicB();
      } else if (selected.toUpperCase().compareTo("BC") == 0) {
         this.boardSettings.getLogicFlag().setLogicBC();
      } else if (selected.toUpperCase().compareTo("C") == 0) {
         this.boardSettings.getLogicFlag().setLogicC();
      } else if (selected.toUpperCase().compareTo("E") == 0) {
         this.boardSettings.getLogicFlag().setLogicE();
      } else if (selected.toUpperCase().compareTo("EP") == 0) {
         this.boardSettings.getLogicFlag().setLogicEP();
      } else if (selected.toUpperCase().compareTo("S") == 0) {
         this.boardSettings.getLogicFlag().setLogicS();
      } else if (selected.toUpperCase().compareTo("SA") == 0) {
         this.boardSettings.getLogicFlag().setLogicSA();
      } else if (selected.toUpperCase().compareTo("SP") == 0) {
         this.boardSettings.getLogicFlag().setLogicSP();
      }

      Calendar cal = Calendar.getInstance();
      Date date = (Date)this.jSpPauseTimeOpenA.getValue();
      cal.setTime(date);
      int iMinutes = cal.get(12);
      int iSeconds = cal.get(13);
      this.boardSettings.setTimePauseA_sec(iMinutes * 60 + iSeconds);
      date = (Date)this.jSpPauseTimeOpenB.getValue();
      cal.setTime(date);
      iMinutes = cal.get(12);
      iSeconds = cal.get(13);
      this.boardSettings.setTimePauseB_sec(iMinutes * 60 + iSeconds);
      this.boardSettings.setPartialOpeningOpenB(Integer.parseInt(this.jSpOpenBPercentage.getValue().toString()));
   }

   private void jSpPauseTimeOpenAStateChanged(ChangeEvent evt) {
      if (this.jSpPauseTimeOpenA.isEnabled()) {
         Calendar cal = Calendar.getInstance();
         Date date = (Date)this.jSpPauseTimeOpenA.getValue();
         cal.setTime(date);
         int iMinutes = cal.get(12);
         int iSeconds = cal.get(13);
         this.boardSettings.setTimePauseA_sec(iMinutes * 60 + iSeconds);
      }
   }

   private void jSpPauseTimeOpenBStateChanged(ChangeEvent evt) {
      if (this.jSpPauseTimeOpenB.isEnabled()) {
         Calendar cal = Calendar.getInstance();
         Date date = (Date)this.jSpPauseTimeOpenB.getValue();
         cal.setTime(date);
         int iMinutes = cal.get(12);
         int iSeconds = cal.get(13);
         this.boardSettings.setTimePauseB_sec(iMinutes * 60 + iSeconds);
      }
   }

   private void jSpOpenBPercentageStateChanged(ChangeEvent evt) {
      this.boardSettings.setPartialOpeningOpenB(Integer.parseInt(this.jSpOpenBPercentage.getValue().toString()));
   }

   private void jBtLogicDetailsMousePressed(MouseEvent evt) {
      Component c = this.getParent();
      Component frame = SwingUtilities.getRoot(c);
      FAACLogicDetails logicDetails = new FAACLogicDetails((Frame)frame.getParent(), true, (String)this.jCBLogic.getSelectedItem());
      logicDetails.setVisible(true);
   }

   private void jRbNoneStateChanged(ChangeEvent evt) {
      if (this.jRbNone.isSelected()) {
         FAAC_Settings.ProgrammingFlag prog = this.boardSettings.getProgrammingFlag();
         prog.set06_Sleep(false);
      }
   }

   private void jRbPartialStateChanged(ChangeEvent evt) {
      if (this.jRbPartial.isSelected()) {
         FAAC_Settings.ProgrammingFlag prog = this.boardSettings.getProgrammingFlag();
         prog.set06_Sleep(true);
         if (this.boardModel.compareTo("E124") == 0) {
            prog.set15_BatterySleep(true);
         } else {
            prog.set11_SleepVacc(true);
         }
      }
   }

   private void jRbTotalStateChanged(ChangeEvent evt) {
      if (this.jRbTotal.isSelected()) {
         FAAC_Settings.ProgrammingFlag prog = this.boardSettings.getProgrammingFlag();
         prog.set06_Sleep(true);
         if (this.boardModel.compareTo("E124") == 0) {
            prog.set15_BatterySleep(false);
         } else {
            prog.set11_SleepVacc(false);
         }
      }
   }

   private void jRbNoneMouseEntered(MouseEvent evt) {
      this.jLbEffEnergTooltip.setText(this.bundle.getString("noneTooltip"));
   }

   private void jRbNoneMouseExited(MouseEvent evt) {
      this.jLbEffEnergTooltip.setText(this.bundle.getString("defaultDetailsTxt"));
   }

   private void jRbPartialMouseEntered(MouseEvent evt) {
      if (this.boardModel.compareTo("E145") == 0) {
         this.jLbEffEnergTooltip.setText(this.bundle.getString("partialTooltip"));
      } else {
         this.jLbEffEnergTooltip.setText(this.bundle.getString("e124partialTooltip"));
      }
   }

   private void jRbPartialMouseExited(MouseEvent evt) {
      this.jLbEffEnergTooltip.setText(this.bundle.getString("defaultDetailsTxt"));
   }

   private void jRbTotalMouseEntered(MouseEvent evt) {
      if (this.boardModel.compareTo("E145") == 0) {
         this.jLbEffEnergTooltip.setText(this.bundle.getString("totalTooltip"));
      } else {
         this.jLbEffEnergTooltip.setText(this.bundle.getString("e124totalTooltip"));
      }
   }

   private void jRbTotalMouseExited(MouseEvent evt) {
      this.jLbEffEnergTooltip.setText(this.bundle.getString("defaultDetailsTxt"));
   }
}
