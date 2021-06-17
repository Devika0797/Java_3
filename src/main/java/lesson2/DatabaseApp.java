package lesson2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import org.apache.derby.jdbc.EmbeddedDriver;

public class DatabaseApp {

    static final String DATABASE_URL = "jdbc:sqlite:javadb.db";
    static Connection connection;
    static Statement statement;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DATABASE_URL);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        DatabaseApp databaseApp = new DatabaseApp();
        databaseApp.createTable();
        databaseApp.insertNewBike("bike1", "no1");
        databaseApp.insertNewBikePS("model", "serial");
        System.out.println(databaseApp.searchForBike());
        databaseApp.dropTable();

    }

    public void createTable() throws SQLException {
        String createTable = "create table bike (" +
                "id integer not null primary key, " +
                "model varchar(30) not null, " +
                "serial_no varchar(10))";
        statement.execute(createTable);
    }

    // инсерт с явной передачей параметра. каждый раз по новой нужно вводить
    public void insertNewBike(String name, String serial) throws SQLException {
        String insertSql = "insert into bike (model, serial_no) values ('" + name + "', '" + serial + "')";
        statement.execute(insertSql);
    }

    // prepared statement - это шаблон,который позволяет использовать один и тот же prepared statement, подставляя параметрв
    public void insertNewBikePS(String model, String serial) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("insert into bike(model, serial_no) values (?,?)")){
            for (int i = 1; i < 11; i++) {
                preparedStatement.setString(1, model + " " + 1);
                preparedStatement.setString(2, serial + " " + (i + 10));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch(); // Batch запрос - можем вставлять сразу "пачку" значений

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    // метод поиска велосипеда
    public List <Bike> searchForBike() throws SQLException {
        String sql = "select * from bike";
        ResultSet resultSet =  statement.executeQuery(sql);//возвращает результат запроса
        List<Bike> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Bike(
                    resultSet.getInt("id"),
                    resultSet.getString("model"),
                    resultSet.getString("serial_no")
                    )
            );
            System.out.println(resultSet.getInt("id") + " " +
                    resultSet.getString("model") + " " +
                    resultSet.getString(3));
        }
        return  list;

    }

    public void dropTable() throws SQLException {
        String dropSql = "drop table bike";
        statement.execute(dropSql);

    }

}
