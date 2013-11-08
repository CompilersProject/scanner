import java.util.Stack;

public class MakeLessThan extends SemanticAction
{
  public MakeLessThan(){
    branches = new SemanticAction[2];
    type = "Less Than Node";
  }
  
  public MakeLessThan( MakeLessThan mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Less Than" );
    
    branches[1] = (SemanticAction) semanticStack.pop();
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeLessThan( this );
  }
}