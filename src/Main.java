import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("deprecation")
public class Main {
    static public void main(String[] args) {
        //System.out.println(QuadraticEquationsSolver.solve(1, 3, 1));

        /*
        DiceGame diceGame = new DiceGame(1, 9, 3);
        diceGame.playGame();*/

        Human[] humans = {new Human(
                "Fire",
                "Max",
                new Address("Russia", "Moscow", "Park", "13", "7"),
                new Date(2000, 11, 9)), new Human(
                "Ice",
                "Stacy",
                new Address("Russia", "Moscow", "Park", "13", "7"),
                new Date(2001, 10, 22)), new Human(
                "Wind",
                "John",
                new Address("Russia", "St. Petersburg", "Main", "10", "3"),
                new Date(1993, 5, 2)), new Human(
                "Earth",
                "Jim",
                new Address("USA", "Los Angeles", "Park", "15", "21"),
                new Date(1980, 6, 8))};

        int indexLastName = IntStream.range(0, humans.length)
                .filter(i -> humans[i].lastName.equals("Earth"))
                .findFirst()
                .orElse(-1);

        int indexAddressAttribute = IntStream.range(0, humans.length)
                .filter(i -> humans[i].address.house.equals("10"))
                .findFirst()
                .orElse(-1);

        List<Integer> indexBirthday = IntStream.range(0, humans.length)
                .filter(i -> (humans[i].birthday.after(new Date(1992, 2, 5))
                        && humans[i].birthday.before(new Date(2001, 1, 1))))
                .boxed()
                .collect(Collectors.toList());

        int indexOldest = IntStream.range(0, humans.length)
                .boxed()
                .max(Comparator.comparing(i -> humans[i].birthday))
                .orElse(-1);

        System.out.println(indexLastName);
        System.out.println(indexAddressAttribute);
        System.out.println(indexBirthday);
        System.out.println(indexOldest);

        Set<Address> elements = new HashSet<>();
        List<Integer> equalsStreet = IntStream.range(0, humans.length)
                .filter(i -> !elements.add(new Address(
                        humans[i].address.country,
                        humans[i].address.city,
                        humans[i].address.street,
                        "",
                        "")))
                .boxed()
                .collect(Collectors.toList());

        for (Address e:elements
        ) {
            List<Integer> equalsStreetHumans = IntStream.range(0, humans.length)
                    .filter(i -> (humans[i].address.country == e.country
                            && humans[i].address.city == e.city
                            && humans[i].address.street == e.street))
                    .boxed()
                    .collect(Collectors.toList());
            if (equalsStreetHumans.size() > 1) {
                System.out.println(e.toString() + equalsStreetHumans);
            }
        }
    }
}