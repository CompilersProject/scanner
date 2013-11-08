import java.util.Stack;

public class MakeProgram extends SemanticAction
{
  public MakeProgram(){
    type = TYPE.PROGRAM;
  }
  
  public MakeProgram( MakeProgram mi ){
    branches = mi.branches;
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Program" );
    
    branches = new SemanticAction[semanticStack.size()];
    for( int i = 0; !semanticStack.isEmpty(); i++ ){
      branches[i] = (SemanticAction) semanticStack.pop();
    }
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeProgram( this );
  }
}