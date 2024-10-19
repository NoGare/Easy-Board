package FAACbeans;

import com.nexes.wizard.WizardPanelDescriptor;

public class CfgWizardPanel1Descriptor extends WizardPanelDescriptor {
   public static final String IDENTIFIER = "INTRODUCTION_PANEL";

   public CfgWizardPanel1Descriptor() {
      super("INTRODUCTION_PANEL", new CfgWizardPanel1());
   }

   @Override
   public Object getNextPanelDescriptor() {
      return "NUMBERMOTORS_PANEL";
   }

   @Override
   public Object getBackPanelDescriptor() {
      return null;
   }
}
