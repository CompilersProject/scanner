import java.util.Stack;

public class SemanticAction implements ParseAction
{
  SemanticAction[] branches;
  
  // Only needed for debugging?
  String type;
  String name;

  public SemanticAction( ){
    branches = new SemanticAction[0];
    
    type = "Generic Action: no type";
    name = "";
  }
  
  public SemanticAction( String type ){
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
    return type;
  }
  
  public SemanticAction[] getBranches() {return branches;}
}