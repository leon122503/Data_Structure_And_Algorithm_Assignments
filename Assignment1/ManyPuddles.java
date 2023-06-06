package cosc201.a1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A class representing a family of puddles in the plane -- lots of them,
 * so mergeOrder is not an appropriate way to iterate over the pairs in 
 * increasing order of distance.
 * 
 * Instead a method called mergeIterator() is given. This is an iterator that
 * returns pairs of points in increasing order of distance. However, it does it
 * without generating a complete list of all pairs - instead it generates them
 * by filtering out the point pairs whose distances lie in certain intervals (starting)
 * with an interval [0, r_0) then an interval [r_0, r_1), etc.
 * 
 * The exact interval sizes are informed by some back of the envelope calculations
 * that seem to work out ok - on my machine I can now handle up to 100K puddles in
 * a few seconds.
 * 
 * Use of ManyPuddles is entirely optional in A1.
 * 
 * @author Michael Albert
 * 
 */
public class ManyPuddles extends Puddles {

  public ManyPuddles() {
  }

  public ManyPuddles(int n) {
    super(n);
  }

  public Iterator<int[]> mergeIterator() {

    
    return new Iterator<int[]>() {

      ArrayList<int[]> pairs = new ArrayList<int[]>();
      // Magic numbers - basically ensure we expect a constant number of points within a
      // distance between min_r and min_r+delta_r from any other point so that the total
      // number of point pairs within this distance grows linearly with n.
      double delta_r;
      double min_r = 0.0;
      Iterator<int[]> pairsIterator;

      {
        // System.out.println("Initialising");
        min_r = 0.0;
        delta_r = 1/Math.sqrt(n);
        pairs = filteredPairs(min_r, min_r+delta_r);
        pairsIterator = pairs.iterator();
      }
  
      @Override
      public boolean hasNext() {
        if (pairsIterator.hasNext()) {
          return true;
        }
        min_r = min_r + delta_r;
        if (min_r*min_r >= 2.0) return false;
        delta_r = 1/(2*n*min_r);
        //delta_r = 0.03/(2*Math.PI*min_r*sn);
        pairs = filteredPairs(min_r, min_r+delta_r);
        pairsIterator = pairs.iterator();
        return hasNext();
      }

      @Override
      public int[] next() {
        return pairsIterator.next();
      }

    };
  }


  private ArrayList<int[]> filteredPairs(double min, double max) {
    
    // System.out.println("Generating pairs between " + min + " and " + max);
    ArrayList<int[]> result = new ArrayList<>();
    double min2 = min*min;
    double max2 = max*max;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        Point2D p = getPoint(i);
        Point2D q = getPoint(j);
        if (p.distance2(q) >= min2 && p.distance2(q) < max2) {
          result.add(new int[] { i, j });
        }
      }
    }

    // The following lines of code are rather opaque!
    // What we're doing is asking to sort the result list according to a comparator
    // that is being defined anonymously within the call to sort.
    result.sort(new Comparator<int[]>() {

      // To compare two pairs of points we compute the difference between the squares
      // of the distances between them. We return -1, 0 or 1 according to whether this
      // distance is negative, zero, or positive.
      @Override
      public int compare(int[] o1, int[] o2) {
        double dd = getPoint(o1[0]).distance2(getPoint(o1[1])) - getPoint(o2[0]).distance2(getPoint(o2[1]));
        if (dd == 0.0)
          return 0;
        return (dd < 0 ? -1 : 1);
      }

    });
    // Uncomment this if you want to see how the groups of points behave in terms of size
    // System.out.println("Generated list of size " + result.size());
    return result;
  }

  
  
}
