package com.cursoSpring.Kruger.controller;

import com.cursoSpring.Kruger.model.Post;
import com.cursoSpring.Kruger.configuracion.Paginas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class controllerBasico {

//    Paginas paginas = new Paginas();
    public Post getPost(){

        ArrayList<Post> post = new ArrayList<>();
        post.add(new Post(1,"Titulo","descipcion",new Date(),"https://projectlombok.org/img/projectlombok-tidelift-mix.png"));

        Post publicacion = new Post();
        publicacion.setDescripcion("Prueba De Descripcion Dinamica");
        publicacion.setUriimg("https://projectlombok.org/img/projectlombok-tidelift-mix.png");
        return publicacion;
    }

    public List<Post> getListPost(){

        ArrayList<Post> post = new ArrayList<>();
        post.add(new Post(1,"Titulo UNO","Descripcion UNO",new Date(),"https://projectlombok.org/img/projectlombok-tidelift-mix.png"));
        post.add(new Post(2,"Titulo DOS","descipcion",new Date(),"https://projectlombok.org/img/projectlombok-tidelift-mix.png"));

        return post;
    }


//    @GetMapping(path = {"/post","/"})
//    public String saludar (Model model){
//        model.addAttribute("posts", this.getPost());
//        return "index";
//    }

    @GetMapping(path = {"/posts","/"}) //aqui redirigo  a la lista de posts MAIN
    public String saludar (Model model){
        model.addAttribute("postslist", this.getListPost());
        return "index";
    }


    @GetMapping(path = {"/post"})  // aqui redirigo a una sola vista filtrada por el id
    public ModelAndView getPostIndividual(@RequestParam(defaultValue = "1",name = "id",required = false) int id){

        ModelAndView modelAndView = new ModelAndView(Paginas.POST);

        List<Post> postFiltrado = this.getListPost().stream()
                .filter((p) -> {
                    return p.getId() == id;
                }).collect(Collectors.toList());
        modelAndView.addObject("postInd",postFiltrado.get(0));
        return modelAndView;

        //NOTA ====> PARA LLAMAR A LA PAGINA CON PARAMETRO SE CONCATENA CON EL ?
    //       <a th:href="@{/home/post?id=}+ ${post.id}" class="btn btn-primary">Go somewhere</a>
    }

    @GetMapping(path = {"/postPath","/postPath/p/{post}"})  // aqui redirigo a una sola vista filtrada por el id y se abre en otro path
    public ModelAndView getPostPath(
                                        @PathVariable(required = true, name = "post") int id){

        ModelAndView modelAndView = new ModelAndView(Paginas.POST);

        List<Post> postFiltrado = this.getListPost().stream()
                .filter((p) -> {
                    return p.getId() == id;
                }).collect(Collectors.toList());
        modelAndView.addObject("postInd",postFiltrado.get(0));
        return modelAndView;
    }

}
