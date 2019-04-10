package io.github.SD810;

public class Main {

    public static void main(String[] args) {
        //금구백억이천오백이만일천오백원정
        System.out.println(KoreanWon.readCashes(90025021500L,false));
        //금 구백억 이천오백이만 일천오백원 정
        System.out.println(KoreanWon.readCashes(90025021500L,true));
    }
}
