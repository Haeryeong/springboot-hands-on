package kr.co.fastcampuse.web.account.model.comparator;


import kr.co.fastcampuse.web.account.model.AccountModel;
import org.springframework.data.domain.Sort.Direction;

import java.lang.reflect.Field;
import java.util.Comparator;

import static org.springframework.data.domain.Sort.Direction.ASC;

public class ModelComparator implements Comparator<AccountModel> {

    final String sortBy;
    final Direction direction;

    public ModelComparator(String sortBy, Direction direction) {
        this.sortBy = sortBy;
        this.direction = direction;
    }

    @Override
    public int compare(AccountModel o1, AccountModel o2) {
        try {
            Field field1 = o1.getClass().getDeclaredField(sortBy);
            Field field2 = o2.getClass().getDeclaredField(sortBy);

            field1.setAccessible(true);
            field2.setAccessible(true);

            Comparable d1 = (Comparable)field1.get(o1);
            Comparable d2 = (Comparable)field2.get(o2);

            return (direction == ASC)? d1.compareTo(d2) : d2.compareTo(d1);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Missing variable sortBy");
        } catch (ClassCastException e) {
            throw new RuntimeException("sortBy is not found in class list");
        }

    }
}

