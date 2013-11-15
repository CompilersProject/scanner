import java.util.Stack;

// Very special, hacky class used to count actuals being pushed on the stack
// Highly coupled with MakeFunction so that the right number of nodes are added to its branches
public class CountActuals implements ParseAction
{
  public void execute( Stack stack )
  {
    //TableDrivenParser.currentActuals++;
  }
  
  public String toString()
  {
    return "Count Actual";
  }
}