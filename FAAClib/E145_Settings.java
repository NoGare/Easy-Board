package FAAClib;

public class E145_Settings extends FAAC_Settings {
   public static String defFileElectromechanical = "default/E145_ElectroMechanical.PRG";
   public static String defFileOleodinamic = "default/E145_OleoDinamic.PRG";
   public static String defFile = "default/E145_df.PRG";
   public static int MotorTypeBattente = 0;
   public static int MotorTypeScorrevole = 1;
   private int livFrenataScorr1;
   private int livFrenataScorr2;
   private int addTimeOpen1;
   private int addTimeOpen2;
   private int addTimeClose1;
   private int addTimeClose2;
   public static final int addTimeMin = 0;
   public static final int addTimeMax = 30;
   public static final int LivFrenataScorrMin = 0;
   public static final int LivFrenataScorrMax = 20;

   public E145_Settings() {
      this.init_E145();
      this.getInType(4).setInTypeWithoutNcFs((byte)15);
      this.getInType(3).setInTypeWithoutNcFs((byte)16);
   }

   private void init_E145() {
      this.progFlag.set03_FirstMovRall(false);
      this.motor1Type = MotorTypeBattente;
      this.motor2Type = MotorTypeBattente;
      this.livFrenataScorr1 = 10;
      this.livFrenataScorr2 = 10;
      this.addTimeOpen1 = 0;
      this.addTimeOpen1 = 0;
      this.addTimeClose1 = 0;
      this.addTimeClose2 = 0;
   }

   public E145_Settings(String password) {
      super(password);
      this.init_E145();
   }

   public int getLivFrenataScorr1() {
      return this.livFrenataScorr1;
   }

   public void setLivFrenataScorr1(int livFrenataScorr1) {
      this.livFrenataScorr1 = livFrenataScorr1;
   }

   public int getLivFrenataScorr2() {
      return this.livFrenataScorr2;
   }

   public void setLivFrenataScorr2(int livFrenataScorr2) {
      this.livFrenataScorr2 = livFrenataScorr2;
   }

   public int getAddTimeOpen1() {
      return this.addTimeOpen1;
   }

   public void setAddTimeOpen1(int addTimeOpen1) {
      this.addTimeOpen1 = addTimeOpen1;
   }

   public int getAddTimeOpen2() {
      return this.addTimeOpen2;
   }

   public void setAddTimeOpen2(int addTimeOpen2) {
      this.addTimeOpen2 = addTimeOpen2;
   }

   public int getAddTimeClose1() {
      return this.addTimeClose1;
   }

   public void setAddTimeClose1(int addTimeClose1) {
      this.addTimeClose1 = addTimeClose1;
   }

   public int getAddTimeClose2() {
      return this.addTimeClose2;
   }

   public void setAddTimeClose2(int addTimeClose2) {
      this.addTimeClose2 = addTimeClose2;
   }
}
