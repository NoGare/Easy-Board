package FAAClib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CircularArrayList extends AbstractList implements List, Serializable {
   private Object[] elementData;
   private int head = 0;
   private int tail = 0;
   private int size = 0;
   private static final int DEFAULT_SIZE = 100;
   private int circularSize;

   public CircularArrayList() {
      this(100);
   }

   public CircularArrayList(int size) {
      this.elementData = new Object[size];
      this.circularSize = size;
   }

   public CircularArrayList(Collection c) {
      this.tail = c.size();
      this.elementData = new Object[c.size()];
      c.toArray(this.elementData);
      this.circularSize = c.size();
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("CircularArray size=");
      sb.append(this.size);
      sb.append("\n");

      for (int i = 0; i < this.size; i++) {
         sb.append("[");
         sb.append(this.convert(i));
         sb.append("]=>");
         sb.append(this.elementData[this.convert(i)]);
         sb.append(", ");
      }

      sb.append("\n");
      return sb.toString();
   }

   private int convert(int index) {
      return (index + this.head) % this.elementData.length;
   }

   @Override
   public boolean isEmpty() {
      return this.head == this.tail;
   }

   public void ensureCapacity(int minCapacity) {
      int oldCapacity = this.elementData.length;
      if (minCapacity > oldCapacity) {
         int newCapacity = oldCapacity * 3 / 2 + 1;
         if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
         }

         Object[] newData = new Object[newCapacity];
         this.toArray(newData);
         this.tail = this.size;
         this.head = 0;
         this.elementData = newData;
      }
   }

   @Override
   public int size() {
      return this.size;
   }

   @Override
   public boolean contains(Object elem) {
      return this.indexOf(elem) >= 0;
   }

   @Override
   public int indexOf(Object elem) {
      if (elem == null) {
         for (int i = 0; i < this.size; i++) {
            if (this.elementData[this.convert(i)] == null) {
               return i;
            }
         }
      } else {
         for (int ix = 0; ix < this.size; ix++) {
            if (elem.equals(this.elementData[this.convert(ix)])) {
               return ix;
            }
         }
      }

      return -1;
   }

   @Override
   public int lastIndexOf(Object elem) {
      if (elem == null) {
         for (int i = this.size - 1; i >= 0; i--) {
            if (this.elementData[this.convert(i)] == null) {
               return i;
            }
         }
      } else {
         for (int ix = this.size - 1; ix >= 0; ix--) {
            if (elem.equals(this.elementData[this.convert(ix)])) {
               return ix;
            }
         }
      }

      return -1;
   }

   @Override
   public Object[] toArray() {
      return this.toArray(new Object[this.size]);
   }

   @Override
   public Object[] toArray(Object[] a) {
      if (a.length < this.size) {
         a = (Object[])Array.newInstance(a.getClass().getComponentType(), this.size);
      }

      if (this.head < this.tail) {
         System.arraycopy(this.elementData, this.head, a, 0, this.tail - this.head);
      } else {
         System.arraycopy(this.elementData, this.head, a, 0, this.elementData.length - this.head);
         System.arraycopy(this.elementData, 0, a, this.elementData.length - this.head, this.tail);
      }

      if (a.length > this.size) {
         a[this.size] = null;
      }

      return a;
   }

   private void rangeCheck(int index) {
      if (index >= this.size || index < 0) {
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
      }
   }

   @Override
   public Object get(int index) {
      this.rangeCheck(index);
      return this.elementData[this.convert(index)];
   }

   @Override
   public Object set(int index, Object element) {
      this.modCount++;
      this.rangeCheck(index);
      Object oldValue = this.elementData[this.convert(index)];
      this.elementData[this.convert(index)] = element;
      return oldValue;
   }

   public boolean addCircular(Object o) {
      if (this.size >= this.circularSize) {
         this.remove(0);
      }

      return this.add(o);
   }

   @Override
   public boolean add(Object o) {
      this.modCount++;
      this.ensureCapacity(this.size + 1 + 1);
      this.elementData[this.tail] = o;
      this.tail = (this.tail + 1) % this.elementData.length;
      this.size++;
      return true;
   }

   @Override
   public Object remove(int index) {
      this.modCount++;
      this.rangeCheck(index);
      int pos = this.convert(index);

      Object var3;
      try {
         var3 = this.elementData[pos];
      } finally {
         this.elementData[pos] = null;
         if (pos == this.head) {
            this.head = (this.head + 1) % this.elementData.length;
         } else if (pos == this.tail) {
            this.tail = (this.tail - 1 + this.elementData.length) % this.elementData.length;
         } else if (pos > this.head && pos > this.tail) {
            System.arraycopy(this.elementData, this.head, this.elementData, this.head + 1, pos - this.head);
            this.head = (this.head + 1) % this.elementData.length;
         } else {
            System.arraycopy(this.elementData, pos + 1, this.elementData, pos, this.tail - pos - 1);
            this.tail = (this.tail - 1 + this.elementData.length) % this.elementData.length;
         }

         this.size--;
      }

      return var3;
   }

   @Override
   public void clear() {
      this.modCount++;

      for (int i = this.head; i != this.tail; i = (i + 1) % this.elementData.length) {
         this.elementData[i] = null;
      }

      this.head = this.tail = this.size = 0;
   }

   @Override
   public boolean addAll(Collection c) {
      this.modCount++;
      int numNew = c.size();
      this.ensureCapacity(this.size + numNew + 1);
      Iterator e = c.iterator();

      for (int i = 0; i < numNew; i++) {
         this.elementData[this.tail] = e.next();
         this.tail = (this.tail + 1) % this.elementData.length;
         this.size++;
      }

      return numNew != 0;
   }

   @Override
   public void add(int index, Object element) {
      if (index == this.size) {
         this.add(element);
      } else {
         this.modCount++;
         this.rangeCheck(index);
         this.ensureCapacity(this.size + 1 + 1);
         int pos = this.convert(index);
         if (pos == this.head) {
            this.head = (this.head - 1 + this.elementData.length) % this.elementData.length;
            this.elementData[this.head] = element;
         } else if (pos == this.tail) {
            this.elementData[this.tail] = element;
            this.tail = (this.tail + 1) % this.elementData.length;
         } else {
            if (pos > this.head && pos > this.tail) {
               System.arraycopy(this.elementData, pos, this.elementData, this.head - 1, pos - this.head + 1);
               this.head = (this.head - 1 + this.elementData.length) % this.elementData.length;
            } else {
               System.arraycopy(this.elementData, pos, this.elementData, pos + 1, this.tail - pos);
               this.tail = (this.tail + 1) % this.elementData.length;
            }

            this.elementData[pos] = element;
         }

         this.size++;
      }
   }

   @Override
   public boolean addAll(int index, Collection c) {
      boolean result = true;
      Iterator it = c.iterator();

      while (it.hasNext()) {
         result &= this.add(it.next());
      }

      return result;
   }

   private synchronized void writeObject(ObjectOutputStream s) throws IOException {
      s.writeInt(this.size);

      for (int i = this.head; i != this.tail; i = (i + 1) % this.elementData.length) {
         s.writeObject(this.elementData[i]);
      }
   }

   private synchronized void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      this.head = 0;
      this.size = this.tail = s.readInt();
      this.elementData = new Object[this.tail];

      for (int i = 0; i < this.tail; i++) {
         this.elementData[i] = s.readObject();
      }
   }
}
