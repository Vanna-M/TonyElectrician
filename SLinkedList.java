public class SLinkedList<E>{

    private Node<E> _head, _tail;
    private int _size;


    //*********** constructor method ***********

    public SLinkedList(){
        _head = _tail = null;
        _size = 0;
    }

    //*********** modifier methods ***********

    //O(1)
    public void addFirst(E val){
        if (_size == 0) _head = _tail = new Node<E>(val, null);
        else _head = new Node<E>(val, _head);
        ++_size;
    }

    //O(1)
    public void addLast(E val){
        if (_size == 0) addFirst(val);
        else {
            _tail.setNext(new Node<E>(val, null));
            _tail = _tail.getNext();
            ++_size;
        }
    }

    public boolean isEmpty(){
        return _size == 0;
    }

    //O(1)
    public E removeFirst(){
        if (_size == 0) throw new IllegalStateException("List size 0, can't remove");
        Node<E> t = _head;
        if (_size == 1)
            _head = _tail = null;
        else
            _head = _head.getNext();
        --_size;
        return t.getValue();
    }

    //O(1)
    public E removeLast(){
        if (_size <= 1) return removeFirst();
        Node<E> t = _tail;
        Node<E> h = _head;
        while (h.getNext().getNext() != null){
            h = h.getNext();
        }
        h.setNext(null);
        _tail = h;
        --_size;
        return t.getValue();
    }

    //O(n)
    public void reverse(){
        if (_size > 1){
            E t = removeLast();
            reverse();
            addFirst(t);
        }
    }

    // postcondition: returns null if key is not in the list.
    public Node<E> search(E key){
        Node<E> t = _head;
        while (t.getNext() != null){
            if (t.getValue().equals(key)) return t;
            t = t.getNext();
        }
        return null;
    }

    //
    public void swap(Node<E> x, Node<E> y){
        //if same
        if (x==y) return;

        Node<E> prevX = new Node<E>(null, _head);
        //make it so that x preceds y
        while(prevX.getNext() != x && prevX.getNext() != y){
            prevX = prevX.getNext();
        }
        if (prevX.getNext() == y){
            Node<E> t = x;
            x = y;
            y = t;
        }

        //find y
        Node<E> prevY = prevX.getNext();
        while (prevY.getNext() != y){
            prevY = prevY.getNext();
        }
        //special cases
        if (_head == x) _head = y;
        if (_tail == y) _tail = x;

        //put them in list
        prevX.setNext(y);
        prevY.setNext(x);
        //set what goes after them
        Node<E> afterY = y.getNext();
        y.setNext(x.getNext());
        x.setNext(afterY);
    }

    //*********** accessor methods ***********

    //O(1)
    // postcondition: throws an  Illegal State Exception if the list is empty
    //                otherwise returns the first value.
    public Node<E> getFirst(){
        return _head;
    }


    //O(1)
    // postcondition: throws an Illegal State Exception if the list is empty
    //                otherwise returns the last value.
    public Node<E> getLast(){
        if (_size == 0) getFirst();
        return _tail;
    }

    //O(n)
    // postcondition: throw an exception if i < 0 or i >= size.
    //                if L = [a,b,c,d]
    //                L.get(0) returns a
    //                L.get(1) returns b
    //                L.get(2) returns c
    //                L.get(3) returns d
    public Node<E> get(int i){
        if ((i < 0) || (i >= _size)) throw new IndexOutOfBoundsException("index out of bounds");
        Node<E> t = _head;
        for (int j = 0; j < i; ++j){
            t = t.getNext();
        }
        return t;
    }

    //*********** other methods ***********

    //O(n)
    public SLinkedList append(SLinkedList L){

        SLinkedList ret = new SLinkedList();

        Node<E> t = _head;
        ret.addLast(t.getValue());
        while (t.getNext() != null){
            t = t.getNext();
            ret.addLast(t.getValue());
        }

        t = L._head;
        ret.addLast(t.getValue());
        while (t.getNext() != null){
            t = t.getNext();
            ret.addLast(t.getValue());
        }
        return ret;
    }


    //O(n)
    public String toString(){
        String ret = "";
        Node<E> t = _head;
        ret += t + " ";
        while (t.getNext() != null){
            t = t.getNext();
            ret += t + " ";
        }
        return ret;
    }



    public E remove(int n){

        if (n >= _size) throw new IndexOutOfBoundsException("");

        Node<E> next = _head;

        for (int i = 0; i < n-2; ++i){
            next = next.getNext();
        }

        next.getNext().setNext(null);
        E ans = next.getNext().getValue();
        next.setNext(next.getNext().getNext());
        return ans;
    }

    public int size(){
        return _size;
    }



    //*********** main method ***********


    public static void main(String[] args){
        SLinkedList<String> l = new SLinkedList<String>();

        l.addFirst("Bob");
        l.addLast("Jane");
        l.addLast("Mary");
        l.addFirst("Lola");
        l.addFirst("Rico");
        l.addLast("Alexander");
        l.addLast("Hamilton");

        //list: Rico, Rico, Lola, Bob, Jane, Mary, Alexander, Hamilton
        System.out.println(l);
        System.out.println();
        System.out.print(l.get(3) + ", ");
        System.out.println(l.get(5));
        System.out.println();
        l.swap(l.get(3), l.get(5));
        System.out.println(l);


        System.out.print(l.get(0) + ", ");
        System.out.println(l.get(6));
        l.swap(l.get(0),l.get(6));
        System.out.println(l);



        System.out.print(l.get(1) + ", ");
        System.out.println(l.get(1));
        l.swap(l.get(1),l.get(1));
        System.out.println(l);


    }
}
