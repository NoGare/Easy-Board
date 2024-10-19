package FAACbeans;

import java.awt.AWTEvent;
import java.awt.BufferCapabilities;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.MenuComponent;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyListener;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
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
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.JDialog;

public class ModifyDateTimeBeanInfo extends SimpleBeanInfo {
   private static final int PROPERTY_accessibleContext = 0;
   private static final int PROPERTY_active = 1;
   private static final int PROPERTY_alignmentX = 2;
   private static final int PROPERTY_alignmentY = 3;
   private static final int PROPERTY_alwaysOnTop = 4;
   private static final int PROPERTY_alwaysOnTopSupported = 5;
   private static final int PROPERTY_background = 6;
   private static final int PROPERTY_backgroundSet = 7;
   private static final int PROPERTY_baselineResizeBehavior = 8;
   private static final int PROPERTY_bounds = 9;
   private static final int PROPERTY_bufferStrategy = 10;
   private static final int PROPERTY_colorModel = 11;
   private static final int PROPERTY_component = 12;
   private static final int PROPERTY_componentCount = 13;
   private static final int PROPERTY_componentListeners = 14;
   private static final int PROPERTY_componentOrientation = 15;
   private static final int PROPERTY_components = 16;
   private static final int PROPERTY_containerListeners = 17;
   private static final int PROPERTY_contentPane = 18;
   private static final int PROPERTY_cursor = 19;
   private static final int PROPERTY_cursorSet = 20;
   private static final int PROPERTY_defaultCloseOperation = 21;
   private static final int PROPERTY_displayable = 22;
   private static final int PROPERTY_doubleBuffered = 23;
   private static final int PROPERTY_dropTarget = 24;
   private static final int PROPERTY_enabled = 25;
   private static final int PROPERTY_focusable = 26;
   private static final int PROPERTY_focusableWindow = 27;
   private static final int PROPERTY_focusableWindowState = 28;
   private static final int PROPERTY_focusCycleRoot = 29;
   private static final int PROPERTY_focusCycleRootAncestor = 30;
   private static final int PROPERTY_focused = 31;
   private static final int PROPERTY_focusListeners = 32;
   private static final int PROPERTY_focusOwner = 33;
   private static final int PROPERTY_focusTraversable = 34;
   private static final int PROPERTY_focusTraversalKeys = 35;
   private static final int PROPERTY_focusTraversalKeysEnabled = 36;
   private static final int PROPERTY_focusTraversalPolicy = 37;
   private static final int PROPERTY_focusTraversalPolicyProvider = 38;
   private static final int PROPERTY_focusTraversalPolicySet = 39;
   private static final int PROPERTY_font = 40;
   private static final int PROPERTY_fontSet = 41;
   private static final int PROPERTY_foreground = 42;
   private static final int PROPERTY_foregroundSet = 43;
   private static final int PROPERTY_glassPane = 44;
   private static final int PROPERTY_graphics = 45;
   private static final int PROPERTY_graphicsConfiguration = 46;
   private static final int PROPERTY_height = 47;
   private static final int PROPERTY_hierarchyBoundsListeners = 48;
   private static final int PROPERTY_hierarchyListeners = 49;
   private static final int PROPERTY_iconImage = 50;
   private static final int PROPERTY_iconImages = 51;
   private static final int PROPERTY_ignoreRepaint = 52;
   private static final int PROPERTY_inputContext = 53;
   private static final int PROPERTY_inputMethodListeners = 54;
   private static final int PROPERTY_inputMethodRequests = 55;
   private static final int PROPERTY_insets = 56;
   private static final int PROPERTY_JMenuBar = 57;
   private static final int PROPERTY_keyListeners = 58;
   private static final int PROPERTY_layeredPane = 59;
   private static final int PROPERTY_layout = 60;
   private static final int PROPERTY_lightweight = 61;
   private static final int PROPERTY_locale = 62;
   private static final int PROPERTY_location = 63;
   private static final int PROPERTY_locationByPlatform = 64;
   private static final int PROPERTY_locationOnScreen = 65;
   private static final int PROPERTY_locationRelativeTo = 66;
   private static final int PROPERTY_maximumSize = 67;
   private static final int PROPERTY_maximumSizeSet = 68;
   private static final int PROPERTY_minimumSize = 69;
   private static final int PROPERTY_minimumSizeSet = 70;
   private static final int PROPERTY_modal = 71;
   private static final int PROPERTY_modalExclusionType = 72;
   private static final int PROPERTY_modalityType = 73;
   private static final int PROPERTY_mostRecentFocusOwner = 74;
   private static final int PROPERTY_mouseListeners = 75;
   private static final int PROPERTY_mouseMotionListeners = 76;
   private static final int PROPERTY_mousePosition = 77;
   private static final int PROPERTY_mouseWheelListeners = 78;
   private static final int PROPERTY_name = 79;
   private static final int PROPERTY_opaque = 80;
   private static final int PROPERTY_ownedWindows = 81;
   private static final int PROPERTY_owner = 82;
   private static final int PROPERTY_parent = 83;
   private static final int PROPERTY_peer = 84;
   private static final int PROPERTY_preferredSize = 85;
   private static final int PROPERTY_preferredSizeSet = 86;
   private static final int PROPERTY_propertyChangeListeners = 87;
   private static final int PROPERTY_resizable = 88;
   private static final int PROPERTY_rootPane = 89;
   private static final int PROPERTY_showing = 90;
   private static final int PROPERTY_size = 91;
   private static final int PROPERTY_title = 92;
   private static final int PROPERTY_toolkit = 93;
   private static final int PROPERTY_transferHandler = 94;
   private static final int PROPERTY_treeLock = 95;
   private static final int PROPERTY_undecorated = 96;
   private static final int PROPERTY_valid = 97;
   private static final int PROPERTY_visible = 98;
   private static final int PROPERTY_warningString = 99;
   private static final int PROPERTY_width = 100;
   private static final int PROPERTY_windowFocusListeners = 101;
   private static final int PROPERTY_windowListeners = 102;
   private static final int PROPERTY_windowStateListeners = 103;
   private static final int PROPERTY_x = 104;
   private static final int PROPERTY_y = 105;
   private static final int EVENT_componentListener = 0;
   private static final int EVENT_containerListener = 1;
   private static final int EVENT_focusListener = 2;
   private static final int EVENT_hierarchyBoundsListener = 3;
   private static final int EVENT_hierarchyListener = 4;
   private static final int EVENT_inputMethodListener = 5;
   private static final int EVENT_keyListener = 6;
   private static final int EVENT_mouseListener = 7;
   private static final int EVENT_mouseMotionListener = 8;
   private static final int EVENT_mouseWheelListener = 9;
   private static final int EVENT_propertyChangeListener = 10;
   private static final int EVENT_windowFocusListener = 11;
   private static final int EVENT_windowListener = 12;
   private static final int EVENT_windowStateListener = 13;
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
   private static final int METHOD_applyResourceBundle10 = 10;
   private static final int METHOD_applyResourceBundle11 = 11;
   private static final int METHOD_areFocusTraversalKeysSet12 = 12;
   private static final int METHOD_bounds13 = 13;
   private static final int METHOD_checkImage14 = 14;
   private static final int METHOD_checkImage15 = 15;
   private static final int METHOD_contains16 = 16;
   private static final int METHOD_contains17 = 17;
   private static final int METHOD_countComponents18 = 18;
   private static final int METHOD_createBufferStrategy19 = 19;
   private static final int METHOD_createBufferStrategy20 = 20;
   private static final int METHOD_createImage21 = 21;
   private static final int METHOD_createImage22 = 22;
   private static final int METHOD_createVolatileImage23 = 23;
   private static final int METHOD_createVolatileImage24 = 24;
   private static final int METHOD_deliverEvent25 = 25;
   private static final int METHOD_disable26 = 26;
   private static final int METHOD_dispatchEvent27 = 27;
   private static final int METHOD_dispose28 = 28;
   private static final int METHOD_doLayout29 = 29;
   private static final int METHOD_enable30 = 30;
   private static final int METHOD_enable31 = 31;
   private static final int METHOD_enableInputMethods32 = 32;
   private static final int METHOD_findComponentAt33 = 33;
   private static final int METHOD_findComponentAt34 = 34;
   private static final int METHOD_firePropertyChange35 = 35;
   private static final int METHOD_firePropertyChange36 = 36;
   private static final int METHOD_firePropertyChange37 = 37;
   private static final int METHOD_firePropertyChange38 = 38;
   private static final int METHOD_firePropertyChange39 = 39;
   private static final int METHOD_firePropertyChange40 = 40;
   private static final int METHOD_getBaseline41 = 41;
   private static final int METHOD_getBounds42 = 42;
   private static final int METHOD_getComponentAt43 = 43;
   private static final int METHOD_getComponentAt44 = 44;
   private static final int METHOD_getComponentZOrder45 = 45;
   private static final int METHOD_getFontMetrics46 = 46;
   private static final int METHOD_getIconImages47 = 47;
   private static final int METHOD_getListeners48 = 48;
   private static final int METHOD_getLocation49 = 49;
   private static final int METHOD_getMousePosition50 = 50;
   private static final int METHOD_getOwnerlessWindows51 = 51;
   private static final int METHOD_getPropertyChangeListeners52 = 52;
   private static final int METHOD_getSize53 = 53;
   private static final int METHOD_getWindows54 = 54;
   private static final int METHOD_gotFocus55 = 55;
   private static final int METHOD_handleEvent56 = 56;
   private static final int METHOD_hasFocus57 = 57;
   private static final int METHOD_hide58 = 58;
   private static final int METHOD_imageUpdate59 = 59;
   private static final int METHOD_insets60 = 60;
   private static final int METHOD_inside61 = 61;
   private static final int METHOD_invalidate62 = 62;
   private static final int METHOD_isAncestorOf63 = 63;
   private static final int METHOD_isDefaultLookAndFeelDecorated64 = 64;
   private static final int METHOD_isFocusCycleRoot65 = 65;
   private static final int METHOD_isFocusOwner66 = 66;
   private static final int METHOD_keyDown67 = 67;
   private static final int METHOD_keyUp68 = 68;
   private static final int METHOD_layout69 = 69;
   private static final int METHOD_list70 = 70;
   private static final int METHOD_list71 = 71;
   private static final int METHOD_list72 = 72;
   private static final int METHOD_list73 = 73;
   private static final int METHOD_list74 = 74;
   private static final int METHOD_locate75 = 75;
   private static final int METHOD_location76 = 76;
   private static final int METHOD_lostFocus77 = 77;
   private static final int METHOD_main78 = 78;
   private static final int METHOD_minimumSize79 = 79;
   private static final int METHOD_mouseDown80 = 80;
   private static final int METHOD_mouseDrag81 = 81;
   private static final int METHOD_mouseEnter82 = 82;
   private static final int METHOD_mouseExit83 = 83;
   private static final int METHOD_mouseMove84 = 84;
   private static final int METHOD_mouseUp85 = 85;
   private static final int METHOD_move86 = 86;
   private static final int METHOD_nextFocus87 = 87;
   private static final int METHOD_pack88 = 88;
   private static final int METHOD_paint89 = 89;
   private static final int METHOD_paintAll90 = 90;
   private static final int METHOD_paintComponents91 = 91;
   private static final int METHOD_postEvent92 = 92;
   private static final int METHOD_preferredSize93 = 93;
   private static final int METHOD_prepareImage94 = 94;
   private static final int METHOD_prepareImage95 = 95;
   private static final int METHOD_print96 = 96;
   private static final int METHOD_printAll97 = 97;
   private static final int METHOD_printComponents98 = 98;
   private static final int METHOD_remove99 = 99;
   private static final int METHOD_remove100 = 100;
   private static final int METHOD_remove101 = 101;
   private static final int METHOD_removeAll102 = 102;
   private static final int METHOD_removeNotify103 = 103;
   private static final int METHOD_removePropertyChangeListener104 = 104;
   private static final int METHOD_repaint105 = 105;
   private static final int METHOD_repaint106 = 106;
   private static final int METHOD_repaint107 = 107;
   private static final int METHOD_repaint108 = 108;
   private static final int METHOD_requestFocus109 = 109;
   private static final int METHOD_requestFocusInWindow110 = 110;
   private static final int METHOD_reshape111 = 111;
   private static final int METHOD_resize112 = 112;
   private static final int METHOD_resize113 = 113;
   private static final int METHOD_setBounds114 = 114;
   private static final int METHOD_setComponentZOrder115 = 115;
   private static final int METHOD_setDefaultLookAndFeelDecorated116 = 116;
   private static final int METHOD_setFocusTraversalKeys117 = 117;
   private static final int METHOD_show118 = 118;
   private static final int METHOD_show119 = 119;
   private static final int METHOD_size120 = 120;
   private static final int METHOD_toBack121 = 121;
   private static final int METHOD_toFront122 = 122;
   private static final int METHOD_toString123 = 123;
   private static final int METHOD_transferFocus124 = 124;
   private static final int METHOD_transferFocusBackward125 = 125;
   private static final int METHOD_transferFocusDownCycle126 = 126;
   private static final int METHOD_transferFocusUpCycle127 = 127;
   private static final int METHOD_update128 = 128;
   private static final int METHOD_validate129 = 129;
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
      return new BeanDescriptor(ModifyDateTime.class, null);
   }

   private static PropertyDescriptor[] getPdescriptor() {
      PropertyDescriptor[] properties = new PropertyDescriptor[106];

      try {
         properties[0] = new PropertyDescriptor("accessibleContext", ModifyDateTime.class, "getAccessibleContext", null);
         properties[1] = new PropertyDescriptor("active", ModifyDateTime.class, "isActive", null);
         properties[2] = new PropertyDescriptor("alignmentX", ModifyDateTime.class, "getAlignmentX", null);
         properties[3] = new PropertyDescriptor("alignmentY", ModifyDateTime.class, "getAlignmentY", null);
         properties[4] = new PropertyDescriptor("alwaysOnTop", ModifyDateTime.class, "isAlwaysOnTop", "setAlwaysOnTop");
         properties[5] = new PropertyDescriptor("alwaysOnTopSupported", ModifyDateTime.class, "isAlwaysOnTopSupported", null);
         properties[6] = new PropertyDescriptor("background", ModifyDateTime.class, "getBackground", "setBackground");
         properties[7] = new PropertyDescriptor("backgroundSet", ModifyDateTime.class, "isBackgroundSet", null);
         properties[8] = new PropertyDescriptor("baselineResizeBehavior", ModifyDateTime.class, "getBaselineResizeBehavior", null);
         properties[9] = new PropertyDescriptor("bounds", ModifyDateTime.class, "getBounds", "setBounds");
         properties[10] = new PropertyDescriptor("bufferStrategy", ModifyDateTime.class, "getBufferStrategy", null);
         properties[11] = new PropertyDescriptor("colorModel", ModifyDateTime.class, "getColorModel", null);
         properties[12] = new IndexedPropertyDescriptor("component", ModifyDateTime.class, null, null, "getComponent", null);
         properties[13] = new PropertyDescriptor("componentCount", ModifyDateTime.class, "getComponentCount", null);
         properties[14] = new PropertyDescriptor("componentListeners", ModifyDateTime.class, "getComponentListeners", null);
         properties[15] = new PropertyDescriptor("componentOrientation", ModifyDateTime.class, "getComponentOrientation", "setComponentOrientation");
         properties[16] = new PropertyDescriptor("components", ModifyDateTime.class, "getComponents", null);
         properties[17] = new PropertyDescriptor("containerListeners", ModifyDateTime.class, "getContainerListeners", null);
         properties[18] = new PropertyDescriptor("contentPane", ModifyDateTime.class, "getContentPane", "setContentPane");
         properties[19] = new PropertyDescriptor("cursor", ModifyDateTime.class, "getCursor", "setCursor");
         properties[20] = new PropertyDescriptor("cursorSet", ModifyDateTime.class, "isCursorSet", null);
         properties[21] = new PropertyDescriptor("defaultCloseOperation", ModifyDateTime.class, "getDefaultCloseOperation", "setDefaultCloseOperation");
         properties[22] = new PropertyDescriptor("displayable", ModifyDateTime.class, "isDisplayable", null);
         properties[23] = new PropertyDescriptor("doubleBuffered", ModifyDateTime.class, "isDoubleBuffered", null);
         properties[24] = new PropertyDescriptor("dropTarget", ModifyDateTime.class, "getDropTarget", "setDropTarget");
         properties[25] = new PropertyDescriptor("enabled", ModifyDateTime.class, "isEnabled", "setEnabled");
         properties[26] = new PropertyDescriptor("focusable", ModifyDateTime.class, "isFocusable", "setFocusable");
         properties[27] = new PropertyDescriptor("focusableWindow", ModifyDateTime.class, "isFocusableWindow", null);
         properties[28] = new PropertyDescriptor("focusableWindowState", ModifyDateTime.class, "getFocusableWindowState", "setFocusableWindowState");
         properties[29] = new PropertyDescriptor("focusCycleRoot", ModifyDateTime.class, "isFocusCycleRoot", "setFocusCycleRoot");
         properties[30] = new PropertyDescriptor("focusCycleRootAncestor", ModifyDateTime.class, "getFocusCycleRootAncestor", null);
         properties[31] = new PropertyDescriptor("focused", ModifyDateTime.class, "isFocused", null);
         properties[32] = new PropertyDescriptor("focusListeners", ModifyDateTime.class, "getFocusListeners", null);
         properties[33] = new PropertyDescriptor("focusOwner", ModifyDateTime.class, "getFocusOwner", null);
         properties[34] = new PropertyDescriptor("focusTraversable", ModifyDateTime.class, "isFocusTraversable", null);
         properties[35] = new IndexedPropertyDescriptor("focusTraversalKeys", ModifyDateTime.class, null, null, "getFocusTraversalKeys", null);
         properties[36] = new PropertyDescriptor(
            "focusTraversalKeysEnabled", ModifyDateTime.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled"
         );
         properties[37] = new PropertyDescriptor("focusTraversalPolicy", ModifyDateTime.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy");
         properties[38] = new PropertyDescriptor(
            "focusTraversalPolicyProvider", ModifyDateTime.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider"
         );
         properties[39] = new PropertyDescriptor("focusTraversalPolicySet", ModifyDateTime.class, "isFocusTraversalPolicySet", null);
         properties[40] = new PropertyDescriptor("font", ModifyDateTime.class, "getFont", "setFont");
         properties[41] = new PropertyDescriptor("fontSet", ModifyDateTime.class, "isFontSet", null);
         properties[42] = new PropertyDescriptor("foreground", ModifyDateTime.class, "getForeground", "setForeground");
         properties[43] = new PropertyDescriptor("foregroundSet", ModifyDateTime.class, "isForegroundSet", null);
         properties[44] = new PropertyDescriptor("glassPane", ModifyDateTime.class, "getGlassPane", "setGlassPane");
         properties[45] = new PropertyDescriptor("graphics", ModifyDateTime.class, "getGraphics", null);
         properties[46] = new PropertyDescriptor("graphicsConfiguration", ModifyDateTime.class, "getGraphicsConfiguration", null);
         properties[47] = new PropertyDescriptor("height", ModifyDateTime.class, "getHeight", null);
         properties[48] = new PropertyDescriptor("hierarchyBoundsListeners", ModifyDateTime.class, "getHierarchyBoundsListeners", null);
         properties[49] = new PropertyDescriptor("hierarchyListeners", ModifyDateTime.class, "getHierarchyListeners", null);
         properties[50] = new PropertyDescriptor("iconImage", ModifyDateTime.class, null, "setIconImage");
         properties[51] = new PropertyDescriptor("iconImages", ModifyDateTime.class, null, "setIconImages");
         properties[52] = new PropertyDescriptor("ignoreRepaint", ModifyDateTime.class, "getIgnoreRepaint", "setIgnoreRepaint");
         properties[53] = new PropertyDescriptor("inputContext", ModifyDateTime.class, "getInputContext", null);
         properties[54] = new PropertyDescriptor("inputMethodListeners", ModifyDateTime.class, "getInputMethodListeners", null);
         properties[55] = new PropertyDescriptor("inputMethodRequests", ModifyDateTime.class, "getInputMethodRequests", null);
         properties[56] = new PropertyDescriptor("insets", ModifyDateTime.class, "getInsets", null);
         properties[57] = new PropertyDescriptor("JMenuBar", ModifyDateTime.class, "getJMenuBar", "setJMenuBar");
         properties[58] = new PropertyDescriptor("keyListeners", ModifyDateTime.class, "getKeyListeners", null);
         properties[59] = new PropertyDescriptor("layeredPane", ModifyDateTime.class, "getLayeredPane", "setLayeredPane");
         properties[60] = new PropertyDescriptor("layout", ModifyDateTime.class, "getLayout", "setLayout");
         properties[61] = new PropertyDescriptor("lightweight", ModifyDateTime.class, "isLightweight", null);
         properties[62] = new PropertyDescriptor("locale", ModifyDateTime.class, "getLocale", "setLocale");
         properties[63] = new PropertyDescriptor("location", ModifyDateTime.class, "getLocation", "setLocation");
         properties[64] = new PropertyDescriptor("locationByPlatform", ModifyDateTime.class, "isLocationByPlatform", "setLocationByPlatform");
         properties[65] = new PropertyDescriptor("locationOnScreen", ModifyDateTime.class, "getLocationOnScreen", null);
         properties[66] = new PropertyDescriptor("locationRelativeTo", ModifyDateTime.class, null, "setLocationRelativeTo");
         properties[67] = new PropertyDescriptor("maximumSize", ModifyDateTime.class, "getMaximumSize", "setMaximumSize");
         properties[68] = new PropertyDescriptor("maximumSizeSet", ModifyDateTime.class, "isMaximumSizeSet", null);
         properties[69] = new PropertyDescriptor("minimumSize", ModifyDateTime.class, "getMinimumSize", "setMinimumSize");
         properties[70] = new PropertyDescriptor("minimumSizeSet", ModifyDateTime.class, "isMinimumSizeSet", null);
         properties[71] = new PropertyDescriptor("modal", ModifyDateTime.class, "isModal", "setModal");
         properties[72] = new PropertyDescriptor("modalExclusionType", ModifyDateTime.class, "getModalExclusionType", "setModalExclusionType");
         properties[73] = new PropertyDescriptor("modalityType", ModifyDateTime.class, "getModalityType", "setModalityType");
         properties[74] = new PropertyDescriptor("mostRecentFocusOwner", ModifyDateTime.class, "getMostRecentFocusOwner", null);
         properties[75] = new PropertyDescriptor("mouseListeners", ModifyDateTime.class, "getMouseListeners", null);
         properties[76] = new PropertyDescriptor("mouseMotionListeners", ModifyDateTime.class, "getMouseMotionListeners", null);
         properties[77] = new PropertyDescriptor("mousePosition", ModifyDateTime.class, "getMousePosition", null);
         properties[78] = new PropertyDescriptor("mouseWheelListeners", ModifyDateTime.class, "getMouseWheelListeners", null);
         properties[79] = new PropertyDescriptor("name", ModifyDateTime.class, "getName", "setName");
         properties[80] = new PropertyDescriptor("opaque", ModifyDateTime.class, "isOpaque", null);
         properties[81] = new PropertyDescriptor("ownedWindows", ModifyDateTime.class, "getOwnedWindows", null);
         properties[82] = new PropertyDescriptor("owner", ModifyDateTime.class, "getOwner", null);
         properties[83] = new PropertyDescriptor("parent", ModifyDateTime.class, "getParent", null);
         properties[84] = new PropertyDescriptor("peer", ModifyDateTime.class, "getPeer", null);
         properties[85] = new PropertyDescriptor("preferredSize", ModifyDateTime.class, "getPreferredSize", "setPreferredSize");
         properties[86] = new PropertyDescriptor("preferredSizeSet", ModifyDateTime.class, "isPreferredSizeSet", null);
         properties[87] = new PropertyDescriptor("propertyChangeListeners", ModifyDateTime.class, "getPropertyChangeListeners", null);
         properties[88] = new PropertyDescriptor("resizable", ModifyDateTime.class, "isResizable", "setResizable");
         properties[89] = new PropertyDescriptor("rootPane", ModifyDateTime.class, "getRootPane", null);
         properties[90] = new PropertyDescriptor("showing", ModifyDateTime.class, "isShowing", null);
         properties[91] = new PropertyDescriptor("size", ModifyDateTime.class, "getSize", "setSize");
         properties[92] = new PropertyDescriptor("title", ModifyDateTime.class, "getTitle", "setTitle");
         properties[93] = new PropertyDescriptor("toolkit", ModifyDateTime.class, "getToolkit", null);
         properties[94] = new PropertyDescriptor("transferHandler", ModifyDateTime.class, "getTransferHandler", "setTransferHandler");
         properties[95] = new PropertyDescriptor("treeLock", ModifyDateTime.class, "getTreeLock", null);
         properties[96] = new PropertyDescriptor("undecorated", ModifyDateTime.class, "isUndecorated", "setUndecorated");
         properties[97] = new PropertyDescriptor("valid", ModifyDateTime.class, "isValid", null);
         properties[98] = new PropertyDescriptor("visible", ModifyDateTime.class, "isVisible", "setVisible");
         properties[99] = new PropertyDescriptor("warningString", ModifyDateTime.class, "getWarningString", null);
         properties[100] = new PropertyDescriptor("width", ModifyDateTime.class, "getWidth", null);
         properties[101] = new PropertyDescriptor("windowFocusListeners", ModifyDateTime.class, "getWindowFocusListeners", null);
         properties[102] = new PropertyDescriptor("windowListeners", ModifyDateTime.class, "getWindowListeners", null);
         properties[103] = new PropertyDescriptor("windowStateListeners", ModifyDateTime.class, "getWindowStateListeners", null);
         properties[104] = new PropertyDescriptor("x", ModifyDateTime.class, "getX", null);
         properties[105] = new PropertyDescriptor("y", ModifyDateTime.class, "getY", null);
      } catch (IntrospectionException var2) {
         var2.printStackTrace();
      }

      return properties;
   }

   private static EventSetDescriptor[] getEdescriptor() {
      EventSetDescriptor[] eventSets = new EventSetDescriptor[14];

      try {
         eventSets[0] = new EventSetDescriptor(
            ModifyDateTime.class,
            "componentListener",
            ComponentListener.class,
            new String[]{"componentResized", "componentMoved", "componentShown", "componentHidden"},
            "addComponentListener",
            "removeComponentListener"
         );
         eventSets[1] = new EventSetDescriptor(
            ModifyDateTime.class,
            "containerListener",
            ContainerListener.class,
            new String[]{"componentAdded", "componentRemoved"},
            "addContainerListener",
            "removeContainerListener"
         );
         eventSets[2] = new EventSetDescriptor(
            ModifyDateTime.class, "focusListener", FocusListener.class, new String[]{"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener"
         );
         eventSets[3] = new EventSetDescriptor(
            ModifyDateTime.class,
            "hierarchyBoundsListener",
            HierarchyBoundsListener.class,
            new String[]{"ancestorMoved", "ancestorResized"},
            "addHierarchyBoundsListener",
            "removeHierarchyBoundsListener"
         );
         eventSets[4] = new EventSetDescriptor(
            ModifyDateTime.class,
            "hierarchyListener",
            HierarchyListener.class,
            new String[]{"hierarchyChanged"},
            "addHierarchyListener",
            "removeHierarchyListener"
         );
         eventSets[5] = new EventSetDescriptor(
            ModifyDateTime.class,
            "inputMethodListener",
            InputMethodListener.class,
            new String[]{"inputMethodTextChanged", "caretPositionChanged"},
            "addInputMethodListener",
            "removeInputMethodListener"
         );
         eventSets[6] = new EventSetDescriptor(
            ModifyDateTime.class,
            "keyListener",
            KeyListener.class,
            new String[]{"keyTyped", "keyPressed", "keyReleased"},
            "addKeyListener",
            "removeKeyListener"
         );
         eventSets[7] = new EventSetDescriptor(
            ModifyDateTime.class,
            "mouseListener",
            MouseListener.class,
            new String[]{"mouseClicked", "mousePressed", "mouseReleased", "mouseEntered", "mouseExited"},
            "addMouseListener",
            "removeMouseListener"
         );
         eventSets[8] = new EventSetDescriptor(
            ModifyDateTime.class,
            "mouseMotionListener",
            MouseMotionListener.class,
            new String[]{"mouseDragged", "mouseMoved"},
            "addMouseMotionListener",
            "removeMouseMotionListener"
         );
         eventSets[9] = new EventSetDescriptor(
            ModifyDateTime.class,
            "mouseWheelListener",
            MouseWheelListener.class,
            new String[]{"mouseWheelMoved"},
            "addMouseWheelListener",
            "removeMouseWheelListener"
         );
         eventSets[10] = new EventSetDescriptor(
            ModifyDateTime.class,
            "propertyChangeListener",
            PropertyChangeListener.class,
            new String[]{"propertyChange"},
            "addPropertyChangeListener",
            "removePropertyChangeListener"
         );
         eventSets[11] = new EventSetDescriptor(
            ModifyDateTime.class,
            "windowFocusListener",
            WindowFocusListener.class,
            new String[]{"windowGainedFocus", "windowLostFocus"},
            "addWindowFocusListener",
            "removeWindowFocusListener"
         );
         eventSets[12] = new EventSetDescriptor(
            ModifyDateTime.class,
            "windowListener",
            WindowListener.class,
            new String[]{"windowOpened", "windowClosing", "windowClosed", "windowIconified", "windowDeiconified", "windowActivated", "windowDeactivated"},
            "addWindowListener",
            "removeWindowListener"
         );
         eventSets[13] = new EventSetDescriptor(
            ModifyDateTime.class,
            "windowStateListener",
            WindowStateListener.class,
            new String[]{"windowStateChanged"},
            "addWindowStateListener",
            "removeWindowStateListener"
         );
      } catch (IntrospectionException var2) {
         var2.printStackTrace();
      }

      return eventSets;
   }

   private static MethodDescriptor[] getMdescriptor() {
      MethodDescriptor[] methods = new MethodDescriptor[130];

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
         methods[7] = new MethodDescriptor(Dialog.class.getMethod("addNotify"));
         methods[7].setDisplayName("");
         methods[8] = new MethodDescriptor(Window.class.getMethod("addPropertyChangeListener", String.class, PropertyChangeListener.class));
         methods[8].setDisplayName("");
         methods[9] = new MethodDescriptor(Container.class.getMethod("applyComponentOrientation", ComponentOrientation.class));
         methods[9].setDisplayName("");
         methods[10] = new MethodDescriptor(Window.class.getMethod("applyResourceBundle", ResourceBundle.class));
         methods[10].setDisplayName("");
         methods[11] = new MethodDescriptor(Window.class.getMethod("applyResourceBundle", String.class));
         methods[11].setDisplayName("");
         methods[12] = new MethodDescriptor(Container.class.getMethod("areFocusTraversalKeysSet", int.class));
         methods[12].setDisplayName("");
         methods[13] = new MethodDescriptor(Component.class.getMethod("bounds"));
         methods[13].setDisplayName("");
         methods[14] = new MethodDescriptor(Component.class.getMethod("checkImage", Image.class, ImageObserver.class));
         methods[14].setDisplayName("");
         methods[15] = new MethodDescriptor(Component.class.getMethod("checkImage", Image.class, int.class, int.class, ImageObserver.class));
         methods[15].setDisplayName("");
         methods[16] = new MethodDescriptor(Component.class.getMethod("contains", int.class, int.class));
         methods[16].setDisplayName("");
         methods[17] = new MethodDescriptor(Component.class.getMethod("contains", Point.class));
         methods[17].setDisplayName("");
         methods[18] = new MethodDescriptor(Container.class.getMethod("countComponents"));
         methods[18].setDisplayName("");
         methods[19] = new MethodDescriptor(Window.class.getMethod("createBufferStrategy", int.class));
         methods[19].setDisplayName("");
         methods[20] = new MethodDescriptor(Window.class.getMethod("createBufferStrategy", int.class, BufferCapabilities.class));
         methods[20].setDisplayName("");
         methods[21] = new MethodDescriptor(Component.class.getMethod("createImage", ImageProducer.class));
         methods[21].setDisplayName("");
         methods[22] = new MethodDescriptor(Component.class.getMethod("createImage", int.class, int.class));
         methods[22].setDisplayName("");
         methods[23] = new MethodDescriptor(Component.class.getMethod("createVolatileImage", int.class, int.class));
         methods[23].setDisplayName("");
         methods[24] = new MethodDescriptor(Component.class.getMethod("createVolatileImage", int.class, int.class, ImageCapabilities.class));
         methods[24].setDisplayName("");
         methods[25] = new MethodDescriptor(Container.class.getMethod("deliverEvent", Event.class));
         methods[25].setDisplayName("");
         methods[26] = new MethodDescriptor(Component.class.getMethod("disable"));
         methods[26].setDisplayName("");
         methods[27] = new MethodDescriptor(Component.class.getMethod("dispatchEvent", AWTEvent.class));
         methods[27].setDisplayName("");
         methods[28] = new MethodDescriptor(Window.class.getMethod("dispose"));
         methods[28].setDisplayName("");
         methods[29] = new MethodDescriptor(Container.class.getMethod("doLayout"));
         methods[29].setDisplayName("");
         methods[30] = new MethodDescriptor(Component.class.getMethod("enable"));
         methods[30].setDisplayName("");
         methods[31] = new MethodDescriptor(Component.class.getMethod("enable", boolean.class));
         methods[31].setDisplayName("");
         methods[32] = new MethodDescriptor(Component.class.getMethod("enableInputMethods", boolean.class));
         methods[32].setDisplayName("");
         methods[33] = new MethodDescriptor(Container.class.getMethod("findComponentAt", int.class, int.class));
         methods[33].setDisplayName("");
         methods[34] = new MethodDescriptor(Container.class.getMethod("findComponentAt", Point.class));
         methods[34].setDisplayName("");
         methods[35] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, byte.class, byte.class));
         methods[35].setDisplayName("");
         methods[36] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, char.class, char.class));
         methods[36].setDisplayName("");
         methods[37] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, short.class, short.class));
         methods[37].setDisplayName("");
         methods[38] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, long.class, long.class));
         methods[38].setDisplayName("");
         methods[39] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, float.class, float.class));
         methods[39].setDisplayName("");
         methods[40] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, double.class, double.class));
         methods[40].setDisplayName("");
         methods[41] = new MethodDescriptor(Component.class.getMethod("getBaseline", int.class, int.class));
         methods[41].setDisplayName("");
         methods[42] = new MethodDescriptor(Component.class.getMethod("getBounds", Rectangle.class));
         methods[42].setDisplayName("");
         methods[43] = new MethodDescriptor(Container.class.getMethod("getComponentAt", int.class, int.class));
         methods[43].setDisplayName("");
         methods[44] = new MethodDescriptor(Container.class.getMethod("getComponentAt", Point.class));
         methods[44].setDisplayName("");
         methods[45] = new MethodDescriptor(Container.class.getMethod("getComponentZOrder", Component.class));
         methods[45].setDisplayName("");
         methods[46] = new MethodDescriptor(Component.class.getMethod("getFontMetrics", Font.class));
         methods[46].setDisplayName("");
         methods[47] = new MethodDescriptor(Window.class.getMethod("getIconImages"));
         methods[47].setDisplayName("");
         methods[48] = new MethodDescriptor(Window.class.getMethod("getListeners", Class.class));
         methods[48].setDisplayName("");
         methods[49] = new MethodDescriptor(Component.class.getMethod("getLocation", Point.class));
         methods[49].setDisplayName("");
         methods[50] = new MethodDescriptor(Container.class.getMethod("getMousePosition", boolean.class));
         methods[50].setDisplayName("");
         methods[51] = new MethodDescriptor(Window.class.getMethod("getOwnerlessWindows"));
         methods[51].setDisplayName("");
         methods[52] = new MethodDescriptor(Component.class.getMethod("getPropertyChangeListeners", String.class));
         methods[52].setDisplayName("");
         methods[53] = new MethodDescriptor(Component.class.getMethod("getSize", Dimension.class));
         methods[53].setDisplayName("");
         methods[54] = new MethodDescriptor(Window.class.getMethod("getWindows"));
         methods[54].setDisplayName("");
         methods[55] = new MethodDescriptor(Component.class.getMethod("gotFocus", Event.class, Object.class));
         methods[55].setDisplayName("");
         methods[56] = new MethodDescriptor(Component.class.getMethod("handleEvent", Event.class));
         methods[56].setDisplayName("");
         methods[57] = new MethodDescriptor(Component.class.getMethod("hasFocus"));
         methods[57].setDisplayName("");
         methods[58] = new MethodDescriptor(Dialog.class.getMethod("hide"));
         methods[58].setDisplayName("");
         methods[59] = new MethodDescriptor(Component.class.getMethod("imageUpdate", Image.class, int.class, int.class, int.class, int.class, int.class));
         methods[59].setDisplayName("");
         methods[60] = new MethodDescriptor(Container.class.getMethod("insets"));
         methods[60].setDisplayName("");
         methods[61] = new MethodDescriptor(Component.class.getMethod("inside", int.class, int.class));
         methods[61].setDisplayName("");
         methods[62] = new MethodDescriptor(Container.class.getMethod("invalidate"));
         methods[62].setDisplayName("");
         methods[63] = new MethodDescriptor(Container.class.getMethod("isAncestorOf", Component.class));
         methods[63].setDisplayName("");
         methods[64] = new MethodDescriptor(JDialog.class.getMethod("isDefaultLookAndFeelDecorated"));
         methods[64].setDisplayName("");
         methods[65] = new MethodDescriptor(Container.class.getMethod("isFocusCycleRoot", Container.class));
         methods[65].setDisplayName("");
         methods[66] = new MethodDescriptor(Component.class.getMethod("isFocusOwner"));
         methods[66].setDisplayName("");
         methods[67] = new MethodDescriptor(Component.class.getMethod("keyDown", Event.class, int.class));
         methods[67].setDisplayName("");
         methods[68] = new MethodDescriptor(Component.class.getMethod("keyUp", Event.class, int.class));
         methods[68].setDisplayName("");
         methods[69] = new MethodDescriptor(Container.class.getMethod("layout"));
         methods[69].setDisplayName("");
         methods[70] = new MethodDescriptor(Component.class.getMethod("list"));
         methods[70].setDisplayName("");
         methods[71] = new MethodDescriptor(Component.class.getMethod("list", PrintStream.class));
         methods[71].setDisplayName("");
         methods[72] = new MethodDescriptor(Component.class.getMethod("list", PrintWriter.class));
         methods[72].setDisplayName("");
         methods[73] = new MethodDescriptor(Container.class.getMethod("list", PrintStream.class, int.class));
         methods[73].setDisplayName("");
         methods[74] = new MethodDescriptor(Container.class.getMethod("list", PrintWriter.class, int.class));
         methods[74].setDisplayName("");
         methods[75] = new MethodDescriptor(Container.class.getMethod("locate", int.class, int.class));
         methods[75].setDisplayName("");
         methods[76] = new MethodDescriptor(Component.class.getMethod("location"));
         methods[76].setDisplayName("");
         methods[77] = new MethodDescriptor(Component.class.getMethod("lostFocus", Event.class, Object.class));
         methods[77].setDisplayName("");
         methods[78] = new MethodDescriptor(ModifyDateTime.class.getMethod("main", String[].class));
         methods[78].setDisplayName("");
         methods[79] = new MethodDescriptor(Container.class.getMethod("minimumSize"));
         methods[79].setDisplayName("");
         methods[80] = new MethodDescriptor(Component.class.getMethod("mouseDown", Event.class, int.class, int.class));
         methods[80].setDisplayName("");
         methods[81] = new MethodDescriptor(Component.class.getMethod("mouseDrag", Event.class, int.class, int.class));
         methods[81].setDisplayName("");
         methods[82] = new MethodDescriptor(Component.class.getMethod("mouseEnter", Event.class, int.class, int.class));
         methods[82].setDisplayName("");
         methods[83] = new MethodDescriptor(Component.class.getMethod("mouseExit", Event.class, int.class, int.class));
         methods[83].setDisplayName("");
         methods[84] = new MethodDescriptor(Component.class.getMethod("mouseMove", Event.class, int.class, int.class));
         methods[84].setDisplayName("");
         methods[85] = new MethodDescriptor(Component.class.getMethod("mouseUp", Event.class, int.class, int.class));
         methods[85].setDisplayName("");
         methods[86] = new MethodDescriptor(Component.class.getMethod("move", int.class, int.class));
         methods[86].setDisplayName("");
         methods[87] = new MethodDescriptor(Component.class.getMethod("nextFocus"));
         methods[87].setDisplayName("");
         methods[88] = new MethodDescriptor(Window.class.getMethod("pack"));
         methods[88].setDisplayName("");
         methods[89] = new MethodDescriptor(Container.class.getMethod("paint", Graphics.class));
         methods[89].setDisplayName("");
         methods[90] = new MethodDescriptor(Component.class.getMethod("paintAll", Graphics.class));
         methods[90].setDisplayName("");
         methods[91] = new MethodDescriptor(Container.class.getMethod("paintComponents", Graphics.class));
         methods[91].setDisplayName("");
         methods[92] = new MethodDescriptor(Window.class.getMethod("postEvent", Event.class));
         methods[92].setDisplayName("");
         methods[93] = new MethodDescriptor(Container.class.getMethod("preferredSize"));
         methods[93].setDisplayName("");
         methods[94] = new MethodDescriptor(Component.class.getMethod("prepareImage", Image.class, ImageObserver.class));
         methods[94].setDisplayName("");
         methods[95] = new MethodDescriptor(Component.class.getMethod("prepareImage", Image.class, int.class, int.class, ImageObserver.class));
         methods[95].setDisplayName("");
         methods[96] = new MethodDescriptor(Container.class.getMethod("print", Graphics.class));
         methods[96].setDisplayName("");
         methods[97] = new MethodDescriptor(Component.class.getMethod("printAll", Graphics.class));
         methods[97].setDisplayName("");
         methods[98] = new MethodDescriptor(Container.class.getMethod("printComponents", Graphics.class));
         methods[98].setDisplayName("");
         methods[99] = new MethodDescriptor(Component.class.getMethod("remove", MenuComponent.class));
         methods[99].setDisplayName("");
         methods[100] = new MethodDescriptor(Container.class.getMethod("remove", int.class));
         methods[100].setDisplayName("");
         methods[101] = new MethodDescriptor(JDialog.class.getMethod("remove", Component.class));
         methods[101].setDisplayName("");
         methods[102] = new MethodDescriptor(Container.class.getMethod("removeAll"));
         methods[102].setDisplayName("");
         methods[103] = new MethodDescriptor(Window.class.getMethod("removeNotify"));
         methods[103].setDisplayName("");
         methods[104] = new MethodDescriptor(Component.class.getMethod("removePropertyChangeListener", String.class, PropertyChangeListener.class));
         methods[104].setDisplayName("");
         methods[105] = new MethodDescriptor(Component.class.getMethod("repaint"));
         methods[105].setDisplayName("");
         methods[106] = new MethodDescriptor(Component.class.getMethod("repaint", long.class));
         methods[106].setDisplayName("");
         methods[107] = new MethodDescriptor(Component.class.getMethod("repaint", int.class, int.class, int.class, int.class));
         methods[107].setDisplayName("");
         methods[108] = new MethodDescriptor(JDialog.class.getMethod("repaint", long.class, int.class, int.class, int.class, int.class));
         methods[108].setDisplayName("");
         methods[109] = new MethodDescriptor(Component.class.getMethod("requestFocus"));
         methods[109].setDisplayName("");
         methods[110] = new MethodDescriptor(Component.class.getMethod("requestFocusInWindow"));
         methods[110].setDisplayName("");
         methods[111] = new MethodDescriptor(Window.class.getMethod("reshape", int.class, int.class, int.class, int.class));
         methods[111].setDisplayName("");
         methods[112] = new MethodDescriptor(Component.class.getMethod("resize", int.class, int.class));
         methods[112].setDisplayName("");
         methods[113] = new MethodDescriptor(Component.class.getMethod("resize", Dimension.class));
         methods[113].setDisplayName("");
         methods[114] = new MethodDescriptor(Window.class.getMethod("setBounds", int.class, int.class, int.class, int.class));
         methods[114].setDisplayName("");
         methods[115] = new MethodDescriptor(Container.class.getMethod("setComponentZOrder", Component.class, int.class));
         methods[115].setDisplayName("");
         methods[116] = new MethodDescriptor(JDialog.class.getMethod("setDefaultLookAndFeelDecorated", boolean.class));
         methods[116].setDisplayName("");
         methods[117] = new MethodDescriptor(Container.class.getMethod("setFocusTraversalKeys", int.class, Set.class));
         methods[117].setDisplayName("");
         methods[118] = new MethodDescriptor(Component.class.getMethod("show", boolean.class));
         methods[118].setDisplayName("");
         methods[119] = new MethodDescriptor(Dialog.class.getMethod("show"));
         methods[119].setDisplayName("");
         methods[120] = new MethodDescriptor(Component.class.getMethod("size"));
         methods[120].setDisplayName("");
         methods[121] = new MethodDescriptor(Dialog.class.getMethod("toBack"));
         methods[121].setDisplayName("");
         methods[122] = new MethodDescriptor(Window.class.getMethod("toFront"));
         methods[122].setDisplayName("");
         methods[123] = new MethodDescriptor(Component.class.getMethod("toString"));
         methods[123].setDisplayName("");
         methods[124] = new MethodDescriptor(Component.class.getMethod("transferFocus"));
         methods[124].setDisplayName("");
         methods[125] = new MethodDescriptor(Container.class.getMethod("transferFocusBackward"));
         methods[125].setDisplayName("");
         methods[126] = new MethodDescriptor(Container.class.getMethod("transferFocusDownCycle"));
         methods[126].setDisplayName("");
         methods[127] = new MethodDescriptor(Component.class.getMethod("transferFocusUpCycle"));
         methods[127].setDisplayName("");
         methods[128] = new MethodDescriptor(JDialog.class.getMethod("update", Graphics.class));
         methods[128].setDisplayName("");
         methods[129] = new MethodDescriptor(Container.class.getMethod("validate"));
         methods[129].setDisplayName("");
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
