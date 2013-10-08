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
        if (symbol.getValue().equals( "program" )       ) return 0;
        if (symbol.equals( NonTerminal.Declarations )  ) return 1;
        if (symbol.equals( NonTerminal.Declaration )   ) return 2;
        if (symbol.equals( NonTerminal.Statements )    ) return 3;
        if (symbol.equals( NonTerminal.Statement )     ) return 4;
        if (symbol.equals( NonTerminal.ExpressionTail )) return 5;
        if (symbol.equals( NonTerminal.Value )         ) return 6;

        return 0; // return an exception
    }

    private int convertToken( Token token )
    {
        return token.type();
    }

}
