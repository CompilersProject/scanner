import java.util.Stack;

public class PushNothing implements ParseAction
{
   public PushNothing() {}

   public void execute( Stack stack ) {}

   public String toString()
   {
      return "(empty)";
   }
}