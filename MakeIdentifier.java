import java.util.Stack;

public class MakeIdentifier extends SemanticAction
{
  //String name; // NAMING
  public MakeIdentifier( ){
    type = "Identifier Node";
  }
  
  public MakeIdentifier( MakeIdentifier mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, String name ){
    this.name = name; // NAMING
    semanticStack.push( this );
    System.out.println("Pushing Identifier " + this.name);
  }
  
  public String toString(){
    return type + " " + name; // NAMING
  }
  
  public SemanticAction copy(){
    return new MakeIdentifier( this );
  }
}