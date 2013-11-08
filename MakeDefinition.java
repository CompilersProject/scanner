import java.util.Stack;

public class MakeDefinition extends SemanticAction
{
  public MakeDefinition(){
    type = TYPE.DEFINITION;
  }
  
  public MakeDefinition( MakeDefinition mi ){
    branches = mi.branches; // Need to copy every element?
    
    type = mi.type;
    name = mi.name;
  }
   
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Definition" );
    
    branches = new SemanticAction[semanticStack.size() - TableDrivenParser.defNodes]; // Enough space for arguments and funct name
    name = (String) nameStack.pop();//branches[0] = new MakeIdentifier( (String) nameStack.pop() );
    for( int i = 0; !semanticStack.isEmpty(); i++ ){
      SemanticAction sa = (SemanticAction) semanticStack.pop();
      if( sa instanceof MakeDefinition ){
        // Replace the Def node so we don't nest them but it is still on the stack
        semanticStack.push( sa );
        break;
      }
      
      branches[i] = sa;
    }
    
    TableDrivenParser.defNodes++;
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new MakeDefinition( this );
  }
}