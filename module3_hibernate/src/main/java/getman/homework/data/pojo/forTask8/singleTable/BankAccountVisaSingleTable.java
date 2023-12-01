package getman.homework.data.pojo.forTask8.singleTable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("V")
public class BankAccountVisaSingleTable extends ClientSingleTable implements Serializable {
    @Column(name = "ACCOUNT_VISA")
    private Long accountVisa;
    @Column(name = "CARD_TYPE")
    private final String cardType = "Visa";

    public BankAccountVisaSingleTable(
            String id,
            Integer age,
            String name,
            String surname,
            long accountNumber) {
        super(id, age, name, surname);
        this.accountVisa = accountNumber;
    }

    public BankAccountVisaSingleTable() {
    }

    public long getAccountVisa() {
        return accountVisa;
    }

    public void setAccountVisa(long accountVisa) {
        this.accountVisa = accountVisa;
    }

    public String getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return "BankAccountPerClass{" +
                "accountNumber=" + accountVisa +
                "} " + super.toString();
    }
}
