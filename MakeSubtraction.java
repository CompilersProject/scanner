import java.util.Stack;

public class MakeSubtraction extends SemanticAction
{
  public MakeSubtraction(){
    branches = new SemanticAction[2];
    type = TYPE.SUBTRACTION;
  }
  
  public MakeSubtraction( MakeSubtraction mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Subtraction" );
    
    branches[1] = (SemanticAction) semanticStack.pop();
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeSubtraction( this );
  }
}