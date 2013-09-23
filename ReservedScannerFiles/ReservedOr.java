
public class ReservedOr {
	
	public static void main( String[] args )
    {
        int START_STATE    = 0;
        int TERMINAL_STATE = 2; 
        

        String candidate = "or";
        char   next;
        
        if (candidate.length()!=2){
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
                        case 'o': state++; break;
                        default : state = -1;
                    }
                    break;

                case 1:
                    switch ( next )
                    {
                        case 'r': state++; break;
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
