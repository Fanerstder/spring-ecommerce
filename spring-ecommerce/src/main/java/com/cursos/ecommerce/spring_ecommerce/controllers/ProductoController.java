package com.cursos.ecommerce.spring_ecommerce.controllers;



import com.cursos.ecommerce.spring_ecommerce.models.Producto;
import com.cursos.ecommerce.spring_ecommerce.models.Usuario;
import com.cursos.ecommerce.spring_ecommerce.services.IUsuarioService;
import com.cursos.ecommerce.spring_ecommerce.services.ProductoService;
import com.cursos.ecommerce.spring_ecommerce.services.UploadFileService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private UploadFileService upload;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

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
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("este es el objeto producto {}", producto);

        Usuario u = usuarioService.findById(Long.valueOf(Integer.parseInt(session.getAttribute("idusuario").toString()))).get();
        //Usuario u = new Usuario(Long.valueOf(1), "",  "", "", "", "", "", "", new ArrayList<>(), new ArrayList<>() );
        producto.setUsuario(u);
        //imagen
        if (producto.getId() == null) { //cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {

        }

        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("producto buscasdo: ()", producto);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
        p = productoService.get(producto.getId()).get();
        if(file.isEmpty()) { // editamos el producto pero no cambiamos la imagen

            producto.setImagen(p.getImagen());
        } else {
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleteImage(p.getImagen());
            }

            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Producto p = new Producto();
        p = productoService.get(id).get();
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleteImage(p.getImagen());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }


}
