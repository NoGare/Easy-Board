package FAACbeans;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

public final class LinesBorder extends AbstractBorder implements SwingConstants {
   protected int northThickness;
   protected int southThickness;
   protected int eastThickness;
   protected int westThickness;
   protected Color northColor;
   protected Color southColor;
   protected Color eastColor;
   protected Color westColor;

   public LinesBorder(Color color) {
      this(color, 1);
   }

   public LinesBorder(Color color, int thickness) {
      this.setColor(color);
      this.setThickness(thickness);
   }

   public LinesBorder(Color color, Insets insets) {
      this.setColor(color);
      this.setThickness(insets);
   }

   @Override
   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      Color oldColor = g.getColor();
      g.setColor(this.northColor);

      for (int i = 0; i < this.northThickness; i++) {
         g.drawLine(x, y + i, x + width - 1, y + i);
      }

      g.setColor(this.southColor);

      for (int i = 0; i < this.southThickness; i++) {
         g.drawLine(x, y + height - i - 1, x + width - 1, y + height - i - 1);
      }

      g.setColor(this.eastColor);

      for (int i = 0; i < this.westThickness; i++) {
         g.drawLine(x + i, y, x + i, y + height - 1);
      }

      g.setColor(this.westColor);

      for (int i = 0; i < this.eastThickness; i++) {
         g.drawLine(x + width - i - 1, y, x + width - i - 1, y + height - 1);
      }

      g.setColor(oldColor);
   }

   @Override
   public Insets getBorderInsets(Component c) {
      return new Insets(this.northThickness, this.westThickness, this.southThickness, this.eastThickness);
   }

   @Override
   public Insets getBorderInsets(Component c, Insets insets) {
      return new Insets(this.northThickness, this.westThickness, this.southThickness, this.eastThickness);
   }

   @Override
   public boolean isBorderOpaque() {
      return true;
   }

   public void setColor(Color c) {
      this.northColor = c;
      this.southColor = c;
      this.eastColor = c;
      this.westColor = c;
   }

   public void setColor(Color c, int direction) {
      switch (direction) {
         case 1:
            this.northColor = c;
         case 2:
         case 4:
         case 6:
         default:
            break;
         case 3:
            this.eastColor = c;
            break;
         case 5:
            this.southColor = c;
            break;
         case 7:
            this.westColor = c;
      }
   }

   public void setThickness(int n) {
      this.northThickness = n;
      this.southThickness = n;
      this.eastThickness = n;
      this.westThickness = n;
   }

   public void setThickness(Insets insets) {
      this.northThickness = insets.top;
      this.southThickness = insets.bottom;
      this.eastThickness = insets.right;
      this.westThickness = insets.left;
   }

   public void setThickness(int n, int direction) {
      switch (direction) {
         case 1:
            this.northThickness = n;
         case 2:
         case 4:
         case 6:
         default:
            break;
         case 3:
            this.eastThickness = n;
            break;
         case 5:
            this.southThickness = n;
            break;
         case 7:
            this.westThickness = n;
      }
   }

   public void append(LinesBorder b, boolean isReplace) {
      if (isReplace) {
         this.northThickness = b.northThickness;
         this.southThickness = b.southThickness;
         this.eastThickness = b.eastThickness;
         this.westThickness = b.westThickness;
      } else {
         this.northThickness = Math.max(this.northThickness, b.northThickness);
         this.southThickness = Math.max(this.southThickness, b.southThickness);
         this.eastThickness = Math.max(this.eastThickness, b.eastThickness);
         this.westThickness = Math.max(this.westThickness, b.westThickness);
      }
   }

   public void append(Insets insets, boolean isReplace) {
      if (isReplace) {
         this.northThickness = insets.top;
         this.southThickness = insets.bottom;
         this.eastThickness = insets.right;
         this.westThickness = insets.left;
      } else {
         this.northThickness = Math.max(this.northThickness, insets.top);
         this.southThickness = Math.max(this.southThickness, insets.bottom);
         this.eastThickness = Math.max(this.eastThickness, insets.right);
         this.westThickness = Math.max(this.westThickness, insets.left);
      }
   }
}
