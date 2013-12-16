import java.util.Stack;

public class StartFunction extends SemanticAction
{
  public StartFunction(){
    name = "Start Function Call";
    type = TYPE.ERROR; // This should only be used for counting actuals
  }
  
  public StartFunction( StartFunction mi )
  {
    name = mi.name;
    type = mi.type;
  }
  
  public void execute( Stack stack )
  {
    if( Compiler.extendedDebug )
      System.out.println( "Pushing Start Function" );
    stack.push( this );
  }
  
  public void updateAST( Stack<SemanticAction> semanticStack, Stack<String> nameStack )
  {
    if( TableDrivenParser.functionDepth > 0 ){
      TableDrivenParser.functionDepth++;
    }
    
    TableDrivenParser.actualsCounts.add( TableDrivenParser.functionDepth ); 
    TableDrivenParser.actualsCounts.set( TableDrivenParser.functionDepth, 0 );
    
    if( TableDrivenParser.functionDepth > 0 ){
      int prevActualsCounter = TableDrivenParser.functionDepth - 1;
      TableDrivenParser.actualsCounts.set( prevActualsCounter , TableDrivenParser.actualsCounts.get( prevActualsCounter ) + 1 );
    }
  }
  
  
  public SemanticAction copy(){
    return new StartFunction( this );
  }
}