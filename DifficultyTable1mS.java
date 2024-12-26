package diving;

import java.util.*;

/**
 * An encapsulation of the degrees of difficulty for 1m springboard
 * diving competitions.
 * 
 * @author Annie Pro Grammer, perspecTV
 * @version H1
 */
public class DifficultyTable1mS extends HashMap<String, Double>
{
  private static final long serialVersionUID = 1L;

  /** 
   * Default Constructor.
   */
  public DifficultyTable1mS()
  {
    super();
    
    put("Forward Dive - Straight", 1.4);
    put("Forward Dive - Pike", 1.3);
    put("Forward Dive - Tuck", 1.2);
    put("Forward Somersault - Straight", 1.6);
    put("Forward Somersault - Pike", 1.5);
    put("Forward Somersault - Tuck", 1.4);
    put("Forward 1 1/2 Somersault - Pike", 1.7);
    put("Forward 1 1/2 Somersault - Tuck", 1.6);
    put("Forward Double Somersault - Pike", 2.3);
    put("Forward Double Somersault - Tuck", 2.2);
    put("Forward 2 1/2 Somersault - Pike", 2.6);
    put("Forward 2 1/2 Somersault - Tuck", 2.4);
    put("Forward Triple Somersault - Tuck", 2.9);
    put("Forward 3 1/2 Somersault - Tuck", 3.0);
  }
}
