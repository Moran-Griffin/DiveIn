package scoring;

/**
 * A class that creates immutable LeafScore objects.
 * 
 * @author Griffin Moran
 * @version 1
 */
public class LeafScore extends AbstractScore implements Score
{
  private Double value;
  
  /**
   * The LeafScore object without a value, the default is 0.0.
   * 
   * @param key The key to use.
   */
  public LeafScore(final String key) throws IllegalArgumentException
  {
    super(key);
    this.value = 0.0;
  }
  
  /**
   * The LeafScore object with a java.lang.Double value.
   * 
   * @param key  The key to use for the score.
   * @param value The score value to use.
   */ 
  public LeafScore(final String key, final Double value) throws IllegalArgumentException
  {
    super(key);
    this.value = value;
  }
  
  /**
   * The LeafScore object with a double value.
   * 
   * @param key  The key to use for the LeafScore.
   * @param value The LeafScore value to use.
   */
  public LeafScore(final String key, final double value) throws IllegalArgumentException
  {
    super(key);
    this.value = value;
  }

  /**
   * Returns the value for the LeafScore object.
   * @return the value of the current LeafScore object
   */
  public Double getValue()
  {
    return this.value;
  }
  
  /**
   * Factory method for creating LeafScores. 
   * 
   * @param key The key for the LeafScore
   * @param value The LeafScore's value
   * @return a new initialization of a LeafScore
   */
  public static LeafScore parseLeafScore(final String key, final String value) 
      throws IllegalArgumentException
  {
    if(key == null || key.equals("") || value == null) throw new IllegalArgumentException();
    return new LeafScore(key, Double.parseDouble(value));
  }
}
