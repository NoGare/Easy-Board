package com.nexes.wizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import javax.swing.Icon;

public class WizardModel {
   public static final String CURRENT_PANEL_DESCRIPTOR_PROPERTY = "currentPanelDescriptorProperty";
   public static final String BACK_BUTTON_TEXT_PROPERTY = "backButtonTextProperty";
   public static final String BACK_BUTTON_ICON_PROPERTY = "backButtonIconProperty";
   public static final String BACK_BUTTON_ENABLED_PROPERTY = "backButtonEnabledProperty";
   public static final String NEXT_FINISH_BUTTON_TEXT_PROPERTY = "nextButtonTextProperty";
   public static final String NEXT_FINISH_BUTTON_ICON_PROPERTY = "nextButtonIconProperty";
   public static final String NEXT_FINISH_BUTTON_ENABLED_PROPERTY = "nextButtonEnabledProperty";
   public static final String CANCEL_BUTTON_TEXT_PROPERTY = "cancelButtonTextProperty";
   public static final String CANCEL_BUTTON_ICON_PROPERTY = "cancelButtonIconProperty";
   public static final String CANCEL_BUTTON_ENABLED_PROPERTY = "cancelButtonEnabledProperty";
   private WizardPanelDescriptor currentPanel;
   private HashMap panelHashmap = new HashMap();
   private HashMap buttonTextHashmap = new HashMap();
   private HashMap buttonIconHashmap = new HashMap();
   private HashMap buttonEnabledHashmap = new HashMap();
   private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

   WizardPanelDescriptor getCurrentPanelDescriptor() {
      return this.currentPanel;
   }

   void registerPanel(Object id, WizardPanelDescriptor descriptor) {
      this.panelHashmap.put(id, descriptor);
   }

   boolean setCurrentPanel(Object id) {
      WizardPanelDescriptor nextPanel = (WizardPanelDescriptor)this.panelHashmap.get(id);
      if (nextPanel == null) {
         throw new WizardPanelNotFoundException();
      } else {
         WizardPanelDescriptor oldPanel = this.currentPanel;
         this.currentPanel = nextPanel;
         if (oldPanel != this.currentPanel) {
            this.firePropertyChange("currentPanelDescriptorProperty", oldPanel, this.currentPanel);
         }

         return true;
      }
   }

   Object getBackButtonText() {
      return this.buttonTextHashmap.get("backButtonTextProperty");
   }

   void setBackButtonText(Object newText) {
      Object oldText = this.getBackButtonText();
      if (!newText.equals(oldText)) {
         this.buttonTextHashmap.put("backButtonTextProperty", newText);
         this.firePropertyChange("backButtonTextProperty", oldText, newText);
      }
   }

   Object getNextFinishButtonText() {
      return this.buttonTextHashmap.get("nextButtonTextProperty");
   }

   void setNextFinishButtonText(Object newText) {
      Object oldText = this.getNextFinishButtonText();
      if (!newText.equals(oldText)) {
         this.buttonTextHashmap.put("nextButtonTextProperty", newText);
         this.firePropertyChange("nextButtonTextProperty", oldText, newText);
      }
   }

   Object getCancelButtonText() {
      return this.buttonTextHashmap.get("cancelButtonTextProperty");
   }

   void setCancelButtonText(Object newText) {
      Object oldText = this.getCancelButtonText();
      if (!newText.equals(oldText)) {
         this.buttonTextHashmap.put("cancelButtonTextProperty", newText);
         this.firePropertyChange("cancelButtonTextProperty", oldText, newText);
      }
   }

   Icon getBackButtonIcon() {
      return (Icon)this.buttonIconHashmap.get("backButtonIconProperty");
   }

   void setBackButtonIcon(Icon newIcon) {
      Object oldIcon = this.getBackButtonIcon();
      if (!newIcon.equals(oldIcon)) {
         this.buttonIconHashmap.put("backButtonIconProperty", newIcon);
         this.firePropertyChange("backButtonIconProperty", oldIcon, newIcon);
      }
   }

   Icon getNextFinishButtonIcon() {
      return (Icon)this.buttonIconHashmap.get("nextButtonIconProperty");
   }

   public void setNextFinishButtonIcon(Icon newIcon) {
      Object oldIcon = this.getNextFinishButtonIcon();
      if (!newIcon.equals(oldIcon)) {
         this.buttonIconHashmap.put("nextButtonIconProperty", newIcon);
         this.firePropertyChange("nextButtonIconProperty", oldIcon, newIcon);
      }
   }

   Icon getCancelButtonIcon() {
      return (Icon)this.buttonIconHashmap.get("cancelButtonIconProperty");
   }

   void setCancelButtonIcon(Icon newIcon) {
      Icon oldIcon = this.getCancelButtonIcon();
      if (!newIcon.equals(oldIcon)) {
         this.buttonIconHashmap.put("cancelButtonIconProperty", newIcon);
         this.firePropertyChange("cancelButtonIconProperty", oldIcon, newIcon);
      }
   }

   Boolean getBackButtonEnabled() {
      return (Boolean)this.buttonEnabledHashmap.get("backButtonEnabledProperty");
   }

   void setBackButtonEnabled(Boolean newValue) {
      Boolean oldValue = this.getBackButtonEnabled();
      if (newValue != oldValue) {
         this.buttonEnabledHashmap.put("backButtonEnabledProperty", newValue);
         this.firePropertyChange("backButtonEnabledProperty", oldValue, newValue);
      }
   }

   Boolean getNextFinishButtonEnabled() {
      return (Boolean)this.buttonEnabledHashmap.get("nextButtonEnabledProperty");
   }

   void setNextFinishButtonEnabled(Boolean newValue) {
      Boolean oldValue = this.getNextFinishButtonEnabled();
      if (newValue != oldValue) {
         this.buttonEnabledHashmap.put("nextButtonEnabledProperty", newValue);
         this.firePropertyChange("nextButtonEnabledProperty", oldValue, newValue);
      }
   }

   Boolean getCancelButtonEnabled() {
      return (Boolean)this.buttonEnabledHashmap.get("cancelButtonEnabledProperty");
   }

   void setCancelButtonEnabled(Boolean newValue) {
      Boolean oldValue = this.getCancelButtonEnabled();
      if (newValue != oldValue) {
         this.buttonEnabledHashmap.put("cancelButtonEnabledProperty", newValue);
         this.firePropertyChange("cancelButtonEnabledProperty", oldValue, newValue);
      }
   }

   public void addPropertyChangeListener(PropertyChangeListener p) {
      this.propertyChangeSupport.addPropertyChangeListener(p);
   }

   public void removePropertyChangeListener(PropertyChangeListener p) {
      this.propertyChangeSupport.removePropertyChangeListener(p);
   }

   protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
      this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
   }
}
