package diving;

import java.io.BufferedReader;
import java.io.IOException;

import scoring.Score;
import scoring.CompositeScore;
import scoring.DropRule;
import scoring.LeafScore;
import scoring.Missing;
import scoring.Rule;
import scoring.ScoringSystem;
import scoring.TotalSystem;
import scoring.WeightedTotalSystem;


/**
 * This class is used to create DiversList objects from the information read from .div files.
 * 
 * @author Griffin Moran
 * @version 1
 */
public class DivFileReader
{
  /**
   * Returns the next Dive in the .div file.
   * 
   * @param in The BufferedReader to use
   * @param judges The list of judges for the Dive
   * @param key The name of the diver
   * @param rule The specified rule to use
   * @return The next Dive object
   */
  private static final String SPLIT = ("\t");
  
  private static CompositeScore readDive(final BufferedReader in, final String[] judges, 
      final String key, final Rule rule)throws IllegalArgumentException, IOException
  {
    ScoringSystem sys = new TotalSystem();
    
    String sName = in.readLine();
    CompositeScore cs = new CompositeScore(sName, rule, sys);
    String[] jScores = in.readLine().split(SPLIT);
    if(jScores.length != judges.length) 
      throw new IllegalArgumentException("Field size not equal to num of Judges.");
    
    Double valid;
    for(int i = 0; i < jScores.length; i++) 
    {
      if(jScores[i].equals("N/A")) valid = Missing.doubleValue(0.0); //issue with String literal N/A
      else valid = Double.parseDouble(jScores[i]);
      Score s = new LeafScore(sName, valid);
      cs.add(s);
    }
    return cs;
  }
  
  /**
   * Returns a DiversList object containing all of the Dives in a .div file
   * conforming to the given rule specifications.
   * 
   * @param in The BufferedReader to use
   * @return a DiversList containing all of the Dives in a .div file
   */
  public static CompositeScore readDiversList(final BufferedReader in) throws IOException
  {
    DifficultyTable1mS dt = new DifficultyTable1mS();
    WeightedTotalSystem wts = new WeightedTotalSystem(dt);
    
    String dName = in.readLine();
    
    String[] judges = in.readLine().split(SPLIT);
    String[] info = in.readLine().split(SPLIT);
    int numDives = Integer.parseInt(info[0]);
    
    boolean dLow = Boolean.parseBoolean(info[1]);
    boolean dHigh = Boolean.parseBoolean(info[2]);
    DropRule dr = new DropRule(dLow, dHigh);
    
    CompositeScore cs = new CompositeScore(dName, new DropRule(false, false), wts);
    
    for(int i = 0; i < numDives; i++)
    {
      cs.add(readDive(in, judges, dName, dr));
    }
    in.close();
    return cs;
  }

}
