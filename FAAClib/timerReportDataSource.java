package FAAClib;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class timerReportDataSource implements JRDataSource {
   private int providedElements;
   private final int maxElements = 0;

   public timerReportDataSource() {
      this.providedElements = 0;
   }

   public boolean next() throws JRException {
      return this.providedElements < 0;
   }

   public Object getFieldValue(JRField jrf) throws JRException {
      this.providedElements++;
      return "";
   }
}
