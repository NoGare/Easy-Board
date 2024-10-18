package quickTable;

import java.awt.Font;

public interface CellFont {
   Font getFont(int var1, int var2);

   void setFont(Font var1, int var2, int var3);

   void setFont(Font var1, int[] var2, int[] var3);
}
