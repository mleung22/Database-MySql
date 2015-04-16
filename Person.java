package application;

public class Person {

	private String firstName;
	private String lastName;
	private int age;
	private long ssn;
	private long creditCard;

	public Person(String firstName, String lastName, int age, long ssn,
			long creditCard) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.ssn = ssn;
		this.creditCard = creditCard;

	}

	public Person() {
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

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	public long getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "\nPerson [firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", ssn=" + ssn + ", creditCard="
				+ creditCard + "]" + "\n";
	}

}

