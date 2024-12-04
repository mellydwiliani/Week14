// Step 1: Interface BankAccount
interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}

// Step 2: Kelas BankAccount Asli
class RealBankAccount implements BankAccount {
    private double balance;

    public RealBankAccount() {
        this.balance = 0.0; // Saldo awal
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}

// Step 3: Kelas Proxy
class BankAccountProxy implements BankAccount {
    private RealBankAccount realBankAccount;
    private String username;
    private String password;

    public BankAccountProxy(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private boolean authenticate() {
        // Simulasi autentikasi pengguna
        // Misalnya, kita memeriksa username dan password
        return username.equals("admin") && password.equals("password123");
    }

    @Override
    public void deposit(double amount) {
        if (authenticate()) {
            if (realBankAccount == null) {
                realBankAccount = new RealBankAccount(); // Inisialisasi objek asli saat dibutuhkan
            }
            realBankAccount.deposit(amount);
        } else {
            System.out.println("Authentication failed. Access denied.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (authenticate()) {
            if (realBankAccount == null) {
                realBankAccount = new RealBankAccount();
            }
            realBankAccount.withdraw(amount);
        } else {
            System.out.println("Authentication failed. Access denied.");
        }
    }

    @Override
    public double getBalance() {
        if (authenticate()) {
            if (realBankAccount == null) {
                realBankAccount = new RealBankAccount();
            }
            return realBankAccount.getBalance();
        } else {
            System.out.println("Authentication failed. Access denied.");
            return -1; // Menandakan akses ditolak
        }
    }
}

// Step 4: Kode Klien
public class proxyMain {
    public static void main(String[] args) {
        // Membuat proxy dengan autentikasi pengguna
        BankAccount bankAccount = new BankAccountProxy("admin", "password123");

        // Melakukan transaksi setelah autentikasi berhasil
        System.out.println("Saldo saat ini: " + bankAccount.getBalance());
        bankAccount.deposit(500);
        System.out.println("Saldo setelah deposit: " + bankAccount.getBalance());
        bankAccount.withdraw(200);
        System.out.println("Saldo setelah penarikan: " + bankAccount.getBalance());

        // Coba akses dengan kredensial yang salah
        System.out.println("\nMencoba dengan kredensial salah:");
        BankAccount bankAccountWrong = new BankAccountProxy("user", "wrongpassword");
        bankAccountWrong.deposit(300);  // Akses ditolak
    }
}
