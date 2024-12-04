public class Bovary {

    static HMap buildTable(String[] files, int n) {
        HMap map = new HMap();
        WordReader r;
        Prefix p;
        for (String file : files) {
            r = new WordReader(file);
            p = new Prefix(n);
            for (String w = r.read(); null != w; w = r.read()) {
                map.add(p, w);
                p = p.addShift(w);
            }
            map.add(p, Prefix.end);
        }
        return map;
    }


    static void generate(HMap t, int n) {}


}
