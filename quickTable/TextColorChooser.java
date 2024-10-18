package quickTable;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JColorChooser;

public class TextColorChooser extends JColorChooser {
   public TextColorChooser(Color target, Color reference, boolean isForgroundSelection) {
      super(target);
      if (isForgroundSelection) {
         this.setPreviewPanel(new TextPreviewLabel(target, reference, isForgroundSelection));
      } else {
         this.setPreviewPanel(new TextPreviewLabel(reference, target, isForgroundSelection));
      }

      this.updateUI();
   }

   public Color showDialog(Component component, String title) {
      ColorChooserDialog dialog = new ColorChooserDialog(component, title, this);
      dialog.setVisible(true);
      Color col = dialog.getColor();
      dialog.dispose();
      return col;
   }
}
