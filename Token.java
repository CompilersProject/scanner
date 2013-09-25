public class Token 
{
  public static final int EOF                 = -1;
  
 //Grammar
    public static final int Program            = 1;
    public static final int Definitions        = 2;
    public static final int Def                = 3;
    public static final int Formals            = 4;
    public static final int NonEmptyFormal     = 5;
    public static final int Formal             = 6;
    public static final int Body               = 7;
    public static final int Type               = 8;
    public static final int Expr        = 9; 
    public static final int SimpleExpr       = 10;
    public static final int Term       = 11;
    public static final int Factor            = 12;
    public static final int Actuals          = 13;
    public static final int NonemptyActuals   = 14;
    public static final int Literal        = 15;
    public static final int Print             = 16;
    
    //Reserved words and symbols
    public static final int PlusOp             = 21;
    public static final int MinusOp            = 22;
    public static final int MultiplyOp         = 23;
    public static final int AssignmentOp       = 24;
    public static final int LessThanOp        = 25;
    public static final int OpenParen        = 26;
    public static final int ClosedParen        = 27;
    public static final int If       = 28;
    public static final int Then      = 29;
    public static final int Else      = 30;
    public static final int EndIf      = 31;
    public static final int Main                     = 32;
    public static final int Not       = 33;
    public static final int Or       = 34;
    public static final int And       = 35;
    public static final int Comment      = 36;
    public static final int ForwardSlash                   = 37;
    public static final int Comma      = 38;
    public static final int Boolean      = 39;
    public static final int True                       = 40;
    public static final int False      = 41;
    public static final int Colon      = 42;
    public static final int Integer                        = 43;
    public static final int PrintWord                      = 44;
    public static final int Identifier   = 45; // ?
   
    private int    type;
    private String cvalue;
    
    public Token(String word)
    {
      type = getTokenNum(word);
      cvalue = word;
    }
    
    public Token(int word)
    {
      cvalue = String.valueOf(word);
      type = getTokenNum(cvalue);
    }
    /*
    public Token(char word)
    {
      cvalue = String.valueOf(word);
      type = 
    }*/
    
    public int    type  () { return type;   }
    public String cvalue() { return cvalue; }

    public boolean isNewline()
    {
      return cvalue == "\n";
    }
    
    public String toString(){ return (String) cvalue; }
    
public static int getTokenNum(String sToken)
 {
  int type = Identifier;
  
  if (isReservedPlusOp(sToken)) {
   type = PlusOp;
  } else if (isReservedMinusOp(sToken)) {
   type = MinusOp;
  } else if(isReservedMultiplyOp(sToken)) {
   type = MultiplyOp;
  } else if(isReservedAssignmentOp(sToken)) {
   type = AssignmentOp;
  } else if (isReservedLessThanOp(sToken)) {
   type = LessThanOp;
  } else if (isReservedOpenParen(sToken)) {
   type = OpenParen;
  } else if (isReservedClosedParen(sToken)) {
   type = ClosedParen;
  } else if (isReservedIf(sToken)) {
   type = If;
  } else if (isReservedThen(sToken)) {
   type = Then;
  } else if (isReservedElse(sToken)) {
   type = Else;
  } else if (isReservedEndIf(sToken)) {
   type = EndIf;
  } else if (isReservedMain(sToken)) {
   type = Main;
  } else if (isReservedNot(sToken)) {
   type = Not;
  } else if (isReservedOr(sToken)) {
   type = Or;
  } else if (isReservedAnd(sToken)) {
   type = And;
  } else if (isReservedComment(sToken)) {
   type = Comment;
  } else if (isReservedForwardSlash(sToken)) {
   type = ForwardSlash;
  } else if (isReservedComma(sToken)) {
   type = Comma;
  } else if (isReservedBoolean(sToken)) {
   type = Boolean;
  } else if (isReservedTrue(sToken)) {
   type = True;
  } else if (isReservedFalse(sToken)) {
   type = False;
  } else if (isReservedColon(sToken)) {
   type = Colon;
  } else if (isInt(sToken)) {
   type = Integer;
  } else if (isReservedPrint(sToken)) {
   type = Print;
  } else if (isEOF(sToken)){
    type = EOF;
  }
  return type;
 }

public static boolean isEOF(String candidate)
{
  int tmp = Integer.parseInt(candidate);
  return tmp == EOF;
}
                              
    public static boolean isReservedAnd(String candidate) {


        int START_STATE    = 0;
        int TERMINAL_STATE = 3; 
        

        char   next;
        
        if (candidate.length()!=3){
         return false;
         
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'a': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'd': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;

     }

     public static boolean isReservedAssignmentOp(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case '=': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedBoolean(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 7; 
        
        char   next;
        
        if (candidate.length()!=7){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'b': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'o': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'o': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'l': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 4:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 5:
                    switch ( next )
                    {
                        case 'a': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 6:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;
                  
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedClosedParen(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case ')': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }
   
   
   public static boolean isReservedColon(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case ':': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedComma(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case ',': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedComment(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 2; 
        
        char   next;
        
        if (candidate.length()!=2){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case '/': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case '/': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedElse(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 4; 
        
        char   next;
        
        if (candidate.length()!=4){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'l': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 's': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedEndIf(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 5; 
        
        char   next;
        
        if (candidate.length()!=5){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'd': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'i': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 4:
                    switch ( next )
                    {
                        case 'f': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedFalse(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 5; 
        
        char   next;
        
        if (candidate.length()!=5){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'f': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'a': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'l': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 's': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 4:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;

            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedForwardSlash(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case '/': state++; break;
                        default : state = -1;
                    }
                    break;  
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedIf(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 2; 
        
        char   next;
        
        if (candidate.length()!=2){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'i': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'f': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }
   
   
   public static boolean isReservedInteger(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 7; 
        
        char   next;
        
        if (candidate.length()!=7){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'i': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 't': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 4:
                    switch ( next )
                    {
                        case 'g': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 5:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 6:
                    switch ( next )
                    {
                        case 'r': state++; break;
                        default : state = -1;
                    }
                    break;
                  
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedLessThanOp(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case '<': state++; break;
                        default : state = -1;
                    }
                    break; 
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedMain(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 4; 
        
        char   next;
        
        if (candidate.length()!=4){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'm': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'a': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'i': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedMinusOp(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case '-': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }
   
   
   public static boolean isReservedMultiplyOp(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case '*': state++; break;
                        default : state = -1;
                    }
                    break; 
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedNot(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 3; 
        
        char   next;
        
        if (candidate.length()!=3){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'o': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 't': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedOpenParen(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case '(': state++; break;
                        default : state = -1;
                    }
                    break; 
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedOr(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 2; 
        
        char   next;
        
        if (candidate.length()!=2){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'o': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'r': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedPlusOp(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 1; 
        
        char   next;
        
        if (candidate.length()!=1){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case '+': state++; break;
                        default : state = -1;
                    }
                    break; 
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedPrint(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 5; 
        
        char   next;
        
        if (candidate.length()!=5){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'p': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'r': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'i': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 4:
                    switch ( next )
                    {
                        case 't': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedThen(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 4; 
        
        char   next;
        
        if (candidate.length()!=4){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 't': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'h': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   
   public static boolean isReservedTrue(String candidate) {
        int START_STATE    = 0;
        int TERMINAL_STATE = 4; 
        
        char   next;
        
        if (candidate.length()!=4){
         return false;
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 't': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'r': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'u': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;
         
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

   public static boolean isInt(String num)
   {
     // TODO use parseInt ******************************************************************
     
     boolean why;
     for(int x = 0; x < num.length(); x = x+1) {
       if (Character.isDigit(num.charAt(x))) {
         why = true;
       } else {
         why = false;
         break;
       }
       if (why) {
         return true;
       }
     }
     return false;
   }
}
