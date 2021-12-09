package com.cos.security1.config.oauth;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.auth.provider.FacebookUserInfo;
import com.cos.security1.config.auth.provider.GoogleUserInfo;
import com.cos.security1.config.auth.provider.NaverUserInfo;
import com.cos.security1.config.auth.provider.OAuth2UserInfo;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

// 구글로그인버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(Oath-Client라이브러리) -> AccessToken 요청
// userRequest정보 -> loadUser 함수 호출 -> 회원프로필
@Service
public class PrincipalOath2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    // 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //System.out.println("userRequest: "+ userRequest.getClientRegistration());  //어떤 Oath로 로그인 했는지 알수 있다.
        //System.out.println("getAccessToken: "+ userRequest.getAccessToken());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //강제로 회원가입 진행해볼 예정
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        }
        //System.out.println("getAttributes: "+ super.loadUser(userRequest).getAttributes());

        String provider = oAuth2UserInfo.getProcider(); //google
        String providerId = oAuth2UserInfo.getProciderId();
        String username = provider+"_"+providerId; //google_0120103012301023123
        String password = bCryptPasswordEncoder.encode("구글가글"); //google_0120103012301023123
        String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);
        if(userEntity==null){
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }else {
            System.out.println("로그인 한 적이 있습니다. 이미 아이디가 존재합니다.");
        }
        // 리턴 될 때(함수가 끝날 때) 자동으로 시큐리티 Session(내부 Authentication(내부 UserDetails)로 들어간다.
        // @AuthenticationPrincipal 어노테이션이 만들어진다.
        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
    }

}
