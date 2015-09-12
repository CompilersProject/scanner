import java.util.Stack;

public class MakeProgram extends SemanticAction
{
  SemanticAction[] functions;
  
  public MakeProgram( ){
    type = "Program Node";
  }
  
  public MakeProgram( MakeProgram mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, String name ){
    semanticStack.push( this );
    System.out.println("Pushing Program");
  }
  
  public SemanticAction copy(){
    return new MakeProgram( this );
  }
}
