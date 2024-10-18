package FAAClib;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Union16_Abstraction {
   boolean[] bits = new boolean[16];

   public Union16_Abstraction() {
   }

   public Union16_Abstraction(byte[] byteArrayIn) {
      this.setBytes_MSBfirst(byteArrayIn);
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

   public final void setBytes_MSBfirst(byte[] byteArrayIn) {
      for (int i = 0; i < 16; i++) {
         try {
            this.bits[i] = FAAC_Protocol.isBitActiveIn2bytes_MSBfirst(byteArrayIn, i);
         } catch (Exception var6) {
            Exception ex = var6;
            Logger.getLogger(Union16_Abstraction.class.getName()).log(Level.SEVERE, null, var6);
            Debug.printlnStatic("Eccezione Union16_Abstraction: " + var6.toString());

            try {
               throw ex;
            } catch (Exception var5) {
               Logger.getLogger(Union16_Abstraction.class.getName()).log(Level.SEVERE, null, var5);
            }
         }
      }
   }

   public final void setBytes_LSBfirst(byte[] byteArrayIn) {
      for (int i = 0; i < 16; i++) {
         try {
            this.bits[i] = FAAC_Protocol.isBitActiveIn2bytes_LSBfirst(byteArrayIn, i);
         } catch (Exception var6) {
            Exception ex = var6;
            Logger.getLogger(Union16_Abstraction.class.getName()).log(Level.SEVERE, null, var6);
            Debug.printlnStatic("Eccezione Union16_Abstraction: " + var6.toString());

            try {
               throw ex;
            } catch (Exception var5) {
               Logger.getLogger(Union16_Abstraction.class.getName()).log(Level.SEVERE, null, var5);
            }
         }
      }
   }

   public byte[] getBytes_MSBfirst() {
      byte[] out = new byte[2];

      for (int iByte = 0; iByte < 2; iByte++) {
         byte mask = 0;
         if (this.bits[iByte * 8 + 7]) {
            byte tmpMask = -128;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 6]) {
            byte tmpMask = 64;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 5]) {
            byte tmpMask = 32;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 4]) {
            byte tmpMask = 16;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 3]) {
            byte tmpMask = 8;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 2]) {
            byte tmpMask = 4;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 1]) {
            byte tmpMask = 2;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 0]) {
            byte tmpMask = 1;
            mask |= tmpMask;
         }

         out[1 - iByte] = mask;
      }

      return out;
   }

   public byte[] getBytes_LSBfirst() {
      byte[] out = new byte[2];

      for (int iByte = 0; iByte < 2; iByte++) {
         byte mask = 0;
         if (this.bits[iByte * 8 + 7]) {
            byte tmpMask = -128;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 6]) {
            byte tmpMask = 64;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 5]) {
            byte tmpMask = 32;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 4]) {
            byte tmpMask = 16;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 3]) {
            byte tmpMask = 8;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 2]) {
            byte tmpMask = 4;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 1]) {
            byte tmpMask = 2;
            mask |= tmpMask;
         }

         if (this.bits[iByte * 8 + 0]) {
            byte tmpMask = 1;
            mask |= tmpMask;
         }

         out[iByte] = mask;
      }

      return out;
   }
}
