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

public class MakeDefinition extends SemanticAction
{
  public MakeDefinition(){
    
    type = TYPE.DEFINITION;
  }
  
  public MakeDefinition( MakeDefinition mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
   
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Definition" );

    name = (String) nameStack.pop();
    while( !semanticStack.isEmpty() ){
      SemanticAction sa = (SemanticAction) semanticStack.pop();
      if( sa instanceof MakeDefinition ){
        // Replace the Def node so we don't nest them but it is still on the stack
        semanticStack.push( sa );
        break;
      }
      
      branches.add( sa );
    }
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeDefinition( this );
  }
}