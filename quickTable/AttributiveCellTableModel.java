package quickTable;

import java.awt.Dimension;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class AttributiveCellTableModel extends DefaultTableModel {
   protected CellAttribute cellAtt;

   public AttributiveCellTableModel() {
      this((Vector)null, 0);
   }

   public AttributiveCellTableModel(int numRows, int numColumns) {
      Vector names = new Vector(numColumns);
      names.setSize(numColumns);
      this.setColumnIdentifiers(names);
      this.dataVector = new Vector<>(numRows);
      this.dataVector.setSize(numRows);
      this.setNumRows(numRows);
      this.cellAtt = new DefaultCellAttribute(numRows, numColumns);
   }

   public AttributiveCellTableModel(Vector columnNames, int numRows) {
      this.setColumnIdentifiers(columnNames);
      this.dataVector = new Vector<>();
      this.setNumRows(numRows);
      this.cellAtt = new DefaultCellAttribute(numRows, columnNames.size());
   }

   public AttributiveCellTableModel(Object[] columnNames, int numRows) {
      this(convertToVector(columnNames), numRows);
   }

   public AttributiveCellTableModel(Vector data, Vector columnNames) {
      super.setDataVector(data, columnNames);
      this.mySetDataVector(data, columnNames);
   }

   public AttributiveCellTableModel(Object[][] data, Object[] columnNames) {
      super.setDataVector(data, columnNames);
      this.doChange(data.length, columnNames.length);
   }

   protected void mySetDataVector(Vector newData, Vector columnNames) {
      if (newData == null) {
         throw new IllegalArgumentException("setDataVector() - Null parameter");
      } else {
         if (columnNames != null) {
            this.setColumnIdentifiers(columnNames);
         }

         this.dataVector = newData;
         this.doChange(this.dataVector.size(), this.columnIdentifiers.size());
      }
   }

   protected void doChange(int dataSize, int columnSize) {
      this.cellAtt = new DefaultCellAttribute(dataSize, columnSize);
      this.newRowsAdded(new TableModelEvent(this, 0, this.getRowCount() - 1, -1, 1));
   }

   @Override
   public void addColumn(Object columnName, Vector columnData) {
      if (columnName == null) {
         throw new IllegalArgumentException("addColumn() - null parameter");
      } else {
         this.columnIdentifiers.addElement(columnName);
         int index = 0;

         for (Enumeration enumeration = this.dataVector.elements(); enumeration.hasMoreElements(); index++) {
            Object value;
            if (columnData != null && index < columnData.size()) {
               value = columnData.elementAt(index);
            } else {
               value = null;
            }

            ((Vector)enumeration.nextElement()).addElement(value);
         }

         this.cellAtt.addColumn();
         this.fireTableStructureChanged();
      }
   }

   @Override
   public void addRow(Vector rowData) {
      Vector newData = null;
      if (rowData == null) {
         newData = new Vector(this.getColumnCount());
      } else {
         rowData.setSize(this.getColumnCount());
      }

      this.dataVector.addElement(newData);
      this.cellAtt.addRow();
      this.newRowsAdded(new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, -1, 1));
   }

   @Override
   public void insertRow(int row, Vector rowData) {
      if (rowData == null) {
         rowData = new Vector(this.getColumnCount());
      } else {
         rowData.setSize(this.getColumnCount());
      }

      this.dataVector.insertElementAt(rowData, row);
      this.cellAtt.insertRow(row);
      this.newRowsAdded(new TableModelEvent(this, row, row, -1, 1));
   }

   public CellAttribute getCellAttribute() {
      return this.cellAtt;
   }

   public void setCellAttribute(CellAttribute newCellAtt) {
      int numColumns = this.getColumnCount();
      int numRows = this.getRowCount();
      if (newCellAtt.getSize().width != numColumns || newCellAtt.getSize().height != numRows) {
         newCellAtt.setSize(new Dimension(numRows, numColumns));
      }

      this.cellAtt = newCellAtt;
      this.fireTableDataChanged();
   }
}
