public class HMap {

    private static final int DEFAULTSIZE = 20;

    EntryList[] t;
    int nbEntries;

    HMap(int n) {
        t = new EntryList[n];
    }

    HMap() {
        t = new EntryList[DEFAULTSIZE];
    }


    public WordList find(Prefix key) {
        EntryList l = t[key.hashCode(t.length)];
        if (l == null) {return null;}
        while (l != null) {if (Prefix.eq(l.head.key, key)) {return l.head.value;}; l = l.next;}
        return null;
    }


    void addSimple(Prefix key, String word) {
        int hash = key.hashCode(t.length);
        if (null == t[hash]) {
            t[hash] = new EntryList(new Entry(key, new WordList(new String[]{word})), null);
            nbEntries += 1;
            return;
        }
        for (EntryList entry = t[hash]; null != entry; entry = entry.next) {
            if (Prefix.eq(entry.head.key, key)) {entry.head.value.addFirst(word); return;}
        }
        t[hash] = new EntryList(new Entry(key, new WordList(new String[]{word})), t[hash]);
        nbEntries += 1;
    }

    void rehash(int n) {
        EntryList[] data = t;
        t = new EntryList[n];
        nbEntries = 0;
        for (EntryList entrylist : data) {
            for (; null != entrylist; entrylist = entrylist.next) {
                for (String word = entrylist.head.value.removeFirst(); null != word; word = entrylist.head.value.removeFirst()) {
                    addSimple(entrylist.head.key, word);
                }
            }
        }
    }

    void add(Prefix key, String w) {
        addSimple(key, w);
        if (nbEntries >= 0.75 * t.length) {
            rehash(2 * t.length);
        }
    }

    public static void main(String[] args) {
    }
}
