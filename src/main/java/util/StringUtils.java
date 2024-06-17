package util;

import java.io.File;

public class StringUtils {
	// For Authentication
    // Start SQL Queries 
    public static final String INSERT_USER = "INSERT INTO users "
            + "(Name, User_name, Gender, Email, Phone_number, Password, Roles) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_LOGIN_USER_INFO = "SELECT * FROM users WHERE User_name = ?";
    public static final String GET_USERNAME = "SELECT COUNT(*) FROM users WHERE User_name = ?";
    public static final String GET_PHONE = "SELECT COUNT(*) FROM users WHERE Phone_number = ?";
    public static final String GET_EMAIL = "SELECT COUNT(*) FROM users WHERE Email = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String GET_USER_ID_BY_USERNAME = "SELECT User_Id FROM users WHERE User_name = ?";
    
    public static final String INSERT_PRODUCT = "INSERT INTO products "
            + "(Product_Name, Brand_Name, Price, Stock, Auto_Focus, Built_In_Flash, Camera_Type, Lens, Sensor_Size, Movie_Format, Image) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PRODUCT = "UPDATE products SET "
    	    + "Product_Name = ?, "
    	    + "Brand_Name = ?, "
    	    + "Stock = ?, "
    	    + "Price = ?, "
    	    + "Auto_Focus = ?, "
    	    + "Built_In_Flash = ?, "
    	    + "Camera_Type = ?, "
    	    + "Lens = ?, "
    	    + "Sensor_Size = ?, "
    	    + "Movie_Format = ?, "
    	    + "Image = ? "
    	    + "WHERE Product_ID = ?";
    public static final String DELETE_PRODUCT = "DELETE FROM products WHERE Product_ID = ?";
    
    public static final String GET_ALL_PRODUCTS = "SELECT * FROM products";
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM products Where Product_Id = ? ";
    public static final String GET_PRODUCT_ID_BY_PRODUCTNAME = "SELECT Product_Id FROM products WHERE Product_Name = ?";
    
    public static final String GET_PRODUCT_BY_NAME = "SELECT * FROM products WHERE Product_Name = ?";
    
    public static final String INSERT_CART = "INSERT INTO cart "
            + "(Product_Id, User_Id, Quantity) "
            + "VALUES (?, ?, ?)";
    public static final String SELECT_CART_BY_USERNAME = 
    	    "SELECT c.Cart_Id, c.Product_Id, c.Quantity, p.Price, p.Product_Name, c.status " +
    	    "FROM cart c " +
    	    "INNER JOIN products p ON c.Product_Id = p.Product_Id " +
    	    "INNER JOIN users u ON c.User_Id = u.User_Id " + 
    	    "WHERE u.User_name = ? AND status ='not ordered';";

    public static final String PRICE_BY_PRODUCT_ID = "SELECT Price FROM products WHERE Product_Id = ?";
    
    public static final String DELETE_CART = "DELETE FROM cart WHERE Cart_Id = ?";
    
    public static final String GET_CART_DETAILS = "SELECT * FROM cart Where User_Id = ? ";
    
    public static final String GET_ALL_ORDERS = 
    	    "SELECT p.Product_Name, o.Order_Date, c.Quantity, p.Price, " +
    	    "o.Status, o.Order_Id, o.Cart_Id, c.Product_Id, c.User_Id " +
    	    "FROM orders o " +
    	    "JOIN cart c ON o.Cart_Id = c.Cart_Id " +
    	    "JOIN products p ON c.Product_Id = p.Product_Id";
    public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET Status = ? WHERE Order_Id = ?;";
    public static final String GET_ORDERS_BY_USER_ID = 
            "SELECT p.Product_Name, o.Order_Date, c.Quantity, p.Price, " +
            "o.Status, o.Order_Id, o.Cart_Id, c.Product_Id, c.User_Id " +
            "FROM orders o " +
            "JOIN cart c ON o.Cart_Id = c.Cart_Id " +
            "JOIN products p ON c.Product_Id = p.Product_Id " +
            "WHERE c.User_Id = ?";

	public static final String INSERT_FEEDBACK = "INSERT INTO feedback"
			+"(Name,Email,Description)"
			+ "VALUES (?, ?, ?)";
	public static final String GET_ALL_FEEDBACKS = "SELECT * FROM feedback";
	
    public static final String UPDATE_PROFILE = "UPDATE users SET "
    	    + "Name = ?, "
    	    + "Email = ?, "
    	    + "Phone_number = ? "
    	    + "WHERE User_ID = ?";
    
    
    
    // End SQL Queries

