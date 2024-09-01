import java.util.*;

public class LinkedListProject {
    class Aadhar {
        String name;
        String address;
        String aadharNumber;
        Aadhar next;
    
        Aadhar(String name, String address, String aadharNumber) {
            this.name = name;
            this.address = address;
            this.aadharNumber = aadharNumber;
            this.next = null;
        }
    }
    
    class PAN {
        String name;
        String address;
        String panNumber;
        String aadharNumber;
        PAN next;
    
        PAN(String name, String address, String panNumber, String aadharNumber) {
            this.name = name;
            this.address = address;
            this.panNumber = panNumber;
            this.aadharNumber = aadharNumber;
            this.next = null;
        }
    }
    
    class BankAccount {
        String name;
        String panNumber;
        String bank;
        String accountNumber;
        double amount;
        BankAccount next;
    
        BankAccount(String name, String panNumber, String bank, String accountNumber, double amount) {
            this.name = name;
            this.panNumber = panNumber;
            this.bank = bank;
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.next = null;
        }
    }
    
    class LPG {
        String name;
        String accountNumber;
        String subsidy;
        LPG next;
    
        LPG(String name, String accountNumber, String subsidy) {
            this.name = name;
            this.accountNumber = accountNumber;
            this.subsidy = subsidy;
            this.next = null;
        }
    }
    
    class AadharList {
        Aadhar head;
    
        AadharList() {
            this.head = null;
        }
    
