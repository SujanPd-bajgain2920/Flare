package model;

public class UserModel {
	private int userId;
    private String name;
    private String userName;
    private String email;
    private String gender;
    private String phone;
    private String password;
    @SuppressWarnings("unused")
	private String roles;
    
    private String feedbackSubName;
    private String feedbackSubEmail;
    private String feedbackSubDescription;

    
	/**
	 * @param name
	 * @param userName
	 * @param email
	 * @param gender
	 * @param phone
	 * @param password
	 * @param roles
	 */
	public UserModel(int userId, String name, String userName, String email, String gender, String phone, String password,
			String roles) {
		super();
		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.gender = gender;
		this.phone = phone;
		this.password = password;
		this.roles = roles;
	}




	public UserModel() {
		// TODO Auto-generated constructor stub
	}
	
	public int getUserId() {
		return userId;
	}




	/**
	 * @param name the name to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}




	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}




	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}




	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}




	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}




	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}




	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}




	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}




	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}




	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}




	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}
	

	public String getFeedbackSubName() {
		return feedbackSubName;
	}
	
	public void setFeedbackSubName(String feedbackSubName) {
		this.feedbackSubName=feedbackSubName;
	}
	
	public String getFeedbackSubEmail()
	{
		return feedbackSubEmail;
	}
	
	public void setFeedbackSubEmail(String feedbackSubEmail)
	{
		this.feedbackSubEmail=feedbackSubEmail;
	}
	
	public String getFeedbackSubDescription()
	{
		return feedbackSubDescription;
	}
	
	public void setFeedbackSubDescription(String feedbackSubDescription)
	{
		this.feedbackSubDescription=feedbackSubDescription;
	}
	
	
	
}
