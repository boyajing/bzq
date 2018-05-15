package bas.jeda.workflow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxq
 *
 * 定义一些流程中常用的布标数据，比如流程定义信息等，避免重复访问数据库
 */
public class FlowConstant {

    /**
     * 流程实例定西 key 是流程定义id 值是 MyProcessDefinition
    */
    private static Map<String, MyProcessDefinition> processDefinitionMap = new HashMap<String, MyProcessDefinition>();


    private static Map<String, Object> processDescriptionMap = new HashMap<String, Object>();//流程描述信息，左侧显示流程审批环节用到该数据


    public static Map<String, MyProcessDefinition> getProcessDefinitionMap() {
        return processDefinitionMap;
    }

    /**
     *
     * @param processDefintionId 流程定义id；
     * @return MyProcessDefinition
     */
    public static MyProcessDefinition getMyProcessDefinitionById(String processDefintionId){

        return processDefinitionMap.get(processDefintionId);
    }

    /**
     *
     * @return  流程的环节信息
     */
    public static Map<String, Object> getProcessDescriptionMap() {

        return processDescriptionMap;
    }
      /**
     *
     * @param processDefintionId
     * @return  流程的环节信息
     */
    public static Object getProcessDescriptionById(String processDefintionId) {

        return processDescriptionMap.get(processDefintionId);
    }
}
