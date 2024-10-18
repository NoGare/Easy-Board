package FAAClib;

public class FAAC_Commands {
   private FAAC_Commands.NoMovCmdArg noMovCmdArg;
   private FAAC_Commands.MovCmdArg movCmdArg = new FAAC_Commands.MovCmdArg();
   private boolean isResetCmdRequested;
   public static final char keyOpenA_h = 'h';
   public static final char keyOpenB_j = 'j';
   public static final char keyClose_k = 'k';
   public static final char keyOpenAprio_y = 'y';
   public static final char keyOpenBprio_i = 'i';
   public static final char keyClosePrio_o = 'o';
   public static final char keyOpenAA_n = 'n';
   public static final char keyOpenBA_m = 'm';
   public static final char keyStop_l = 'l';
   public static final char keySicChiusura_e = 'e';
   public static final char keySicApertura_w = 'w';
   public static final char keySicApCh_r = 'r';
   public static final char keySicApCmd_q = 'q';

   public FAAC_Commands() {
      this.noMovCmdArg = new FAAC_Commands.NoMovCmdArg();
      this.isResetCmdRequested = false;
   }

   public FAAC_Commands.NoMovCmdArg getNoMovCmdArg() {
      return this.noMovCmdArg;
   }

   public void setNoMovCmdArg(FAAC_Commands.NoMovCmdArg noMovCmdArg) {
      this.noMovCmdArg = noMovCmdArg;
   }

   public FAAC_Commands.MovCmdArg getMovCmdArg() {
      return this.movCmdArg;
   }

   public void setMovCmdArg(FAAC_Commands.MovCmdArg movCmdArg) {
      this.movCmdArg = movCmdArg;
   }

   public boolean isIsResetCmdRequested() {
      return this.isResetCmdRequested;
   }

   public void setIsResetCmdRequested(boolean isResetCmdRequested) {
      this.isResetCmdRequested = isResetCmdRequested;
   }

   public class MovCmdArg extends Union32_Abstraction {
      public void zeroAll() {
         for (int i = 0; i < 32; i++) {
            this.bits[i] = false;
         }
      }

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

      public boolean get28_emergOpenNoMem() {
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

      public void set17_stop(boolean bVal) {
         this.bits[17] = bVal;
      }

      public boolean get17_stop() {
         return this.bits[17];
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

      public void set08_indipClose2(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_indipClose2() {
         return this.bits[8];
      }

      public void set07_indipOpen2(boolean bVal) {
         this.bits[7] = bVal;
      }

      public boolean get07_indipOpen2() {
         return this.bits[7];
      }

      public void set06_indipClose1(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_indipClose1() {
         return this.bits[6];
      }

      public void set05_indipOpen1(boolean bVal) {
         this.bits[5] = bVal;
      }

      public boolean get05_indipOpen1() {
         return this.bits[5];
      }

      public void set04_secOpenCmd(boolean bVal) {
         this.bits[4] = bVal;
      }

      public boolean get04_secOpenCmd() {
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

   public class NoMovCmdArg extends Union32_Abstraction {
      public void zeroAll() {
         for (int i = 0; i < 32; i++) {
            this.bits[i] = false;
         }
      }

      public void set20_reset(boolean bVal) {
         this.bits[20] = bVal;
      }

      public boolean get20_reset() {
         return this.bits[20];
      }

      public void set19_setupSimple(boolean bVal) {
         this.bits[19] = bVal;
      }

      public boolean get19_setupSimple() {
         return this.bits[19];
      }

      public void set11_fwUpdateE124_2_0(boolean bVal) {
         this.bits[11] = bVal;
      }

      public boolean get11_fwUpdateE124_2_0() {
         return this.bits[11];
      }

      public void set10_memRadio2(boolean bVal) {
         this.bits[10] = bVal;
      }

      public boolean get10_memRadio2() {
         return this.bits[10];
      }

      public void set09_memRadio1(boolean bVal) {
         this.bits[9] = bVal;
      }

      public boolean get09_memRadio1() {
         return this.bits[9];
      }

      public void set08_fwUpdate(boolean bVal) {
         this.bits[8] = bVal;
      }

      public boolean get08_fwUpdate() {
         return this.bits[8];
      }

      public void set07_zeroMemRadio(boolean bVal) {
         Debug.printlnStatic("Setting erase radio to " + bVal);
         this.bits[7] = bVal;
      }

      public boolean get07_zeroMemRadio() {
         return this.bits[7];
      }

      public void set06_zeroEmergMem(boolean bVal) {
         this.bits[6] = bVal;
      }

      public boolean get06_zeroEmergMem() {
         return this.bits[6];
      }

      public void set03_zeroNbCycles(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_zeroNbCycles() {
         return this.bits[3];
      }

      public void set02_iscrBus(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_iscrBus() {
         return this.bits[2];
      }

      public void set01_setupComplete(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_setupComplete() {
         return this.bits[1];
      }
   }
}
