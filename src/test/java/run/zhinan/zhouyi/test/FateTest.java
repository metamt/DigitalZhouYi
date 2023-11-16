package run.zhinan.zhouyi.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Test;
import run.zhinan.zhouyi.classic.fate.FateCode;

import java.time.LocalDateTime;

public class FateTest {
    @Test
    public void testFateCode() {
        System.out.println(JSON.toJSONString(
                FateCode.of(LocalDateTime.of(1976, 2, 11, 11, 40)),
                SerializerFeature.PrettyFormat, SerializerFeature.SortField, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString
        ));
    }
}
