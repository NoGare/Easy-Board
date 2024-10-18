package FAAClib;

import java.util.Vector;

public class PBentry {
   public static final byte profile_invalid = 0;
   public static final byte profile_administrator = 1;
   public static final byte profile_installer = 2;
   public static final byte profile_user = 4;
   public static final byte attribute_F1 = 1;
   public static final byte attribute_F2 = 2;
   public static final byte attribute_F3 = 4;
   public static final byte attribute_none = 0;
   public static final byte attribute_invalid = -1;
   public static final short newEntryIndex = -1;
   private String phone;
   private byte profiles;
   private byte attributes;
   private int index;

   public static String profileToString(byte profile) {
      StringBuffer res = new StringBuffer(5);
      if ((profile & 1) == 1) {
         res.append("A");
      }

      if ((profile & 2) == 2) {
         res.append("I");
      }

      if ((profile & 4) == 4) {
         res.append("U");
      }

      return res.toString();
   }

   public static String attributesToString(byte attributes) {
      StringBuffer res = new StringBuffer(10);
      if ((attributes & 1) == 1) {
         res.append("F1");
      }

      if ((attributes & 2) == 2) {
         res.append("F2");
      }

      if ((attributes & 4) == 4) {
         res.append("F3");
      }

      return res.toString();
   }

   public static String attributesToLongString(byte attributes) {
      StringBuffer res = new StringBuffer(10);
      if ((attributes & 1) == 1) {
         res.append("F1 yes ");
      } else {
         res.append("F1 no ");
      }

      if ((attributes & 2) == 2) {
         res.append("F2 yes ");
      } else {
         res.append("F2 no ");
      }

      if ((attributes & 4) == 4) {
         res.append("F3 yes");
      } else {
         res.append("F3 no");
      }

      return res.toString();
   }

   static PBentry GetNewEmptyEntry(int i) {
      return new PBentry(i, "", (byte)0, (byte)0);
   }

   public PBentry(int index, String phone, byte profiles, byte attributes) {
      this.phone = phone;
      this.profiles = profiles;
      this.attributes = attributes;
      this.index = index;
   }

   public static PBentry GetNewEntryFromString(String strElement) {
      PBentry entry = null;
      String[] pieces = strElement.split(":");
      if (pieces.length != 4) {
         return null;
      } else if (pieces[0].length() == 0) {
         return null;
      } else if (pieces[1].length() == 0) {
         return null;
      } else {
         short tmpindex;
         try {
            tmpindex = (short)Integer.parseInt(pieces[0]);
         } catch (NumberFormatException var9) {
            return null;
         }

         byte tmpprofile;
         try {
            tmpprofile = (byte)Integer.parseInt(pieces[2], 16);
         } catch (NumberFormatException var8) {
            return null;
         }

         byte tmpattr;
         try {
            tmpattr = (byte)Integer.parseInt(pieces[3], 16);
         } catch (NumberFormatException var7) {
            return null;
         }

         return new PBentry(tmpindex, pieces[1], tmpprofile, tmpattr);
      }
   }

   public boolean equals(PBentry entry) {
      return entry == null ? false : this.phone.compareTo(entry.phone) == 0;
   }

   public boolean isProfile(byte profile) {
      return (byte)(this.profiles & profile) == profile;
   }

   public boolean hasAttribute(byte attribute) {
      return (byte)(this.attributes & attribute) == attribute;
   }

   public String GetPhone() {
      return this.phone;
   }

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int val) {
      this.index = val;
   }

   @Override
   public String toString() {
      StringBuffer res = new StringBuffer(20);
      res.append(this.index);
      res.append(";");
      res.append(this.phone);
      res.append(";");
      res.append(Integer.toHexString(this.getProfiles()));
      res.append(";");
      res.append(Integer.toHexString(this.getAttributes()));
      return res.toString();
   }

   public String toLongString() {
      StringBuffer res = new StringBuffer(20);
      res.append(this.index);
      res.append(";");
      res.append(this.phone);
      res.append(";");
      res.append(profileToString(this.profiles));
      res.append(";");
      res.append(attributesToString(this.getAttributes()));
      return res.toString();
   }

   public String toFancyString() {
      StringBuffer res = new StringBuffer(30);
      res.append(this.phone);
      res.append(" ");
      res.append(profileToString(this.profiles));
      res.append(" ");
      res.append(attributesToLongString(this.getAttributes()));
      return res.toString();
   }

   public byte getProfiles() {
      return this.profiles;
   }

   public void setProfiles(byte profiles) {
      this.profiles = profiles;
   }

   public byte getAttributes() {
      return this.attributes;
   }

   public void setAttributes(byte attributes) {
      this.attributes = attributes;
   }

   Vector getVectorData() {
      Vector pbdata = new Vector(8);
      pbdata.add(this.index);
      pbdata.add(this.phone);
      pbdata.add(this.isProfile((byte)1));
      pbdata.add(this.isProfile((byte)2));
      pbdata.add(this.isProfile((byte)4));
      pbdata.add(this.hasAttribute((byte)1));
      pbdata.add(this.hasAttribute((byte)2));
      pbdata.add(this.hasAttribute((byte)4));
      return pbdata;
   }

   byte[] getByteArrayForFrame() {
      byte[] pbByteArray = new byte[18];
      char[] phonearray;
      if (this.phone.isEmpty()) {
         phonearray = "0000000000000000".toCharArray();
      } else {
         phonearray = this.phone.toCharArray();
      }

      int len = phonearray.length;
      len = len < 16 ? len : 16;

      int i;
      for (i = 0; i < len; i++) {
         pbByteArray[i] = (byte)phonearray[i];
      }

      while (i < 16) {
         pbByteArray[i] = 65;
         i++;
      }

      pbByteArray[16] = this.profiles;
      pbByteArray[17] = this.attributes;
      return pbByteArray;
   }
}
