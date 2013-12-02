import java.util.Stack;
import java.util.ArrayList;

public class MakeDivision extends SemanticAction
{
  public MakeDivision(){
    type = TYPE.DIVISION;
  }
  
  public MakeDivision( MakeDivision mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
       System.out.println( "Pushing Division" );
    
    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeDivision( this );
  }
}