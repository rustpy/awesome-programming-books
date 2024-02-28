package com.demo.stream;

import com.model.Author;
import com.model.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author: yong.peng
 * @create: 2023/12/18 14:23
 **/

public class StreamDemo {

    public static void main(String[] args) {

        // 获取任意一个年龄大于18的作家，如果存在就输出他的名字
        List<Author> authors = getAuthors();
        Optional<Author> optionalAuthor = authors.stream()
            .filter(author -> author.getAge()>18)
            .findAny();
        optionalAuthor.ifPresent(author ->
            System.out.println(author.getName()));

        // 使用reduce求所有作者中年龄的最小值
        Optional<Integer> minOptional = authors.stream()
            .map(Author::getAge)
            .reduce((result, element) -> result > element ? element :
                result);
        minOptional.ifPresent(System.out::println);

        Optional<Author> authorOptional = Optional.ofNullable(getAuthor());
        authorOptional.ifPresent(author ->
            System.out.println(author.getName()));

        Author author1 = authorOptional.orElse(new Author());

        Author author2 = authorOptional.orElseGet(Author::new);
        // 获取数据，如果数据不为空就能获取到该数据。如果为空则根据你传入的参数来创建异常抛出。
        try {
            Author author3 = authorOptional.orElseThrow((Supplier<? extends Throwable>)
                () -> new RuntimeException("author is null"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        // 如果数据不为空就执行下面的方法
        authorOptional.filter(author -> author.getAge()>100).ifPresent(author ->
            System.out.println(author.getName()));

    }

    private static Author getAuthor(){
        return null;
    }



    private static List<Author> getAuthors(){

        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();
        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99,
            "讲述如何从失败中明悟真理"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56,
            "一个哲学家的恋爱观注定很难把他所在的时代理解"));
        books3.add(
            new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100,
            "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100,
            "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);
        return new ArrayList<>
            (Arrays.asList(author, author2, author3, author4));
    }

}
