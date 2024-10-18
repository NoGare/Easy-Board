package FAAClib;

public class FAAC_Protocol {
   public static final byte SoF = 2;
   public static final byte EoF = 3;
   public static final byte CommandCedac = 0;
   public static final String DefaultPassword = "0000";

   public static synchronized byte CheckSum(byte[] payloadFrame, byte commandCedac) {
      int payloadLength = payloadFrame.length;
      int cksum = 1 + commandCedac;

      for (int i = 0; i < payloadLength; i++) {
         cksum += payloadFrame[i];
      }

      return (byte)(~cksum);
   }

   public static synchronized boolean CheckSumProof(byte[] receivedFrame) throws Exception {
      int length = receivedFrame.length;
      byte[] frameToBeDecoded = new byte[length - 2];

      for (int i = 0; i < length - 2; i++) {
         frameToBeDecoded[i] = receivedFrame[i + 1];
      }

      byte[] decodedFrame;
      try {
         decodedFrame = intelHexDecoderDataFlow(frameToBeDecoded);
      } catch (Exception var13) {
         Debug.printlnStatic("Exception in CheckSumProof causata intelHexDecoderDataFlow ->" + var13.toString());
         throw var13;
      }

      int lengthDec = decodedFrame.length;
      int cksum = 1;

      for (int i = 1; i < lengthDec - 1; i++) {
         cksum += decodedFrame[i];
      }

      byte bCks = (byte)(~cksum);
      byte cks1 = receivedFrame[length - 3];
      byte cks2 = receivedFrame[length - 2];
      byte[] codedCks = new byte[]{cks1, cks2};

      byte decodedRxCks;
      try {
         decodedRxCks = intelHexDecoder(codedCks);
      } catch (Exception var12) {
         Debug.printlnStatic("Exception in CheckSumProof causata intelHexDecoder ->" + var12.toString());
         throw var12;
      }

      return bCks == decodedRxCks;
   }

   public static synchronized boolean CheckSumProofUncoded(byte[] byte2proof) {
      int length = byte2proof.length;
      int cksum = 1;

      for (int i = 0; i < length - 1; i++) {
         cksum += byte2proof[i];
      }

      byte bCks = (byte)(~cksum);
      byte decodedRxCks = byte2proof[byte2proof.length - 1];
      return bCks == decodedRxCks;
   }

   public static synchronized boolean CheckHeaderFile(byte[] header, String password, String boardModel, String extension) throws Exception {
      boolean bRes = true;
      String str = boardModel.toUpperCase();
      if (str.length() > 0) {
         byte[] boardName = str.getBytes();

         for (int i = 0; i < boardName.length; i++) {
            byte tmp = FileSplitByteArrayToByte(new byte[]{header[i * 2], header[i * 2 + 1]});
            if (boardName[i] != tmp) {
               return false;
            }
         }
      }

      byte[] psw = password.getBytes();
      if (header[16] != 2) {
         return false;
      } else if (header[17] != 14) {
         return false;
      } else {
         if (extension.toLowerCase().equals(FAAC_frames.FILE_EXT_PRG.toLowerCase())) {
            if (header[18] != 7) {
               return false;
            }

            if (header[19] != 0) {
               return false;
            }

            if (header[20] != 7) {
               return false;
            }

            if (header[21] != 2) {
               return false;
            }

            if (header[22] != 6) {
               return false;
            }

            if (header[23] != 7) {
               return false;
            }
         } else if (extension.toLowerCase().equals(FAAC_frames.FILE_EXT_TMR.toLowerCase())) {
            if (header[18] != 7) {
               return false;
            }

            if (header[19] != 4) {
               return false;
            }

            if (header[20] != 6) {
               return false;
            }

            if (header[21] != 13) {
               return false;
            }

            if (header[22] != 7) {
               return false;
            }

            if (header[23] != 2) {
               return false;
            }
         } else if (extension.toLowerCase().equals(FAAC_frames.FILE_EXT_RAD.toLowerCase())) {
            if (header[18] != 7) {
               return false;
            }

            if (header[19] != 2) {
               return false;
            }

            if (header[20] != 6) {
               return false;
            }

            if (header[21] != 1) {
               return false;
            }

            if (header[22] != 6) {
               return false;
            }

            if (header[23] != 4) {
               return false;
            }
         } else if (extension.toLowerCase().equals(FAAC_frames.FILE_EXT_GCOM.toLowerCase())) {
            if (header[18] != 7) {
               return false;
            }

            if (header[19] != 3) {
               return false;
            }

            if (header[20] != 6) {
               return false;
            }

            if (header[21] != 7) {
               return false;
            }

            if (header[22] != 6) {
               return false;
            }

            if (header[23] != 3) {
               return false;
            }
         }

         return bRes;
      }
   }

