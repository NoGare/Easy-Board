package FAACbeans;

import FAAClib.FAAC_Settings;
import com.nexes.wizard.WizardPanelDescriptor;

public class CfgWizardPanel3Descriptor extends WizardPanelDescriptor {
   public static final String IDENTIFIER = "ENCODER_PANEL";
   CfgWizardPanel3 panel3;

   public CfgWizardPanel3Descriptor(FAAC_Settings boardSettings) {
      this.panel3 = new CfgWizardPanel3(boardSettings);
      this.setPanelDescriptorIdentifier("ENCODER_PANEL");
      this.setPanelComponent(this.panel3);
   }

   @Override
   public Object getNextPanelDescriptor() {
      return "FINECORSA_PANEL";
   }

   @Override
   public Object getBackPanelDescriptor() {
      return "NUMBERMOTORS_PANEL";
   }

   @Override
   public void aboutToDisplayPanel() {
      this.panel3.initPanel();
   }
}
