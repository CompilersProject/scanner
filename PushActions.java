import java.util.Stack;

/**
 * 
 * Creates an array of ParseActions then uses the
 * execute function to push the contents of the array
 * into the stack.
 *
 */

public class PushActions implements ParseAction
{
  private ParseAction[] actions;

  public PushActions( ParseAction[] actions )
  {
    this.actions = actions;
  }

  public void execute( Stack stack )
  {
    for (int i = actions.length-1; i >= 0; i--)
      actions[i].execute( stack );
  }

  public String toString()
  {
    String answer = "";
    for (int i = 0; i < actions.length; i++)
      answer += (actions[i].toString() + ", ");
    return answer;
  }
}