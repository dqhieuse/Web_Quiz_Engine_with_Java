import java.util.*;

class BankCard {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String numbers = scn.nextLine();
        String card = numbers.replaceAll("\\s", "");
        String visaRegex = "^4\\d{15}$";
        String masterCardRegex = "(5[1-5]\\d{14})|(2((221|([01][0-9]))|([3-6][0-9][0-9])|(7(([0-1][0-9])|(20))))\\d{12})";
        String americanExpressRegex = "^(34|37)\\d{13}$";

        if (card.matches(visaRegex)) {
            System.out.println("Visa");
        } else if (card.matches(masterCardRegex)) {
            System.out.println("MasterCard");
        } else if (card.matches(americanExpressRegex)) {
            System.out.println("AmericanExpress");
        } else {
            System.out.println("Card number does not exist");
        }
    }
}