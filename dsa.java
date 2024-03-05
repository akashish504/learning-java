
import java.util.HashMap;

interface stackstructure {
   void getElement();
   void pushElement(int x);
   void popElement();
}

class Stack implements stackstructure{
    private int size;
    private int current_pointer;
    private Integer[] arr;


    public Stack (int size_of_stack) {
        size = size_of_stack;
        current_pointer = -1;
        arr = new Integer[size];
    }

    @Override
    public void getElement() {
        if (current_pointer == -1) {
            System.out.println("stack is empty");
            return;
        }
        System.out.println(arr[current_pointer]);
        return;
    }

    @Override
    public void pushElement(int x) {
        if (current_pointer == size - 1) {
            System.out.println("stack overflow");
            return;
        }
        arr[++current_pointer] = x;
        System.out.println("Element Successfully stacked");
        return;
    }

    @Override
    public void popElement (){
        if (current_pointer == -1) {
            System.out.println("stack is empty");
            return;
        }
        System.out.println(arr[current_pointer]);
        arr[current_pointer] = null;
        current_pointer = current_pointer -1;
        return;
    }

    public static void main(String[] args) {
        Stack s = new Stack(3);
        s.pushElement(64);
        s.pushElement(32);
        s.pushElement(16);
        s.pushElement(8);
        s.getElement();
        s.popElement();
        s.popElement();
        s.popElement();
        s.popElement();
    }

}

class DoublyLinkedList {
    Integer key;
    Integer value;
    DoublyLinkedList next;
    DoublyLinkedList prev;

    public DoublyLinkedList(Integer key, Integer value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

interface lrustructure {
    Integer get(Integer x);
    void put(Integer key, Integer value);
    // void add_node(DoublyLinkedList node);
    // void remove_node(DoublyLinkedList node);
    // void move_to_head(DoublyLinkedList node);

}



class LRUCache implements lrustructure {
    
    DoublyLinkedList head = new DoublyLinkedList(null,null);
    DoublyLinkedList tail = new DoublyLinkedList(null,null);
    int capacity;

    HashMap<Integer, DoublyLinkedList> map = new HashMap<>();

    public LRUCache(int size) {
        this.capacity = size;
        this.head.next = tail;
        this.tail.prev = head;
    }

    private void add_node(DoublyLinkedList node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void remove_node(DoublyLinkedList node) {
        DoublyLinkedList prev_node = node.prev;
        DoublyLinkedList next_node = node.next;
        prev_node.next = next_node;
        next_node.prev = prev_node;

        // node.prev.next = node.next;
        // node.next.prev = node.prev;

        // node.prev = null;
        // node.next = null;
    }

    private void move_to_head(DoublyLinkedList node) {
        this.remove_node(node);
        this.add_node(node);
    }

    public void printList() {
        DoublyLinkedList node = this.head.next;
        while (node.key != null) {
            System.out.println("key -> " + node.key);
            System.out.println("value -> " + node.value);
            node = node.next;
        }
    }

    @Override
    public void put(Integer key, Integer value) {
        if (map.containsKey(key)) {
            DoublyLinkedList node = this.map.get(key);
            node.value = value;
            this.move_to_head(node);
            return;
        } else {
            if (this.map.size() >= this.capacity) {
                DoublyLinkedList tail_prev = this.tail.prev;
                // Integer tail_key = tail_prev.key;
                this.remove_node(tail_prev);
                this.map.remove(tail_prev.key);
            }
            DoublyLinkedList node = new DoublyLinkedList(key, value);
            this.map.put(key,node);
            this.add_node(node);
        }
        return;
    };

    @Override
    public Integer get(Integer x){
        if (map.containsKey(x)) {
            DoublyLinkedList node = this.map.get(x);
            this.move_to_head(node);
            return(node.value);
        }
        return(-1);
    }

    public static void main(String[] args) {
        LRUCache new_cache = new LRUCache(3);
        new_cache.put(1,2);
        new_cache.put(3,4);
        new_cache.put(1,4);
        new_cache.printList();

        new_cache.put(5,6);
        new_cache.printList();
    }
       
}