package fr.geccel.qcm.component.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.geccel.qcm.component.repository.IUserRepository;
import fr.geccel.qcm.model.User;

@Repository
public final class UserRepositoryImpl extends AbstractRepositoryImpl<User>
		implements IUserRepository {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User loadByLogin(String login) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));

		return (User) criteria.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNbUsers() {
		Query query = getCurrentSession().createQuery("SELECT COUNT(*) FROM User u");
		
		return (Long) query.uniqueResult();
	}
}
