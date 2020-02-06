 package util;

 import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;

 public class StringUtils {

     public static String firstLetterToUpperCase(String word) {

         int wordLength = word.length();
         String firstLetter = word.substring(0, 1);
         firstLetter = firstLetter.toUpperCase();

         return (firstLetter + word.substring(1, wordLength));
     }

     public static String firstLetterToUpperCaseRestToLowerCase(String word) {

         int wordLength = word.length();
         String firstLetter = word.substring(0, 1);
         String rest = word.substring(1, wordLength);

         firstLetter = firstLetter.toUpperCase();
         rest = rest.toLowerCase();

         return (firstLetter + rest);
     }

     public static String parseAndFormatDate(String date) {
         return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
                 .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
     }

 }