package scoring;

/**
 * A class that allows for use an exception based on sizing issues.
 * 
 * @author Griffin Moran
 * @version 1
 */
public class SizeException extends Exception
{
  private static final long serialVersionUID = 1;
  
  /**
   * The default constructor for the exception.
   */
  public SizeException()
  {
    super();
  }
  
  /**
   * The a regular constructor that allows for the use of a message.
   * 
   * @param message The message to print.
   */
  public SizeException(final String message) 
  {
    super(message);
  }
}
