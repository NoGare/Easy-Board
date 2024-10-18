package quickTable;

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
import java.text.MessageFormat;
import java.util.EventObject;
import java.util.Locale;
import javax.print.PrintService;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.JTable.PrintMode;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class MultiSpanCellTableBeanInfo extends SimpleBeanInfo {
   private static final int PROPERTY_accessibleContext = 0;
   private static final int PROPERTY_actionMap = 1;
   private static final int PROPERTY_alignmentX = 2;
   private static final int PROPERTY_alignmentY = 3;
   private static final int PROPERTY_ancestorListeners = 4;
   private static final int PROPERTY_autoCreateColumnsFromModel = 5;
   private static final int PROPERTY_autoCreateRowSorter = 6;
   private static final int PROPERTY_autoResizeMode = 7;
   private static final int PROPERTY_autoscrolls = 8;
   private static final int PROPERTY_background = 9;
   private static final int PROPERTY_backgroundSet = 10;
   private static final int PROPERTY_baselineResizeBehavior = 11;
   private static final int PROPERTY_border = 12;
   private static final int PROPERTY_bounds = 13;
   private static final int PROPERTY_cellEditor = 14;
   private static final int PROPERTY_cellSelectionEnabled = 15;
   private static final int PROPERTY_colorModel = 16;
   private static final int PROPERTY_columnClass = 17;
   private static final int PROPERTY_columnCount = 18;
   private static final int PROPERTY_columnModel = 19;
   private static final int PROPERTY_columnName = 20;
   private static final int PROPERTY_columnSelectionAllowed = 21;
   private static final int PROPERTY_columnSelectionInterval = 22;
   private static final int PROPERTY_component = 23;
   private static final int PROPERTY_componentCount = 24;
   private static final int PROPERTY_componentListeners = 25;
   private static final int PROPERTY_componentOrientation = 26;
   private static final int PROPERTY_componentPopupMenu = 27;
   private static final int PROPERTY_components = 28;
   private static final int PROPERTY_containerListeners = 29;
   private static final int PROPERTY_cursor = 30;
   private static final int PROPERTY_cursorSet = 31;
   private static final int PROPERTY_debugGraphicsOptions = 32;
   private static final int PROPERTY_displayable = 33;
   private static final int PROPERTY_doubleBuffered = 34;
   private static final int PROPERTY_dragEnabled = 35;
   private static final int PROPERTY_dropLocation = 36;
   private static final int PROPERTY_dropMode = 37;
   private static final int PROPERTY_dropTarget = 38;
   private static final int PROPERTY_editing = 39;
   private static final int PROPERTY_editingColumn = 40;
   private static final int PROPERTY_editingRow = 41;
   private static final int PROPERTY_editorComponent = 42;
   private static final int PROPERTY_enabled = 43;
   private static final int PROPERTY_fillsViewportHeight = 44;
   private static final int PROPERTY_focusable = 45;
   private static final int PROPERTY_focusCycleRoot = 46;
   private static final int PROPERTY_focusCycleRootAncestor = 47;
   private static final int PROPERTY_focusListeners = 48;
   private static final int PROPERTY_focusOwner = 49;
   private static final int PROPERTY_focusTraversable = 50;
   private static final int PROPERTY_focusTraversalKeys = 51;
   private static final int PROPERTY_focusTraversalKeysEnabled = 52;
   private static final int PROPERTY_focusTraversalPolicy = 53;
   private static final int PROPERTY_focusTraversalPolicyProvider = 54;
   private static final int PROPERTY_focusTraversalPolicySet = 55;
   private static final int PROPERTY_font = 56;
   private static final int PROPERTY_fontSet = 57;
   private static final int PROPERTY_foreground = 58;
   private static final int PROPERTY_foregroundSet = 59;
   private static final int PROPERTY_graphics = 60;
   private static final int PROPERTY_graphicsConfiguration = 61;
   private static final int PROPERTY_gridColor = 62;
   private static final int PROPERTY_height = 63;
   private static final int PROPERTY_hierarchyBoundsListeners = 64;
   private static final int PROPERTY_hierarchyListeners = 65;
   private static final int PROPERTY_ignoreRepaint = 66;
   private static final int PROPERTY_inheritsPopupMenu = 67;
   private static final int PROPERTY_inputContext = 68;
   private static final int PROPERTY_inputMap = 69;
   private static final int PROPERTY_inputMethodListeners = 70;
   private static final int PROPERTY_inputMethodRequests = 71;
   private static final int PROPERTY_inputVerifier = 72;
   private static final int PROPERTY_insets = 73;
   private static final int PROPERTY_intercellSpacing = 74;
   private static final int PROPERTY_keyListeners = 75;
   private static final int PROPERTY_layout = 76;
   private static final int PROPERTY_lightweight = 77;
   private static final int PROPERTY_locale = 78;
   private static final int PROPERTY_location = 79;
   private static final int PROPERTY_locationOnScreen = 80;
   private static final int PROPERTY_managingFocus = 81;
   private static final int PROPERTY_maximumSize = 82;
   private static final int PROPERTY_maximumSizeSet = 83;
   private static final int PROPERTY_minimumSize = 84;
   private static final int PROPERTY_minimumSizeSet = 85;
   private static final int PROPERTY_model = 86;
   private static final int PROPERTY_mouseListeners = 87;
   private static final int PROPERTY_mouseMotionListeners = 88;
   private static final int PROPERTY_mousePosition = 89;
   private static final int PROPERTY_mouseWheelListeners = 90;
   private static final int PROPERTY_name = 91;
   private static final int PROPERTY_nextFocusableComponent = 92;
   private static final int PROPERTY_opaque = 93;
   private static final int PROPERTY_optimizedDrawingEnabled = 94;
   private static final int PROPERTY_paintingForPrint = 95;
   private static final int PROPERTY_paintingTile = 96;
   private static final int PROPERTY_parent = 97;
   private static final int PROPERTY_peer = 98;
   private static final int PROPERTY_preferredScrollableViewportSize = 99;
   private static final int PROPERTY_preferredSize = 100;
   private static final int PROPERTY_preferredSizeSet = 101;
   private static final int PROPERTY_propertyChangeListeners = 102;
   private static final int PROPERTY_registeredKeyStrokes = 103;
   private static final int PROPERTY_requestFocusEnabled = 104;
   private static final int PROPERTY_rootPane = 105;
   private static final int PROPERTY_rowCount = 106;
   private static final int PROPERTY_rowHeight = 107;
   private static final int PROPERTY_rowMargin = 108;
   private static final int PROPERTY_rowSelectionAllowed = 109;
   private static final int PROPERTY_rowSelectionInterval = 110;
   private static final int PROPERTY_rowSorter = 111;
   private static final int PROPERTY_scrollableTracksViewportHeight = 112;
   private static final int PROPERTY_scrollableTracksViewportWidth = 113;
   private static final int PROPERTY_selectedColumn = 114;
   private static final int PROPERTY_selectedColumnCount = 115;
   private static final int PROPERTY_selectedColumns = 116;
   private static final int PROPERTY_selectedRow = 117;
   private static final int PROPERTY_selectedRowCount = 118;
   private static final int PROPERTY_selectedRows = 119;
   private static final int PROPERTY_selectionBackground = 120;
   private static final int PROPERTY_selectionForeground = 121;
   private static final int PROPERTY_selectionMode = 122;
   private static final int PROPERTY_selectionModel = 123;
   private static final int PROPERTY_showGrid = 124;
   private static final int PROPERTY_showHorizontalLines = 125;
   private static final int PROPERTY_showing = 126;
   private static final int PROPERTY_showVerticalLines = 127;
   private static final int PROPERTY_size = 128;
   private static final int PROPERTY_surrendersFocusOnKeystroke = 129;
   private static final int PROPERTY_tableHeader = 130;
   private static final int PROPERTY_toolkit = 131;
   private static final int PROPERTY_toolTipText = 132;
   private static final int PROPERTY_topLevelAncestor = 133;
   private static final int PROPERTY_transferHandler = 134;
   private static final int PROPERTY_treeLock = 135;
   private static final int PROPERTY_UI = 136;
   private static final int PROPERTY_UIClassID = 137;
   private static final int PROPERTY_updateSelectionOnSort = 138;
   private static final int PROPERTY_valid = 139;
   private static final int PROPERTY_validateRoot = 140;
   private static final int PROPERTY_verifyInputWhenFocusTarget = 141;
   private static final int PROPERTY_vetoableChangeListeners = 142;
   private static final int PROPERTY_visible = 143;
   private static final int PROPERTY_visibleRect = 144;
   private static final int PROPERTY_width = 145;
   private static final int PROPERTY_x = 146;
   private static final int PROPERTY_y = 147;
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
   private static final int METHOD_addColumn7 = 7;
   private static final int METHOD_addColumnSelectionInterval8 = 8;
   private static final int METHOD_addNotify9 = 9;
   private static final int METHOD_addPropertyChangeListener10 = 10;
   private static final int METHOD_addRowSelectionInterval11 = 11;
   private static final int METHOD_applyComponentOrientation12 = 12;
   private static final int METHOD_areFocusTraversalKeysSet13 = 13;
   private static final int METHOD_bounds14 = 14;
   private static final int METHOD_changeSelection15 = 15;
   private static final int METHOD_checkImage16 = 16;
   private static final int METHOD_checkImage17 = 17;
   private static final int METHOD_clearSelection18 = 18;
   private static final int METHOD_columnAdded19 = 19;
   private static final int METHOD_columnAtPoint20 = 20;
   private static final int METHOD_columnMarginChanged21 = 21;
   private static final int METHOD_columnMoved22 = 22;
   private static final int METHOD_columnRemoved23 = 23;
   private static final int METHOD_columnSelectionChanged24 = 24;
   private static final int METHOD_computeVisibleRect25 = 25;
   private static final int METHOD_contains26 = 26;
   private static final int METHOD_contains27 = 27;
   private static final int METHOD_convertColumnIndexToModel28 = 28;
   private static final int METHOD_convertColumnIndexToView29 = 29;
   private static final int METHOD_convertRowIndexToModel30 = 30;
   private static final int METHOD_convertRowIndexToView31 = 31;
   private static final int METHOD_countComponents32 = 32;
   private static final int METHOD_createDefaultColumnsFromModel33 = 33;
   private static final int METHOD_createImage34 = 34;
   private static final int METHOD_createImage35 = 35;
   private static final int METHOD_createScrollPaneForTable36 = 36;
   private static final int METHOD_createToolTip37 = 37;
   private static final int METHOD_createVolatileImage38 = 38;
   private static final int METHOD_createVolatileImage39 = 39;
   private static final int METHOD_deliverEvent40 = 40;
   private static final int METHOD_disable41 = 41;
   private static final int METHOD_dispatchEvent42 = 42;
   private static final int METHOD_doLayout43 = 43;
   private static final int METHOD_editCellAt44 = 44;
   private static final int METHOD_editCellAt45 = 45;
   private static final int METHOD_editingCanceled46 = 46;
   private static final int METHOD_editingStopped47 = 47;
   private static final int METHOD_enable48 = 48;
   private static final int METHOD_enable49 = 49;
   private static final int METHOD_enableInputMethods50 = 50;
   private static final int METHOD_findComponentAt51 = 51;
   private static final int METHOD_findComponentAt52 = 52;
   private static final int METHOD_firePropertyChange53 = 53;
   private static final int METHOD_firePropertyChange54 = 54;
   private static final int METHOD_firePropertyChange55 = 55;
   private static final int METHOD_firePropertyChange56 = 56;
   private static final int METHOD_firePropertyChange57 = 57;
   private static final int METHOD_firePropertyChange58 = 58;
   private static final int METHOD_firePropertyChange59 = 59;
   private static final int METHOD_firePropertyChange60 = 60;
   private static final int METHOD_getActionForKeyStroke61 = 61;
   private static final int METHOD_getBaseline62 = 62;
   private static final int METHOD_getBounds63 = 63;
   private static final int METHOD_getCellEditor64 = 64;
   private static final int METHOD_getCellRect65 = 65;
   private static final int METHOD_getCellRenderer66 = 66;
   private static final int METHOD_getClientProperty67 = 67;
   private static final int METHOD_getColumn68 = 68;
   private static final int METHOD_getComponentAt69 = 69;
   private static final int METHOD_getComponentAt70 = 70;
   private static final int METHOD_getComponentZOrder71 = 71;
   private static final int METHOD_getConditionForKeyStroke72 = 72;
   private static final int METHOD_getDefaultEditor73 = 73;
   private static final int METHOD_getDefaultLocale74 = 74;
   private static final int METHOD_getDefaultRenderer75 = 75;
   private static final int METHOD_getFocusTraversalKeys76 = 76;
   private static final int METHOD_getFontMetrics77 = 77;
   private static final int METHOD_getInsets78 = 78;
   private static final int METHOD_getListeners79 = 79;
   private static final int METHOD_getLocation80 = 80;
   private static final int METHOD_getMousePosition81 = 81;
   private static final int METHOD_getPopupLocation82 = 82;
   private static final int METHOD_getPrintable83 = 83;
   private static final int METHOD_getPropertyChangeListeners84 = 84;
   private static final int METHOD_getScrollableBlockIncrement85 = 85;
   private static final int METHOD_getScrollableUnitIncrement86 = 86;
   private static final int METHOD_getSize87 = 87;
   private static final int METHOD_getToolTipLocation88 = 88;
   private static final int METHOD_getToolTipText89 = 89;
   private static final int METHOD_getValueAt90 = 90;
   private static final int METHOD_gotFocus91 = 91;
   private static final int METHOD_grabFocus92 = 92;
   private static final int METHOD_handleEvent93 = 93;
   private static final int METHOD_hasFocus94 = 94;
   private static final int METHOD_hide95 = 95;
   private static final int METHOD_imageUpdate96 = 96;
   private static final int METHOD_insets97 = 97;
   private static final int METHOD_inside98 = 98;
   private static final int METHOD_invalidate99 = 99;
   private static final int METHOD_isAncestorOf100 = 100;
   private static final int METHOD_isCellEditable101 = 101;
   private static final int METHOD_isCellSelected102 = 102;
   private static final int METHOD_isColumnSelected103 = 103;
   private static final int METHOD_isFocusCycleRoot104 = 104;
   private static final int METHOD_isLightweightComponent105 = 105;
   private static final int METHOD_isRowSelected106 = 106;
   private static final int METHOD_keyDown107 = 107;
   private static final int METHOD_keyUp108 = 108;
   private static final int METHOD_layout109 = 109;
   private static final int METHOD_list110 = 110;
   private static final int METHOD_list111 = 111;
   private static final int METHOD_list112 = 112;
   private static final int METHOD_list113 = 113;
   private static final int METHOD_list114 = 114;
   private static final int METHOD_locate115 = 115;
   private static final int METHOD_location116 = 116;
   private static final int METHOD_lostFocus117 = 117;
   private static final int METHOD_minimumSize118 = 118;
   private static final int METHOD_mouseDown119 = 119;
   private static final int METHOD_mouseDrag120 = 120;
   private static final int METHOD_mouseEnter121 = 121;
   private static final int METHOD_mouseExit122 = 122;
   private static final int METHOD_mouseMove123 = 123;
   private static final int METHOD_mouseUp124 = 124;
   private static final int METHOD_move125 = 125;
   private static final int METHOD_moveColumn126 = 126;
   private static final int METHOD_nextFocus127 = 127;
   private static final int METHOD_paint128 = 128;
   private static final int METHOD_paintAll129 = 129;
   private static final int METHOD_paintComponents130 = 130;
   private static final int METHOD_paintImmediately131 = 131;
   private static final int METHOD_paintImmediately132 = 132;
   private static final int METHOD_postEvent133 = 133;
   private static final int METHOD_preferredSize134 = 134;
   private static final int METHOD_prepareEditor135 = 135;
   private static final int METHOD_prepareImage136 = 136;
   private static final int METHOD_prepareImage137 = 137;
   private static final int METHOD_prepareRenderer138 = 138;
   private static final int METHOD_print139 = 139;
   private static final int METHOD_print140 = 140;
   private static final int METHOD_print141 = 141;
   private static final int METHOD_print142 = 142;
   private static final int METHOD_print143 = 143;
   private static final int METHOD_print144 = 144;
   private static final int METHOD_printAll145 = 145;
   private static final int METHOD_printComponents146 = 146;
   private static final int METHOD_putClientProperty147 = 147;
   private static final int METHOD_registerKeyboardAction148 = 148;
   private static final int METHOD_registerKeyboardAction149 = 149;
   private static final int METHOD_remove150 = 150;
   private static final int METHOD_remove151 = 151;
   private static final int METHOD_remove152 = 152;
   private static final int METHOD_removeAll153 = 153;
   private static final int METHOD_removeColumn154 = 154;
   private static final int METHOD_removeColumnSelectionInterval155 = 155;
   private static final int METHOD_removeEditor156 = 156;
   private static final int METHOD_removeNotify157 = 157;
   private static final int METHOD_removePropertyChangeListener158 = 158;
   private static final int METHOD_removeRowSelectionInterval159 = 159;
   private static final int METHOD_repaint160 = 160;
   private static final int METHOD_repaint161 = 161;
   private static final int METHOD_repaint162 = 162;
   private static final int METHOD_repaint163 = 163;
   private static final int METHOD_repaint164 = 164;
   private static final int METHOD_requestDefaultFocus165 = 165;
   private static final int METHOD_requestFocus166 = 166;
   private static final int METHOD_requestFocus167 = 167;
   private static final int METHOD_requestFocusInWindow168 = 168;
   private static final int METHOD_resetKeyboardActions169 = 169;
   private static final int METHOD_reshape170 = 170;
   private static final int METHOD_resize171 = 171;
   private static final int METHOD_resize172 = 172;
   private static final int METHOD_revalidate173 = 173;
   private static final int METHOD_rowAtPoint174 = 174;
   private static final int METHOD_scrollRectToVisible175 = 175;
   private static final int METHOD_selectAll176 = 176;
   private static final int METHOD_setBounds177 = 177;
   private static final int METHOD_setComponentZOrder178 = 178;
   private static final int METHOD_setDefaultEditor179 = 179;
   private static final int METHOD_setDefaultLocale180 = 180;
   private static final int METHOD_setDefaultRenderer181 = 181;
   private static final int METHOD_setRowHeight182 = 182;
   private static final int METHOD_setValueAt183 = 183;
   private static final int METHOD_show184 = 184;
   private static final int METHOD_show185 = 185;
   private static final int METHOD_size186 = 186;
   private static final int METHOD_sizeColumnsToFit187 = 187;
   private static final int METHOD_sizeColumnsToFit188 = 188;
   private static final int METHOD_sorterChanged189 = 189;
   private static final int METHOD_tableChanged190 = 190;
   private static final int METHOD_toString191 = 191;
   private static final int METHOD_transferFocus192 = 192;
   private static final int METHOD_transferFocusBackward193 = 193;
   private static final int METHOD_transferFocusDownCycle194 = 194;
   private static final int METHOD_transferFocusUpCycle195 = 195;
   private static final int METHOD_unregisterKeyboardAction196 = 196;
   private static final int METHOD_update197 = 197;
   private static final int METHOD_updateUI198 = 198;
   private static final int METHOD_validate199 = 199;
   private static final int METHOD_valueChanged200 = 200;
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
      return new BeanDescriptor(MultiSpanCellTable.class, null);
   }

   private static PropertyDescriptor[] getPdescriptor() {
      PropertyDescriptor[] properties = new PropertyDescriptor[148];

      try {
         properties[0] = new PropertyDescriptor("accessibleContext", MultiSpanCellTable.class, "getAccessibleContext", null);
         properties[1] = new PropertyDescriptor("actionMap", MultiSpanCellTable.class, "getActionMap", "setActionMap");
         properties[2] = new PropertyDescriptor("alignmentX", MultiSpanCellTable.class, "getAlignmentX", "setAlignmentX");
         properties[3] = new PropertyDescriptor("alignmentY", MultiSpanCellTable.class, "getAlignmentY", "setAlignmentY");
         properties[4] = new PropertyDescriptor("ancestorListeners", MultiSpanCellTable.class, "getAncestorListeners", null);
         properties[5] = new PropertyDescriptor(
            "autoCreateColumnsFromModel", MultiSpanCellTable.class, "getAutoCreateColumnsFromModel", "setAutoCreateColumnsFromModel"
         );
         properties[6] = new PropertyDescriptor("autoCreateRowSorter", MultiSpanCellTable.class, "getAutoCreateRowSorter", "setAutoCreateRowSorter");
         properties[7] = new PropertyDescriptor("autoResizeMode", MultiSpanCellTable.class, "getAutoResizeMode", "setAutoResizeMode");
         properties[8] = new PropertyDescriptor("autoscrolls", MultiSpanCellTable.class, "getAutoscrolls", "setAutoscrolls");
         properties[9] = new PropertyDescriptor("background", MultiSpanCellTable.class, "getBackground", "setBackground");
         properties[10] = new PropertyDescriptor("backgroundSet", MultiSpanCellTable.class, "isBackgroundSet", null);
         properties[11] = new PropertyDescriptor("baselineResizeBehavior", MultiSpanCellTable.class, "getBaselineResizeBehavior", null);
         properties[12] = new PropertyDescriptor("border", MultiSpanCellTable.class, "getBorder", "setBorder");
         properties[13] = new PropertyDescriptor("bounds", MultiSpanCellTable.class, "getBounds", "setBounds");
         properties[14] = new PropertyDescriptor("cellEditor", MultiSpanCellTable.class, "getCellEditor", "setCellEditor");
         properties[15] = new PropertyDescriptor("cellSelectionEnabled", MultiSpanCellTable.class, "getCellSelectionEnabled", "setCellSelectionEnabled");
         properties[16] = new PropertyDescriptor("colorModel", MultiSpanCellTable.class, "getColorModel", null);
         properties[17] = new IndexedPropertyDescriptor("columnClass", MultiSpanCellTable.class, null, null, "getColumnClass", null);
         properties[18] = new PropertyDescriptor("columnCount", MultiSpanCellTable.class, "getColumnCount", null);
         properties[19] = new PropertyDescriptor("columnModel", MultiSpanCellTable.class, "getColumnModel", "setColumnModel");
         properties[20] = new IndexedPropertyDescriptor("columnName", MultiSpanCellTable.class, null, null, "getColumnName", null);
         properties[21] = new PropertyDescriptor("columnSelectionAllowed", MultiSpanCellTable.class, "getColumnSelectionAllowed", "setColumnSelectionAllowed");
         properties[22] = new IndexedPropertyDescriptor("columnSelectionInterval", MultiSpanCellTable.class, null, null, null, "setColumnSelectionInterval");
         properties[23] = new IndexedPropertyDescriptor("component", MultiSpanCellTable.class, null, null, "getComponent", null);
         properties[24] = new PropertyDescriptor("componentCount", MultiSpanCellTable.class, "getComponentCount", null);
         properties[25] = new PropertyDescriptor("componentListeners", MultiSpanCellTable.class, "getComponentListeners", null);
         properties[26] = new PropertyDescriptor("componentOrientation", MultiSpanCellTable.class, "getComponentOrientation", "setComponentOrientation");
         properties[27] = new PropertyDescriptor("componentPopupMenu", MultiSpanCellTable.class, "getComponentPopupMenu", "setComponentPopupMenu");
         properties[28] = new PropertyDescriptor("components", MultiSpanCellTable.class, "getComponents", null);
         properties[29] = new PropertyDescriptor("containerListeners", MultiSpanCellTable.class, "getContainerListeners", null);
         properties[30] = new PropertyDescriptor("cursor", MultiSpanCellTable.class, "getCursor", "setCursor");
         properties[31] = new PropertyDescriptor("cursorSet", MultiSpanCellTable.class, "isCursorSet", null);
         properties[32] = new PropertyDescriptor("debugGraphicsOptions", MultiSpanCellTable.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions");
         properties[33] = new PropertyDescriptor("displayable", MultiSpanCellTable.class, "isDisplayable", null);
         properties[34] = new PropertyDescriptor("doubleBuffered", MultiSpanCellTable.class, "isDoubleBuffered", "setDoubleBuffered");
         properties[35] = new PropertyDescriptor("dragEnabled", MultiSpanCellTable.class, "getDragEnabled", "setDragEnabled");
         properties[36] = new PropertyDescriptor("dropLocation", MultiSpanCellTable.class, "getDropLocation", null);
         properties[37] = new PropertyDescriptor("dropMode", MultiSpanCellTable.class, "getDropMode", "setDropMode");
         properties[38] = new PropertyDescriptor("dropTarget", MultiSpanCellTable.class, "getDropTarget", "setDropTarget");
         properties[39] = new PropertyDescriptor("editing", MultiSpanCellTable.class, "isEditing", null);
         properties[40] = new PropertyDescriptor("editingColumn", MultiSpanCellTable.class, "getEditingColumn", "setEditingColumn");
         properties[41] = new PropertyDescriptor("editingRow", MultiSpanCellTable.class, "getEditingRow", "setEditingRow");
         properties[42] = new PropertyDescriptor("editorComponent", MultiSpanCellTable.class, "getEditorComponent", null);
         properties[43] = new PropertyDescriptor("enabled", MultiSpanCellTable.class, "isEnabled", "setEnabled");
         properties[44] = new PropertyDescriptor("fillsViewportHeight", MultiSpanCellTable.class, "getFillsViewportHeight", "setFillsViewportHeight");
         properties[45] = new PropertyDescriptor("focusable", MultiSpanCellTable.class, "isFocusable", "setFocusable");
         properties[46] = new PropertyDescriptor("focusCycleRoot", MultiSpanCellTable.class, "isFocusCycleRoot", "setFocusCycleRoot");
         properties[47] = new PropertyDescriptor("focusCycleRootAncestor", MultiSpanCellTable.class, "getFocusCycleRootAncestor", null);
         properties[48] = new PropertyDescriptor("focusListeners", MultiSpanCellTable.class, "getFocusListeners", null);
         properties[49] = new PropertyDescriptor("focusOwner", MultiSpanCellTable.class, "isFocusOwner", null);
         properties[50] = new PropertyDescriptor("focusTraversable", MultiSpanCellTable.class, "isFocusTraversable", null);
         properties[51] = new IndexedPropertyDescriptor("focusTraversalKeys", MultiSpanCellTable.class, null, null, null, "setFocusTraversalKeys");
         properties[52] = new PropertyDescriptor(
            "focusTraversalKeysEnabled", MultiSpanCellTable.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled"
         );
         properties[53] = new PropertyDescriptor("focusTraversalPolicy", MultiSpanCellTable.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy");
         properties[54] = new PropertyDescriptor(
            "focusTraversalPolicyProvider", MultiSpanCellTable.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider"
         );
         properties[55] = new PropertyDescriptor("focusTraversalPolicySet", MultiSpanCellTable.class, "isFocusTraversalPolicySet", null);
         properties[56] = new PropertyDescriptor("font", MultiSpanCellTable.class, "getFont", "setFont");
         properties[57] = new PropertyDescriptor("fontSet", MultiSpanCellTable.class, "isFontSet", null);
         properties[58] = new PropertyDescriptor("foreground", MultiSpanCellTable.class, "getForeground", "setForeground");
         properties[59] = new PropertyDescriptor("foregroundSet", MultiSpanCellTable.class, "isForegroundSet", null);
         properties[60] = new PropertyDescriptor("graphics", MultiSpanCellTable.class, "getGraphics", null);
         properties[61] = new PropertyDescriptor("graphicsConfiguration", MultiSpanCellTable.class, "getGraphicsConfiguration", null);
         properties[62] = new PropertyDescriptor("gridColor", MultiSpanCellTable.class, "getGridColor", "setGridColor");
         properties[63] = new PropertyDescriptor("height", MultiSpanCellTable.class, "getHeight", null);
         properties[64] = new PropertyDescriptor("hierarchyBoundsListeners", MultiSpanCellTable.class, "getHierarchyBoundsListeners", null);
         properties[65] = new PropertyDescriptor("hierarchyListeners", MultiSpanCellTable.class, "getHierarchyListeners", null);
         properties[66] = new PropertyDescriptor("ignoreRepaint", MultiSpanCellTable.class, "getIgnoreRepaint", "setIgnoreRepaint");
         properties[67] = new PropertyDescriptor("inheritsPopupMenu", MultiSpanCellTable.class, "getInheritsPopupMenu", "setInheritsPopupMenu");
         properties[68] = new PropertyDescriptor("inputContext", MultiSpanCellTable.class, "getInputContext", null);
         properties[69] = new PropertyDescriptor("inputMap", MultiSpanCellTable.class, "getInputMap", null);
         properties[70] = new PropertyDescriptor("inputMethodListeners", MultiSpanCellTable.class, "getInputMethodListeners", null);
         properties[71] = new PropertyDescriptor("inputMethodRequests", MultiSpanCellTable.class, "getInputMethodRequests", null);
         properties[72] = new PropertyDescriptor("inputVerifier", MultiSpanCellTable.class, "getInputVerifier", "setInputVerifier");
         properties[73] = new PropertyDescriptor("insets", MultiSpanCellTable.class, "getInsets", null);
         properties[74] = new PropertyDescriptor("intercellSpacing", MultiSpanCellTable.class, "getIntercellSpacing", "setIntercellSpacing");
         properties[75] = new PropertyDescriptor("keyListeners", MultiSpanCellTable.class, "getKeyListeners", null);
         properties[76] = new PropertyDescriptor("layout", MultiSpanCellTable.class, "getLayout", "setLayout");
         properties[77] = new PropertyDescriptor("lightweight", MultiSpanCellTable.class, "isLightweight", null);
         properties[78] = new PropertyDescriptor("locale", MultiSpanCellTable.class, "getLocale", "setLocale");
         properties[79] = new PropertyDescriptor("location", MultiSpanCellTable.class, "getLocation", "setLocation");
         properties[80] = new PropertyDescriptor("locationOnScreen", MultiSpanCellTable.class, "getLocationOnScreen", null);
         properties[81] = new PropertyDescriptor("managingFocus", MultiSpanCellTable.class, "isManagingFocus", null);
         properties[82] = new PropertyDescriptor("maximumSize", MultiSpanCellTable.class, "getMaximumSize", "setMaximumSize");
         properties[83] = new PropertyDescriptor("maximumSizeSet", MultiSpanCellTable.class, "isMaximumSizeSet", null);
         properties[84] = new PropertyDescriptor("minimumSize", MultiSpanCellTable.class, "getMinimumSize", "setMinimumSize");
         properties[85] = new PropertyDescriptor("minimumSizeSet", MultiSpanCellTable.class, "isMinimumSizeSet", null);
         properties[86] = new PropertyDescriptor("model", MultiSpanCellTable.class, "getModel", "setModel");
         properties[87] = new PropertyDescriptor("mouseListeners", MultiSpanCellTable.class, "getMouseListeners", null);
         properties[88] = new PropertyDescriptor("mouseMotionListeners", MultiSpanCellTable.class, "getMouseMotionListeners", null);
         properties[89] = new PropertyDescriptor("mousePosition", MultiSpanCellTable.class, "getMousePosition", null);
         properties[90] = new PropertyDescriptor("mouseWheelListeners", MultiSpanCellTable.class, "getMouseWheelListeners", null);
         properties[91] = new PropertyDescriptor("name", MultiSpanCellTable.class, "getName", "setName");
         properties[92] = new PropertyDescriptor("nextFocusableComponent", MultiSpanCellTable.class, "getNextFocusableComponent", "setNextFocusableComponent");
         properties[93] = new PropertyDescriptor("opaque", MultiSpanCellTable.class, "isOpaque", "setOpaque");
         properties[94] = new PropertyDescriptor("optimizedDrawingEnabled", MultiSpanCellTable.class, "isOptimizedDrawingEnabled", null);
         properties[95] = new PropertyDescriptor("paintingForPrint", MultiSpanCellTable.class, "isPaintingForPrint", null);
         properties[96] = new PropertyDescriptor("paintingTile", MultiSpanCellTable.class, "isPaintingTile", null);
         properties[97] = new PropertyDescriptor("parent", MultiSpanCellTable.class, "getParent", null);
         properties[98] = new PropertyDescriptor("peer", MultiSpanCellTable.class, "getPeer", null);
         properties[99] = new PropertyDescriptor(
            "preferredScrollableViewportSize", MultiSpanCellTable.class, "getPreferredScrollableViewportSize", "setPreferredScrollableViewportSize"
         );
         properties[100] = new PropertyDescriptor("preferredSize", MultiSpanCellTable.class, "getPreferredSize", "setPreferredSize");
         properties[101] = new PropertyDescriptor("preferredSizeSet", MultiSpanCellTable.class, "isPreferredSizeSet", null);
         properties[102] = new PropertyDescriptor("propertyChangeListeners", MultiSpanCellTable.class, "getPropertyChangeListeners", null);
         properties[103] = new PropertyDescriptor("registeredKeyStrokes", MultiSpanCellTable.class, "getRegisteredKeyStrokes", null);
         properties[104] = new PropertyDescriptor("requestFocusEnabled", MultiSpanCellTable.class, "isRequestFocusEnabled", "setRequestFocusEnabled");
         properties[105] = new PropertyDescriptor("rootPane", MultiSpanCellTable.class, "getRootPane", null);
         properties[106] = new PropertyDescriptor("rowCount", MultiSpanCellTable.class, "getRowCount", null);
         properties[107] = new PropertyDescriptor("rowHeight", MultiSpanCellTable.class, "getRowHeight", "setRowHeight");
         properties[108] = new PropertyDescriptor("rowMargin", MultiSpanCellTable.class, "getRowMargin", "setRowMargin");
         properties[109] = new PropertyDescriptor("rowSelectionAllowed", MultiSpanCellTable.class, "getRowSelectionAllowed", "setRowSelectionAllowed");
         properties[110] = new IndexedPropertyDescriptor("rowSelectionInterval", MultiSpanCellTable.class, null, null, null, "setRowSelectionInterval");
         properties[111] = new PropertyDescriptor("rowSorter", MultiSpanCellTable.class, "getRowSorter", "setRowSorter");
         properties[112] = new PropertyDescriptor("scrollableTracksViewportHeight", MultiSpanCellTable.class, "getScrollableTracksViewportHeight", null);
         properties[113] = new PropertyDescriptor("scrollableTracksViewportWidth", MultiSpanCellTable.class, "getScrollableTracksViewportWidth", null);
         properties[114] = new PropertyDescriptor("selectedColumn", MultiSpanCellTable.class, "getSelectedColumn", null);
         properties[115] = new PropertyDescriptor("selectedColumnCount", MultiSpanCellTable.class, "getSelectedColumnCount", null);
         properties[116] = new PropertyDescriptor("selectedColumns", MultiSpanCellTable.class, "getSelectedColumns", null);
         properties[117] = new PropertyDescriptor("selectedRow", MultiSpanCellTable.class, "getSelectedRow", null);
         properties[118] = new PropertyDescriptor("selectedRowCount", MultiSpanCellTable.class, "getSelectedRowCount", null);
         properties[119] = new PropertyDescriptor("selectedRows", MultiSpanCellTable.class, "getSelectedRows", null);
         properties[120] = new PropertyDescriptor("selectionBackground", MultiSpanCellTable.class, "getSelectionBackground", "setSelectionBackground");
         properties[121] = new PropertyDescriptor("selectionForeground", MultiSpanCellTable.class, "getSelectionForeground", "setSelectionForeground");
         properties[122] = new PropertyDescriptor("selectionMode", MultiSpanCellTable.class, null, "setSelectionMode");
         properties[123] = new PropertyDescriptor("selectionModel", MultiSpanCellTable.class, "getSelectionModel", "setSelectionModel");
         properties[124] = new PropertyDescriptor("showGrid", MultiSpanCellTable.class, null, "setShowGrid");
         properties[125] = new PropertyDescriptor("showHorizontalLines", MultiSpanCellTable.class, "getShowHorizontalLines", "setShowHorizontalLines");
         properties[126] = new PropertyDescriptor("showing", MultiSpanCellTable.class, "isShowing", null);
         properties[127] = new PropertyDescriptor("showVerticalLines", MultiSpanCellTable.class, "getShowVerticalLines", "setShowVerticalLines");
         properties[128] = new PropertyDescriptor("size", MultiSpanCellTable.class, "getSize", "setSize");
         properties[129] = new PropertyDescriptor(
            "surrendersFocusOnKeystroke", MultiSpanCellTable.class, "getSurrendersFocusOnKeystroke", "setSurrendersFocusOnKeystroke"
         );
         properties[130] = new PropertyDescriptor("tableHeader", MultiSpanCellTable.class, "getTableHeader", "setTableHeader");
         properties[131] = new PropertyDescriptor("toolkit", MultiSpanCellTable.class, "getToolkit", null);
         properties[132] = new PropertyDescriptor("toolTipText", MultiSpanCellTable.class, "getToolTipText", "setToolTipText");
         properties[133] = new PropertyDescriptor("topLevelAncestor", MultiSpanCellTable.class, "getTopLevelAncestor", null);
         properties[134] = new PropertyDescriptor("transferHandler", MultiSpanCellTable.class, "getTransferHandler", "setTransferHandler");
         properties[135] = new PropertyDescriptor("treeLock", MultiSpanCellTable.class, "getTreeLock", null);
         properties[136] = new PropertyDescriptor("UI", MultiSpanCellTable.class, "getUI", "setUI");
         properties[137] = new PropertyDescriptor("UIClassID", MultiSpanCellTable.class, "getUIClassID", null);
         properties[138] = new PropertyDescriptor("updateSelectionOnSort", MultiSpanCellTable.class, "getUpdateSelectionOnSort", "setUpdateSelectionOnSort");
         properties[139] = new PropertyDescriptor("valid", MultiSpanCellTable.class, "isValid", null);
         properties[140] = new PropertyDescriptor("validateRoot", MultiSpanCellTable.class, "isValidateRoot", null);
         properties[141] = new PropertyDescriptor(
            "verifyInputWhenFocusTarget", MultiSpanCellTable.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget"
         );
         properties[142] = new PropertyDescriptor("vetoableChangeListeners", MultiSpanCellTable.class, "getVetoableChangeListeners", null);
         properties[143] = new PropertyDescriptor("visible", MultiSpanCellTable.class, "isVisible", "setVisible");
         properties[144] = new PropertyDescriptor("visibleRect", MultiSpanCellTable.class, "getVisibleRect", null);
         properties[145] = new PropertyDescriptor("width", MultiSpanCellTable.class, "getWidth", null);
         properties[146] = new PropertyDescriptor("x", MultiSpanCellTable.class, "getX", null);
         properties[147] = new PropertyDescriptor("y", MultiSpanCellTable.class, "getY", null);
      } catch (IntrospectionException var2) {
         var2.printStackTrace();
      }

      return properties;
   }

   private static EventSetDescriptor[] getEdescriptor() {
      EventSetDescriptor[] eventSets = new EventSetDescriptor[13];

      try {
         eventSets[0] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "ancestorListener",
            AncestorListener.class,
            new String[]{"ancestorAdded", "ancestorRemoved", "ancestorMoved"},
            "addAncestorListener",
            "removeAncestorListener"
         );
         eventSets[1] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "componentListener",
            ComponentListener.class,
            new String[]{"componentResized", "componentMoved", "componentShown", "componentHidden"},
            "addComponentListener",
            "removeComponentListener"
         );
         eventSets[2] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "containerListener",
            ContainerListener.class,
            new String[]{"componentAdded", "componentRemoved"},
            "addContainerListener",
            "removeContainerListener"
         );
         eventSets[3] = new EventSetDescriptor(
            MultiSpanCellTable.class, "focusListener", FocusListener.class, new String[]{"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener"
         );
         eventSets[4] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "hierarchyBoundsListener",
            HierarchyBoundsListener.class,
            new String[]{"ancestorMoved", "ancestorResized"},
            "addHierarchyBoundsListener",
            "removeHierarchyBoundsListener"
         );
         eventSets[5] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "hierarchyListener",
            HierarchyListener.class,
            new String[]{"hierarchyChanged"},
            "addHierarchyListener",
            "removeHierarchyListener"
         );
         eventSets[6] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "inputMethodListener",
            InputMethodListener.class,
            new String[]{"inputMethodTextChanged", "caretPositionChanged"},
            "addInputMethodListener",
            "removeInputMethodListener"
         );
         eventSets[7] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "keyListener",
            KeyListener.class,
            new String[]{"keyTyped", "keyPressed", "keyReleased"},
            "addKeyListener",
            "removeKeyListener"
         );
         eventSets[8] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "mouseListener",
            MouseListener.class,
            new String[]{"mouseClicked", "mousePressed", "mouseReleased", "mouseEntered", "mouseExited"},
            "addMouseListener",
            "removeMouseListener"
         );
         eventSets[9] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "mouseMotionListener",
            MouseMotionListener.class,
            new String[]{"mouseDragged", "mouseMoved"},
            "addMouseMotionListener",
            "removeMouseMotionListener"
         );
         eventSets[10] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "mouseWheelListener",
            MouseWheelListener.class,
            new String[]{"mouseWheelMoved"},
            "addMouseWheelListener",
            "removeMouseWheelListener"
         );
         eventSets[11] = new EventSetDescriptor(
            MultiSpanCellTable.class,
            "propertyChangeListener",
            PropertyChangeListener.class,
            new String[]{"propertyChange"},
            "addPropertyChangeListener",
            "removePropertyChangeListener"
         );
         eventSets[12] = new EventSetDescriptor(
            MultiSpanCellTable.class,
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
      MethodDescriptor[] methods = new MethodDescriptor[201];

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
         methods[7] = new MethodDescriptor(JTable.class.getMethod("addColumn", TableColumn.class));
         methods[7].setDisplayName("");
         methods[8] = new MethodDescriptor(JTable.class.getMethod("addColumnSelectionInterval", int.class, int.class));
         methods[8].setDisplayName("");
         methods[9] = new MethodDescriptor(JTable.class.getMethod("addNotify"));
         methods[9].setDisplayName("");
         methods[10] = new MethodDescriptor(Container.class.getMethod("addPropertyChangeListener", String.class, PropertyChangeListener.class));
         methods[10].setDisplayName("");
         methods[11] = new MethodDescriptor(JTable.class.getMethod("addRowSelectionInterval", int.class, int.class));
         methods[11].setDisplayName("");
         methods[12] = new MethodDescriptor(Container.class.getMethod("applyComponentOrientation", ComponentOrientation.class));
         methods[12].setDisplayName("");
         methods[13] = new MethodDescriptor(Container.class.getMethod("areFocusTraversalKeysSet", int.class));
         methods[13].setDisplayName("");
         methods[14] = new MethodDescriptor(Component.class.getMethod("bounds"));
         methods[14].setDisplayName("");
         methods[15] = new MethodDescriptor(JTable.class.getMethod("changeSelection", int.class, int.class, boolean.class, boolean.class));
         methods[15].setDisplayName("");
         methods[16] = new MethodDescriptor(Component.class.getMethod("checkImage", Image.class, ImageObserver.class));
         methods[16].setDisplayName("");
         methods[17] = new MethodDescriptor(Component.class.getMethod("checkImage", Image.class, int.class, int.class, ImageObserver.class));
         methods[17].setDisplayName("");
         methods[18] = new MethodDescriptor(JTable.class.getMethod("clearSelection"));
         methods[18].setDisplayName("");
         methods[19] = new MethodDescriptor(JTable.class.getMethod("columnAdded", TableColumnModelEvent.class));
         methods[19].setDisplayName("");
         methods[20] = new MethodDescriptor(MultiSpanCellTable.class.getMethod("columnAtPoint", Point.class));
         methods[20].setDisplayName("");
         methods[21] = new MethodDescriptor(JTable.class.getMethod("columnMarginChanged", ChangeEvent.class));
         methods[21].setDisplayName("");
         methods[22] = new MethodDescriptor(JTable.class.getMethod("columnMoved", TableColumnModelEvent.class));
         methods[22].setDisplayName("");
         methods[23] = new MethodDescriptor(JTable.class.getMethod("columnRemoved", TableColumnModelEvent.class));
         methods[23].setDisplayName("");
         methods[24] = new MethodDescriptor(MultiSpanCellTable.class.getMethod("columnSelectionChanged", ListSelectionEvent.class));
         methods[24].setDisplayName("");
         methods[25] = new MethodDescriptor(JComponent.class.getMethod("computeVisibleRect", Rectangle.class));
         methods[25].setDisplayName("");
         methods[26] = new MethodDescriptor(Component.class.getMethod("contains", Point.class));
         methods[26].setDisplayName("");
         methods[27] = new MethodDescriptor(JComponent.class.getMethod("contains", int.class, int.class));
         methods[27].setDisplayName("");
         methods[28] = new MethodDescriptor(JTable.class.getMethod("convertColumnIndexToModel", int.class));
         methods[28].setDisplayName("");
         methods[29] = new MethodDescriptor(JTable.class.getMethod("convertColumnIndexToView", int.class));
         methods[29].setDisplayName("");
         methods[30] = new MethodDescriptor(JTable.class.getMethod("convertRowIndexToModel", int.class));
         methods[30].setDisplayName("");
         methods[31] = new MethodDescriptor(JTable.class.getMethod("convertRowIndexToView", int.class));
         methods[31].setDisplayName("");
         methods[32] = new MethodDescriptor(Container.class.getMethod("countComponents"));
         methods[32].setDisplayName("");
         methods[33] = new MethodDescriptor(JTable.class.getMethod("createDefaultColumnsFromModel"));
         methods[33].setDisplayName("");
         methods[34] = new MethodDescriptor(Component.class.getMethod("createImage", ImageProducer.class));
         methods[34].setDisplayName("");
         methods[35] = new MethodDescriptor(Component.class.getMethod("createImage", int.class, int.class));
         methods[35].setDisplayName("");
         methods[36] = new MethodDescriptor(JTable.class.getMethod("createScrollPaneForTable", JTable.class));
         methods[36].setDisplayName("");
         methods[37] = new MethodDescriptor(JComponent.class.getMethod("createToolTip"));
         methods[37].setDisplayName("");
         methods[38] = new MethodDescriptor(Component.class.getMethod("createVolatileImage", int.class, int.class));
         methods[38].setDisplayName("");
         methods[39] = new MethodDescriptor(Component.class.getMethod("createVolatileImage", int.class, int.class, ImageCapabilities.class));
         methods[39].setDisplayName("");
         methods[40] = new MethodDescriptor(Container.class.getMethod("deliverEvent", Event.class));
         methods[40].setDisplayName("");
         methods[41] = new MethodDescriptor(JComponent.class.getMethod("disable"));
         methods[41].setDisplayName("");
         methods[42] = new MethodDescriptor(Component.class.getMethod("dispatchEvent", AWTEvent.class));
         methods[42].setDisplayName("");
         methods[43] = new MethodDescriptor(JTable.class.getMethod("doLayout"));
         methods[43].setDisplayName("");
         methods[44] = new MethodDescriptor(JTable.class.getMethod("editCellAt", int.class, int.class));
         methods[44].setDisplayName("");
         methods[45] = new MethodDescriptor(JTable.class.getMethod("editCellAt", int.class, int.class, EventObject.class));
         methods[45].setDisplayName("");
         methods[46] = new MethodDescriptor(JTable.class.getMethod("editingCanceled", ChangeEvent.class));
         methods[46].setDisplayName("");
         methods[47] = new MethodDescriptor(JTable.class.getMethod("editingStopped", ChangeEvent.class));
         methods[47].setDisplayName("");
         methods[48] = new MethodDescriptor(Component.class.getMethod("enable", boolean.class));
         methods[48].setDisplayName("");
         methods[49] = new MethodDescriptor(JComponent.class.getMethod("enable"));
         methods[49].setDisplayName("");
         methods[50] = new MethodDescriptor(Component.class.getMethod("enableInputMethods", boolean.class));
         methods[50].setDisplayName("");
         methods[51] = new MethodDescriptor(Container.class.getMethod("findComponentAt", int.class, int.class));
         methods[51].setDisplayName("");
         methods[52] = new MethodDescriptor(Container.class.getMethod("findComponentAt", Point.class));
         methods[52].setDisplayName("");
         methods[53] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, byte.class, byte.class));
         methods[53].setDisplayName("");
         methods[54] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, short.class, short.class));
         methods[54].setDisplayName("");
         methods[55] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, long.class, long.class));
         methods[55].setDisplayName("");
         methods[56] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, float.class, float.class));
         methods[56].setDisplayName("");
         methods[57] = new MethodDescriptor(Component.class.getMethod("firePropertyChange", String.class, double.class, double.class));
         methods[57].setDisplayName("");
         methods[58] = new MethodDescriptor(JComponent.class.getMethod("firePropertyChange", String.class, boolean.class, boolean.class));
         methods[58].setDisplayName("");
         methods[59] = new MethodDescriptor(JComponent.class.getMethod("firePropertyChange", String.class, int.class, int.class));
         methods[59].setDisplayName("");
         methods[60] = new MethodDescriptor(JComponent.class.getMethod("firePropertyChange", String.class, char.class, char.class));
         methods[60].setDisplayName("");
         methods[61] = new MethodDescriptor(JComponent.class.getMethod("getActionForKeyStroke", KeyStroke.class));
         methods[61].setDisplayName("");
         methods[62] = new MethodDescriptor(JComponent.class.getMethod("getBaseline", int.class, int.class));
         methods[62].setDisplayName("");
         methods[63] = new MethodDescriptor(JComponent.class.getMethod("getBounds", Rectangle.class));
         methods[63].setDisplayName("");
         methods[64] = new MethodDescriptor(JTable.class.getMethod("getCellEditor", int.class, int.class));
         methods[64].setDisplayName("");
         methods[65] = new MethodDescriptor(MultiSpanCellTable.class.getMethod("getCellRect", int.class, int.class, boolean.class));
         methods[65].setDisplayName("");
         methods[66] = new MethodDescriptor(JTable.class.getMethod("getCellRenderer", int.class, int.class));
         methods[66].setDisplayName("");
         methods[67] = new MethodDescriptor(JComponent.class.getMethod("getClientProperty", Object.class));
         methods[67].setDisplayName("");
         methods[68] = new MethodDescriptor(JTable.class.getMethod("getColumn", Object.class));
         methods[68].setDisplayName("");
         methods[69] = new MethodDescriptor(Container.class.getMethod("getComponentAt", int.class, int.class));
         methods[69].setDisplayName("");
         methods[70] = new MethodDescriptor(Container.class.getMethod("getComponentAt", Point.class));
         methods[70].setDisplayName("");
         methods[71] = new MethodDescriptor(Container.class.getMethod("getComponentZOrder", Component.class));
         methods[71].setDisplayName("");
         methods[72] = new MethodDescriptor(JComponent.class.getMethod("getConditionForKeyStroke", KeyStroke.class));
         methods[72].setDisplayName("");
         methods[73] = new MethodDescriptor(JTable.class.getMethod("getDefaultEditor", Class.class));
         methods[73].setDisplayName("");
         methods[74] = new MethodDescriptor(JComponent.class.getMethod("getDefaultLocale"));
         methods[74].setDisplayName("");
         methods[75] = new MethodDescriptor(JTable.class.getMethod("getDefaultRenderer", Class.class));
         methods[75].setDisplayName("");
         methods[76] = new MethodDescriptor(Container.class.getMethod("getFocusTraversalKeys", int.class));
         methods[76].setDisplayName("");
         methods[77] = new MethodDescriptor(JComponent.class.getMethod("getFontMetrics", Font.class));
         methods[77].setDisplayName("");
         methods[78] = new MethodDescriptor(JComponent.class.getMethod("getInsets", Insets.class));
         methods[78].setDisplayName("");
         methods[79] = new MethodDescriptor(JComponent.class.getMethod("getListeners", Class.class));
         methods[79].setDisplayName("");
         methods[80] = new MethodDescriptor(JComponent.class.getMethod("getLocation", Point.class));
         methods[80].setDisplayName("");
         methods[81] = new MethodDescriptor(Container.class.getMethod("getMousePosition", boolean.class));
         methods[81].setDisplayName("");
         methods[82] = new MethodDescriptor(JComponent.class.getMethod("getPopupLocation", MouseEvent.class));
         methods[82].setDisplayName("");
         methods[83] = new MethodDescriptor(JTable.class.getMethod("getPrintable", PrintMode.class, MessageFormat.class, MessageFormat.class));
         methods[83].setDisplayName("");
         methods[84] = new MethodDescriptor(Component.class.getMethod("getPropertyChangeListeners", String.class));
         methods[84].setDisplayName("");
         methods[85] = new MethodDescriptor(JTable.class.getMethod("getScrollableBlockIncrement", Rectangle.class, int.class, int.class));
         methods[85].setDisplayName("");
         methods[86] = new MethodDescriptor(JTable.class.getMethod("getScrollableUnitIncrement", Rectangle.class, int.class, int.class));
         methods[86].setDisplayName("");
         methods[87] = new MethodDescriptor(JComponent.class.getMethod("getSize", Dimension.class));
         methods[87].setDisplayName("");
         methods[88] = new MethodDescriptor(JComponent.class.getMethod("getToolTipLocation", MouseEvent.class));
         methods[88].setDisplayName("");
         methods[89] = new MethodDescriptor(JTable.class.getMethod("getToolTipText", MouseEvent.class));
         methods[89].setDisplayName("");
         methods[90] = new MethodDescriptor(JTable.class.getMethod("getValueAt", int.class, int.class));
         methods[90].setDisplayName("");
         methods[91] = new MethodDescriptor(Component.class.getMethod("gotFocus", Event.class, Object.class));
         methods[91].setDisplayName("");
         methods[92] = new MethodDescriptor(JComponent.class.getMethod("grabFocus"));
         methods[92].setDisplayName("");
         methods[93] = new MethodDescriptor(Component.class.getMethod("handleEvent", Event.class));
         methods[93].setDisplayName("");
         methods[94] = new MethodDescriptor(Component.class.getMethod("hasFocus"));
         methods[94].setDisplayName("");
         methods[95] = new MethodDescriptor(Component.class.getMethod("hide"));
         methods[95].setDisplayName("");
         methods[96] = new MethodDescriptor(Component.class.getMethod("imageUpdate", Image.class, int.class, int.class, int.class, int.class, int.class));
         methods[96].setDisplayName("");
         methods[97] = new MethodDescriptor(Container.class.getMethod("insets"));
         methods[97].setDisplayName("");
         methods[98] = new MethodDescriptor(Component.class.getMethod("inside", int.class, int.class));
         methods[98].setDisplayName("");
         methods[99] = new MethodDescriptor(Container.class.getMethod("invalidate"));
         methods[99].setDisplayName("");
         methods[100] = new MethodDescriptor(Container.class.getMethod("isAncestorOf", Component.class));
         methods[100].setDisplayName("");
         methods[101] = new MethodDescriptor(JTable.class.getMethod("isCellEditable", int.class, int.class));
         methods[101].setDisplayName("");
         methods[102] = new MethodDescriptor(JTable.class.getMethod("isCellSelected", int.class, int.class));
         methods[102].setDisplayName("");
         methods[103] = new MethodDescriptor(JTable.class.getMethod("isColumnSelected", int.class));
         methods[103].setDisplayName("");
         methods[104] = new MethodDescriptor(Container.class.getMethod("isFocusCycleRoot", Container.class));
         methods[104].setDisplayName("");
         methods[105] = new MethodDescriptor(JComponent.class.getMethod("isLightweightComponent", Component.class));
         methods[105].setDisplayName("");
         methods[106] = new MethodDescriptor(JTable.class.getMethod("isRowSelected", int.class));
         methods[106].setDisplayName("");
         methods[107] = new MethodDescriptor(Component.class.getMethod("keyDown", Event.class, int.class));
         methods[107].setDisplayName("");
         methods[108] = new MethodDescriptor(Component.class.getMethod("keyUp", Event.class, int.class));
         methods[108].setDisplayName("");
         methods[109] = new MethodDescriptor(Container.class.getMethod("layout"));
         methods[109].setDisplayName("");
         methods[110] = new MethodDescriptor(Component.class.getMethod("list"));
         methods[110].setDisplayName("");
         methods[111] = new MethodDescriptor(Component.class.getMethod("list", PrintStream.class));
         methods[111].setDisplayName("");
         methods[112] = new MethodDescriptor(Component.class.getMethod("list", PrintWriter.class));
         methods[112].setDisplayName("");
         methods[113] = new MethodDescriptor(Container.class.getMethod("list", PrintStream.class, int.class));
         methods[113].setDisplayName("");
         methods[114] = new MethodDescriptor(Container.class.getMethod("list", PrintWriter.class, int.class));
         methods[114].setDisplayName("");
         methods[115] = new MethodDescriptor(Container.class.getMethod("locate", int.class, int.class));
         methods[115].setDisplayName("");
         methods[116] = new MethodDescriptor(Component.class.getMethod("location"));
         methods[116].setDisplayName("");
         methods[117] = new MethodDescriptor(Component.class.getMethod("lostFocus", Event.class, Object.class));
         methods[117].setDisplayName("");
         methods[118] = new MethodDescriptor(Container.class.getMethod("minimumSize"));
         methods[118].setDisplayName("");
         methods[119] = new MethodDescriptor(Component.class.getMethod("mouseDown", Event.class, int.class, int.class));
         methods[119].setDisplayName("");
         methods[120] = new MethodDescriptor(Component.class.getMethod("mouseDrag", Event.class, int.class, int.class));
         methods[120].setDisplayName("");
         methods[121] = new MethodDescriptor(Component.class.getMethod("mouseEnter", Event.class, int.class, int.class));
         methods[121].setDisplayName("");
         methods[122] = new MethodDescriptor(Component.class.getMethod("mouseExit", Event.class, int.class, int.class));
         methods[122].setDisplayName("");
         methods[123] = new MethodDescriptor(Component.class.getMethod("mouseMove", Event.class, int.class, int.class));
         methods[123].setDisplayName("");
         methods[124] = new MethodDescriptor(Component.class.getMethod("mouseUp", Event.class, int.class, int.class));
         methods[124].setDisplayName("");
         methods[125] = new MethodDescriptor(Component.class.getMethod("move", int.class, int.class));
         methods[125].setDisplayName("");
         methods[126] = new MethodDescriptor(JTable.class.getMethod("moveColumn", int.class, int.class));
         methods[126].setDisplayName("");
         methods[127] = new MethodDescriptor(Component.class.getMethod("nextFocus"));
         methods[127].setDisplayName("");
         methods[128] = new MethodDescriptor(JComponent.class.getMethod("paint", Graphics.class));
         methods[128].setDisplayName("");
         methods[129] = new MethodDescriptor(Component.class.getMethod("paintAll", Graphics.class));
         methods[129].setDisplayName("");
         methods[130] = new MethodDescriptor(Container.class.getMethod("paintComponents", Graphics.class));
         methods[130].setDisplayName("");
         methods[131] = new MethodDescriptor(JComponent.class.getMethod("paintImmediately", int.class, int.class, int.class, int.class));
         methods[131].setDisplayName("");
         methods[132] = new MethodDescriptor(JComponent.class.getMethod("paintImmediately", Rectangle.class));
         methods[132].setDisplayName("");
         methods[133] = new MethodDescriptor(Component.class.getMethod("postEvent", Event.class));
         methods[133].setDisplayName("");
         methods[134] = new MethodDescriptor(Container.class.getMethod("preferredSize"));
         methods[134].setDisplayName("");
         methods[135] = new MethodDescriptor(JTable.class.getMethod("prepareEditor", TableCellEditor.class, int.class, int.class));
         methods[135].setDisplayName("");
         methods[136] = new MethodDescriptor(Component.class.getMethod("prepareImage", Image.class, ImageObserver.class));
         methods[136].setDisplayName("");
         methods[137] = new MethodDescriptor(Component.class.getMethod("prepareImage", Image.class, int.class, int.class, ImageObserver.class));
         methods[137].setDisplayName("");
         methods[138] = new MethodDescriptor(JTable.class.getMethod("prepareRenderer", TableCellRenderer.class, int.class, int.class));
         methods[138].setDisplayName("");
         methods[139] = new MethodDescriptor(JComponent.class.getMethod("print", Graphics.class));
         methods[139].setDisplayName("");
         methods[140] = new MethodDescriptor(JTable.class.getMethod("print"));
         methods[140].setDisplayName("");
         methods[141] = new MethodDescriptor(JTable.class.getMethod("print", PrintMode.class));
         methods[141].setDisplayName("");
         methods[142] = new MethodDescriptor(JTable.class.getMethod("print", PrintMode.class, MessageFormat.class, MessageFormat.class));
         methods[142].setDisplayName("");
         methods[143] = new MethodDescriptor(
            JTable.class
               .getMethod("print", PrintMode.class, MessageFormat.class, MessageFormat.class, boolean.class, PrintRequestAttributeSet.class, boolean.class)
         );
         methods[143].setDisplayName("");
         methods[144] = new MethodDescriptor(
            JTable.class
               .getMethod(
                  "print",
                  PrintMode.class,
                  MessageFormat.class,
                  MessageFormat.class,
                  boolean.class,
                  PrintRequestAttributeSet.class,
                  boolean.class,
                  PrintService.class
               )
         );
         methods[144].setDisplayName("");
         methods[145] = new MethodDescriptor(JComponent.class.getMethod("printAll", Graphics.class));
         methods[145].setDisplayName("");
         methods[146] = new MethodDescriptor(Container.class.getMethod("printComponents", Graphics.class));
         methods[146].setDisplayName("");
         methods[147] = new MethodDescriptor(JComponent.class.getMethod("putClientProperty", Object.class, Object.class));
         methods[147].setDisplayName("");
         methods[148] = new MethodDescriptor(
            JComponent.class.getMethod("registerKeyboardAction", ActionListener.class, String.class, KeyStroke.class, int.class)
         );
         methods[148].setDisplayName("");
         methods[149] = new MethodDescriptor(JComponent.class.getMethod("registerKeyboardAction", ActionListener.class, KeyStroke.class, int.class));
         methods[149].setDisplayName("");
         methods[150] = new MethodDescriptor(Component.class.getMethod("remove", MenuComponent.class));
         methods[150].setDisplayName("");
         methods[151] = new MethodDescriptor(Container.class.getMethod("remove", int.class));
         methods[151].setDisplayName("");
         methods[152] = new MethodDescriptor(Container.class.getMethod("remove", Component.class));
         methods[152].setDisplayName("");
         methods[153] = new MethodDescriptor(Container.class.getMethod("removeAll"));
         methods[153].setDisplayName("");
         methods[154] = new MethodDescriptor(JTable.class.getMethod("removeColumn", TableColumn.class));
         methods[154].setDisplayName("");
         methods[155] = new MethodDescriptor(JTable.class.getMethod("removeColumnSelectionInterval", int.class, int.class));
         methods[155].setDisplayName("");
         methods[156] = new MethodDescriptor(JTable.class.getMethod("removeEditor"));
         methods[156].setDisplayName("");
         methods[157] = new MethodDescriptor(JTable.class.getMethod("removeNotify"));
         methods[157].setDisplayName("");
         methods[158] = new MethodDescriptor(Component.class.getMethod("removePropertyChangeListener", String.class, PropertyChangeListener.class));
         methods[158].setDisplayName("");
         methods[159] = new MethodDescriptor(JTable.class.getMethod("removeRowSelectionInterval", int.class, int.class));
         methods[159].setDisplayName("");
         methods[160] = new MethodDescriptor(Component.class.getMethod("repaint"));
         methods[160].setDisplayName("");
         methods[161] = new MethodDescriptor(Component.class.getMethod("repaint", long.class));
         methods[161].setDisplayName("");
         methods[162] = new MethodDescriptor(Component.class.getMethod("repaint", int.class, int.class, int.class, int.class));
         methods[162].setDisplayName("");
         methods[163] = new MethodDescriptor(JComponent.class.getMethod("repaint", long.class, int.class, int.class, int.class, int.class));
         methods[163].setDisplayName("");
         methods[164] = new MethodDescriptor(JComponent.class.getMethod("repaint", Rectangle.class));
         methods[164].setDisplayName("");
         methods[165] = new MethodDescriptor(JComponent.class.getMethod("requestDefaultFocus"));
         methods[165].setDisplayName("");
         methods[166] = new MethodDescriptor(JComponent.class.getMethod("requestFocus"));
         methods[166].setDisplayName("");
         methods[167] = new MethodDescriptor(JComponent.class.getMethod("requestFocus", boolean.class));
         methods[167].setDisplayName("");
         methods[168] = new MethodDescriptor(JComponent.class.getMethod("requestFocusInWindow"));
         methods[168].setDisplayName("");
         methods[169] = new MethodDescriptor(JComponent.class.getMethod("resetKeyboardActions"));
         methods[169].setDisplayName("");
         methods[170] = new MethodDescriptor(JComponent.class.getMethod("reshape", int.class, int.class, int.class, int.class));
         methods[170].setDisplayName("");
         methods[171] = new MethodDescriptor(Component.class.getMethod("resize", int.class, int.class));
         methods[171].setDisplayName("");
         methods[172] = new MethodDescriptor(Component.class.getMethod("resize", Dimension.class));
         methods[172].setDisplayName("");
         methods[173] = new MethodDescriptor(JComponent.class.getMethod("revalidate"));
         methods[173].setDisplayName("");
         methods[174] = new MethodDescriptor(MultiSpanCellTable.class.getMethod("rowAtPoint", Point.class));
         methods[174].setDisplayName("");
         methods[175] = new MethodDescriptor(JComponent.class.getMethod("scrollRectToVisible", Rectangle.class));
         methods[175].setDisplayName("");
         methods[176] = new MethodDescriptor(JTable.class.getMethod("selectAll"));
         methods[176].setDisplayName("");
         methods[177] = new MethodDescriptor(Component.class.getMethod("setBounds", int.class, int.class, int.class, int.class));
         methods[177].setDisplayName("");
         methods[178] = new MethodDescriptor(Container.class.getMethod("setComponentZOrder", Component.class, int.class));
         methods[178].setDisplayName("");
         methods[179] = new MethodDescriptor(JTable.class.getMethod("setDefaultEditor", Class.class, TableCellEditor.class));
         methods[179].setDisplayName("");
         methods[180] = new MethodDescriptor(JComponent.class.getMethod("setDefaultLocale", Locale.class));
         methods[180].setDisplayName("");
         methods[181] = new MethodDescriptor(JTable.class.getMethod("setDefaultRenderer", Class.class, TableCellRenderer.class));
         methods[181].setDisplayName("");
         methods[182] = new MethodDescriptor(JTable.class.getMethod("setRowHeight", int.class, int.class));
         methods[182].setDisplayName("");
         methods[183] = new MethodDescriptor(JTable.class.getMethod("setValueAt", Object.class, int.class, int.class));
         methods[183].setDisplayName("");
         methods[184] = new MethodDescriptor(Component.class.getMethod("show"));
         methods[184].setDisplayName("");
         methods[185] = new MethodDescriptor(Component.class.getMethod("show", boolean.class));
         methods[185].setDisplayName("");
         methods[186] = new MethodDescriptor(Component.class.getMethod("size"));
         methods[186].setDisplayName("");
         methods[187] = new MethodDescriptor(JTable.class.getMethod("sizeColumnsToFit", boolean.class));
         methods[187].setDisplayName("");
         methods[188] = new MethodDescriptor(JTable.class.getMethod("sizeColumnsToFit", int.class));
         methods[188].setDisplayName("");
         methods[189] = new MethodDescriptor(JTable.class.getMethod("sorterChanged", RowSorterEvent.class));
         methods[189].setDisplayName("");
         methods[190] = new MethodDescriptor(JTable.class.getMethod("tableChanged", TableModelEvent.class));
         methods[190].setDisplayName("");
         methods[191] = new MethodDescriptor(Component.class.getMethod("toString"));
         methods[191].setDisplayName("");
         methods[192] = new MethodDescriptor(Component.class.getMethod("transferFocus"));
         methods[192].setDisplayName("");
         methods[193] = new MethodDescriptor(Container.class.getMethod("transferFocusBackward"));
         methods[193].setDisplayName("");
         methods[194] = new MethodDescriptor(Container.class.getMethod("transferFocusDownCycle"));
         methods[194].setDisplayName("");
         methods[195] = new MethodDescriptor(Component.class.getMethod("transferFocusUpCycle"));
         methods[195].setDisplayName("");
         methods[196] = new MethodDescriptor(JComponent.class.getMethod("unregisterKeyboardAction", KeyStroke.class));
         methods[196].setDisplayName("");
         methods[197] = new MethodDescriptor(JComponent.class.getMethod("update", Graphics.class));
         methods[197].setDisplayName("");
         methods[198] = new MethodDescriptor(JTable.class.getMethod("updateUI"));
         methods[198].setDisplayName("");
         methods[199] = new MethodDescriptor(Container.class.getMethod("validate"));
         methods[199].setDisplayName("");
         methods[200] = new MethodDescriptor(MultiSpanCellTable.class.getMethod("valueChanged", ListSelectionEvent.class));
         methods[200].setDisplayName("");
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
