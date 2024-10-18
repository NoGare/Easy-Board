package FAAClib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class FAAC_Settings {
   public static int defaultOutTime_MinOrSec = 2;
   private String password;
   protected FAAC_Settings.ProgrammingFlag progFlag;
   protected FAAC_Settings.LogicFlag logicFlag;
   private int timePauseA_sec;
   private int timePauseB_sec;
   private int timeSleep_sec;
   private int timeoutMov_sec;
   private int forceOpenMotor1;
   private int forceOpenMotor2;
   private int forceCloseMotor1;
   private int forceCloseMotor2;
   protected int motor1Type;
   protected int motor2Type;
   private int rallSpaceOpenMotor1;
   private int rallSpaceOpenMotor2;
   private int rallSpaceCloseMotor1;
   private int rallSpaceCloseMotor2;
   private int battSpaceOpenMotor1_01deg;
   private int battSpaceOpenMotor2_01deg;
   private int battSpaceCloseMotor1_01deg;
   private int battSpaceCloseMotor2_01deg;
   private int partialOpeningOpenB;
   private int nbcycleAssistance;
   private int timeHoldclose_sec;
   private int timeDelayOpenAnta_sec;
   private int timeDelayCloseAnta_sec;
   private int timeReverseStrokeAnta1_100msec;
   private int timeReverseStrokeAnta2_100msec;
   private int typeReverseStrokeAnta1;
   private int typeReverseStrokeAnta2;
   private int timeLock1_100msec;
   private int timeLock2_100msec;
   protected FAAC_Settings.TypeLock typeLock1;
   protected FAAC_Settings.TypeLock typeLock2;
   private int timeColpoFinAnta1_100msec;
   private int timeColpoFinAnta2_100msec;
   private int typeColpoFinAnta1;
   private int typeColpoFinAnta2;
   private int timeSpuntoAnta1_100msec;
   private int timeSpuntoAnta2_100msec;
   private int finecorsaOpenAnta1;
   private int finecorsaCloseAnta1;
   private int finecorsaOpenAnta2;
   private int finecorsaCloseAnta2;
   private int timePrelamp_100msec;
   private int typePrelamp;
   private int encoderMotor1;
   private int encoderMotor2;
   private int timeInvObstPartial_100msec;
   private FAAC_Settings.input[] sec2easyTypeArray;
   private FAAC_Settings.input[] datImp2easyTypeArray;
   private int timeOut1_secOrMin;
   private int timeOut2_secOrMin;
   private FAAC_Settings.output[] outType;
   private FAAC_Settings.input[] inType;
   private FAAC_Settings.input[] radioDecType;
   private FAAC_Settings.input[] radioXfType;
   private int sensObstOpenAnta1_100msec;
   private int sensObstOpenAnta2_100msec;
   private int sensObstCloseAnta1_100msec;
   private int sensObstCloseAnta2_100msec;
   private int sensObstRallOpenAnta1_100msec;
   private int sensObstRallOpenAnta2_100msec;
   private int sensObstRallCloseAnta1_100msec;
   private int sensObstRallCloseAnta2_100msec;
   private int timeAddAnta1_sec;
   private int numObstCons;
   private byte prog4_Byte31;
   private byte prog4_Byte32;
   private int softstart1;
   private int softstart2;
   public static final int TimePauseMax_sec = 590;
   public static final int TimePauseMin_sec = 0;
   public static final int TimeSleepMax_sec = 590;
   public static final int TimeSpleepMin_sec = 0;
   public static final int TimeoutMovMax_sec = 590;
   public static final int TimeoutMovMin_sec = 1;
   public static final int ForceMin_lvl = 1;
   public static final int ForceMax_lvl = 50;
   public static final int RallSpaceMin = 0;
   public static final int RallSpaceMax = 99;
   public static final int BattSpaceMin_01deg = 3;
   public static final int BattSpaceMax_01deg = 200;
   public static final int PartialOpeningOpenBMin = 0;
   public static final int PartialOpeningOpenBMax1motor = 80;
   public static final int PartialOpeningOpenBMax2motors = 100;
   public static final int NbcycleAssistanceMin = 1;
   public static final int NbcycleAssistanceMax = 65000;
   public static final int TimeHoldCloseMin_sec = 0;
   public static final int TimeHoldCloseMax_sec = 86400;
   public static final int TimeDelayAntaMin_sec = 0;
   public static final int TimeDelayAntaMax_sec = 90;
   public static final int TimeReverseStrokeMin_100ms = 0;
   public static final int TimeReverseStrokeMax_100ms = 50;
   public static final int TimeLockMin_100msec = 0;
   public static final int TimeLockMax_100msec = 100;
   public static final int TimeColpoFinMin_100msec = 0;
   public static final int TimeColpoFinMax_100msec = 100;
   public static final int TimeSpuntoMin_100msec = 0;
   public static final int TimeSpuntoMax_100msec = 100;
   public static final int TimePrelampMin_100msec = 0;
   public static final int TimePrelampMax_100msec = 100;
   public static final int TimeInvObstPartialMin_100msec = 0;
   public static final int TimeInvObstPartialMax_100msec = 200;
   public static final int TimeOut1Min_secOrMin = 1;
   public static final int TimeOut1Max_secOrMin = 119;
   public static final int TimeOut1Max_secOrMin_out11 = 59;
   public static final int SensObstMin_100msec = 0;
   public static final int SensObstMax_100msec = 200;
   public static final int SensObstrallMin_100msec = 0;
   public static final int SensObstRallMax_100msec = 200;
   public static final int TimeAddMin_sec = 0;
   public static final int TimeAddMax_sec = 30;
   public static final int SoftStartMin = 0;
   public static final int SoftStartMax = 9;
   public static final int RevStrType_None = 0;
   public static final int RevStrType_Close = 1;
   public static final int RevStrType_Open = 2;
   public static final int RevStrType_Always = 3;
   public static final int ColpoFinType_None = 0;
   public static final int ColpoFinType_Close = 1;
   public static final int ColpoFinType_Open = 2;
   public static final int ColpoFinType_Always = 3;
   public static final int Finecorsa_None = 0;
   public static final int Finecorsa_EndMov = 1;
   public static final int Finecorsa_BeginRall = 2;
   public static final int Prelamp_None = 0;
   public static final int Prelamp_BeforeMov = 1;
   public static final int Prelamp_BeforeClose = 2;
   public static final int Prelamp_BeforeOpen = 3;
   public static final int Prelamp_EndPause = 4;
   public static final int Encoder_None = 0;
   public static final int Encoder_One = 1;
   public static final byte OutType_Always = 0;
   public static final byte OutType_Failsafe = 1;
   public static final byte OutType_LampSpia = 2;
   public static final byte OutType_LuceCort = 3;
   public static final byte OutType_Error = 4;
   public static final byte OutType_AlarmOpen = 5;
   public static final byte OutType_AlarmClose = 6;
   public static final byte OutType_AlarmMov = 7;
   public static final byte OutType_AlarmEmerg = 8;
   public static final byte OutType_AlarmOpening = 9;
   public static final byte OutType_AlarmClosing = 10;
   public static final byte OutType_LockClose = 11;
   public static final byte OutType_AlarmSicActuve = 12;
   public static final byte OutType_Semaforo = 13;
   public static final byte OutType_RadioXF2timer = 14;
   public static final byte OutType_RadioXF2impulse = 15;
   public static final byte OutNc = -128;
   public static final byte InType_EmCloseWithMemory = 0;
   public static final byte InType_EmOpenWithMemory = 1;
   public static final byte InType_EmCloseNoMemory = 2;
   public static final byte InType_EmOpenNoMemory = 3;
   public static final byte InType_Stop = 4;
   public static final byte InType_OpenA = 5;
   public static final byte InType_OpenB = 6;
   public static final byte InType_Close = 7;
   public static final byte InType_OpenAA = 8;
   public static final byte InType_OpenBA = 9;
   public static final byte InType_ClosePrio = 10;
   public static final byte InType_OpenAPrio = 11;
   public static final byte InType_OpenBPrio = 12;
   public static final byte InType_Inactive = 13;
   public static final byte InType_SicOpenCmd_ = 14;
   public static final byte InType_SicOpen = 15;
   public static final byte InType_SicClose = 16;
   public static final byte InType_SicOpenClose = 17;
   public static final byte InType_TimerDisable = 18;
   public static final byte InType_TimerJolly = 19;
   public static final byte InType_CoastOpen = 20;
   public static final byte InType_CoastClose = 21;
   public static final byte InType_CoastOpenClose = 22;
   public static final byte InNc = -128;
   public static final byte InFailsafe = 64;
   public static final byte SicType_SicOpenCmd_ = 14;
   public static final byte SicType_SicOpen = 15;
   public static final byte SicType_SicClose = 16;
   public static final byte SicType_SicOpenClose = 17;

   public FAAC_Settings() {
      this.password = "0000";
      this.init();
   }

   private void init() {
      this.progFlag = new FAAC_Settings.ProgrammingFlag();
      this.progFlag.set00_SingleMotor(false);
      this.progFlag.set01_FailsafeBus(true);
      this.progFlag.set02_Holdposition(false);
      this.progFlag.set03_FirstMovRall(false);
      this.progFlag.set04_AssistanceReq(false);
      this.progFlag.set05_Antiaccavallamento(false);
      this.progFlag.set06_Sleep(false);
      this.progFlag.set07_LegalHour(true);
      this.progFlag.set10_DisableBus(false);
      this.progFlag.set11_SleepVacc(false);
      this.progFlag.set12_DisBattRecharge(false);
      this.progFlag.set13_LockSingleLeaf(false);
      this.progFlag.set14_Leaf2(false);
      this.progFlag.set15_BatterySleep(false);
      this.progFlag.set16_ULobstacles(false);
      this.logicFlag = new FAAC_Settings.LogicFlag();
      this.logicFlag.setLogicE();
      this.logicFlag.set27_L28(false);
      this.timePauseA_sec = 20;
      this.timePauseB_sec = 20;
      this.timeSleep_sec = 300;
      this.timeoutMov_sec = 250;
      this.forceOpenMotor1 = 25;
      this.forceOpenMotor2 = 25;
      this.forceCloseMotor1 = 25;
      this.forceCloseMotor2 = 25;
      this.motor1Type = 0;
      this.motor2Type = 0;
      this.rallSpaceOpenMotor1 = 30;
      this.rallSpaceOpenMotor2 = 30;
      this.rallSpaceCloseMotor1 = 30;
      this.rallSpaceCloseMotor2 = 30;
      this.battSpaceOpenMotor1_01deg = 30;
      this.battSpaceOpenMotor2_01deg = 30;
      this.battSpaceCloseMotor1_01deg = 30;
      this.battSpaceCloseMotor2_01deg = 30;
      this.partialOpeningOpenB = 50;
      this.nbcycleAssistance = 0;
      this.timeHoldclose_sec = 3600;
      this.timeDelayOpenAnta_sec = 0;
      this.timeDelayCloseAnta_sec = 3;
      this.timeReverseStrokeAnta1_100msec = 10;
      this.timeReverseStrokeAnta2_100msec = 10;
      this.typeReverseStrokeAnta1 = 2;
      this.typeReverseStrokeAnta2 = 0;
      this.timeLock1_100msec = 20;
      this.timeLock2_100msec = 20;
      this.typeLock1 = new FAAC_Settings.TypeLock();
      this.typeLock1.set0_BeforeClose(false);
      this.typeLock1.set1_BeforeOpen(true);
      this.typeLock1.set6_0Na1Nc(false);
      this.typeLock1.set7_0faac1other(false);
      this.typeLock2 = new FAAC_Settings.TypeLock();
      this.typeLock2.set0_BeforeClose(false);
      this.typeLock2.set1_BeforeOpen(true);
      this.typeLock2.set6_0Na1Nc(false);
      this.typeLock2.set7_0faac1other(false);
      this.timeColpoFinAnta1_100msec = 20;
      this.timeColpoFinAnta2_100msec = 20;
      this.typeColpoFinAnta1 = 0;
      this.typeColpoFinAnta2 = 0;
      this.timeSpuntoAnta1_100msec = 6;
      this.timeColpoFinAnta2_100msec = 6;
      this.finecorsaOpenAnta1 = 0;
      this.finecorsaCloseAnta1 = 0;
      this.finecorsaOpenAnta2 = 0;
      this.finecorsaCloseAnta2 = 0;
      this.timePrelamp_100msec = 30;
      this.typePrelamp = 0;
      this.encoderMotor1 = 1;
      this.encoderMotor2 = 1;
      this.setTimeInvObstPartial_100msec(0);
      this.sec2easyTypeArray = new FAAC_Settings.input[16];

      for (int i = 0; i < 16; i++) {
         if (i == 0 || i == 1 || i == 2 || i == 3 || i == 6 || i == 7) {
            this.sec2easyTypeArray[i] = new FAAC_Settings.input();
            this.sec2easyTypeArray[i].setInTypeWithoutNcFs((byte)15);
            this.sec2easyTypeArray[i].setInNc(true);
         } else if (i == 4 || i == 5) {
            this.sec2easyTypeArray[i] = new FAAC_Settings.input();
            this.sec2easyTypeArray[i].setInTypeWithoutNcFs((byte)17);
            this.sec2easyTypeArray[i].setInNc(true);
         } else if (i == 15) {
            this.sec2easyTypeArray[i] = new FAAC_Settings.input();
            this.sec2easyTypeArray[i].setInTypeWithoutNcFs((byte)14);
            this.sec2easyTypeArray[i].setInNc(true);
         } else {
            this.sec2easyTypeArray[i] = new FAAC_Settings.input();
            this.sec2easyTypeArray[i].setInTypeWithoutNcFs((byte)16);
            this.sec2easyTypeArray[i].setInNc(true);
         }
      }

      this.datImp2easyTypeArray = new FAAC_Settings.input[14];

      for (int ix = 0; ix < 14; ix++) {
         if (ix == 0 || ix == 1 || ix == 2 || ix == 3 || ix == 4) {
            this.datImp2easyTypeArray[ix] = new FAAC_Settings.input();
            this.datImp2easyTypeArray[ix].setInTypeWithoutNcFs((byte)5);
         } else if (ix == 5) {
            this.datImp2easyTypeArray[ix] = new FAAC_Settings.input();
            this.datImp2easyTypeArray[ix].setInTypeWithoutNcFs((byte)4);
            this.datImp2easyTypeArray[ix].setInNc(true);
         } else if (ix == 6 || ix == 7) {
            this.datImp2easyTypeArray[ix] = new FAAC_Settings.input();
            this.datImp2easyTypeArray[ix].setInTypeWithoutNcFs((byte)4);
            this.datImp2easyTypeArray[ix].setInNc(true);
         } else if (ix == 8) {
            this.datImp2easyTypeArray[ix] = new FAAC_Settings.input();
            this.datImp2easyTypeArray[ix].setInTypeWithoutNcFs((byte)7);
         } else {
            this.datImp2easyTypeArray[ix] = new FAAC_Settings.input();
            this.datImp2easyTypeArray[ix].setInTypeWithoutNcFs((byte)6);
         }
      }

      this.timeOut1_secOrMin = defaultOutTime_MinOrSec;
      this.timeOut2_secOrMin = defaultOutTime_MinOrSec;
      this.outType = new FAAC_Settings.output[2];

      for (int ixx = 0; ixx < 2; ixx++) {
         this.outType[ixx] = new FAAC_Settings.output();
         if (ixx == 0) {
            this.outType[ixx].setOutTypeWithoutNc((byte)0);
            this.outType[ixx].setOutNc(false);
         }

         if (ixx == 1) {
            this.outType[ixx].setOutTypeWithoutNc((byte)2);
            this.outType[ixx].setOutNc(false);
         }
      }

      this.inType = new FAAC_Settings.input[5];

      for (int ixx = 0; ixx < 5; ixx++) {
         this.inType[ixx] = new FAAC_Settings.input();
         if (ixx == 0) {
            this.inType[ixx].setInTypeWithoutNcFs((byte)5);
            this.inType[ixx].setInNc(false);
            this.inType[ixx].setInFailsafe(false);
         }

         if (ixx == 1) {
            this.inType[ixx].setInTypeWithoutNcFs((byte)6);
            this.inType[ixx].setInNc(false);
            this.inType[ixx].setInFailsafe(false);
         }

         if (ixx == 2) {
            this.inType[ixx].setInTypeWithoutNcFs((byte)4);
            this.inType[ixx].setInNc(true);
            this.inType[ixx].setInFailsafe(false);
         }

         if (ixx == 3) {
            this.inType[ixx].setInTypeWithoutNcFs((byte)15);
            this.inType[ixx].setInNc(true);
            this.inType[ixx].setInFailsafe(true);
         }

         if (ixx == 4) {
            this.inType[ixx].setInTypeWithoutNcFs((byte)16);
            this.inType[ixx].setInNc(true);
            this.inType[ixx].setInFailsafe(true);
         }
      }

      this.radioDecType = new FAAC_Settings.input[2];

      for (int ixx = 0; ixx < 2; ixx++) {
         this.radioDecType[ixx] = new FAAC_Settings.input();
         if (ixx == 0) {
            this.radioDecType[ixx].setInTypeWithoutNcFs((byte)5);
            this.radioDecType[ixx].setInNc(false);
            this.radioDecType[ixx].setInFailsafe(false);
         }

         if (ixx == 1) {
            this.radioDecType[ixx].setInTypeWithoutNcFs((byte)6);
            this.radioDecType[ixx].setInNc(false);
            this.radioDecType[ixx].setInFailsafe(false);
         }
      }

      this.radioXfType = new FAAC_Settings.input[2];

      for (int ixx = 0; ixx < 2; ixx++) {
         this.radioXfType[ixx] = new FAAC_Settings.input();
         if (ixx == 0) {
            this.radioXfType[ixx].setInTypeWithoutNcFs((byte)5);
            this.radioXfType[ixx].setInNc(false);
            this.radioXfType[ixx].setInFailsafe(false);
         }

         if (ixx == 1) {
            this.radioXfType[ixx].setInTypeWithoutNcFs((byte)6);
            this.radioXfType[ixx].setInNc(false);
            this.radioXfType[ixx].setInFailsafe(false);
         }
      }

      this.sensObstOpenAnta1_100msec = 30;
      this.sensObstOpenAnta2_100msec = 30;
      this.sensObstCloseAnta1_100msec = 30;
      this.sensObstCloseAnta2_100msec = 30;
      this.sensObstRallOpenAnta1_100msec = 60;
      this.sensObstRallOpenAnta2_100msec = 60;
      this.sensObstRallCloseAnta1_100msec = 60;
      this.sensObstRallCloseAnta2_100msec = 60;
      this.timeAddAnta1_sec = 5;
      this.numObstCons = 3;
      this.softstart1 = 0;
      this.softstart2 = 0;
   }

   public FAAC_Settings(String password) {
      this.password = password;
      this.init();
   }

   public int getFinecorsaNb() {
      int nb = 0;
      if (this.finecorsaCloseAnta1 != 0) {
         nb++;
      }

      if (this.finecorsaCloseAnta2 != 0) {
         nb++;
      }

      if (this.finecorsaOpenAnta1 != 0) {
         nb++;
      }

      if (this.finecorsaOpenAnta2 != 0) {
         nb++;
      }

      return nb;
   }

   public int getEncoderNb() {
      int nb = 0;
      if (this.getMotorsNb() == 2) {
         if (this.encoderMotor1 == 1) {
            nb++;
         }

         if (this.encoderMotor2 == 1) {
            nb++;
         }
      } else if (this.encoderMotor1 == 1) {
         nb++;
      }

      return nb;
   }

   public int getMotorsNb() {
      return this.getProgrammingFlag().get00_SingleMotor() ? 1 : 2;
   }

   public void loadDefaultPrgFileForBoard(File fileName, String boardModel) {
      byte[] readBytes = new byte[FAAC_frames.FILE_PRG_LENGTH];

      try {
         FileInputStream fis = new FileInputStream(fileName);

         for (int i = 0; i < FAAC_frames.FILE_PRG_LENGTH; i++) {
            readBytes[i] = (byte)fis.read();
         }

         fis.close();
      } catch (IOException var8) {
         Logger.getLogger(FAAC_Settings.class.getName()).log(Level.SEVERE, null, var8);
      }

      boolean bCheck = false;

      try {
         bCheck = FAAC_Protocol.CheckPrgFile(readBytes, this.getPassword(), boardModel);
      } catch (Exception var7) {
         Logger.getLogger(FAAC_Settings.class.getName()).log(Level.SEVERE, null, var7);
      }

      if (!bCheck) {
      }

      FAAC_frames.parsePrgFile(readBytes, this, boardModel, "");
   }

   public FAAC_Settings.ProgrammingFlag getProgrammingFlag() {
      return this.progFlag;
   }

   public void setProgrammingFlag(FAAC_Settings.ProgrammingFlag prgFlg) {
      this.progFlag = prgFlg;
   }

   public int getForceOpenMotor1() {
      return this.forceOpenMotor1;
   }

   public void setForceOpenMotor1(int forceOpenMotor1) {
      this.forceOpenMotor1 = forceOpenMotor1;
   }

   public int getForceOpenMotor2() {
      return this.forceOpenMotor2;
   }

   public void setForceOpenMotor2(int forceOpenMotor2) {
      this.forceOpenMotor2 = forceOpenMotor2;
   }

   public int getForceCloseMotor1() {
      return this.forceCloseMotor1;
   }

   public void setForceCloseMotor1(int forceCloseMotor1) {
      this.forceCloseMotor1 = forceCloseMotor1;
   }

   public int getForceCloseMotor2() {
      return this.forceCloseMotor2;
   }

   public void setForceCloseMotor2(int forceCloseMotor2) {
      this.forceCloseMotor2 = forceCloseMotor2;
   }

   public FAAC_Settings.LogicFlag getLogicFlag() {
      return this.logicFlag;
   }

   public void setLogicFlag(FAAC_Settings.LogicFlag logicFlag) {
      this.logicFlag = logicFlag;
   }

   public int getTimePauseA_sec() {
      return this.timePauseA_sec;
   }

   public void setTimePauseA_sec(int timePauseA_sec) {
      this.timePauseA_sec = timePauseA_sec;
   }

   public int getTimePauseB_sec() {
      return this.timePauseB_sec;
   }

   public void setTimePauseB_sec(int timePauseB_sec) {
      this.timePauseB_sec = timePauseB_sec;
   }

   public int getTimeSleep_sec() {
      return this.timeSleep_sec;
   }

   public void setTimeSleep_sec(int timeSleep_sec) {
      this.timeSleep_sec = timeSleep_sec;
   }

   public int getTimeoutMov_sec() {
      return this.timeoutMov_sec;
   }

   public void setTimeoutMov_sec(int timeoutMov_sec) {
      this.timeoutMov_sec = timeoutMov_sec;
   }

   public int getRallSpaceOpenMotor1() {
      return this.rallSpaceOpenMotor1;
   }

   public void setRallSpaceOpenMotor1(int rallSpaceOpenMotor1) {
      this.rallSpaceOpenMotor1 = rallSpaceOpenMotor1;
   }

   public int getRallSpaceOpenMotor2() {
      return this.rallSpaceOpenMotor2;
   }

   public void setRallSpaceOpenMotor2(int rallSpaceOpenMotor2) {
      this.rallSpaceOpenMotor2 = rallSpaceOpenMotor2;
   }

   public int getRallSpaceCloseMotor1() {
      return this.rallSpaceCloseMotor1;
   }

   public void setRallSpaceCloseMotor1(int rallSpaceCloseMotor1) {
      this.rallSpaceCloseMotor1 = rallSpaceCloseMotor1;
   }

   public int getRallSpaceCloseMotor2() {
      return this.rallSpaceCloseMotor2;
   }

   public void setRallSpaceCloseMotor2(int rallSpaceCloseMotor2) {
      this.rallSpaceCloseMotor2 = rallSpaceCloseMotor2;
   }

   public int getBattSpaceOpenMotor1_01deg() {
      return this.battSpaceOpenMotor1_01deg;
   }

   public void setBattSpaceOpenMotor1_01deg(int battSpaceOpenMotor1_01deg) {
      this.battSpaceOpenMotor1_01deg = battSpaceOpenMotor1_01deg;
   }

   public int getBattSpaceOpenMotor2_01deg() {
      return this.battSpaceOpenMotor2_01deg;
   }

   public void setBattSpaceOpenMotor2_01deg(int battSpaceOpenMotor2_01deg) {
      this.battSpaceOpenMotor2_01deg = battSpaceOpenMotor2_01deg;
   }

   public int getBattSpaceCloseMotor1_01deg() {
      return this.battSpaceCloseMotor1_01deg;
   }

   public void setBattSpaceCloseMotor1_01deg(int battSpaceCloseMotor1_01deg) {
      this.battSpaceCloseMotor1_01deg = battSpaceCloseMotor1_01deg;
   }

   public int getBattSpaceCloseMotor2_01deg() {
      return this.battSpaceCloseMotor2_01deg;
   }

   public void setBattSpaceCloseMotor2_01deg(int battSpaceCloseMotor2_01deg) {
      this.battSpaceCloseMotor2_01deg = battSpaceCloseMotor2_01deg;
   }

   public int getPartialOpeningOpenB() {
      return this.partialOpeningOpenB;
   }

   public void setPartialOpeningOpenB(int partialOpeningOpenB) {
      this.partialOpeningOpenB = partialOpeningOpenB;
   }

   public int getNbcycleAssistance() {
      return this.nbcycleAssistance;
   }

   public void setNbcycleAssistance(int nbcycleAssistance) {
      this.nbcycleAssistance = nbcycleAssistance;
   }

   public int getTimeHoldclose_sec() {
      return this.timeHoldclose_sec;
   }

   public void setTimeHoldclose_sec(int timeHoldclose_sec) {
      this.timeHoldclose_sec = timeHoldclose_sec;
   }

   public int getTimeDelayOpenAnta_sec() {
      return this.timeDelayOpenAnta_sec;
   }

   public void setTimeDelayOpenAnta_sec(int timeDelayOpenAnta_sec) {
      this.timeDelayOpenAnta_sec = timeDelayOpenAnta_sec;
   }

   public int getTimeDelayCloseAnta_sec() {
      return this.timeDelayCloseAnta_sec;
   }

   public void setTimeDelayCloseAnta_sec(int timeDelayCloseAnta_sec) {
      this.timeDelayCloseAnta_sec = timeDelayCloseAnta_sec;
   }

   public int getTimeReverseStrokeAnta1_100msec() {
      return this.timeReverseStrokeAnta1_100msec;
   }

   public void setTimeReverseStrokeAnta1_100msec(int timeReverseStrokeAnta1_100msec) {
      this.timeReverseStrokeAnta1_100msec = timeReverseStrokeAnta1_100msec;
   }

   public int getTimeReverseStrokeAnta2_100msec() {
      return this.timeReverseStrokeAnta2_100msec;
   }

   public void setTimeReverseStrokeAnta2_100msec(int timeReverseStrokeAnta2_100msec) {
      this.timeReverseStrokeAnta2_100msec = timeReverseStrokeAnta2_100msec;
   }

   public int getTypeReverseStrokeAnta1() {
      return this.typeReverseStrokeAnta1;
   }

   public void setTypeReverseStrokeAnta1(int typeReverseStrokeAnta1) {
      this.typeReverseStrokeAnta1 = typeReverseStrokeAnta1;
   }

   public int getTypeReverseStrokeAnta2() {
      return this.typeReverseStrokeAnta2;
   }

   public void setTypeReverseStrokeAnta2(int typeReverseStrokeAnta2) {
      this.typeReverseStrokeAnta2 = typeReverseStrokeAnta2;
   }

   public int getTimeLock1_100msec() {
      return this.timeLock1_100msec;
   }

   public void setTimeLock1_100msec(int timeLock1_100msec) {
      this.timeLock1_100msec = timeLock1_100msec;
   }

   public int getTimeLock2_100msec() {
      return this.timeLock2_100msec;
   }

   public void setTimeLock2_100msec(int timeLock2_100msec) {
      this.timeLock2_100msec = timeLock2_100msec;
   }

   public FAAC_Settings.TypeLock getTypeLock1() {
      return this.typeLock1;
   }

   public void setTypeLock1(FAAC_Settings.TypeLock typeLock1) {
      this.typeLock1 = typeLock1;
   }

   public FAAC_Settings.TypeLock getTypeLock2() {
      return this.typeLock2;
   }

   public void setTypeLock2(FAAC_Settings.TypeLock typeLock2) {
      this.typeLock2 = typeLock2;
   }

   public int getTimeColpoFinAnta1_100msec() {
      return this.timeColpoFinAnta1_100msec;
   }

   public void setTimeColpoFinAnta1_100msec(int timeColpoFinAnta1_100msec) {
      this.timeColpoFinAnta1_100msec = timeColpoFinAnta1_100msec;
   }

   public int getTimeColpoFinAnta2_100msec() {
      return this.timeColpoFinAnta2_100msec;
   }

   public void setTimeColpoFinAnta2_100msec(int timeColpoFinAnta2_100msec) {
      this.timeColpoFinAnta2_100msec = timeColpoFinAnta2_100msec;
   }

   public int getTypeColpoFinAnta1() {
      return this.typeColpoFinAnta1;
   }

   public void setTypeColpoFinAnta1(int typeColpoFinAnta1) {
      this.typeColpoFinAnta1 = typeColpoFinAnta1;
   }

   public int getTypeColpoFinAnta2() {
      return this.typeColpoFinAnta2;
   }

   public void setTypeColpoFinAnta2(int typeColpoFinAnta2) {
      this.typeColpoFinAnta2 = typeColpoFinAnta2;
   }

   public int getTimeSpuntoAnta1_100msec() {
      return this.timeSpuntoAnta1_100msec;
   }

   public void setTimeSpuntoAnta1_100msec(int timeSpuntoAnta1_100msec) {
      this.timeSpuntoAnta1_100msec = timeSpuntoAnta1_100msec;
   }

   public int getTimeSpuntoAnta2_100msec() {
      return this.timeSpuntoAnta2_100msec;
   }

   public void setTimeSpuntoAnta2_100msec(int timeSpuntoAnta2_100msec) {
      this.timeSpuntoAnta2_100msec = timeSpuntoAnta2_100msec;
   }

   public int getFinecorsaOpenAnta1() {
      return this.finecorsaOpenAnta1;
   }

   public void setFinecorsaOpenAnta1(int finecorsaOpenAnta1) {
      this.finecorsaOpenAnta1 = finecorsaOpenAnta1;
   }

   public int getFinecorsaCloseAnta1() {
      return this.finecorsaCloseAnta1;
   }

   public void setFinecorsaCloseAnta1(int finecorsaCloseAnta1) {
      this.finecorsaCloseAnta1 = finecorsaCloseAnta1;
   }

   public int getFinecorsaOpenAnta2() {
      return this.finecorsaOpenAnta2;
   }

   public void setFinecorsaOpenAnta2(int finecorsaOpenAnta2) {
      this.finecorsaOpenAnta2 = finecorsaOpenAnta2;
   }

   public int getFinecorsaCloseAnta2() {
      return this.finecorsaCloseAnta2;
   }

   public void setFinecorsaCloseAnta2(int finecorsaCloseAnta2) {
      this.finecorsaCloseAnta2 = finecorsaCloseAnta2;
   }

   public int getTimePrelamp_100msec() {
      return this.timePrelamp_100msec;
   }

   public void setTimePrelamp_100msec(int timePrelamp_100msec) {
      this.timePrelamp_100msec = timePrelamp_100msec;
   }

   public int getTypePrelamp() {
      return this.typePrelamp;
   }

   public void setTypePrelamp(int typePrelamp) {
      this.typePrelamp = typePrelamp;
   }

   public int getEncoderMotor1() {
      return this.encoderMotor1;
   }

   public void setEncoderMotor1(int encoderMotor1) {
      this.encoderMotor1 = encoderMotor1;
   }

   public int getEncoderMotor2() {
      return this.encoderMotor2;
   }

   public void setEncoderMotor2(int encoderMotor2) {
      this.encoderMotor2 = encoderMotor2;
   }

   public int getTimeOut1_secOrMin() {
      return this.timeOut1_secOrMin;
   }

   public void setTimeOut1_secOrMin(int timeOut1_secOrMin) {
      this.timeOut1_secOrMin = timeOut1_secOrMin;
   }

   public int getTimeOut2_secOrMin() {
      return this.timeOut2_secOrMin;
   }

   public void setTimeOut2_secOrMin(int timeOut2_secOrMin) {
      this.timeOut2_secOrMin = timeOut2_secOrMin;
   }

   public FAAC_Settings.output[] getOutType() {
      return this.outType;
   }

   public void setOutType(FAAC_Settings.output[] outType) {
      this.outType = outType;
   }

   public FAAC_Settings.output getOutType(int index) {
      return this.outType[index];
   }

   public void setOutType(FAAC_Settings.output outType, int index) {
      this.outType[index] = outType;
   }

   public FAAC_Settings.input[] getInType() {
      return this.inType;
   }

   public void setInType(FAAC_Settings.input[] inType) {
      this.inType = inType;
   }

   public FAAC_Settings.input getInType(int index) {
      return this.inType[index];
   }

   public void setInType(FAAC_Settings.input inType, int index) {
      this.inType[index] = inType;
   }

   public FAAC_Settings.input[] getRadioDecType() {
      return this.radioDecType;
   }

   public void setRadioDecType(FAAC_Settings.input[] radioDecType) {
      this.radioDecType = radioDecType;
   }

   public FAAC_Settings.input getRadioDecType(int index) {
      return this.radioDecType[index];
   }

   public void setRadioDecType(FAAC_Settings.input radioDecType, int index) {
      this.radioDecType[index] = radioDecType;
   }

   public FAAC_Settings.input[] getRadioXfType() {
      return this.radioXfType;
   }

   public void setRadioXfType(FAAC_Settings.input[] radioXfType) {
      this.radioXfType = radioXfType;
   }

   public FAAC_Settings.input getRadioXfType(int index) {
      return this.radioXfType[index];
   }

   public void setRadioXfType(FAAC_Settings.input radioXfType, int index) {
      this.radioXfType[index] = radioXfType;
   }

   public int getSensObstOpenAnta1_100msec() {
      return this.sensObstOpenAnta1_100msec;
   }

   public void setSensObstOpenAnta1_100msec(int sensObstOpenAnta1_100msec) {
      this.sensObstOpenAnta1_100msec = sensObstOpenAnta1_100msec;
   }

   public int getSensObstOpenAnta2_100msec() {
      return this.sensObstOpenAnta2_100msec;
   }

   public void setSensObstOpenAnta2_100msec(int sensObstOpenAnta2_100msec) {
      this.sensObstOpenAnta2_100msec = sensObstOpenAnta2_100msec;
   }

   public int getSensObstCloseAnta1_100msec() {
      return this.sensObstCloseAnta1_100msec;
   }

   public void setSensObstCloseAnta1_100msec(int sensObstCloseAnta1_100msec) {
      this.sensObstCloseAnta1_100msec = sensObstCloseAnta1_100msec;
   }

   public int getSensObstCloseAnta2_100msec() {
      return this.sensObstCloseAnta2_100msec;
   }

   public void setSensObstCloseAnta2_100msec(int sensObstCloseAnta2_100msec) {
      this.sensObstCloseAnta2_100msec = sensObstCloseAnta2_100msec;
   }

   public int getSensObstRallOpenAnta1_100msec() {
      return this.sensObstRallOpenAnta1_100msec;
   }

   public void setSensObstRallOpenAnta1_100msec(int sensObstRallOpenAnta1_100msec) {
      this.sensObstRallOpenAnta1_100msec = sensObstRallOpenAnta1_100msec;
   }

   public int getSensObstRallOpenAnta2_100msec() {
      return this.sensObstRallOpenAnta2_100msec;
   }

   public void setSensObstRallOpenAnta2_100msec(int sensObstRallOpenAnta2_100msec) {
      this.sensObstRallOpenAnta2_100msec = sensObstRallOpenAnta2_100msec;
   }

   public int getSensObstRallCloseAnta1_100msec() {
      return this.sensObstRallCloseAnta1_100msec;
   }

   public void setSensObstRallCloseAnta1_100msec(int sensObstRallCloseAnta1_100msec) {
      this.sensObstRallCloseAnta1_100msec = sensObstRallCloseAnta1_100msec;
   }

   public int getSensObstRallCloseAnta2_100msec() {
      return this.sensObstRallCloseAnta2_100msec;
   }

   public void setSensObstRallCloseAnta2_100msec(int sensObstRallCloseAnta2_100msec) {
      this.sensObstRallCloseAnta2_100msec = sensObstRallCloseAnta2_100msec;
   }

   public int getTimeAddAnta1_sec() {
      return this.timeAddAnta1_sec;
   }

   public void setTimeAddAnta1_sec(int timeAddAnta1_sec) {
      this.timeAddAnta1_sec = timeAddAnta1_sec;
   }

   public int getNumObstCons() {
      return this.numObstCons;
   }

   public void setNumObstCons(int numObstCons) {
      this.numObstCons = numObstCons;
   }

   public FAAC_Settings.input[] getSec2easyTypeArray() {
      return this.sec2easyTypeArray;
   }

   public void setSec2easyTypeArray(FAAC_Settings.input[] sec2easyTypeArray) {
      this.sec2easyTypeArray = sec2easyTypeArray;
   }

   public FAAC_Settings.input getSec2easyTypeArray(int index) {
      return this.sec2easyTypeArray[index];
   }

   public void setSec2easyTypeArray(FAAC_Settings.input sec2easyType, int index) {
      this.sec2easyTypeArray[index] = sec2easyType;
   }

   public FAAC_Settings.input[] getDatImp2easyTypeArray() {
      return this.datImp2easyTypeArray;
   }

   public void setDatImp2easyTypeArray(FAAC_Settings.input[] datImp2easyTypeArray) {
      this.datImp2easyTypeArray = datImp2easyTypeArray;
   }

   public FAAC_Settings.input getDatImp2easyTypeArray(int index) {
      return this.datImp2easyTypeArray[index];
   }

   public void setDatImp2easyTypeArray(FAAC_Settings.input datImp2easyType, int index) {
      this.datImp2easyTypeArray[index] = datImp2easyType;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public byte getProg4_Byte31() {
      return this.prog4_Byte31;
   }

   public void setProg4_Byte31(byte prog4_Byte31) {
      this.prog4_Byte31 = prog4_Byte31;
   }

   public byte getProg4_Byte32() {
      return this.prog4_Byte32;
   }

   public void setProg4_Byte32(byte prog4_Byte32) {
      this.prog4_Byte32 = prog4_Byte32;
   }

   public int getTimeInvObstPartial_100msec() {
      return this.timeInvObstPartial_100msec;
   }

   public void setTimeInvObstPartial_100msec(int timeInvObstPartial_100msec) {
      this.timeInvObstPartial_100msec = timeInvObstPartial_100msec;
   }

   public int getMotor1Type() {
      return this.motor1Type;
   }

   public void setMotor1Type(int motor1Type) {
      this.motor1Type = motor1Type;
   }

   public int getMotor2Type() {
      return this.motor2Type;
   }

   public void setMotor2Type(int motor2Type) {
      this.motor2Type = motor2Type;
   }

   public int getSoftstart1() {
      return this.softstart1;
   }

   public void setSoftstart1(int softstart1) {
      this.softstart1 = softstart1;
   }

   public int getSoftstart2() {
      return this.softstart2;
   }

   public void setSoftstart2(int softstart2) {
      this.softstart2 = softstart2;
   }

   public class LogicFlag extends Union32_Abstraction {
      public void setLogic(String logic) {
         if (logic.equalsIgnoreCase("A")) {
            this.setLogicA();
         } else if (logic.equalsIgnoreCase("AP")) {
            this.setLogicAP();
         } else if (logic.equalsIgnoreCase("AT")) {
            this.setLogicAT();
         } else if (logic.equalsIgnoreCase("B")) {
            this.setLogicB();
         } else if (logic.equalsIgnoreCase("BC")) {
            this.setLogicBC();
         } else if (logic.equalsIgnoreCase("C")) {
            this.setLogicC();
         } else if (logic.equalsIgnoreCase("E")) {
            this.setLogicE();
         } else if (logic.equalsIgnoreCase("EP")) {
            this.setLogicEP();
         } else if (logic.equalsIgnoreCase("S")) {
            this.setLogicS();
         } else if (logic.equalsIgnoreCase("SA")) {
            this.setLogicSA();
         } else if (logic.equalsIgnoreCase("SP")) {
            this.setLogicSP();
         } else if (logic.equalsIgnoreCase("A1")) {
            this.setLogicA1();
         }
      }

      public String getLogic() {
         byte[] logicBytes = this.getBytes_LSBfirst();
         String logicStr = "CU";
         if (logicBytes[0] == 1 && logicBytes[1] == 0 && (logicBytes[2] & -17) == 0) {
            logicStr = "A";
         } else if (logicBytes[0] == 113 && logicBytes[1] == 0 && (logicBytes[2] & -17) == 0) {
            logicStr = "AP";
         } else if (logicBytes[0] == 1 && logicBytes[1] == 0 && (logicBytes[2] & -17) == 32) {
            logicStr = "At";
         } else if (logicBytes[0] == 1 && logicBytes[1] == 0 && (logicBytes[2] & -17) == 3) {
            logicStr = "A1";
         } else if (logicBytes[0] == -79 && logicBytes[1] == 1 && (logicBytes[2] & -17) == 3) {
            logicStr = "SP";
         } else if (logicBytes[0] == -95 && logicBytes[1] == 0 && (logicBytes[2] & -17) == 3) {
            logicStr = "S";
         } else if (logicBytes[0] == -127 && logicBytes[1] == 0 && (logicBytes[2] & -17) == 0) {
            logicStr = "SA";
         } else if (logicBytes[0] == 10 && logicBytes[1] == 2 && (logicBytes[2] & -17) == 0) {
            logicStr = "C";
         } else if (logicBytes[0] == 2 && logicBytes[1] == 2 && (logicBytes[2] & -17) == 0) {
            logicStr = "bC";
         } else if (logicBytes[0] == 2 && logicBytes[1] == 0 && (logicBytes[2] & -17) == 0) {
            logicStr = "b";
         } else if (logicBytes[0] == 48 && logicBytes[1] == 1 && (logicBytes[2] & -17) == 0) {
            logicStr = "EP";
         } else if (logicBytes[0] == 48 && logicBytes[1] == 0 && (logicBytes[2] & -17) == 0) {
            logicStr = "E";
         } else {
            logicStr = "CU";
         }

         return logicStr;
      }

      public void setLogicA() {
         for (int i = 0; i < 32; i++) {
            if (i == 0) {
               this.bits[i] = true;
            } else {
               this.bits[i] = false;
            }
         }
      }

      public void setLogicAP() {
         for (int i = 0; i < 32; i++) {
            if (i != 0 && i != 4 && i != 5 && i != 6) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicAT() {
         for (int i = 0; i < 32; i++) {
            if (i != 0 && i != 21) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicB() {
         for (int i = 0; i < 32; i++) {
            if (i == 1) {
               this.bits[i] = true;
            } else {
               this.bits[i] = false;
            }
         }
      }

      public void setLogicBC() {
         for (int i = 0; i < 32; i++) {
            if (i != 1 && i != 9) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicC() {
         for (int i = 0; i < 32; i++) {
            if (i != 1 && i != 3 && i != 9) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicE() {
         for (int i = 0; i < 32; i++) {
            if (i != 4 && i != 5) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicEP() {
         for (int i = 0; i < 32; i++) {
            if (i != 4 && i != 5 && i != 8) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicS() {
         for (int i = 0; i < 32; i++) {
            if (i != 0 && i != 7 && i != 16 && i != 17 && i != 5) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicSA() {
         for (int i = 0; i < 32; i++) {
            if (i != 0 && i != 7) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicSP() {
         for (int i = 0; i < 32; i++) {
            if (i != 0 && i != 4 && i != 5 && i != 7 && i != 8 && i != 17 && i != 16) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void setLogicA1() {
         for (int i = 0; i < 32; i++) {
            if (i != 0 && i != 16 && i != 17) {
               this.bits[i] = false;
            } else {
               this.bits[i] = true;
            }
         }
      }

      public void set00_L1(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_L1() {
         return this.bits[0];
      }

      public void set01_L2(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_L2() {
         return this.bits[1];
      }

      public void set02_L3(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_L3() {
         return this.bits[2];
      }

      public void set03_L4(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_L4() {
         return this.bits[3];
      }

      public void set04_L5(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_L5() {
         return this.bits[4];
      }

      public void set05_L6(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_L6() {
         return this.bits[5];
      }

      public void set06_L7(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_L7() {
         return this.bits[6];
      }

      public void set07_L8(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_L8() {
         return this.bits[7];
      }

      public void set08_L9(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_L9() {
         return this.bits[8];
      }

      public void set09_L10(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_L10() {
         return this.bits[9];
      }

      public void set10_L11(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_L11() {
         return this.bits[10];
      }

      public void set11_L12(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_L12() {
         return this.bits[11];
      }

      public void set12_L13(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_L13() {
         return this.bits[12];
      }

      public void set13_L14(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_L14() {
         return this.bits[13];
      }

      public void set14_L15(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_L15() {
         return this.bits[14];
      }

      public void set15_L16(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_L16() {
         return this.bits[15];
      }

      public void set16_L17(boolean bVal) {
         this.bits[16] = bVal;
      }

      public boolean get16_L17() {
         return this.bits[16];
      }

      public void set17_L18(boolean bVal) {
         this.bits[17] = bVal;
      }

      public boolean get17_L18() {
         return this.bits[17];
      }

      public void set18_L19(boolean bVal) {
         this.bits[18] = bVal;
      }

      public boolean get18_L19() {
         return this.bits[18];
      }

      public void set19_L20(boolean bVal) {
         this.bits[19] = bVal;
      }

      public boolean get19_L20() {
         return this.bits[19];
      }

      public void set20_L21(boolean bVal) {
         this.bits[20] = bVal;
      }

      public boolean get20_L21() {
         return this.bits[20];
      }

      public void set21_L22(boolean bVal) {
         this.bits[21] = bVal;
      }

      public boolean get21_L22() {
         return this.bits[21];
      }

      public void set22_L23(boolean bVal) {
         this.bits[22] = bVal;
      }

      public boolean get22_L23() {
         return this.bits[22];
      }

      public void set23_L24(boolean bVal) {
         this.bits[23] = bVal;
      }

      public boolean get23_L24() {
         return this.bits[23];
      }

      public void set24_L25(boolean bVal) {
         this.bits[24] = bVal;
      }

      public boolean get24_L25() {
         return this.bits[24];
      }

      public void set25_L26(boolean bVal) {
         this.bits[25] = bVal;
      }

      public boolean get25_L26() {
         return this.bits[25];
      }

      public void set26_L27(boolean bVal) {
         this.bits[26] = bVal;
      }

      public boolean get26_L27() {
         return this.bits[26];
      }

      public void set27_L28(boolean bVal) {
         this.bits[27] = bVal;
      }

      public boolean get27_L28() {
         return this.bits[27];
      }

      public void set28_L29(boolean bVal) {
         this.bits[28] = bVal;
      }

      public boolean get28_L29() {
         return this.bits[28];
      }
   }

   public class ProgrammingFlag extends Union32_Abstraction {
      public void set00_SingleMotor(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_SingleMotor() {
         return this.bits[0];
      }

      public void set01_FailsafeBus(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_FailsafeBus() {
         return this.bits[1];
      }

      public void set02_Holdposition(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_Holdposition() {
         return this.bits[2];
      }

      public void set03_FirstMovRall(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_FirstMovRall() {
         return this.bits[3];
      }

      public void set04_AssistanceReq(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_AssistanceReq() {
         return this.bits[4];
      }

      public void set05_Antiaccavallamento(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_Antiaccavallamento() {
         return this.bits[5];
      }

      public void set06_Sleep(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_Sleep() {
         return this.bits[6];
      }

      public void set07_LegalHour(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_LegalHour() {
         return this.bits[7];
      }

      public void set10_DisableBus(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_DisableBus() {
         return this.bits[10];
      }

      public void set11_SleepVacc(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_SleepVacc() {
         return this.bits[11];
      }

      public void set12_DisBattRecharge(boolean bVal) {
         this.bits[12] = bVal;
      }

      public boolean get12_DisBattRecharge() {
         return this.bits[12];
      }

      public void set13_LockSingleLeaf(boolean bVal) {
         this.bits[13] = bVal;
      }

      public boolean get13_LockSingleLeaf() {
         return this.bits[13];
      }

      public void set14_Leaf2(boolean bVal) {
         this.bits[14] = bVal;
      }

      public boolean get14_Leaf2() {
         return this.bits[14];
      }

      public void set15_BatterySleep(boolean bVal) {
         this.bits[15] = bVal;
      }

      public boolean get15_BatterySleep() {
         return this.bits[15];
      }

      public void set16_ULobstacles(boolean bVal) {
         this.bits[16] = bVal;
      }

      public boolean get16_ULobstacles() {
         return this.bits[16];
      }
   }

   public class TypeLock extends Union8_Abstraction {
      public void set0_BeforeClose(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get0_BeforeClose() {
         return this.bits[0];
      }

      public void set1_BeforeOpen(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get1_BeforeOpen() {
         return this.bits[1];
      }

      public void set6_0Na1Nc(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get6_0Na1Nc() {
         return this.bits[6];
      }

      public void set7_0faac1other(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get7_0faac1other() {
         return this.bits[7];
      }
   }

   public class input {
      private byte inTypeWithoutNcFs = 13;
      private boolean inNc = false;
      private boolean inFailsafe = false;

      public byte getInTypeByteForBoard() {
         byte tmp = this.getInTypeWithoutNcFs();
         if (this.isInNc()) {
            tmp |= -128;
         }

         if (this.isInFailsafe()) {
            tmp = (byte)(tmp | 64);
         }

         return tmp;
      }

      public void setInTypeFromBoard(byte inTypeWithNcFs) {
         byte tmp = (byte)(inTypeWithNcFs & -128);
         if (tmp != 0) {
            this.setInNc(true);
         } else {
            this.setInNc(false);
         }

         tmp = (byte)(inTypeWithNcFs & 64);
         if (tmp != 0) {
            this.setInFailsafe(true);
         } else {
            this.setInFailsafe(false);
         }

         tmp = (byte)(inTypeWithNcFs & 63);
         this.setInTypeWithoutNcFs(tmp);
      }

      public byte getInTypeWithoutNcFs() {
         return this.inTypeWithoutNcFs;
      }

      public void setInTypeWithoutNcFs(byte inTypeWithoutNcFs) {
         this.inTypeWithoutNcFs = inTypeWithoutNcFs;
      }

      public boolean isInNc() {
         return this.inNc;
      }

      public void setInNc(boolean isInNc) {
         this.inNc = isInNc;
      }

      public boolean isInFailsafe() {
         return this.inFailsafe;
      }

      public void setInFailsafe(boolean isInFailsafe) {
         this.inFailsafe = isInFailsafe;
      }
   }

   public class output {
      private byte outTypeWithoutNc = 0;
      private boolean outNc = false;

      public byte getOutTypeByteForBoard() {
         byte tmp = this.getOutTypeWithoutNc();
         if (this.isOutNc()) {
            tmp |= -128;
         }

         return tmp;
      }

      public void setOutTypeFromBoard(byte outTypeWithNc) {
         byte tmp = (byte)(outTypeWithNc & -128);
         if (tmp != 0) {
            this.setOutNc(true);
         } else {
            this.setOutNc(false);
         }

         byte res = (byte)(outTypeWithNc & 127);
         this.setOutTypeWithoutNc(res);
      }

      public byte getOutTypeWithoutNc() {
         return this.outTypeWithoutNc;
      }

      public void setOutTypeWithoutNc(byte outTypeWithoutNc) {
         this.outTypeWithoutNc = outTypeWithoutNc;
      }

      public boolean isOutNc() {
         return this.outNc;
      }

      public void setOutNc(boolean isOutNc) {
         this.outNc = isOutNc;
      }
   }
}
