public class WordList {

    static WordList foobar = new WordList(new String[]{"foo", "bar", "baz"});


    Node content;

    WordList() {
        content = null;
    }
    
    WordList(Node n) {
        content = n;
    }

    WordList(String[] words) {
        content = null;
        for (String word : words) {
            addLast(word);
        }
    }



    int length() {return Node.length(content);}

    public String toString() {return Node.makeString(content);}

    void addFirst(String w) {content = new Node(w, content);}

    void addLast(String w) {if (content == null) {content = new Node(w, null);} else {Node.addLast(w, content);}}

    String removeFirst() {if (content == null) {return null;} else {String c = content.head; content = content.next; return c;}}

    String removeLast() {
        if (content == null) {return null;}
        String c;
        if (content.next == null) {c = content.head; content = null;}
        else {Node cur = content; while (cur.next.next != null) {cur = cur.next;}; c = cur.next.head; cur.next = null;}
        return c;
    }

    void insert(String s) {content = Node.insert(s, content);}

    void insertionSort() {content = Node.insertionSort(content);}

    String[] toArray() {
        String[] res = new String[length()];
        int i = 0;
        Node cur = content;
        while (cur != null) {res[i] = cur.head; cur = cur.next; i++;}
        return res;
    }

    public static void main(String[] args) {
    }
    }