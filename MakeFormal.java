import java.util.Stack;

public class MakeFormal extends SemanticAction
{
  public MakeFormal( ){
    branches = new SemanticAction[1];
    
    type = "Formal Node";
  }
  
  public MakeFormal( String name ){
    branches = new SemanticAction[1];
    
    type = "Formal Node";
    this.name = name;
  }
  
  public MakeFormal( MakeFormal mi ){
    branches = mi.branches;
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug ){
      System.out.println("Pushing Formal " + this.name);
    }
    
    name = (String) nameStack.pop(); // NAMING
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name;
  }
  
  public SemanticAction copy(){
    return new MakeFormal( this );
  }
}