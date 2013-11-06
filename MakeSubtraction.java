import java.util.Stack;

public class MakeSubtraction extends SemanticAction
{
  public MakeSubtraction(){
    branches = new SemanticAction[2];
    type = "Subtraction Node";
  }
  
  public MakeSubtraction( MakeSubtraction mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    branches[0] = (SemanticAction) semanticStack.pop();
    branches[1] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Subtraction" );
  }
  
  public SemanticAction copy(){
    return new MakeSubtraction( this );
  }
}