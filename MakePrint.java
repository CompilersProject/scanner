import java.util.Stack;
import java.util.ArrayList;

/**
 * 
 * Makes a node from the semantic stack to 
 * be placed in the AST. Uses the updateAST method
 * to add it to the AST. The copy constructor allows
 * us to carry information for later use.
 *
 */

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

    removeFuncCallCount( 2 );
    
    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakePrint( this );
  }
  
  
  public String getReturnType(){
    return branches.get(1).getReturnType();
  }
}