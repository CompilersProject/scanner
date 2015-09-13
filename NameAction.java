import java.util.Stack;

/**
 * 
 * Uses the ParseAction interface to push
 * names of an identifier token onto the 
 * stack.
 *
 */

public class NameAction implements ParseAction
{
  public void addName( Stack<String> stack, String name )
  {
    stack.push( name );
  }
  
  public void   execute( Stack stack )
  {
    stack.push( this );
  }
  
  public String toString()
  {
    return "Name Action";
  }
}