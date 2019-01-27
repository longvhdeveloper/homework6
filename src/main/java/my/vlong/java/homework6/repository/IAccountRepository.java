package my.vlong.java.homework6.repository;

import java.util.Optional;
import my.vlong.java.homework6.entity.Account;

public interface IAccountRepository {

    Optional<Account> findByEmail(String email);
}
