import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 13.09.2014.
 */
public class PercolationStats {
    private double[] m_results;

    public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
    {
        if (N < 1 || T < 1)
        {
            throw new java.lang.IllegalArgumentException();
        }
        int sitesCount = N*N;
        m_results = new double[T];
        for (int testIndex = 0; testIndex < T; testIndex++)
        {
            //Stopwatch sw = new Stopwatch();

            int openedSitesCount = 0;
            Percolation model = new Percolation(N);
            while (!model.percolates())
            {
                int randomRow = StdRandom.uniform(1, N+1);
                int randomCol = StdRandom.uniform(1, N+1);

                if (model.isOpen(randomRow, randomCol))
                {
                    continue;
                }
                model.open(randomRow, randomCol);
                openedSitesCount++;

                //PercolationVisualizer.draw(model, N);
                //StdDraw.show(100);
            }

            double threshold = (double)openedSitesCount / sitesCount;
            m_results[testIndex] = threshold;
            //StdOut.printf("Iteration #%d has taken %fs, threshold %f\r\n", testIndex, sw.elapsedTime(),threshold);
        }
    }
    public double mean()                     // sample mean of percolation threshold
    {
        return StdStats.mean(m_results);
    }
    public double stddev()                   // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(m_results);
    }
    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    {
       return mean() - 1.96 * (stddev() / Math.sqrt(m_results.length));
    }
    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        return mean() + 1.96 * (stddev() / Math.sqrt(m_results.length));
    }

    public static void main(String[] args)   // test client, described below
    {
        if (args == null)
        {
            throw new IllegalArgumentException();
        }

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N,T);
        StdOut.printf("mean = %f\r\n", stats.mean());
        StdOut.printf("stddev = %f\r\n", stats.stddev());
        StdOut.printf("95 confidence interval = %f, %f\r\n", stats.confidenceLo(),stats.confidenceHi() );

    }
}
