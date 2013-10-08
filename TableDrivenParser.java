import java.io.IOException;
import java.util.Stack;

public class TableDrivenParser extends Parser
{
    private ParsingTable acTable;

    public TableDrivenParser( Scanner source )
    {
        super( source );
        acTable = makeAcParsingTable();
    }

    protected boolean parseProgram() throws ParseException,
                                            LexicalException,
                                            IOException
    {
        // ... the algorithm
        return answer;
    }

    // -------------------------------------------------------------------
    // AC PARSING TABLE FACTORY

    private ParsingTable makeAcParsingTable()
    {
       ParsingTable table = new ParsingTable();

       try {

       Token floatDeclaration = new Token( Token.FloatDeclaration );
       Token intDeclaration   = new Token( Token.IntegerDeclaration );
       Token print            = new Token( Token.PrintOp );
       Token assignment       = new Token( Token.AssignmentOp );
       Token plus             = new Token( Token.PlusOp );
       Token minus            = new Token( Token.MinusOp );
       Token identifier       = new Token( Token.Identifier );
       Token floatValue       = new Token( Token.FloatValue );
       Token integerValue     = new Token( Token.IntegerValue );
       Token endOfStream      = new Token( Token.EOFsymbol );

       ParseAction rule00 = new PushNothing();
       ParseAction rule01 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Declarations),
                                new PushNonTerminal(NonTerminal.Statements),
                                new PushTerminal   (endOfStream)
                      } );
       ParseAction rule02 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Declaration),
                                new PushNonTerminal(NonTerminal.Declarations)
                      } );
       ParseAction rule03 = new PushSequence(
                 new ParseAction[] { new PushTerminal(floatDeclaration),
                                new PushTerminal(identifier)
                      } );
       ParseAction rule04 = new PushSequence(
                 new ParseAction[] { new PushTerminal(intDeclaration),
                                new PushTerminal(identifier)
                      } );
       ParseAction rule05 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Statement),
                                new PushNonTerminal(NonTerminal.Statements)
                      } );
       ParseAction rule06 = new PushSequence(
                 new ParseAction[] { new PushTerminal(print),
                                new PushTerminal(identifier)
                      } );
       ParseAction rule07 = new PushSequence(
                 new ParseAction[] { new PushTerminal(identifier),
                                new PushTerminal(assignment),
                                new PushNonTerminal(NonTerminal.Value),
                                new PushNonTerminal(NonTerminal.ExpressionTail)
                      } );
       ParseAction rule08 = new PushSequence(
                 new ParseAction[] { new PushTerminal(plus),
                                new PushNonTerminal(NonTerminal.Value),
                                new PushNonTerminal(NonTerminal.ExpressionTail)
                      } );
       ParseAction rule09 = new PushSequence(
                 new ParseAction[] { new PushTerminal(minus),
                                new PushNonTerminal(NonTerminal.Value),
                                new PushNonTerminal(NonTerminal.ExpressionTail)
                      } );
       ParseAction rule10 = new PushTerminal(identifier);
       ParseAction rule11 = new PushTerminal(floatValue);
       ParseAction rule12 = new PushTerminal(integerValue);

       table.add( NonTerminal.Program, floatDeclaration, rule01 );
       table.add( NonTerminal.Program, intDeclaration,   rule01 );
       table.add( NonTerminal.Program, print,            rule01 );
       table.add( NonTerminal.Program, identifier,       rule01 );
       table.add( NonTerminal.Program, endOfStream,      rule01 );

       table.add( NonTerminal.Declarations, floatDeclaration, rule02 );
       table.add( NonTerminal.Declarations, intDeclaration,   rule02 );
       table.add( NonTerminal.Declarations, print,            rule00 );
       table.add( NonTerminal.Declarations, identifier,       rule00 );
       table.add( NonTerminal.Declarations, endOfStream,      rule00 );

       table.add( NonTerminal.Declaration, floatDeclaration, rule03 );
       table.add( NonTerminal.Declaration, intDeclaration,   rule04 );

       table.add( NonTerminal.Statements, print,       rule05 );
       table.add( NonTerminal.Statements, identifier,  rule05 );
       table.add( NonTerminal.Statements, endOfStream, rule00 );

       table.add( NonTerminal.Statement, print,      rule06 );
       table.add( NonTerminal.Statement, identifier, rule07 );

       table.add( NonTerminal.ExpressionTail, print,       rule00 );
       table.add( NonTerminal.ExpressionTail, plus,        rule08 );
       table.add( NonTerminal.ExpressionTail, minus,       rule09 );
       table.add( NonTerminal.ExpressionTail, identifier,  rule00 );
       table.add( NonTerminal.ExpressionTail, endOfStream, rule00 );

       table.add( NonTerminal.Value, identifier,   rule10 );
       table.add( NonTerminal.Value, floatValue,   rule11 );
       table.add( NonTerminal.Value, integerValue, rule12 );

       } catch (Exception e) {
           System.out.println( "Trouble building parse table.  No entries." );
       }

       return table;
    }
}