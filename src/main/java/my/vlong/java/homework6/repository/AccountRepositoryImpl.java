package my.vlong.java.homework6.repository;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import my.vlong.java.homework6.entity.Account;

public class AccountRepositoryImpl implements IAccountRepository {

    private EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public AccountRepositoryImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CMSPU");
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        Account account = null;

        if (email == null || "".equals(email)) {
            return Optional.ofNullable(account);
        }

        entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Account> createQuery = entityManager.createQuery("SELECT a FROM Account a WHERE a.email = :email", Account.class);
        createQuery.setParameter("email", email);

        account = createQuery.getSingleResult();

        return Optional.ofNullable(account);
    }

}
