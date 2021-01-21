package com.salena.portfolio.recipebox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogPostController {
	
    @Autowired
    private BlogPostRepository blogPostRepository;
    private static List<BlogPost> posts = new ArrayList<>();
	
    @GetMapping(value = "/recipes")
    public String index(BlogPost blogPost, Model model) {
        posts.removeAll(posts);
        for (BlogPost post : blogPostRepository.findAll()) {
        	posts.add(post);
        }
        model.addAttribute("posts", posts);
        return "index";
    }
    
    @GetMapping(value = "/recipes/new")
    public String newBlog (BlogPost blogPost) {
        return "new";
    }
    
    @PostMapping(value = "/recipes")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
    	blogPostRepository.save(blogPost);
	model.addAttribute("title", blogPost.getTitle());
	model.addAttribute("author", blogPost.getAuthor());
	model.addAttribute("blogEntry", blogPost.getBlogEntry());
	model.addAttribute("bloglink", blogPost.getLink());
	model.addAttribute("id", blogPost.getId());
	return "result";
    }
    
    @RequestMapping(value = "/recipes/delete/{id}", method = RequestMethod.GET)
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {
    		blogPostRepository.deleteById(id);
    		return "redirect:/recipes";
    }
    
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }
        return "edit";
    }
    @RequestMapping(value = "/recipes/update/{id}")
    public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            blogPostRepository.save(actualPost);
            model.addAttribute("blogPost", actualPost);
        }
 
		return "redirect:/recipes";

	}
}
