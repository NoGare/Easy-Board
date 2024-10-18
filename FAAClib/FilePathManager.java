package FAAClib;

import java.io.File;

public class FilePathManager {
   private static FilePathManager staticfpm;
   private static boolean isInitialized;
   private final boolean isMac;
   private String settingsdir;
   private String applicationdir;
   private String tempdir;

   public FilePathManager() {
      String lcOSName = System.getProperty("os.name").toLowerCase();
      this.isMac = lcOSName.startsWith("mac os x");
      if (this.isMac) {
         String bundle = System.getProperty("appbundlepath");
         if (bundle == null) {
            this.applicationdir = "./";
         } else if (bundle.toLowerCase().contains("java")) {
            this.applicationdir = "./";
         } else {
            this.applicationdir = bundle + "/";
         }

         this.settingsdir = System.getProperty("LibraryDirectory");
         if (this.settingsdir == null) {
            this.settingsdir = "./";
         } else {
            this.settingsdir = this.settingsdir + "/EasyBoard/";
         }

         this.tempdir = System.getProperty("user.home") + "/Library/Application Support/EasyBoard/";
         File tmp = new File(this.tempdir);
         if (!tmp.exists()) {
            tmp.mkdirs();
         }

         tmp = new File(this.settingsdir);
         if (!tmp.exists()) {
            tmp.mkdir();
         }
      } else {
         this.applicationdir = "./";
         this.settingsdir = "./";
         this.tempdir = "./";
      }
   }

   public static void init() {
      staticfpm = new FilePathManager();
      isInitialized = true;
   }

   public static String getSettingsDir() {
      if (!isInitialized) {
         init();
      }

      return staticfpm.applicationdir;
   }

   public static String getApplicationDir() {
      if (!isInitialized) {
         init();
      }

      return staticfpm.applicationdir;
   }

   public static String getTempDir() {
      if (!isInitialized) {
         init();
      }

      return staticfpm.tempdir;
   }
}
