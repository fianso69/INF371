public class UnionFind {
    static int[] equiv;
    static int[] height;


    public static void init(int len) {
        equiv = new int[len];
        height = new int[len];
        for (int i = 0; i < len; i++) {equiv[i] = i; height[i] = 0;};
    }

    public static int naiveFind(int x) {
        return(equiv[x]);
    }


    public static int naiveUnion(int x, int y) {
        int len = equiv.length;
        int eq_x = naiveFind(x);
        int eq_y = naiveFind(y);
        for (int i = 0; i < len; i++) {if (equiv[i] == eq_x) {equiv[i] = eq_y;}}
        return(eq_y);
    }


    public static int find(int x) {
        return(logFind(x));
    }


    public static int union(int x, int y) {
        return(logUnion(x, y));
    }


    public static int fastFind(int x) {
        if (equiv[x] == x) {return(x);} else {return(fastFind(equiv[x]));}
    }

    public static int fastUnion(int x, int y){
        int len = equiv.length;
        int eq_x = fastFind(x);
        int eq_y = fastFind(y);
        for (int i = 0; i < len; i++) {if (equiv[i] == eq_x) {equiv[i] = eq_y;}}
        return(eq_y);
    }


    public static int logFind(int x) {
        int eq_x = equiv[x];
        if (equiv[x] == x) {return(x);} else {equiv[x] = equiv[eq_x]; return(fastFind(equiv[x]));}
    }

public static int logUnion(int x, int y) {
    int eq_x = logFind(x);
    int eq_y = logFind(y);
    int h_x = height[eq_x];
    int h_y = height[eq_y];
    if (h_x < h_y) {equiv[eq_x] = eq_y; return eq_y;} 
    else {if (h_x == h_y) {equiv[eq_x] = eq_y; height[eq_y]++; return eq_y;} else {equiv[eq_y] = eq_x; return eq_x;}}
    }



    public static void main(String[] args){
    } 
}