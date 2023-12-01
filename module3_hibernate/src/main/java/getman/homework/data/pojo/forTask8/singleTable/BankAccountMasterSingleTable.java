package getman.homework.data.pojo.forTask8.singleTable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("M")
public class BankAccountMasterSingleTable extends ClientSingleTable implements Serializable {
    @Column(name = "CARD_TYPE")
    private final String cardType = "MasterCard";

    @Column(name = "ACCOUNT_MASTERCARD")

    private Long accountMasterCard;

    public BankAccountMasterSingleTable(
            String id,
            Integer age,
            String name,
            String surname,
            long accountNumber
    ) {
        super(id, age, name, surname);
        this.accountMasterCard = accountNumber;
    }

    public BankAccountMasterSingleTable() {
    }

    public String getCardType() {
        return cardType;
    }

    public long getAccountMasterCard() {
        return accountMasterCard;
    }

    public void setAccountMasterCard(long accountMasterCard) {
        this.accountMasterCard = accountMasterCard;
    }
}
