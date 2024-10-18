package FAAClib;

public class FAAC_NETCOM {
   private String hostName = "NET-COM";
   private int IP1 = 10;
   private int IP2 = 0;
   private int IP3 = 0;
   private int IP4 = 1;
   private int MASK1 = 255;
   private int MASK2 = 255;
   private int MASK3 = 255;
   private int MASK4 = 0;
   private int GW1 = this.IP1;
   private int GW2 = this.IP2;
   private int GW3 = this.IP3;
   private int GW4 = this.IP4;
   private int primDNS1 = this.IP1;
   private int primDNS2 = this.IP2;
   private int primDNS3 = this.IP3;
   private int primDNS4 = this.IP4;
   private int secDNS1 = this.IP1;
   private int secDNS2 = this.IP2;
   private int secDNS3 = this.IP3;
   private int secDNS4 = this.IP4;
   private boolean isDHCPset = true;
   public static final int maxNetcomNameLength = 15;

   public String getHostName() {
      return this.hostName;
   }

   public void setHostName(String hostName) {
      this.hostName = hostName;
   }

   public int getIP1() {
      return this.IP1;
   }

   public void setIP1(int IP1) {
      this.IP1 = IP1;
   }

   public int getIP2() {
      return this.IP2;
   }

   public void setIP2(int IP2) {
      this.IP2 = IP2;
   }

   public int getIP3() {
      return this.IP3;
   }

   public void setIP3(int IP3) {
      this.IP3 = IP3;
   }

   public int getIP4() {
      return this.IP4;
   }

   public void setIP4(int IP4) {
      this.IP4 = IP4;
   }

   public int getMASK1() {
      return this.MASK1;
   }

   public void setMASK1(int MASK1) {
      this.MASK1 = MASK1;
   }

   public int getMASK2() {
      return this.MASK2;
   }

   public void setMASK2(int MASK2) {
      this.MASK2 = MASK2;
   }

   public int getMASK3() {
      return this.MASK3;
   }

   public void setMASK3(int MASK3) {
      this.MASK3 = MASK3;
   }

   public int getMASK4() {
      return this.MASK4;
   }

   public void setMASK4(int MASK4) {
      this.MASK4 = MASK4;
   }

   public int getGW1() {
      return this.GW1;
   }

   public void setGW1(int GW1) {
      this.GW1 = GW1;
   }

   public int getGW2() {
      return this.GW2;
   }

   public void setGW2(int GW2) {
      this.GW2 = GW2;
   }

   public int getGW3() {
      return this.GW3;
   }

   public void setGW3(int GW3) {
      this.GW3 = GW3;
   }

   public int getGW4() {
      return this.GW4;
   }

   public void setGW4(int GW4) {
      this.GW4 = GW4;
   }

   public int getPrimDNS1() {
      return this.primDNS1;
   }

   public void setPrimDNS1(int primDNS1) {
      this.primDNS1 = primDNS1;
   }

   public int getPrimDNS2() {
      return this.primDNS2;
   }

   public void setPrimDNS2(int primDNS2) {
      this.primDNS2 = primDNS2;
   }

   public int getPrimDNS3() {
      return this.primDNS3;
   }

   public void setPrimDNS3(int primDNS3) {
      this.primDNS3 = primDNS3;
   }

   public int getPrimDNS4() {
      return this.primDNS4;
   }

   public void setPrimDNS4(int primDNS4) {
      this.primDNS4 = primDNS4;
   }

   public int getSecDNS1() {
      return this.secDNS1;
   }

   public void setSecDNS1(int secDNS1) {
      this.secDNS1 = secDNS1;
   }

   public int getSecDNS2() {
      return this.secDNS2;
   }

   public void setSecDNS2(int secDNS2) {
      this.secDNS2 = secDNS2;
   }

   public int getSecDNS3() {
      return this.secDNS3;
   }

   public void setSecDNS3(int secDNS3) {
      this.secDNS3 = secDNS3;
   }

   public int getSecDNS4() {
      return this.secDNS4;
   }

   public void setSecDNS4(int secDNS4) {
      this.secDNS4 = secDNS4;
   }

   public boolean isIsDHCPset() {
      return this.isDHCPset;
   }

   public void setIsDHCPset(boolean isDHCPset) {
      this.isDHCPset = isDHCPset;
   }
}
