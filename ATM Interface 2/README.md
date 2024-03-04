# ATM Interface Program

## Overview
This Java program simulates the functionalities of a real ATM machine, providing users with a secure and intuitive way to perform banking operations. Users can check their balance, withdraw money, deposit money, and view their account statement.

## Project Details
- **Functionalities:**
  - Check Balance
  - Withdraw Money
  - Deposit Money
  - Account Statement (transaction history)

- **User Authentication:**
  - Users need to provide their User ID and PIN for authentication.

- **Error Handling:**
  - Invalid PIN during authentication
  - Insufficient funds during withdrawal

## Project Structure
- `ATMInterface.java`: Main class for the ATM interface program. Handles user authentication, menu display, and functionalities.
- `User.java`: Class representing a user account. Includes methods for balance, deposit, withdrawal, and transaction history.
- `InsufficientFundsException.java`: Custom exception class for handling insufficient funds during a withdrawal.

## How to Run the Program
1. **Setup Java:**
   - Ensure you have Java installed on your machine.

2. **Compile and Run:**
   - Open a terminal or command prompt.
   - Navigate to the directory containing the Java files.
   - Compile the program using the command: `javac ATMInterface.java`
   - Run the program with: `java ATMInterface`

3. **Usage:**
   - Upon running, the program will prompt you to enter a User ID and PIN for authentication.
   - Follow the on-screen instructions to perform various operations.
   - Use option 4 to view the account statement (transaction history).
   - Use option 5 to log out and exit the program.

## Sample User Data
- Two sample user accounts are provided for testing:
  - User 1: User ID - 1234, PIN - 1234, Balance - $150.0
  - User 2: User ID - 5678, PIN - 5678, Balance - $100.0

## Customization
- You can customize the program by adding more user accounts or modifying the initial balances in the `ATMInterface` constructor.

## Contribution
Feel free to contribute to the project by providing enhancements, fixing bugs, or adding new features. Create a pull request with detailed information about the changes.


