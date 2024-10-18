package com.nexes.wizard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class Wizard extends WindowAdapter implements PropertyChangeListener {
   public static final int FINISH_RETURN_CODE = 0;
   public static final int CANCEL_RETURN_CODE = 1;
   public static final int ERROR_RETURN_CODE = 2;
   public static final String NEXT_BUTTON_ACTION_COMMAND = "NextButtonActionCommand";
   public static final String BACK_BUTTON_ACTION_COMMAND = "BackButtonActionCommand";
   public static final String CANCEL_BUTTON_ACTION_COMMAND = "CancelButtonActionCommand";
   static String BACK_TEXT;
   static String NEXT_TEXT;
   static String FINISH_TEXT;
   static String CANCEL_TEXT;
   static Icon BACK_ICON;
   static Icon NEXT_ICON;
   static Icon FINISH_ICON;
   static Icon CANCEL_ICON;
   private WizardModel wizardModel = new WizardModel();
   private WizardController wizardController;
   private JDialog wizardDialog;
   private JPanel cardPanel;
   private CardLayout cardLayout;
   private JButton backButton;
   private JButton nextButton;
   private JButton cancelButton;
   private int returnCode;

   public Wizard() {
      this((Frame)null);
   }

   public Wizard(Dialog owner) {
      this.wizardDialog = new JDialog(owner);
      this.initComponents();
   }

   public Wizard(Frame owner) {
      this.wizardDialog = new JDialog(owner);
      this.initComponents();
   }

   public JDialog getDialog() {
      return this.wizardDialog;
   }

   public Component getOwner() {
      return this.wizardDialog.getOwner();
   }

   public void setTitle(String s) {
      this.wizardDialog.setTitle(s);
   }

   public String getTitle() {
      return this.wizardDialog.getTitle();
   }

   public void setModal(boolean b) {
      this.wizardDialog.setModal(b);
   }

   public boolean isModal() {
      return this.wizardDialog.isModal();
   }

   public int showModalDialog() {
      this.wizardDialog.setModal(true);
      this.wizardDialog.pack();
      PropertyResourceBundle resources = (PropertyResourceBundle)ResourceBundle.getBundle("com.nexes.wizard.wizard");
      ImageIcon icon = new ImageIcon((URL)getImage((String)resources.getObject("wizardIcon")));
      this.wizardDialog.setIconImage(icon.getImage());
      this.wizardDialog.setVisible(true);
      return this.returnCode;
   }

   public int showModalDialog(Frame parent) {
      this.wizardDialog.setModal(true);
      this.wizardDialog.pack();
      PropertyResourceBundle resources = (PropertyResourceBundle)ResourceBundle.getBundle("com.nexes.wizard.wizard");
      ImageIcon icon = new ImageIcon((URL)getImage((String)resources.getObject("wizardIcon")));
      this.wizardDialog.setIconImage(icon.getImage());
      this.wizardDialog.setLocationRelativeTo(parent);
      this.wizardDialog.setVisible(true);
      return this.returnCode;
   }

   public WizardModel getModel() {
      return this.wizardModel;
   }

   public void registerWizardPanel(Object id, WizardPanelDescriptor panel) {
      this.cardPanel.add(panel.getPanelComponent(), id);
      panel.setWizard(this);
      this.wizardModel.registerPanel(id, panel);
   }

   public void setCurrentPanel(Object id) {
      if (id == null) {
         this.close(2);
      }

      WizardPanelDescriptor oldPanelDescriptor = this.wizardModel.getCurrentPanelDescriptor();
      if (oldPanelDescriptor != null) {
         oldPanelDescriptor.aboutToHidePanel();
      }

      this.wizardModel.setCurrentPanel(id);
      this.wizardModel.getCurrentPanelDescriptor().aboutToDisplayPanel();
      this.cardLayout.show(this.cardPanel, id.toString());
      this.wizardModel.getCurrentPanelDescriptor().displayingPanel();
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName().equals("currentPanelDescriptorProperty")) {
         this.wizardController.resetButtonsToPanelRules();
      } else if (evt.getPropertyName().equals("nextButtonTextProperty")) {
         this.nextButton.setText(evt.getNewValue().toString());
      } else if (evt.getPropertyName().equals("backButtonTextProperty")) {
         this.backButton.setText(evt.getNewValue().toString());
      } else if (evt.getPropertyName().equals("cancelButtonTextProperty")) {
         this.cancelButton.setText(evt.getNewValue().toString());
      } else if (evt.getPropertyName().equals("nextButtonEnabledProperty")) {
         this.nextButton.setEnabled((Boolean)evt.getNewValue());
      } else if (evt.getPropertyName().equals("backButtonEnabledProperty")) {
         this.backButton.setEnabled((Boolean)evt.getNewValue());
      } else if (evt.getPropertyName().equals("cancelButtonEnabledProperty")) {
         this.cancelButton.setEnabled((Boolean)evt.getNewValue());
      } else if (evt.getPropertyName().equals("nextButtonIconProperty")) {
         this.nextButton.setIcon((Icon)evt.getNewValue());
      } else if (evt.getPropertyName().equals("backButtonIconProperty")) {
         this.backButton.setIcon((Icon)evt.getNewValue());
      } else if (evt.getPropertyName().equals("cancelButtonIconProperty")) {
         this.cancelButton.setIcon((Icon)evt.getNewValue());
      }
   }

   public int getReturnCode() {
      return this.returnCode;
   }

   public boolean getBackButtonEnabled() {
      return this.wizardModel.getBackButtonEnabled();
   }

   public void setBackButtonEnabled(boolean newValue) {
      this.wizardModel.setBackButtonEnabled(newValue);
   }

   public boolean getNextFinishButtonEnabled() {
      return this.wizardModel.getNextFinishButtonEnabled();
   }

   public void setNextFinishButtonEnabled(boolean newValue) {
      this.wizardModel.setNextFinishButtonEnabled(newValue);
   }

   public boolean getCancelButtonEnabled() {
      return this.wizardModel.getCancelButtonEnabled();
   }

   public void setCancelButtonEnabled(boolean newValue) {
      this.wizardModel.setCancelButtonEnabled(newValue);
   }

   void close(int code) {
      this.returnCode = code;
      this.wizardDialog.dispose();
   }

   private void initComponents() {
      this.wizardModel.addPropertyChangeListener(this);
      this.wizardController = new WizardController(this);
      this.wizardDialog.getContentPane().setLayout(new BorderLayout());
      this.wizardDialog.addWindowListener(this);
      JPanel buttonPanel = new JPanel();
      JSeparator separator = new JSeparator();
      Box buttonBox = new Box(0);
      this.cardPanel = new JPanel();
      this.cardPanel.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
      this.cardLayout = new CardLayout();
      this.cardPanel.setLayout(this.cardLayout);
      this.backButton = new JButton(new ImageIcon("com/nexes/wizard/backIcon.png"));
      this.nextButton = new JButton();
      this.cancelButton = new JButton();
      this.backButton.setActionCommand("BackButtonActionCommand");
      this.nextButton.setActionCommand("NextButtonActionCommand");
      this.cancelButton.setActionCommand("CancelButtonActionCommand");
      this.backButton.addActionListener(this.wizardController);
      this.nextButton.addActionListener(this.wizardController);
      this.cancelButton.addActionListener(this.wizardController);
      buttonPanel.setLayout(new BorderLayout());
      buttonPanel.add(separator, "North");
      buttonBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
      buttonBox.add(this.backButton);
      buttonBox.add(Box.createHorizontalStrut(10));
      buttonBox.add(this.nextButton);
      buttonBox.add(Box.createHorizontalStrut(30));
      buttonBox.add(this.cancelButton);
      buttonPanel.add(buttonBox, "East");
      this.wizardDialog.getContentPane().add(buttonPanel, "South");
      this.wizardDialog.getContentPane().add(this.cardPanel, "Center");
   }

   private static Object getImage(String name) {
      URL url = null;

      try {
         Class c = Class.forName("com.nexes.wizard.Wizard");
         url = c.getResource(name);
      } catch (ClassNotFoundException var3) {
         System.err.println("Unable to find Wizard class");
      }

      return url;
   }

   @Override
   public void windowClosing(WindowEvent e) {
      this.returnCode = 1;
   }

   static {
      try {
         PropertyResourceBundle resources = (PropertyResourceBundle)ResourceBundle.getBundle("com.nexes.wizard.wizard");
         BACK_TEXT = (String)resources.getObject("backButtonText");
         NEXT_TEXT = (String)resources.getObject("nextButtonText");
         CANCEL_TEXT = (String)resources.getObject("cancelButtonText");
         FINISH_TEXT = (String)resources.getObject("finishButtonText");
         BACK_ICON = new ImageIcon((URL)getImage((String)resources.getObject("backButtonIcon")));
         NEXT_ICON = new ImageIcon((URL)getImage((String)resources.getObject("nextButtonIcon")));
         CANCEL_ICON = new ImageIcon((URL)getImage((String)resources.getObject("cancelButtonIcon")));
         FINISH_ICON = new ImageIcon((URL)getImage((String)resources.getObject("finishButtonIcon")));
      } catch (MissingResourceException var1) {
         System.out.println(var1);
         System.exit(1);
      }
   }
}
