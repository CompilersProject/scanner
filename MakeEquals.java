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

public class MakeEquals extends SemanticAction
{
  public MakeEquals(){
    type = TYPE.EQUALS;
    returnType = "integer";
    childType = "integer";
    name = "equals";
  }
  
  public MakeEquals( MakeEquals mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
    returnType = mi.returnType;
    childType = mi.childType;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Equals" );

    removeFuncCallCount( 2 );
    
    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeEquals( this );
  }
}