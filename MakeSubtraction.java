import java.util.Stack;
import java.util.ArrayList;

public class MakeSubtraction extends SemanticAction
{
  public MakeSubtraction(){
    type = TYPE.SUBTRACTION;
  }
  
  public MakeSubtraction( MakeSubtraction mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Subtraction" );

    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeSubtraction( this );
  }
}