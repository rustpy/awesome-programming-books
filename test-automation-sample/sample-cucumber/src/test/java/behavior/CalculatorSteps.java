package behavior;

import com.google.inject.Inject;
import core.CraftsmanAssert;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
//import org.junit.Assert;

@ScenarioScoped
public class CalculatorSteps {

    private int firstNumber;
    private int secondNumber;
    private int result;
    private CraftsmanAssert assertObject;

    @Inject
    public CalculatorSteps(CraftsmanAssert assertObject) {
        this.assertObject = assertObject;
    }

    @Given("the first number is {int}")
    @假如("第一个数字是 {int}")
    public void the_first_number_is(int number) {
        firstNumber = number;
    }

    @Given("the second number is {int}")
    @假如("第二个数字是 {int}")
    public void the_second_number_is(int number) {
        secondNumber = number;
    }

    @When("the two numbers are added")
    @当("两个数字相加")
    public void the_two_numbers_are_added() {
        result = firstNumber + secondNumber;
    }

    @Then("the result should be {int}")
    @那么("显示的结果为 {int}")
    public void the_result_should_be(int result) {

        //Assert.assertEquals(result, this.result);
        assertObject.assertEquals(result, this.result);
    }

}
