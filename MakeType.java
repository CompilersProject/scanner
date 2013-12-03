import java.util.Stack;

/**
 * 
 * Makes a node from the semantic stack to 
 * be placed in the AST. Uses the updateAST method
 * to add it to the AST. The copy constructor allows
 * us to carry information for later use.
 *
 */

public class MakeType extends SemanticAction
{
  public MakeType( ){
    type = TYPE.TYPE;
  }
  
  public MakeType( String name ){
    type = TYPE.TYPE;
    this.name = name;
  }
  
  public MakeType( MakeType mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println("Pushing Type");
    
    name = (String) nameStack.pop();
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name;
  }
  
  public SemanticAction copy(){
    return new MakeType( this );
  }
}