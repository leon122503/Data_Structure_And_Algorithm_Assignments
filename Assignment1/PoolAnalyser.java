package cosc201.a1;

import java.util.*;
import cosc201.unionfind.UnionFind;
import cosc201.unionfind.UF3;

/**
 * A modified version of this file is the only code you should submit.
 * 
 * You may add methods (and must complete some) but must not make changes
 * to the given data fields or constructor. You may, but should not need to
 * add data fields.
 * 
 * Methods that you need to complete currently just return some value of the
 * correct type. When you've written them they should return the correct answer
 * as described in the javadoc.
 * 
 * All methods should run correctly even if called more than once or in
 * sequence. This may require re-initialisation of the UnionFind data field
 * using its make method.
 * 
 * This is not necessarily the code you will use to carry out your experiments
 * (though it could be part of that). It's designed to test your understanding
 * of the UF data structure, and your ability to integrate that into code.
 * 
 */
public class PoolAnalyser {

  private final Puddles puddles;
  private final UnionFind uf;

  /**
   * Constructs a PoolAnalyser backed by the given UF instance and family of
   * puddles.
   * 
   * @param uf      a UnionFind instance
   * @param puddles a family of puddles
   */
  public PoolAnalyser(UnionFind uf, Puddles puddles) {
    this.uf = uf;
    this.puddles = puddles;
  }

  /**
   * Compuites the index startng from 1 of the last essential merge in
   * the given puddles. So, for example, if there are only two puddles
   * this would always return 1 since the first merge is the only essential
   * one.
   * 
   * @return the 1-based index of the last essential merge
   */
  public int lastEssentialMerge() {
    int n = puddles.count();
    int i = 0;
    int groups = n;

    uf.make(puddles.count());
    for (int[] pair : puddles.mergeOrder()) {
      uf.union(pair[0], pair[1]);
      if (uf.groups() < groups) {
        // System.out.println(" E");
        groups--;

      }
      i++;
      if (uf.groups() == 1) {
        break;
      }
    }

    return i;
  }

  /**
   * Computes the index starting from 1 of the first superfluous merge in
   * the given puddles. Note that if the number of puddles is at most two
   * then there are no such merges. In that case it should return -1.
   * 
   * @return the 1-based index of the first superfluous merge
   */
  public int firstSuperfluousMerge() {
    int n = puddles.count();
    int i = 0;
    int groups = n;

    uf.make(puddles.count());
    for (int[] pair : puddles.mergeOrder()) {
      uf.union(pair[0], pair[1]);
      i++;
      if (uf.groups() < groups) {
        groups--;
      } else {

        return i;
      }

    }

    return -1;
  }

  /**
   * Computes the number of superfluous merges that take
   * place in the first <code>numberOfMerges</code> merges.
   * 
   * @param numberOfMerges the number of merges to make
   * @return the number of superflous merges among the first
   *         <code>numberOfMerges</code> merges.
   */
  public int countSuperfluousMerges(int numberOfMerges) {
    int n = puddles.count();
    int i = 0;
    uf.make(puddles.count());
    int groups = n;
    int e = 0;
    int s = 0;

    for (int[] pair : puddles.mergeOrder()) {
      // System.out.print(String.format("%.4f", p0.distance(p1)));
      uf.union(pair[0], pair[1]);
      i++;
      if (uf.groups() < groups) {
        // System.out.println(" E");
        e++;
        groups--;
      } else {
        s++;
        // System.out.println(" S");
      }
      if (i == numberOfMerges) {
        break;
      }
    }
    return s;
  }

  /**
   * Computes the ratio between the total number of superfluous merges
   * and essential merges at the point where the last essential merge
   * takes place.
   * 
   * @return the final ratio between superfluous and essential merges
   *         when the last essential merge takes place.
   */
  public double superfluousRatio() {
    int n = puddles.count();
    int groups = n;
    double e = 0;
    double s = 0;
    uf.make(puddles.count());
    for (int[] pair : puddles.mergeOrder()) {
      uf.union(pair[0], pair[1]);
      if (uf.groups() < groups) {
        e++;
        groups--;
      } else {
        s++;
      }
      if (uf.groups() == 1) {
        break;
      }
    }

    double output = s / e;
    return output;
  }

}
