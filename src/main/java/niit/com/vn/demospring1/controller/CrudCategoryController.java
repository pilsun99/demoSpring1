package niit.com.vn.demospring1.controller;


import niit.com.vn.demospring1.domains.Category;

import niit.com.vn.demospring1.repository.CategoryRepository;
import niit.com.vn.demospring1.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("crudCategoryController")
@RequestMapping(value = "/admin/categories")
public class CrudCategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/add")
    public String add(Model model){
        return "categories/add";
    }
    @PostMapping("/add")
    public String add(@RequestParam("name") String name, Model model){
        categoryService.saveCategory(name);
        return "redirect:/admin/categories/add";
    }

    @GetMapping("/")
    public String list(Model model,@RequestParam(value = "page", defaultValue = "1")int page){
        Iterable<Category> categories = categoryRepository.findAll(PageRequest.of(page -1,5));
        Long total = categoryRepository.count();
        double totalPage = Math.ceil(total/5);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("list", categories);
        return "categories/list";
    }


}
