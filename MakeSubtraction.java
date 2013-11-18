import java.util.Stack;

public class MakeSubtraction extends SemanticAction
{
  public MakeSubtraction(){
    type = "Subtraction Node";
  }
  
  public MakeSubtraction( MakeSubtraction mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, String name ){
    semanticStack.push( this );
    System.out.println( "Pushing subtraction" );
  }
  
  public SemanticAction copy(){
    return new MakeSubtraction( this );
  }
}