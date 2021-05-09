package tests;

import com.qc.skillscy.commons.codes.ApplicationCodes;
import com.qc.skillscy.commons.dto.StatusIndicator;
import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.misc.QcUtils;
import dto.SampleDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
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
    public void parseForFirestore_ValidObject_ReturnsMapObject() throws WebServiceException {
        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.setName("Sample Name");
        sampleDTO.setAge(12);
        sampleDTO.setMarks(72);
        sampleDTO.setGrade('B');

        Map<String, Object> returnedObject = QcUtils.parseForFirestore(sampleDTO);

        Assert.assertNotNull(returnedObject);
        Assert.assertNotNull(returnedObject.get("name"));
        Assert.assertNotNull(returnedObject.get("age"));
        Assert.assertNotNull(returnedObject.get("marks"));
        Assert.assertNotNull(returnedObject.get("grade"));
        Assert.assertEquals(returnedObject.get("name"), "Sample Name");
        Assert.assertEquals(returnedObject.get("age"), 12);
        Assert.assertEquals(returnedObject.get("marks"), 72.0);
        Assert.assertEquals(returnedObject.get("grade"), "B");
    }

    @Test
    public void defaultSuccessBody_NormalCall_ReturnsStatusIndicatorObject() {
        StatusIndicator statusIndicator = QcUtils.defaultSuccessBody();
        Assert.assertNotNull(statusIndicator);
        Assert.assertTrue(statusIndicator.isSuccess());
    }

}
