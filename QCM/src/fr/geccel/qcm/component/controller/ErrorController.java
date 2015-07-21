package fr.geccel.qcm.component.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Page d'erreur
 */
@Controller
public class ErrorController {

	/**
	 * Affiche la page d'erreur
	 * 
	 * @param code
	 *            Code de l'erreur
	 * @param model
	 *            Mod?le
	 * @return La vue � afficher
	 */
	@RequestMapping("/error/{code}")
	public String displayError(@PathVariable("code") Integer code,
			ModelMap model) {
		model.put(IModelConstants.CODE, code);

		return "error";
	}
}
