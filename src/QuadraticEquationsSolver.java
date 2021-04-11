import java.util.ArrayList;
import java.util.List;

//После компиляции структура классов логически не изменилась, только конструкторы по умолчанию реализовались в явном виде
public class QuadraticEquationsSolver {

    //Считаю использования вложенного класса нерациональным в данном решении, так как используется только один раз
    static class DiscriminantSolver {
        static double solve(double a, double b, double c) {
            return b*b - 4*a*c;
        }
    }

    static List<Double> solve(double a, double b, double c) {
        List<Double> result = new ArrayList<>();
        double D = DiscriminantSolver.solve(a, b, c);
        if (D == 0) {
            result.add(-b/(2*a));
        } else if (D > 0) {
            result.add((-b + Math.sqrt(D))/(2*a));
            result.add((-b - Math.sqrt(D))/(2*a));
        }
        return result;
    }
}
