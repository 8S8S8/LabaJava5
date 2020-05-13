import java.util.ArrayList;
import Exception.*;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AccountManager {
    private Account[] accounts;
    private int size;

    public AccountManager(int size) {
        setAccounts(new Account[size]);
        size = 0;
    }

    public AccountManager(Account[] accounts) {
        setAccounts(accounts);
        size = accounts.length;
    }

    public boolean add(Account account) throws DuplicateAccountNumberException {
        if(containNumber(account)) throw new DuplicateAccountNumberException();
        Objects.requireNonNull(account);
        if (checkFreeSpace()) {
            extend();
            return false;
        } else {
            addAccount(account);
            size++;
            return true;
        }
    }

    public boolean add(Account account, int index) throws DuplicateAccountNumberException {
        if(containNumber(account)) throw new DuplicateAccountNumberException();
        Objects.requireNonNull(account);
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (accounts[index] != null) {
            return false;
        } else {
            accounts[index] = account;
            return true;
        }
    }

    private void addAccount(Account account) {
        Objects.requireNonNull(account);
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                return;
            }
        }
    }

    public Account get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return accounts[index];
    }

    public Account get(long number) {
        if(number<001000000000001L || number>999999999999999L) throw new IllegalAccountNumber();
        for (Account account : accounts) {
            if (account.getNumber() == number) {
                return account;
            }
        }
        throw new NoSuchElementException();
    }

    public Account set(int index, Account newAccount) throws DuplicateAccountNumberException {
        if(containNumber(newAccount)) throw new DuplicateAccountNumberException();
        Objects.requireNonNull(newAccount);
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Account forReturn = accounts[index];
        accounts[index] = newAccount;
        return forReturn;
    }

    public Account delete(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Account forReturn = accounts[index];
        accounts[index] = null;
        return forReturn;
    }

    private boolean checkFreeSpace() {
        return size == accounts.length;
    }

    private void extend() {
        Account[] newAccounts = new Account[accounts.length * 2];
        for (int i = 0; i < accounts.length; i++) {
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts;
    }

    public Account[] getAccounts() {
        trim();
        Account[] returnAccounts = new Account[size];
        for (int i = 0; i < returnAccounts.length; i++) {
            returnAccounts[i] = accounts[i];
        }
        return returnAccounts;
    }

    public Tariff getTariff(int accountIndex) {
        if (accountIndex < 0 || accountIndex >= size) throw new IndexOutOfBoundsException();
        if (accounts[accountIndex] != null) {
            return accounts[accountIndex].getTariff();
        } else {
            return null;
        }
    }

    public Tariff changeTariff(int accountIndex, Tariff newTariff) {
        if (accountIndex < 0 || accountIndex >= size) throw new IndexOutOfBoundsException();
        Tariff forReturn = getTariff(accountIndex);
        accounts[accountIndex].setTariff(newTariff);
        return forReturn;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    public int getAccountsCount() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void trim() {
        for (int i = 0; i < accounts.length; i++) {
            for (int j = 0; j < accounts.length - 1; j++) {
                if (accounts[j] == null) {
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = null;
                }
            }
        }
    }

    public Account[] getAccountWithTypedServices(ServiceTypes type) {
        Objects.requireNonNull(type);
        ArrayList<Account> list = new ArrayList<>();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getTariff().getTypedServices(type) != null) {
                list.add(accounts[i]);
            }
        }
        return (Account[]) list.toArray();
    }

    public IndividualAccount[] getIndividualAccounts() {
        ArrayList<IndividualAccount> list = new ArrayList<>();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i] instanceof IndividualAccount) {
                list.add((IndividualAccount) accounts[i]);
            }
        }
        return (IndividualAccount[]) list.toArray();
    }

    public EntityAccount[] getEntityAccounts() {
        ArrayList<EntityAccount> list = new ArrayList<>();
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i] instanceof EntityAccount) {
                list.add((EntityAccount) accounts[i]);
            }
        }
        return (EntityAccount[]) list.toArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < accounts.length; i++) {
            sb.append(accounts[i].toString());
        }
        return sb.toString();
    }

    public boolean remove(Account account) {
        Objects.requireNonNull(account);
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                accounts[i] = null;
                return true;
            }
        }
        throw new NoSuchElementException();
    }

    public int indexOf(Account account) {
        Objects.requireNonNull(account);
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    public boolean containNumber(Account account){
        for(Account buf:accounts){
            if(buf.getNumber() == account.getNumber()) return true;
        }
        return false;
    }
}
