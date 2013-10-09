import java.io.IOException;
import java.util.Stack;

public class TableDrivenParser extends Parser

{
	public Stack stackAttack;
    private ParsingTable kleinTable;

    public TableDrivenParser( Scanner source )
    {
        super( source );
        kleinTable = makeKleinParsingTable();
    }

    protected void parseProgram() throws 
                                            IOException
    {
    	
    	

        // ... the algorithm
        // return answer;
    }

    // -------------------------------------------------------------------
    // AC PARSING TABLE FACTORY

    private ParsingTable makeKleinParsingTable()
    {
       ParsingTable table = new ParsingTable();

       try {
    	   
       Token endOfStream        = new Token("$");
       Token plusOp             = new Token("+");
       Token minusOp            = new Token("-");
       Token multiplyOp         = new Token("*");
       Token assignmentOp       = new Token("=");
       Token lessThanOp         = new Token("<");
       Token openParen          = new Token("(");
       Token closedParen        = new Token(")");
       Token ifOp               = new Token("if");
       Token thenOp             = new Token("then");
       Token elseOp             = new Token("else");
       Token endIfOp            = new Token("endif");
       Token mainOp             = new Token("main");
       Token notOp              = new Token("not");
       Token orOp               = new Token("or");
       Token andOp              = new Token("and");
       Token comment            = new Token("//");
       Token forwardSlash       = new Token("/");
       Token comma              = new Token(",");
       Token booleanOp          = new Token("boolean");
       Token trueOp             = new Token("true");
       Token falseOp            = new Token("false");
       Token colonOp            = new Token(":");
       Token integerOp          = new Token("integer");
       Token printOp            = new Token("print");
       Token identifierOp       = new Token("identifier");

       

       ParseAction rule00 = new PushNothing();
       ParseAction ruleProgram = new PushSequence(
                 new ParseAction[] { new Push("DEFINITIONS"),
                                	 new Push(endOfStream)
                      } );
       ParseAction ruleDefinitions = new PushSequence(
                 new ParseAction[] { new Push("DEF1")
                                
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