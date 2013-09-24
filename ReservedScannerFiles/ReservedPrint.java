private boolean isReservedPrint(String candidate)
    {
        int START_STATE    = 0;
        int TERMINAL_STATE = 5; 
        
        char   next;
        
        if (candidate.length()!=5){
        	return false;
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
                        case 'p': state++; break;
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

                case 2:
                    switch ( next )
                    {
                        case 'i': state++; break;
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
                    
                case 4:
                    switch ( next )
                    {
                        case 't': state++; break;
                        default : state = -1;
                    }
                    break;
            }
        }

        if ( state == TERMINAL_STATE )
            return true;
        else
            return false;
    }

}
