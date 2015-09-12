import java.util.Stack;

public class SemanticAction implements ParseAction
{
  protected ArrayList<SemanticAction> branches;
  
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
      TYPE, // TODO: CHANGE THIS NAME
      ERROR
  }
  
  public SemanticAction( ){
    branches = new ArrayList<SemanticAction>();
    
    type = TYPE.ERROR;
  }
  
  public SemanticAction( TYPE type ){
    this.type = type;
  }
  
  public void execute( Stack stack )
  {
    stack.push(this);
  }

  public SemanticAction( SemanticAction mi ){
    type = mi.type;
    name = mi.name;
  }
  
  // Applies a semantic action 
  public void updateAST( Stack semanticStack, Stack nameStack ){
    semanticStack.push( this );
  }
  
  public SemanticAction copy(){
    return new SemanticAction( this );
  }
  
  public String toString()
  {
    return name;
  }
  
  public boolean equals( SemanticAction rh )
  {
    return this.name.equals( rh.name );
  }
  
  protected void addNodes( Stack stack, int n )
  {
    while( n > 0 ){
      branches.add( (SemanticAction) stack.pop() );
      n--;
    }
  }
  
  public ArrayList<SemanticAction> getBranches() {return branches;}
  public String getName() {return name;}
  public TYPE getType() {return type;}
}