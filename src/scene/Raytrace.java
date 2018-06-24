package scene;

import primitives.Ray;

import java.util.HashMap;
import java.util.Map;

public  class Raytrace {
    public static Map<String,Map<String,String>> arr=new HashMap<>();
    public  Raytrace(){
        Map<String,String> front=new HashMap<>();
        front.put("front","end");
        front.put("left","right");
        front.put("right","left");
        front.put("down","up");
        front.put("up","down");
        front.put("end","front");
        arr.put("front",front);

        Map<String,String> up=new HashMap<>();
        up.put("front","down");
        up.put("left","right");
        up.put("right","left");
        up.put("down","end");
        up.put("up","front");
        up.put("end","up");
        arr.put("up",up);

        Map<String,String> down=new HashMap<>();
        down.put("front","up");
        down.put("left","right");
        down.put("right","left");
        down.put("down","front");
        down.put("up","end");
        down.put("end","down");
        arr.put("down",down);

        Map<String,String> right=new HashMap<>();
        right.put("front","left");
        right.put("left","end");
        right.put("right","front");
        right.put("down","up");
        right.put("up","down");
        right.put("end","right");
        arr.put("right",right);

        Map<String,String> left=new HashMap<>();
        left.put("front","right");
        left.put("left","front");
        left.put("right","end");
        left.put("down","up");
        left.put("up","down");
        left.put("end","left");
        arr.put("left",left);

        Map<String,String> end=new HashMap<>();
        end.put("front","front");
        end.put("left","right");
        end.put("right","left");
        end.put("down","up");
        end.put("up","down");
        end.put("end","end");
        arr.put("end",end);

    }
}
