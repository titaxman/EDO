package fr.geccel.qcm.component.controller;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import fr.geccel.qcm.component.service.IUserService;
import fr.geccel.qcm.model.User;

@Controller
@SessionAttributes(IModelConstants.USER)
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * Affiche le profil d'un utilisateur
	 * 
	 * @return Vue du profil d'un utilisateur
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String displayProfilePage(@PathVariable("id") Long id, ModelMap model) {
		model.put(IModelConstants.USER, userService.getById(id));

		return "user";
	}

	/**
	 * Enregistre les modifications apport�es � un utilisateur
	 * 
	 * @return Vue du profil d'un utilisateur
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public String handleEditForm(@ModelAttribute(IModelConstants.USER) User user) {

		CommonsMultipartFile avatar = user.getUploadPhoto();
		if (avatar != null && !avatar.isEmpty()) {
			user.setPhoto(avatar.getBytes());
		}

		userService.updateAccount(user);
		return "user";
	}

	/**
	 * Affiche la photo par d�faut ou la photo enregistr�e
	 * 
	 * @return Vue du profil d'un utilisateur
	 */
	@RequestMapping("/user/photo/{id}")
	public String displayphoto(@PathVariable("id") Long id,
			@ModelAttribute(IModelConstants.USER) User user, OutputStream output) {
		try {
			output.write(user.getPhoto());
			return null;

		} catch (Throwable e) {
			return "redirect:/static/img/default_profile.jpg";
		}
	}
}
