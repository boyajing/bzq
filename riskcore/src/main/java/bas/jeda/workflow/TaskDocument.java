/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.workflow;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>用于使用JSON保存task的Document信息</p>
 * <p>在设计时需要在流程引擎中定义一些配置参数，为了方便，这些参数保存在用户任务的document属性中，采用cdata节的方式嵌入json字符串。</p>
 * <p>{<br>
&nbsp;&nbsp;&nbsp;&nbsp;"document": "",//用于显示的文档信息<br>
&nbsp;&nbsp;&nbsp;&nbsp;"assignVarName": "",//保存下一个环节分配用户的变量名<br>
&nbsp;&nbsp;&nbsp;&nbsp;"assignGroups": [],//点击发送或通过按钮后显示下一个环节分配用户的选择窗，在此处定义该选择窗列出的用户的角色<br>
&nbsp;&nbsp;&nbsp;&nbsp;"forms": [&nbsp;//定义流程处理框架的流程操作窗体<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{&nbsp;//定义窗体中一个控件<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "审查意见",//名称<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"id": "commit",//定义HTML中的ID<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"varName": "commit",定义提交后的变量名，系统以该变量名保存到流程引擎中<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"type": "text",//类型，text是文本类型，可以自由定义<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"group": ""//针对radioBox的group名称<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;],<br>
&nbsp;&nbsp;&nbsp;&nbsp;"buttonValues": [&nbsp;//定义按钮<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"btnName": "btnSend",//用于显示的名称<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"varName": "approve",//点击后将值赋予的变量名<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"varValue": "1"//点击后的变量值<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"btnName": "btnReject",<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"varName": "approve",<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"varValue": "0"<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
&nbsp;&nbsp;&nbsp;&nbsp;]<br>
}
</p>
 * @author menghui
 */
public class TaskDocument {

    public class ButtonValue {

        private String btnName;
        private String varName;
        private String varValue;
        private String opt;

        /**
         * @return the btnName
         */
        public String getBtnName() {
            return btnName;
        }

        /**
         * @param btnName the btnName to set
         */
        public void setBtnName(String btnName) {
            this.btnName = btnName;
        }

        /**
         * @return the varName
         */
        public String getVarName() {
            return varName;
        }

        /**
         * @param varName the varName to set
         */
        public void setVarName(String varName) {
            this.varName = varName;
        }

        /**
         * @return the varValue
         */
        public String getVarValue() {
            return varValue;
        }

        /**
         * @param varValue the varValue to set
         */
        public void setVarValue(String varValue) {
            this.varValue = varValue;
        }

        /**
         * @return the opt
         */
        public String getOpt() {
            return opt;
        }

        /**
         * @param opt the opt to set
         */
        public void setOpt(String opt) {
            this.opt = opt;
        }

    }

    public class FormElem {
        private String name;
        private String id;
        private String varName;
        private String type;
        private String group;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the varName
         */
        public String getVarName() {
            return varName;
        }

        /**
         * @param varName the varName to set
         */
        public void setVarName(String varName) {
            this.varName = varName;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return the group
         */
        public String getGroup() {
            return group;
        }

        /**
         * @param group the group to set
         */
        public void setGroup(String group) {
            this.group = group;
        }
    }

    public class Recipient{
        private String id;
        private String name;
        private String recipientGrop;
        private String reVarName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRecipientGrop() {
            return recipientGrop;
        }

        public void setRecipientGrop(String recipientGrop) {
            this.recipientGrop = recipientGrop;
        }

        public String getReVarName() {
            return reVarName;
        }

        public void setReVarName(String reVarName) {
            this.reVarName = reVarName;
        }
    }



    
    private String document;
    private List<String> assignGroups;
    private List<ButtonValue> buttonValues;
    private List<FormElem> forms;
    private String assignVarName;
    private String assginBtn;
    private String assignMutil;
    private String optiontype;
    private String meetingtask;

    private List<Recipient> recipients;
    
    public TaskDocument() {
        this.buttonValues = new ArrayList<ButtonValue>();
    }

    public void addButtonValues(String btnName, String varName, String varValue) {
        ButtonValue bv = new ButtonValue();
        bv.setBtnName(btnName);
        bv.setVarName(varName);
        bv.setVarValue(varValue);
        this.buttonValues.add(bv);
    }

    /**
     * @return the document
     */
    public String getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(String document) {
        this.document = document;
    }

    /**
     * @return the assignGroups
     */
    public List<String> getAssignGroups() {
        return assignGroups;
    }

    /**
     * @param assignGroups the assignGroups to set
     */
    public void setAssignGroups(List<String> assignGroups) {
        this.assignGroups = assignGroups;
    }

    /**
     * @return the buttonValues
     */
    public List<ButtonValue> getButtonValues() {
        return buttonValues;
    }

    /**
     * @return the forms
     */
    public List<FormElem> getForms() {
        return forms;
    }

    /**
     * @param forms the forms to set
     */
    public void setForms(List<FormElem> forms) {
        this.forms = forms;
    }

    /**
     * @param buttonValues the buttonValues to set
     */
    public void setButtonValues(List<ButtonValue> buttonValues) {
        this.buttonValues = buttonValues;
    }

    /**
     * @return the assignVarName
     */
    public String getAssignVarName() {
        return assignVarName;
    }

    /**
     * @param assignVarName the assignVarName to set
     */
    public void setAssignVarName(String assignVarName) {
        this.assignVarName = assignVarName;
    }

    /**
     * @return the assginBtn
     */
    public String getAssginBtn() {
        return assginBtn;
    }

    /**
     * @param assginBtn the assginBtn to set
     */
    public void setAssginBtn(String assginBtn) {
        this.assginBtn = assginBtn;
    }

    /**
     * @return the assignMutil
     */
    public String getAssignMutil() {
        return assignMutil;
    }

    /**
     * @param assignMutil the assignMutil to set
     */
    public void setAssignMutil(String assignMutil) {
        this.assignMutil = assignMutil;
    }

    public String getOptiontype() {
        return optiontype;
    }

    public void setOptiontype(String optiontype) {
        this.optiontype = optiontype;
    }

    /**
     * @return the meetingtask
     */
    public String getMeetingtask() {
        return meetingtask;
    }

    /**
     * @param meetingtask the meetingtask to set
     */
    public void setMeetingtask(String meetingtask) {
        this.meetingtask = meetingtask;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }
}
