import java.util.Stack;

/**
 * 
 * The execute method in PushSequence pushes
 * the tokens onto the stack in reverse order.
 *
 */

public class PushSequence implements ParseAction {

  private ParseAction[] actions;

  public PushSequence( ParseAction[] actions )
  {
    this.actions = actions;
  }
  
  public void execute( Stack stack )
  {
    for (int i = actions.length-1; i >= 0; i--){
      if( Compiler.extendedDebug ){

      }
      actions[i].execute( stack );
    }
  }
  
  public String toString()
  {
    String answer = "";
    for (int i = 0; i < actions.length; i++)
      answer += (actions[i].toString() + ", ");
    return answer;
  }
}