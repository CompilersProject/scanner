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
        
        semanticStack.push( sa );
        break;
      }
      
      branches.add( sa );
    }
    
    reverseFormals();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeDefinition( this );
  }
  
  // Puts formals in order of Klein definition. Needed for CodeGenerator
  private void reverseFormals()
  {
    // Skip body and return type nodes
    // This is all Jordan's fault
    ArrayList<SemanticAction> tmp = new ArrayList<SemanticAction>(branches.subList( 0, 2 ));
    ArrayList<SemanticAction> tmp2 = new ArrayList<SemanticAction>(branches.subList( 2, branches.size() ));
    branches.clear();
    
    java.util.Collections.reverse( tmp2 );
    tmp.addAll( tmp2 );
    branches = tmp;
  }
}