package com.example.webshopkea1.controller;

import com.example.webshopkea1.model.Product;
import com.example.webshopkea1.model.SimpleAudioPlayer;
import com.example.webshopkea1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    ProductService productService;
    static boolean tomVisible = false;




    @GetMapping("/")
    public String index(Model model) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //SimpleAudioPlayer music = new SimpleAudioPlayer("intro.wav");
        //music.play();
        model.addAttribute("tomVisible", tomVisible);

        return "home/home";
    }

    @GetMapping("/viewProductList")
    public String viewProductList(Model model) {
        model.addAttribute("products", productService.fetchAllProducts());
        return "home/productList";

    }


    @GetMapping("/addProduct")
    public String addProductPage() {
        return "home/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductButton(@ModelAttribute Product product) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        productService.insertProduct(product);
        SimpleAudioPlayer music = new SimpleAudioPlayer("addSound.wav");
        music.play();


        return "redirect:/viewProductList";

    }

    @GetMapping("/deleteProductId={id}")
    public String deleteProduct(@PathVariable ("id") int id) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        productService.deleteProduct(id);
        SimpleAudioPlayer music = new SimpleAudioPlayer("deleteSound.wav");
        music.play();
        return "redirect:/viewProductList";

    }

    @GetMapping("/updateProductId={id}")
    public String updateProduct(@PathVariable ("id") int id, Model model) {
        model.addAttribute("product", productService.fetchProductById(id));
        return "home/updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        productService.updateProduct(product);
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
