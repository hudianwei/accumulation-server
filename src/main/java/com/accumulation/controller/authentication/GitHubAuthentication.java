package com.accumulation.controller.authentication;

import com.accumulation.controller.login.LoginFailureExcepiton;
import com.accumulation.dao.UserDao;
import com.accumulation.model.UserEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/19 17:19
 */
@Service
public class GitHubAuthentication implements MyAuthentication {
    @Autowired
    private UserDao userDao;
    private RestTemplate restTemplate = new RestTemplate();
    private static final String CLIENT_ID = "d943253e7963c70e6fb4";
    private static final String CLIENT_SECRET = "fa6a292dc5053e3a7c56308722e143bd32cacbcf";
    private static final String GITHUB_ACCESSS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_URL = "https://api.github.com/user";

    @Override
    @Transactional
    public String getUserId(String code) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("client_id", CLIENT_ID);
        requestEntity.add("client_secert", CLIENT_SECRET);
        requestEntity.add("code", code);
        //post方式访问有问题使用get方式https://github.com/login/oauth/access_token?client_id=d943253e7963c70e6fb4&client_secret=fa6a292dc5053e3a7c56308722e143bd32cacbcf&code=7ac3a91f747639af7bbb
        // String result=restTemplate.postForObject(GITHUB_ACCESSS_TOKEN_URL,requestEntity,String.class);
        //ResponseEntity<String> responseEntity = restTemplate.postForEntity(GITHUB_ACCESSS_TOKEN_URL, requestEntity, String.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(GITHUB_ACCESSS_TOKEN_URL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + code + "", String.class);
        String message = responseEntity.getBody().trim();
        String access_token = message.split("&")[0].split("=")[1];
        if (access_token == null || "".equals(access_token)) {
            throw new LoginFailureExcepiton(message);
        }
        String url = GITHUB_USER_URL + "?access_token=" + access_token;
        responseEntity = restTemplate.getForEntity(url, String.class);
        UserEntity userEntity = null;
        try {
            JSONObject githubUserInfo = new JSONObject(responseEntity.getBody().trim());
            String loginName = githubUserInfo.getString("login");
            if (loginName == null) {
                throw new LoginFailureExcepiton(githubUserInfo.toString());
            }
            userEntity = userDao.getUserEntityByLoginName(loginName);
            if (userEntity == null) {
                return inserUser(githubUserInfo);
            } else {
                return userEntity.getLoginName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String inserUser(JSONObject githubUserInfo) throws JSONException {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(githubUserInfo.getString("email"));
        userEntity.setHeadimg(githubUserInfo.getString("avatar_url"));
        userEntity.setLoginName(githubUserInfo.getString("login"));
        userEntity.setUrl(githubUserInfo.getString("html_url"));
        userEntity.setUserType(0);
        userDao.insertUser(userEntity);
        return userEntity.getLoginName();
    }
}
