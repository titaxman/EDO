package fr.geccel.qcm.component.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.geccel.qcm.component.repository.ITagRepository;
import fr.geccel.qcm.component.service.ITagService;
import fr.geccel.qcm.model.Questionnaire;
import fr.geccel.qcm.model.Tag;

/**
 * Service des tags (implementation).
 */
@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements ITagService {

	/**
	 * Depot des tags
	 */
	@Autowired
	private ITagRepository tagRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Tag> getAll() {
		return tagRepository.loadAllOrderedByQuestionnairesSize();
	}

	@Override
	public List<Questionnaire> getListQuestionnaireByTag(String idTag,
			Integer page, boolean isAdmin) {
		return loadTags(tagRepository.getListQuestionnaireByTag(idTag, page, isAdmin));
	}
	
	/**
	 * Charge la propriete "tags" de chaque bean de la liste, qui est en lazy
	 * loading.
	 */
	private List<Questionnaire> loadTags(List<Questionnaire> questionnaires) {
		for (Questionnaire questionnaire : questionnaires) {
			questionnaire.getTags().size(); // Lazy
		}

		return questionnaires;
	}

	@Override
	public Long getNbQuestionnairesValid(String idTag,	boolean isAdmin) {
		return tagRepository.getListNbQuestionnairesValid(idTag, isAdmin);
	}
}
