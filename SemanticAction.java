import java.util.Stack;

public class SemanticAction implements ParseAction
{
  String type;
    
  public SemanticAction( String string){
    type = string;
  }
  
  public void execute( Stack stack )
  {
    stack.push(this);
    System.out.println(type);
  }
  
  public String toString()
  {
    return "Test";
  }
}