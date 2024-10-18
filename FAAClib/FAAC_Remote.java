package FAAClib;

public abstract class FAAC_Remote {
   private FAAC_Remote.ProgRadioFlag progRadio;
   private FAAC_Remote.RadioCode[] radioCodeArray;
   private int nbCodesInMemory;
   private int currentActiveCodeIndex;
   protected static int NbRadioCode;
   protected int radFileLenght_inBytes;
   public static final int numRadio_A = 256;
   public static final int numRadio_B = 768;
   public static final int numRadio_C = 1600;
   public static final int radFileHeaderLength_inBytes = 24;
   public static final int radFileSingleCodeLength_inBytes = 8;
   public static final int radFileProgRadioLength_inBytes = 5;

   public FAAC_Remote() {
      NbRadioCode = 1600;
      this.radioCodeArray = new FAAC_Remote.RadioCode[NbRadioCode];

      for (int iRem = 0; iRem < NbRadioCode; iRem++) {
         this.radioCodeArray[iRem] = new FAAC_Remote.RadioCode();
         this.radioCodeArray[iRem].setIndexRadioCode(iRem);
      }

      this.nbCodesInMemory = 0;
      this.currentActiveCodeIndex = 0;
      this.progRadio = new FAAC_Remote.ProgRadioFlag();
      this.progRadio.set00_ClosedList(false);
      this.progRadio.set01_DisableDS(false);
      this.progRadio.set02_DisableRC(false);
      this.progRadio.set03_DisableSLH(false);
   }

   public void emptyRadio() {
      this.radioCodeArray = new FAAC_Remote.RadioCode[NbRadioCode];

      for (int iRem = 0; iRem < NbRadioCode; iRem++) {
         this.radioCodeArray[iRem] = new FAAC_Remote.RadioCode();
         this.radioCodeArray[iRem].setIndexRadioCode(iRem);
      }

      this.nbCodesInMemory = 0;
      this.currentActiveCodeIndex = 0;
   }

   public boolean isNotNullCode(int iCodeIndex) {
      FAAC_Remote.RadioCode codeTmp = this.getRadioCodeArray()[iCodeIndex];
      return codeTmp.getCode1().getBytes_MSBfirst()[0] != 0
         || codeTmp.getCode1().getBytes_MSBfirst()[1] != 0
         || codeTmp.getCode1().getBytes_MSBfirst()[2] != 0
         || codeTmp.getCode1().getBytes_MSBfirst()[3] != 0
         || codeTmp.getCode2().getBytes_MSBfirst()[0] != 0
         || codeTmp.getCode2().getBytes_MSBfirst()[1] != 0
         || codeTmp.getCode2().getBytes_MSBfirst()[2] != 0
         || codeTmp.getCode2().getBytes_MSBfirst()[3] != 0;
   }

   public int getRadFileLenght_inBytes() {
      return this.radFileLenght_inBytes;
   }

   public void setRadFileLenght_inBytes(int iRadFileLenght_inBytes) {
      this.radFileLenght_inBytes = iRadFileLenght_inBytes;
   }

   public FAAC_Remote.RadioCode[] getRadioCodeArray() {
      return this.radioCodeArray;
   }

   public void setRadioCodeArray(FAAC_Remote.RadioCode[] radioCodeArray) {
      this.radioCodeArray = radioCodeArray;
   }

   public void setRadioCodeArrayIndex(FAAC_Remote.RadioCode code, int iRadioIndex) {
      this.radioCodeArray[iRadioIndex] = code;
   }

   public int getNbCodesInMemory() {
      return this.nbCodesInMemory;
   }

   public void setNbCodesInMemory(int nbCodesInMemory) {
      this.nbCodesInMemory = nbCodesInMemory;
   }

   public int getCurrentActiveCodeIndex() {
      return this.currentActiveCodeIndex;
   }

   public void setCurrentActiveCodeIndex(int currentActiveCodeIndex) {
      this.currentActiveCodeIndex = currentActiveCodeIndex;
   }

   public static int getNbRadioCode() {
      return NbRadioCode;
   }

   public void setNbRadioCode(int NbRadioCode_set) {
      NbRadioCode = NbRadioCode_set;
      this.radFileLenght_inBytes = 24 + NbRadioCode * 8 + 5;
   }

