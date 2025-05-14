package agg.selm.manager.controller;

import agg.selm.manager.payload.NewProductPayload;
import agg.selm.manager.payload.UpdateProductPayload;
import agg.selm.manager.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping("list")
    public String getProductList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalog/products/list";
    }

    @GetMapping("add")
    public String addProduct() {
        return "catalog/products/add";
    }

    @PostMapping("store")
    public String storeProduct(@Valid NewProductPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalog/products/add";
        } else {
            this.productService.createProduct(payload.name(), payload.details());
            return "redirect:/catalog/products/list";
        }
    }

    @GetMapping("{productId:\\d+}")
    public String getProduct(@PathVariable("productId") int productId, Model model) {
        model.addAttribute("product", this.productService.findProduct(productId).orElseThrow());
        return "/catalog/products/product";
    }

    @GetMapping("{productId:\\d+}/edit")
    public String productEdit(@PathVariable("productId") int productId, Model model) {
        model.addAttribute("product", this.productService.findProduct(productId).orElseThrow());
        model.addAttribute("payload", null);
        return "/catalog/products/edit";
    }

    @PostMapping("{productId:\\d+}/update")
    public String updateProduct(@PathVariable("productId") int productId, @Valid UpdateProductPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", this.productService.findProduct(productId).orElseThrow());
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "/catalog/products/edit";
        }
        this.productService.updateProduct(productId, payload.name(), payload.details());
        return "redirect:/catalog/products/list";
    }

    @PostMapping("{productId:\\d+}/delete")
    public String deleteProduct(@PathVariable("productId") int productId) {
        this.productService.deleteProduct(productId);
        return "redirect:/catalog/products/list";
    }
}
