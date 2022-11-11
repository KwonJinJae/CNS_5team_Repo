package com.lgUCamp.catchMe.Controller;

import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.Pagenation;
import com.lgUCamp.catchMe.Controller.CarbonInfoPaging.SelectCriteria;
import com.lgUCamp.catchMe.DTO.AdminCarbonFile;
import com.lgUCamp.catchMe.DTO.CarbonInfoDTO;
import com.lgUCamp.catchMe.DTO.CarbonInfoFileDTO;
import com.lgUCamp.catchMe.DTO.MessageDTO;
import com.lgUCamp.catchMe.Service.CarbonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class CarbonInfoController {

    @Autowired
    CarbonInfoService carbonInfoService;

    @Autowired
    ResourceLoader resourceLoader;

    /* 탄소중립 전체 게시글 조회 - 최신 날짜순 정렬 */
    @RequestMapping("/carbonInfo/carbonInfoList")
    public String carbonInfoList (Model model, HttpServletRequest request) {

        // 페이징 및 검색 처리
        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        /* 검색구분과 검색값을 받아온다. */
        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        int totalCount = carbonInfoService.carbonInfoCount(searchMap);

        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 10;		//얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 3;

        /* 페이징 처리를 위한 로직을 가져오고, 페이징 처리에 대한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;
        if(searchValue != null && !"".equals(searchValue)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        ArrayList<CarbonInfoDTO> carbonInfo = carbonInfoService.carbonInfoList(selectCriteria);

        model.addAttribute("carbonInfoList", carbonInfo);
        model.addAttribute("selectCriteria", selectCriteria);

        return "carbonInfo/carbonInfoList";
    }

    /* 탄소중립 상세 게시글 조회 */
    @RequestMapping("/carbonInfo/carbonInfoDetail")
    public String carbonInfoDetail (@RequestParam int infoNo, Model model,
                                    HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies =request.getCookies();

        // 번호는 해석 순서입니다. 로직 진행 순서는 이대로 해주세요
        // 쿠키를 담을 수 있는 변수를 만들고, request로 받아온 쿠키 중에 infoView가 있는지 확인
        Cookie oldCookie = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("infoView")){
                    oldCookie = cookie;
                }
            }
        }

        // 2. 이미 조회한 사람일 경우
        if(oldCookie != null){
            if(!oldCookie.getValue().contains("_[" + infoNo + "]")){
                this.carbonInfoService.updateCarbonInfoView(infoNo);
                oldCookie.setValue(oldCookie.getValue() + "_[" + infoNo + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);  // 쿠키 시간 설정
                response.addCookie(oldCookie);
            }

            // 1. 쿠키가 일치하지 않는 경우 = 새로 조회하는 사람일 경우
        } else {
            // 조회수 증가
            this.carbonInfoService.updateCarbonInfoView(infoNo);

            // infoView라는 이름으로 쿠키를 만들어준다. (다른 게시판은 꼭!!! 다른 이름 사용해주세요)
            Cookie newCookie = new Cookie("infoView", "_[" + infoNo + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);  // 24시간
            response.addCookie(newCookie);
        }

        model.addAttribute("carbonInfo", carbonInfoService.carbonInfoDetail(infoNo));

        return "carbonInfo/carbonInfoDetail";
    }

    /* 탄소중립 글 등록 페이지 이동 */
    @GetMapping("/carbonInfo/carbonInfoRegist")
    public String carbonInfoRegist (Model model, HttpServletRequest request){

        /* 화면에 작성자명을 보여주기 위해 세션에서 로그인 정보를 받아
           관리자 테이블의 직무와 관리자번호 정보를 불러온다. */
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // 포맷 적용
        String today = now.format(formatter);

        model.addAttribute("today", today);

        return "carbonInfo/carbonInfoRegist";
    }

    /* 탄소 중립 게시글 등록 */
    @RequestMapping("carbonInfo/carbonInfoRegist/run")
    public String carbonInfoRegistRun (CarbonInfoDTO carbonInfo, CarbonInfoFileDTO carbonInfoFile,
                                       Model model, MessageDTO params,
                                       HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file ) throws IOException {

        /* th:value 에 담아뒀던 관리자 번호를 DTO에 set 해주어 함께 insert 한다.
            지금은 임의로 1을 담아 insert한다.
         */
        int adminNo = 1;
        carbonInfo.setAdminNo(adminNo);

        carbonInfoService.carbonInfoInsert(carbonInfo);

        /* 사진이 null이 아닐 경우 insert 진행 */
        if(file != null){

            /* 등록한 게시글의 번호를 불러온다. */
            int infoNo = carbonInfoService.carbonInfoNoSelect();
            carbonInfoFile.setInfoNo(infoNo);
            insertImage(file, request, carbonInfoFile);
        }

        /* 등록 완료를 알려주기 위한 처리 */
        params.setMessage("탄소 중립 정보 게시글 등록이 완료되었습니다.");
        params.setRedirectUri("/carbonInfo/carbonInfoList");

        model.addAttribute("params", params);

        return "carbonInfo/carbonInfoMessage";
    }

    /* 탄소 중립 게시글 수정을 위한 조회 */
    @RequestMapping("/carbonInfo/carbonInfoModifySelect/{infoNo}")
    public String carbonInfoModifySelect (@PathVariable int infoNo , Model model){

        model.addAttribute("carbonInfo", carbonInfoService.carbonInfoDetail(infoNo));

        return "carbonInfo/carbonInfoModify";
    }

    /* 탄소 중립 게시글 수정 */
    @PostMapping("/carbonInfo/carbonInfoModifySelect/run")
    public String carbonInfoModyfy (CarbonInfoDTO carbonInfo, CarbonInfoFileDTO carbonInfoFile, Model model, MessageDTO params,
                                    HttpServletRequest request, @RequestParam String status,
                                    @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        System.out.println(status);
//        System.out.println(status2);

        /* 업무 로직상 안전성을 위해 테이블 별로 나누어 수정한다. 트랜잭션 처리한다. */
        int modifyResult = carbonInfoService.carbonInfoModify(carbonInfo);

        int infoNo = carbonInfo.getInfoNo();

        /* 사진이 null이 아닐 경우 insert 진행 */

        if("delete".equals(status)){

            /* 저장된 파일 내역 삭제 */
            new File(carbonInfoFile.getInfoFilePath() +
                    "/" + carbonInfoFile.getInfoFileName()).delete();

            int deleteFileResult = carbonInfoService.carbonInfoFileDelete(infoNo);

        }

//        if ("uploading".equals(status2)){

            /* 등록한 게시글의 번호를 불러온다. */
//            carbonInfoFile.setInfoNo(infoNo);
//            insertImage(file, request, carbonInfoFile);

//        }
        if(file != null){

            /* 등록한 게시글의 번호를 불러온다. */
            carbonInfoFile.setInfoNo(infoNo);
            insertImage(file, request, carbonInfoFile);
        }

        /* 파일 테이블 수정 */

        if(modifyResult == 1){

            params.setMessage("탄소 중립 정보 게시글 수정이 완료되었습니다.");
            params.setRedirectUri("/carbonInfo/carbonInfoDetail?infoNo=" + infoNo);

        } else {

            params.setMessage("탄소 중립 정보 게시글 수정이 정상적으로 처리되지 않았습니다.");
            // 에러페이지 연결
            params.setRedirectUri("/carbonInfo/carbonInfoDetail?infoNo=" + infoNo);
        }

        model.addAttribute("params", params);

        return "carbonInfo/carbonInfoMessage";
    }



    /* Ajax를 이용해 summernote 이미지 저장 */
    @RequestMapping(value="resources/static/carbonInfoImage")
    public ResponseEntity<?> summerimage(@RequestParam("file") MultipartFile img,
                                                      HttpServletRequest request) throws IOException {

        String path = request.getServletContext().getRealPath("/resources/static/carbonInfoImage");
        Random random = new Random();

        long currentTime = System.currentTimeMillis();
        int	randomValue = random.nextInt(100);
        String fileName = Long.toString(currentTime) + "_" + randomValue + "_a_" + img.getOriginalFilename();

        File file = new File(path , fileName);
        img.transferTo(file);

        return ResponseEntity.ok().body("/resources/static/carbonInfoImage"+fileName);

    }

    /* 파일 저장 */
    public void insertImage(@RequestParam("file") MultipartFile file,
                                                 HttpServletRequest request, CarbonInfoFileDTO carbonInfoFile) throws IOException {

        String filePath = "/carbonInfoFile";
        String savePath = System.getProperty("user.dir")+"/src/main/resources/static"+filePath;
        Random random = new Random();

        /* 파일 저장경로가 존재하지 않을 경우를 대비해 생성하는 코드 */
        File directory = new File(savePath);
        if (!directory.exists()) {
            System.out.println("폴더 생성 : " + directory.mkdir());
        }

        List<MultipartFile> fileList = new ArrayList<>();
        fileList.add(file);
        for (MultipartFile fileSave : fileList) {
            if (fileSave.getSize() > 0) {

                /* 파일명 전달받기 */
                String fileName = file.getOriginalFilename();

                long currentTime = System.currentTimeMillis();

                /* 파일명 변경 */
                String ext = fileName.substring(fileName.lastIndexOf("."));
                String realName = fileName.substring(0, fileName.lastIndexOf("."));
                String fileSaveName = Long.toString(currentTime) + "_" + realName + ext;

                try {

                    file.transferTo(new File(savePath + "/" + fileSaveName));

                    /* DB에 업로드할 파일의 정보를 DTO에 set */
                    carbonInfoFile.setInfoFileName(fileSaveName);
                    carbonInfoFile.setInfoFilePath(filePath);

                    carbonInfoService.carbonInfoFileInsert(carbonInfoFile);

                } catch (IllegalStateException | IOException e) {

                    e.printStackTrace();

                    /* 실패시 파일 삭제 */
                    new File(savePath + "/" + fileSaveName).delete();

//                    return "redirect:/admin/error.html";

                }
            }
        }
    }

    /* 파일 다운로드 */
