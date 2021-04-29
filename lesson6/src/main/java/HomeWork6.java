public class HomeWork6 {
    public static void main(String[] args) {
        //Реализуйте свой итератор массива объектов
        Integer[] array = {134, 1, 97, 5678, 0};
        MyIterator<Integer> myIterator = new MyIterator<>(array);
        while (myIterator.hasNext()) {
            System.out.println(myIterator.next());
        }
    }
}
