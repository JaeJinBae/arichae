package com.webaid.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webaid.domain.AdviceVO;
import com.webaid.domain.Category1VO;
import com.webaid.domain.ClinicVO;
import com.webaid.domain.EventVO;
import com.webaid.domain.HospitalTimeVO;
import com.webaid.domain.MediaVO;
import com.webaid.domain.NoticeVO;
import com.webaid.domain.PageMaker;
import com.webaid.domain.PopupVO;
import com.webaid.domain.ReservationVO;
import com.webaid.domain.SearchCriteria;
import com.webaid.domain.StatisticSelectDateVO;
import com.webaid.domain.StatisticVO;
import com.webaid.domain.YoutubeVO;
import com.webaid.service.AdviceService;
import com.webaid.service.Category1Service;
import com.webaid.service.ClinicService;
import com.webaid.service.EventService;
import com.webaid.service.HospitalTimeService;
import com.webaid.service.MediaService;
import com.webaid.service.NoticeService;
import com.webaid.service.PopupService;
import com.webaid.service.ReservationService;
import com.webaid.service.StatisticService;
import com.webaid.service.YoutubeService;
import com.webaid.util.FileDelete;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin*")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private NoticeService nService;
	
	@Autowired
	private MediaService mService;
	
	@Autowired
	private AdviceService aService;
	
	@Autowired
	private StatisticService sService;
	
	@Autowired
	private PopupService pService;
	
	@Autowired
	private EventService eService;
	
	@Autowired
	private HospitalTimeService htService;
	
	@Autowired
	private ClinicService cService;
	
	@Autowired
	private ReservationService resService;
	
	@Autowired
	private Category1Service c1Service;
	
	@Autowired
	private YoutubeService yService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String mainLogin(Model model) {
		logger.info("mainLogin GET");
		
		return "admin/login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		if(session != null){
			session.invalidate();
		}
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("login GET");
		
		return "admin/login";
	}
	
	@RequestMapping(value = "/admLogin", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody Map<String, String> info, HttpSession session) {
		logger.info("loginIdPwChk");
		ResponseEntity<String> entity = null;
		
		if(info.get("id").equals("prive") && info.get("pw").equals("prive5475")){
			session.setAttribute("id", "prive");
			entity = new ResponseEntity<String>("ok", HttpStatus.OK);
		}else{
			entity = new ResponseEntity<String>("no", HttpStatus.OK);
		}
		return entity;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		logger.info("main GET");
		List<AdviceVO> list = aService.selectNonComplete();
		
		model.addAttribute("list", list);
		return "admin/main";
	}
	
	@ResponseBody
	@RequestMapping("/imgUpload/{btype}")
	public Map<String, Object> imgaeUpload(@PathVariable("btype") String btype, HttpServletRequest req, @RequestParam MultipartFile upload, Model model)
			throws Exception {
		logger.info("image upload!!!!!");

		// http body
		OutputStream out = null;

		Map<String, Object> map = new HashMap<String, Object>();

		// 오리지날 파일명
		String originalName = upload.getOriginalFilename();

		// 랜덤이름 생성(중복 방지용)
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;

		// 업로드한 파일 이름
		String fileName = savedName;

		// 바이트 배열로 변환
		byte[] bytes = upload.getBytes();

		// 이미지를 업로드할 디렉토리(배포경로로 설정)
		String innerUploadPath = "";
		if(btype.equals("notice")){
			innerUploadPath = "resources/uploadNotice/";
		}else if(btype.equals("popup")){
			innerUploadPath = "resources/uploadPopup/";
		}else if(btype.equals("advice")){
			innerUploadPath = "resources/uploadAdvice/";
		}else if(btype.equals("media")){
			innerUploadPath = "resources/uploadMedia/";
		}else if(btype.equals("youtube")){
			innerUploadPath = "resources/uploadYoutube/";
		}
		
		String uploadPath = (req.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		logger.info(uploadPath);

		out = new FileOutputStream(new File(uploadPath + fileName));// 서버에 파일 저장
		// 서버에 저장됨
		out.write(bytes);

		String fileUrl = "/" + innerUploadPath + fileName;

		System.out.println(fileUrl);

		map.put("uploaded", 1);
		map.put("fileName", fileName);
		map.put("url", fileUrl);
		
		return map;
	}
	
	
	
	@RequestMapping(value = "/menu01_01", method = RequestMethod.GET)
	public String menu01_01(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu01_01 GET");

		List<AdviceVO> list = aService.listSearch(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(aService.listSearchCount(cri));
		pageMaker.setFinalPage(aService.listSearchCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu01_01";
	}
	
	@RequestMapping(value = "/menu01_01register", method = RequestMethod.GET)
	public String menu01_01register() throws Exception {
		logger.info("menu01_01register GET");
		
		return "admin/menu01_01register";
	}
	
	@RequestMapping(value = "/menu01_01register", method = RequestMethod.POST)
	public String menu01_01registerPost(MultipartHttpServletRequest mtfReq, Model model) throws IOException {
		logger.info("menu01_01register POST");
		
		AdviceVO vo = new AdviceVO();
		
		vo.setName(mtfReq.getParameter("name"));
		vo.setPhone(mtfReq.getParameter("phone"));
		vo.setRegdate(mtfReq.getParameter("regdate"));
		vo.setEmail(mtfReq.getParameter("email"));
		vo.setState(mtfReq.getParameter("state"));
		vo.setSecret(mtfReq.getParameter("secret"));
		vo.setPw(mtfReq.getParameter("pw"));
		vo.setTitle(mtfReq.getParameter("title"));
		vo.setContent(mtfReq.getParameter("content"));
		vo.setReply(mtfReq.getParameter("reply"));
		vo.setMemo(mtfReq.getParameter("memo"));
		vo.setReply_date(mtfReq.getParameter("reply_date"));
		
		aService.insert(vo);
		return "redirect:/admin/menu01_01";
	}
	
	@RequestMapping(value = "/menu01_01update", method = RequestMethod.GET)
	public String menu01_01update(int no, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu01_01update GET");
		
		AdviceVO vo = aService.selectOne(no);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(aService.listSearchCount(cri));

		model.addAttribute("item", vo);
		model.addAttribute("pageMaker", pageMaker);
		return "admin/menu01_01update";
	}
	
	@RequestMapping(value = "/menu01_01update", method = RequestMethod.POST)
	public String menu01_01updatePOST(MultipartHttpServletRequest mtfReq, int page, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rtts) throws Exception {
		logger.info("menu01_01update POST");		
		
		AdviceVO vo = new AdviceVO();
		
		vo.setNo(Integer.parseInt(mtfReq.getParameter("no")));
		vo.setName(mtfReq.getParameter("name"));
		vo.setPhone(mtfReq.getParameter("phone"));
		vo.setEmail(mtfReq.getParameter("email"));
		vo.setState(mtfReq.getParameter("state"));
		vo.setSecret(mtfReq.getParameter("secret"));
		vo.setTitle(mtfReq.getParameter("title"));
		vo.setContent(mtfReq.getParameter("content"));
		vo.setReply(mtfReq.getParameter("reply"));
		vo.setReply_date(mtfReq.getParameter("reply_date"));
		vo.setMemo(mtfReq.getParameter("memo"));
		
		aService.update(vo);
		
		rtts.addAttribute("no", mtfReq.getParameter("no"));

		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.makeSearch(page);
		pageMaker.setTotalCount(aService.listSearchCount(cri));

		rtts.addAttribute("page", page);
		return "redirect:/admin/menu01_01update";
	}
	
	@RequestMapping(value="/menu01_01delete/{no}", method=RequestMethod.GET)
	public String menu01_01delete(@PathVariable("no") int no){
		logger.info("advice delete");
		
		aService.delete(no);
		
		return "redirect:/admin/menu01_01";
	}
	
	@RequestMapping(value = "/menu01_02", method = RequestMethod.GET)
	public String menu01_02(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu01_02 GET");
		
		List<NoticeVO> topList = nService.selectTopNotice("o");
		List<NoticeVO> list = nService.listSearchAll(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(nService.listSearchCountAll(cri));
		pageMaker.setFinalPage(nService.listSearchCountAll(cri));
		
		model.addAttribute("topList", topList);
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu01_02";
	}
	
	@RequestMapping(value = "/menu01_02register", method = RequestMethod.GET)
	public String menu01_02register(NoticeVO vo) {
		logger.info("menu01_02register GET");

		return "admin/menu01_02register";
	}
	
	@RequestMapping(value = "/menu01_02register", method = RequestMethod.POST)
	public String menu01_02registerPOST(NoticeVO vo) {
		logger.info("menu01_02register POST");
		System.out.println(vo);
		nService.insert(vo);
		return "redirect:/admin/menu01_02";
	}
	
	@RequestMapping(value = "/menu01_02update", method = RequestMethod.GET)
	public String menu01_02update(int no, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu01_02update GET");
		
		NoticeVO vo = nService.selectOne(no);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(nService.listSearchCountAll(cri));

		model.addAttribute("item", vo);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu01_02update";
	}
	
	@RequestMapping(value = "/menu01_02update", method = RequestMethod.POST)
	public String menu01_02updatePost(NoticeVO vo, int page, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rtts, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu01_02update Post");
		
		nService.update(vo);

		rtts.addAttribute("no", vo.getNo());

		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.makeSearch(page);
		pageMaker.setTotalCount(nService.listSearchCountAll(cri));

		rtts.addAttribute("page", page);

		return "redirect:/admin/menu01_02update";
	}
	
	@RequestMapping(value="/menu01_02delete/{no}", method=RequestMethod.GET)
	public String menu01_02delete(@PathVariable("no") int no){
		logger.info("notice delete");
		
		nService.delete(no);
		
		return "redirect:/admin/menu01_02";
	}
	
	@RequestMapping(value = "/menu01_03", method = RequestMethod.GET)
	public String menu01_03(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu01_03 GET");
		
		List<MediaVO> list = mService.listSearchAll(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(mService.listSearchCountAll(cri));
		pageMaker.setFinalPage(mService.listSearchCountAll(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu01_03";
	}
	
	@RequestMapping(value = "/menu01_03register", method = RequestMethod.GET)
	public String menu01_03register(Model model) {
		logger.info("menu01_03register GET");
		
		return "admin/menu01_03register";
	}
	
	@RequestMapping(value = "/menu01_03register", method = RequestMethod.POST)
	public String menu01_03registerPost(MultipartHttpServletRequest mtfReq) throws IOException {
		logger.info("menu01_03register POST");
		
		MediaVO vo = new MediaVO();
		
		vo.setNo(0);
		vo.setWriter(mtfReq.getParameter("writer"));
		vo.setRegdate(mtfReq.getParameter("regdate"));
		vo.setCnt(Integer.parseInt(mtfReq.getParameter("cnt")));
		vo.setTitle(mtfReq.getParameter("title"));
		vo.setContent(mtfReq.getParameter("content"));
		vo.setUse_state("o");
		
		//이미지 업로드
		String innerUploadPath = "resources/uploadMedia/";
		String path = (mtfReq.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		String fileName = "";
		String storedFileName = "";
		
		Iterator<String> files = mtfReq.getFileNames();
		mtfReq.getFileNames();
		while(files.hasNext()){
			String uploadFile = files.next();
			
			MultipartFile mFile = mtfReq.getFile(uploadFile);
			fileName = mFile.getOriginalFilename();
			if(fileName.length() == 0){
				storedFileName = "";
			}else{
				storedFileName = System.currentTimeMillis()+"_"+fileName;
			}
			
			vo.setThumb_origin(fileName);
			vo.setThumb_stored(storedFileName);
			
			try {
				mFile.transferTo(new File(path+storedFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//이미지 업로드 끝
		
		mService.insert(vo);
		
		return "redirect:/admin/menu01_03";
	}
	
	@RequestMapping(value = "/menu01_03update", method = RequestMethod.GET)
	public String menu01_03update(int no, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu01_03update GET");
		
		MediaVO vo = mService.selectOne(no);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(mService.listSearchCountAll(cri));

		model.addAttribute("item", vo);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu01_03update";
	}
	
	@RequestMapping(value = "/menu01_03update", method = RequestMethod.POST)
	public String menu01_03updatePOST(MultipartHttpServletRequest mtfReq, int page, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rtts) throws Exception {
		logger.info("menu01_03update POST");
		
		//이미지 업로드
		String innerUploadPath = "resources/uploadMedia/";
		String path = (mtfReq.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		String fileName = "";
		String storedFileName = "";
		
		Iterator<String> files = mtfReq.getFileNames();
		mtfReq.getFileNames();
		while(files.hasNext()){
			String uploadFile = files.next();
			
			MultipartFile mFile = mtfReq.getFile(uploadFile);
			fileName = mFile.getOriginalFilename();
			if(fileName.length() == 0){
				storedFileName = "";
			}else{
				storedFileName = System.currentTimeMillis()+"_"+fileName;
			}
			
			try {
				mFile.transferTo(new File(path+storedFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//이미지 업로드 끝
		
		String thumbState = mtfReq.getParameter("thumbState");
		
		
		MediaVO vo = new MediaVO();
		MediaVO prevVO = mService.selectOne(Integer.parseInt(mtfReq.getParameter("no")));
		
		vo.setNo(Integer.parseInt(mtfReq.getParameter("no")));
		vo.setWriter(mtfReq.getParameter("writer"));
		vo.setRegdate(mtfReq.getParameter("regdate"));
		vo.setCnt(Integer.parseInt(mtfReq.getParameter("cnt")));
		vo.setTitle(mtfReq.getParameter("title"));
		vo.setContent(mtfReq.getParameter("content"));
		vo.setUse_state(mtfReq.getParameter("use_state"));
		
		if(thumbState.equals("o")){
			vo.setThumb_origin(fileName);
			vo.setThumb_stored(storedFileName);
		}else{
			vo.setThumb_origin(prevVO.getThumb_origin());
			vo.setThumb_stored(prevVO.getThumb_stored());
		}

		mService.update(vo);
		
		rtts.addAttribute("no", mtfReq.getParameter("no"));
		
		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.makeSearch(page);
		pageMaker.setTotalCount(mService.listSearchCountAll(cri));

		rtts.addAttribute("page", page);
		
		return "redirect:/admin/menu01_03update";
	}
	
	@RequestMapping(value = "/menu01_03uploadImgDelete", method = RequestMethod.POST)
	public ResponseEntity<String> menu01_03uploadImgDelete(HttpServletRequest req, @RequestBody Map<String, String> info) {
		logger.info("menu01_03update POST");
		ResponseEntity<String> entity = null;
		
		int no = Integer.parseInt(info.get("no"));
		
		String innerUploadPath = "resources/uploadMedia/";
		String path = (req.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		System.out.println(path);
		MediaVO prevVO = mService.selectOne(no);
		FileDelete fd = new FileDelete();
		
		MediaVO vo = new MediaVO();
		vo.setNo(no);
		
		try {
			
			fd.fileDelete(path, prevVO.getThumb_stored());
			
			vo.setThumb_origin("");
			vo.setThumb_stored("");
			mService.updateThumb(vo);
			
			entity = new ResponseEntity<String>("ok", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>("no", HttpStatus.OK);
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@RequestMapping(value="/menu01_03delete/{no}", method=RequestMethod.GET)
	public String menu01_03delete(@PathVariable("no") int no){
		logger.info("menu01_03 delete");
		
		mService.delete(no);
		
		return "redirect:/admin/menu01_03";
	}
	
	//============= youtube menu01_04 ===============
	@RequestMapping(value = "/menu01_04", method = RequestMethod.GET)
	public String menu01_04(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu01_04 GET");
		
		List<YoutubeVO> list = yService.listSearchAll(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(yService.listSearchCountAll(cri));
		pageMaker.setFinalPage(yService.listSearchCountAll(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu01_04";
	}
	
	@RequestMapping(value = "/menu01_04register", method = RequestMethod.GET)
	public String menu01_04register(Model model) {
		logger.info("menu01_04register GET");
		
		return "admin/menu01_04register";
	}
	
	@RequestMapping(value = "/menu01_04register", method = RequestMethod.POST)
	public String menu01_04registerPost(MultipartHttpServletRequest mtfReq) throws IOException {
		logger.info("menu01_04register POST");
		
		YoutubeVO vo = new YoutubeVO();
		
		vo.setNo(0);
		vo.setWriter(mtfReq.getParameter("writer"));
		vo.setRegdate(mtfReq.getParameter("regdate"));
		vo.setCnt(Integer.parseInt(mtfReq.getParameter("cnt")));
		vo.setTitle(mtfReq.getParameter("title"));
		vo.setContent(mtfReq.getParameter("content"));
		vo.setUse_state("o");
		
		//이미지 업로드
		String innerUploadPath = "resources/uploadYoutube/";
		String path = (mtfReq.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		String fileName = "";
		String storedFileName = "";
		
		Iterator<String> files = mtfReq.getFileNames();
		mtfReq.getFileNames();
		while(files.hasNext()){
			String uploadFile = files.next();
			
			MultipartFile mFile = mtfReq.getFile(uploadFile);
			fileName = mFile.getOriginalFilename();
			if(fileName.length() == 0){
				storedFileName = "";
			}else{
				storedFileName = System.currentTimeMillis()+"_"+fileName;
			}
			
			vo.setThumb_origin(fileName);
			vo.setThumb_stored(storedFileName);
			
			try {
				mFile.transferTo(new File(path+storedFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//이미지 업로드 끝
		
		yService.insert(vo);
		
		return "redirect:/admin/menu01_04";
	}
	
	@RequestMapping(value = "/menu01_04update", method = RequestMethod.GET)
	public String menu01_04update(int no, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu01_04update GET");
		
		YoutubeVO vo = yService.selectOne(no);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(yService.listSearchCountAll(cri));

		model.addAttribute("item", vo);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu01_04update";
	}
	
	@RequestMapping(value = "/menu01_04update", method = RequestMethod.POST)
	public String menu01_04updatePOST(MultipartHttpServletRequest mtfReq, int page, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rtts) throws Exception {
		logger.info("menu01_04update POST");
		
		//이미지 업로드
		String innerUploadPath = "resources/uploadYoutube/";
		String path = (mtfReq.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		String fileName = "";
		String storedFileName = "";
		
		Iterator<String> files = mtfReq.getFileNames();
		mtfReq.getFileNames();
		while(files.hasNext()){
			String uploadFile = files.next();
			
			MultipartFile mFile = mtfReq.getFile(uploadFile);
			fileName = mFile.getOriginalFilename();
			if(fileName.length() == 0){
				storedFileName = "";
			}else{
				storedFileName = System.currentTimeMillis()+"_"+fileName;
			}
			
			try {
				mFile.transferTo(new File(path+storedFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//이미지 업로드 끝
		
		String thumbState = mtfReq.getParameter("thumbState");
		
		YoutubeVO vo = new YoutubeVO();
		YoutubeVO prevVO = yService.selectOne(Integer.parseInt(mtfReq.getParameter("no")));
		
		vo.setNo(Integer.parseInt(mtfReq.getParameter("no")));
		vo.setWriter(mtfReq.getParameter("writer"));
		vo.setRegdate(mtfReq.getParameter("regdate"));
		vo.setCnt(Integer.parseInt(mtfReq.getParameter("cnt")));
		vo.setTitle(mtfReq.getParameter("title"));
		vo.setContent(mtfReq.getParameter("content"));
		vo.setUse_state(mtfReq.getParameter("use_state"));
		
		if(thumbState.equals("o")){
			vo.setThumb_origin(fileName);
			vo.setThumb_stored(storedFileName);
		}else{
			vo.setThumb_origin(prevVO.getThumb_origin());
			vo.setThumb_stored(prevVO.getThumb_stored());
		}
		
		yService.update(vo);
		
		rtts.addAttribute("no", mtfReq.getParameter("no"));
		
		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.makeSearch(page);
		pageMaker.setTotalCount(yService.listSearchCountAll(cri));

		rtts.addAttribute("page", page);
		
		return "redirect:/admin/menu01_04update";
	}
	
	@RequestMapping(value = "/menu01_04uploadImgDelete", method = RequestMethod.POST)
	public ResponseEntity<String> menu01_04uploadImgDelete(HttpServletRequest req, @RequestBody Map<String, String> info) {
		logger.info("menu01_04update POST");
		ResponseEntity<String> entity = null;
		
		int no = Integer.parseInt(info.get("no"));
		
		String innerUploadPath = "resources/uploadYoutube/";
		String path = (req.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		
		YoutubeVO prevVO = yService.selectOne(no);
		FileDelete fd = new FileDelete();
		
		YoutubeVO vo = new YoutubeVO();
		vo.setNo(no);
		
		try {
			
			fd.fileDelete(path, prevVO.getThumb_stored());
			
			vo.setThumb_origin("");
			vo.setThumb_stored("");
			yService.updateThumb(vo);
			
			entity = new ResponseEntity<String>("ok", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>("no", HttpStatus.OK);
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@RequestMapping(value="/menu01_04delete/{no}", method=RequestMethod.GET)
	public String menu01_04delete(@PathVariable("no") int no){
		logger.info("menu01_04 delete");
		
		yService.delete(no);
		
		return "redirect:/admin/menu01_04";
	}
	
	@RequestMapping(value = "/menu02_01", method = RequestMethod.GET)
	public String menu02_01(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu02_01 GET");
		
		List<PopupVO> list = pService.listSearch(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(pService.listSearchCount(cri));
		pageMaker.setFinalPage(pService.listSearchCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu02_01";
	}
	
	@RequestMapping(value = "/menu02_01register", method = RequestMethod.GET)
	public String menu02_01register(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu02_01register GET");
		
		return "admin/menu02_01register";
	}
	
	@RequestMapping(value = "/menu02_01register", method = RequestMethod.POST)
	public String menu02_01registerPost(PopupVO vo) throws Exception {
		logger.info("menu02_01register POST");
		
		System.out.println(vo);
		pService.insert(vo);
		
		return "redirect:/admin/menu02_01";
	}
	
	@RequestMapping(value = "/menu02_01update", method = RequestMethod.GET)
	public String menu02_01update(int no, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu02_01update GET");
		
		PopupVO vo = pService.selectOne(no);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(pService.listSearchCount(cri));

		model.addAttribute("item", vo);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu02_01update";
	}
	
	@RequestMapping(value = "/menu02_01update", method = RequestMethod.POST)
	public String menu02_01updatePost(PopupVO vo, int page, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rtts, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu02_01update POST");
		
		pService.update(vo);
		
		rtts.addAttribute("no", vo.getNo());
		
		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.makeSearch(page);
		pageMaker.setTotalCount(pService.listSearchCount(cri));

		rtts.addAttribute("page", page);
		
		return "redirect:/admin/menu02_01update";
	}
	
	@RequestMapping(value="/menu02_01delete/{no}", method=RequestMethod.GET)
	public String menu02_01delete(@PathVariable("no") int no){
		logger.info("popup delete");
		
		pService.delete(no);
		
		return "redirect:/admin/menu02_01";
	}
	
	@RequestMapping(value = "/menu03_01", method = RequestMethod.GET)
	public String menu07_01(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu03_01 GET");
		
		return "admin/menu03_01";
	}
	
	@RequestMapping(value="/menu03_01InfoGet/{type}/{s_date}/{e_date}", method=RequestMethod.GET)
	public ResponseEntity<List<List<String>>> menu07_01InfoGet(@PathVariable("type") String type, @PathVariable("s_date") String s_date, @PathVariable("e_date") String e_date) throws ParseException{
		ResponseEntity<List<List<String>>> entity = null;
		
		StatisticSelectDateVO ssdVO = new StatisticSelectDateVO();
		ssdVO.setS_date(s_date);
		ssdVO.setE_date(e_date);
		List<StatisticVO> list = sService.selectByDate(ssdVO);
		List<List<String>> totalList = new ArrayList<List<String>>();
		if(type.equals("time")){
			int t_00=0;int t_01=0;int t_02=0;int t_03=0;int t_04=0;int t_05=0;int t_06=0;int t_07=0;int t_08=0;int t_09=0;int t_10=0;
			int t_11=0;int t_12=0;int t_13=0;int t_14=0;int t_15=0;int t_16=0;int t_17=0;int t_18=0;int t_19=0;int t_20=0;int t_21=0;int t_22=0;int t_23=0;
			for(int i=0; i<list.size();i++){
				if(list.get(i).getHour()==0){
					t_00++;
				}else if(list.get(i).getHour()==1){
					t_01++;
				}else if(list.get(i).getHour()==2){
					t_02++;
				}else if(list.get(i).getHour()==3){
					t_03++;
				}else if(list.get(i).getHour()==4){
					t_04++;
				}else if(list.get(i).getHour()==5){
					t_05++;
				}else if(list.get(i).getHour()==6){
					t_06++;
				}else if(list.get(i).getHour()==7){
					t_07++;
				}else if(list.get(i).getHour()==8){
					t_08++;
				}else if(list.get(i).getHour()==9){
					t_09++;
				}else if(list.get(i).getHour()==10){
					t_10++;
				}else if(list.get(i).getHour()==11){
					t_11++;
				}else if(list.get(i).getHour()==12){
					t_12++;
				}else if(list.get(i).getHour()==13){
					t_13++;
				}else if(list.get(i).getHour()==14){
					t_14++;
				}else if(list.get(i).getHour()==15){
					t_15++;
				}else if(list.get(i).getHour()==16){
					t_16++;
				}else if(list.get(i).getHour()==17){
					t_17++;
				}else if(list.get(i).getHour()==18){
					t_18++;
				}else if(list.get(i).getHour()==19){
					t_19++;
				}else if(list.get(i).getHour()==20){
					t_20++;
				}else if(list.get(i).getHour()==21){
					t_21++;
				}else if(list.get(i).getHour()==22){
					t_22++;
				}else if(list.get(i).getHour()==23){
					t_23++;
				}
			}
			
			List<String> part_list00 = new ArrayList<String>(); part_list00.add("00"); part_list00.add(t_00+"");
			List<String> part_list01 = new ArrayList<String>(); part_list01.add("01"); part_list01.add(t_01+"");
			List<String> part_list02 = new ArrayList<String>(); part_list02.add("02"); part_list02.add(t_02+"");
			List<String> part_list03 = new ArrayList<String>(); part_list03.add("03"); part_list03.add(t_03+"");
			List<String> part_list04 = new ArrayList<String>(); part_list04.add("04"); part_list04.add(t_04+"");
			List<String> part_list05 = new ArrayList<String>(); part_list05.add("05"); part_list05.add(t_05+"");
			List<String> part_list06 = new ArrayList<String>(); part_list06.add("06"); part_list06.add(t_06+"");
			List<String> part_list07 = new ArrayList<String>(); part_list07.add("07"); part_list07.add(t_07+"");
			List<String> part_list08 = new ArrayList<String>(); part_list08.add("08"); part_list08.add(t_08+"");
			List<String> part_list09 = new ArrayList<String>(); part_list09.add("09"); part_list09.add(t_09+"");
			List<String> part_list10 = new ArrayList<String>(); part_list10.add("10"); part_list10.add(t_10+"");
			List<String> part_list11 = new ArrayList<String>(); part_list11.add("11"); part_list11.add(t_11+"");
			List<String> part_list12 = new ArrayList<String>(); part_list12.add("12"); part_list12.add(t_12+"");
			List<String> part_list13 = new ArrayList<String>(); part_list13.add("13"); part_list13.add(t_13+"");
			List<String> part_list14 = new ArrayList<String>(); part_list14.add("14"); part_list14.add(t_14+"");
			List<String> part_list15 = new ArrayList<String>(); part_list15.add("15"); part_list15.add(t_15+"");
			List<String> part_list16 = new ArrayList<String>(); part_list16.add("16"); part_list16.add(t_16+"");
			List<String> part_list17 = new ArrayList<String>(); part_list17.add("17"); part_list17.add(t_17+"");
			List<String> part_list18 = new ArrayList<String>(); part_list18.add("18"); part_list18.add(t_18+"");
			List<String> part_list19 = new ArrayList<String>(); part_list19.add("19"); part_list19.add(t_19+"");
			List<String> part_list20 = new ArrayList<String>(); part_list20.add("20"); part_list20.add(t_20+"");
			List<String> part_list21 = new ArrayList<String>(); part_list21.add("21"); part_list21.add(t_21+"");
			List<String> part_list22 = new ArrayList<String>(); part_list22.add("22"); part_list22.add(t_22+"");
			List<String> part_list23 = new ArrayList<String>(); part_list23.add("23"); part_list23.add(t_23+"");
			totalList.add(part_list00); totalList.add(part_list01); totalList.add(part_list02);
			totalList.add(part_list03); totalList.add(part_list04); totalList.add(part_list05);
			totalList.add(part_list06); totalList.add(part_list07); totalList.add(part_list08);
			totalList.add(part_list09); totalList.add(part_list10); totalList.add(part_list11);
			totalList.add(part_list12); totalList.add(part_list13); totalList.add(part_list14);
			totalList.add(part_list15); totalList.add(part_list16); totalList.add(part_list17);
			totalList.add(part_list18); totalList.add(part_list19); totalList.add(part_list20);
			totalList.add(part_list21); totalList.add(part_list22); totalList.add(part_list23);
			entity = new ResponseEntity<List<List<String>>>(totalList, HttpStatus.OK);
		}else if(type.equals("date")){
			List<String> dateList = new ArrayList<>();
			HashMap<String, Integer> map = new HashMap<>();
			
			String s1=s_date;
			String s2=e_date;

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			//Date타입으로 변경
			Date d1 = df.parse( s1 );
			Date d2 = df.parse( s2 );

			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();

			//Calendar 타입으로 변경 add()메소드로 1일씩 추가해 주기위해 변경
			c1.setTime( d1 );
			c2.setTime( d2 );
			
			Date d3 = null;
			//시작날짜와 끝 날짜를 비교해, 시작날짜가 작거나 같은 경우 출력
			while( c1.compareTo( c2 ) !=1 ){
				d3 = new Date(c1.getTimeInMillis());
				
				//출력
				dateList.add(df.format(d3));
				map.put(df.format(d3), 0);
	
				//시작날짜 + 1 일
				c1.add(Calendar.DATE, 1);
			}
			
			TreeMap<String, Integer> tm = new TreeMap<String, Integer>(map);
			Iterator<String> iterKey = tm.keySet().iterator();
			
			for(int i=0;i<list.size();i++){
				tm.put(list.get(i).getDate(), (tm.get(list.get(i).getDate())+1));
			}
			List<String> tempList = null;
			for(int i=0;i<dateList.size();i++){
				tempList = new ArrayList<String>();
				tempList.add(dateList.get(i));
				tempList.add(tm.get(dateList.get(i))+"");
				totalList.add(tempList);
			}
			
			entity = new ResponseEntity<List<List<String>>>(totalList, HttpStatus.OK);
		}else if(type.equals("dayofweek")){
			List<String> dowList = new ArrayList<String>();
			dowList.add("월");
			dowList.add("화");
			dowList.add("수");
			dowList.add("목");
			dowList.add("금");
			dowList.add("토");
			dowList.add("일");
			
			TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
			tm.put("월", 0);tm.put("화", 0);tm.put("수", 0);tm.put("목", 0);tm.put("금", 0);tm.put("토", 0);tm.put("일", 0);
			for(int i=0;i<list.size();i++){
				tm.put(list.get(i).getDayofweek(), (tm.get(list.get(i).getDayofweek())+1));
			}
			
			List<String> tempList = null;
			for(int i=0;i<dowList.size();i++){
				tempList = new ArrayList<String>();
				tempList.add(dowList.get(i));
				tempList.add(tm.get(dowList.get(i))+"");
				totalList.add(tempList);
			}
			
			entity = new ResponseEntity<List<List<String>>>(totalList, HttpStatus.OK);
		}else if(type.equals("month")){
			List<String> monthList = new ArrayList<String>();
			String[] sd = s_date.split("-");
			String[] ed = e_date.split("-");
			int sy=Integer.parseInt(sd[0]);
			int sm=Integer.parseInt(sd[1]);
			int ey=Integer.parseInt(ed[0]);
			int em=Integer.parseInt(ed[1]);
			
			int period = (ey-sy)*12+(em-sm);
			
			if(period == 0){
				monthList.add(sd[0]+"-"+sd[1]);
			}else{
				monthList.add(sd[0]+"-"+sd[1]);
				for(int i=0;i<period;i++){
					if(sm ==12){
						sy++;
						sm=1;
					}else{
						sm++;
					}
					if(sm<10){
						monthList.add(sy+"-"+"0"+sm);
					}else{
						monthList.add(sy+"-"+sm);
					}
				}
			}
			
			TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
			
			for(int i=0; i<monthList.size(); i++){
				tm.put(monthList.get(i), 0);
			}
			String[] ym_split_arr = null;
			String ym = "";
			for(int i=0; i<list.size(); i++){
				ym_split_arr = list.get(i).getDate().split("-");
				ym = ym_split_arr[0]+"-"+ym_split_arr[1];
				tm.put(ym, (tm.get(ym)+1));
			}
			
			List<String> tempList = null;
			for(int i=0;i<monthList.size();i++){
				tempList = new ArrayList<String>();
				tempList.add(monthList.get(i));
				tempList.add(tm.get(monthList.get(i))+"");
				totalList.add(tempList);
			}
			
			entity = new ResponseEntity<List<List<String>>>(totalList, HttpStatus.OK);
			
		}else if(type.equals("year")){
			String[] s_date_txt_arr = s_date.split("-");
			String[] e_date_txt_arr = e_date.split("-");
			int sy = Integer.parseInt(s_date_txt_arr[0]);
			int ey = Integer.parseInt(e_date_txt_arr[0]);
			int resVal = ey-sy;
			List<String> yearList = new ArrayList<String>();

			for(int i=0; i<(resVal+1); i++){
				yearList.add((sy+i)+"");
			}
			TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
			
			for(int i=0; i<yearList.size(); i++){
				tm.put(yearList.get(i), 0);
			}
			
			String[] ym_split_arr = null;
			String y = "";
			for(int i=0; i<list.size(); i++){
				ym_split_arr = list.get(i).getDate().split("-");
				y = ym_split_arr[0];
				tm.put(y, (tm.get(y)+1));
			}
			
			List<String> tempList = null;
			for(int i=0;i<yearList.size();i++){
				tempList = new ArrayList<String>();
				tempList.add(yearList.get(i));
				tempList.add(tm.get(yearList.get(i))+"");
				totalList.add(tempList);
			}
			
			entity = new ResponseEntity<List<List<String>>>(totalList, HttpStatus.OK);
		}else if(type.equals("browser")){
			List<String> browserList = new ArrayList<String>();
			browserList.add("Internet Explorer7");
			browserList.add("Internet Explorer8");
			browserList.add("Internet Explorer9");
			browserList.add("Internet Explorer10");
			browserList.add("Internet Explorer11");
			browserList.add("Chrome");
			browserList.add("Safari");
			browserList.add("Opera");
			browserList.add("Mac");
			browserList.add("Safari3");
			browserList.add("Firefox");
			browserList.add("Unknown");
			
			TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
			
			for(int i=0; i<browserList.size(); i++){
				tm.put(browserList.get(i), 0);
			}

			for(int i=0; i<list.size(); i++){

				tm.put(list.get(i).getBrowser(), (tm.get(list.get(i).getBrowser())+1));
			}
			
			List<String> tempList = null;
			for(int i=0;i<browserList.size();i++){
				tempList = new ArrayList<String>();
				tempList.add(browserList.get(i));
				tempList.add(tm.get(browserList.get(i))+"");
				totalList.add(tempList);
			}
			
			entity = new ResponseEntity<List<List<String>>>(totalList, HttpStatus.OK);
		}else if(type.equals("os")){
			List<String> osList = new ArrayList<String>();

			osList.add("Windows 7");
			osList.add("Windows 8");
			osList.add("Windows Vista");
			osList.add("Windows XP");
			osList.add("Windows 10");
			osList.add("Linux");
			osList.add("Mac");
			osList.add("Unknown");
			
			TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
			
			for(int i=0; i<osList.size(); i++){
				tm.put(osList.get(i), 0);
			}

			for(int i=0; i<list.size(); i++){

				tm.put(list.get(i).getOs(), (tm.get(list.get(i).getOs())+1));
			}
			
			List<String> tempList = null;
			for(int i=0;i<osList.size();i++){
				tempList = new ArrayList<String>();
				tempList.add(osList.get(i));
				tempList.add(tm.get(osList.get(i))+"");
				totalList.add(tempList);
			}
			
			entity = new ResponseEntity<List<List<String>>>(totalList, HttpStatus.OK);
		}
		
		return entity;
	}

	@RequestMapping(value = "/menu03_02", method = RequestMethod.GET)
	public String menu03_02(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu03_02 GET");
		
		List<StatisticVO> list = sService.listSearch(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(sService.listSearchCount(cri));
		pageMaker.setFinalPage(sService.listSearchCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/menu03_02";
	}
	
	//================메뉴4(이벤트)============================
	
	@RequestMapping(value = "/menu04_01", method = RequestMethod.GET)
	public String menu04_01(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu04_01 GET");
		
		List<EventVO> list = eService.listSearch(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(eService.listSearchCount(cri));
		pageMaker.setFinalPage(eService.listSearchCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		return "admin/menu04_01";
	}
	
	@RequestMapping(value = "/menu04_01register", method = RequestMethod.GET)
	public String menu04_01register(Model model) {
		logger.info("menu04_01register GET");
		
		return "admin/menu04_01register";
	}
	
	@RequestMapping(value = "/menu04_01register", method = RequestMethod.POST)
	public String menu04_01registerPost(MultipartHttpServletRequest mtfReq, Model model) throws IOException {
		logger.info("menu04_01register POST");
		
		EventVO vo = new EventVO();
		
		vo.setNo(0);
		vo.setRegdate(mtfReq.getParameter("regdate"));
		vo.setTitle(mtfReq.getParameter("title"));
		vo.setStart_date(mtfReq.getParameter("start_date"));
		vo.setEnd_date(mtfReq.getParameter("end_date"));
		vo.setPrice(Integer.parseInt(mtfReq.getParameter("price")));
		vo.setContent(mtfReq.getParameter("content"));
		
		//이미지 업로드
		String innerUploadPath = "resources/uploadEvent/";
		String path = (mtfReq.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		String fileName = "";
		String storedFileName = "";
		
		Iterator<String> files = mtfReq.getFileNames();
		mtfReq.getFileNames();
		while(files.hasNext()){
			String uploadFile = files.next();
			
			MultipartFile mFile = mtfReq.getFile(uploadFile);
			fileName = mFile.getOriginalFilename();
			if(fileName.length() == 0){
				storedFileName = "";
			}else{
				storedFileName = System.currentTimeMillis()+"_"+fileName;
			}
			
			vo.setThumb_origin(fileName);
			vo.setThumb_stored(storedFileName);
			
			try {
				mFile.transferTo(new File(path+storedFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//이미지 업로드 끝
		System.out.println(vo);
		eService.insert(vo);
		return "redirect:/admin/menu04_01";
	}
	
	@RequestMapping(value = "/menu04_01update", method = RequestMethod.GET)
	public String menu04_01update(int no, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu04_01update GET");
		
		EventVO vo = eService.selectOne(no);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(eService.listSearchCount(cri));

		model.addAttribute("item", vo);
		model.addAttribute("pageMaker", pageMaker);
		return "admin/menu04_01update";
	}
	
	@RequestMapping(value = "/menu04_01update", method = RequestMethod.POST)
	public String menu04_01updatePOST(MultipartHttpServletRequest mtfReq, int page, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rtts) throws Exception {
		logger.info("menu04_01update POST");
		
		//이미지 업로드
		String innerUploadPath = "resources/uploadEvent/";
		String path = (mtfReq.getSession().getServletContext().getRealPath("/")) + innerUploadPath;
		String fileName = "";
		String storedFileName = "";
		
		Iterator<String> files = mtfReq.getFileNames();
		mtfReq.getFileNames();
		while(files.hasNext()){
			String uploadFile = files.next();
			
			MultipartFile mFile = mtfReq.getFile(uploadFile);
			fileName = mFile.getOriginalFilename();
			if(fileName.length() == 0){
				storedFileName = "";
			}else{
				storedFileName = System.currentTimeMillis()+"_"+fileName;
			}
			
			try {
				mFile.transferTo(new File(path+storedFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//이미지 업로드 끝
		
		String thumbState = mtfReq.getParameter("thumbState");
		
		
		EventVO vo = new EventVO();
		EventVO prevVO = eService.selectOne(Integer.parseInt(mtfReq.getParameter("no")));
		
		vo.setNo(Integer.parseInt(mtfReq.getParameter("no")));
		vo.setTitle(mtfReq.getParameter("title"));
		vo.setStart_date(mtfReq.getParameter("start_date"));
		vo.setEnd_date(mtfReq.getParameter("end_date"));
		vo.setPrice(Integer.parseInt(mtfReq.getParameter("price")));
		vo.setContent(mtfReq.getParameter("content"));
		
		if(thumbState.equals("o")){
			vo.setThumb_origin(fileName);
			vo.setThumb_stored(storedFileName);
		}else{
			vo.setThumb_origin(prevVO.getThumb_origin());
			vo.setThumb_stored(prevVO.getThumb_stored());
		}
		
		eService.update(vo);
		
		rtts.addAttribute("no", mtfReq.getParameter("no"));

		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.makeSearch(page);
		pageMaker.setTotalCount(eService.listSearchCount(cri));

		rtts.addAttribute("page", page);
		return "redirect:/admin/menu04_01update";
	}
	
	@RequestMapping(value="/menu04_01delete/{no}", method=RequestMethod.GET)
	public String menu04_01delete(@PathVariable("no") int no){
		logger.info("event delete");
		
		eService.delete(no);
		
		return "redirect:/admin/menu04_01";
	}
	
	//================메뉴5(예약관리)=============================
	
	@RequestMapping(value = "/menu05_01", method = RequestMethod.GET)
	public String menu05_01(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu05_01 GET");
		
		List<ReservationVO> list = resService.listSearch(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(resService.listSearchCount(cri));
		pageMaker.setFinalPage(resService.listSearchCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		return "admin/menu05_01";
	}
	
	@RequestMapping(value = "/menu05_01update", method = RequestMethod.GET)
	public String menu05_01update(int no, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest req) throws Exception {
		logger.info("menu05_01update GET");
		
		ReservationVO vo = resService.selectOne(no);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(resService.listSearchCount(cri));

		model.addAttribute("item", vo);
		model.addAttribute("pageMaker", pageMaker);
		return "admin/menu05_01update";
	}
	
	@RequestMapping(value = "/menu05_01update", method = RequestMethod.POST)
	public String menu05_01updatePOST(MultipartHttpServletRequest mtfReq, int page, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rtts) throws Exception {
		logger.info("menu05_01update POST");
		
		ReservationVO vo = new ReservationVO();
		
		vo.setNo(Integer.parseInt(mtfReq.getParameter("no")));
		vo.setName(mtfReq.getParameter("name"));
		vo.setPhone(mtfReq.getParameter("phone"));
		vo.setEmail(mtfReq.getParameter("email"));
		vo.setMemo(mtfReq.getParameter("memo"));
		vo.setRes_state(mtfReq.getParameter("res_state"));
		
		resService.update(vo);
		
		rtts.addAttribute("no", mtfReq.getParameter("no"));

		PageMaker pageMaker = new PageMaker();

		pageMaker.setCri(cri);
		pageMaker.makeSearch(page);
		pageMaker.setTotalCount(resService.listSearchCount(cri));

		rtts.addAttribute("page", page);
		return "redirect:/admin/menu05_01update";
	}
	
	@RequestMapping(value="/menu05_01delete/{no}", method=RequestMethod.GET)
	public String menu05_01delete(@PathVariable("no") int no){
		logger.info("clinicResList delete");
		
		resService.delete(no);
		
		return "redirect:/admin/menu05_01";
	}
	
	@RequestMapping(value = "/menu05_02", method = RequestMethod.GET)
	public String menu05_02(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("menu05_02 GET");
		
		List<ClinicVO> list = cService.listSearch(cri);
		List<Category1VO> c1List = c1Service.selectAll();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.makeSearch(cri.getPage());
		pageMaker.setTotalCount(cService.listSearchCount(cri));
		pageMaker.setFinalPage(cService.listSearchCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("category1List", c1List);
		model.addAttribute("pageMaker", pageMaker);
		return "admin/menu05_02";
	}
	
	@RequestMapping(value = "/menu05_category_register", method = RequestMethod.POST)
	public String menu05_category_registerPost(MultipartHttpServletRequest mtfReq, Model model) throws IOException {
		logger.info("menu05_category_register POST");
		
		Category1VO vo = new Category1VO();
		
		vo.setNo(0);
		vo.setName(mtfReq.getParameter("categorynm"));
		
		c1Service.insert(vo);
		return "redirect:/admin/menu05_02";
	}
	
	@RequestMapping(value = "/menu05_category_update", method = RequestMethod.POST)
	public String menu05_category_updatePost(MultipartHttpServletRequest mtfReq, Model model) throws IOException {
		logger.info("menu05_category_update POST");
		
		Category1VO vo = new Category1VO();
		
		vo.setNo(Integer.parseInt(mtfReq.getParameter("no")));
		vo.setName(mtfReq.getParameter("name"));
		
		c1Service.update(vo);
		return "redirect:/admin/menu05_02";
	}
	
	@RequestMapping(value = "/menu05_category_delete/{no}", method = RequestMethod.GET)
	public String menu05_category_deletePost(@PathVariable("no") int no, Model model) throws IOException {
		logger.info("menu05_category_delete POST");
				
		c1Service.delete(no);
		return "redirect:/admin/menu05_02";
	}
	
	@RequestMapping(value = "/menu05_clinic_register", method = RequestMethod.POST)
	public String menu05_clinic_registerPost(MultipartHttpServletRequest mtfReq, Model model) throws IOException {
		logger.info("menu05_clinic_register POST");
		
		ClinicVO vo = new ClinicVO();
		
		vo.setNo(0);
		vo.setKind1(Integer.parseInt(mtfReq.getParameter("kind1")));
		vo.setKind1nm(mtfReq.getParameter("kind1nm"));
		vo.setKind2nm(mtfReq.getParameter("kind2nm"));
		vo.setName(mtfReq.getParameter("name"));
		vo.setCode(mtfReq.getParameter("code"));
		vo.setPrice_before(Integer.parseInt(mtfReq.getParameter("price_before")));
		vo.setPrice_after(Integer.parseInt(mtfReq.getParameter("price_after")));
		vo.setDiscount(mtfReq.getParameter("discount"));
		vo.setPopular(mtfReq.getParameter("popular"));
		
		cService.insert(vo);
		return "redirect:/admin/menu05_02";
	}
	
	@RequestMapping(value = "/menu05_clinic_update", method = RequestMethod.POST)
	public String menu05_clinic_updatePOST(MultipartHttpServletRequest mtfReq, RedirectAttributes rtts) throws Exception {
		logger.info("menu05_clinic_update POST");
		
		ClinicVO vo = new ClinicVO();
		
		vo.setNo(Integer.parseInt(mtfReq.getParameter("no")));
		vo.setKind1(Integer.parseInt(mtfReq.getParameter("kind1")));
		vo.setKind1nm(mtfReq.getParameter("kind1nm"));
		vo.setKind2nm(mtfReq.getParameter("kind2nm"));
		vo.setName(mtfReq.getParameter("name"));
		vo.setCode(mtfReq.getParameter("code"));
		vo.setPrice_before(Integer.parseInt(mtfReq.getParameter("price_before")));
		vo.setPrice_after(Integer.parseInt(mtfReq.getParameter("price_after")));
		vo.setDiscount(mtfReq.getParameter("discount"));
		vo.setPopular(mtfReq.getParameter("popular"));
		
		cService.update(vo);
		
		return "redirect:/admin/menu05_02";
	}
	
	@RequestMapping(value = "/menu05_clinic_delete/{no}", method = RequestMethod.GET)
	public String menu05_clinic_deletePost(@PathVariable("no") int no, Model model) throws IOException {
		logger.info("menu05_clinic_delete POST");
		
		cService.delete(no);
		return "redirect:/admin/menu05_02";
	}
	
	@RequestMapping(value = "/menu05_03", method = RequestMethod.GET)
	public String menu05_03(Model model) throws Exception {
		logger.info("menu05_03");
		
		List<HospitalTimeVO> list = htService.selectAll();
		
		int mon_start = list.get(0).getStart_time();
		int mon_end = list.get(0).getEnd_time();
		int tue_start = list.get(1).getStart_time();
		int tue_end = list.get(1).getEnd_time();
		int wed_start = list.get(2).getStart_time();
		int wed_end = list.get(2).getEnd_time();
		int thu_start = list.get(3).getStart_time();
		int thu_end = list.get(3).getEnd_time();
		int fri_start = list.get(4).getStart_time();
		int fri_end = list.get(4).getEnd_time();
		int sat_start = list.get(5).getStart_time();
		int sat_end = list.get(5).getEnd_time();
		
		model.addAttribute("mon_s", mon_start);
		model.addAttribute("mon_e", mon_end);
		model.addAttribute("tue_s", tue_start);
		model.addAttribute("tue_e", tue_end);
		model.addAttribute("wed_s", wed_start);
		model.addAttribute("wed_e", wed_end);
		model.addAttribute("thu_s", thu_start);
		model.addAttribute("thu_e", thu_end);
		model.addAttribute("fri_s", fri_start);
		model.addAttribute("fri_e", fri_end);
		model.addAttribute("sat_s", sat_start);
		model.addAttribute("sat_e", sat_end);
		return "admin/menu05_03";
	}
	
	@RequestMapping(value = "/menu05_03update", method = RequestMethod.POST)
	public String menu05_03updatePOST(MultipartHttpServletRequest mtfReq, RedirectAttributes rtts) throws Exception {
		logger.info("menu05_03update POST");
		
		int mon_start = (Integer.parseInt(mtfReq.getParameter("mon_start_hour"))*60)+Integer.parseInt(mtfReq.getParameter("mon_start_minute"));
		int mon_end = (Integer.parseInt(mtfReq.getParameter("mon_end_hour"))*60)+Integer.parseInt(mtfReq.getParameter("mon_end_minute"));
		int tue_start = (Integer.parseInt(mtfReq.getParameter("tue_start_hour"))*60)+Integer.parseInt(mtfReq.getParameter("tue_start_minute"));
		int tue_end = (Integer.parseInt(mtfReq.getParameter("tue_end_hour"))*60)+Integer.parseInt(mtfReq.getParameter("tue_end_minute"));
		int wed_start = (Integer.parseInt(mtfReq.getParameter("wed_start_hour"))*60)+Integer.parseInt(mtfReq.getParameter("wed_start_minute"));
		int wed_end = (Integer.parseInt(mtfReq.getParameter("wed_end_hour"))*60)+Integer.parseInt(mtfReq.getParameter("wed_end_minute"));
		int thu_start = (Integer.parseInt(mtfReq.getParameter("thu_start_hour"))*60)+Integer.parseInt(mtfReq.getParameter("thu_start_minute"));
		int thu_end = (Integer.parseInt(mtfReq.getParameter("thu_end_hour"))*60)+Integer.parseInt(mtfReq.getParameter("thu_end_minute"));
		int fri_start =( Integer.parseInt(mtfReq.getParameter("fri_start_hour"))*60)+Integer.parseInt(mtfReq.getParameter("fri_start_minute"));
		int fri_end = (Integer.parseInt(mtfReq.getParameter("fri_end_hour"))*60)+Integer.parseInt(mtfReq.getParameter("fri_end_minute"));
		int sat_start = (Integer.parseInt(mtfReq.getParameter("sat_start_hour"))*60)+Integer.parseInt(mtfReq.getParameter("sat_start_minute"));
		int sat_end = (Integer.parseInt(mtfReq.getParameter("sat_end_hour"))*60)+Integer.parseInt(mtfReq.getParameter("sat_end_minute"));
		
		List<Integer> timeList = new ArrayList<Integer>();
		timeList.add(mon_start);
		timeList.add(mon_end);
		timeList.add(tue_start);
		timeList.add(tue_end);
		timeList.add(wed_start);
		timeList.add(wed_end);
		timeList.add(thu_start);
		timeList.add(thu_end);
		timeList.add(fri_start);
		timeList.add(fri_end);
		timeList.add(sat_start);
		timeList.add(sat_end);
		
		int k= 0;
		
		for(int i=1; i<7; i++){
			HospitalTimeVO vo = new HospitalTimeVO();
			vo.setNo(i);
			vo.setStart_time(timeList.get(k));
			k++;
			vo.setEnd_time(timeList.get(k));
			k++;
			htService.update(vo);
			System.out.println(vo);
		}
		
		return "redirect:/admin/menu05_03";
	}
}
