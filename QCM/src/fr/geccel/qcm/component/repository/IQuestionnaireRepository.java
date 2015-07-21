package fr.geccel.qcm.component.repository;

import java.util.List;

import fr.geccel.qcm.model.Questionnaire;

public interface IQuestionnaireRepository extends
		IAbstractRepository<Questionnaire> {

	/**
	 * Renvoie le questionnaire pour un utilisateur
	 * 
	 * @param questionnaireId
	 *            , id
	 * @return Questionnaire
	 */
	Questionnaire loadForUser(Long questionnaireId, Long id);

	/**
	 * Renvoie la liste des questionnaires
	 * 
	 * @param page
	 *            , admin
	 * @return Liste des questionnaires pagin�e
	 */
	List<Questionnaire> paginateListQuestionnaire(Integer page, boolean admin);

	/**
	 * Renvoie le nombre de questionnaires
	 * 
	 * @return Nombre de questionnaires
	 */
	Long getNbQuestionnaires();

	/**
	 * Renvoie la liste des questionnaires r�cents
	 * 
	 * @param admin
	 * @return Liste des questionnaires r�cents (MAX 5)
	 */
	List<Questionnaire> getLastQuestionnaires(boolean admin);

	/**
	 * Renvoie la liste des questionnaires populaires
	 * 
	 * @param admin
	 * @return Liste des questionnaires populaires (MAX 5)
	 */
	List<Questionnaire> getPopularQuestionnaires(boolean admin);

	/**
	 * Renvoie le nombre de questionnaires en cours
	 * 
	 * @param admin
	 * @return Liste des questionnaires en cours
	 */
	Long getNbQuestionnairesValid(boolean admin);

}
