package com.derteuffel.controller;

import com.derteuffel.data.These;
import com.derteuffel.data.User;
import com.derteuffel.repository.TheseRepository;
import com.derteuffel.repository.UserRepository;
import com.derteuffel.service.TheseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by derteuffel on 14/10/2018.
 */
@Controller
@RequestMapping("/these")
public class TheseController {


    @Autowired
    private TheseRepository theseRepository;
    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private TheseService theseService;
    @Autowired
    UserRepository userRepository;


    private static int currentPage=1;
    private static int pageSize=6;
    @GetMapping("")
    public String findAllThese(Model model,
                         @RequestParam("page")Optional<Integer> page,
                         @RequestParam("size")Optional<Integer> size, HttpSession session) {
        page.ifPresent(p->currentPage=p);
        size.ifPresent(s->pageSize=s);

        Page<These> thesePage=theseService.findAllPaginated(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("theses", thesePage);
        int totalPages=thesePage.getTotalPages();
        if (totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);

        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userRepository.findByEmail(auth.getName());
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("avatar", user.getImg());
        session.setAttribute("name", user.getName());
        return "these/theses";
    }

    @GetMapping("/get/")
    public String findByUser(Model model, @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size")Optional<Integer>size, HttpSession session){
        page.ifPresent(p->currentPage=p);
        size.ifPresent(s->pageSize=s);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userRepository.findByEmail(auth.getName());
        Page<These> thesePage=theseService.findAllByUser(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("theses", thesePage);
        int totalPages=thesePage.getTotalPages();
        if (totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);

        }

        session.setAttribute("avatar", user.getImg());
        session.setAttribute("name", user.getName());
        System.out.println();
        return "these/theses";
    }


    @PostMapping("/add/create")
    public String save(These these, Long userId, @RequestParam("file") MultipartFile file) {

        String fileName= fileUploadService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();


        FileUploadRespone fileUploadRespone=new FileUploadRespone(fileName,fileDownloadUri);
        these.setResumes(fileUploadRespone.getFileDownloadUri());
        User user=userRepository.getOne(userId);
        these.setUser(user);
        theseRepository.save(these);

        return "redirect:/these";
    }


    @DeleteMapping("/delete/{theseId}")
    public void deleteById(@PathVariable Long theseId) {
        theseService.delete(theseId);
    }

    @PutMapping("/update")
    @ResponseBody
    public These updateThese(These these, @RequestParam("file") MultipartFile file){

        String fileName= fileUploadService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        FileUploadRespone fileUploadRespone=new FileUploadRespone(fileName,fileDownloadUri);
        these.setResumes(fileUploadRespone.getFileDownloadUri());


       return theseRepository.save(these);
    }

    @GetMapping("/user/{userId}")
    public List<These> findByUser(@PathVariable Long userId){

        List<These> theses= theseRepository.findByUserOrderByTheseIdDesc(userId);

        List<These> all= theseRepository.findAll();
        //System.out.println(all);
        System.out.println(theses);
        return theses;
    }

}

