public class Token 
{     
    private String type;
    private String cvalue;
    
    public Token(String word) throws LexicalException
    {
      type = assignType(word);
      cvalue = word;
    }
    
    public Token(int word) throws LexicalException
    {
      type = assignType(cvalue);
      cvalue = String.valueOf(word);
    }
    
    public static String assignType(String sToken) throws LexicalException
    {
      String type = "";
      
      if (isReservedPlusOp(sToken)) {
        type = "plusOp";
      } else if (isReservedMinusOp(sToken)) {
        type = "minusOp";
      } else if(isReservedMultiplyOp(sToken)) {
        type = "multiplyOp";
      } else if(isReservedAssignmentOp(sToken)) {
        type = "assignmentOp";
      } else if (isReservedLessThanOp(sToken)) {
        type = "lessthanOp";
      } else if (isReservedOpenParen(sToken)) {
        type = "openparen";
      } else if (isReservedClosedParen(sToken)) {
        type = "closedparen";
      } else if (isReservedIf(sToken)) {
        type = "if";
      } else if (isReservedThen(sToken)) {
        type = "then";
      } else if (isReservedElse(sToken)) {
        type = "else";
      } else if (isReservedEndIf(sToken)) {
        type = "endif";
      } else if (isReservedMain(sToken)) {
        type = "main";
      } else if (isReservedNot(sToken)) {
        type = "not";
      } else if (isReservedOr(sToken)) {
        type = "or";
      } else if (isReservedAnd(sToken)) {
        type = "and";
      } else if (isReservedComment(sToken)) {
        type = "comment";
      } else if (isReservedForwardSlash(sToken)) {
        type = "forwardslash";
      } else if (isReservedComma(sToken)) {
        type = "comma";
      } else if (isReservedBoolean(sToken)) {
        type = "boolean";
      } else if (isReservedTrue(sToken)) {
        type = "true";
      } else if (isReservedFalse(sToken)) {
        type = "false";
      } else if (isReservedColon(sToken)) {
        type = "colon";
      } else if (isInt(sToken)) {
        type = "integer";
      } else if (isReservedPrint(sToken)) {
        type = "print";
      }else{
        type = "identifier";
      }  
      return type;
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

   
   public static boolean isInt(String num) throws LexicalException
   {
     Long lowerBound = -4294967296L; 
     Long upperBound =  4294967295L;
     
     try {
       
       Long.valueOf(num).longValue();
       
     } catch (NumberFormatException nfe) {
      return false;
     }
     
     Long temp = Long.valueOf(num).longValue();
     if( !(lowerBound<=temp) || (upperBound>=temp) ){
       // TODO: Add exception here
       throw new LexicalException("Number out of range");
     }
     
     return true; 
   }
   
   public String getValue(){ return cvalue; }   
   public String getType(){ return type; }
   public String toString(){ return (String) cvalue; }
}