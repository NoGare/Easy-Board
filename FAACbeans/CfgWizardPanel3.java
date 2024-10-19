package FAACbeans;

import FAAClib.FAAC_Settings;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CfgWizardPanel3 extends JPanel {
   private final FAAC_Settings boardSettings;
   private int nbMotor;
   private ButtonGroup btGrpWhichMotor;
   private ButtonGroup btGrpYesNo;
   private JLabel jLbEncoder;
   private JLabel jLbFigStep2;
   private JLabel jLbStep2;
   private JPanel jPencoder;
   private JPanel jPfigureStep2;
   private JRadioButton jRbBothMotors;
   private JRadioButton jRbMotor1Only;
   private JRadioButton jRbMotor2Only;
   private JRadioButton jRbNo;
   private JRadioButton jRbYes;

   public CfgWizardPanel3(FAAC_Settings boardSettings) {
      this.initComponents();
      this.boardSettings = boardSettings;
      this.initPanel();
   }

   public final void initPanel() {
      this.nbMotor = this.boardSettings.getProgrammingFlag().get00_SingleMotor() ? 1 : 2;
      int iEncoder1 = this.boardSettings.getEncoderMotor1();
      int iEncoder2 = this.boardSettings.getEncoderMotor2();
      if (this.nbMotor == 2) {
         if (iEncoder1 == 0 && iEncoder2 == 0) {
            this.jRbNo.setSelected(true);
            this.jRbYes.setSelected(false);
            this.jRbMotor1Only.setEnabled(false);
            this.jRbMotor2Only.setEnabled(false);
            this.jRbBothMotors.setEnabled(false);
            this.jRbMotor1Only.setSelected(false);
            this.jRbMotor2Only.setSelected(false);
            this.jRbBothMotors.setSelected(false);
         } else if (iEncoder1 == 1 && iEncoder2 == 0) {
            this.jRbNo.setSelected(false);
            this.jRbYes.setSelected(true);
            this.jRbMotor1Only.setEnabled(true);
            this.jRbMotor1Only.setSelected(true);
            this.jRbMotor2Only.setSelected(false);
            this.jRbBothMotors.setSelected(false);
            this.jRbMotor2Only.setEnabled(true);
            this.jRbBothMotors.setEnabled(true);
         } else if (iEncoder2 == 1 && iEncoder1 == 0) {
            this.jRbNo.setSelected(false);
            this.jRbYes.setSelected(true);
            this.jRbMotor1Only.setEnabled(true);
            this.jRbMotor1Only.setSelected(false);
            this.jRbMotor2Only.setSelected(true);
            this.jRbBothMotors.setSelected(false);
            this.jRbMotor2Only.setEnabled(true);
            this.jRbBothMotors.setEnabled(true);
         } else {
            this.jRbNo.setSelected(false);
            this.jRbYes.setSelected(true);
            this.jRbMotor1Only.setEnabled(true);
            this.jRbMotor1Only.setSelected(false);
            this.jRbMotor2Only.setSelected(false);
            this.jRbBothMotors.setSelected(true);
            this.jRbMotor2Only.setEnabled(true);
            this.jRbBothMotors.setEnabled(true);
         }
      } else if (iEncoder1 == 0) {
         this.jRbNo.setSelected(true);
         this.jRbYes.setSelected(false);
         this.jRbMotor1Only.setEnabled(false);
         this.jRbMotor2Only.setEnabled(false);
         this.jRbBothMotors.setEnabled(false);
         this.jRbMotor1Only.setSelected(false);
         this.jRbMotor2Only.setSelected(false);
         this.jRbBothMotors.setSelected(false);
      } else {
         this.jRbNo.setSelected(false);
         this.jRbYes.setSelected(true);
         this.jRbMotor1Only.setEnabled(true);
         this.jRbMotor1Only.setSelected(true);
         this.jRbMotor2Only.setSelected(false);
         this.jRbBothMotors.setSelected(false);
         this.jRbMotor2Only.setEnabled(false);
         this.jRbBothMotors.setEnabled(false);
      }

      if (this.boardSettings.getMotor1Type() != 5 && this.boardSettings.getMotor1Type() != 3 && this.boardSettings.getMotor1Type() != 6) {
         this.jRbNo.setEnabled(true);
         this.jRbYes.setEnabled(true);
         this.jRbMotor1Only.setEnabled(true);
         this.jRbMotor2Only.setEnabled(true);
         this.jRbBothMotors.setEnabled(true);
      } else {
         this.jRbNo.setEnabled(false);
         this.jRbYes.setEnabled(false);
         this.jRbMotor1Only.setEnabled(false);
         this.jRbMotor2Only.setEnabled(false);
         this.jRbBothMotors.setEnabled(false);
      }
   }

   private void initComponents() {
      this.btGrpYesNo = new ButtonGroup();
      this.btGrpWhichMotor = new ButtonGroup();
      this.jPfigureStep2 = new JPanel();
      this.jLbFigStep2 = new JLabel();
      this.jLbStep2 = new JLabel();
      this.jLbEncoder = new JLabel();
      this.jRbNo = new JRadioButton();
      this.jRbYes = new JRadioButton();
      this.jPencoder = new JPanel();
      this.jRbMotor1Only = new JRadioButton();
      this.jRbMotor2Only = new JRadioButton();
      this.jRbBothMotors = new JRadioButton();
      this.setName("CfgWizardPanel3");
      this.setPreferredSize(new Dimension(450, 272));
      this.jPfigureStep2.setName("jPfigureStep2");
      this.jLbFigStep2.setIcon(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/wizard_2.png")));
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/CfgWizardPanel3");
      this.jLbFigStep2.setText(bundle.getString("CfgWizardPanel3.jLbFigStep2.text"));
      this.jLbFigStep2.setName("jLbFigStep2");
      GroupLayout jPfigureStep2Layout = new GroupLayout(this.jPfigureStep2);
      this.jPfigureStep2.setLayout(jPfigureStep2Layout);
      jPfigureStep2Layout.setHorizontalGroup(
         jPfigureStep2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPfigureStep2Layout.createSequentialGroup().addContainerGap().addComponent(this.jLbFigStep2).addContainerGap(-1, 32767))
      );
      jPfigureStep2Layout.setVerticalGroup(
         jPfigureStep2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPfigureStep2Layout.createSequentialGroup().addContainerGap().addComponent(this.jLbFigStep2).addContainerGap(-1, 32767))
      );
      this.jLbStep2.setFont(new Font("Tahoma", 1, 14));
      this.jLbStep2.setText(bundle.getString("CfgWizardPanel3.jLbStep2.text"));
      this.jLbStep2.setName("jLbStep2");
      this.jLbEncoder.setText(bundle.getString("CfgWizardPanel3.jLbEncoder.text"));
      this.jLbEncoder.setName("jLbEncoder");
      this.btGrpYesNo.add(this.jRbNo);
      this.jRbNo.setSelected(true);
      this.jRbNo.setText(bundle.getString("CfgWizardPanel3.jRbNo.text"));
      this.jRbNo.setName("jRbNo");
      this.jRbNo.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel3.this.jRbNoStateChanged(evt);
         }
      });
      this.btGrpYesNo.add(this.jRbYes);
      this.jRbYes.setText(bundle.getString("CfgWizardPanel3.jRbYes.text"));
      this.jRbYes.setName("jRbYes");
      this.jRbYes.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel3.this.jRbYesStateChanged(evt);
         }
      });
      this.jPencoder.setName("jPencoder");
      this.btGrpWhichMotor.add(this.jRbMotor1Only);
      this.jRbMotor1Only.setText(bundle.getString("CfgWizardPanel3.jRbMotor1Only.text"));
      this.jRbMotor1Only.setEnabled(false);
      this.jRbMotor1Only.setName("jRbMotor1Only");
      this.jRbMotor1Only.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel3.this.jRbMotor1OnlyStateChanged(evt);
         }
      });
      this.btGrpWhichMotor.add(this.jRbMotor2Only);
      this.jRbMotor2Only.setText(bundle.getString("CfgWizardPanel3.jRbMotor2Only.text"));
      this.jRbMotor2Only.setEnabled(false);
      this.jRbMotor2Only.setName("jRbMotor2Only");
      this.jRbMotor2Only.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            CfgWizardPanel3.this.jRbMotor2OnlyActionPerformed(evt);
         }
      });
      this.btGrpWhichMotor.add(this.jRbBothMotors);
      this.jRbBothMotors.setText(bundle.getString("CfgWizardPanel3.jRbBothMotors.text"));
      this.jRbBothMotors.setEnabled(false);
      this.jRbBothMotors.setName("jRbBothMotors");
      this.jRbBothMotors.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            CfgWizardPanel3.this.jRbBothMotorsActionPerformed(evt);
         }
      });
      GroupLayout jPencoderLayout = new GroupLayout(this.jPencoder);
      this.jPencoder.setLayout(jPencoderLayout);
      jPencoderLayout.setHorizontalGroup(
         jPencoderLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPencoderLayout.createSequentialGroup()
                  .addGap(16, 16, 16)
                  .addGroup(
                     jPencoderLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jRbBothMotors)
                        .addComponent(this.jRbMotor2Only)
                        .addComponent(this.jRbMotor1Only)
                  )
                  .addContainerGap(21, 32767)
            )
      );
      jPencoderLayout.setVerticalGroup(
         jPencoderLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPencoderLayout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(this.jRbMotor1Only)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jRbMotor2Only)
                  .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                  .addComponent(this.jRbBothMotors)
                  .addContainerGap()
            )
      );
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addComponent(this.jPfigureStep2, -2, -1, -2)
                  .addGap(43, 43, 43)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jLbStep2)
                        .addComponent(this.jLbEncoder)
                        .addComponent(this.jRbNo)
                        .addComponent(this.jRbYes)
                        .addComponent(this.jPencoder, -2, -1, -2)
                  )
                  .addContainerGap(85, 32767)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addComponent(this.jPfigureStep2, -2, -1, -2)
            .addGroup(
               layout.createSequentialGroup()
                  .addGap(44, 44, 44)
                  .addComponent(this.jLbStep2)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jLbEncoder)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jRbNo)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jRbYes)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jPencoder, -2, -1, -2)
            )
      );
      this.getAccessibleContext().setAccessibleName(bundle.getString("CfgWizardPanel3.AccessibleContext.accessibleName"));
   }

   private void jRbMotor2OnlyActionPerformed(ActionEvent evt) {
      if (this.jRbMotor2Only.isEnabled() && this.jRbMotor2Only.isSelected()) {
         this.boardSettings.setEncoderMotor1(0);
         this.boardSettings.setEncoderMotor2(1);
      }
   }

   private void jRbBothMotorsActionPerformed(ActionEvent evt) {
      if (this.jRbBothMotors.isEnabled() && this.jRbBothMotors.isSelected()) {
         this.boardSettings.setEncoderMotor1(1);
         this.boardSettings.setEncoderMotor2(1);
      }
   }

   private void jRbNoStateChanged(ChangeEvent evt) {
      if (this.jRbNo.isSelected()) {
         this.jRbMotor1Only.setEnabled(false);
         this.jRbMotor2Only.setEnabled(false);
         this.jRbBothMotors.setEnabled(false);
         this.boardSettings.setEncoderMotor1(0);
         this.boardSettings.setEncoderMotor2(0);
      }
   }

   private void jRbYesStateChanged(ChangeEvent evt) {
      if (this.jRbYes.isSelected()) {
         if (this.nbMotor == 2) {
            this.jRbMotor1Only.setEnabled(true);
            this.jRbMotor2Only.setEnabled(true);
            this.jRbBothMotors.setEnabled(true);
            if (this.jRbMotor1Only.isSelected()) {
               this.boardSettings.setEncoderMotor1(1);
               this.boardSettings.setEncoderMotor2(0);
            } else if (this.jRbMotor2Only.isSelected()) {
               this.boardSettings.setEncoderMotor1(0);
               this.boardSettings.setEncoderMotor2(1);
            } else {
               this.boardSettings.setEncoderMotor1(1);
               this.boardSettings.setEncoderMotor2(1);
            }
         } else {
            this.jRbMotor1Only.setEnabled(false);
            this.jRbMotor1Only.setSelected(true);
            this.jRbMotor2Only.setEnabled(false);
            this.jRbBothMotors.setEnabled(false);
            if (!this.jRbMotor1Only.isSelected() && !this.jRbBothMotors.isSelected()) {
               this.boardSettings.setEncoderMotor1(0);
            } else {
               this.boardSettings.setEncoderMotor1(1);
            }
         }
      }
   }

   private void jRbMotor1OnlyStateChanged(ChangeEvent evt) {
      if (this.jRbMotor1Only.isEnabled() && this.jRbMotor1Only.isSelected()) {
         this.boardSettings.setEncoderMotor1(1);
         this.boardSettings.setEncoderMotor2(0);
      }
   }
}
