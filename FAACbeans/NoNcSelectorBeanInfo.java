package FAACbeans;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.Insets;
import java.awt.MenuComponent;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyListener;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.beans.VetoableChangeListener;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.AncestorListener;

public class NoNcSelectorBeanInfo extends SimpleBeanInfo {
   private static final int PROPERTY_accessibleContext = 0;
   private static final int PROPERTY_actionMap = 1;
   private static final int PROPERTY_alignmentX = 2;
   private static final int PROPERTY_alignmentY = 3;
   private static final int PROPERTY_ancestorListeners = 4;
   private static final int PROPERTY_autoscrolls = 5;
   private static final int PROPERTY_background = 6;
   private static final int PROPERTY_backgroundSet = 7;
   private static final int PROPERTY_baselineResizeBehavior = 8;
   private static final int PROPERTY_border = 9;
   private static final int PROPERTY_bounds = 10;
   private static final int PROPERTY_colorModel = 11;
   private static final int PROPERTY_component = 12;
   private static final int PROPERTY_componentCount = 13;
   private static final int PROPERTY_componentListeners = 14;
   private static final int PROPERTY_componentOrientation = 15;
   private static final int PROPERTY_componentPopupMenu = 16;
   private static final int PROPERTY_components = 17;
   private static final int PROPERTY_containerListeners = 18;
   private static final int PROPERTY_cursor = 19;
   private static final int PROPERTY_cursorSet = 20;
   private static final int PROPERTY_debugGraphicsOptions = 21;
   private static final int PROPERTY_displayable = 22;
   private static final int PROPERTY_doubleBuffered = 23;
   private static final int PROPERTY_dropTarget = 24;
   private static final int PROPERTY_enabled = 25;
   private static final int PROPERTY_focusable = 26;
   private static final int PROPERTY_focusCycleRoot = 27;
   private static final int PROPERTY_focusCycleRootAncestor = 28;
   private static final int PROPERTY_focusListeners = 29;
   private static final int PROPERTY_focusOwner = 30;
   private static final int PROPERTY_focusTraversable = 31;
   private static final int PROPERTY_focusTraversalKeys = 32;
   private static final int PROPERTY_focusTraversalKeysEnabled = 33;
   private static final int PROPERTY_focusTraversalPolicy = 34;
   private static final int PROPERTY_focusTraversalPolicyProvider = 35;
   private static final int PROPERTY_focusTraversalPolicySet = 36;
   private static final int PROPERTY_font = 37;
   private static final int PROPERTY_fontSet = 38;
   private static final int PROPERTY_foreground = 39;
   private static final int PROPERTY_foregroundSet = 40;
   private static final int PROPERTY_graphics = 41;
   private static final int PROPERTY_graphicsConfiguration = 42;
   private static final int PROPERTY_height = 43;
   private static final int PROPERTY_hierarchyBoundsListeners = 44;
   private static final int PROPERTY_hierarchyListeners = 45;
   private static final int PROPERTY_ignoreRepaint = 46;
   private static final int PROPERTY_inheritsPopupMenu = 47;
   private static final int PROPERTY_inputContext = 48;
   private static final int PROPERTY_inputMap = 49;
   private static final int PROPERTY_inputMethodListeners = 50;
   private static final int PROPERTY_inputMethodRequests = 51;
   private static final int PROPERTY_inputVerifier = 52;
   private static final int PROPERTY_insets = 53;
   private static final int PROPERTY_keyListeners = 54;
   private static final int PROPERTY_layout = 55;
   private static final int PROPERTY_lightweight = 56;
   private static final int PROPERTY_locale = 57;
   private static final int PROPERTY_location = 58;
   private static final int PROPERTY_locationOnScreen = 59;
   private static final int PROPERTY_managingFocus = 60;
   private static final int PROPERTY_maximumSize = 61;
   private static final int PROPERTY_maximumSizeSet = 62;
   private static final int PROPERTY_minimumSize = 63;
   private static final int PROPERTY_minimumSizeSet = 64;
   private static final int PROPERTY_mouseListeners = 65;
   private static final int PROPERTY_mouseMotionListeners = 66;
   private static final int PROPERTY_mousePosition = 67;
   private static final int PROPERTY_mouseWheelListeners = 68;
   private static final int PROPERTY_name = 69;
   private static final int PROPERTY_NC = 70;
   private static final int PROPERTY_nextFocusableComponent = 71;
   private static final int PROPERTY_opaque = 72;
   private static final int PROPERTY_optimizedDrawingEnabled = 73;
   private static final int PROPERTY_paintingForPrint = 74;
   private static final int PROPERTY_paintingTile = 75;
   private static final int PROPERTY_parent = 76;
   private static final int PROPERTY_peer = 77;
   private static final int PROPERTY_preferredSize = 78;
   private static final int PROPERTY_preferredSizeSet = 79;
   private static final int PROPERTY_propertyChangeListeners = 80;
   private static final int PROPERTY_registeredKeyStrokes = 81;
   private static final int PROPERTY_requestFocusEnabled = 82;
   private static final int PROPERTY_rootPane = 83;
   private static final int PROPERTY_showing = 84;
   private static final int PROPERTY_size = 85;
   private static final int PROPERTY_toolkit = 86;
   private static final int PROPERTY_toolTipText = 87;
   private static final int PROPERTY_topLevelAncestor = 88;
   private static final int PROPERTY_transferHandler = 89;
   private static final int PROPERTY_treeLock = 90;
   private static final int PROPERTY_UI = 91;
   private static final int PROPERTY_UIClassID = 92;
   private static final int PROPERTY_valid = 93;
   private static final int PROPERTY_validateRoot = 94;
   private static final int PROPERTY_verifyInputWhenFocusTarget = 95;
   private static final int PROPERTY_vetoableChangeListeners = 96;
   private static final int PROPERTY_visible = 97;
   private static final int PROPERTY_visibleRect = 98;
   private static final int PROPERTY_width = 99;
   private static final int PROPERTY_x = 100;
   private static final int PROPERTY_y = 101;
   private static final int EVENT_ancestorListener = 0;
   private static final int EVENT_componentListener = 1;
   private static final int EVENT_containerListener = 2;
   private static final int EVENT_focusListener = 3;
   private static final int EVENT_hierarchyBoundsListener = 4;
   private static final int EVENT_hierarchyListener = 5;
   private static final int EVENT_inputMethodListener = 6;
   private static final int EVENT_keyListener = 7;
   private static final int EVENT_mouseListener = 8;
   private static final int EVENT_mouseMotionListener = 9;
   private static final int EVENT_mouseWheelListener = 10;
   private static final int EVENT_propertyChangeListener = 11;
   private static final int EVENT_vetoableChangeListener = 12;
   private static final int METHOD_action0 = 0;
   private static final int METHOD_add1 = 1;
   private static final int METHOD_add2 = 2;
   private static final int METHOD_add3 = 3;
   private static final int METHOD_add4 = 4;
   private static final int METHOD_add5 = 5;
   private static final int METHOD_add6 = 6;
   private static final int METHOD_addNotify7 = 7;
   private static final int METHOD_addPropertyChangeListener8 = 8;
   private static final int METHOD_applyComponentOrientation9 = 9;
   private static final int METHOD_areFocusTraversalKeysSet10 = 10;
   private static final int METHOD_bounds11 = 11;
   private static final int METHOD_checkImage12 = 12;
   private static final int METHOD_checkImage13 = 13;
   private static final int METHOD_computeVisibleRect14 = 14;
   private static final int METHOD_contains15 = 15;
   private static final int METHOD_contains16 = 16;
   private static final int METHOD_countComponents17 = 17;
   private static final int METHOD_createImage18 = 18;
   private static final int METHOD_createImage19 = 19;
   private static final int METHOD_createToolTip20 = 20;
   private static final int METHOD_createVolatileImage21 = 21;
   private static final int METHOD_createVolatileImage22 = 22;
   private static final int METHOD_deliverEvent23 = 23;
   private static final int METHOD_disable24 = 24;
   private static final int METHOD_dispatchEvent25 = 25;
   private static final int METHOD_doLayout26 = 26;
   private static final int METHOD_enable27 = 27;
   private static final int METHOD_enable28 = 28;
   private static final int METHOD_enableInputMethods29 = 29;
   private static final int METHOD_findComponentAt30 = 30;
   private static final int METHOD_findComponentAt31 = 31;
   private static final int METHOD_firePropertyChange32 = 32;
   private static final int METHOD_firePropertyChange33 = 33;
   private static final int METHOD_firePropertyChange34 = 34;
   private static final int METHOD_firePropertyChange35 = 35;
   private static final int METHOD_firePropertyChange36 = 36;
   private static final int METHOD_firePropertyChange37 = 37;
   private static final int METHOD_firePropertyChange38 = 38;
   private static final int METHOD_firePropertyChange39 = 39;
   private static final int METHOD_getActionForKeyStroke40 = 40;
   private static final int METHOD_getBaseline41 = 41;
   private static final int METHOD_getBounds42 = 42;
   private static final int METHOD_getClientProperty43 = 43;
   private static final int METHOD_getComponentAt44 = 44;
   private static final int METHOD_getComponentAt45 = 45;
   private static final int METHOD_getComponentZOrder46 = 46;
   private static final int METHOD_getConditionForKeyStroke47 = 47;
   private static final int METHOD_getDefaultLocale48 = 48;
   private static final int METHOD_getFocusTraversalKeys49 = 49;
   private static final int METHOD_getFontMetrics50 = 50;
   private static final int METHOD_getInsets51 = 51;
   private static final int METHOD_getListeners52 = 52;
   private static final int METHOD_getLocation53 = 53;
   private static final int METHOD_getMousePosition54 = 54;
   private static final int METHOD_getPopupLocation55 = 55;
   private static final int METHOD_getPropertyChangeListeners56 = 56;
   private static final int METHOD_getSize57 = 57;
   private static final int METHOD_getToolTipLocation58 = 58;
   private static final int METHOD_getToolTipText59 = 59;
   private static final int METHOD_gotFocus60 = 60;
   private static final int METHOD_grabFocus61 = 61;
   private static final int METHOD_handleEvent62 = 62;
   private static final int METHOD_hasFocus63 = 63;
   private static final int METHOD_hide64 = 64;
   private static final int METHOD_imageUpdate65 = 65;
   private static final int METHOD_insets66 = 66;
   private static final int METHOD_inside67 = 67;
   private static final int METHOD_invalidate68 = 68;
   private static final int METHOD_isAncestorOf69 = 69;
   private static final int METHOD_isFocusCycleRoot70 = 70;
   private static final int METHOD_isLightweightComponent71 = 71;
   private static final int METHOD_keyDown72 = 72;
   private static final int METHOD_keyUp73 = 73;
   private static final int METHOD_layout74 = 74;
   private static final int METHOD_list75 = 75;
   private static final int METHOD_list76 = 76;
   private static final int METHOD_list77 = 77;
   private static final int METHOD_list78 = 78;
   private static final int METHOD_list79 = 79;
   private static final int METHOD_locate80 = 80;
   private static final int METHOD_location81 = 81;
   private static final int METHOD_lostFocus82 = 82;
   private static final int METHOD_minimumSize83 = 83;
   private static final int METHOD_mouseDown84 = 84;
   private static final int METHOD_mouseDrag85 = 85;
   private static final int METHOD_mouseEnter86 = 86;
   private static final int METHOD_mouseExit87 = 87;
   private static final int METHOD_mouseMove88 = 88;
   private static final int METHOD_mouseUp89 = 89;
   private static final int METHOD_move90 = 90;
   private static final int METHOD_nextFocus91 = 91;
   private static final int METHOD_paint92 = 92;
   private static final int METHOD_paintAll93 = 93;
   private static final int METHOD_paintComponents94 = 94;
   private static final int METHOD_paintImmediately95 = 95;
   private static final int METHOD_paintImmediately96 = 96;
   private static final int METHOD_postEvent97 = 97;
   private static final int METHOD_preferredSize98 = 98;
   private static final int METHOD_prepareImage99 = 99;
   private static final int METHOD_prepareImage100 = 100;
   private static final int METHOD_print101 = 101;
   private static final int METHOD_printAll102 = 102;
   private static final int METHOD_printComponents103 = 103;
   private static final int METHOD_putClientProperty104 = 104;
   private static final int METHOD_registerKeyboardAction105 = 105;
   private static final int METHOD_registerKeyboardAction106 = 106;
   private static final int METHOD_remove107 = 107;
   private static final int METHOD_remove108 = 108;
   private static final int METHOD_remove109 = 109;
   private static final int METHOD_removeAll110 = 110;
   private static final int METHOD_removeNotify111 = 111;
   private static final int METHOD_removePropertyChangeListener112 = 112;
   private static final int METHOD_repaint113 = 113;
   private static final int METHOD_repaint114 = 114;
   private static final int METHOD_repaint115 = 115;
   private static final int METHOD_repaint116 = 116;
   private static final int METHOD_repaint117 = 117;
   private static final int METHOD_requestDefaultFocus118 = 118;
   private static final int METHOD_requestFocus119 = 119;
   private static final int METHOD_requestFocus120 = 120;
   private static final int METHOD_requestFocusInWindow121 = 121;
   private static final int METHOD_resetKeyboardActions122 = 122;
   private static final int METHOD_reshape123 = 123;
   private static final int METHOD_resize124 = 124;
   private static final int METHOD_resize125 = 125;
   private static final int METHOD_revalidate126 = 126;
   private static final int METHOD_scrollRectToVisible127 = 127;
   private static final int METHOD_setBounds128 = 128;
   private static final int METHOD_setComponentZOrder129 = 129;
   private static final int METHOD_setDefaultLocale130 = 130;
   private static final int METHOD_setNC131 = 131;
   private static final int METHOD_show132 = 132;
   private static final int METHOD_show133 = 133;
   private static final int METHOD_size134 = 134;
   private static final int METHOD_toString135 = 135;
   private static final int METHOD_transferFocus136 = 136;
   private static final int METHOD_transferFocusBackward137 = 137;
   private static final int METHOD_transferFocusDownCycle138 = 138;
   private static final int METHOD_transferFocusUpCycle139 = 139;
   private static final int METHOD_unregisterKeyboardAction140 = 140;
   private static final int METHOD_update141 = 141;
   private static final int METHOD_updateUI142 = 142;
   private static final int METHOD_validate143 = 143;
   private static Image iconColor16 = null;
   private static Image iconColor32 = null;
   private static Image iconMono16 = null;
   private static Image iconMono32 = null;
   private static String iconNameC16 = null;
   private static String iconNameC32 = null;
   private static String iconNameM16 = null;
   private static String iconNameM32 = null;
   private static final int defaultPropertyIndex = -1;
   private static final int defaultEventIndex = -1;

