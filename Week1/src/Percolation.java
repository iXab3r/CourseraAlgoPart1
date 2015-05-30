public class Percolation {
    private int m_elementsCount;


    private boolean[] m_sites;
    private WeightedQuickUnionUF m_uf;

    // just to avoid confusion with real grid, both UF are pointing to the same object
    private WeightedQuickUnionUF m_virtualUf;

    private int m_virtualTopIndex;
    private int m_fullnessVirtualGridVirtualTopOffset;

    private int m_virtualBottomIndex;

    private int m_virtualGridOffset;

    public Percolation(int N) {

        if (N <= 0)
        {
            throw new java.lang.IllegalArgumentException();
        }

        m_elementsCount = N;
        int m_ufLength = m_elementsCount * m_elementsCount;
        m_virtualGridOffset = m_ufLength;
        m_fullnessVirtualGridVirtualTopOffset = m_ufLength;
        m_ufLength += m_ufLength; // virtual grid
        m_ufLength += 1; // virtual top for virtual grid x_X
        m_ufLength += 2; // virtual top+bottom

        m_virtualUf = m_uf = new WeightedQuickUnionUF(m_ufLength);

        m_sites = new boolean[m_elementsCount*m_elementsCount];

        m_virtualTopIndex = m_ufLength - 2; // last two points in UF are virtual
        m_virtualBottomIndex = m_virtualTopIndex+1;


        int lastRowFirstIndex = N*(N-1);
        for (int i = 0; i < N; i++)
        {
            m_uf.union(m_virtualTopIndex,i);
            m_virtualUf.union(m_virtualGridOffset+ m_fullnessVirtualGridVirtualTopOffset,m_virtualGridOffset+i);

            m_uf.union(m_virtualBottomIndex,lastRowFirstIndex + i);
        }
    }// create N-by-N grid, with all sites blocked

    public static void main(String[] args)   // test client, optional
    {

    }

    public void open(int i, int j)           // open site (row i, column j) if it is not already
    {
        int ijSite = RowColToId(i, j);
        if (m_sites[ijSite])
        {
            return;
        }
        m_sites[ijSite] = true;
        if (j-1 >= 1) {
            int leftSite = RowColToId(i, j - 1);
            if (m_sites[leftSite]) {
                m_uf.union(ijSite, leftSite);
                m_virtualUf.union(m_virtualGridOffset+ijSite, m_virtualGridOffset+leftSite);
            }
        }
        if (j+1 <= m_elementsCount) {
            int rightSite = RowColToId(i, j + 1);
            if (m_sites[rightSite]) {
                m_uf.union(ijSite, rightSite);
                m_virtualUf.union(m_virtualGridOffset+ijSite, m_virtualGridOffset+rightSite);

            }
        }

        if (i-1 >= 1) {
            int topSite = RowColToId(i - 1, j);
            if (m_sites[topSite]) {
                m_uf.union(ijSite, topSite);
                m_virtualUf.union(m_virtualGridOffset+ijSite, m_virtualGridOffset+topSite);
            }
        }
        if (i+1 <= m_elementsCount) {
            int bottomSite = RowColToId(i + 1, j);
            if (m_sites[bottomSite]) {
                m_uf.union(ijSite, bottomSite);
                m_virtualUf.union(m_virtualGridOffset+ijSite, m_virtualGridOffset+bottomSite);
            }
        }

    }

    public boolean isOpen(int i, int j)      // is site (row i, column j) open?
    {
        int ijSite = RowColToId(i, j);
        return m_sites[ijSite];
    }

    public boolean isFull(int i, int j)      // is site (row i, column j) full?
    {
        int ijSite = RowColToId(i, j);

        return isOpen(i,j) && m_virtualUf.connected(m_virtualGridOffset+ m_fullnessVirtualGridVirtualTopOffset, m_virtualGridOffset+ijSite);
    }

    public boolean percolates()              // does the system percolate?
    {
        boolean cornerCaseCheck = true;
        if (m_elementsCount == 1)
        {
            cornerCaseCheck = m_sites[0];
        }
        return cornerCaseCheck && m_uf.connected(m_virtualTopIndex,m_virtualBottomIndex);
    }

    private void check(int i)
    {
        if (i < 1 || i > m_elementsCount)
        {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private int RowColToId(int _rowIndex, int _columnIndex){
        check(_rowIndex);
        check(_columnIndex);
        return (_rowIndex-1) * m_elementsCount + _columnIndex-1;
    }

}

