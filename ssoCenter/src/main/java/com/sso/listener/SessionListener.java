package com.sso.listener;

import com.sso.bean.ClientInfo;
import com.sso.bean.Mockdb;
import com.sso.util.HttpUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Brian in 1:16 2018/12/12
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String id = session.getId();
        String token = (String) session.getAttribute("token");

        ArrayList<ClientInfo> clientInfos = Mockdb.CLIENT_MAP.remove(token);
        if (clientInfos != null && clientInfos.size() >0){
            for (ClientInfo clientInfo : clientInfos){
                String jsessionid = clientInfo.getJsessionid();
                String clientUrl = clientInfo.getClientUrl();

                HashMap<String, String> paramMap = new HashMap<>();
                paramMap.put("jsessionid", jsessionid);

                String resp = null;
                try {
                    resp = HttpUtil.sendRequest(clientUrl+"/tologout", paramMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
