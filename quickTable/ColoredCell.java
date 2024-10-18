package quickTable;

import java.awt.Color;

public interface ColoredCell {
   Color getForeground(int var1, int var2);

   void setForeground(Color var1, int var2, int var3);

   void setForeground(Color var1, int[] var2, int[] var3);

   Color getBackground(int var1, int var2);

   void setBackground(Color var1, int var2, int var3);

   void setBackground(Color var1, int[] var2, int[] var3);
}
