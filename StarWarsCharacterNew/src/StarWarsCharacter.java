public class StarWarsCharacter {

        //variables
        private String firstName;
        private String lastName;
        private int age;
        private String gender;
        private Force force;
        private String tauntPhrase;
        //add some sort of fight style? or lightsaber form??

        public StarWarsCharacter(){
            //Empty
        }

        public StarWarsCharacter(String firstName, String lastName) {
            //Name Constructor
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public StarWarsCharacter(String firstName, String lastName, Force force) {
            // Name + Force Constructor
            this.firstName = firstName;
            this.lastName = lastName;
            this.force = force;
        }

        public StarWarsCharacter(String firstName, String lastName, int age, String gender, Force force, String tauntPhrase) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.gender = gender;
            this.force = force;
            this.tauntPhrase = tauntPhrase;
        }

        //get and set
        public String getFirstName(){
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Force getForce() {
            return this.force;
        }

        public void setForce(int strength, boolean isLightSide, boolean isDarkSide) {
            this.force = new Force(strength, isLightSide, isDarkSide);
        }

        public String getTauntPhrase() {
            return tauntPhrase;
        }

        public void setTauntPhrase(String tauntPhrase) {
            this.tauntPhrase = tauntPhrase;
        }

        //method to use taunts in the main/tester file
        public void taunt(StarWarsCharacter target) {
            System.out.println(this.firstName + " " + this.lastName + " taunts " + target.getFirstName() + " " + target.getLastName() + ": \"" + this.tauntPhrase + "\"");
        }

        //  method to simulate a fight between characters
        public static void fight(StarWarsCharacter char1, StarWarsCharacter char2) {
            System.out.println("Fight: " + char1.getFirstName() + " " + char1.getLastName() + " vs " + char2.getFirstName() + " " + char2.getLastName());
            int strength1 = char1.getForce().getStrength();
            int strength2 = char2.getForce().getStrength();
            String side1 = char1.getForce().isLightSide() ? "Light" : "Dark";
            String side2 = char2.getForce().isLightSide() ? "Light" : "Dark";

            System.out.println(char1.getFirstName() + char1.getLastName() + " (" + side1 + " side, strength " + strength1 + ") faces " +
                    char2.getFirstName() + char2.getLastName() + " (" + side2 + " side, strength " + strength2 + ")");

            //to check who wins
            if (strength1 > strength2) {
                System.out.println(char1.getFirstName() + " " + char1.getLastName() + " wins!");
            } else if (strength2 > strength1) {
                System.out.println(char2.getFirstName() + " " + char2.getLastName() + " wins!");
            } else {
                System.out.println("Both characters are too equally matched, it ends in a draw.");
            }

            char1.taunt(char2);
            char2.taunt(char1);
        }
    }
