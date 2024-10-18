package FAAClib;

import java.util.LinkedList;

public class FAAC_XCOM {
   private String id_XcomBox = "";
   private int verSw_XcomBox = 0;
   private int revSw_XcomBox = 0;
   private byte[] stdDestAddress_MSBfirst = new byte[4];
   private byte[] address_Xcom_MSBfirst = new byte[4];
   private LinkedList presentXCOMlist = new LinkedList();
   private String name_Xcom = "";
   private boolean isSDAactive = false;

   public void emptyXCOMlist() {
      this.presentXCOMlist.clear();
   }

   public String getId_XcomBox() {
      return this.id_XcomBox;
   }

   public void setId_XcomBox(String id_XcomBox) {
      this.id_XcomBox = id_XcomBox;
   }

   public int getVerSw_XcomBox() {
      return this.verSw_XcomBox;
   }

   public void setVerSw_XcomBox(int verSw_XcomBox) {
      this.verSw_XcomBox = verSw_XcomBox;
   }

   public int getRevSw_XcomBox() {
      return this.revSw_XcomBox;
   }

   public void setRevSw_XcomBox(int revSw_XcomSw) {
      this.revSw_XcomBox = revSw_XcomSw;
   }

   public byte[] getAddress_Xcom_MSBfirst() {
      return this.address_Xcom_MSBfirst;
   }

   public void setAddress_Xcom_MSBfirst(byte[] address_Xcom) {
      this.address_Xcom_MSBfirst = address_Xcom;
   }

   public LinkedList getPresentXCOMlist() {
      return this.presentXCOMlist;
   }

   public void setPresentXCOMlist(LinkedList presentXCOMlist) {
      this.presentXCOMlist = presentXCOMlist;
   }

   public byte[] getStdDestAddress_MSBfirst() {
      return this.stdDestAddress_MSBfirst;
   }

   public void setStdDestAddress_MSBfirst(byte[] stdDestAddress_MSBfirst) {
      this.stdDestAddress_MSBfirst = stdDestAddress_MSBfirst;
   }

   public String getName_Xcom() {
      return this.name_Xcom;
   }

   public void setName_Xcom(String name_Xcom) {
      this.name_Xcom = name_Xcom;
   }

   public boolean isIsSDAactive() {
      return this.isSDAactive;
   }

   public void setIsSDAactive(boolean isSDAactive) {
      this.isSDAactive = isSDAactive;
   }

   public static class xcomNameAddress {
      private String name = "";
      private byte[] address_MSBfirst = new byte[4];

      public String getName() {
         return this.name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public String getAddressString() {
         int iRes = FAAC_Protocol.byteArrayToInt(this.address_MSBfirst);
         return Integer.toString(iRes, 16).toUpperCase();
      }

      public byte[] getAddress_MSBfirst() {
         return this.address_MSBfirst;
      }

      public void setAddress_MSBfirst(byte[] address_MSBfirst) {
         this.address_MSBfirst = address_MSBfirst;
      }
   }
}
