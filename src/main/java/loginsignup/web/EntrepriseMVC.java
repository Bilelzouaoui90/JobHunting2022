package loginsignup.web;


import javafx.css.Styleable;
import loginsignup.model.Entreprise;
import loginsignup.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.util.List;
import java.io.*;
import java.nio.file.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class EntrepriseMVC {

    @Autowired
    EntrepriseService agent;

    /*//Diplay of the link : http://localhost:8082/projetfinal
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("index");
        return mv;

    }

   ///Diplay form add entreprise : http://localhost:8082/entreprises/entreprises/add
    @RequestMapping(value = "/entreprises/add",method = RequestMethod.GET)
    public ModelAndView form_Add() {
        ModelAndView mv = new ModelAndView();
        Entreprise entreprise = new Entreprise();
        mv.addObject(entreprise);
        mv.setViewName("entreprise");
        mv.addObject("Formentreprise",entreprise);
        return mv;
    }

    //envoyer la requette en formdoctor
    //Ajout de la partie controle des données
    @RequestMapping(value = "/entreprises/save",method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("Formentreprise") Entreprise d, BindingResult result) {
        if(result.hasErrors()){
            return (new ModelAndView("entreprise"));
        }else{
            agent.add_entreprise(d);
            return new ModelAndView("redirect:/entreprises/list");
        }

    }
    @RequestMapping(value = "/enterprises/list",method =RequestMethod.GET)
    public ModelAndView display_entreprise(){
        ModelAndView mv=new ModelAndView();
       List<Entreprise> entreprises =agent.findAll();
        mv.addObject("entreprises",entreprises);
        mv.setViewName("entreprises");
        return mv;
    }

    package com.corp.cabinet.controller;

import com.corp.cabinet.model.Doctor;
import com.corp.cabinet.model.Search;
import com.corp.cabinet.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

    @Controller
    public class DoctorMvc {

        @Autowired
        EntrepriseService agent;*/

        @RequestMapping(value = "/",method = RequestMethod.GET)
        public ModelAndView index(){
            ModelAndView mv = new ModelAndView();
            mv.setViewName("index");
            return mv;
        }

        @RequestMapping(value = "/entreprises/add",method = RequestMethod.GET)
        public ModelAndView form_add(){
            ModelAndView mv = new ModelAndView();
            Entreprise entreprise = new Entreprise();
            mv.addObject("Formentreprise",entreprise);
            mv.setViewName("entreprise");
            return mv;
        }

       /* @RequestMapping(value = "/entreprises/save",method = RequestMethod.POST)
        public ModelAndView save(@Valid @ModelAttribute("Formentreprise") Entreprise e, BindingResult result, @RequestParam("fileImage") MultipartFile multipartFile) {
            if (result.hasErrors()) {
                return (new ModelAndView("entreprise"));
            } else {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                agent.setImage(fileName);
                agent.add_entreprise(e);
                String uploadDir = "user-photos/" +save.g
                Path uploadPath= Paths.get(uploadDir);
                return (new ModelAndView("redirect:/entreprises/list"));
            }

        }*/
       @RequestMapping(value = "entreprises/save", method = RequestMethod.POST)
       public ModelAndView save(@ModelAttribute("Formentreprise")Entreprise entreprises,@RequestParam("fileImage") MultipartFile multipartFile)throws IOException{
           String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
           entreprises.setImage(fileName);

           Entreprise save= agent.add_entreprise(entreprises);
           String uploadDir = "user-photos/" + save.getId();
           Path uploadPath= Paths.get(uploadDir);
           if (!Files.exists(uploadPath)){
               Files.createDirectories(uploadPath);
           }
           try ( InputStream inputStream=multipartFile.getInputStream()) {
               Path filePath = uploadPath.resolve(fileName);
               Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
           }catch (IOException ioe) {
               throw new IOException("Could not save image file: " + fileName, ioe);
           }
           return new ModelAndView("redirect:/entreprises/list");
       }

        @RequestMapping(value = "/entreprises/list",method = RequestMethod.GET)
        public String viewListEntreprises(Model model) {
            return findPaginated(1, "nomEnt", "asc", model);
        }


        @RequestMapping(value = "/entreprises/delete/{id}",method = RequestMethod.GET)
        public ModelAndView delete_entreprise(@PathVariable("id") long id){
            ModelAndView mv = new ModelAndView();
            agent.delete_entreprise(id);
            return new ModelAndView("redirect:/entreprises/list");
        }

        @RequestMapping(value = "/entreprises/update/{ide}",method = RequestMethod.GET)
        public ModelAndView display_form_update(@PathVariable("ide") long id){
            Entreprise entreprise = agent.find_entreprise(id).get();
            ModelAndView mv = new ModelAndView();
            mv.setViewName("editentreprise");
            mv.addObject("Formentreprise",entreprise);
            return mv;
        }

        @RequestMapping(value = "/entreprises/list/page/{pageNo}",method = RequestMethod.GET)
        public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("sortDir") String sortDir,
                                    Model model) {
        int pageSize = 5;
        Page< Entreprise > page = agent.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Entreprise > entreprises = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("list", entreprises);
        return "entreprises";
    }


    /*@RequestMapping(value = "/entreprises/list",method = RequestMethod.GET)
    public ModelAndView display_doctors(){
        ModelAndView mv = new ModelAndView();
        List<Entreprise> entreprises = agent.findAll();
        mv.addObject("list",entreprises);
        mv.setViewName("entreprises");
        return mv;
    }*/
/*
        @RequestMapping(value = "/doctors/recherche",method = RequestMethod.GET)
        public ModelAndView display_search(){
            ModelAndView mv = new ModelAndView();
            Search search = new Search();
            mv.addObject("search",search);
            mv.setViewName("search");
            return mv;
        }

        @RequestMapping(value = "/doctors/search",method = RequestMethod.POST)
        public ModelAndView search(@ModelAttribute("search") Search search) {
            List<Doctor> res = agent.search_specialite(search.getSpecialite());
            search.setDoctors(res);
            ModelAndView mv = new ModelAndView();
            mv.addObject("search",search);
            mv.setViewName("search");
            return mv;
        }

        @RequestMapping(value = "/doctors/avancee",method = RequestMethod.GET)
        public ModelAndView display_advanced_search(){
            ModelAndView mv = new ModelAndView();
            Search search = new Search();
            mv.addObject("search",search);
            mv.setViewName("advancedsearch");
            return mv;
        }

        @RequestMapping(value = "/doctors/advancedsearch",method = RequestMethod.POST)
        public ModelAndView advanced_search(@ModelAttribute("search") Search search) {
            List<Doctor> res = agent.advanced_search(search.getSpecialite(),search.getPseudo());
            search.setDoctors(res);
            ModelAndView mv = new ModelAndView();
            mv.addObject("search",search);
            mv.setViewName("advancedsearch");
            return mv;
        }

    }*/



}
