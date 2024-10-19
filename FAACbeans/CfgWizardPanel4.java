package FAACbeans;

import FAAClib.E145_Settings;
import FAAClib.FAAC_Settings;
import FAAClib.KeyValue;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CfgWizardPanel4 extends JPanel {
   private final FAAC_Settings boardSettings;
   private String boardModel;
   private int nbMotor;
   private ButtonGroup btGrpFcClose;
   private ButtonGroup btGrpFcOpen;
   private JComboBox jCbFinecorsaClose1;
   private JComboBox jCbFinecorsaClose2;
   private JComboBox jCbFinecorsaOpen1;
   private JComboBox jCbFinecorsaOpen2;
   private JLabel jLbFcClose;
   private JLabel jLbFcOpen;
   private JLabel jLbFigStep3;
   private JLabel jLbFinecorsaClose1;
   private JLabel jLbFinecorsaClose2;
   private JLabel jLbFinecorsaOpen1;
   private JLabel jLbFinecorsaOpen2;
   private JLabel jLbStep3;
   private JPanel jPfigureStep3;
   private JRadioButton jRbFcCloseNo;
   private JRadioButton jRbFcCloseYes;
   private JRadioButton jRbFcOpenNo;
   private JRadioButton jRbFcOpenYes;
   ResourceBundle bundle;

   public CfgWizardPanel4(FAAC_Settings boardSettings, String boardModel) {
      this.initComponents();
      this.boardSettings = boardSettings;
      this.boardModel = boardModel;
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/CfgWizardPanel4");
      this.jCbFinecorsaOpen1.removeAllItems();
      this.jCbFinecorsaOpen2.removeAllItems();
      this.jCbFinecorsaClose1.removeAllItems();
      this.jCbFinecorsaClose2.removeAllItems();
      KeyValue kvTmp = new KeyValue(1, this.bundle.getString("finecorsa_endMov"));
      this.jCbFinecorsaOpen1.addItem(kvTmp);
      this.jCbFinecorsaOpen2.addItem(kvTmp);
      this.jCbFinecorsaClose1.addItem(kvTmp);
      this.jCbFinecorsaClose2.addItem(kvTmp);
      kvTmp = new KeyValue(2, this.bundle.getString("finecorsa_beginRall"));
      this.jCbFinecorsaOpen1.addItem(kvTmp);
      this.jCbFinecorsaOpen2.addItem(kvTmp);
      this.jCbFinecorsaClose1.addItem(kvTmp);
      this.jCbFinecorsaClose2.addItem(kvTmp);
      this.initPanel();
   }

   public final void initPanel() {
      this.nbMotor = this.boardSettings.getProgrammingFlag().get00_SingleMotor() ? 1 : 2;
      if (this.boardSettings.getFinecorsaOpenAnta1() == 0) {
         this.jRbFcOpenNo.setSelected(true);
         this.jRbFcOpenYes.setSelected(false);
         this.jCbFinecorsaOpen1.setSelectedIndex(0);
         this.jCbFinecorsaOpen2.setSelectedIndex(0);
      } else {
         this.jRbFcOpenNo.setSelected(false);
         this.jRbFcOpenYes.setSelected(true);
         KeyValue kvTmp = this.getjCbFinecorsaOpen1ItemFromKey(this.boardSettings.getFinecorsaOpenAnta1());
         this.jCbFinecorsaOpen1.setSelectedItem(kvTmp);
         kvTmp = this.getjCbFinecorsaOpen2ItemFromKey(this.boardSettings.getFinecorsaOpenAnta2());
         this.jCbFinecorsaOpen2.setSelectedItem(kvTmp);
      }

      if (this.boardSettings.getFinecorsaCloseAnta1() == 0) {
         this.jRbFcCloseNo.setSelected(true);
         this.jRbFcCloseYes.setSelected(false);
         this.jCbFinecorsaClose1.setSelectedIndex(0);
         this.jCbFinecorsaClose2.setSelectedIndex(0);
      } else {
         this.jRbFcCloseNo.setSelected(false);
         this.jRbFcCloseYes.setSelected(true);
         KeyValue kvTmp = this.getjCbFinecorsaClose1ItemFromKey(this.boardSettings.getFinecorsaCloseAnta1());
         this.jCbFinecorsaClose1.setSelectedItem(kvTmp);
         kvTmp = this.getjCbFinecorsaClose2ItemFromKey(this.boardSettings.getFinecorsaCloseAnta2());
         this.jCbFinecorsaClose2.setSelectedItem(kvTmp);
      }

      if (this.jRbFcOpenYes.isSelected()) {
         this.jCbFinecorsaOpen1.setEnabled(true);
         if (this.nbMotor == 1) {
            this.jCbFinecorsaOpen2.setEnabled(false);
         } else {
            this.jCbFinecorsaOpen2.setEnabled(true);
         }
      } else {
         this.jCbFinecorsaOpen1.setEnabled(false);
         this.jCbFinecorsaOpen2.setEnabled(false);
      }

      if (this.jRbFcCloseYes.isSelected()) {
         this.jCbFinecorsaClose1.setEnabled(true);
         if (this.nbMotor == 1) {
            this.jCbFinecorsaClose2.setEnabled(false);
         } else {
            this.jCbFinecorsaClose2.setEnabled(true);
         }
      } else {
         this.jCbFinecorsaClose1.setEnabled(false);
         this.jCbFinecorsaClose2.setEnabled(false);
      }

      if (this.boardModel.compareTo("E145") == 0) {
         if (this.boardSettings.getMotor1Type() == E145_Settings.MotorTypeScorrevole) {
            this.jRbFcOpenNo.setEnabled(false);
            this.jRbFcOpenYes.setEnabled(false);
            this.jCbFinecorsaOpen1.setEnabled(false);
            this.jCbFinecorsaOpen2.setEnabled(false);
            this.jRbFcCloseNo.setEnabled(false);
            this.jRbFcCloseYes.setEnabled(false);
            this.jCbFinecorsaClose1.setEnabled(false);
            this.jCbFinecorsaClose2.setEnabled(false);
         } else {
            this.jRbFcOpenNo.setEnabled(true);
            this.jRbFcOpenYes.setEnabled(true);
            this.jRbFcCloseNo.setEnabled(true);
            this.jRbFcCloseYes.setEnabled(true);
         }
      }
   }

   private KeyValue getjCbFinecorsaOpen1ItemFromKey(int key) {
      new KeyValue();

      for (int i = 0; i < this.jCbFinecorsaOpen1.getItemCount(); i++) {
         KeyValue kvTmp = (KeyValue)this.jCbFinecorsaOpen1.getItemAt(i);
         if (Integer.parseInt(kvTmp.getKey().toString()) == key) {
            return kvTmp;
         }
      }

      return null;
   }

   private KeyValue getjCbFinecorsaClose1ItemFromKey(int key) {
      new KeyValue();

      for (int i = 0; i < this.jCbFinecorsaClose1.getItemCount(); i++) {
         KeyValue kvTmp = (KeyValue)this.jCbFinecorsaClose1.getItemAt(i);
         if (Integer.parseInt(kvTmp.getKey().toString()) == key) {
            return kvTmp;
         }
      }

      return null;
   }

   private KeyValue getjCbFinecorsaOpen2ItemFromKey(int key) {
      new KeyValue();

      for (int i = 0; i < this.jCbFinecorsaOpen2.getItemCount(); i++) {
         KeyValue kvTmp = (KeyValue)this.jCbFinecorsaOpen2.getItemAt(i);
         if (Integer.parseInt(kvTmp.getKey().toString()) == key) {
            return kvTmp;
         }
      }

      return null;
   }

   private KeyValue getjCbFinecorsaClose2ItemFromKey(int key) {
      new KeyValue();

      for (int i = 0; i < this.jCbFinecorsaClose2.getItemCount(); i++) {
         KeyValue kvTmp = (KeyValue)this.jCbFinecorsaClose2.getItemAt(i);
         if (Integer.parseInt(kvTmp.getKey().toString()) == key) {
            return kvTmp;
         }
      }

      return null;
   }

   private void initComponents() {
      this.btGrpFcOpen = new ButtonGroup();
      this.btGrpFcClose = new ButtonGroup();
      this.jPfigureStep3 = new JPanel();
      this.jLbFigStep3 = new JLabel();
      this.jRbFcOpenYes = new JRadioButton();
      this.jRbFcOpenNo = new JRadioButton();
      this.jLbFcOpen = new JLabel();
      this.jLbStep3 = new JLabel();
      this.jLbFcClose = new JLabel();
      this.jRbFcCloseYes = new JRadioButton();
      this.jRbFcCloseNo = new JRadioButton();
      this.jLbFinecorsaOpen1 = new JLabel();
      this.jCbFinecorsaOpen1 = new JComboBox();
      this.jCbFinecorsaOpen2 = new JComboBox();
      this.jLbFinecorsaOpen2 = new JLabel();
      this.jLbFinecorsaClose1 = new JLabel();
      this.jCbFinecorsaClose1 = new JComboBox();
      this.jCbFinecorsaClose2 = new JComboBox();
      this.jLbFinecorsaClose2 = new JLabel();
      this.setName("CfgWizardPanel4");
      this.setPreferredSize(new Dimension(450, 272));
      this.jPfigureStep3.setName("jPfigureStep3");
      this.jLbFigStep3.setIcon(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/wizard_3.png")));
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/CfgWizardPanel4");
      this.jLbFigStep3.setText(bundle.getString("CfgWizardPanel4.jLbFigStep3.text"));
      this.jLbFigStep3.setName("jLbFigStep3");
      GroupLayout jPfigureStep3Layout = new GroupLayout(this.jPfigureStep3);
      this.jPfigureStep3.setLayout(jPfigureStep3Layout);
      jPfigureStep3Layout.setHorizontalGroup(
         jPfigureStep3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPfigureStep3Layout.createSequentialGroup().addContainerGap().addComponent(this.jLbFigStep3).addContainerGap(-1, 32767))
      );
      jPfigureStep3Layout.setVerticalGroup(
         jPfigureStep3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPfigureStep3Layout.createSequentialGroup().addContainerGap().addComponent(this.jLbFigStep3).addContainerGap(-1, 32767))
      );
      this.btGrpFcOpen.add(this.jRbFcOpenYes);
      this.jRbFcOpenYes.setText(bundle.getString("CfgWizardPanel4.jRbFcOpenYes.text"));
      this.jRbFcOpenYes.setName("jRbFcOpenYes");
      this.jRbFcOpenYes.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel4.this.jRbFcOpenYesStateChanged(evt);
         }
      });
      this.btGrpFcOpen.add(this.jRbFcOpenNo);
      this.jRbFcOpenNo.setSelected(true);
      this.jRbFcOpenNo.setText(bundle.getString("CfgWizardPanel4.jRbFcOpenNo.text"));
      this.jRbFcOpenNo.setName("jRbFcOpenNo");
      this.jRbFcOpenNo.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel4.this.jRbFcOpenNoStateChanged(evt);
         }
      });
      this.jLbFcOpen.setText(bundle.getString("CfgWizardPanel4.jLbFcOpen.text"));
      this.jLbFcOpen.setName("jLbFcOpen");
      this.jLbStep3.setFont(new Font("Tahoma", 1, 14));
      this.jLbStep3.setText(bundle.getString("CfgWizardPanel4.jLbStep3.text"));
      this.jLbStep3.setName("jLbStep3");
      this.jLbFcClose.setText(bundle.getString("CfgWizardPanel4.jLbFcClose.text"));
      this.jLbFcClose.setName("jLbFcClose");
      this.btGrpFcClose.add(this.jRbFcCloseYes);
      this.jRbFcCloseYes.setText(bundle.getString("CfgWizardPanel4.jRbFcCloseYes.text"));
      this.jRbFcCloseYes.setName("jRbFcCloseYes");
      this.jRbFcCloseYes.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel4.this.jRbFcCloseYesStateChanged(evt);
         }
      });
      this.btGrpFcClose.add(this.jRbFcCloseNo);
      this.jRbFcCloseNo.setSelected(true);
      this.jRbFcCloseNo.setText(bundle.getString("CfgWizardPanel4.jRbFcCloseNo.text"));
      this.jRbFcCloseNo.setName("jRbFcCloseNo");
      this.jRbFcCloseNo.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent evt) {
            CfgWizardPanel4.this.jRbFcCloseNoStateChanged(evt);
         }
      });
      this.jLbFinecorsaOpen1.setText(bundle.getString("CfgWizardPanel4.jLbFinecorsaOpen1.text"));
      this.jLbFinecorsaOpen1.setName("jLbFinecorsaOpen1");
      this.jCbFinecorsaOpen1.setEnabled(false);
      this.jCbFinecorsaOpen1.setName("jCbFinecorsaOpen1");
      this.jCbFinecorsaOpen1.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent evt) {
            CfgWizardPanel4.this.jCbFinecorsaOpen1ItemStateChanged(evt);
         }
      });
      this.jCbFinecorsaOpen2.setEnabled(false);
      this.jCbFinecorsaOpen2.setName("jCbFinecorsaOpen2");
      this.jCbFinecorsaOpen2.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent evt) {
            CfgWizardPanel4.this.jCbFinecorsaOpen2ItemStateChanged(evt);
         }
      });
      this.jLbFinecorsaOpen2.setText(bundle.getString("CfgWizardPanel4.jLbFinecorsaOpen2.text"));
      this.jLbFinecorsaOpen2.setName("jLbFinecorsaOpen2");
      this.jLbFinecorsaClose1.setText(bundle.getString("CfgWizardPanel4.jLbFinecorsaClose1.text"));
      this.jLbFinecorsaClose1.setName("jLbFinecorsaClose1");
      this.jCbFinecorsaClose1.setEnabled(false);
      this.jCbFinecorsaClose1.setName("jCbFinecorsaClose1");
      this.jCbFinecorsaClose1.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent evt) {
            CfgWizardPanel4.this.jCbFinecorsaClose1ItemStateChanged(evt);
         }
      });
      this.jCbFinecorsaClose2.setEnabled(false);
      this.jCbFinecorsaClose2.setName("jCbFinecorsaClose2");
      this.jCbFinecorsaClose2.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent evt) {
            CfgWizardPanel4.this.jCbFinecorsaClose2ItemStateChanged(evt);
         }
      });
      this.jLbFinecorsaClose2.setText(bundle.getString("CfgWizardPanel4.jLbFinecorsaClose2.text"));
      this.jLbFinecorsaClose2.setName("jLbFinecorsaClose2");
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addComponent(this.jPfigureStep3, -2, -1, -2)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(this.jLbStep3)
                        .addComponent(this.jLbFcOpen)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addComponent(this.jRbFcOpenYes)
                              .addGap(18, 18, 18)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jLbFinecorsaOpen1).addComponent(this.jLbFinecorsaOpen2)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(this.jCbFinecorsaOpen2, 0, -1, 32767)
                                    .addComponent(this.jCbFinecorsaOpen1, 0, 205, 32767)
                              )
                        )
                        .addComponent(this.jRbFcOpenNo)
                        .addComponent(this.jLbFcClose)
                        .addComponent(this.jRbFcCloseNo)
                        .addGroup(
                           Alignment.TRAILING,
                           layout.createSequentialGroup()
                              .addGroup(
                                 layout.createParallelGroup(Alignment.TRAILING)
                                    .addGroup(
                                       layout.createSequentialGroup().addComponent(this.jRbFcCloseYes).addGap(18, 18, 18).addComponent(this.jLbFinecorsaClose1)
                                    )
                                    .addComponent(this.jLbFinecorsaClose2)
                              )
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addGroup(
                                 layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(this.jCbFinecorsaClose1, 0, 205, 32767)
                                    .addComponent(this.jCbFinecorsaClose2, 0, 156, 32767)
                              )
                        )
                  )
                  .addContainerGap(26, 32767)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addComponent(this.jPfigureStep3, -2, -1, -2)
            .addGroup(
               layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(this.jLbStep3)
                  .addPreferredGap(ComponentPlacement.UNRELATED)
                  .addComponent(this.jLbFcOpen)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jRbFcOpenNo, -2, 23, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(this.jRbFcOpenYes)
                        .addComponent(this.jLbFinecorsaOpen1)
                        .addComponent(this.jCbFinecorsaOpen1, -2, -1, -2)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jCbFinecorsaOpen2, -2, -1, -2).addComponent(this.jLbFinecorsaOpen2)
                  )
                  .addGap(18, 18, 18)
                  .addComponent(this.jLbFcClose)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jRbFcCloseNo, -2, 23, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(this.jRbFcCloseYes)
                        .addComponent(this.jLbFinecorsaClose1)
                        .addComponent(this.jCbFinecorsaClose1, -2, -1, -2)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jCbFinecorsaClose2, -2, -1, -2).addComponent(this.jLbFinecorsaClose2)
                  )
            )
      );
      layout.linkSize(1, this.jCbFinecorsaClose1, this.jCbFinecorsaClose2, this.jCbFinecorsaOpen1, this.jCbFinecorsaOpen2);
      this.getAccessibleContext().setAccessibleName(bundle.getString("CfgWizardPanel4.AccessibleContext.accessibleName"));
   }

   private void jCbFinecorsaOpen1ItemStateChanged(ItemEvent evt) {
      if (this.jRbFcOpenYes.isSelected() && this.jCbFinecorsaOpen1.isEnabled() && this.jCbFinecorsaOpen1.getSelectedItem() != null) {
         this.boardSettings.setFinecorsaOpenAnta1(Integer.parseInt(((KeyValue)this.jCbFinecorsaOpen1.getSelectedItem()).getKey().toString()));
      }
   }

   private void jCbFinecorsaOpen2ItemStateChanged(ItemEvent evt) {
      if (this.jRbFcOpenYes.isSelected() && this.jCbFinecorsaOpen2.isEnabled() && this.jCbFinecorsaOpen2.getSelectedItem() != null) {
         this.boardSettings.setFinecorsaOpenAnta2(Integer.parseInt(((KeyValue)this.jCbFinecorsaOpen2.getSelectedItem()).getKey().toString()));
      }
   }

   private void jCbFinecorsaClose1ItemStateChanged(ItemEvent evt) {
      if (this.jRbFcCloseYes.isSelected() && this.jCbFinecorsaClose1.isEnabled() && this.jCbFinecorsaClose1.getSelectedItem() != null) {
         this.boardSettings.setFinecorsaCloseAnta1(Integer.parseInt(((KeyValue)this.jCbFinecorsaClose1.getSelectedItem()).getKey().toString()));
      }
   }

   private void jCbFinecorsaClose2ItemStateChanged(ItemEvent evt) {
      if (this.jRbFcCloseYes.isSelected() && this.jCbFinecorsaClose2.isEnabled() && this.jCbFinecorsaClose2.getSelectedItem() != null) {
         this.boardSettings.setFinecorsaCloseAnta2(Integer.parseInt(((KeyValue)this.jCbFinecorsaClose2.getSelectedItem()).getKey().toString()));
      }
   }

   private void jRbFcOpenYesStateChanged(ChangeEvent evt) {
      if (this.jRbFcOpenYes.isSelected()) {
         if (this.nbMotor == 1) {
            this.jCbFinecorsaOpen2.setEnabled(false);
            this.jCbFinecorsaOpen1.setEnabled(true);
         } else {
            this.jCbFinecorsaOpen2.setEnabled(true);
            this.jCbFinecorsaOpen1.setEnabled(true);
         }

         this.boardSettings.setFinecorsaOpenAnta1(Integer.parseInt(((KeyValue)this.jCbFinecorsaOpen1.getSelectedItem()).getKey().toString()));
         if (this.nbMotor == 2) {
            this.boardSettings.setFinecorsaOpenAnta2(Integer.parseInt(((KeyValue)this.jCbFinecorsaOpen2.getSelectedItem()).getKey().toString()));
         }
      }
   }

   private void jRbFcCloseYesStateChanged(ChangeEvent evt) {
      if (this.jRbFcCloseYes.isSelected()) {
         if (this.nbMotor == 1) {
            this.jCbFinecorsaClose2.setEnabled(false);
            this.jCbFinecorsaClose1.setEnabled(true);
         } else {
            this.jCbFinecorsaClose2.setEnabled(true);
            this.jCbFinecorsaClose1.setEnabled(true);
         }

         this.boardSettings.setFinecorsaCloseAnta1(Integer.parseInt(((KeyValue)this.jCbFinecorsaClose1.getSelectedItem()).getKey().toString()));
         if (this.nbMotor == 2) {
            this.boardSettings.setFinecorsaCloseAnta2(Integer.parseInt(((KeyValue)this.jCbFinecorsaClose2.getSelectedItem()).getKey().toString()));
         }
      }
   }

   private void jRbFcOpenNoStateChanged(ChangeEvent evt) {
      if (this.jRbFcOpenNo.isSelected()) {
         this.boardSettings.setFinecorsaOpenAnta1(0);
         if (this.nbMotor == 2) {
            this.boardSettings.setFinecorsaOpenAnta2(0);
         }
      }
   }

   private void jRbFcCloseNoStateChanged(ChangeEvent evt) {
      if (this.jRbFcCloseNo.isSelected()) {
         this.boardSettings.setFinecorsaCloseAnta1(0);
         if (this.nbMotor == 2) {
            this.boardSettings.setFinecorsaCloseAnta2(0);
         }
      }
   }
}
