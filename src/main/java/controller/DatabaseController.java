package controller;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.CartModel;
import model.PasswordEncryptionWIthAes;
import model.UserModel;
import model.ProductModel;
import util.StringUtils;
import java.util.logging.Logger;

import model.OrderModel;


public class DatabaseController {
	
	


// database connection part
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/flare";
        String user = "root";
        String pass = "";
        return DriverManager.getConnection(url, user, pass);
    }

    // addUser method 
    public int addUser(UserModel userModel) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.INSERT_USER);
            
            // Set default role
            String defaultRole = "user"; // Change this to your desired default role
            userModel.setRoles(defaultRole); // Set default role in the UserModel object

            PreparedStatement checkUsernameSt = con.prepareStatement(StringUtils.GET_USERNAME);
            checkUsernameSt.setString(1, userModel.getUserName());
            ResultSet checkUsernameRs = checkUsernameSt.executeQuery();

            checkUsernameRs.next();

            if (checkUsernameRs.getInt(1) > 0) {
                return -2; // Username already exists
            }

            PreparedStatement checkPhoneSt = con.prepareStatement(StringUtils.GET_PHONE);
            checkPhoneSt.setString(1, userModel.getPhone());
            ResultSet checkPhoneRs = checkPhoneSt.executeQuery();

            checkPhoneRs.next();

            if (checkPhoneRs.getInt(1) > 0) {
                return -4; // Phone Number already exists
            }

            PreparedStatement checkEmailSt = con.prepareStatement(StringUtils.GET_EMAIL);
            checkEmailSt.setString(1, userModel.getEmail());
            ResultSet checkEmailRs = checkEmailSt.executeQuery();

            checkEmailRs.next();

            if (checkEmailRs.getInt(1) > 0) {
                return -3; // Email already exists
            }

            // Encrypt password before storing it in the database
            String encryptedPassword = PasswordEncryptionWIthAes.encryptPassword(userModel.getPassword(), "U3CdwubLD5yQbUOG92ZnHw==");

            st.setString(1, userModel.getUserName());
            st.setString(2, userModel.getName());
            st.setString(3, userModel.getGender());
            st.setString(4, userModel.getEmail());
            st.setString(5, userModel.getPhone());
            st.setString(6, encryptedPassword);
            st.setString(7, userModel.getRoles());
            

            int result = st.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    // addProduct method 
    public int addProduct(ProductModel productModel) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.INSERT_PRODUCT);

            // Set product attributes in the prepared statement
            st.setString(1, productModel.getProductName());
            st.setString(2, productModel.getBrandName());
            st.setFloat(3, productModel.getProductPrice());
            st.setInt(4, productModel.getProductStock());
            st.setString(5, productModel.getAutoFocus());
            st.setString(6, productModel.getProductFlash());
            st.setString(7, productModel.getProductType());
            st.setString(8, productModel.getProductLens());
            st.setString(9, productModel.getSensorSize());
            st.setString(10, productModel.getMovieFormat());
            st.setString(11, productModel.getImageUrlFromPart());
            

            int result = st.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getUserLoginInfo(String username, String password) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String userDb = rs.getString("user_name");
                String encryptedPassword = rs.getString("password");

                // Decrypt password from database and compare
                String decryptedPassword = PasswordEncryptionWIthAes.decryptPassword(encryptedPassword, "U3CdwubLD5yQbUOG92ZnHw==");

                if (decryptedPassword!=null && userDb.equals(username) && decryptedPassword.equals(password)) {
                    return 1; // Login successful
                } else {
                    return 0; // Password mismatch
                }
            } else {
                // No matching record found
                return 0;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public UserModel getUserDetails(String userName) throws ClassNotFoundException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(StringUtils.GET_LOGIN_USER_INFO)) {
            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetch user details from the result set and create a UserModel object
                    UserModel user = new UserModel();
                    user.setUserName(resultSet.getString("User_name"));
                    user.setName(resultSet.getString("Name"));
                    user.setEmail(resultSet.getString("Email")); 
                    user.setPhone(resultSet.getString("Phone_number")); 
                    user.setRoles(resultSet.getString("Roles"));
                    
                    // Print user details to the console
                    System.out.println("Name: " + user.getName());
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("Phone: " + user.getPhone());
                    System.out.println("Username: " + user.getUserName());
                    
                    // Populate other fields as needed
                    return user;
                } else {
                    // No user found with the given username
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return null;
        }
    }

 
    
        private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());

       public List<ProductModel> getAllProducts() {
            List<ProductModel> products = new ArrayList<>();
            LOGGER.info("Fetching all products from the database...");
            try (Connection con = getConnection()) {
                PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_PRODUCTS);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    ProductModel product = new ProductModel();
                    product.setProductId(resultSet.getInt("Product_Id"));
                    product.setProductName(resultSet.getString("Product_Name"));
                   product.setProductPrice(resultSet.getFloat("Price"));
                    product.setImageUrlFromPart(resultSet.getString("Image"));
                    // Populate other fields as needed
                    products.add(product);
                    LOGGER.info("Added product: " + product.getProductName());
                }
                LOGGER.info("All products fetched successfully.");
            } catch (SQLException | ClassNotFoundException ex) {
                LOGGER.severe("Error fetching products from the database: " + ex.getMessage());
                ex.printStackTrace(); // Log the exception for debugging
            }
            return products;
        }
     /*  public ProductModel getProductById(int productId) throws SQLException, ClassNotFoundException {
    	   ProductModel product = null;
    	   LOGGER.info("Fetching product with ID: " + productId + " from the database...");
    	   try (Connection con = getConnection()) {
    		   PreparedStatement st = con.prepareStatement(StringUtils.GET_PRODUCT_BY_ID);
    		   st.setInt(1, productId);
    		   ResultSet resultSet = st.executeQuery();
    		   if (resultSet.next()) {
		            product = new ProductModel();
		            product.setProductId(resultSet.getInt("Product_Id"));
		            product.setProductName(resultSet.getString("Product_Name"));
		            product.setBrandName(resultSet.getString("Brand_Name"));
		            product.setStock(resultSet.getInt("Stock"));
		            product.setPrice(resultSet.getFloat("Price"));
		            product.setAutoFocus(resultSet.getString("Auto_Focus"));
		            product.setFlash(resultSet.getString("Built_In_Flash"));
		            product.setType(resultSet.getString("Camera_Type"));
		            product.setLens(resultSet.getString("Lens"));
		            product.setSensorSize(resultSet.getString("Sensor_Size"));
		            product.setMovieFormat(resultSet.getString("Movie_Format"));
		            product.setImageUrlFromPart(resultSet.getString("Image")); 
		            // Populate other fields as needed
		            LOGGER.info("Product with ID " + productId + " fetched successfully.");
    		   } else {
    			   LOGGER.warning("Product with ID " + productId + " not found.");
    		   }
    	   }
    	   return product;
       }
       */
       
       public ProductModel getProductByName(String productName) throws SQLException, ClassNotFoundException {
    	    ProductModel product = null;
    	    LOGGER.info("Fetching product with name: " + productName + " from the database...");
    	    try (Connection con = getConnection()) {
    	        PreparedStatement st = con.prepareStatement(StringUtils.GET_PRODUCT_BY_NAME);
    	        st.setString(1, productName);
    	        ResultSet resultSet = st.executeQuery();
    	        if (resultSet.next()) {
    	            product = new ProductModel();
    	            product.setProductId(resultSet.getInt("Product_Id"));
    	            product.setProductName(resultSet.getString("Product_Name"));
    	            product.setBrandName(resultSet.getString("Brand_Name"));
    	            product.setProductStock(resultSet.getInt("Stock"));
    	            product.setProductPrice(resultSet.getFloat("Price"));
    	            product.setAutoFocus(resultSet.getString("Auto_Focus"));
    	            product.setProductFlash(resultSet.getString("Built_In_Flash"));
    	            product.setProductType(resultSet.getString("Camera_Type"));
    	            product.setProductLens(resultSet.getString("Lens"));
    	            product.setSensorSize(resultSet.getString("Sensor_Size"));
    	            product.setMovieFormat(resultSet.getString("Movie_Format"));
    	            product.setImageUrlFromPart(resultSet.getString("Image")); 
    	            // Populate other fields as needed
    	            LOGGER.info("Product with name " + productName + " fetched successfully. Product ID: " + product.getProductId());
    	        } else {
    	            LOGGER.warning("Product with name " + productName + " not found.");
    	        }
    	    }
    	    return product;
    	}


    // Add item to cart
    

       
       // Get cart details for a specific user
       public List<CartModel> getCartDetails(int userId) {
           List<CartModel> cart = new ArrayList<>();
           try (Connection con = getConnection()) {
               PreparedStatement st = con.prepareStatement(StringUtils.GET_CART_DETAILS);
               st.setInt(1, userId);
               ResultSet resultSet = st.executeQuery();
               while (resultSet.next()) {
                   CartModel cartItem = new CartModel();
                   
                   cartItem.setProductId(resultSet.getInt(StringUtils.PRODUCT_ID));
                   cartItem.setUserId(resultSet.getInt(StringUtils.USER_ID));
                   cartItem.setQuantity(resultSet.getInt(StringUtils.QUANTITY));
                   // You can retrieve additional details of the product and add them to the cart item
                   cart.add(cartItem);
               }
           } catch (SQLException | ClassNotFoundException ex) {
               ex.printStackTrace(); // Log the exception for debugging
           }
           return cart;
       }

       
       
       // in cart
       public int getUserIdByUsername(String username) {
    	    try (Connection con = getConnection()) {
    	        PreparedStatement st = con.prepareStatement(StringUtils.GET_USER_ID_BY_USERNAME);
    	        st.setString(1, username);
    	        ResultSet resultSet = st.executeQuery();
    	        if (resultSet.next()) {
    	            return resultSet.getInt("User_Id");
    	        }
    	    } catch (SQLException | ClassNotFoundException ex) {
    	        ex.printStackTrace(); // Log the exception for debugging
    	    }
    	    return -1; // Return -1 if user ID not found or an error occurs
    	}
       
       
       
       
       
       // update product
       public int updateProdcut(ProductModel productModel) {
           try (Connection con = getConnection()) {
               PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_PRODUCT);

            // Set product attributes in the prepared statement
               st.setString(1, productModel.getProductName());
               st.setString(2, productModel.getBrandName());
               st.setInt(3, productModel.getProductStock());
               st.setFloat(4, productModel.getProductPrice());
               st.setString(5, productModel.getAutoFocus());
               st.setString(6, productModel.getProductFlash());
               st.setString(7, productModel.getProductType());
               st.setString(8, productModel.getProductLens());
               st.setString(9, productModel.getSensorSize());
               st.setString(10, productModel.getMovieFormat());
               st.setString(11, productModel.getImageUrlFromPart());
               String productId = String.valueOf(productModel.getProductId());
               st.setString(12, productId);

               int result = st.executeUpdate();
               return result > 0 ? 1 : 0;
           } catch (SQLException | ClassNotFoundException ex) {
               ex.printStackTrace(); // Log the exception for debugging
               return -1;
           } catch (Exception e) {
               e.printStackTrace();
               return -1;
           }
       }
       

       // delete product
	public int deleteProduct(int productId) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement(StringUtils.DELETE_PRODUCT);
            st.setInt(1, productId);

            int result = st.executeUpdate();
            return result > 0 ? 1 : 0; // Return 1 if deletion is successful, otherwise return 0
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return -1; // Return -1 for any exceptions
        }
    }

	public float getPriceByProductId(int productId) {
	    float price = 0;
	    try (Connection con = getConnection()) {
	        PreparedStatement st = con.prepareStatement(StringUtils.PRICE_BY_PRODUCT_ID);
	        st.setInt(1, productId);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            price = rs.getFloat("price");
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }
	    return price;
	}

	public int getQuantityInCart(int productId, int userId) {
        int quantity = 0;
        try (Connection con = getConnection()) {
            // Prepare SQL query to retrieve the quantity of the specified product in the user's cart
            String query = "SELECT Quantity FROM cart WHERE Product_Id = ? AND User_Id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, productId);
            pstmt.setInt(2, userId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // If the product is found in the cart, retrieve its quantity
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return quantity;
    }

	 public void updateCartQuantity(int productId, int userId, int newQuantity) {
	        try (Connection con = getConnection()) {
	            // Prepare SQL query to update the quantity of the specified product in the user's cart
	            String query = "UPDATE cart SET Quantity = ? WHERE Product_Id = ? AND User_Id = ?";
	            PreparedStatement pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, newQuantity);
	            pstmt.setInt(2, productId);
	            pstmt.setInt(3, userId);
	            
	            // Execute the update query
	            pstmt.executeUpdate();
	        } catch (SQLException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
	    }
	 public boolean deletecart(int cartId) {
		    try (Connection con = getConnection()) {
		        PreparedStatement st = con.prepareStatement(StringUtils.DELETE_CART);
		        st.setInt(1, cartId);

		        int result = st.executeUpdate();
		        return result > 0; // Return true if deletion is successful, otherwise return false
		    } catch (SQLException | ClassNotFoundException ex) {
		        ex.printStackTrace(); // Log the exception for debugging
		        return false; // Return false for any exceptions
		    }
		}



	 public void updateQuantityInCart(int productId, int userId, int newQuantity) {
		    try (Connection con = getConnection()) {
		        // Prepare the SQL statement to update the quantity in the cart
		        String sql = "UPDATE cart SET Quantity = ? WHERE User_Id = ? AND Product_Id = ?";
		        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		            pstmt.setInt(1, newQuantity);
		            pstmt.setInt(2, userId);
		            pstmt.setInt(3, productId);

		            // Execute the update
		            pstmt.executeUpdate();
		        }
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		        // Handle the exception
		    }
	 }

	 public List<CartModel> getCartItemsByUsername(String username) {
		    List<CartModel> carts = new ArrayList<>();
		    try (Connection con = getConnection(); 
		         PreparedStatement st = con.prepareStatement(StringUtils.SELECT_CART_BY_USERNAME)) {
		        st.setString(1, username);
		        try (ResultSet rs = st.executeQuery()) {
		            while (rs.next()) {
		                CartModel cart = new CartModel();
		                cart.setCartId(rs.getInt("cart_id"));
		                cart.setProductName(rs.getString("product_name"));
		                cart.setProductId(rs.getInt("product_id"));
		                cart.setQuantity(rs.getInt("quantity"));
		                cart.setStatus(rs.getString("status"));
		                cart.setProductPrice(rs.getInt("price"));
		                carts.add(cart);
		            }
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        // Log SQL exception
		    } catch (ClassNotFoundException ex) {
		        ex.printStackTrace();
		        // Log class not found exception
		    }
		    System.out.println("Retrieved " + carts.size() + " cart items for user: " + username);
		    return carts;
		}

	

	
	 public boolean insertOrder(Date orderDate, String orderStatus, int cartId, int productId, int userId) {
	        String sql = "INSERT INTO orders (Order_Date, Status, Cart_Id) VALUES (?, ?, ?)";
	        try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
	            java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime()); // Convert java.util.Date to java.sql.Date
	            pstmt.setDate(1, sqlDate);
	            pstmt.setString(2, orderStatus);
	            pstmt.setInt(3, cartId);
	           
	            
	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0; // Return true if rows were affected
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return false; // Return false if an exception occurs
	        }
	 }
	 
	 public List<OrderModel> getAllOrders() {
			List<OrderModel> orders = new ArrayList<>();
			try (Connection con = getConnection();
					PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_ORDERS)) {
				// Execute the query
				try (ResultSet rs = st.executeQuery()) {
					// Iterate through the result set
					while (rs.next()) {
						// Create a new OrderModel object for each row
						OrderModel order = new OrderModel();
						// Set the attributes of the OrderModel object using data from the result set
						order.setOrderId(rs.getInt("order_id"));
						order.setProductName(rs.getString("product_name"));
						order.setQuantity(rs.getInt("quantity"));
						order.setPrice(rs.getInt("price"));
						order.setOrderDate(rs.getDate("order_date"));
						order.setStatus(rs.getString("status"));

						// Add the OrderModel object to the list
						orders.add(order);
					}
				}
			} catch (SQLException | ClassNotFoundException ex) {
				// Handle any SQL or class loading exceptions
				ex.printStackTrace(); // Log the exception for debugging
			}
			// Return the list of OrderModel objects
			return orders;
		}
	 
	 public boolean updateCartStatus(int cartId, String status) throws SQLException, ClassNotFoundException {
		    String sql = "UPDATE cart SET Status = ? WHERE Cart_Id = ?";
		    try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
		        pstmt.setString(1, status);
		        pstmt.setInt(2, cartId);
		        int rowsAffected = pstmt.executeUpdate();
		        return rowsAffected > 0; 
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		        throw e; 
		    }
	 }
	 
	 
	 
	 public int addToCart(String productId, String user) {
		    try (Connection con = getConnection()) {
		        // Get user ID from username (you'll need to implement this method)
		        int userId = getUserIdByUsername(user);
		        
		        // Parse productId to an integer
		        int parsedProductId = Integer.parseInt(productId);
		        
		        // Check if the product already exists in the cart for the user
		        CartModel existingCartItem = getCartItem(parsedProductId, userId);
		        
		        if (existingCartItem != null) {
		            // If the product exists, update the quantity
		            int newQuantity = existingCartItem.getQuantity() + 1;
		            updateCartQuantity(parsedProductId, userId, newQuantity);
		            return 1; // Indicate success
		        } else {
		            // If the product doesn't exist, add it to the cart
		            // Prepare SQL statement
		            String sql = "INSERT INTO cart (Product_Id, User_Id, Quantity) VALUES (?, ?, 1)";
		            PreparedStatement statement = con.prepareStatement(sql);
		            statement.setInt(1, parsedProductId);
		            statement.setInt(2, userId);
		            
		            // Execute SQL statement
		            int rowsInserted = statement.executeUpdate();
		            
		            return rowsInserted;
		        }
		    } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
		        // Handle exceptions
		        ex.printStackTrace();
		        return 0; // Or return a specific error code
		    }
		}
	 
	 private CartModel getCartItem(int productId, int userId) {
		    try (Connection con = getConnection()) {
		        // Prepare SQL query to select the cart item
		        String query = "SELECT * FROM cart WHERE Product_Id = ? AND User_Id = ?";
		        PreparedStatement pstmt = con.prepareStatement(query);
		        pstmt.setInt(1, productId);
		        pstmt.setInt(2, userId);
		        
		        // Execute the query
		        ResultSet rs = pstmt.executeQuery();
		        
		        // Check if a row was returned
		        if (rs.next()) {
		            // If a row exists, create a CartModel object and populate it with the retrieved data
		            CartModel cartItem = new CartModel();
		            cartItem.setProductId(rs.getInt("Product_Id"));
		            cartItem.setUserId(rs.getInt("User_Id"));
		            cartItem.setQuantity(rs.getInt("Quantity"));
		            
		            return cartItem;
		        } else {
		            // If no row exists, return null indicating that the cart item does not exist
		            return null;
		        }
		    } catch (SQLException | ClassNotFoundException ex) {
		        // Handle exceptions
		        ex.printStackTrace();
		        return null;
		    }
		}


	 
	 public int addFeedback(UserModel userModel) {
 		// TODO Auto-generated method stub
 		try (Connection con = getConnection();
 				
 			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_FEEDBACK)) {
 			st.setString(1, userModel.getFeedbackSubName());
 			st.setString(2, userModel.getFeedbackSubEmail());
 			st.setString(3,userModel.getFeedbackSubDescription());
 			
 			return st.executeUpdate();
 		} catch (SQLException ex) {
 			ex.printStackTrace();
 			return -1;
 		}
 		catch (ClassNotFoundException ex) {
 			ex.printStackTrace();
 		}
 		
 		catch (Exception ex) {
 			ex.printStackTrace();
 			return -1;
 		}
 		return 0;
 				
 	}
	 
	 public  List<UserModel> getAllFeedbacks() {
     	List<UserModel> feedbacks = new ArrayList<>();
     	try (Connection con = getConnection();
     			PreparedStatement st = con.prepareStatement(StringUtils.GET_ALL_FEEDBACKS);
     			ResultSet rs = st.executeQuery()) {
     		while (rs.next()) {
     			UserModel feedback = new UserModel();
     			feedback.setFeedbackSubName(rs.getString("name"));
     			feedback.setFeedbackSubEmail(rs.getString("email"));
     			feedback.setFeedbackSubDescription(rs.getString("description"));
     			feedbacks.add(feedback);
     		
     		}
     	}
     		catch (SQLException ex) {
     			ex.printStackTrace();
     		}
     		catch (ClassNotFoundException ex) {
     			ex.printStackTrace();
     		}
     	return feedbacks;
     		
     	}
	 public int updateOrderStatus(String orderId, String newStatus) {
			try (Connection con = getConnection()) {
				PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_ORDER_STATUS);
				st.setString(1, newStatus);
				st.setString(2, orderId);
				int result = st.executeUpdate();
				return result > 0 ? 1 : 0; // Return 1 if update is successful, otherwise return 0
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace(); // Log the exception for debugging
				return -1; // Return -1 for any exceptions
			}
		}
	 public List<OrderModel> getOrdersByUserId(int userId) {
		    List<OrderModel> orders = new ArrayList<>();
		    try (Connection con = getConnection();
		         PreparedStatement st = con.prepareStatement(StringUtils.GET_ORDERS_BY_USER_ID)) {
		        // Set the user ID parameter in the prepared statement
		        st.setInt(1, userId);
		        
		        // Execute the query
		        try (ResultSet rs = st.executeQuery()) {
		            // Iterate through the result set
		            while (rs.next()) {
		                // Create a new OrderModel object for each row
		                OrderModel order = new OrderModel();
		                // Set the attributes of the OrderModel object using data from the result set
		                order.setOrderId(rs.getInt("order_id"));
						order.setProductName(rs.getString("product_name"));
						order.setQuantity(rs.getInt("quantity"));
						order.setPrice(rs.getInt("price"));
						order.setOrderDate(rs.getDate("order_date"));
						order.setStatus(rs.getString("status"));
		                
		                // Add the OrderModel object to the list
		                orders.add(order);
		            }
		        }
		    } catch (SQLException | ClassNotFoundException ex) {
		        // Handle any SQL or class loading exceptions
		        ex.printStackTrace(); // Log the exception for debugging
		    }
		    // Return the list of OrderModel objects
		    return orders;
		}
	 public int updateProfile(UserModel userModel) {
		    try (Connection con = getConnection()) {
		        PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_PROFILE);

		        // Set product attributes in the prepared statement
		        st.setString(1, userModel.getName());
		        st.setString(2, userModel.getEmail());
		        st.setString(3, userModel.getPhone());
		        st.setInt(4, userModel.getUserId());

		        System.out.println("Executing SQL update statement: " + st.toString()); // Log the SQL statement

		        int result = st.executeUpdate();

		        System.out.println("Rows affected by the update: " + result); // Log the number of rows affected

		        return result > 0 ? 1 : 0;
		    } catch (SQLException | ClassNotFoundException ex) {
		        ex.printStackTrace(); // Log the exception for debugging
		        return 1;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return 1;
		    }
		}
	 
	 

}
    