        void addLast(String name, String address, String aadharNumber) {
            Aadhar newNode = new Aadhar(name, address, aadharNumber);
            if (head == null) {
                head = newNode;
            } else {
                Aadhar current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
    }
    
    class PANList {
        PAN head;
    
        PANList() {
            this.head = null;
        }
    
        void addLast(String name, String address, String panNumber, String aadharNumber) {
            PAN newNode = new PAN(name, address, panNumber, aadharNumber);
            if (head == null) {
                head = newNode;
            } else {
                PAN current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
    }
    
    class BankAccountList {
        BankAccount head;
    
        BankAccountList() {
            this.head = null;
        }
    
        void addLast(String name, String panNumber, String bank, String accountNumber, double amount) {
            BankAccount newNode = new BankAccount(name, panNumber, bank, accountNumber, amount);
            if (head == null) {
                head = newNode;
            } else {
                BankAccount current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
    }
    
    class LPGList {
        LPG head;
    
        LPGList() {
            this.head = null;
        }
    
        void addLast(String name, String accountNumber, String subsidy) {
            LPG newNode = new LPG(name, accountNumber, subsidy);
            if (head == null) {
                head = newNode;
            } else {
                LPG current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
    }

    // Task 1: Print names, addresses and Aadhar numbers of people with no PAN numbers
    public static void printPeopleWithNoPAN(AadharList aadharList, PANList panList) {
        Set<String> panAadharNumbers = new HashSet<>();
        PAN pan = panList.head;
        while (pan != null) {
            panAadharNumbers.add(pan.aadharNumber);
            pan = pan.next;
        }
        Aadhar aadhar = aadharList.head;
        while (aadhar != null) {
            if (!panAadharNumbers.contains(aadhar.aadharNumber)) {
                System.out.println(aadhar.name + ", " + aadhar.address + ", " + aadhar.aadharNumber);
            }
            aadhar = aadhar.next;
        }
    }

    // Task 2: Print names, addresses and Aadhar numbers of people with multiple PAN numbers
    public static void printPeopleWithMultiplePANs(PANList panList) {
        Map<String, Set<String>> aadharToPANs = new HashMap<>();
        PAN pan = panList.head;
        while (pan != null) {
            aadharToPANs.computeIfAbsent(pan.aadharNumber, k -> new HashSet<>()).add(pan.panNumber);
            pan = pan.next;
        }
        for (Map.Entry<String, Set<String>> entry : aadharToPANs.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("Aadhar Number: " + entry.getKey() + " has multiple PAN numbers: " + entry.getValue());
            }
        }
    }

    // Task 3: Print names, addresses and Aadhar numbers of people with multiple bank accounts under multiple PAN numbers
    public static void printPeopleWithMultipleBankAccounts(PANList panList, BankAccountList bankAccountList) {
        Map<String, Set<String>> aadharToPANs = new HashMap<>();
        PAN pan = panList.head;
        while (pan != null) {
            aadharToPANs.computeIfAbsent(pan.aadharNumber, k -> new HashSet<>()).add(pan.panNumber);
            pan = pan.next;
        }
        Map<String, Set<String>> panToAccounts = new HashMap<>();
        BankAccount account = bankAccountList.head;
        while (account != null) {
            panToAccounts.computeIfAbsent(account.panNumber, k -> new HashSet<>()).add(account.accountNumber);
            account = account.next;
        }
        for (Map.Entry<String, Set<String>> entry : aadharToPANs.entrySet()) {
            if (entry.getValue().size() > 1) {
                for (String panNumber : entry.getValue()) {
                    if (panToAccounts.containsKey(panNumber) && panToAccounts.get(panNumber).size() > 1) {
                        System.out.println("Aadhar Number: " + entry.getKey() + " has multiple PAN numbers with multiple bank accounts.");
                    }
                }
            }
        }
    }

    // Task 4: Print details of a person who has availed LPG subsidy
    public static void printDetailsOfPersonWithLPGSubsidy(AadharList aadharList, PANList panList, BankAccountList bankAccountList, LPGList lpgList) {
        Map<String, Aadhar> aadharMap = new HashMap<>();
        Aadhar aadhar = aadharList.head;
        while (aadhar != null) {
            aadharMap.put(aadhar.aadharNumber, aadhar);
            aadhar = aadhar.next;
        }
        Map<String, PAN> panMap = new HashMap<>();
        PAN pan = panList.head;
        while (pan != null) {
            panMap.put(pan.panNumber, pan);
            pan = pan.next;
        }
        Map<String, BankAccount> bankAccountMap = new HashMap<>();
        BankAccount bankAccount = bankAccountList.head;
        while (bankAccount != null) {
            bankAccountMap.put(bankAccount.accountNumber, bankAccount);
            bankAccount = bankAccount.next;
        }
        LPG lpg = lpgList.head;
        while (lpg != null) {
            if (lpg.subsidy.equals("YES")) {
                BankAccount acc = bankAccountMap.get(lpg.accountNumber);
                PAN panDetails = panMap.get(acc.panNumber);
                Aadhar aadharDetails = aadharMap.get(panDetails.aadharNumber);
                System.out.println("Aadhar: " + aadharDetails.aadharNumber + ", PAN: " + panDetails.panNumber + ", Bank: " + acc.bank + ", Account: " + acc.accountNumber + ", Amount: " + acc.amount);
            }
            lpg = lpg.next;
        }
    }

    // Task 5: Print names, addresses and Aadhar numbers of people with total savings greater than amount X and have availed LPG subsidy
    public static void printPeopleWithSavingsGreaterThanX(AadharList aadharList, PANList panList, BankAccountList bankAccountList, LPGList lpgList, double amountX) {
        Map<String, Aadhar> aadharMap = new HashMap<>();
        Aadhar aadhar = aadharList.head;
        while (aadhar != null) {
            aadharMap.put(aadhar.aadharNumber, aadhar);
            aadhar = aadhar.next;
        }
        Map<String, List<BankAccount>> panToBankAccounts = new HashMap<>();
        BankAccount account = bankAccountList.head;
        while (account != null) {
            panToBankAccounts.computeIfAbsent(account.panNumber, k -> new ArrayList<>()).add(account);
            account = account.next;
        }
        LPG lpg = lpgList.head;
        while (lpg != null) {
            if (lpg.subsidy.equals("YES")) {
                double totalAmount = 0;
                for (List<BankAccount> accounts : panToBankAccounts.values()) {
                    for (BankAccount acc : accounts) {
                        if (acc.accountNumber.equals(lpg.accountNumber)) {
                            totalAmount += acc.amount;
                        }
                    }
                }
                if (totalAmount > amountX) {
                    for (List<BankAccount> accounts : panToBankAccounts.values()) {
                        for (BankAccount acc : accounts) {
                            if (acc.accountNumber.equals(lpg.accountNumber)) {
                                PAN pan = panList.head;
                                while (pan != null && !pan.panNumber.equals(acc.panNumber)) {
                                    pan = pan.next;
                                }
                                if (pan != null) {
                                    Aadhar aadharDetails = aadharMap.get(pan.aadharNumber);
                                    System.out.println(aadharDetails.name + ", " + aadharDetails.address + ", " + aadharDetails.aadharNumber);
                                }
                            }
                        }
                    }
                }
            }
            lpg = lpg.next;
        }
    }

    // Task 6: Print inconsistent data
    public static void printInconsistentData(AadharList aadharList, PANList panList, BankAccountList bankAccountList, LPGList lpgList) {
        Map<String, Aadhar> aadharMap = new HashMap<>();
        Aadhar aadhar = aadharList.head;
        while (aadhar != null) {
            aadharMap.put(aadhar.aadharNumber, aadhar);
            aadhar = aadhar.next;
        }
        PAN pan = panList.head;
        while (pan != null) {
            Aadhar aadharDetails = aadharMap.get(pan.aadharNumber);
            if (!pan.name.equals(aadharDetails.name) || !pan.address.equals(aadharDetails.address)) {
                System.out.println(aadharDetails.name + ", " + aadharDetails.address + ", " + aadharDetails.aadharNumber);
            }
            pan = pan.next;
        }
        BankAccount account = bankAccountList.head;
        while (account != null) {
            pan = panList.head;
            while (pan != null) {
                if (account.panNumber.equals(pan.panNumber) && !account.name.equals(pan.name)) {
                    System.out.println(account.name + ", " + account.panNumber);
                }
                pan = pan.next;
            }
            account = account.next;
        }
        LPG lpg = lpgList.head;
        while (lpg != null) {
            account = bankAccountList.head;
            while (account != null) {
                if (lpg.accountNumber.equals(account.accountNumber) && !lpg.name.equals(account.name)) {
                    System.out.println(lpg.name + ", " + lpg.accountNumber);
                }
                account = account.next;
            }
            lpg = lpg.next;
        }
    }

    // Task 7: Merge two bank account lists
    public static BankAccountList mergeBankAccountLists(BankAccountList list1, BankAccountList list2) {
        BankAccountList mergedList = new BankAccountList();
        BankAccount current = list1.head;
        while (current != null) {
            mergedList.addLast(current.name, current.panNumber, current.bank, current.accountNumber, current.amount);
            current = current.next;
        }
        current = list2.head;
        while (current != null) {
            mergedList.addLast(current.name, current.panNumber, current.bank, current.accountNumber, current.amount);
            current = current.next;
        }
        return mergedList;
    }
    
    public static void main(String[] args) {
        AadharList aadharList = new AadharList();
        PANList panList = new PANList();
        BankAccountList bankAccountList = new BankAccountList();
        LPGList lpgList = new LPGList();

        // Add example data (in real usage, this should be populated from an external source)
        
        // Populate the lists (this should be done based on the real data)

        // Task 1: People with Aadhar numbers but no PAN numbers
        printPeopleWithNoPAN(aadharList, panList);

        // Task 2: People with multiple PAN numbers
        printPeopleWithMultiplePANs(panList);

        // Task 3: People with multiple bank accounts registered under multiple PAN numbers
        printPeopleWithMultipleBankAccounts(panList, bankAccountList);

        // Task 4: Details of a person who has availed LPG subsidy
        printDetailsOfPersonWithLPGSubsidy(aadharList, panList, bankAccountList, lpgList);

        // Task 5: People with total savings greater than amount X and have availed LPG subsidy
        double amountX = 10000; // Example amount
        printPeopleWithSavingsGreaterThanX(aadharList, panList, bankAccountList, lpgList, amountX);

        // Task 6: Inconsistent data
        printInconsistentData(aadharList, panList, bankAccountList, lpgList);

        // Task 7: Merge two bank account lists
        BankAccountList bankAccountList2 = new BankAccountList();
        BankAccountList mergedBankAccounts = mergeBankAccountLists(bankAccountList, bankAccountList2);
    }
}
