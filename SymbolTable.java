import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Symbol table implemented as a hash map and
 * contains a method to add to the hash map.
 *
 */

public class SymbolTable 
{
  Map<String, List<SemanticAction>> table;
  
  public SymbolTable()
  {
    table = new HashMap<String, List<SemanticAction>>();
  }
  
  public boolean compare( String key, SemanticAction value )
  {
    List<SemanticAction> nodes = table.get( key );
    
    for( SemanticAction tmp: nodes )
    {
      if( tmp.equals( value ) ){
        return true;
      }
    }
    return false;
  }
  
  public boolean checkFunctionCall( String key )
  {
    return table.containsKey( key );
  }

  public void put( String functionName, SemanticAction node )
  {
    if( table.get(functionName) == null ){
      ArrayList<SemanticAction> tmp = new ArrayList<SemanticAction>();
      tmp.add( node );
      table.put( functionName, tmp );
    }
    else
      table.get( functionName ).add( node );
  }
  
  public Map<String, List<SemanticAction>> getTable() { return table; }
}