import java.io.PushbackInputStream;
import java.io.IOException;
import java.io.FileInputStream;

public class Scanner
{
  private PushbackInputStream sourceFile;
  private Token               nextToken; // Added this for peek as Wallingford pointed out we would need it

  public Scanner( PushbackInputStream input )
  {
    sourceFile = input;
    nextToken = null;
  }
  

  public Token peek() throws IOException
  {
    if (nextToken == null)
      nextToken = getNextToken();
    return nextToken;
  }

  // Was having problems getting the Token class to work here so for now it just keeps everything as a string.
  // Ideally we will return a Token from here which will be identified in its constructor.
  public Token getNextToken() throws IOException
  {
    String rawToken = "";
    boolean check;
    int nextByte = getNextByte();

    if( isSymbol( (char) nextByte) ){
      return new Token(nextByte);
    }

    while( !isSymbol((char) nextByte) ){
      if( nextByte != -1 ){ // EOF character
        rawToken += (char) nextByte;
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
  
  // This is reading bytes... just not the right ones? 
  public int getNextByte() throws IOException
  {
    int nextChar;
    
    while(true)
    {
      nextChar = sourceFile.read();
      if( nextChar == -1 )
        return -1;
      if( !isWhitespace((char) nextChar) )
        return nextChar;
    }
  }
  
  // Static member funtions
  public static boolean isWhitespace( char c )
  {
    return c == ' '  ||
      c == '\b' ||
      c == '\f' ||
      c == '\r' ||
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
      c == ':' ||
      c == '\n';
    // Note: Comment tag is two characters and is perceived as a keyword
  }
}