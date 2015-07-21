package fr.geccel.qcm.component.repository;

import fr.geccel.qcm.model.User;

public interface IUserRepository extends IAbstractRepository<User> {

	/**
	 * Renvoie l'utilisateur
	 * 
	 * @param login
	 * @return Utilisateur
	 */
	User loadByLogin(String login);
	
	/**
	 * Renvoie le nombre d'utilisateurs
	 * 
	 * @return Nombre d'utilisateurs
	 */
	Long getNbUsers();

}
