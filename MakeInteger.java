import java.util.Stack;

public class MakeInteger extends SemanticAction
{
  public MakeInteger( ){
    type = "Integer Node";
  }
  
  public MakeInteger( String name ){
    type = "Integer Node";
    this.name = name;
  }
  
  public MakeInteger( MakeInteger mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println("Pushing Integer");
    
    name = (String) nameStack.pop(); // NAMING
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name; // NAMING
  }
  
  public SemanticAction copy(){
    return new MakeInteger( this );
  }
}