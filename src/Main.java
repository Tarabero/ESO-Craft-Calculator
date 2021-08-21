
import db.DatabaseHelper;
import gui.MainScreen;
import gui.MainScreenPresenter;
import util.DatabaseRepository;

public class Main {

    public static void main(String[] args) {
        MainScreen mainScreenWindow = new MainScreen(new MainScreenPresenter(createDatabaseRepository()));
    }

    private static DatabaseRepository createDatabaseRepository() {
        return new DatabaseRepository(createDatabaseInstanceAndConnect());
    }

    private static DatabaseHelper createDatabaseInstanceAndConnect() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.connect();
        return databaseHelper;
    }

    ;
}
