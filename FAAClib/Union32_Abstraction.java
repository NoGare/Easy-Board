package FAAClib;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Union32_Abstraction {
   boolean[] bits = new boolean[32];

   public Union32_Abstraction() {
   }

   public Union32_Abstraction(byte[] byteArrayIn) {
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

   public final void setBytes_LSBfirst(byte[] byteArrayIn) {
      for (int i = 0; i < 32; i++) {
         try {
            this.bits[i] = FAAC_Protocol.isBitActiveIn4bytes_LSBfirst(byteArrayIn, i);
         } catch (Exception var6) {
            Exception ex = var6;
            Logger.getLogger(Union32_Abstraction.class.getName()).log(Level.SEVERE, null, var6);
            Debug.printlnStatic("Eccezione Union32_Abstraction: " + var6.toString());

            try {
               throw ex;
            } catch (Exception var5) {
               Logger.getLogger(Union32_Abstraction.class.getName()).log(Level.SEVERE, null, var5);
            }
         }
      }
   }

   public final void setBytes_MSBfirst(byte[] byteArrayIn) {
      for (int i = 0; i < 32; i++) {
         try {
            this.bits[i] = FAAC_Protocol.isBitActiveIn4bytes_MSBfirst(byteArrayIn, i);
         } catch (Exception var6) {
            Exception ex = var6;
            Logger.getLogger(Union32_Abstraction.class.getName()).log(Level.SEVERE, null, var6);
            Debug.printlnStatic("Eccezione Union32_Abstraction: " + var6.toString());

            try {
               throw ex;
            } catch (Exception var5) {
               Logger.getLogger(Union32_Abstraction.class.getName()).log(Level.SEVERE, null, var5);
            }
         }
      }
   }

   public byte[] getBytes_LSBfirst() {
      byte[] out = new byte[4];

      for (int iByte = 0; iByte < 4; iByte++) {
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

   public byte[] getBytes_MSBfirst() {
      byte[] out = new byte[4];

      for (int iByte = 0; iByte < 4; iByte++) {
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

         out[3 - iByte] = mask;
      }

      return out;
   }
}
