package com.medium.agrawalniket.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Transformer;
import org.junit.Test;


public class ListUtilTest {

  List<UserObject> defaultUserList = Arrays.asList(new UserObject("User1", 1, 10000.0),
      new UserObject("User2", 2, 20000.0), new UserObject("User3", 3, 30000.0),
      new UserObject("User4", 4, 40000.0), new UserObject("User5", 5, 50000.0));

  @Test
  public void testDefaultList() {
    List<UserObject> nullList = null;
    assertEquals(defaultUserList, ListUtils.defaultIfNull(defaultUserList, nullList));

    assertTrue(ListUtils.emptyIfNull(nullList).isEmpty());
  }

  @Test
  public void testFixedList() {
    List<UserObject> fixedList = ListUtils.fixedSizeList(defaultUserList);

    assertEquals(Double.valueOf(10000), fixedList.get(0).getUserSalary());

    Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
      fixedList.add(new UserObject("User6", 6, 60000.0));
    });
    assertEquals("List is fixed size", exception.getMessage());

    assertThrows(UnsupportedOperationException.class, () -> {
      fixedList.remove(0);
    });
  }

  @Test
  public void testPredicatedList() {
    assertThrows(IllegalArgumentException.class, () -> {
      ListUtils.predicatedList(defaultUserList, userObj -> userObj.getUserSalary() > 20000.0);
    });

    List<UserObject> predicatedUserList = ListUtils.predicatedList(new ArrayList<UserObject>(),
        userObj -> userObj.getUserSalary() > 20000.0);

    assertThrows(IllegalArgumentException.class, () -> {
      predicatedUserList.add(new UserObject("User", 2, 10000.0));
    });
  }

  @Test
  public void testSelect() {
    List<UserObject> java8List = defaultUserList.stream()
        .filter(userObj -> userObj.getUserSalary() > 20000.0).collect(Collectors.toList());
    assertEquals(3, java8List.size());

    List<UserObject> predicatedList =
        ListUtils.select(defaultUserList, userObj -> userObj.getUserSalary() > 20000.0);
    assertEquals(3, predicatedList.size());

    List<UserObject> listNotMeetingPredicate =
        ListUtils.selectRejected(defaultUserList, userObj -> userObj.getUserSalary() > 20000.0);
    assertEquals(2, listNotMeetingPredicate.size());
  }

  @Test
  public void testIsEqual() {
    List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
    List<Integer> list2 = Arrays.asList(1, 2, 3, 4);
    List<Integer> list3 = Arrays.asList(3, 4, 5, 6, 7);

    assertTrue(ListUtils.isEqualList(list1, list2));
    assertFalse(ListUtils.isEqualList(list1, list3));

    List<Integer> linkedList = new LinkedList<>(list1);
    Set<Integer> hashSet = new HashSet<>(list1);
    assertTrue(ListUtils.isEqualList(list1, linkedList));
    assertTrue(ListUtils.isEqualList(list1, hashSet));

    assertEquals(Arrays.asList(3, 4), ListUtils.intersection(list1, list3));
  }

  @Test
  public void testListUtilsFeatures() {

    List<List<UserObject>> partitionedList = ListUtils.partition(defaultUserList, 2);
    assertEquals(3, partitionedList.size());

    int indexOfObject =
        ListUtils.indexOf(defaultUserList, userObj -> userObj.getUserSalary().equals(20000.0));
    assertEquals(1, indexOfObject);

  }

  @Test
  public void testLazyListWithTransformer() {

    Transformer<Integer, UserObject> transformer = input -> {
      if (defaultUserList.size() > input)
        return defaultUserList.get(input);
      else
        return new UserObject();
    };

    List<UserObject> lazyList = ListUtils.lazyList(new ArrayList<UserObject>(), transformer);

    assertEquals(defaultUserList.get(0), lazyList.get(0));
    assertEquals(new UserObject(), lazyList.get(6));
    assertEquals(1, lazyList.indexOf(null));

  }

  @Test
  public void testLazyListWithFactory() {
    Factory<UserObject> factory = new Factory<UserObject>() {
      public UserObject create() {
        return new UserObject("default", 0, 0.0);
      }
    };

    List<UserObject> lazyList1 = ListUtils.lazyList(new ArrayList<UserObject>(), factory);
    assertEquals("default", lazyList1.get(0).getUserName());
    lazyList1.add(0, defaultUserList.get(0));
    assertEquals(defaultUserList.get(0), lazyList1.get(0));
    assertEquals("default", lazyList1.get(1).getUserName());

  }
}
