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
       Token print              = new Token("print");
       Token number             = new Token("number");

       

       ParseAction rule00 = new PushNothing();
       ParseAction rule01 = new PushSequence(
                 new ParseAction[] { new Push("DEFINITIONS"),
                                     new Push(endOfStream)
                      } );
       ParseAction rule02 = new PushSequence(
                 new ParseAction[] { new Push("DEF1")
                                
                      } );
       ParseAction rule03 = new PushSequence(
                 new ParseAction[] { new Push("DEF"),
                		     new Push("DEFINITIONS1")
                                     
                      } );
       ParseAction rule04 = new PushSequence(
                 new ParseAction[] {  new Push("Definitions"),
                		              
                      } );
       ParseAction rule05 = new PushSequence(
               	new ParseAction[] { new Push("IDENTIFIER"),
                                    new Push(openParen),
                                    new Push("FORMALS"),
                                    new Push(closedParen),
                                    new Push(colonOp),
                                    new Push("TYPE"),
                                    new Push("BODY")
                      } );
       ParseAction rule06 = new PushSequence(
                 new ParseAction[] {
                                     new Push("NONEMPTYFORMALS")
                      } );
       ParseAction rule07 = new PushSequence(
                 new ParseAction[] { new Push("FORMAL"),
                                     new Push("NONEMPTYFORMALS1")
                      } );
       ParseAction rule08 = new PushSequence(
                 new ParseAction[] { new Push(comma),
                                     new Push("NONEMPTYFORMALS"),
                                
                      } );
       ParseAction rule09 = new PushSequence(
                 new ParseAction[] { new Push("IDENTIFIER"),
                                     new Push(colonOp),
                                     new Push("TYPE")
                      } );
       ParseAction rule10 = new PushSequence(
               new ParseAction[] { new Push("PRINT"),
                                   new Push("BODY1")                             
                    } );
       ParseAction rule11 = new PushSequence(
               new ParseAction[] { new Push("EXPR"),
                    } );
       ParseAction rule12 = new PushSequence(
               new ParseAction[] { new Push("BODY"),
                    } );
       ParseAction rule13 = new PushSequence(
               new ParseAction[] { new Push(integerOp),
                                   new Push(booleanOp),
                    } );
       ParseAction rule14 = new PushSequence(
               new ParseAction[] { new Push("SIMPLE-EXPR"),
                                   new Push("SIMPLE-EXPR1"),
                    } );
       ParseAction rule15 = new PushSequence(
               new ParseAction[] { new Push(lessThanOp),
                                   new Push("EXPR"),
                    } );
       ParseAction rule16 = new PushSequence(
               new ParseAction[] { new Push(assignmentOp),
            		   		       new Push("EXPR"),
                    } );
       ParseAction rule17 = new PushSequence(
               new ParseAction[] { new Push("TERM"),
                              new Push("TERM2"),
                    } );
       ParseAction rule18 = new PushSequence(
               new ParseAction[] { new Push(orOp),
                                   new Push("SIMPLE-EXPR"),
                    } );
       ParseAction rule19 = new PushSequence(
               new ParseAction[] { new Push(plusOp),
                              new Push("SIMPLE-EXPR"),
                    } );
       ParseAction rule20 = new PushSequence(
               new ParseAction[] { new Push(minusOp),
                                   new Push("SIMPLE-EXPR"),
                    } );
       ParseAction rule21 = new PushSequence(
               new ParseAction[] { new Push("FACTOR"),
                                   new Push("TERM1"),
                    } );
       ParseAction rule22 = new PushSequence(
               new ParseAction[] { new Push(andOp),
                                   new Push("TERM"),
                    } );
       ParseAction rule23 = new PushSequence(
               new ParseAction[] { new Push(multiplyOp),
                                   new Push("TERM"),
                    } );
       ParseAction rule24 = new PushSequence(
               new ParseAction[] {  new Push(forwardSlash),
                                    new Push("TERM"),
                    } );
       ParseAction rule25 = new PushSequence(
               new ParseAction[] { new Push(ifOp),
                              new Push("EXPR"),
                              new Push(thenOp),
                              new Push("EXPR"),
                              new Push(elseOp),
                              new Push("EXPR"),
                              new Push(endIfOp)
                    } );
       ParseAction rule26 = new PushSequence(
               new ParseAction[] { new Push(notOp),
                                   new Push("FACTOR"),
                    } );
       ParseAction rule27 = new PushSequence(
               new ParseAction[] { new Push("IDENTIFIER1"),
                    } );
       ParseAction rule28 = new PushSequence(
               new ParseAction[] { new Push("LITERAL"),
                    } );
       ParseAction rule29 = new PushSequence(
               new ParseAction[] { new Push(minusOp),
                                   new Push("FACTOR"),
                    } );
       ParseAction rule30 = new PushSequence(
               new ParseAction[] { new Push("IDENTIFIER"),
                                   new Push("ACTUALS1"),
                    } );
       ParseAction rule31 = new PushSequence(
               new ParseAction[] { new Push(openParen),
                                   new Push("ACTUALS"),
                                   new Push(closedParen)
                    } );
       ParseAction rule32 = new PushSequence(
               new ParseAction[] { new Push("NONEMPTYACTUALS"),
                    } );
       ParseAction rule33 = new PushSequence(
               new ParseAction[] { new Push("EXPR"),
                              	   new Push(comma),
                              	   new Push("NONEMPTYACTUALS1")
                    } );
       ParseAction rule34 = new PushSequence(
               new ParseAction[] { new Push("NONEMPTYACTUALS")
                    } );
       ParseAction rule35 = new PushSequence(
               new ParseAction[] { new Push("NUMBER"),
            		   	   new Push("BOOLEAN"),
                    } );
       ParseAction rule36 = new PushSequence(
               new ParseAction[] { new Push(print),
            	                   new Push(openParen),
            		           new Push("EXPR"),
            		   	   new Push(closedParen)
                    } );


       table.add( "PROGRAM", identifierOp, rule01 );
       table.add( "DEFINITIONS", identifierOP, rule02 );
       table.add( "DEF1", identifierOP, rule03 );
       
       table.add( "DEFINITIONS1", identifierOP, rule04 );
       table.add( "DEFINITIONS1", endOfStream, rule00 );

       table.add( "DEF", identifierOp, rule05 );
       
       table.add( "FORMALS", identifierOp, rule06 );
       table.add( "FORMALS", endOfStream,  rule00 );
       
       table.add( "NONEMPTYFORMALS", identifierOp, rule07 );
       table.add( "NONEMPTYFORMALS1", comma, rule08 );

       table.add( "FORMAL", identifierOp , rule09 );
       
       table.add( "BODY", print, rule10 );
       table.add( "BODY", ifOp, rule11 );
       table.add( "BODY", notOP, rule12 );
       table.add( "BODY", identifierOp, rule13 );
       table.add( "BODY", number, rule14 );
       table.add( "BODY", booleanOp, rule15 );
       table.add( "BODY", minusOp, rule16 );
       
       table.add( "BODY1", print, rule10 );
       table.add( "BODY1", ifOp, rule11 );
       table.add( "BODY1", notOP, rule12 );
       table.add( "BODY1", identifierOp, rule13 );
       table.add( "BODY1", literal, rule14 );
       table.add( "BODY1", minusOp, rule15 );
       
       table.add( "TYPE", integerOp, rule16 );
       table.add( "TYPE", booleanOp, rule17 );
       
       table.add( "EXPR", ifOp,  rule18 );
       table.add( "EXPR", notOp,  rule19 );
       table.add( "EXPR", identifierOp,  rule20 );
       table.add( "EXPR", literal,  rule21 );
       table.add( "EXPR", minusOp,  rule22 );
       
       table.add( "SIMPLE-EXPR1", lessThanOp,  rule23 );
       table.add( "SIMPLE-EXPR1", equalsOp,  rule24 );
       table.add( "SIMPLE-EXPR1", endOfStream,  rule00 );
       
       table.add( "SIMPLE-EXPR", ifOp,  rule18 );
       table.add( "SIMPLE-EXPR", notOp,  rule19 );
       table.add( "SIMPLE-EXPR", identifierOp,  rule20 );
       table.add( "SIMPLE-EXPR", literal,  rule21 );
       table.add( "SIMPLE-EXPR", minusOp,  rule22 );
       
       table.add( "TERM2", orOp, rule23 );
       table.add( "TERM2", plusOp, rule24 );
       table.add( "TERM2", minusOp, rule25 );
       table.add( "TERM2", endOfStream, rule00 );

       table.add( "TERM", ifOp, rule24 );
       table.add( "TERM", notOp, rule25 );
       table.add( "TERM", identifierOp, rule26 );
       table.add( "TERM", number, rule27 );
       table.add( "TERM", booleanOp, rule28 );
       table.add( "TERM", minusOp, rule29 );
       
       table.add( "TERM1", andOp, rule29 );
       table.add( "TERM1", multiplyOp, rule30 );
       table.add( "TERM1", divideOp, rule31 );
       table.add( "TERM1", endOfStream, rule00 );
       
       table.add( "FACTOR", ifOp, rule24 );
       table.add( "FACTOR", notOp, rule25 );
       table.add( "FACTOR", identifierOp, rule26 );
       table.add( "FACTOR", number, rule27 );
       table.add( "FACTOR", booleanOp, rule28 );
       table.add( "FACTOR", minusOp, rule29 );
       
       table.add( "IDENTIFIER1", identifierOp, rule32 );
       table.add( "ACTUALS1", OpenParen, rule33 );
       
       table.add( "ACTUALS", ifOp, rule32 );
       table.add( "ACTUALS", notOp, rule33 );
       table.add( "ACTUALS", identifierOp, rule34 );
       table.add( "ACTUALS", number, rule35 );
       table.add( "ACTUALS", booleanOp, rule36 );
       table.add( "ACTUALS", minusOp, rule37 );
       table.add( "ACTUALS", endOfStream, rule00 );
       
       table.add( "NONEMPTYACTUALS", ifOp, rule32 );
       table.add( "NONEMPTYACTUALS", notOp, rule33 );
       table.add( "NONEMPTYACTUALS", identifierOp, rule34 );
       table.add( "NONEMPTYACTUALS", number, rule35 );
       table.add( "NONEMPTYACTUALS", booleanOp, rule36 );
       table.add( "NONEMPTYACTUALS", minusOp, rule37 );
       
       table.add( "NONEMPTYACTUALS1", ifOp, rule32 );
       table.add( "NONEMPTYACTUALS1", notOp, rule33 );
       table.add( "NONEMPTYACTUALS1", identifierOp, rule34 );
       table.add( "NONEMPTYACTUALS1", literal, rule35 );
       table.add( "NONEMPTYACTUALS1", minusOp, rule36 );
       table.add( "NONEMPTYACTUALS1", endOfStream, rule00 );
       
       table.add( "LITERAL", number, rule38 );
       table.add( "LITERAL", booleanOp, rule39 );
       
       table.add( "PRINT", print, rule40 );
       

       } catch (Exception e) {
           System.out.println( "Trouble building parse table.  No entries." );
       }

       return table;
    }
}
