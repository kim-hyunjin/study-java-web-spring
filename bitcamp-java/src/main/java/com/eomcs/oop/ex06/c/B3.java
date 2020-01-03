package com.eomcs.oop.ex06.c;

public class B3 extends B {
    // 오버라이딩 문법 검사
    // => 오버라이딩을 제대로 했는지 컴파일러에게 검사하도록 요청할 수 있다.
    // => 어떻게? 
    //    메서드 정의 앞에 @Override를 붙여라
    @Override // <= 컴파일러야, 내가 상속받은 메서드를 재정의한다고 했는데,
              //    혹시 실수는 없는지 검사해 줄래?
    void m(int x) {
        // 오버라이딩을 한다고 하면서 파라미터의 타입이나 개수, 순서를 달리해서
        // 오버로딩이 되는 경우가 있기 때문에 
        // 이를 방지하기 위해 오버라이딩을 하는 메서드 앞에 
        // @Override 애노테이션를 붙임으로써 잘못 사용하는 경우를 방지하라!
        System.out.println("B3의 m()");
    }
}
