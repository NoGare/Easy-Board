package FAAClib;

public class FAAC_frames {
   public static String FILENAME_PRG_E145_wExt = "E145.PRG";
   public static String FILENAME_TMR_E145_wExt = "E145.TMR";
   public static String FILENAME_RAD_E145_wExt = "E145.RAD";
   public static String FILENAME_PRG_E145_noExt = "E145";
   public static String FILENAME_TMR_E145_noExt = "E145";
   public static String FILENAME_RAD_E145_noExt = "E145";
   public static String FILENAME_PRG_E124_wExt = "E124.PRG";
   public static String FILENAME_TMR_E124_wExt = "E124.TMR";
   public static String FILENAME_RAD_E124_wExt = "E124.RAD";
   public static String FILENAME_PRG_E124_noExt = "E124";
   public static String FILENAME_TMR_E124_noExt = "E124";
   public static String FILENAME_RAD_E124_noExt = "E124";
   public static String FILENAME_GCOM = "GCOM.SGC";
   public static String FILENAME_GCOM_CSV = "GCOM.CSV";
   public static String FILE_EXT_PRG = "PRG";
   public static String FILE_EXT_TMR = "TMR";
   public static String FILE_EXT_RAD = "RAD";
   public static String FILE_EXT_BIN = "BIN";
   public static String FILE_EXT_S19 = "S19";
   public static String FILE_EXT_GCOM = "SGC";
   public static String FILE_EXT_CSV = "CSV";
   public static int FILE_PRG_LENGTH = 184;
   public static int FILE_TMR_LENGTH = 312;
   public static int FW_UPDATE_BLOCK_SIZE = 1024;

   public static byte[] getFwUpdateMsg_US() {
      return new byte[]{85, 83};
   }

   public static byte[] getFwUpdateMsg_Lenght(int binLenght) {
      byte[] msg = new byte[5];
      byte[] tmp = FAAC_Protocol.intTo4bytes_MSBfirst(binLenght);
      System.arraycopy(tmp, 0, msg, 0, tmp.length);
      msg[4] = 3;
      return msg;
   }

   public static byte[] getFwUpdateMsg_Block(int index, int length, byte[] fullBinFileContent) {
      int entireBlockNum = length / FW_UPDATE_BLOCK_SIZE;
      int partialBlockLength = length % FW_UPDATE_BLOCK_SIZE;
      byte[] msg;
      if (index < entireBlockNum) {
         msg = new byte[FW_UPDATE_BLOCK_SIZE + 2];
         System.arraycopy(fullBinFileContent, index * FW_UPDATE_BLOCK_SIZE, msg, 0, FW_UPDATE_BLOCK_SIZE);
         byte bCksum = FAAC_Protocol.CheckSum(msg, (byte)0);
         msg[FW_UPDATE_BLOCK_SIZE] = bCksum;
         msg[FW_UPDATE_BLOCK_SIZE + 1] = 3;
      } else {
         msg = new byte[partialBlockLength + 2];
         System.arraycopy(fullBinFileContent, index * FW_UPDATE_BLOCK_SIZE, msg, 0, partialBlockLength);
         byte bCksum = FAAC_Protocol.CheckSum(msg, (byte)0);
         msg[partialBlockLength] = bCksum;
         msg[partialBlockLength + 1] = 3;
      }

      return msg;
   }

   public static boolean isMsgACK(byte[] msg) {
      if (msg[0] != 65) {
         return false;
      } else {
         return msg[1] != 67 ? false : msg[2] == 75;
      }
   }

   public static boolean isMsgSWFEWFACK(byte[] msg) {
      if (msg[0] != 83) {
         return false;
      } else if (msg[1] != 87) {
         return false;
      } else if (msg[2] != 70) {
         return false;
      } else if (msg[3] != 69) {
         return false;
      } else if (msg[4] != 87) {
         return false;
      } else if (msg[5] != 70) {
         return false;
      } else if (msg[6] != 65) {
         return false;
      } else {
         return msg[7] != 67 ? false : msg[8] == 75;
      }
   }

   public static boolean isMsgNCK(byte[] msg) {
      if (msg[0] != 78) {
         return false;
      } else {
         return msg[1] != 67 ? false : msg[2] == 75;
      }
   }

   public static boolean isMsgSEF(byte[] msg) {
      if (msg[0] != 83) {
         return false;
      } else {
         return msg[1] != 69 ? false : msg[2] == 70;
      }
   }

   public static boolean isMsgEEF(byte[] msg) {
      if (msg[0] != 69) {
         return false;
      } else {
         return msg[1] != 69 ? false : msg[2] == 70;
      }
   }

   public static boolean isMsgSWF(byte[] msg) {
      if (msg[0] != 83) {
         return false;
      } else {
         return msg[1] != 87 ? false : msg[2] == 70;
      }
   }

   public static boolean isMsgEWF(byte[] msg) {
      if (msg[0] != 69) {
         return false;
      } else {
         return msg[1] != 87 ? false : msg[2] == 70;
      }
   }

   public static boolean isMsgSWFEWFSBx(byte[] msg) {
      if (msg[0] != 83) {
         return false;
      } else if (msg[1] != 87) {
         return false;
      } else if (msg[2] != 70) {
         return false;
      } else if (msg[3] != 69) {
         return false;
      } else if (msg[4] != 87) {
         return false;
      } else if (msg[5] != 70) {
         return false;
      } else {
         return msg[6] != 83 ? false : msg[7] == 66;
      }
   }

   public static int getMsgSWFEWFSBxIndex(byte[] msg) {
      return msg[8];
   }

   public static boolean isMsgSBx(byte[] msg) {
      return msg[0] != 83 ? false : msg[1] == 66;
   }

   public static int getMsgSBxIndex(byte[] msg) {
      return msg[2];
   }

   public static boolean isMsgEEFSB1(byte[] msg) {
      if (msg[0] != 69) {
         return false;
      } else if (msg[1] != 69) {
         return false;
      } else if (msg[2] != 70) {
         return false;
      } else {
         return msg[3] != 83 ? false : msg[4] == 66;
      }
   }

   public static int getMsgEEFSB1Index(byte[] msg) {
      return msg[5];
   }

   public static byte[] getFrame0x01() {
      String password = "0000";
      return getFrame0x01(password);
   }

   public static byte[] getFrame0x01(String password) {
      byte[] codedFrame01 = new byte[18];
      byte[] uncodedFrame01 = new byte[8];
      codedFrame01[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame01[0] = lenghtPayload;
      uncodedFrame01[1] = 0;
      byte dataRequest = 1;
      uncodedFrame01[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame01[3] = psw[0];
      uncodedFrame01[4] = psw[1];
      uncodedFrame01[5] = psw[2];
      uncodedFrame01[6] = psw[3];
      byte[] payload0x01 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame01, 2, payload0x01, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x01, (byte)0);
      uncodedFrame01[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame01);
      System.arraycopy(res, 0, codedFrame01, 1, res.length);
      codedFrame01[17] = 3;
      return codedFrame01;
   }

   public static byte[] getFrame0x02() {
      String password = "0000";
      return getFrame0x02(password);
   }

   public static byte[] getFrame0x02(String password) {
      byte[] codedFrame02 = new byte[18];
      byte[] uncodedFrame02 = new byte[8];
      codedFrame02[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame02[0] = lenghtPayload;
      uncodedFrame02[1] = 0;
      byte dataRequest = 2;
      uncodedFrame02[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame02[3] = psw[0];
      uncodedFrame02[4] = psw[1];
      uncodedFrame02[5] = psw[2];
      uncodedFrame02[6] = psw[3];
      byte[] payload0x02 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame02, 2, payload0x02, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x02, (byte)0);
      uncodedFrame02[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame02);
      System.arraycopy(res, 0, codedFrame02, 1, res.length);
      codedFrame02[17] = 3;
      return codedFrame02;
   }

   public static byte[] getFrame0x03() {
      String password = "0000";
      return getFrame0x03(password);
   }

   public static byte[] getFrame0x03(String password) {
      byte[] codedFrame03 = new byte[18];
      byte[] uncodedFrame03 = new byte[8];
      codedFrame03[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame03[0] = lenghtPayload;
      uncodedFrame03[1] = 0;
      byte dataRequest = 3;
      uncodedFrame03[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame03[3] = psw[0];
      uncodedFrame03[4] = psw[1];
      uncodedFrame03[5] = psw[2];
      uncodedFrame03[6] = psw[3];
      byte[] payload0x03 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame03, 2, payload0x03, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x03, (byte)0);
      uncodedFrame03[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame03);
      System.arraycopy(res, 0, codedFrame03, 1, res.length);
      codedFrame03[17] = 3;
      return codedFrame03;
   }

   public static byte[] getFrame0x04() {
      String password = "0000";
      return getFrame0x04(password);
   }

   public static byte[] getFrame0x04(String password) {
      byte[] codedFrame04 = new byte[18];
      byte[] uncodedFrame04 = new byte[8];
      codedFrame04[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame04[0] = lenghtPayload;
      uncodedFrame04[1] = 0;
      byte dataRequest = 4;
      uncodedFrame04[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame04[3] = psw[0];
      uncodedFrame04[4] = psw[1];
      uncodedFrame04[5] = psw[2];
      uncodedFrame04[6] = psw[3];
      byte[] payload0x04 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame04, 2, payload0x04, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x04, (byte)0);
      uncodedFrame04[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame04);
      System.arraycopy(res, 0, codedFrame04, 1, res.length);
      codedFrame04[17] = 3;
      return codedFrame04;
   }

   public static byte[] getFrame0x0C() {
      String password = "0000";
      return getFrame0x0C(password);
   }

   public static byte[] getFrame0x0C(String password) {
      byte[] codedFrame0C = new byte[18];
      byte[] uncodedFrame0C = new byte[8];
      codedFrame0C[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame0C[0] = lenghtPayload;
      uncodedFrame0C[1] = 0;
      byte dataRequest = 12;
      uncodedFrame0C[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame0C[3] = psw[0];
      uncodedFrame0C[4] = psw[1];
      uncodedFrame0C[5] = psw[2];
      uncodedFrame0C[6] = psw[3];
      byte[] payload0x0C = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame0C, 2, payload0x0C, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x0C, (byte)0);
      uncodedFrame0C[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame0C);
      System.arraycopy(res, 0, codedFrame0C, 1, res.length);
      codedFrame0C[17] = 3;
      return codedFrame0C;
   }

   public static byte[] getFrame0x05() {
      byte[] codedFrame05 = new byte[8];
      byte[] uncodedFrame05 = new byte[3];
      codedFrame05[0] = 2;
      byte lenghtPayload = 1;
      uncodedFrame05[0] = lenghtPayload;
      uncodedFrame05[1] = 0;
      byte dataRequest = 5;
      uncodedFrame05[2] = dataRequest;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame05);
      System.arraycopy(res, 0, codedFrame05, 1, res.length);
      codedFrame05[7] = 3;
      return codedFrame05;
   }

   public static byte[] getFrame0x06() {
      String password = "0000";
      return getFrame0x06(password);
   }

   public static byte[] getFrame0x06(String password) {
      byte[] codedFrame06 = new byte[18];
      byte[] uncodedFrame06 = new byte[8];
      codedFrame06[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame06[0] = lenghtPayload;
      uncodedFrame06[1] = 0;
      byte dataRequest = 6;
      uncodedFrame06[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame06[3] = psw[0];
      uncodedFrame06[4] = psw[1];
      uncodedFrame06[5] = psw[2];
      uncodedFrame06[6] = psw[3];
      byte[] payload0x06 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame06, 2, payload0x06, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x06, (byte)0);
      uncodedFrame06[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame06);
      System.arraycopy(res, 0, codedFrame06, 1, res.length);
      codedFrame06[17] = 3;
      return codedFrame06;
   }

   public static byte[] getFrame0x07(String password) {
      byte[] codedFrame07 = new byte[18];
      byte[] uncodedFrame07 = new byte[8];
      codedFrame07[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame07[0] = lenghtPayload;
      uncodedFrame07[1] = 0;
      byte dataRequest = 7;
      uncodedFrame07[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame07[3] = psw[0];
      uncodedFrame07[4] = psw[1];
      uncodedFrame07[5] = psw[2];
      uncodedFrame07[6] = psw[3];
      byte[] payload0x07 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame07, 2, payload0x07, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x07, (byte)0);
      uncodedFrame07[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame07);
      System.arraycopy(res, 0, codedFrame07, 1, res.length);
      codedFrame07[17] = 3;
      return codedFrame07;
   }

   public static byte[] getFrame0x08() {
      String password = "0000";
      return getFrame0x08(password);
   }

   public static byte[] getFrame0x08(String password) {
      byte[] codedFrame08 = new byte[18];
      byte[] uncodedFrame08 = new byte[8];
      codedFrame08[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame08[0] = lenghtPayload;
      uncodedFrame08[1] = 0;
      byte dataRequest = 8;
      uncodedFrame08[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame08[3] = psw[0];
      uncodedFrame08[4] = psw[1];
      uncodedFrame08[5] = psw[2];
      uncodedFrame08[6] = psw[3];
      byte[] payload0x08 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame08, 2, payload0x08, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x08, (byte)0);
      uncodedFrame08[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame08);
      System.arraycopy(res, 0, codedFrame08, 1, res.length);
      codedFrame08[17] = 3;
      return codedFrame08;
   }

   public static byte[] getFrame0x0A(String password, int logIndex) {
      byte[] codedFrame0A = new byte[20];
      byte[] uncodedFrame0A = new byte[9];
      codedFrame0A[0] = 2;
      byte lenghtPayload = 6;
      uncodedFrame0A[0] = lenghtPayload;
      uncodedFrame0A[1] = 0;
      byte dataRequest = 10;
      uncodedFrame0A[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame0A[3] = psw[0];
      uncodedFrame0A[4] = psw[1];
      uncodedFrame0A[5] = psw[2];
      uncodedFrame0A[6] = psw[3];
      uncodedFrame0A[7] = (byte)logIndex;
      byte[] payload0x0A = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame0A, 2, payload0x0A, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x0A, (byte)0);
      uncodedFrame0A[8] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame0A);
      System.arraycopy(res, 0, codedFrame0A, 1, res.length);
      codedFrame0A[19] = 3;
      return codedFrame0A;
   }

   public static byte[] getFrame0x11(String password, int logIndex) {
      byte[] codedFrame11 = new byte[22];
      byte[] uncodedFrame11 = new byte[10];
      codedFrame11[0] = 2;
      byte lenghtPayload = 7;
      uncodedFrame11[0] = lenghtPayload;
      uncodedFrame11[1] = 0;
      byte dataRequest = 17;
      uncodedFrame11[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame11[3] = psw[0];
      uncodedFrame11[4] = psw[1];
      uncodedFrame11[5] = psw[2];
      uncodedFrame11[6] = psw[3];
      byte[] logIndexByte = FAAC_Protocol.intToByteArray(logIndex);
      uncodedFrame11[7] = logIndexByte[3];
      uncodedFrame11[8] = logIndexByte[2];
      byte[] payload0x11 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame11, 2, payload0x11, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x11, (byte)0);
      uncodedFrame11[9] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame11);
      System.arraycopy(res, 0, codedFrame11, 1, res.length);
      codedFrame11[21] = 3;
      return codedFrame11;
   }

   public static byte[] getFrame0x09() {
      String password = "0000";
      return getFrame0x09(password);
   }

   public static byte[] getFrame0x09(String password) {
      byte[] codedFrame09 = new byte[18];
      byte[] uncodedFrame09 = new byte[8];
      codedFrame09[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame09[0] = lenghtPayload;
      uncodedFrame09[1] = 0;
      byte dataRequest = 9;
      uncodedFrame09[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame09[3] = psw[0];
      uncodedFrame09[4] = psw[1];
      uncodedFrame09[5] = psw[2];
      uncodedFrame09[6] = psw[3];
      byte[] payload0x09 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame09, 2, payload0x09, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x09, (byte)0);
      uncodedFrame09[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame09);
      System.arraycopy(res, 0, codedFrame09, 1, res.length);
      codedFrame09[17] = 3;
      return codedFrame09;
   }

   public static byte[] getFrame0x10(String password, int timerDayIndex) {
      byte[] codedFrame10 = new byte[20];
      byte[] uncodedFrame10 = new byte[9];
      codedFrame10[0] = 2;
      byte lenghtPayload = 6;
      uncodedFrame10[0] = lenghtPayload;
      uncodedFrame10[1] = 0;
      byte dataRequest = 16;
      uncodedFrame10[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame10[3] = psw[0];
      uncodedFrame10[4] = psw[1];
      uncodedFrame10[5] = psw[2];
      uncodedFrame10[6] = psw[3];
      uncodedFrame10[7] = (byte)timerDayIndex;
      byte[] payload0x10 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame10, 2, payload0x10, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x10, (byte)0);
      uncodedFrame10[8] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame10);
      System.arraycopy(res, 0, codedFrame10, 1, res.length);
      codedFrame10[19] = 3;
      return codedFrame10;
   }

   public static byte[] getFrame0x14(String password) {
      byte[] codedFrame14 = new byte[18];
      byte[] uncodedFrame14 = new byte[8];
      codedFrame14[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame14[0] = lenghtPayload;
      uncodedFrame14[1] = 0;
      byte dataRequest = 20;
      uncodedFrame14[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame14[3] = psw[0];
      uncodedFrame14[4] = psw[1];
      uncodedFrame14[5] = psw[2];
      uncodedFrame14[6] = psw[3];
      byte[] payload0x14 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame14, 2, payload0x14, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x14, (byte)0);
      uncodedFrame14[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame14);
      System.arraycopy(res, 0, codedFrame14, 1, res.length);
      codedFrame14[17] = 3;
      return codedFrame14;
   }

   public static byte[] getFrame0x20(String password, int radioCodeIndex) {
      byte[] codedFrame20 = new byte[22];
      byte[] uncodedFrame20 = new byte[10];
      codedFrame20[0] = 2;
      byte lenghtPayload = 7;
      uncodedFrame20[0] = lenghtPayload;
      uncodedFrame20[1] = 0;
      byte dataRequest = 32;
      uncodedFrame20[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame20[3] = psw[0];
      uncodedFrame20[4] = psw[1];
      uncodedFrame20[5] = psw[2];
      uncodedFrame20[6] = psw[3];
      byte[] radioCodeBytes = FAAC_Protocol.intTo4bytes_MSBfirst(radioCodeIndex);
      uncodedFrame20[7] = radioCodeBytes[2];
      uncodedFrame20[8] = radioCodeBytes[3];
      byte[] payload0x20 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame20, 2, payload0x20, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x20, (byte)0);
      uncodedFrame20[9] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame20);
      System.arraycopy(res, 0, codedFrame20, 1, res.length);
      codedFrame20[21] = 3;
      return codedFrame20;
   }

   public static byte[] getFrame0x2C(String password) {
      byte[] codedFrame2C = new byte[18];
      byte[] uncodedFrame2C = new byte[8];
      codedFrame2C[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame2C[0] = lenghtPayload;
      uncodedFrame2C[1] = 0;
      byte dataRequest = 44;
      uncodedFrame2C[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame2C[3] = psw[0];
      uncodedFrame2C[4] = psw[1];
      uncodedFrame2C[5] = psw[2];
      uncodedFrame2C[6] = psw[3];
      byte[] payload0x2C = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame2C, 2, payload0x2C, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x2C, (byte)0);
      uncodedFrame2C[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame2C);
      System.arraycopy(res, 0, codedFrame2C, 1, res.length);
      codedFrame2C[17] = 3;
      return codedFrame2C;
   }

   public static byte[] getFrame0x2D(String password) {
      byte[] codedFrame2D = new byte[18];
      byte[] uncodedFrame2D = new byte[8];
      codedFrame2D[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame2D[0] = lenghtPayload;
      uncodedFrame2D[1] = 0;
      byte dataRequest = 45;
      uncodedFrame2D[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame2D[3] = psw[0];
      uncodedFrame2D[4] = psw[1];
      uncodedFrame2D[5] = psw[2];
      uncodedFrame2D[6] = psw[3];
      byte[] payload0x2D = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame2D, 2, payload0x2D, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x2D, (byte)0);
      uncodedFrame2D[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame2D);
      System.arraycopy(res, 0, codedFrame2D, 1, res.length);
      codedFrame2D[17] = 3;
      return codedFrame2D;
   }

   public static byte[] getFrame0x2E(String password, int pbIndex) {
      byte[] codedFrame2E = new byte[22];
      byte[] uncodedFrame2E = new byte[10];
      codedFrame2E[0] = 2;
      byte lenghtPayload = 7;
      uncodedFrame2E[0] = lenghtPayload;
      uncodedFrame2E[1] = 0;
      byte dataRequest = 46;
      uncodedFrame2E[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame2E[3] = psw[0];
      uncodedFrame2E[4] = psw[1];
      uncodedFrame2E[5] = psw[2];
      uncodedFrame2E[6] = psw[3];
      uncodedFrame2E[7] = (byte)(pbIndex >> 8);
      uncodedFrame2E[8] = (byte)pbIndex;
      byte[] payload0x2E = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame2E, 2, payload0x2E, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x2E, (byte)0);
      uncodedFrame2E[9] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame2E);
      System.arraycopy(res, 0, codedFrame2E, 1, res.length);
      codedFrame2E[21] = 3;
      return codedFrame2E;
   }

   public static byte[] getFrame0x30(String password) {
      byte[] codedFrame30 = new byte[18];
      byte[] uncodedFrame30 = new byte[8];
      codedFrame30[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame30[0] = lenghtPayload;
      uncodedFrame30[1] = 0;
      byte dataRequest = 48;
      uncodedFrame30[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame30[3] = psw[0];
      uncodedFrame30[4] = psw[1];
      uncodedFrame30[5] = psw[2];
      uncodedFrame30[6] = psw[3];
      byte[] payload0x30 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame30, 2, payload0x30, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x30, (byte)0);
      uncodedFrame30[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame30);
      System.arraycopy(res, 0, codedFrame30, 1, res.length);
      codedFrame30[17] = 3;
      return codedFrame30;
   }

   public static byte[] getFrame0x0B(String password) {
      byte[] codedFrame0B = new byte[18];
      byte[] uncodedFrame0B = new byte[8];
      codedFrame0B[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame0B[0] = lenghtPayload;
      uncodedFrame0B[1] = 0;
      byte dataRequest = 11;
      uncodedFrame0B[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame0B[3] = psw[0];
      uncodedFrame0B[4] = psw[1];
      uncodedFrame0B[5] = psw[2];
      uncodedFrame0B[6] = psw[3];
      byte[] payload0x0B = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame0B, 2, payload0x0B, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x0B, (byte)0);
      uncodedFrame0B[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame0B);
      System.arraycopy(res, 0, codedFrame0B, 1, res.length);
      codedFrame0B[17] = 3;
      return codedFrame0B;
   }

   public static byte[] getFrame0x0E(String password) {
      byte[] codedFrame0E = new byte[18];
      byte[] uncodedFrame0E = new byte[8];
      codedFrame0E[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame0E[0] = lenghtPayload;
      uncodedFrame0E[1] = 0;
      byte dataRequest = 14;
      uncodedFrame0E[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame0E[3] = psw[0];
      uncodedFrame0E[4] = psw[1];
      uncodedFrame0E[5] = psw[2];
      uncodedFrame0E[6] = psw[3];
      byte[] payload0x0E = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame0E, 2, payload0x0E, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x0E, (byte)0);
      uncodedFrame0E[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame0E);
      System.arraycopy(res, 0, codedFrame0E, 1, res.length);
      codedFrame0E[17] = 3;
      return codedFrame0E;
   }

   public static byte[] getFrame0x0D(String password) {
      byte[] codedFrame0D = new byte[18];
      byte[] uncodedFrame0D = new byte[8];
      codedFrame0D[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame0D[0] = lenghtPayload;
      uncodedFrame0D[1] = 0;
      byte dataRequest = 13;
      uncodedFrame0D[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame0D[3] = psw[0];
      uncodedFrame0D[4] = psw[1];
      uncodedFrame0D[5] = psw[2];
      uncodedFrame0D[6] = psw[3];
      byte[] payload0x0D = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame0D, 2, payload0x0D, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x0D, (byte)0);
      uncodedFrame0D[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame0D);
      System.arraycopy(res, 0, codedFrame0D, 1, res.length);
      codedFrame0D[17] = 3;
      return codedFrame0D;
   }

   private static byte[] getUncodedPartFrame0x81(String password, FAAC_Settings boardSettings) {
      byte[] uncodedFrame81 = new byte[39];
      byte lenghtPayload = 36;
      uncodedFrame81[0] = lenghtPayload;
      uncodedFrame81[1] = 0;
      byte dataRequest = -127;
      uncodedFrame81[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame81[3] = psw[0];
      uncodedFrame81[4] = psw[1];
      uncodedFrame81[5] = psw[2];
      uncodedFrame81[6] = psw[3];
      FAAC_Settings.LogicFlag logFlag = boardSettings.getLogicFlag();
      byte[] logBytes = logFlag.getBytes_LSBfirst();
      uncodedFrame81[7] = logBytes[0];
      uncodedFrame81[8] = logBytes[1];
      uncodedFrame81[9] = logBytes[2];
      uncodedFrame81[10] = logBytes[3];
      FAAC_Settings.ProgrammingFlag progFlag = boardSettings.getProgrammingFlag();
      byte[] progBytes = progFlag.getBytes_LSBfirst();
      uncodedFrame81[11] = progBytes[0];
      uncodedFrame81[12] = progBytes[1];
      uncodedFrame81[13] = progBytes[2];
      uncodedFrame81[14] = progBytes[3];
      int timeOpenA = boardSettings.getTimePauseA_sec();
      byte[] pauseOpenA = FAAC_Protocol.intToByteArray(timeOpenA);
      uncodedFrame81[15] = pauseOpenA[3];
      uncodedFrame81[16] = pauseOpenA[2];
      int timeOpenB = boardSettings.getTimePauseB_sec();
      byte[] pauseOpenB = FAAC_Protocol.intToByteArray(timeOpenB);
      uncodedFrame81[17] = pauseOpenB[3];
      uncodedFrame81[18] = pauseOpenB[2];
      int timeSleep = boardSettings.getTimeSleep_sec();
      byte[] timeSleepArray = FAAC_Protocol.intToByteArray(timeSleep);
      uncodedFrame81[19] = timeSleepArray[3];
      uncodedFrame81[20] = timeSleepArray[2];
      int timeOut = boardSettings.getTimeoutMov_sec();
      byte[] timeOutArray = FAAC_Protocol.intToByteArray(timeOut);
      uncodedFrame81[21] = timeOutArray[3];
      uncodedFrame81[22] = timeOutArray[2];
      uncodedFrame81[23] = (byte)boardSettings.getForceOpenMotor1();
      uncodedFrame81[24] = (byte)boardSettings.getForceOpenMotor2();
      uncodedFrame81[25] = (byte)boardSettings.getForceCloseMotor1();
      uncodedFrame81[26] = (byte)boardSettings.getForceCloseMotor2();
      uncodedFrame81[27] = (byte)boardSettings.getMotor1Type();
      uncodedFrame81[28] = (byte)boardSettings.getMotor2Type();
      uncodedFrame81[29] = (byte)boardSettings.getRallSpaceOpenMotor1();
      uncodedFrame81[30] = (byte)boardSettings.getRallSpaceOpenMotor2();
      uncodedFrame81[31] = (byte)boardSettings.getRallSpaceCloseMotor1();
      uncodedFrame81[32] = (byte)boardSettings.getRallSpaceCloseMotor2();
      uncodedFrame81[33] = (byte)boardSettings.getBattSpaceOpenMotor1_01deg();
      uncodedFrame81[34] = (byte)boardSettings.getBattSpaceOpenMotor2_01deg();
      uncodedFrame81[35] = (byte)boardSettings.getBattSpaceCloseMotor1_01deg();
      uncodedFrame81[36] = (byte)boardSettings.getBattSpaceCloseMotor2_01deg();
      uncodedFrame81[37] = (byte)boardSettings.getPartialOpeningOpenB();
      byte[] payload0x81 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame81, 2, payload0x81, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x81, (byte)0);
      uncodedFrame81[38] = bCksum;
      return uncodedFrame81;
   }

   private static byte[] getUncodedPartFrame0x81_ForFile(String password, FAAC_Settings boardSettings) {
      byte[] uncodedFrame81 = new byte[32];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0x81(password, boardSettings);

      for (int i = 0; i < uncodedFrame81.length - 1; i++) {
         uncodedFrame81[i] = uncodedFrameForBoard[i + 7];
      }

      byte lenghtPayload = 31;
      byte[] payload0x81 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame81, 0, payload0x81, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x81, (byte)0);
      uncodedFrame81[31] = bCksum;
      return uncodedFrame81;
   }

   public static byte[] getFrame0x81(String password, FAAC_Settings boardSettings) {
      byte[] codedFrame81 = new byte[80];
      byte[] uncodedFrame81 = new byte[39];
      codedFrame81[0] = 2;
      uncodedFrame81 = getUncodedPartFrame0x81(password, boardSettings);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame81);
      System.arraycopy(res, 0, codedFrame81, 1, res.length);
      codedFrame81[79] = 3;
      return codedFrame81;
   }

   private static byte[] getUncodedPartFrame0x82(String password, FAAC_Settings boardSettings) {
      byte[] uncodedFrame82 = new byte[39];
      byte lenghtPayload = 36;
      uncodedFrame82[0] = lenghtPayload;
      uncodedFrame82[1] = 0;
      byte dataRequest = -126;
      uncodedFrame82[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame82[3] = psw[0];
      uncodedFrame82[4] = psw[1];
      uncodedFrame82[5] = psw[2];
      uncodedFrame82[6] = psw[3];
      int nbcycle = boardSettings.getNbcycleAssistance();
      byte[] nbCycleBytes = FAAC_Protocol.intToByteArray(nbcycle);
      uncodedFrame82[7] = nbCycleBytes[3];
      uncodedFrame82[8] = nbCycleBytes[2];
      int timeHold = boardSettings.getTimeHoldclose_sec();
      byte[] timeHoldByte = FAAC_Protocol.intToByteArray(timeHold);
      uncodedFrame82[9] = timeHoldByte[3];
      uncodedFrame82[10] = timeHoldByte[2];
      uncodedFrame82[11] = (byte)boardSettings.getTimeDelayOpenAnta_sec();
      uncodedFrame82[12] = (byte)boardSettings.getTimeDelayCloseAnta_sec();
      uncodedFrame82[13] = (byte)boardSettings.getTimeReverseStrokeAnta1_100msec();
      uncodedFrame82[14] = (byte)boardSettings.getTimeReverseStrokeAnta2_100msec();
      uncodedFrame82[15] = (byte)boardSettings.getTypeReverseStrokeAnta1();
      uncodedFrame82[16] = (byte)boardSettings.getTypeReverseStrokeAnta2();
      uncodedFrame82[17] = (byte)boardSettings.getTimeLock1_100msec();
      uncodedFrame82[18] = (byte)boardSettings.getTimeLock2_100msec();
      FAAC_Settings.TypeLock typeLock1 = boardSettings.getTypeLock1();
      uncodedFrame82[19] = typeLock1.getByte();
      FAAC_Settings.TypeLock typeLock2 = boardSettings.getTypeLock2();
      uncodedFrame82[20] = typeLock2.getByte();
      uncodedFrame82[21] = (byte)boardSettings.getTimeColpoFinAnta1_100msec();
      uncodedFrame82[22] = (byte)boardSettings.getTimeColpoFinAnta2_100msec();
      uncodedFrame82[23] = (byte)boardSettings.getTypeColpoFinAnta1();
      uncodedFrame82[24] = (byte)boardSettings.getTypeColpoFinAnta2();
      uncodedFrame82[25] = (byte)boardSettings.getTimeSpuntoAnta1_100msec();
      uncodedFrame82[26] = (byte)boardSettings.getTimeSpuntoAnta2_100msec();
      uncodedFrame82[27] = (byte)boardSettings.getFinecorsaOpenAnta1();
      uncodedFrame82[28] = (byte)boardSettings.getFinecorsaOpenAnta2();
      uncodedFrame82[29] = (byte)boardSettings.getFinecorsaCloseAnta1();
      uncodedFrame82[30] = (byte)boardSettings.getFinecorsaCloseAnta2();
      uncodedFrame82[31] = (byte)boardSettings.getTimePrelamp_100msec();
      uncodedFrame82[32] = (byte)boardSettings.getTypePrelamp();
      uncodedFrame82[33] = (byte)boardSettings.getEncoderMotor1();
      uncodedFrame82[34] = (byte)boardSettings.getEncoderMotor2();
      uncodedFrame82[35] = 0;
      uncodedFrame82[36] = 0;
      uncodedFrame82[37] = (byte)boardSettings.getTimeInvObstPartial_100msec();
      byte[] payload0x82 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame82, 2, payload0x82, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x82, (byte)0);
      uncodedFrame82[38] = bCksum;
      return uncodedFrame82;
   }

   private static byte[] getUncodedPartFrame0x82_ForFile(String password, FAAC_Settings boardSettings) {
      byte[] uncodedFrame82 = new byte[32];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0x82(password, boardSettings);

      for (int i = 0; i < uncodedFrame82.length - 1; i++) {
         uncodedFrame82[i] = uncodedFrameForBoard[i + 7];
      }

      byte lenghtPayload = 31;
      byte[] payload0x82 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame82, 0, payload0x82, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x82, (byte)0);
      uncodedFrame82[31] = bCksum;
      return uncodedFrame82;
   }

   public static byte[] getFrame0x82(String password, FAAC_Settings boardSettings) {
      byte[] codedFrame82 = new byte[80];
      byte[] uncodedFrame82 = new byte[39];
      codedFrame82[0] = 2;
      uncodedFrame82 = getUncodedPartFrame0x82(password, boardSettings);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame82);
      System.arraycopy(res, 0, codedFrame82, 1, res.length);
      codedFrame82[79] = 3;
      return codedFrame82;
   }

   private static byte[] getUncodedPartFrame0x83(String password, FAAC_Settings boardSettings) {
      byte[] uncodedFrame83 = new byte[39];
      byte lenghtPayload = 36;
      uncodedFrame83[0] = lenghtPayload;
      uncodedFrame83[1] = 0;
      byte dataRequest = -125;
      uncodedFrame83[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame83[3] = psw[0];
      uncodedFrame83[4] = psw[1];
      uncodedFrame83[5] = psw[2];
      uncodedFrame83[6] = psw[3];

      for (int i = 0; i < 16; i++) {
         uncodedFrame83[7 + i] = boardSettings.getSec2easyTypeArray(i).getInTypeByteForBoard();
      }

      for (int i = 0; i < 14; i++) {
         uncodedFrame83[23 + i] = boardSettings.getDatImp2easyTypeArray(i).getInTypeByteForBoard();
      }

      uncodedFrame83[37] = 0;
      byte[] payload0x83 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame83, 2, payload0x83, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x83, (byte)0);
      uncodedFrame83[38] = bCksum;
      return uncodedFrame83;
   }

   private static byte[] getUncodedPartFrame0x83_ForFile(String password, FAAC_Settings boardSettings) {
      byte[] uncodedFrame83 = new byte[32];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0x83(password, boardSettings);

      for (int i = 0; i < uncodedFrame83.length - 1; i++) {
         uncodedFrame83[i] = uncodedFrameForBoard[i + 7];
      }

      byte lenghtPayload = 31;
      byte[] payload0x83 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame83, 0, payload0x83, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x83, (byte)0);
      uncodedFrame83[31] = bCksum;
      return uncodedFrame83;
   }

   public static byte[] getFrame0x83(String password, FAAC_Settings boardSettings) {
      byte[] codedFrame83 = new byte[80];
      byte[] uncodedFrame83 = new byte[39];
      codedFrame83[0] = 2;
      uncodedFrame83 = getUncodedPartFrame0x83(password, boardSettings);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame83);
      System.arraycopy(res, 0, codedFrame83, 1, res.length);
      codedFrame83[79] = 3;
      return codedFrame83;
   }

   private static byte[] getUncodedPartFrame0x84(String password, FAAC_Settings boardSettings, String boardModel) {
      byte[] uncodedFrame84 = new byte[39];
      byte lenghtPayload = 36;
      uncodedFrame84[0] = lenghtPayload;
      uncodedFrame84[1] = 0;
      byte dataRequest = -124;
      uncodedFrame84[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame84[3] = psw[0];
      uncodedFrame84[4] = psw[1];
      uncodedFrame84[5] = psw[2];
      uncodedFrame84[6] = psw[3];
      if (boardModel.compareToIgnoreCase("E145") == 0) {
         uncodedFrame84[7] = (byte)((E145_Settings)boardSettings).getLivFrenataScorr1();
         uncodedFrame84[8] = (byte)((E145_Settings)boardSettings).getLivFrenataScorr2();
      }

      uncodedFrame84[9] = (byte)boardSettings.getTimeOut1_secOrMin();
      uncodedFrame84[10] = (byte)boardSettings.getTimeOut2_secOrMin();
      uncodedFrame84[11] = boardSettings.getOutType(0).getOutTypeByteForBoard();
      uncodedFrame84[12] = boardSettings.getOutType(1).getOutTypeByteForBoard();
      uncodedFrame84[13] = boardSettings.getInType(0).getInTypeByteForBoard();
      uncodedFrame84[14] = boardSettings.getInType(1).getInTypeByteForBoard();
      uncodedFrame84[15] = boardSettings.getInType(2).getInTypeByteForBoard();
      uncodedFrame84[16] = boardSettings.getInType(3).getInTypeByteForBoard();
      uncodedFrame84[17] = boardSettings.getInType(4).getInTypeByteForBoard();
      uncodedFrame84[18] = boardSettings.getRadioDecType(0).getInTypeByteForBoard();
      uncodedFrame84[19] = boardSettings.getRadioDecType(1).getInTypeByteForBoard();
      uncodedFrame84[20] = boardSettings.getRadioXfType(0).getInTypeByteForBoard();
      uncodedFrame84[21] = boardSettings.getRadioXfType(1).getInTypeByteForBoard();
      uncodedFrame84[22] = (byte)boardSettings.getSensObstOpenAnta1_100msec();
      uncodedFrame84[23] = (byte)boardSettings.getSensObstOpenAnta2_100msec();
      uncodedFrame84[24] = (byte)boardSettings.getSensObstCloseAnta1_100msec();
      uncodedFrame84[25] = (byte)boardSettings.getSensObstCloseAnta2_100msec();
      uncodedFrame84[26] = (byte)boardSettings.getSensObstRallOpenAnta1_100msec();
      uncodedFrame84[27] = (byte)boardSettings.getSensObstRallOpenAnta2_100msec();
      uncodedFrame84[28] = (byte)boardSettings.getSensObstRallCloseAnta1_100msec();
      uncodedFrame84[29] = (byte)boardSettings.getSensObstRallCloseAnta2_100msec();
      uncodedFrame84[30] = (byte)boardSettings.getTimeAddAnta1_sec();
      uncodedFrame84[31] = 0;
      uncodedFrame84[32] = boardSettings.getProg4_Byte31();
      uncodedFrame84[33] = boardSettings.getProg4_Byte32();
      uncodedFrame84[34] = (byte)boardSettings.getSoftstart1();
      uncodedFrame84[35] = (byte)boardSettings.getSoftstart2();
      uncodedFrame84[36] = (byte)boardSettings.getNumObstCons();
      uncodedFrame84[37] = 0;
      byte[] payload0x84 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame84, 2, payload0x84, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x84, (byte)0);
      uncodedFrame84[38] = bCksum;
      return uncodedFrame84;
   }

   private static byte[] getUncodedPartFrame0x84_ForFile(String password, FAAC_Settings boardSettings, String boardModel) {
      byte[] uncodedFrame84 = new byte[32];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0x84(password, boardSettings, boardModel);

      for (int i = 0; i < uncodedFrame84.length - 1; i++) {
         uncodedFrame84[i] = uncodedFrameForBoard[i + 7];
      }

      byte lenghtPayload = 31;
      byte[] payload0x84 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame84, 0, payload0x84, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x84, (byte)0);
      uncodedFrame84[31] = bCksum;
      return uncodedFrame84;
   }

   public static byte[] getFrame0x84(String password, FAAC_Settings boardSettings, String boardModel) {
      byte[] codedFrame84 = new byte[80];
      byte[] uncodedFrame84 = new byte[39];
      codedFrame84[0] = 2;
      uncodedFrame84 = getUncodedPartFrame0x84(password, boardSettings, boardModel);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame84);
      System.arraycopy(res, 0, codedFrame84, 1, res.length);
      codedFrame84[79] = 3;
      return codedFrame84;
   }

   private static byte[] getUncodedPartFrame0x9C(String password, FAAC_Settings boardSettings, String boardModel) {
      byte[] uncodedFrame9C = new byte[39];
      byte lenghtPayload = 36;
      uncodedFrame9C[0] = lenghtPayload;
      uncodedFrame9C[1] = 0;
      byte dataRequest = -100;
      uncodedFrame9C[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame9C[3] = psw[0];
      uncodedFrame9C[4] = psw[1];
      uncodedFrame9C[5] = psw[2];
      uncodedFrame9C[6] = psw[3];
      int nextIndex = 0;
      byte var10;
      if (boardModel.compareToIgnoreCase("E124") == 0) {
         uncodedFrame9C[7] = (byte)((E124_Settings)boardSettings).getSpeedOpen1();
         uncodedFrame9C[8] = (byte)((E124_Settings)boardSettings).getSpeedOpen2();
         uncodedFrame9C[9] = (byte)((E124_Settings)boardSettings).getSpeedClose1();
         uncodedFrame9C[10] = (byte)((E124_Settings)boardSettings).getSpeedClose2();
         uncodedFrame9C[11] = 0;
         uncodedFrame9C[12] = 0;
         uncodedFrame9C[13] = 0;
         uncodedFrame9C[14] = 0;
         uncodedFrame9C[15] = (byte)((E124_Settings)boardSettings).getUltrasense1();
         uncodedFrame9C[16] = (byte)((E124_Settings)boardSettings).getUltrasense2();
         uncodedFrame9C[17] = (byte)((E124_Settings)boardSettings).getSpeedRall1();
         uncodedFrame9C[18] = (byte)((E124_Settings)boardSettings).getSpeedRall2();
         uncodedFrame9C[19] = (byte)((E124_Settings)boardSettings).getTimeSoftTouchAnta1_100msec();
         uncodedFrame9C[20] = (byte)((E124_Settings)boardSettings).getTimeSoftTouchAnta2_100msec();
         uncodedFrame9C[21] = (byte)((E124_Settings)boardSettings).getTypeSoftTouchAnta1();
         uncodedFrame9C[22] = (byte)((E124_Settings)boardSettings).getTypeSoftTouchAnta2();
         uncodedFrame9C[23] = (byte)((E124_Settings)boardSettings).getColpoForce1();
         uncodedFrame9C[24] = (byte)((E124_Settings)boardSettings).getColpoForce2();
         uncodedFrame9C[25] = (byte)((E124_Settings)boardSettings).getRevStrForce1();
         uncodedFrame9C[26] = (byte)((E124_Settings)boardSettings).getRevStrForce2();
         var10 = 27;
      } else {
         uncodedFrame9C[7] = 0;
         uncodedFrame9C[8] = 0;
         uncodedFrame9C[9] = 0;
         uncodedFrame9C[10] = 0;
         uncodedFrame9C[11] = (byte)((E145_Settings)boardSettings).getAddTimeOpen1();
         uncodedFrame9C[12] = (byte)((E145_Settings)boardSettings).getAddTimeOpen2();
         uncodedFrame9C[13] = (byte)((E145_Settings)boardSettings).getAddTimeClose1();
         uncodedFrame9C[14] = (byte)((E145_Settings)boardSettings).getAddTimeClose2();
         var10 = 15;
      }

      for (int i = var10; i < 38; i++) {
         uncodedFrame9C[i] = 0;
      }

      byte[] payload0x9C = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame9C, 2, payload0x9C, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x9C, (byte)0);
      uncodedFrame9C[38] = bCksum;
      return uncodedFrame9C;
   }

   private static byte[] getUncodedPartFrame0x9C_ForFile(String password, FAAC_Settings boardSettings, String boardModel) {
      byte[] uncodedFrame9C = new byte[32];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0x9C(password, boardSettings, boardModel);

      for (int i = 0; i < uncodedFrame9C.length - 1; i++) {
         uncodedFrame9C[i] = uncodedFrameForBoard[i + 7];
      }

      byte lenghtPayload = 31;
      byte[] payload0x9C = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame9C, 0, payload0x9C, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x9C, (byte)0);
      uncodedFrame9C[31] = bCksum;
      return uncodedFrame9C;
   }

   public static byte[] getFrame0x9C(String password, FAAC_Settings boardSettings, String boardModel) {
      byte[] codedFrame9C = new byte[80];
      byte[] uncodedFrame9C = new byte[39];
      codedFrame9C[0] = 2;
      uncodedFrame9C = getUncodedPartFrame0x9C(password, boardSettings, boardModel);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame9C);
      System.arraycopy(res, 0, codedFrame9C, 1, res.length);
      codedFrame9C[79] = 3;
      return codedFrame9C;
   }

