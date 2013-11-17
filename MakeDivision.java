import java.util.Stack;

public class MakeDivision extends SemanticAction
{
  public MakeDivision(){
    type = "Division Node";
  }
  
  public MakeDivision( MakeDivision mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, String name ){
    semanticStack.push( this );
    System.out.println( "Pushing Division" );
  }
  
  public SemanticAction copy(){
    return new MakeDivision( this );
  }
}