package test;

import android.test.InstrumentationTestCase;

import com.master.app.Manager.AcquisitionPara;
import com.master.app.Manager.ConfigMAnager;

import java.util.List;

/**
 * <p>Title:${type_inName}<p/>
 * <p>Description:<p/>
 * <p>Company: </p>
 *
 * @author litao
 * @mail llsmpsvn@gmail.com
 * @date on 2016/12/5
 */
public class TestAcquisition extends InstrumentationTestCase {


    public void testAll() {
        ConfigMAnager.create().initVariablesCfg(getInstrumentation().getContext());
        AcquisitionPara a = new AcquisitionPara();
        List pointList = a.getPointList();
        System.out.println(pointList.size() + "");
    }

}
