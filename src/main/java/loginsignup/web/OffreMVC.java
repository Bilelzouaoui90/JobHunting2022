package loginsignup.web;



import loginsignup.model.Entreprise;
import loginsignup.model.Offre;
import loginsignup.model.Search;
import loginsignup.service.EntrepriseService;
import loginsignup.service.OffresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OffreMVC {
    @Autowired
    OffresService agent;

    @Autowired
    EntrepriseService agentent;
/*
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
*/
    @RequestMapping(value = "/offres/add",method = RequestMethod.GET)
    public ModelAndView form_add(){
        ModelAndView mv = new ModelAndView();
       Offre offre = new Offre();
        List<Entreprise> entreprises=agentent.findAll();
        mv.setViewName("offre");
        mv.addObject("Formoffre",offre);
        mv.addObject("entreprises",entreprises);

        return mv;
    }

    @RequestMapping(value = "/offres/save",method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("Formoffre") Offre offre, BindingResult result) {
        if (result.hasErrors()) {
            return (new ModelAndView("offre"));
        } else {
            agent.add_offres(offre);
            return (new ModelAndView("redirect:/offres/list"));
        }

    }

    @RequestMapping(value = "/offres/list",method = RequestMethod.GET)
    public String viewListEntreprises(Model model) {
        return findPaginated(1, "deadline", "asc", model);
    }

    @RequestMapping(value = "/offres/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete_offres(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView();
        agent.delete_offre(id);
        return new ModelAndView("redirect:/offres/list");
    }

    @RequestMapping(value = "/offres/update/{ido}",method = RequestMethod.GET)
    public ModelAndView display_form_update(@PathVariable("ido") long id){
       Offre offre = agent.find_offre(id).get();
        List<Entreprise> entreprises=agentent.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editoffre");
        mv.addObject("Formoffre",offre);
        mv.addObject("entreprises",entreprises);
        return mv;
    }

    @RequestMapping(value = "/offres/list/page/{pageNo}",method = RequestMethod.GET)
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page< Offre > page = agent.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Offre > offres = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("list", offres);
        return "offres";
    }

    /*@RequestMapping(value = "/offres/recherche",method = RequestMethod.GET)
    public ModelAndView display_search(){
        ModelAndView mv = new ModelAndView();
        Search search = new Search();
        mv.addObject("search",search);
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping(value = "/offres/search",method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("search") Search search) {
        List<Offre> res = agent.search_domaine(search.getDomaine());
        search.setOffreList(res);
        ModelAndView mv = new ModelAndView();
        mv.addObject("search",search);
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping(value = "/offres/avancee",method = RequestMethod.GET)
    public ModelAndView display_advanced_search(){
        ModelAndView mv = new ModelAndView();
        Search search = new Search();
        mv.addObject("search",search);
        mv.setViewName("advancedsearch");
        return mv;
    }

    @RequestMapping(value = "/offres/advancedsearch",method = RequestMethod.POST)
    public ModelAndView advanced_search(@ModelAttribute("search") Search search) {
        List<Offre> res = agent.advanced_search(search.getDomaine(),search.getPseudo());
        search.setOffreList(res);
        ModelAndView mv = new ModelAndView();
        mv.addObject("search",search);
        mv.setViewName("advancedsearch");
        return mv;
    }*/

}
