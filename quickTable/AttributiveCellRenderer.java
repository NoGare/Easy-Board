package quickTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class AttributiveCellRenderer extends JLabel implements TableCellRenderer {
   protected static Border noFocusBorder;

   public AttributiveCellRenderer() {
      noFocusBorder = new EmptyBorder(1, 2, 1, 2);
      this.setOpaque(true);
      this.setBorder(noFocusBorder);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      Color foreground = null;
      Color background = null;
      Font font = null;
      TableModel model = table.getModel();
      if (model instanceof AttributiveCellTableModel) {
         CellAttribute cellAtt = ((AttributiveCellTableModel)model).getCellAttribute();
         if (cellAtt instanceof ColoredCell) {
            foreground = ((ColoredCell)cellAtt).getForeground(row, column);
            background = ((ColoredCell)cellAtt).getBackground(row, column);
         }

         if (cellAtt instanceof CellFont) {
            font = ((CellFont)cellAtt).getFont(row, column);
         }
      }

      if (isSelected) {
         this.setForeground(foreground != null ? foreground : table.getSelectionForeground());
         this.setBackground(table.getSelectionBackground());
      } else {
         this.setForeground(foreground != null ? foreground : table.getForeground());
         this.setBackground(background != null ? background : table.getBackground());
      }

      this.setFont(font != null ? font : table.getFont());
      if (hasFocus) {
         this.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
         if (table.isCellEditable(row, column)) {
            this.setForeground(foreground != null ? foreground : UIManager.getColor("Table.focusCellForeground"));
            this.setBackground(table.getSelectionBackground());
         }
      } else {
         this.setBorder(noFocusBorder);
      }

      this.setValue(value);
      return this;
   }

   protected void setValue(Object value) {
      this.setText(value == null ? "" : value.toString());
   }
}
