package controllers.member;

import commons.validators.MobileValidator;
import lombok.RequiredArgsConstructor;
import models.members.MemberDao;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator , MobileValidator {

    private final MemberDao memberDao;

    @Override
    public boolean supports(Class<?> clazz) { // 검증하고자 하는 커맨드 객체 제한
        return clazz.isAssignableFrom(JoinForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) { // 유효성 검사 진행
        JoinForm joinForm = (JoinForm)target;
        /**
         * 1. 아이디 중복 여부 검사
         * 2. 비밀번호, 비밀번호 확인의 일치 여부
         * 3. 휴대전화 번호 형식 체크
         *
         */
        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();
        String mobile = joinForm.getMobile();

        //1. 아이디 중복 여부 검사
        if(memberDao.exists(userId)){
            errors.rejectValue("userId","Duplicate");
        }
        //2. 비밀번호, 비밀번호 확인의 일치 여부
        if(!userPw.equals(userPwRe)){
            errors.rejectValue("userPwRe", "Incorrect.password");
        }
        //3. 휴대전화 번호 형식 체크
        if(mobile != null && !mobile.isBlank()){
            boolean result =numberCheck(mobile);
            if(!result){
                errors.rejectValue("mobile","Incorrect.mobile.pattern");
            }

            mobile = mobile.replaceAll("\\D", "");
            joinForm.setMobile(mobile);

        }




        /** 필수 항목 검증 */
        /*
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userId","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userPw","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userPwRe","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userNm","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","Required");*/



       /** 약관 동의 필수 여부*/
       /*if(!joinForm.isAgree()){
           errors.rejectValue("agree","Required");
       }*/
    }
}