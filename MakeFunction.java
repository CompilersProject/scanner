import java.util.Stack;

public class MakeFunction extends SemanticAction
{
  public MakeFunction(){
    type = TYPE.FUNCTION;
  }
  
  public MakeFunction( MakeFunction mi ){
    branches = mi.branches; // Need to copy every element?
    
    type = mi.type;
    name = mi.name;
  }
   
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Function" );
    
    branches = new SemanticAction[semanticStack.size() - TableDrivenParser.defNodes]; // TODO: find a way to not allocate too many spaces
    for( int i = 0; !semanticStack.isEmpty(); i++ ){
      SemanticAction sa = (SemanticAction) semanticStack.pop();
      if( sa instanceof MakeType || sa instanceof MakeFormal){
        // Replace the Def node so we don't nest them but it is still on the stack
        semanticStack.push( sa );
        break;
      }
      
      branches[i] = sa;
    }
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeFunction( this );
  }
}