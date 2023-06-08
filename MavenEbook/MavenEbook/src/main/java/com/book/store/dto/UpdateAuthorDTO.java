package com.book.store.dto;



public class UpdateAuthorDTO {
private long id;
private String firstName;
private String lastName;
private String email;
private String password;
private Boolean active;
public UpdateAuthorDTO() {
	super();

}
public UpdateAuthorDTO(long id, String firstName, String lastName, String email, String password, Boolean active) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.active = active;
}
@Override
public String toString() {
	return "UpdateAuthorDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
			+ ", password=" + password + ", active=" + active + "]";
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Boolean getActive() {
	return active;
}
public void setActive(Boolean active) {
	this.active = active;
}
	
}
