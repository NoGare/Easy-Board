package FAAClib;

public class FAAC_GCOM {
   private String deviceName = "";
   private FAAC_GCOM.SmsEnableFlag smsEnableFlag = new FAAC_GCOM.SmsEnableFlag();
   private int resendPeriod_hours = 12;
   private int resendTimes = 2;
   private String numGcom;
   private PhoneBook pbook = new PhoneBook();
   public static final int maxGcomNameLenght = 23;
   public static String defFile = "default/defGCOM.SGC";

   public FAAC_GCOM() {
      this.numGcom = "-";
   }

   public String getDeviceName() {
      return this.deviceName;
   }

   public PhoneBook getPhoneBook() {
      return this.pbook;
   }

   public void setDeviceName(String deviceName) {
      this.deviceName = deviceName;
   }

   public FAAC_GCOM.SmsEnableFlag getSmsEnableFlag() {
      return this.smsEnableFlag;
   }

   public void setSmsEnableFlag(FAAC_GCOM.SmsEnableFlag smsEnableFlag) {
      this.smsEnableFlag = smsEnableFlag;
   }

   public int getResendPeriod_hours() {
      return this.resendPeriod_hours;
   }

   public void setResendPeriod_hours(int resendPeriod_hours) {
      this.resendPeriod_hours = resendPeriod_hours;
   }

   public int getResendTimes() {
      return this.resendTimes;
   }

   public void setResendTimes(int resendTimes) {
      this.resendTimes = resendTimes;
   }

   public String getNumGcom() {
      return this.numGcom;
   }

   public void setNumGcom(String numGcom) {
      this.numGcom = numGcom;
   }

   public class SmsEnableFlag extends Union32_Abstraction {
      public void set20_Rete(boolean bVal) {
         this.bits[20] = bVal;
      }

      public boolean get20_Rete() {
         return this.bits[20];
      }

      public void set19_Battery(boolean bVal) {
         this.bits[19] = bVal;
      }

      public boolean get19_Battery() {
         return this.bits[19];
      }

      public void set18_Loop(boolean bVal) {
         this.bits[18] = bVal;
      }

      public boolean get18_Loop() {
         return this.bits[18];
      }

      public void set17_Effraction(boolean bVal) {
         this.bits[17] = bVal;
      }

      public boolean get17_Effraction() {
         return this.bits[17];
      }

      public void set16_CcLamp(boolean bVal) {
         this.bits[16] = bVal;
      }

      public boolean get16_CcLamp() {
         return this.bits[16];
      }

      public void set15_CcLock(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_CcLock() {
         return this.bits[15];
      }

      public void set14_ReqAss(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_ReqAss() {
         return this.bits[14];
      }

      public void set13_Encoder2(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_Encoder2() {
         return this.bits[13];
      }

      public void set12_Encoder1(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_Encoder1() {
         return this.bits[12];
      }

      public void set11_FailMot2(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_FailMot2() {
         return this.bits[11];
      }

      public void set10_FailMot1(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_FailMot1() {
         return this.bits[10];
      }

      public void set09_AstaDivelta(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_AstaDivelta() {
         return this.bits[9];
      }

      public void set08_Vacc(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_Vacc() {
         return this.bits[8];
      }

      public void set07_Finecorsa2(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_Finecorsa2() {
         return this.bits[7];
      }

      public void set06_Finecorsa1(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_Finecorsa1() {
         return this.bits[6];
      }

      public void set05_Failsafe(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_Failsafe() {
         return this.bits[5];
      }

      public void set04_Bus2Easy(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_Bus2Easy() {
         return this.bits[4];
      }

      public void set03_Timeout(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_Timeout() {
         return this.bits[3];
      }

      public void set02_FailBoard(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_FailBoard() {
         return this.bits[2];
      }

      public void set01_ErrComm(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_ErrComm() {
         return this.bits[1];
      }

      public void set00_FailGCOM(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_FailGCOM() {
         return this.bits[0];
      }
   }
}
