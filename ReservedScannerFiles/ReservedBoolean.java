
public class ReservedBoolean {
	
	public static void main( String[] args )
    {
        int START_STATE    = 0;
        int TERMINAL_STATE = 7; 
        

        String candidate = "boolean";
        char   next;
        
        if (candidate.length()!=7){
        	System.out.println("Reject");
        	System.exit(0);
        }

        int state = START_STATE;
        for (int i = 0; i < candidate.length(); i++)
        {
            next = candidate.charAt(i);
            switch (state)
            {
                case 0:
                    switch ( next )
                    {
                        case 'b': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'o': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'o': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
                    switch ( next )
                    {
                        case 'l': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 4:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 5:
                    switch ( next )
                    {
                        case 'a': state++; break;
                        default : state = -1;
                    }
                    break;
                    
                case 6:
                    switch ( next )
                    {
                        case 'n': state++; break;
                        default : state = -1;
                    }
                    break;
                  
            }
        }

        if ( state == TERMINAL_STATE )
            System.out.println( "accept" );
        else
            System.out.println( "reject" );
    }

}
