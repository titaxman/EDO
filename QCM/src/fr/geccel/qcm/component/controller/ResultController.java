package fr.geccel.qcm.component.controller;

import static fr.geccel.qcm.util.UserUtil.getUser;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.geccel.qcm.component.service.IQuestionnaireService;
import fr.geccel.qcm.model.User;

@Controller
public class ResultController {

	@Autowired
	private IQuestionnaireService questionnaireService;


	/**
	 * Affiche les r�sultats
	 * 
	 * @return Vue des r�sultats
	 */
	@RequestMapping("/result/{id}")
	public String displayResult(@PathVariable("id") Long id,
			HttpSession session, ModelMap model) {
		User user = getUser(session);

		if (user == null) {
			return "redirect:/login";

		} else {
			model.put(IModelConstants.RESULT, questionnaireService
					.getResultByUserAndQuestionnaireId(user, id));

			return "result";
		}
	}
}
