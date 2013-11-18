import java.util.Stack;

public class MakeMultiplication extends SemanticAction
{
  public MakeMultiplication(){
    type = "Multiplication Node";
  }
  
  public MakeMultiplication( MakeMultiplication mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, String name ){
    semanticStack.push( this );
    System.out.println( "Pushing Multiplication" );
  }
  
  public SemanticAction copy( ){
    return new MakeMultiplication( this );
  }
}