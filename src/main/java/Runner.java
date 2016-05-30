
import java.util.Map;
import java.util.TreeMap;

public class Runner {
    private static int MAX = 1;
    private static int MAX_N = 1000;
    private static double STEP = 0.01;
    private static double STEP_T = 0.1;
    public static void main(String[] args) {


        //This data needs to be written (Object[])
        Map<Integer, Object[]> data = calculate();
        WriteToExcel.write(data);

    }

    private static Map<Integer, Object[]> calculate() {
        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();

        double  result = 0, H0 = 0, result2n = 0;
        int l = 1, q = 1, c_v = 1, y = 1, n = 1, iterration = 1;

        for (double t = 0.1; t <= MAX; t+= STEP_T){
            data.put(iterration, new Object[]{"ID", "result", "t=" + t});
            iterration++;
            for (double x = 0; x <= MAX; x += STEP) {
                H0 = q / y;
                result = 2 * H0 / l * summ(l, c_v, t, x, MAX_N);
                result2n = 2 * H0 / l * summ(l, c_v, t, x, MAX_N / 2);
                data.put(iterration, new Object[]{x, result, result2n});
                iterration++;
            }
         }

        return data;
    }

    private static double summ(int l, int c_v, double t, double x, int numberOfIteration) {
        double sum = 0;
        for (int i = 1; i < numberOfIteration; i++ ){
            double alfa = i * Math.PI/l;
            sum += (1 + (-1) ^ (i +1 )) * Math.exp(-c_v * t *alfa * alfa) * Math.sin(alfa *x) / alfa;
        }
        return sum;
    }

}

