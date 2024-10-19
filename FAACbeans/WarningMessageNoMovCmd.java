package FAACbeans;

import FAAClib.FAAC_Commands;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class WarningMessageNoMovCmd extends JDialog {
   public static final String noMovCmdType_Reset = "Reset";
   public static final String noMovCmdType_IscrBus = "IscrBus";
   public static final String noMovCmdType_ZeroCycles = "ZeroCycles";
   public static final String noMovCmdType_ZeroMemEmerg = "ZeroMemEmerg";
   public static final String noMovCmdType_acqRadio1 = "AcqRadio1";
   public static final String noMovCmdType_acqRadio2 = "AcqRadio2";
   public static final String noMovCmdType_Setup = "Setup";
   private JButton jBtNo;
   private JButton jBtYes;
   private JLabel jLbMsg;
   private String cmdType;
   private String msg;
   private FAAC_Commands boardCommands;

   public WarningMessageNoMovCmd(Frame parent, boolean modal, String msg, String noMovCmdType, FAAC_Commands boardCommands) {
      super(parent, modal);
      this.setLocationRelativeTo(parent);
      this.initComponents();
      this.cmdType = noMovCmdType;
      this.msg = msg;
      this.jLbMsg.setText(msg);
      this.boardCommands = boardCommands;
      this.setIconImage(null);
   }

   private void initComponents() {
      this.jLbMsg = new JLabel();
      this.jBtYes = new JButton();
      this.jBtNo = new JButton();
      this.setDefaultCloseOperation(2);
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/WarningMessageNoMovCmd");
      this.setTitle(bundle.getString("WarningMessageNoMovCmd.title"));
      this.setIconImage(null);
      this.setName("WarningMessageNoMovCmd");
      this.setResizable(false);
      this.jLbMsg.setText(bundle.getString("WarningMessageNoMovCmd.jLbMsg.text"));
      this.jLbMsg.setName("jLbMsg");
      this.jBtYes.setText(bundle.getString("WarningMessageNoMovCmd.jBtYes.text"));
      this.jBtYes.setName("jBtYes");
      this.jBtYes.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            WarningMessageNoMovCmd.this.jBtYesMousePressed(evt);
         }

         @Override
         public void mouseReleased(MouseEvent evt) {
            WarningMessageNoMovCmd.this.jBtYesMouseReleased(evt);
         }
      });
      this.jBtNo.setText(bundle.getString("WarningMessageNoMovCmd.jBtNo.text"));
      this.jBtNo.setName("jBtNo");
      this.jBtNo.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            WarningMessageNoMovCmd.this.jBtNoMousePressed(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addContainerGap(24, 32767)
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jLbMsg, -2, 455, -2).addGap(22, 22, 22))
                        .addGroup(
                           Alignment.TRAILING,
                           layout.createSequentialGroup()
                              .addComponent(this.jBtYes, -2, 65, -2)
                              .addGap(18, 18, 18)
                              .addComponent(this.jBtNo, -2, 65, -2)
                              .addGap(171, 171, 171)
                        )
                  )
            )
      );
      layout.linkSize(0, this.jBtNo, this.jBtYes);
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(this.jLbMsg, -2, 32, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(this.jBtNo).addComponent(this.jBtYes))
                  .addContainerGap(-1, 32767)
            )
      );
      layout.linkSize(1, this.jBtNo, this.jBtYes);
      this.getAccessibleContext().setAccessibleName(bundle.getString("WarningMessageNoMovCmd.AccessibleContext.accessibleName"));
      this.pack();
   }

   private void jBtYesMousePressed(MouseEvent evt) {
      boolean bSet = true;
      if (this.cmdType.equals("Reset")) {
         this.boardCommands.setIsResetCmdRequested(bSet);
         this.boardCommands.getNoMovCmdArg().set20_reset(bSet);
      } else if (this.cmdType.equals("IscrBus")) {
         this.boardCommands.getNoMovCmdArg().set02_iscrBus(bSet);
      } else if (this.cmdType.equals("ZeroCycles")) {
         this.boardCommands.getNoMovCmdArg().set03_zeroNbCycles(bSet);
      } else if (this.cmdType.equals("ZeroMemEmerg")) {
         this.boardCommands.getNoMovCmdArg().set06_zeroEmergMem(bSet);
      } else if (this.cmdType.equals("AcqRadio1")) {
         this.boardCommands.getNoMovCmdArg().set09_memRadio1(bSet);
      } else if (this.cmdType.equals("AcqRadio2")) {
         this.boardCommands.getNoMovCmdArg().set10_memRadio2(bSet);
      } else if (this.cmdType.equals("Setup")) {
         this.boardCommands.getNoMovCmdArg().set19_setupSimple(bSet);
      }
   }

   private void jBtYesMouseReleased(MouseEvent evt) {
      try {
         Thread.sleep(250L);
      } catch (InterruptedException var3) {
         Logger.getLogger(WarningMessageNoMovCmd.class.getName()).log(Level.SEVERE, null, var3);
      }

      boolean bSet = false;
      if (this.cmdType.equals("Reset")) {
         this.boardCommands.getNoMovCmdArg().set20_reset(bSet);
      } else if (this.cmdType.equals("IscrBus")) {
         this.boardCommands.getNoMovCmdArg().set02_iscrBus(bSet);
      } else if (this.cmdType.equals("ZeroCycles")) {
         this.boardCommands.getNoMovCmdArg().set03_zeroNbCycles(bSet);
      } else if (this.cmdType.equals("ZeroMemEmerg")) {
         this.boardCommands.getNoMovCmdArg().set06_zeroEmergMem(bSet);
      } else if (this.cmdType.equals("AcqRadio1")) {
         this.boardCommands.getNoMovCmdArg().set09_memRadio1(bSet);
      } else if (this.cmdType.equals("AcqRadio2")) {
         this.boardCommands.getNoMovCmdArg().set10_memRadio2(bSet);
      } else if (this.cmdType.equals("Setup")) {
         this.boardCommands.getNoMovCmdArg().set19_setupSimple(bSet);
      }

      this.setVisible(false);
   }

   private void jBtNoMousePressed(MouseEvent evt) {
      this.setVisible(false);
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
            WarningMessageNoMovCmd dialog = new WarningMessageNoMovCmd(new JFrame(), true, "", "", new FAAC_Commands());
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
}
