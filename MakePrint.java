import java.util.Stack;

public class MakePrint extends SemanticAction
{
  public MakePrint(){
    branches = new SemanticAction[2];
    type = "Print Node";
  }
  
  public MakePrint( MakePrint mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    branches[0] = (SemanticAction) semanticStack.pop(); // Expression to print
    branches[1] = (SemanticAction) semanticStack.pop(); // Body
    
    semanticStack.push( this );
    System.out.println( "Pushing Print" );
  }
  
  public SemanticAction copy(){
    return new MakePrint( this );
  }
}