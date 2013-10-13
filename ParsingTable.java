import java.util.Stack;

public class ParsingTable {
 private ParseAction[][] table;

    public ParsingTable()
    {
         table = new ParseAction[27][29];
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
        if (symbol.equalsIgnoreCase("program")            ) return 0;
        if (symbol.equalsIgnoreCase( "definitions" )      ) return 1;
        if (symbol.equalsIgnoreCase( "def1" )             ) return 2;
        if (symbol.equalsIgnoreCase( "definitions1" )     ) return 3;
        if (symbol.equalsIgnoreCase( "def" )              ) return 4;
        if (symbol.equalsIgnoreCase( "formals" )          ) return 5;
        if (symbol.equalsIgnoreCase( "nonemptyformals" )  ) return 6;
        if (symbol.equalsIgnoreCase( "nonemptyformals1" ) ) return 7;
        if (symbol.equalsIgnoreCase( "formal" )           ) return 8;
        if (symbol.equalsIgnoreCase( "body" )             ) return 9;
        if (symbol.equalsIgnoreCase( "body1" )            ) return 10;
        if (symbol.equalsIgnoreCase( "type" )             ) return 11;
        if (symbol.equalsIgnoreCase( "expr" )             ) return 12;
        if (symbol.equalsIgnoreCase( "simple-expr1" )     ) return 13;
        if (symbol.equalsIgnoreCase( "simple-expr" )      ) return 14;
        if (symbol.equalsIgnoreCase( "term2" )            ) return 15;
        if (symbol.equalsIgnoreCase( "term" )             ) return 16;
        if (symbol.equalsIgnoreCase( "term1" )            ) return 17;
        if (symbol.equalsIgnoreCase( "factor" )           ) return 18;
        if (symbol.equalsIgnoreCase( "identifier1" )      ) return 19;
        if (symbol.equalsIgnoreCase( "actuals1" )         ) return 20;
        if (symbol.equalsIgnoreCase( "actuals" )          ) return 21;
        if (symbol.equalsIgnoreCase( "nonemptyactuals" )  ) return 22;
        if (symbol.equalsIgnoreCase( "nonemptyactuals1" ) ) return 23;
        if (symbol.equalsIgnoreCase( "literal" )          ) return 24;
        if (symbol.equalsIgnoreCase( "print" )            ) return 25;       
        
        //left all numbers as 0 until we figure out what were keeping
        //hey group...should identifier be one? it's not in our grammar
        
        return 0; // return an exception
    }

    private int convertToken( Token token )
    {
        return token.typeToInt();
    }

}