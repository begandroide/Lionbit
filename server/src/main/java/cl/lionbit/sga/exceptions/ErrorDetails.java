package cl.lionbit.sga.exceptions;
import java.util.Date;


public class ErrorDetails {
    private Date timestamp;
    private String message;
    private Throwable details;
    public ErrorDetails(Date timestamp, String message, Throwable details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
    public Throwable getDetails() {
        return details;
    }

    // @Override
    // public String toString(){
    //     return "{time: " + timestamp.toString() + "}";
    // }
}
