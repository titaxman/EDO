package fr.geccel.qcm.component.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.geccel.qcm.component.service.IUserService;

/**
 * Liste des utilisateurs
 */
@Controller
public class ListUserController {

	@Autowired
	private IUserService userService;

	/**
	 * Affiche la liste des utilisateurs
	 * 
	 * @return Vue de la liste des utilisateurs
	 */
	@RequestMapping("/userlist")
	public String displayUserList(ModelMap model) {
		model.put(IModelConstants.USER, userService.getAll());
		return "userlist";
	}
}