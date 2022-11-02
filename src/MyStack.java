import java.util.Objects;

interface Stack<T> {
    void push(T element);
    T remove (int index);
    T pop();
    T peek();
    int size();
    void clear();
}


    public class MyStack<T> implements Stack<T>{

        public static void main(String[] args) {
            Stack<String> stack = new MyStack<>();
            stack.push("One");
            stack.push("Two");
            stack.push("Three");
            stack.push("Four");
            System.out.println(stack.pop());
            System.out.println(stack.peek());
            System.out.println(stack.remove(2));
            System.out.println(stack.size());
            stack.clear();

        }
        private static class Node<T> {
            T element;
            Node<T> next;

            public static <T> Node<T> valueOf(T element) {
                return new Node<>(element);
            }

            private Node(T element) {
                this.element = element;
            }
        }
        private Node<T> head;
        private int size = 0;

        @Override
        public void push(T element) {
            Objects.requireNonNull(element);
            Node<T> newNode = Node.valueOf(element);
            if (head != null){
            newNode.next = head;
            }
            head = newNode;
            size++;
        }
        private Node<T> getNodeByIndex(int index){
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
        @Override
        public T remove(int index) {
            Objects.checkIndex(index, size);
            T removedElement;
            if (index == 0) {
                removedElement = head.element;
                head = head.next;
                if (head == null) {
                    head = null;
                }
            } else {
                Node<T> prev = getNodeByIndex(index - 1);
                removedElement = prev.next.element;
                prev.next = prev.next.next;
                if (index == size - 1) {
                    head = prev;
                }
            }
            size --;
            return removedElement;
        }

        @Override
        public T pop() {
            if (head != null) {
                T element = head.element;
                head = head.next;
                if (head == null) {
                }   size--;
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
        public int size() {
            return size;
        }

        @Override
        public void clear() {
            head = null;
            size = 0;
        }


    }


