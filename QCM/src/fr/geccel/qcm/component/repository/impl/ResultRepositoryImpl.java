package fr.geccel.qcm.component.repository.impl;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.geccel.qcm.closure.RefreshClosure;
import fr.geccel.qcm.component.repository.IResultRepository;
import fr.geccel.qcm.model.Result;

@Repository
public final class ResultRepositoryImpl extends AbstractRepositoryImpl<Result>
		implements IResultRepository {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Result loadByUserIdAndQuestionnaireId(Long userId,
			Long questionnaireId) {
		Criteria criteria = getCurrentSession().createCriteria(Result.class);
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.add(Restrictions.eq("questionnaire.id", questionnaireId));

		Result result = (Result) criteria.uniqueResult();
		
		result.getAnswers().size();
		result.getQuestionnaire().getQuestions().size();
		result.getQuestionnaire().getTags().size();
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Result save(Result result) {
		Closure refreshClosure = new RefreshClosure(getCurrentSession());
		
		refreshClosure.execute(result.getUser());
		CollectionUtils.forAllDo(result.getAnswers(), refreshClosure);

		return super.save(result);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNbResults() {
		Query query = getCurrentSession().createQuery("SELECT COUNT(*) FROM Result r");
		
		return (Long) query.uniqueResult();
	}
	
}
