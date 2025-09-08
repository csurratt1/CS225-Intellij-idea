import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
public class StarWarsCharacterTest {
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

    @org.junit.Test
    //3 required fight tests
    public void fight_LightFightsLightOneStronger_StrongerWins() {
        //arrange
        StarWarsCharacter quiGonJinn = new StarWarsCharacter("Qui-Gon", "Jinn", 60, "Male", new Force(88, true, false), "Your focus determines your reality.");
        StarWarsCharacter maceWindu = new StarWarsCharacter("Mace", "Windu", 53, "Male", new Force(95, true, false), "The Force is strong with this one.");
        String expectedResult = "Fight: Qui-Gon Jinn vs Mace Windu" + System.lineSeparator() + "Qui-GonJinn (Light side, strength 88) faces MaceWindu (Light side, strength 95)" + System.lineSeparator() + "Mace Windu wins!" + System.lineSeparator() + "Qui-Gon Jinn taunts Mace Windu: \"Your focus determines your reality.\"" + System.lineSeparator() + "Mace Windu taunts Qui-Gon Jinn: \"The Force is strong with this one.\"" + System.lineSeparator();

        //act
        StarWarsCharacter.fight(quiGonJinn, maceWindu);
        //assert
        assertEquals(expectedResult, outContent.toString());
    }

    @org.junit.Test
    public void fight_LightFightsDarkOneStronger_StrongerWins() {
        //arrange
        StarWarsCharacter darthVader = new StarWarsCharacter("Darth", "Vader", 45, "Male", new Force(100, false, true), "I find your lack of faith disturbing.");
        StarWarsCharacter quiGonJinn = new StarWarsCharacter("Qui-Gon", "Jinn", 60, "Male", new Force(88, true, false), "Your focus determines your reality.");
        String expectedResult = "Fight: Qui-Gon Jinn vs Darth Vader" + System.lineSeparator() + "Qui-GonJinn (Light side, strength 88) faces DarthVader (Dark side, strength 100)" + System.lineSeparator() + "Darth Vader wins!" + System.lineSeparator() + "Qui-Gon Jinn taunts Darth Vader: \"Your focus determines your reality.\"" + System.lineSeparator() + "Darth Vader taunts Qui-Gon Jinn: \"I find your lack of faith disturbing.\"" + System.lineSeparator();

        //act
        StarWarsCharacter.fight(quiGonJinn, darthVader);
        //assert
        assertEquals(expectedResult, outContent.toString());
    }

    @org.junit.Test
    public void fight_DarkFightsDarkOneStronger_StrongerWins() {
        //arrange
        StarWarsCharacter darthVader = new StarWarsCharacter("Darth", "Vader", 45, "Male", new Force(100, false, true), "I find your lack of faith disturbing.");
        StarWarsCharacter darthMaul = new StarWarsCharacter("Darth", "Maul", 35, "Male", new Force(85, false, true), "At last we will reveal ourselves to the Jedi.");
        String expectedResult = "Fight: Darth Vader vs Darth Maul" + System.lineSeparator() + "DarthVader (Dark side, strength 100) faces DarthMaul (Dark side, strength 85)" + System.lineSeparator() + "Darth Vader wins!" + System.lineSeparator() + "Darth Vader taunts Darth Maul: \"I find your lack of faith disturbing.\"" + System.lineSeparator() + "Darth Maul taunts Darth Vader: \"At last we will reveal ourselves to the Jedi.\"" + System.lineSeparator();
        //act
        StarWarsCharacter.fight(darthVader, darthMaul);
        //assert
        assertEquals(expectedResult, outContent.toString());
    }
    @org.junit.Test
    //1 required taunt test
    public void taunt_LightSideTauntsLightSide_CorrectTauntDisplayed() {
        //arrange
        StarWarsCharacter yoda = new StarWarsCharacter("Yoda", "", 900, "Male", new Force(99, true, false), "When nine hundred years old you reach, look as good you will not.");
        StarWarsCharacter lukeSkywalker = new StarWarsCharacter("Luke", "Skywalker", 23, "Male", new Force(80, true, false), "I am a Jedi, like my father before me.");
        String expectedResult = "Yoda  taunts Luke Skywalker: \"When nine hundred years old you reach, look as good you will not.\"" + System.lineSeparator();

        //act
        yoda.taunt(lukeSkywalker);

        //assert
        assertEquals(expectedResult, outContent.toString());
    }

}