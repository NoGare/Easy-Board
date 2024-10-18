package FAAClib;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;

public class SerialBoardController implements Runnable {
   EventListenerList dataReceivedListeners;
   CommPortIdentifier portId;
   InputStream inputStream;
   OutputStream outputStream;
   SerialPort serialPort;
   Thread readThread;
   protected String divertCode = "10";
   static String TimeStamp;
   Debug debug;
   public static final int baudrateE145_USB = 115200;
   public static final int baudrate_XCOM = 38400;
   public static final int baudrateE124_USB = 38400;
   int baudrate;
   private String readBytes;
   private SerialBoardController.SerialReaderHandler reader;
   private boolean isWaitingForReply;
   private StringBuilder readBuffer;
   private SerialBoardController.SerialWatchDogElapsed serialWatchDogElapsed;
   private Timer serialWatchDog;
   private Thread writeThread;

   public SerialBoardController() {
      this.dataReceivedListeners = new EventListenerList();
      this.debug = new Debug(true);
      this.debug.setLevel(0);
      this.isWaitingForReply = false;
      this.baudrate = 115200;
      this.readBuffer = new StringBuilder();
      this.serialWatchDogElapsed = new SerialBoardController.SerialWatchDogElapsed();
   }

   public void setSerialDriverForUsbDirectE124() {
      this.baudrate = 38400;
   }

   public void setSerialDriverForUsbDirectE145() {
      this.baudrate = 115200;
   }

   public void setSerialDriverForXCOM() {
      this.baudrate = 38400;
   }

   public ArrayList<String> getAvailablePorts() {
      ArrayList<String> portsNames = new ArrayList<>();
      Enumeration en = CommPortIdentifier.getPortIdentifiers();

      while (en.hasMoreElements()) {
         CommPortIdentifier tmpId = (CommPortIdentifier)en.nextElement();
         if (tmpId.getPortType() == 1) {
            portsNames.add(tmpId.getName());
         }
      }

      return portsNames;
   }

   public void WaitForReplyNoTimeout() {
      while (this.isIsWaitingForReply()) {
      }
   }

   public void WaitForReplyTimeout() {
      for (int i = 0; i < 1500 && this.isIsWaitingForReply(); i++) {
         try {
            Thread.sleep(1L);
         } catch (InterruptedException var3) {
            Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
         }
      }

      this.setIsWaitingForReply(false);
   }

   public void WaitForReplyShortTimeout() {
      this.debug.printlnIfDebug("WaitForReplyShortTimeout: isWaitingForReply = " + this.isIsWaitingForReply());

      for (int i = 0; i < 100 && this.isIsWaitingForReply(); i++) {
         try {
            Thread.sleep(10L);
         } catch (InterruptedException var3) {
            Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
         }
      }

      this.setIsWaitingForReply(false);
   }

   public void WaitForReplyTimeoutXcomRemote() {
      for (int i = 0; i < 8000 && this.isIsWaitingForReply(); i++) {
         try {
            Thread.sleep(1L);
         } catch (InterruptedException var3) {
            Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
         }
      }

      this.setIsWaitingForReply(false);
   }

   public void DisconnectSynch() {
      this.setIsWaitingForReply(false);
      if (this.serialPort != null) {
         try {
            this.outputStream.close();
            this.inputStream.close();
         } catch (IOException var2) {
         }

         this.serialPort.close();
      }
   }

