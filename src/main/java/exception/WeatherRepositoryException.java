package exception;

public class WeatherRepositoryException extends RuntimeException {
     public WeatherRepositoryException() {
     }

     public WeatherRepositoryException(String message) {
         super(message);
     }

     public WeatherRepositoryException(String message, Throwable cause) {
         super(message, cause);
     }

     public WeatherRepositoryException(Throwable cause) {
         super(cause);
     }

     public WeatherRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
         super(message, cause, enableSuppression, writableStackTrace);
     }
}