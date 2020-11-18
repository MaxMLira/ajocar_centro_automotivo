package br.com.ajocar.Ajocar.model.paging;

import java.util.*;

public class EmployeeComparators {

    static  class Key{
        String name;
        Direction dir;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Direction getDir() {
            return dir;
        }

        public void setDir(Direction dir) {
            this.dir = dir;
        }

        public Key(String name, Direction dir) {
            this.name = name;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(name, key.name) &&
                    dir == key.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, dir);
        }
    }


    static Map<Key, Comparator<Object>> map  = new HashMap<>();
//    private List
    static {

    }
}
