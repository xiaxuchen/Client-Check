package ferture_z.com.z.dataUtil;

import android.content.Context;
import android.util.Log;

/**
 * Created by &#x9c7c;&#x5858;&#x4e3b; on 2018/9/25.
 * @param
 *
 */

public class GetRintId {

    private GetRintId(){}
    /**
     * 根据给定的类型名和字段名，返回R文件中的字段的值
     * @param typeName 属于哪个类别的属性 （id,layout,drawable,string,color,attr......）
     * @param fieldName 字段名
     * @return 字段的值
     * @throws Exception
     * @param  :  TextView forget = (TextView) findViewById(R.id.forget);
                            int s3 = GetRintId.getFieldValue("id","forget",this);
                            int s2 = R.id.forget;
                            if(s2==s3){
                            forget.setText("true");
                            }else{
                            forget.setText("false");
                            }
                     结果为true
     */
    public static int getFieldValue(String typeName,String fieldName,Context context){
        int i = -1;
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".R$"+typeName);
            i = clazz.getField(fieldName).getInt(null);
        } catch (Exception e) {

            Log.d(""+context.getClass(),"没有找到"+  context.getPackageName() +".R$"+typeName+"类型资源 "+fieldName+"请copy相应文件到对应的目录.");
            return -1;
        }
        return i;
    }


}
