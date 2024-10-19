package FAACbeans;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Component.BaselineResizeBehavior;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

public class VerticalLabelUI extends BasicLabelUI {
   private boolean clockwise = false;
   Rectangle verticalViewR = new Rectangle();
   Rectangle verticalIconR = new Rectangle();
   Rectangle verticalTextR = new Rectangle();
   protected static VerticalLabelUI verticalLabelUI = new VerticalLabelUI();
   private static final VerticalLabelUI SAFE_VERTICAL_LABEL_UI = new VerticalLabelUI();

   public VerticalLabelUI() {
   }

   public VerticalLabelUI(boolean clockwise) {
      this.clockwise = clockwise;
   }

   public static ComponentUI createUI(JComponent c) {
      return System.getSecurityManager() != null ? SAFE_VERTICAL_LABEL_UI : verticalLabelUI;
   }

   @Override
   public int getBaseline(JComponent c, int width, int height) {
      super.getBaseline(c, width, height);
      return -1;
   }

   @Override
   public BaselineResizeBehavior getBaselineResizeBehavior(JComponent c) {
      super.getBaselineResizeBehavior(c);
      return BaselineResizeBehavior.OTHER;
   }

   @Override
   protected String layoutCL(JLabel label, FontMetrics fontMetrics, String text, Icon icon, Rectangle viewR, Rectangle iconR, Rectangle textR) {
      this.verticalViewR = this.transposeRectangle(viewR, this.verticalViewR);
      this.verticalIconR = this.transposeRectangle(iconR, this.verticalIconR);
      this.verticalTextR = this.transposeRectangle(textR, this.verticalTextR);
      text = super.layoutCL(label, fontMetrics, text, icon, this.verticalViewR, this.verticalIconR, this.verticalTextR);
      viewR = this.copyRectangle(this.verticalViewR, viewR);
      iconR = this.copyRectangle(this.verticalIconR, iconR);
      textR = this.copyRectangle(this.verticalTextR, textR);
      return text;
   }

   @Override
   public void paint(Graphics g, JComponent c) {
      Graphics2D g2 = (Graphics2D)g.create();
      if (this.clockwise) {
         g2.rotate(Math.PI / 2, (double)(c.getSize().width / 2), (double)(c.getSize().width / 2));
      } else {
         g2.rotate(-Math.PI / 2, (double)(c.getSize().height / 2), (double)(c.getSize().height / 2));
      }

      super.paint(g2, c);
   }

   @Override
   public Dimension getPreferredSize(JComponent c) {
      return this.transposeDimension(super.getPreferredSize(c));
   }

   @Override
   public Dimension getMaximumSize(JComponent c) {
      return this.transposeDimension(super.getMaximumSize(c));
   }

   @Override
   public Dimension getMinimumSize(JComponent c) {
      return this.transposeDimension(super.getMinimumSize(c));
   }

   private Dimension transposeDimension(Dimension from) {
      return new Dimension(from.height, from.width + 2);
   }

   private Rectangle transposeRectangle(Rectangle from, Rectangle to) {
      if (to == null) {
         to = new Rectangle();
      }

      to.x = from.y;
      to.y = from.x;
      to.width = from.height;
      to.height = from.width;
      return to;
   }

   private Rectangle copyRectangle(Rectangle from, Rectangle to) {
      if (to == null) {
         to = new Rectangle();
      }

      to.x = from.x;
      to.y = from.y;
      to.width = from.width;
      to.height = from.height;
      return to;
   }
}
