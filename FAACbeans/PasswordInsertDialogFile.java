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

public class PasswordInsertDialogFile extends JDialog {
   private JButton jBtCancel;
   private JButton jBtDefault;
   private JButton jBtOk;
   private JLabel jLbPsw;
   private JLabel jLbPsw1;
   private JPasswordField jPfPsw;
   private JPasswordField jPfPsw_rep;
   private String password;
   private String boardPassword;
   private boolean isPswInserted;
   ResourceBundle bundle;

   public PasswordInsertDialogFile(Frame parent, boolean modal) {
      super(parent, modal);
      this.initComponents();
      this.setLocationRelativeTo(parent);
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/PasswordInsertDialogFile");
      this.password = "0000";
      this.isPswInserted = false;
   }

   private void initComponents() {
      this.jPfPsw = new JPasswordField();
      this.jLbPsw = new JLabel();
      this.jBtCancel = new JButton();
      this.jBtOk = new JButton();
      this.jBtDefault = new JButton();
      this.jLbPsw1 = new JLabel();
      this.jPfPsw_rep = new JPasswordField();
      this.setDefaultCloseOperation(2);
      ResourceBundle resourceMap = ResourceBundle.getBundle("FAACbeans/resources/PasswordInsertDialogFile");
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
            PasswordInsertDialogFile.this.jBtCancelMousePressed(evt);
         }
      });
      this.jBtOk.setText(resourceMap.getString("jBtOk.text"));
      this.jBtOk.setName("jBtOk");
      this.jBtOk.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            PasswordInsertDialogFile.this.jBtOkMousePressed(evt);
         }
      });
      this.jBtDefault.setText(resourceMap.getString("jBtDefault.text"));
      this.jBtDefault.setName("jBtDefault");
      this.jBtDefault.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            PasswordInsertDialogFile.this.jBtDefaultMousePressed(evt);
         }
      });
      this.jLbPsw1.setText(resourceMap.getString("jLbPsw1.text"));
      this.jLbPsw1.setName("jLbPsw1");
      this.jPfPsw_rep.setColumns(4);
      this.jPfPsw_rep.setName("jPfPsw_rep");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jBtOk, Alignment.TRAILING)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addContainerGap()
                              .addGroup(
                                 layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLbPsw, -2, 130, -2).addComponent(this.jLbPsw1, -2, 130, -2)
                              )
                        )
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(
                     layout.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(this.jPfPsw, Alignment.LEADING)
                        .addComponent(this.jBtCancel, Alignment.LEADING, -1, -1, 32767)
                        .addComponent(this.jPfPsw_rep, Alignment.LEADING)
                  )
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBtDefault)
                  .addContainerGap(21, 32767)
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
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jPfPsw_rep, -2, -1, -2).addComponent(this.jLbPsw1))
                  .addPreferredGap(ComponentPlacement.RELATED, 22, 32767)
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jBtCancel).addComponent(this.jBtOk))
                  .addGap(20, 20, 20)
            )
      );
      this.getAccessibleContext().setAccessibleName(resourceMap.getString("PasswordInsertDialog.AccessibleContext.accessibleName"));
      this.pack();
   }

   private void jBtDefaultMousePressed(MouseEvent evt) {
      this.jPfPsw.setText("0000");
      this.jPfPsw_rep.setText("0000");
   }

   private void jBtOkMousePressed(MouseEvent evt) {
      char[] psw = this.jPfPsw.getPassword();
      if (psw.length != 4) {
         JOptionPane.showMessageDialog(this, this.bundle.getString("pswLengthError"), this.bundle.getString("attention"), 0);
         this.jPfPsw.setText("");
         this.jPfPsw_rep.setText("");
      } else {
         String tmpPsw_1 = new String(psw);
         String tmpPsw_2 = new String(this.jPfPsw_rep.getPassword());
         if (tmpPsw_1.compareTo(tmpPsw_2) != 0) {
            JOptionPane.showMessageDialog(this, this.bundle.getString("pswRepError"), this.bundle.getString("attention"), 0);
            this.password = "";
            this.setIsPswInserted(false);
            this.jPfPsw.setText("");
            this.jPfPsw_rep.setText("");
            this.jPfPsw.requestFocus();
         } else {
            this.password = tmpPsw_1;
            this.setIsPswInserted(true);
            this.setVisible(false);
         }
      }
   }

   private void jBtCancelMousePressed(MouseEvent evt) {
      this.setIsPswInserted(false);
      JOptionPane.showMessageDialog(this, this.bundle.getString("fileSaveCanceled"), this.bundle.getString("attention"), 0);
      this.setVisible(false);
   }

   public String getPassword(String boardPassword, boolean isBoardConnected) {
      int res;
      if (isBoardConnected) {
         res = JOptionPane.showConfirmDialog(this, this.bundle.getString("useBoardPsw"), this.bundle.getString("insertPassword"), 0);
         this.jPfPsw.setText("");
      } else {
         res = 1;
      }

      if (res == 0) {
         return boardPassword;
      } else {
         this.isPswInserted = false;
         this.setVisible(true);
         return this.isPswInserted ? this.password : "";
      }
   }

   public boolean getIsPswInserted() {
      return this.isPswInserted;
   }

   public void setIsPswInserted(boolean isPswInserted) {
      this.isPswInserted = isPswInserted;
   }
}
