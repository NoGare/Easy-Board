package FAAClib;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class FAAC_Timer {
   public static final int NbHourRanges = 6;
   public static final int NbDays = 8;
   public static final int NbJollyIntervals = 6;
   private FAAC_Timer.TimerEvent[][] timerEventMatrix_6rangesx8days = new FAAC_Timer.TimerEvent[6][8];
   private FAAC_Timer.ProgTimerFlag progTimer;
   private FAAC_Timer.JollyInterval[] jollyIntervals;

   public FAAC_Timer() {
      for (int iCol = 0; iCol < 8; iCol++) {
         for (int iRow = 0; iRow < 6; iRow++) {
            this.timerEventMatrix_6rangesx8days[iRow][iCol] = new FAAC_Timer.TimerEvent(iCol);
         }
      }

      this.progTimer = new FAAC_Timer.ProgTimerFlag();
      this.progTimer.set00_TimerEnabled(false);
      this.progTimer.set01_DisableJollyInterval_1(false);
      this.progTimer.set02_DisableJollyInterval_2(false);
      this.progTimer.set03_DisableJollyInterval_3(false);
      this.progTimer.set04_DisableJollyInterval_4(false);
      this.progTimer.set05_DisableJollyInterval_5(false);
      this.progTimer.set06_DisableJollyInterval_6(false);
      this.jollyIntervals = new FAAC_Timer.JollyInterval[6];

      for (int i = 0; i < 6; i++) {
         this.jollyIntervals[i] = new FAAC_Timer.JollyInterval();
      }
   }

   public boolean AddEvent(int iDayIndex, Calendar calStartExact, Calendar calEndExact, int value, String description) {
      boolean bRes = false;
      FAAC_Timer.TimerEvent eventToBeAdded = new FAAC_Timer.TimerEvent(iDayIndex);
      eventToBeAdded.setBeginDateExact(calStartExact);
      eventToBeAdded.setEndDateExact(calEndExact);
      eventToBeAdded.ExactTimerRange2RoundedTimerRange();
      eventToBeAdded.setValueFunction(value);
      eventToBeAdded.setDescription(description);
      boolean bAdded = false;

      for (int iRow = 0; iRow < 6; iRow++) {
         if (this.timerEventMatrix_6rangesx8days[iRow][iDayIndex].getValueFunction() == 0) {
            this.timerEventMatrix_6rangesx8days[iRow][iDayIndex] = eventToBeAdded;
            bAdded = true;
            break;
         }
      }

      if (bAdded) {
         FAAC_Timer.TimerEvent[] dayArrayTmp = new FAAC_Timer.TimerEvent[6];

         for (int iRowx = 0; iRowx < 6; iRowx++) {
            dayArrayTmp[iRowx] = this.timerEventMatrix_6rangesx8days[iRowx][iDayIndex];
         }

         Arrays.sort((Object[])dayArrayTmp);

         for (int iRowx = 0; iRowx < 6; iRowx++) {
            this.timerEventMatrix_6rangesx8days[iRowx][iDayIndex] = dayArrayTmp[iRowx];
         }

         bRes = true;
      } else {
         bRes = false;
      }

      return bRes;
   }

   public boolean canAddEvent(int iDayIndex, Calendar calStart, Calendar calEnd) {
      boolean bCanAdd = true;

      for (int iRange = 0; iRange < 6; iRange++) {
         if (this.timerEventMatrix_6rangesx8days[iRange][iDayIndex].getValueFunction() != 0) {
            Calendar calStartAlready = this.timerEventMatrix_6rangesx8days[iRange][iDayIndex].getBeginDateExact();
            Calendar calEndAlready = this.timerEventMatrix_6rangesx8days[iRange][iDayIndex].getEndDateExact();
            if (calStart.getTime().compareTo(calStartAlready.getTime()) <= 0 && calEnd.getTime().compareTo(calEndAlready.getTime()) >= 0) {
               bCanAdd = false;
            } else if (calStart.getTime().compareTo(calStartAlready.getTime()) <= 0
               && calEnd.getTime().compareTo(calStartAlready.getTime()) >= 0
               && calEnd.getTime().compareTo(calEndAlready.getTime()) <= 0) {
               bCanAdd = false;
            } else if (calStart.getTime().compareTo(calStartAlready.getTime()) >= 0
               && calEnd.getTime().compareTo(calEndAlready.getTime()) >= 0
               && calStart.getTime().compareTo(calEndAlready.getTime()) <= 0) {
               bCanAdd = false;
            } else if (calStart.getTime().compareTo(calStartAlready.getTime()) >= 0 && calEnd.getTime().compareTo(calEndAlready.getTime()) <= 0) {
               bCanAdd = false;
            }
         }
      }

      return bCanAdd;
   }

   public boolean canModifyEvent(int iDayIndex, Calendar calStart, Calendar calEnd, FAAC_Timer.TimerEvent event) {
      boolean bCanModify = true;

      for (int iRange = 0; iRange < 6; iRange++) {
         if (this.timerEventMatrix_6rangesx8days[iRange][iDayIndex].getValueFunction() != 0) {
            Calendar calStartAlready = this.timerEventMatrix_6rangesx8days[iRange][iDayIndex].getBeginDateExact();
            Calendar calEndAlready = this.timerEventMatrix_6rangesx8days[iRange][iDayIndex].getEndDateExact();
            if (event.getUniqueId() != this.timerEventMatrix_6rangesx8days[iRange][iDayIndex].getUniqueId()) {
               if (calStart.getTime().compareTo(calStartAlready.getTime()) < 0 && calEnd.getTime().compareTo(calEndAlready.getTime()) > 0) {
                  bCanModify = false;
               } else if (calStart.getTime().compareTo(calStartAlready.getTime()) < 0
                  && calEnd.getTime().compareTo(calStartAlready.getTime()) > 0
                  && calEnd.getTime().compareTo(calEndAlready.getTime()) < 0) {
                  bCanModify = false;
               } else if (calStart.getTime().compareTo(calStartAlready.getTime()) > 0
                  && calEnd.getTime().compareTo(calEndAlready.getTime()) > 0
                  && calStart.getTime().compareTo(calEndAlready.getTime()) < 0) {
                  bCanModify = false;
               } else if (calStart.getTime().compareTo(calStartAlready.getTime()) > 0 && calEnd.getTime().compareTo(calEndAlready.getTime()) < 0) {
                  bCanModify = false;
               } else {
                  bCanModify = true;
               }
            }
         }
      }

      return bCanModify;
   }

   public FAAC_Timer.TimerEvent[] getTimer(int iDayIndex) {
      FAAC_Timer.TimerEvent[] res = new FAAC_Timer.TimerEvent[6];

      for (int iRow = 0; iRow < 6; iRow++) {
         res[iRow] = this.timerEventMatrix_6rangesx8days[iRow][iDayIndex];
      }

      return res;
   }

   public FAAC_Timer.TimerEvent[] getTimer00_Sunday() {
      return this.getTimer(0);
   }

   public FAAC_Timer.TimerEvent[] getTimer01_Monday() {
      return this.getTimer(1);
   }

   public FAAC_Timer.TimerEvent[] getTimer02_Tuesday() {
      return this.getTimer(2);
   }

   public FAAC_Timer.TimerEvent[] getTimer03_Wednesday() {
      return this.getTimer(3);
   }

   public FAAC_Timer.TimerEvent[] getTimer04_Thursday() {
      return this.getTimer(4);
   }

   public FAAC_Timer.TimerEvent[] getTimer05_Friday() {
      return this.getTimer(5);
   }

   public FAAC_Timer.TimerEvent[] getTimer06_Saturday() {
      return this.getTimer(6);
   }

   public FAAC_Timer.TimerEvent[] getTimer07_Jolly() {
      return this.getTimer(7);
   }

   public FAAC_Timer.TimerEvent[][] getTimerEventMatrix_6rangesx8days() {
      return this.timerEventMatrix_6rangesx8days;
   }

   public void setTimerEventMatrix_6rangesx8days(FAAC_Timer.TimerEvent[][] timerEventMatrix_6rangesx8days) {
      this.timerEventMatrix_6rangesx8days = timerEventMatrix_6rangesx8days;
   }

   public FAAC_Timer.ProgTimerFlag getProgTimer() {
      return this.progTimer;
   }

   public void setProgTimer(FAAC_Timer.ProgTimerFlag progTimer) {
      this.progTimer = progTimer;
   }

   public FAAC_Timer.JollyInterval[] getJollyIntervals() {
      return this.jollyIntervals;
   }

   public void setJollyIntervals(FAAC_Timer.JollyInterval[] jollyIntervals) {
      this.jollyIntervals = jollyIntervals;
   }

   public class JollyInterval {
      private int iStartDay = 1;
      private int iStartMonth = 1;
      private int iEndDay = 1;
      private int iEndMonth = 1;

      public int getStartDay() {
         return this.iStartDay;
      }

      public void setStartDay(int iStartDay) {
         this.iStartDay = iStartDay;
      }

      public int getStartMonth() {
         return this.iStartMonth;
      }

      public void setStartMonth(int iStartMonth) {
         this.iStartMonth = iStartMonth;
      }

      public int getEndDay() {
         return this.iEndDay;
      }

      public void setEndDay(int iEndDay) {
         this.iEndDay = iEndDay;
      }

      public int getEndMonth() {
         return this.iEndMonth;
      }

      public void setEndMonth(int iEndMonth) {
         this.iEndMonth = iEndMonth;
      }
   }

   public class ProgTimerFlag extends Union32_Abstraction {
      public void set00_TimerEnabled(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_TimerEnabled() {
         return this.bits[0];
      }

      public void set01_DisableJollyInterval_1(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_DisableJollyInterval_1() {
         return this.bits[1];
      }

      public void set02_DisableJollyInterval_2(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_DisableJollyInterval_2() {
         return this.bits[2];
      }

      public void set03_DisableJollyInterval_3(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_DisableJollyInterval_3() {
         return this.bits[3];
      }

      public void set04_DisableJollyInterval_4(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_DisableJollyInterval_4() {
         return this.bits[4];
      }

      public void set05_DisableJollyInterval_5(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_DisableJollyInterval_5() {
         return this.bits[5];
      }

      public void set06_DisableJollyInterval_6(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_DisableJollyInterval_6() {
         return this.bits[6];
      }
   }

   public static class TimerEvent implements Comparable {
      private int indexDay;
      private Calendar beginDateExact;
      private int beginHourExact;
      private int beginMinuteExact;
      private Calendar beginDateRound;
      private int beginHourRound;
      private int beginMinuteRound;
      private Calendar endDateExact;
      private int endHourExact;
      private int endMinuteExact;
      private Calendar endDateRound;
      private int endHourRound;
      private int endMinuteRound;
      private int valueFunction = 0;
      private String description;
      private UUID uniqueId;

      public TimerEvent() {
         this.uniqueId = UUID.randomUUID();
      }

      public TimerEvent(int indexDay) {
         this.indexDay = indexDay;
         this.uniqueId = UUID.randomUUID();
      }

      @Override
      public String toString() {
         return this.getDescription();
      }

      public void ExactTimerRange2RoundedTimerRange() {
         int MinRangeRounding = 30;
         this.beginDateExact = GregorianCalendar.getInstance();
         this.beginDateExact.clear();
         this.beginDateExact.add(12, this.beginMinuteExact);
         this.beginDateExact.add(11, this.beginHourExact);
         this.beginDateRound = GregorianCalendar.getInstance();
         this.beginDateRound.clear();
         int res = this.beginMinuteExact % 30;
         if (res == 0) {
            this.beginHourRound = this.beginHourExact;
            this.beginMinuteRound = this.beginMinuteExact - res;
            this.beginDateRound.add(12, this.beginMinuteRound);
            this.beginDateRound.add(11, this.beginHourRound);
         } else {
            this.beginHourRound = this.beginHourExact;
            this.beginMinuteRound = this.beginMinuteExact - res;
            this.beginDateRound.add(12, this.beginMinuteRound);
            this.beginDateRound.add(11, this.beginHourRound);
         }

         this.endDateExact = GregorianCalendar.getInstance();
         this.endDateExact.clear();
         this.endDateExact.add(12, this.endMinuteExact);
         this.endDateExact.add(11, this.endHourExact);
         this.endDateRound = GregorianCalendar.getInstance();
         this.endDateRound.clear();
         res = this.endMinuteExact % 30;
         if (res == 0) {
            this.endHourRound = this.endHourExact;
            this.endMinuteRound = this.endMinuteExact - res;
            this.endDateRound.add(12, this.endMinuteRound);
            this.endDateRound.add(11, this.endHourRound);
         } else {
            this.endMinuteRound = this.endMinuteExact - res + MinRangeRounding;
            this.endDateRound.add(12, this.endMinuteRound);
            this.endDateRound.add(11, this.endHourExact);
            this.endHourRound = this.endDateRound.get(11);
            this.endMinuteRound = this.endDateRound.get(12);
         }
      }

      public int getIndexDay() {
         return this.indexDay;
      }

      public void setIndexDay(int indexDay) {
         this.indexDay = indexDay;
      }

      public int getValueFunction() {
         return this.valueFunction;
      }

      public void setValueFunction(int valueFunction) {
         this.valueFunction = valueFunction;
      }

      public String getDescription() {
         return this.description;
      }

      public void setDescription(String description) {
         this.description = description;
      }

      public Calendar getBeginDateExact() {
         return this.beginDateExact;
      }

      public void setBeginDateExact(Calendar beginDateExact) {
         this.beginDateExact = beginDateExact;
         this.beginHourExact = beginDateExact.get(11);
         this.beginMinuteExact = beginDateExact.get(12);
      }

      public void setBeginDateExact(int beginHour, int beginMin) {
         Calendar date = GregorianCalendar.getInstance();
         date.clear();
         date.add(11, beginHour);
         date.add(12, beginMin);
         this.beginDateExact = date;
      }

      public int getBeginHourExact() {
         return this.beginHourExact;
      }

      public void setBeginHourExact(int beginHourExact) {
         this.beginHourExact = beginHourExact;
      }

      public int getBeginMinuteExact() {
         return this.beginMinuteExact;
      }

      public void setBeginMinuteExact(int beginMinuteExact) {
         this.beginMinuteExact = beginMinuteExact;
      }

      public Calendar getBeginDateRound() {
         return this.beginDateRound;
      }

      public void setBeginDateRound(Calendar beginDateRound) {
         this.beginDateRound = beginDateRound;
         this.beginHourRound = beginDateRound.get(11);
         this.beginMinuteRound = beginDateRound.get(12);
      }

      public void setBeginDateRound(int beginHour, int beginMin) {
         Calendar date = GregorianCalendar.getInstance();
         date.clear();
         date.add(11, beginHour);
         date.add(12, beginMin);
         this.beginDateRound = date;
      }

      public int getBeginHourRound() {
         return this.beginHourRound;
      }

      public void setBeginHourRound(int beginHourRound) {
         this.beginHourRound = beginHourRound;
      }

      public int getBeginMinuteRound() {
         return this.beginMinuteRound;
      }

      public void setBeginMinuteRound(int beginMinuteRound) {
         this.beginMinuteRound = beginMinuteRound;
      }

      public Calendar getEndDateExact() {
         return this.endDateExact;
      }

      public void setEndDateExact(Calendar endDateExact) {
         this.endDateExact = endDateExact;
         this.endHourExact = endDateExact.get(11);
         this.endMinuteExact = endDateExact.get(12);
      }

      public void setEndDateExact(int beginHour, int beginMin) {
         Calendar date = GregorianCalendar.getInstance();
         date.clear();
         date.add(11, beginHour);
         date.add(12, beginMin);
         this.endDateExact = date;
      }

      public int getEndHourExact() {
         return this.endHourExact;
      }

      public void setEndHourExact(int endHourExact) {
         this.endHourExact = endHourExact;
      }

      public int getEndMinuteExact() {
         return this.endMinuteExact;
      }

      public void setEndMinuteExact(int endMinuteExact) {
         this.endMinuteExact = endMinuteExact;
      }

      public Calendar getEndDateRound() {
         return this.endDateRound;
      }

      public void setEndDateRound(Calendar endDateRound) {
         this.endDateRound = endDateRound;
         this.endHourRound = endDateRound.get(11);
         this.endMinuteRound = endDateRound.get(12);
      }

      public void setEndDateRound(int beginHour, int beginMin) {
         Calendar date = GregorianCalendar.getInstance();
         date.clear();
         date.add(11, beginHour);
         date.add(12, beginMin);
         this.endDateRound = date;
      }

      public int getEndHourRound() {
         return this.endHourRound;
      }

      public void setEndHourRound(int endHourRound) {
         this.endHourRound = endHourRound;
      }

      public int getEndMinuteRound() {
         return this.endMinuteRound;
      }

      public void setEndMinuteRound(int endMinuteRound) {
         this.endMinuteRound = endMinuteRound;
      }

      @Override
      public int compareTo(Object o) {
         int res = 0;
         Calendar thisDate = this.getBeginDateExact();
         Calendar passedDate = ((FAAC_Timer.TimerEvent)o).getBeginDateExact();
         if (passedDate != null && thisDate != null) {
            res = thisDate.getTime().compareTo(passedDate.getTime());
         }

         return res;
      }

      public UUID getUniqueId() {
         return this.uniqueId;
      }

      public void setUniqueId(UUID uniqueId) {
         this.uniqueId = uniqueId;
      }
   }
}
