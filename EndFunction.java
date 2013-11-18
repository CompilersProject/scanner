import java.util.Stack;

public class EndFunction extends SemanticAction
{
  public EndFunction(){
    name = "End Function Call";
    type = TYPE.ERROR; // This should only be used for counting actuals
  }
  
  public void execute( Stack stack )
  {
    System.out.println( "Pushing End Function" );
    stack.push( this );
  }
}