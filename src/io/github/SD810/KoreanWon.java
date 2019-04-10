package io.github.SD810;

public class KoreanWon {
    private static final char[] NUMBERS_IN_KOREAN = {'영','일','이','삼','사','오','육','칠','팔','구'};
    private static final char[] TENS_IN_KOREAN = {'영','일','십','백','천','만','십','백','천','억','십','백','천','조','십','백','천'};


    /**
     *
     * @param amount
     * @return
     */
    public static String readCashes(long amount, boolean space){
        // 결과를 저장할 곳
        StringBuffer result = new StringBuffer();
        result.append('금');
        if(space) {
            result.append(' ');
        }

        // 양수로 변환
        amount = Math.abs(amount);

        // 수를 문자열로 단순변환
        String amountStr = String.valueOf(amount);

        if(amountStr.length() > (TENS_IN_KOREAN.length - 1)){
            throw new NumberFormatException("처리할 수 없는 수("+amountStr+")입니다. ("+amountStr.length()+"자리수인데 "+(TENS_IN_KOREAN.length - 1)+"보다 큽니다.) ");
        }

        for(int i = 0; i < amountStr.length(); i++){
            // 수
            final int num = getNumber(amountStr.charAt(i));

            // 수가 0 보다 크면 수를 더합니다.
            if(num > 0) {
                result.append(NUMBERS_IN_KOREAN[num]);
            }

            // 자릿수
            final int tens = amountStr.length() - i;

            // 동북아시아의 수는 4자리 단위로 끊으므로 천백십일'만' 천백십일 순으로 됩니다.
            // 일의자리는 표시하지 않습니다.
            if((tens - 1) % 4 == 0 && tens > 1){
                // 만 억 조 까지 시행합니다.
                result.append(TENS_IN_KOREAN[tens]);

                //만 단위로 띄우기를 할 경우에만
                if(space) {
                    result.append(' ');
                }
            }else{
                if(num != 0 && tens > 1) {
                    result.append(TENS_IN_KOREAN[tens]);
                }
            }
        }

        result.append('원');
        if(space) {
            result.append(' ');
        }
        result.append('정');
        return result.toString();
    }

    private static int getNumber(char asciiNum){
        int result = asciiNum - '0';
        result %= 10;
        return result;
    }
}
