package com.cursos.ecommerce.spring_ecommerce.controllers;



import com.cursos.ecommerce.spring_ecommerce.models.Producto;
import com.cursos.ecommerce.spring_ecommerce.models.Usuario;
import com.cursos.ecommerce.spring_ecommerce.services.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "/productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto) {
        LOGGER.info("este es el objeto producto {}", producto);
        Usuario u = new Usuario(Long.valueOf(1), "",  "", "", "", "", "", "", new ArrayList<>(), new ArrayList<>() );
        producto.setUsuario(u);
        productoService.save(producto);
        return "redirect:/productos";
    }

}
