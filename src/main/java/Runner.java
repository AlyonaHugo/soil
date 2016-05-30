
import java.util.*;

public class Runner {
    private static final int MAX = 1;
    private static final int MAX_N = 1000;
    private static final double STEP = 0.01;
    private static final double STEP_T = 0.1;

    private static double  result = 0, H0 = 0, result2n = 0;
    private static int y = 1, iterration = 1;
    private static Double[] arrayOfC_V = {0.1, 0.1, 0.5};
    private static Integer[] arrayOfQ = {1, 2, 1};
    private static Integer[] arrayOfL = {1, 1, 1};


    public static void main(String[] args) {
        int sizeOfArrays = arrayOfC_V.length;
        List<Double> listC_V = new ArrayList<Double>(Arrays.asList(arrayOfC_V));
        List<Integer> listQ = new ArrayList<Integer>(Arrays.asList(arrayOfQ));
        List<Integer> listL = new ArrayList<Integer>(Arrays.asList(arrayOfL));
        for (int i = 0; i < sizeOfArrays; i++) {
            double c_v = listC_V.get(i);
            int q = listQ.get(i);
            int l = listL.get(i);
            Map<Integer, Object[]> data = calculate(l, q, c_v);
            String resultFileName = "l=" + l + " q=" + q + " c_v=" + c_v;
            WriteToExcel.write(data, resultFileName);
        }

    }

    private static Map<Integer, Object[]> calculate(int l, int q, double c_v) {
        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();


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

    private static double summ(int l, double c_v, double t, double x, int numberOfIteration) {
        double sum = 0;
        for (int i = 1; i < numberOfIteration; i++ ){
            double alfa = i * Math.PI/l;
            sum += (1 + (-1) ^ (i +1 )) * Math.exp(-c_v * t *alfa * alfa) * Math.sin(alfa *x) / alfa;
        }
        return sum;
    }

}

