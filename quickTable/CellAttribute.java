package quickTable;

import java.awt.Dimension;

public interface CellAttribute {
   void addColumn();

   void addRow();

   void insertRow(int var1);

   Dimension getSize();

   void setSize(Dimension var1);
}
