package OnlineBookResellingSystem.OBRS_BackEnd.exception;

public class bookNotFoundException extends RuntimeException
{
    public bookNotFoundException(String msg)
    {
        super(msg);
    }
}
