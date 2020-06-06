package ImageHoster.controller;

import ImageHoster.model.Comments;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComments(@PathVariable("imageId") Integer id, @PathVariable("imageTitle") String title,
                              @RequestParam("comment") String comment, HttpSession session) throws IOException
    {
        Comments newComment = new Comments();
        User user = (User) session.getAttribute("loggeduser");
        newComment.setUser(user);
        Image image = imageService.getImageById(id, title);
        newComment.setImage(image);
        newComment.setText(comment);
        newComment.setDate(LocalDate.now());
        commentService.addComments(newComment);
        return "redirect:/images/"  + image.getId() + "/" + image.getTitle();
    }


}
