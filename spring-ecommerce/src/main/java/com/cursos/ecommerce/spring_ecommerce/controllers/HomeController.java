package com.cursos.ecommerce.spring_ecommerce.controllers;

import com.cursos.ecommerce.spring_ecommerce.models.DetalleOrden;
import com.cursos.ecommerce.spring_ecommerce.models.Orden;
import com.cursos.ecommerce.spring_ecommerce.models.Producto;
import com.cursos.ecommerce.spring_ecommerce.models.Usuario;
import com.cursos.ecommerce.spring_ecommerce.services.IDetalleOrdenService;
import com.cursos.ecommerce.spring_ecommerce.services.IOrdenService;
import com.cursos.ecommerce.spring_ecommerce.services.IUsuarioService;
import com.cursos.ecommerce.spring_ecommerce.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

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

    @GetMapping("/order")
    public String order(Model model) {

        Usuario usuario = usuarioService.findById(Long.valueOf(1)).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        return "usuario/resumenorden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder() {
        LocalDateTime fechaCreacion = LocalDateTime.now();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //usuario
        Usuario usuario = usuarioService.findById(Long.valueOf(1)).get();

        orden.setUsuario(usuario);
        ordenService.save(orden);

        //guardar detalles
        for(DetalleOrden dt:detalles) {
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        //limpiar lista y orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {
        log.info("Nombre del producto: {}", nombre);
        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "usuario/home";
    }
}
