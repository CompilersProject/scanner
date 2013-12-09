import java.util.Stack;

/**
 * 
 * Makes a node from the semantic stack to 
 * be placed in the AST. Uses the updateAST method
 * to add it to the AST. The copy constructor allows
 * us to carry information for later use.
 *
 */

public class MakeBoolean extends SemanticAction
{
  public MakeBoolean( ){
    type = TYPE.BOOLEAN;
    returnType = "boolean";
    childType = "boolean";
  }
  
  public MakeBoolean( String name ){
    type = TYPE.BOOLEAN;
    this.name = name;
    returnType = "boolean";
    childType = "boolean";
  }
  
  public MakeBoolean( MakeBoolean mi ){
    type = mi.type;
    name = mi.name;
    returnType = mi.returnType;
    childType = mi.childType;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println("Pushing Boolean");
    
    name = (String) nameStack.pop();
    
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name; // NAMING
  }
  
  public SemanticAction copy(){
    return new MakeBoolean( this );
  }
}