import java.util.Stack;

public class SemanticAction implements ParseAction
{
  // Only needed for debugging?
  String type;
  String name;

  public SemanticAction( ){
    type = "Generic Action: no type";
    name = "";
  }
  
  public SemanticAction( String type ){
    this.type = type;
  }
  
  public void execute( Stack stack )
  {
    stack.push(this);
  }
  
  public SemanticAction( SemanticAction mi ){
    type = mi.type;
    name = mi.name;
  }
  
  // Applies a semantic action 
  public void updateAST( Stack semanticStack, String name ){
  }
  
  public SemanticAction copy(){
    return new SemanticAction( this );
  }
  
  public String toString()
  {
    return type;
  }
}