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


       table.add( "Program", identifierOp, rule01 );
       table.add( "Definitions", identifierOP, rule02 );
       table.add( "Def1", identifierOP, rule03 );
       
       table.add( "Definitions1", identifierOP, rule04 );
       table.add( "Definitions1", endOfStream, rule00 );

       table.add( "Def", identifierOp, rule05 );
       
       table.add( "Formals", identifierOp, rule06 );
       table.add( "Formals", endOfStream,  rule00 );
       
       table.add( "NonemptyFormals", identifierOp, rule07 );
       table.add( "NonemptyFormals1", comma, rule08 );

       table.add( "Formal", identifierOp , rule09 );
       
       table.add( "Body", print, rule10 );
       table.add( "Body", ifOp, rule11 );
       table.add( "Body", notOP, rule12 );
       table.add( "Body", identifierOp, rule13 );
       table.add( "Body", number, rule14 );
       table.add( "Body", booleanOp, rule15 );
       table.add( "Body", minusOp, rule16 );
       
       table.add( "Body1", print, rule10 );
       table.add( "Body1", ifOp, rule11 );
       table.add( "Body1", notOP, rule12 );
       table.add( "Body1", identifierOp, rule13 );
       table.add( "Body1", literal, rule14 );
       table.add( "Body1", minusOp, rule15 );
       
       table.add( "Type", integerOp, rule16 );
       table.add( "Type", booleanOp, rule17 );
       
       table.add( "Expr", ifOp,  rule18 );
       table.add( "Expr", notOp,  rule19 );
       table.add( "Expr", identifierOp,  rule20 );
       table.add( "Expr", literal,  rule21 );
       table.add( "Expr", minusOp,  rule22 );
       
       table.add( "Simple-expr1", lessThanOp,  rule23 );
       table.add( "Simple-expr1", equalsOp,  rule24 );
       table.add( "Simple-expr1", endOfStream,  rule00 );
       
       table.add( "Simple-expr", ifOp,  rule18 );
       table.add( "Simple-expr", notOp,  rule19 );
       table.add( "Simple-expr", identifierOp,  rule20 );
       table.add( "Simple-expr", literal,  rule21 );
       table.add( "Simple-expr", minusOp,  rule22 );
       
       table.add( "Term2", orOp, rule23 );
       table.add( "Term2", plusOp, rule24 );
       table.add( "Term2", minusOp, rule25 );
       table.add( "Term2", endOfStream, rule00 );

       table.add( "Term", ifOp, rule24 );
       table.add( "Term", notOp, rule25 );
       table.add( "Term", identifierOp, rule26 );
       table.add( "Term", number, rule27 );
       table.add( "Term", booleanOp, rule28 );
       table.add( "Term", minusOp, rule29 );
       
       table.add( "Term1", andOp, rule29 );
       table.add( "Term1", multiplyOp, rule30 );
       table.add( "Term1", divideOp, rule31 );
       table.add( "Term1", endOfStream, rule00 );
       
       table.add( "Factor", ifOp, rule24 );
       table.add( "Factor", notOp, rule25 );
       table.add( "Factor", identifierOp, rule26 );
       table.add( "Factor", number, rule27 );
       table.add( "Factor", booleanOp, rule28 );
       table.add( "Factor", minusOp, rule29 );
       
       table.add( "Identifier1", identifierOp, rule32 );
       table.add( "Actuals1", OpenParen, rule33 );
       
       table.add( "Actuals", ifOp, rule32 );
       table.add( "Actuals", notOp, rule33 );
       table.add( "Actuals", identifierOp, rule34 );
       table.add( "Actuals", number, rule35 );
       table.add( "Actuals", booleanOp, rule36 );
       table.add( "Actuals", minusOp, rule37 );
       table.add( "Actuals", endOfStream, rule00 );
       
       table.add( "NonemptyActuals", ifOp, rule32 );
       table.add( "NonemptyActuals", notOp, rule33 );
       table.add( "NonemptyActuals", identifierOp, rule34 );
       table.add( "NonemptyActuals", number, rule35 );
       table.add( "NonemptyActuals", booleanOp, rule36 );
       table.add( "NonemptyActuals", minusOp, rule37 );
       
       table.add( "NonemptyActuals1", ifOp, rule32 );
       table.add( "NonemptyActuals1", notOp, rule33 );
       table.add( "NonemptyActuals1", identifierOp, rule34 );
       table.add( "NonemptyActuals1", literal, rule35 );
       table.add( "NonemptyActuals1", minusOp, rule36 );
       table.add( "NonemptyActuals1", endOfStream, rule00 );
       
       table.add( "Literal", number, rule38 );
       table.add( "Literal", booleanOp, rule39 );
       
       table.add( "Print", print, rule40 );
       

       } catch (Exception e) {
           System.out.println( "Trouble building parse table.  No entries." );
       }

       return table;
    }
}
