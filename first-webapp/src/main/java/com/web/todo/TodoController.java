package com.web.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model) {
		model.addAttribute("todos", service.retrieveTodos("Anmol"));
		return "list-todos";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo" , new Todo(0,"Anmol","",new Date(),false));
		return "todo";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addTodo(@Valid Todo todo,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			return "todo";
		}
		service.addTodo("Anmol", todo.getDesc(), new Date(), false);
		model.clear();
		return "redirect:list-todos";
	}
	

	@RequestMapping(value="/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) {
		Todo todo = service.retrieveTodo(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todo.setUser("Anmol");
		service.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		service.deleteTodo(id);
		model.clear();
		return "redirect:list-todos";
	}
	
	
	
	
}
