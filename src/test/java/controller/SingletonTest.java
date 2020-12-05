package controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class to test the Singleton Pattern of the GameController.
 * <p> Since GameController inherits equals from Object, two instances
 * apparently equals should differs when asking for assertEquals. However,
 * if the SingletonPattern is implemented right, every call to getInstance
 * will returns the same, first instance, and the assertEquals will pass. </p>
 */
public class SingletonTest {
    @Test
    void uniqueInstanceTest(){
        GameController A = GameController.getInstance();
        GameController B = GameController.getInstance();
        assertNotNull(A);
        assertEquals(A, B);
    }
}
