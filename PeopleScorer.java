package app;

import diving.*;
import java.io.*;
import scoring.*;

/**
 * An application that can be used to read score information 
 * for a particular competition/event from one or more .div files,
 * each of which contains Dive information for one competitor.
 * 
 * @author Griffin Moran
 * @version H5
 */
public class PeopleScorer
{
  /**
   * The entry point of the application.
   * 
   * @param args The command line arguments (i.e., the names of the .div files)
   * @throws FileNotFoundException If a file can't be found
   * @throws IOException If there is a problem reading a file
   * @throws SizeException If the ScoringSystem detects a problem
   */
  public static void main(final String[] args) 
      throws FileNotFoundException, IOException, SizeException
  {
    for (int i=0; i<args.length; i++)
    {
      BufferedReader in = new BufferedReader(new FileReader(args[i]));
      CompositeScore diver = DivFileReader.readDiversList(in);

      System.out.println(diver.toString());
      System.out.println("\n");
    }
  }
}
