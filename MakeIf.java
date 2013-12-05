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

public class MakeIf extends SemanticAction
{
  public MakeIf(){
    type = TYPE.IF;
  }
  
  public MakeIf( MakeIf mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing If" );

    addNodes( semanticStack, 3 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeIf( this );
  }
}