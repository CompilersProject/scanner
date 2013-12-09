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

public class MakeOr extends SemanticAction
{
  public MakeOr(){
    type = TYPE.OR;
    returnType = "boolean";
    childType = "boolean";
  }
  
  public MakeOr( MakeOr mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
    returnType = mi.returnType;
    childType = mi.childType;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Or" );

    addNodes( semanticStack, 2 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeOr( this );
  }
}