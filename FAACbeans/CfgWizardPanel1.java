package FAACbeans;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CfgWizardPanel1 extends JPanel {
   private JLabel jLbFigStep0;
   private JLabel jLbText1;
   private JLabel jLbText2;
   private JLabel jLbWelcome;
   private JPanel jPWelcome;

   public CfgWizardPanel1() {
      this.initComponents();
   }

   private void initComponents() {
      this.jPWelcome = new JPanel();
      this.jLbFigStep0 = new JLabel();
      this.jLbWelcome = new JLabel();
      this.jLbText1 = new JLabel();
      this.jLbText2 = new JLabel();
      this.setName("CfgWizardPanel1");
      this.setPreferredSize(new Dimension(450, 272));
      this.jPWelcome.setName("jPWelcome");
      this.jLbFigStep0.setIcon(new ImageIcon(this.getClass().getResource("/FAACbeans/resources/wizard_0.png")));
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/CfgWizardPanel1");
      this.jLbFigStep0.setText(bundle.getString("CfgWizardPanel1.jLbFigStep0.text"));
      this.jLbFigStep0.setName("jLbFigStep0");
      this.jLbWelcome.setFont(new Font("Tahoma", 1, 14));
      this.jLbWelcome.setText(bundle.getString("CfgWizardPanel1.jLbWelcome.text"));
      this.jLbWelcome.setName("jLbWelcome");
      this.jLbText1.setText(bundle.getString("CfgWizardPanel1.jLbText1.text"));
      this.jLbText1.setName("jLbText1");
      this.jLbText2.setText(bundle.getString("CfgWizardPanel1.jLbText2.text"));
      this.jLbText2.setName("jLbText2");
      GroupLayout jPWelcomeLayout = new GroupLayout(this.jPWelcome);
      this.jPWelcome.setLayout(jPWelcomeLayout);
      jPWelcomeLayout.setHorizontalGroup(
         jPWelcomeLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPWelcomeLayout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(this.jLbFigStep0)
                  .addPreferredGap(ComponentPlacement.RELATED, 37, 32767)
                  .addGroup(
                     jPWelcomeLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jLbText1)
                        .addComponent(this.jLbText2)
                        .addComponent(this.jLbWelcome)
                  )
                  .addGap(22, 22, 22)
            )
      );
      jPWelcomeLayout.setVerticalGroup(
         jPWelcomeLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPWelcomeLayout.createSequentialGroup()
                  .addGroup(
                     jPWelcomeLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jPWelcomeLayout.createSequentialGroup().addContainerGap().addComponent(this.jLbFigStep0))
                        .addGroup(
                           jPWelcomeLayout.createSequentialGroup()
                              .addGap(70, 70, 70)
                              .addComponent(this.jLbWelcome)
                              .addGap(50, 50, 50)
                              .addComponent(this.jLbText1)
                              .addPreferredGap(ComponentPlacement.RELATED)
                              .addComponent(this.jLbText2)
                        )
                  )
                  .addContainerGap(-1, 32767)
            )
      );
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(this.jPWelcome, -2, -1, -2).addContainerGap(66, 32767))
      );
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPWelcome, -2, -1, -2));
      this.getAccessibleContext().setAccessibleName(bundle.getString("CfgWizardPanel1.AccessibleContext.accessibleName"));
   }
}
