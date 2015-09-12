import java.util.Stack;
import java.util.ArrayList;

public class MakeProgram extends SemanticAction
{
  public MakeProgram(){
    type = TYPE.PROGRAM;
    name = "Program";
  }
  
  public MakeProgram( MakeProgram mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Program" );
    
    addNodes( semanticStack, semanticStack.size() );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeProgram( this );
  }
}
