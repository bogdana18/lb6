
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CinemaTest {

    @Mock
    private Cinema cinema;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testBookSeats_inHallWithNoSeats() {
        // Створюємо кінотеатр з 10 місцями в залі 0
        when(cinema.getNumberOfSeats(0)).thenReturn(10);

        // Спробуємо забронювати 11 місць в залі 0
        assertThrows(IllegalStateException.class, () -> cinema.bookSeats(0, 0, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}));
    }

    @Test
    void testBookSeats_alreadyBookedSeats() {
        // Створюємо кінотеатр з 10 місцями в залі 0
        when(cinema.getNumberOfSeats(0)).thenReturn(10);

        // Бронуємо 5 місць в залі 0
        cinema.bookSeats(0, 0, new int[]{1, 2, 3, 4, 5});

        // Спробуємо забронювати ці ж місця знову
        assertThrows(IllegalStateException.class, () -> cinema.bookSeats(0, 0, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testCancelBooking() {
        // Створюємо кінотеатр з 10 місцями в залі 0
        when(cinema.getNumberOfSeats(0)).thenReturn(10);

        // Бронуємо 5 місць в залі 0
        cinema.bookSeats(0, 0, new int[]{1, 2, 3, 4, 5});

        // Скасуємо бронювання
        cinema.cancelBooking(0, 0, new int[]{1, 2, 3, 4, 5});

        // Перевіримо, що місця більше не заброньовані
        assertFalse(cinema.isSeatBooked(0, 0, 1));
        assertFalse(cinema.isSeatBooked(0, 0, 2));
        assertFalse(cinema.isSeatBooked(0, 0, 3));
        assertFalse(cinema.isSeatBooked(0, 0, 4));
        assertFalse(cinema.isSeatBooked(0, 0, 5));
    }

    @Test
    void testFindBestAvailable_bestAvailableSeats() {
        // Створюємо кінотеатр з 10 місцями в залі 0
        when(cinema.getNumberOfSeats(0)).thenReturn(10);

        // Бронуємо 5 місць в залі 0
        cinema.bookSeats(0, 0, new int[]{1, 2, 3, 4, 5});

        // Знаходимо найкращі доступні послідовні місця
        int[] bestSeats = cinema.findBestAvailable(0, 5);

        // Перевіримо, що знайдені місця є найкращими
        assertEquals(new int[]{6, 7, 8, 9, 10}, bestSeats);
    }
}