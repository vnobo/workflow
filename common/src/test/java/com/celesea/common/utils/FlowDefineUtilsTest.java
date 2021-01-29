package com.celesea.common.utils;

import com.hankcs.hanlp.HanLP;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.junit.jupiter.api.Test;

/**
 * com.celesea.common.utils.DeploymentUtilsTest
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/28
 */
class FlowDefineUtilsTest {

  @Test
  public void hanLPTest() {
    // 申请单 -> s_q_d.bpmn20.xml.
    String bpmnFileName = HanLP.convertToPinyinFirstCharString("员工请假申请单",
        "", false).toUpperCase(Locale.ROOT);
    bpmnFileName =
        "WF_" + bpmnFileName + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmssS"));
    System.out.println(bpmnFileName);
  }

}