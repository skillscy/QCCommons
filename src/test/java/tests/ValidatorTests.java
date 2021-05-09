package tests;

import com.qc.skillscy.commons.codes.ApplicationCodes;
import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.misc.Validator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatorTests {

    /*
     * Naming Convention of these Methods
     * ----------------------------------
     *
     * [MethodName_StateUnderTest_ExpectedBehavior]
     *
     */

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void ignoreNullByString_NullInput_ReturnsEmptyString() {
        String outputValue = Validator.ignoreNullByString(null);
        Assert.assertEquals("Both input and output values are same", "", outputValue);
    }

    @Test
    public void ignoreNullByString_NonNullInput_ReturnsSameInput() {
        String inputValue = "sample";
        String outputValue = Validator.ignoreNullByString(inputValue);
        Assert.assertEquals("Both input and output values are same", inputValue, outputValue);
    }

    @Test
    public void notNull_NonNullInput_DoNothing() throws WebServiceException {
        Validator.notNull("sample");
    }

    @Test
    public void notNull_NullInput_ThrowsWebServiceException() throws WebServiceException {
        exception.expect(WebServiceException.class);
        exception.expectMessage(ApplicationCodes.VALIDATION_NULL_FOUND.logMessage());
        Validator.notNull(null);
    }

    @Test
    public void checkQcEmployeeID_InvalidEmployeeID_ThrowsWebServiceException() throws WebServiceException {
        exception.expect(WebServiceException.class);
        exception.expectMessage(ApplicationCodes.INVALID_QC_EMPLOYEE_ID.logMessage());
        Validator.checkQcEmployeeID("INVALID");
    }

    @Test
    public void checkQcEmployeeID_ValidEmployeeID_DoNothing() throws WebServiceException {
        Validator.checkQcEmployeeID("Q1AA1000");
    }

    @Test
    public void checkQcInternID_InvalidInternID_ThrowsWebServiceException() throws WebServiceException {
        exception.expect(WebServiceException.class);
        exception.expectMessage(ApplicationCodes.INVALID_QC_INTERN_ID.logMessage());
        Validator.checkQcInternID("INVALID");
    }

    @Test
    public void checkQcInternID_ValidInternID_DoNothing() throws WebServiceException {
        Validator.checkQcInternID("Q2AA1000");
    }

    @Test
    public void checkQcTraineeID_InvalidTraineeID_ThrowsWebServiceException() throws WebServiceException {
        exception.expect(WebServiceException.class);
        exception.expectMessage(ApplicationCodes.INVALID_QC_TRAINEE_ID.logMessage());
        Validator.checkQcTraineeID("INVALID");
    }

    @Test
    public void checkQcTraineeID_ValidTraineeID_DoNothing() throws WebServiceException {
        Validator.checkQcTraineeID("Q3AA1000");
    }

    @Test
    public void checkQcID_InvalidCompanyID_ThrowsWebServiceException() throws WebServiceException {
        exception.expect(WebServiceException.class);
        exception.expectMessage(ApplicationCodes.INVALID_QC_ID.logMessage());
        Validator.checkQcID("INVALID");
    }

    @Test
    public void checkQcID_ValidCompanyID_DoNothing() throws WebServiceException {
        Validator.checkQcID("Q3AA1001");
    }

}
