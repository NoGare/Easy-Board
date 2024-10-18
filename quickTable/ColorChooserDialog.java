package quickTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

class ColorChooserDialog extends JDialog {
   private Color initialColor;
   private Color retColor;
   private JColorChooser chooserPane;

   public ColorChooserDialog(Component c, String title, final JColorChooser chooserPane) {
      super(JOptionPane.getFrameForComponent(c), title, true);
      this.setResizable(false);
      this.chooserPane = chooserPane;
      String okString = UIManager.getString("ColorChooser.okText");
      String cancelString = UIManager.getString("ColorChooser.cancelText");
      String resetString = UIManager.getString("ColorChooser.resetText");
      Container contentPane = this.getContentPane();
      contentPane.setLayout(new BorderLayout());
      contentPane.add(chooserPane, "Center");
      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new FlowLayout(1));
      JButton okButton = new JButton(okString);
      this.getRootPane().setDefaultButton(okButton);
      okButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            ColorChooserDialog.this.retColor = chooserPane.getColor();
            ColorChooserDialog.this.setVisible(false);
         }
      });
      buttonPane.add(okButton);
      JButton cancelButton = new JButton(cancelString);
      cancelButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            ColorChooserDialog.this.retColor = null;
            ColorChooserDialog.this.setVisible(false);
         }
      });
      buttonPane.add(cancelButton);
      JButton resetButton = new JButton(resetString);
      resetButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            chooserPane.setColor(ColorChooserDialog.this.initialColor);
         }
      });
      buttonPane.add(resetButton);
      contentPane.add(buttonPane, "South");
      this.pack();
      this.setLocationRelativeTo(c);
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            ColorChooserDialog.this.setVisible(false);
         }
      });
   }

   public Color getColor() {
      return this.retColor;
   }
}
