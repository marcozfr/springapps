package com.learn.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.spring.data.service.SpittleRepository;

@Controller
@RequestMapping(value={"/spittles"})
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";
    
    private SpittleRepository spittleRepository;
    
    @Autowired
    public SpittleController(SpittleRepository spittlerRepository) {
        this.spittleRepository = spittlerRepository;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String spittles(
            @RequestParam(value="max",defaultValue=MAX_LONG_AS_STRING) long max,
            @RequestParam(value="count",defaultValue="20") int count,
            Model model){
        model.addAttribute("spittleList",spittleRepository.findSpittles(max, count));
        return "spittles";
    }
    
    @RequestMapping(value="/show",method=RequestMethod.GET)
    public String showSpittle(
            @RequestParam("spittle_id") long spittleId,
            Model model){
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }
    
    @RequestMapping(value="/{spittleId}",method=RequestMethod.GET)
    public String spittle(
            @PathVariable("spittleId") long spittleId,
            Model model){
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }
}
