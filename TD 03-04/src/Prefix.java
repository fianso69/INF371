import static java.lang.Math.floorMod;


public class Prefix {

    String[] t;

    final static String start = "<START>", end = "<END>", par = "<PAR>";


    Prefix(int n) {
        t = new String[n];
        for (int i = 0; i < n; i++) {t[i] = start;}
    }

    static boolean eq(Prefix p1, Prefix p2) {
        if (p1.t.length != p2.t.length) {return false;}
        for (int i = 0; i < p1.t.length; i++) {if (!p1.t[i].equals(p2.t[i])) {return false;}}
        return true;
    }


    Prefix addShift(String w) {
        Prefix res = new Prefix(t.length);
        if (t.length == 0) {return res;}
        if (t.length == 1) {res.t[0] = w; return res;}
        for (int i = 0; i < t.length - 1; i++) {res.t[i] = t[i+1];}
        res.t[t.length-1] = w;
        return res;
    }


    public int hashCode() {
        int h = 0;
        for (String s : t) {
            h = 37*h+s.hashCode();
        } 
        return h;
    }

    int hashCode(int n) {
        return floorMod(hashCode(), n);
    }


    public static void main(String[] args) {
    }
}
