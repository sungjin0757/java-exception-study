## Item 73. 추상화 수준에 맞는 예외를 던져라
***
수행하려는 일과 관련 없어 보이는 예외가 튀어나오면 당황스러울 것입니다.
메서드가 저수준 예외를 처리하지 않고 바깥으로 전파해버릴 때 종종 일어나는 일입니다.

이는 프로그래머를 당황시키는 것을 넘어 내부 구현 방식을 드러내어 윗 레벨 `API`를 오염시킵니다.
다음 릴리스에서 구현 방식을 바꾸면 다른 예외가 튀어나와 기존 클라이언트 프로그램을 깨지게 할 수도 있습니다.

이 문제를 피하려면 상위 계층에서는 저수준 예외를 잡아 자신의 추상화 수준에 맞는 예외로 바꿔 던져야 합니다.
이를 예외 번역이라 합니다.

```java
try{
        ... // 저수준 추상화를 이용한다.
} catch(LoweLevelException e){
    throw new HigherLevelException() ;   
}
```

다음의 코드를 봅시다.
```java
    /**
     * 이 리스트 안의 지정한 위치의 원소를 반환한다.
     * @throws IndexOutOfBoundsException index가 범위 밖이라면 발생
     */
    @Override
    public E get(int index) {
        ListIterator<E> listIterator = listIterator(index);
        try{
            return listIterator.next();
        }catch (NoSuchElementException e){
            throw new IndexOutOfBoundsException("인덱스: "+index);
        }
    }
```

예외를 번역할 때, 저수준 예외가 디버깅에 도움이 된다면 예외 연쇄를 사용하는 게 좋습니다. 문제의 근본 원인인 저수준 예외를
고수준 예외에 실어 보내는 방식입니다.

```java
try{
        ... // 저수준 추상화를 이용한다.
} catch(LoweLevelException e){
    throw new HigherLevelException(e) ;   

```

고수준 예외의 생성자는 상위 클래스의 생성자에 이 '원인'을 건네주어, 최종적으로 Throwable 생성자까지 건네지게 합니다.

**예외 연쇄용 생성자**
```java
class HigherLevelException extends Exception{
    HigherLevelException(Throwable cause){
        super(cause);
    }
}
```

예외 번역과 연쇄에 대하여 알아봤습니다.
하지만, 이와 같은 방법을 남용해서도 안됩니다. 가능하다면 저수준 메서드가 반드시 성공하도록 하여 아래 계층에서는 예외가 발생하지 않도록 하는 것이 최선입니다.

차선책으로는, 상위 계층에서 예외를 조용하게 처리하여 문제를 `API`호출자에까지 전파하지 않는 방법이 있습니다.
이와 같은 경우는 `Logging`을 활용하여 기록해두면 좋습니다.