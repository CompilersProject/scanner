import java.util.Stack;
import java.util.ArrayList;

public class MakeOr extends SemanticAction
{
  public MakeOr(){
    type = TYPE.OR;
  }
  
  public MakeOr( MakeOr mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Or" );

    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeOr( this );
  }
}