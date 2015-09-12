import java.util.Stack;

public class SemanticAction implements ParseAction
{
<<<<<<< HEAD
  // Only needed for debugging?
  String type;
  String name;

  public SemanticAction( ){
    type = "Generic Action: no type";
    name = "";
  }
  
  public SemanticAction( String type ){
    this.type = type;
=======
  String type;
    
  public SemanticAction( String string){
    type = string;
>>>>>>> 7abfbba130cea9ae24b4bec117ce954bc99815ad
  }
  
  public void execute( Stack stack )
  {
    stack.push(this);
<<<<<<< HEAD
    //System.out.println( "Pushed " + type + " onto parse stack");
  }
  
  public SemanticAction( SemanticAction mi ){
    type = mi.type;
    name = mi.name;
  }
  
  // Applies a semantic action 
  public void updateAST( Stack semanticStack, String name ){
  }
  
  public SemanticAction copy(){
    return new SemanticAction( this );
=======
    System.out.println(type);
>>>>>>> 7abfbba130cea9ae24b4bec117ce954bc99815ad
  }
  
  public String toString()
  {
<<<<<<< HEAD
    return type;
=======
    return "Test";
>>>>>>> 7abfbba130cea9ae24b4bec117ce954bc99815ad
  }
}