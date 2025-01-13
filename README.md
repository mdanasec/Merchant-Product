
# Merchant Product Management System

# Overview
The **Merchant Product Management System** is a robust and scalable web application designed to streamline the management of products by merchants. 
Developed using Java, Spring Boot, and React.js, this system enables merchants to easily manage their products, including creating, updating, and deleting product listings. The system also supports the filtering of products by brand, category, and merchant ID.

# Features
  **Product Management**: Add, update, and delete products.
  **Product Search**: Search products by brand, category, or merchant ID.
  **Product Details**: Retrieve detailed information about a specific product.
  **Responsive UI**: User-friendly interface built with React.js for seamless interaction.
  **API Integration**: RESTful APIs for efficient communication between frontend and backend.
  **Cross-Origin Support**: Supports CORS for local development with React frontend.

# Technologies Used
#  Backend  : 
  Java,  Spring Boot,  Spring Framework,  Spring Data JPA,  MySQL
# Frontend  : 
  React.js,  CSS
  
# API Testing : Postman for testing REST APIs

# RESTful API Endpoints

# Product Management
1. # Create a Product
     Endpoint : `POST /products/{merchant_id}`
     Description : Add a new product for a specific merchant.
     Request Body : `Product` object.
     Response : Returns the created product.

2. # Update a Product
     Endpoint : `PUT /updateproud/{id}`
     Description : Update an existing product by ID.
     Request Body : `Product` object.
     Response : Returns the updated product.

3. **Get Product by ID**
     Endpoint : `GET /product/{id}`
     Description : Retrieve a product by its unique ID.
     Response  : Returns the product details.

4. # Get All Products
     Endpoint  : `GET /products`
     Description  : Retrieve all products.
     Response  : Returns a list of all products.

5. # Delete Product by ID
     Endpoint  : `DELETE /product/{id}`
     Description  : Delete a product by its unique ID.
     Response  : Returns a confirmation message.

# Product Filtering
1.  Filter by Brand
      Endpoint  : `GET /product/by-brand/{brand}`
      Description  : Retrieve products by brand.
      Response: Returns a list of products matching the brand.

2. #Filter by Category
     Endpoint  : `GET /product/by-category/{category}`
     Description  : Retrieve products by category.
     Response  : Returns a list of products matching the category.

3. # Filter by Merchant ID
     Endpoint  : `GET /product/by-merchantid/{id}`
     Description  : Retrieve products by merchant ID.
     Response  : Returns a list of products for the specified merchant.

# Setup Instructions

# Prerequisites
  JDK 11 or higher
  MySQL database
  Node.js (for React frontend)

# Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/mdanasec/MerchantProduct.git
