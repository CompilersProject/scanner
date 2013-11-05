import java.util.Stack;

public class MakePrint extends SemanticAction
{
  public MakePrint(){
    type = "Subtraction Node";
  }
  
  public MakePrint( MakePrint mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, String name ){
    // Grab variable nodes?
    semanticStack.push( this );
    System.out.println( "Pushing print" );
  }
  
  public SemanticAction copy(){
    return new MakePrint( this );
  }
}