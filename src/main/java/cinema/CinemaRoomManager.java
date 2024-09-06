package cinema;

import java.util.Scanner;

public class CinemaRoomManager {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // Declare variables.
        int regularTicketPrice = 10; // Ticket price for front row seats.
        int discountedTicketPrice = 8; // Ticket price for back row seats.
        int totalIncome;
        int seatsFront;
        int seatsBack;

        // Print seat arrangement.
        System.out.println("Cinema:");
        System.out.println("  1 2 3 4 5 6 7 8");
        System.out.println("1 S S S S S S S S");
        System.out.println("2 S S S S S S S S");
        System.out.println("3 S S S S S S S S");
        System.out.println("4 S S S S S S S S");
        System.out.println("5 S S S S S S S S");
        System.out.println("6 S S S S S S S S");
        System.out.println("7 S S S S S S S S");

        // Read two positive integer numbers that represent the number of rows and seats in each row.
        System.out.println("Enter the number of rows:");
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = input.nextInt();

        /* Read two integer numbers from the input: a row number and a seat number in that row.
         * These numbers represent the coordinates of the seat according to which the program should print the ticket price.
         */
        int rowNumber = input.nextInt();
        int seatNumber = input.nextInt();

        // Determine if rows are greater than 4 to apply seat discount.
        if (rows <= 4) {
            seatsFront = seats * rows * regularTicketPrice;
            totalIncome = seatsFront;
        } else {
            int frontRows = rows / 2;
            int backRows = (rows / 2) + (rows % 2);

            seatsFront = seats * regularTicketPrice * frontRows;
            seatsBack = seats * discountedTicketPrice * backRows;

            totalIncome = seatsFront + seatsBack;
        }
        // Print total income.
        System.out.println("Total income:\n$" + totalIncome);
    }
}