   private static BeanDescriptor getBdescriptor() {
      return new BeanDescriptor(NoNcSelector.class, null);
   }

   private static PropertyDescriptor[] getPdescriptor() {
      PropertyDescriptor[] properties = new PropertyDescriptor[102];

      try {
         properties[0] = new PropertyDescriptor("accessibleContext", NoNcSelector.class, "getAccessibleContext", null);
         properties[1] = new PropertyDescriptor("actionMap", NoNcSelector.class, "getActionMap", "setActionMap");
         properties[2] = new PropertyDescriptor("alignmentX", NoNcSelector.class, "getAlignmentX", "setAlignmentX");
         properties[3] = new PropertyDescriptor("alignmentY", NoNcSelector.class, "getAlignmentY", "setAlignmentY");
         properties[4] = new PropertyDescriptor("ancestorListeners", NoNcSelector.class, "getAncestorListeners", null);
         properties[5] = new PropertyDescriptor("autoscrolls", NoNcSelector.class, "getAutoscrolls", "setAutoscrolls");
         properties[6] = new PropertyDescriptor("background", NoNcSelector.class, "getBackground", "setBackground");
         properties[7] = new PropertyDescriptor("backgroundSet", NoNcSelector.class, "isBackgroundSet", null);
         properties[8] = new PropertyDescriptor("baselineResizeBehavior", NoNcSelector.class, "getBaselineResizeBehavior", null);
         properties[9] = new PropertyDescriptor("border", NoNcSelector.class, "getBorder", "setBorder");
         properties[10] = new PropertyDescriptor("bounds", NoNcSelector.class, "getBounds", "setBounds");
         properties[11] = new PropertyDescriptor("colorModel", NoNcSelector.class, "getColorModel", null);
         properties[12] = new IndexedPropertyDescriptor("component", NoNcSelector.class, null, null, "getComponent", null);
         properties[13] = new PropertyDescriptor("componentCount", NoNcSelector.class, "getComponentCount", null);
         properties[14] = new PropertyDescriptor("componentListeners", NoNcSelector.class, "getComponentListeners", null);
         properties[15] = new PropertyDescriptor("componentOrientation", NoNcSelector.class, "getComponentOrientation", "setComponentOrientation");
         properties[16] = new PropertyDescriptor("componentPopupMenu", NoNcSelector.class, "getComponentPopupMenu", "setComponentPopupMenu");
         properties[17] = new PropertyDescriptor("components", NoNcSelector.class, "getComponents", null);
         properties[18] = new PropertyDescriptor("containerListeners", NoNcSelector.class, "getContainerListeners", null);
         properties[19] = new PropertyDescriptor("cursor", NoNcSelector.class, "getCursor", "setCursor");
         properties[20] = new PropertyDescriptor("cursorSet", NoNcSelector.class, "isCursorSet", null);
         properties[21] = new PropertyDescriptor("debugGraphicsOptions", NoNcSelector.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions");
         properties[22] = new PropertyDescriptor("displayable", NoNcSelector.class, "isDisplayable", null);
         properties[23] = new PropertyDescriptor("doubleBuffered", NoNcSelector.class, "isDoubleBuffered", "setDoubleBuffered");
         properties[24] = new PropertyDescriptor("dropTarget", NoNcSelector.class, "getDropTarget", "setDropTarget");
         properties[25] = new PropertyDescriptor("enabled", NoNcSelector.class, "isEnabled", "setEnabled");
         properties[26] = new PropertyDescriptor("focusable", NoNcSelector.class, "isFocusable", "setFocusable");
         properties[27] = new PropertyDescriptor("focusCycleRoot", NoNcSelector.class, "isFocusCycleRoot", "setFocusCycleRoot");
         properties[28] = new PropertyDescriptor("focusCycleRootAncestor", NoNcSelector.class, "getFocusCycleRootAncestor", null);
         properties[29] = new PropertyDescriptor("focusListeners", NoNcSelector.class, "getFocusListeners", null);
         properties[30] = new PropertyDescriptor("focusOwner", NoNcSelector.class, "isFocusOwner", null);
         properties[31] = new PropertyDescriptor("focusTraversable", NoNcSelector.class, "isFocusTraversable", null);
         properties[32] = new IndexedPropertyDescriptor("focusTraversalKeys", NoNcSelector.class, null, null, null, "setFocusTraversalKeys");
         properties[33] = new PropertyDescriptor(
            "focusTraversalKeysEnabled", NoNcSelector.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled"
         );
         properties[34] = new PropertyDescriptor("focusTraversalPolicy", NoNcSelector.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy");
         properties[35] = new PropertyDescriptor(
            "focusTraversalPolicyProvider", NoNcSelector.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider"
         );
         properties[36] = new PropertyDescriptor("focusTraversalPolicySet", NoNcSelector.class, "isFocusTraversalPolicySet", null);
         properties[37] = new PropertyDescriptor("font", NoNcSelector.class, "getFont", "setFont");
         properties[38] = new PropertyDescriptor("fontSet", NoNcSelector.class, "isFontSet", null);
         properties[39] = new PropertyDescriptor("foreground", NoNcSelector.class, "getForeground", "setForeground");
         properties[40] = new PropertyDescriptor("foregroundSet", NoNcSelector.class, "isForegroundSet", null);
         properties[41] = new PropertyDescriptor("graphics", NoNcSelector.class, "getGraphics", null);
         properties[42] = new PropertyDescriptor("graphicsConfiguration", NoNcSelector.class, "getGraphicsConfiguration", null);
         properties[43] = new PropertyDescriptor("height", NoNcSelector.class, "getHeight", null);
         properties[44] = new PropertyDescriptor("hierarchyBoundsListeners", NoNcSelector.class, "getHierarchyBoundsListeners", null);
         properties[45] = new PropertyDescriptor("hierarchyListeners", NoNcSelector.class, "getHierarchyListeners", null);
         properties[46] = new PropertyDescriptor("ignoreRepaint", NoNcSelector.class, "getIgnoreRepaint", "setIgnoreRepaint");
         properties[47] = new PropertyDescriptor("inheritsPopupMenu", NoNcSelector.class, "getInheritsPopupMenu", "setInheritsPopupMenu");
         properties[48] = new PropertyDescriptor("inputContext", NoNcSelector.class, "getInputContext", null);
         properties[49] = new PropertyDescriptor("inputMap", NoNcSelector.class, "getInputMap", null);
         properties[50] = new PropertyDescriptor("inputMethodListeners", NoNcSelector.class, "getInputMethodListeners", null);
         properties[51] = new PropertyDescriptor("inputMethodRequests", NoNcSelector.class, "getInputMethodRequests", null);
         properties[52] = new PropertyDescriptor("inputVerifier", NoNcSelector.class, "getInputVerifier", "setInputVerifier");
         properties[53] = new PropertyDescriptor("insets", NoNcSelector.class, "getInsets", null);
         properties[54] = new PropertyDescriptor("keyListeners", NoNcSelector.class, "getKeyListeners", null);
         properties[55] = new PropertyDescriptor("layout", NoNcSelector.class, "getLayout", "setLayout");
         properties[56] = new PropertyDescriptor("lightweight", NoNcSelector.class, "isLightweight", null);
         properties[57] = new PropertyDescriptor("locale", NoNcSelector.class, "getLocale", "setLocale");
         properties[58] = new PropertyDescriptor("location", NoNcSelector.class, "getLocation", "setLocation");
         properties[59] = new PropertyDescriptor("locationOnScreen", NoNcSelector.class, "getLocationOnScreen", null);
         properties[60] = new PropertyDescriptor("managingFocus", NoNcSelector.class, "isManagingFocus", null);
         properties[61] = new PropertyDescriptor("maximumSize", NoNcSelector.class, "getMaximumSize", "setMaximumSize");
         properties[62] = new PropertyDescriptor("maximumSizeSet", NoNcSelector.class, "isMaximumSizeSet", null);
         properties[63] = new PropertyDescriptor("minimumSize", NoNcSelector.class, "getMinimumSize", "setMinimumSize");
         properties[64] = new PropertyDescriptor("minimumSizeSet", NoNcSelector.class, "isMinimumSizeSet", null);
         properties[65] = new PropertyDescriptor("mouseListeners", NoNcSelector.class, "getMouseListeners", null);
         properties[66] = new PropertyDescriptor("mouseMotionListeners", NoNcSelector.class, "getMouseMotionListeners", null);
         properties[67] = new PropertyDescriptor("mousePosition", NoNcSelector.class, "getMousePosition", null);
         properties[68] = new PropertyDescriptor("mouseWheelListeners", NoNcSelector.class, "getMouseWheelListeners", null);
         properties[69] = new PropertyDescriptor("name", NoNcSelector.class, "getName", "setName");
         properties[70] = new PropertyDescriptor("NC", NoNcSelector.class, "isNC", null);
         properties[71] = new PropertyDescriptor("nextFocusableComponent", NoNcSelector.class, "getNextFocusableComponent", "setNextFocusableComponent");
         properties[72] = new PropertyDescriptor("opaque", NoNcSelector.class, "isOpaque", "setOpaque");
         properties[73] = new PropertyDescriptor("optimizedDrawingEnabled", NoNcSelector.class, "isOptimizedDrawingEnabled", null);
         properties[74] = new PropertyDescriptor("paintingForPrint", NoNcSelector.class, "isPaintingForPrint", null);
         properties[75] = new PropertyDescriptor("paintingTile", NoNcSelector.class, "isPaintingTile", null);
         properties[76] = new PropertyDescriptor("parent", NoNcSelector.class, "getParent", null);
         properties[77] = new PropertyDescriptor("peer", NoNcSelector.class, "getPeer", null);
         properties[78] = new PropertyDescriptor("preferredSize", NoNcSelector.class, "getPreferredSize", "setPreferredSize");
         properties[79] = new PropertyDescriptor("preferredSizeSet", NoNcSelector.class, "isPreferredSizeSet", null);
         properties[80] = new PropertyDescriptor("propertyChangeListeners", NoNcSelector.class, "getPropertyChangeListeners", null);
         properties[81] = new PropertyDescriptor("registeredKeyStrokes", NoNcSelector.class, "getRegisteredKeyStrokes", null);
         properties[82] = new PropertyDescriptor("requestFocusEnabled", NoNcSelector.class, "isRequestFocusEnabled", "setRequestFocusEnabled");
         properties[83] = new PropertyDescriptor("rootPane", NoNcSelector.class, "getRootPane", null);
         properties[84] = new PropertyDescriptor("showing", NoNcSelector.class, "isShowing", null);
         properties[85] = new PropertyDescriptor("size", NoNcSelector.class, "getSize", "setSize");
         properties[86] = new PropertyDescriptor("toolkit", NoNcSelector.class, "getToolkit", null);
         properties[87] = new PropertyDescriptor("toolTipText", NoNcSelector.class, "getToolTipText", "setToolTipText");
         properties[88] = new PropertyDescriptor("topLevelAncestor", NoNcSelector.class, "getTopLevelAncestor", null);
         properties[89] = new PropertyDescriptor("transferHandler", NoNcSelector.class, "getTransferHandler", "setTransferHandler");
         properties[90] = new PropertyDescriptor("treeLock", NoNcSelector.class, "getTreeLock", null);
         properties[91] = new PropertyDescriptor("UI", NoNcSelector.class, "getUI", "setUI");
         properties[92] = new PropertyDescriptor("UIClassID", NoNcSelector.class, "getUIClassID", null);
         properties[93] = new PropertyDescriptor("valid", NoNcSelector.class, "isValid", null);
         properties[94] = new PropertyDescriptor("validateRoot", NoNcSelector.class, "isValidateRoot", null);
         properties[95] = new PropertyDescriptor(
            "verifyInputWhenFocusTarget", NoNcSelector.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget"
         );
         properties[96] = new PropertyDescriptor("vetoableChangeListeners", NoNcSelector.class, "getVetoableChangeListeners", null);
         properties[97] = new PropertyDescriptor("visible", NoNcSelector.class, "isVisible", "setVisible");
         properties[98] = new PropertyDescriptor("visibleRect", NoNcSelector.class, "getVisibleRect", null);
         properties[99] = new PropertyDescriptor("width", NoNcSelector.class, "getWidth", null);
         properties[100] = new PropertyDescriptor("x", NoNcSelector.class, "getX", null);
         properties[101] = new PropertyDescriptor("y", NoNcSelector.class, "getY", null);
      } catch (IntrospectionException var2) {
         var2.printStackTrace();
      }

      return properties;
   }

   private static EventSetDescriptor[] getEdescriptor() {
      EventSetDescriptor[] eventSets = new EventSetDescriptor[13];

      try {
         eventSets[0] = new EventSetDescriptor(
            NoNcSelector.class,
            "ancestorListener",
            AncestorListener.class,
            new String[]{"ancestorAdded", "ancestorRemoved", "ancestorMoved"},
            "addAncestorListener",
            "removeAncestorListener"
         );
         eventSets[1] = new EventSetDescriptor(
            NoNcSelector.class,
            "componentListener",
            ComponentListener.class,
            new String[]{"componentResized", "componentMoved", "componentShown", "componentHidden"},
            "addComponentListener",
            "removeComponentListener"
         );
         eventSets[2] = new EventSetDescriptor(
            NoNcSelector.class,
            "containerListener",
            ContainerListener.class,
            new String[]{"componentAdded", "componentRemoved"},
            "addContainerListener",
            "removeContainerListener"
         );
         eventSets[3] = new EventSetDescriptor(
            NoNcSelector.class, "focusListener", FocusListener.class, new String[]{"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener"
         );
         eventSets[4] = new EventSetDescriptor(
            NoNcSelector.class,
            "hierarchyBoundsListener",
            HierarchyBoundsListener.class,
            new String[]{"ancestorMoved", "ancestorResized"},
            "addHierarchyBoundsListener",
            "removeHierarchyBoundsListener"
         );
         eventSets[5] = new EventSetDescriptor(
            NoNcSelector.class,
            "hierarchyListener",
            HierarchyListener.class,
            new String[]{"hierarchyChanged"},
            "addHierarchyListener",
            "removeHierarchyListener"
         );
         eventSets[6] = new EventSetDescriptor(
            NoNcSelector.class,
            "inputMethodListener",
            InputMethodListener.class,
            new String[]{"inputMethodTextChanged", "caretPositionChanged"},
            "addInputMethodListener",
            "removeInputMethodListener"
         );
         eventSets[7] = new EventSetDescriptor(
            NoNcSelector.class, "keyListener", KeyListener.class, new String[]{"keyTyped", "keyPressed", "keyReleased"}, "addKeyListener", "removeKeyListener"
         );
         eventSets[8] = new EventSetDescriptor(
            NoNcSelector.class,
            "mouseListener",
            MouseListener.class,
            new String[]{"mouseClicked", "mousePressed", "mouseReleased", "mouseEntered", "mouseExited"},
            "addMouseListener",
            "removeMouseListener"
         );
         eventSets[9] = new EventSetDescriptor(
            NoNcSelector.class,
            "mouseMotionListener",
            MouseMotionListener.class,
            new String[]{"mouseDragged", "mouseMoved"},
            "addMouseMotionListener",
            "removeMouseMotionListener"
         );
         eventSets[10] = new EventSetDescriptor(
            NoNcSelector.class,
            "mouseWheelListener",
            MouseWheelListener.class,
            new String[]{"mouseWheelMoved"},
            "addMouseWheelListener",
            "removeMouseWheelListener"
         );
         eventSets[11] = new EventSetDescriptor(
            NoNcSelector.class,
            "propertyChangeListener",
            PropertyChangeListener.class,
            new String[]{"propertyChange"},
            "addPropertyChangeListener",
            "removePropertyChangeListener"
         );
         eventSets[12] = new EventSetDescriptor(
            NoNcSelector.class,
            "vetoableChangeListener",
            VetoableChangeListener.class,
            new String[]{"vetoableChange"},
            "addVetoableChangeListener",
            "removeVetoableChangeListener"
         );
      } catch (IntrospectionException var2) {
         var2.printStackTrace();
      }

      return eventSets;
   }

   private static MethodDescriptor[] getMdescriptor() {
      MethodDescriptor[] methods = new MethodDescriptor[144];

      try {
         methods[0] = new MethodDescriptor(Component.class.getMethod("action", Event.class, Object.class));
         methods[0].setDisplayName("");
         methods[1] = new MethodDescriptor(Component.class.getMethod("add", PopupMenu.class));
         methods[1].setDisplayName("");
         methods[2] = new MethodDescriptor(Container.class.getMethod("add", Component.class));
         methods[2].setDisplayName("");
         methods[3] = new MethodDescriptor(Container.class.getMethod("add", String.class, Component.class));
         methods[3].setDisplayName("");
         methods[4] = new MethodDescriptor(Container.class.getMethod("add", Component.class, int.class));
         methods[4].setDisplayName("");
         methods[5] = new MethodDescriptor(Container.class.getMethod("add", Component.class, Object.class));
         methods[5].setDisplayName("");
         methods[6] = new MethodDescriptor(Container.class.getMethod("add", Component.class, Object.class, int.class));
         methods[6].setDisplayName("");
         methods[7] = new MethodDescriptor(JComponent.class.getMethod("addNotify"));
         methods[7].setDisplayName("");
         methods[8] = new MethodDescriptor(Container.class.getMethod("addPropertyChangeListener", String.class, PropertyChangeListener.class));
         methods[8].setDisplayName("");
         methods[9] = new MethodDescriptor(Container.class.getMethod("applyComponentOrientation", ComponentOrientation.class));
         methods[9].setDisplayName("");
         methods[10] = new MethodDescriptor(Container.class.getMethod("areFocusTraversalKeysSet", int.class));
         methods[10].setDisplayName("");
         methods[11] = new MethodDescriptor(Component.class.getMethod("bounds"));
         methods[11].setDisplayName("");
         methods[12] = new MethodDescriptor(Component.class.getMethod("checkImage", Image.class, ImageObserver.class));
         methods[12].setDisplayName("");
         methods[13] = new MethodDescriptor(Component.class.getMethod("checkImage", Image.class, int.class, int.class, ImageObserver.class));
         methods[13].setDisplayName("");
         methods[14] = new MethodDescriptor(JComponent.class.getMethod("computeVisibleRect", Rectangle.class));
         methods[14].setDisplayName("");
         methods[15] = new MethodDescriptor(Component.class.getMethod("contains", Point.class));
         methods[15].setDisplayName("");
         methods[16] = new MethodDescriptor(JComponent.class.getMethod("contains", int.class, int.class));
         methods[16].setDisplayName("");
         methods[17] = new MethodDescriptor(Container.class.getMethod("countComponents"));
         methods[17].setDisplayName("");
         methods[18] = new MethodDescriptor(Component.class.getMethod("createImage", ImageProducer.class));
         methods[18].setDisplayName("");
         methods[19] = new MethodDescriptor(Component.class.getMethod("createImage", int.class, int.class));
         methods[19].setDisplayName("");
         methods[20] = new MethodDescriptor(JComponent.class.getMethod("createToolTip"));
         methods[20].setDisplayName("");
         methods[21] = new MethodDescriptor(Component.class.getMethod("createVolatileImage", int.class, int.class));
         methods[21].setDisplayName("");
         methods[22] = new MethodDescriptor(Component.class.getMethod("createVolatileImage", int.class, int.class, ImageCapabilities.class));
         methods[22].setDisplayName("");
         methods[23] = new MethodDescriptor(Container.class.getMethod("deliverEvent", Event.class));
         methods[23].setDisplayName("");
         methods[24] = new MethodDescriptor(JComponent.class.getMethod("disable"));
         methods[24].setDisplayName("");
         methods[25] = new MethodDescriptor(Component.class.getMethod("dispatchEvent", AWTEvent.class));
         methods[25].setDisplayName("");
         methods[26] = new MethodDescriptor(Container.class.getMethod("doLayout"));
         methods[26].setDisplayName("");
         methods[27] = new MethodDescriptor(Component.class.getMethod("enable", boolean.class));
         methods[27].setDisplayName("");
         methods[28] = new MethodDescriptor(JComponent.class.getMethod("enable"));
         methods[28].setDisplayName("");
         methods[29] = new MethodDescriptor(Component.class.getMethod("enableInputMethods", boolean.class));
         methods[29].setDisplayName("");
         methods[30] = new MethodDescriptor(Container.class.getMethod("findComponentAt", int.class, int.class));
         methods[30].setDisplayName("");
         methods[31] = new MethodDescriptor(Container.class.getMethod("findComponentAt", Point.class));
         methods[31].setDisplayName("");
         methods[32] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, byte.class, byte.class));
         methods[32].setDisplayName("");
         methods[33] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, short.class, short.class));
         methods[33].setDisplayName("");
         methods[34] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, long.class, long.class));
         methods[34].setDisplayName("");
         methods[35] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, float.class, float.class));
         methods[35].setDisplayName("");
         methods[36] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, double.class, double.class));
         methods[36].setDisplayName("");
         methods[37] = new MethodDescriptor(JComponent.class.getMethod("firePropertyChange", String.class, boolean.class, boolean.class));
         methods[37].setDisplayName("");
         methods[38] = new MethodDescriptor(JComponent.class.getMethod("firePropertyChange", String.class, int.class, int.class));
         methods[38].setDisplayName("");
         methods[39] = new MethodDescriptor(JComponent.class.getMethod("firePropertyChange", String.class, char.class, char.class));
         methods[39].setDisplayName("");
         methods[40] = new MethodDescriptor(JComponent.class.getMethod("getActionForKeyStroke", KeyStroke.class));
         methods[40].setDisplayName("");
         methods[41] = new MethodDescriptor(JComponent.class.getMethod("getBaseline", int.class, int.class));
         methods[41].setDisplayName("");
         methods[42] = new MethodDescriptor(JComponent.class.getMethod("getBounds", Rectangle.class));
         methods[42].setDisplayName("");
         methods[43] = new MethodDescriptor(JComponent.class.getMethod("getClientProperty", Object.class));
         methods[43].setDisplayName("");
         methods[44] = new MethodDescriptor(Container.class.getMethod("getComponentAt", int.class, int.class));
         methods[44].setDisplayName("");
         methods[45] = new MethodDescriptor(Container.class.getMethod("getComponentAt", Point.class));
         methods[45].setDisplayName("");
         methods[46] = new MethodDescriptor(Container.class.getMethod("getComponentZOrder", Component.class));
         methods[46].setDisplayName("");
         methods[47] = new MethodDescriptor(JComponent.class.getMethod("getConditionForKeyStroke", KeyStroke.class));
         methods[47].setDisplayName("");
         methods[48] = new MethodDescriptor(JComponent.class.getMethod("getDefaultLocale"));
         methods[48].setDisplayName("");
         methods[49] = new MethodDescriptor(Container.class.getMethod("getFocusTraversalKeys", int.class));
         methods[49].setDisplayName("");
         methods[50] = new MethodDescriptor(JComponent.class.getMethod("getFontMetrics", Font.class));
         methods[50].setDisplayName("");
         methods[51] = new MethodDescriptor(JComponent.class.getMethod("getInsets", Insets.class));
         methods[51].setDisplayName("");
         methods[52] = new MethodDescriptor(JComponent.class.getMethod("getListeners", Class.class));
         methods[52].setDisplayName("");
         methods[53] = new MethodDescriptor(JComponent.class.getMethod("getLocation", Point.class));
         methods[53].setDisplayName("");
         methods[54] = new MethodDescriptor(Container.class.getMethod("getMousePosition", boolean.class));
         methods[54].setDisplayName("");
         methods[55] = new MethodDescriptor(JComponent.class.getMethod("getPopupLocation", MouseEvent.class));
         methods[55].setDisplayName("");
         methods[56] = new MethodDescriptor(Component.class.getMethod("getPropertyChangeListeners", String.class));
         methods[56].setDisplayName("");
         methods[57] = new MethodDescriptor(JComponent.class.getMethod("getSize", Dimension.class));
         methods[57].setDisplayName("");
         methods[58] = new MethodDescriptor(JComponent.class.getMethod("getToolTipLocation", MouseEvent.class));
         methods[58].setDisplayName("");
         methods[59] = new MethodDescriptor(JComponent.class.getMethod("getToolTipText", MouseEvent.class));
         methods[59].setDisplayName("");
         methods[60] = new MethodDescriptor(Component.class.getMethod("gotFocus", Event.class, Object.class));
         methods[60].setDisplayName("");
         methods[61] = new MethodDescriptor(JComponent.class.getMethod("grabFocus"));
         methods[61].setDisplayName("");
         methods[62] = new MethodDescriptor(Component.class.getMethod("handleEvent", Event.class));
         methods[62].setDisplayName("");
         methods[63] = new MethodDescriptor(Component.class.getMethod("hasFocus"));
         methods[63].setDisplayName("");
         methods[64] = new MethodDescriptor(Component.class.getMethod("hide"));
         methods[64].setDisplayName("");
         methods[65] = new MethodDescriptor(Component.class.getMethod("imageUpdate", Image.class, int.class, int.class, int.class, int.class, int.class));
         methods[65].setDisplayName("");
         methods[66] = new MethodDescriptor(Container.class.getMethod("insets"));
         methods[66].setDisplayName("");
         methods[67] = new MethodDescriptor(Component.class.getMethod("inside", int.class, int.class));
         methods[67].setDisplayName("");
         methods[68] = new MethodDescriptor(Container.class.getMethod("invalidate"));
         methods[68].setDisplayName("");
         methods[69] = new MethodDescriptor(Container.class.getMethod("isAncestorOf", Component.class));
         methods[69].setDisplayName("");
         methods[70] = new MethodDescriptor(Container.class.getMethod("isFocusCycleRoot", Container.class));
         methods[70].setDisplayName("");
         methods[71] = new MethodDescriptor(JComponent.class.getMethod("isLightweightComponent", Component.class));
         methods[71].setDisplayName("");
         methods[72] = new MethodDescriptor(Component.class.getMethod("keyDown", Event.class, int.class));
         methods[72].setDisplayName("");
         methods[73] = new MethodDescriptor(Component.class.getMethod("keyUp", Event.class, int.class));
         methods[73].setDisplayName("");
         methods[74] = new MethodDescriptor(Container.class.getMethod("layout"));
         methods[74].setDisplayName("");
         methods[75] = new MethodDescriptor(Component.class.getMethod("list"));
         methods[75].setDisplayName("");
         methods[76] = new MethodDescriptor(Component.class.getMethod("list", PrintStream.class));
         methods[76].setDisplayName("");
         methods[77] = new MethodDescriptor(Component.class.getMethod("list", PrintWriter.class));
         methods[77].setDisplayName("");
         methods[78] = new MethodDescriptor(Container.class.getMethod("list", PrintStream.class, int.class));
         methods[78].setDisplayName("");
         methods[79] = new MethodDescriptor(Container.class.getMethod("list", PrintWriter.class, int.class));
         methods[79].setDisplayName("");
         methods[80] = new MethodDescriptor(Container.class.getMethod("locate", int.class, int.class));
         methods[80].setDisplayName("");
         methods[81] = new MethodDescriptor(Component.class.getMethod("location"));
         methods[81].setDisplayName("");
         methods[82] = new MethodDescriptor(Component.class.getMethod("lostFocus", Event.class, Object.class));
         methods[82].setDisplayName("");
         methods[83] = new MethodDescriptor(Container.class.getMethod("minimumSize"));
         methods[83].setDisplayName("");
         methods[84] = new MethodDescriptor(Component.class.getMethod("mouseDown", Event.class, int.class, int.class));
         methods[84].setDisplayName("");
         methods[85] = new MethodDescriptor(Component.class.getMethod("mouseDrag", Event.class, int.class, int.class));
         methods[85].setDisplayName("");
         methods[86] = new MethodDescriptor(Component.class.getMethod("mouseEnter", Event.class, int.class, int.class));
         methods[86].setDisplayName("");
         methods[87] = new MethodDescriptor(Component.class.getMethod("mouseExit", Event.class, int.class, int.class));
         methods[87].setDisplayName("");
         methods[88] = new MethodDescriptor(Component.class.getMethod("mouseMove", Event.class, int.class, int.class));
         methods[88].setDisplayName("");
         methods[89] = new MethodDescriptor(Component.class.getMethod("mouseUp", Event.class, int.class, int.class));
         methods[89].setDisplayName("");
         methods[90] = new MethodDescriptor(Component.class.getMethod("move", int.class, int.class));
         methods[90].setDisplayName("");
         methods[91] = new MethodDescriptor(Component.class.getMethod("nextFocus"));
         methods[91].setDisplayName("");
         methods[92] = new MethodDescriptor(JComponent.class.getMethod("paint", Graphics.class));
         methods[92].setDisplayName("");
         methods[93] = new MethodDescriptor(Component.class.getMethod("paintAll", Graphics.class));
         methods[93].setDisplayName("");
         methods[94] = new MethodDescriptor(Container.class.getMethod("paintComponents", Graphics.class));
         methods[94].setDisplayName("");
         methods[95] = new MethodDescriptor(JComponent.class.getMethod("paintImmediately", int.class, int.class, int.class, int.class));
         methods[95].setDisplayName("");
         methods[96] = new MethodDescriptor(JComponent.class.getMethod("paintImmediately", Rectangle.class));
         methods[96].setDisplayName("");
         methods[97] = new MethodDescriptor(Component.class.getMethod("postEvent", Event.class));
         methods[97].setDisplayName("");
         methods[98] = new MethodDescriptor(Container.class.getMethod("preferredSize"));
         methods[98].setDisplayName("");
         methods[99] = new MethodDescriptor(Component.class.getMethod("prepareImage", Image.class, ImageObserver.class));
         methods[99].setDisplayName("");
         methods[100] = new MethodDescriptor(Component.class.getMethod("prepareImage", Image.class, int.class, int.class, ImageObserver.class));
         methods[100].setDisplayName("");
         methods[101] = new MethodDescriptor(JComponent.class.getMethod("print", Graphics.class));
         methods[101].setDisplayName("");
         methods[102] = new MethodDescriptor(JComponent.class.getMethod("printAll", Graphics.class));
         methods[102].setDisplayName("");
         methods[103] = new MethodDescriptor(Container.class.getMethod("printComponents", Graphics.class));
         methods[103].setDisplayName("");
         methods[104] = new MethodDescriptor(JComponent.class.getMethod("putClientProperty", Object.class, Object.class));
         methods[104].setDisplayName("");
         methods[105] = new MethodDescriptor(
            JComponent.class.getMethod("registerKeyboardAction", ActionListener.class, String.class, KeyStroke.class, int.class)
         );
         methods[105].setDisplayName("");
         methods[106] = new MethodDescriptor(JComponent.class.getMethod("registerKeyboardAction", ActionListener.class, KeyStroke.class, int.class));
         methods[106].setDisplayName("");
         methods[107] = new MethodDescriptor(Component.class.getMethod("remove", MenuComponent.class));
         methods[107].setDisplayName("");
         methods[108] = new MethodDescriptor(Container.class.getMethod("remove", int.class));
         methods[108].setDisplayName("");
         methods[109] = new MethodDescriptor(Container.class.getMethod("remove", Component.class));
         methods[109].setDisplayName("");
         methods[110] = new MethodDescriptor(Container.class.getMethod("removeAll"));
         methods[110].setDisplayName("");
         methods[111] = new MethodDescriptor(JComponent.class.getMethod("removeNotify"));
         methods[111].setDisplayName("");
         methods[112] = new MethodDescriptor(Component.class.getMethod("removePropertyChangeListener", String.class, PropertyChangeListener.class));
         methods[112].setDisplayName("");
         methods[113] = new MethodDescriptor(Component.class.getMethod("repaint"));
         methods[113].setDisplayName("");
         methods[114] = new MethodDescriptor(Component.class.getMethod("repaint", long.class));
         methods[114].setDisplayName("");
         methods[115] = new MethodDescriptor(Component.class.getMethod("repaint", int.class, int.class, int.class, int.class));
         methods[115].setDisplayName("");
         methods[116] = new MethodDescriptor(JComponent.class.getMethod("repaint", long.class, int.class, int.class, int.class, int.class));
         methods[116].setDisplayName("");
         methods[117] = new MethodDescriptor(JComponent.class.getMethod("repaint", Rectangle.class));
         methods[117].setDisplayName("");
         methods[118] = new MethodDescriptor(JComponent.class.getMethod("requestDefaultFocus"));
         methods[118].setDisplayName("");
         methods[119] = new MethodDescriptor(JComponent.class.getMethod("requestFocus"));
         methods[119].setDisplayName("");
         methods[120] = new MethodDescriptor(JComponent.class.getMethod("requestFocus", boolean.class));
         methods[120].setDisplayName("");
         methods[121] = new MethodDescriptor(JComponent.class.getMethod("requestFocusInWindow"));
         methods[121].setDisplayName("");
         methods[122] = new MethodDescriptor(JComponent.class.getMethod("resetKeyboardActions"));
         methods[122].setDisplayName("");
         methods[123] = new MethodDescriptor(JComponent.class.getMethod("reshape", int.class, int.class, int.class, int.class));
         methods[123].setDisplayName("");
         methods[124] = new MethodDescriptor(Component.class.getMethod("resize", int.class, int.class));
         methods[124].setDisplayName("");
         methods[125] = new MethodDescriptor(Component.class.getMethod("resize", Dimension.class));
         methods[125].setDisplayName("");
         methods[126] = new MethodDescriptor(JComponent.class.getMethod("revalidate"));
         methods[126].setDisplayName("");
         methods[127] = new MethodDescriptor(JComponent.class.getMethod("scrollRectToVisible", Rectangle.class));
         methods[127].setDisplayName("");
         methods[128] = new MethodDescriptor(Component.class.getMethod("setBounds", int.class, int.class, int.class, int.class));
         methods[128].setDisplayName("");
         methods[129] = new MethodDescriptor(Container.class.getMethod("setComponentZOrder", Component.class, int.class));
         methods[129].setDisplayName("");
         methods[130] = new MethodDescriptor(JComponent.class.getMethod("setDefaultLocale", Locale.class));
         methods[130].setDisplayName("");
         methods[131] = new MethodDescriptor(NoNcSelector.class.getMethod("setNC"));
         methods[131].setDisplayName("");
         methods[132] = new MethodDescriptor(Component.class.getMethod("show"));
         methods[132].setDisplayName("");
         methods[133] = new MethodDescriptor(Component.class.getMethod("show", boolean.class));
         methods[133].setDisplayName("");
         methods[134] = new MethodDescriptor(Component.class.getMethod("size"));
         methods[134].setDisplayName("");
         methods[135] = new MethodDescriptor(Component.class.getMethod("toString"));
         methods[135].setDisplayName("");
         methods[136] = new MethodDescriptor(Component.class.getMethod("transferFocus"));
         methods[136].setDisplayName("");
         methods[137] = new MethodDescriptor(Container.class.getMethod("transferFocusBackward"));
         methods[137].setDisplayName("");
         methods[138] = new MethodDescriptor(Container.class.getMethod("transferFocusDownCycle"));
         methods[138].setDisplayName("");
         methods[139] = new MethodDescriptor(Component.class.getMethod("transferFocusUpCycle"));
         methods[139].setDisplayName("");
         methods[140] = new MethodDescriptor(JComponent.class.getMethod("unregisterKeyboardAction", KeyStroke.class));
         methods[140].setDisplayName("");
         methods[141] = new MethodDescriptor(JComponent.class.getMethod("update", Graphics.class));
         methods[141].setDisplayName("");
         methods[142] = new MethodDescriptor(JPanel.class.getMethod("updateUI"));
         methods[142].setDisplayName("");
         methods[143] = new MethodDescriptor(Container.class.getMethod("validate"));
         methods[143].setDisplayName("");
      } catch (Exception var2) {
      }

      return methods;
   }

   @Override
   public BeanDescriptor getBeanDescriptor() {
      return getBdescriptor();
   }

   @Override
   public PropertyDescriptor[] getPropertyDescriptors() {
      return getPdescriptor();
   }

   @Override
   public EventSetDescriptor[] getEventSetDescriptors() {
      return getEdescriptor();
   }

   @Override
   public MethodDescriptor[] getMethodDescriptors() {
      return getMdescriptor();
   }

   @Override
   public int getDefaultPropertyIndex() {
      return -1;
   }

   @Override
   public int getDefaultEventIndex() {
      return -1;
   }

   @Override
   public Image getIcon(int iconKind) {
      switch (iconKind) {
         case 1:
            if (iconNameC16 == null) {
               return null;
            }

            if (iconColor16 == null) {
               iconColor16 = this.loadImage(iconNameC16);
            }

            return iconColor16;
         case 2:
            if (iconNameC32 == null) {
               return null;
            }

            if (iconColor32 == null) {
               iconColor32 = this.loadImage(iconNameC32);
            }

            return iconColor32;
         case 3:
            if (iconNameM16 == null) {
               return null;
            }

            if (iconMono16 == null) {
               iconMono16 = this.loadImage(iconNameM16);
            }

            return iconMono16;
         case 4:
            if (iconNameM32 == null) {
               return null;
            }

            if (iconMono32 == null) {
               iconMono32 = this.loadImage(iconNameM32);
            }

            return iconMono32;
         default:
            return null;
      }
   }
}
