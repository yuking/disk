package yu.web;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yu.service.DirService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class FileUploadController {

    //private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);
    private final String ROOT;

    private final DirService dirService;

    private final ResourceLoader resourceLoader;

    @Autowired
    public FileUploadController(ResourceLoader resourceLoader, DirService dirService, Environment env) {
        this.resourceLoader = resourceLoader;
        this.dirService = dirService;
        this.ROOT = env.getRequiredProperty("yu.root");
    }

//    @GetMapping(value = "/upload")
//    public String provideUploadInfo(Model model) throws IOException {
//
//        return "upload";
//    }

    @RequestMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/upload/{parentId}")
    public String handleFileUpload(Principal principal, @RequestParam("file") MultipartFile file,
                                   @PathVariable long parentId, RedirectAttributes redirectAttributes) throws FileUploadBase.SizeLimitExceededException {
        System.out.println("--------------------------------------------");
        System.out.println("i am at the beginning of fileupload method ...");
        System.out.println("--------------------------------------------");
        String username = principal.getName();
        if (parentId != 0 && !dirService.checkOwner(parentId, username)) {
            return "redirect:/id/0";
        }
        String filename = file.getOriginalFilename();
        if (dirService.existDir(parentId, filename, username, false)) {
            return "redirect:/id/" + parentId;
        }
        long size = file.getSize();
        dirService.addDir(parentId, username, filename, false, size);
        long fileId = dirService.getId(parentId, username, filename);
        System.out.println("--------------------------------------------");
        System.out.println("file: " + filename + " has been uploaded");
        System.out.println("--------------------------------------------");
        if (!file.isEmpty()) {
            try {
                Files.copy(file.getInputStream(), Paths.get(ROOT, String.valueOf(fileId)));
                return "redirect:/id/" + parentId;
            } catch (IOException | RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
                dirService.removeDir(String.valueOf(fileId), username);
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return "uploadError";
    }

}
