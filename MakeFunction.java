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

public class MakeFunction extends SemanticAction
{
  public MakeFunction(){
    type = TYPE.FUNCTION;
    name = "Function";
  }
  
  public MakeFunction( MakeFunction mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Function" );
    
    name = (String) nameStack.pop();
    
    addNodes( semanticStack, TableDrivenParser.actualsCounts.get( TableDrivenParser.functionDepth ) );
    TableDrivenParser.actualsCounts.remove( TableDrivenParser.functionDepth );
    TableDrivenParser.functionDepth--;
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeFunction( this );
  }
}