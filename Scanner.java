import java.io.PushbackInputStream;
import java.io.IOException;
import java.io.FileInputStream;


public class Scanner
{
 public static final int MAX_LENGTH = 256;
 
  private PushbackInputStream sourceFile;
  private Token               nextToken; // Added this for peek as Wallingford pointed out we would need it

  public Scanner( String filename ) throws IOException
  {
    sourceFile = new PushbackInputStream(new FileInputStream(filename));

    nextToken = null;
  }
  

  public Token peek() throws IOException, LexicalException
  {
    if (nextToken == null)
      nextToken = getNextToken();
    return nextToken;
  }


  public Token getNextToken() throws IOException, LexicalException
  {
    if( nextToken != null ){
      Token tmp = nextToken;
      nextToken = null;
      return tmp;
    }
    
    String rawToken = "";
    int nextByte = getNextByte();
    int tokenLength = 1;
    
    // Throw away leading whitespace
    while( isOurWhitespace((char) nextByte) ){
      nextByte = getNextByte();
    }
    if( nextByte == -1 || nextByte == 'ÿ'){
      // Doing a check within Token is tricky, because we are passing in a valid integer value for literals
      // Saving off a copy of EOS when it is found initially doesn't work well for peeks
      // Best solution that works so far.
      return new Token(Token.TYPE.EOS);
    }
    if( isComment( (char) nextByte) ){
      char temp='j';
      while (temp!='\n')
      {         
        temp = (char) sourceFile.read();
        if (temp == 65535)
          break;
      }
      return new Token(Token.TYPE.COMMENT);
    }
    
    if( isSymbol( (char) nextByte) ){
      String stringByte =  Character.toString((char)nextByte);
      return makeToken(stringByte);
    }

    while( !isSymbol( (char) nextByte) && 
      !isOurWhitespace( (char) nextByte) ) {
      if( tokenLength > MAX_LENGTH ){
        throw new LexicalException("Identifier is too long. Max identifier length: " + MAX_LENGTH);
      }
      
      if( nextByte != -1 || nextByte == 'ÿ'){ // EOF character
        rawToken += (char) nextByte;
        tokenLength++;
        nextByte = getNextByte();
      }
      else{
        // Found the End of FIle, return what we were reading and save an EOS Token
        //nextToken = new Token(Token.TYPE.EOS);
        return makeToken(rawToken);
      }
    }

    // If we reach here we found a symbol signifying a new token, so we must first replace it in the stream
    sourceFile.unread( nextByte );
    return makeToken(rawToken);
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
      c == ':' ||
      c == 'ÿ';
    // Note: Comment tag is two characters and is perceived as a keyword
  }
  public boolean isComment (char c) throws IOException
  {
    char temp = (char)sourceFile.read();
    if ((c=='/') && temp=='/')
    {
      sourceFile.unread(temp);
      return true;
    }
    sourceFile.unread(temp);
    return false;
  }
  
  private Token makeToken( String rawToken ) throws LexicalException
  {
    nextToken = new Token( rawToken );
    return nextToken;
  }
}
