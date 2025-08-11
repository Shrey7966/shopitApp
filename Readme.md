# shopitapp

I Functional Requirements

A. Key Actors

##Back End

1. Admin
2. Editor
3. Salesperson
4. Shipper
5. Assistant,

	##Back End  >> Use cases for Editors
	1. Manage categories
	2. Manage articles
	3. Manage menus
	4. Manage Products
	5. Manage brands

	##Back End  >> Use cases for Sales person
	1. Manage Shipping rates
	2. Manage customers
	3. Manage sales report
	4. View Products
	5. Manage orders
	6. Update product prices

	##Back End  >> Use cases for Shippers
	1. View Products
	2. View Orders
	3. Update order status

	##Back End  >> Use cases for Shippers
	1. Manage Everything such as Manage settings, Manage users


##Front End

1. Visitor
2. Customer

	##Front End  >> Use cases for visitors
	1. Signup
	2. View Products
	3. View Articles

	##Front End  >> Use cases for customers 
	1. View orders
	2. Manage Shopping cart
	3. Checkout
	4. Manage Addresses
	5. Vote questions
	6. Vote reviews
	7. View Articles
	8. View Products
	9. Post reviews


II Technical requirements

1. Accessibility
2. Availability 
3. Security
4. Performance
5. Scalability


III System Architecture 

1. Local Development -> Local File systems + JARs connected to MySQL Database
2. Production -> AWS s3 storage service + JARs in DYNOS connected to MySQL Database which comes as add-on on HEROKU cloud platform

IV Software versions used as on 23-07-2025

1. Oracle JDK 21
2. STS 4.31.0
3. MySQL 8.0.43
4. Git 2.50.1
5. Maven 3.9.11

V Multi-Module Project schema

	ShopitApp, root project (pom); pom.xml
		- ShopitCommon, module 1 (jar); pom.xml
		- ShopitWebParent, module 2 (pom); pom.xml
			-ShopitBackEnd, module 2a (jar); pom.xml
			-ShopitFrontEnd, module 2b (jar); pom.xml

VI Application Architecture (Logical Layers)

1. View Layer ( Thymeleaf, HTML)
2. Controller Layer ( MVC, REST )
3. Sercice Layer (Business classes)
4. Repository Layer ( Entities & Interfaces)
5. Spring Data JPA
6. Hibernate framework Communicates with Database using JDBC Driver


VII Requirements

A. Admin Home Page

	- Create header (Logo and Top level menu), Page heading and Footer
	- Display name of the currently logged-in user
	- Display Summary Boxes


IX Why this ?

A. Bootstrap
	- Free and open source CSS framework
	- Responsive, contains CSS and JS based design templates for forms, buttons, navigation, 			pagination, icons
	- Reduce Development time
B. JQuery

	- Free and open source JS library
	- Helps to simplify HTML DOM tree traversal and manipulation, event handling, CSS animation, 			Ajax.
	-Provides JS API.
C. BCrypt

	- Its Provided by Spring Security
	- BCrypt is a password-hashing function based on BlowFish (Symmetric-key block cipher ) and 		crypt function in Unix.
	- It's high secure and adaptive.

