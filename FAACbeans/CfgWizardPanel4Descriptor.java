package FAACbeans;

import FAAClib.FAAC_Settings;
import com.nexes.wizard.WizardPanelDescriptor;

public class CfgWizardPanel4Descriptor extends WizardPanelDescriptor {
   public static final String IDENTIFIER = "FINECORSA_PANEL";
   CfgWizardPanel4 panel4;

   public CfgWizardPanel4Descriptor(FAAC_Settings boardSettings, String boardModel) {
      this.panel4 = new CfgWizardPanel4(boardSettings, boardModel);
      this.setPanelDescriptorIdentifier("FINECORSA_PANEL");
      this.setPanelComponent(this.panel4);
   }

   @Override
   public Object getNextPanelDescriptor() {
      return "LOGIC_PANEL";
   }

   @Override
   public Object getBackPanelDescriptor() {
      return "ENCODER_PANEL";
   }

   @Override
   public void aboutToDisplayPanel() {
      this.panel4.initPanel();
   }
}
