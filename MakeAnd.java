import java.util.Stack;
import java.util.ArrayList;

public class MakeAnd extends SemanticAction
{
  public MakeAnd(){
    type = TYPE.AND;
  }
  
  public MakeAnd( MakeAnd mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing And" );
    
    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeAnd( this );
  }
}