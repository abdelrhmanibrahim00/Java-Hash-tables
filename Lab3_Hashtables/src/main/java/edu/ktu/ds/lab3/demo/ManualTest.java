package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.*;

import java.util.List;
import java.util.Locale;

import static edu.ktu.ds.lab3.utils.HashMap.DEFAULT_INITIAL_CAPACITY;
import static edu.ktu.ds.lab3.utils.HashMap.DEFAULT_LOAD_FACTOR;

public class ManualTest {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // we standardize number formats
        executeTest();
    }

    public static void executeTest() {
        Car car1 = new Car("Renault", "Laguna", 1997, 50000, 1700);
        Car car2 = new Car("Renault", "Megane", 2001, 20000, 3500);
        Car car3 = new Car("Toyota", "Corolla", 2001, 20000, 8500.8);
        Car car4 = new Car("Renault Laguna 2001 115900 7500");
        Car car5 = new Car.Builder().buildRandom();
        Car car6 = new Car("Honda   Civic  2007  36400 8500.3");
        Car car7 = new Car("Renault Laguna 2001 115900 7500");

        // view key array
        String[] carsIds = {"TA156", "TA102", "TA178", "TA126", "TA105", "TA106", "TA107", "TA108"};
        // An array of view values
        Car[] cars = {car1, car2, car3, car3, car3, car3, car7};

        executeCarMapTests(carsIds, cars); // separate chain
        executeCarMapOaTests(carsIds, cars); //open addressing
        executeSpeedtest(carsIds, cars);

    }

    private static void executeCarMapTests(String[] carsIds, Car[] cars) {
      // To merge values
        ParsableMap<String, Car> carsMap = new ParsableHashMap<>(String::new, Car::new, DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, HashManager.HashType.DIVISION);

        for (int id = 0; id < cars.length; id++) {
            carsMap.put(carsIds[id], cars[id]);
        }
        //Car car1 = new Car("Renault", "Megane", 2001, 20000, 3500);
        Car car2 = new Car("Renault", "lllll", 2000, 50000, 1700);
        Ks.oun("Arrangement of pairs in the view by keys:");
        carsMap.println("");
        /*Ks.oun("Is there a pair in the picture?");
        Ks.oun(carsMap.contains(carsIds[6]));
        Ks.oun(carsMap.contains(carsIds[7]));
        Ks.oun("Arrangement of pairs in the view by keys. Only keys are shown:");
        carsMap.println("=");

        Ks.oun("\n" +
                "We are searching for pairs in the view:");
        Ks.oun(carsMap.get(carsIds[2]));
        Ks.oun(carsMap.get(carsIds[7]));
        Ks.oun("We print the view pairs in String:");
        Ks.ounn(carsMap);
        carsMap.remove("TA107");
        Ks.ounn(carsMap);
        Ks.ounn("Replace Method");
        Ks.ounn(carsMap.replace("TA178", cars[2], car2));
        Ks.ounn(carsMap);
        Ks.ounn("ContainsValue Method");
        Ks.ounn(carsMap.containsValue(cars[1]));*/


        //////////////VALUES////////////////////////
        Ks.ounn("-------------------------------------");
        Ks.ounn("Values Method");
        List<Car> carsMap1 = carsMap.Values();
        for(Car c: carsMap1)
        {
            System.out.println(c.toString());
        }
        Ks.ounn("-------------------------------------");
    }

    private static void executeCarMapOaTests(String[] carsIds, Car[] cars) {
        ParsableMap<String, Car> carsMapOa
                = new ParsableHashMapOa<>(String::new, Car::new, DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, HashManager.HashType.DIVISION, HashMapOa.OpenAddressingType.LINEAR);

        for (int id = 0; id < cars.length; id++) {
            carsMapOa.put(carsIds[id], cars[id]);
        }
        Car car2 = new Car("Renault", "Fluence", 2022, 50000, 1700);


        Ks.oun("Location of pairs in the open address view by keys:");
        carsMapOa.println("");
       /* Ks.oun("Is there a pair in the open address view?");
        Ks.oun(carsMapOa.contains(carsIds[6]));
        Ks.oun(carsMapOa.contains(carsIds[7]));
        Ks.oun("Arrangement of pairs in an open addressing view by keys. Only keys are shown:");
        carsMapOa.println("=");
        Ks.oun("We are searching for pairs in the open address view:");
        Ks.oun(carsMapOa.get(carsIds[2]));
        Ks.oun(carsMapOa.get(carsIds[7]));
        Ks.oun("We print the pairs of the open address view in the String line:");
        Ks.ounn(carsMapOa);
        Ks.ounn("");
        Ks.ounn("Remove Method");
        carsMapOa.remove("TA107");
        Ks.ounn(carsMapOa);
        Ks.ounn("Replace Method");
        Ks.ounn(carsMapOa.replace("TA178", cars[0], car2));
        Ks.ounn(carsMapOa);
        Ks.ounn("Contains Method");
        Ks.ounn(carsMapOa.containsValue(cars[1]));*/
        Ks.ounn("-------------------------------------");
        Ks.ounn("Replace All");
        carsMapOa.replaceALL(cars[2],car2);
        Ks.ounn(carsMapOa);
        Ks.ounn("-------------------------------------");
    }

    private static void executeSpeedtest(String[] carsIds, Car[] cars) {
        ParsableMap<String, Car> carsMap
                = new ParsableHashMap<>(String::new, Car::new, DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, HashManager.HashType.DIVISION);
        ParsableMap<String, Car> carsMapOa
                = new ParsableHashMap<>(String::new, Car::new, DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, HashManager.HashType.DIVISION);

        for (int id = 0; id < cars.length; id++) {
            carsMap.put(carsIds[id], cars[id]);
            carsMapOa.put(carsIds[id], cars[id]);
        }
        Ks.oun("..................Speed Test Separate Chaining REMOVE..................");
        long start,elapsed;
        start = System.nanoTime();
        carsMap.remove("TA107");
        elapsed =System.nanoTime()-start;
        Ks.oun(elapsed);
        long start2,elapsed2;
        Ks.oun("..................Speed Test OA REMOVE..................");
        start2 = System.nanoTime();
        carsMapOa.remove("TA107");
        elapsed2 =System.nanoTime()-start2;
        Ks.oun("BST time: "+elapsed2);
    }

}
