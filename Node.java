public class Node<E>{

    private E _value;
    private Node<E> _next;

    public Node(E value, Node<E> next){
        _value = value;
        _next = next;
    }

    // accessor methods
    public E getValue(){
        return _value;
    }

    public Node<E> getNext(){
        return _next;
    }

    // modifier methods
    public E setValue(E val){
        E ans = getValue();
        _value = val;
        return ans;
    }

    public Node<E> setNext(Node<E> t){
        Node<E> ans = getNext();
        _next = t;
        return ans;
    }

    public String toString(){
        return getValue().toString();
    }

    public static void main(String  [] args){
        Node a = new Node("Sue", new Node("Mary", new Node("Bill", null)));
        System.out.println(a);
        System.out.println(a.getNext());
        System.out.println(a.getNext().getNext());
        System.out.println(a.getNext().getNext().getNext());
        System.out.println(a.getNext().getNext().getNext().getNext());
    }






}
