import java.util.EmptyStackException;


public class Pilha {

    private class Node {
        public String element;
        public Node next;

        public Node(String element) {
            this.element = element;
            next = null;
        }     
    }
    private Node head;
    private Node tail;
    private int count=0;

    public void push(String e) { //O(1)
        Node n = new Node(e);
        if (head == null) {
            head = n;
        } else {
            tail.next = n;
        }
        tail = n;
        count++;
    }

    public String pop() { //O(n)
        if(head==null){
            throw new NullPointerException("Pilha vazia");
        }
        String elemRemovido = tail.element;
        if (count == 1) {// se tinha apenas um elemento na lista
            tail = null;
            head=null;
        }
        else{
            Node ant = head; //referencia para o anterior
            for (int i=0; i <= count - 1; i++) {
                if(ant.next==tail) {
                    ant.next=null;
                    tail=ant;
                }
                else
                  ant = ant.next;
            }
        }
        count--;
        return elemRemovido;
    }

    public String top() { 
        if (count == 0) {
            throw new EmptyStackException();
        }
        return tail.element;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }  
}