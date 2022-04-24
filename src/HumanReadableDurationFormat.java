import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Your task in order to complete this Kata is to write a function which formats a duration, given as a number of seconds, in a human-friendly way.
 *
 * The function must accept a non-negative integer. If it is zero, it just returns "now". Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds.
 *
 * It is much easier to understand with an example:
 *
 * * For seconds = 62, your function should return
 *     "1 minute and 2 seconds"
 * * For seconds = 3662, your function should return
 *     "1 hour, 1 minute and 2 seconds"
 *
 * For the purpose of this Kata, a year is 365 days and a day is 24 hours.
 *
 * Note that spaces are important.
 * Detailed rules
 *
 * The resulting expression is made of components like 4 seconds, 1 year, etc. In general, a positive integer and one of the valid units of time, separated by a space. The unit of time is used in plural if the integer is greater than 1.
 *
 * The components are separated by a comma and a space (", "). Except the last component, which is separated by " and ", just like it would be written in English.
 *
 * A more significant units of time will occur before than a least significant one. Therefore, 1 second and 1 year is not correct, but 1 year and 1 second is.
 *
 * Different components have different unit of times. So there is not repeated units like in 5 seconds and 1 second.
 *
 * A component will not appear at all if its value happens to be zero. Hence, 1 minute and 0 seconds is not valid, but it should be just 1 minute.
 *
 * A unit of time must be used "as much as possible". It means that the function should not return 61 seconds, but 1 minute and 1 second instead. Formally, the duration specified by of a component must not be greater than any valid more significant unit of time.
 */
public class HumanReadableDurationFormat {


    public static void main(String[] args) {
        System.out.println(formatDuration(0)); // "1 second"
        System.out.println(formatDuration(1)); // "1 second"
        System.out.println(formatDuration(62)); // "1 minute and 2 seconds"
        System.out.println(formatDuration(120)); // "2 minutes"
        System.out.println(formatDuration(3600)); // "1 hour"
        System.out.println(formatDuration(3662)); // "1 hour, 1 minute and 2 seconds"
        System.out.println(formatDuration(86400));// "1 day"
        System.out.println(formatDuration(31_536_000)); // "1 year"
        System.out.println(formatDuration(31_536_001)); // "1 year and 1 second"
    }

    private static String generateDurationType(AtomicInteger seconds, int duration, String durationName) {
        if (seconds.get() >= duration * 2) {
            int count = seconds.get() / duration;
            seconds.set(seconds.get() - (count * duration));
            return count + " " + durationName + "s";
        }
        if (seconds.get() >= duration) {
            seconds.set(seconds.get() - duration);
            return "1 " + durationName;
        }
        return "";
    }

    public static String formatDuration(int seconds) {
        if (seconds == 0) {
            return "0 seconds";
        }
        int oneYear = 31_536_000;
        int oneDay = 86400;
        int oneHour = 3600;
        int oneMinute = 60;

        AtomicInteger mutableInt = new AtomicInteger(seconds);
        List<String> answerList = new ArrayList<>();

        answerList.add(generateDurationType(mutableInt, oneYear, "year"));
        answerList.add(generateDurationType(mutableInt, oneDay, "day"));
        answerList.add(generateDurationType(mutableInt, oneHour, "hour"));
        answerList.add(generateDurationType(mutableInt, oneMinute, "minute"));
        answerList.add(generateDurationType(mutableInt, 1, "second"));

        /*
        if(seconds>=oneYear*2){
            int count=seconds/oneYear;
            seconds=seconds-(count*oneYear);
            answerList.add(count + " year");
        }
        if(seconds>=oneYear){
            seconds=seconds-oneYear;
            answerList.add("1 year");
        }
 /*
        if(seconds>=oneDay*2){
            int count=seconds/oneDay;
            seconds=seconds-(count*oneDay);
            answerList.add(count + " days");
        }
        if(seconds>=oneDay){
            seconds=seconds-oneDay;
            answerList.add("1 day");
        }

        if(seconds>=oneHour*2){
            int count=seconds/oneHour;
            seconds=seconds-(count*oneHour);
            answerList.add(count + " hours");
        }
        if(seconds>=oneHour){
            seconds=seconds-oneHour;
            answerList.add("1 hour");
        }

        if(seconds>=oneMinute * 2){
            int count = seconds/oneMinute;
            seconds=seconds-(count*oneMinute);
            answerList.add(count + " minutes");
        }
        if(seconds>oneMinute /*&& seconds<oneMinute*2){
            seconds=seconds-oneMinute;
            answerList.add("1 minute");
        }
        if(seconds>1){
            answerList.add(seconds + " seconds");
        }else  if(seconds==1){
            answerList.add("1 second");
        }


        */


        answerList = answerList.stream().filter(a -> !"".equals(a)).collect(Collectors.toList());

        if (answerList.size() == 1) return answerList.get(0);
        int last = answerList.size() - 1;
        return String.join(" and ",
                String.join(", ", answerList.subList(0, last)),
                answerList.get(last));

//        return String.join(" ", answerList);
//        for (int i = 1; i <= 100; i++) {
//            String result = "";
//            if (i % 3 == 0) {
//                result = "Fizz";
//            }
//            if (i % 5 == 0) {
//                result += "Buzz";
//            }
//            if (result.isEmpty()) {
//                result += i;
//            }
////            System.out.println(result);
//        }
//        /////////////////////////////////
//        for (int i = 1; i <= 100; i++) {
////            System.out.println(i % 15 != 0
////                    ? i % 5 != 0
////                    ? i % 3 != 0
////                    ? String.valueOf(i)
////                    : "Fizz" : "Buzz" : "FizzBuzz");
//        }
        ///////////////////////////////////////////
    }

}
