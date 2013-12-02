import java.util.Stack;

public class Push implements ParseAction {
 
  private Token token;
  private String string;
  
  public Push( Token token )
  {
    this.token = token;
  }
  
  public Push (String string)
  {
    this.string = string;
  }

  public void execute( Stack stack )
  {
    if (string==null)
    {
      stack.push( token );
    } else {
      stack.push( string );
    }
  }
  
  public String toString()
  {
    if( string != "" ){
      return " " + string;
    }
    else{
      return " " + token;
    }
  }
}
