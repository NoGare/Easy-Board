package FAAClib;

public abstract class FAAC_Monitor {
   protected String boardType = "E145";
   private String boardSerialId = "";
   private byte versionMem = 67;
   private char boardSwVersion_SW1 = '0';
   private char boardSwVersion_SW2 = '0';
   private int nbCyclesAbsolute = 0;
   private int lastFW_day = 1;
   private int lastFW_month = 1;
   private int lastFW_year = 0;
   private int forceMotor1 = 0;
   private int forceMotor2 = 0;
   private int voltageAcc_10mV = 0;
   protected FAAC_Monitor.Errors error = new FAAC_Monitor.Errors();
   protected FAAC_Monitor.Allarms allarm = new FAAC_Monitor.Allarms();
   protected FAAC_Monitor.Battery battery;
   protected FAAC_Monitor.AllarmsBis allarmBis = new FAAC_Monitor.AllarmsBis();
   private int positionMotor1_percentage;
   private int positionMotor2_percentage;
   private FAAC_Monitor.PhysicalInsOuts physicalIns;
   private int timeCountDownPause_sec;
   private byte logicSet;
   private FAAC_Monitor.Subscribed2easy subscribed2easy;
   private FAAC_Monitor.LogicIns logicIns;
   private FAAC_Monitor.ActiveSec2easy activeSec2easy;
   private FAAC_Monitor.ActiveDat2easy activeDat2easy;
   private FAAC_Monitor.LedState ledState;
   private FAAC_Monitor.AccessoryMsg accessoryMsg;
   private int nbCyclesPartial;
   private byte state;
   private int minutes;
   private int hour;
   private int day;
   private int dayOfTheWeek;
   private int month;
   private int year;
   private FAAC_Monitor.Device2easyError device2easyError;
   private int voltageMot_10mV;
   private int voltageBat_10mV;
   private int currentMot1_10mA;
   private int currentMot2_10mA;
   private int speedMot1;
   private int speedMot2;
   public static final int ForceMotorMin = 0;
   public static final int ForceMotorMax = 50;
   public static final byte byteLogicCU = 0;
   public static final byte byteLogicA = 1;
   public static final byte byteLogicAP = 2;
   public static final byte byteLogicAT = 3;
   public static final byte byteLogicB = 4;
   public static final byte byteLogicBC = 5;
   public static final byte byteLogicC = 6;
   public static final byte byteLogicE = 7;
   public static final byte byteLogicEP = 8;
   public static final byte byteLogicS = 9;
   public static final byte byteLogicSA = 10;
   public static final byte byteLogicSP = 11;
   public static final byte byteLogicA1 = 12;
   public static final byte byteStateChiuso = 0;
   public static final byte byteStateAperto = 1;
   public static final byte byteStateFermoApre = 2;
   public static final byte byteStateFermoChiude = 3;
   public static final byte byteStatePausa = 4;
   public static final byte byteStateApre = 5;
   public static final byte byteStateChiude = 6;
   public static final byte byteStateAppello = 7;
   public static final byte byteStateFailsafe = 8;
   public static final byte byteStatePrelampOpen = 9;
   public static final byte byteStatePrelampClose = 10;
   public static final byte byteStateEmergOpen = 11;
   public static final byte byteStateEmergClose = 12;
   public static final byte byteStateSleep = 13;
   public static final byte byteStateSetup0 = -128;
   public static final byte byteStateSetup1 = -127;
   public static final byte byteStateSetup2 = -126;
   public static final byte byteStateSetup3 = -125;
   public static final byte byteStateSetup4 = -124;
   public static final byte byteStateSetup5 = -123;
   public static final byte byteStateSetup6 = -122;
   public static final byte byteStateSetup7 = -121;
   public static final byte byteStateSetup8 = -120;
   public static final int monday = 1;
   public static final int tuesday = 2;
   public static final int wednesday = 3;
   public static final int thursday = 4;
   public static final int friday = 5;
   public static final int saturday = 6;
   public static final int sunday = 7;
   public static final byte versionMemStd = 48;
   public static final byte versionMemA = 65;
   public static final byte versionMemB = 66;
   public static final byte versionMemC = 67;

