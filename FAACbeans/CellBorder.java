package FAACbeans;

import java.awt.Color;
import javax.swing.border.Border;

public interface CellBorder {
   Border getBorder();

   Border getBorder(int var1, int var2);

   Color getBackgroundColor();

   void setBorder(Border var1);

   void setBorder(Border var1, int var2, int var3);

   void setBackgroundColor(Color var1);
}
