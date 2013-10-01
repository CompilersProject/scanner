import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;


public class Compiler {
	  public static void main( String[] args ) throws java.io.FileNotFoundException, IOException
	  {
		  
	    try{

	    Scanner test = new Scanner( "max.txt" );
	    Token tmp = test.getNextToken();
	   
	    System.out.println("<program>");
	    while( tmp.getValue() != "" ){
	      
	      if((tmp.getType().equals("identifier")) || (tmp.getType().equals("integer")))
	        System.out.println("<"+tmp.getType()+">" + " " +tmp);
	      
	      else{System.out.println("<"+tmp.getType()+">");
	      }
	      
	      tmp = test.getNextToken();
	    }
	    
	    }
	    catch(Exception e){
	      System.out.println( e );
	    }
	}
}
