package com.littlepage.airplaneticketsystem.controller;

import com.littlepage.airplaneticketsystem.pojo.User;
import com.littlepage.airplaneticketsystem.service.AirflightService;
import com.littlepage.airplaneticketsystem.service.TodayTicketService;
import com.littlepage.airplaneticketsystem.service.UserService;
import com.littlepage.airplaneticketsystem.utils.MD5Utils;
import com.littlepage.airplaneticketsystem.utils.ValidateUtils;
import com.littlepage.airplaneticketsystem.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Login and Register Page
 */
@Controller
@RequestMapping("admin")
public class LoginAndRegister {

    /**
     * today ticket service
     *
     * from a database view
     */
    @Autowired
    TodayTicketService todayTicketService;

    /**
     * air flight service
     */
    @Autowired
    private AirflightService airflightService;

    /**
     * user service
     */
    @Autowired
    private UserService userService;

    /**
     * login page
     * @return page resource address
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    /**
     * register page
     * @return page resource address
     */
    @RequestMapping("register")
    public String register(){
        return "register";
    }

    /**
     * login validation
     * @param username user name
     * @param password password
     * @param session session
     * @param attributes attributes for redirect
     * @param model Model object
     * @return success or not success
     */
    @PostMapping("login/valiLogin")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes,
                        Model model) {
        if(username.equals("") || password.equals("")){
            attributes.addFlashAttribute("message", "用户名或密码不能为空");
            return "redirect:/admin/login";
        }
        password = MD5Utils.code(password);
        User user = userService.findUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setMaxInactiveInterval(30*24*60*60);
            session.setAttribute("user",user);
            Page page = new Page();
            page.setPageNumber(airflightService.countAirflightService()/10).
                    setPageSize(10).
                    setIndex(0);
            model.addAttribute("airflightcount",airflightService.countAirflightService());
            model.addAttribute("todayTicket",todayTicketService.getTodayTicket(page));
            model.addAttribute("page",page);
            return "/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin/login";
        }
    }

    /**
     * log out user
     *
     * remove login information
     * @param session session
     * @return the login page
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin/login";
    }

    /**
     * validate the register info
     * @param username user name
     * @param password password
     * @param passwordRepeat passwordRepeat
     * @param attributes attributes
     * @return valiRegister
     */
    @PostMapping("register/valiRegister")
    public String valiRegister(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String passwordRepeat,
                               RedirectAttributes attributes){
        if(username.equals("")||password.equals("")||passwordRepeat.equals("")){
            attributes.addFlashAttribute("registerMessage", "用户名或密码不能为空");
            return "redirect:/admin/register";
        }else if(!password.equals(passwordRepeat)){
            attributes.addFlashAttribute("registerMessage", "两次密码输入不一致");
            return "redirect:/admin/register";
        }else if(!ValidateUtils.valiUsername(username)){
            attributes.addFlashAttribute("registerMessage", "用户名不能包含特殊字符");
            return "redirect:/admin/register";
        }else if(!ValidateUtils.valiPassword(password)){
            attributes.addFlashAttribute("registerMessage", "密码必须由大小写字母和数字构成");
            return "redirect:/admin/register";
        }
        User user = userService.findUser(username);
        if(user != null) {
            attributes.addFlashAttribute("registerMessage", "该用户已经注册");
            return "redirect:/admin/register";
        }
        String uid = UUID.randomUUID().toString();
        userService.addUser(uid,username,MD5Utils.code(password));
        attributes.addFlashAttribute("message","注册成功");
        return "redirect:/admin/login";
    }


}
