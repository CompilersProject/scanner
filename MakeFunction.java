import java.util.Stack;
import java.util.ArrayList;

public class MakeFunction extends SemanticAction
{
  public MakeFunction(){
    type = TYPE.FUNCTION;
  }
  
  public MakeFunction( MakeFunction mi ){
    branches = new ArrayList<SemanticAction>(mi.getBranches());
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Function" );
    
    name = (String) nameStack.pop();
    
    addNodes( semanticStack, TableDrivenParser.currentActuals );
    TableDrivenParser.currentActuals = 0;
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeFunction( this );
  }
}