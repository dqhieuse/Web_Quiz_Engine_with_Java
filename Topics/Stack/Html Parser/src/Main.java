import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Deque<AbstractMap.SimpleEntry<Integer, String>> stack = new ArrayDeque<>();

        String tagRegex = "<[^>]+>";
        Pattern pattern = Pattern.compile(tagRegex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find())  {
            if (matcher.group().matches("<([^/][^> ]*)>")) {
                stack.addLast(new AbstractMap.SimpleEntry<>(matcher.start() + matcher.group().length(), matcher.group().substring(1, matcher.group().length() - 1)));
            } else if (matcher.group().matches("</([^>]+)>")) {
                AbstractMap.SimpleEntry<Integer, String> poped = stack.removeLast();
                System.out.println(input.substring(poped.getKey(), matcher.start()));
            }
        }
    }
}
