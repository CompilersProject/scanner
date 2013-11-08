import java.util.Stack;

public class MakeMultiplication extends SemanticAction
{
  public MakeMultiplication(){
    branches = new SemanticAction[2];
    type = "Multiplication Node";
  }
  
  public MakeMultiplication( MakeMultiplication mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Multiplication" );
    
    branches[1] = (SemanticAction) semanticStack.pop();
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy( ){
    return new MakeMultiplication( this );
  }
}