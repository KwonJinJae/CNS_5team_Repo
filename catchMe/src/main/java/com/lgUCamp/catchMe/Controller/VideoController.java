package com.lgUCamp.catchMe.Controller;



import com.lgUCamp.catchMe.DTO.VideoDTO;
import com.lgUCamp.catchMe.Service.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class VideoController {


    @Autowired
    VideoServiceImpl videoService;





    @RequestMapping("/videolist")
    public String videolist(Model model)  {



        model.addAttribute("videolist",videoService.videolist());


        return "videolist";


    }

    @RequestMapping("/videoDetail")
    public String videoDetail(@RequestParam(required = false) int video_no, Model model) {
        model.addAttribute("onevideo",videoService.oneVideolist(video_no));
        model.addAttribute("videolist",videoService.videolist());

        return "videoDetail";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(required = false) String range, @RequestParam(required = false) String search_input,Model model){
        if( range.equals("title")){
            model.addAttribute("videolist",videoService.Keyword_title_Videolist("%"+search_input+"%"));

        }else if(range.equals("content")){
            model.addAttribute("videolist",videoService.Keyword_content_Videolist("%"+search_input+"%"));

        }else if(range.equals("all")){
            model.addAttribute("videolist",videoService.Keyword_all_Videolist("%"+search_input+"%"));

        }
        return "videolist";
    }

    @RequestMapping("/insert")
    public String insert(Model model){
        model.addAttribute("videolist",videoService.videolist());
        return "videoInsert";
    }
    @RequestMapping("/insertdata")
    public String insertdata(Model model, @RequestParam(required = false) String title,@RequestParam(required = false) String url,
                             @RequestParam(required = false) String img,@RequestParam(required = false) String content){

        videoService.insertVideo(new VideoDTO(title,url,content,img));

        return "redirect:/videolist";

    }

    @RequestMapping("/videofix")
    public String videofix(Model model, @RequestParam int video_no){
        model.addAttribute("onevideo",videoService.oneVideolist(video_no));
        return "videofix";
    }

    @RequestMapping("/fixdata")
    public  String  fixdata(@RequestParam String video_title, @RequestParam String video_url,
                            @RequestParam String video_img,@RequestParam String video_content, @RequestParam int video_no){
       videoService.updateVideo(new VideoDTO(video_no,video_title,video_url,video_content,video_img));
        return "redirect:/videoDetail?video_no="+video_no;
    }
    @RequestMapping("/videodel")
    public  String videodel(@RequestParam int video_no){
        videoService.deleteVideo(video_no);
        return "redirect:/videolist";
    }

}
