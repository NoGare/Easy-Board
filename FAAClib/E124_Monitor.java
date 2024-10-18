package FAAClib;

public class E124_Monitor extends FAAC_Monitor {
   public E124_Monitor() {
      this.boardType = "E124";
   }

   @Override
   public boolean isSomeErrorsPresent() {
      boolean[] errArray = this.error.getBits();

      for (int i = 0; i < errArray.length; i++) {
         if (i != 5 && i != 19 && i != 20 && i != 23 && i != 24 && i != 25 && i != 26 && i != 28 && i != 29 && i != 30 && errArray[i]) {
            return true;
         }
      }

      boolean[] allArray = this.allarm.getBits();
      return allArray[5];
   }

   @Override
   public boolean isSomeAllarmsPresent() {
      boolean[] allArray = this.allarm.getBits();

      for (int i = 0; i < allArray.length; i++) {
         if (i != 5 && i != 7 && i != 0 && i != 10 && i != 11 && i != 13 && allArray[i]) {
            return true;
         }
      }

      boolean[] allBisArray = this.allarmBis.getBits();

      for (int ix = 0; ix < allBisArray.length; ix++) {
         if (ix != 3 && ix != 5 && ix != 8 && ix != 9 && ix != 10 && ix != 11 && ix <= 14 && allBisArray[ix]) {
            return true;
         }
      }

      return false;
   }
}
