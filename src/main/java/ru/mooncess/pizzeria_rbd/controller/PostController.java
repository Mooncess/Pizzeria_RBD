package ru.mooncess.pizzeria_rbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.pizzeria_rbd.dto.PostDto;
import ru.mooncess.pizzeria_rbd.entity.Post;
import ru.mooncess.pizzeria_rbd.service.DepartmentService;
import ru.mooncess.pizzeria_rbd.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final DepartmentService departmentService;

    public PostController(PostService postService, DepartmentService departmentService) {
        this.postService = postService;
        this.departmentService = departmentService;
    }
    @GetMapping
    public String getAllPost(Model model){
        model.addAttribute("posts", postService.getAllPosts());
        return "all_post";
    }
    @GetMapping("byId")
    public String getPostById(@RequestParam Integer id, Model model){
        model.addAttribute("onePost", postService.getPostById(id));
        return "post";
    }

    @GetMapping("byId/{id}")
    public String getPostById(@PathVariable int id, Model model){
        model.addAttribute("onePost", postService.getPostById(id));
        return "post";
    }

    @GetMapping("/create")
    public String getCreatePost(Model model){
        model.addAttribute("departmentList", departmentService.getAllDepartments());
        return "create_post";
    }
    @PostMapping("/create")
    public String createPost(@RequestParam ("nameOfPost") String name,
                             @RequestParam ("salary") float salary,
                             @RequestParam ("department") int department){
        Post post = new Post();
        post.setNameOfPost(name);
        post.setSalary(salary);
        post.setDepartment(departmentService.getDepartmentById(department));
        postService.createPost(post);
        return "redirect:/post";
    }
    @GetMapping("/update/{id}")
    public String getUpdatePost(@PathVariable Integer id, Model model){
        PostDto post = postService.getPostById(id);
        post.setIdPost(id);
        model.addAttribute("onePost", post);
        model.addAttribute("departmentList", departmentService.getAllDepartments());
        return "update_post";
    }
    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Integer id,
                             @RequestParam ("nameOfPost") String name,
                             @RequestParam ("salary") float salary,
                             @RequestParam ("department") int department){
        Post post = new Post();
        post.setIdPost(id);
        post.setNameOfPost(name);
        post.setSalary(salary);
        post.setDepartment(departmentService.getDepartmentById(department));
        System.out.println(post.getNameOfPost());
        System.out.println(post.getDepartment().getNameOfDepartment());
        postService.updatePost(post);
        return "redirect:/post/byId/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Integer id){
        postService.deletePostById(id);
        return "redirect:/post";
    }
}
