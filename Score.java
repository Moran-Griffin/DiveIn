package scoring;

/**
 * An interface that can be used to create a specific type of Score.
 * 
 * @author Griffin Moran
 * @version 1
 */
public interface Score extends Comparable<Score>
{
  /**
   * Determines whether one score has a higher or lower value than another.
   * 
   * @param other The score to compare.
   * @return the result of the comparison
   */
  @Override
  public int compareTo(Score other);
  
  /**
   * Returns the key for the Score implementation.
   * @return the key of the current Score implementation
   */
  public String getKey();
  
  /**
   * Returns the value for the Score implementation.
   * @return the value of the current Score implementation
   */
  public Double getValue();
}
