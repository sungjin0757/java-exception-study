## Item 72. 표준 예외를 사용하라
***

숙련된 프로그래머는 그렇지 못한 프로그래머 보다 더 많은 코드를 재사용한다고 합니다.
예외도 마찬가지로 재사용하는 것이 좋으며, 자바 라이브러리는 대부분 `API`에서 쓰기에 충분한 수의 예외를 제공합니다.

**표준 예외를 사용하면 좋은 점**
1. 개발자의 `API`가 다른 사람이 익히고 사용하기 쉬워진다는 것입니다.
2. 낯선 예외를 사용하지 않게 되어 읽기 쉽게 됩니다.
3. 예외 클래스 수가 적을수록 메모리 사용량도 줄고 클래스를 적재하는 시간도 짧게 걸립니다.

<br></br>
**주요 쓰이는 예외와 재사용하지 말아야할 예외를 알아봅시다.**
***

`Exception`, `RuntimeException`, `Throwable`, `Error`는 직접 사용하면
안됩니다.

이 예외들은 다른 예외들의 상위 클래스이므로, 즉 여러 성격의 예외들을 포괄하는 클래스이므로 안정적으로 테스트할 수 없습니다.

**주로 쓰이는 예외**
1. `IllegaArgumentException` : 허용하지 않는 값이 인수로 건네졌을 때 (null 은 따로 `NullPointerException`으로 처리)
2. `IllegalStateExcetpion` : 객체가 메서드를 수행하기에 적절하지 않은 상태일 때
3. `NullPointerException` : null을 허용하지 않는 메서드에 null을 건넸을때
4. `IndexOutOfBoundsException` : 인덱스가 범위를 넘어 섰을 때
5. `ConcurrentModificationException` : 허용하지 않는 동시 수정이 발견됐을 때
6. `UnSupportedOperationException` : 호출한 메서드를 지원하지 않을 때

여기서, `IllegalArgumentException` 과 `IllegalStateException` 이 모호하다 싶을 수 있습니다.
인수 값이 무엇이었든 어차피 실패했을 거라면 `IllegalStateException`을, 그렇지 않으면 `IllegalArgumentException`을 사용하도록 권장 됩니다.