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

    protected void parseProgram() throws IOException, LexicalException
    {
      Stack parseStack = new Stack();
      
      new Push( new Token(Token.TYPE.EOS) ).execute(parseStack);   // Step 1
      new Push( "PROGRAM" ).execute(parseStack); // Step 2

      while( true ){ // Step 3?
        Object symbol = parseStack.pop(); // Pop A
        
        if( symbol instanceof Token ){ // A is terminal
          Token terminal = (Token) symbol;
          
          if( terminal.getType() == Token.TYPE.EOS ){
            System.out.println("LE FIN");
            return;
          } else if( terminal.getType() == Token.TYPE.COMMENT ){
            break; // Ignore comments
          } else if( terminal.equals( scanner.peek() )){
            scanner.getNextToken();
          } else{
            throw new LexicalException("Terminal Error: Make a new exception for this, stupid");
          }
        }
        else { // A is non-terminal
          String nonTerminal = (String) symbol;
          ParseAction rule = kleinTable.lookup(nonTerminal,scanner.peek());
          
          if( rule != null ){ // TODO: Does this work?
            new PushSequence( rule ).execute(parseStack); // TODO: Loop through and push these on backwards 
          } else{ // Failed to find rule for table[A,i]
            throw new LexicalException("Non-Terminal Error: Make a new exception for this, stupid");
          }
        }
      }
      // ... the algorithm
      // return answer;
    }

    // -------------------------------------------------------------------
    // AC PARSING TABLE FACTORY

    private ParsingTable makeKleinParsingTable()
    {
       ParsingTable table = new ParsingTable();

       try {
/*
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
       Token print    = new Token("print");

       

       ParseAction rule00 = new PushNothing();
       ParseAction ruleProgram = new PushSequence(
                 new ParseAction[] { new Push("DEFINITIONS"),
                                  new Push(endOfStream)
                      } );
       ParseAction ruleDefinitions = new PushSequence(
                 new ParseAction[] { new Push("DEF1")
                                
                      } );
       ParseAction ruleDef1 = new PushSequence(
                 new ParseAction[] { new Push("DEF"),
                       new Push("DEFINITIONS1")
                                     
                      } );
       ParseAction ruleDefinitions1 = new PushSequence(
                 new ParseAction[] {  new Push("Definitions"),
                                
                      } );
       ParseAction ruleDef = new PushSequence(
                new ParseAction[] { new Push("IDENTIFIER"),
                                    new Push(openParen),
                                    new Push("FORMALS"),
                                    new Push(closedParen),
                                    new Push(colonOp),
                                    new Push("TYPE"),
                                    new Push("BODY")
                      } );
       ParseAction ruleFormals = new PushSequence(
                 new ParseAction[] {
                                     new Push("NONEMPTYFORMALS")
                      } );
       ParseAction ruleNonEmptyFormals = new PushSequence(
                 new ParseAction[] { new Push("FORMAL"),
                                     new Push("NONEMPTYFORMALS1")
                      } );
       ParseAction ruleNonEmptyFormals1 = new PushSequence(
                 new ParseAction[] { new Push(comma),
                                     new Push("NONEMPTYFORMALS"),
                                
                      } );
       ParseAction ruleFormal = new PushSequence(
                 new ParseAction[] { new Push("IDENTIFIER"),
                                new Push(colonOp),
                                new Push("TYPE")
                      } );
       ParseAction ruleBody = new PushSequence(
               new ParseAction[] { new Push("PRINT"),
                                   new Push("BODY1")                             
                    } );
       ParseAction ruleBodyPart2 = new PushSequence(
               new ParseAction[] { new Push("EXPR"),
                    } );
       ParseAction ruleBody1 = new PushSequence(
               new ParseAction[] { new Push("BODY"),
                    } );
       ParseAction ruleType = new PushSequence(
               new ParseAction[] { new Push(integerOp),
                                   new Push(booleanOp),
                    } );
       ParseAction ruleExpr = new PushSequence(
               new ParseAction[] { new Push("SIMPLE-EXPR"),
                                   new Push("SIMPLE-EXPR1"),
                    } );
       ParseAction ruleSimpleExpr1 = new PushSequence(
               new ParseAction[] { new Push(lessThanOp),
                                   new Push("EXPR"),
                    } );
       ParseAction ruleSimpleExpr1Part2 = new PushSequence(
               new ParseAction[] { new Push(assignmentOp),
                          new Push("EXPR"),
                    } );
       ParseAction ruleSimpleExpr = new PushSequence(
               new ParseAction[] { new Push("TERM"),
                              new Push("TERM2"),
                    } );
       ParseAction ruleTerm2 = new PushSequence(
               new ParseAction[] { new Push(orOp),
                                   new Push("SIMPLE-EXPR"),
                    } );
       ParseAction ruleTerm2Part2 = new PushSequence(
               new ParseAction[] { new Push(plusOp),
                              new Push("SIMPLE-EXPR"),
                    } );
       ParseAction ruleTerm2Part3 = new PushSequence(
               new ParseAction[] { new Push(minusOp),
                                   new Push("SIMPLE-EXPR"),
                    } );
       ParseAction ruleTerm = new PushSequence(
               new ParseAction[] { new Push("FACTOR"),
                                   new Push("TERM1"),
                    } );
       ParseAction ruleTerm1 = new PushSequence(
               new ParseAction[] { new Push(andOp),
                                   new Push("TERM"),
                    } );
       ParseAction ruleTerm1Part2 = new PushSequence(
               new ParseAction[] { new Push(multiplyOp),
                                   new Push("TERM"),
                    } );
       ParseAction ruleTerm1Part3 = new PushSequence(
               new ParseAction[] {  new Push(forwardSlash),
                                    new Push("TERM"),
                    } );
       ParseAction ruleFactor = new PushSequence(
               new ParseAction[] { new Push(ifOp),
                              new Push("EXPR"),
                              new Push(thenOp),
                              new Push("EXPR"),
                              new Push(elseOp),
                              new Push("EXPR"),
                              new Push(endIfOp)
                    } );
       ParseAction ruleFactorPart2 = new PushSequence(
               new ParseAction[] { new Push(notOp),
                                   new Push("FACTOR"),
                    } );
       ParseAction ruleFactorPart3 = new PushSequence(
               new ParseAction[] { new Push("IDENTIFIER1"),
                    } );
       ParseAction ruleFactorPart4 = new PushSequence(
               new ParseAction[] { new Push("LITERAL"),
                    } );
       ParseAction ruleFactorPart5 = new PushSequence(
               new ParseAction[] { new Push(minusOp),
                                   new Push("FACTOR"),
                    } );
       ParseAction ruleIdentifier1 = new PushSequence(
               new ParseAction[] { new Push("IDENTIFIER"),
                                   new Push("ACTUALS1"),
                    } );
       ParseAction ruleActuals1 = new PushSequence(
               new ParseAction[] { new Push(openParen),
                                   new Push("ACTUALS"),
                                   new Push(closedParen)
                    } );
       ParseAction ruleActuals = new PushSequence(
               new ParseAction[] { new Push("NONEMPTYACTUALS"),
                    } );
       ParseAction ruleNonEmptyActuals = new PushSequence(
               new ParseAction[] { new Push("EXPR"),
                                  new Push(comma),
                                  new Push("NONEMPTYACTUALS1")
                    } );
       ParseAction ruleNonEmptyActuals1 = new PushSequence(
               new ParseAction[] { new Push("NONEMPTYACTUALS")
                    } );
       ParseAction ruleLiteral = new PushSequence(
               new ParseAction[] { new Push("NUMBER"),
                             new Push("BOOLEAN"),
                    } );
       ParseAction rulePrint = new PushSequence(
               new ParseAction[] { new Push(print),
                       new Push(openParen),
                       new Push("EXPR"),
                       new Push(closedParen)
                    } );


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
*/
       } catch (Exception e) {
           System.out.println( "Trouble building parse table.  No entries." );
       }

       return table;
    }
}