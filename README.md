# Statewide-Individuals-Data-Management

## Overview

This Java program is a comprehensive system that manages data across several types of entities: Aadhar, PAN, Bank Accounts, and LPG subsidies. The project uses linked lists to manage and relate these entities, performing a variety of tasks such as data validation, identifying inconsistencies, and merging lists. 

It features seven main functionalities:
1. Identifying people with Aadhar cards but without PAN numbers.
2. Identifying people with multiple PAN numbers.
3. Identifying people with multiple bank accounts under multiple PAN numbers.
4. Printing details of people who have availed of LPG subsidies.
5. Identifying people who have savings greater than a specified amount and have availed of LPG subsidies.
6. Detecting and printing inconsistent data in the system.
7. Merging two bank account lists.

## Key Classes

### `Aadhar`
Represents an individual’s Aadhar details. Each `Aadhar` object has the following attributes:
- `name` (String): The name of the individual.
- `address` (String): The individual's address.
- `aadharNumber` (String): The unique Aadhar number.
- `next` (Aadhar): Pointer to the next `Aadhar` node in the linked list.

### `PAN`
Represents an individual’s PAN (Permanent Account Number) details. Each `PAN` object has:
- `name` (String): The individual's name.
- `address` (String): The individual's address.
- `panNumber` (String): The PAN number.
- `aadharNumber` (String): The linked Aadhar number.
- `next` (PAN): Pointer to the next `PAN` node.

### `BankAccount`
Represents an individual’s bank account details. Attributes include:
- `name` (String): The name on the bank account.
- `panNumber` (String): The PAN number linked to the bank account.
- `bank` (String): The bank name.
- `accountNumber` (String): The bank account number.
- `amount` (double): The account balance.
- `next` (BankAccount): Pointer to the next `BankAccount` node.

### `LPG`
Represents details of LPG subsidy information for an individual. Attributes include:
- `name` (String): The name of the individual.
- `accountNumber` (String): The linked bank account number.
- `subsidy` (String): Whether or not the individual avails of an LPG subsidy (YES/NO).
- `next` (LPG): Pointer to the next `LPG` node.

## Task-Specific Methods

### Task 1: Print People with Aadhar Numbers but No PAN
```java
public static void printPeopleWithNoPAN(AadharList aadharList, PANList panList)
```
This method checks the `AadharList` and prints details of individuals who do not have a PAN number associated with them.

### Task 2: Print People with Multiple PAN Numbers
```java
public static void printPeopleWithMultiplePANs(PANList panList)
```
This method prints the details of individuals who have multiple PAN numbers associated with their Aadhar numbers.

### Task 3: Print People with Multiple Bank Accounts under Multiple PAN Numbers
```java
public static void printPeopleWithMultipleBankAccounts(PANList panList, BankAccountList bankAccountList)
```
This method identifies people who have multiple bank accounts under more than one PAN number.

### Task 4: Print Details of a Person with LPG Subsidy
```java
public static void printDetailsOfPersonWithLPGSubsidy(AadharList aadharList, PANList panList, BankAccountList bankAccountList, LPGList lpgList)
```
This method prints the Aadhar, PAN, bank details, and account balance of individuals who have availed of an LPG subsidy.

### Task 5: Print People with Savings Greater than X and LPG Subsidy
```java
public static void printPeopleWithSavingsGreaterThanX(AadharList aadharList, PANList panList, BankAccountList bankAccountList, LPGList lpgList, double amountX)
```
Prints details of individuals with a total bank balance greater than a specified amount and who have also availed of an LPG subsidy.

### Task 6: Print Inconsistent Data
```java
public static void printInconsistentData(AadharList aadharList, PANList panList, BankAccountList bankAccountList, LPGList lpgList)
```
This method checks for and prints inconsistencies between the Aadhar, PAN, bank, and LPG lists.

### Task 7: Merge Two Bank Account Lists
```java
public static BankAccountList mergeBankAccountLists(BankAccountList list1, BankAccountList list2)
```
This method merges two bank account lists into one.

## Usage

### Step 1: Creating Lists
Initialize the lists for Aadhar, PAN, Bank Account, and LPG information:
```java
AadharList aadharList = new AadharList();
PANList panList = new PANList();
BankAccountList bankAccountList = new BankAccountList();
LPGList lpgList = new LPGList();
```

### Step 2: Populating Data
Data should be added to these lists using the respective `addLast()` methods:
```java
aadharList.addLast("John Doe", "123 Street", "Aadhar123");
panList.addLast("John Doe", "123 Street", "PAN123", "Aadhar123");
bankAccountList.addLast("John Doe", "PAN123", "BankName", "Acc123", 50000.0);
lpgList.addLast("John Doe", "Acc123", "YES");
```

### Step 3: Running Tasks
Invoke tasks like:
```java
printPeopleWithNoPAN(aadharList, panList);
printPeopleWithMultiplePANs(panList);
```

## Conclusion

This program demonstrates how linked lists can manage and interlink various entity types, offering solutions to detect data inconsistencies, identify important patterns (e.g., multiple PANs or bank accounts), and merge lists for effective data management.
