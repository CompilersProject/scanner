import java.util.Stack;

/**
 * 
 * This class is similar to ParseAction but the 
 * toString is overwritten.PushNothing will be used
 * in TableDrivenParser to handle the epsilons in
 * the grammar.
 *
 */

public class PushNothing implements ParseAction
{
   public PushNothing() {}

   public void execute( Stack stack ) {}

   public String toString()
   {
      return "(empty)";
   }
}