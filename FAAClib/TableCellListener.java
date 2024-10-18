package FAAClib;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class TableCellListener implements PropertyChangeListener, Runnable {
   private JTable table;
   private Action action;
   private int row;
   private int column;
   private Object oldValue;
   private Object newValue;

   public TableCellListener(JTable table, Action action) {
      this.table = table;
      this.action = action;
      this.table.addPropertyChangeListener(this);
   }

   private TableCellListener(JTable table, int row, int column, Object oldValue, Object newValue) {
      this.table = table;
      this.row = row;
      this.column = column;
      this.oldValue = oldValue;
      this.newValue = newValue;
   }

   public int getColumn() {
      return this.column;
   }

   public Object getNewValue() {
      return this.newValue;
   }

   public Object getOldValue() {
      return this.oldValue;
   }

   public int getRow() {
      return this.row;
   }

   public JTable getTable() {
      return this.table;
   }

   @Override
   public void propertyChange(PropertyChangeEvent e) {
      if ("tableCellEditor".equals(e.getPropertyName())) {
         if (this.table.isEditing()) {
            this.processEditingStarted();
         } else {
            this.processEditingStopped();
         }
      }
   }

   private void processEditingStarted() {
      SwingUtilities.invokeLater(this);
   }

   @Override
   public void run() {
      this.row = this.table.convertRowIndexToModel(this.table.getEditingRow());
      this.column = this.table.convertColumnIndexToModel(this.table.getEditingColumn());
      this.oldValue = this.table.getModel().getValueAt(this.row, this.column);
      this.newValue = null;
   }

   private void processEditingStopped() {
      this.newValue = this.table.getModel().getValueAt(this.row, this.column);
      if (!this.newValue.equals(this.oldValue)) {
         TableCellListener tcl = new TableCellListener(this.getTable(), this.getRow(), this.getColumn(), this.getOldValue(), this.getNewValue());
         ActionEvent event = new ActionEvent(tcl, 1001, "");
         this.action.actionPerformed(event);
      }
   }
}
