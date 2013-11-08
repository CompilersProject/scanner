
public class SymbolTable 
{
 private Object[][] table;

    public SymbolTable()
    {
         table = new Object[100][100];
    }

    public Object lookup( int row, int col )
    {

        return table[row][col];
    }

    // ------------------------------------------------------------------

    public void add( Object node, int row, int col )
    {
      table[row][col] = node;
    }

    public String toString()
    {
        return table.toString();
    }
}
