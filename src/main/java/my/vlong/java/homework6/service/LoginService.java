package my.vlong.java.homework6.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import my.vlong.java.homework6.dto.AccountDTO;
import my.vlong.java.homework6.entity.Account;
import my.vlong.java.homework6.exception.LoginException;
import my.vlong.java.homework6.repository.AccountRepositoryImpl;
import my.vlong.java.homework6.repository.IAccountRepository;
import my.vlong.java.homework6.mapper.AccountMapper;
import my.vlong.java.homework6.request.LoginRequest;

public class LoginService {

    private final IAccountRepository accountRepository;

    public LoginService() {
        accountRepository = new AccountRepositoryImpl();
    }

    public Optional<AccountDTO> login(LoginRequest loginRequest) throws LoginException {

        if (!isValidate(loginRequest)) {
            throw new LoginException("Email or password is not valid");
        }

        AccountDTO accountDTO = null;

        Optional<Account> accountOptional = accountRepository.findByEmail(loginRequest.getEmail());

        if (accountOptional.isPresent()) {
            System.out.println("XXXXXXXXXXXXX");
            Account account = accountOptional.get();
            System.out.println(account);
            String passwordHash = hashPassword(loginRequest.getPassword());
            System.out.println("hash : " + passwordHash);
            if (passwordHash.equals(account.getPassword())) {
                accountDTO = AccountMapper.INSTANCE.toDTO(account);
            }
        }

        return Optional.ofNullable(accountDTO);
    }

    private String hashPassword(String password) {
        String hashString = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] hash = messageDigest.digest();
            hashString = DatatypeConverter.printHexBinary(hash).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }

        return hashString;
    }

    private boolean isValidate(LoginRequest request) {
        if (request == null) {
            return false;
        }

        if (request.getEmail() == null || "".equals(request.getEmail())) {
            return false;
        }

        if (request.getPassword() == null || "".equals(request.getPassword())) {
            return false;
        }

        return true;
    }
}
