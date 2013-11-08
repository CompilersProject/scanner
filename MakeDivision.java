import java.util.Stack;

public class MakeDivision extends SemanticAction
{
  public MakeDivision(){
    branches = new SemanticAction[2];
    type = "Division Node";
  }
  
  public MakeDivision( MakeDivision mi ){
    super();
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
       System.out.println( "Pushing Division" );
    
    branches[1] = (SemanticAction) semanticStack.pop();
    branches[0] = (SemanticAction) semanticStack.pop();
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeDivision( this );
  }
}