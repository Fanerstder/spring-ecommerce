package com.cursos.ecommerce.spring_ecommerce.controllers;

import com.cursos.ecommerce.spring_ecommerce.models.DetalleOrden;
import com.cursos.ecommerce.spring_ecommerce.models.Orden;
import com.cursos.ecommerce.spring_ecommerce.models.Producto;
import com.cursos.ecommerce.spring_ecommerce.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    //Para almacenar los detalle de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //Datos de la orden
    Orden orden = new Orden();

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("productos" , productoService.findAll());
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Long id, Model model) {
        log.info("Id del producto enviado como parámetro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Long id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        //validar que no se añada dos veces
        Long idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    //Quitar producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Long id, Model model) {

        //Lista nueva del producto
        List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

        for(DetalleOrden detalleOrden: detalles) {
            if (detalleOrden.getProducto().getId()!=id) {
                ordenesNuevas.add(detalleOrden);

            }
        }

        //Poner la nueva lista con los productos restantes
        detalles = ordenesNuevas;

        double sumaTotal= 0;

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public  String getCart(Model model) {
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "/usuario/carrito";
    }
}
