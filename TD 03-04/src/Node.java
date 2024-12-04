public class Node {
    String head;
    Node next;

    Node(String head, Node next) {
        this.head = head;
        this.next = next;
    }

    public static Node foobar = new Node("foo", new Node("bar", new Node("baz", null)));


    static int lengthRec(Node l) {
        if (l == null) {return 0;} else {return 1+lengthRec(l.next);}
    }
    

    public static int length(Node l) {
        int r = 0;
        while (l != null) {l = l.next; r++;}
        return r;
    }


    public static String makeString(Node l) {
        String s = "[";
        while (l != null) {if (l.next == null) {s += l.head; l = l.next;} else {s += l.head; s+= ", "; l = l.next;}}
        s +="]";
        return s;
    }

    public static void addLast(String s, Node l) {
        while (l.next != null) {l = l.next;}
        l.next = new Node(s, null);
    }


    public static Node copy(Node l) {
        if (l == null) {return null;} else {
            Node res = new Node(l.head, null);
            Node curRes = res;
            for (Node cur = l.next; null != cur; cur = cur.next) {
                curRes.next = new Node(cur.head, null);
                curRes = curRes.next;
            }
            return res;
        }
    }


    public static Node insert(String s, Node l) {
        if (l == null) {return new Node(s, null);}
        if (s.compareTo(l.head) <= 0) {return new Node(s, l);}
        l.next = insert(s, l.next);
        return l;
    }

    public static Node rec(Node m, Node n) {
        if (m != null) {n = insert(m.head, n); return rec(m.next, n);} else {return n;}
    }

    public static Node insertionSort(Node l) {
        return rec(l, null);
    }


    public static Node merge(Node l1, Node l2) {
        Node c = copy(l1);
        if (l1 == null) {return l2;}
        while (l2 != null) {insert(l2.head, c); l2 = l2.next;}
        return c;
    }



    public static void main(String[] args) {
    }
}
