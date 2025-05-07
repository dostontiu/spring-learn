package agg.selm.manager.controller;

import agg.selm.manager.payload.NewProductPayload;
import agg.selm.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String storeProduct(NewProductPayload payload) {
        this.productService.createProduct(payload.name(), payload.details());
        return "redirect:/catalog/products/list";
    }

    @GetMapping("{productId:\\d+}")
    public String getProduct(@PathVariable("productId") int productId, Model model) {
        model.addAttribute("product", this.productService.findProduct(productId).orElseThrow());
        return "/catalog/products/product";
    }
}
