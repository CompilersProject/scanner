import java.util.Stack;

public class MakeIf extends SemanticAction
{
  public MakeIf(){
    branches = new SemanticAction[3];
    type = "If Node";
  }
  
  public MakeIf( MakeIf mi ){
    branches = new SemanticAction[3];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    branches[0] = (SemanticAction) semanticStack.pop(); // If (boolean exp)
    branches[1] = (SemanticAction) semanticStack.pop(); // Then (exp)
    branches[2] = (SemanticAction) semanticStack.pop(); // Else (exp)
    
    semanticStack.push( this );
    System.out.println( "Pushing If" );
  }
  
  public SemanticAction copy(){
    return new MakeIf( this );
  }
}