//    @GetMapping("/{fileName}")
//    public ResponseEntity<Resource> resouceFileDownload(@PathVariable String fileName) {
//        try {
////            Resource resource = resourceLoader.getResource("classpath:static/carbonInfoFile/"+URLDecoder.decode(fileName, "UTF-8"));
//            InputStream inputStream = new ClassPathResource("src/main/resource/static/carbonInfoFile").getInputStream();
//
//            FileUtils.copyInputStreamToFile(inputStream, fileName);
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION,file.getName())	//다운 받아지는 파일 명 설정
//                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))	//파일 사이즈 설정
//                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())	//바이너리 데이터로 받아오기 설정
//                    .body(resource);	//파일 넘기기
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest()
//                    .body(null);
//
//        } catch (Exception e ) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


    /* 탄소중립 정보글 삭제 */
    @RequestMapping("/carbonInfo/carbonInfoDelete/{infoNo}")
    public String carbonInfoDelete (@PathVariable int infoNo, AdminCarbonFile adminCarbonFile ,
                                    HttpServletRequest request, MessageDTO params, Model model){

        /* request의 session 정보와 작성자 정보를 비교해 본인만 삭제할 수 있도록 한다. */

        /* 파일명을 받아 null이 아닐 경우 파일을 먼저 삭제한다. */
        if(adminCarbonFile.getCarbonInfoFileList().getInfoFileName() != null){

            /* 저장된 파일 내역 삭제 */
            new File(adminCarbonFile.getCarbonInfoFileList().getInfoFilePath() +
                    "/" + adminCarbonFile.getCarbonInfoFileList().getInfoFileName()).delete();

            /* 파일 정보 삭제 */
            int deleteFileResult = carbonInfoService.carbonInfoFileDelete(infoNo);
        }
        
        int deleteResult = carbonInfoService.carbonInfoDelete(infoNo);

        if (deleteResult == 1) {

            params.setMessage("해당 탄소 중립 정보 게시글 삭제가 완료되었습니다.");
            params.setRedirectUri("/carbonInfo/carbonInfoList");

        } else {

            // 에러페이지로 보내기
            params.setMessage("해당 탄소 중립 정보 게시글 삭제가 정상적으로 처리되지 않았습니다. \n 관리자에게 문의해주세요.");
            params.setRedirectUri("/carbonInfo/carbonInfoList");

        }

        model.addAttribute("params", params);

        return "carbonInfo/carbonInfoMessage";
    }


//    @GetMapping("/deleteFile/{infoNo}")
//    public String deleteFile(@PathVariable int infoNo){
//
//        int deleteFileResult = carbonInfoService.carbonInfoFileDelete(infoNo);
//
//            return "redirect:/carbonInfo/carbonInfoDetail?infoNo=" + infoNo;
//    }



}
