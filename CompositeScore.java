package scoring;

import java.util.List;
import java.util.ArrayList;

/**
 * A class that creates CompositeScore objects.
 * 
 * @author Griffin Moran
 * @version 1
 */
public class CompositeScore extends AbstractScore
{
  private Rule rule;
  private ScoringSystem system;
  private List<Score> components = new ArrayList<Score>();
  
  /**
   * Creates a CompositeScore object that can hold any type of Score.
   * 
   * @param key The key for the CompositeScore
   * @param rule The Rule to use for the CompositeScore
   * @param system The ScoringSystem to use for the CompositeScore
   */
  public CompositeScore(final String key, final Rule rule, final ScoringSystem system) 
      throws IllegalArgumentException
  {
    super(key);
    this.rule = rule;
    this.system = system;
  }
  /**
   * Adds a Score to ths private list of Scores.
   * 
   * @param score The Score to add
   */
  public void add(final Score score) 
  {
    components.add(score);
  }
  
  @Override
  public Double getValue()
  {
    try
    {
      Score calculated;
      if(this.system == null) return null;
      
      if(rule == null) calculated = system.calculate(getKey(), components);
      
      else 
      {
        List<Score> applied = rule.apply(components);
        calculated = system.calculate(getKey(), applied);
      }
      
      return calculated.getValue();
    }
    catch (SizeException e)
    {
      return null;
    }
  }

}
