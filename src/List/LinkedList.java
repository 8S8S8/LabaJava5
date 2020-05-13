package List;

public class LinkedList<T> {
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T value) {
        if (head == null) {//Случай когда список пустой
            head = new Node(value, null);
            tail = head;
        } else {
            tail.next = new Node(value,tail);
            tail = tail.next;
        }
        size++;
    }

    public void add(T value, int pos) {
        if (pos < 0) throw new ArrayIndexOutOfBoundsException();
        else if (pos >= size || head == null) add(value);
            //todo make it faster
        else if(pos==0 && head!=tail){
            Node buf = new Node(value,null);
            buf.next = head;
            head = buf;
        }else{
            Node buf = head;
            for(int i = 0; i<pos;i++){
                buf = buf.next;
            }
            Node prev = buf.prev;
            Node next = buf;
            prev.next =
                    new Node(value,prev);
            prev.next.next = next;
            next.prev = prev.next;
        }
        size++;
    }

    public T get(int index){
        Node<T> buf = head;
        for(int i = 0; i<index;i++){
            buf = buf.next;
        }
        return buf.value;
    }

    public void delete(int index){
        if(index>=size || index<0) throw new IndexOutOfBoundsException();
        if(size==1){ head = tail = null; }
        else if(index == 0){ head = head.next; }
        else if(index == size-1){
            tail = tail.prev;
            tail.next = null;
        }
        else{
            Node<T> buf = head;
            for(int i = 0; i<index;i++){
                buf = buf.next;
            }
            Node next = buf.next;
            Node prev = buf.prev;
            prev.next = next;
            next.prev = prev;
        }
        size--;
    }

    public void set(T value, int index){
        Node<T> buf = head;
        for(int i = 0; i<index;i++){
            buf = buf.next;
        }
        buf.value = value;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        if(head==null) return null;
        String str = "";
        Node buf = head;
        str+="["+buf.value.toString()+"]";
        while(buf.next!=null){
            buf = buf.next;
            str+="["+buf.value.toString()+"]";
        }
        return str;
    }

    public int indexOf(T value){
        Node<T> buf = head;
        int index = 0;
        do{
            if(buf.value.equals(value)) return index;
            buf = buf.next;
            index++;
        }while (buf.next!=null);
        return -1;
    }

    public int lastIndexOf(T value){
        Node<T> buf = tail;
        int index = size-1;
        do{
            if(buf.value.equals(value)) return index;
            buf = buf.prev;
            index--;
        }while (buf.prev!=null);
        return -1;
    }
}



class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value, Node<T> prev) {
        this.value = value;
        this.prev = prev;
    }

}