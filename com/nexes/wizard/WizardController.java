package com.nexes.wizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WizardController implements ActionListener {
   private Wizard wizard;

   public WizardController(Wizard w) {
      this.wizard = w;
   }

   @Override
   public void actionPerformed(ActionEvent evt) {
      if (evt.getActionCommand().equals("CancelButtonActionCommand")) {
         this.cancelButtonPressed();
      } else if (evt.getActionCommand().equals("BackButtonActionCommand")) {
         this.backButtonPressed();
      } else if (evt.getActionCommand().equals("NextButtonActionCommand")) {
         this.nextButtonPressed();
      }
   }

   private void cancelButtonPressed() {
      this.wizard.close(1);
   }

   private void nextButtonPressed() {
      WizardModel model = this.wizard.getModel();
      WizardPanelDescriptor descriptor = model.getCurrentPanelDescriptor();
      Object nextPanelDescriptor = descriptor.getNextPanelDescriptor();
      if (nextPanelDescriptor instanceof WizardPanelDescriptor.FinishIdentifier) {
         this.wizard.close(0);
      } else {
         this.wizard.setCurrentPanel(nextPanelDescriptor);
      }
   }

   private void backButtonPressed() {
      WizardModel model = this.wizard.getModel();
      WizardPanelDescriptor descriptor = model.getCurrentPanelDescriptor();
      Object backPanelDescriptor = descriptor.getBackPanelDescriptor();
      this.wizard.setCurrentPanel(backPanelDescriptor);
   }

   void resetButtonsToPanelRules() {
      WizardModel model = this.wizard.getModel();
      WizardPanelDescriptor descriptor = model.getCurrentPanelDescriptor();
      model.setCancelButtonText(Wizard.CANCEL_TEXT);
      model.setCancelButtonIcon(Wizard.CANCEL_ICON);
      model.setBackButtonText(Wizard.BACK_TEXT);
      model.setBackButtonIcon(Wizard.BACK_ICON);
      if (descriptor.getBackPanelDescriptor() != null) {
         model.setBackButtonEnabled(Boolean.TRUE);
      } else {
         model.setBackButtonEnabled(Boolean.FALSE);
      }

      if (descriptor.getNextPanelDescriptor() != null) {
         model.setNextFinishButtonEnabled(Boolean.TRUE);
      } else {
         model.setNextFinishButtonEnabled(Boolean.FALSE);
      }

      if (descriptor.getNextPanelDescriptor() instanceof WizardPanelDescriptor.FinishIdentifier) {
         model.setNextFinishButtonText(Wizard.FINISH_TEXT);
         model.setNextFinishButtonIcon(Wizard.FINISH_ICON);
      } else {
         model.setNextFinishButtonText(Wizard.NEXT_TEXT);
         model.setNextFinishButtonIcon(Wizard.NEXT_ICON);
      }
   }
}
