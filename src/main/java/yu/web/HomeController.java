package yu.web;

import javax.servlet.http.*;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yu.domain.FileNode;
import yu.domain.FileNodeVO;
import yu.service.DirService;
import yu.service.DirServiceImpl;

@Controller
public class HomeController {

    private final DirService dirService;

    @Autowired
    public HomeController(DirService dirService) {
        this.dirService = dirService;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/id/0";
    }

    @RequestMapping("/id/{parentId}")
    public String home(Model model, Principal principal, @PathVariable Long parentId) {
        String username = principal.getName();
        if (parentId != 0 && !dirService.checkOwner(parentId, username)) {
            return "redirect:/id/0";
        }
        model.addAttribute("parent", parentId);
        model.addAttribute("grandpa", dirService.getParent(parentId));
        List<FileNodeVO> fileNodeVOs = dirService.getChildren(parentId, username);
        model.addAttribute("fileNodeList", fileNodeVOs);
        return "home";
    }
}