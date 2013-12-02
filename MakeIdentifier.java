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
    
    try{
      int tmp = TableDrivenParser.actualsCounts.get( TableDrivenParser.functionDepth );
      tmp++;
      TableDrivenParser.actualsCounts.set( TableDrivenParser.functionDepth, tmp++ );
    }
    catch(Exception e){
      // Do nothing if we are not in a function call
    }
    
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