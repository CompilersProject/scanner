import java.util.Stack;

public class MakeEquals extends SemanticAction
{
  public MakeEquals(){
    branches = new SemanticAction[2];
    type = TYPE.EQUALS;
  }
  
  public MakeEquals( MakeEquals mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Equals" );
    
    branches[1] = (SemanticAction) semanticStack.pop();
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeEquals( this );
  }
}