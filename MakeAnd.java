import java.util.Stack;

public class MakeAnd extends SemanticAction
{
  public MakeAnd(){
    branches = new SemanticAction[2];
    type = "And Node";
  }
  
  public MakeAnd( MakeAnd mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing And" );
    
    branches[1] = (SemanticAction) semanticStack.pop();
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeAnd( this );
  }
}