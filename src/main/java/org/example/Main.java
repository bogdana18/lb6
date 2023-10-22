package org.example;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();

        // Ініціалізація масиву
        cinema.initializeSeats();

        // Бронювання місць
        cinema.bookSeats(0, 0, new int[]{1, 2, 3, 4, 5});
        cinema.bookSeats(0, 1, new int[]{1, 2, 3, 4, 5});
        cinema.bookSeats(1, 0, new int[]{1, 2, 3, 4, 5});

        // Перевірка наявності
        System.out.println("Чи доступні 4 місця в залі 0? " + cinema.checkAvailability(0, 4));

        // Друк схеми розміщення місць
        cinema.printSeatingArrangement(0);

        // Бонус
        // Знаходження та бронювання найкращих доступних послідовних місць
        int[] bestSeats = cinema.findBestAvailable(0, 4);
        cinema.bookSeats(0, bestSeats[0], bestSeats);

        // Друк схеми розміщення місць
        cinema.printSeatingArrangement(0);

        // Скасування бронювання
        cinema.cancelBooking(0, 0, new int[]{1, 2, 3, 4, 5});

        // Друк схеми розміщення місць
        cinema.printSeatingArrangement(0);
    }
}