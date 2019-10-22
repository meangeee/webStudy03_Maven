package kr.or.ddit.springbatch;

public class Person {

	private Long personId;
	private String lastName;
	private String firstName;

	public Person() {
	}

	private Person(Long personId, String firstName, String lastName) {
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName;
	}

	// 밑에 애들 대신 생성하기 위해서 만들었음
	public static PersonBuilder builder() {
		return new PersonBuilder();
	}

	static class PersonBuilder {
		private PersonBuilder() {
		}

		private Long personId;
		private String firstName;
		private String lastName;

		public PersonBuilder personId(Long personId) {
			this.personId = personId;
			return this;
		}

		public PersonBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public PersonBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Person build() {
			return new Person(personId, firstName, lastName);
		}
	}

}