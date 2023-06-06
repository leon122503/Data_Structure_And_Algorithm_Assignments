package cosc201.a1;

import java.util.Iterator;

import cosc201.unionfind.UF3;
import cosc201.unionfind.UnionFind;

public class ManyTest {
  
  public static void main(String[] args) {

    int n = 5;
    if (args.length > 0) {
      n = Integer.parseInt(args[0]);
    }
    ManyPuddles p = new ManyPuddles(n);
    // for (int i = 0; i < n; i++) {
    //   System.out.println(i + " " + p.getPoint(i));
    // }
    // System.out.println();

    UnionFind u = new UF3();
    u.make(n);
    int count = 0;

    Iterator<int[]> it = p.mergeIterator();
    while (it.hasNext()) {
      int[] pair = it.next();
      count++;
      u.union(pair[0], pair[1]);
      if (u.groups() == 1) break;
    }
    System.out.println(n + " " + count);

  }
}
