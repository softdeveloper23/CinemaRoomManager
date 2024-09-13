package cinema;

import java.util.Scanner;

public class CinemaRoomManager {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // Declare variables.
        int totalIncome = 0;
        int rows;
        int seats;
        int rowNumber = 0;
        int seatNumber = 0;

        // Read two positive integer numbers that represent the number of rows and seats in each row.
        System.out.println("Enter the number of rows:");
        rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = input.nextInt();
        int item;

        do {
            System.out.println("1. Show the seats\n2. Buy a ticket\n0. Exit");
            item = input.nextInt();

            switch (item) {
                case 1:
                    String[][] boughtSeatsArray = new String[rows][seats];
                    String[][] newBoughtSeatsArray = new String[rows][seats];
                    if (rowNumber == 0 && seatNumber == 0) {
                        printSeats(rows, seats); // Calls a method that prints rows and seats.
                        System.out.println();
                    } else {
                        // Calls a method that prints the rows and seats including the purchased seat(s).
                        newBoughtSeatsArray = ticketsBought(rows, seats, rowNumber, seatNumber, boughtSeatsArray);
                        printSeatsBought(rows, seats, rowNumber, seatNumber, newBoughtSeatsArray);
                    }
                    break;
                /* Read two integer numbers from the input: a row number and a seat number in that row.
                 * These numbers represent the coordinates of the seat according to which the program should print the ticket price.
                 */
                case 2:
                    System.out.println("Enter a row number:");
                    rowNumber = input.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatNumber = input.nextInt();
                    // Calls a method that determines the price of tickets.
                    determineTicketPrice(rowNumber);

                    System.out.println();



                    System.out.println();
                    break;
                case 0:
                    break;
            }
        } while (item != 0);


        // Calls method to determine total income.
        totalIncome = findTotalIncome(rows, seats);

        // Print total income.
        System.out.println("Total income:\n$" + totalIncome);
    }

    // A method that determines the price of tickets.
    public static void determineTicketPrice(int rowNumber) {
        if (rowNumber <= 4) {
            System.out.println("Ticket price: $10");
        } else {
            System.out.println("Ticket price: $8");
        }
    }

    // A method that prints a regular seating arrangement.
    public static void printSeats(int rows, int seats) {
        // Print seat arrangement.
        System.out.println("Cinema:");
        // Print seat numbers.
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        // Print rows
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print("S ");
            }
            System.out.println();
        }
    }

    // A method that determines if a ticket was bought and updates an array
    public static String[][] ticketsBought(int rows, int seats, int rowNumber, int seatNumber, String[][] seatingArray) {
        for (int i = 0; i < seats; i++) {
            for (int j = 0; j < rows; j++) {
                // Determine if seat is bought (B) or not (S).
                if (rowNumber == (i + 1) && seatNumber == (j + 1)) {
                    seatingArray[i][j] = "B";
                } else {
                    seatingArray[i][j] = "S";
                }
            }
        }
        return seatingArray;
    }
    // A method that prints the seating arrangement with purchased seats.
    public static void printSeatsBought(int rows, int seats, int rowNumber, int seatNumber, String[][] newSeatingArray) {
        // Print seat arrangement with bought seats(s).
        System.out.println("Cinema:");
        // Print seat numbers.
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        // Print rows.
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            // Print seats in each row.
            for (int j = 0; j < seats; j++) {
                System.out.print(newSeatingArray[i][j]);
            }
            System.out.println();
        }
    }

    // A method that determines the regular and discounted prices for tickets and figures out the total income.
    public static int findTotalIncome(int rows, int seats) {
        // Determine if rows are greater than 4 to apply seat discount.
        int totalIncome;
        int seatsFront;
        int seatsBack;
        int regularTicketPrice = 10; // Ticket price for front row seats.
        int discountedTicketPrice = 8; // Ticket price for back row seats.

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
        return totalIncome;
    }
}
