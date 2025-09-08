//worked on force class with caiden after class, probably will look really similar to his.
    public class Force {
        //variables
        private int strength;
        private boolean isLightSide;
        private boolean isDarkSide;

        public Force(int strength, boolean isLightSide, boolean isDarkSide) {
            this.strength = strength;
            this.isLightSide = isLightSide;
            this.isDarkSide = isDarkSide;
        }

        // Getters and Setters
        public int getStrength() {
            return strength;
        }

    public void setStrength(int strength) {
        if (strength < 1) {
            this.strength = 1;
        } else if (strength > 100) {
            this.strength = 100;
        } else {
            this.strength = strength;
        }
    }
        public boolean isLightSide() {
            return isLightSide;
        }

        public void setLightSide(boolean isLightSide) {
            this.isLightSide = isLightSide;
            this.isDarkSide = !isLightSide; // Ensure dark side is set correctly just in case
        }

        public boolean isDarkSide() {
            return isDarkSide;
        }

        public void setDarkSide(boolean isDarkSide) {
            this.isDarkSide = isDarkSide;
            this.isLightSide = !isDarkSide; // Ensure light side is set correctly just in case
        }

        // Methods (Used Ai here to get the outline, made some changes afterwards
        public static void influence(StarWarsCharacter character, String target) {
            // Influence (static method)
            if (character.getForce().getStrength() >= 60) {
                if (character.getForce().isLightSide()) {
                    System.out.println(character.getFirstName() + " " + character.getLastName() + " ensures " + target + " these aren't the droids they're looking for.");
                } else {
                    System.out.println(character.getFirstName() + " " + character.getLastName() + " uses the dark side to manipulate " + target + ".");
                }
            } else {
                System.out.println(character.getFirstName() + " " + character.getLastName() + " fails to influence " + target + ".");
            }
        }
        //used ai for this section as well
        public static void moveObject(StarWarsCharacter character, String target) {
            // Move Object (static method)
            if (character.getForce().getStrength() >= 60) {
                if (character.getForce().isLightSide()) {
                    System.out.println(character.getFirstName() + " " + character.getLastName() + " flings " + target + " across the room with the power of the light side!");
                } else {
                    System.out.println(character.getFirstName() + " " + character.getLastName() + " uses the dark side to violently hurl " + target + " across the room.");
                }
            } else {
                System.out.println(target + " is unmoved by " + character.getFirstName() + " " + character.getLastName() + ".");
            }
        }

        public Force clone() {
            // Clone method
            return new Force(this.strength, this.isLightSide, this.isDarkSide);
        }


    }
