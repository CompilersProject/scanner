import java.util.Stack;

public class MakeIdentifier extends SemanticAction
{
  public MakeIdentifier( ){
    type = "Identifier Node";
  }
  
  public MakeIdentifier( String name ){
    type = "Identifier Node";
    this.name = name;
  }
  
  public MakeIdentifier( MakeIdentifier mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    name = (String) nameStack.pop(); // NAMING
    semanticStack.push( this );
    if( Compiler.extendedDebug ){
      System.out.println("Pushing Identifier " + this.name);
    }
  }
  
  public String toString(){
    return type + " " + name; // NAMING
  }
  
  public SemanticAction copy(){
    return new MakeIdentifier( this );
  }
}