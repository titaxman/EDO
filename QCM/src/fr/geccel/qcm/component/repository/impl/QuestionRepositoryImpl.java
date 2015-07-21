package fr.geccel.qcm.component.repository.impl;

import org.springframework.stereotype.Repository;

import fr.geccel.qcm.component.repository.IQuestionRepository;
import fr.geccel.qcm.model.Question;

@Repository
public final class QuestionRepositoryImpl extends
		AbstractRepositoryImpl<Question> implements IQuestionRepository {

}
