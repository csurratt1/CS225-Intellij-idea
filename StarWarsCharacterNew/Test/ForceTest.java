import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ForceTest {
    //to check the output of the terminal from stackoverflow
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    //3 required set strength tests
    @org.junit.Test
    public void setStrength_LessThanOne_SetsToOne() {
        // Arrange
        Force force = new Force(50, true, false);

        // Act
        force.setStrength(-10);

        // Assert
        assertEquals(1, force.getStrength());
    }

    @org.junit.Test
    public void setStrength_GreaterThanHundred_SetsToHundred() {
        // Arrange
        Force force = new Force(50, true, false);

        // Act
        force.setStrength(150);

        // Assert
        assertEquals(100, force.getStrength());
    }

    @org.junit.Test
    public void setStrength_WithinValidRange_SetsCorrectly() {
        // Arrange
        Force force = new Force(50, true, false);

        // Act
        force.setStrength(75);

        // Assert
        assertEquals(75, force.getStrength());
    }

    //3 required move object tests
    @org.junit.Test
    public void moveObject_LightSideStrengthAboveSixty_MovesObjectWithLightSide() {
        // Arrange
        StarWarsCharacter yoda = new StarWarsCharacter("Yoda", "", 900, "Male", new Force(99, true, false), "When nine hundred years old you reach, look as good you will not.");
        String expectedResult = "Yoda  flings X-wing fighter across the room with the power of the light side!" + System.lineSeparator();

        // Act
        Force.moveObject(yoda, "X-wing fighter");

        // Assert
        assertEquals(expectedResult, outContent.toString());
    }

    @org.junit.Test
    public void moveObject_DarkSideStrengthAboveSixty_MovesObjectWithDarkSide() {
        // Arrange
        StarWarsCharacter darthVader = new StarWarsCharacter("Darth", "Vader", 45, "Male", new Force(100, false, true), "I find your lack of faith disturbing.");
        String expectedResult = "Darth Vader uses the dark side to violently hurl X-wing fighter across the room." + System.lineSeparator();

        // Act
        Force.moveObject(darthVader, "X-wing fighter");

        // Assert
        assertEquals(expectedResult, outContent.toString());
    }

    @org.junit.Test
    public void moveObject_StrengthBelowSixty_FailsToMove() {
        // Arrange
        StarWarsCharacter stormTrooper = new StarWarsCharacter("TK", "421", 30, "Male", new Force(2, false, true), "Move along, move along.");
        String expectedResult = "X-wing fighter is unmoved by TK 421." + System.lineSeparator();

        // Act
        Force.moveObject(stormTrooper, "X-wing fighter");

        // Assert
        assertEquals(expectedResult, outContent.toString());
    }
    //3 required influence method tests
    @org.junit.Test
    public void influence_LightSideStrengthAboveSixty_SuccessfullyInfluences() {
        // Arrange
        StarWarsCharacter quiGonJinn = new StarWarsCharacter("Qui-Gon", "Jinn", 60, "Male", new Force(88, true, false), "Your focus determines your reality.");
        String expectedResult = "Qui-Gon Jinn ensures the Hutts these aren't the droids they're looking for." + System.lineSeparator();

        // Act
        Force.influence(quiGonJinn, "the Hutts");

        // Assert
        assertEquals(expectedResult, outContent.toString());
    }

    @org.junit.Test
    public void influence_StrengthBelowSixty_FailsToInfluence() {
        // Arrange
        StarWarsCharacter stormTrooper = new StarWarsCharacter("TK", "421", 30, "Male", new Force(2, false, true), "Move along, move along.");
        String expectedResult = "TK 421 fails to influence a Jedi Master." + System.lineSeparator();

        // Act
        Force.influence(stormTrooper, "a Jedi Master");

        // Assert
        assertEquals(expectedResult, outContent.toString());
    }
}
