package scoring;

import java.util.List;

/**
 * A class that calculates a total point value for.
 * a list of Score objects
 * 
 * @author Griffin Moran
 * @version 1
 */
public class TotalSystem implements ScoringSystem
{
  /**
   * The default constructor.
   */
  public TotalSystem() {}

  /**
   * Calculates the total for all of the Score objects
   * in the list using the values.
   * 
   * @param key The key to use.
   * @param scores The list of scores
   * @return A new score object with the key being the given parameter
   * and the value being the total
   */
  public Score calculate(final String key,final List<Score> scores) throws SizeException
  {
    Double total = 0.0;
    if(scores == null || scores.size() == 0) throw new SizeException("Invalid size");
    for(Score sc: scores)
    {
      total += Missing.doubleValue(sc.getValue());
    }
    return new LeafScore(key, total);
  }
}
