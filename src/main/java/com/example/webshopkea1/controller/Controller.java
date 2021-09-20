package com.example.webshopkea1.controller;

import com.example.webshopkea1.model.Product;
import com.example.webshopkea1.model.SimpleAudioPlayer;
import com.example.webshopkea1.repository.ProductRepositoryJpa;
import com.example.webshopkea1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.stereotype.Controller
//@ResponseBody
public class Controller {
    @Autowired
    ProductService productService;
    static boolean tomVisible = false;
    @Autowired
    ProductRepositoryJpa repo;



    @GetMapping("/")
    public String index2(Model model) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //SimpleAudioPlayer music = new SimpleAudioPlayer("intro.wav");
        //music.play();
        model.addAttribute("tomVisible", tomVisible);

         return "home/home";
    }


    @GetMapping("/customHeader")
    ResponseEntity<String> customHeader() {
        return ResponseEntity.ok()
                .header("Custom-Header", "foo")
                .body("Custom header set");
    }
    /*

    @GetMapping("/")
    public ResponseEntity<String> index(Model model) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //SimpleAudioPlayer music = new SimpleAudioPlayer("intro.wav");
        //music.play();
        model.addAttribute("tomVisible", tomVisible);

        Stream<String> stream = Files.lines(Paths.get("src/main/resources/templates/home/home.html"), StandardCharsets.ISO_8859_1);
        StringBuilder x = new StringBuilder();
        List<String> list = stream.collect(Collectors.toList());
        for (String s : list) {
            x.append(s);
        }
        String y = x.toString();;

        return new ResponseEntity<>(y, HttpStatus.OK);




    }

     */

    /*
    @GetMapping("/user/{id}/{name}")
    public String getUserDetails(
            @PathVariable Integer id,
            @PathVariable String name
    )
    {
        return "Path variable data is: " + id + "-" + name;
    }

     */
    @GetMapping("/viewProductList") // edited to JPA
    public String viewProductList(Model model) {
        model.addAttribute("products", productService.fetchAllJpa());
        return "home/productList";

    }

    @GetMapping("/productListProducts") // ResponseEntity
    public ResponseEntity<List<Product>> getAllProductsInList(){
        List<Product> products = new ArrayList<>();
        products = productService.fetchAllJpa();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }




    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value ="/createProduct")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        productService.insertProductJpa(product);
        Optional<Product> newProduct = productService.findByName(product.getName());
        // location header
        if (newProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).header("Location", "products/" + product.getName() + "/" + product.getPrice()).body(newProduct.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
    }

    @PutMapping("/updateProductREST/{id}")
    public ResponseEntity<Product> update(@PathVariable ("id") int id, @RequestBody Product product) {




        Optional<Product> oPproduct = productService.findProductByIdJpa(id);

        if (oPproduct.isPresent()) {
            if (product.getId() != id) {
                return ResponseEntity.badRequest().build();
            }
            productService.updateProductJpa(product);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("deleteProductREST/{id}")
    public ResponseEntity<Product> delete(@PathVariable ("id") int id){
        Optional<Product> oPproduct = productService.findProductByIdJpa(id);

        if (oPproduct.isPresent()) {
            productService.deleteProductJpa(id);
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/addProduct")
    public String addProductPage() {
        return "home/addProduct";
    }



    @PostMapping("/addProduct") // edited to JPA
    public String addProductButton(@ModelAttribute Product product) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        productService.insertProductJpa(product);
        SimpleAudioPlayer music = new SimpleAudioPlayer("addSound.wav");
        music.play();


        return "redirect:/viewProductList";

    }

    @GetMapping("/deleteProductId={id}") // edited jpa
    public String deleteProduct(@PathVariable ("id") int id) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        productService.deleteProductJpa(id);
        SimpleAudioPlayer music = new SimpleAudioPlayer("deleteSound.wav");
        music.play();
        return "redirect:/viewProductList";

    }

    @GetMapping("/updateProductId={id}") // edited jpa
    public String updateProduct(@PathVariable ("id") int id, Model model) {
        Optional<Product> oP = productService.findProductByIdJpa(id);
        oP.ifPresent(product -> model.addAttribute("product", product));

        return "home/updateProduct";
    }

    @PostMapping("/updateProduct") // edited jpa
    public String updateProduct(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        productService.updateProductJpa(product);
        return "redirect:/viewProductList";

    }

    @GetMapping("/contact")
    public String contactLinkPress() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SimpleAudioPlayer music = new SimpleAudioPlayer("inDaClub.wav");
        music.play();
        return "redirect:/";
    }
    @GetMapping("/vision")
    public String visionLinkPress() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SimpleAudioPlayer music = new SimpleAudioPlayer("TomSigma.wav");
        music.play();
        tomVisible = true;
        return "redirect:/";
    }

}
