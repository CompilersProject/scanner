public class Token
{
	//Grammar
    public static final int Program            = 1;
    public static final int Definitions        = 2;
    public static final int Def                = 3;
    public static final int Formals            = 4;
    public static final int NonEmptyFormal     = 5;
    public static final int Formal             = 6;
    public static final int Body               = 7;
    public static final int Type               = 8;
    public static final int Expr	       = 9; 
    public static final int SimpleExpr	      = 10;
    public static final int Term	      = 11;
    public static final int Factor     	      = 12;
    public static final int Actuals   	      = 13;
    public static final int NonemptyActuals   = 14;
    public static final int Literal 	      = 15;
    public static final int Print             = 16;
    
    //Reserved words and symbols
    public static final int PlusOp             = 21;
    public static final int MinusOp            = 22;
    public static final int MultiplyOp         = 23;
    public static final int AssignmentOp       = 24;
    public static final int LessThanOp	       = 25;
    public static final int OpenParen	       = 26;
    public static final int ClosedParen	       = 27;
    public static final int If				   = 28;
    public static final int Then			   = 29;
    public static final int	Else			   = 30;
    public static final int EndIf			   = 31;
    public static final int Main     	   	           = 32;
    public static final int Not				   = 33;
    public static final int Or				   = 34;
    public static final int And				   = 35;
    public static final int Comment			   = 36;
    public static final int ForwardSlash                   = 37;
    public static final int Comma			   = 38;
    public static final int Boolean			   = 39;
    public static final int True   	                   = 40;
    public static final int False			   = 41;
    public static final int Colon			   = 42;
    public static final int Integer                        = 43;
    public static final int PrintWord                      = 44;

    private int    type;
    private char   cvalue;
    private int    ivalue;

    public Token(int t)
    {
       this( t, 'A', 0 );
    }
    
    public Token(int t, char v)
    {
       this( t, v, 0 );
    }
    
    public Token(int t, int v)
    {
       this( t, 'A', v );
    }
    
//    public Token(int t, double v)
//    {
//       this( t, 'A', 0);
//    }
    
    protected Token(int t, char c, int i )
    {
       type   = t;
       cvalue = c;
       ivalue = i;
    }

    public int    type  () { return type;   }
    public char   cvalue() { return cvalue; }
    public int    ivalue() { return ivalue; }
}
