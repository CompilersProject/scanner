import java.util.Stack;

public class MakeDefinition extends SemanticAction
{
  public MakeDefinition(){
    //branches = new SemanticAction[];
    type = "Definition Node";
  }
  
  public MakeDefinition( MakeDefinition mi ){
    branches = mi.branches; // Need to copy every element?
    
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Definition" );
    
    branches = new SemanticAction[semanticStack.size() + 1]; // Enough space for arguments and funct name
    branches[0] = new MakeIdentifier( (String) nameStack.pop() );
    for( int i = 1; !semanticStack.isEmpty(); i++ ){
      branches[i] = (SemanticAction) semanticStack.pop();
    }
    
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeDefinition( this );
  }
}