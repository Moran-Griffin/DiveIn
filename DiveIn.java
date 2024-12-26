package app;

import diving.*;
import diving.gui.DiveInWindow;

import java.lang.reflect.*;
import javax.swing.*;

/**
 * The main class for the DiveIn application.
 * 
 * @author  Annie Pro Grammer, perspecTV
 * @version 1.0
 */
public class DiveIn implements Runnable
{
  /**
   * The entry point of the application.
   * 
   * @param args  The command line arguments (which are ignored)
   */
  public static void main(final String[] args)
  {
    try
    {
      SwingUtilities.invokeAndWait(new DiveIn());
    }
    catch (InterruptedException | InvocationTargetException  e)
    {
      System.out.println("Unable to start the GUI.");
    }
  }

  /**
   * The code to execute in the event dispatch thread.
   */
  public void run()
  {
    String[] judges = {"ALB", "BEL", "CAN", "DJI", "ESA"};
    DifficultyTable1mS dod = new DifficultyTable1mS();
    DiveInWindow window = new DiveInWindow(judges, dod);
    window.setVisible(true);
  }
}
