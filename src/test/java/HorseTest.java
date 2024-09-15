import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void constructor_NullNameChecking_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Horse(null, 0, 0));
    }

    @Test
    public void constructor_NullNameChecking_ThrowsIllegalArgumentException_GetMassage() {
        assertEquals("Name cannot be null.", assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 0, 0)).getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\n", "\t"})
    public void constructor_EmptyAndIncorrectName_ThrowsIllegalArgumentException_GetMassage(String name ){
        String exceptionMessage = "Name cannot be blank.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 2));
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void constructor_NegativeSpeed_ThrowsIllegalArgumentException_GetMassage() {
        assertEquals("Speed cannot be negative.", assertThrows(IllegalArgumentException.class, () ->
                new Horse("HorseName", -1, 0)).getMessage());
    }

    @Test
    public void constructor_NegativeDistance_ThrowsIllegalArgumentException_GetMassage() {
        assertEquals("Distance cannot be negative.", assertThrows(IllegalArgumentException.class, () ->
                new Horse("HorseName", 0, -1)).getMessage());
    }

    @Test
    void getName_ReturnsActualName() {
        String name = "HorseName";
        Horse horse = new Horse(name, 0,0);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed_ReturnsActualSpeed () {
        double speed = 0;
        Horse horse = new Horse("HorseName", speed,0);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance_ReturnsActualDistance() {
        double distance = 0;
        Horse horse = new Horse("HorseName", 0,distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDistance_ReturnsZeroIfNotPassed() {
        Horse horse = new Horse("HorseName", 0);
        assertEquals(0, horse.getDistance());
    }


    @Test
    void move_CallsMethod_GetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("HorseName", 0, 0);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.5})
    void move_UpdatesDistanceWithGetRandom(double randomValue){
        double speed = 2;
        double distance = 100;

        Horse horse = new Horse("HorseName", speed, distance);
        double expectedDistance = distance + speed * randomValue;

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            horse.move();
        }
            assertEquals(expectedDistance, horse.getDistance());
    }
}