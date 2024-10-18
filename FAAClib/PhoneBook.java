package FAAClib;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PhoneBook {
   public static final int PhonebookSize = 20;
   private PBentry[] pbook = new PBentry[20];

   public PhoneBook() {
      for (int i = 0; i < 20; i++) {
         this.pbook[i] = new PBentry(i, "", (byte)0, (byte)0);
      }
   }

   public void populateTable(JTable jTabNumbers) {
      DefaultTableModel model = (DefaultTableModel)jTabNumbers.getModel();
      model.setRowCount(0);

      for (int i = 0; i < 20; i++) {
         model.addRow(this.pbook[i].getVectorData());
      }
   }

   public void updateFromTable(JTable jTabNumbers) {
      DefaultTableModel model = (DefaultTableModel)jTabNumbers.getModel();
      byte attributes = 0;
      byte profile = 0;

      for (int i = 0; i < 20; i++) {
         int index = (Integer)model.getValueAt(i, 0);
         String num = (String)model.getValueAt(i, 1);
         profile = 0;
         if ((Boolean)model.getValueAt(i, 2)) {
            profile = (byte)(profile | 1);
         }

         if ((Boolean)model.getValueAt(i, 3)) {
            profile = (byte)(profile | 2);
         }

         if ((Boolean)model.getValueAt(i, 4)) {
            profile = (byte)(profile | 4);
         }

         attributes = 0;
         if ((Boolean)model.getValueAt(i, 5)) {
            attributes = (byte)(attributes | 1);
         }

         if ((Boolean)model.getValueAt(i, 6)) {
            attributes = (byte)(attributes | 2);
         }

         if ((Boolean)model.getValueAt(i, 7)) {
            attributes = (byte)(attributes | 4);
         }

         this.pbook[i] = new PBentry(index, num, profile, attributes);
      }
   }

   void AddEntries(PBentry[] entries, int pbIndex) {
      int actualIndex = pbIndex * 4;

      for (int i = 0; i < entries.length; i++) {
         this.pbook[i + actualIndex] = entries[i];
      }
   }

   PBentry[] GetEntryGroup(int groupIndex) {
      PBentry[] entryGrp = new PBentry[4];
      int firstIndex = groupIndex * 4;
      if (firstIndex + 4 > this.pbook.length) {
         Debug.printlnStatic("ERROR: not enough capacity in the phonebook");
      }

      for (int i = 0; i < 4; i++) {
         entryGrp[i] = this.pbook[firstIndex + i];
      }

      return entryGrp;
   }

   public String GetCsv() {
      StringBuilder builder = new StringBuilder("N;Phone;Profile;Attributes\r\n");

      for (int i = 0; i < 20; i++) {
         if (this.pbook[i] != null) {
            builder.append(this.pbook[i].toLongString() + "\n\r");
         } else {
            builder.append(i + ";;;\r\n");
         }
      }

      return builder.toString();
   }
}
