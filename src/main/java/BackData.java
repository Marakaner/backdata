import login.LoginManager;

public class BackData {

    private static BackData instance;


    /**
     * All Manager
     */
    private LoginManager loginManager;

    public BackData() {

        

    }

    private void init() {

    }

    /**
     * Initializing all Manager
     */
    private void initManager() {
        this.loginManager = new LoginManager();
    }

    public BackData getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        instance = new BackData();
    }

}
