import java.util.Stack;

public class MakePrint extends SemanticAction
{
  public MakePrint(){
    branches = new SemanticAction[2];
    type = TYPE.PRINT;
  }
  
  public MakePrint( MakePrint mi ){
    branches = new SemanticAction[2];
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Print" );
    
    branches[1] = (SemanticAction) semanticStack.pop(); // Expression to print
    branches[0] = (SemanticAction) semanticStack.pop(); // Body
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakePrint( this );
  }
}