   public void Connect(String port, int handshakeType) {
      this.portId = null;

      try {
         this.portId = CommPortIdentifier.getPortIdentifier(port);
      } catch (NoSuchPortException var9) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var9);
         this.debug.Log(10, var9.toString());
         return;
      }

      try {
         TimeStamp = new Date().toString();
         int timeout = 2000;
         this.serialPort = (SerialPort)this.portId.open("FAAC_Serial_Port", timeout);
         this.debug.Log(1, TimeStamp + ": " + this.portId.getName() + " opened");
      } catch (PortInUseException var8) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var8);
         this.debug.Log(10, TimeStamp + ": " + this.portId.getName() + " failed to open. Port already in use. " + var8.toString());
      }

      try {
         this.inputStream = this.serialPort.getInputStream();
      } catch (IOException var7) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var7);
         this.debug.Log(10, TimeStamp + ": " + this.portId.getName() + " failed to get input stream. " + var7.toString());
      }

      this.reader = new SerialBoardController.SerialReaderHandler();
      this.serialPort.notifyOnDataAvailable(true);

      try {
         this.serialPort.addEventListener(this.reader);
      } catch (TooManyListenersException var6) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var6);
         this.debug.Log(10, var6.toString());
      }

      try {
         this.serialPort.setSerialPortParams(this.baudrate, 8, 1, 0);
      } catch (UnsupportedCommOperationException var5) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var5);
         this.debug.Log(10, var5.toString());
      }

      try {
         this.serialPort.setFlowControlMode(handshakeType);
      } catch (UnsupportedCommOperationException var4) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var4);
         this.debug.Log(10, var4.toString());
      }

      this.readThread = new Thread(this);
      this.readThread.start();
   }

   public void NotifyDataAvailable(boolean notify) {
      this.serialPort.notifyOnDataAvailable(notify);
   }

   public boolean Connect(String port) {
      this.portId = null;

      try {
         this.portId = CommPortIdentifier.getPortIdentifier(port);
      } catch (NoSuchPortException var8) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var8);
         this.debug.Log(10, var8.toString());
         return false;
      }

      try {
         TimeStamp = new Date().toString();
         int timeout = 500;
         Debug.printlnStatic("Try to open port " + port);
         this.serialPort = (SerialPort)this.portId.open("FAAC_Serial_Port", timeout);
         Debug.printlnStatic("Port " + port + "opened");
         this.debug.Log(1, TimeStamp + ": " + this.portId.getName() + " opened");
      } catch (PortInUseException var7) {
         Debug.printlnStatic("Fail to open port " + port);
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var7);
         this.debug.Log(10, TimeStamp + ": " + this.portId.getName() + " failed to open. Port already in use. " + var7.toString());
         return false;
      }

      try {
         this.inputStream = this.serialPort.getInputStream();
      } catch (IOException var6) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var6);
         this.debug.Log(10, TimeStamp + ": " + this.portId.getName() + " failed to get input stream. " + var6.toString());
         return false;
      }

      this.reader = new SerialBoardController.SerialReaderHandler();
      this.serialPort.notifyOnDataAvailable(true);

      try {
         this.serialPort.addEventListener(this.reader);
      } catch (TooManyListenersException var5) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var5);
         this.debug.Log(10, var5.toString());
         return false;
      }

      try {
         this.serialPort.setSerialPortParams(this.baudrate, 8, 1, 0);
      } catch (UnsupportedCommOperationException var4) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var4);
         this.debug.Log(10, var4.toString());
         return false;
      }

      try {
         this.serialPort.setFlowControlMode(0);
      } catch (UnsupportedCommOperationException var3) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
         this.debug.Log(10, var3.toString());
         return false;
      }

      this.readThread = new Thread(this);
      this.readThread.start();
      return true;
   }

   public boolean WriteSynch(byte dataByte) {
      for (int i = 0; i < 200 && this.isIsWaitingForReply(); i++) {
         try {
            Thread.sleep(1L);
         } catch (InterruptedException var4) {
            Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var4);
         }
      }

      this.setIsWaitingForReply(true);
      this.Write(dataByte);
      return true;
   }

   public synchronized boolean WriteTimeout(byte[] dataBytes) {
      SerialBoardController.WriteThreadRunner writeThreadRunner = new SerialBoardController.WriteThreadRunner(dataBytes);
      this.writeThread = new Thread(writeThreadRunner);
      this.writeThread.start();
      this.serialWait(2000);
      if (!writeThreadRunner.isWriteSuccessul()) {
         this.writeThread.interrupt();
         this.serialPort = null;
      }

      return true;
   }

   public boolean WriteSynch(byte[] dataBytes) {
      for (int i = 0; i < 200 && this.isIsWaitingForReply(); i++) {
         try {
            Thread.sleep(1L);
         } catch (InterruptedException var4) {
            Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var4);
         }
      }

      this.setIsWaitingForReply(true);
      this.Write(dataBytes);
      return true;
   }

   public synchronized boolean WriteWait(byte[] dataBytes) {
      this.Write(dataBytes);

      try {
         this.wait();
      } catch (InterruptedException var3) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
      }

      return true;
   }

   public synchronized void notifyRead() {
      this.notify();
   }

   public synchronized void serialNotify() {
      this.notify();
   }

   public synchronized void serialWait(int mills) {
      try {
         this.wait((long)mills);
      } catch (InterruptedException var3) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
      }
   }

   public void Write(byte dataByte) {
      try {
         this.outputStream = this.serialPort.getOutputStream();
         this.outputStream.write(dataByte);
         Debug.printlnStatic(TimeStamp + ": write: " + dataByte);
      } catch (IOException var3) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
         this.debug.Log(10, var3.toString());
      }
   }

   public void Write(byte[] dataBytes) {
      try {
         this.outputStream = this.serialPort.getOutputStream();
         this.outputStream.write(dataBytes);
         Debug.printlnStatic(TimeStamp + ": write: " + dataBytes);
      } catch (IOException var3) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
         this.debug.Log(10, var3.toString());
      }
   }

   public void Write(String data) {
      try {
         this.outputStream = this.serialPort.getOutputStream();
         this.outputStream.write(data.getBytes());
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.INFO, null, data);
         this.outputStream.close();
      } catch (IOException var3) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
         this.debug.Log(10, var3.toString());
      }
   }

   public String read() {
      try {
         this.inputStream = this.serialPort.getInputStream();
      } catch (IOException var3) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var3);
         this.debug.Log(10, var3.toString());
      }

      this.readBuffer = new StringBuilder();

      try {
         while (true) {
            int c = this.inputStream.read();
            this.readBuffer.append((char)c);
            if (c == 3 || c <= 0) {
               this.readBytes = this.readBuffer.toString();
               TimeStamp = new Date().toString();
               Debug.printlnStatic(TimeStamp + ": read: " + this.readBytes);
               Debug.printlnStatic(TimeStamp + ": read in Hex: " + FAAC_Protocol.StringToHex(this.readBytes));
               this.inputStream.close();
               break;
            }
         }
      } catch (IOException var4) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var4);
         this.debug.Log(10, var4.toString());
      }

      return this.readBytes;
   }

   public byte readByteForFWUpdate() {
      try {
         this.inputStream = this.serialPort.getInputStream();
      } catch (IOException var5) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var5);
         this.debug.Log(10, var5.toString());
      }

      this.readBuffer = new StringBuilder();
      byte c = -1;
      byte[] allowedBytes = new byte[]{17, 75, 85};
      int MAXtries = 1000;

      try {
         for (int i = 0; i < MAXtries; i++) {
            c = (byte)this.inputStream.read();
            if (c == allowedBytes[0] || c == allowedBytes[1] || c == allowedBytes[2]) {
               break;
            }
         }

         TimeStamp = new Date().toString();
         Debug.printlnStatic(TimeStamp + ": read byte : " + c + "(" + FAAC_Protocol.byteToHexString(c) + ")");
         this.inputStream.close();
      } catch (IOException var6) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var6);
         this.debug.Log(10, var6.toString());
      }

      return c;
   }

   @Override
   public void run() {
      try {
         Thread.sleep(100L);
      } catch (InterruptedException var2) {
         Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var2);
         this.debug.Log(10, var2.toString());
      }
   }

   public String getReadBytes() {
      return this.readBytes;
   }

   public void addDataReceivedListener(ActionListener listener) {
      this.dataReceivedListeners.add(ActionListener.class, listener);
   }

   public void removeDataReceivedListener(ActionListener listener) {
      this.dataReceivedListeners.remove(ActionListener.class, listener);
   }

   public void openOutputStream() throws IOException {
      this.outputStream = this.serialPort.getOutputStream();
   }

   public void openInputStream() throws IOException {
      this.inputStream = this.serialPort.getInputStream();
   }

   public void closeInputStream() throws IOException {
      this.inputStream.close();
   }

   public void closeOutputStream() throws IOException {
      this.outputStream.close();
   }

   public boolean isIsWaitingForReply() {
      return this.isWaitingForReply;
   }

   public void setIsWaitingForReply(boolean isWaitingForReply) {
      this.isWaitingForReply = isWaitingForReply;
   }

   class SerialReaderHandler implements SerialPortEventListener {
      public void serialEvent(SerialPortEvent event) {
         switch (event.getEventType()) {
            case 1:
               SerialBoardController.this.readBuffer = new StringBuilder();

               try {
                  while (true) {
                     int c = SerialBoardController.this.inputStream.read();
                     SerialBoardController.this.readBuffer.append((char)c);
                     if (c == 3 || c <= 0) {
                        String scannedInput = SerialBoardController.this.readBuffer.toString();
                        SerialBoardController.TimeStamp = new Date().toString();
                        Debug.printlnStatic(SerialBoardController.TimeStamp + ": read: " + scannedInput);
                        Debug.printlnStatic(SerialBoardController.TimeStamp + ": read in Hex: " + FAAC_Protocol.StringToHex(scannedInput));
                        SerialBoardController.this.inputStream.close();
                        SerialBoardController.this.readBytes = SerialBoardController.this.readBuffer.toString();
                        Object[] listeners = SerialBoardController.this.dataReceivedListeners.getListenerList();
                        int numListeners = listeners.length;

                        for (int i = 0; i < numListeners; i += 2) {
                           if (listeners[i] == ActionListener.class) {
                              ((ActionListener)listeners[i + 1]).actionPerformed(null);
                           }
                        }

                        SerialBoardController.this.setIsWaitingForReply(false);
                        break;
                     }
                  }
               } catch (IOException var7) {
                  Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var7);
                  SerialBoardController.this.debug.Log(10, var7.toString());
               }
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
         }
      }
   }

   class SerialWatchDogElapsed implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
   }

   class WriteThreadRunner implements Runnable {
      private byte[] backgroundWriteData;
      private boolean writeSuccess;

      public WriteThreadRunner(byte[] dataBytes) {
         this.backgroundWriteData = dataBytes;
      }

      public synchronized void notifyWrite() {
         this.notify();
      }

      public synchronized void waitWrite() {
         try {
            this.wait(2000L);
         } catch (InterruptedException var2) {
            Logger.getLogger(SerialBoardController.class.getName()).log(Level.SEVERE, null, var2);
         }
      }

      @Override
      public void run() {
         this.writeSuccess = false;
         SerialBoardController.this.setIsWaitingForReply(true);
         SerialBoardController.this.Write(this.backgroundWriteData);
         this.writeSuccess = true;
         SerialBoardController.this.serialNotify();
      }

      public boolean isWriteSuccessul() {
         return this.writeSuccess;
      }
   }
}
