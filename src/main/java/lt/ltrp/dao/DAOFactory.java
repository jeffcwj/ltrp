package lt.ltrp.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lt.ltrp.LtrpGamemode;
import lt.ltrp.dao.impl.*;
import lt.ltrp.item.SqlItemDao;
import net.gtaun.shoebill.Shoebill;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Bebras
 *         2015.11.12.
 */
public abstract class DAOFactory {


    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String database = "ltrp-java";
    private static final String url = "jdbc:mysql://localhost:3306/" + database;
    //private static final String url = "jdbc:jdbcdslog:jdbc:mysql://localhost:3306/" + database + ";targetDriver=" + driver;
    //jdbc:jdbcdslog:<original URL>;targetDriver=<original JDBC driver full class name>

    public static DAOFactory getInstance() {
        DAOFactory instance = null;

        Properties p = new Properties(System.getProperties());
        //p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.slf4j.Slf4jMLog");
        //p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "ALL"); // Off or any other level
        System.setProperties(p);

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(url);
        cpds.setUser(user);
        cpds.setPassword(password);

        cpds.setMinPoolSize(1);
        cpds.setMaxPoolSize(6);

        try {
            instance = new JdbcDAO(cpds);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instance;
    }

    public abstract Connection getConnection() throws SQLException;

    public abstract PlayerDao getPlayerDao();
    public abstract PhoneDao getPhoneDao();
    public abstract ItemDao getItemDao();
    public abstract HouseDao getHouseDao();
    public abstract JobDao getJobDao();
    public abstract DmvDao getDmvDao();
    public abstract VehicleDao getVehicleDao();
}

class JdbcDAO extends DAOFactory {

    private ComboPooledDataSource ds;
    private PlayerDao playerDao;
    private PhoneDao phoneDao;
    private ItemDao itemDao;
    private HouseDao houseDao;
    private JobDao jobDao;
    private DmvDao dmvDao;
    private VehicleDao vehicleDao;

    public JdbcDAO(ComboPooledDataSource ds) throws IOException, SQLException {
        this.ds = ds;
        /*BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("database.sql")));
        String line;
        StringBuilder builder = new StringBuilder();
        while((line = in.readLine()) != null) {
            if(line.contains(";")) {
                builder.append(line.substring(0, line.indexOf(";")));
                Statement statement = null;
                Connection con = null;
                try {
                    con = ds.getConnection();
                    statement = con.createStatement();
                    statement.execute(builder.toString());
                } catch(SQLException e) {
                    String error = "Query failed: " + builder.toString() + " Reason:" + e.getMessage() + ".";
                    if(statement != null)
                        error += statement.getWarnings();
                    Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING, error);
                    e.printStackTrace();
                } finally {
                    if(con != null) {
                        con.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                }
                builder = new StringBuilder();
                builder.append(line.substring(line.indexOf(";")+1));
            }
            else {
                builder.append(line);
            }
        }

        System.out.println("File read");
        in.close();
        */
        this.playerDao = new SqlPlayerDaoImpl(ds);
        this.phoneDao = new SqlPhoneDaoImpl(ds);
        this.itemDao = new SqlItemDao(ds);
        this.houseDao = new SqlHouseDao(ds);
        this.jobDao = new FileJobDaoImpl(Shoebill.get().getResourceManager().getGamemode().getDataDir());
        this.dmvDao = new FileDmvDaoImpl(LtrpGamemode.get().getDataDir());
        this.vehicleDao = new SqlVehicleDaoImpl(ds);
        System.out.println("JDBCDAO initialized");
    }


    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    @Override
    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    @Override
    public PhoneDao getPhoneDao() {
        return phoneDao;
    }

    @Override
    public ItemDao getItemDao() {
        return itemDao;
    }

    @Override
    public HouseDao getHouseDao() {
        return houseDao;
    }

    @Override
    public JobDao getJobDao() {
        return jobDao;
    }

    @Override
    public DmvDao getDmvDao() {
        return dmvDao;
    }

    @Override
    public VehicleDao getVehicleDao() {
        return vehicleDao;
    }

}
