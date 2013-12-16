import java.util.Stack;

public class StartActualsCounter extends SemanticAction
{
  public void execute( Stack stack )
  {
    
    stack.push( this );
  }
  
  public void updateAST( Stack semanticStack, Stack nameStack )
  {
    TableDrivenParser.actualsCounts.add(0);
    TableDrivenParser.functionDepth++;
  }
  
  public String toString()
  {
    return "Actuals Counter";
  }
}