   public FAAC_Monitor() {
      this.battery = new FAAC_Monitor.Battery();
      this.allarmBis.set08_ov40_enc2easyAnta1(false);
      this.allarmBis.set09_ov41_enc2easyAnta2(false);
      this.allarmBis.set10_ov42_gatecoderAnta1(false);
      this.allarmBis.set11_ov43_gatecoderAnta2(false);
      this.positionMotor1_percentage = 0;
      this.positionMotor2_percentage = 0;
      this.physicalIns = new FAAC_Monitor.PhysicalInsOuts();
      this.timeCountDownPause_sec = 0;
      this.subscribed2easy = new FAAC_Monitor.Subscribed2easy();
      this.logicIns = new FAAC_Monitor.LogicIns();
      this.activeSec2easy = new FAAC_Monitor.ActiveSec2easy();
      this.activeDat2easy = new FAAC_Monitor.ActiveDat2easy();
      this.ledState = new FAAC_Monitor.LedState();
      this.accessoryMsg = new FAAC_Monitor.AccessoryMsg();
      this.nbCyclesPartial = 0;
      this.day = 1;
      this.dayOfTheWeek = 6;
      this.year = 0;
      this.month = 1;
      this.hour = 0;
      this.minutes = 0;
      this.device2easyError = new FAAC_Monitor.Device2easyError();
      this.voltageMot_10mV = 0;
      this.voltageBat_10mV = 0;
      this.currentMot1_10mA = 0;
      this.currentMot2_10mA = 0;
      this.speedMot1 = 0;
      this.speedMot2 = 0;
   }

   public abstract boolean isSomeErrorsPresent();

   public abstract boolean isSomeAllarmsPresent();

   public int getForceMotor1() {
      return this.forceMotor1;
   }

   public void setForceMotor1(int forceMotor1) {
      this.forceMotor1 = forceMotor1;
   }

   public int getForceMotor2() {
      return this.forceMotor2;
   }

   public void setForceMotor2(int forceMotor2) {
      this.forceMotor2 = forceMotor2;
   }

   public int getVoltageAcc_10mV() {
      return this.voltageAcc_10mV;
   }

   public void setVoltageAcc_10mV(int voltageAcc_10mV) {
      this.voltageAcc_10mV = voltageAcc_10mV;
   }

   public FAAC_Monitor.Errors getError() {
      return this.error;
   }

   public void setError(FAAC_Monitor.Errors error) {
      this.error = error;
   }

   public FAAC_Monitor.Allarms getAllarm() {
      return this.allarm;
   }

   public FAAC_Monitor.Battery getBattery() {
      return this.battery;
   }

   public void setAllarm(FAAC_Monitor.Allarms allarm) {
      this.allarm = allarm;
   }

   public FAAC_Monitor.AllarmsBis getAllarmBis() {
      return this.allarmBis;
   }

   public void setAllarmBis(FAAC_Monitor.AllarmsBis allarmBis) {
      this.allarmBis = allarmBis;
   }

   public int getPositionMotor1_percentage() {
      return this.positionMotor1_percentage;
   }

   public void setPositionMotor1_percentage(int positionMotor1_percentage) {
      this.positionMotor1_percentage = positionMotor1_percentage;
   }

   public int getPositionMotor2_percentage() {
      return this.positionMotor2_percentage;
   }

   public void setPositionMotor2_percentage(int positionMotor2_percentage) {
      this.positionMotor2_percentage = positionMotor2_percentage;
   }

   public FAAC_Monitor.PhysicalInsOuts getPhysicalIns() {
      return this.physicalIns;
   }

   public void setPhysicalIns(FAAC_Monitor.PhysicalInsOuts physicalIns) {
      this.physicalIns = physicalIns;
   }

   public int getTimeCountDownPause_sec() {
      return this.timeCountDownPause_sec;
   }

   public void setTimeCountDownPause_sec(int timeCountDownPause_sec) {
      this.timeCountDownPause_sec = timeCountDownPause_sec;
   }

   public byte getLogicSet() {
      return this.logicSet;
   }

   public void setLogicSet(byte logicSet) {
      this.logicSet = logicSet;
   }

   public FAAC_Monitor.Subscribed2easy getSubscribed2easy() {
      return this.subscribed2easy;
   }

   public void setSubscribed2easy(FAAC_Monitor.Subscribed2easy subscribed2easy) {
      this.subscribed2easy = subscribed2easy;
   }

   public FAAC_Monitor.LogicIns getLogicIns() {
      return this.logicIns;
   }

   public void setLogicIns(FAAC_Monitor.LogicIns logicIns) {
      this.logicIns = logicIns;
   }

   public FAAC_Monitor.ActiveSec2easy getActiveSec2easy() {
      return this.activeSec2easy;
   }

   public void setActiveSec2easy(FAAC_Monitor.ActiveSec2easy activeSec2easy) {
      this.activeSec2easy = activeSec2easy;
   }

   public FAAC_Monitor.LedState getLedState() {
      return this.ledState;
   }

   public void setLedState(FAAC_Monitor.LedState ledState) {
      this.ledState = ledState;
   }

   public FAAC_Monitor.AccessoryMsg getAccessoryMsg() {
      return this.accessoryMsg;
   }

   public void setAccessoryMsg(FAAC_Monitor.AccessoryMsg accessoryMsg) {
      this.accessoryMsg = accessoryMsg;
   }

   public int getNbCyclesPartial() {
      return this.nbCyclesPartial;
   }

   public void setNbCyclesPartial(int nbCyclesPartial) {
      this.nbCyclesPartial = nbCyclesPartial;
   }

   public byte getState() {
      return this.state;
   }

   public void setState(byte state) {
      this.state = state;
   }

