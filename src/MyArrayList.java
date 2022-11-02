import java.util.Arrays;

import java.util.Objects;

interface ArrayList<T>{
    void add(T element);
    T remove (int index);
    int size();
    void clear();
    T get(int index);
    void print();
}
    public class MyArrayList<T> implements ArrayList<T> {
        public static void main(String[] args) {
            MyArrayList<String> list = new MyArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        list.add("Fife");
        list.print();
        list.size();
        list.get(1);
        list.remove(2);
        list.print();
        list.clear();
        list.print();

        }
        private Object[] elements;
        private static final int DEFAULT_CAPACITY = 5;
        private int size;
        private void resize(){
            if (elements.length == size){
                Object[] newArray = new Object[elements.length * 2];
                System.arraycopy(elements, 0, newArray, 0, size);
                elements= newArray;
            }
        }
        public MyArrayList(int initCapacity){
            if (initCapacity<=0){
                throw new IllegalArgumentException();

            }
            elements = new Object[initCapacity];
        }

        public MyArrayList(){
            this(DEFAULT_CAPACITY);
        }

        @Override
        public void add(T element) {
            resize();
            elements[size] = element;
            size++;
        }

        @Override
        public T remove(int index) {
            Objects.checkIndex(index, size);
            T removerEl = (T) elements[index];
            System.arraycopy(elements, index+1, elements, index, size - index - 1);
            size--;
            return removerEl;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public void clear() {
            size = 0;
            elements = new Object[DEFAULT_CAPACITY];
        }

        @Override
        public T get(int index) {
            return (T) elements[index];
        }

        @Override
        public void print() {
            System.out.println(Arrays.toString(elements));
        }
    }
