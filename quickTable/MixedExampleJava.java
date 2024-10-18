package quickTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class MixedExampleJava extends JFrame {
   public MixedExampleJava() {
      super("Mixed Example");
      AttributiveCellTableModel ml = new AttributiveCellTableModel(20, 5) {
         @Override
         public Object getValueAt(int row, int col) {
            return "" + row + "," + col;
         }
      };
      CellAttribute cellAtt = ml.getCellAttribute();
      MultiSpanCellTable table = new MultiSpanCellTable(ml);
      table.setCellSelectionEnabled(true);
      table.setSelectionMode(1);
      table.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
      JScrollPane scroll = new JScrollPane(table);
      MixedExampleJava.ColorPanel colorPanel = new MixedExampleJava.ColorPanel(table, (ColoredCell)cellAtt);
      MixedExampleJava.FontPanel fontPanel = new MixedExampleJava.FontPanel(table, (CellFont)cellAtt);
      MixedExampleJava.SpanPanel spanPanel = new MixedExampleJava.SpanPanel(table, (CellSpan)cellAtt);
      Box boxAtt = new Box(1);
      boxAtt.add(colorPanel);
      boxAtt.add(fontPanel);
      boxAtt.add(spanPanel);
      Box box = new Box(0);
      box.add(scroll);
      box.add(new JSeparator(0));
      box.add(boxAtt);
      this.getContentPane().add(box);
      this.setSize(400, 300);
      this.setVisible(true);
   }

   public static void main(String[] args) {
      MixedExampleJava frame = new MixedExampleJava();
      frame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
   }

   class ColorPanel extends JPanel {
      JTable table;
      ColoredCell cellAtt;

      ColorPanel(JTable table, ColoredCell cellAtt) {
         this.table = table;
         this.cellAtt = cellAtt;
         this.setLayout(new GridLayout(2, 1));
         this.setBorder(BorderFactory.createTitledBorder("Color"));
         JButton b_fore = new JButton("Foreground");
         b_fore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ColorPanel.this.changeColor(true);
            }
         });
         JButton b_back = new JButton("Background");
         b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ColorPanel.this.changeColor(false);
            }
         });
         new JPanel();
         this.add(b_fore);
         this.add(b_back);
      }

      private void changeColor(boolean isForeground) {
         int[] columns = this.table.getSelectedColumns();
         int[] rows = this.table.getSelectedRows();
         if (rows != null && columns != null) {
            if (rows.length >= 1 && columns.length >= 1) {
               Color target = this.cellAtt.getForeground(rows[0], columns[0]);
               Color reference = this.cellAtt.getBackground(rows[0], columns[0]);

               for (int i = 0; i < rows.length; i++) {
                  int row = rows[i];

                  for (int j = 0; j < columns.length; j++) {
                     int column = columns[j];
                     target = target != this.cellAtt.getForeground(row, column) ? null : target;
                     reference = reference != this.cellAtt.getBackground(row, column) ? null : reference;
                  }
               }

               String title;
               if (isForeground) {
                  target = target != null ? target : this.table.getForeground();
                  reference = reference != null ? reference : this.table.getBackground();
                  title = "Foreground Color";
               } else {
                  target = reference != null ? reference : this.table.getBackground();
                  reference = target != null ? target : this.table.getForeground();
                  title = "Foreground Color";
               }

               TextColorChooser chooser = new TextColorChooser(target, reference, isForeground);
               Color color = chooser.showDialog(MixedExampleJava.this, title);
               if (color != null) {
                  if (isForeground) {
                     this.cellAtt.setForeground(color, rows, columns);
                  } else {
                     this.cellAtt.setBackground(color, rows, columns);
                  }

                  this.table.clearSelection();
                  this.table.revalidate();
                  this.table.repaint();
               }
            }
         }
      }
   }

   class FontPanel extends JPanel {
      String[] str_size = new String[]{"10", "12", "14", "16", "20"};
      String[] str_style = new String[]{"PLAIN", "BOLD", "ITALIC"};
      JComboBox name;
      JComboBox style;
      JComboBox size;

      FontPanel(final JTable table, final CellFont cellAtt) {
         this.setLayout(new BorderLayout());
         this.setBorder(BorderFactory.createTitledBorder("Font"));
         Box box = new Box(0);
         JPanel p2 = new JPanel(new GridLayout(3, 1));
         JPanel p3 = new JPanel(new GridLayout(3, 1));
         JPanel p4 = new JPanel(new BorderLayout());
         p2.add(new JLabel("Name:"));
         p2.add(new JLabel("Style:"));
         p2.add(new JLabel("Size:"));
         Toolkit toolkit = Toolkit.getDefaultToolkit();
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
         this.name = new JComboBox<>(ge.getAvailableFontFamilyNames());
         this.style = new JComboBox<>(this.str_style);
         this.size = new JComboBox<>(this.str_size);
         this.size.setEditable(true);
         JButton b_apply = new JButton("Apply");
         b_apply.addActionListener(
            new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int[] columns = table.getSelectedColumns();
                  int[] rows = table.getSelectedRows();
                  if (rows != null && columns != null) {
                     if (rows.length >= 1 && columns.length >= 1) {
                        Font font = new Font(
                           (String)FontPanel.this.name.getSelectedItem(),
                           FontPanel.this.style.getSelectedIndex(),
                           Integer.parseInt((String)FontPanel.this.size.getSelectedItem())
                        );
                        cellAtt.setFont(font, rows, columns);
                        table.clearSelection();
                        table.revalidate();
                        table.repaint();
                     }
                  }
               }
            }
         );
         p3.add(this.name);
         p3.add(this.style);
         p3.add(this.size);
         p4.add("Center", b_apply);
         box.add(p2);
         box.add(p3);
         this.add("Center", box);
         this.add("South", p4);
      }
   }

   class SpanPanel extends JPanel {
      JTable table;
      CellSpan cellAtt;

      SpanPanel(final JTable table, final CellSpan cellAtt) {
         this.table = table;
         this.cellAtt = cellAtt;
         this.setLayout(new GridLayout(2, 1));
         this.setBorder(BorderFactory.createTitledBorder("Span"));
         JButton b_one = new JButton("Combine");
         b_one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int[] columns = table.getSelectedColumns();
               int[] rows = table.getSelectedRows();
               cellAtt.combine(rows, columns);
               table.clearSelection();
               table.revalidate();
               table.repaint();
            }
         });
         JButton b_split = new JButton("Split");
         b_split.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int column = table.getSelectedColumn();
               int row = table.getSelectedRow();
               cellAtt.split(row, column);
               table.clearSelection();
               table.revalidate();
               table.repaint();
            }
         });
         this.add(b_one);
         this.add(b_split);
      }
   }
}
