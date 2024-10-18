package FAAClib;

public class XcomBox_frames {
   public static final byte ANSWER_OPCODE_OK = -16;
   public static final byte ANSWER_OPCODE_GeneralError = 1;
   public static final byte ANSWER_OPCODE_Unsupported = 2;
   public static final byte ANSWER_OPCODE_Timeout = 3;
   public static final byte ANSWER_OPCODE_NACK = -15;
   public static final byte ANSWER_OPCODE_Unexpected = -14;
   public static final byte ANSWER_OPCODE_NotAuthorized = -13;
   public static final byte ANSWER_OPCODE_Unable = -12;

   public static byte[] getCedacFrame0x01_ChangeLocalRF() {
      byte[] codedFrame01 = new byte[16];
      byte[] uncodedFrame01 = new byte[7];
      codedFrame01[0] = 2;
      byte lenghtPayload = 4;
      uncodedFrame01[0] = lenghtPayload;
      byte opcode = 1;
      uncodedFrame01[1] = opcode;
      uncodedFrame01[2] = 0;
      uncodedFrame01[3] = 0;
      uncodedFrame01[4] = -6;
      uncodedFrame01[5] = -84;
      byte[] payload0x01 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame01, 2, payload0x01, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x01, opcode);
      uncodedFrame01[6] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame01);
      System.arraycopy(res, 0, codedFrame01, 1, res.length);
      codedFrame01[15] = 3;
      return codedFrame01;
   }

   public static byte[] getCedacFrame0x02_Inquiry() {
      byte[] codedFrame02 = new byte[10];
      byte[] uncodedFrame02 = new byte[4];
      codedFrame02[0] = 2;
      byte lenghtPayload = 1;
      uncodedFrame02[0] = lenghtPayload;
      byte opcode = 2;
      uncodedFrame02[1] = opcode;
      uncodedFrame02[2] = 0;
      byte[] payload0x02 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame02, 2, payload0x02, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x02, opcode);
      uncodedFrame02[3] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame02);
      System.arraycopy(res, 0, codedFrame02, 1, res.length);
      codedFrame02[9] = 3;
      return codedFrame02;
   }

   public static byte[] getCedacFrame0x03_SDAactive() {
      byte[] codedFrame03 = new byte[10];
      byte[] uncodedFrame03 = new byte[4];
      codedFrame03[0] = 2;
      byte lenghtPayload = 1;
      uncodedFrame03[0] = lenghtPayload;
      byte opcode = 3;
      uncodedFrame03[1] = opcode;
      uncodedFrame03[2] = 0;
      byte[] payload0x03 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame03, 2, payload0x03, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x03, opcode);
      uncodedFrame03[3] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame03);
      System.arraycopy(res, 0, codedFrame03, 1, res.length);
      codedFrame03[9] = 3;
      return codedFrame03;
   }

   public static byte[] getCedacFrame0x03_SDAset(byte[] address_MSBfirst) {
      byte[] codedFrame03 = new byte[18];
      byte[] uncodedFrame03 = new byte[8];
      codedFrame03[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrame03[0] = lenghtPayload;
      byte opcode = 3;
      uncodedFrame03[1] = opcode;
      uncodedFrame03[2] = 1;
      uncodedFrame03[3] = address_MSBfirst[0];
      uncodedFrame03[4] = address_MSBfirst[1];
      uncodedFrame03[5] = address_MSBfirst[2];
      uncodedFrame03[6] = address_MSBfirst[3];
      byte[] payload0x03 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame03, 2, payload0x03, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x03, opcode);
      uncodedFrame03[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame03);
      System.arraycopy(res, 0, codedFrame03, 1, res.length);
      codedFrame03[17] = 3;
      return codedFrame03;
   }

   public static byte[] getCedacFrame0x03_SDAdelete() {
      byte[] codedFrame03 = new byte[10];
      byte[] uncodedFrame03 = new byte[4];
      codedFrame03[0] = 2;
      byte lenghtPayload = 1;
      uncodedFrame03[0] = lenghtPayload;
      byte opcode = 3;
      uncodedFrame03[1] = opcode;
      uncodedFrame03[2] = 2;
      byte[] payload0x03 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrame03, 2, payload0x03, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0x03, opcode);
      uncodedFrame03[3] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrame03);
      System.arraycopy(res, 0, codedFrame03, 1, res.length);
      codedFrame03[9] = 3;
      return codedFrame03;
   }

   public static byte[] getCedacRemoteFrame0xEC_isReadyXFirstConf() {
      byte[] codedFrameEC = new byte[10];
      byte[] uncodedFrameEC = new byte[4];
      codedFrameEC[0] = 2;
      byte lenghtPayload = 1;
      uncodedFrameEC[0] = lenghtPayload;
      byte opcode = -122;
      uncodedFrameEC[1] = opcode;
      uncodedFrameEC[2] = -20;
      byte[] payload0xEC = new byte[lenghtPayload];
      System.arraycopy(uncodedFrameEC, 2, payload0xEC, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0xEC, opcode);
      uncodedFrameEC[3] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrameEC);
      System.arraycopy(res, 0, codedFrameEC, 1, res.length);
      codedFrameEC[9] = 3;
      return codedFrameEC;
   }

   public static byte[] getCedacRemoteFrame0xE0_FirstConfStart(byte[] destAddress_MSBfirst) {
      byte[] codedFrameE0 = new byte[26];
      byte[] uncodedFrameE0 = new byte[12];
      codedFrameE0[0] = 2;
      byte lenghtPayload = 9;
      uncodedFrameE0[0] = lenghtPayload;
      byte opcode = 6;
      uncodedFrameE0[1] = opcode;
      uncodedFrameE0[2] = destAddress_MSBfirst[0];
      uncodedFrameE0[3] = destAddress_MSBfirst[1];
      uncodedFrameE0[4] = destAddress_MSBfirst[2];
      uncodedFrameE0[5] = destAddress_MSBfirst[3];
      uncodedFrameE0[6] = -32;
      uncodedFrameE0[7] = 70;
      uncodedFrameE0[8] = 65;
      uncodedFrameE0[9] = 65;
      uncodedFrameE0[10] = 67;
      byte[] payload0xE0 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrameE0, 2, payload0xE0, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0xE0, opcode);
      uncodedFrameE0[11] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrameE0);
      System.arraycopy(res, 0, codedFrameE0, 1, res.length);
      codedFrameE0[25] = 3;
      return codedFrameE0;
   }

   public static byte[] getCedacRemoteFrame0xE2_FirstConfSet(byte[] destAddress_MSBfirst, String XcomName) {
      int nameLegth = XcomName.length();
      int lenghtPayload = 6 + nameLegth;
      int cksCodedFirstIndex = 17 + 2 * lenghtPayload;
      int EoFindex = cksCodedFirstIndex + 2;
      byte[] codedFrameE2 = new byte[EoFindex + 1];
      byte[] uncodedFrameE2 = new byte[(EoFindex + 1 - 2) / 2];
      codedFrameE2[0] = 2;
      uncodedFrameE2[0] = (byte)lenghtPayload;
      byte opcode = 6;
      uncodedFrameE2[1] = opcode;
      uncodedFrameE2[2] = destAddress_MSBfirst[0];
      uncodedFrameE2[3] = destAddress_MSBfirst[1];
      uncodedFrameE2[4] = destAddress_MSBfirst[2];
      uncodedFrameE2[5] = destAddress_MSBfirst[3];
      uncodedFrameE2[6] = -30;
      byte numTerminal = 1;
      uncodedFrameE2[7] = numTerminal;
      byte[] nameBytes = XcomName.getBytes();
      System.arraycopy(nameBytes, 0, uncodedFrameE2, 8, nameBytes.length);
      byte[] payload0xE2 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrameE2, 2, payload0xE2, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0xE2, opcode);
      uncodedFrameE2[8 + nameLegth] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrameE2);
      System.arraycopy(res, 0, codedFrameE2, 1, res.length);
      codedFrameE2[EoFindex] = 3;
      return codedFrameE2;
   }

   public static byte[] getCedacRemoteFrame0xE3_RequestAuthorization(byte[] destAddress_MSBfirst) {
      byte[] codedFrameE3 = new byte[18];
      byte[] uncodedFrameE3 = new byte[8];
      codedFrameE3[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrameE3[0] = lenghtPayload;
      byte opcode = 6;
      uncodedFrameE3[1] = opcode;
      uncodedFrameE3[2] = destAddress_MSBfirst[0];
      uncodedFrameE3[3] = destAddress_MSBfirst[1];
      uncodedFrameE3[4] = destAddress_MSBfirst[2];
      uncodedFrameE3[5] = destAddress_MSBfirst[3];
      uncodedFrameE3[6] = -29;
      byte[] payload0xE3 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrameE3, 2, payload0xE3, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0xE3, opcode);
      uncodedFrameE3[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrameE3);
      System.arraycopy(res, 0, codedFrameE3, 1, res.length);
      codedFrameE3[17] = 3;
      return codedFrameE3;
   }

   public static byte[] getCedacRemoteFrame0xE7_GetPresents() {
      byte[] codedFrameE7 = new byte[10];
      byte[] uncodedFrameE7 = new byte[4];
      codedFrameE7[0] = 2;
      byte lenghtPayload = 1;
      uncodedFrameE7[0] = lenghtPayload;
      byte opcode = -122;
      uncodedFrameE7[1] = opcode;
      uncodedFrameE7[2] = -25;
      byte[] payload0xE7 = new byte[lenghtPayload];
      System.arraycopy(uncodedFrameE7, 2, payload0xE7, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0xE7, opcode);
      uncodedFrameE7[3] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrameE7);
      System.arraycopy(res, 0, codedFrameE7, 1, res.length);
      codedFrameE7[9] = 3;
      return codedFrameE7;
   }

   public static byte[] getCedacRemoteFrame0xED_GetNameByAddress(byte[] destAddress_MSBfirst) {
      byte[] codedFrameED = new byte[18];
      byte[] uncodedFrameED = new byte[8];
      codedFrameED[0] = 2;
      byte lenghtPayload = 5;
      uncodedFrameED[0] = lenghtPayload;
      byte opcode = 6;
      uncodedFrameED[1] = opcode;
      uncodedFrameED[2] = destAddress_MSBfirst[0];
      uncodedFrameED[3] = destAddress_MSBfirst[1];
      uncodedFrameED[4] = destAddress_MSBfirst[2];
      uncodedFrameED[5] = destAddress_MSBfirst[3];
      uncodedFrameED[6] = -19;
      byte[] payload0xED = new byte[lenghtPayload];
      System.arraycopy(uncodedFrameED, 2, payload0xED, 0, lenghtPayload);
      byte bCksum = FAAC_Protocol.CheckSum(payload0xED, opcode);
      uncodedFrameED[7] = bCksum;
      byte[] res = FAAC_Protocol.intelHexEncoderDataFlow(uncodedFrameED);
      System.arraycopy(res, 0, codedFrameED, 1, res.length);
      codedFrameED[17] = 3;
      return codedFrameED;
   }

   public static byte parseFrameCedacSimpleReply(byte[] frame2parse) throws Exception {
      byte[] codedAnswerOpcode = new byte[]{frame2parse[3], frame2parse[4]};

      try {
         return FAAC_Protocol.intelHexDecoder(codedAnswerOpcode);
      } catch (Exception var4) {
         Debug.printlnStatic(var4.toString());
         throw var4;
      }
   }

   public static void parseFrameCedacInquiryReply(byte[] frame2parse, FAAC_XCOM xcomObj) throws Exception {
      byte[] codedId = new byte[16];

      for (int i = 0; i < 16; i++) {
         codedId[i] = frame2parse[i + 5];
      }

      byte[] decodedId;
      try {
         decodedId = FAAC_Protocol.intelHexDecoderDataFlow(codedId);
      } catch (Exception var12) {
         Debug.printlnStatic(var12.toString());
         throw var12;
      }

      int zerofinalcharCounter = 0;

      for (int i = 0; i < 8 && decodedId[7 - i] == 0; i++) {
         zerofinalcharCounter++;
      }

      if (zerofinalcharCounter < 8) {
         xcomObj.setId_XcomBox(new String(decodedId).substring(0, 8 - zerofinalcharCounter));
      } else {
         xcomObj.setId_XcomBox("");
      }

      byte[] codedverSw = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedverSw[i] = frame2parse[i + 21];
      }

      byte decodedverSw;
      try {
         decodedverSw = FAAC_Protocol.intelHexDecoder(codedverSw);
      } catch (Exception var11) {
         Debug.printlnStatic(var11.toString());
         throw var11;
      }

      xcomObj.setVerSw_XcomBox(decodedverSw);
      byte[] codedrevSw = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedrevSw[i] = frame2parse[i + 23];
      }

      byte decodedrevSw;
      try {
         decodedrevSw = FAAC_Protocol.intelHexDecoder(codedrevSw);
      } catch (Exception var10) {
         Debug.printlnStatic(var10.toString());
         throw var10;
      }

      xcomObj.setRevSw_XcomBox(decodedrevSw);
   }

   public static void parseFrameCedacIsReadyXFirstConfReply(byte[] frame2parse, FAAC_XCOM xcomObj) throws Exception {
      byte[] codedAddress = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedAddress[i] = frame2parse[i + 7];
      }

      byte[] decodedAddress;
      try {
         decodedAddress = FAAC_Protocol.intelHexDecoderDataFlow(codedAddress);
      } catch (Exception var5) {
         Debug.printlnStatic(var5.toString());
         throw var5;
      }

      xcomObj.setAddress_Xcom_MSBfirst(decodedAddress);
   }

   public static void parseFrameCedacGetPresentsReply(byte[] frame2parse, FAAC_XCOM xcomObj) throws Exception {
      byte[] codedLength = new byte[2];

      for (int i = 0; i < 2; i++) {
         codedLength[i] = frame2parse[i + 1];
      }

      byte decodedLength;
      try {
         decodedLength = FAAC_Protocol.intelHexDecoder(codedLength);
      } catch (Exception var10) {
         Debug.printlnStatic(var10.toString());
         throw var10;
      }

      int n = (decodedLength - 1) / 4;
      xcomObj.emptyXCOMlist();

      for (int iNode = 0; iNode < n; iNode++) {
         byte[] codedAddress = new byte[8];

         for (int i = 0; i < 8; i++) {
            codedAddress[i] = frame2parse[i * (iNode + 1) + 7];
         }

         byte[] decodedAddress;
         try {
            decodedAddress = FAAC_Protocol.intelHexDecoderDataFlow(codedAddress);
         } catch (Exception var9) {
            Debug.printlnStatic(var9.toString());
            throw var9;
         }

         FAAC_XCOM.xcomNameAddress xcomRemote = new FAAC_XCOM.xcomNameAddress();
         xcomRemote.setAddress_MSBfirst(decodedAddress);
         xcomObj.getPresentXCOMlist().add(xcomRemote);
      }
   }

   public static void parseFrameCedacGetNameByAddressReply(byte[] frame2parse, FAAC_XCOM xcomObj, byte[] requestedAddress_MSBfirst) throws Exception {
      int length = frame2parse.length;
      int lengthName = length - 8;
      byte[] codedName = new byte[lengthName];

      for (int i = 0; i < lengthName; i++) {
         codedName[i] = frame2parse[i + 5];
      }

      byte[] decodedName;
      try {
         decodedName = FAAC_Protocol.intelHexDecoderDataFlow(codedName);
      } catch (Exception var12) {
         Debug.printlnStatic(var12.toString());
         throw var12;
      }

      int foundIndex = -1;

      for (int i = 0; i < xcomObj.getPresentXCOMlist().size(); i++) {
         byte[] ithAddress = ((FAAC_XCOM.xcomNameAddress)xcomObj.getPresentXCOMlist().get(i)).getAddress_MSBfirst();
         boolean isEqual = true;

         for (int iByte = 0; iByte < 4; iByte++) {
            if (ithAddress[i] != requestedAddress_MSBfirst[i]) {
               isEqual = false;
            }
         }

         if (isEqual) {
            foundIndex = i;
            break;
         }
      }

      if (foundIndex != -1) {
         ((FAAC_XCOM.xcomNameAddress)xcomObj.getPresentXCOMlist().get(foundIndex)).setName(new String(decodedName));
      }
   }

   public static void parseFrameCedacSDAactiveReply(byte[] frame2parse, FAAC_XCOM xcomObj) throws Exception {
      byte[] codedSDA = new byte[8];

      for (int i = 0; i < 8; i++) {
         codedSDA[i] = frame2parse[i + 5];
      }

      byte[] decodedSDA;
      try {
         decodedSDA = FAAC_Protocol.intelHexDecoderDataFlow(codedSDA);
      } catch (Exception var5) {
         Debug.printlnStatic(var5.toString());
         throw var5;
      }

      xcomObj.setStdDestAddress_MSBfirst(decodedSDA);
   }
}
