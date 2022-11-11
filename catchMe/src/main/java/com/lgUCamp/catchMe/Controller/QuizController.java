package com.lgUCamp.catchMe.Controller;

import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.Pagenation;
import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.SelectCriteria;
import com.lgUCamp.catchMe.DTO.Quiz.Quiz;
import com.lgUCamp.catchMe.DTO.Quiz.QuizParamsDTO;
import com.lgUCamp.catchMe.DTO.Quiz.UserQuizScoreDTO;
import com.lgUCamp.catchMe.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class QuizController {

    @Autowired
    QuizService quizService;

    private final int MAX_LINE = 5;

    @RequestMapping("/quiz/quizCateList")
    public String quizScoreRanking(Model model) {


        ArrayList<UserQuizScoreDTO> quizList = new ArrayList<>();
        quizList.add(quizService.userQuizScoreOne());
        quizList.add(quizService.userQuizScoreTwo());
        quizList.add(quizService.userQuizScoreThree());
        quizList.add(quizService.userQuizScoreFour());


        System.out.println(quizList);


        model.addAttribute("quizList", quizList);

        return "quiz/quizCateList";
    }

    @RequestMapping("/test")
    public String testController() {

        return "quiz/test";
    }

    @RequestMapping("/api/quiz")
    @ResponseBody
    public Map<String, Object> quizRun(@RequestBody QuizParamsDTO quizCate,
                                       @PageableDefault(size = MAX_LINE) Pageable pageable) {


        System.out.println("ajax 호출 완료 " + quizCate.getQuizCateNo());
        System.out.println(quizCate.getPage());

        Map<String, Object> map = new HashMap<>();
        map = quizService.selectQuizSolveList(quizCate.getQuizCateNo(), pageable);

        return map;


    }

    @RequestMapping("/quiz/quizRun")
    public String QuizPage(Model model, Pageable pageable, int quizCateNo) {

        System.out.println("이동 컨트롤러 호출 완료 " + quizCateNo);

        model.addAttribute("pageable", pageable);
        model.addAttribute("quizCateNo", quizCateNo);

        System.out.println("이동 컨트롤러 리턴 완료 " + quizCateNo);

        return "quiz/quizRun";
    }


    /* 이동 */
    @RequestMapping("/quiz/quizSolve")
    public String quizSolve(@RequestParam int searchCondition, String currentPage, Model model, HttpServletRequest request) {


        // 페이징 및 검색 처리
//        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if (currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        int totalCount = 10;

        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 1;        //얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 3;

        /* 페이징 처리를 위한 로직을 가져오고, 페이징 처리에 대한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;
        selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, String.valueOf(searchCondition));

        ArrayList<Quiz> quizList = quizService.selectQuizList(selectCriteria);

        /* 랜덤으로 섞어준다. */
        Collections.shuffle(quizList);

        /* 담아줄 리스트 만들기 */
        ArrayList<Quiz> randomQuiz = new ArrayList<>();

        /* random.nextInt 로 하면 중복값이 존재할 수 있기 때문에
            셔플로 리스트를 섞어준 후 인덱스번호 10개의 값을 뽑아 빈 리스트에 담는다.*/
        for (int i = 0; i < quizList.size(); i++) {

            randomQuiz.add(i, quizList.get(i));
        }

//        int quizScoreSum = Integer.valueOf(request.getParameter("quizScoreSum"));

//        System.out.println(quizScoreSum);

        model.addAttribute("quizList", randomQuiz);
        model.addAttribute("selectCriteria", selectCriteria);

        return "quiz/quizSolve";
    }

    @RequestMapping("quiz/Score")
    public String quizScore(){

        return "quiz/Score";
    }

}