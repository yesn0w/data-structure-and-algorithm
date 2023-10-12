package class06;

import java.util.Comparator;

public abstract class C01_Comparator {

    /**
     * 比较器使用的code展示
     */

    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }
    public static class IdAscComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }
}
