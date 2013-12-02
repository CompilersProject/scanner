import java.util.Stack;
import java.util.ArrayList;

public class MakePrint extends SemanticAction
{
  public MakePrint(){
    type = TYPE.PRINT;
    name = "print";
  }
  
  public MakePrint( MakePrint mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Print" );

    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakePrint( this );
  }
}