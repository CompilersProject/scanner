import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PushbackInputStream;
import java.io.IOException;
import java.util.Scanner;


public class NewScanner  {
	
    public static void main( String args[] ) throws FileNotFoundException
    {
		PushbackInputStream input = new PushbackInputStream(
				new FileInputStream("test.txt"));
		
		
//		  Not sure why this doesn't work, I thought
//		  NewScanner takes in a PushBackInputStream
		  
//		  NewScanner tester = NewScanner(input);
		
		
    }
	





	private PushbackInputStream source;
    private Token               lookahead;
    
    
	
    public NewScanner( PushbackInputStream in )
    {
        source    = in;
        lookahead = null;
    }
    
//		I can read from a text file and get the
// 		first char which is in ASCII, so (char) 
//		turns it into a character.
    
//		PushbackInputStream input = new PushbackInputStream(
//                new FileInputStream("test.txt"));
//		 
//		System.out.println((char)input.read());
		

		protected int getNextByte() throws IOException
	    {
	       int nextChar;
	       
	       while (true)
	       {
	          nextChar = source.read();
	          if ( nextChar == -1 )
	             return -1;
	          if ( !isWhitespace((char) nextChar) )
	             return nextChar;
	       }
	    }

		private boolean isWhitespace(char nextChar) {
			// TODO Auto-generated method stub
			return false;
		}
	    

    }