   public static synchronized boolean CheckRadFile(byte[] byte2check, String password, String boardModel) throws Exception {
      boolean bRes = true;
      byte[] header = new byte[24];
      System.arraycopy(byte2check, 0, header, 0, 24);
      return CheckHeaderFile(header, password, boardModel, FAAC_frames.FILE_EXT_RAD.toLowerCase());
   }

   public static synchronized boolean CheckTmrFile(byte[] byte2check, String password, String boardModel) throws Exception {
      boolean bRes = true;
      byte[] header = new byte[24];
      System.arraycopy(byte2check, 0, header, 0, 24);
      bRes = CheckHeaderFile(header, password, boardModel, FAAC_frames.FILE_EXT_TMR.toLowerCase());
      if (!bRes) {
         return false;
      } else {
         for (int iDay = 0; iDay < 8; iDay++) {
            byte[] timerTmp = new byte[32];

            for (int i = 0; i < 32; i++) {
               timerTmp[i] = byte2check[i + 24 + 32 * iDay];
            }

            bRes = CheckSumProofUncoded(timerTmp);
            if (!bRes) {
               return false;
            }
         }

         return true;
      }
   }

   public static synchronized boolean CheckPrgFile(byte[] byte2check, String password, String boardModel) throws Exception {
      boolean bRes = true;
      byte[] header = new byte[24];
      System.arraycopy(byte2check, 0, header, 0, 24);
      bRes = CheckHeaderFile(header, password, boardModel, FAAC_frames.FILE_EXT_PRG.toLowerCase());
      if (!bRes) {
         return false;
      } else {
         byte[] prog1 = new byte[32];

         for (int i = 0; i < 32; i++) {
            prog1[i] = byte2check[i + 24];
         }

         bRes = CheckSumProofUncoded(prog1);
         if (!bRes) {
            return false;
         } else {
            byte[] prog2 = new byte[32];

            for (int i = 0; i < 32; i++) {
               prog2[i] = byte2check[i + 56];
            }

            bRes = CheckSumProofUncoded(prog2);
            if (!bRes) {
               return false;
            } else {
               byte[] prog3 = new byte[32];

               for (int i = 0; i < 32; i++) {
                  prog3[i] = byte2check[i + 88];
               }

               bRes = CheckSumProofUncoded(prog3);
               if (!bRes) {
                  return false;
               } else {
                  byte[] prog4 = new byte[32];

                  for (int i = 0; i < 32; i++) {
                     prog4[i] = byte2check[i + 120];
                  }

                  return CheckSumProofUncoded(prog4);
               }
            }
         }
      }
   }

   public static synchronized boolean CheckGcomFile(byte[] byte2check, String password) throws Exception {
      int index = 0;
      int phonebookFrameNumber = 5;
      boolean bRes = true;
      byte[] header = new byte[24];
      System.arraycopy(byte2check, index, header, 0, header.length);
      bRes = CheckHeaderFile(header, password, "GCOM", FAAC_frames.FILE_EXT_GCOM);
      if (!bRes) {
         return false;
      } else {
         index += header.length;
         byte[] gcomParamBytes = new byte[39];
         System.arraycopy(byte2check, index, gcomParamBytes, 0, gcomParamBytes.length);
         bRes = CheckSumProofUncoded(gcomParamBytes);
         if (!bRes) {
            return false;
         } else {
            index += gcomParamBytes.length;
            byte[] gcomSimBytes = new byte[17];
            System.arraycopy(byte2check, index, gcomSimBytes, 0, gcomSimBytes.length);
            bRes = CheckSumProofUncoded(gcomSimBytes);
            if (!bRes) {
               return false;
            } else {
               index += gcomSimBytes.length;
               byte[] gcomPhoneBookBytes = new byte[75];

               for (int i = 0; i < phonebookFrameNumber; i++) {
                  System.arraycopy(byte2check, index, gcomPhoneBookBytes, 0, gcomPhoneBookBytes.length);
                  bRes = CheckSumProofUncoded(gcomPhoneBookBytes);
                  if (!bRes) {
                     return false;
                  }

                  index += gcomPhoneBookBytes.length;
               }

               return true;
            }
         }
      }
   }

