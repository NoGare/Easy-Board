package quickTable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class DefaultCellAttribute implements CellAttribute, CellSpan, ColoredCell, CellFont {
   protected int rowSize;
   protected int columnSize;
   protected int[][][] span;
   protected Color[][] foreground;
   protected Color[][] background;
   protected Font[][] font;

   public DefaultCellAttribute() {
      this(1, 1);
   }

   public DefaultCellAttribute(int numRows, int numColumns) {
      this.setSize(new Dimension(numColumns, numRows));
   }

   protected void initValue() {
      for (int i = 0; i < this.span.length; i++) {
         for (int j = 0; j < this.span[i].length; j++) {
            this.span[i][j][1] = 1;
            this.span[i][j][0] = 1;
         }
      }
   }

   @Override
   public int[] getSpan(int row, int column) {
      return this.isOutOfBounds(row, column) ? new int[]{1, 1} : this.span[row][column];
   }

   @Override
   public void setSpan(int[] span, int row, int column) {
      if (!this.isOutOfBounds(row, column)) {
         this.span[row][column] = span;
      }
   }

   @Override
   public boolean isVisible(int row, int column) {
      return this.isOutOfBounds(row, column) ? false : this.span[row][column][1] >= 1 && this.span[row][column][0] >= 1;
   }

   @Override
   public void combine(int[] rows, int[] columns) {
      if (!this.isOutOfBounds(rows, columns)) {
         int rowSpan = rows.length;
         int columnSpan = columns.length;
         int startRow = rows[0];
         int startColumn = columns[0];

         for (int i = 0; i < rowSpan; i++) {
            for (int j = 0; j < columnSpan; j++) {
               if (this.span[startRow + i][startColumn + j][1] != 1 || this.span[startRow + i][startColumn + j][0] != 1) {
                  System.out.println("can't combine");
                  return;
               }
            }
         }

         int i = 0;

         for (int ii = 0; i < rowSpan; ii--) {
            int jx = 0;

            for (int jj = 0; jx < columnSpan; jj--) {
               this.span[startRow + i][startColumn + jx][1] = jj;
               this.span[startRow + i][startColumn + jx][0] = ii;
               jx++;
            }

            i++;
         }

         this.span[startRow][startColumn][1] = columnSpan;
         this.span[startRow][startColumn][0] = rowSpan;
      }
   }

   @Override
   public void split(int row, int column) {
      if (!this.isOutOfBounds(row, column)) {
         int columnSpan = this.span[row][column][1];
         int rowSpan = this.span[row][column][0];

         for (int i = 0; i < rowSpan; i++) {
            for (int j = 0; j < columnSpan; j++) {
               this.span[row + i][column + j][1] = 1;
               this.span[row + i][column + j][0] = 1;
            }
         }
      }
   }

   @Override
   public Color getForeground(int row, int column) {
      return this.isOutOfBounds(row, column) ? null : this.foreground[row][column];
   }

   @Override
   public void setForeground(Color color, int row, int column) {
      if (!this.isOutOfBounds(row, column)) {
         this.foreground[row][column] = color;
      }
   }

   @Override
   public void setForeground(Color color, int[] rows, int[] columns) {
      if (!this.isOutOfBounds(rows, columns)) {
         this.setValues(this.foreground, color, rows, columns);
      }
   }

   @Override
   public Color getBackground(int row, int column) {
      return this.isOutOfBounds(row, column) ? null : this.background[row][column];
   }

   @Override
   public void setBackground(Color color, int row, int column) {
      if (!this.isOutOfBounds(row, column)) {
         this.background[row][column] = color;
      }
   }

   @Override
   public void setBackground(Color color, int[] rows, int[] columns) {
      if (!this.isOutOfBounds(rows, columns)) {
         this.setValues(this.background, color, rows, columns);
      }
   }

   @Override
   public Font getFont(int row, int column) {
      return this.isOutOfBounds(row, column) ? null : this.font[row][column];
   }

   @Override
   public void setFont(Font font, int row, int column) {
      if (!this.isOutOfBounds(row, column)) {
         this.font[row][column] = font;
      }
   }

   @Override
   public void setFont(Font font, int[] rows, int[] columns) {
      if (!this.isOutOfBounds(rows, columns)) {
         this.setValues(this.font, font, rows, columns);
      }
   }

   @Override
   public void addColumn() {
      int[][][] oldSpan = this.span;
      int numRows = oldSpan.length;
      int numColumns = oldSpan[0].length;
      this.span = new int[numRows][numColumns + 1][2];
      System.arraycopy(oldSpan, 0, this.span, 0, numRows);

      for (int i = 0; i < numRows; i++) {
         this.span[i][numColumns][1] = 1;
         this.span[i][numColumns][0] = 1;
      }
   }

   @Override
   public void addRow() {
      int[][][] oldSpan = this.span;
      int numRows = oldSpan.length;
      int numColumns = oldSpan[0].length;
      this.span = new int[numRows + 1][numColumns][2];
      System.arraycopy(oldSpan, 0, this.span, 0, numRows);

      for (int i = 0; i < numColumns; i++) {
         this.span[numRows][i][1] = 1;
         this.span[numRows][i][0] = 1;
      }
   }

   @Override
   public void insertRow(int row) {
      int[][][] oldSpan = this.span;
      int numRows = oldSpan.length;
      int numColumns = oldSpan[0].length;
      this.span = new int[numRows + 1][numColumns][2];
      if (0 < row) {
         System.arraycopy(oldSpan, 0, this.span, 0, row - 1);
      }

      System.arraycopy(oldSpan, 0, this.span, row, numRows - row);

      for (int i = 0; i < numColumns; i++) {
         this.span[row][i][1] = 1;
         this.span[row][i][0] = 1;
      }
   }

   @Override
   public Dimension getSize() {
      return new Dimension(this.rowSize, this.columnSize);
   }

   @Override
   public void setSize(Dimension size) {
      this.columnSize = size.width;
      this.rowSize = size.height;
      this.span = new int[this.rowSize][this.columnSize][2];
      this.foreground = new Color[this.rowSize][this.columnSize];
      this.background = new Color[this.rowSize][this.columnSize];
      this.font = new Font[this.rowSize][this.columnSize];
      this.initValue();
   }

   protected boolean isOutOfBounds(int row, int column) {
      return row < 0 || this.rowSize <= row || column < 0 || this.columnSize <= column;
   }

   protected boolean isOutOfBounds(int[] rows, int[] columns) {
      for (int i = 0; i < rows.length; i++) {
         if (rows[i] < 0 || this.rowSize <= rows[i]) {
            return true;
         }
      }

      for (int ix = 0; ix < columns.length; ix++) {
         if (columns[ix] < 0 || this.columnSize <= columns[ix]) {
            return true;
         }
      }

      return false;
   }

   protected void setValues(Object[][] target, Object value, int[] rows, int[] columns) {
      for (int i = 0; i < rows.length; i++) {
         int row = rows[i];

         for (int j = 0; j < columns.length; j++) {
            int column = columns[j];
            target[row][column] = value;
         }
      }
   }
}
