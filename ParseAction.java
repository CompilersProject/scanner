import java.util.Stack;

/**
 * 
 * The ParseAction interface is composed of the
 * execute and toString methods. These will be used
 * to push items on the stack.
 *
 */

public interface ParseAction
{
  public void   execute( Stack stack );
  public String toString();
}