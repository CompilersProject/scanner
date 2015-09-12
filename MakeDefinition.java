import java.util.Stack;
import java.util.ArrayList;

public class MakeDefinition extends SemanticAction
{
  public MakeDefinition(){
    type = TYPE.DEFINITION;
  }
  
  public MakeDefinition( MakeDefinition mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
   
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Definition" );

    name = (String) nameStack.pop();
    while( !semanticStack.isEmpty() ){
      SemanticAction sa = (SemanticAction) semanticStack.pop();
      if( sa instanceof MakeDefinition ){
        // Replace the Def node so we don't nest them but it is still on the stack
        semanticStack.push( sa );
        break;
      }
      
      branches.add( sa );
    }
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeDefinition( this );
  }
}