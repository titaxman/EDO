package fr.geccel.qcm.component.repository.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.geccel.qcm.closure.SaveOrUpdateClosure;
import fr.geccel.qcm.component.controller.IModelConstants;
import fr.geccel.qcm.component.repository.IQuestionnaireRepository;
import fr.geccel.qcm.model.Questionnaire;

@Repository
public final class QuestionnaireRepositoryImpl extends
		AbstractRepositoryImpl<Questionnaire> implements
		IQuestionnaireRepository {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Questionnaire loadForUser(Long questionnaireId, Long userId) {
		Query query = getCurrentSession()
				.createQuery(
						"FROM Questionnaire q WHERE q.id = :questionnaireId AND NOT EXISTS (SELECT 1 FROM Result r WHERE r.questionnaire.id = q.id AND r.user.id = :userId)");

		query.setLong("questionnaireId", questionnaireId);
		query.setLong("userId", userId);

		return (Questionnaire) query.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Questionnaire save(Questionnaire questionnaire) {
		Closure closure = new SaveOrUpdateClosure(getCurrentSession());
		CollectionUtils.forAllDo(questionnaire.getTags(), closure);

		return super.save(questionnaire);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings( { "unchecked" })
	@Override
	public List<Questionnaire> paginateListQuestionnaire(Integer page,
			boolean admin) {
		Date today = Calendar.getInstance().getTime();

		Criteria criteria = getCurrentSession().createCriteria(
				Questionnaire.class);
		if (!admin) {
			criteria.add(Restrictions.or(Restrictions.le("start", today),
					Restrictions.isNull("start")));
			criteria.add(Restrictions.or(Restrictions.ge("end", today),
					Restrictions.isNull("end")));
		}
		criteria.addOrder(Order.desc("datecreate"));

		paginate(criteria, page);

		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNbQuestionnaires() {
		Query query = getCurrentSession().createQuery(
				"SELECT COUNT(*) FROM Questionnaire q");

		return (Long) query.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Questionnaire> getLastQuestionnaires(boolean admin) {
		Date today = Calendar.getInstance().getTime();

		Criteria criteria = getCurrentSession().createCriteria(
				Questionnaire.class);
		if (!admin) {
			criteria.add(Restrictions.or(Restrictions.le("start", today),
					Restrictions.isNull("start")));
			criteria.add(Restrictions.or(Restrictions.ge("end", today),
					Restrictions.isNull("end")));
		}
		criteria.addOrder(Order.desc("datecreate"));
		criteria.setMaxResults(IModelConstants.NB_RESULTS_LAST_QCM);

		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Questionnaire> getPopularQuestionnaires(boolean admin) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM Questionnaire q WHERE resultsSize != 0 ");
		if (!admin) {
			sql.append("AND (q.start <= NOW() OR q.start IS NULL) AND (q.end >= NOW() OR q.end IS NULL) ");
		}
		sql.append("ORDER BY resultsSize DESC");
		
		Query query = getCurrentSession().createQuery(sql.toString());
		query.setMaxResults(IModelConstants.NB_RESULTS_POPULAR_QCM);

		return query.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNbQuestionnairesValid(boolean admin) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM Questionnaire q ");
		if (!admin) {
			sql.append("WHERE (q.start <= NOW() OR q.start IS NULL) AND (q.end >= NOW() OR q.end IS NULL) ");
		}
		
		Query query = getCurrentSession().createQuery(sql.toString());

		return (Long) query.uniqueResult();
	}

}
