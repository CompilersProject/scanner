import java.util.Stack;
import java.util.ArrayList;

public class MakeAddition extends SemanticAction
{
  public MakeAddition(){
    type = TYPE.ADDITION;
  }
  
  public MakeAddition( MakeAddition mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Addtion" );
    
    // Get L and R Hand Operands
    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeAddition( this );
  }
}