package example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

class InvokeTest {
  private static final Logger logger = LoggerFactory.getLogger(InvokeTest.class);

  @Test
  void invokeTest() {

    try {
      logger.info("Invoke TEST");
      HashMap<String,String> event = new HashMap<String,String>();

      String JSON_SOURCE_1= "{ \n" +
              "    \"data\" : \n" +
              "    { \n" +
              "        \"field1\" : \"value1\", \n" +
              "        \"field2\" : \"value2\"\n" +
              "    }\n" +
              "}";
      String JSON_SOURCE ="{\n" +
              "        \"description\": \"createPlantTransac\",\n" +
              "        \"plantID_SDATA_MOTORMODId\": {\n" +
              "            \"description\": \"plantID_SDATA_MOTORMODId\",\n" +
              "            \"Pnom\": 11600,\n" +
              "            \"Vnom\": 6600,\n" +
              "            \"Inom\": 1140,\n" +
              "            \"Fnom\": 60,\n" +
              "            \"Nnom\": 145,\n" +
              "            \"MotorType\": 0,\n" +
              "            \"Theta\": 200,\n" +
              "            \"PolePairs\": 2,\n" +
              "            \"Rs\": 0.0031,\n" +
              "            \"Rr\": 0.00313,\n" +
              "            \"iRem\": 5,\n" +
 //             "            \"id\": \"99fefe6a-ff92-4794-bcb3-b5fb3e141d66\",\n" +
              "            \"table\": \"IdPlantMotorMod\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_SINGLETRAFOId\": {\n" +
              "            \"description\": \"plantID_SDATA_SINGLETRAFOId\",\n" +
              "            \"R\": 0,\n" +
              "            \"Lh\": 0,\n" +
              "            \"Ls\": 0,\n" +
              "            \"u\": 0,\n" +
 //             "            \"id\": \"4ec96065-2488-4784-a927-768bf933d9c2\",\n" +
              "            \"table\": \"IdPlantSingleTrafo\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_DOUBLETRAFOId\": {\n" +
              "            \"description\": \"plantID_SDATA_DOUBLETRAFOId\",\n" +
              "            \"R\": 0,\n" +
              "            \"Lh\": 0,\n" +
              "            \"Ls\": 0,\n" +
              "            \"u\": 0,\n" +
              "            \"trafoAngle\": 0,\n" +
 //             "            \"id\": \"635c4c0d-be5d-4003-aa55-4e3777b2cd91\",\n" +
              "            \"table\": \"IdPlantDoubleTrafo\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_SWITCHCTRLId\": {\n" +
              "            \"description\": \"plantID_SDATA_SWITCHCTRLId\",\n" +
              "            \"eSimLSSType\": 0,\n" +
              "            \"fSimLSSCloseTime\": 0,\n" +
              "            \"fSimLSSOpenTime\": 0,\n" +
 //             "            \"id\": \"267de98a-1ce0-49bf-bdce-470271b97106\",\n" +
              "            \"table\": \"IdPlantSwitchCtrl\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_LINEId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_LINEId\",\n" +
              "            \"Line_ampF\": 0,\n" +
              "            \"Line_freq\": 0,\n" +
 //             "            \"id\": \"1d49b90e-076d-427f-b3e2-e8ef613e2370\",\n" +
              "            \"table\": \"IdPlantLine\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_B2BINDUCTANCEId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_B2BINDUCTANCEId\",\n" +
              "            \"B2B_L\": 0,\n" +
              "            \"B2B_R\": 0,\n" +
 //             "            \"id\": \"bef4e725-8446-45d7-b5da-4b12939d0037\",\n" +
              "            \"table\": \"IdPlantB2BInductance\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_TRAFO3PId\": {\n" +
              "            \"description\": \"plantID_SDATA_TRAFO3PId\",\n" +
              "            \"U1rated\": 0,\n" +
              "            \"U2rated\": 0,\n" +
              "            \"frated\": 0,\n" +
              "            \"uk\": 0,\n" +
 //             "            \"id\": \"a2511e92-334b-4df6-82ef-2efaff650e15\",\n" +
              "            \"table\": \"IdPlantTrafo3P\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_PRECHARGERId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_PRECHARGERId\",\n" +
              "            \"PreChargerSimple_fU\": 0,\n" +
              "            \"PreChargerSimple_fRinv\": 0,\n" +
 //             "            \"id\": \"7421ce9c-2ee8-4219-a3b5-b3ef9ae3d2e8\",\n" +
              "            \"table\": \"IdPlantPrecharger\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_DCLINKId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_DCLINKId\",\n" +
              "            \"DcLink_CDc\": 0,\n" +
              "            \"DcLink_RDc\": 0,\n" +
              "            \"DcLink_iDcM\": 0,\n" +
              "            \"DcLink_MidPot\": 0,\n" +
 //             "            \"id\": \"e10fb569-87cd-49c5-b88d-231cc544de59\",\n" +
              "            \"table\": \"IdPlantDcLink\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_CONVMODELId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_CONVMODELId\",\n" +
              "            \"nSeries\": 0,\n" +
              "            \"phaseR_Switch_vF\": 0,\n" +
              "            \"phaseR_Diode_vF\": 0,\n" +
              "            \"phaseR_Switch_rDiff\": 0,\n" +
              "            \"phaseR_Diode_rDiff\": 0,\n" +
              "            \"phaseS_Switch_vF\": 0,\n" +
              "            \"phaseS_Diode_vF\": 0,\n" +
              "            \"phaseS_Switch_rDiff\": 0,\n" +
              "            \"phaseS_Diode_rDiff\": 0,\n" +
              "            \"phaseT_Switch_vF\": 0,\n" +
              "            \"phaseT_Diode_vF\": 0,\n" +
              "            \"phaseT_Switch_rDiff\": 0,\n" +
              "            \"phaseT_Diode_rDiff\": 0,\n" +
 //             "            \"id\": \"29f3eaa6-aee8-4c47-a264-37819f562d76\",\n" +
              "            \"table\": \"IdPlantConvModel\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_MECHLOADId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_MECHLOADId\",\n" +
              "            \"MechLoad_fa\": 0,\n" +
              "            \"MechLoad_fb\": 0,\n" +
              "            \"MechLoad_fc\": 0,\n" +
              "            \"MechLoad_fRatedFreq\": 0,\n" +
              "            \"MechLoad_fRatedSpeed\": 0,\n" +
              "            \"MechLoad_fRatedTorque\": 0,\n" +
              "            \"MechLoad_fMaxTorque\": 0,\n" +
              "            \"MechLoad_fThetaLoad\": 0,\n" +
              "            \"MechLoad_fStiffness\": 0,\n" +
              "            \"MechLoad_fDampingLoad\": 0,\n" +
              "            \"MechLoad_bSimpleModel\": true,\n" +
 //             "            \"id\": \"8de024ec-05ee-4df2-929e-1a8126047903\",\n" +
              "            \"table\": \"IdPlantMechLoad\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_MOTFILTERId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_MOTFILTERId\",\n" +
              "            \"MotFilter_fC\": 0,\n" +
              "            \"MotFilter_fCR\": 0,\n" +
              "            \"MotFilter_fL\": 0,\n" +
              "            \"MotFilter_fLR\": 0,\n" +
 //             "            \"id\": \"da160e49-2ab7-408f-b7d6-ed4c13deca85\",\n" +
              "            \"table\": \"IdPlantMotFilter\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_RKAId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_RKAId\",\n" +
              "            \"RKAMod_fTEin\": 0,\n" +
              "            \"RKAMod_fTWaterFlowOk\": 0,\n" +
              "            \"RKAMod_fTWaterCondUp\": 0,\n" +
              "            \"RKAMod_fTWaterCondDown\": 0,\n" +
              "            \"RKAMod_fRKACond\": 0,\n" +
              "            \"RKAMod_fWaterPressInlet\": 0,\n" +
              "            \"RKAMod_fWaterPressOutlet\": 0,\n" +
              "            \"RKAMod_fWaterTempInp\": 0,\n" +
              "            \"RKAMod_fTWTIsmooth\": 0,\n" +
              "            \"RKAMod_uCtrlWord\": 0,\n" +
 ////             "            \"id\": \"1014aa98-35da-40b5-bb3e-64536b1bb1c0\",\n" +
              "            \"table\": \"IdPlantRka\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_AUXFANId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_AUXFANId\",\n" +
              "            \"AuxFan_fTDelFbFan\": 0,\n" +
              "            \"AuxFan_fTDelSpdFan\": [\n" +
              "                0\n" +
              "            ],\n" +
              "            \"AuxFan_uCtrlWord\": 0,\n" +
 ////             "            \"id\": \"52032ade-f3b7-4c6a-aaaa-e8cf09a009f7\",\n" +
              "            \"table\": \"IdPlantAuxFan\"\n" +
              "        },\n" +
              "        \"plantID_SDATA_PLANT_CHOPPERId\": {\n" +
              "            \"description\": \"plantID_SDATA_PLANT_CHOPPERId\",\n" +
              "            \"DcLinkChopper_fR_Ch_pos\": 0,\n" +
              "            \"DcLinkChopper_fR_Ch_neg\": 0,\n" +
 //             "            \"id\": \"bf18e895-e6bc-486c-9edd-c5d5ed431518\",\n" +
              "            \"table\": \"IdPlantChopper\"\n" +
              "        }\n" +
//              "        \"idsss\": \"e5c5bd1f-960b-44ee-b60c-c399c043f16e\"\n" +
              "    }";
              String JSON_SOURCE_old = "{\n" +
              "   \"description\":\"createPlantTransac\",\n" +
              "   \"plantID_SDATA_MOTORMODId\":{\n" +
              "      \"Pnom\":11600.0,\n" +
              "      \"Vnom\":6600.0,\n" +
              "      \"Inom\":1140.0,\n" +
              "      \"Fnom\":60.0,\n" +
              "      \"Nnom\":145,\n" +
              "      \"MotorType\":0,\n" +
              "      \"Theta\":200.0,\n" +
              "      \"PolePairs\":2,\n" +
              "      \"Rs\":0.00310,\n" +
              "      \"Rr\":0.00313,\n" +
              "      \"iRem\":5.0,\n" +
              "      \"description\":\"plantID_SDATA_MOTORMODId\"\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_RKAId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_RKAId\",\n" +
              "      \"RKAMod_fTEin\":0.0,\n" +
              "      \"RKAMod_fTWaterFlowOk\":0.0,\n" +
              "      \"RKAMod_fTWaterCondUp\":0.0,\n" +
              "      \"RKAMod_fTWaterCondDown\":0.0,\n" +
              "      \"RKAMod_fRKACond\":0.0,\n" +
              "      \"RKAMod_fWaterPressInlet\":0.0,\n" +
              "      \"RKAMod_fWaterPressOutlet\":0.0,\n" +
              "      \"RKAMod_fWaterTempInp\":0.0,\n" +
              "      \"RKAMod_fTWTIsmooth\":0.0,\n" +
              "      \"RKAMod_uCtrlWord\":0\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_DCLINKId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_DCLINKId\",\n" +
              "      \"DcLink_CDc\":0.0,\n" +
              "      \"DcLink_RDc\":0.0,\n" +
              "      \"DcLink_iDcM\":0.0,\n" +
              "      \"DcLink_MidPot\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_DOUBLETRAFOId\":{\n" +
              "      \"description\":\"plantID_SDATA_DOUBLETRAFOId\",\n" +
              "      \"R\":0.0,\n" +
              "      \"Lh\":0.0,\n" +
              "      \"Ls\":0.0,\n" +
              "      \"u\":0.0,\n" +
              "      \"trafoAngle\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_MECHLOADId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_MECHLOADId\",\n" +
              "      \"MechLoad_fa\":0.0,\n" +
              "      \"MechLoad_fb\":0.0,\n" +
              "      \"MechLoad_fc\":0.0,\n" +
              "      \"MechLoad_fRatedFreq\":0.0,\n" +
              "      \"MechLoad_fRatedSpeed\":0.0,\n" +
              "      \"MechLoad_fRatedTorque\":0.0,\n" +
              "      \"MechLoad_fMaxTorque\":0.0,\n" +
              "      \"MechLoad_fThetaLoad\":0.0,\n" +
              "      \"MechLoad_fStiffness\":0.0,\n" +
              "      \"MechLoad_fDampingLoad\":0.0,\n" +
              "      \"MechLoad_bSimpleModel\":true\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_CHOPPERId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_CHOPPERId\",\n" +
              "      \"DcLinkChopper_fR_Ch_pos\":0.0,\n" +
              "      \"DcLinkChopper_fR_Ch_neg\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_MOTFILTERId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_MOTFILTERId\",\n" +
              "      \"MotFilter_fC\":0.0,\n" +
              "      \"MotFilter_fCR\":0.0,\n" +
              "      \"MotFilter_fL\":0.0,\n" +
              "      \"MotFilter_fLR\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_SINGLETRAFOId\":{\n" +
              "      \"description\":\"plantID_SDATA_SINGLETRAFOId\",\n" +
              "      \"R\":0.0,\n" +
              "      \"Lh\":0.0,\n" +
              "      \"Ls\":0.0,\n" +
              "      \"u\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_TRAFO3PId\":{\n" +
              "      \"description\":\"plantID_SDATA_TRAFO3PId\",\n" +
              "      \"U1rated\":0.0,\n" +
              "      \"U2rated\":0.0,\n" +
              "      \"frated\":0.0,\n" +
              "      \"uk\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_AUXFANId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_AUXFANId\",\n" +
              "      \"AuxFan_fTDelFbFan\":0.0,\n" +
              "      \"AuxFan_fTDelSpdFan\":[\n" +
              "         0.0\n" +
              "      ],\n" +
              "      \"AuxFan_uCtrlWord\":0\n" +
              "   },\n" +
              "   \"plantID_SDATA_SWITCHCTRLId\":{\n" +
              "      \"description\":\"plantID_SDATA_SWITCHCTRLId\",\n" +
              "      \"eSimLSSType\":0,\n" +
              "      \"fSimLSSCloseTime\":0.0,\n" +
              "      \"fSimLSSOpenTime\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_B2BINDUCTANCEId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_B2BINDUCTANCEId\",\n" +
              "      \"B2B_L\":0.0,\n" +
              "      \"B2B_R\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_PRECHARGERId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_PRECHARGERId\",\n" +
              "      \"PreChargerSimple_fU\":0.0,\n" +
              "      \"PreChargerSimple_fRinv\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_CONVMODELId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_CONVMODELId\",\n" +
              "      \"table\":\"IdPlantConvModel\",\n" +
              "      \"nSeries\":0.0,\n" +
              "      \"phaseR_Switch_vF\":0.0,\n" +
              "      \"phaseR_Diode_vF\":0.0,\n" +
              "      \"phaseR_Switch_rDiff\":0.0,\n" +
              "      \"phaseR_Diode_rDiff\":0.0,\n" +
              "      \"phaseS_Switch_vF\":0.0,\n" +
              "      \"phaseS_Diode_vF\":0.0,\n" +
              "      \"phaseS_Switch_rDiff\":0.0,\n" +
              "      \"phaseS_Diode_rDiff\":0.0,\n" +
              "      \"phaseT_Switch_vF\":0.0,\n" +
              "      \"phaseT_Diode_vF\":0.0,\n" +
              "      \"phaseT_Switch_rDiff\":0.0,\n" +
              "      \"phaseT_Diode_rDiff\":0.0\n" +
              "   },\n" +
              "   \"plantID_SDATA_PLANT_LINEId\":{\n" +
              "      \"description\":\"plantID_SDATA_PLANT_LINEId\",\n" +
              "      \"Line_ampF\":0.0,\n" +
              "      \"Line_freq\":0.0\n" +
              "   }\n" +
              "}";

      Map<String,Object> result1 =
              new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);
      Context context = new TestContext();

      DynamoDBCompletePantSaver handler = new DynamoDBCompletePantSaver();
      Object result = handler.handleRequest(result1, context);
      Gson gson = new Gson();
      //System.out.println(gson.toJson(result) );

      assertTrue(((HashMap) result).containsKey("id"));

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
