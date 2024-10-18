package FAAClib;

public class E124_Settings extends FAAC_Settings {
   public static String defFile0_NoFAAC = "default/E124_Def0_NoFAAC.PRG";
   public static String defFile1_413_415 = "default/E124_Def1_413_415.PRG";
   public static String defFile2_391 = "default/E124_Def2_391.PRG";
   public static String defFile3_S700_S800 = "default/E124_Def3_S700_S800.PRG";
   public static String defFile4_418 = "default/E124_Def4_418.PRG";
   public static String defFile5_450H = "default/E124_Def5_450H.PRG";
   public static String defFile6_S800H_ENC = "default/E124_Def6_S800ENC.PRG";
   public static String defFile = "default/E124_Def5_450H.PRG";
   public static final int MotorTypeNoFAAC = 0;
   public static final int MotorType413_415 = 1;
   public static final int MotorType391 = 2;
   public static final int MotorTypeS700_S800 = 3;
   public static final int MotorType418 = 4;
   public static final int MotorType450H = 5;
   public static final int MotorTypeS800H_ENC = 6;
   public static String MotorTypeStr0_NoFAAC = "no FAAC";
   public static String MotorTypeStr1_413_415 = "413/415/770/770N/390";
   public static String MotorTypeStr2_391 = "391";
   public static String MotorTypeStr3_S700_S800 = "S800H/S700H";
   public static String MotorTypeStr4_418 = "S418";
   public static String MotorTypeStr5_450H = "S450H";
   public static String MotorTypeStr6_S800H_ENC = "S800H ENC";
   private int speedOpen1;
   private int speedOpen2;
   private int speedClose1;
   private int speedClose2;
   private int ultrasense1;
   private int ultrasense2;
   private int speedRall1;
   private int speedRall2;
   private int timeSoftTouchAnta1_100msec;
   private int timeSoftTouchAnta2_100msec;
   private int typeSoftTouchAnta1;
   private int typeSoftTouchAnta2;
   private int colpoForce1;
   private int colpoForce2;
   private int revStrForce1;
   private int revStrForce2;

   public E124_Settings() {
      this.init_E124();
   }

   public static String getDefFileForMotor(int motorType) {
      switch (motorType) {
         case 0:
            return defFile0_NoFAAC;
         case 1:
            return defFile1_413_415;
         case 2:
            return defFile2_391;
         case 3:
            return defFile3_S700_S800;
         case 4:
            return defFile4_418;
         case 5:
            return defFile5_450H;
         case 6:
            return defFile6_S800H_ENC;
         default:
            return defFile;
      }
   }

   private void init_E124() {
      this.progFlag.set03_FirstMovRall(true);
      this.motor1Type = 5;
      this.motor2Type = 5;
      this.speedOpen1 = 5;
      this.speedOpen2 = 5;
      this.speedClose1 = 5;
      this.speedClose2 = 5;
      this.ultrasense1 = 0;
      this.ultrasense2 = 0;
      this.speedRall1 = 5;
      this.speedRall2 = 5;
      this.timeSoftTouchAnta1_100msec = 20;
      this.timeSoftTouchAnta2_100msec = 20;
      this.typeSoftTouchAnta1 = 0;
      this.typeSoftTouchAnta2 = 0;
      this.colpoForce1 = 25;
      this.colpoForce2 = 25;
      this.revStrForce1 = 25;
      this.revStrForce2 = 25;
   }

   public E124_Settings(String password) {
      super(password);
      this.init_E124();
   }

   public int getSpeedOpen1() {
      return this.speedOpen1;
   }

   public void setSpeedOpen1(int speedOpen1) {
      this.speedOpen1 = speedOpen1;
   }

   public int getSpeedOpen2() {
      return this.speedOpen2;
   }

   public void setSpeedOpen2(int speedOpen2) {
      this.speedOpen2 = speedOpen2;
   }

   public int getSpeedClose1() {
      return this.speedClose1;
   }

   public void setSpeedClose1(int speedClose1) {
      this.speedClose1 = speedClose1;
   }

   public int getSpeedClose2() {
      return this.speedClose2;
   }

   public void setSpeedClose2(int speedClose2) {
      this.speedClose2 = speedClose2;
   }

   public int getUltrasense1() {
      return this.ultrasense1;
   }

   public void setUltrasense1(int ultrasense1) {
      this.ultrasense1 = ultrasense1;
   }

   public int getUltrasense2() {
      return this.ultrasense2;
   }

   public void setUltrasense2(int ultrasense2) {
      this.ultrasense2 = ultrasense2;
   }

   public int getSpeedRall1() {
      return this.speedRall1;
   }

   public void setSpeedRall1(int speedRall1) {
      this.speedRall1 = speedRall1;
   }

   public int getSpeedRall2() {
      return this.speedRall2;
   }

   public void setSpeedRall2(int speedRall2) {
      this.speedRall2 = speedRall2;
   }

   public int getTimeSoftTouchAnta1_100msec() {
      return this.timeSoftTouchAnta1_100msec;
   }

   public void setTimeSoftTouchAnta1_100msec(int timeSoftTouchAnta1_100msec) {
      this.timeSoftTouchAnta1_100msec = timeSoftTouchAnta1_100msec;
   }

   public int getTimeSoftTouchAnta2_100msec() {
      return this.timeSoftTouchAnta2_100msec;
   }

   public void setTimeSoftTouchAnta2_100msec(int timeSoftTouchAnta2_100msec) {
      this.timeSoftTouchAnta2_100msec = timeSoftTouchAnta2_100msec;
   }

   public int getTypeSoftTouchAnta1() {
      return this.typeSoftTouchAnta1;
   }

   public void setTypeSoftTouchAnta1(int typeSoftTouchAnta1) {
      this.typeSoftTouchAnta1 = typeSoftTouchAnta1;
   }

   public int getTypeSoftTouchAnta2() {
      return this.typeSoftTouchAnta2;
   }

   public void setTypeSoftTouchAnta2(int typeSoftTouchAnta2) {
      this.typeSoftTouchAnta2 = typeSoftTouchAnta2;
   }

   public int getColpoForce1() {
      return this.colpoForce1;
   }

   public void setColpoForce1(int colpoForce1) {
      this.colpoForce1 = colpoForce1;
   }

   public int getColpoForce2() {
      return this.colpoForce2;
   }

   public void setColpoForce2(int colpoForce2) {
      this.colpoForce2 = colpoForce2;
   }

   public int getRevStrForce1() {
      return this.revStrForce1;
   }

   public void setRevStrForce1(int revStrForce1) {
      this.revStrForce1 = revStrForce1;
   }

   public int getRevStrForce2() {
      return this.revStrForce2;
   }

   public void setRevStrForce2(int revStrForce2) {
      this.revStrForce2 = revStrForce2;
   }
}
