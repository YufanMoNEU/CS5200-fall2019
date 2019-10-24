package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class DeveloperDao implements DeveloperImpl {

    private static DeveloperDao instance = null;

    private DeveloperDao() {

    }

    public static DeveloperDao getInstance() {
        if (instance == null) {
            instance = new DeveloperDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;

    //private final String INSERT_PERSON = "INSERT INTO `Person` (`id`, `username`, `password`, `firstName`, `lastName`, `email`, `dob`) VALUES (?, ?, ?, ?, ?, ?, ?)";
//    private final String INSERT_PHONE = "INSERT INTO `Phone` (`phone`,`primary`,`personId`) VALUES (?, ?, ?)";
//    private final String DELETE_PHONE = "DELETE FROM Phone WHERE personId=?";
//    private final String INSERT_ADDRESS = "INSERT INTO `Address` (`street1`,`street2`,`city`,`state`,`zip`,`primary`,`personId`) VALUES (?, ?, ?, ?, ?, ?, ?)";
//    private final String DELETE_ADDRESS = "DELETE FROM Address WHERE personId=?";
//    private final String INSERT_DEVELOPER = "INSERT INTO `Developer` (`personId`, `developerKey`) VALUES (?, ?)";
//    private final String FIND_PERSON_BY_ID = "SELECT * FROM `Person` WHERE id=?";
//    private final String FIND_ALL_PHONE_BY_PERSON_ID = "SELECT * FROM `Phone` WHERE personId=?";
//    private final String FIND_ALL_ADDRESS_BY_PERSON_ID = "SELECT * FROM `Address` WHERE personId=?";
//    private final String FIND_ALL_DEVELOPERS = "SELECT * FROM `Developer`";
//    private final String FIND_DEVELOPER_BY_ID = "SELECT * FROM `Developer` WHERE personId=?";
//    private final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM Person p JOIN Developer d ON p.id = d.personId WHERE username=?";
//    private final String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM Person p JOIN Developer d ON p.id = d.personId WHERE username=? AND password=?";
//    private final String UPDATE_PERSON = "UPDATE Person SET firstName=?, lastName=?, username=?, password=?, email=?, dob=? WHERE id=?";
//    private final String UPDATE_DEVELOPER = "UPDATE Developer SET developerKey=? WHERE personId=?";
//    private final String DELETE_PERSON = "DELETE FROM Person WHERE id=?";
//    private final String DELETE_DEVELOPER = "DELETE FROM Developer WHERE personId=?";

    public void createDeveloper(Developer developer) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            //whenever we create a developer, we have to create a person in person class
            //create a new person first
            pStatement = connection.prepareStatement("INSERT INTO `Person` (`id`, `username`, `password`, `firstName`, `lastName`, `email`, `dob`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pStatement.setInt(1, developer.getId());
            pStatement.setString(2, developer.getUsername());
            pStatement.setString(3, developer.getPassword());
            pStatement.setString(4, developer.getFirstName());
            pStatement.setString(5, developer.getLastName());
            pStatement.setString(6, developer.getEmail());
            Date dob = developer.getDob();
            java.sql.Date dobSql;
            if (dob != null) {
                dobSql = new java.sql.Date(dob.getTime());
            } else dobSql = null;
            pStatement.setDate(7, dobSql);
            pStatement.executeUpdate();
            //Phone
            Collection<Phone> phones = developer.getPhones();
            insertPhone(developer, phones);
            //Address
            Collection<Address> addresses =  developer.getAddresses();//from collection
            insertAddress(developer, addresses);
            //Developer
            pStatement = connection.prepareStatement("INSERT INTO `Developer` (`personId`, `developerKey`) VALUES (?,?)");
            pStatement.setInt(1, developer.getId());
            pStatement.setString(2, developer.getDeveloperKey());
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Collection<Developer> findAllDevelopers() {
        Collection<Developer> developers = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            statement = connection.createStatement();
            //Developer is extended from person (inheritance)
            //find all developers
            ResultSet developerResults = statement.executeQuery("SELECT * FROM `Developer`");
            while (developerResults.next()) {
                int id = developerResults.getInt("personId");
                String developerKey = developerResults.getString("developerKey");
                //Person
                pStatement = connection.prepareStatement("SELECT * FROM `Person` WHERE id=?");
                pStatement.setInt(1, id);
                ResultSet personResults = pStatement.executeQuery();
                if (personResults.next()) {
                    String firstName = personResults.getString("firstName");
                    String lastName = personResults.getString("lastName");
                    String username = personResults.getString("username");
                    String password = personResults.getString("password");
                    String email = personResults.getString("email");
                    Date dob = personResults.getDate("dob");
                    Developer developer = new Developer(developerKey, id, firstName, lastName, username, password, email, dob);
                    //find all phone by person id
                    pStatement = connection.prepareStatement("SELECT * FROM `Phone` WHERE personId=?");
                    pStatement.setInt(1, id);
                    ResultSet tempResults = pStatement.executeQuery();
                    addPhone(developer, tempResults);
                    //find all address by person id
                    pStatement = connection.prepareStatement("SELECT * FROM `Address` WHERE personId=?");
                    pStatement.setInt(1, id);
                    tempResults = pStatement.executeQuery();
                    addAddress(developer, tempResults);
                    developers.add(developer);
                }
            }
            developerResults.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    public Developer findDeveloperById(int developerId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            //find developer by id
            pStatement = connection.prepareStatement("SELECT * FROM `developer` WHERE developerId=?");
            pStatement.setInt(1, developerId);
            //Developer
            ResultSet developerResults = pStatement.executeQuery();
            if (developerResults.next()) {
                int id = developerResults.getInt("personId");//the set int
                String developerKey = developerResults.getString("developerKey");
                //find person by id
                pStatement = connection.prepareStatement("SELECT * FROM `Person` WHERE id=?");
                pStatement.setInt(1, id);
                ResultSet personResults = pStatement.executeQuery();
                if (personResults.next()) {
                    String firstName = personResults.getString("firstName");
                    String lastName = personResults.getString("lastName");
                    String username = personResults.getString("username");
                    String password = personResults.getString("password");
                    String email = personResults.getString("email");
                    Date dob = personResults.getDate("dob");
                    Developer developer = new Developer(developerKey, id, firstName, lastName, username, password, email, dob);
                    //find all phone by person id
                    pStatement = connection.prepareStatement("SELECT * FROM `Phone` WHERE personId=?");
                    pStatement.setInt(1, id);
                    ResultSet tempResults = pStatement.executeQuery();
                    addPhone(developer, tempResults);
                    //find all address by person id
                    pStatement = connection.prepareStatement("SELECT * FROM `Address` WHERE personId=?");
                    pStatement.setInt(1, id);
                    tempResults = pStatement.executeQuery();
                    addAddress(developer, tempResults);
                    return developer;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Developer findDeveloperByUsername(String username) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            //find developer by username
            pStatement = connection.prepareStatement("SELECT * FROM `Developer` WHERE username=?");
            pStatement.setString( 1, username);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String password = results.getString("password");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                String developerKey = results.getString("developerKey");
                Developer developer = new Developer(developerKey, id, firstName, lastName, username, password, email, dob);
                //find all phone by person id
                pStatement = connection.prepareStatement("SELECT * FROM `Phone` WHERE personId=?");
                pStatement.setInt(1, id);
                ResultSet tempResults = pStatement.executeQuery();
                addPhone(developer, tempResults);
                //find all address by person id
                pStatement = connection.prepareStatement("SELECT * FROM `Phone` WHERE personId=?");
                pStatement.setInt(1, id);
                tempResults = pStatement.executeQuery();
                addAddress(developer, tempResults);
                return developer;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Developer findDeveloperByCredentials(String username, String password) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();

            pStatement = connection.prepareStatement("SELECT * FROM Person p JOIN Developer d ON p.id = d.personId WHERE username=? AND password=?");
            pStatement.setString(1, username);
            pStatement.setString(2, password);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String email = results.getString("email");
                Date dob = results.getDate("dob");
                String developerKey = results.getString("developerKey");
                Developer developer = new Developer(developerKey, id, firstName, lastName, username, password, email, dob);
                //find all phone by person id
                pStatement = connection.prepareStatement("SELECT * FROM `Phone` WHERE personId=?");
                pStatement.setInt(1, id);
                ResultSet tempResults = pStatement.executeQuery();
                addPhone(developer, tempResults);
                //find all address by person id
                pStatement = connection.prepareStatement("SELECT * FROM `Address` WHERE personId=?");
                pStatement.setInt(1, id);
                tempResults = pStatement.executeQuery();
                addAddress(developer, tempResults);
                return developer;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateDeveloper(int developerId, Developer developer) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement("UPDATE Person SET firstName=?, lastName=?, username=?, password=?, email=?, dob=? WHERE id=?");
            pStatement.setString(1, developer.getFirstName());
            pStatement.setString(2, developer.getLastName());
            pStatement.setString(3, developer.getUsername());
            pStatement.setString(4, developer.getPassword());
            pStatement.setString(5, developer.getEmail());
            Date dob = developer.getDob();
            java.sql.Date dobSql;
            if (dob != null) {
                dobSql = new java.sql.Date(dob.getTime());
            } else dobSql = null;
            pStatement.setDate(6, dobSql);
            pStatement.setInt(7, developerId);
            int per = pStatement.executeUpdate();
            pStatement = connection.prepareStatement("UPDATE Developer SET developerKey=? WHERE personId=?");
            pStatement.setString(1, developer.getDeveloperKey());
            pStatement.setInt(2, developerId);
            int dev = pStatement.executeUpdate();
            //Phone
            pStatement = connection.prepareStatement("DELETE FROM `Phone` WHERE personId=?");
            pStatement.setInt(1, developer.getId());
            pStatement.executeUpdate();
            Collection<Phone> phones = developer.getPhones();
            insertPhone(developer, phones);
            //Address
            pStatement = connection.prepareStatement("DELETE FROM `Address` WHERE personId=?");
            pStatement.setInt(1, developer.getId());
            pStatement.executeUpdate();
            Collection<Address> addresses =  developer.getAddresses();
            insertAddress(developer, addresses);
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteDeveloper(int developerId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            //delete from phone collection
            pStatement = connection.prepareStatement("DELETE FROM `Phone` WHERE personId=?");
            pStatement.setInt(1, developerId);
            pStatement.executeUpdate();
            //delete from address collection
            pStatement = connection.prepareStatement("DELETE FROM `Address` WHERE personId=?");
            pStatement.setInt(1, developerId);
            pStatement.executeUpdate();
            //delete from developer
            pStatement = connection.prepareStatement("DELETE FROM 'Developer' WHERE personId=?");
            pStatement.setInt(1, developerId);
            pStatement.executeUpdate();
            //delete from person
            pStatement = connection.prepareStatement("DELETE FROM `Person` WHERE personId=?");
            pStatement.setInt(1, developerId);
            pStatement.executeUpdate();
            return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void addAddress(Developer developer, ResultSet tempResults) throws SQLException {
        while (tempResults.next()) {
            String street1 = tempResults.getString("street1");
            String street2 = tempResults.getString("street2");
            String city = tempResults.getString("city");
            String state = tempResults.getString("state");
            String zip = tempResults.getString("zip");
            Boolean isPrimary = (tempResults.getInt("primary") == 0 ? false : true);
            Address address = new Address(street1, street2, city, state, zip, isPrimary, developer);
            developer.addAddress(address);
        }
    }

    private void addPhone(Developer developer, ResultSet tempResults) throws SQLException {
        while (tempResults.next()) {
            String phone = tempResults.getString("phone");
            Boolean isPrimary = (tempResults.getInt("primary") == 0) ? false : true;
            Phone phoneObj = new Phone(isPrimary, phone, developer);
            developer.addPhone(phoneObj);
        }
    }

    private void insertAddress(Developer developer, Collection<Address> addresses) throws SQLException {
        for (Address address : addresses) {
            pStatement = connection.prepareStatement("INSERT INTO `Address` (`street1`, `street2`, `city`, `state`, `zip`, `primary`, `personId`) VALUES (?,?,?,?,?,?,?)");
            pStatement.setString(1, address.getStreet1());
            pStatement.setString(2, address.getStreet2());
            pStatement.setString(3, address.getCity());
            pStatement.setString(4, address.getState());
            pStatement.setString(5, address.getZip());
            pStatement.setInt(6, address.getPrimary());
            pStatement.setInt(7, developer.getId());
            pStatement.executeUpdate();
        }
    }

    private void insertPhone(Developer developer, Collection<Phone> phones) throws SQLException {
        for (Phone phone : phones) {
            pStatement = connection.prepareStatement("INSERT INTO `Phone`(`phone`, `primary`, `personId`) VALUES (?,?,?)");
            pStatement.setString(1, phone.getPhone());
            pStatement.setInt(2, phone.getPrimary() ? 1 : 0);
            pStatement.setInt(3, developer.getId());
            pStatement.executeUpdate();
        }
    }

}