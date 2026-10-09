package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exceptions.RegistrationException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_CREDENTIAL_LENGTH = 6;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 125;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RegistrationException("User cannot be null");
        }

        if (storageDao.get(user.getLogin()) != null) {
            throw new RegistrationException("Can't register user because the user already exists");
        }

        if (user.getLogin() == null) {
            throw new RegistrationException("Login cannot be null");
        }

        if (user.getLogin().length() < MIN_CREDENTIAL_LENGTH) {
            throw new RegistrationException("Login must be at least 6 character long");
        }

        if (user.getPassword() == null) {
            throw new RegistrationException("Password cannot be null");
        }

        if (user.getPassword().length() < MIN_CREDENTIAL_LENGTH) {
            throw new RegistrationException("Password must be at least 6 character long");
        }

        if (user.getAge() < MIN_AGE || user.getAge() > MAX_AGE) {
            throw new RegistrationException("User age must be between 18 and 125 years old");
        }

        return storageDao.add(user);
    }
}
