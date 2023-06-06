package cosc201.a1;

import cosc201.utilities.*;
import cosc201.unionfind.UF1;
import cosc201.unionfind.UF2;
import cosc201.unionfind.UF3;
import cosc201.unionfind.UF4;
import cosc201.unionfind.UnionFind;
import java.util.ArrayList;

/**
 * A simple demo to show how the Puddles class works. It just initialises a list
 * of 5 puddles (or n if given a command-line argument), prints their positions
 * (Point2D defaults to two decimal places) and then prints the list of pairs of
 * puddles in sorted order of distance.
 */
public class A1Demo {

  /**
   * Runs (versions of) the demo
   * 
   * @param args optional number of puddles
   */
  public static void main(String[] args) {
    int n = 14000;

    if (args.length > 0) {
      n = Integer.parseInt(args[0]);
    }
    Timer t = new Timer();

    // exp1(n);
    // t.stop();
    // System.out.println(t.getTime() / 1000000);

    // for (int i = 100; i < 10000; i = i * 2) {
    // Puddles p = new Puddles(i);
    // UnionFind u = new UF4();
    // PoolAnalyser pa = new PoolAnalyser(u, p);
    // int x = pa.countSuperfluousMerges(pa.lastEssentialMerge());
    // System.out.println("1 " + i);
    // System.out.println("2 " + x);
    // }

    for (int i = 1000; i < 15001; i = i + 1000) {
      t.reset();
      Puddles p = new Puddles(i);
      t.start();
      test2(p);
      t.stop();
      System.out.println(t.getTime());
    }

    // for (int trial = 0; trial < 100; trial++) {
    // exp1(n);
    // }
    // exp1(n);

    // Timer t;
    // while (true) {
    // t = new Timer();
    // t.start();
    // exp1(n);
    // t.stop();
    // System.out.println(n + " " + t.getTime());
    // if (t.getTime() > 1.0E+9) {
    // break;
    // }
    // n += 5;
    // }

  }

  public static void demo1(int n, Puddles p) {

    for (int i = 0; i < n; i++) {
      System.out.println(i + " " + p.getPoint(i));
    }
    System.out.println();
    for (int[] pair : p.mergeOrder()) {
      System.out.print(pair[0] + " " + pair[1] + " ");
      Point2D p0 = p.getPoint(pair[0]);
      Point2D p1 = p.getPoint(pair[1]);
      System.out.println(String.format("%.4f", p0.distance(p1)));
    }
  }

  public static void demo2(int n, Puddles p) {
    for (int i = 0; i < n; i++) {
      System.out.println(i + " " + p.getPoint(i));
    }
    System.out.println();
    UnionFind u = new UF3();
    u.make(n);
    int groups = u.groups();
    for (int[] pair : p.mergeOrder()) {
      System.out.print(pair[0] + " " + pair[1] + " ");
      Point2D p0 = p.getPoint(pair[0]);
      Point2D p1 = p.getPoint(pair[1]);
      System.out.print(String.format("%.4f", p0.distance(p1)));
      u.union(pair[0], pair[1]);
      if (u.groups() < groups) {
        System.out.println(" E");
        groups--;
      } else {
        System.out.println(" S");

      }
    }
  }

  public static void test1(int n) {
    Puddles p = new Puddles(n);
  }

  public static void test2(Puddles p) {
    p.mergeOrder();
  }

  public static void test3(int n) {
    Puddles p = new Puddles(n);
    UnionFind u = new UF4();

    u.make(n);
    Timer t = new Timer();

    ArrayList<int[]> x = p.mergeOrder();
    t.start();

    for (int[] pair : x) {
      u.union(pair[0], pair[1]);
      if (u.groups() == 1)
        break;
    }
    t.stop();
    System.out.println(n + " " + t.getTime() / 1000000);

  }

  public static void exp1(int n) {
    Puddles p = new Puddles(n);
    // for (int i = 0; i < n; i++) {
    // System.out.println(i + " " + p.getPoint(i));
    // }
    // System.out.println();
    UnionFind u = new UF4();
    u.make(n);
    int e = 0;
    int s = 0;
    int groups = n;
    for (int[] pair : p.mergeOrder()) {
      // System.out.print(pair[0] + " " + pair[1] + " ");
      Point2D p0 = p.getPoint(pair[0]);
      Point2D p1 = p.getPoint(pair[1]);
      // System.out.print(String.format("%.4f", p0.distance(p1)));
      u.union(pair[0], pair[1]);
      if (u.groups() < groups) {
        // System.out.println(" E");
        e++;
        groups--;
      } else {
        s++;
        // System.out.println(" S");
      }
      if (u.groups() == 1)
        break;
    }
    // System.out.println(n + " " + e + " " + s);
  }

}
