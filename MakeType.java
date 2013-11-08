import java.util.Stack;

public class MakeType extends SemanticAction
{
  public MakeType( ){
    type = TYPE.TYPE;
  }
  
  public MakeType( String name ){
    type = TYPE.TYPE;
    this.name = name;
  }
  
  public MakeType( MakeType mi ){
    type = mi.type;
    name = mi.name;
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack ){
    if( Compiler.extendedDebug )
      System.out.println("Pushing Type");
    
    name = (String) nameStack.pop(); // NAMING
    semanticStack.push( this );
  }
  
  public String toString(){
    return type + " " + name; // NAMING
  }
  
  public SemanticAction copy(){
    return new MakeType( this );
  }
}