import java.util.Stack;
import java.util.ArrayList;

public class MakeIf extends SemanticAction
{
  public MakeIf(){
    type = TYPE.IF;
  }
  
  public MakeIf( MakeIf mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing If" );

    addNodes( semanticStack, 3 );
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeIf( this );
  }
}