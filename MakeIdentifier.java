import java.util.Stack;

public class MakeIdentifier extends SemanticAction
{
  public MakeIdentifier( ){
    type = TYPE.IDENTIFIER;
  }
  
  public MakeIdentifier( String name ){
    type = TYPE.IDENTIFIER;
    this.name = name;
  }
  
  public MakeIdentifier( MakeIdentifier mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println("Pushing Identifier");
    
    //TableDrivenParser.currentActuals++;
    TableDrivenParser.actualsCounts.get( TableDrivenParser.functionDepth );
    //tmp++;
    
    name = (String) nameStack.pop();
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name;
  }

  public SemanticAction copy(){
    return new MakeIdentifier( this );
  }
}