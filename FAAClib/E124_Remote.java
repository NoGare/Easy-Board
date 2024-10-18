package FAAClib;

public class E124_Remote extends FAAC_Remote {
   public static final int numRadio_0 = 256;

   public E124_Remote() {
      NbRadioCode = 1600;
      this.radFileLenght_inBytes = 24 + NbRadioCode * 8 + 5;
   }
}
