package homework4;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Kommivoyajer {

    private static Random random = new Random();

    public static double kommyvoyajerAlgorythm(
            Pair[] coordinates, double startTemperature, double endTemperature) {
        int n = coordinates.length;

        double temperature = startTemperature;
        Pair[] thisState = coordinates;
        double thisStateEnergy = energy(coordinates);
        int i = 1;

        while (temperature >= endTemperature) {
            Pair[] candidate = nextStep(thisState, temperature, startTemperature);
            double candidateEnergy = energy(candidate);
            if (candidateEnergy < thisStateEnergy) {
                thisState = candidate;
                thisStateEnergy = candidateEnergy;
            } else {
                double chance = getChance(
                        candidateEnergy - thisStateEnergy, temperature);
                if (Math.random() < chance) {
                    thisState = candidate;
                    thisStateEnergy = candidateEnergy;
                }
            }
            if (i < 9) {
                temperature = startTemperature * (1 / candidate.length);
            } else {
                temperature = startTemperature / i;
            }
            i++;
        }

        return thisStateEnergy;
    }

    private static double getChance(double change, double temperature) {
        return Math.exp(- change / temperature);
    }

    private static Pair[] nextStep(Pair[] coordinates, double temperature, double startTemperature) {
        Pair[] nextStepCoordinates = Arrays.copyOf(coordinates, coordinates.length);
        double ratio = temperature / startTemperature;
        int distance = (int) Math.round(ratio * coordinates.length);
        if (distance < 1) {
             distance = 1;
        }
        int i;
        int j;
        if (distance == coordinates.length) {
            i = 1;
            j = coordinates.length - 1;
        } else {
            i = random.nextInt(coordinates.length - distance + 1) + 1;
            j = i + distance;
        }
        subsequenceInvertor(nextStepCoordinates, i, j);
        return nextStepCoordinates;
    }

    static void subsequenceInvertor(Object[] objects, int i, int j) {
        int n = (j - i) / 2;
        for (int k = 0; k < n; k++) {
            swap(i + k, j - k, objects);
        }
    }

    private static double energy(Pair[] coordinates) {
        double sumOfEnergy = 0;
        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            double x = coordinates[i % n].x - coordinates[(i+1) % n].x;
            double y = coordinates[i % n].y - coordinates[(i+1) % n].y;
            sumOfEnergy += Math.sqrt(x * x + y * y);
        }
        return sumOfEnergy;
    }

    private static void swap(int i1, int i2, Object[] objects) {
        Object object = objects[i1];
        objects[i1] = objects[i2];
        objects[i2] = object;
    }

    static class Pair {
        double x;
        double y;

        public Pair(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Pair[] array1 = {
                new Pair(0, 0),
                new Pair(0, 1),
                new Pair(1, 0),
                new Pair(1, 1)
        };
        Pair[] array2 = {
                new Pair(0, 0),
                new Pair(2, 2),
                new Pair(2, 4),
                new Pair(0, 6),
                new Pair(-2, 4),
                new Pair(-2, 2),
        };
        System.out.println(kommyvoyajerAlgorythm(array1, 1, 0.00001));
        System.out.println(energy(array2) + " " + kommyvoyajerAlgorythm(array2, 5, 0.00001));
    }

}
