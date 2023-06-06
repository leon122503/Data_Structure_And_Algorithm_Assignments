package cosc201.a1;

/**
 * Simple convenience class for representing points in two-dimensional space.
 */
public class Point2D {

  private static int PRECISION = 6; // DP shown by toString


  private double x;
  private double y;

  /**
   * Constructs a point with given coordinates
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  /**
   * Computes the distance to another point.
   * @param other the other point
   * @return the distance between the two points
   */
  public double distance(Point2D other) {
    return Math.sqrt(distance2(other));
  }

  /**
   * Computes the square of the distance to another point. This saves a 
   * square root computation when all you want to know is the relative order
   * of distances between a bunch of pairs of points.
   * @param other the other point
   * @return the square of the distance between the two points
   */
  public double distance2(Point2D other) {
    double dx = this.x - other.x;
    double dy = this.y - other.y;
    return dx * dx + dy * dy;
  }

  /**
   * Sets the precision to which points are displayed in toString method.
   * @param dp the number of decimal places to display
   */
  public void setPrecision(int dp) {
    PRECISION = dp;
  }

  public String toString() {
    String f = "%." + PRECISION + "f";
    return "(" + String.format(f, x) + "," + String.format(f, y) + ")";
  }

}
