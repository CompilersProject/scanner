/**
 * 
 * This exception is thrown in the table driven parser
 * when a semantic error is found.
 *
 */

public class SemanticException extends Exception 
{
  public SemanticException( String e )
  {
    super( e );
  }
}