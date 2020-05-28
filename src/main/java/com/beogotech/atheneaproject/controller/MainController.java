package com.beogotech.atheneaproject.controller;

import com.beogotech.atheneaproject.utils.AtheneaUtil;
import com.beogotech.atheneaproject.utils.LoggerFactoryUtil;
import com.beogotech.atheneaproject.utils.TreeNode;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Controller
public class MainController {
	
	private static Logger LOG = LoggerFactoryUtil.getLog(MainController.class);

	@GetMapping(value = "/dashboard")
	public String showIndexPage(Model model){
		model.addAttribute("year", AtheneaUtil.COPYRIGHT_YEAR_INT);
		model.addAttribute("numberOfNotifications", "3");
		model.addAttribute("numberOfMessages", "2");
		model.addAttribute("actionPlanActiveOption", "PA1 - PLAN D'ACTION BUDGETISE DU PAAQE 2018-2020");
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
	
	@RequestMapping(value = "/action-plan-tree", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getTreeData(){
		
		String actionPlanRootNode = "PA1 - PLAN D'ACTION BUDGETISE DU PAAQE 2018-2020";
		
		TreeNode<String> tree = getActionPlanTree(actionPlanRootNode);
		Gson gson = new Gson();
		String treeJsonString = gson.toJson(tree);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(treeJsonString);
		
		return sb.toString();
	}
	
	private TreeNode<String> getActionPlanTree(String actionPlanRootNode) {
		String[] actionPlanComponents = new String[] {"CMP1 - Elargissement de l’accès équitable à l'enseignement préscolaire et  à l'enseignement secondaire",
				"CMP2 - Amélioration de la qualité du processus d’enseignement et d’apprentissage",
				"CMP3 - Renforcement des capacités institutionnelles"};
		
		List<TreeNode<String>> components = new ArrayList<>();
		List<TreeNode<String>> firstComponentChildren = new ArrayList<>();
		List<TreeNode<String>> secondComponentChildren = new ArrayList<>();
		List<TreeNode<String>> thirdComponentChildren = new ArrayList<>();
		TreeNode<String> rootNode = new TreeNode<>(actionPlanRootNode);
		TreeNode<String> firstComponent = new TreeNode<>(getShorttenedName(actionPlanComponents[0]));
		TreeNode<String> secondComponent = new TreeNode<>(getShorttenedName(actionPlanComponents[1]));
		TreeNode<String> thirdComponent = new TreeNode<>(getShorttenedName(actionPlanComponents[2]));
		// subcomponents of first component
		TreeNode<String> firstSubComponent = new TreeNode<>(getShorttenedName("VOL1.1 - Amélioration de l'accès et de la qualité de l'éducation de la petite enfance à travers le pilotage d'un programme d'enseignement de l'audio interactif et certification sur piste courte"));
		TreeNode<String> secondSubComponent = new TreeNode<>(getShorttenedName("VOL1.2 - Elargir l'accès à l’enseignement secondaire"));
		firstComponentChildren.add(firstSubComponent);
		firstComponentChildren.add(secondSubComponent);
		firstComponent.addChildren(firstComponentChildren);
		
		//Budget Line of VOL1.1
		TreeNode<String> budgetLineVol1_1 = new TreeNode<>(getShorttenedName("LB1.1.1 - Développement et adoption de programmes «IAI» et «DPE»   au profit des enfants de 3-5 ans d'âge des régions de l'Est, du Centre-Est et de  la ville de Ouagadougou"));
		firstSubComponent.addChild(budgetLineVol1_1);
		//Activities of Budget Line lb 1-1-1
		List<TreeNode<String>>activitiesOfBudgetLine = new ArrayList<>();
		TreeNode<String> activityLB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.1 - Rédaction  et révision de scripts des émissions EIA en 8 sessions"));
		TreeNode<String> activity2LB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.2 - Rédaction et révision de scripts des émissions de la formation à distance (FD) en 10 sessions"));
		TreeNode<String> activity3LB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.3 - Enregistrement et montage de 48 émissions EIA"));
		TreeNode<String> activity4LB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.4 - Enregistrement et montage de 25 émissions de la formation à distance (FD)"));
		TreeNode<String> activity5LB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.5 - Testing des 96 émissions EIA"));
		activitiesOfBudgetLine.add(activityLB1_1_1);
		activitiesOfBudgetLine.add(activity2LB1_1_1);
		activitiesOfBudgetLine.add(activity3LB1_1_1);
		activitiesOfBudgetLine.add(activity4LB1_1_1);
		activitiesOfBudgetLine.add(activity5LB1_1_1);
		budgetLineVol1_1.addChildren(activitiesOfBudgetLine);
		// subcomponents of second component
		TreeNode<String> thirdSubComponent = new TreeNode<>(getShorttenedName("VOL2.1 - Réforme du curriculum"));
		TreeNode<String> fourthSubComponent = new TreeNode<>(getShorttenedName("VOL2.2 - Formation initiale et continue des enseignants du secondaire"));
		TreeNode<String> fifthSubComponent = new TreeNode<>(getShorttenedName("VOL2.3 - Augmentation de la disponibilité des manuels et matériels pédagogiques"));
		TreeNode<String> sixthSubComponent = new TreeNode<>(getShorttenedName("VOL2.4 - Développement des initiatives de qualité en milieu scolaire"));
		TreeNode<String> seventhSubComponent = new TreeNode<>(getShorttenedName("VOL2.5 - Évaluation de l'apprentissage du rendement des élèves et des examens "));
		secondComponentChildren.add(thirdSubComponent);
		secondComponentChildren.add(fourthSubComponent);
		secondComponentChildren.add(fifthSubComponent);
		secondComponentChildren.add(sixthSubComponent);
		secondComponentChildren.add(seventhSubComponent);
		secondComponent.addChildren(secondComponentChildren);
		//subcomponents of component 3
		TreeNode<String> eighthSubComponent = new TreeNode<>(getShorttenedName("VOL3.1 - Planification de l'éducation et Gestion administrative"));
		TreeNode<String> ninthSubComponent = new TreeNode<>(getShorttenedName("VOL3.2 - Promotion des comités de gestion scolaire (COGES)"));
		TreeNode<String> tenthSubComponent = new TreeNode<>(getShorttenedName("VOL3.3 - Gestion de projet"));
		thirdComponentChildren.add(eighthSubComponent);
		thirdComponentChildren.add(ninthSubComponent);
		thirdComponentChildren.add(tenthSubComponent);
		thirdComponent.addChildren(thirdComponentChildren);
		
		components.add(firstComponent);
		components.add(secondComponent);
		components.add(thirdComponent);
		rootNode.addChildren(components);
		return rootNode;
	}
	
	private String getShorttenedName(String name){
		String shortenedName = "";
		if(!name.isEmpty()){
			int numberOfWords =0;
			StringTokenizer st = new StringTokenizer(name);
			numberOfWords = st.countTokens();
			if(numberOfWords>=6){
				String words = Arrays.stream(name.split("\\s+")).limit(6).collect(Collectors.joining(" "));
				shortenedName = words +"...";
			}else{
				shortenedName = name;
			}
		}
		return shortenedName;
	}
	
	
}