   public static synchronized byte CheckFrame(byte[] frame2check) throws Exception {
      byte errReturn = 0;
      int length = frame2check.length;
      if (frame2check[0] != 2) {
         return errReturn;
      } else if (frame2check[length - 1] != 3) {
         return errReturn;
      } else {
         boolean isCksCorrect;
         try {
            isCksCorrect = CheckSumProof(frame2check);
         } catch (Exception var10) {
            Debug.printlnStatic("CheckFrame" + var10.toString());
            throw var10;
         }

         if (!isCksCorrect) {
            return errReturn;
         } else {
            byte codedFrameType1 = frame2check[5];
            byte codedFrameType2 = frame2check[6];
            byte[] codedFrameType = new byte[]{codedFrameType1, codedFrameType2};

            try {
               return intelHexDecoder(codedFrameType);
            } catch (Exception var9) {
               Debug.printlnStatic("CheckFrame" + var9.toString());
               throw var9;
            }
         }
      }
   }

   public static synchronized byte CheckCedacFrame(byte[] frame2check) throws Exception {
      byte errReturn = 0;
      int length = frame2check.length;
      if (frame2check[0] != 2) {
         return errReturn;
      } else if (frame2check[length - 1] != 3) {
         return errReturn;
      } else {
         boolean isCksCorrect;
         try {
            isCksCorrect = CheckSumProof(frame2check);
         } catch (Exception var10) {
            Debug.printlnStatic("CheckFrame" + var10.toString());
            throw var10;
         }

         if (!isCksCorrect) {
            return errReturn;
         } else {
            byte codedFrameType1 = frame2check[3];
            byte codedFrameType2 = frame2check[4];
            byte[] codedFrameType = new byte[]{codedFrameType1, codedFrameType2};

            try {
               return intelHexDecoder(codedFrameType);
            } catch (Exception var9) {
               Debug.printlnStatic("CheckCedacFrame" + var9.toString());
               throw var9;
            }
         }
      }
   }

   public static synchronized byte[] intTo4bytes_MSBfirst(int iIn) {
      return new byte[]{(byte)(iIn / 16777216), (byte)(iIn / 65536), (byte)(iIn / 256), (byte)iIn};
   }

   public static synchronized byte[] intTo4bytes_LSBfirst(int iIn) {
      return new byte[]{(byte)iIn, (byte)(iIn / 256), (byte)(iIn / 65536), (byte)(iIn / 16777216)};
   }

   public static synchronized int fourBytesToInt_MSBfirst(byte[] byteArrayIn) {
      int iOut = 0;
      iOut += byteArrayIn[3];
      iOut += byteArrayIn[2] * 256;
      iOut += byteArrayIn[1] * 65536;
      return iOut + byteArrayIn[0] * 16777216;
   }

   public static synchronized int fourBytesToInt_LSBfirst(byte[] byteArrayIn) {
      int iOut = 0;
      iOut += byteArrayIn[0];
      iOut += byteArrayIn[1] * 256;
      iOut += byteArrayIn[2] * 65536;
      return iOut + byteArrayIn[3] * 16777216;
   }

   public static synchronized boolean isBitActiveIn4bytes_MSBfirst(byte[] inByteArray, int index) {
      int boundedIndex = index % 8;
      boolean btmp = false;
      if (index < 8) {
         try {
            btmp = isBitActiveInByte(inByteArray[3], boundedIndex);
         } catch (Exception var8) {
            Debug.printlnStatic("isBitActiveIn4bytes quarto byte" + var8.toString());
         }
      } else if (index < 16) {
         try {
            btmp = isBitActiveInByte(inByteArray[2], boundedIndex);
         } catch (Exception var7) {
            Debug.printlnStatic("isBitActiveIn4bytes terzo byte" + var7.toString());
         }
      } else if (index < 24) {
         try {
            btmp = isBitActiveInByte(inByteArray[1], boundedIndex);
         } catch (Exception var6) {
            Debug.printlnStatic("isBitActiveIn4bytes secondo byte" + var6.toString());
         }
      } else if (index < 32) {
         try {
            btmp = isBitActiveInByte(inByteArray[0], boundedIndex);
         } catch (Exception var5) {
            Debug.printlnStatic("isBitActiveIn4bytes primo byte" + var5.toString());
         }
      }

      return btmp;
   }

