package FAACbeans;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FAACLogicDetails extends JDialog {
   private JButton jBOk;
   private JLabel jLbFotocellulaTitle;
   private JLabel jLbFotocellulaTxt;
   private JLabel jLbImpFermoTitle;
   private JLabel jLbImpFermoTxt;
   private JLabel jLbImpMovTitle;
   private JLabel jLbImpMovTxt;
   private JLabel jLbLogic;
   private JLabel jLbLogicTitle;
   private JPanel jPanelDetails;
   private JPanel jPanelImpFermo;
   private JPanel jPanelImpMov;
   private JPanel jPanelPhotocell;
   private String selectedLogic;
   ResourceBundle bundle;

   public FAACLogicDetails(Frame parent, boolean modal, String selectedLogic) {
      super(parent, modal);
      this.selectedLogic = selectedLogic;
      this.bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACLogicDetails");
      this.initComponents();
      this.updateLogicInfo();
      this.setLocationRelativeTo(parent);
   }

   private void updateLogicInfo() {
      this.jLbLogic.setText(this.selectedLogic);
      if (this.selectedLogic.compareToIgnoreCase("A") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicA_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicA_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicA_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("A1") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicA1_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicA1_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicA1_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("AP") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicAP_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicAP_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicAP_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("At") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicAt_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicAt_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicAt_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("b") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicb_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicb_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicb_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("bC") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicbC_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicbC_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicbC_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("C") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicC_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicC_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicC_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("CA") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicCA_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicCA_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicCA_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("Cn") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicCn_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicCn_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicCn_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("E") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicE_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicE_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicE_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("EP") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicEP_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicEP_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicEP_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("P") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicP_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicP_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicP_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("PA") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicPA_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicPA_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicPA_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("S") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicS_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicS_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicS_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("SA") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicSA_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicSA_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicSA_Fot"));
      } else if (this.selectedLogic.compareToIgnoreCase("SP") == 0) {
         this.jLbImpFermoTxt.setText(this.bundle.getString("logicSP_impFermo"));
         this.jLbImpMovTxt.setText(this.bundle.getString("logicSP_impMov"));
         this.jLbFotocellulaTxt.setText(this.bundle.getString("logicSP_Fot"));
      }
   }

   private void initComponents() {
      this.jLbLogicTitle = new JLabel();
      this.jLbLogic = new JLabel();
      this.jPanelDetails = new JPanel();
      this.jPanelImpFermo = new JPanel();
      this.jLbImpFermoTitle = new JLabel();
      this.jLbImpFermoTxt = new JLabel();
      this.jPanelImpMov = new JPanel();
      this.jLbImpMovTitle = new JLabel();
      this.jLbImpMovTxt = new JLabel();
      this.jPanelPhotocell = new JPanel();
      this.jLbFotocellulaTitle = new JLabel();
      this.jLbFotocellulaTxt = new JLabel();
      this.jBOk = new JButton();
      this.setDefaultCloseOperation(2);
      ResourceBundle bundle = ResourceBundle.getBundle("FAACbeans/resources/FAACLogicDetails");
      this.setTitle(bundle.getString("FAACLogicDetails.title"));
      this.setName("FAACLogicDetails");
      this.setResizable(false);
      this.jLbLogicTitle.setText(bundle.getString("FAACLogicDetails.jLbLogicTitle.text"));
      this.jLbLogicTitle.setName("jLbLogicTitle");
      this.jLbLogic.setFont(new Font("Tahoma", 1, 11));
      this.jLbLogic.setText(bundle.getString("FAACLogicDetails.jLbLogic.text"));
      this.jLbLogic.setName("jLbLogic");
      this.jPanelDetails.setBorder(BorderFactory.createTitledBorder(bundle.getString("FAACLogicDetails.jPanelDetails.border.title")));
      this.jPanelDetails.setName("jPanelDetails");
      this.jPanelImpFermo.setName("jPanelImpFermo");
      this.jLbImpFermoTitle.setText(bundle.getString("FAACLogicDetails.jLbImpFermoTitle.text"));
      this.jLbImpFermoTitle.setName("jLbImpFermoTitle");
      this.jLbImpFermoTxt.setHorizontalAlignment(2);
      this.jLbImpFermoTxt.setName("jLbImpFermoTxt");
      GroupLayout jPanelImpFermoLayout = new GroupLayout(this.jPanelImpFermo);
      this.jPanelImpFermo.setLayout(jPanelImpFermoLayout);
      jPanelImpFermoLayout.setHorizontalGroup(
         jPanelImpFermoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelImpFermoLayout.createSequentialGroup()
                  .addComponent(this.jLbImpFermoTitle)
                  .addPreferredGap(ComponentPlacement.RELATED, 61, 32767)
                  .addComponent(this.jLbImpFermoTxt, -2, 305, -2)
            )
      );
      jPanelImpFermoLayout.setVerticalGroup(
         jPanelImpFermoLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelImpFermoLayout.createSequentialGroup()
                  .addGap(0, 0, 0)
                  .addGroup(
                     jPanelImpFermoLayout.createParallelGroup(Alignment.CENTER)
                        .addComponent(this.jLbImpFermoTitle)
                        .addComponent(this.jLbImpFermoTxt, -2, 40, -2)
                  )
                  .addGap(0, 0, 0)
            )
      );
      this.jPanelImpMov.setName("jPanelImpMov");
      this.jLbImpMovTitle.setText(bundle.getString("FAACLogicDetails.jLbImpMovTitle.text"));
      this.jLbImpMovTitle.setName("jLbImpMovTitle");
      this.jLbImpMovTxt.setHorizontalAlignment(2);
      this.jLbImpMovTxt.setName("jLbImpMovTxt");
      GroupLayout jPanelImpMovLayout = new GroupLayout(this.jPanelImpMov);
      this.jPanelImpMov.setLayout(jPanelImpMovLayout);
      jPanelImpMovLayout.setHorizontalGroup(
         jPanelImpMovLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               Alignment.TRAILING,
               jPanelImpMovLayout.createSequentialGroup()
                  .addComponent(this.jLbImpMovTitle, -2, 163, -2)
                  .addPreferredGap(ComponentPlacement.RELATED, -1, 32767)
                  .addComponent(this.jLbImpMovTxt, -2, 305, -2)
            )
      );
      jPanelImpMovLayout.setVerticalGroup(
         jPanelImpMovLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelImpMovLayout.createSequentialGroup()
                  .addGap(0, 0, 0)
                  .addGroup(
                     jPanelImpMovLayout.createParallelGroup(Alignment.CENTER).addComponent(this.jLbImpMovTitle).addComponent(this.jLbImpMovTxt, -2, 40, -2)
                  )
                  .addGap(0, 0, 0)
            )
      );
      this.jPanelPhotocell.setName("jPanelPhotocell");
      this.jLbFotocellulaTitle.setText(bundle.getString("FAACLogicDetails.jLbFotocellulaTitle.text"));
      this.jLbFotocellulaTitle.setName("jLbFotocellulaTitle");
      this.jLbFotocellulaTxt.setHorizontalAlignment(2);
      this.jLbFotocellulaTxt.setName("jLbFotocellulaTxt");
      GroupLayout jPanelPhotocellLayout = new GroupLayout(this.jPanelPhotocell);
      this.jPanelPhotocell.setLayout(jPanelPhotocellLayout);
      jPanelPhotocellLayout.setHorizontalGroup(
         jPanelPhotocellLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelPhotocellLayout.createSequentialGroup()
                  .addComponent(this.jLbFotocellulaTitle)
                  .addPreferredGap(ComponentPlacement.RELATED, 65, 32767)
                  .addComponent(this.jLbFotocellulaTxt, -2, 305, -2)
            )
      );
      jPanelPhotocellLayout.setVerticalGroup(
         jPanelPhotocellLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelPhotocellLayout.createSequentialGroup()
                  .addGap(0, 0, 0)
                  .addGroup(
                     jPanelPhotocellLayout.createParallelGroup(Alignment.CENTER)
                        .addComponent(this.jLbFotocellulaTitle)
                        .addComponent(this.jLbFotocellulaTxt, -1, 40, 32767)
                  )
                  .addGap(0, 0, 0)
            )
      );
      GroupLayout jPanelDetailsLayout = new GroupLayout(this.jPanelDetails);
      this.jPanelDetails.setLayout(jPanelDetailsLayout);
      jPanelDetailsLayout.setHorizontalGroup(
         jPanelDetailsLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(this.jPanelImpFermo, Alignment.TRAILING, -2, -1, -2)
            .addComponent(this.jPanelImpMov, -2, -1, -2)
            .addComponent(this.jPanelPhotocell, -2, -1, -2)
      );
      jPanelDetailsLayout.linkSize(0, this.jPanelImpFermo, this.jPanelImpMov, this.jPanelPhotocell);
      jPanelDetailsLayout.setVerticalGroup(
         jPanelDetailsLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               jPanelDetailsLayout.createSequentialGroup()
                  .addComponent(this.jPanelImpFermo, -2, -1, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jPanelImpMov, -2, -1, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jPanelPhotocell, -2, -1, -2)
            )
      );
      this.jBOk.setText(bundle.getString("FAACLogicDetails.jBOk.text"));
      this.jBOk.setName("jBOk");
      this.jBOk.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            FAACLogicDetails.this.jBOkMousePressed(evt);
         }
      });
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(
                     layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.jPanelDetails, -2, -1, -2)
                        .addGroup(
                           layout.createSequentialGroup()
                              .addComponent(this.jLbLogicTitle)
                              .addPreferredGap(ComponentPlacement.UNRELATED)
                              .addComponent(this.jLbLogic)
                        )
                        .addComponent(this.jBOk, Alignment.TRAILING)
                  )
                  .addContainerGap()
            )
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(Alignment.LEADING)
            .addGroup(
               layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLbLogicTitle).addComponent(this.jLbLogic))
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jPanelDetails, -2, -1, -2)
                  .addPreferredGap(ComponentPlacement.RELATED)
                  .addComponent(this.jBOk)
                  .addContainerGap(16, 32767)
            )
      );
      this.getAccessibleContext().setAccessibleName(bundle.getString("FAACLogicDetails.AccessibleContext.accessibleName"));
      this.pack();
   }

   private void jBOkMousePressed(MouseEvent evt) {
      this.setVisible(false);
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
            FAACLogicDetails dialog = new FAACLogicDetails(new JFrame(), true, "");
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
