package com.cos.security1.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOath2UserService extends DefaultOAuth2UserService {

    // 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest: "+ userRequest.getClientRegistration());  //어떤 Oath로 로그인 했는지 알수 있다.
        System.out.println("getAccessToken: "+ userRequest.getAccessToken());
        // 구글로그인버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(Oath-Client라이브러리) -> AccessToken 요청
        // userRequest정보 -> loadUser 함수 호출 -> 회원프로필
        System.out.println("getAttributes: "+ super.loadUser(userRequest).getAttributes());
        //강제로 회원가입 진행해볼 예정
        OAuth2User oAuth2User =super.loadUser(userRequest);

        return super.loadUser(userRequest);
    }

}
