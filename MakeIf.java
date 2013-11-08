import java.util.Stack;

public class MakeIf extends SemanticAction
{
  public MakeIf(){
    branches = new SemanticAction[3];
    type = TYPE.IF;
  }
  
  public MakeIf( MakeIf mi ){
    branches = new SemanticAction[3];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    System.out.println( "Pushing If" );
    
    branches[0] = (SemanticAction) semanticStack.pop(); // If (boolean exp)
    branches[1] = (SemanticAction) semanticStack.pop(); // Then (exp)
    branches[2] = (SemanticAction) semanticStack.pop(); // Else (exp)
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeIf( this );
  }
}