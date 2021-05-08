import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.misc.QcUtils;
import org.junit.Assert;
import org.junit.Test;

public class QcUtilTests {

    /*
     * Naming Convention of these Methods
     * ----------------------------------
     *
     * [MethodName_StateUnderTest_ExpectedBehavior]
     *
     */

    @Test
    public void generateNextID_ClearCompanyID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextID("Q1AA1000");
        Assert.assertEquals("Q1AA1001", nextID);
    }

    @Test
    public void generateNextID_NumberEndCompanyID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextID("Q1AA9999");
        Assert.assertEquals("Q1AB1000", nextID);
    }

    @Test
    public void generateNextID_TextEndCompanyID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextID("Q1AZ9999");
        Assert.assertEquals("Q1BA1000", nextID);
    }

    @Test
    public void generateNextID_ToExpireEmployeeID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextID("Q1WZ9999");
        Assert.assertEquals("Q1XA1000", nextID);
    }

    @Test
    public void generateNextID_ToExpireInternID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextID("Q2WZ9999");
        Assert.assertEquals("Q2XA1000", nextID);
    }

    @Test
    public void generateNextID_ToExpireTraineeID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextID("Q3WZ9999");
        Assert.assertEquals("Q3XA1000", nextID);
    }

}
