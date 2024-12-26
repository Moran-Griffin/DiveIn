package scoring;

import java.util.List;
import java.util.Map;

/**
 * A class that calculates a weighted total point value for.
 * a list of Score objects
 * 
 * @author Griffin Moran
 * @version 1
 */
public class WeightedTotalSystem implements ScoringSystem
{
  private Map<String, Double> weights;
  
  /**
   * The default constructor with no weights.
   */
  public WeightedTotalSystem()
  {
    this.weights = null;
  }
  
  /**
   * The constructor with specified weights.
   * @param weights A map containing corresponding weights to use for each key
   */
  public WeightedTotalSystem(final Map<String, Double> weights) 
  {
    this.weights = weights;
  }
  
  /**
   * Calculates the weighted total for all of the Score objects
   * in the list using the values.
   * 
   * @param key The key to use.
   * @param scores The list of scores
   * @return A new score object with the key being the given parameter
   * and the value being the weighted total
   */
  public Score calculate(final String key, final List<Score> scores) throws SizeException
  {
    Double total = 0.0;
    Double weight = 1.0;
    
    if(scores == null || scores.size() == 0) throw new SizeException();
    
    for(Score sc: scores)
    {
      if(sc.getValue() == null || sc.getValue() == 0.0) weight = 0.0;
      else if(weights == null) weight = 1.0;
      else weight = Missing.doubleValue(weights.get(sc.getKey()), 1.0, 1.0);
      
      total += weight * Missing.doubleValue(sc.getValue());
    }
    return new LeafScore(key, total);
  }
}
