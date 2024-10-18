package FAAClib;

import java.util.Date;

public class Debug {
   public final int LOG_INFO = 1;
   public final int LOG_WARNING = 5;
   public final int LOG_ERROR = 10;
   public final int LOG_ALL = 0;
   public final int LOG_NONE = 99;
   private int level;
   private static boolean staticDebugActive = true;
   private boolean debugActive;

   public static boolean isStaticDebugActive() {
      return staticDebugActive;
   }

   public static void setStaticDebugActive(boolean setStaticDebugActive) {
      staticDebugActive = setStaticDebugActive;
   }

   public Debug() {
      this.debugActive = false;
   }

   public Debug(boolean debugActive) {
      this.debugActive = debugActive;
   }

   public void Log(int level, String s) {
      if (level >= this.getLevel()) {
         Date dt = new Date();
         String dtLogInstant = "[" + dt.toString() + "] ";
         switch (level) {
            case 1:
               System.out.print(dtLogInstant + ">> INFO: ");
               break;
            case 5:
               System.out.print(dtLogInstant + ">> WARNING: ");
               break;
            case 10:
               System.out.print(dtLogInstant + ">> ERROR: ");
         }

         System.out.println(s);
         System.out.flush();
      }
   }

   public int getLevel() {
      return this.level;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void printIfDebug(String s) {
      if (this.debugActive) {
         System.out.print(s);
      }
   }

   public void printlnIfDebug(String s) {
      if (this.debugActive) {
         System.out.println(s);
      }
   }

   public void printWithThreadInfoIfDebug(String s) {
      if (this.debugActive) {
         Date dtemp = new Date();
         String now = dtemp.toString();
         System.out.println("**" + now + " - " + Thread.currentThread().toString() + "** " + s);
      }
   }

   public boolean isDebugActive() {
      return this.debugActive;
   }

   public void setDebugActive(boolean debugActive) {
      this.debugActive = debugActive;
   }

   public static void printlnStatic(String s) {
      if (isStaticDebugActive()) {
         System.out.println(s);
      }
   }
}
