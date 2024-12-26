package scoring;

/**
 * This class is used to Abstract realizations of the Score interface.
 * 
 * @author Griffin Moran
 * @version 1
 */
public abstract class AbstractScore implements Score
{
  private String key;
  
  /**
   * Allows for constructor features to be inherited.
   * 
   * @param key The key to use
   */
  public AbstractScore(final String key) throws IllegalArgumentException
  {
    if( key == null || key.equals("")) throw new IllegalArgumentException();
    this.key = key;
  }

  @Override
  public int compareTo(final Score other)
  {
    int comparison;
    if(this.getValue() == null) 
    {
      if(other.getValue() == null) comparison = 0;
      else comparison = -1;
    }
    else if(other.getValue() == null) comparison = 1;
    else comparison = sign(this.getValue().compareTo(other.getValue()));
    return comparison;
  }
  
  /**
   * Helper method for compareTo that allows
   * the use of compareTo() in the Double class.
   * 
   * @param n the integer to test
   * @return the sign to use for the comparison
   */
  private int sign(final int n) 
  {
    if(n < 0) return -1;
    else if (n > 0) return 1;
    return 0;
  }

  @Override
  public String getKey()
  {
    return this.key;
  }

  @Override
  public abstract Double getValue();
  
  /**
   * Returns a terse string for the Score object.
   * @return the terse representation of the current Score object

   */
  @Override
  public String toString()
  {
    return toString(false);
  }
  
  /**
   * Create a String representation of this Score.
   *
   * @param verbose   true to return a verbose representation; false for terse
   * @return  The String representation
   */
  public String toString(final boolean verbose)
  {
    String result;
    Double value = this.getValue();
    final String NA = "N/A";

    if (verbose)
    {
      if (value == null) result = String.format("%s: %5s",   key, NA); // Spec 5.1.2
      else               result = String.format("%s: %5.1f", key, value); // Spec 5.1.1
    }
    else
    {
      if (value == null) result = String.format("%5s",   NA); // Spec 5.2.2
      else               result = String.format("%5.1f", value); // Spec 5.2.1
    }

    return result;
  }

}