   private static byte[] getHeaderBytesForGomFile(String password) {
      byte[] headerBytes = new byte[24];
      String str = "GCOM";
      if (str.length() > 0) {
         byte[] gcomName = str.getBytes();

         for (int i = 0; i < gcomName.length; i++) {
            byte[] arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(gcomName[i]);
            headerBytes[i * 2] = arrayTmp[0];
            headerBytes[i * 2 + 1] = arrayTmp[1];
         }
      }

      byte[] psw = password.getBytes();
      byte[] arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[0]);
      headerBytes[8] = arrayTmp[0];
      headerBytes[9] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[1]);
      headerBytes[10] = arrayTmp[0];
      headerBytes[11] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[2]);
      headerBytes[12] = arrayTmp[0];
      headerBytes[13] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[3]);
      headerBytes[14] = arrayTmp[0];
      headerBytes[15] = arrayTmp[1];
      byte dot = 46;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(dot);
      headerBytes[16] = arrayTmp[0];
      headerBytes[17] = arrayTmp[1];
      byte esse = 115;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(esse);
      headerBytes[18] = arrayTmp[0];
      headerBytes[19] = arrayTmp[1];
      byte gi = 103;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(gi);
      headerBytes[20] = arrayTmp[0];
      headerBytes[21] = arrayTmp[1];
      byte ci = 99;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(ci);
      headerBytes[22] = arrayTmp[0];
      headerBytes[23] = arrayTmp[1];
      return headerBytes;
   }

   private static byte[] getHeaderBytesForPrgFile(String password, FAAC_Monitor boardMonitor) {
      byte[] headerBytes = new byte[24];
      String str = boardMonitor.getBoardType().toUpperCase();
      if (str.length() > 0) {
         byte[] boardName = str.getBytes();

         for (int i = 0; i < boardName.length; i++) {
            byte[] arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(boardName[i]);
            headerBytes[i * 2] = arrayTmp[0];
            headerBytes[i * 2 + 1] = arrayTmp[1];
         }
      }

      byte[] psw = password.getBytes();
      byte[] arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[0]);
      headerBytes[8] = arrayTmp[0];
      headerBytes[9] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[1]);
      headerBytes[10] = arrayTmp[0];
      headerBytes[11] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[2]);
      headerBytes[12] = arrayTmp[0];
      headerBytes[13] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[3]);
      headerBytes[14] = arrayTmp[0];
      headerBytes[15] = arrayTmp[1];
      byte dot = 46;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(dot);
      headerBytes[16] = arrayTmp[0];
      headerBytes[17] = arrayTmp[1];
      byte pi = 112;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(pi);
      headerBytes[18] = arrayTmp[0];
      headerBytes[19] = arrayTmp[1];
      byte erre = 114;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(erre);
      headerBytes[20] = arrayTmp[0];
      headerBytes[21] = arrayTmp[1];
      byte gi = 103;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(gi);
      headerBytes[22] = arrayTmp[0];
      headerBytes[23] = arrayTmp[1];
      return headerBytes;
   }

   public static byte[] getBytesForPrgFile(String password, FAAC_Settings boardSettings, FAAC_Monitor boardMonitor, String boardModel) {
      byte[] byteSequence = new byte[184];
      byte[] header = getHeaderBytesForPrgFile(password, boardMonitor);
      System.arraycopy(header, 0, byteSequence, 0, header.length);
      byte[] prog1Bytes = getUncodedPartFrame0x81_ForFile(password, boardSettings);
      System.arraycopy(prog1Bytes, 0, byteSequence, 24, prog1Bytes.length);
      byte[] prog2Bytes = getUncodedPartFrame0x82_ForFile(password, boardSettings);
      System.arraycopy(prog2Bytes, 0, byteSequence, 56, prog2Bytes.length);
      byte[] prog3Bytes = getUncodedPartFrame0x83_ForFile(password, boardSettings);
      System.arraycopy(prog3Bytes, 0, byteSequence, 88, prog3Bytes.length);
      byte[] prog4Bytes = getUncodedPartFrame0x84_ForFile(password, boardSettings, boardModel);
      System.arraycopy(prog4Bytes, 0, byteSequence, 120, prog4Bytes.length);
      byte[] prog5Bytes = getUncodedPartFrame0x9C_ForFile(password, boardSettings, boardModel);
      System.arraycopy(prog5Bytes, 0, byteSequence, 152, prog5Bytes.length);
      return byteSequence;
   }

   private static byte[] getUncodedPartFrame0x9E_ForFile(String password, FAAC_Remote boardRemote) {
      byte[] uncodedFrame9E = new byte[5];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0x9E(password, boardRemote);

      for (int i = 0; i < uncodedFrame9E.length - 1; i++) {
         uncodedFrame9E[i] = uncodedFrameForBoard[i + 7];
      }

      byte lenghtPayload = 4;
      byte[] payload0x9E = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame9E, 0, payload0x9E, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x9E, (byte)0);
      uncodedFrame9E[4] = bCksum;
      return uncodedFrame9E;
   }

   private static byte[] getUncodedPartFrame0x9E(String password, FAAC_Remote boardRemote) {
      byte[] uncodedFrame9E = new byte[12];
      byte lenghtPayload = 9;
      uncodedFrame9E[0] = lenghtPayload;
      uncodedFrame9E[1] = 0;
      byte dataRequest = -98;
      uncodedFrame9E[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame9E[3] = psw[0];
      uncodedFrame9E[4] = psw[1];
      uncodedFrame9E[5] = psw[2];
      uncodedFrame9E[6] = psw[3];
      FAAC_Remote.ProgRadioFlag progRadioFlag = boardRemote.getProgRadio();
      byte[] progRadioBytes = progRadioFlag.getBytes_MSBfirst();
      uncodedFrame9E[7] = progRadioBytes[0];
      uncodedFrame9E[8] = progRadioBytes[1];
      uncodedFrame9E[9] = progRadioBytes[2];
      uncodedFrame9E[10] = progRadioBytes[3];
      byte[] payload0x9E = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame9E, 2, payload0x9E, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x9E, (byte)0);
      uncodedFrame9E[11] = bCksum;
      return uncodedFrame9E;
   }

   public static byte[] getFrame0x9E(String password, FAAC_Remote boardRemote) {
      byte[] codedFrame9E = new byte[26];
      byte[] uncodedFrame9E = new byte[12];
      codedFrame9E[0] = 2;
      uncodedFrame9E = getUncodedPartFrame0x9E(password, boardRemote);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame9E);
      System.arraycopy(res, 0, codedFrame9E, 1, res.length);
      codedFrame9E[25] = 3;
      return codedFrame9E;
   }

   private static byte[] getUncodedPartFrame0x9D_ForFile(String password, FAAC_Timer boardTimer) {
      byte[] uncodedFrame9D = new byte[29];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0x9D(password, boardTimer);

      for (int i = 0; i < uncodedFrame9D.length - 1; i++) {
         uncodedFrame9D[i] = uncodedFrameForBoard[i + 7];
      }

      byte lenghtPayload = 28;
      byte[] payload0x9D = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame9D, 0, payload0x9D, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x9D, (byte)0);
      uncodedFrame9D[28] = bCksum;
      return uncodedFrame9D;
   }

   private static byte[] getUncodedPartFrame0x9D(String password, FAAC_Timer boardTimer) {
      byte[] uncodedFrame9D = new byte[36];
      byte lenghtPayload = 33;
      uncodedFrame9D[0] = lenghtPayload;
      uncodedFrame9D[1] = 0;
      byte dataRequest = -99;
      uncodedFrame9D[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame9D[3] = psw[0];
      uncodedFrame9D[4] = psw[1];
      uncodedFrame9D[5] = psw[2];
      uncodedFrame9D[6] = psw[3];
      FAAC_Timer.ProgTimerFlag progTimerFlag = boardTimer.getProgTimer();
      byte[] progTimerBytes = progTimerFlag.getBytes_MSBfirst();
      uncodedFrame9D[7] = progTimerBytes[0];
      uncodedFrame9D[8] = progTimerBytes[1];
      uncodedFrame9D[9] = progTimerBytes[2];
      uncodedFrame9D[10] = progTimerBytes[3];

      for (int i = 0; i < 6; i++) {
         uncodedFrame9D[11 + i * 4] = FAAC_Protocol.intToByte_BCDformat(boardTimer.getJollyIntervals()[i].getStartDay());
         uncodedFrame9D[12 + i * 4] = FAAC_Protocol.intToByte_BCDformat(boardTimer.getJollyIntervals()[i].getStartMonth());
         uncodedFrame9D[13 + i * 4] = FAAC_Protocol.intToByte_BCDformat(boardTimer.getJollyIntervals()[i].getEndDay());
         uncodedFrame9D[14 + i * 4] = FAAC_Protocol.intToByte_BCDformat(boardTimer.getJollyIntervals()[i].getEndMonth());
      }

      byte[] payload0x9D = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame9D, 2, payload0x9D, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x9D, (byte)0);
      uncodedFrame9D[35] = bCksum;
      return uncodedFrame9D;
   }

   public static byte[] getFrame0x9D(String password, FAAC_Timer boardTimer) {
      byte[] codedFrame9D = new byte[74];
      byte[] uncodedFrame9D = new byte[36];
      codedFrame9D[0] = 2;
      uncodedFrame9D = getUncodedPartFrame0x9D(password, boardTimer);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame9D);
      System.arraycopy(res, 0, codedFrame9D, 1, res.length);
      codedFrame9D[73] = 3;
      return codedFrame9D;
   }

   public static byte[] getFrame0x85(String password, FAAC_Commands boardCommand) {
      byte[] codedFrame85 = new byte[26];
      byte[] uncodedFrame85 = new byte[12];
      codedFrame85[0] = 2;
      byte lenghtPayload = 9;
      uncodedFrame85[0] = lenghtPayload;
      uncodedFrame85[1] = 0;
      byte dataRequest = -123;
      uncodedFrame85[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame85[3] = psw[0];
      uncodedFrame85[4] = psw[1];
      uncodedFrame85[5] = psw[2];
      uncodedFrame85[6] = psw[3];
      FAAC_Commands.NoMovCmdArg cmdArg = boardCommand.getNoMovCmdArg();
      byte[] cmdBytesArray = cmdArg.getBytes_MSBfirst();
      uncodedFrame85[7] = cmdBytesArray[0];
      uncodedFrame85[8] = cmdBytesArray[1];
      uncodedFrame85[9] = cmdBytesArray[2];
      uncodedFrame85[10] = cmdBytesArray[3];
      byte[] payload0x85 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame85, 2, payload0x85, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x85, (byte)0);
      uncodedFrame85[11] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame85);
      System.arraycopy(res, 0, codedFrame85, 1, res.length);
      codedFrame85[25] = 3;
      return codedFrame85;
   }

   public static byte[] getFrame0x87(String oldPassword, String newPassword) {
      byte[] codedFrame87 = new byte[26];
      byte[] uncodedFrame87 = new byte[12];
      codedFrame87[0] = 2;
      byte lenghtPayload = 9;
      uncodedFrame87[0] = lenghtPayload;
      uncodedFrame87[1] = 0;
      byte dataRequest = -121;
      uncodedFrame87[2] = dataRequest;
      byte[] psw = oldPassword.getBytes();
      uncodedFrame87[3] = psw[0];
      uncodedFrame87[4] = psw[1];
      uncodedFrame87[5] = psw[2];
      uncodedFrame87[6] = psw[3];
      byte[] newPsw = newPassword.getBytes();
      uncodedFrame87[7] = newPsw[0];
      uncodedFrame87[8] = newPsw[1];
      uncodedFrame87[9] = newPsw[2];
      uncodedFrame87[10] = newPsw[3];
      byte[] payload0x87 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame87, 2, payload0x87, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x87, (byte)0);
      uncodedFrame87[11] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame87);
      System.arraycopy(res, 0, codedFrame87, 1, res.length);
      codedFrame87[25] = 3;
      return codedFrame87;
   }

   public static byte[] getFrame0x8A(String password, FAAC_Commands boardCommand) {
      byte[] codedFrame8A = new byte[26];
      byte[] uncodedFrame8A = new byte[12];
      codedFrame8A[0] = 2;
      byte lenghtPayload = 9;
      uncodedFrame8A[0] = lenghtPayload;
      uncodedFrame8A[1] = 0;
      byte dataRequest = -118;
      uncodedFrame8A[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame8A[3] = psw[0];
      uncodedFrame8A[4] = psw[1];
      uncodedFrame8A[5] = psw[2];
      uncodedFrame8A[6] = psw[3];
      FAAC_Commands.MovCmdArg cmdArg = boardCommand.getMovCmdArg();
      byte[] cmdBytesArray = cmdArg.getBytes_MSBfirst();
      uncodedFrame8A[7] = cmdBytesArray[0];
      uncodedFrame8A[8] = cmdBytesArray[1];
      uncodedFrame8A[9] = cmdBytesArray[2];
      uncodedFrame8A[10] = cmdBytesArray[3];
      byte[] payload0x8A = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame8A, 2, payload0x8A, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x8A, (byte)0);
      uncodedFrame8A[11] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame8A);
      System.arraycopy(res, 0, codedFrame8A, 1, res.length);
      codedFrame8A[25] = 3;
      return codedFrame8A;
   }

   public static byte[] getFrame0x8C(String password, FAAC_NETCOM netcom) {
      byte[] codedFrame8C = new byte[92];
      byte[] uncodedFrame8C = new byte[45];
      codedFrame8C[0] = 2;
      byte lenghtPayload = 42;
      uncodedFrame8C[0] = lenghtPayload;
      uncodedFrame8C[1] = 0;
      byte dataRequest = -116;
      uncodedFrame8C[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame8C[3] = psw[0];
      uncodedFrame8C[4] = psw[1];
      uncodedFrame8C[5] = psw[2];
      uncodedFrame8C[6] = psw[3];
      uncodedFrame8C[7] = (byte)netcom.getIP4();
      uncodedFrame8C[8] = (byte)netcom.getIP3();
      uncodedFrame8C[9] = (byte)netcom.getIP2();
      uncodedFrame8C[10] = (byte)netcom.getIP1();
      uncodedFrame8C[11] = (byte)netcom.getMASK4();
      uncodedFrame8C[12] = (byte)netcom.getMASK3();
      uncodedFrame8C[13] = (byte)netcom.getMASK2();
      uncodedFrame8C[14] = (byte)netcom.getMASK1();
      uncodedFrame8C[15] = (byte)netcom.getGW4();
      uncodedFrame8C[16] = (byte)netcom.getGW3();
      uncodedFrame8C[17] = (byte)netcom.getGW2();
      uncodedFrame8C[18] = (byte)netcom.getGW1();
      uncodedFrame8C[19] = (byte)netcom.getPrimDNS4();
      uncodedFrame8C[20] = (byte)netcom.getPrimDNS3();
      uncodedFrame8C[21] = (byte)netcom.getPrimDNS2();
      uncodedFrame8C[22] = (byte)netcom.getPrimDNS1();
      uncodedFrame8C[23] = (byte)netcom.getSecDNS4();
      uncodedFrame8C[24] = (byte)netcom.getSecDNS3();
      uncodedFrame8C[25] = (byte)netcom.getSecDNS2();
      uncodedFrame8C[26] = (byte)netcom.getSecDNS1();
      String hostname = netcom.getHostName();
      int maxLength = 16;
      int length = hostname.length();
      byte[] tempArray = new byte[length];
      char[] chTmp = new char[length];
      hostname.getChars(0, length, chTmp, 0);

      for (int i = 0; i < length; i++) {
         tempArray[i] = (byte)chTmp[i];
         uncodedFrame8C[27 + i] = tempArray[i];
      }

      uncodedFrame8C[length] = 0;

      for (int i = 0; i < maxLength - length - 1; i++) {
         uncodedFrame8C[27 + length + i] = 0;
      }

      if (netcom.isIsDHCPset()) {
         uncodedFrame8C[43] = 1;
      } else {
         uncodedFrame8C[43] = 0;
      }

      byte[] payload0x8C = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame8C, 2, payload0x8C, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x8C, (byte)0);
      uncodedFrame8C[44] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame8C);
      System.arraycopy(res, 0, codedFrame8C, 1, res.length);
      codedFrame8C[91] = 3;
      return codedFrame8C;
   }

   public static byte[] getUncodedFrame0x8D(String password, FAAC_GCOM gcom) {
      byte[] uncodedFrame8D = new byte[46];
      byte lenghtPayload = 43;
      uncodedFrame8D[0] = lenghtPayload;
      byte[] payload0x8D = new byte[lenghtPayload];
      uncodedFrame8D[1] = 0;
      byte dataRequest = -115;
      uncodedFrame8D[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame8D[3] = psw[0];
      uncodedFrame8D[4] = psw[1];
      uncodedFrame8D[5] = psw[2];
      uncodedFrame8D[6] = psw[3];
      String hostname = gcom.getDeviceName();
      int maxLength = 24;
      int length = hostname.length();
      byte[] tempArray = new byte[length];
      char[] chTmp = new char[length];
      hostname.getChars(0, length, chTmp, 0);

      for (int i = 0; i < length; i++) {
         tempArray[i] = (byte)chTmp[i];
         uncodedFrame8D[7 + i] = tempArray[i];
      }

      for (int i = 0; i < maxLength - length - 1; i++) {
         uncodedFrame8D[7 + length + i] = 0;
      }

      byte[] flagByteArray = gcom.getSmsEnableFlag().getBytes_MSBfirst();
      uncodedFrame8D[31] = flagByteArray[0];
      uncodedFrame8D[32] = flagByteArray[1];
      uncodedFrame8D[33] = flagByteArray[2];
      uncodedFrame8D[34] = flagByteArray[3];
      uncodedFrame8D[35] = (byte)gcom.getResendPeriod_hours();
      uncodedFrame8D[36] = (byte)gcom.getResendTimes();
      System.arraycopy(uncodedFrame8D, 2, payload0x8D, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x8D, (byte)0);
      uncodedFrame8D[45] = bCksum;
      return uncodedFrame8D;
   }

   public static byte[] getFrame0x8D(String password, FAAC_GCOM gcom) {
      byte[] codedFrame8D = new byte[94];
      codedFrame8D[0] = 2;
      byte[] uncodedFrame8D = getUncodedFrame0x8D(password, gcom);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame8D);
      System.arraycopy(res, 0, codedFrame8D, 1, res.length);
      codedFrame8D[93] = 3;
      return codedFrame8D;
   }

   public static byte[] getUncodedFrame0x8E(String password, FAAC_GCOM gcom, int pbindex) {
      byte[] uncodedFrame8E = new byte[82];
      byte lenghtPayload = 79;
      uncodedFrame8E[0] = lenghtPayload;
      uncodedFrame8E[1] = 0;
      byte dataRequest = -114;
      uncodedFrame8E[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame8E[3] = psw[0];
      uncodedFrame8E[4] = psw[1];
      uncodedFrame8E[5] = psw[2];
      uncodedFrame8E[6] = psw[3];
      uncodedFrame8E[7] = (byte)(pbindex >> 8);
      uncodedFrame8E[8] = (byte)pbindex;
      PBentry[] entryGrp = gcom.getPhoneBook().GetEntryGroup(pbindex);
      int frameindex = 9;

      for (int i = 0; i < 4; i++) {
         byte[] pbByteArray = entryGrp[i].getByteArrayForFrame();
         int len = pbByteArray.length;

         for (int j = 0; j < len; j++) {
            uncodedFrame8E[frameindex] = pbByteArray[j];
            frameindex++;
         }
      }

      byte[] payload0x8E = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame8E, 2, payload0x8E, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x8E, (byte)0);
      uncodedFrame8E[81] = bCksum;
      return uncodedFrame8E;
   }

   public static byte[] getUncodedFrameForFile(byte[] uncodedFrameforBoard) {
      int lengthFrame = uncodedFrameforBoard.length - 7;
      byte[] uncodedFrameForFile = new byte[lengthFrame];
      System.arraycopy(uncodedFrameforBoard, 7, uncodedFrameForFile, 0, lengthFrame - 1);
      byte bCksum = FAAC_Protocol.CheckSum(uncodedFrameForFile, (byte)0);
      uncodedFrameForFile[lengthFrame - 1] = bCksum;
      return uncodedFrameForFile;
   }

   public static byte[] getFrame0x8E(String password, FAAC_GCOM gcom, int pbindex) {
      byte[] codedFrame8E = new byte[166];
      codedFrame8E[0] = 2;
      byte[] uncodedFrame8E = getUncodedFrame0x8E(password, gcom, pbindex);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame8E);
      System.arraycopy(res, 0, codedFrame8E, 1, res.length);
      codedFrame8E[165] = 3;
      return codedFrame8E;
   }

   public static byte[] getFrame0x8F(String password, int minutes, int hour, int day, int dayWeek, int month, int year) {
      byte[] codedFrame8F = new byte[30];
      byte[] uncodedFrame8F = new byte[14];
      codedFrame8F[0] = 2;
      byte lenghtPayload = 11;
      uncodedFrame8F[0] = lenghtPayload;
      uncodedFrame8F[1] = 0;
      byte dataRequest = -113;
      uncodedFrame8F[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame8F[3] = psw[0];
      uncodedFrame8F[4] = psw[1];
      uncodedFrame8F[5] = psw[2];
      uncodedFrame8F[6] = psw[3];
      uncodedFrame8F[7] = FAAC_Protocol.intToByte_BCDformat(minutes);
      uncodedFrame8F[8] = FAAC_Protocol.intToByte_BCDformat(hour);
      uncodedFrame8F[9] = FAAC_Protocol.intToByte_BCDformat(day);
      uncodedFrame8F[10] = FAAC_Protocol.intToByte_BCDformat(dayWeek);
      uncodedFrame8F[11] = FAAC_Protocol.intToByte_BCDformat(month);
      uncodedFrame8F[12] = FAAC_Protocol.intToByte_BCDformat(year);
      byte[] payload0x8F = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame8F, 2, payload0x8F, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x8F, (byte)0);
      uncodedFrame8F[13] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame8F);
      System.arraycopy(res, 0, codedFrame8F, 1, res.length);
      codedFrame8F[29] = 3;
      return codedFrame8F;
   }

   private static byte[] getUncodedPartFrame0x90_ForFile(String password, int timerDayIndex, FAAC_Timer boardTimer) {
      byte[] uncodedFrame90 = new byte[32];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0x90(password, timerDayIndex, boardTimer);

      for (int i = 0; i < uncodedFrame90.length - 1; i++) {
         uncodedFrame90[i] = uncodedFrameForBoard[i + 8];
      }

      byte lenghtPayload = 31;
      byte[] payload0x90 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame90, 0, payload0x90, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x90, (byte)0);
      uncodedFrame90[31] = bCksum;
      return uncodedFrame90;
   }

   private static byte[] getUncodedPartFrame0x90(String password, int timerDayIndex, FAAC_Timer boardTimer) {
      byte[] uncodedFrame90 = new byte[40];
      byte lenghtPayload = 37;
      uncodedFrame90[0] = lenghtPayload;
      uncodedFrame90[1] = 0;
      byte dataRequest = -112;
      uncodedFrame90[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame90[3] = psw[0];
      uncodedFrame90[4] = psw[1];
      uncodedFrame90[5] = psw[2];
      uncodedFrame90[6] = psw[3];
      uncodedFrame90[7] = (byte)timerDayIndex;
      FAAC_Timer.TimerEvent[] timerDay = boardTimer.getTimer(timerDayIndex);
      int iRange = 0;
      uncodedFrame90[8] = (byte)timerDay[iRange].getValueFunction();
      uncodedFrame90[9] = FAAC_Protocol.intToByte_BCDformat(timerDay[iRange].getBeginHourExact());
      uncodedFrame90[10] = FAAC_Protocol.intToByte_BCDformat(timerDay[iRange].getBeginMinuteExact());
      uncodedFrame90[11] = FAAC_Protocol.intToByte_BCDformat(timerDay[iRange].getEndHourExact());
      uncodedFrame90[12] = FAAC_Protocol.intToByte_BCDformat(timerDay[iRange].getEndMinuteExact());
      int var11 = 1;
      uncodedFrame90[13] = (byte)timerDay[var11].getValueFunction();
      uncodedFrame90[14] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginHourExact());
      uncodedFrame90[15] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginMinuteExact());
      uncodedFrame90[16] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndHourExact());
      uncodedFrame90[17] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndMinuteExact());
      var11 = 2;
      uncodedFrame90[18] = (byte)timerDay[var11].getValueFunction();
      uncodedFrame90[19] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginHourExact());
      uncodedFrame90[20] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginMinuteExact());
      uncodedFrame90[21] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndHourExact());
      uncodedFrame90[22] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndMinuteExact());
      var11 = 3;
      uncodedFrame90[23] = (byte)timerDay[var11].getValueFunction();
      uncodedFrame90[24] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginHourExact());
      uncodedFrame90[25] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginMinuteExact());
      uncodedFrame90[26] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndHourExact());
      uncodedFrame90[27] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndMinuteExact());
      var11 = 4;
      uncodedFrame90[28] = (byte)timerDay[var11].getValueFunction();
      uncodedFrame90[29] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginHourExact());
      uncodedFrame90[30] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginMinuteExact());
      uncodedFrame90[31] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndHourExact());
      uncodedFrame90[32] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndMinuteExact());
      var11 = 5;
      uncodedFrame90[33] = (byte)timerDay[var11].getValueFunction();
      uncodedFrame90[34] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginHourExact());
      uncodedFrame90[35] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getBeginMinuteExact());
      uncodedFrame90[36] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndHourExact());
      uncodedFrame90[37] = FAAC_Protocol.intToByte_BCDformat(timerDay[var11].getEndMinuteExact());
      uncodedFrame90[38] = 0;
      byte[] payload0x90 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame90, 2, payload0x90, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x90, (byte)0);
      uncodedFrame90[39] = bCksum;
      return uncodedFrame90;
   }

   private static byte[] getHeaderBytesForTmrFile(String password, FAAC_Monitor boardMonitor) {
      byte[] headerBytes = new byte[24];
      String str = boardMonitor.getBoardType().toUpperCase();
      if (str.length() > 0) {
         byte[] boardName = str.getBytes();

         for (int i = 0; i < boardName.length; i++) {
            byte[] arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(boardName[i]);
            headerBytes[i * 2] = arrayTmp[0];
            headerBytes[i * 2 + 1] = arrayTmp[1];
         }
      }

      byte[] psw = password.getBytes();
      byte[] arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[0]);
      headerBytes[8] = arrayTmp[0];
      headerBytes[9] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[1]);
      headerBytes[10] = arrayTmp[0];
      headerBytes[11] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[2]);
      headerBytes[12] = arrayTmp[0];
      headerBytes[13] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[3]);
      headerBytes[14] = arrayTmp[0];
      headerBytes[15] = arrayTmp[1];
      byte dot = 46;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(dot);
      headerBytes[16] = arrayTmp[0];
      headerBytes[17] = arrayTmp[1];
      byte ti = 116;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(ti);
      headerBytes[18] = arrayTmp[0];
      headerBytes[19] = arrayTmp[1];
      byte emme = 109;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(emme);
      headerBytes[20] = arrayTmp[0];
      headerBytes[21] = arrayTmp[1];
      byte erre = 114;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(erre);
      headerBytes[22] = arrayTmp[0];
      headerBytes[23] = arrayTmp[1];
      return headerBytes;
   }

   public static byte[] getBytesForTmrFile(String password, FAAC_Timer boardTimer, FAAC_Monitor boardMonitor) {
      byte[] byteSequence = new byte[FILE_TMR_LENGTH];
      byte[] header = getHeaderBytesForTmrFile(password, boardMonitor);
      System.arraycopy(header, 0, byteSequence, 0, header.length);
      byte[] timer0Bytes = getUncodedPartFrame0x90_ForFile(password, 0, boardTimer);
      System.arraycopy(timer0Bytes, 0, byteSequence, 24, timer0Bytes.length);
      byte[] timer1Bytes = getUncodedPartFrame0x90_ForFile(password, 1, boardTimer);
      System.arraycopy(timer1Bytes, 0, byteSequence, 56, timer1Bytes.length);
      byte[] timer2Bytes = getUncodedPartFrame0x90_ForFile(password, 2, boardTimer);
      System.arraycopy(timer2Bytes, 0, byteSequence, 88, timer2Bytes.length);
      byte[] timer3Bytes = getUncodedPartFrame0x90_ForFile(password, 3, boardTimer);
      System.arraycopy(timer3Bytes, 0, byteSequence, 120, timer3Bytes.length);
      byte[] timer4Bytes = getUncodedPartFrame0x90_ForFile(password, 4, boardTimer);
      System.arraycopy(timer4Bytes, 0, byteSequence, 152, timer4Bytes.length);
      byte[] timer5Bytes = getUncodedPartFrame0x90_ForFile(password, 5, boardTimer);
      System.arraycopy(timer5Bytes, 0, byteSequence, 184, timer5Bytes.length);
      byte[] timer6Bytes = getUncodedPartFrame0x90_ForFile(password, 6, boardTimer);
      System.arraycopy(timer6Bytes, 0, byteSequence, 216, timer6Bytes.length);
      byte[] timer7Bytes = getUncodedPartFrame0x90_ForFile(password, 7, boardTimer);
      System.arraycopy(timer7Bytes, 0, byteSequence, 248, timer7Bytes.length);
      byte[] progTimer = getUncodedPartFrame0x9D_ForFile(password, boardTimer);
      System.arraycopy(progTimer, 0, byteSequence, 280, 28);
      byteSequence[308] = 0;
      byteSequence[309] = 0;
      byteSequence[310] = 0;
      byteSequence[311] = progTimer[28];
      return byteSequence;
   }

   public static byte[] getFrame0x90(String password, int timerDayIndex, FAAC_Timer boardTimer) {
      byte[] codedFrame90 = new byte[82];
      byte[] uncodedFrame90 = new byte[40];
      codedFrame90[0] = 2;
      uncodedFrame90 = getUncodedPartFrame0x90(password, timerDayIndex, boardTimer);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame90);
      System.arraycopy(res, 0, codedFrame90, 1, res.length);
      codedFrame90[81] = 3;
      return codedFrame90;
   }

   public static byte[] getUncodedFrame0x93(String password, FAAC_GCOM gcom) {
      byte[] uncodedFrame93 = new byte[24];
      byte lenghtPayload = 21;
      uncodedFrame93[0] = lenghtPayload;
      uncodedFrame93[1] = 0;
      byte dataRequest = -109;
      uncodedFrame93[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame93[3] = psw[0];
      uncodedFrame93[4] = psw[1];
      uncodedFrame93[5] = psw[2];
      uncodedFrame93[6] = psw[3];
      String strNumGcom = gcom.getNumGcom();
      byte[] bNum = new byte[16];
      char[] chTmp = new char[16];
      if (strNumGcom.compareTo("-") == 0) {
         for (int i = 0; i < 16; i++) {
            uncodedFrame93[7 + i] = 0;
         }
      } else {
         String strTmp = strNumGcom.substring(1);
         int length = strTmp.length();
         strTmp.getChars(0, length, chTmp, 0);

         for (int i = 0; i < length; i++) {
            bNum[i] = (byte)Integer.parseInt(new String(new char[]{chTmp[i]}), 16);
            uncodedFrame93[7 + i] = bNum[i];
         }

         if (length < 16) {
            for (int i = 0; i < 16 - length; i++) {
               uncodedFrame93[7 + i + length] = 10;
            }
         }
      }

      byte[] payload0x93 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame93, 2, payload0x93, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x93, (byte)0);
      uncodedFrame93[23] = bCksum;
      return uncodedFrame93;
   }

   public static byte[] getFrame0x93(String password, FAAC_GCOM gcom) {
      byte[] codedFrame93 = new byte[50];
      byte[] uncodedFrame93 = new byte[24];
      codedFrame93[0] = 2;
      byte lenghtPayload = 21;
      uncodedFrame93[0] = lenghtPayload;
      uncodedFrame93[1] = 0;
      byte dataRequest = -109;
      uncodedFrame93[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrame93[3] = psw[0];
      uncodedFrame93[4] = psw[1];
      uncodedFrame93[5] = psw[2];
      uncodedFrame93[6] = psw[3];
      String strNumGcom = gcom.getNumGcom();
      byte[] bNum = new byte[16];
      char[] chTmp = new char[16];
      if (strNumGcom.compareTo("-") == 0) {
         for (int i = 0; i < 16; i++) {
            uncodedFrame93[7 + i] = 0;
         }
      } else {
         String strTmp = strNumGcom.substring(1);
         int length = strTmp.length();
         strTmp.getChars(0, length, chTmp, 0);

         for (int i = 0; i < length; i++) {
            bNum[i] = (byte)Integer.parseInt(new String(new char[]{chTmp[i]}), 16);
            uncodedFrame93[7 + i] = bNum[i];
         }

         if (length < 16) {
            for (int i = 0; i < 16 - length; i++) {
               uncodedFrame93[7 + i + length] = 10;
            }
         }
      }

      byte[] payload0x93 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame93, 2, payload0x93, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x93, (byte)0);
      uncodedFrame93[23] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame93);
      System.arraycopy(res, 0, codedFrame93, 1, res.length);
      codedFrame93[49] = 3;
      return codedFrame93;
   }

   private static byte[] getHeaderBytesForRadFile(String password, FAAC_Monitor boardMonitor) {
      byte[] headerBytes = new byte[24];
      String str = boardMonitor.getBoardType().toUpperCase();
      if (str.length() > 0) {
         byte[] boardName = str.getBytes();

         for (int i = 0; i < boardName.length; i++) {
            byte[] arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(boardName[i]);
            headerBytes[i * 2] = arrayTmp[0];
            headerBytes[i * 2 + 1] = arrayTmp[1];
         }
      }

      byte[] psw = password.getBytes();
      byte[] arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[0]);
      headerBytes[8] = arrayTmp[0];
      headerBytes[9] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[1]);
      headerBytes[10] = arrayTmp[0];
      headerBytes[11] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[2]);
      headerBytes[12] = arrayTmp[0];
      headerBytes[13] = arrayTmp[1];
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(psw[3]);
      headerBytes[14] = arrayTmp[0];
      headerBytes[15] = arrayTmp[1];
      byte dot = 46;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(dot);
      headerBytes[16] = arrayTmp[0];
      headerBytes[17] = arrayTmp[1];
      byte erre = 114;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(erre);
      headerBytes[18] = arrayTmp[0];
      headerBytes[19] = arrayTmp[1];
      byte a = 97;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(a);
      headerBytes[20] = arrayTmp[0];
      headerBytes[21] = arrayTmp[1];
      byte di = 100;
      arrayTmp = FAAC_Protocol.byteToFileSplitByteArray(di);
      headerBytes[22] = arrayTmp[0];
      headerBytes[23] = arrayTmp[1];
      return headerBytes;
   }

   private static byte[] getUncodedPartFrame0xA0_ForFile(String password, int radioCodeIndex, FAAC_Remote boardRemote) {
      byte[] uncodedFrameA0 = new byte[8];
      byte[] uncodedFrameForBoard = getUncodedPartFrame0xA0reverse_endianity(password, radioCodeIndex, boardRemote);

      for (int i = 0; i < uncodedFrameA0.length; i++) {
         uncodedFrameA0[i] = uncodedFrameForBoard[i + 9];
      }

      return uncodedFrameA0;
   }

   private static byte[] getUncodedPartFrame0xA0reverse_endianity(String password, int radioCodeIndex, FAAC_Remote boardRemote) {
      byte[] uncodedFrameA0 = new byte[18];
      byte lenghtPayload = 15;
      uncodedFrameA0[0] = lenghtPayload;
      uncodedFrameA0[1] = 0;
      byte dataRequest = -96;
      uncodedFrameA0[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrameA0[3] = psw[0];
      uncodedFrameA0[4] = psw[1];
      uncodedFrameA0[5] = psw[2];
      uncodedFrameA0[6] = psw[3];
      byte[] radioCodeBytes = FAAC_Protocol.intTo4bytes_MSBfirst(radioCodeIndex);
      uncodedFrameA0[7] = radioCodeBytes[2];
      uncodedFrameA0[8] = radioCodeBytes[3];
      FAAC_Remote.RadioCode currentRadioCode = boardRemote.getRadioCodeArray()[radioCodeIndex];
      byte[] code1Bytes = currentRadioCode.getCode1().getBytes_MSBfirst();
      uncodedFrameA0[12] = code1Bytes[0];
      uncodedFrameA0[11] = code1Bytes[1];
      uncodedFrameA0[10] = code1Bytes[2];
      uncodedFrameA0[9] = code1Bytes[3];
      byte[] code2Bytes = currentRadioCode.getCode2().getBytes_MSBfirst();
      uncodedFrameA0[16] = code2Bytes[0];
      uncodedFrameA0[15] = code2Bytes[1];
      uncodedFrameA0[14] = code2Bytes[2];
      uncodedFrameA0[13] = code2Bytes[3];
      byte[] payload0xA0 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrameA0, 2, payload0xA0, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0xA0, (byte)0);
      uncodedFrameA0[17] = bCksum;
      return uncodedFrameA0;
   }

   private static byte[] getUncodedPartFrame0xA0(String password, int radioCodeIndex, FAAC_Remote boardRemote) {
      byte[] uncodedFrameA0 = new byte[18];
      byte lenghtPayload = 15;
      uncodedFrameA0[0] = lenghtPayload;
      uncodedFrameA0[1] = 0;
      byte dataRequest = -96;
      uncodedFrameA0[2] = dataRequest;
      byte[] psw = password.getBytes();
      uncodedFrameA0[3] = psw[0];
      uncodedFrameA0[4] = psw[1];
      uncodedFrameA0[5] = psw[2];
      uncodedFrameA0[6] = psw[3];
      byte[] radioCodeBytes = FAAC_Protocol.intTo4bytes_MSBfirst(radioCodeIndex);
      uncodedFrameA0[7] = radioCodeBytes[2];
      uncodedFrameA0[8] = radioCodeBytes[3];
      FAAC_Remote.RadioCode currentRadioCode = boardRemote.getRadioCodeArray()[radioCodeIndex];
      byte[] code1Bytes = currentRadioCode.getCode1().getBytes_MSBfirst();
      uncodedFrameA0[9] = code1Bytes[0];
      uncodedFrameA0[10] = code1Bytes[1];
      uncodedFrameA0[11] = code1Bytes[2];
      uncodedFrameA0[12] = code1Bytes[3];
      byte[] code2Bytes = currentRadioCode.getCode2().getBytes_MSBfirst();
      uncodedFrameA0[13] = code2Bytes[0];
      uncodedFrameA0[14] = code2Bytes[1];
      uncodedFrameA0[15] = code2Bytes[2];
      uncodedFrameA0[16] = code2Bytes[3];
      byte[] payload0xA0 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrameA0, 2, payload0xA0, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0xA0, (byte)0);
      uncodedFrameA0[17] = bCksum;
      return uncodedFrameA0;
   }

   public static byte[] getFrame0xA0(String password, int radioCodeIndex, FAAC_Remote boardRemote) {
      byte[] codedFrameA0 = new byte[38];
      codedFrameA0[0] = 2;
      byte[] uncodedFrameA0 = getUncodedPartFrame0xA0(password, radioCodeIndex, boardRemote);
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrameA0);
      System.arraycopy(res, 0, codedFrameA0, 1, res.length);
      codedFrameA0[37] = 3;
      return codedFrameA0;
   }

   public static byte[] getBytesForRadFile(String password, FAAC_Remote boardRemote, FAAC_Monitor boardMonitor) {
      int iFileLenght = boardRemote.getRadFileLenght_inBytes();
      byte[] byteSequence = new byte[iFileLenght];
      byte[] header = getHeaderBytesForRadFile(password, boardMonitor);
      System.arraycopy(header, 0, byteSequence, 0, header.length);
      int radioCodesNb;
      if (boardMonitor.getBoardType().compareToIgnoreCase("E145") == 0) {
         radioCodesNb = E145_Remote.getNbRadioCode();
      } else {
         radioCodesNb = E124_Remote.getNbRadioCode();
      }

      for (int i = 0; i < radioCodesNb; i++) {
         byte[] remBytes = getUncodedPartFrame0xA0_ForFile(password, i, boardRemote);
         System.arraycopy(remBytes, 0, byteSequence, 24 + 8 * i, remBytes.length);
      }

      byte[] progRadioBytes = getUncodedPartFrame0x9E_ForFile(password, boardRemote);
      System.arraycopy(progRadioBytes, 0, byteSequence, 24 + 8 * radioCodesNb, progRadioBytes.length);
      return byteSequence;
   }

   public static byte parseFrameNack0xFF(byte[] frame2parse) throws Exception {
      byte[] codedCommandField = new byte[]{frame2parse[7], frame2parse[8]};

      try {
         return FAAC_Protocol.intelHexDecoder(codedCommandField);
      } catch (Exception var4) {
         Debug.printlnStatic(var4.toString());
         throw var4;
      }
   }

   public static FAAC_frames.AckReplyObject parseFrameAck0xF0(byte[] frame2parse) throws Exception {
      byte replyCommand = 0;
      FAAC_frames.AckReplyObject reply = new FAAC_frames.AckReplyObject();
      byte[] codedCommandField = new byte[]{frame2parse[7], frame2parse[8]};

      byte decodedCommandField;
      try {
         decodedCommandField = FAAC_Protocol.intelHexDecoder(codedCommandField);
      } catch (Exception var10) {
         Debug.printlnStatic(var10.toString());
         throw var10;
      }

      reply.setReplyCommand(decodedCommandField);
      if (decodedCommandField == -112) {
         byte[] codedTimerDayIndex = new byte[]{frame2parse[9], frame2parse[10]};

         byte decodedTimerDayIndex;
         try {
            decodedTimerDayIndex = FAAC_Protocol.intelHexDecoder(codedTimerDayIndex);
         } catch (Exception var9) {
            Debug.printlnStatic(var9.toString());
            throw var9;
         }

         reply.setIndex(decodedTimerDayIndex);
      } else if (decodedCommandField == -96) {
         byte[] codedRadioIndex = new byte[]{frame2parse[9], frame2parse[10], frame2parse[11], frame2parse[12]};

         byte[] decodedRadioIndex;
         try {
            decodedRadioIndex = FAAC_Protocol.intelHexDecoderDataFlow(codedRadioIndex);
         } catch (Exception var8) {
            Debug.printlnStatic(var8.toString());
            throw var8;
         }

         reply.setIndex(FAAC_Protocol.byteArrayToInt(decodedRadioIndex));
      }

      return reply;
   }

   public static byte[] getBytesForGcomFile(String password, FAAC_GCOM gcom) {
      int index = 0;
      int length = 0;
      int phonebookFrameNumber = 5;
      byte[] header = getHeaderBytesForGomFile(password);
      length += header.length;
      byte[] gcomParamBytes = getUncodedFrameForFile(getUncodedFrame0x8D(password, gcom));
      length += gcomParamBytes.length;
      byte[] gcomSimBytes = getUncodedFrameForFile(getUncodedFrame0x93(password, gcom));
      length += gcomSimBytes.length;
      byte[] gcomPhoneBookBytes = getUncodedFrameForFile(getUncodedFrame0x8E(password, gcom, 0));
      length += gcomPhoneBookBytes.length * phonebookFrameNumber;
      byte[] byteSequence = new byte[length];
      System.arraycopy(header, 0, byteSequence, index, header.length);
      index += header.length;
      System.arraycopy(gcomParamBytes, 0, byteSequence, index, gcomParamBytes.length);
      index += gcomParamBytes.length;
      System.arraycopy(gcomSimBytes, 0, byteSequence, index, gcomSimBytes.length);
      index += gcomSimBytes.length;
      int pbIndex = 0;

      for (int i = 0; i < phonebookFrameNumber; i++) {
         gcomPhoneBookBytes = getUncodedFrameForFile(getUncodedFrame0x8E(password, gcom, pbIndex));
         System.arraycopy(gcomPhoneBookBytes, 0, byteSequence, index, gcomPhoneBookBytes.length);
         index += gcomPhoneBookBytes.length;
         pbIndex++;
      }

      return byteSequence;
   }

   public static boolean isBoardVersionSupported(FAAC_Monitor boardMonitor, String boardModel) {
      char sw1 = boardMonitor.getBoardSwVersion_SW1();
      char sw2 = boardMonitor.getBoardSwVersion_SW2();
      if (boardModel.compareToIgnoreCase("E124") == 0) {
         return sw1 >= '3' && sw2 >= '0';
      } else {
         return boardModel.compareToIgnoreCase("E145") != 0 ? false : sw1 >= '1' && sw2 >= '0' || sw1 >= 't' && sw2 >= '7';
      }
   }

   public static boolean parseGcomFile(byte[] readBytes, FAAC_GCOM gcom, String filePassword) {
      byte[] psw = new byte[4];

      for (int i = 0; i < 4; i++) {
         psw[i] = FAAC_Protocol.FileSplitByteArrayToByte(new byte[]{readBytes[8 + i * 2], readBytes[9 + i * 2]});
      }

      String readPsw = new String(psw);
      if (filePassword.compareTo("") != 0 && filePassword.compareTo(readPsw) != 0) {
         return false;
      } else {
         int index = 0;
         int phonebookFrameNumber = 5;
         boolean bRes = true;
         byte[] header = new byte[24];
         System.arraycopy(readBytes, index, header, 0, header.length);
         index += header.length;
         byte[] gcomParamBytes = new byte[39];
         System.arraycopy(readBytes, index, gcomParamBytes, 0, gcomParamBytes.length - 1);
         index += gcomParamBytes.length;
         parsePayloadFrame0x8D(gcomParamBytes, gcom);
         byte[] gcomSimBytes = new byte[17];
         System.arraycopy(readBytes, index, gcomSimBytes, 0, gcomSimBytes.length - 1);
         index += gcomSimBytes.length;
         parsePayloadFrame0x93(gcomSimBytes, gcom);
         byte[] gcomPhoneBookBytes = new byte[75];

         for (int i = 0; i < phonebookFrameNumber; i++) {
            System.arraycopy(readBytes, index, gcomPhoneBookBytes, 0, gcomPhoneBookBytes.length - 1);
            index += gcomPhoneBookBytes.length;
            parsePayloadFrame0x8E(gcomPhoneBookBytes, gcom);
         }

         return true;
      }
   }

   public static void parsePayloadFrame0x8D(byte[] gcomParamBytes, FAAC_GCOM gcom) {
      int index = 0;
      byte[] decodedDeviceName = new byte[24];
      System.arraycopy(gcomParamBytes, index, decodedDeviceName, 0, decodedDeviceName.length);
      index += decodedDeviceName.length;
      int i = 0;

      while (i < decodedDeviceName.length && decodedDeviceName[i] != 0) {
         i++;
      }

      int length = i;
      byte[] byteName = new byte[i];

      for (int var14 = 0; var14 < length; var14++) {
         byteName[var14] = decodedDeviceName[var14];
      }

      gcom.setDeviceName(new String(byteName));
      byte[] decodedFlags = new byte[4];
      System.arraycopy(gcomParamBytes, index, decodedFlags, 0, decodedFlags.length);
      index += decodedFlags.length;
      gcom.getSmsEnableFlag().setBytes_MSBfirst(decodedFlags);
      byte[] decodedResendPeriod = new byte[1];
      System.arraycopy(gcomParamBytes, index, decodedResendPeriod, 0, decodedResendPeriod.length);
      index += decodedResendPeriod.length;
      gcom.setResendPeriod_hours(decodedResendPeriod[0]);
      byte[] decodedResendTimes = new byte[1];
      System.arraycopy(gcomParamBytes, index, decodedResendTimes, 0, decodedResendTimes.length);
      index += decodedResendTimes.length;
      gcom.setResendTimes(decodedResendTimes[0]);
   }

   public static void parsePayloadFrame0x93(byte[] gcomParamBytes, FAAC_GCOM gcom) {
      int index = 0;
      byte[] decodedNum1 = new byte[16];
      System.arraycopy(gcomParamBytes, index, decodedNum1, 0, decodedNum1.length);
      index += decodedNum1.length;
      boolean bAllZeros = true;

      for (int i = 0; i < 16 && bAllZeros; i++) {
         if (decodedNum1[i] != 0) {
            bAllZeros = false;
         }
      }

      if (bAllZeros) {
         gcom.setNumGcom("-");
      } else {
         int ifirstAbyte = 0;
         char[] decodedNum1Chars = new char[16];

         for (int ix = 0; ix < 16; ix++) {
            if (decodedNum1[ix] == 10) {
               ifirstAbyte = ix;
               break;
            }

            String temp = Integer.toHexString(decodedNum1[ix]);
            char[] chtemp = new char[1];
            temp.getChars(0, 1, chtemp, 0);
            decodedNum1Chars[ix] = chtemp[0];
         }

         String strTemp = new String(decodedNum1Chars);
         String StrNum1 = "+" + strTemp;
         String num1 = StrNum1.substring(0, ifirstAbyte + 1);
         gcom.setNumGcom(num1);
      }
   }

   public static void parsePayloadFrame0x8E(byte[] gcomParamBytes, FAAC_GCOM gcom) {
      int index = 0;
      byte[] decodedPbIndex = new byte[2];
      System.arraycopy(gcomParamBytes, index, decodedPbIndex, 0, decodedPbIndex.length);
      index += decodedPbIndex.length;
      int pbIndex = decodedPbIndex[0];
      pbIndex <<= 8;
      pbIndex |= decodedPbIndex[1];
      PBentry[] entries = new PBentry[4];
      byte[] decodedPhone = new byte[16];
      byte[] decodedAttribute = new byte[1];
      byte[] decodedProfile = new byte[1];

      for (int numIndex = 0; numIndex < 4; numIndex++) {
         System.arraycopy(gcomParamBytes, index, decodedPhone, 0, decodedPhone.length);
         index += decodedPhone.length;
         int phoneLength = 16;

         for (int i = 0; i < 16; i++) {
            if (decodedPhone[i] == 65) {
               phoneLength = i;
               break;
            }
         }

         String phone = new String(decodedPhone, 0, phoneLength);
         System.arraycopy(gcomParamBytes, index, decodedProfile, 0, decodedProfile.length);
         index += decodedProfile.length;
         System.arraycopy(gcomParamBytes, index, decodedAttribute, 0, decodedAttribute.length);
         index += decodedAttribute.length;
         if (phone.compareTo("0000000000000000") == 0) {
            entries[numIndex] = PBentry.GetNewEmptyEntry(pbIndex * 4 + numIndex);
         } else {
            entries[numIndex] = new PBentry(pbIndex * 4 + numIndex, phone, decodedProfile[0], decodedAttribute[0]);
         }
      }

      gcom.getPhoneBook().AddEntries(entries, pbIndex);
   }

   public static boolean parsePrgFile(byte[] byte2parse, FAAC_Settings boardSettings, String boardModel, String filePassword) {
      byte[] psw = new byte[4];

      for (int i = 0; i < 4; i++) {
         psw[i] = FAAC_Protocol.FileSplitByteArrayToByte(new byte[]{byte2parse[8 + i * 2], byte2parse[9 + i * 2]});
      }

      String readPsw = new String(psw);
      if (filePassword.compareTo("") != 0 && filePassword.compareTo(readPsw) != 0) {
         return false;
      } else {
         byte[] prog1 = new byte[32];

         for (int i = 0; i < 32; i++) {
            prog1[i] = byte2parse[i + 24];
         }

         byte[] prog2 = new byte[32];

         for (int i = 0; i < 32; i++) {
            prog2[i] = byte2parse[i + 56];
         }

         byte[] prog3 = new byte[32];

         for (int i = 0; i < 32; i++) {
            prog3[i] = byte2parse[i + 88];
         }

         byte[] prog4 = new byte[32];

         for (int i = 0; i < 32; i++) {
            prog4[i] = byte2parse[i + 120];
         }

         byte[] prog5 = new byte[32];

         for (int i = 0; i < 32; i++) {
            prog5[i] = byte2parse[i + 152];
         }

         boardSettings.getLogicFlag().setBytes_LSBfirst(new byte[]{prog1[0], prog1[1], prog1[2], prog1[3]});
         boardSettings.getProgrammingFlag().setBytes_LSBfirst(new byte[]{prog1[4], prog1[5], prog1[6], prog1[7]});
         boardSettings.setTimePauseA_sec(FAAC_Protocol.byteArrayToInt(new byte[]{prog1[9], prog1[8]}));
         boardSettings.setTimePauseB_sec(FAAC_Protocol.byteArrayToInt(new byte[]{prog1[11], prog1[10]}));
         boardSettings.setTimeSleep_sec(FAAC_Protocol.byteArrayToInt(new byte[]{prog1[13], prog1[12]}));
         boardSettings.setTimeoutMov_sec(FAAC_Protocol.byteArrayToInt(new byte[]{prog1[15], prog1[14]}));
         boardSettings.setForceOpenMotor1(prog1[16]);
         boardSettings.setForceOpenMotor2(prog1[17]);
         boardSettings.setForceCloseMotor1(prog1[18]);
         boardSettings.setForceCloseMotor2(prog1[19]);
         boardSettings.setMotor1Type(prog1[20]);
         boardSettings.setMotor2Type(prog1[21]);
         boardSettings.setRallSpaceOpenMotor1(prog1[22]);
         boardSettings.setRallSpaceOpenMotor2(prog1[23]);
         boardSettings.setRallSpaceCloseMotor1(prog1[24]);
         boardSettings.setRallSpaceCloseMotor2(prog1[25]);
         boardSettings.setBattSpaceOpenMotor1_01deg(prog1[26]);
         boardSettings.setBattSpaceOpenMotor2_01deg(prog1[27]);
         boardSettings.setBattSpaceCloseMotor1_01deg(prog1[28]);
         boardSettings.setBattSpaceCloseMotor2_01deg(prog1[29]);
         boardSettings.setPartialOpeningOpenB(prog1[30]);
         boardSettings.setNbcycleAssistance(FAAC_Protocol.byteArrayToInt(new byte[]{prog2[1], prog2[0]}));
         boardSettings.setTimeHoldclose_sec(FAAC_Protocol.byteArrayToInt(new byte[]{prog2[3], prog2[2]}));
         boardSettings.setTimeDelayOpenAnta_sec(prog2[4]);
         boardSettings.setTimeDelayCloseAnta_sec(prog2[5]);
         boardSettings.setTimeReverseStrokeAnta1_100msec(prog2[6]);
         boardSettings.setTimeReverseStrokeAnta2_100msec(prog2[7]);
         boardSettings.setTypeReverseStrokeAnta1(prog2[8]);
         boardSettings.setTypeReverseStrokeAnta2(prog2[9]);
         boardSettings.setTimeLock1_100msec(prog2[10]);
         boardSettings.setTimeLock2_100msec(prog2[11]);
         boardSettings.getTypeLock1().setByte(prog2[12]);
         boardSettings.getTypeLock2().setByte(prog2[13]);
         boardSettings.setTimeColpoFinAnta1_100msec(prog2[14]);
         boardSettings.setTimeColpoFinAnta2_100msec(prog2[15]);
         boardSettings.setTypeColpoFinAnta1(prog2[16]);
         boardSettings.setTypeColpoFinAnta2(prog2[17]);
         boardSettings.setTimeSpuntoAnta1_100msec(prog2[18]);
         boardSettings.setTimeSpuntoAnta2_100msec(prog2[19]);
         boardSettings.setFinecorsaOpenAnta1(prog2[20]);
         boardSettings.setFinecorsaOpenAnta2(prog2[21]);
         boardSettings.setFinecorsaCloseAnta1(prog2[22]);
         boardSettings.setFinecorsaCloseAnta2(prog2[23]);
         boardSettings.setTimePrelamp_100msec(prog2[24]);
         boardSettings.setTypePrelamp(prog2[25]);
         boardSettings.setEncoderMotor1(prog2[26]);
         boardSettings.setEncoderMotor2(prog2[27]);
         boardSettings.setTimeInvObstPartial_100msec(prog2[30]);

         for (int index = 0; index < 16; index++) {
            boardSettings.getSec2easyTypeArray(index).setInTypeFromBoard(prog3[index]);
         }

         for (int index = 0; index < 14; index++) {
            boardSettings.getDatImp2easyTypeArray(index).setInTypeFromBoard(prog3[16 + index]);
         }

         if (boardModel.compareToIgnoreCase("E145") == 0) {
            ((E145_Settings)boardSettings).setLivFrenataScorr1(prog4[0]);
            ((E145_Settings)boardSettings).setLivFrenataScorr2(prog4[1]);
         }

         boardSettings.setTimeOut1_secOrMin(prog4[2]);
         boardSettings.setTimeOut2_secOrMin(prog4[3]);
         boardSettings.getOutType(0).setOutTypeFromBoard(prog4[4]);
         boardSettings.getOutType(1).setOutTypeFromBoard(prog4[5]);

         for (int index = 0; index < 5; index++) {
            boardSettings.getInType(index).setInTypeFromBoard(prog4[6 + index]);
         }

         for (int index = 0; index < 2; index++) {
            boardSettings.getRadioDecType(index).setInTypeFromBoard(prog4[11 + index]);
         }

         for (int index = 0; index < 2; index++) {
            boardSettings.getRadioXfType(index).setInTypeFromBoard(prog4[13 + index]);
         }

         boardSettings.setSensObstOpenAnta1_100msec(prog4[15]);
         boardSettings.setSensObstOpenAnta2_100msec(prog4[16]);
         boardSettings.setSensObstCloseAnta1_100msec(prog4[17]);
         boardSettings.setSensObstCloseAnta2_100msec(prog4[18]);
         boardSettings.setSensObstRallOpenAnta1_100msec(prog4[19]);
         boardSettings.setSensObstRallOpenAnta2_100msec(prog4[20]);
         boardSettings.setSensObstRallCloseAnta1_100msec(prog4[21]);
         boardSettings.setSensObstRallCloseAnta2_100msec(prog4[22]);
         boardSettings.setTimeAddAnta1_sec(prog4[23]);
         boardSettings.setProg4_Byte31(prog4[25]);
         boardSettings.setProg4_Byte32(prog4[26]);
         boardSettings.setSoftstart1(prog4[27]);
         boardSettings.setSoftstart2(prog4[28]);
         boardSettings.setNumObstCons(prog4[29]);
         if (boardModel.compareToIgnoreCase("E124") == 0) {
            ((E124_Settings)boardSettings).setSpeedOpen1(prog5[0]);
            ((E124_Settings)boardSettings).setSpeedOpen2(prog5[1]);
            ((E124_Settings)boardSettings).setSpeedClose1(prog5[2]);
            ((E124_Settings)boardSettings).setSpeedClose2(prog5[3]);
            ((E124_Settings)boardSettings).setUltrasense1(prog5[8]);
            ((E124_Settings)boardSettings).setUltrasense2(prog5[9]);
            ((E124_Settings)boardSettings).setSpeedRall1(prog5[10]);
            ((E124_Settings)boardSettings).setSpeedRall2(prog5[11]);
            ((E124_Settings)boardSettings).setTimeSoftTouchAnta1_100msec(prog5[12]);
            ((E124_Settings)boardSettings).setTimeSoftTouchAnta2_100msec(prog5[13]);
            ((E124_Settings)boardSettings).setTypeSoftTouchAnta1(prog5[14]);
            ((E124_Settings)boardSettings).setTypeSoftTouchAnta2(prog5[15]);
            ((E124_Settings)boardSettings).setColpoForce1(prog5[16]);
            ((E124_Settings)boardSettings).setColpoForce2(prog5[17]);
            ((E124_Settings)boardSettings).setRevStrForce1(prog5[18]);
            ((E124_Settings)boardSettings).setRevStrForce2(prog5[19]);
         }

         if (boardModel.compareToIgnoreCase("E145") == 0) {
            ((E145_Settings)boardSettings).setAddTimeOpen1(prog5[4]);
            ((E145_Settings)boardSettings).setAddTimeOpen2(prog5[5]);
            ((E145_Settings)boardSettings).setAddTimeClose1(prog5[6]);
            ((E145_Settings)boardSettings).setAddTimeClose2(prog5[7]);
         }

         return true;
      }
   }

   public static void parseFrame0x81(byte[] frame2parse, FAAC_Settings boardSettings) throws Exception {
      int ideltaPsw = 8;
      byte[] codedLogicFlag = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedLogicFlag[i] = frame2parse[i + 15 - ideltaPsw];
      }

      byte[] decodedLogicFlag;
      try {
         decodedLogicFlag = FAAC_Protocol.intelHexDecoderDataFlow(codedLogicFlag);
      } catch (Exception var70) {
         Debug.printlnStatic(var70.toString());
         throw var70;
      }

      boardSettings.getLogicFlag().setBytes_LSBfirst(decodedLogicFlag);
      byte[] codedProgFlag = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedProgFlag[i] = frame2parse[i + 23 - ideltaPsw];
      }

      byte[] decodedProgFlag;
      try {
         decodedProgFlag = FAAC_Protocol.intelHexDecoderDataFlow(codedProgFlag);
      } catch (Exception var69) {
         Debug.printlnStatic(var69.toString());
         throw var69;
      }

      boardSettings.getProgrammingFlag().setBytes_LSBfirst(decodedProgFlag);
      byte[] codedTempoPausaOpenA = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedTempoPausaOpenA[i] = frame2parse[i + 31 - ideltaPsw];
      }

      byte[] decodedTempoPausaOpenA;
      try {
         decodedTempoPausaOpenA = FAAC_Protocol.intelHexDecoderDataFlow(codedTempoPausaOpenA);
      } catch (Exception var68) {
         Debug.printlnStatic(var68.toString());
         throw var68;
      }

      byte[] decodedTempoPausaOpenA_MSBfirst = new byte[]{decodedTempoPausaOpenA[1], decodedTempoPausaOpenA[0]};
      boardSettings.setTimePauseA_sec(FAAC_Protocol.byteArrayToInt(decodedTempoPausaOpenA_MSBfirst));
      byte[] codedTempoPausaOpenB = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedTempoPausaOpenB[i] = frame2parse[i + 35 - ideltaPsw];
      }

      byte[] decodedTempoPausaOpenB;
      try {
         decodedTempoPausaOpenB = FAAC_Protocol.intelHexDecoderDataFlow(codedTempoPausaOpenB);
      } catch (Exception var67) {
         Debug.printlnStatic(var67.toString());
         throw var67;
      }

      byte[] decodedTempoPausaOpenB_MSBfirst = new byte[]{decodedTempoPausaOpenB[1], decodedTempoPausaOpenB[0]};
      boardSettings.setTimePauseB_sec(FAAC_Protocol.byteArrayToInt(decodedTempoPausaOpenB_MSBfirst));
      byte[] codedTempoSleep = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedTempoSleep[i] = frame2parse[i + 39 - ideltaPsw];
      }

      byte[] decodedTempoSleep;
      try {
         decodedTempoSleep = FAAC_Protocol.intelHexDecoderDataFlow(codedTempoSleep);
      } catch (Exception var66) {
         Debug.printlnStatic(var66.toString());
         throw var66;
      }

      byte[] decodedTempoSleep_MSBfirst = new byte[]{decodedTempoSleep[1], decodedTempoSleep[0]};
      boardSettings.setTimeSleep_sec(FAAC_Protocol.byteArrayToInt(decodedTempoSleep_MSBfirst));
      byte[] codedTimeout = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedTimeout[i] = frame2parse[i + 43 - ideltaPsw];
      }

      byte[] decodedTimeout;
      try {
         decodedTimeout = FAAC_Protocol.intelHexDecoderDataFlow(codedTimeout);
      } catch (Exception var65) {
         Debug.printlnStatic(var65.toString());
         throw var65;
      }

      byte[] decodedTimeout_MSBfirst = new byte[]{decodedTimeout[1], decodedTimeout[0]};
      boardSettings.setTimeoutMov_sec(FAAC_Protocol.byteArrayToInt(decodedTimeout_MSBfirst));
      byte[] codedForceOpen1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedForceOpen1[i] = frame2parse[i + 47 - ideltaPsw];
      }

      byte decodedForceOpen1;
      try {
         decodedForceOpen1 = FAAC_Protocol.intelHexDecoder(codedForceOpen1);
      } catch (Exception var64) {
         Debug.printlnStatic(var64.toString());
         throw var64;
      }

      boardSettings.setForceOpenMotor1(decodedForceOpen1);
      byte[] codedForceOpen2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedForceOpen2[i] = frame2parse[i + 49 - ideltaPsw];
      }

      byte decodedForceOpen2;
      try {
         decodedForceOpen2 = FAAC_Protocol.intelHexDecoder(codedForceOpen2);
      } catch (Exception var63) {
         Debug.printlnStatic(var63.toString());
         throw var63;
      }

      boardSettings.setForceOpenMotor2(decodedForceOpen2);
      byte[] codedForceClose1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedForceClose1[i] = frame2parse[i + 51 - ideltaPsw];
      }

      byte decodedForceClose1;
      try {
         decodedForceClose1 = FAAC_Protocol.intelHexDecoder(codedForceClose1);
      } catch (Exception var62) {
         Debug.printlnStatic(var62.toString());
         throw var62;
      }

      boardSettings.setForceCloseMotor1(decodedForceClose1);
      byte[] codedForceClose2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedForceClose2[i] = frame2parse[i + 53 - ideltaPsw];
      }

      byte decodedForceClose2;
      try {
         decodedForceClose2 = FAAC_Protocol.intelHexDecoder(codedForceClose2);
      } catch (Exception var61) {
         Debug.printlnStatic(var61.toString());
         throw var61;
      }

      boardSettings.setForceCloseMotor2(decodedForceClose2);
      byte[] codedMotor1Type = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMotor1Type[i] = frame2parse[i + 55 - ideltaPsw];
      }

      byte decodedMotor1Type;
      try {
         decodedMotor1Type = FAAC_Protocol.intelHexDecoder(codedMotor1Type);
      } catch (Exception var60) {
         Debug.printlnStatic(var60.toString());
         throw var60;
      }

      boardSettings.setMotor1Type(decodedMotor1Type);
      byte[] codedMotor2Type = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMotor2Type[i] = frame2parse[i + 57 - ideltaPsw];
      }

      byte decodedMotor2Type;
      try {
         decodedMotor2Type = FAAC_Protocol.intelHexDecoder(codedMotor2Type);
      } catch (Exception var59) {
         Debug.printlnStatic(var59.toString());
         throw var59;
      }

      boardSettings.setMotor2Type(decodedMotor2Type);
      byte[] codedSpRallOpen1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpRallOpen1[i] = frame2parse[i + 59 - ideltaPsw];
      }

      byte decodedSpRallOpen1;
      try {
         decodedSpRallOpen1 = FAAC_Protocol.intelHexDecoder(codedSpRallOpen1);
      } catch (Exception var58) {
         Debug.printlnStatic(var58.toString());
         throw var58;
      }

      boardSettings.setRallSpaceOpenMotor1(decodedSpRallOpen1);
      byte[] codedSpRallOpen2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpRallOpen2[i] = frame2parse[i + 61 - ideltaPsw];
      }

      byte decodedSpRallOpen2;
      try {
         decodedSpRallOpen2 = FAAC_Protocol.intelHexDecoder(codedSpRallOpen2);
      } catch (Exception var57) {
         Debug.printlnStatic(var57.toString());
         throw var57;
      }

      boardSettings.setRallSpaceOpenMotor2(decodedSpRallOpen2);
      byte[] codedSpRallClose1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpRallClose1[i] = frame2parse[i + 63 - ideltaPsw];
      }

      byte decodedSpRallClose1;
      try {
         decodedSpRallClose1 = FAAC_Protocol.intelHexDecoder(codedSpRallClose1);
      } catch (Exception var56) {
         Debug.printlnStatic(var56.toString());
         throw var56;
      }

      boardSettings.setRallSpaceCloseMotor1(decodedSpRallClose1);
      byte[] codedSpRallClose2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpRallClose2[i] = frame2parse[i + 65 - ideltaPsw];
      }

      byte decodedSpRallClose2;
      try {
         decodedSpRallClose2 = FAAC_Protocol.intelHexDecoder(codedSpRallClose2);
      } catch (Exception var55) {
         Debug.printlnStatic(var55.toString());
         throw var55;
      }

      boardSettings.setRallSpaceCloseMotor2(decodedSpRallClose2);
      byte[] codedSpBattOpen1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpBattOpen1[i] = frame2parse[i + 67 - ideltaPsw];
      }

      byte decodedSpBattOpen1;
      try {
         decodedSpBattOpen1 = FAAC_Protocol.intelHexDecoder(codedSpBattOpen1);
      } catch (Exception var54) {
         Debug.printlnStatic(var54.toString());
         throw var54;
      }

      if (decodedSpBattOpen1 < 0) {
         boardSettings.setBattSpaceOpenMotor1_01deg(FAAC_Protocol.unsignedByteToInt(decodedSpBattOpen1));
      } else {
         boardSettings.setBattSpaceOpenMotor1_01deg(decodedSpBattOpen1);
      }

      byte[] codedSpBattOpen2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpBattOpen2[i] = frame2parse[i + 69 - ideltaPsw];
      }

      byte decodedSpBattOpen2;
      try {
         decodedSpBattOpen2 = FAAC_Protocol.intelHexDecoder(codedSpBattOpen2);
      } catch (Exception var53) {
         Debug.printlnStatic(var53.toString());
         throw var53;
      }

      if (decodedSpBattOpen2 < 0) {
         boardSettings.setBattSpaceOpenMotor2_01deg(FAAC_Protocol.unsignedByteToInt(decodedSpBattOpen2));
      } else {
         boardSettings.setBattSpaceOpenMotor2_01deg(decodedSpBattOpen2);
      }

      byte[] codedSpBattClose1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpBattClose1[i] = frame2parse[i + 71 - ideltaPsw];
      }

      byte decodedSpBattClose1;
      try {
         decodedSpBattClose1 = FAAC_Protocol.intelHexDecoder(codedSpBattClose1);
      } catch (Exception var52) {
         Debug.printlnStatic(var52.toString());
         throw var52;
      }

      if (decodedSpBattClose1 < 0) {
         boardSettings.setBattSpaceCloseMotor1_01deg(FAAC_Protocol.unsignedByteToInt(decodedSpBattClose1));
      } else {
         boardSettings.setBattSpaceCloseMotor1_01deg(decodedSpBattClose1);
      }

      byte[] codedSpBattClose2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpBattClose2[i] = frame2parse[i + 73 - ideltaPsw];
      }

      byte decodedSpBattClose2;
      try {
         decodedSpBattClose2 = FAAC_Protocol.intelHexDecoder(codedSpBattClose2);
      } catch (Exception var51) {
         Debug.printlnStatic(var51.toString());
         throw var51;
      }

      if (decodedSpBattClose2 < 0) {
         boardSettings.setBattSpaceCloseMotor2_01deg(FAAC_Protocol.unsignedByteToInt(decodedSpBattClose2));
      } else {
         boardSettings.setBattSpaceCloseMotor2_01deg(decodedSpBattClose2);
      }

      byte[] codedPartialOp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedPartialOp[i] = frame2parse[i + 75 - ideltaPsw];
      }

      byte decodedPartialOp;
      try {
         decodedPartialOp = FAAC_Protocol.intelHexDecoder(codedPartialOp);
      } catch (Exception var50) {
         Debug.printlnStatic(var50.toString());
         throw var50;
      }

      boardSettings.setPartialOpeningOpenB(decodedPartialOp);
   }

   public static void parseFrame0x82(byte[] frame2parse, FAAC_Settings boardSettings) throws Exception {
      int ideltaPsw = 8;
      byte[] codedNbcycle = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedNbcycle[i] = frame2parse[i + 15 - ideltaPsw];
      }

      byte[] decodedNbCycle;
      try {
         decodedNbCycle = FAAC_Protocol.intelHexDecoderDataFlow(codedNbcycle);
      } catch (Exception var86) {
         Debug.printlnStatic(var86.toString());
         throw var86;
      }

      byte[] decodedNbCycle_MSBfirst = new byte[]{decodedNbCycle[1], decodedNbCycle[0]};
      boardSettings.setNbcycleAssistance(FAAC_Protocol.byteArrayToInt(decodedNbCycle_MSBfirst));
      byte[] codedTimeHold = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedTimeHold[i] = frame2parse[i + 19 - ideltaPsw];
      }

      byte[] decodedTimeHold;
      try {
         decodedTimeHold = FAAC_Protocol.intelHexDecoderDataFlow(codedTimeHold);
      } catch (Exception var85) {
         Debug.printlnStatic(var85.toString());
         throw var85;
      }

      byte[] decodedTimeHold_MSBfirst = new byte[]{decodedTimeHold[1], decodedTimeHold[0]};
      boardSettings.setTimeHoldclose_sec(FAAC_Protocol.byteArrayToInt(decodedTimeHold_MSBfirst));
      byte[] codedRitOp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedRitOp[i] = frame2parse[i + 23 - ideltaPsw];
      }

      byte decodedRitOp;
      try {
         decodedRitOp = FAAC_Protocol.intelHexDecoder(codedRitOp);
      } catch (Exception var84) {
         Debug.printlnStatic(var84.toString());
         throw var84;
      }

      boardSettings.setTimeDelayOpenAnta_sec(decodedRitOp);
      byte[] codedRitCl = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedRitCl[i] = frame2parse[i + 25 - ideltaPsw];
      }

      byte decodedRitCl;
      try {
         decodedRitCl = FAAC_Protocol.intelHexDecoder(codedRitCl);
      } catch (Exception var83) {
         Debug.printlnStatic(var83.toString());
         throw var83;
      }

      boardSettings.setTimeDelayCloseAnta_sec(decodedRitCl);
      byte[] codedRevStr1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedRevStr1[i] = frame2parse[i + 27 - ideltaPsw];
      }

      byte decodedRevStr1;
      try {
         decodedRevStr1 = FAAC_Protocol.intelHexDecoder(codedRevStr1);
      } catch (Exception var82) {
         Debug.printlnStatic(var82.toString());
         throw var82;
      }

      boardSettings.setTimeReverseStrokeAnta1_100msec(decodedRevStr1);
      byte[] codedRevStr2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedRevStr2[i] = frame2parse[i + 29 - ideltaPsw];
      }

      byte decodedRevStr2;
      try {
         decodedRevStr2 = FAAC_Protocol.intelHexDecoder(codedRevStr2);
      } catch (Exception var81) {
         Debug.printlnStatic(var81.toString());
         throw var81;
      }

      boardSettings.setTimeReverseStrokeAnta2_100msec(decodedRevStr2);
      byte[] codedTypeRevStr1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypeRevStr1[i] = frame2parse[i + 31 - ideltaPsw];
      }

      byte decodedTypeRevStr1;
      try {
         decodedTypeRevStr1 = FAAC_Protocol.intelHexDecoder(codedTypeRevStr1);
      } catch (Exception var80) {
         Debug.printlnStatic(var80.toString());
         throw var80;
      }

      boardSettings.setTypeReverseStrokeAnta1(decodedTypeRevStr1);
      byte[] codedTypeRevStr2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypeRevStr2[i] = frame2parse[i + 33 - ideltaPsw];
      }

      byte decodedTypeRevStr2;
      try {
         decodedTypeRevStr2 = FAAC_Protocol.intelHexDecoder(codedTypeRevStr2);
      } catch (Exception var79) {
         Debug.printlnStatic(var79.toString());
         throw var79;
      }

      boardSettings.setTypeReverseStrokeAnta2(decodedTypeRevStr2);
      byte[] codedTimeLock1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeLock1[i] = frame2parse[i + 35 - ideltaPsw];
      }

      byte decodedTimeLock1;
      try {
         decodedTimeLock1 = FAAC_Protocol.intelHexDecoder(codedTimeLock1);
      } catch (Exception var78) {
         Debug.printlnStatic(var78.toString());
         throw var78;
      }

      boardSettings.setTimeLock1_100msec(decodedTimeLock1);
      byte[] codedTimeLock2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeLock2[i] = frame2parse[i + 37 - ideltaPsw];
      }

      byte decodedTimeLock2;
      try {
         decodedTimeLock2 = FAAC_Protocol.intelHexDecoder(codedTimeLock2);
      } catch (Exception var77) {
         Debug.printlnStatic(var77.toString());
         throw var77;
      }

      boardSettings.setTimeLock2_100msec(decodedTimeLock2);
      byte[] codedTypeLock1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypeLock1[i] = frame2parse[i + 39 - ideltaPsw];
      }

      byte decodedTypeLock1;
      try {
         decodedTypeLock1 = FAAC_Protocol.intelHexDecoder(codedTypeLock1);
      } catch (Exception var76) {
         Debug.printlnStatic(var76.toString());
         throw var76;
      }

      boardSettings.getTypeLock1().setByte(decodedTypeLock1);
      byte[] codedTypeLock2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypeLock2[i] = frame2parse[i + 41 - ideltaPsw];
      }

      byte decodedTypeLock2;
      try {
         decodedTypeLock2 = FAAC_Protocol.intelHexDecoder(codedTypeLock2);
      } catch (Exception var75) {
         Debug.printlnStatic(var75.toString());
         throw var75;
      }

      boardSettings.getTypeLock2().setByte(decodedTypeLock2);
      byte[] codedTimeColpoFin1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeColpoFin1[i] = frame2parse[i + 43 - ideltaPsw];
      }

      byte decodedTimeColpoFin1;
      try {
         decodedTimeColpoFin1 = FAAC_Protocol.intelHexDecoder(codedTimeColpoFin1);
      } catch (Exception var74) {
         Debug.printlnStatic(var74.toString());
         throw var74;
      }

      boardSettings.setTimeColpoFinAnta1_100msec(decodedTimeColpoFin1);
      byte[] codedTimeColpoFin2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeColpoFin2[i] = frame2parse[i + 45 - ideltaPsw];
      }

      byte decodedTimeColpoFin2;
      try {
         decodedTimeColpoFin2 = FAAC_Protocol.intelHexDecoder(codedTimeColpoFin2);
      } catch (Exception var73) {
         Debug.printlnStatic(var73.toString());
         throw var73;
      }

      boardSettings.setTimeColpoFinAnta2_100msec(decodedTimeColpoFin2);
      byte[] codedTypeColpoFin1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypeColpoFin1[i] = frame2parse[i + 47 - ideltaPsw];
      }

      byte decodedTypeColpoFin1;
      try {
         decodedTypeColpoFin1 = FAAC_Protocol.intelHexDecoder(codedTypeColpoFin1);
      } catch (Exception var72) {
         Debug.printlnStatic(var72.toString());
         throw var72;
      }

      boardSettings.setTypeColpoFinAnta1(decodedTypeColpoFin1);
      byte[] codedTypeColpoFin2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypeColpoFin2[i] = frame2parse[i + 49 - ideltaPsw];
      }

      byte decodedTypeColpoFin2;
      try {
         decodedTypeColpoFin2 = FAAC_Protocol.intelHexDecoder(codedTypeColpoFin2);
      } catch (Exception var71) {
         Debug.printlnStatic(var71.toString());
         throw var71;
      }

      boardSettings.setTypeColpoFinAnta2(decodedTypeColpoFin2);
      byte[] codedTimeSpunto1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeSpunto1[i] = frame2parse[i + 51 - ideltaPsw];
      }

      byte decodedTimeSpunto1;
      try {
         decodedTimeSpunto1 = FAAC_Protocol.intelHexDecoder(codedTimeSpunto1);
      } catch (Exception var70) {
         Debug.printlnStatic(var70.toString());
         throw var70;
      }

      boardSettings.setTimeSpuntoAnta1_100msec(decodedTimeSpunto1);
      byte[] codedTimeSpunto2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeSpunto2[i] = frame2parse[i + 53 - ideltaPsw];
      }

      byte decodedTimeSpunto2;
      try {
         decodedTimeSpunto2 = FAAC_Protocol.intelHexDecoder(codedTimeSpunto2);
      } catch (Exception var69) {
         Debug.printlnStatic(var69.toString());
         throw var69;
      }

      boardSettings.setTimeSpuntoAnta2_100msec(decodedTimeSpunto2);
      byte[] codedFinecorsaOpen1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedFinecorsaOpen1[i] = frame2parse[i + 55 - ideltaPsw];
      }

      byte decodedFinecorsaOpen1;
      try {
         decodedFinecorsaOpen1 = FAAC_Protocol.intelHexDecoder(codedFinecorsaOpen1);
      } catch (Exception var68) {
         Debug.printlnStatic(var68.toString());
         throw var68;
      }

      boardSettings.setFinecorsaOpenAnta1(decodedFinecorsaOpen1);
      byte[] codedFinecorsaOpen2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedFinecorsaOpen2[i] = frame2parse[i + 57 - ideltaPsw];
      }

      byte decodedFinecorsaOpen2;
      try {
         decodedFinecorsaOpen2 = FAAC_Protocol.intelHexDecoder(codedFinecorsaOpen2);
      } catch (Exception var67) {
         Debug.printlnStatic(var67.toString());
         throw var67;
      }

      boardSettings.setFinecorsaOpenAnta2(decodedFinecorsaOpen2);
      byte[] codedFinecorsaClose1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedFinecorsaClose1[i] = frame2parse[i + 59 - ideltaPsw];
      }

      byte decodedFinecorsaClose1;
      try {
         decodedFinecorsaClose1 = FAAC_Protocol.intelHexDecoder(codedFinecorsaClose1);
      } catch (Exception var66) {
         Debug.printlnStatic(var66.toString());
         throw var66;
      }

      boardSettings.setFinecorsaCloseAnta1(decodedFinecorsaClose1);
      byte[] codedFinecorsaClose2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedFinecorsaClose2[i] = frame2parse[i + 61 - ideltaPsw];
      }

      byte decodedFinecorsaClose2;
      try {
         decodedFinecorsaClose2 = FAAC_Protocol.intelHexDecoder(codedFinecorsaClose2);
      } catch (Exception var65) {
         Debug.printlnStatic(var65.toString());
         throw var65;
      }

      boardSettings.setFinecorsaCloseAnta2(decodedFinecorsaClose2);
      byte[] codedTimePrelamp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimePrelamp[i] = frame2parse[i + 63 - ideltaPsw];
      }

      byte decodedTimePrelamp;
      try {
         decodedTimePrelamp = FAAC_Protocol.intelHexDecoder(codedTimePrelamp);
      } catch (Exception var64) {
         Debug.printlnStatic(var64.toString());
         throw var64;
      }

      boardSettings.setTimePrelamp_100msec(decodedTimePrelamp);
      byte[] codedTypePrelamp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypePrelamp[i] = frame2parse[i + 65 - ideltaPsw];
      }

      byte decodedTypePrelamp;
      try {
         decodedTypePrelamp = FAAC_Protocol.intelHexDecoder(codedTypePrelamp);
      } catch (Exception var63) {
         Debug.printlnStatic(var63.toString());
         throw var63;
      }

      boardSettings.setTypePrelamp(decodedTypePrelamp);
      byte[] codedEnc1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedEnc1[i] = frame2parse[i + 67 - ideltaPsw];
      }

      byte decodedEnc1;
      try {
         decodedEnc1 = FAAC_Protocol.intelHexDecoder(codedEnc1);
      } catch (Exception var62) {
         Debug.printlnStatic(var62.toString());
         throw var62;
      }

      boardSettings.setEncoderMotor1(decodedEnc1);
      byte[] codedEnc2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedEnc2[i] = frame2parse[i + 69 - ideltaPsw];
      }

      byte decodedEnc2;
      try {
         decodedEnc2 = FAAC_Protocol.intelHexDecoder(codedEnc2);
      } catch (Exception var61) {
         Debug.printlnStatic(var61.toString());
         throw var61;
      }

      boardSettings.setEncoderMotor2(decodedEnc2);
      byte[] codedTimeInv = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeInv[i] = frame2parse[i + 75 - ideltaPsw];
      }

      byte decodedTimeInv;
      try {
         decodedTimeInv = FAAC_Protocol.intelHexDecoder(codedTimeInv);
      } catch (Exception var60) {
         Debug.printlnStatic(var60.toString());
         throw var60;
      }

      boardSettings.setTimeInvObstPartial_100msec(decodedTimeInv);
   }

   public static void parseFrame0x83(byte[] frame2parse, FAAC_Settings boardSettings) throws Exception {
      int ideltaPsw = 8;
      byte[] codedsec = new byte[2];

      for (int index = 0; index < 16; index++) {
         for (int i = 0; i < 2; i++) {
            codedsec[i] = frame2parse[i + 15 + index * 2 - ideltaPsw];
         }

         byte decodedsec;
         try {
            decodedsec = FAAC_Protocol.intelHexDecoder(codedsec);
         } catch (Exception var10) {
            Debug.printlnStatic(var10.toString());
            throw var10;
         }

         boardSettings.getSec2easyTypeArray(index).setInTypeFromBoard(decodedsec);
      }

      byte[] codeddat = new byte[2];

      for (int index = 0; index < 14; index++) {
         for (int i = 0; i < 2; i++) {
            codeddat[i] = frame2parse[i + 47 + index * 2 - ideltaPsw];
         }

         byte decodeddat;
         try {
            decodeddat = FAAC_Protocol.intelHexDecoder(codeddat);
         } catch (Exception var9) {
            Debug.printlnStatic(var9.toString());
            throw var9;
         }

         boardSettings.getDatImp2easyTypeArray(index).setInTypeFromBoard(decodeddat);
      }
   }

   public static void parseFrame0x84(byte[] frame2parse, FAAC_Settings boardSettings, String boardModel) throws Exception {
      int ideltaPsw = 8;
      if (boardModel.compareToIgnoreCase("E145") == 0) {
         byte[] codedLivFrenata1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedLivFrenata1[i] = frame2parse[i + 15 - ideltaPsw];
         }

         byte decodedLivFrenata1;
         try {
            decodedLivFrenata1 = FAAC_Protocol.intelHexDecoder(codedLivFrenata1);
         } catch (Exception var69) {
            Debug.printlnStatic(var69.toString());
            throw var69;
         }

         ((E145_Settings)boardSettings).setLivFrenataScorr1(decodedLivFrenata1);
         byte[] codedLivFrenata2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedLivFrenata2[i] = frame2parse[i + 17 - ideltaPsw];
         }

         byte decodedLivFrenata2;
         try {
            decodedLivFrenata2 = FAAC_Protocol.intelHexDecoder(codedLivFrenata2);
         } catch (Exception var68) {
            Debug.printlnStatic(var68.toString());
            throw var68;
         }

         ((E145_Settings)boardSettings).setLivFrenataScorr2(decodedLivFrenata2);
      }

      byte[] codedTimeout1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeout1[i] = frame2parse[i + 19 - ideltaPsw];
      }

      byte decodedTimeout1;
      try {
         decodedTimeout1 = FAAC_Protocol.intelHexDecoder(codedTimeout1);
      } catch (Exception var67) {
         Debug.printlnStatic(var67.toString());
         throw var67;
      }

      boardSettings.setTimeOut1_secOrMin(decodedTimeout1);
      byte[] codedTimeout2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeout2[i] = frame2parse[i + 21 - ideltaPsw];
      }

      byte decodedTimeout2;
      try {
         decodedTimeout2 = FAAC_Protocol.intelHexDecoder(codedTimeout2);
      } catch (Exception var66) {
         Debug.printlnStatic(var66.toString());
         throw var66;
      }

      boardSettings.setTimeOut2_secOrMin(decodedTimeout2);
      byte[] codedTypeOut1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypeOut1[i] = frame2parse[i + 23 - ideltaPsw];
      }

      byte decodedTypeOut1;
      try {
         decodedTypeOut1 = FAAC_Protocol.intelHexDecoder(codedTypeOut1);
      } catch (Exception var65) {
         Debug.printlnStatic(var65.toString());
         throw var65;
      }

      boardSettings.getOutType(0).setOutTypeFromBoard(decodedTypeOut1);
      byte[] codedTypeOut2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTypeOut2[i] = frame2parse[i + 25 - ideltaPsw];
      }

      byte decodedTypeOut2;
      try {
         decodedTypeOut2 = FAAC_Protocol.intelHexDecoder(codedTypeOut2);
      } catch (Exception var64) {
         Debug.printlnStatic(var64.toString());
         throw var64;
      }

      boardSettings.getOutType(1).setOutTypeFromBoard(decodedTypeOut2);
      byte[] codedTypeIn = new byte[2];

      for (int index = 0; index < 5; index++) {
         for (int i = 0; i < 2; i++) {
            codedTypeIn[i] = frame2parse[i + 27 + index * 2 - ideltaPsw];
         }

         byte decodedTypeIn;
         try {
            decodedTypeIn = FAAC_Protocol.intelHexDecoder(codedTypeIn);
         } catch (Exception var63) {
            Debug.printlnStatic(var63.toString());
            throw var63;
         }

         boardSettings.getInType(index).setInTypeFromBoard(decodedTypeIn);
      }

      byte[] codedTypeRadioDec = new byte[2];

      for (int index = 0; index < 2; index++) {
         for (int i = 0; i < 2; i++) {
            codedTypeRadioDec[i] = frame2parse[i + 37 + index * 2 - ideltaPsw];
         }

         byte decodedTypeRadioDec;
         try {
            decodedTypeRadioDec = FAAC_Protocol.intelHexDecoder(codedTypeRadioDec);
         } catch (Exception var62) {
            Debug.printlnStatic(var62.toString());
            throw var62;
         }

         boardSettings.getRadioDecType(index).setInTypeFromBoard(decodedTypeRadioDec);
      }

      byte[] codedTypeRadioXf = new byte[2];

      for (int index = 0; index < 2; index++) {
         for (int i = 0; i < 2; i++) {
            codedTypeRadioXf[i] = frame2parse[i + 41 + index * 2 - ideltaPsw];
         }

         byte decodedTypeRadioXf;
         try {
            decodedTypeRadioXf = FAAC_Protocol.intelHexDecoder(codedTypeRadioXf);
         } catch (Exception var61) {
            Debug.printlnStatic(var61.toString());
            throw var61;
         }

         boardSettings.getRadioXfType(index).setInTypeFromBoard(decodedTypeRadioXf);
      }

      byte[] codedSensOpen1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSensOpen1[i] = frame2parse[i + 45 - ideltaPsw];
      }

      byte decodedSensOpen1;
      try {
         decodedSensOpen1 = FAAC_Protocol.intelHexDecoder(codedSensOpen1);
      } catch (Exception var60) {
         Debug.printlnStatic(var60.toString());
         throw var60;
      }

      if (decodedSensOpen1 < 0) {
         boardSettings.setSensObstOpenAnta1_100msec(FAAC_Protocol.unsignedByteToInt(decodedSensOpen1));
      } else {
         boardSettings.setSensObstOpenAnta1_100msec(decodedSensOpen1);
      }

      byte[] codedSensOpen2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSensOpen2[i] = frame2parse[i + 47 - ideltaPsw];
      }

      byte decodedSensOpen2;
      try {
         decodedSensOpen2 = FAAC_Protocol.intelHexDecoder(codedSensOpen2);
      } catch (Exception var59) {
         Debug.printlnStatic(var59.toString());
         throw var59;
      }

      if (decodedSensOpen2 < 0) {
         boardSettings.setSensObstOpenAnta2_100msec(FAAC_Protocol.unsignedByteToInt(decodedSensOpen2));
      } else {
         boardSettings.setSensObstOpenAnta2_100msec(decodedSensOpen2);
      }

      byte[] codedSensClose1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSensClose1[i] = frame2parse[i + 49 - ideltaPsw];
      }

      byte decodedSensClose1;
      try {
         decodedSensClose1 = FAAC_Protocol.intelHexDecoder(codedSensClose1);
      } catch (Exception var58) {
         Debug.printlnStatic(var58.toString());
         throw var58;
      }

      if (decodedSensClose1 < 0) {
         boardSettings.setSensObstCloseAnta1_100msec(FAAC_Protocol.unsignedByteToInt(decodedSensClose1));
      } else {
         boardSettings.setSensObstCloseAnta1_100msec(decodedSensClose1);
      }

      byte[] codedSensClose2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSensClose2[i] = frame2parse[i + 51 - ideltaPsw];
      }

      byte decodedSensClose2;
      try {
         decodedSensClose2 = FAAC_Protocol.intelHexDecoder(codedSensClose2);
      } catch (Exception var57) {
         Debug.printlnStatic(var57.toString());
         throw var57;
      }

      if (decodedSensClose2 < 0) {
         boardSettings.setSensObstCloseAnta2_100msec(FAAC_Protocol.unsignedByteToInt(decodedSensClose2));
      } else {
         boardSettings.setSensObstCloseAnta2_100msec(decodedSensClose2);
      }

      byte[] codedSensRallOpen1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSensRallOpen1[i] = frame2parse[i + 53 - ideltaPsw];
      }

      byte decodedSensRallOpen1;
      try {
         decodedSensRallOpen1 = FAAC_Protocol.intelHexDecoder(codedSensRallOpen1);
      } catch (Exception var56) {
         Debug.printlnStatic(var56.toString());
         throw var56;
      }

      if (decodedSensRallOpen1 < 0) {
         boardSettings.setSensObstRallOpenAnta1_100msec(FAAC_Protocol.unsignedByteToInt(decodedSensRallOpen1));
      } else {
         boardSettings.setSensObstRallOpenAnta1_100msec(decodedSensRallOpen1);
      }

      byte[] codedSensRallOpen2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSensRallOpen2[i] = frame2parse[i + 55 - ideltaPsw];
      }

      byte decodedSensRallOpen2;
      try {
         decodedSensRallOpen2 = FAAC_Protocol.intelHexDecoder(codedSensRallOpen2);
      } catch (Exception var55) {
         Debug.printlnStatic(var55.toString());
         throw var55;
      }

      if (decodedSensRallOpen2 < 0) {
         boardSettings.setSensObstRallOpenAnta2_100msec(FAAC_Protocol.unsignedByteToInt(decodedSensRallOpen2));
      } else {
         boardSettings.setSensObstRallOpenAnta2_100msec(decodedSensRallOpen2);
      }

      byte[] codedSensRallClose1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSensRallClose1[i] = frame2parse[i + 57 - ideltaPsw];
      }

      byte decodedSensRallClose1;
      try {
         decodedSensRallClose1 = FAAC_Protocol.intelHexDecoder(codedSensRallClose1);
      } catch (Exception var54) {
         Debug.printlnStatic(var54.toString());
         throw var54;
      }

      if (decodedSensRallClose1 < 0) {
         boardSettings.setSensObstRallCloseAnta1_100msec(FAAC_Protocol.unsignedByteToInt(decodedSensRallClose1));
      } else {
         boardSettings.setSensObstRallCloseAnta1_100msec(decodedSensRallClose1);
      }

      byte[] codedSensRallClose2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSensRallClose2[i] = frame2parse[i + 59 - ideltaPsw];
      }

      byte decodedSensRallClose2;
      try {
         decodedSensRallClose2 = FAAC_Protocol.intelHexDecoder(codedSensRallClose2);
      } catch (Exception var53) {
         Debug.printlnStatic(var53.toString());
         throw var53;
      }

      if (decodedSensRallClose2 < 0) {
         boardSettings.setSensObstRallCloseAnta2_100msec(FAAC_Protocol.unsignedByteToInt(decodedSensRallClose2));
      } else {
         boardSettings.setSensObstRallCloseAnta2_100msec(decodedSensRallClose2);
      }

      byte[] codedTimeAdd = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimeAdd[i] = frame2parse[i + 61 - ideltaPsw];
      }

      byte decodedTimeAdd;
      try {
         decodedTimeAdd = FAAC_Protocol.intelHexDecoder(codedTimeAdd);
      } catch (Exception var52) {
         Debug.printlnStatic(var52.toString());
         throw var52;
      }

      boardSettings.setTimeAddAnta1_sec(decodedTimeAdd);
      byte[] codedByte31 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedByte31[i] = frame2parse[i + 65 - ideltaPsw];
      }

      byte decodedByte31;
      try {
         decodedByte31 = FAAC_Protocol.intelHexDecoder(codedByte31);
      } catch (Exception var51) {
         Debug.printlnStatic(var51.toString());
         throw var51;
      }

      boardSettings.setProg4_Byte31(decodedByte31);
      byte[] codedByte32 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedByte32[i] = frame2parse[i + 67 - ideltaPsw];
      }

      byte decodedByte32;
      try {
         decodedByte32 = FAAC_Protocol.intelHexDecoder(codedByte32);
      } catch (Exception var50) {
         Debug.printlnStatic(var50.toString());
         throw var50;
      }

      boardSettings.setProg4_Byte32(decodedByte32);
      byte[] codedSoftStart1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSoftStart1[i] = frame2parse[i + 69 - ideltaPsw];
      }

      byte decodedSoftStart1;
      try {
         decodedSoftStart1 = FAAC_Protocol.intelHexDecoder(codedSoftStart1);
      } catch (Exception var49) {
         Debug.printlnStatic(var49.toString());
         throw var49;
      }

      boardSettings.setSoftstart1(decodedSoftStart1);
      byte[] codedSoftStart2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSoftStart2[i] = frame2parse[i + 71 - ideltaPsw];
      }

      byte decodedSoftStart2;
      try {
         decodedSoftStart2 = FAAC_Protocol.intelHexDecoder(codedSoftStart2);
      } catch (Exception var48) {
         Debug.printlnStatic(var48.toString());
         throw var48;
      }

      boardSettings.setSoftstart2(decodedSoftStart2);
      byte[] codedNbObst = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedNbObst[i] = frame2parse[i + 73 - ideltaPsw];
      }

      byte decodedNbObst;
      try {
         decodedNbObst = FAAC_Protocol.intelHexDecoder(codedNbObst);
      } catch (Exception var47) {
         Debug.printlnStatic(var47.toString());
         throw var47;
      }

      boardSettings.setNumObstCons(decodedNbObst);
   }

   public static void parseFrame0x9C(byte[] frame2parse, FAAC_Settings boardSettings, String boardModel) throws Exception {
      int ideltaPsw = 8;
      if (boardModel.compareToIgnoreCase("E124") == 0) {
         byte[] codedSpeedOpen1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedSpeedOpen1[i] = frame2parse[i + 15 - ideltaPsw];
         }

         byte decodedSpeedOpen1;
         try {
            decodedSpeedOpen1 = FAAC_Protocol.intelHexDecoder(codedSpeedOpen1);
         } catch (Exception var56) {
            Debug.printlnStatic(var56.toString());
            throw var56;
         }

         ((E124_Settings)boardSettings).setSpeedOpen1(decodedSpeedOpen1);
         byte[] codedSpeedOpen2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedSpeedOpen2[i] = frame2parse[i + 17 - ideltaPsw];
         }

         byte decodedSpeedOpen2;
         try {
            decodedSpeedOpen2 = FAAC_Protocol.intelHexDecoder(codedSpeedOpen2);
         } catch (Exception var55) {
            Debug.printlnStatic(var55.toString());
            throw var55;
         }

         ((E124_Settings)boardSettings).setSpeedOpen2(decodedSpeedOpen2);
         byte[] codedSpeedClose1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedSpeedClose1[i] = frame2parse[i + 19 - ideltaPsw];
         }

         byte decodedSpeedClose1;
         try {
            decodedSpeedClose1 = FAAC_Protocol.intelHexDecoder(codedSpeedClose1);
         } catch (Exception var54) {
            Debug.printlnStatic(var54.toString());
            throw var54;
         }

         ((E124_Settings)boardSettings).setSpeedClose1(decodedSpeedClose1);
         byte[] codedSpeedClose2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedSpeedClose2[i] = frame2parse[i + 21 - ideltaPsw];
         }

         byte decodedSpeedClose2;
         try {
            decodedSpeedClose2 = FAAC_Protocol.intelHexDecoder(codedSpeedClose2);
         } catch (Exception var53) {
            Debug.printlnStatic(var53.toString());
            throw var53;
         }

         ((E124_Settings)boardSettings).setSpeedClose2(decodedSpeedClose2);
         byte[] codedUltrasense1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedUltrasense1[i] = frame2parse[i + 31 - ideltaPsw];
         }

         byte decodedUltrasense1;
         try {
            decodedUltrasense1 = FAAC_Protocol.intelHexDecoder(codedUltrasense1);
         } catch (Exception var52) {
            Debug.printlnStatic(var52.toString());
            throw var52;
         }

         ((E124_Settings)boardSettings).setUltrasense1(decodedUltrasense1);
         byte[] codedUltrasense2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedUltrasense2[i] = frame2parse[i + 33 - ideltaPsw];
         }

         byte decodedUltrasense2;
         try {
            decodedUltrasense2 = FAAC_Protocol.intelHexDecoder(codedUltrasense2);
         } catch (Exception var51) {
            Debug.printlnStatic(var51.toString());
            throw var51;
         }

         ((E124_Settings)boardSettings).setUltrasense2(decodedUltrasense2);
         byte[] codedSpeedRall1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedSpeedRall1[i] = frame2parse[i + 35 - ideltaPsw];
         }

         byte decodedSpeedRall1;
         try {
            decodedSpeedRall1 = FAAC_Protocol.intelHexDecoder(codedSpeedRall1);
         } catch (Exception var50) {
            Debug.printlnStatic(var50.toString());
            throw var50;
         }

         ((E124_Settings)boardSettings).setSpeedRall1(decodedSpeedRall1);
         byte[] codedSpeedRall2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedSpeedRall2[i] = frame2parse[i + 37 - ideltaPsw];
         }

         byte decodedSpeedRall2;
         try {
            decodedSpeedRall2 = FAAC_Protocol.intelHexDecoder(codedSpeedRall2);
         } catch (Exception var49) {
            Debug.printlnStatic(var49.toString());
            throw var49;
         }

         ((E124_Settings)boardSettings).setSpeedRall2(decodedSpeedRall2);
         byte[] codedTimeSoftTouch1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedTimeSoftTouch1[i] = frame2parse[i + 39 - ideltaPsw];
         }

         byte decodedTimeSoftTouch1;
         try {
            decodedTimeSoftTouch1 = FAAC_Protocol.intelHexDecoder(codedTimeSoftTouch1);
         } catch (Exception var48) {
            Debug.printlnStatic(var48.toString());
            throw var48;
         }

         ((E124_Settings)boardSettings).setTimeSoftTouchAnta1_100msec(decodedTimeSoftTouch1);
         byte[] codedTimeSoftTouch2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedTimeSoftTouch2[i] = frame2parse[i + 41 - ideltaPsw];
         }

         byte decodedTimeSoftTouch2;
         try {
            decodedTimeSoftTouch2 = FAAC_Protocol.intelHexDecoder(codedTimeSoftTouch2);
         } catch (Exception var47) {
            Debug.printlnStatic(var47.toString());
            throw var47;
         }

         ((E124_Settings)boardSettings).setTimeSoftTouchAnta2_100msec(decodedTimeSoftTouch2);
         byte[] codedTypeSoftTouch1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedTypeSoftTouch1[i] = frame2parse[i + 43 - ideltaPsw];
         }

         byte decodedTypeSoftTouch1;
         try {
            decodedTypeSoftTouch1 = FAAC_Protocol.intelHexDecoder(codedTypeSoftTouch1);
         } catch (Exception var46) {
            Debug.printlnStatic(var46.toString());
            throw var46;
         }

         ((E124_Settings)boardSettings).setTypeSoftTouchAnta1(decodedTypeSoftTouch1);
         byte[] codedTypeSoftTouch2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedTypeSoftTouch2[i] = frame2parse[i + 45 - ideltaPsw];
         }

         byte decodedTypeSoftTouch2;
         try {
            decodedTypeSoftTouch2 = FAAC_Protocol.intelHexDecoder(codedTypeSoftTouch2);
         } catch (Exception var45) {
            Debug.printlnStatic(var45.toString());
            throw var45;
         }

         ((E124_Settings)boardSettings).setTypeSoftTouchAnta2(decodedTypeSoftTouch2);
         byte[] codedForceColpo1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedForceColpo1[i] = frame2parse[i + 47 - ideltaPsw];
         }

         byte decodedForceColpo1;
         try {
            decodedForceColpo1 = FAAC_Protocol.intelHexDecoder(codedForceColpo1);
         } catch (Exception var44) {
            Debug.printlnStatic(var44.toString());
            throw var44;
         }

         ((E124_Settings)boardSettings).setColpoForce1(decodedForceColpo1);
         byte[] codedForceColpo2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedForceColpo2[i] = frame2parse[i + 49 - ideltaPsw];
         }

         byte decodedForceColpo2;
         try {
            decodedForceColpo2 = FAAC_Protocol.intelHexDecoder(codedForceColpo2);
         } catch (Exception var43) {
            Debug.printlnStatic(var43.toString());
            throw var43;
         }

         ((E124_Settings)boardSettings).setColpoForce2(decodedForceColpo2);
         byte[] codedForceRevStr1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedForceRevStr1[i] = frame2parse[i + 51 - ideltaPsw];
         }

         byte decodedForceRevStr1;
         try {
            decodedForceRevStr1 = FAAC_Protocol.intelHexDecoder(codedForceRevStr1);
         } catch (Exception var42) {
            Debug.printlnStatic(var42.toString());
            throw var42;
         }

         ((E124_Settings)boardSettings).setRevStrForce1(decodedForceRevStr1);
         byte[] codedForceRevStr2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedForceRevStr2[i] = frame2parse[i + 53 - ideltaPsw];
         }

         byte decodedForceRevStr2;
         try {
            decodedForceRevStr2 = FAAC_Protocol.intelHexDecoder(codedForceRevStr2);
         } catch (Exception var41) {
            Debug.printlnStatic(var41.toString());
            throw var41;
         }

         ((E124_Settings)boardSettings).setRevStrForce2(decodedForceRevStr2);
      }

      if (boardModel.compareToIgnoreCase("E145") == 0) {
         byte[] codedAddTimeOpen1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedAddTimeOpen1[i] = frame2parse[i + 23 - ideltaPsw];
         }

         byte decodedAddTimeOpen1;
         try {
            decodedAddTimeOpen1 = FAAC_Protocol.intelHexDecoder(codedAddTimeOpen1);
         } catch (Exception var40) {
            Debug.printlnStatic(var40.toString());
            throw var40;
         }

         ((E145_Settings)boardSettings).setAddTimeOpen1(decodedAddTimeOpen1);
         byte[] codedAddTimeOpen2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedAddTimeOpen2[i] = frame2parse[i + 25 - ideltaPsw];
         }

         byte decodedAddTimeOpen2;
         try {
            decodedAddTimeOpen2 = FAAC_Protocol.intelHexDecoder(codedAddTimeOpen2);
         } catch (Exception var39) {
            Debug.printlnStatic(var39.toString());
            throw var39;
         }

         ((E145_Settings)boardSettings).setAddTimeOpen2(decodedAddTimeOpen2);
         byte[] codedAddTimeClose1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedAddTimeClose1[i] = frame2parse[i + 27 - ideltaPsw];
         }

         byte decodedAddTimeClose1;
         try {
            decodedAddTimeClose1 = FAAC_Protocol.intelHexDecoder(codedAddTimeClose1);
         } catch (Exception var38) {
            Debug.printlnStatic(var38.toString());
            throw var38;
         }

         ((E145_Settings)boardSettings).setAddTimeClose1(decodedAddTimeClose1);
         byte[] codedAddTimeClose2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedAddTimeClose2[i] = frame2parse[i + 29 - ideltaPsw];
         }

         byte decodedAddTimeClose2;
         try {
            decodedAddTimeClose2 = FAAC_Protocol.intelHexDecoder(codedAddTimeClose2);
         } catch (Exception var37) {
            Debug.printlnStatic(var37.toString());
            throw var37;
         }

         ((E145_Settings)boardSettings).setAddTimeClose2(decodedAddTimeClose2);
      }
   }

   public static void parseFrame0x86(byte[] frame2parse, FAAC_Monitor boardMonitor) throws Exception {
      byte[] codedVacc = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedVacc[i] = frame2parse[i + 15];
      }

      byte[] decodedVacc;
      try {
         decodedVacc = FAAC_Protocol.intelHexDecoderDataFlow(codedVacc);
      } catch (Exception var69) {
         Debug.printlnStatic(var69.toString());
         throw var69;
      }

      int vAcc = FAAC_Protocol.byteArrayToInt(decodedVacc);
      boardMonitor.setVoltageAcc_10mV(vAcc);
      byte[] codedErrorField = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedErrorField[i] = frame2parse[i + 19];
      }

      byte[] decodedErrorField = new byte[4];

      try {
         decodedErrorField = FAAC_Protocol.intelHexDecoderDataFlow(codedErrorField);
      } catch (Exception var68) {
         Debug.printlnStatic("ParseFrame0x86 errori" + var68.toString());
         throw var68;
      }

      boardMonitor.getError().setBytes_MSBfirst(decodedErrorField);
      byte[] codedAllarmField = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedAllarmField[i] = frame2parse[i + 27];
      }

      byte[] decodedAllarmField = new byte[4];

      try {
         decodedAllarmField = FAAC_Protocol.intelHexDecoderDataFlow(codedAllarmField);
      } catch (Exception var67) {
         Debug.printlnStatic("ParseFrame0x86 alalrmi " + var67.toString());
         throw var67;
      }

      boardMonitor.getAllarm().setBytes_MSBfirst(decodedAllarmField);
      byte[] codedPosMotor1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedPosMotor1[i] = frame2parse[i + 35];
      }

      byte decodedPosMotor1;
      try {
         decodedPosMotor1 = FAAC_Protocol.intelHexDecoder(codedPosMotor1);
      } catch (Exception var66) {
         Debug.printlnStatic(var66.toString());
         throw var66;
      }

      boardMonitor.setPositionMotor1_percentage(decodedPosMotor1);
      byte[] codedPosMotor2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedPosMotor2[i] = frame2parse[i + 37];
      }

      byte decodedPosMotor2;
      try {
         decodedPosMotor2 = FAAC_Protocol.intelHexDecoder(codedPosMotor2);
      } catch (Exception var65) {
         Debug.printlnStatic(var65.toString());
         throw var65;
      }

      boardMonitor.setPositionMotor2_percentage(decodedPosMotor2);
      byte[] codedPhysInsOuts = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedPhysInsOuts[i] = frame2parse[i + 39];
      }

      byte[] decodedPhysInsOuts;
      try {
         decodedPhysInsOuts = FAAC_Protocol.intelHexDecoderDataFlow(codedPhysInsOuts);
      } catch (Exception var64) {
         Debug.printlnStatic(var64.toString());
         throw var64;
      }

      boardMonitor.getPhysicalIns().setBytes_MSBfirst(decodedPhysInsOuts);
      byte[] codedDatBusAttivi = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedDatBusAttivi[i] = frame2parse[i + 47];
      }

      byte[] decodedDatBusAttivi;
      try {
         decodedDatBusAttivi = FAAC_Protocol.intelHexDecoderDataFlow(codedDatBusAttivi);
      } catch (Exception var63) {
         Debug.printlnStatic(var63.toString());
         throw var63;
      }

      boardMonitor.getActiveDat2easy().setBytes_MSBfirst(decodedDatBusAttivi);
      byte[] codedCountdownPause = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedCountdownPause[i] = frame2parse[i + 51];
      }

      byte decodedCountdownPause;
      try {
         decodedCountdownPause = FAAC_Protocol.intelHexDecoder(codedCountdownPause);
      } catch (Exception var62) {
         Debug.printlnStatic(var62.toString());
         throw var62;
      }

      if (decodedCountdownPause < 0) {
         boardMonitor.setTimeCountDownPause_sec(FAAC_Protocol.unsignedByteToInt(decodedCountdownPause));
      } else {
         boardMonitor.setTimeCountDownPause_sec(decodedCountdownPause);
      }

      byte[] codedIscrBus = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedIscrBus[i] = frame2parse[i + 55];
      }

      byte[] decodedIscrBus;
      try {
         decodedIscrBus = FAAC_Protocol.intelHexDecoderDataFlow(codedIscrBus);
      } catch (Exception var61) {
         Debug.printlnStatic(var61.toString());
         throw var61;
      }

      boardMonitor.getSubscribed2easy().setBytes_MSBfirst(decodedIscrBus);
      byte[] codedLogicIns = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedLogicIns[i] = frame2parse[i + 63];
      }

      byte[] decodedLogicIns;
      try {
         decodedLogicIns = FAAC_Protocol.intelHexDecoderDataFlow(codedLogicIns);
      } catch (Exception var60) {
         Debug.printlnStatic(var60.toString());
         throw var60;
      }

      boardMonitor.getLogicIns().setBytes_MSBfirst(decodedLogicIns);
      byte[] codedSicuBusAttive = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedSicuBusAttive[i] = frame2parse[i + 71];
      }

      byte[] decodedSicuBusAttive;
      try {
         decodedSicuBusAttive = FAAC_Protocol.intelHexDecoderDataFlow(codedSicuBusAttive);
      } catch (Exception var59) {
         Debug.printlnStatic(var59.toString());
         throw var59;
      }

      boardMonitor.getActiveSec2easy().setBytes_MSBfirst(decodedSicuBusAttive);
      byte[] codedAllarmBisField = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedAllarmBisField[i] = frame2parse[i + 75];
      }

      byte[] decodedAllarmBisField;
      try {
         decodedAllarmBisField = FAAC_Protocol.intelHexDecoderDataFlow(codedAllarmBisField);
      } catch (Exception var58) {
         Debug.printlnStatic("ParseFrame0x86 allarmi 2" + var58.toString());
         throw var58;
      }

      boardMonitor.getAllarmBis().setBytes_MSBfirst(decodedAllarmBisField);
      byte[] codedSegnVarie = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSegnVarie[i] = frame2parse[i + 79];
      }

      byte decodedSegnVarie;
      try {
         decodedSegnVarie = FAAC_Protocol.intelHexDecoder(codedSegnVarie);
      } catch (Exception var57) {
         Debug.printlnStatic(var57.toString());
         throw var57;
      }

      boardMonitor.getLedState().setByte(decodedSegnVarie);
      byte[] codedNbPartialCycles = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedNbPartialCycles[i] = frame2parse[i + 83];
      }

      byte[] decodedNbPartialCycles;
      try {
         decodedNbPartialCycles = FAAC_Protocol.intelHexDecoderDataFlow(codedNbPartialCycles);
      } catch (Exception var56) {
         Debug.printlnStatic(var56.toString());
         throw var56;
      }

      int iNbPartial = FAAC_Protocol.byteArrayToInt(decodedNbPartialCycles);
      boardMonitor.setNbCyclesPartial(iNbPartial);
      byte[] codedStatoLogico = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedStatoLogico[i] = frame2parse[i + 87];
      }

      byte decodedStatoLogico;
      try {
         decodedStatoLogico = FAAC_Protocol.intelHexDecoder(codedStatoLogico);
      } catch (Exception var55) {
         Debug.printlnStatic(var55.toString());
         throw var55;
      }

      boardMonitor.setState(decodedStatoLogico);
      byte[] codedMinutes = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMinutes[i] = frame2parse[i + 89];
      }

      byte decodedMinutes;
      try {
         decodedMinutes = FAAC_Protocol.intelHexDecoder(codedMinutes);
      } catch (Exception var54) {
         Debug.printlnStatic(var54.toString());
         throw var54;
      }

      boardMonitor.setMinutes(FAAC_Protocol.byteToInt_BCDformat(decodedMinutes));
      byte[] codedHour = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedHour[i] = frame2parse[i + 91];
      }

      byte decodedHour;
      try {
         decodedHour = FAAC_Protocol.intelHexDecoder(codedHour);
      } catch (Exception var53) {
         Debug.printlnStatic(var53.toString());
         throw var53;
      }

      boardMonitor.setHour(FAAC_Protocol.byteToInt_BCDformat(decodedHour));
      byte[] codedDay = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedDay[i] = frame2parse[i + 93];
      }

      byte decodedDay;
      try {
         decodedDay = FAAC_Protocol.intelHexDecoder(codedDay);
      } catch (Exception var52) {
         Debug.printlnStatic(var52.toString());
         throw var52;
      }

      boardMonitor.setDay(FAAC_Protocol.byteToInt_BCDformat(decodedDay));
      byte[] codedDayWeek = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedDayWeek[i] = frame2parse[i + 95];
      }

      byte decodedDayWeek;
      try {
         decodedDayWeek = FAAC_Protocol.intelHexDecoder(codedDayWeek);
      } catch (Exception var51) {
         Debug.printlnStatic(var51.toString());
         throw var51;
      }

      boardMonitor.setDayOfTheWeek(FAAC_Protocol.byteToInt_BCDformat(decodedDayWeek));
      byte[] codedMonth = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMonth[i] = frame2parse[i + 97];
      }

      byte decodedMonth;
      try {
         decodedMonth = FAAC_Protocol.intelHexDecoder(codedMonth);
      } catch (Exception var50) {
         Debug.printlnStatic(var50.toString());
         throw var50;
      }

      boardMonitor.setMonth(FAAC_Protocol.byteToInt_BCDformat(decodedMonth));
      byte[] codedYear = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedYear[i] = frame2parse[i + 99];
      }

      byte decodedYear;
      try {
         decodedYear = FAAC_Protocol.intelHexDecoder(codedYear);
      } catch (Exception var49) {
         Debug.printlnStatic(var49.toString());
         throw var49;
      }

      boardMonitor.setYear(FAAC_Protocol.byteToInt_BCDformat(decodedYear));
   }

   public static void parseFrame0x77(byte[] frame2parse, FAAC_Monitor boardMonitor) throws Exception {
      byte[] codedVmot = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedVmot[i] = frame2parse[i + 7];
      }

      byte[] decodedVmot;
      try {
         decodedVmot = FAAC_Protocol.intelHexDecoderDataFlow(codedVmot);
      } catch (Exception var27) {
         Debug.printlnStatic(var27.toString());
         throw var27;
      }

      int vMot = FAAC_Protocol.byteArrayToInt(decodedVmot);
      boardMonitor.setVoltageMot_10mV(vMot);
      byte[] codedVbat = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedVbat[i] = frame2parse[i + 11];
      }

      byte[] decodedVbat;
      try {
         decodedVbat = FAAC_Protocol.intelHexDecoderDataFlow(codedVbat);
      } catch (Exception var26) {
         Debug.printlnStatic(var26.toString());
         throw var26;
      }

      int vBat = FAAC_Protocol.byteArrayToInt(decodedVbat);
      boardMonitor.setVoltageBat_10mV(vBat);
      byte[] codedImot1 = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedImot1[i] = frame2parse[i + 15];
      }

      byte[] decodedImot1;
      try {
         decodedImot1 = FAAC_Protocol.intelHexDecoderDataFlow(codedImot1);
      } catch (Exception var25) {
         Debug.printlnStatic(var25.toString());
         throw var25;
      }

      int iMot1 = FAAC_Protocol.byteArrayToInt(decodedImot1);
      boardMonitor.setCurrentMot1_10mA(iMot1);
      byte[] codedImot2 = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedImot2[i] = frame2parse[i + 19];
      }

      byte[] decodedImot2;
      try {
         decodedImot2 = FAAC_Protocol.intelHexDecoderDataFlow(codedImot2);
      } catch (Exception var24) {
         Debug.printlnStatic(var24.toString());
         throw var24;
      }

      int iMot2 = FAAC_Protocol.byteArrayToInt(decodedImot2);
      boardMonitor.setCurrentMot2_10mA(iMot2);
      byte[] codedSpeed1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpeed1[i] = frame2parse[i + 23];
      }

      byte decodedSpeed1;
      try {
         decodedSpeed1 = FAAC_Protocol.intelHexDecoder(codedSpeed1);
      } catch (Exception var23) {
         Debug.printlnStatic(var23.toString());
         throw var23;
      }

      boardMonitor.setSpeedMot1(decodedSpeed1);
      byte[] codedSpeed2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSpeed2[i] = frame2parse[i + 25];
      }

      byte decodedSpeed2;
      try {
         decodedSpeed2 = FAAC_Protocol.intelHexDecoder(codedSpeed2);
      } catch (Exception var22) {
         Debug.printlnStatic(var22.toString());
         throw var22;
      }

      boardMonitor.setSpeedMot2(decodedSpeed2);
      byte[] codedBattery = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedBattery[i] = frame2parse[i + 27];
      }

      byte decodedBattery;
      try {
         decodedBattery = FAAC_Protocol.intelHexDecoder(codedBattery);
      } catch (Exception var21) {
         Debug.printlnStatic(var21.toString());
         throw var21;
      }

      boardMonitor.getBattery().setByte(decodedBattery);
   }

   public static void parseFrame0x88(byte[] frame2parse, FAAC_Monitor boardMonitor, FAAC_Logs boardLogs, FAAC_Remote boardRemote) throws Exception {
      byte[] codedBoardType = new byte[16];

      for (int i = 0; i < 16; i++) {
         codedBoardType[i] = frame2parse[i + 7];
      }

      byte[] decodedBoardType = new byte[8];

      try {
         decodedBoardType = FAAC_Protocol.intelHexDecoderDataFlow(codedBoardType);
      } catch (Exception var32) {
         Debug.printlnStatic(var32.toString());
         throw var32;
      }

      for (int i = 0; i < 8; i++) {
         if (decodedBoardType[i] < 0) {
            decodedBoardType[i] = 32;
         }
      }

      String boardType = new String(decodedBoardType).trim();
      boardMonitor.setBoardType(boardType);
      byte[] codedSerialId = new byte[20];

      for (int ix = 0; ix < 20; ix++) {
         codedSerialId[ix] = frame2parse[ix + 23];
      }

      byte[] decodedSerialId = new byte[10];

      try {
         decodedSerialId = FAAC_Protocol.intelHexDecoderDataFlow(codedSerialId);
      } catch (Exception var31) {
         Debug.printlnStatic(var31.toString());
         throw var31;
      }

      String boardSerialId = new String(decodedSerialId);
      boardMonitor.setBoardSerialId(boardSerialId);
      byte[] codedVersionMem = new byte[2];

      for (int ix = 0; ix < 2; ix++) {
         codedVersionMem[ix] = frame2parse[ix + 43];
      }

      byte decodedVersionMem;
      try {
         decodedVersionMem = FAAC_Protocol.intelHexDecoder(codedVersionMem);
      } catch (Exception var30) {
         Debug.printlnStatic(var30.toString());
         throw var30;
      }

      boardMonitor.setVersionMem(decodedVersionMem);
      Debug.printlnStatic("Read from 0x88 the following version of memory: " + FAAC_Protocol.byteToHexString(decodedVersionMem));
      if (decodedVersionMem == 65) {
         boardLogs.setNbLogsOnBoard(128);
         boardRemote.setNbRadioCode(256);
      } else if (decodedVersionMem == 66) {
         boardLogs.setNbLogsOnBoard(128);
         boardRemote.setNbRadioCode(768);
      }

      if (decodedVersionMem == 67) {
         boardLogs.setNbLogsOnBoard(352);
         boardRemote.setNbRadioCode(1600);
      } else if (decodedVersionMem == 48) {
         boardLogs.setNbLogsOnBoard(128);
         if (boardType.equals("E145")) {
            ((E145_Remote)boardRemote).setNbRadioCode(768);
         } else if (boardType.equals("E124")) {
            ((E124_Remote)boardRemote).setNbRadioCode(256);
         }
      }

      byte[] codedBoardSwVersion = new byte[4];

      for (int ix = 0; ix < 4; ix++) {
         codedBoardSwVersion[ix] = frame2parse[ix + 69];
      }

      byte[] decodedBoardSwVersion = new byte[2];

      try {
         decodedBoardSwVersion = FAAC_Protocol.intelHexDecoderDataFlow(codedBoardSwVersion);
      } catch (Exception var29) {
         Debug.printlnStatic(var29.toString());
         throw var29;
      }

      byte Sw1 = decodedBoardSwVersion[0];
      byte Sw2 = decodedBoardSwVersion[1];
      boardMonitor.setBoardSwVersion_SW1((char)Sw1);
      boardMonitor.setBoardSwVersion_SW2((char)Sw2);
      byte[] codedDay = new byte[2];

      for (int ix = 0; ix < 2; ix++) {
         codedDay[ix] = frame2parse[ix + 73];
      }

      byte decodedDay;
      try {
         decodedDay = FAAC_Protocol.intelHexDecoder(codedDay);
      } catch (Exception var28) {
         Debug.printlnStatic(var28.toString());
         throw var28;
      }

      boardMonitor.setLastFW_day(FAAC_Protocol.byteToInt_BCDformat(decodedDay));
      byte[] codedMonth = new byte[2];

      for (int ix = 0; ix < 2; ix++) {
         codedMonth[ix] = frame2parse[ix + 75];
      }

      byte decodedMonth;
      try {
         decodedMonth = FAAC_Protocol.intelHexDecoder(codedMonth);
      } catch (Exception var27) {
         Debug.printlnStatic(var27.toString());
         throw var27;
      }

      boardMonitor.setLastFW_month(FAAC_Protocol.byteToInt_BCDformat(decodedMonth));
      byte[] codedYear = new byte[2];

      for (int ix = 0; ix < 2; ix++) {
         codedYear[ix] = frame2parse[ix + 77];
      }

      byte decodedYear;
      try {
         decodedYear = FAAC_Protocol.intelHexDecoder(codedYear);
      } catch (Exception var26) {
         Debug.printlnStatic(var26.toString());
         throw var26;
      }

      boardMonitor.setLastFW_year(FAAC_Protocol.byteToInt_BCDformat(decodedYear));
      byte[] codedBoardNbCycleAbs = new byte[8];

      for (int ix = 0; ix < 8; ix++) {
         codedBoardNbCycleAbs[ix] = frame2parse[ix + 79];
      }

      byte[] decodedBoardNbCycleAbs = new byte[4];

      try {
         decodedBoardNbCycleAbs = FAAC_Protocol.intelHexDecoderDataFlow(codedBoardNbCycleAbs);
      } catch (Exception var25) {
         Debug.printlnStatic(var25.toString());
         throw var25;
      }

      int nbCycleAbs = FAAC_Protocol.byteArrayToInt(decodedBoardNbCycleAbs);
      boardMonitor.setNbCyclesAbsolute(nbCycleAbs);
   }

   public static void parseFrame0x8B(byte[] frame2parse, FAAC_Monitor boardMonitor) throws Exception {
      byte[] codedBusErr = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedBusErr[i] = frame2parse[i + 7];
      }

      byte[] decodedBusErr = new byte[4];

      try {
         decodedBusErr = FAAC_Protocol.intelHexDecoderDataFlow(codedBusErr);
      } catch (Exception var5) {
         Debug.printlnStatic(var5.toString());
         throw var5;
      }

      boardMonitor.getDevice2easyError().setBytes_MSBfirst(decodedBusErr);
   }

   public static void parseFrame0x8C(byte[] frame2parse, FAAC_NETCOM netcom) throws Exception {
      int iDeltaPsw = 8;
      byte[] codedIp4 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedIp4[i] = frame2parse[i - iDeltaPsw + 15];
      }

      byte decodedIp4;
      try {
         decodedIp4 = FAAC_Protocol.intelHexDecoder(codedIp4);
      } catch (Exception var46) {
         Debug.printlnStatic(var46.toString());
         throw var46;
      }

      netcom.setIP4(decodedIp4);
      byte[] codedIp3 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedIp3[i] = frame2parse[i - iDeltaPsw + 17];
      }

      byte decodedIp3;
      try {
         decodedIp3 = FAAC_Protocol.intelHexDecoder(codedIp3);
      } catch (Exception var45) {
         Debug.printlnStatic(var45.toString());
         throw var45;
      }

      netcom.setIP3(decodedIp3);
      byte[] codedIp2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedIp2[i] = frame2parse[i - iDeltaPsw + 19];
      }

      byte decodedIp2;
      try {
         decodedIp2 = FAAC_Protocol.intelHexDecoder(codedIp2);
      } catch (Exception var44) {
         Debug.printlnStatic(var44.toString());
         throw var44;
      }

      netcom.setIP2(decodedIp2);
      byte[] codedIp1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedIp1[i] = frame2parse[i - iDeltaPsw + 21];
      }

      byte decodedIp1;
      try {
         decodedIp1 = FAAC_Protocol.intelHexDecoder(codedIp1);
      } catch (Exception var43) {
         Debug.printlnStatic(var43.toString());
         throw var43;
      }

      netcom.setIP1(decodedIp1);
      byte[] codedMaskTmp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMaskTmp[i] = frame2parse[i - iDeltaPsw + 23];
      }

      byte decodedMaskTmp;
      try {
         decodedMaskTmp = FAAC_Protocol.intelHexDecoder(codedMaskTmp);
      } catch (Exception var42) {
         Debug.printlnStatic(var42.toString());
         throw var42;
      }

      netcom.setMASK4(decodedMaskTmp);

      for (int i = 0; i < 2; i++) {
         codedMaskTmp[i] = frame2parse[i - iDeltaPsw + 25];
      }

      try {
         decodedMaskTmp = FAAC_Protocol.intelHexDecoder(codedMaskTmp);
      } catch (Exception var41) {
         Debug.printlnStatic(var41.toString());
         throw var41;
      }

      netcom.setMASK3(decodedMaskTmp);

      for (int i = 0; i < 2; i++) {
         codedMaskTmp[i] = frame2parse[i - iDeltaPsw + 27];
      }

      try {
         decodedMaskTmp = FAAC_Protocol.intelHexDecoder(codedMaskTmp);
      } catch (Exception var40) {
         Debug.printlnStatic(var40.toString());
         throw var40;
      }

      netcom.setMASK2(decodedMaskTmp);

      for (int i = 0; i < 2; i++) {
         codedMaskTmp[i] = frame2parse[i - iDeltaPsw + 29];
      }

      try {
         decodedMaskTmp = FAAC_Protocol.intelHexDecoder(codedMaskTmp);
      } catch (Exception var39) {
         Debug.printlnStatic(var39.toString());
         throw var39;
      }

      netcom.setMASK1(decodedMaskTmp);
      byte[] codedGwTmp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedGwTmp[i] = frame2parse[i - iDeltaPsw + 31];
      }

      byte decodedGwTmp;
      try {
         decodedGwTmp = FAAC_Protocol.intelHexDecoder(codedGwTmp);
      } catch (Exception var38) {
         Debug.printlnStatic(var38.toString());
         throw var38;
      }

      netcom.setGW4(decodedGwTmp);

      for (int i = 0; i < 2; i++) {
         codedGwTmp[i] = frame2parse[i - iDeltaPsw + 33];
      }

      try {
         decodedGwTmp = FAAC_Protocol.intelHexDecoder(codedGwTmp);
      } catch (Exception var37) {
         Debug.printlnStatic(var37.toString());
         throw var37;
      }

      netcom.setGW3(decodedGwTmp);

      for (int i = 0; i < 2; i++) {
         codedGwTmp[i] = frame2parse[i - iDeltaPsw + 35];
      }

      try {
         decodedGwTmp = FAAC_Protocol.intelHexDecoder(codedGwTmp);
      } catch (Exception var36) {
         Debug.printlnStatic(var36.toString());
         throw var36;
      }

      netcom.setGW2(decodedGwTmp);

      for (int i = 0; i < 2; i++) {
         codedGwTmp[i] = frame2parse[i - iDeltaPsw + 37];
      }

      try {
         decodedGwTmp = FAAC_Protocol.intelHexDecoder(codedGwTmp);
      } catch (Exception var35) {
         Debug.printlnStatic(var35.toString());
         throw var35;
      }

      netcom.setGW1(decodedGwTmp);
      byte[] codedPrimDNSTmp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedPrimDNSTmp[i] = frame2parse[i - iDeltaPsw + 39];
      }

      byte decodedPrimDNSTmp;
      try {
         decodedPrimDNSTmp = FAAC_Protocol.intelHexDecoder(codedPrimDNSTmp);
      } catch (Exception var34) {
         Debug.printlnStatic(var34.toString());
         throw var34;
      }

      netcom.setPrimDNS4(decodedPrimDNSTmp);

      for (int i = 0; i < 2; i++) {
         codedPrimDNSTmp[i] = frame2parse[i - iDeltaPsw + 41];
      }

      try {
         decodedPrimDNSTmp = FAAC_Protocol.intelHexDecoder(codedPrimDNSTmp);
      } catch (Exception var33) {
         Debug.printlnStatic(var33.toString());
         throw var33;
      }

      netcom.setPrimDNS3(decodedPrimDNSTmp);

      for (int i = 0; i < 2; i++) {
         codedPrimDNSTmp[i] = frame2parse[i - iDeltaPsw + 43];
      }

      try {
         decodedPrimDNSTmp = FAAC_Protocol.intelHexDecoder(codedPrimDNSTmp);
      } catch (Exception var32) {
         Debug.printlnStatic(var32.toString());
         throw var32;
      }

      netcom.setPrimDNS2(decodedPrimDNSTmp);

      for (int i = 0; i < 2; i++) {
         codedPrimDNSTmp[i] = frame2parse[i - iDeltaPsw + 45];
      }

      try {
         decodedPrimDNSTmp = FAAC_Protocol.intelHexDecoder(codedPrimDNSTmp);
      } catch (Exception var31) {
         Debug.printlnStatic(var31.toString());
         throw var31;
      }

      netcom.setPrimDNS1(decodedPrimDNSTmp);
      byte[] codedSecDNSTmp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedSecDNSTmp[i] = frame2parse[i - iDeltaPsw + 47];
      }

      byte decodedSecDNSTmp;
      try {
         decodedSecDNSTmp = FAAC_Protocol.intelHexDecoder(codedSecDNSTmp);
      } catch (Exception var30) {
         Debug.printlnStatic(var30.toString());
         throw var30;
      }

      netcom.setSecDNS4(decodedSecDNSTmp);

      for (int i = 0; i < 2; i++) {
         codedSecDNSTmp[i] = frame2parse[i - iDeltaPsw + 49];
      }

      try {
         decodedSecDNSTmp = FAAC_Protocol.intelHexDecoder(codedSecDNSTmp);
      } catch (Exception var29) {
         Debug.printlnStatic(var29.toString());
         throw var29;
      }

      netcom.setSecDNS3(decodedSecDNSTmp);

      for (int i = 0; i < 2; i++) {
         codedSecDNSTmp[i] = frame2parse[i - iDeltaPsw + 51];
      }

      try {
         decodedSecDNSTmp = FAAC_Protocol.intelHexDecoder(codedSecDNSTmp);
      } catch (Exception var28) {
         Debug.printlnStatic(var28.toString());
         throw var28;
      }

      netcom.setSecDNS2(decodedSecDNSTmp);

      for (int i = 0; i < 2; i++) {
         codedSecDNSTmp[i] = frame2parse[i - iDeltaPsw + 53];
      }

      try {
         decodedSecDNSTmp = FAAC_Protocol.intelHexDecoder(codedSecDNSTmp);
      } catch (Exception var27) {
         Debug.printlnStatic(var27.toString());
         throw var27;
      }

      netcom.setSecDNS1(decodedSecDNSTmp);
      byte[] codedHostName = new byte[32];

      for (int i = 0; i < 32; i++) {
         codedHostName[i] = frame2parse[i - iDeltaPsw + 55];
      }

      byte[] decodedHostName;
      try {
         decodedHostName = FAAC_Protocol.intelHexDecoderDataFlow(codedHostName);
      } catch (Exception var26) {
         Debug.printlnStatic(var26.toString());
         throw var26;
      }

      int zerofinalcharCounter = 0;

      for (int i = 0; i < 16 && decodedHostName[15 - i] == 0; i++) {
         zerofinalcharCounter++;
      }

      if (zerofinalcharCounter < 16) {
         netcom.setHostName(new String(decodedHostName).substring(0, 16 - zerofinalcharCounter));
      } else {
         netcom.setHostName("");
      }

      byte[] codeddhcp = new byte[2];

      for (int i = 0; i < 2; i++) {
         codeddhcp[i] = frame2parse[i - iDeltaPsw + 87];
      }

      byte decodeddhcp;
      try {
         decodeddhcp = FAAC_Protocol.intelHexDecoder(codeddhcp);
      } catch (Exception var25) {
         Debug.printlnStatic(var25.toString());
         throw var25;
      }

      if ((decodeddhcp & 1) == 1) {
         netcom.setIsDHCPset(true);
      } else {
         netcom.setIsDHCPset(false);
      }
   }

   public static void parseFrame0x8D(byte[] frame2parse, FAAC_GCOM gcom) throws Exception {
      int iDeltaPsw = 8;
      byte[] codedDeviceName = new byte[48];

      for (int i = 0; i < 48; i++) {
         codedDeviceName[i] = frame2parse[i - iDeltaPsw + 15];
      }

      byte[] decodedDeviceName;
      try {
         decodedDeviceName = FAAC_Protocol.intelHexDecoderDataFlow(codedDeviceName);
      } catch (Exception var18) {
         Debug.printlnStatic(var18.toString());
         throw var18;
      }

      int i = 0;

      while (i < decodedDeviceName.length && decodedDeviceName[i] != 0) {
         i++;
      }

      int length = i;
      byte[] byteName = new byte[i];

      for (int var20 = 0; var20 < length; var20++) {
         byteName[var20] = decodedDeviceName[var20];
      }

      gcom.setDeviceName(new String(byteName));
      byte[] codedFlags = new byte[8];

      for (int var21 = 0; var21 < 8; var21++) {
         codedFlags[var21] = frame2parse[var21 - iDeltaPsw + 63];
      }

      byte[] decodedFlags;
      try {
         decodedFlags = FAAC_Protocol.intelHexDecoderDataFlow(codedFlags);
      } catch (Exception var17) {
         Debug.printlnStatic(var17.toString());
         throw var17;
      }

      gcom.getSmsEnableFlag().setBytes_MSBfirst(decodedFlags);
      byte[] codedResendPeriod = new byte[2];

      for (int var22 = 0; var22 < 2; var22++) {
         codedResendPeriod[var22] = frame2parse[var22 - iDeltaPsw + 71];
      }

      byte decodedResendPeriod;
      try {
         decodedResendPeriod = FAAC_Protocol.intelHexDecoder(codedResendPeriod);
      } catch (Exception var16) {
         Debug.printlnStatic(var16.toString());
         throw var16;
      }

      gcom.setResendPeriod_hours(decodedResendPeriod);
      byte[] codedResendTimes = new byte[2];

      for (int var23 = 0; var23 < 2; var23++) {
         codedResendTimes[var23] = frame2parse[var23 - iDeltaPsw + 73];
      }

      byte decodedResendTimes;
      try {
         decodedResendTimes = FAAC_Protocol.intelHexDecoder(codedResendTimes);
      } catch (Exception var15) {
         Debug.printlnStatic(var15.toString());
         throw var15;
      }

      gcom.setResendTimes(decodedResendTimes);
   }

   public static void parseFrame0x8E(byte[] frame2parse, FAAC_GCOM gcom) throws Exception {
      int iDeltaPsw = 8;
      byte[] codedPbIndex = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedPbIndex[i] = frame2parse[i - iDeltaPsw + 15];
      }

      byte[] decodedPbIndex;
      try {
         decodedPbIndex = FAAC_Protocol.intelHexDecoderDataFlow(codedPbIndex);
      } catch (Exception var18) {
         Debug.printlnStatic(var18.toString());
         throw var18;
      }

      int pbIndex = decodedPbIndex[0];
      pbIndex <<= 8;
      pbIndex |= decodedPbIndex[1];
      PBentry[] entries = new PBentry[4];
      byte[] codedPhone = new byte[32];
      byte[] codedAttrProf = new byte[2];

      for (int numIndex = 0; numIndex < 4; numIndex++) {
         int firstNumByteIndex = numIndex * 36 + 19 - iDeltaPsw;

         for (int i = 0; i < 32; i++) {
            codedPhone[i] = frame2parse[i + firstNumByteIndex];
         }

         byte[] decodedPhone;
         try {
            decodedPhone = FAAC_Protocol.intelHexDecoderDataFlow(codedPhone);
         } catch (Exception var17) {
            Debug.printlnStatic(var17.toString());
            throw var17;
         }

         int phoneLength = 16;

         for (int i = 0; i < 16; i++) {
            if (decodedPhone[i] == 65) {
               phoneLength = i;
               break;
            }
         }

         String phone = new String(decodedPhone, 0, phoneLength);
         firstNumByteIndex += 32;

         for (int ix = 0; ix < 2; ix++) {
            codedAttrProf[ix] = frame2parse[ix + firstNumByteIndex];
         }

         byte decodedProfile = FAAC_Protocol.intelHexDecoder(codedAttrProf);
         firstNumByteIndex += 2;

         for (int ix = 0; ix < 2; ix++) {
            codedAttrProf[ix] = frame2parse[ix + firstNumByteIndex];
         }

         byte decodedAttribute = FAAC_Protocol.intelHexDecoder(codedAttrProf);
         if (phone.compareTo("0000000000000000") == 0) {
            entries[numIndex] = PBentry.GetNewEmptyEntry(pbIndex * 4 + numIndex);
         } else {
            entries[numIndex] = new PBentry(pbIndex * 4 + numIndex, phone, decodedProfile, decodedAttribute);
         }
      }

      gcom.getPhoneBook().AddEntries(entries, pbIndex);
   }

   public static boolean parseTmrFile(byte[] byte2parse, FAAC_Timer boardTimer, String filePassword) {
      byte[] psw = new byte[4];

      for (int i = 0; i < 4; i++) {
         psw[i] = FAAC_Protocol.FileSplitByteArrayToByte(new byte[]{byte2parse[8 + i * 2], byte2parse[9 + i * 2]});
      }

      String readPsw = new String(psw);
      if (filePassword.compareTo("") != 0 && filePassword.compareTo(readPsw) != 0) {
         return false;
      } else {
         byte[][] timerMatrix = new byte[8][32];

         for (int iDay = 0; iDay < 8; iDay++) {
            for (int i = 0; i < 32; i++) {
               timerMatrix[iDay][i] = byte2parse[i + 24 + 32 * iDay];
            }
         }

         for (int iDayIndex = 0; iDayIndex < 8; iDayIndex++) {
            byte[] timerTmp = timerMatrix[iDayIndex];

            for (int iRange = 0; iRange < 6; iRange++) {
               boardTimer.getTimer(iDayIndex)[iRange].setValueFunction(timerTmp[iRange * 5]);
               boardTimer.getTimer(iDayIndex)[iRange].setBeginHourExact(FAAC_Protocol.byteToInt_BCDformat(timerTmp[iRange * 5 + 1]));
               boardTimer.getTimer(iDayIndex)[iRange].setBeginMinuteExact(FAAC_Protocol.byteToInt_BCDformat(timerTmp[iRange * 5 + 2]));
               boardTimer.getTimer(iDayIndex)[iRange].setEndHourExact(FAAC_Protocol.byteToInt_BCDformat(timerTmp[iRange * 5 + 3]));
               boardTimer.getTimer(iDayIndex)[iRange].setEndMinuteExact(FAAC_Protocol.byteToInt_BCDformat(timerTmp[iRange * 5 + 4]));
               boardTimer.getTimer(iDayIndex)[iRange]
                  .setBeginDateExact(FAAC_Protocol.byteToInt_BCDformat(timerTmp[iRange * 5 + 1]), FAAC_Protocol.byteToInt_BCDformat(timerTmp[iRange * 5 + 2]));
               boardTimer.getTimer(iDayIndex)[iRange]
                  .setEndDateExact(FAAC_Protocol.byteToInt_BCDformat(timerTmp[iRange * 5 + 3]), FAAC_Protocol.byteToInt_BCDformat(timerTmp[iRange * 5 + 4]));
               boardTimer.getTimer(iDayIndex)[iRange].ExactTimerRange2RoundedTimerRange();
            }
         }

         int iProgLenght = 29;
         int iHeaderLenght = 24;
         int iSingleTimerDayLenght = 32;
         int iProgTimerIndexInFile = iHeaderLenght + 8 * iSingleTimerDayLenght;
         byte[] progTimerBytes = new byte[4];

         for (int i = 0; i < 4; i++) {
            progTimerBytes[i] = byte2parse[iProgTimerIndexInFile + i];
         }

         boardTimer.getProgTimer().setBytes_MSBfirst(progTimerBytes);

         for (int iJolly = 0; iJolly < 6; iJolly++) {
            boardTimer.getJollyIntervals()[iJolly].setStartDay(FAAC_Protocol.byteToInt_BCDformat(byte2parse[iProgTimerIndexInFile + 4 + 4 * iJolly]));
            boardTimer.getJollyIntervals()[iJolly].setStartMonth(FAAC_Protocol.byteToInt_BCDformat(byte2parse[iProgTimerIndexInFile + 5 + 4 * iJolly]));
            boardTimer.getJollyIntervals()[iJolly].setEndDay(FAAC_Protocol.byteToInt_BCDformat(byte2parse[iProgTimerIndexInFile + 6 + 4 * iJolly]));
            boardTimer.getJollyIntervals()[iJolly].setEndMonth(FAAC_Protocol.byteToInt_BCDformat(byte2parse[iProgTimerIndexInFile + 7 + 4 * iJolly]));
         }

         return true;
      }
   }

   public static int parseFrame0x90(byte[] frame2parse, FAAC_Timer boardTimer) throws Exception {
      int ideltaPsw = 8;
      byte[] codedTimerDay = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedTimerDay[i] = frame2parse[i + 15 - ideltaPsw];
      }

      byte decodedTimerDay;
      try {
         decodedTimerDay = FAAC_Protocol.intelHexDecoder(codedTimerDay);
      } catch (Exception var23) {
         Debug.printlnStatic(var23.toString());
         throw var23;
      }

      int iDayIndex = decodedTimerDay;

      for (int iRange = 0; iRange < 6; iRange++) {
         byte[] codedFunction = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedFunction[i] = frame2parse[i + 17 + 10 * iRange - ideltaPsw];
         }

         byte decodedFunction;
         try {
            decodedFunction = FAAC_Protocol.intelHexDecoder(codedFunction);
         } catch (Exception var22) {
            Debug.printlnStatic(var22.toString());
            throw var22;
         }

         boardTimer.getTimer(iDayIndex)[iRange].setValueFunction(decodedFunction);
         byte[] codedBeginHour = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedBeginHour[i] = frame2parse[i + 19 + 10 * iRange - ideltaPsw];
         }

         byte decodedBeginHour;
         try {
            decodedBeginHour = FAAC_Protocol.intelHexDecoder(codedBeginHour);
         } catch (Exception var21) {
            Debug.printlnStatic(var21.toString());
            throw var21;
         }

         boardTimer.getTimer(iDayIndex)[iRange].setBeginHourExact(FAAC_Protocol.byteToInt_BCDformat(decodedBeginHour));
         byte[] codedBeginMin = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedBeginMin[i] = frame2parse[i + 21 + 10 * iRange - ideltaPsw];
         }

         byte decodedBeginMin;
         try {
            decodedBeginMin = FAAC_Protocol.intelHexDecoder(codedBeginMin);
         } catch (Exception var20) {
            Debug.printlnStatic(var20.toString());
            throw var20;
         }

         boardTimer.getTimer(iDayIndex)[iRange].setBeginMinuteExact(FAAC_Protocol.byteToInt_BCDformat(decodedBeginMin));
         byte[] codedEndHour = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedEndHour[i] = frame2parse[i + 23 + 10 * iRange - ideltaPsw];
         }

         byte decodedEndHour;
         try {
            decodedEndHour = FAAC_Protocol.intelHexDecoder(codedEndHour);
         } catch (Exception var19) {
            Debug.printlnStatic(var19.toString());
            throw var19;
         }

         boardTimer.getTimer(iDayIndex)[iRange].setEndHourExact(FAAC_Protocol.byteToInt_BCDformat(decodedEndHour));
         byte[] codedEndMin = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedEndMin[i] = frame2parse[i + 25 + 10 * iRange - ideltaPsw];
         }

         byte decodedEndMin;
         try {
            decodedEndMin = FAAC_Protocol.intelHexDecoder(codedEndMin);
         } catch (Exception var18) {
            Debug.printlnStatic(var18.toString());
            throw var18;
         }

         boardTimer.getTimer(iDayIndex)[iRange].setEndMinuteExact(FAAC_Protocol.byteToInt_BCDformat(decodedEndMin));
         boardTimer.getTimer(iDayIndex)[iRange]
            .setBeginDateExact(FAAC_Protocol.byteToInt_BCDformat(decodedBeginHour), FAAC_Protocol.byteToInt_BCDformat(decodedBeginMin));
         boardTimer.getTimer(iDayIndex)[iRange]
            .setEndDateExact(FAAC_Protocol.byteToInt_BCDformat(decodedEndHour), FAAC_Protocol.byteToInt_BCDformat(decodedEndMin));
         boardTimer.getTimer(iDayIndex)[iRange].ExactTimerRange2RoundedTimerRange();
      }

      return iDayIndex;
   }

   public static boolean parseFrame0x92ForRealTimeLog(byte[] frame2parse, int logIndex, FAAC_Logs boardLogs) throws Exception {
      boolean bAlreadyPresent = false;
      byte[] codedLogIndex = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedLogIndex[i] = frame2parse[i + 7];
      }

      byte decodedLogIndex;
      try {
         decodedLogIndex = FAAC_Protocol.intelHexDecoder(codedLogIndex);
      } catch (Exception var34) {
         Debug.printlnStatic(var34.toString());
         throw var34;
      }

      if (logIndex != decodedLogIndex) {
         return false;
      } else {
         FAAC_Logs.Log newLog = new FAAC_Logs.Log();
         newLog.setIndexLog(logIndex);
         FAAC_Logs.LogDate newLogDate = new FAAC_Logs.LogDate();
         byte[] codedMin = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedMin[i] = frame2parse[i + 9];
         }

         byte decodedMinutes;
         try {
            decodedMinutes = FAAC_Protocol.intelHexDecoder(codedMin);
         } catch (Exception var33) {
            Debug.printlnStatic(var33.toString());
            throw var33;
         }

         newLogDate.setMinute(FAAC_Protocol.byteToInt_BCDformat(decodedMinutes));
         byte[] codedHour = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedHour[i] = frame2parse[i + 11];
         }

         byte decodedHour;
         try {
            decodedHour = FAAC_Protocol.intelHexDecoder(codedHour);
         } catch (Exception var32) {
            Debug.printlnStatic(var32.toString());
            throw var32;
         }

         newLogDate.setHour(FAAC_Protocol.byteToInt_BCDformat(decodedHour));
         byte[] codedDay = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedDay[i] = frame2parse[i + 13];
         }

         byte decodedDay;
         try {
            decodedDay = FAAC_Protocol.intelHexDecoder(codedDay);
         } catch (Exception var31) {
            Debug.printlnStatic(var31.toString());
            throw var31;
         }

         newLogDate.setDay(FAAC_Protocol.byteToInt_BCDformat(decodedDay));
         byte[] codedMonth = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedMonth[i] = frame2parse[i + 15];
         }

         byte decodedMonth;
         try {
            decodedMonth = FAAC_Protocol.intelHexDecoder(codedMonth);
         } catch (Exception var30) {
            Debug.printlnStatic(var30.toString());
            throw var30;
         }

         newLogDate.setMonth(FAAC_Protocol.byteToInt_BCDformat(decodedMonth));
         byte[] codedYear = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedYear[i] = frame2parse[i + 17];
         }

         byte decodedYear;
         try {
            decodedYear = FAAC_Protocol.intelHexDecoder(codedYear);
         } catch (Exception var29) {
            Debug.printlnStatic(var29.toString());
            throw var29;
         }

         newLogDate.setYear(FAAC_Protocol.byteToInt_BCDformat(decodedYear));
         newLog.setLogDate(newLogDate);
         byte[] codedEvent1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedEvent1[i] = frame2parse[i + 19];
         }

         byte decodedEvent1;
         try {
            decodedEvent1 = FAAC_Protocol.intelHexDecoder(codedEvent1);
         } catch (Exception var28) {
            Debug.printlnStatic(var28.toString());
            throw var28;
         }

         newLog.setEvent1(decodedEvent1);
         boolean isLogCode1 = false;
         if (decodedEvent1 == 1) {
            isLogCode1 = true;
         }

         byte[] codedEvent2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedEvent2[i] = frame2parse[i + 21];
         }

         byte decodedEvent2;
         try {
            decodedEvent2 = FAAC_Protocol.intelHexDecoder(codedEvent2);
         } catch (Exception var27) {
            Debug.printlnStatic(var27.toString());
            throw var27;
         }

         if (!isLogCode1) {
            newLog.setEvent2(decodedEvent2);
         } else {
            newLog.setEvent2(0);
            newLog.setLogSwVersion_SW1((char)decodedEvent2);
         }

         byte[] codedEvent3 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedEvent3[i] = frame2parse[i + 23];
         }

         byte decodedEvent3;
         try {
            decodedEvent3 = FAAC_Protocol.intelHexDecoder(codedEvent3);
         } catch (Exception var26) {
            Debug.printlnStatic(var26.toString());
            throw var26;
         }

         if (!isLogCode1) {
            newLog.setEvent3(decodedEvent3);
         } else {
            newLog.setEvent3(0);
            newLog.setLogSwVersion_SW2((char)decodedEvent3);
         }

         if (newLog.isNullLog()) {
            bAlreadyPresent = true;
         } else if (boardLogs.getLogList().contains(newLog)) {
            bAlreadyPresent = true;
         }

         if (!bAlreadyPresent) {
            boardLogs.addLog(newLog);
         }

         return bAlreadyPresent;
      }
   }

   public static boolean parseFrame0x95ForRealTimeLog(byte[] frame2parse, int logIndex, FAAC_Logs boardLogs) throws Exception {
      boolean bAlreadyPresent = false;
      byte[] codedLogIndex = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedLogIndex[i] = frame2parse[i + 7];
      }

      byte[] decodedLogIndex;
      try {
         decodedLogIndex = FAAC_Protocol.intelHexDecoderDataFlow(codedLogIndex);
      } catch (Exception var36) {
         Debug.printlnStatic(var36.toString());
         throw var36;
      }

      byte[] decodedLogIndex_MSBfirst = new byte[]{decodedLogIndex[1], decodedLogIndex[0]};
      int iLogIndex = FAAC_Protocol.byteArrayToInt(decodedLogIndex_MSBfirst);
      if (logIndex != iLogIndex) {
         return false;
      } else {
         FAAC_Logs.Log newLog = new FAAC_Logs.Log();
         newLog.setIndexLog(logIndex);
         FAAC_Logs.LogDate newLogDate = new FAAC_Logs.LogDate();
         byte[] codedMin = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedMin[i] = frame2parse[i + 11];
         }

         byte decodedMinutes;
         try {
            decodedMinutes = FAAC_Protocol.intelHexDecoder(codedMin);
         } catch (Exception var35) {
            Debug.printlnStatic(var35.toString());
            throw var35;
         }

         newLogDate.setMinute(FAAC_Protocol.byteToInt_BCDformat(decodedMinutes));
         byte[] codedHour = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedHour[i] = frame2parse[i + 13];
         }

         byte decodedHour;
         try {
            decodedHour = FAAC_Protocol.intelHexDecoder(codedHour);
         } catch (Exception var34) {
            Debug.printlnStatic(var34.toString());
            throw var34;
         }

         newLogDate.setHour(FAAC_Protocol.byteToInt_BCDformat(decodedHour));
         byte[] codedDay = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedDay[i] = frame2parse[i + 15];
         }

         byte decodedDay;
         try {
            decodedDay = FAAC_Protocol.intelHexDecoder(codedDay);
         } catch (Exception var33) {
            Debug.printlnStatic(var33.toString());
            throw var33;
         }

         newLogDate.setDay(FAAC_Protocol.byteToInt_BCDformat(decodedDay));
         byte[] codedMonth = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedMonth[i] = frame2parse[i + 17];
         }

         byte decodedMonth;
         try {
            decodedMonth = FAAC_Protocol.intelHexDecoder(codedMonth);
         } catch (Exception var32) {
            Debug.printlnStatic(var32.toString());
            throw var32;
         }

         newLogDate.setMonth(FAAC_Protocol.byteToInt_BCDformat(decodedMonth));
         byte[] codedYear = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedYear[i] = frame2parse[i + 19];
         }

         byte decodedYear;
         try {
            decodedYear = FAAC_Protocol.intelHexDecoder(codedYear);
         } catch (Exception var31) {
            Debug.printlnStatic(var31.toString());
            throw var31;
         }

         newLogDate.setYear(FAAC_Protocol.byteToInt_BCDformat(decodedYear));
         newLog.setLogDate(newLogDate);
         byte[] codedEvent1 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedEvent1[i] = frame2parse[i + 21];
         }

         byte decodedEvent1;
         try {
            decodedEvent1 = FAAC_Protocol.intelHexDecoder(codedEvent1);
         } catch (Exception var30) {
            Debug.printlnStatic(var30.toString());
            throw var30;
         }

         newLog.setEvent1(decodedEvent1);
         boolean isLogCode1 = false;
         if (decodedEvent1 == 1) {
            isLogCode1 = true;
         }

         byte[] codedEvent2 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedEvent2[i] = frame2parse[i + 23];
         }

         byte decodedEvent2;
         try {
            decodedEvent2 = FAAC_Protocol.intelHexDecoder(codedEvent2);
         } catch (Exception var29) {
            Debug.printlnStatic(var29.toString());
            throw var29;
         }

         if (!isLogCode1) {
            newLog.setEvent2(decodedEvent2);
         } else {
            newLog.setEvent2(0);
            newLog.setLogSwVersion_SW1((char)decodedEvent2);
         }

         byte[] codedEvent3 = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedEvent3[i] = frame2parse[i + 25];
         }

         byte decodedEvent3;
         try {
            decodedEvent3 = FAAC_Protocol.intelHexDecoder(codedEvent3);
         } catch (Exception var28) {
            Debug.printlnStatic(var28.toString());
            throw var28;
         }

         if (!isLogCode1) {
            newLog.setEvent3(decodedEvent3);
         } else {
            newLog.setEvent3(0);
            newLog.setLogSwVersion_SW2((char)decodedEvent3);
         }

         if (newLog.isNullLog()) {
            bAlreadyPresent = true;
         } else if (boardLogs.getLogList().contains(newLog)) {
            bAlreadyPresent = true;
         }

         if (!bAlreadyPresent) {
            boardLogs.addLog(newLog);
         }

         return bAlreadyPresent;
      }
   }

   public static int parseFrame0x92(byte[] frame2parse, FAAC_Logs boardLogs) throws Exception {
      byte[] codedLogIndex = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedLogIndex[i] = frame2parse[i + 7];
      }

      byte decodedLogIndex;
      try {
         decodedLogIndex = FAAC_Protocol.intelHexDecoder(codedLogIndex);
      } catch (Exception var33) {
         Debug.printlnStatic(var33.toString());
         throw var33;
      }

      FAAC_Logs.Log newLog = new FAAC_Logs.Log();
      newLog.setIndexLog(decodedLogIndex);
      FAAC_Logs.LogDate newLogDate = new FAAC_Logs.LogDate();
      byte[] codedMin = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMin[i] = frame2parse[i + 9];
      }

      byte decodedMinutes;
      try {
         decodedMinutes = FAAC_Protocol.intelHexDecoder(codedMin);
      } catch (Exception var32) {
         Debug.printlnStatic(var32.toString());
         throw var32;
      }

      newLogDate.setMinute(FAAC_Protocol.byteToInt_BCDformat(decodedMinutes));
      byte[] codedHour = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedHour[i] = frame2parse[i + 11];
      }

      byte decodedHour;
      try {
         decodedHour = FAAC_Protocol.intelHexDecoder(codedHour);
      } catch (Exception var31) {
         Debug.printlnStatic(var31.toString());
         throw var31;
      }

      newLogDate.setHour(FAAC_Protocol.byteToInt_BCDformat(decodedHour));
      byte[] codedDay = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedDay[i] = frame2parse[i + 13];
      }

      byte decodedDay;
      try {
         decodedDay = FAAC_Protocol.intelHexDecoder(codedDay);
      } catch (Exception var30) {
         Debug.printlnStatic(var30.toString());
         throw var30;
      }

      newLogDate.setDay(FAAC_Protocol.byteToInt_BCDformat(decodedDay));
      byte[] codedMonth = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMonth[i] = frame2parse[i + 15];
      }

      byte decodedMonth;
      try {
         decodedMonth = FAAC_Protocol.intelHexDecoder(codedMonth);
      } catch (Exception var29) {
         Debug.printlnStatic(var29.toString());
         throw var29;
      }

      newLogDate.setMonth(FAAC_Protocol.byteToInt_BCDformat(decodedMonth));
      byte[] codedYear = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedYear[i] = frame2parse[i + 17];
      }

      byte decodedYear;
      try {
         decodedYear = FAAC_Protocol.intelHexDecoder(codedYear);
      } catch (Exception var28) {
         Debug.printlnStatic(var28.toString());
         throw var28;
      }

      newLogDate.setYear(FAAC_Protocol.byteToInt_BCDformat(decodedYear));
      newLog.setLogDate(newLogDate);
      byte[] codedEvent1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedEvent1[i] = frame2parse[i + 19];
      }

      byte decodedEvent1;
      try {
         decodedEvent1 = FAAC_Protocol.intelHexDecoder(codedEvent1);
      } catch (Exception var27) {
         Debug.printlnStatic(var27.toString());
         throw var27;
      }

      newLog.setEvent1(decodedEvent1);
      boolean isLogCode1 = false;
      if (decodedEvent1 == 1) {
         isLogCode1 = true;
      }

      byte[] codedEvent2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedEvent2[i] = frame2parse[i + 21];
      }

      byte decodedEvent2;
      try {
         decodedEvent2 = FAAC_Protocol.intelHexDecoder(codedEvent2);
      } catch (Exception var26) {
         Debug.printlnStatic(var26.toString());
         throw var26;
      }

      if (!isLogCode1) {
         newLog.setEvent2(decodedEvent2);
      } else {
         newLog.setEvent2(0);
         newLog.setLogSwVersion_SW1((char)decodedEvent2);
      }

      byte[] codedEvent3 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedEvent3[i] = frame2parse[i + 23];
      }

      byte decodedEvent3;
      try {
         decodedEvent3 = FAAC_Protocol.intelHexDecoder(codedEvent3);
      } catch (Exception var25) {
         Debug.printlnStatic(var25.toString());
         throw var25;
      }

      if (!isLogCode1) {
         newLog.setEvent3(decodedEvent3);
      } else {
         newLog.setEvent3(0);
         newLog.setLogSwVersion_SW2((char)decodedEvent3);
      }

      if (!newLog.isNullLog() && !boardLogs.getLogList().contains(newLog)) {
         boardLogs.addLog(newLog);
      }

      return decodedLogIndex;
   }

   public static int parseFrame0x95(byte[] frame2parse, FAAC_Logs boardLogs) throws Exception {
      byte[] codedLogIndex = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedLogIndex[i] = frame2parse[i + 7];
      }

      byte[] decodedLogIndex;
      try {
         decodedLogIndex = FAAC_Protocol.intelHexDecoderDataFlow(codedLogIndex);
      } catch (Exception var34) {
         Debug.printlnStatic(var34.toString());
         throw var34;
      }

      byte[] decodedLogIndex_MSBfirst = new byte[]{decodedLogIndex[1], decodedLogIndex[0]};
      int iLogIndex = FAAC_Protocol.byteArrayToInt(decodedLogIndex_MSBfirst);
      FAAC_Logs.Log newLog = new FAAC_Logs.Log();
      newLog.setIndexLog(iLogIndex);
      FAAC_Logs.LogDate newLogDate = new FAAC_Logs.LogDate();
      byte[] codedMin = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMin[i] = frame2parse[i + 11];
      }

      byte decodedMinutes;
      try {
         decodedMinutes = FAAC_Protocol.intelHexDecoder(codedMin);
      } catch (Exception var33) {
         Debug.printlnStatic(var33.toString());
         throw var33;
      }

      newLogDate.setMinute(FAAC_Protocol.byteToInt_BCDformat(decodedMinutes));
      byte[] codedHour = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedHour[i] = frame2parse[i + 13];
      }

      byte decodedHour;
      try {
         decodedHour = FAAC_Protocol.intelHexDecoder(codedHour);
      } catch (Exception var32) {
         Debug.printlnStatic(var32.toString());
         throw var32;
      }

      newLogDate.setHour(FAAC_Protocol.byteToInt_BCDformat(decodedHour));
      byte[] codedDay = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedDay[i] = frame2parse[i + 15];
      }

      byte decodedDay;
      try {
         decodedDay = FAAC_Protocol.intelHexDecoder(codedDay);
      } catch (Exception var31) {
         Debug.printlnStatic(var31.toString());
         throw var31;
      }

      newLogDate.setDay(FAAC_Protocol.byteToInt_BCDformat(decodedDay));
      byte[] codedMonth = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedMonth[i] = frame2parse[i + 17];
      }

      byte decodedMonth;
      try {
         decodedMonth = FAAC_Protocol.intelHexDecoder(codedMonth);
      } catch (Exception var30) {
         Debug.printlnStatic(var30.toString());
         throw var30;
      }

      newLogDate.setMonth(FAAC_Protocol.byteToInt_BCDformat(decodedMonth));
      byte[] codedYear = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedYear[i] = frame2parse[i + 19];
      }

      byte decodedYear;
      try {
         decodedYear = FAAC_Protocol.intelHexDecoder(codedYear);
      } catch (Exception var29) {
         Debug.printlnStatic(var29.toString());
         throw var29;
      }

      newLogDate.setYear(FAAC_Protocol.byteToInt_BCDformat(decodedYear));
      newLog.setLogDate(newLogDate);
      byte[] codedEvent1 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedEvent1[i] = frame2parse[i + 21];
      }

      byte decodedEvent1;
      try {
         decodedEvent1 = FAAC_Protocol.intelHexDecoder(codedEvent1);
      } catch (Exception var28) {
         Debug.printlnStatic(var28.toString());
         throw var28;
      }

      newLog.setEvent1(decodedEvent1);
      boolean isLogCode1 = false;
      if (decodedEvent1 == 1) {
         isLogCode1 = true;
      }

      byte[] codedEvent2 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedEvent2[i] = frame2parse[i + 23];
      }

      byte decodedEvent2;
      try {
         decodedEvent2 = FAAC_Protocol.intelHexDecoder(codedEvent2);
      } catch (Exception var27) {
         Debug.printlnStatic(var27.toString());
         throw var27;
      }

      if (!isLogCode1) {
         newLog.setEvent2(decodedEvent2);
      } else {
         newLog.setEvent2(0);
         newLog.setLogSwVersion_SW1((char)decodedEvent2);
      }

      byte[] codedEvent3 = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedEvent3[i] = frame2parse[i + 25];
      }

      byte decodedEvent3;
      try {
         decodedEvent3 = FAAC_Protocol.intelHexDecoder(codedEvent3);
      } catch (Exception var26) {
         Debug.printlnStatic(var26.toString());
         throw var26;
      }

      if (!isLogCode1) {
         newLog.setEvent3(decodedEvent3);
      } else {
         newLog.setEvent3(0);
         newLog.setLogSwVersion_SW2((char)decodedEvent3);
      }

      if (!newLog.isNullLog() && !boardLogs.getLogList().contains(newLog)) {
         boardLogs.addLog(newLog);
      }

      return iLogIndex;
   }

   public static void parseFrame0x93(byte[] frame2parse, FAAC_GCOM gcom) throws Exception {
      int iDeltaPsw = 8;
      byte[] codedNum1 = new byte[32];

      for (int i = 0; i < 32; i++) {
         codedNum1[i] = frame2parse[i - iDeltaPsw + 15];
      }

      byte[] decodedNum1;
      try {
         decodedNum1 = FAAC_Protocol.intelHexDecoderDataFlow(codedNum1);
      } catch (Exception var11) {
         Debug.printlnStatic(var11.toString());
         throw var11;
      }

      boolean bAllZeros = true;

      for (int i = 0; i < 16 && bAllZeros; i++) {
         if (decodedNum1[i] != 0) {
            bAllZeros = false;
         }
      }

      if (bAllZeros) {
         gcom.setNumGcom("-");
      } else {
         int ifirstAbyte = 0;
         char[] decodedNum1Chars = new char[16];

         for (int ix = 0; ix < 16; ix++) {
            if (decodedNum1[ix] == 10) {
               ifirstAbyte = ix;
               break;
            }

            String temp = Integer.toHexString(decodedNum1[ix]);
            char[] chtemp = new char[1];
            temp.getChars(0, 1, chtemp, 0);
            decodedNum1Chars[ix] = chtemp[0];
         }

         String strTemp = new String(decodedNum1Chars);
         String StrNum1 = "+" + strTemp;
         String num1 = StrNum1.substring(0, ifirstAbyte + 1);
         gcom.setNumGcom(num1);
      }
   }

   public static int parseFrame0x94(byte[] frame2parse, FAAC_Remote boardRemote) throws Exception {
      byte[] codedNum = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedNum[i] = frame2parse[i + 7];
      }

      byte[] decodedNum;
      try {
         decodedNum = FAAC_Protocol.intelHexDecoderDataFlow(codedNum);
      } catch (Exception var9) {
         Debug.printlnStatic(var9.toString());
         throw var9;
      }

      int iRadioNum = FAAC_Protocol.byteArrayToInt(decodedNum);
      boardRemote.setNbCodesInMemory(iRadioNum);
      byte[] codedActiveCodeIndex = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedActiveCodeIndex[i] = frame2parse[i + 15];
      }

      byte[] decodedActiveCodeIndex;
      try {
         decodedActiveCodeIndex = FAAC_Protocol.intelHexDecoderDataFlow(codedActiveCodeIndex);
      } catch (Exception var8) {
         Debug.printlnStatic(var8.toString());
         throw var8;
      }

      int iRadioActiveIndex = FAAC_Protocol.byteArrayToInt(decodedActiveCodeIndex);
      boardRemote.setCurrentActiveCodeIndex(iRadioActiveIndex);
      return iRadioActiveIndex;
   }

   public static boolean parseRadFile(byte[] byte2parse, FAAC_Remote boardRemote, String filePassword) {
      byte[] psw = new byte[4];

      for (int i = 0; i < 4; i++) {
         psw[i] = FAAC_Protocol.FileSplitByteArrayToByte(new byte[]{byte2parse[8 + i * 2], byte2parse[9 + i * 2]});
      }

      String readPsw = new String(psw);
      if (filePassword.compareTo("") != 0 && filePassword.compareTo(readPsw) != 0) {
         return false;
      } else {
         int iExpectedLenght = boardRemote.getRadFileLenght_inBytes();
         int iLenght = byte2parse.length;
         int iProgLenght = 5;
         int iProgRadioIndexInFile = iLenght - iProgLenght;
         int iHeaderLenght = 24;
         int iSingleRadioCodeLenght = 8;
         int numCodesPresentInFile = (iLenght - iProgLenght - iHeaderLenght) / iSingleRadioCodeLenght;
         byte[][] remoteMatrix = new byte[numCodesPresentInFile][iSingleRadioCodeLenght];

         for (int iRem = 0; iRem < numCodesPresentInFile; iRem++) {
            for (int i = 0; i < iSingleRadioCodeLenght; i++) {
               remoteMatrix[iRem][i] = byte2parse[i + iHeaderLenght + iSingleRadioCodeLenght * iRem];
            }
         }

         byte[] progRadioBytes = new byte[5];

         for (int i = 0; i < iProgLenght - 1; i++) {
            progRadioBytes[i] = byte2parse[iProgRadioIndexInFile + i];
         }

         boardRemote.getProgRadio().setBytes_MSBfirst(progRadioBytes);
         int numCodesToSet;
         if (numCodesPresentInFile <= FAAC_Remote.getNbRadioCode()) {
            numCodesToSet = numCodesPresentInFile;
         } else {
            numCodesToSet = FAAC_Remote.getNbRadioCode();
         }

         for (int iRemIndex = 0; iRemIndex < numCodesToSet; iRemIndex++) {
            byte[] remoteTmp = remoteMatrix[iRemIndex];
            FAAC_Remote.RadioCode code = boardRemote.getRadioCodeArray()[iRemIndex];
            boolean wasNullCode = false;
            if (code == null) {
               code = new FAAC_Remote.RadioCode();
               code.setIndexRadioCode(iRemIndex);
               wasNullCode = true;
            }

            boolean allNullCode1 = true;
            byte[] decodedCode1 = new byte[4];

            for (int i = 0; i < 4; i++) {
               decodedCode1[i] = remoteTmp[3 - i];
               if (decodedCode1[i] != 0) {
                  allNullCode1 = false;
                  code.setIsModified(true);
               }
            }

            FAAC_Remote.RadioCode.RadioCode1 code1 = new FAAC_Remote.RadioCode.RadioCode1();
            code1.setBytes_MSBfirst(decodedCode1);
            code.setCode1(code1);
            boolean allNullCode2 = true;
            byte[] decodedCode2 = new byte[4];

            for (int ix = 0; ix < 4; ix++) {
               decodedCode2[ix] = remoteTmp[7 - ix];
               if (decodedCode2[ix] != 0) {
                  allNullCode2 = false;
                  code.setIsModified(true);
               }
            }

            FAAC_Remote.RadioCode.RadioCode2 code2 = new FAAC_Remote.RadioCode.RadioCode2();
            code2.setBytes_MSBfirst(decodedCode2);
            code.setCode2(code2);
            if (allNullCode1 && allNullCode2) {
               if (!wasNullCode) {
                  FAAC_Remote.RadioCode nullCode = new FAAC_Remote.RadioCode(iRemIndex);
                  boardRemote.setRadioCodeArrayIndex(nullCode, iRemIndex);
               }
            } else if (wasNullCode) {
               boardRemote.setRadioCodeArrayIndex(code, iRemIndex);
            }
         }

         return true;
      }
   }

   public static int parseFrame0xA0(byte[] frame2parse, FAAC_Remote boardRemote) throws Exception {
      int ideltaPsw = 8;
      byte[] codedRadioCodeIndex = new byte[4];

      for (int i = 0; i < 4; i++) {
         codedRadioCodeIndex[i] = frame2parse[i + 15 - ideltaPsw];
      }

      byte[] decodedRadioCodeIndex;
      try {
         decodedRadioCodeIndex = FAAC_Protocol.intelHexDecoderDataFlow(codedRadioCodeIndex);
      } catch (Exception var19) {
         Debug.printlnStatic(var19.toString());
         throw var19;
      }

      int iRadioIndex = FAAC_Protocol.byteArrayToInt(decodedRadioCodeIndex);
      FAAC_Remote.RadioCode code = boardRemote.getRadioCodeArray()[iRadioIndex];
      boolean wasNullCode = false;
      if (code == null) {
         code = new FAAC_Remote.RadioCode();
         code.setIndexRadioCode(iRadioIndex);
         wasNullCode = true;
      }

      boolean allNullCode1 = true;
      byte[] codedCode1 = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedCode1[i] = frame2parse[i + 19 - ideltaPsw];
      }

      byte[] decodedCode1;
      try {
         decodedCode1 = FAAC_Protocol.intelHexDecoderDataFlow(codedCode1);
      } catch (Exception var18) {
         Debug.printlnStatic(var18.toString());
         throw var18;
      }

      for (int i = 0; i < 4; i++) {
         if (decodedCode1[i] != 0) {
            allNullCode1 = false;
            break;
         }
      }

      FAAC_Remote.RadioCode.RadioCode1 code1 = new FAAC_Remote.RadioCode.RadioCode1();
      code1.setBytes_MSBfirst(decodedCode1);
      code.setCode1(code1);
      boolean allNullCode2 = true;
      byte[] codedCode2 = new byte[8];

      for (int ix = 0; ix < 8; ix++) {
         codedCode2[ix] = frame2parse[ix + 27 - ideltaPsw];
      }

      byte[] decodedCode2;
      try {
         decodedCode2 = FAAC_Protocol.intelHexDecoderDataFlow(codedCode2);
      } catch (Exception var17) {
         Debug.printlnStatic(var17.toString());
         throw var17;
      }

      for (int ix = 0; ix < 4; ix++) {
         if (decodedCode2[ix] != 0) {
            allNullCode2 = false;
            break;
         }
      }

      FAAC_Remote.RadioCode.RadioCode2 code2 = new FAAC_Remote.RadioCode.RadioCode2();
      code2.setBytes_MSBfirst(decodedCode2);
      code.setCode2(code2);
      if (allNullCode1 && allNullCode2) {
         if (!wasNullCode) {
            FAAC_Remote.RadioCode nullCode = new FAAC_Remote.RadioCode(iRadioIndex);
            boardRemote.setRadioCodeArrayIndex(nullCode, iRadioIndex);
         }
      } else if (wasNullCode) {
         boardRemote.setRadioCodeArrayIndex(code, iRadioIndex);
      }

      return iRadioIndex;
   }

   public static void parseFrame0x9E(byte[] frame2parse, FAAC_Remote boardRemote) throws Exception {
      int ideltaPsw = 8;
      byte[] codedProgField = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedProgField[i] = frame2parse[i + 15 - ideltaPsw];
      }

      byte[] decodedProgField = new byte[4];

      try {
         decodedProgField = FAAC_Protocol.intelHexDecoderDataFlow(codedProgField);
      } catch (Exception var6) {
         Debug.printlnStatic("ParseFrame0x9E prog radio " + var6.toString());
         throw var6;
      }

      boardRemote.getProgRadio().setBytes_MSBfirst(decodedProgField);
   }

   public static void parseFrame0x9D(byte[] frame2parse, FAAC_Timer boardTimer) throws Exception {
      int ideltaPsw = 8;
      byte[] codedProgField = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedProgField[i] = frame2parse[i + 15 - ideltaPsw];
      }

      byte[] decodedProgField = new byte[4];

      try {
         decodedProgField = FAAC_Protocol.intelHexDecoderDataFlow(codedProgField);
      } catch (Exception var15) {
         Debug.printlnStatic("ParseFrame0x9D prog timer " + var15.toString());
         throw var15;
      }

      boardTimer.getProgTimer().setBytes_MSBfirst(decodedProgField);

      for (int iJolly = 0; iJolly < 6; iJolly++) {
         byte[] codedDay = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedDay[i] = frame2parse[23 - ideltaPsw + i + iJolly * 8];
         }

         byte decodedDay;
         try {
            decodedDay = FAAC_Protocol.intelHexDecoder(codedDay);
         } catch (Exception var14) {
            Debug.printlnStatic(var14.toString());
            throw var14;
         }

         boardTimer.getJollyIntervals()[iJolly].setStartDay(FAAC_Protocol.byteToInt_BCDformat(decodedDay));
         byte[] codedMonth = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedMonth[i] = frame2parse[25 - ideltaPsw + i + iJolly * 8];
         }

         byte decodedMonth;
         try {
            decodedMonth = FAAC_Protocol.intelHexDecoder(codedMonth);
         } catch (Exception var13) {
            Debug.printlnStatic(var13.toString());
            throw var13;
         }

         boardTimer.getJollyIntervals()[iJolly].setStartMonth(FAAC_Protocol.byteToInt_BCDformat(decodedMonth));
         codedDay = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedDay[i] = frame2parse[27 - ideltaPsw + i + iJolly * 8];
         }

         try {
            decodedDay = FAAC_Protocol.intelHexDecoder(codedDay);
         } catch (Exception var12) {
            Debug.printlnStatic(var12.toString());
            throw var12;
         }

         boardTimer.getJollyIntervals()[iJolly].setEndDay(FAAC_Protocol.byteToInt_BCDformat(decodedDay));
         codedMonth = new byte[2];

         for (int i = 0; i < 2; i++) {
            codedMonth[i] = frame2parse[29 - ideltaPsw + i + iJolly * 8];
         }

         try {
            decodedMonth = FAAC_Protocol.intelHexDecoder(codedMonth);
         } catch (Exception var11) {
            Debug.printlnStatic(var11.toString());
            throw var11;
         }

         boardTimer.getJollyIntervals()[iJolly].setEndMonth(FAAC_Protocol.byteToInt_BCDformat(decodedMonth));
      }
   }

   public static class AckReplyObject {
      private byte replyCommand;
      private int index;

      public byte getReplyCommand() {
         return this.replyCommand;
      }

      public void setReplyCommand(byte replyCommand) {
         this.replyCommand = replyCommand;
      }

      public int getIndex() {
         return this.index;
      }

      public void setIndex(int index) {
         this.index = index;
      }
   }
}
