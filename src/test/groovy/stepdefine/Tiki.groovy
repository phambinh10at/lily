package stepdefine

import com.codoid.products.fillo.Fillo
import com.codoid.products.fillo.Recordset
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import cucumber.api.java.en.And
import net.serenitybdd.screenplay.Actor
import page.TikiPage

import java.sql.Connection

class Tiki {
    TikiPage tikiPage
    Actor actor

    @And("Access to the Tiki system")
    void access() {
        tikiPage.open()
        Fillo fillo = new Fillo()
        Connection conn = fillo.getConnection("testData.xls");

        String query = "Select * from Data";

        Recordset record = conn

    }

    @And("When Search {string} on Tiki")
    public void whenSearchOnTiki(String input) {
        String searchQueryApi = "https://tiki.vn/api/v2/products?q=$input"
        JsonNode response = Unirest.get(searchQueryApi)
                .asJson()
                .getBody()
        System.out.println(response)

        int[] pri = new int[10]
        String[] nameproduct = new String[10]
        String[] url_pathproduct = new String[10]
        for (int i = 0; i < 10; i++) {
            nameproduct[i] = response.getObject().getJSONArray("data").get(i).getAt("name")
            int price = response.getObject().getJSONArray("data").get(i).getAt("price")
            url_pathproduct[i] = response.getObject().getJSONArray("data").get(i).getAt("url_path")
            pri[i] = price
        }
        System.out.println('price Tiki: ' + pri)
        System.out.println('name Tiki: ' + nameproduct)
        System.out.println('url_path: ' + url_pathproduct)

        int tg = pri[0]
        System.out.println(pri[0])
        for (int n = 0; n < pri.size(); n++) {
            for (int m = n + 1; m < pri.size(); m++) {
                if (pri[n] > pri[m]) {
                    tg = pri[n]
                    pri[n] = pri[m]
                    pri[m] = tg
                }
            }
        }
        System.out.println('price: ' + pri.toString())
        for (int b = 0; b < pri.size(); b++) {
            String name1 = pri[b]
            String name2 = nameproduct[b]
            String name3 = url_pathproduct[b]
            System.out.println('name: ' + name2.toString())
            System.out.println('price: ' + name1.toString())
            System.out.println('url_path: https://tiki.vn/' + name3.toString())
            System.out.println("-----------------------------------------------------------------------")
        }
        String searchQueryApiShopee = "https://shopee.vn/api/v4/search/search_items?by=relevancy&keyword=$input"
        JsonNode responseShopee = Unirest.get(searchQueryApiShopee)
                .asJson()
                .getBody()
        System.out.println(responseShopee)

        int[] priceShopee = new int[10]
        String[] nameproductShopee = new String[10]
        for (int ii = 0; ii < 10; ii++) {
            nameproductShopee[ii] = responseShopee.getObject().getJSONArray("items").get(ii).getAt("item_basic").getAt("name")
            int priceS = responseShopee.getObject().getJSONArray("items").get(ii).getAt("item_basic").getAt("price")
            priceShopee[ii] = priceS
        }

        System.out.println('price shopee: ' + priceShopee)
        System.out.println('name shopee: ' + nameproductShopee)


    }

}