   public static synchronized boolean isBitActiveIn4bytes_LSBfirst(byte[] inByteArray, int index) {
      int boundedIndex = index % 8;
      boolean btmp = false;
      if (index < 8) {
         try {
            btmp = isBitActiveInByte(inByteArray[0], boundedIndex);
         } catch (Exception var8) {
            Debug.printlnStatic("isBitActiveIn4bytes primo byte" + var8.toString());
         }
      } else if (index < 16) {
         try {
            btmp = isBitActiveInByte(inByteArray[1], boundedIndex);
         } catch (Exception var7) {
            Debug.printlnStatic("isBitActiveIn4bytes secondo byte" + var7.toString());
         }
      } else if (index < 24) {
         try {
            btmp = isBitActiveInByte(inByteArray[2], boundedIndex);
         } catch (Exception var6) {
            Debug.printlnStatic("isBitActiveIn4bytes terzo byte" + var6.toString());
         }
      } else if (index < 32) {
         try {
            btmp = isBitActiveInByte(inByteArray[3], boundedIndex);
         } catch (Exception var5) {
            Debug.printlnStatic("isBitActiveIn4bytes quarto byte" + var5.toString());
         }
      }

      return btmp;
   }

   public static synchronized boolean isBitActiveInByte(byte inByte, int index) throws Exception {
      byte mask = 0;
      byte zeroMask = 0;
      switch (index) {
         case 0:
            mask = 1;
            break;
         case 1:
            mask = 2;
            break;
         case 2:
            mask = 4;
            break;
         case 3:
            mask = 8;
            break;
         case 4:
            mask = 16;
            break;
         case 5:
            mask = 32;
            break;
         case 6:
            mask = 64;
            break;
         case 7:
            mask = -128;
      }

      byte res = (byte)(inByte & mask);
      if (res == zeroMask) {
         return false;
      } else if (res == mask) {
         return true;
      } else {
         throw new Exception("isBitActiveInByte: Error while performing bit wise AND operation");
      }
   }

   public static synchronized boolean isBitActiveInByte_LSbitFirst(byte inByte, int index) throws Exception {
      byte mask = 0;
      byte zeroMask = 0;
      switch (index) {
         case 0:
            mask = -128;
            break;
         case 1:
            mask = 64;
            break;
         case 2:
            mask = 32;
            break;
         case 3:
            mask = 16;
            break;
         case 4:
            mask = 8;
            break;
         case 5:
            mask = 4;
            break;
         case 6:
            mask = 2;
            break;
         case 7:
            mask = 1;
      }

      byte res = (byte)(inByte & mask);
      if (res == zeroMask) {
         return false;
      } else if (res == mask) {
         return true;
      } else {
         throw new Exception("isBitActiveInByte: Error while performing bit wise AND operation");
      }
   }

   public static synchronized boolean isBitActiveIn2bytes_MSBfirst(byte[] inByteArray, int index) {
      int boundedIndex = index % 8;
      boolean btmp = false;
      if (index < 8) {
         try {
            btmp = isBitActiveInByte(inByteArray[1], boundedIndex);
         } catch (Exception var6) {
            Debug.printlnStatic("isBitActiveIn2bytes secondo byte" + var6.toString());
         }
      } else if (index < 16) {
         try {
            btmp = isBitActiveInByte(inByteArray[0], boundedIndex);
         } catch (Exception var5) {
            Debug.printlnStatic("isBitActiveIn2bytes primo byte" + var5.toString());
         }
      }

      return btmp;
   }

   public static synchronized boolean isBitActiveIn2bytes_LSBfirst(byte[] inByteArray, int index) {
      int boundedIndex = index % 8;
      boolean btmp = false;
      if (index < 8) {
         try {
            btmp = isBitActiveInByte(inByteArray[0], boundedIndex);
         } catch (Exception var6) {
            Debug.printlnStatic("isBitActiveIn2bytes primo byte" + var6.toString());
         }
      } else if (index < 16) {
         try {
            btmp = isBitActiveInByte(inByteArray[1], boundedIndex);
         } catch (Exception var5) {
            Debug.printlnStatic("isBitActiveIn2bytes secondo byte" + var5.toString());
         }
      }

      return btmp;
   }

   public static synchronized String StringToHex(String stringToEncode) {
      StringBuilder temp = new StringBuilder();
      String hexchar = "";

      for (int i = 0; i < stringToEncode.length(); i++) {
         int k = stringToEncode.charAt(i);
         hexchar = Integer.toHexString(k);
         if (hexchar.length() < 2) {
            temp.append("0").append(hexchar);
         } else {
            temp.append(hexchar);
         }
      }

      return temp.toString().toUpperCase();
   }

   public static synchronized byte[] intelHexEncoder(byte input) {
      String hexint = Integer.toHexString(input);
      StringBuilder temp = new StringBuilder();
      String hexchar;
      if (hexint.length() < 2) {
         temp.append("0").append(hexint);
         hexchar = temp.toString();
      } else if (hexint.length() == 2) {
         hexchar = hexint;
      } else {
         hexchar = hexint.substring(6);
      }

      return hexchar.toUpperCase().getBytes();
   }

