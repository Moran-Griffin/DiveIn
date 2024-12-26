package scoring;

import java.util.List;

/**
 * A interface that allows for implementation of a calculation method.
 * 
 * @author Griffin Moran
 * @version 1
 */
public interface ScoringSystem
{
  /**
   * Applies the specified calculation.
   * 
   * @throws SizeException
   * @param key the key to use
   * @param scores the list of scores to work through
   * @return a new score object after calculations are completed
   */
  public Score calculate(String key, List<Score> scores) throws SizeException;
}
