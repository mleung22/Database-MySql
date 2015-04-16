package application;

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

public class Database {

	private static Connection myConn;

	public Database() throws FileNotFoundException, IOException, SQLException {

		Properties props = new Properties();
		props.load(new FileInputStream("loginRootInfo"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");

		myConn = DriverManager.getConnection(dburl, user, password);

		System.out.println("Connection to " + dburl + " successful.");

	}

	public String insertPerson(Person person) throws SQLException {

		Statement myStmt = myConn.createStatement();

		String sql = "INSERT into customerinformation "
				+ "(first_name, last_name, age, ssn, credit_card)"
				+ " values ('" + person.getFirstName() + "', '"
				+ person.getLastName() + "', '" + person.getAge() + "', '"
				+ person.getSsn() + "', '" + person.getCreditCard() + "')";

		myStmt.executeUpdate(sql);

		return "Insert complete";

	}

	public Person convertDataToPerson(ResultSet myResultSet)
			throws SQLException {

		String firstName = myResultSet.getString("first_name");
		String lastName = myResultSet.getString("last_name");
		int age = myResultSet.getInt("age");
		long ssn = myResultSet.getLong("ssn");
		long creditCard = myResultSet.getLong("credit_card");

		Person tmpPerson = new Person(firstName, lastName, age, ssn, creditCard);
		return tmpPerson;

	}

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

	public Person findPersonByLastName(String lastName) throws SQLException {

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
			return tmpPerson;
		}

	}

	public Person findPersonByFullName(String firstName, String lastName)
			throws SQLException {

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

	public String deletePerson(String firstName, String lastName)
			throws SQLException {

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
