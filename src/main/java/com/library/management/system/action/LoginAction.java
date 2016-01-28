/**
 * 
 */
package com.library.management.system.action;



import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.library.management.system.dao.BookDao;
import com.library.management.system.model.Book;
import com.library.management.system.service.StudentService;


@Controller
public class LoginAction {

	private final Logger logger = LoggerFactory.getLogger(LoginAction.class);

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BookDao bookDao;
	
	/**
	 * Welcome Page for students
	 * @return
	 */
	@RequestMapping(value = { "/", "/home**" }, method = RequestMethod.GET)
	public ModelAndView studentDefaultPage() {
		
		ModelAndView model = new ModelAndView();
		try {
			String studentName = studentService.getStudentDetials().getStudName();
			model.addObject("name", studentName);
			model.setViewName("home");
		} catch (AuthenticationException e) {
			logger.error(e.toString());
			model.setViewName("redirect:/login?error");
		}
		
		return model;

	}
	
	/**
	 * redirect to the search page
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchPage() {
		
		ModelAndView model = new ModelAndView();
		
		model.setViewName("search");
		
		return model;

	}
	
	/**
	 * 
	 * @param name the name of the book to search
	 * @return the books requests in form of a Json object
	 */
	@RequestMapping(value = "/search/book", method = RequestMethod.GET)
	public @ResponseBody String searchBook(@RequestParam(value = "name", required = false) String name) {
		List<Book> books;
		if(name == null || name.equals("")) {
			books = bookDao.findAll();
		} else {
			books = bookDao.findByName(name);
		}
		return new Gson().toJson(books);
	}
	
	/**
	 * both "normal login" and "login for update" shared this form.
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		
		if(SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities().iterator().next().getAuthority().equals("ROLE_USER")) {
			model.setViewName("redirect:/home");
			return model;
		}
		
		if (error != null) {
			logger.debug("Invalid email and/or password!");
			model.addObject("error", "Invalid email and/or password!");

			//login form for update, if login error, get the targetUrl from session again.
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			System.out.println(targetUrl);
			if(StringUtils.hasText(targetUrl)){
				model.addObject("targetUrl", targetUrl);
				model.addObject("loginUpdate", true);
			}

		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		model.setViewName("login");

		return model;

	}
	/**
	 * This update page is for user login with password only.
	 * If user is logjn via remember me cookie, send login to ask for password again.
	 * To avoid stolen remember me cookie to update anything
	 */
	@RequestMapping(value = "/admin/update**", method = RequestMethod.GET)
	public ModelAndView updatePage(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();

		if (isRememberMeAuthenticated()) {
			//send login for update
			setRememberMeTargetUrlToSession(request);
			model.addObject("loginUpdate", true);
			model.setViewName("/login");
			
		} else {
			model.setViewName("update");
		}

		return model;

	}

	/**
	 * save targetURL in session
	 */
	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/admin/update");
		}
	}

	/**
	 * get targetURL from session
	 */
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?"":session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}
	
	/**
	 * If the login in from remember me cookie, refer
	 * org.springframework.security.authentication.AuthenticationTrustResolverImpl
	 */
	private boolean isRememberMeAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

}

