import java.util.Stack;

public class MakeFormal extends SemanticAction
{
  public MakeFormal( ){
    type = TYPE.FORMAL;
  }
  
  public MakeFormal( String name ){
    type = TYPE.FORMAL;
    this.name = name;
  }
  
  public MakeFormal( MakeFormal mi ){
    branches = mi.branches;
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug ){
      System.out.println("Pushing Formal " + this.name);
    }
    
    name = (String) nameStack.pop();
    addNodes( semanticStack, 1 );
    
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name;
  }
  
  public SemanticAction copy(){
    return new MakeFormal( this );
  }
}