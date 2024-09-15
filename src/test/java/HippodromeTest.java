import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    public void constructor_NullListChecking_ThrowsIllegalArgumentException_GetMassage() {
        List<Horse> horses = null;
        assertEquals("Horses cannot be null.", assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses)).getMessage());
    }

    @Test
    public void constructor_EmptyListChecking_ThrowsIllegalArgumentException_GetMassage() {
        List<Horse> horses = new ArrayList<>();
        assertEquals("Horses cannot be empty.", assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses)).getMessage());
    }

    @Test
    void getHorses_ReturnAListOfAllHorsesInOrder() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 25 ; i++) {
            horses.add(new Horse("NameHorse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(25, hippodrome.getHorses().size());
        assertEquals("NameHorse0", hippodrome.getHorses().get(0).getName());
        assertEquals("NameHorse5", hippodrome.getHorses().get(5).getName());
        assertEquals("NameHorse15", hippodrome.getHorses().get(15).getName());
        assertEquals("NameHorse24", hippodrome.getHorses().get(24).getName());
    }

    @Test
    void move_CallsMoveMethodForAllHorses() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse hors : horses) {
            Mockito.verify(hors, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner_ReturnsCorrectWinner() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("NameHorse", 3 , 8),
                new Horse("NameHorse1", 2, 10),
                new Horse("NameHorse2", 1, 15)
        ));

        assertEquals("NameHorse2", hippodrome.getWinner().getName());
    }
}