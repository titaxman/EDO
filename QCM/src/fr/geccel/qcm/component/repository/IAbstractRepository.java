package fr.geccel.qcm.component.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Depot generique.
 * 
 * @param <T>
 *            Classe entite
 */
public interface IAbstractRepository<T> {

	/**
	 * Retourne toutes les entites de type <code>T</code>.
	 * 
	 * @return La liste des entites de type <code>T</code>
	 */
	public List<T> loadAll();

	/**
	 * Retourne l'entite correspondant a la cle.
	 * 
	 * @param primaryKey
	 *            La cle
	 * @return L'entite
	 */
	public T load(Serializable primaryKey);

	/**
	 * Sauvegarde l'entit� de type <code>T</code>.
	 * 
	 * @return l'entit� de type <code>T</code>
	 */
	public T save(T entity);

	/**
	 * Sauvegarde l'entit� de type <code>T</code>.
	 */
	public void saveOrUpdate(T entity);

	/**
	 * Sauvegarde l'entit� de type <code>T</code>.
	 */
	public void update(T entity);

	/**
	 * Supprime l'entit� de type <code>T</code>.
	 */
	public void delete(T entity);

	/**
	 * D�connecte l'entit� de type <code>T</code>.
	 */
	public void evict(T entity);

	/**
	 * Recharge l'entit� de type <code>T</code>.
	 */
	public void refresh(T entity);
	
	/**
	 * Merge l'entit� de type <code>T</code>.
	 */
	public T merge(T entity);
	
	/**
	 * Enl�ve les r�f�rences Hibernate de l'entit� de type <code>T</code>.
	 * Posait probl�me dans la session Spring
	 */
	public T unproxy(T entity);
}
