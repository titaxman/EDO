package fr.geccel.qcm.component.service.impl;

import static org.apache.commons.codec.digest.DigestUtils.shaHex;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.geccel.qcm.component.repository.IUserRepository;
import fr.geccel.qcm.component.service.IUserService;
import fr.geccel.qcm.exception.FunctionalException;
import fr.geccel.qcm.model.Result;
import fr.geccel.qcm.model.User;

/**
 * Service des utilisateurs (implementation).
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {

	/**
	 * Depot des utilisateurs.
	 */
	@Autowired
	private IUserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User checkCredentials(User user) throws FunctionalException {
		User databaseUser = userRepository.loadByLogin(user.getLogin());
		String passwordHash = shaHex(user.getPassword());

		if (databaseUser == null
				|| !databaseUser.getPassword().equals(passwordHash)) {
			throw new FunctionalException("wrong credentials");
		}

		return userRepository.unproxy(databaseUser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public User createAccount(User user) throws FunctionalException {
		User databaseUser = userRepository.loadByLogin(user.getLogin());

		if (databaseUser != null) {
			throw new FunctionalException("login already registered");
		}

		user.setPassword(shaHex(user.getPassword()));
		return userRepository.unproxy(userRepository.save(user));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getById(Long id) {
		User user = userRepository.load(id);

		if (user != null) {
			for (Result result : user.getResults()) {
				result.getQuestionnaire().getQuestions().size(); // Lazy
				result.getQuestionnaire().getTags().size(); // Lazy
				result.getAnswers().size(); // Lazy
			}

			// Les meilleurs scores en premier
			Collections.sort(user.getResults());
			
			user = userRepository.unproxy(user);
		}
		
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> getAll() {
		return userRepository.loadAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public User updateAccount(User user) {
		userRepository.update(user);
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNbUsers() {
		return userRepository.getNbUsers();
	}
}
