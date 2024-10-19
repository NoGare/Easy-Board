package FAACbeans;

import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class NoNcSelector extends JPanel {
   private ButtonGroup bGnonc;
   private JRadioButton jRBnc;
   private JRadioButton jRBno;

   public NoNcSelector() {
      this.initComponents();
   }

   public NoNcSelector(boolean isNc) {
      this.initComponents();
      this.jRBnc.setSelected(true);
   }

   public boolean isNC() {
      return this.jRBnc.isSelected();
   }

   public void setNC() {
      this.jRBnc.setSelected(true);
      this.jRBno.setSelected(false);
   }

   public void setNO() {
      this.jRBno.setSelected(true);
      this.jRBnc.setSelected(false);
   }

   public void setColorBackground(Color color) {
      this.jRBno.setBackground(color);
      this.jRBnc.setBackground(color);
   }

   @Override
   public void setEnabled(boolean enabled) {
      this.jRBnc.setEnabled(enabled);
      this.jRBno.setEnabled(enabled);
   }

   private void initComponents() {
      this.bGnonc = new ButtonGroup();
      this.jRBno = new JRadioButton();
      this.jRBnc = new JRadioButton();
      this.setName("NoNcSelector");
      this.bGnonc.add(this.jRBno);
      this.jRBno.setSelected(true);
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/NoNcSelector");
      this.jRBno.setText(bundle.getString("NoNcSelector.jRBno.text"));
      this.jRBno.setToolTipText(bundle.getString("NoNcSelector.jRBno.toolTipText"));
      this.bGnonc.add(this.jRBnc);
      this.jRBnc.setText(bundle.getString("NoNcSelector.jRBnc.text"));
      this.jRBnc.setToolTipText(bundle.getString("NoNcSelector.jRBnc.toolTipText"));
      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(this.jRBno).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jRBnc))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jRBno).addComponent(this.jRBnc))
      );
      this.getAccessibleContext().setAccessibleName(bundle.getString("NoNcSelector.AccessibleContext.accessibleName"));
   }
}
