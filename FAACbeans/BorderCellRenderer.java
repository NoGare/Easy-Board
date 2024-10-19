package FAACbeans;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class BorderCellRenderer extends JLabel implements TableCellRenderer {
   protected Border noFocusBorder = new EmptyBorder(1, 2, 1, 2);
   protected Border columnBorder;

   public BorderCellRenderer() {
      this.setOpaque(true);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      if (isSelected) {
         this.setForeground(table.getSelectionForeground());
         this.setBackground(table.getSelectionBackground());
      } else {
         this.setForeground(table.getForeground());
         this.setBackground(table.getBackground());
      }

      this.setFont(table.getFont());
      if (hasFocus) {
         this.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
         if (table.isCellEditable(row, column)) {
            this.setForeground(UIManager.getColor("Table.focusCellForeground"));
            this.setBackground(UIManager.getColor("Table.focusCellBackground"));
         }
      } else if (value instanceof CellBorder) {
         Border border = ((CellBorder)value).getBorder();
         this.setBorder(border);
         Color bg = ((CellBorder)value).getBackgroundColor();
         if (bg != null) {
            this.setBackground(bg);
         }
      } else if (this.columnBorder != null) {
         this.setBorder(this.columnBorder);
      } else {
         this.setBorder(this.noFocusBorder);
      }

      this.setText(value == null ? "" : value.toString());
      return this;
   }

   public void setColumnBorder(Border border) {
      this.columnBorder = border;
   }

   public Border getColumnBorder() {
      return this.columnBorder;
   }
}
