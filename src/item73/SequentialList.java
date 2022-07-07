package item73;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SequentialList<E> extends AbstractSequentialList {
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

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
}
