package FAAClib;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Union8_Abstraction {
   boolean[] bits = new boolean[8];

   public Union8_Abstraction() {
   }

   public Union8_Abstraction(byte byteIn) {
      this.setByte(byteIn);
   }

   public boolean[] getBits() {
      return this.bits;
   }

   public boolean isSomeBitsHigh() {
      for (int i = 0; i < this.bits.length; i++) {
         if (this.bits[i]) {
            return true;
         }
      }

      return false;
   }

   public final void setByte(byte byteIn) {
      for (int i = 0; i < 8; i++) {
         try {
            this.bits[i] = FAAC_Protocol.isBitActiveInByte(byteIn, i);
         } catch (Exception var6) {
            Exception ex = var6;
            Logger.getLogger(Union8_Abstraction.class.getName()).log(Level.SEVERE, null, var6);
            Debug.printlnStatic("Eccezione Union8_Abstraction: " + var6.toString());

            try {
               throw ex;
            } catch (Exception var5) {
               Logger.getLogger(Union8_Abstraction.class.getName()).log(Level.SEVERE, null, var5);
            }
         }
      }
   }

   public byte getByte() {
      byte mask = 0;
      if (this.bits[7]) {
         byte tmpMask = -128;
         mask |= tmpMask;
      }

      if (this.bits[6]) {
         byte tmpMask = 64;
         mask |= tmpMask;
      }

      if (this.bits[5]) {
         byte tmpMask = 32;
         mask |= tmpMask;
      }

      if (this.bits[4]) {
         byte tmpMask = 16;
         mask |= tmpMask;
      }

      if (this.bits[3]) {
         byte tmpMask = 8;
         mask |= tmpMask;
      }

      if (this.bits[2]) {
         byte tmpMask = 4;
         mask |= tmpMask;
      }

      if (this.bits[1]) {
         byte tmpMask = 2;
         mask |= tmpMask;
      }

      if (this.bits[0]) {
         byte tmpMask = 1;
         mask |= tmpMask;
      }

      return mask;
   }
}
