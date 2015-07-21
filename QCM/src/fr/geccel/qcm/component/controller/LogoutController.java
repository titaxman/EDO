package fr.geccel.qcm.component.controller;

import static fr.geccel.qcm.util.UserUtil.unsetUser;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Page de d�connexion
 */
@Controller
public class LogoutController {

	/**
	 * D�connecte l'utilisateur
	 * 
	 * @param session
	 *            Session web
	 * @return Redirection vers la page d'accueil
	 */
	@RequestMapping("/logout")
	public String handleLogoutRequest(HttpSession session) {
		unsetUser(session);
		return "redirect:/";
	}
}
