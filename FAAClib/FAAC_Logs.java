package FAAClib;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class FAAC_Logs {
   private LinkedList<FAAC_Logs.Log> logList;
   public FAAC_Logs.Log nullLog;
   public FAAC_Logs.LogDate nullLogDate;
   public static final int numLog_0 = 128;
   public static final int numLog_A = 128;
   public static final int numLog_B = 128;
   public static final int numLog_C = 352;
   private int nbLogsInMemory;
   public int nbLogsOnBoard = 128;
   private static int NbLogsInMemory;
   public static final int nullEvent = 0;
   public static final int E01_ACCENSIONE_SCHEDA = 1;
   public static final int E02_SCHEDA_GUASTA = 2;
   public static final int E03_PASSAGGIO_A_BATTERIA = 3;
   public static final int E04_PASSAGGIO_A_RETE = 4;
   public static final int E05_INIZIO_APERTURA = 5;
   public static final int E06_INIZIO_CHIUSURA = 6;
   public static final int E07_OSTACOLO1 = 7;
   public static final int E08_CC_BUS2EASY = 8;
   public static final int E09_ERRORE_FAILSAFE = 9;
   public static final int E10_TIME_OUT = 10;
   public static final int E11_ERROR_FC1 = 11;
   public static final int E12_ERROR_FC2 = 12;
   public static final int E13_ERROR_2EASY = 13;
   public static final int E14_REQ_ASSISTENZA = 14;
   public static final int E15_RESET_NUM_CICLI = 15;
   public static final int E16_LOCK1_CC = 16;
   public static final int E17_LOCK2_CC = 17;
   public static final int E18_RADIO_FULL = 18;
   public static final int E19_TIMER_ON = 19;
   public static final int E20_TIMER_OFF = 20;
   public static final int E21_TEACH_RADIO1 = 21;
   public static final int E22_TEACH_RADIO2 = 22;
   public static final int E23_ERROR_ENCODER1 = 23;
   public static final int E24_ERROR_ENCODER2 = 24;
   public static final int E25_ERROR_CKS_SETUP = 25;
   public static final int E26_SAVE_PROG = 26;
   public static final int E27_2EASY_ISCRIZIONE = 27;
   public static final int E28_APERTO = 28;
   public static final int E29_CHIUSO = 29;
   public static final int E30_PAUSA = 30;
   public static final int E31_FERMO = 31;
   public static final int E32_INIZIO_APERTURA_INDIP1 = 32;
   public static final int E33_INIZIO_CHIUSURA_INDIP1 = 33;
   public static final int E34_INIZIO_APERTURA_INDIP2 = 34;
   public static final int E35_INIZIO_CHIUSURA_INDIP2 = 35;
   public static final int E36_INIZIO_HOLDPOSITION = 36;
   public static final int E37_SETUP_START = 37;
   public static final int E38_SETUP_END = 38;
   public static final int E39_SETUP_ABORTED = 39;
   public static final int E40_ACCAVALLAMENTO_ANTA = 40;
   public static final int E41_IN_SLEEP = 41;
   public static final int E42_VACC_FAIL = 42;
   public static final int E43_CONFIG_ERROR = 43;
   public static final int E44_BLOCCO_TERMICO = 44;
   public static final int E45_RADIO_CLEAR = 45;
   public static final int E46_BUS_APPELLO_ERROR = 46;
   public static final int E47_2EASY_FAILSAFE = 47;
   public static final int E48_FAIL_MOTOR1 = 48;
   public static final int E49_FAIL_MOTOR2 = 49;
   public static final int E50_DEEP_SLEEP = 50;
   public static final int E51_PARTIAL_OPEN = 51;
   public static final int E52_PARTIAL_CLOSE = 52;
   public static final int E53_UL_OSTACOLO = 53;
   public static final int IN128_OPENA = -128;
   public static final int IN129_OPENB = -127;
   public static final int IN130_OPENAA = -126;
   public static final int IN131_OPENBA = -125;
   public static final int IN132_OPENAprio = -124;
   public static final int IN133_OPENBprio = -123;
   public static final int IN134_EmergOpen = -122;
   public static final int IN135_EmergClose = -121;
   public static final int IN136_Timer = -120;
   public static final int IN137_SicurOpenCmd = -119;
   public static final int IN138_SicurClose = -118;
   public static final int IN139_SicurOpen = -117;
   public static final int IN140_IndipMov = -116;
   public static final int IN141_FinePausaA = -115;
   public static final int IN142_Close = -114;
   public static final int IN143_ClosePrio = -113;
   public static final int IN145_PrenotClose = -111;
   public static final int IN147_TimerProg = -109;
   public static final int IN148_Stop = -108;
   public static final int IN149_TypeIn1 = -107;
   public static final int IN150_TypeIn2 = -106;
   public static final int IN151_TypeIn3 = -105;
   public static final int IN152_TypeIn4 = -104;
   public static final int IN153_TypeIn5 = -103;
   public static final int IN154_Error = -102;
   public static final int IN155_TypeRadio1Dec = -101;
   public static final int IN156_TypeRadio2Dec = -100;
   public static final int IN157_TypeRadio1Xf = -99;
   public static final int IN158_TypeRadio2Xf = -98;
   public static final int IN159_TypeExt = -97;
   public static final int IN160_TypeBus = -96;
   public static final int IN161_TypeFinecorsa = -95;
   public static final int IN162_TypeEmerg = -94;
   public static final int IN163_NoClose = -93;
   public static final int IN164_NoOpen = -92;
   public static final int IN165_CoastOpen = -91;
   public static final int IN166_CoastClose = -90;
   public static final int IN167_CoastOpenClose = -89;
   public static final int IN170_Intrusion = -86;
   public static final int IN255_FineMov = -1;

   public FAAC_Logs() {
      this.nbLogsInMemory = this.nbLogsOnBoard;
      NbLogsInMemory = this.nbLogsInMemory;
      this.nullLog = new FAAC_Logs.Log();
      this.nullLogDate = new FAAC_Logs.LogDate(0, 0, 0, 0, 0);
      this.nullLog.logDate = this.nullLogDate;
      this.nullLog.event1 = 0;
      this.nullLog.event2 = 0;
      this.nullLog.event3 = 0;
      this.logList = new LinkedList<>();

      for (int i = 0; i < this.nbLogsInMemory; i++) {
         this.logList.add(this.nullLog);
      }
   }

   public void addLog(FAAC_Logs.Log log) {
      if (this.logList.size() >= this.getNbLogsInMemory()) {
         this.logList.remove(this.logList.size() - 1);
      }

      this.logList.addFirst(log);
      this.sortLogList_MostRecentFirst();
   }

   public static void reverse(FAAC_Logs.Log[] b) {
      int left = 0;

      for (int right = b.length - 1; left < right; right--) {
         FAAC_Logs.Log temp = b[left];
         b[left] = b[right];
         b[right] = temp;
         left++;
      }
   }

   public final void sortLogList_MostRecentFirst() {
      FAAC_Logs.Log[] logArray = new FAAC_Logs.Log[this.getNbLogsInMemory()];
      logArray = this.logList.toArray(logArray);
      Arrays.sort((Object[])logArray);
      this.logList.clear();
      this.logList.addAll(Arrays.asList(logArray));
   }

   public LinkedList getLogList() {
      return this.logList;
   }

   public void setLogList(LinkedList logList) {
      this.logList = logList;
   }

   public static int GetNbLogsOnBoard() {
      return NbLogsInMemory;
   }

   public int getNbLogsOnBoard() {
      return this.nbLogsOnBoard;
   }

   public void setNbLogsOnBoard(int nbLogsOnBoard) {
      this.nbLogsInMemory = nbLogsOnBoard;
      this.nbLogsOnBoard = nbLogsOnBoard;
      NbLogsInMemory = nbLogsOnBoard;
      this.nullLog = new FAAC_Logs.Log();
      this.nullLogDate = new FAAC_Logs.LogDate(0, 0, 0, 0, 0);
      this.nullLog.logDate = this.nullLogDate;
      this.nullLog.event1 = 0;
      this.nullLog.event2 = 0;
      this.nullLog.event3 = 0;
      this.logList = new LinkedList<>();

      for (int i = 0; i < this.getNbLogsInMemory(); i++) {
         this.logList.add(this.nullLog);
      }
   }

   public int getNbLogsInMemory() {
      return this.nbLogsInMemory;
   }

   public static class Log implements Comparable {
      private FAAC_Logs.LogDate logDate = new FAAC_Logs.LogDate();
      private int event1 = 1;
      private int event2 = 1;
      private int event3 = 1;
      private char logSwVersion_SW1 = '0';
      private char logSwVersion_SW2 = '0';
      private int indexLog = -1;

      public boolean isNullLog() {
         boolean bRes = false;
         if (this.logDate.day == 0
            && this.logDate.month == 0
            && this.logDate.year == 0
            && this.logDate.minute == 0
            && this.logDate.hour == 0
            && this.event1 == 0
            && this.event2 == 0
            && this.event3 == 0) {
            bRes = true;
         } else {
            bRes = false;
         }

         return bRes;
      }

      public int getEvent1() {
         return this.event1;
      }

      public void setEvent1(int event1) {
         this.event1 = event1;
      }

      public int getEvent2() {
         return this.event2;
      }

      public void setEvent2(int event2) {
         this.event2 = event2;
      }

      public int getEvent3() {
         return this.event3;
      }

      public void setEvent3(int event3) {
         this.event3 = event3;
      }

      public FAAC_Logs.LogDate getLogDate() {
         return this.logDate;
      }

      public void setLogDate(FAAC_Logs.LogDate logDate) {
         this.logDate = logDate;
      }

      @Override
      public boolean equals(Object obj) {
         return obj != null
            ? this.getLogDate().getDay() == ((FAAC_Logs.Log)obj).getLogDate().getDay()
               && this.getLogDate().getMonth() == ((FAAC_Logs.Log)obj).getLogDate().getMonth()
               && this.getLogDate().getYear() == ((FAAC_Logs.Log)obj).getLogDate().getYear()
               && this.getLogDate().getHour() == ((FAAC_Logs.Log)obj).getLogDate().getHour()
               && this.getLogDate().getMinute() == ((FAAC_Logs.Log)obj).getLogDate().getMinute()
               && this.getEvent1() == ((FAAC_Logs.Log)obj).getEvent1()
               && this.getEvent2() == ((FAAC_Logs.Log)obj).getEvent2()
               && this.getEvent3() == ((FAAC_Logs.Log)obj).getEvent3()
            : false;
      }

      @Override
      public int compareTo(Object o) {
         int res = 0;
         if (this.isNullLog()) {
            return 1;
         } else if (o != null && !((FAAC_Logs.Log)o).isNullLog()) {
            Calendar thisDate = GregorianCalendar.getInstance();
            thisDate.clear();
            thisDate.set(5, this.logDate.day);
            thisDate.set(2, this.logDate.month - 1);
            thisDate.set(1, this.logDate.year - 1970);
            thisDate.set(11, this.logDate.hour);
            thisDate.set(12, this.logDate.minute);
            Calendar passedDate = GregorianCalendar.getInstance();
            passedDate.clear();
            passedDate.set(5, ((FAAC_Logs.Log)o).getLogDate().getDay());
            passedDate.set(2, ((FAAC_Logs.Log)o).getLogDate().getMonth() - 1);
            passedDate.set(1, ((FAAC_Logs.Log)o).getLogDate().getYear() - 1970);
            passedDate.set(11, ((FAAC_Logs.Log)o).getLogDate().getHour());
            passedDate.set(12, ((FAAC_Logs.Log)o).getLogDate().getMinute());
            if (thisDate != null && passedDate != null) {
               res = thisDate.getTime().compareTo(passedDate.getTime());
               res *= -1;
               if (res == 0) {
                  if (this.indexLog != -1 && ((FAAC_Logs.Log)o).indexLog != -1) {
                     if (this.indexLog > ((FAAC_Logs.Log)o).indexLog) {
                        res = 1;
                     } else {
                        res = -1;
                     }
                  } else if (this.indexLog != -1) {
                     res = 1;
                  } else {
                     res = -1;
                  }
               }

               return res;
            } else if (thisDate == null && passedDate != null) {
               return 1;
            } else {
               return thisDate != null && passedDate == null ? -1 : 0;
            }
         } else {
            return -1;
         }
      }

      public char getLogSwVersion_SW1() {
         return this.logSwVersion_SW1;
      }

      public void setLogSwVersion_SW1(char logSwVersion_SW1) {
         this.logSwVersion_SW1 = logSwVersion_SW1;
      }

      public char getLogSwVersion_SW2() {
         return this.logSwVersion_SW2;
      }

      public void setLogSwVersion_SW2(char logSwVersion_SW2) {
         this.logSwVersion_SW2 = logSwVersion_SW2;
      }

      public int getIndexLog() {
         return this.indexLog;
      }

      public void setIndexLog(int indexLog) {
         this.indexLog = indexLog;
      }
   }

   public static class LogDate implements Comparable {
      private int minute;
      private int hour;
      private int day;
      private int month;
      private int year;

      public LogDate() {
         this.minute = 0;
         this.hour = 0;
         this.day = 1;
         this.month = 1;
         this.year = 2000;
      }

      public LogDate(int dd, int MM, int yyyy, int HH, int mm) {
         this.minute = mm;
         this.hour = HH;
         this.day = dd;
         this.month = MM;
         this.year = yyyy;
      }

      public int getMinute() {
         return this.minute;
      }

      public void setMinute(int minute) {
         this.minute = minute;
      }

      public int getHour() {
         return this.hour;
      }

      public void setHour(int hour) {
         this.hour = hour;
      }

      public int getDay() {
         return this.day;
      }

      public void setDay(int day) {
         this.day = day;
      }

      public int getMonth() {
         return this.month;
      }

      public void setMonth(int month) {
         this.month = month;
      }

      public int getYear() {
         return this.year;
      }

      public void setYear(int year) {
         this.year = year;
      }

      @Override
      public String toString() {
         Calendar date = GregorianCalendar.getInstance();
         date.clear();
         date.set(5, this.day);
         date.set(2, this.month - 1);
         date.set(1, this.year);
         date.set(11, this.hour);
         date.set(12, this.minute);
         String DATE_FORMAT = "dd/MM/yy HH:mm";
         SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
         return sdf.format(date.getTime());
      }

      @Override
      public int compareTo(Object o) {
         int res = 0;
         Calendar thisDate = GregorianCalendar.getInstance();
         thisDate.clear();
         thisDate.set(5, this.day);
         thisDate.set(2, this.month - 1);
         thisDate.set(1, this.year - 1970);
         thisDate.set(11, this.hour);
         thisDate.set(12, this.minute);
         Calendar passedDate = GregorianCalendar.getInstance();
         passedDate.clear();
         passedDate.set(5, ((FAAC_Logs.LogDate)o).getDay());
         passedDate.set(2, ((FAAC_Logs.LogDate)o).getMonth() - 1);
         passedDate.set(1, ((FAAC_Logs.LogDate)o).getYear() - 1970);
         passedDate.set(11, ((FAAC_Logs.LogDate)o).getHour());
         passedDate.set(12, ((FAAC_Logs.LogDate)o).getMinute());
         if (thisDate != null && passedDate != null) {
            return thisDate.getTime().compareTo(passedDate.getTime());
         } else if (thisDate == null && passedDate != null) {
            return -1;
         } else {
            return thisDate != null && passedDate == null ? 1 : 0;
         }
      }
   }
}
