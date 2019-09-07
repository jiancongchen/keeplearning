package http.request;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author : jiancongchen on 2019-09-06
 **/
public class TestHttpRquest {

    public static final String URL = "http://3.duotucms.com/index.php/index/order";
    public static final HashMap<String,String> paramterMap = new HashMap<>();

    static{
        paramterMap.put("name","陈建聪");
        paramterMap.put("tel","18201826046");
        paramterMap.put("sn","350303199409050318");
    }

    @Test
    public void reservationApply() {
        while(true){
            String result = HttpRequest.doPostForm(URL,paramterMap);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String status = (String)jsonObject.get("status");
            System.out.println("status:"+ status);

            String message = (String)jsonObject.get("message");
            System.out.println("message:"+message);
            if(!"2".equals(status)){
                break;
            }
            System.out.println();
        }
    }
}
