import java.util.Objects;

interface List<T> {

    void add(T element);

    T remove(int index);

    void clear();

    int size();

    T get(int index);

    String toString();

}

public class MyLinkedList<T> implements List<T> {
    public static void main(String[] args) {
        List<String> list = new MyLinkedList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        System.out.println(list.toString());
        System.out.println(list.get(2));
        System.out.println(list.size());
        System.out.println(list.remove(1));
        list.clear();
    }

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    private int size;
    private Node<T> first;
    private Node<T> last;

    private Node<T> getNodeByIndex(int index) {
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (size == 0) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement;
        if (index == 0) {
            removedElement = first.element;
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            removedElement = prev.next.element;
            prev.next = prev.next.next;
            if (index == size - 1) {
                last = prev;
            }
        }
        size--;
        return removedElement;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).element;
    }

    @Override
    public String toString() {
        String result = "";
        for (Node<T> current = first; current != last.next; current = current.next) {
            result += current.element + " ";
        }
        return result;
    }
}

