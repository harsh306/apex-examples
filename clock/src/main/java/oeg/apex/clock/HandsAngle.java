package oeg.apex.clock;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Created by harsh on 21/6/16.
 */
public class HandsAngle extends BaseOperator {
    public String AngleCalculator(String min,String sec) {
        String angle="NUL";
        //one minute minute-hand ==6deg
        //one sec  minu-hand = 0.1 deg
        //one sec sec-hand =6 deg
        double m=Double.parseDouble(min);
        double s=Double.parseDouble(sec);
        m = (6*m+0.1*s);
        s =6*s;
        angle=String.valueOf((m-s))+" deg";
        return angle;
    }
    public final transient DefaultInputPort<String> inputPort = new DefaultInputPort<String>() {
        @Override
        public void process(String s) {
            String[] a =s.split(" ");
            Marker m= MarkerFactory.getMarker("ERROR");
            Logger logger= LoggerFactory.getLogger(HandsAngle.class);
            logger.info("Calculte angle here");
            logger.debug(m,"you have an error");

            String angle =AngleCalculator(a[0],a[1]);
            outputPort.emit(angle);
        }
    };



    public final transient DefaultOutputPort<String> outputPort =new DefaultOutputPort<String>();
}
