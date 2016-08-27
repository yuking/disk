package yu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yu.service.DirService;

import java.security.Principal;

@Controller
public class DirController {

    @Autowired
    private DirService dirService;

    @RequestMapping("/add/{parentId}/{filename}")
    public String addDir(Principal principal, @PathVariable long parentId, @PathVariable String filename) {
        String username = principal.getName();
        if(parentId != 0 && !dirService.checkOwner(parentId, username)) {
            return "redirect:/id/0";
        }
        if(!dirService.existDir(parentId, filename, username, true)) {
            dirService.addDir(parentId, username, filename, true, 0);
        }
        return "redirect:/id/" + parentId;
    }

    @RequestMapping("/delete/{parentId}/{ids}")
    public String deleteDir(Principal principal, @PathVariable long parentId, @PathVariable String ids) {
        String username = principal.getName();
        dirService.removeDir(ids, username);
        return "redirect:/id/" + parentId;
    }
}
