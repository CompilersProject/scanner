import java.util.Stack;
import java.util.ArrayList;

public class MakeEquals extends SemanticAction
{
  public MakeEquals(){
    type = TYPE.EQUALS;
  }
  
  public MakeEquals( MakeEquals mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Equals" );

    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeEquals( this );
  }
}