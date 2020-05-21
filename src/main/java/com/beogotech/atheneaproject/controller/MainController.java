package com.beogotech.atheneaproject.controller;

import com.beogotech.atheneaproject.utils.LoggerFactoryUtil;
import com.beogotech.atheneaproject.utils.AtheneaUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
	
	private static Logger LOG = LoggerFactoryUtil.getLog(MainController.class);

	@GetMapping(value = "/dashboard")
	public String showIndexPage(Model model){
		model.addAttribute("year", AtheneaUtil.COPYRIGHT_YEAR_INT);
		model.addAttribute("numberOfNotifications", "3");
		model.addAttribute("numberOfMessages", "2");
		model.addAttribute("actionPlanActiveOption", "PA001 - Plan d'action budgetisé PAAQE 2018");
		model.addAttribute("actionPlanComponents",new String[] {"CMP001 - Elargissement de l'accès equitable...", "CMP002 - Amelioration de la qualité du processus...", "CMP003 - Renforcement de la capacité institutionnelle..."});
		return "/dashboard";
	}
	
	@GetMapping(value = {"/", "/login"})
	public String showLoginPage(Model model){
		model.addAttribute("year", AtheneaUtil.COPYRIGHT_YEAR_INT);
		return AtheneaUtil.LOGIN_PAGE_URI;
	}
	
	@PostMapping(value = "/login")
	public String logUserIn(Model model){
		model.addAttribute("year", AtheneaUtil.COPYRIGHT_YEAR_INT);
		model.addAttribute("page-title", AtheneaUtil.COPYRIGHT_YEAR_INT);
		return "redirect:dashboard";
	}
	
	
}
