
public class ReservedThen {
	
	public static void main( String[] args )
    {
        int START_STATE    = 0;
        int TERMINAL_STATE = 4; 
        

        String candidate = "then";
        char   next;
        
        if (candidate.length()!=4){
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
                        case 't': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'h': state++; break;
                        default : state = -1;
                    }
                    break;

                case 2:
                    switch ( next )
                    {
                        case 'e': state++; break;
                        default : state = -1;
                    }
                    break;

                case 3:
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
