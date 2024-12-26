package scoring;

/**
 * A class that determines what a value should be if it is not specified correctly.
 * 
 * @author Griffin Moran
 * @version 1
 */
public class Missing
{
  private static final double DEFAULT_MISSING_VALUE = 0.0;
  
  /**
   * Calculates the total for all of the Score objects
   * in the list using the values.
   * 
   * @param number The value to check
   * @return The value to be used
   */
  public static double doubleValue(final Double number) 
  {
    if(number == null) return DEFAULT_MISSING_VALUE;
    return number;
  }
  
  /**
   * Calculates the total for all of the Score objects
   * in the list using the values.
   * 
   * @param number The value to check
   * @param missingValue The updated default value
   * @return The value to be used
   */
  public static double doubleValue(final Double number, final double missingValue) 
  {
    if(number == null) return missingValue;
    return number;
  }
  /**
   * Calculates the total for all of the Score objects
   * in the list using the values.
   * 
   * @param number The value to check
   * @param missingValue Updated default value
   * @param lowerBound The number to check against for a minimum
   * @return The value to be used
   */
  public static double doubleValue(final Double number, 
      final double missingValue, final double lowerBound) 
  {
    if(number == null || number < lowerBound) return missingValue;
    return number;
  }
}
