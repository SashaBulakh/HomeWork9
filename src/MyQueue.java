import org.w3c.dom.Node;

import java.util.Objects;

interface Queue<T>{
    void add(T element);
    T remove (int index);
    int size();
    void clear();
    T poll();
    T peek();
    String toString();

}
public class MyQueue<T> implements Queue<T> {
    public static void main(String[] args) {
        Queue<String> queue = new MyQueue<>();
            queue.add("First");
            queue.add("Second");
            queue.add("Third");
            queue.add("Fours");
            System.out.println(queue.toString());
            System.out.println(queue.size());
            System.out.println(queue.poll());
            System.out.println(queue.peek());
            System.out.println(queue.remove(2));
            queue.clear();

    }

    static final class Node<T> {
        private T element;
        private Node<T> next;

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }

        public Node(T element) {
            this.element = element;
        }
    }

    private int size;
    private Node<T> head;
    private Node<T> tail;

    private Node<T> getNodeByIndex(int index){
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;

    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement;
        if (index == 0){
            removedElement = head.element;
            head = head.next;
            if (head == null){
                tail = null;
            }
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            removedElement = prev.next.element;
            prev.next = prev.next.next;
            if (index == size -1){
                tail = prev;
            }
        }
        size --;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public T poll() {
        if (head != null) {
            T element = head.element;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return element;
        } else {
            return null;
        }
    }

    @Override
    public T peek() {
        if (head != null) {
            T element = head.element;
            return element;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Node<T> current = head; current != tail.next; current = current.next) {
            result += current.element + " ";
        }
        return result;
    }
}
