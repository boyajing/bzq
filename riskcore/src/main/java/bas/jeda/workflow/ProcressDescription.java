package bas.jeda.workflow;

import java.util.List;

/**
 * 流程描述信息，这里用来放流程环节.
 */
public class ProcressDescription {

    List<UserActiviti> userActiviti;

    public List<UserActiviti> getUserActiviti() {
        return userActiviti;
    }

    public void setUserActiviti(List<UserActiviti> userActiviti) {
        this.userActiviti = userActiviti;
    }


}


class UserActiviti{
    private String id ;
    private String name ;

    private int order;

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }



}