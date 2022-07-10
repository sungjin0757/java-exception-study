## Item 75. 예외의 상세 메시지에 실패 관련 정보를 담으라
***

예외를 잡지 못해 프로그램이 실패하면 자바 시스템은 그 예외의 스택 추적 정보를 자동으로 출력합니다.

스택 추적은 예외 객체의 `toString` 메서드를 호출해 얻는 문자열로, 보통은 예외의 클래스 이름 뒤에
상세 메시지가 붙는 형태입니다.

이 정보가 실패 원인을 분석해야 하는 프로그래머 혹은 사이트 신뢰성 엔지니어가 얻을 수 있는 유일한 정보 입니다.

따라서, **분석을 위해 실패 순간의 상황을 정확히 포착해 예외의 상세 메시지에 담아야합니다**

<br></br>

**실패 순간을 포착하려면 발생한 예외에 관여된 모든 매개변수와 필드의 값을 실패 메시지에 담아야합니다.**
***
예를 들어, `IndexOutOfBoundsException` 의 상세 메시지는 범위의 최솟값과 최댓값, 그리고 그 범위를 벗어 났다는 인덱스의 값을 담아야 합니다.

또한, 예외 상세 메시지를 작성할 때는 주 소비층이 사이트 신뢰성 엔지니어이기 때문에 
가독성보다는 담긴 내용이 훨씬 중요하게 됩니다.

다음의 예를 봐봅시다.

```java
public class IndexOutOfBoundsException extends RuntimeException{
    private int lowerBound;
    private int upperBound;
    private int index;
    /**
     * IndexOutOfBoundsException을 생성한다.
     * @param lowerBound 인덱스의 최솟값
     * @param upperBound 인덱스의 최댓값 + 1
     * @param index 인덱스의 실젯값
     */
    public IndexOutOfBoundsException(int lowerBound, int upperBound,
                                     int index){
        // 실패를 포착하는 상세 메시지를 생성한다.
        super(String.format(
                "최솟값: %d, 최댓값: %d, 인덱스: %d",
                lowerBound, upperBound, index
        ));
        
        // 프로그램에서 이용할 수 있도록 실패 정보를 저장해둔다.
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }
}
```