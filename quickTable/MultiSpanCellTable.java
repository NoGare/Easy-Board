package quickTable;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Enumeration;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class MultiSpanCellTable extends JTable {
   public MultiSpanCellTable() {
      this.setUI(new MultiSpanCellTableUI());
      this.getTableHeader().setReorderingAllowed(false);
      this.setCellSelectionEnabled(true);
      this.setSelectionMode(1);
   }

   public MultiSpanCellTable(TableModel model) {
      super(model);
      this.setUI(new MultiSpanCellTableUI());
      this.getTableHeader().setReorderingAllowed(false);
      this.setCellSelectionEnabled(true);
      this.setSelectionMode(1);
   }

   @Override
   public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
      Rectangle sRect = super.getCellRect(row, column, includeSpacing);
      if (row >= 0 && column >= 0 && this.getRowCount() > row && this.getColumnCount() > column) {
         CellSpan cellAtt = (CellSpan)((AttributiveCellTableModel)this.getModel()).getCellAttribute();
         if (!cellAtt.isVisible(row, column)) {
            int temp_row = row;
            row += cellAtt.getSpan(row, column)[0];
            column += cellAtt.getSpan(temp_row, column)[1];
         }

         int[] n = cellAtt.getSpan(row, column);
         int index = 0;
         int columnMargin = this.getColumnModel().getColumnMargin() - 1;
         Rectangle cellFrame = new Rectangle();
         int aCellHeight = this.rowHeight + this.rowMargin;
         cellFrame.y = row * aCellHeight;
         cellFrame.height = n[0] * aCellHeight;

         Enumeration enumeration;
         for (enumeration = this.getColumnModel().getColumns(); enumeration.hasMoreElements(); index++) {
            TableColumn aColumn = (TableColumn)enumeration.nextElement();
            cellFrame.width = aColumn.getWidth() + columnMargin;
            if (index == column) {
               break;
            }

            cellFrame.x = cellFrame.x + cellFrame.width;
         }

         for (int i = 0; i < n[1] - 1; i++) {
            TableColumn aColumn = (TableColumn)enumeration.nextElement();
            cellFrame.width = cellFrame.width + aColumn.getWidth() + columnMargin;
         }

         if (!includeSpacing) {
            Dimension spacing = this.getIntercellSpacing();
            cellFrame.setBounds(
               cellFrame.x + spacing.width / 2, cellFrame.y + spacing.height / 2, cellFrame.width - spacing.width, cellFrame.height - spacing.height
            );
         }

         return cellFrame;
      } else {
         return sRect;
      }
   }

   private int[] rowColumnAtPoint(Point point) {
      int[] retValue = new int[]{-1, -1};
      int row = point.y / (this.rowHeight + this.rowMargin);
      if (row >= 0 && this.getRowCount() > row) {
         int column = this.getColumnModel().getColumnIndexAtX(point.x);
         CellSpan cellAtt = (CellSpan)((AttributiveCellTableModel)this.getModel()).getCellAttribute();
         if (cellAtt.isVisible(row, column)) {
            retValue[1] = column;
            retValue[0] = row;
            return retValue;
         } else {
            retValue[1] = column + cellAtt.getSpan(row, column)[1];
            retValue[0] = row + cellAtt.getSpan(row, column)[0];
            return retValue;
         }
      } else {
         return retValue;
      }
   }

   @Override
   public int rowAtPoint(Point point) {
      return this.rowColumnAtPoint(point)[0];
   }

   @Override
   public int columnAtPoint(Point point) {
      return this.rowColumnAtPoint(point)[1];
   }

   @Override
   public void columnSelectionChanged(ListSelectionEvent e) {
      this.repaint();
   }

   @Override
   public void valueChanged(ListSelectionEvent e) {
      int firstIndex = e.getFirstIndex();
      int lastIndex = e.getLastIndex();
      if (firstIndex == -1 && lastIndex == -1) {
         this.repaint();
      }

      Rectangle dirtyRegion = this.getCellRect(firstIndex, 0, false);
      int numCoumns = this.getColumnCount();
      int index = firstIndex;

      for (int i = 0; i < numCoumns; i++) {
         dirtyRegion.add(this.getCellRect(index, i, false));
      }

      index = lastIndex;

      for (int i = 0; i < numCoumns; i++) {
         dirtyRegion.add(this.getCellRect(index, i, false));
      }

      this.repaint(dirtyRegion.x, dirtyRegion.y, dirtyRegion.width, dirtyRegion.height);
   }
}
