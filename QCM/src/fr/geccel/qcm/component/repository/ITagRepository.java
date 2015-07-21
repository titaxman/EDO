package fr.geccel.qcm.component.repository;

import java.util.Collection;
import java.util.List;

import fr.geccel.qcm.model.Questionnaire;
import fr.geccel.qcm.model.Tag;

public interface ITagRepository extends IAbstractRepository<Tag> {

	/**
	 * Renvoie la collection des tags tri�es par nombre d'apparition
	 * 
	 * @return Collection des tags
	 */
	Collection<Tag> loadAllOrderedByQuestionnairesSize();

	/**
	 * Renvoie la liste pagin�e des questionnaires pour un tag
	 * 
	 * @param idTag, page, isAdmin
	 * @return Liste des questionnaires pour un tag
	 */
	List<Questionnaire> getListQuestionnaireByTag(String idTag, Integer page,
			boolean isAdmin);

	/**
	 * Renvoie le nombre de questionnaires actifs pour un tag
	 * 
	 * @param idTag, isAdmin
	 * @return Liste des questionnaires actifs pour un tag
	 */
	Long getListNbQuestionnairesValid(String idTag,
			boolean isAdmin);

}
