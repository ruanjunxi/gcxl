package com.example.gcxl.util;//package com.example.gcxl.util;
//
//import com.example.gcxl.domain.OutBound;
//import com.example.gcxl.domain.OutBoundWithDetail;
//import io.swagger.annotations.ApiModelProperty;
//
//import java.lang.reflect.Field;
//import java.util.Date;
//
///**
// * @ClassName: ReflectUtils
// * @author: Eason
// * @data: 2022/4/24 16:42
// */
//public class ReflectUtils <T> {
//    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
//        OutBoundWithDetail outBoundWithDetail = new OutBoundWithDetail(1, "s", "w", new Date());
//        Class c1 = outBoundWithDetail.getClass();
//        Field[] fields = c1.getDeclaredFields();
//        Field account = c1.getDeclaredField("account");
//        System.out.println(account.getName());
//        for (Field f : fields) {
//            f.setAccessible(true);
////            System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(outBoundWithDetail));
//            System.out.println(f.getAnnotation(ApiModelProperty.class).value()+ f.getName() + f.get(outBoundWithDetail));
//        }
//
//    }
//
//    public void  get( T obg) throws NoSuchFieldException, IllegalAccessException {
//        Class c1 = obg.getClass();
//        Field[] fields = c1.getDeclaredFields();
//        Field account = c1.getDeclaredField("account");
//        System.out.println(account.getName());
//        for (Field f : fields) {
//            f.setAccessible(true);
////            System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(outBoundWithDetail));
//            System.out.println(f.getAnnotation(ApiModelProperty.class).value()+ f.getName() + f.get(obg));
//        }
//    }
//}
