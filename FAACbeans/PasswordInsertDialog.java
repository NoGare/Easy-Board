package FAACbeans;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PasswordInsertDialog extends JDialog {
   private JButton jBtCancel;
   private JButton jBtDefault;
   private JButton jBtOk;
   private JLabel jLbPsw;
   private JPasswordField jPfPsw;
   private String password;
   private boolean isPswInserted;
   private ResourceBundle resourceMap;

   public PasswordInsertDialog(Frame parent, boolean modal) {
      super(parent, modal);
      this.initComponents();
      this.setLocationRelativeTo(parent);
      this.resourceMap = ResourceBundle.getBundle("FAACbeans/resources/PasswordInsertDialog");
      this.password = "0000";
      this.isPswInserted = false;
   }

   private void initComponents() {
      this.jPfPsw = new JPasswordField();
      this.jLbPsw = new JLabel();
      this.jBtCancel = new JButton();
      this.jBtOk = new JButton();
      this.jBtDefault = new JButton();
      this.setDefaultCloseOperation(2);
      ResourceBundle resourceMap = ResourceBundle.getBundle("FAACbeans/resources/PasswordInsertDialog");
      this.setTitle(resourceMap.getString("PasswordInsertDialog.title"));
      this.setName("PasswordInsertDialog");
      this.setResizable(false);
      this.jPfPsw.setColumns(4);
      this.jPfPsw.setText(resourceMap.getString("jPfPsw.text"));
      this.jPfPsw.setName("jPfPsw");
      this.jLbPsw.setText(resourceMap.getString("jLbPsw.text"));
      this.jLbPsw.setName("jLbPsw");
      this.jBtCancel.setText(resourceMap.getString("jBtCancel.text"));
      this.jBtCancel.setName("jBtCancel");
      this.jBtCancel.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            PasswordInsertDialog.this.jBtCancelMousePressed(evt);
         }
      });
      this.jBtOk.setText(resourceMap.getString("jBtOk.text"));
      this.jBtOk.setName("jBtOk");
      this.jBtOk.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            PasswordInsertDialog.this.jBtOkMousePressed(evt);
         }
      });
      this.jBtDefault.setText(resourceMap.getString("jBtDefault.text"));
      this.jBtDefault.setName("jBtDefault");
      this.jBtDefault.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            PasswordInsertDialog.this.jBtDefaultMousePressed(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGap(22, 22, 22)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jBtOk).addPreferredGap(ComponentPlacement.UNRELATED))
                        .addGroup(layout.createSequentialGroup().addComponent(this.jLbPsw).addPreferredGap(ComponentPlacement.RELATED))
                  )
                  .addGroup(
                     layout.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(this.jPfPsw, Alignment.LEADING)
                        .addComponent(this.jBtCancel, Alignment.LEADING, -1, -1, 32767)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtDefault)
                  .addContainerGap(16, 32767)
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.jPfPsw, -2, -1, -2)
                        .addComponent(this.jLbPsw)
                        .addComponent(this.jBtDefault)
                  )
                  .addGap(18, 18, 18)
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jBtCancel).addComponent(this.jBtOk))
                  .addContainerGap(-1, 32767)
            )
      );
      this.getAccessibleContext().setAccessibleName(resourceMap.getString("PasswordInsertDialog.AccessibleContext.accessibleName"));
      this.pack();
   }

   private void jBtDefaultMousePressed(MouseEvent evt) {
      this.jPfPsw.setText("0000");
   }

   private void jBtOkMousePressed(MouseEvent evt) {
      char[] psw = this.jPfPsw.getPassword();
      if (psw.length != 4) {
         JOptionPane.showMessageDialog(this, this.resourceMap.getString("pswLengthError"), this.resourceMap.getString("Attention"), 0);
         this.jPfPsw.setText("");
      } else {
         this.password = new String(psw);
         this.setIsPswInserted(true);
         this.setVisible(false);
      }
   }

   private void jBtCancelMousePressed(MouseEvent evt) {
      this.setIsPswInserted(false);
      this.setVisible(false);
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean getIsPswInserted() {
      return this.isPswInserted;
   }

   public void setIsPswInserted(boolean isPswInserted) {
      this.isPswInserted = isPswInserted;
   }
}
