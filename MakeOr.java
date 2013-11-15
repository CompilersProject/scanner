import java.util.Stack;

public class MakeOr extends SemanticAction
{
  public MakeOr(){
    branches = new SemanticAction[2];
    type = TYPE.OR;
  }
  
  public MakeOr( MakeOr mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Or" );
    
    branches[1] = (SemanticAction) semanticStack.pop();
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeOr( this );
  }
}