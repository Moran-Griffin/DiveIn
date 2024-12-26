package app;

import diving.*;
import java.io.*;
import scoring.*;

/**
 * An application that can be used to find the combined score for a
 * team of divers. Note that, unlike the PeopleScorer application (which
 * just reports individual scores of each participant), this application
 * also reports a combined score for the team.
 * 
 * args[0] must contain the name of the team.
 * args[1] .. args[n-1] must contain the .div files for the competitors
 *  
 * @author Griffin Moran
 * @version H5
 */
public class TeamScorer
{
  /**
   * The entry point of the application.
   * 
   * @param args The command line arguments
   * @throws FileNotFoundException If a file can't be found
   * @throws IOException If there is a problem reading a file
   * @throws SizeException If the ScoringSystem detects a problem
   */
  public static void main(final String[] args) 
      throws FileNotFoundException, IOException, SizeException
  {
    // Early exit
    if ((args == null) || (args.length < 2))
    {
      System.err.println("You must provide a team name and at least one .div file.");
      System.exit(1);
    }

    // Construct the CompositeScore for the team
    CompositeScore event = new CompositeScore(args[0], null, new TotalSystem());
    for (int i=1; i<args.length; i++)
    {
      BufferedReader in = new BufferedReader(new FileReader(args[i]));
      // Read the CompositeScore for the diver
      CompositeScore diver = DivFileReader.readDiversList(in);
      event.add(diver);
      System.out.println(diver.toString());
    }
    System.out.println("\n");
    System.out.println(event.toString());
  }
}
