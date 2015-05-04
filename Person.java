package application;

/**
 * 
 * Class that creates Person objects
 *
 */
public class Person {

	private String firstName;
	private String lastName;
	private int age;
	private String ssn;
	private String creditCard;

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param ssn
	 * @param creditCard
	 * @throws InvalidSsnException
	 * @throws InvalidCCException
	 * 
	 * Person Constructor
	 * 
	 */
	public Person(String firstName, String lastName, int age, String ssn,
			String creditCard) throws InvalidSsnException, InvalidCCException {

		ssn = ssn.replaceAll("[^0-9]", "");
		creditCard = creditCard.replaceAll("[^0-9]", "");

		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.ssn = ssn;
		this.creditCard = creditCard;
		isValid();

	}
	
	/**
	 * Person constructor that takes no parameters
	 */
	public Person(){		
	}

	/**
	 * 
	 * @return
	 * @throws InvalidSsnException
	 * @throws InvalidCCException
	 * 
	 * Checks if Social Security and Credit Card Number inputs are valid
	 */
	public boolean isValid() throws InvalidSsnException, InvalidCCException {
		if (ssn.length() != 9) {
			throw new InvalidSsnException("Invalid Social Security Input "
					+ ssn);
		}
		if (creditCard.length() != 16) {
			throw new InvalidCCException("Invalid Credit Card Input " + ssn);
		}
		return true;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 
	 * @return Returns social security number in xxx-xx-xxxx format
	 */

	public String getSsn() {
		return ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-"
				+ ssn.substring(5, 9);
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * 
	 * @return Returns credit card number in xxxx-xxxx-xxxx-xxxx format
	 */
	public String getCreditCard() {
		return creditCard.substring(0, 4) + "-" + creditCard.substring(4, 8)
				+ "-" + creditCard.substring(8, 12) + "-"
				+ creditCard.substring(12, 16);
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "\nPerson [firstName:" + firstName + ", lastName:" + lastName
				+ ", age:" + age + ", ssn:" + getSsn() + ", creditCard:"
				+ getCreditCard() + "]" + "\n";
	}

}

