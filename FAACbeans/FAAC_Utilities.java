package FAACbeans;

import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

public class FAAC_Utilities {
   public static void disableSpinnerEditing(JSpinner jSpinnerDate) {
      JFormattedTextField ftfTmp = ((DefaultEditor)jSpinnerDate.getEditor()).getTextField();
      ftfTmp.setEditable(false);
      ftfTmp.setBackground(SystemColor.WHITE);
   }

   public static void listenForManualEditDoubleSpinner(final JSpinner jSpinnerNum_in, final Comparable min_in, final Comparable max_in) {
      ((DefaultEditor)jSpinnerNum_in.getEditor()).getTextField().addKeyListener(new KeyListener() {
         @Override
         public void keyPressed(KeyEvent e) {
         }

         @Override
         public void keyReleased(KeyEvent e) {
            JTextField editor = ((DefaultEditor)jSpinnerNum_in.getEditor()).getTextField();
            double val = Double.parseDouble(editor.getText());
            if (val > Double.parseDouble(max_in.toString())) {
               val = Double.parseDouble(max_in.toString());
               editor.setText(String.valueOf(val));
               jSpinnerNum_in.setValue(val);

               try {
                  jSpinnerNum_in.commitEdit();
               } catch (ParseException var7) {
                  Logger.getLogger(FAAC_Utilities.class.getName()).log(Level.SEVERE, null, var7);
               }
            } else if (val < Double.parseDouble(min_in.toString())) {
               val = Double.parseDouble(min_in.toString());
               editor.setText(String.valueOf(val));
               jSpinnerNum_in.setValue(val);

               try {
                  jSpinnerNum_in.commitEdit();
               } catch (ParseException var6) {
               }
            }
         }

         @Override
         public void keyTyped(KeyEvent e) {
         }
      });
      ChangeListener numSpinnerChList = new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent e) {
            try {
               String val = ((JSpinner)e.getSource()).getValue().toString();
               ((JSpinner)e.getSource()).commitEdit();
            } catch (ParseException var3) {
               Logger.getLogger(FAAC_Utilities.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      };
      JComponent comp = jSpinnerNum_in.getEditor();
      JFormattedTextField field = (JFormattedTextField)comp.getComponent(0);
      DefaultFormatter formatter = (DefaultFormatter)field.getFormatter();
      formatter.setCommitsOnValidEdit(true);
      jSpinnerNum_in.addChangeListener(numSpinnerChList);
   }

   public static void listenForManualEditIntSpinner(final JSpinner jSpinnerNum_in, final Comparable min_in, final Comparable max_in) {
      ((DefaultEditor)jSpinnerNum_in.getEditor()).getTextField().addKeyListener(new KeyListener() {
         @Override
         public void keyPressed(KeyEvent e) {
         }

         @Override
         public void keyReleased(KeyEvent e) {
            JTextField editor = ((DefaultEditor)jSpinnerNum_in.getEditor()).getTextField();
            int val = Integer.parseInt(editor.getText());
            if (val > Integer.parseInt(max_in.toString())) {
               val = Integer.parseInt(max_in.toString());
               editor.setText(String.valueOf(val));
               jSpinnerNum_in.setValue(val);

               try {
                  jSpinnerNum_in.commitEdit();
               } catch (ParseException var6) {
                  Logger.getLogger(FAAC_Utilities.class.getName()).log(Level.SEVERE, null, var6);
               }
            } else if (val < Integer.parseInt(min_in.toString())) {
               val = Integer.parseInt(min_in.toString());
               editor.setText(String.valueOf(val));
               jSpinnerNum_in.setValue(val);

               try {
                  jSpinnerNum_in.commitEdit();
               } catch (ParseException var5) {
               }
            }
         }

         @Override
         public void keyTyped(KeyEvent e) {
         }
      });
      ChangeListener numSpinnerChList = new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent e) {
            try {
               String val = ((JSpinner)e.getSource()).getValue().toString();
               ((JSpinner)e.getSource()).commitEdit();
            } catch (ParseException var3) {
               Logger.getLogger(FAAC_Utilities.class.getName()).log(Level.SEVERE, null, var3);
            }
         }
      };
      JComponent comp = jSpinnerNum_in.getEditor();
      JFormattedTextField field = (JFormattedTextField)comp.getComponent(0);
      DefaultFormatter formatter = (DefaultFormatter)field.getFormatter();
      formatter.setCommitsOnValidEdit(true);
      jSpinnerNum_in.addChangeListener(numSpinnerChList);
   }

   public static boolean parseStr_HH_mm(String txt, int MaxHours, int MaxMinutes, int[] res) {
      String delims = "[:]";
      boolean bRes = true;

      int mm;
      int HH;
      try {
         String[] parts = txt.split(delims);
         mm = Integer.parseInt(parts[1]);
         HH = Integer.parseInt(parts[0]);
         if (mm > MaxMinutes) {
            mm = MaxMinutes;
            bRes = false;
         }

         if (HH > MaxHours) {
            HH = MaxHours;
            bRes = false;
         }
      } catch (Exception var9) {
         mm = MaxMinutes;
         HH = MaxHours;
         bRes = false;
      }

      res[0] = mm;
      res[1] = HH;
      return bRes;
   }

   public static void setSpinnerHourMinModel(JSpinner spinner, int MaxHours, int MaxMinutes) {
      Calendar start = GregorianCalendar.getInstance();
      Calendar end = GregorianCalendar.getInstance();
      start.clear();
      end.clear();
      end.add(12, MaxMinutes);
      end.add(11, MaxHours);
      SpinnerDateModel m2 = new SpinnerDateModel(start.getTime(), start.getTime(), end.getTime(), 12);
      spinner.setModel(m2);
      spinner.setEditor(new DateEditor(spinner, "HH:mm"));
   }

   public static boolean parseStr_mm_ss(String txt, int MaxMinutes, int MaxSeconds, int[] res) {
      String delims = "[:]";
      boolean bRes = true;

      int mm;
      int ss;
      try {
         String[] parts = txt.split(delims);
         ss = Integer.parseInt(parts[1]);
         mm = Integer.parseInt(parts[0]);
         if (mm > MaxMinutes) {
            mm = MaxMinutes;
            bRes = false;
         }

         if (ss > MaxSeconds) {
            ss = MaxSeconds;
            bRes = false;
         }
      } catch (Exception var9) {
         mm = MaxMinutes;
         ss = MaxSeconds;
         bRes = false;
      }

      res[0] = ss;
      res[1] = mm;
      return bRes;
   }

   public static void setSpinnerMinSecModel(JSpinner spinner, int MaxMinutes, int MaxSeconds) {
      Calendar start = GregorianCalendar.getInstance();
      Calendar end = GregorianCalendar.getInstance();
      start.clear();
      end.clear();
      end.add(12, MaxMinutes);
      end.add(13, MaxSeconds);
      SpinnerDateModel m2 = new SpinnerDateModel(start.getTime(), start.getTime(), end.getTime(), 13);
      spinner.setModel(m2);
      spinner.setEditor(new DateEditor(spinner, "mm:ss"));
   }

   public static boolean parseStr_HH_mm_ss(String txt, int MaxHours, int MaxMinutes, int MaxSeconds, int[] res) {
      String delims = "[:]";
      boolean bRes = true;

      int HH;
      int mm;
      int ss;
      try {
         String[] parts = txt.split(delims);
         ss = Integer.parseInt(parts[2]);
         mm = Integer.parseInt(parts[1]);
         HH = Integer.parseInt(parts[0]);
         if (HH > MaxHours) {
            HH = MaxHours;
            bRes = false;
         }

         if (mm > MaxMinutes) {
            mm = MaxMinutes;
            bRes = false;
         }

         if (ss > MaxSeconds) {
            bRes = false;
            ss = MaxSeconds;
         }
      } catch (Exception var11) {
         HH = MaxHours;
         mm = MaxMinutes;
         ss = MaxSeconds;
         bRes = false;
      }

      res[0] = ss;
      res[1] = mm;
      res[2] = HH;
      return bRes;
   }

   public static void setSpinnerHourMinSecModel(JSpinner spinner, int MaxHours, int MaxMinutes, int MaxSeconds) {
      Calendar start = GregorianCalendar.getInstance();
      Calendar end = GregorianCalendar.getInstance();
      start.clear();
      end.clear();
      end.add(10, MaxHours);
      end.add(12, MaxMinutes);
      end.add(13, MaxSeconds);
      SpinnerDateModel m2 = new SpinnerDateModel(start.getTime(), start.getTime(), end.getTime(), 13);
      spinner.setModel(m2);
      spinner.setEditor(new DateEditor(spinner, "HH:mm:ss"));
   }

   public static boolean parseStr_mm_30ss(String txt, int MaxMinutes, int MaxSeconds, int[] res) {
      String delims = "[:]";
      boolean bRes = true;

      int mm;
      int ss;
      try {
         String[] parts = txt.split(delims);
         ss = Integer.parseInt(parts[1]);
         mm = Integer.parseInt(parts[0]);
         if (mm % 30 != 0) {
            mm = MaxMinutes;
            ss = MaxSeconds;
            bRes = false;
         } else {
            if (mm > MaxMinutes) {
               mm = MaxMinutes;
               bRes = false;
            }

            if (ss > MaxSeconds) {
               ss = MaxSeconds;
               bRes = false;
            }
         }
      } catch (Exception var9) {
         mm = MaxMinutes;
         ss = MaxSeconds;
         bRes = false;
      }

      res[0] = ss;
      res[1] = mm;
      return bRes;
   }

   public static void setSpinnerMin30SecModel(JSpinner spinner, int MaxMinutes, int MaxSeconds) {
      Calendar start = GregorianCalendar.getInstance();
      Calendar end = GregorianCalendar.getInstance();
      start.clear();
      end.clear();
      end.add(12, MaxMinutes);
      end.add(13, MaxSeconds);
      SpinnerDateModel m2 = new SpinnerDateModel(start.getTime(), start.getTime(), end.getTime(), 13) {
         @Override
         public Object getNextValue() {
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date)this.getValue());
            cal.add(13, 30);
            Date next = cal.getTime();
            return this.getEnd() != null && this.getEnd().compareTo(next) < 0 ? null : next;
         }

         @Override
         public Object getPreviousValue() {
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date)this.getValue());
            cal.add(13, -30);
            Date previous = cal.getTime();
            return this.getStart() != null && this.getStart().compareTo(previous) > 0 ? null : previous;
         }
      };
      spinner.setModel(m2);
      spinner.setEditor(new DateEditor(spinner, "mm:ss"));
   }
}
