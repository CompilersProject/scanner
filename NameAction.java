import java.util.Stack;

public class NameAction implements ParseAction
{
  public void addName( Stack stack, String name )
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