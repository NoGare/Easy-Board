package FAACbeans;

import FAAClib.Debug;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BackgroundWorkDialog extends JDialog {
   private JLabel jLbMsg;
   private JLabel jLbWait;
   private JProgressBar jProgressBarProcess;
   private int maximumLevel;
   private int valueLevel;
   private boolean operationOngoing;
   static ResourceBundle bundle;

   public BackgroundWorkDialog(Frame parent, boolean modal, int maximumLevel) {
      super(parent, modal);
      this.initComponents();
      bundle = ResourceBundle.getBundle("FAACbeans/resources/BackgroundWorkDialog");
      this.maximumLevel = maximumLevel;
      this.jProgressBarProcess.setMaximum(maximumLevel);
      this.jProgressBarProcess.repaint();
   }

   public void reset() {
      this.jProgressBarProcess.setValue(0);
      this.valueLevel = 0;
      this.jProgressBarProcess.repaint();
      Debug.printlnStatic("Background worker level: " + this.valueLevel + " Progress bar level: " + this.jProgressBarProcess.getValue());
   }

   public void updateLocale() {
      this.initComponentsForLocalization();
      this.repaint();
   }

   private void initComponents() {
      this.jLbMsg = new JLabel();
      this.jProgressBarProcess = new JProgressBar();
      this.jLbWait = new JLabel();
      this.setDefaultCloseOperation(0);
      this.setName("BackgroundWorkDialog");
      this.setResizable(false);
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent evt) {
            BackgroundWorkDialog.this.formWindowClosing(evt);
         }
      });
      this.jLbMsg.setText("Processo in corso...");
      this.jLbWait.setText("Attendere il termine dell'esecuzione");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGap(33, 33, 33)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(this.jLbWait)
                        .addComponent(this.jLbMsg, -1, -1, 32767)
                        .addComponent(this.jProgressBarProcess, -2, 341, -2)
                  )
                  .addContainerGap(38, 32767)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGap(23, 23, 23)
                  .addComponent(this.jLbMsg, -1, -1, 32767)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jProgressBarProcess, -2, -1, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jLbWait)
                  .addContainerGap(21, 32767)
            )
      );
      this.getAccessibleContext().setAccessibleName("BackgroundWorkDialog");
      this.pack();
   }

   private void initComponentsForLocalization() {
      this.jLbWait.setText(bundle.getString("jLbWait.text"));
   }

   private void formWindowClosing(WindowEvent evt) {
      int actionDialog = JOptionPane.showConfirmDialog(this, bundle.getString("closingMsg"), bundle.getString("Attention"), 0);
      if (actionDialog == 0) {
         this.setVisible(false);
         this.dispose();
      }
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
            BackgroundWorkDialog dialog = new BackgroundWorkDialog(new JFrame(), true, 100);
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

   public String getMsg() {
      return this.jLbMsg.getText();
   }

   public void setMsg(String text) {
      this.jLbMsg.setText(text);
   }

   public int getMaximumLevel() {
      this.maximumLevel = this.jProgressBarProcess.getMaximum();
      return this.maximumLevel;
   }

   public void setMaximumLevel(int maximumLevel) {
      this.maximumLevel = maximumLevel;
      this.jProgressBarProcess.setMaximum(maximumLevel);
      this.jProgressBarProcess.repaint();
   }

   public int getValueLevel() {
      this.valueLevel = this.jProgressBarProcess.getValue();
      return this.valueLevel;
   }

   public void setValueLevel(int value) {
      this.valueLevel = value;
      this.jProgressBarProcess.setValue(this.valueLevel);
      this.jProgressBarProcess.repaint();
      Debug.printlnStatic("Progress bar level: " + this.jProgressBarProcess.getValue());
   }

   public synchronized void incrementValue(int deltaValue) {
      Debug.printlnStatic("Incremet value");
      this.valueLevel += deltaValue;
      if (this.valueLevel < this.maximumLevel) {
         this.jProgressBarProcess.setValue(this.valueLevel);
         this.jProgressBarProcess.repaint();
      } else {
         this.operationOngoing = false;
         this.setVisible(false);
      }
   }

   public synchronized boolean isOperationOngoing() {
      return this.operationOngoing;
   }

   public void setOperationOngoing(boolean operationOngoing) {
      this.operationOngoing = operationOngoing;
   }
}
