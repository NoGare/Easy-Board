package FAACbeans;

import FAAClib.FAAC_Monitor;
import FAAClib.FAAC_Settings;
import com.nexes.wizard.WizardPanelDescriptor;

public class CfgWizardPanel2Descriptor extends WizardPanelDescriptor {
   public static final String IDENTIFIER = "NUMBERMOTORS_PANEL";
   CfgWizardPanel2 panel2;

   public CfgWizardPanel2Descriptor(FAAC_Settings boardSettings, String boardModel, FAAC_Monitor boardMonitor) {
      this.panel2 = new CfgWizardPanel2(boardSettings, boardModel);
      this.setPanelDescriptorIdentifier("NUMBERMOTORS_PANEL");
      this.setPanelComponent(this.panel2);
   }

   @Override
   public void aboutToDisplayPanel() {
      this.panel2.initPanel();
   }

   @Override
   public Object getNextPanelDescriptor() {
      return "ENCODER_PANEL";
   }

   @Override
   public Object getBackPanelDescriptor() {
      return null;
   }
}
