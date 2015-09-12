import java.util.Stack;

public class PushSequence implements ParseAction {

  private ParseAction[] actions;

  public PushSequence( ParseAction[] actions )
  {
    this.actions = actions;
  }
  
  public void execute( Stack stack )
  {
<<<<<<< HEAD
    for (int i = actions.length-1; i >= 0; i--){
      if( Compiler.extendedDebug ){
        //System.out.println(actions[i]);
      }
      actions[i].execute( stack );
    }
=======
    for (int i = actions.length-1; i >= 0; i--)
      actions[i].execute( stack );
>>>>>>> 7abfbba130cea9ae24b4bec117ce954bc99815ad
  }
  
  public String toString()
  {
    String answer = "";
    for (int i = 0; i < actions.length; i++)
      answer += (actions[i].toString() + ", ");
    return answer;
  }
  
}