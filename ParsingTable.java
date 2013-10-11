import java.util.Stack;

public class ParsingTable {
	private ParseAction[][] table;

    public ParsingTable()
    {
         table = new ParseAction[15][11];
    }

    public ParseAction lookup( String nonTerm, Token term )
    {
        int row = convertNonTerminal( nonTerm );
        int col = convertToken( term  );

        return table[row][col];
    }

    // ------------------------------------------------------------------

    public void add( String nonTerm, Token term, ParseAction action )
                throws Exception
    {
        int row = convertNonTerminal( nonTerm );
        int col = convertToken( term  );

        table[row][col] = action;
    }

    public String toString()
    {
        return table.toString();
    }

    // ------------------------------------------------------------------

    private int convertNonTerminal( String symbol )
    {
        if (symbol.equals("program")            ) return 0;
        if (symbol.equals( "definitions" )      ) return 1;
        if (symbol.equals( "def1" )             ) return 2;
        if (symbol.equals( "definitions1" )     ) return 3;
        if (symbol.equals( "def" )              ) return 4;
        if (symbol.equals( "formals" )          ) return 5;
        if (symbol.equals( "nonemptyformals" )  ) return 6;
        if (symbol.equals( "nonemptyformals1" ) ) return 7;
        if (symbol.equals( "formal" )           ) return 8;
        if (symbol.equals( "body" )             ) return 9;
        if (symbol.equals( "body1" )            ) return 10;
        if (symbol.equals( "type" )             ) return 11;
        if (symbol.equals( "expr" )             ) return 12;
        if (symbol.equals( "simple-expr1" )     ) return 13;
        if (symbol.equals( "simple-expr" )      ) return 14;
        if (symbol.equals( "term2" )            ) return 15;
        if (symbol.equals( "term" )             ) return 16;
        if (symbol.equals( "term1" )            ) return 17;
        if (symbol.equals( "factor" )           ) return 18;
        if (symbol.equals( "identifier1" )      ) return 19;
        if (symbol.equals( "actuals1" )         ) return 20;
        if (symbol.equals( "actuals" )          ) return 21;
        if (symbol.equals( "nonemptyactuals" )  ) return 22;
        if (symbol.equals( "nonemptyactuals1" ) ) return 23;
        if (symbol.equals( "literal" )          ) return 24;
        if (symbol.equals( "print" )            ) return 25;       
        
        //left all numbers as 0 until we figure out what were keeping
        //hey group...should identifier be one? it's not in our grammar
        
        return 0; // return an exception
    }

    private int convertToken( Token token )
    {
        return token.typeToInt();
    }

}