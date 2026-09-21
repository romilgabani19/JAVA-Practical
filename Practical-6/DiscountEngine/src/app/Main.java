package app;

import discount.DiscountRule;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Double> prices = Arrays.asList(
            500.0,
            1000.0,
            1500.0,
            2000.0
        );

        System.out.println("===== Discount Engine =====");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat Rs. 100 Discount");

        System.out.print("Choose discount rule: ");
        int choice = sc.nextInt();

        DiscountRule rule;

        switch (choice) {

            case 1:
                rule = price -> price * 0.90;
                break;

            case 2:
                rule = price -> price * 0.80;
                break;

            case 3:
                rule = price -> Math.max(0, price - 100);
                break;

            default:
                System.out.println("Invalid choice");
                sc.close();
                return;
        }

        System.out.println("\nOriginal Prices:");

        for (double price : prices) {
            System.out.println("Rs. " + price);
        }

        System.out.println("\nPrices After Discount:");

        for (double price : prices) {
            double finalPrice = rule.apply(price);
            System.out.println("Rs. " + finalPrice);
        }

        sc.close();
    }
}