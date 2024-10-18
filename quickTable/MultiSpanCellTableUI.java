package quickTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.TableCellRenderer;

public class MultiSpanCellTableUI extends BasicTableUI {
   @Override
   public void paint(Graphics g, JComponent c) {
      Rectangle oldClipBounds = g.getClipBounds();
      Rectangle clipBounds = new Rectangle(oldClipBounds);
      int tableWidth = this.table.getColumnModel().getTotalColumnWidth();
      clipBounds.width = Math.min(clipBounds.width, tableWidth);
      g.setClip(clipBounds);
      int firstIndex = this.table.rowAtPoint(new Point(0, clipBounds.y));
      int lastIndex = this.table.getRowCount() - 1;
      Rectangle rowRect = new Rectangle(0, 0, tableWidth, this.table.getRowHeight() + this.table.getRowMargin());
      rowRect.y = firstIndex * rowRect.height;

      for (int index = firstIndex; index <= lastIndex; index++) {
         if (rowRect.intersects(clipBounds)) {
            this.paintRow(g, index);
         }

         rowRect.y = rowRect.y + rowRect.height;
      }

      g.setClip(oldClipBounds);
   }

   private void paintRow(Graphics g, int row) {
      Rectangle rect = g.getClipBounds();
      boolean drawn = false;
      AttributiveCellTableModel tableModel = (AttributiveCellTableModel)this.table.getModel();
      CellSpan cellAtt = (CellSpan)tableModel.getCellAttribute();
      int numColumns = this.table.getColumnCount();

      for (int column = 0; column < numColumns; column++) {
         Rectangle cellRect = this.table.getCellRect(row, column, true);
         int cellRow;
         int cellColumn;
         if (cellAtt.isVisible(row, column)) {
            cellRow = row;
            cellColumn = column;
         } else {
            cellRow = row + cellAtt.getSpan(row, column)[0];
            cellColumn = column + cellAtt.getSpan(row, column)[1];
         }

         if (cellRect.intersects(rect)) {
            drawn = true;
            this.paintCell(g, cellRect, cellRow, cellColumn);
         } else if (drawn) {
            break;
         }
      }
   }

   private void paintCell(Graphics g, Rectangle cellRect, int row, int column) {
      int spacingHeight = this.table.getRowMargin();
      int spacingWidth = this.table.getColumnModel().getColumnMargin();
      Color c = g.getColor();
      g.setColor(this.table.getGridColor());
      g.drawRect(cellRect.x, cellRect.y, cellRect.width - 1, cellRect.height - 1);
      g.setColor(c);
      cellRect.setBounds(cellRect.x + spacingWidth / 2, cellRect.y + spacingHeight / 2, cellRect.width - spacingWidth, cellRect.height - spacingHeight);
      if (this.table.isEditing() && this.table.getEditingRow() == row && this.table.getEditingColumn() == column) {
         Component component = this.table.getEditorComponent();
         component.setBounds(cellRect);
         component.validate();
      } else {
         TableCellRenderer renderer = this.table.getCellRenderer(row, column);
         Component component = this.table.prepareRenderer(renderer, row, column);
         if (component.getParent() == null) {
            this.rendererPane.add(component);
         }

         this.rendererPane.paintComponent(g, component, this.table, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
      }
   }
}
