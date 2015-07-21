package fr.geccel.qcm.component.service;

import java.util.Collection;
import java.util.List;

import fr.geccel.qcm.model.Questionnaire;
import fr.geccel.qcm.model.Tag;

/**
 * Service des tags.
 */
public interface ITagService {

	/**
	 * Retourne la liste des tags.
	 * 
	 * @return Liste des tags
	 */
	Collection<Tag> getAll();
	
	/**
	 * Retourne une liste de questionnaire pour un tag.
	 * 
	 * @return Liste des questionnaire
	 */
	List<Questionnaire> getListQuestionnaireByTag(String idTag, Integer page, boolean isAdmin);
	
	/**
	 * Retourne le nombre de questionnaires pour un tag.
	 * 
	 * @return Nombre de questionnaires
	 */
	Long getNbQuestionnairesValid(String idTag, boolean isAdmin);
	
}
