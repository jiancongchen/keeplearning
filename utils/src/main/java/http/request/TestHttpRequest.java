package http.request;

import com.alibaba.fastjson.JSONObject;


import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : jiancongchen on 2019-09-06
 **/
public class TestHttpRequest {


    public static final String URL_3 = "http://3.duotucms.com/index.php/index/order";
    /**
     * 下沙街道社区
     */
    public static final String URL_6 = "http://6.duotucms.com/index.php/index/order";
    public static final HashMap<String,String> qttPersonMap = new HashMap<>();
    public static final HashMap<String,String> xukePersonMap = new HashMap<>();

    static{
        qttPersonMap.put("name","邱婷婷");
        qttPersonMap.put("tel","18818208440");
        qttPersonMap.put("sn","350321199311253347");

        xukePersonMap.put("name","许珂");
        xukePersonMap.put("tel","13675623461");
        xukePersonMap.put("sn","342901199308106482");

    }

    public ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,100L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());


    public static void main(String[] args) {
        TestHttpRequest testHttpRequest = new TestHttpRequest();
        testHttpRequest.currentRequest();
    }

    public void currentRequest(){
        ThreadForApply qiutingting = new ThreadForApply(URL_3,qttPersonMap);
        ThreadForApply xuke = new ThreadForApply(URL_6,xukePersonMap);
        threadPoolExecutor.execute(qiutingting);
        threadPoolExecutor.execute(xuke);
    }


    public void reservationApply(String URL,HashMap<String,String> personInfo) {
        while(true){
            String result = HttpRequest.doPostForm(URL,personInfo);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String status = (String)jsonObject.get("status");
            System.out.println("status:"+ status);

            String message = (String)jsonObject.get("message");
            System.out.println("name:" + personInfo.get("name") + "  message:"+message);
            if(!"2".equals(status) || message.contains("预约成功")){
                break;
            }
            System.out.println();
        }
    }

    private class ThreadForApply extends Thread{

        private HashMap<String,String> personInfo;
        private String URL;

        public ThreadForApply(String URL,HashMap<String,String> personInfo){
            this.personInfo = personInfo;
            this.URL = URL;
        }

        @Override
        public void run() {
            reservationApply(URL,personInfo);
        }
    }
}
