package iie.ac.cn.calculation.util;

import iie.ac.cn.common.utils.CartesianProduct;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CartesianProductTest {

    @Test
    public void cartesionProduct() {
        List<String> a=new ArrayList<>();
        a.add("习");
        a.add("刁");
        List<String> b=new ArrayList<>();
        b.add("进");
        b.add("静");
        b.add("劲");
        List<String> c=new ArrayList<>();
        c.add("品");
        c.add("平");
        c.add("萍");
        List<List<String>>  d=new ArrayList<>();

        d.add(a);
        d.add(b);
        d.add(c);

        System.out.println(CartesianProduct.cartesionProduct(d));

     }
}