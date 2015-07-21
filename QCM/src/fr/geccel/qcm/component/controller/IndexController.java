package fr.geccel.qcm.component.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.geccel.qcm.component.service.IQuestionnaireService;
import fr.geccel.qcm.component.service.IUserService;
import fr.geccel.qcm.model.User;
import fr.geccel.qcm.util.UserUtil;

/**
 * Page d'accueil
 */
@Controller
public class IndexController {

	/**
	 * Service des questionnaires
	 */
	@Autowired
	private IQuestionnaireService questionnaireService;

	/**
	 * Service des utilisateurs
	 */
	@Autowired
	private IUserService userService;

	/**
	 * Affiche la page d'accueil
	 * 
	 * @return Vue de la page d'accueil
	 */
	@RequestMapping("/")
	public String displayIndexPage(HttpSession session, ModelMap model) {
		User userCourant = UserUtil.getUser(session);

		model.put("nbQuestionnaires", questionnaireService
				.getNbQuestionnaires());
		model.put("nbTakenQCM", questionnaireService.getNbResults());

		if (userCourant != null) {
			model.put("listLastQCM", questionnaireService
					.getLastQuestionnaires(userCourant.isAdmin()));
			model.put("listPopularQCM", questionnaireService
					.getPopularQuestionnaires(userCourant.isAdmin()));
		} else {
			model.put("listLastQCM", questionnaireService
					.getLastQuestionnaires(false));
			model.put("listPopularQCM", questionnaireService
					.getPopularQuestionnaires(false));
		}

		model.put("nbUsers", userService.getNbUsers());

		return "index";
	}
}
