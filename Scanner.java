import java.io.PushbackInputStream;
import java.io.IOException;
import java.io.FileInputStream;


public class Scanner
{
	public static final int MAX_LENGTH = 256;
	
  private PushbackInputStream sourceFile;
  private Token               nextToken; // Added this for peek as Wallingford pointed out we would need it

  public Scanner( String filename )
  {
	try{
		sourceFile = new PushbackInputStream(new FileInputStream(filename));
	} catch(IOException e){
		System.out.println("Invalid filename.");
		System.exit(0);
	}

    nextToken = null;
  }
  

  public Token peek() throws IOException
  {
    if (nextToken == null)
      nextToken = getNextToken();
    return nextToken;
  }


  public Token getNextToken() throws IOException
  {
    String rawToken = "";
    int nextByte = getNextByte();
    int tokenLength = 1;
    
    // Throw away leading whitespace
    while( isOurWhitespace((char) nextByte) ){
    	nextByte = getNextByte();
    }

    if( isSymbol( (char) nextByte) ){
      String stringByte =  Character.toString((char)nextByte);
      return new Token(stringByte);
    }

    while( !isSymbol( (char) nextByte) && 
    		!isOurWhitespace( (char) nextByte) ) {
      if( tokenLength > MAX_LENGTH ){
    	  // TODO: Put exception here
    	  System.out.println("Identifier is too long. Max identifier length: " + MAX_LENGTH);
    	  System.exit(0);
      }
    	  
    	  
      if( nextByte != -1 ){ // EOF character
        rawToken += (char) nextByte;
        tokenLength++;
        nextByte = getNextByte();
      }
      else{
        return new Token(rawToken);
      }      
    }

    // If we reach here we found a symbol signifying a new token, so we must first replace it in the stream
    sourceFile.unread( nextByte );
    return new Token(rawToken);
  }
  

  public int getNextByte() throws IOException
  {
	  return sourceFile.read();
  }
  

  
  // Static member funtions
  public static boolean isOurWhitespace( char c )
  {
    return c == ' '  ||
      c == '\b' ||
      c == '\f' ||
      c == '\r' ||
      c == '\n' ||
      c == '\t';
  }
  
  public static boolean isSymbol( char c )
  {
    return c == '+' ||
      c == '-' ||
      c == '*' ||
      c == '/' ||
      c == '<' ||
      c == '=' ||
      c == '(' ||
      c == ')' ||
      c == ',' ||
      c == ':';
    // Note: Comment tag is two characters and is perceived as a keyword
  }
}