package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/")
public class ProductController {
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping
	public String getMainPage() {
		return "index";
	}
	
	@GetMapping("/product/form")
	public String getProductForm(Product product) {
		return "product-add";
	}
	
	@GetMapping("/product/update")
	public String getUpdateForm(@RequestParam("code")String code , Model model) {
		model.addAttribute("product", productRepo.getById(code));
		return "product-update";
	}
	
	@GetMapping("/product/delete")
	public String getDeleteForm(@RequestParam("code")String code , Model model) {
		model.addAttribute("product", productRepo.getById(code));
		return "product-delete";
	}
	
	@GetMapping("/product/all")
	public String getAllProduct(Model model) {
		model.addAttribute("list", productRepo.findAll());
		return "product-list";
	}
	
	@PostMapping("/product/add")
	public String addProduct(Product product, Model model) {
		boolean codeExist = productRepo.checktCodeExist(product.getCode());
		if(codeExist) {
			model.addAttribute("error", "Product code is Exist");
			model.addAttribute("product", product);
			return "product-add";
		}else 
			productRepo.save(product);
		return "redirect:/product/all";
	}
	
	@PostMapping("/product/update")
	public String updateProduct(Product product) {
		productRepo.save(product);
		return "redirect:/product/all";
	}
	
	@PostMapping("/product/delete")
	public String deleteProduct(@RequestParam("code")String code) {
		productRepo.deleteById(code);
		return "redirect:/product/all";
	}
}