   public FAAC_Remote.ProgRadioFlag getProgRadio() {
      return this.progRadio;
   }

   public void setProgRadio(FAAC_Remote.ProgRadioFlag progRadio) {
      this.progRadio = progRadio;
   }

   public class ProgRadioFlag extends Union32_Abstraction {
      public void set00_ClosedList(boolean bVal) {
         this.bits[0] = bVal;
      }

      public boolean get00_ClosedList() {
         return this.bits[0];
      }

      public void set01_DisableDS(boolean bVal) {
         this.bits[1] = bVal;
      }

      public boolean get01_DisableDS() {
         return this.bits[1];
      }

      public void set02_DisableRC(boolean bVal) {
         this.bits[2] = bVal;
      }

      public boolean get02_DisableRC() {
         return this.bits[2];
      }

      public void set03_DisableSLH(boolean bVal) {
         this.bits[3] = bVal;
      }

      public boolean get03_DisableSLH() {
         return this.bits[3];
      }
   }

   public static class RadioCode {
      private int indexRadioCode;
      private FAAC_Remote.RadioCode.RadioCode1 code1;
      private FAAC_Remote.RadioCode.RadioCode2 code2;
      private boolean isModified;
      public static final int MinIndexRadioCode = 1;
      public static final int MaxIndexRadioCode = 767;
      public static final int SiteCodeIndex = 0;
      public static final String codingSLH_001 = "SLH";
      public static final String codingRC_010 = "RC";
      public static final String codingDS_011 = "DS";

      public RadioCode() {
         this.indexRadioCode = 1;
         this.code1 = new FAAC_Remote.RadioCode.RadioCode1();
         this.code2 = new FAAC_Remote.RadioCode.RadioCode2();
         this.isModified = false;
      }

      public RadioCode(int index) {
         this.indexRadioCode = index;
         this.code1 = new FAAC_Remote.RadioCode.RadioCode1();
         this.code2 = new FAAC_Remote.RadioCode.RadioCode2();
         this.isModified = false;
      }

      public void delete() {
         byte[] nullArray = new byte[]{0, 0, 0, 0};
         this.getCode1().setBytes_MSBfirst(nullArray);
         this.getCode2().setBytes_MSBfirst(nullArray);
         this.isModified = true;
      }

      public void deleteLocal() {
         byte[] nullArray = new byte[]{0, 0, 0, 0};
         this.getCode1().setBytes_MSBfirst(nullArray);
         this.getCode2().setBytes_MSBfirst(nullArray);
      }

      public int getIndexRadioCode() {
         return this.indexRadioCode;
      }

      public void setIndexRadioCode(int indexRadioCode) {
         this.indexRadioCode = indexRadioCode;
      }

      public FAAC_Remote.RadioCode.RadioCode1 getCode1() {
         return this.code1;
      }

      public void setCode1(FAAC_Remote.RadioCode.RadioCode1 code1) {
         this.code1 = code1;
      }

      public FAAC_Remote.RadioCode.RadioCode2 getCode2() {
         return this.code2;
      }

      public void setCode2(FAAC_Remote.RadioCode.RadioCode2 code2) {
         this.code2 = code2;
      }

      public boolean isIsModified() {
         return this.isModified;
      }

      public void setIsModified(boolean isModified) {
         this.isModified = isModified;
      }

      public static class RadioCode1 extends Union32_Abstraction {
         public void set31_Channel_0ch1_1ch2(boolean bVal) {
            this.bits[31] = bVal;
         }

         public boolean get31_Channel_0ch1_1ch2() {
            return this.bits[31];
         }

         public String getSerialCode(String codingType) {
            String str = "";
            if (codingType.equalsIgnoreCase("SLH") || codingType.equalsIgnoreCase("RC")) {
               return this.getSerialCode_RC_SLH();
            } else {
               return codingType.equalsIgnoreCase("DS") ? this.getSerialCode_DS() : str;
            }
         }

