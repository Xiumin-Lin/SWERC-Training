package lin;

public class Personne {
    private final int val;
    private Personne prev;
    private Personne next;

    public Personne(int val, Personne pre, Personne next) {
        this.val = val;
        this.prev = pre;
        this.next = next;
    }

    public int getVal() { return val; }

    public Personne getPrev() { return prev; }

    public void setPrev(Personne prev) { this.prev = prev; }

    public Personne getNext() { return next; }

    public void setNext(Personne next) { this.next = next; }
}
