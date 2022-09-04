package kulthumassignment3;

/**
 *
 * Edge class represents a vertex from u to v.
 */
public class Edge 
{
    int u;
    int v;

    public Edge(int u, int v)
    {
        this.u = u;
        this.v = v;
    }

    public boolean equals(Object o)
    {
        return u == ((Edge)o).u && v == ((Edge)o).v;
    }

}
