package behavior;

import entities.Author;
import entities.Color;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class StepArgumentSteps {
    @Given("I have {int} cucumbers in my belly")
    public void i_have_cucumbers_in_my_belly(int number) {
        System.out.println(number);
    }
    @Given("I have a {color} ball")
    public void i_have_a_color_ball(Color color) {
        System.out.println(color.getColor());
    }

    @ParameterType("red|blue|yellow")
    public Color color(String color) {
        return new Color(color);
    }

    @Given("I have {int} apple(s) in my belly")
    public void i_have_apple_in_my_belly(int number) {
    }

    @Given("I have {int} banana(s) in my belly/stomach")
    public void i_have_banana_in_my_belly_stomach(int number) {
    }

    @Given("I have {int} \\{what} beef in my belly \\(amazing!)")
    public void i_have_beef_in_my_belly_amazing(int number) {
    }

    @Given("a blog post named {string} with Markdown body")
    public void a_blog_post_named_with_markdown_body(String name, String docString) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(docString);
    }

    @Given("the following users exist:")
    public void the_following_users_exist(DataTable dataTable) {
        System.out.println(dataTable.cell(1, 0));
    }

//    @Given("I have {float} cucumbers in my belly")
//    public void i_have_cucumbers_in_my_belly(float number) {
//        System.out.println(number);
//    }

//    @Given("I have {int} {word} in my belly")
//    public void i_have_word_in_my_belly(int number,String str) {
//        System.out.println(number);
//        System.out.println(str);
//    }

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate isDate(String year, String month, String day) {
        return LocalDate.of(
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day));
    }
    @Given("today is {isDate}")
    public void today_is(LocalDate date) {
        System.out.println("The date is:" + date.toString());
    }

    @DataTableType(replaceWithEmptyString = "[EmptyStr]")
    public  Author authorEntryTransformer(Map<String,String> entry) {
        Author author = new Author();
        author.setFirstName(entry.get("firstName"));
        author.setLastName(entry.get("lastName"));
        author.setBirthDate(entry.get("birthDate"));
        return author;
    }
    @Given("a list of authors in a table")
    public void a_list_of_authors_in_a_table(@Transpose List<Author> authors) {
        for (Author author : authors)
            System.out.println(String.format("author:[%s,%s,%s]", author.getFirstName(), author.getLastName(), author.getBirthDate()));
    }


    @Given("this is Json data: {}")
    public void this_is_json_data(Author author) {
        System.out.println(String.format("From Json author:[%s,%s,%s]", author.getFirstName(), author.getLastName(), author.getBirthDate()));
    }
}
