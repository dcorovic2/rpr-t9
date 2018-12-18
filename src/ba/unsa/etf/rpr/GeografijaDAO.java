package ba.unsa.etf.rpr;

public class GeografijaDAO {
    private static GeografijaDAO ourInstance = new GeografijaDAO();

    public static GeografijaDAO getInstance() {
        return ourInstance;
    }

    private GeografijaDAO() {
    }
}