    // Start Parameter names
    public static final String USER_NAME = "username";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "confirm_password";
    public static final String ROLES = "Roles";
    // End Parameter names

    // Start string messages
    // Start register page messages
    public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered!";
    public static final String ERROR_REGISTER_MESSAGE = "Please correct the form data.";
    public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred.";
    public static final String USERNAME_ERROR_MESSAGE = "Username is already registered.";
    public static final String EMAIL_ERROR_MESSAGE = "Email is already registered.";
    public static final String PHONE_NUMBER_ERROR_MESSAGE = "Phone Number is already registered.";
    public static final String PASSWORD_UNMATCHED_ERROR_MESSAGE = "Password not matched.";
    // End register page messages

    // Start login page message
    public static final String SUCCESS_LOGIN_MESSAGE = "Successfully LoggedIn!";
    public static final String ERROR_LOGIN_MESSAGE = "Either username or password is not correct!";
    // End login page message

    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String ERROR_MESSAGE = "errorMessage";
    // End string messages

    // Start JSP Route
    public static final String LOGIN_PAGE = "/pages/login.jsp";
    public static final String REGISTER_PAGE = "/pages/Register.jsp";
    public static final String HOME_PAGE = "/pages/home.jsp";
    
    public static final String ADD_PRODUCT_PAGE = "/pages/AddProduct.jsp";
    public static final String PRODUCT_PAGE = "/pages/product.jsp";

    // End JSP Route

    // Start Servlet Route
    public static final String REGISTER_SERVLET = "/RegisterServlet";
    public static final String LOGIN_SERVLET = "/LoginServlet";
    public static final String USERS_SERVLET = "/UserServlet";
    
    public static final String ADD_PRODUCT_SERVLET = "/AddProductServlet";
    public static final String RETRIEVE_PRODUCT_SERVLET = "/RetrieveProductServlet";
    public static final String ADMIN_PRODUCT_SERVLET = "/AdminProductServlet";
    public static final String CART_SERVLET = "/CartServlet";
    
	public static final String FEEDBACK_SERVLET = "/FeedbackSubmission";
	
	public static final String PROFILE_SERVLET = "/ProfileServlet";
    
    // End Servlet Route
    
  
    public static final String IMAGE_DIR = "Users\\ACER\\eclipse-workspace\\Flare\\src\\main\\webapp\\resources\\images\\";
	public static final String IMAGE_DIR_PATH = "C:" + File.separator + IMAGE_DIR;

    // Start Parameter names
	public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_NAME = "productName";
    public static final String BRAND_NAME = "productBrand";
    public static final String STOCK = "productStock";
    public static final String PRICE = "productPrice";
    public static final String AUTO_FOCUS = "autoFocus";
    public static final String BUILT_IN_FLASH = "builtinFlash";
    public static final String CAMERA = "cameraType";
    public static final String LENS = "productLens";
    public static final String SENSOR_SIZE = "sensorSize";
    public static final String MOVIE_FORMAT = "movieFormat";
    public static final String IMAGE = "productImage";
    // End Parameter names

    // Start string messages
    // Start product page messages
    public static final String PRODUCT_ADD_SUCCESS_MESSAGE = "Product Added Successfully. ";
    public static final String PRODUCT_ADD_ERROR_MESSAGE = "Please correct the form data.";
    public static final String PRICE_ERROR_MESSAGE = "Invalid price value. ";
    public static final String PRICE_PARAMETER_ERROR_MESSAGE = "Price parameter is required.";
    public static final String STOCK_ERROR_MESSAGE = "Invalid stock value.";
    public static final String NULL_VALUES_ERROR_MESSAGE = "Please fill out all the required fields.";
    public static final String IMAGE_ERROR_MESSAGE = "Please upload an image for the product..";
	public static final String SUCCESS_UPDATE_MESSAGE = "Product Updated";
	public static final String ERROR_UPDATE_MESSAGE = "Product Update failed";
	public static final String SUCCESS_DELETE_MESSAGE = "Product Delete Successful!";
	public static final String ERROR_DELETE_MESSAGE = "Product Delete Fail!";
    // End product page messages





    // Start JSP Route

   
    // Parameter names
    public static final String USER_ID = "User_Id";
    public static final String QUANTITY = "Quantity";
    
    public static final String CART_PAGE = "/pages/cart.jsp";
    
   
	

	
	  public static final String FEEDBACK_ADD_SUCCESS_MESSAGE = "Feedback submit Successfully. ";
      //Start feedback names
    
    public static final String ABOUT_NAME="name";
    public static final String ABOUT_EMAIL="email";
    public static final String ABOUT_DESCRIPTION="description";
    

    
    
}
