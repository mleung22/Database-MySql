package applicati;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class that manipulates Person objects in the database 
 * Such as inserting, deleting and viewing person objects
 */
public class Database {

	private static Connection myConn;
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 * 
	 * Database constructor
	 * Connects to MySql Database
	 */

	public Database() throws FileNotFoundException, IOException, SQLException {

		Properties props = new Properties();
		props.load(new FileInputStream("loginRootInfo"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");

		myConn = DriverManager.getConnection(dburl, user, password);

		System.out.println("Connection to " + dburl + " successful.");

	}
	
	/**
	 * 
	 * @param person Person Object
	 * @return 
	 * 		Returns a String that lets the user know data insertion was successful
	 * @throws SQLException
	 * 
	 * Insert method that inserts a person object into the database
	 */

	public String insertPerson(Person person) throws SQLException {

		Statement myStmt = myConn.createStatement();

		String insertSql = "INSERT into customerinformation "
				+ "(first_name, last_name, age, ssn, credit_card)"
				+ " values ('" + person.getFirstName() + "', '"
				+ person.getLastName() + "', '" + person.getAge() + "', '"
				+ person.getSsn() + "', '" + person.getCreditCard() + "')";

		myStmt.executeUpdate(insertSql);

		return "Insert complete";

	}
	
	/**
	 * 
	 * @param myResultSet
	 * @return
	 * 		Returns a Person object
	 * @throws SQLException
	 * @throws InvalidSsnException
	 * @throws InvalidCCException
	 * 
	 * Converts MySql table row data to Person object and returns it
	 */

	public Person convertDataToPerson(ResultSet myResultSet)
			throws SQLException, InvalidSsnException, InvalidCCException {

		String firstName = myResultSet.getString("first_name");
		String lastName = myResultSet.getString("last_name");
		int age = myResultSet.getInt("age");
		String ssn = myResultSet.getString("ssn");
		String creditCard = myResultSet.getString("credit_card");

		Person tmpPerson = new Person(firstName, lastName, age, ssn, creditCard);
		return tmpPerson;

	}
	
	/**
	 * 
	 * @return
	 * 		Returns a list of Person objects
	 * @throws Exception
	 * 
	 * Places all Person objects in the MySql Database in a list and returns it
	 */

	public List<Person> findAllPeople() throws Exception {
		List<Person> list = new ArrayList<>();

		try {
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt
					.executeQuery("select * from customerinformation");

			while (myRs.next()) {
				Person tmpPerson = convertDataToPerson(myRs);
				list.add(tmpPerson);
			}

			return list;
		} finally {
			System.out.println("\nData retrieved");
		}
	}
	
	/**
	 * 
	 * @return
	 * 		Returns a list of Person objects
	 * @throws Exception
	 * 
	 * Places the first name of every Person object in the MySql Database and returns it
	 */

	public List findAllPeopleByFirstName() throws Exception {
		List list = new ArrayList<>();

		try {
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt
					.executeQuery("select * from customerinformation");

			while (myRs.next()) {
				Person tmpPerson = convertDataToPerson(myRs);
				list.add(tmpPerson.getFirstName());
			}

			return list;
		} finally {
		}
	}
	
	/**
	 * 
	 * @return
	 * 		Returns a list of Person objects
	 * @throws Exception
	 * 
	 * Places the last name of every Person object in the MySql Database and returns it
	 */

	public List findAllPeopleByLastName() throws Exception {
		List list = new ArrayList<>();

		try {
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt
					.executeQuery("select * from customerinformation");

			while (myRs.next()) {
				Person tmpPerson = convertDataToPerson(myRs);
				list.add(tmpPerson.getLastName());
			}

			return list;
		} finally {
		}
	}
	
	/**
	 * 
	 * @return
	 * 		Returns a list of Person objects
	 * @throws Exception
	 * 
	 * Places the age of every Person object in the MySql Database and returns it
	 */

	public List findAllPeopleByAge() throws Exception {
		List list = new ArrayList<>();

		try {
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt
					.executeQuery("select * from customerinformation");

			while (myRs.next()) {
				Person tmpPerson = convertDataToPerson(myRs);
				list.add(tmpPerson.getAge());
			}

			return list;
		} finally {
		}
	}
	
	/**
	 * 
	 * @return
	 * 		Returns a list of Person objects
	 * @throws Exception
	 * 
	 * Places the Social Security Number of every Person object in the MySql Database and returns it
	 */

	public List findAllPeopleBySsn() throws Exception {
		List list = new ArrayList<>();

		try {
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt
					.executeQuery("select * from customerinformation");

			while (myRs.next()) {
				Person tmpPerson = convertDataToPerson(myRs);
				list.add(tmpPerson.getSsn());
			}

			return list;
		} finally {
		}
	}
	
	/**
	 * 
	 * @return
	 * 		Returns a list of Person objects
	 * @throws Exception
	 * 
	 * Places the Credit Card Number of every Person object in the MySql Database and returns it
	 */

	public List findAllPeopleByCC() throws Exception {
		List list = new ArrayList<>();

		try {
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt
					.executeQuery("select * from customerinformation");

			while (myRs.next()) {
				Person tmpPerson = convertDataToPerson(myRs);
				list.add(tmpPerson.getCreditCard());
			}

			return list;
		} finally {
		}
	}
	
	/**
	 * 
	 * @param lastName
	 * @return
	 * 		Returns a list of Person objects
	 * @throws SQLException
	 * @throws InvalidSsnException
	 * @throws InvalidCCException
	 * 
	 * Finds a Person object in the MySql database by their last name
	 */
	public Person findPersonByLastName(String lastName) throws SQLException,
			InvalidSsnException, InvalidCCException {

		Person tmpPerson = new Person();
		Statement myStmt = myConn.createStatement();

		String query = "SELECT first_name, last_name, age, ssn, credit_card "
				+ "FROM customerinformation WHERE last_name='" + lastName + "'";
		ResultSet myRs = myStmt.executeQuery(query);

		while (myRs.next()) {
			tmpPerson = convertDataToPerson(myRs);
		}

		if (tmpPerson.getLastName() == null) {
			System.out
					.println("That last name does not match anyone in this database");
			return null;
		} else {
			System.out.println("\nPerson Found");
			return tmpPerson;
		}

	}
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 * 		Returns a list of Person objects
	 * @throws SQLException
	 * @throws InvalidSsnException
	 * @throws InvalidCCException
	 * 
	 * Finds a Person object in the MySql database by their full name
	 */

	public Person findPersonByFullName(String firstName, String lastName)
			throws SQLException, InvalidSsnException, InvalidCCException {

		Person tmpPerson = new Person();
		Statement myStmt = myConn.createStatement();

		String query = "SELECT first_name, last_name, age, ssn, credit_card "
				+ "FROM customerinformation WHERE first_name= '" + firstName
				+ "' AND last_name='" + lastName + "'";
		ResultSet myRs = myStmt.executeQuery(query);

		while (myRs.next()) {
			tmpPerson = convertDataToPerson(myRs);
		}

		if (tmpPerson.getLastName() == null && tmpPerson.getFirstName() == null) {
			System.out
					.println("That full name does not match anyone in this database");
			return null;
		} else {
			return tmpPerson;
		}

	}
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 * 		Returns a string that lets the user know that the deletion was successful
	 * @throws SQLException
	 * @throws InvalidSsnException
	 * @throws InvalidCCException
	 * 
	 * Deletes a person in the MySql database
	 */

	public String deletePerson(String firstName, String lastName)
			throws SQLException, InvalidSsnException, InvalidCCException {

		Statement myStmt = myConn.createStatement();

		if (findPersonByFullName(firstName, lastName) != null) {
			System.out.println("\nDeleting" + "\n"
					+ findPersonByFullName(firstName, lastName));

			String deleteQuery = "DELETE FROM customerinformation WHERE first_name='"
					+ firstName + "' AND last_name='" + lastName + "'";

			myStmt.executeUpdate(deleteQuery);

			return "Deletion Complete";
		} else {
			return "Deletion Failed";
		}
	}

}