   public static synchronized String byteToHexString(byte input) {
      StringBuilder temp = new StringBuilder();
      String hexchar = Integer.toHexString(input);
      String strB = "";
      if (hexchar.length() < 2) {
         temp.append("0").append(hexchar);
         strB = temp.toString();
      } else if (hexchar.length() == 2) {
         temp.append(hexchar);
         strB = temp.toString();
      } else {
         strB = hexchar.substring(6);
      }

      return "0x" + strB.toUpperCase();
   }

   public static synchronized byte[] intelHexEncoderDataFlow(byte[] inputArray) {
      int length = inputArray.length;
      byte[] res = new byte[length * 2];

      for (int i = 0; i < length; i++) {
         StringBuffer temp = new StringBuffer();
         String hexint = Integer.toHexString(inputArray[i]);
         String hexchar;
         if (hexint.length() < 2) {
            temp.append("0").append(hexint);
            hexchar = temp.toString();
         } else if (hexint.length() == 2) {
            hexchar = hexint;
         } else {
            hexchar = hexint.substring(6);
         }

         byte[] tmp = hexchar.toUpperCase().getBytes();
         res[2 * i] = tmp[0];
         res[2 * i + 1] = tmp[1];
      }

      return res;
   }

   public static synchronized byte intelHexDecoder(byte[] input) throws Exception {
      String strInput = new String(input);

      try {
         int tmp = Integer.parseInt(strInput, 16);
         return (byte)tmp;
      } catch (NumberFormatException var4) {
         Debug.printlnStatic("Exception in intelHexDecoder ->" + var4.toString());
         throw new Exception("intelHexDecoder: Error while decoding the byte array " + new String(input));
      }
   }

   public static int unsignedByteToInt(byte b) {
      return b & 0xFF;
   }

   public static synchronized byte[] intelHexDecoderDataFlow(byte[] inputArray) throws Exception {
      int length = inputArray.length;
      if (length % 2 != 0) {
         length--;
      }

      int resLength = length / 2;
      byte[] res = new byte[resLength];

      for (int i = 0; i < resLength; i++) {
         byte[] input = new byte[]{inputArray[i * 2], inputArray[i * 2 + 1]};
         String strInput = new String(input);

         try {
            int tmp = Integer.parseInt(strInput, 16);
            res[i] = (byte)tmp;
         } catch (NumberFormatException var8) {
            Debug.printlnStatic(var8.toString());
            throw new Exception("intelHexDecoderDataFlow: Error while decoding the byte array " + new String(inputArray));
         }
      }

      return res;
   }

   public static int byteArrayToInt(byte[] b) {
      return byteArrayToInt(b, 0);
   }

   public static int byteArrayToInt(byte[] b, int offset) {
      int value = 0;

      for (int i = 0; i < b.length; i++) {
         int shift = (b.length - 1 - i) * 8;
         value += (b[i + offset] & 255) << shift;
      }

      return value;
   }

   public static byte[] intToByteArray(int value) {
      return new byte[]{(byte)(value >>> 24), (byte)(value >>> 16), (byte)(value >>> 8), (byte)value};
   }

   public static int byteToInt_BCDformat(byte bBCD) {
      String str = Integer.toHexString(bBCD);
      if (str.length() == 1) {
         str = "0" + str;
      }

      try {
         return Integer.parseInt(str);
      } catch (Exception var3) {
         return 0;
      }
   }

   public static byte intToByte_BCDformat(int iBCD) {
      String str = Integer.toString(iBCD);
      if (str.length() == 1) {
         str = "0" + str;
      }

      return (byte)Integer.parseInt(str, 16);
   }

   public static byte[] byteToFileSplitByteArray(byte bChar) {
      return new byte[]{(byte)((byte)(bChar & -16) >> 4), (byte)(bChar & 15)};
   }

   public static byte FileSplitByteArrayToByte(byte[] byteSplit) {
      byte tmp1 = (byte)(byteSplit[0] << 4 & -16);
      byte tmp2 = (byte)(byteSplit[1] & 15);
      return (byte)(tmp1 | tmp2);
   }

   public static boolean isStringNumeric(String str) {
      boolean bRes = true;
      int length = str.length();
      char[] chTmp = new char[length];
      str.getChars(0, length, chTmp, 0);

      for (int i = 0; i < length; i++) {
         bRes = Character.isDigit(chTmp[i]);
         if (!bRes) {
            return false;
         }
      }

      return bRes;
   }
}
