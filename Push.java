import java.util.Stack;

public class Push implements ParseAction {
	
	   private Token token;
	   private String terminal;

	   public Push( Token token )
	   {
	      this.token = token;
	   }
	   
	   public Push (String string)
	   {
		   this.string = terminal;
	   }

	   public void execute( Stack stack )
	   {
		   if (string==null)
		   {
			   stack.push( token );
		   } else {
			   stack.push( terminal );
		   }
	      	
	   }

//	   public String toString()
//	   {
//	      return " " +  symbol;
//	   }

}
