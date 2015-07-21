package fr.geccel.qcm.component.service;

import java.util.List;

import fr.geccel.qcm.exception.FunctionalException;
import fr.geccel.qcm.model.User;

/**
 * Service des utilisateurs.
 */
public interface IUserService {

	/**
	 * Vérifie les droits d'un utilisateur
	 * 
	 * @param user
	 * @return Utilisateur
	 */
	User checkCredentials(User user) throws FunctionalException;

	/**
	 * Créer le compte d'un utilisateur
	 * 
	 * @param user
	 * @return Utilisateur
	 */
	User createAccount(User user) throws FunctionalException;

	/**
	 * Renvoie l'utilisateur
	 * 
	 * @param login
	 * @return Utilisateur
	 */
	User getById(Long id);

	/**
	 * Renvoie la liste des utilisateurs
	 * 
	 * @param login
	 * @return Liste des utilisateurs
	 */
	List<User> getAll();

	
	User updateAccount(User user);

	/**
	 * Compte le nombre d'utilisateurs.
	 * 
	 * @return Nombre d'utilisateurs
	 */
	Long getNbUsers();
}
