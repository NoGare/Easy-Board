package FAAClib;

public class E145_Remote extends FAAC_Remote {
   public static final int numRadio_0 = 768;

   public E145_Remote() {
      NbRadioCode = 1600;
      this.radFileLenght_inBytes = 24 + NbRadioCode * 8 + 5;
   }
}
