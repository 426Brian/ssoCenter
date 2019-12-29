package com.sso.controller;

import com.sso.bean.ClientInfo;
import com.sso.bean.Mockdb;
import com.sso.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Brian in 0:33 2018/12/12
 */
@Controller
public class SSOController {

    @RequestMapping("/checkLogin")
    public String checkLogin(String clientUrl, HttpSession session, HttpServletResponse response, Model model) {

        String token = (String) session.getAttribute("token");
        if (!StringUtils.isAllEmpty(token)) {
            // 其他系统登陆过
            try {
                response.sendRedirect(clientUrl + "?token=" + token);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        } else {
            model.addAttribute("clientUrl", clientUrl);
            return "sso/login";
        }
    }

    @RequestMapping("/login")
    public String login(String clientUrl, Model model) {
        model.addAttribute("clientUrl", clientUrl);
        return "sso/login";

    }

    @RequestMapping("/islogin")
    public String isLogin(User user, HttpServletResponse response, Model model, HttpServletRequest request) {

        String clientUrl = request.getParameter("clientUrl");
        if (user != null) {
            if ("123".equals(user.getEmpno()) && "456".equals(user.getPassword())) {
                // 账号有效, 生成token
                String token = UUID.randomUUID().toString();
                // 保存token
                Mockdb.TOKEN_SET.add(token);

                // token 放在session 中
                request.getSession().setAttribute("token", token);

                try {
                    response.sendRedirect(clientUrl + "?token=" + token);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return "sso/success";
            }

        }

        model.addAttribute("clientUrl", clientUrl);
        return "sso/login";
    }

    @RequestMapping("/verify")
    @ResponseBody
    public String verify(String token, String clientUrl, String jsessionid) {

        if (!StringUtils.isAllEmpty(token)) {
            if (Mockdb.TOKEN_SET.contains(token)) {
                // token 有效
                ArrayList<ClientInfo> clientList = Mockdb.CLIENT_MAP.get(token);
                if(clientList == null){
                    clientList = new ArrayList<>();
                    Mockdb.CLIENT_MAP.put(token, clientList);
                }

                ClientInfo clientInfo = new ClientInfo();
                clientInfo.setClientUrl(clientUrl);
                clientInfo.setJsessionid(jsessionid);
                clientList.add(clientInfo);

                return "true";
            }
        }
        return "false";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "sso/logout";
    }
}
