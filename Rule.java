package scoring;

import java.util.List;

/**
 * A interface that can be used to apply a specified rule.
 * 
 * @author Griffin Moran
 * @version 1
 */
public interface Rule
{
  /**
   * Applies the specified rule.
   * 
   * @throws SizeException
   * @param scores The list of scores to assess
   * @return an updated list after the rule has been applied
   */
  public List<Score> apply(List<Score> scores) throws SizeException;
}
