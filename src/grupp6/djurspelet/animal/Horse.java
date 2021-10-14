package grupp6.djurspelet.animal;


import grupp6.djurspelet.food.Grass;

public class Horse extends Animal{


        private String name;



        public Horse(String name, int gender) {
            super(name, gender, 15, null, new Grass());

        }
    }



