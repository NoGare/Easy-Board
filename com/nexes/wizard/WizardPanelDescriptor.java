package com.nexes.wizard;

import java.awt.Component;
import javax.swing.JPanel;

public class WizardPanelDescriptor {
   private static final String DEFAULT_PANEL_IDENTIFIER = "defaultPanelIdentifier";
   public static final WizardPanelDescriptor.FinishIdentifier FINISH = new WizardPanelDescriptor.FinishIdentifier();
   private Wizard wizard;
   private Component targetPanel;
   private Object panelIdentifier;

   public WizardPanelDescriptor() {
      this.panelIdentifier = "defaultPanelIdentifier";
      this.targetPanel = new JPanel();
   }

   public WizardPanelDescriptor(Object id, Component panel) {
      this.panelIdentifier = id;
      this.targetPanel = panel;
   }

   public final Component getPanelComponent() {
      return this.targetPanel;
   }

   public final void setPanelComponent(Component panel) {
      this.targetPanel = panel;
   }

   public final Object getPanelDescriptorIdentifier() {
      return this.panelIdentifier;
   }

   public final void setPanelDescriptorIdentifier(Object id) {
      this.panelIdentifier = id;
   }

   final void setWizard(Wizard w) {
      this.wizard = w;
   }

   public final Wizard getWizard() {
      return this.wizard;
   }

   public WizardModel getWizardModel() {
      return this.wizard.getModel();
   }

   public Object getNextPanelDescriptor() {
      return null;
   }

   public Object getBackPanelDescriptor() {
      return null;
   }

   public void aboutToDisplayPanel() {
   }

   public void displayingPanel() {
   }

   public void aboutToHidePanel() {
   }

   static class FinishIdentifier {
      public static final String ID = "FINISH";
   }
}
