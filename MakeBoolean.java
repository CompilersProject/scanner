import java.util.Stack;

public class MakeBoolean extends SemanticAction
{
  public MakeBoolean( ){
    type = "Boolean Node";
  }
  
  public MakeBoolean( String name ){
    type = "Boolean Node";
    this.name = name;
  }
  
  public MakeBoolean( MakeBoolean mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
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