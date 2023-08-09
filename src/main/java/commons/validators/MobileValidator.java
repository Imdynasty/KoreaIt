package commons.validators;

public interface MobileValidator {
    default boolean numberCheck(String number){
        /**
         * 1. 010, 011, 016 / 000~0000 / 0000
         * 2. 010-0000-0000, 010.0000.0000, 01000000000 -> 체크의 통일성을 위해 숫자만 남기고 전부 제거
         * 3. 검증 패턴
         * \d -> [0~9] :  숫자 1개
         * \D -> [^0~9] : 숫자가 아닌 문자 1개
         * {시작개수, 종료개수}
         * {갯수}
         * {시작개수}
         * ^패턴 ㅣ : 패턴으로 시작
         * 패턴$ : 패턴으로 끝
         *
         */
        number = number.replaceAll("\\D","");//숫자 아닌 문자 모두 제거 -> 숫자
        String pattern = "^01[016]\\d{3,4}\\d{4}$";
        return number.matches(pattern);
    }
}
