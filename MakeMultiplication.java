import java.util.Stack;
import java.util.ArrayList;

public class MakeMultiplication extends SemanticAction
{
  public MakeMultiplication(){
    type = TYPE.MULTIPLICATION;
  }
  
  public MakeMultiplication( MakeMultiplication mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Multiplication" );

    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy( ){
    return new MakeMultiplication( this );
  }
}