package fr.geccel.qcm.component.service;

import java.util.List;

import fr.geccel.qcm.model.Questionnaire;
import fr.geccel.qcm.model.Result;
import fr.geccel.qcm.model.User;

public interface IQuestionnaireService {

	Result getResultByUserAndQuestionnaireId(User user, Long questionnaireId);

	/**
	 * Renvoie le questionnaire pour un utilisateur
	 * 
	 * @param questionnaireId
	 *            , id
	 * @return Questionnaire
	 */
	Questionnaire getQuestionnaireByIdAndUser(Long id, User user);

	/**
	 * Renvoie le questionnaire
	 * 
	 * @param questionnaireId
	 * @return Questionnaire
	 */
	Questionnaire getQuestionnaireById(Long id);

	/**
	 * Sauvegarde un r�sultat
	 * 
	 * @param result
	 */
	void saveAnswers(Result result);

	/**
	 * Sauvegarde un questionnaire
	 * 
	 * @param questionnaire
	 */
	void saveQuestionnaire(Questionnaire questionnaire);
	
	/**
	 * Renvoie la liste des questionnaires
	 * 
	 * @param page
	 *            , admin
	 * @return Liste des questionnaires pagin�e
	 */
	List<Questionnaire> getListQuestionnaire(Integer page, boolean admin);

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

	/**
	 * Renvoie le nombre de r�sultats
	 * 
	 * @return Nombre de r�sultats
	 */
	Long getNbResults();
	
}
