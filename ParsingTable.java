import java.util.Stack;

public class ParsingTable {
	private ParseAction[][] table;

    public ParsingTable()
    {
         table = new ParseAction[7][10];
    }

    public ParseAction lookup( Token nonTerm, Token term )
    {
        int row = convertNonTerminal( nonTerm );
        int col = convertToken( term  );

        return table[row][col];
    }

    // ------------------------------------------------------------------

    public void add( Token nonTerm, Token term, ParseAction action )
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

    private int convertNonTerminal( Token symbol )
    {
        if (symbol.getValue().equals( "program" )          ) return 0;
        if (symbol.getValue().equals( "definitions" )      ) return 0;
        if (symbol.getValue().equals( "def1" )             ) return 0;
        if (symbol.getValue().equals( "definitions1" )     ) return 0;
        if (symbol.getValue().equals( "def" )              ) return 0;
        if (symbol.getValue().equals( "formals" )          ) return 0;
        if (symbol.getValue().equals( "nonemptyformals" )  ) return 0;
        if (symbol.getValue().equals( "nonemptyformals1" ) ) return 0;
        if (symbol.getValue().equals( "formal" )           ) return 0;
        if (symbol.getValue().equals( "body" )             ) return 0;
        if (symbol.getValue().equals( "body1" )            ) return 0;
        if (symbol.getValue().equals( "type" )             ) return 0;
        if (symbol.getValue().equals( "expr" )             ) return 0;
        if (symbol.getValue().equals( "simple-expr1" )     ) return 0;
        if (symbol.getValue().equals( "simple-expr" )      ) return 0;
        if (symbol.getValue().equals( "term2" )            ) return 0;
        if (symbol.getValue().equals( "term" )             ) return 0;
        if (symbol.getValue().equals( "term1" )            ) return 0;
        if (symbol.getValue().equals( "factor" )           ) return 0;
        if (symbol.getValue().equals( "identifier1" )      ) return 0;
        if (symbol.getValue().equals( "actuals1" )         ) return 0;
        if (symbol.getValue().equals( "actuals" )          ) return 0;
        if (symbol.getValue().equals( "nonemptyactuals" )  ) return 0;
        if (symbol.getValue().equals( "nonemptyactuals1" ) ) return 0;
        if (symbol.getValue().equals( "literal" )          ) return 0;
        if (symbol.getValue().equals( "print" )            ) return 0;       
        
        //left all numbers as 0 until we figure out what were keeping
        //hey group...should identifier be one? it's not in our grammer
        
        return 0; // return an exception
    }

    private int convertToken( Token token )
    {
        return token.type();
    }

}
