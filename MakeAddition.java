import java.util.Stack;

public class MakeAddition extends SemanticAction
{
  public MakeAddition(){
    type = "Addition Node";
  }
  
  public MakeAddition( MakeAddition mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, String name ){
    semanticStack.push( this );
    System.out.println( "Pushing Addtion" );
  }
  
  public SemanticAction copy(){
    return new MakeAddition( this );
  }
}