   public int getMinutes() {
      return this.minutes;
   }

   public void setMinutes(int minutes) {
      this.minutes = minutes;
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

   public int getDayOfTheWeek() {
      return this.dayOfTheWeek;
   }

   public void setDayOfTheWeek(int dayOfTheWeek) {
      this.dayOfTheWeek = dayOfTheWeek;
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

   public String getBoardType() {
      return this.boardType;
   }

   public void setBoardType(String boardType) {
      this.boardType = boardType;
   }

   public char getBoardSwVersion_SW1() {
      return this.boardSwVersion_SW1;
   }

   public void setBoardSwVersion_SW1(char boardSwVersion_SW1) {
      this.boardSwVersion_SW1 = boardSwVersion_SW1;
   }

   public char getBoardSwVersion_SW2() {
      return this.boardSwVersion_SW2;
   }

   public void setBoardSwVersion_SW2(char boardSwVersion_SW2) {
      this.boardSwVersion_SW2 = boardSwVersion_SW2;
   }

   public int getNbCyclesAbsolute() {
      return this.nbCyclesAbsolute;
   }

   public void setNbCyclesAbsolute(int nbCyclesAbsolute) {
      this.nbCyclesAbsolute = nbCyclesAbsolute;
   }

   public String getBoardSerialId() {
      return this.boardSerialId;
   }

   public void setBoardSerialId(String boardSerialId) {
      this.boardSerialId = boardSerialId;
   }

   public FAAC_Monitor.Device2easyError getDevice2easyError() {
      return this.device2easyError;
   }

   public void setDevice2easyError(FAAC_Monitor.Device2easyError device2easyError) {
      this.device2easyError = device2easyError;
   }

   public int getLastFW_day() {
      return this.lastFW_day;
   }

   public void setLastFW_day(int lastFW_day) {
      this.lastFW_day = lastFW_day;
   }

   public int getLastFW_month() {
      return this.lastFW_month;
   }

   public void setLastFW_month(int lastFW_month) {
      this.lastFW_month = lastFW_month;
   }

   public int getLastFW_year() {
      return this.lastFW_year;
   }

   public void setLastFW_year(int lastFW_year) {
      this.lastFW_year = lastFW_year;
   }

   public FAAC_Monitor.ActiveDat2easy getActiveDat2easy() {
      return this.activeDat2easy;
   }

   public void setActiveDat2easy(FAAC_Monitor.ActiveDat2easy activeDat2easy) {
      this.activeDat2easy = activeDat2easy;
   }

   public int getVoltageMot_10mV() {
      return this.voltageMot_10mV;
   }

   public void setVoltageMot_10mV(int voltageMot_10mV) {
      this.voltageMot_10mV = voltageMot_10mV;
   }

   public int getVoltageBat_10mV() {
      return this.voltageBat_10mV;
   }

   public void setVoltageBat_10mV(int voltageBat_10mV) {
      this.voltageBat_10mV = voltageBat_10mV;
   }

   public int getCurrentMot1_10mA() {
      return this.currentMot1_10mA;
   }

   public void setCurrentMot1_10mA(int currentMot1_10mA) {
      this.currentMot1_10mA = currentMot1_10mA;
   }

   public int getCurrentMot2_10mA() {
      return this.currentMot2_10mA;
   }

   public void setCurrentMot2_10mA(int currentMot2_10mA) {
      this.currentMot2_10mA = currentMot2_10mA;
   }

   public int getSpeedMot1() {
      return this.speedMot1;
   }

   public void setSpeedMot1(int speedMot1) {
      this.speedMot1 = speedMot1;
   }

   public int getSpeedMot2() {
      return this.speedMot2;
   }

   public void setSpeedMot2(int speedMot2) {
      this.speedMot2 = speedMot2;
   }

   public byte getVersionMem() {
      return this.versionMem;
   }

   public void setVersionMem(byte versionMem) {
      this.versionMem = versionMem;
   }

   public class AccessoryMsg extends Union8_Abstraction {
      public void set07_newCfgGcom(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_newCfgGcom() {
         return this.bits[7];
      }

      public void set06_newIpNetcom(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_newIpNetcom() {
         return this.bits[6];
      }

      public void set05_newNbsGcom(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_newNbsGcom() {
         return this.bits[5];
      }

      public void set04_newPsw(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_newPsw() {
         return this.bits[4];
      }

      public void set03_newSimNbGcom(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_newSimNbGcom() {
         return this.bits[3];
      }
   }

   public class ActiveDat2easy extends Union16_Abstraction {
      public void set15_dat0000(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_dat0000() {
         return this.bits[15];
      }

      public void set14_dat0001(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_dat0001() {
         return this.bits[14];
      }

      public void set13_dat0010(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_dat0010() {
         return this.bits[13];
      }

      public void set12_dat0011(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_dat0011() {
         return this.bits[12];
      }

      public void set11_dat0100(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_dat0100() {
         return this.bits[11];
      }

      public void set10_dat0101(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_dat0101() {
         return this.bits[10];
      }

      public void set09_dat0110(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_dat0110() {
         return this.bits[9];
      }

      public void set08_dat0111(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_dat0111() {
         return this.bits[8];
      }

      public void set07_dat1000(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_dat1000() {
         return this.bits[7];
      }

      public void set06_dat1001(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_dat1001() {
         return this.bits[6];
      }

      public void set05_dat1010(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_dat1010() {
         return this.bits[5];
      }

      public void set04_dat1011(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_dat1011() {
         return this.bits[4];
      }

      public void set03_dat1100(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_dat1100() {
         return this.bits[3];
      }

      public void set02_dat1101(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_dat1101() {
         return this.bits[2];
      }
   }

   public class ActiveSec2easy extends Union16_Abstraction {
      public void set15_sec0000(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_sec0000() {
         return this.bits[15];
      }

      public void set14_sec0001(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_sec0001() {
         return this.bits[14];
      }

      public void set13_sec0010(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_sec0010() {
         return this.bits[13];
      }

      public void set12_sec0011(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_sec0011() {
         return this.bits[12];
      }

      public void set11_sec0100(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_sec0100() {
         return this.bits[11];
      }

      public void set10_sec0101(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_sec0101() {
         return this.bits[10];
      }

      public void set09_sec0110(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_sec0110() {
         return this.bits[9];
      }

      public void set08_sec0111(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_sec0111() {
         return this.bits[8];
      }

      public void set07_sec1000(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_sec1000() {
         return this.bits[7];
      }

      public void set06_sec1001(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_sec1001() {
         return this.bits[6];
      }

      public void set05_sec1010(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_sec1010() {
         return this.bits[5];
      }

      public void set04_sec1011(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_sec1011() {
         return this.bits[4];
      }

      public void set03_sec1100(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_sec1100() {
         return this.bits[3];
      }

      public void set02_sec1101(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_sec1101() {
         return this.bits[2];
      }

      public void set01_sec1110(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_sec1110() {
         return this.bits[1];
      }

      public void set00_sec1111(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_sec1111() {
         return this.bits[0];
      }
   }

   public class Allarms extends Union32_Abstraction {
      public void set31_allMissedHour(boolean bVal) {
         this.bits[31] = bVal;
      }

      public boolean get31_allMissedHour() {
         return this.bits[31];
      }

      public void set30_allCfgStdInUse(boolean bVal) {
         this.bits[30] = bVal;
      }

      public boolean get30_allCfgStdInUse() {
         return this.bits[30];
      }

      public void set29_allMovIndep(boolean bVal) {
         this.bits[29] = bVal;
      }

      public boolean get29_allMovIndep() {
         return this.bits[29];
      }

      public void set28_allFirstMov(boolean bVal) {
         this.bits[28] = bVal;
      }

      public boolean get28_allFirstMov() {
         return this.bits[28];
      }

      public void set27_allTimerFun(boolean bVal) {
         this.bits[27] = bVal;
      }

      public boolean get27_allTimerFun() {
         return this.bits[27];
      }

      public void set26_allCloseReq(boolean bVal) {
         this.bits[26] = bVal;
      }

      public boolean get26_allCloseReq() {
         return this.bits[26];
      }

      public void set25_allBatteryInUse(boolean bVal) {
         this.bits[25] = bVal;
      }

      public boolean get25_allBatteryInUse() {
         return this.bits[25];
      }

      public void set24_allCClamp(boolean bVal) {
         this.bits[24] = bVal;
      }

      public boolean get24_allCClamp() {
         return this.bits[24];
      }

      public void set23_allObstAnta2(boolean bVal) {
         this.bits[23] = bVal;
      }

      public boolean get23_allObstAnta2() {
         return this.bits[23];
      }

      public void set22_allObstAnta1(boolean bVal) {
         this.bits[22] = bVal;
      }

      public boolean get22_allObstAnta1() {
         return this.bits[22];
      }

      public void set21_allCcLock1(boolean bVal) {
         this.bits[21] = bVal;
      }

      public boolean get21_allCcLock1() {
         return this.bits[21];
      }

      public void set20_allCcLock2(boolean bVal) {
         this.bits[20] = bVal;
      }

      public boolean get20_allCcLock2() {
         return this.bits[20];
      }

      public void set19_allFailsafeTrad(boolean bVal) {
         this.bits[19] = bVal;
      }

      public boolean get19_allFailsafeTrad() {
         return this.bits[19];
      }

      public void set18_allFailsafe2easy(boolean bVal) {
         this.bits[18] = bVal;
      }

      public boolean get18_allFailsafe2easy() {
         return this.bits[18];
      }

      public void set17_allTimerData(boolean bVal) {
         this.bits[17] = bVal;
      }

      public boolean get17_allTimerData() {
         return this.bits[17];
      }

      public void set16_allOpenPartial(boolean bVal) {
         this.bits[16] = bVal;
      }

      public boolean get16_allOpenPartial() {
         return this.bits[16];
      }

      public void set15_allPartialCloseOngoing(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_allPartialCloseOngoing() {
         return this.bits[15];
      }

      public void set14_allPartialOpenOngoing(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_allPartialOpenOngoing() {
         return this.bits[14];
      }

      public void set12_allIdentOngoing(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_allIdentOngoing() {
         return this.bits[12];
      }

      public void set10_Emergency(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_Emergency() {
         return this.bits[10];
      }

      public void set09_allHoldposition(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_allHoldposition() {
         return this.bits[9];
      }

      public void set08_allNoStdPsw(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_allNoStdPsw() {
         return this.bits[8];
      }

      public void set07_allBusSleep(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_allBusSleep() {
         return this.bits[7];
      }

      public void set06_allFullRadioMem(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_allFullRadioMem() {
         return this.bits[6];
      }

      public void set05_allE2PROM(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_allE2PROM() {
         return this.bits[5];
      }

      public void set04_allNumObstClose(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_allNumObstClose() {
         return this.bits[4];
      }

      public void set03_allNumObstOpen(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_allNumObstOpen() {
         return this.bits[3];
      }

      public void set02_allAssistReq(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_allAssistReq() {
         return this.bits[2];
      }

      public void set01_allAppello2easy(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_allAppello2easy() {
         return this.bits[1];
      }
   }

   public class AllarmsBis extends Union16_Abstraction {
      public void set13_ov45_currCl2(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_ov45_currCl2() {
         return this.bits[13];
      }

      public void set12_ov44_currCl1(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_ov44_currCl1() {
         return this.bits[12];
      }

      public void set11_ov43_gatecoderAnta2(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_ov43_gatecoderAnta2() {
         return this.bits[11];
      }

      public void set10_ov42_gatecoderAnta1(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_ov42_gatecoderAnta1() {
         return this.bits[10];
      }

      public void set09_ov41_enc2easyAnta2(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_ov41_enc2easyAnta2() {
         return this.bits[9];
      }

      public void set08_ov40_enc2easyAnta1(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_ov40_enc2easyAnta1() {
         return this.bits[8];
      }

      public void set07_ov39_acqCh2(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_ov39_acqCh2() {
         return this.bits[7];
      }

      public void set06_ov38_acqCh1(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_ov38_acqCh1() {
         return this.bits[6];
      }

      public void set05_ov37_cfgStdBis(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_ov37_cfgStdBis() {
         return this.bits[5];
      }

      public void set04_ov36_allAccessoryBoard(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_ov36_allAccessoryBoard() {
         return this.bits[4];
      }

      public void set03_ov35_intrusion(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_ov35_intrusion() {
         return this.bits[3];
      }

      public void set02_ov34_allsleepLev1(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_ov34_allsleepLev1() {
         return this.bits[2];
      }

      public void set01_ov33_allRadioClearing(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_ov33_allRadioClearing() {
         return this.bits[1];
      }

      public void set00_ov32_allTimerActive(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_ov32_allTimerActive() {
         return this.bits[0];
      }
   }

   public class Battery extends Union8_Abstraction {
      public void set1_BatteryInUse(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get1_BatteryInUse() {
         return this.bits[1];
      }
   }

   public class Device2easyError extends Union32_Abstraction {
      public void set31_sec0000(boolean bVal) {
         this.bits[31] = bVal;
      }

      public boolean get31_sec0000() {
         return this.bits[31];
      }

      public void set30_sec0001(boolean bVal) {
         this.bits[30] = bVal;
      }

      public boolean get30_sec0001() {
         return this.bits[30];
      }

      public void set29_sec0010(boolean bVal) {
         this.bits[29] = bVal;
      }

      public boolean get29_sec0010() {
         return this.bits[29];
      }

      public void set28_sec0011(boolean bVal) {
         this.bits[28] = bVal;
      }

      public boolean get28_sec0011() {
         return this.bits[28];
      }

      public void set27_sec0100(boolean bVal) {
         this.bits[27] = bVal;
      }

      public boolean get27_sec0100() {
         return this.bits[27];
      }

      public void set26_sec0101(boolean bVal) {
         this.bits[26] = bVal;
      }

      public boolean get26_sec0101() {
         return this.bits[26];
      }

      public void set25_sec0110(boolean bVal) {
         this.bits[25] = bVal;
      }

      public boolean get25_sec0110() {
         return this.bits[25];
      }

      public void set24_sec0111(boolean bVal) {
         this.bits[24] = bVal;
      }

      public boolean get24_sec0111() {
         return this.bits[24];
      }

      public void set23_sec1000(boolean bVal) {
         this.bits[23] = bVal;
      }

      public boolean get23_sec1000() {
         return this.bits[23];
      }

      public void set22_sec1001(boolean bVal) {
         this.bits[22] = bVal;
      }

      public boolean get22_sec1001() {
         return this.bits[22];
      }

      public void set21_sec1010(boolean bVal) {
         this.bits[21] = bVal;
      }

      public boolean get21_sec1010() {
         return this.bits[21];
      }

      public void set20_sec1011(boolean bVal) {
         this.bits[20] = bVal;
      }

      public boolean get20_sec1011() {
         return this.bits[20];
      }

      public void set19_sec1100(boolean bVal) {
         this.bits[19] = bVal;
      }

      public boolean get19_sec1100() {
         return this.bits[19];
      }

      public void set18_sec1101(boolean bVal) {
         this.bits[18] = bVal;
      }

      public boolean get18_sec1101() {
         return this.bits[18];
      }

      public void set17_sec1110(boolean bVal) {
         this.bits[17] = bVal;
      }

      public boolean get17_sec1110() {
         return this.bits[17];
      }

      public void set16_sec1111(boolean bVal) {
         this.bits[16] = bVal;
      }

      public boolean get16_sec1111() {
         return this.bits[16];
      }

      public void set15_dat0000(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_dat0000() {
         return this.bits[15];
      }

      public void set14_dat0001(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_dat0001() {
         return this.bits[14];
      }

      public void set13_dat0010(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_dat0010() {
         return this.bits[13];
      }

      public void set12_dat0011(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_dat0011() {
         return this.bits[12];
      }

      public void set11_dat0100(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_dat0100() {
         return this.bits[11];
      }

      public void set10_dat0101(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_dat0101() {
         return this.bits[10];
      }

      public void set09_dat0110(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_dat0110() {
         return this.bits[9];
      }

      public void set08_dat0111(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_dat0111() {
         return this.bits[8];
      }

      public void set07_dat1000(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_dat1000() {
         return this.bits[7];
      }

      public void set06_dat1001(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_dat1001() {
         return this.bits[6];
      }

      public void set05_dat1010(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_dat1010() {
         return this.bits[5];
      }

      public void set04_dat1011(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_dat1011() {
         return this.bits[4];
      }

      public void set03_dat1100(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_dat1100() {
         return this.bits[3];
      }

      public void set02_dat1101(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_dat1101() {
         return this.bits[2];
      }

      public void set01_encoder2(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_encoder2() {
         return this.bits[1];
      }

      public void set00_encoder1(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_encoder1() {
         return this.bits[0];
      }
   }

   public class Errors extends Union32_Abstraction {
      public void set31_errAppello(boolean bVal) {
         this.bits[31] = bVal;
      }

      public boolean get31_errAppello() {
         return this.bits[31];
      }

      public void set29_errULobst(boolean bVal) {
         this.bits[29] = bVal;
      }

      public boolean get229_errULobst() {
         return this.bits[29];
      }

      public void set28_errDeepSleep(boolean bVal) {
         this.bits[28] = bVal;
      }

      public boolean get28_errDeepSleep() {
         return this.bits[28];
      }

      public void set27_errThermalBlock(boolean bVal) {
         this.bits[27] = bVal;
      }

      public boolean get27_errThermalBlock() {
         return this.bits[27];
      }

      public void set22_errEnc2_2easy(boolean bVal) {
         this.bits[22] = bVal;
      }

      public boolean get22_errEnc2_2easy() {
         return this.bits[22];
      }

      public void set21_errEnc1_2easy(boolean bVal) {
         this.bits[21] = bVal;
      }

      public boolean get21_errEnc1_2easy() {
         return this.bits[21];
      }

      public void set18_errTimeoutMov(boolean bVal) {
         this.bits[18] = bVal;
      }

      public boolean get18_errTimeoutMov() {
         return this.bits[18];
      }

      public void set17_errE2prom(boolean bVal) {
         this.bits[17] = bVal;
      }

      public boolean get17_errE2prom() {
         return this.bits[17];
      }

      public void set16_errHWfail(boolean bVal) {
         this.bits[16] = bVal;
      }

      public boolean get16_errHWfail() {
         return this.bits[16];
      }

      public void set15_errData2easy(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_errData2easy() {
         return this.bits[15];
      }

      public void set14_errProg(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_errProg() {
         return this.bits[14];
      }

      public void set13_errFailMotor2(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_errFailMotor2() {
         return this.bits[13];
      }

      public void set12_errFailMotor1(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_errFailMotor1() {
         return this.bits[12];
      }

      public void set11_errConf(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_errConf() {
         return this.bits[11];
      }

      public void set10_errFailsafe(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_errFailsafe() {
         return this.bits[10];
      }

      public void set09_errFC2(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_errFC2() {
         return this.bits[9];
      }

      public void set08_errFC1(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_errFC1() {
         return this.bits[8];
      }

      public void set07_errFailsafe2easy(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_errFailsafe2easy() {
         return this.bits[7];
      }

      public void set06_err2easyCc(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_err2easyCc() {
         return this.bits[6];
      }

      public void set05_errIscr2easy(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_errIscr2easy() {
         return this.bits[5];
      }

      public void set04_err2easy(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_err2easy() {
         return this.bits[4];
      }

      public void set03_errVacc(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_errVacc() {
         return this.bits[3];
      }

      public void set01_errDataSetup(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_errDataSetup() {
         return this.bits[1];
      }

      public void set00_errGuastoScheda(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_errGuastoScheda() {
         return this.bits[0];
      }
   }

   public class LedState extends Union8_Abstraction {
      public void set07_out1(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_out1() {
         return this.bits[7];
      }

      public void set06_out2(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_out2() {
         return this.bits[6];
      }

      public void set04_in5(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_in5() {
         return this.bits[4];
      }

      public void set03_in4(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_in4() {
         return this.bits[3];
      }

      public void set02_in3(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_in3() {
         return this.bits[2];
      }

      public void set01_in2(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_in2() {
         return this.bits[1];
      }

      public void set00_in1(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_in1() {
         return this.bits[0];
      }
   }

   public class LogicIns extends Union32_Abstraction {
      public void set31_emergCloseMem(boolean bVal) {
         this.bits[31] = bVal;
      }

      public boolean get31_emergCloseMem() {
         return this.bits[31];
      }

      public void set30_emergOpenMem(boolean bVal) {
         this.bits[30] = bVal;
      }

      public boolean get30_emergOpenMem() {
         return this.bits[30];
      }

      public void set29_emergCloseNoMem(boolean bVal) {
         this.bits[29] = bVal;
      }

      public boolean get29_emergCloseNoMem() {
         return this.bits[29];
      }

      public void set28_emergOpenNoMem(boolean bVal) {
         this.bits[28] = bVal;
      }

      public boolean get28_emergOpeneNoMem() {
         return this.bits[28];
      }

      public void set27_closePrio(boolean bVal) {
         this.bits[27] = bVal;
      }

      public boolean get27_closePrio() {
         return this.bits[27];
      }

      public void set26_openBPrio(boolean bVal) {
         this.bits[26] = bVal;
      }

      public boolean get26_openBPrio() {
         return this.bits[26];
      }

      public void set25_openAPrio(boolean bVal) {
         this.bits[25] = bVal;
      }

      public boolean get25_openAPrio() {
         return this.bits[25];
      }

      public void set21_close(boolean bVal) {
         this.bits[21] = bVal;
      }

      public boolean get21_close() {
         return this.bits[21];
      }

      public void set20_coastOpen(boolean bVal) {
         this.bits[20] = bVal;
      }

      public boolean get20_coastOpen() {
         return this.bits[20];
      }

      public void set19_coastClose(boolean bVal) {
         this.bits[19] = bVal;
      }

      public boolean get19_coastClose() {
         return this.bits[19];
      }

      public void set18_coastOpenClose(boolean bVal) {
         this.bits[18] = bVal;
      }

      public boolean get18_coastOpenClose() {
         return this.bits[18];
      }

      public void set17_stop(boolean bVal) {
         this.bits[17] = bVal;
      }

      public boolean get17_stop() {
         return this.bits[17];
      }

      public void set16_timerDisabled(boolean bVal) {
         this.bits[16] = bVal;
      }

      public boolean get16_timerDisabled() {
         return this.bits[16];
      }

      public void set15_jollyActive(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_jollyActive() {
         return this.bits[15];
      }

      public void set10_openBA(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_openBA() {
         return this.bits[10];
      }

      public void set09_openAA(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_openAA() {
         return this.bits[9];
      }

      public void set08_finecorsa2Close(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_finecorsa2Close() {
         return this.bits[8];
      }

      public void set07_finecorsa2Open(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_finecorsa2Open() {
         return this.bits[7];
      }

      public void set06_finecorsa1Close(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_finecorsa1Close() {
         return this.bits[6];
      }

      public void set05_finecorsa1Open(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_finecorsa1Open() {
         return this.bits[5];
      }

      public void set04_openCmdFromSec(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_openCmdFromSec() {
         return this.bits[4];
      }

      public void set03_openB(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_openB() {
         return this.bits[3];
      }

      public void set02_openA(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_openA() {
         return this.bits[2];
      }

      public void set01_secClose(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_secClose() {
         return this.bits[1];
      }

      public void set00_secOpen(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_secOpen() {
         return this.bits[0];
      }
   }

   public class PhysicalInsOuts extends Union32_Abstraction {
      public void set15_extIn(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_extIn() {
         return this.bits[15];
      }

      public void set14_bus2easy(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_bus2easy() {
         return this.bits[14];
      }

      public void set12_phyLock2(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_phyLock2() {
         return this.bits[12];
      }

      public void set11_phyLock1(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_phyLock1() {
         return this.bits[11];
      }

      public void set10_phyEnc2(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_phyEnc2() {
         return this.bits[10];
      }

      public void set09_phyEnc1(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_phyEnc1() {
         return this.bits[9];
      }

      public void set08_phyRadioXF2(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_phyRadioXF2() {
         return this.bits[8];
      }

      public void set07_phyRadioXF1(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_phyRadioXF1() {
         return this.bits[7];
      }

      public void set06_phyRadioDec2(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_phyRadioDec2() {
         return this.bits[6];
      }

      public void set05_phyRadioDec1(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_phyRadioDec1() {
         return this.bits[5];
      }

      public void set04_phyIn5(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_phyIn5() {
         return this.bits[4];
      }

      public void set03_phyIn4(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_phyIn4() {
         return this.bits[3];
      }

      public void set02_phyIn3(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_phyIn3() {
         return this.bits[2];
      }

      public void set01_phyIn2(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_phyIn2() {
         return this.bits[1];
      }

      public void set00_phyIn1(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_phyIn1() {
         return this.bits[0];
      }
   }

   public class Subscribed2easy extends Union32_Abstraction {
      public void set31_sec0000(boolean bVal) {
         this.bits[31] = bVal;
      }

      public boolean get31_sec0000() {
         return this.bits[31];
      }

      public void set30_sec0001(boolean bVal) {
         this.bits[30] = bVal;
      }

      public boolean get30_sec0001() {
         return this.bits[30];
      }

      public void set29_sec0010(boolean bVal) {
         this.bits[29] = bVal;
      }

      public boolean get29_sec0010() {
         return this.bits[29];
      }

      public void set28_sec0011(boolean bVal) {
         this.bits[28] = bVal;
      }

      public boolean get28_sec0011() {
         return this.bits[28];
      }

      public void set27_sec0100(boolean bVal) {
         this.bits[27] = bVal;
      }

      public boolean get27_sec0100() {
         return this.bits[27];
      }

      public void set26_sec0101(boolean bVal) {
         this.bits[26] = bVal;
      }

      public boolean get26_sec0101() {
         return this.bits[26];
      }

      public void set25_sec0110(boolean bVal) {
         this.bits[25] = bVal;
      }

      public boolean get25_sec0110() {
         return this.bits[25];
      }

      public void set24_sec0111(boolean bVal) {
         this.bits[24] = bVal;
      }

      public boolean get24_sec0111() {
         return this.bits[24];
      }

      public void set23_sec1000(boolean bVal) {
         this.bits[23] = bVal;
      }

      public boolean get23_sec1000() {
         return this.bits[23];
      }

      public void set22_sec1001(boolean bVal) {
         this.bits[22] = bVal;
      }

      public boolean get22_sec1001() {
         return this.bits[22];
      }

      public void set21_sec1010(boolean bVal) {
         this.bits[21] = bVal;
      }

      public boolean get21_sec1010() {
         return this.bits[21];
      }

      public void set20_sec1011(boolean bVal) {
         this.bits[20] = bVal;
      }

      public boolean get20_sec1011() {
         return this.bits[20];
      }

      public void set19_sec1100(boolean bVal) {
         this.bits[19] = bVal;
      }

      public boolean get19_sec1100() {
         return this.bits[19];
      }

      public void set18_sec1101(boolean bVal) {
         this.bits[18] = bVal;
      }

      public boolean get18_sec1101() {
         return this.bits[18];
      }

      public void set17_sec1110(boolean bVal) {
         this.bits[17] = bVal;
      }

      public boolean get17_sec1110() {
         return this.bits[17];
      }

      public void set16_sec1111(boolean bVal) {
         this.bits[16] = bVal;
      }

      public boolean get16_sec1111() {
         return this.bits[16];
      }

      public void set15_dat0000(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_dat0000() {
         return this.bits[15];
      }

      public void set14_dat0001(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_dat0001() {
         return this.bits[14];
      }

      public void set13_dat0010(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_dat0010() {
         return this.bits[13];
      }

      public void set12_dat0011(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_dat0011() {
         return this.bits[12];
      }

      public void set11_dat0100(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_dat0100() {
         return this.bits[11];
      }

      public void set10_dat0101(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_dat0101() {
         return this.bits[10];
      }

      public void set09_dat0110(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_dat0110() {
         return this.bits[9];
      }

      public void set08_dat0111(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_dat0111() {
         return this.bits[8];
      }

      public void set07_dat1000(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_dat1000() {
         return this.bits[7];
      }

      public void set06_dat1001(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_dat1001() {
         return this.bits[6];
      }

      public void set05_dat1010(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_dat1010() {
         return this.bits[5];
      }

      public void set04_dat1011(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_dat1011() {
         return this.bits[4];
      }

      public void set03_dat1100(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_dat1100() {
         return this.bits[3];
      }

      public void set02_dat1101(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_dat1101() {
         return this.bits[2];
      }

      public void set01_encoder2(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_encoder2() {
         return this.bits[1];
      }

      public void set00_encoder1(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_encoder1() {
         return this.bits[0];
      }
   }
}
