package FAACbeans;

import FAAClib.FAAC_Settings;
import com.nexes.wizard.WizardPanelDescriptor;

public class CfgWizardPanel5Descriptor extends WizardPanelDescriptor {
   public static final String IDENTIFIER = "LOGIC_PANEL";
   CfgWizardPanel5 panel5;

   public CfgWizardPanel5Descriptor(FAAC_Settings boardSettings, String boardModel) {
      this.panel5 = new CfgWizardPanel5(boardSettings, boardModel);
      this.setPanelDescriptorIdentifier("LOGIC_PANEL");
      this.setPanelComponent(this.panel5);
   }

   @Override
   public Object getNextPanelDescriptor() {
      return FINISH;
   }

   @Override
   public Object getBackPanelDescriptor() {
      return "FINECORSA_PANEL";
   }

   @Override
   public void aboutToDisplayPanel() {
      this.panel5.initPanel();
   }
}
