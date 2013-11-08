import java.util.Stack;

public class MakeAddition extends SemanticAction
{
  public MakeAddition(){
    branches = new SemanticAction[2];
    type = "Addition Node";
  }
  
  public MakeAddition( MakeAddition mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Addtion" );
    
    // Get L and R Hand Operands
    branches[1] = (SemanticAction) semanticStack.pop();
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeAddition( this );
  }
}