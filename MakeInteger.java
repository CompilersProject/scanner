import java.util.Stack;

public class MakeInteger extends SemanticAction
{
  public MakeInteger( ){
    type = TYPE.INTEGER;
  }
  
  public MakeInteger( String name ){
    type = TYPE.INTEGER;
    this.name = name;
  }
  
  public MakeInteger( MakeInteger mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
    if( Compiler.extendedDebug )
      System.out.println("Pushing Integer");
    
    name = (String) nameStack.pop();
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name;
  }
  
  public SemanticAction copy(){
    return new MakeInteger( this );
  }
}