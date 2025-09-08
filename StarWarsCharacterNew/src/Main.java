//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{

    public static void main(String[] args) {
        // Create character instances
        StarWarsCharacter darthVader = new StarWarsCharacter("Darth", "Vader", 45, "Male", new Force(100, false, true), "I find your lack of faith disturbing.");
        StarWarsCharacter yoda = new StarWarsCharacter("Yoda", "", 900, "Male", new Force(99, true, false), "When nine hundred years old you reach, look as good you will not.");
        StarWarsCharacter stormTrooper = new StarWarsCharacter("TK", "421", 30, "Male", new Force(2, false, true), "Move along, move along.");
        StarWarsCharacter lukeSkywalker = new StarWarsCharacter("Luke", "Skywalker", 23, "Male", new Force(80, true, false), "I am a Jedi, like my father before me.");
        StarWarsCharacter obiWan = new StarWarsCharacter("Obi-Wan", "Kenobi", 57, "Male", new Force(90, true, false), "The Force will be with you. Always.");
        StarWarsCharacter maceWindu = new StarWarsCharacter("Mace", "Windu", 53, "Male", new Force(95, true, false), "The Force is strong with this one.");
        StarWarsCharacter darthMaul = new StarWarsCharacter("Darth", "Maul", 35, "Male", new Force(85, false, true), "At last we will reveal ourselves to the Jedi.");
        StarWarsCharacter quiGonJinn = new StarWarsCharacter("Qui-Gon", "Jinn", 60, "Male", new Force(88, true, false), "Your focus determines your reality.");
        StarWarsCharacter starKiller = new StarWarsCharacter("Galen", "Marek", 17, "Male", new Force(95, false, true), "A Jedi's hand is at work.");

        System.out.println("\nFIGHT SCENARIOS:");

        // 1. Light vs. Dark (light stronger)
        StarWarsCharacter.fight(yoda, darthMaul);

        // 2. Light vs. Dark (dark stronger)
        StarWarsCharacter.fight(quiGonJinn, darthVader);

        // 3. Light vs. Dark (equal strength)
        StarWarsCharacter.fight(maceWindu, starKiller);

        // 4. Light vs. Light (one stronger)
        StarWarsCharacter.fight(yoda, lukeSkywalker);

        // 5. Light vs. Light (equal strength)
        StarWarsCharacter.fight(obiWan, quiGonJinn);

        // Additional: Dark vs. Dark because it's cool
        StarWarsCharacter.fight(darthVader, darthMaul);

        //J unit tests
        StarWarsCharacter.fight(quiGonJinn, maceWindu);

        System.out.println("\nOTHER SCENARIOS:");

        Force.moveObject(maceWindu, "a Separatist battle droid");

        Force.influence(quiGonJinn, "the Hutts");

        Force.influence(stormTrooper, "a Jedi Master");

        // 4. Create four clones of a Storm Trooper
        System.out.println("\nStorm Trooper Squad Taunts:");
        StarWarsCharacter[] trooperSquad = new StarWarsCharacter[5];
        trooperSquad[0] = stormTrooper;
        for (int i = 1; i < 5; i++) {
            trooperSquad[i] = new StarWarsCharacter("TK", String.format("%03d", 421 + i), 30, "Male", new Force(2, false, true), "");
        }

        // Give troopers taunts
        String[] taunts = {
                "Move along, move along.",
                "These aren't the droids we're looking for.",
                "Set for stun.",
                "Look sir, droids!",
                "Rebel scum!"
        };

        for (int i = 0; i < 5; i++) {
            trooperSquad[i].setTauntPhrase(taunts[i]);
            System.out.println("Trooper " + trooperSquad[i].getLastName() + ": " + trooperSquad[i].getTauntPhrase());
        }
    }
}