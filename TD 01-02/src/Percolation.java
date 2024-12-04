public class Percolation {
    static int size = 10;
    static int length = size * size;
    static boolean[] grid = new boolean[length];


    public static void init() {
        grid = new boolean[length];
        for (int i = 0; i < length; i++) {grid[i] = false;};
        UnionFind.init(length+2);
    }

    public static void print(){
        int i;
        int j;
        for (i = 0; i < size; i++) {
            String s = "";
            for (j = 0; j < size; j++) {
                if (grid[i*size+j]) {s = s.concat("*");} else {s = s.concat("-");};
            }
            System.out.println(s);
            System.out.println();
        }
    }

    public static int randomShadow() {
        double e = length * Math.random();
        while (grid[(int)Math.floor(e)]) {e = Math.random()*(double) length;}
        grid[(int)Math.floor(e)] = true;
        propagateUnion((int)Math.floor(e));
        return((int) Math.floor(e));
    }


   public static boolean detectPath(boolean[] seen, int n, boolean up) {
    if (up && n < size) {return(true);};
    if (!up && n >= size*(size - 1)) {return(true);};
    int[] neighbors = new int[] {-1, -1, -1, -1};
    if (n >= size) {neighbors[0] = n - size;};
    if (n%size != 0) {neighbors[1] = n - 1;};
    if (n%size != size-1) {neighbors[2] = n + 1;};
    if (n < size*(size - 1)) {neighbors[3] = n + size;};
    boolean result = false;
    int compteur = 0;
    while (!result && compteur < 4) {
        if (neighbors[compteur] >= 0) {
            if (grid[neighbors[compteur]] && !seen[neighbors[compteur]]) 
            {seen[neighbors[compteur]] = true; result = detectPath(seen, neighbors[compteur], up);}
            else {seen[neighbors[compteur]] = true; compteur ++;};}
        else {compteur ++;};    
    }
   return result;
}

   public static boolean isNaivePercolation(int n) {
    boolean[] seen = new boolean[length];
    return(detectPath(seen, n, true) && detectPath(seen, n, false));
   }


   public static boolean isPercolation() {
    return(isLogPercolation());
   }


   public static double percolation() {
    double i = 0;
    init();
    while (!isPercolation()) {
    randomShadow();
    i += 1;
    }
    return(i/(double) length);
   }

   public static double monteCarlo(int n) {
    double s;
    int m;
    s = 0;
    for (m = 0; m < n; m++) {init(); s = s + percolation();}
    return(s/(double) n);
   }



   public static boolean isFastPercolation(int n) {
    int v = UnionFind.find(n);
    boolean b = false;
    boolean c = false;
    for (int i = 0; i < size; i++) {if (grid[i]) {b = b || (UnionFind.find(i) == v);};}
    for (int j = size*(size-1); j < length; j++) {if (grid[j]) {c = c || (UnionFind.find(j) == v);};}
    return(b && c && grid[n]);
   }


   

   private static void elementaryPropagateUnionLog(int x, int newX) {
    if (grid[newX] && UnionFind.find(newX) != UnionFind.find(x)) {
        UnionFind.union(newX, x);
        propagateUnion(newX);
    }
}


   static void propagateUnion(int x) {
    if (size <= x) {
        elementaryPropagateUnionLog(x, x - size);
    }
    if (length - size > x) {
        elementaryPropagateUnionLog(x, x + size);
    }
    if (0 != x % size) {
        elementaryPropagateUnionLog(x, x - 1);
    }
    if (size - 1 != x % size) {
        elementaryPropagateUnionLog(x, x + 1);
    }
    if (size > x) {
        UnionFind.union(x, length);
    }
    if (length - size <= x) {
        UnionFind.union(x, length + 1);
    }
}


   static boolean isLogPercolation() {
return (UnionFind.find(length) == UnionFind.find(length + 1));
   }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        System.out.println(monteCarlo(100000));
        long t2 = System.currentTimeMillis();
        System.out.print(t2-t1);
    }
}
