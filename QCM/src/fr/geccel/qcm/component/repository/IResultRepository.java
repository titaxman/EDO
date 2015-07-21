package fr.geccel.qcm.component.repository;

import fr.geccel.qcm.model.Result;

public interface IResultRepository extends IAbstractRepository<Result> {

	/**
	 * Renvoie les r�sultats d'un utilisateur � un questionnaire
	 * 
	 * @param userId
	 *            , questionnaireId
	 * @return R�sultat du questionnaire
	 */
	Result loadByUserIdAndQuestionnaireId(Long userId, Long questionnaireId);

	/**
	 * Renvoie le nombre de r�sultats
	 * 
	 * @return Nombre de r�sultats
	 */
	Long getNbResults();

}