         public String getSerialCode_DS() {
            byte[] bytes = this.getBytes_MSBfirst();
            byte[] serialBytes = new byte[3];

            for (int i = 1; i < 4; i++) {
               serialBytes[i - 1] = bytes[i];
            }

            int iRes = FAAC_Protocol.byteArrayToInt(serialBytes);
            String strTemp = Integer.toString(iRes, 2).toUpperCase();
            String zeroPaddedStr = "";
            if (strTemp.length() < 12) {
               int paddingNb = 12 - strTemp.length();
               char[] tmp = strTemp.toCharArray();
               char[] padded = new char[12];

               int i;
               for (i = 0; i < paddingNb; i++) {
                  padded[i] = '0';
               }

               for (int j = i; j < 12; j++) {
                  padded[j] = tmp[j - paddingNb];
               }

               zeroPaddedStr = new String(padded);
            }

            return zeroPaddedStr;
         }

         public String getSerialCode_RC_SLH() {
            byte[] bytes = this.getBytes_MSBfirst();
            byte[] serialBytes = new byte[4];

            for (int i = 0; i < 4; i++) {
               if (i == 0) {
                  serialBytes[i] = (byte)(bytes[i] & 127);
               } else {
                  serialBytes[i] = bytes[i];
               }
            }

            int iRes = FAAC_Protocol.byteArrayToInt(serialBytes);
            String strTemp = Integer.toString(iRes, 16).toUpperCase();
            String zeroPaddedStr = "";
            if (strTemp.length() < 16) {
               int paddingNb = 16 - strTemp.length();
               char[] tmp = strTemp.toCharArray();
               char[] padded = new char[16];

               int ix;
               for (ix = 0; ix < paddingNb; ix++) {
                  padded[ix] = '0';
               }

               for (int j = ix; j < 16; j++) {
                  padded[j] = tmp[j - paddingNb];
               }

               zeroPaddedStr = new String(padded);
            }

            return zeroPaddedStr;
         }

         public void setCodeForBoard(int iChannel, String serialCode, String codingType) {
            if (codingType.equalsIgnoreCase("SLH") || codingType.equalsIgnoreCase("RC")) {
               this.setCodeForBoard_RC_SLH(iChannel, serialCode);
            }

            if (codingType.equalsIgnoreCase("DS")) {
               this.setCodeForBoard_DS(iChannel, serialCode);
            }
         }

         public void setCodeForBoard_DS(int iChannel, String serialCode) {
            int serial = Integer.parseInt(serialCode, 2);
            byte[] serialId = FAAC_Protocol.intTo4bytes_MSBfirst(serial);
            this.setBytes_MSBfirst(serialId);
            if (iChannel == 1) {
               this.bits[31] = false;
            } else {
               this.bits[31] = true;
            }
         }

         public void setCodeForBoard_RC_SLH(int iChannel, String serialCode) {
            int serial = Integer.parseInt(serialCode, 16);
            byte[] serialId = FAAC_Protocol.intTo4bytes_MSBfirst(serial);
            this.setBytes_MSBfirst(serialId);
            if (iChannel == 1) {
               this.bits[31] = false;
            } else {
               this.bits[31] = true;
            }
         }
      }

      public static class RadioCode2 extends Union32_Abstraction {
         public void set31_1disabled0enabled(boolean bVal) {
            this.bits[31] = bVal;
         }

         public boolean get31_1disabled0enabled() {
            return this.bits[31];
         }

         public String getCodingType() {
            String str = "";
            if (!this.bits[30] && !this.bits[29] && this.bits[28]) {
               str = "SLH";
            } else if (!this.bits[30] && this.bits[29] && !this.bits[28]) {
               str = "RC";
            } else if (!this.bits[30] && this.bits[29] && this.bits[28]) {
               str = "DS";
            }

            return str;
         }

         public void setCodingType(String str) {
            if (str.compareTo("") != 0) {
               if (str.compareTo("SLH") == 0) {
                  this.bits[30] = false;
                  this.bits[29] = false;
                  this.bits[28] = true;
               } else if (str.compareTo("RC") == 0) {
                  this.bits[30] = false;
                  this.bits[29] = true;
                  this.bits[28] = false;
               } else if (str.compareTo("DS") == 0) {
                  this.bits[30] = false;
                  this.bits[29] = true;
                  this.bits[28] = true;
               }
            }
         }
      }
   }
}
