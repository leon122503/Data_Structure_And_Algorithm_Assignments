package cosc201.a1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * A class representing a family of puddles in the plane.
 */
public class Puddles {

  static final Random R = new Random();
  static final int DEFAULT_SIZE = 10;

  // Changed modifiers to package
  int n;
  Point2D[] points;

  /**
   * A family of the default number of puddles (10)
   */
  public Puddles() {
    this(DEFAULT_SIZE);
  }

  /**
   * A family of a given number of puddles.
   * 
   * @param n the number of puddles
   */
  public Puddles(int n) {
    this.n = n;
    points = new Point2D[n];
    for (int i = 0; i < n; i++) {
      points[i] = new Point2D(R.nextDouble(), R.nextDouble());
    }
  }

  /**
   * The coordinates of a given puddle
   * 
   * @param i the index (0-based) of the puddle
   * @return the coordinates of that puddle
   */
  public Point2D getPoint(int i) {
    return points[i];
  }

  /**
   * Returns the number of puddles
   * 
   * @return the number of puddles
   */
  public int count() {
    return this.n;
  }

  /**
   * Computes and returns a list of pairs (i, j) of indices to the points
   * array with the following properties:
   * - Every pair of distinct indices (i, j) with i less than j occurs exactly
   * once
   * - The pairs are sorted according to the order of the distances
   * between the corresponding points. That is, if (i, j) precedes (a,b)
   * in the list then the distance between points[i] and points[j] is
   * less than or equal the distance between points[a] and points[b].
   * 
   * @return A list of pairs of point indices sorted by increasing distance.
   */
  public ArrayList<int[]> mergeOrder() {
    ArrayList<int[]> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        result.add(new int[] { i, j, });
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
    return result;
  }

}
