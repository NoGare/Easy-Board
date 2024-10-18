package quickTable;

import java.awt.Color;
import javax.swing.JLabel;

final class TextPreviewLabel extends JLabel {
   private String sampleText = "  Sample Text  Sample Text  ";
   boolean isForgroundSelection;

   public TextPreviewLabel() {
      this(Color.black, Color.white, true);
   }

   public TextPreviewLabel(Color fore, Color back, boolean isForgroundSelection) {
      this.setOpaque(true);
      this.setForeground(fore);
      this.setBackground(back);
      this.isForgroundSelection = isForgroundSelection;
      this.setText(this.sampleText);
   }

   @Override
   public void setForeground(Color col) {
      if (this.isForgroundSelection) {
         super.setForeground(col);
      } else {
         super.setBackground(col);
      }
   }
}
