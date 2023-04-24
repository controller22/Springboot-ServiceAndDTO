package shop.mtcoding.servicebank.dto.account;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.servicebank.core.util.MyDateUtils;
import shop.mtcoding.servicebank.model.account.Account;
import shop.mtcoding.servicebank.model.transaction.Transaction;
import shop.mtcoding.servicebank.model.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class AccountResponse {

    @Setter @Getter
    public static class SaveOutDTO {
        private Long id;
        private Integer number;
        private Long balance;

        public SaveOutDTO(Account account) {
            this.id = account.getId();
            this.number = account.getNumber();
            this.balance = account.getBalance();
        }
    }

    @Setter @Getter
    public static class DetailOutDTO {
        private Long id;
        private String fullName;
        private Integer number;
        private Long balance;
        private String createdAt;

        public DetailOutDTO(Account account) {
            this.id = account.getId();
            this.fullName = account.getUser().getFullName();
            this.number = account.getNumber();
            this.balance = account.getBalance();
            this.createdAt = MyDateUtils.toStringFormat(account.getCreatedAt());
        }
    }

    @Setter @Getter
    public static class ListOutDTO {
        private String fullName;
        private List<AccountDTO> accounts;

        public ListOutDTO(User user, List<Account> accounts) {
            this.fullName = user.getFullName();
            this.accounts = accounts.stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
        }

        @Setter @Getter
        public class AccountDTO {
            private Long id;
            private Integer number;
            private Long balance;

            public AccountDTO(Account account) {
                this.id = account.getId();
                this.number = account.getNumber();
                this.balance = account.getBalance();
            }
        }
    }

    @Setter
    @Getter
    public static class TransferOutDTO {
        private Long id;
        private Integer number;
        private Long balance;
        private TransactionDTO transaction;

        public TransferOutDTO(Account account, Transaction transaction) {
            this.id = account.getId();
            this.number = account.getNumber();
            this.balance = account.getBalance();
            this.transaction = new TransactionDTO(transaction);
        }

        @Setter
        @Getter
        public class TransactionDTO {
            private Long id;
            private String sender;
            private String receiver;
            private Long amount;
            private Long balance;
            private String createdAt;

            public TransactionDTO(Transaction transaction) {
                this.id = transaction.getId();
                this.sender = transaction.getWithdrawAccount().getNumber()+"";
                this.receiver = transaction.getDepositAccount().getNumber()+"";
                this.amount = transaction.getAmount();
                this.balance = transaction.getWithdrawAccountBalance();
                this.createdAt = MyDateUtils.toStringFormat(transaction.getCreatedAt());
            }
        }
    }
}
