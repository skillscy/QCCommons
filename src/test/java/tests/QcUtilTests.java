package tests;

import com.qc.skillscy.commons.dto.StatusIndicator;
import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.misc.QcUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QcUtilTests {

    /*
     * Naming Convention of these Methods
     * ----------------------------------
     *
     * [MethodName_StateUnderTest_ExpectedBehavior]
     *
     */

    public ExpectedException exception = ExpectedException.none();

    @Test
    public void generateNextCompanyID_ClearCompanyID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextCompanyID("Q1AA1000");
        Assert.assertEquals("Q1AA1001", nextID);
    }

    @Test
    public void generateNextCompanyID_NumberEndCompanyID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextCompanyID("Q1AA9999");
        Assert.assertEquals("Q1AB1000", nextID);
    }

    @Test
    public void generateNextCompanyID_TextEndCompanyID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextCompanyID("Q1AZ9999");
        Assert.assertEquals("Q1BA1000", nextID);
    }

    @Test
    public void generateNextCompanyID_ToExpireEmployeeID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextCompanyID("Q1WZ9999");
        Assert.assertEquals("Q1XA1000", nextID);
    }

    @Test
    public void generateNextCompanyID_ToExpireInternID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextCompanyID("Q2WZ9999");
        Assert.assertEquals("Q2XA1000", nextID);
    }

    @Test
    public void generateNextCompanyID_ToExpireTraineeID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextCompanyID("Q3WZ9999");
        Assert.assertEquals("Q3XA1000", nextID);
    }

    @Test
    public void generateNextArticleID_ClearArticleID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextArticleID("AA1000");
        Assert.assertEquals("AA1001", nextID);
    }

    @Test
    public void generateNextArticleID_NumberEndArticleID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextArticleID("AA9999");
        Assert.assertEquals("AB1000", nextID);
    }

    @Test
    public void generateNextArticleID_TextEndArticleID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextArticleID("AZ9999");
        Assert.assertEquals("BA1000", nextID);
    }

    @Test
    public void generateNextArticleID_ToExpireArticleID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextArticleID("WZ9999");
        Assert.assertEquals("XA1000", nextID);
    }

    @Test
    public void generateNextCommentsID_ClearCommentsID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextCommentsID("00002001");
        Assert.assertEquals("00002002", nextID);
    }

    @Test
    public void generateNextCommentsID_ToExpireCommentsID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextCommentsID("92345678");
        Assert.assertEquals("92345679", nextID);
    }

    @Test
    public void generateNextClientID_ClearClientID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextClientID("C1234");
        Assert.assertEquals("C1235", nextID);
    }

    @Test
    public void generateNextClientID_ToExpireClientID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextClientID("C9876");
        Assert.assertEquals("C9877", nextID);
    }

    @Test
    public void generateNextFilesID_ClearFilesID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextFilesID("FQ1BY8735");
        Assert.assertEquals("FQ1BY8736", nextID);
    }

    @Test
    public void generateNextFilesID_ToExpireFilesID_GeneratesNextValue() throws WebServiceException {
        String nextID = QcUtils.generateNextFilesID("FQ1YF8735");
        Assert.assertEquals("FQ1YF8736", nextID);
    }

    @Test
    public void objectToJsonString_ValidMapObject_ReturnsJsonString() throws WebServiceException {
        Map<String, Object> sampleObject = new HashMap<>();

        Map<String, Object> america = new HashMap<>();
        america.put("CountryName", "United States of America");
        america.put("PresidentName", "Joe Biden");
        america.put("VicePresidentName", "Kamala Harris");
        america.put("NumberOfStates", 50);

        Map<String, Object> india = new HashMap<>();
        india.put("CountryName", "India");
        india.put("PresidentName", "Ram Nath Kovind");
        india.put("VicePresidentName", "Venkaiah Naidu");
        india.put("NumberOfStates", 28);

        List<Map<String, Object>> countries = new ArrayList<>();
        countries.add(america);
        countries.add(india);

        sampleObject.put("Countries", countries);

        String returnedString = QcUtils.objectToJsonString(sampleObject);
        Assert.assertNotNull(returnedString);
    }

    @Test
    public void jsonStringToObject_ValidJsonString_ReturnsMapObject() throws WebServiceException {
        String sampleJsonString = "{\"SampleValue\": 3}";
        Map<String, Object> returnedObject = (Map) QcUtils.jsonStringToObject(sampleJsonString, Map.class);
        Assert.assertNotNull(returnedObject);
        Assert.assertNotNull(returnedObject.get("SampleValue"));
        Assert.assertEquals(returnedObject.get("SampleValue"), 3);
    }

    @Test
    public void defaultSuccessBody_NormalCall_ReturnsStatusIndicatorObject() {
        StatusIndicator statusIndicator = QcUtils.defaultSuccessBody();
        Assert.assertNotNull(statusIndicator);
        Assert.assertTrue(statusIndicator.isSuccess());
    }

}
