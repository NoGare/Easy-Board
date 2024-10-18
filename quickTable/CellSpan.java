package quickTable;

public interface CellSpan {
   int ROW = 0;
   int COLUMN = 1;

   int[] getSpan(int var1, int var2);

   void setSpan(int[] var1, int var2, int var3);

   boolean isVisible(int var1, int var2);

   void combine(int[] var1, int[] var2);

   void split(int var1, int var2);
}
