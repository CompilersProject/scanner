import java.util.Stack;
import java.util.ArrayList;

public class SemanticAction implements ParseAction
{
  protected ArrayList<SemanticAction> branches;
  
  protected TYPE type;
  protected String name;
  protected String returnType;
  protected String childType;

  public enum TYPE {
    ADDITION,
      AND,
      BOOLEAN,
      DEFINITION,
      DIVISION,
      EQUALS,
      FORMAL,
      FUNCTION,// NOT DONE
      IDENTIFIER,// NOT DONE?
      IF,// NOT DONE
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
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack ){
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
    
    java.util.Collections.reverse( branches ); // So all branches will be in the expected order (GenerateCode)
  }
  
  public SemanticAction getLastNode() 
  { 
    if( branches.isEmpty() ){ 
      return new SemanticAction(); 
    } else { 
      return branches.get(branches.size() - 1); 
    }
  }
  
  // Checks if we are in a function call, removes a consumed node if so
  protected void removeFuncCallCount( int nodeChildren ){
    if( !TableDrivenParser.actualsCounts.isEmpty() ){
      int tmp = TableDrivenParser.actualsCounts.get( TableDrivenParser.functionDepth );
      tmp = tmp - (nodeChildren - 1);
      TableDrivenParser.actualsCounts.set( TableDrivenParser.functionDepth, tmp );
    }
  }
  
  public ArrayList<SemanticAction> getBranches() {return branches;}
  public String getName() {return name;}
  public TYPE getType() {return type;}
  public String getReturnType() {return returnType;}
  public String getChildType() {return childType;}
  public boolean hasBranches() {return !branches.isEmpty();}
}