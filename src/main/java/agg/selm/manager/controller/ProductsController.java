package agg.selm.manager.controller;

import agg.selm.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/products")
public class ProductsController {

    private final ProductService productService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getProductList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        System.out.println(model);
        return "catalog/products/list";
    }
}
