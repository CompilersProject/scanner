import java.util.Stack;

public class SemanticAction implements ParseAction
{
  protected SemanticAction[] branches;
  
  // Only needed for debugging?
  protected TYPE type;
  protected String name;

  public enum TYPE {
    ADDITION,
      AND,
      BOOLEAN,
      DEFINITION,
      DIVISION,
      EQUALS,
      FORMAL,
      FUNCTION,
      IDENTIFIER,
      IF,
      INTEGER,
      LESSTHAN,
      MULTIPLICATION,
      OR,
      PRINT,
      PROGRAM,
      SUBTRACTION,
      TYPE,
      ERROR
  }
  
  public SemanticAction( ){
    branches = new SemanticAction[0];
    
    type = TYPE.ERROR;
    name = "";
  }
  
  public SemanticAction( TYPE type ){
    this.type = type;
  }
  
  public void execute( Stack stack )
  {
    stack.push(this);
    //System.out.println( "Pushed " + type + " onto parse stack");
  }
  
  public SemanticAction( SemanticAction mi ){
    type = mi.type;
    name = mi.name;
  }
  
  // Applies a semantic action 
  public void updateAST( Stack semanticStack, Stack nameStack ){
  }
  
  public SemanticAction copy(){
    return new SemanticAction( this );
  }
  
  public String toString()
  {
    return name;
  }
  
  public SemanticAction[] getBranches() {return branches;}
}