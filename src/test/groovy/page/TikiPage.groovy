 package page

import net.serenitybdd.core.pages.PageObject
import net.serenitybdd.core.pages.WebElementFacade
import net.thucydides.core.annotations.DefaultUrl
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.FindBy

@DefaultUrl("https://tiki.vn/")
class TikiPage extends PageObject {
    public TikiPage(WebDriver webDriver) {
        super(webDriver)
    }
    @FindBy(name = '.ehiBA')
    WebElementFacade BTN_SEARCH
    @FindBy(name = '.product-item')
    WebElementFacade List

    @FindBy(css = '.product-item')
    List<WebElementFacade> getListProduct

    //lấy sp theo điều kiện nhập vào
    public WebElementFacade filter(String input) {
        List<WebElementFacade> list = getListProduct
        WebElementFacade result = null
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().toLowerCase().equals(input.toLowerCase())) {
                result = list.get(i)
                break
            }
        }
        return result
    }

}
