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
    name = "TYPE";
    type = TYPE.TYPE;
  }
  
  public MakeType( String name ){
    type = TYPE.TYPE;
    this.name = name;
  }
  
  public MakeType( MakeType mi ){
    type = mi.type;
    name = mi.name;
    returnType = mi.returnType;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println("Pushing Type");
    
    name = (String) nameStack.pop();
    returnType = name;
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name;
  }
  
  public SemanticAction copy(){
    return new MakeType( this );
  }
  
  /*
  // Overloaded because this type relies on the type of its child but there is no reason to make other functions dig any deeper node-wise
  public String getReturnType()
  {
    return branches.get(0).getReturnType();
  }
  */
}