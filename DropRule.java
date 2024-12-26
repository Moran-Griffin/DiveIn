package scoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that removes the smallest/largest score/s if determined.
 * 
 * @author Griffin Moran
 * @version 1
 */
public class DropRule implements Rule
{
  private boolean shouldDropLowest;
  private boolean shouldDropHighest;
  
  /**
   * The default constructor that drops the lowest and highest scores.
   */
  public DropRule()
  {
    this.shouldDropLowest = true;
    this.shouldDropHighest = true;
  }
  
  /**
   * The default constructor that drops the lowest and highest scores.
   * @param shouldDropLowest whether to drop the lowest score.
   * @param shouldDropHighest whether to drop the highest score.
   */
  public DropRule(final boolean shouldDropLowest, final boolean shouldDropHighest) 
  {
    this.shouldDropLowest = shouldDropLowest;
    this.shouldDropHighest = shouldDropHighest;
  }

  /**
   * Executes the score dropping as specified in the creation of the object.
   * @param scores The list of scores to assess.
   * @return an updated list with the specified scores dropped.
   */
  public List<Score> apply(final List<Score> scores) throws SizeException
  {
    if(!this.shouldDropLowest && !this.shouldDropHighest) return scores;//maybe remoce
    List<Score> applied = new ArrayList<Score>();
    if(scores == null || scores.size() <= 2) throw new SizeException();
    for(Score sc: scores)
    {
      applied.add(sc);
    }
    Collections.sort(applied);
    
    if(this.shouldDropLowest) applied.remove(applied.get(0));
    if(this.shouldDropHighest) applied.remove(applied.get(applied.size()-1));
    return applied;
  }
  
  
}
