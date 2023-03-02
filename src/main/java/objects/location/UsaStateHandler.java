package objects.location;

import dao.location.UsaStateDao;

import java.util.List;

public class UsaStateHandler {

    List<UsaStateObject> states;

    public UsaStateHandler() {
        states = new UsaStateDao().getStates();
    }

    public UsaStateObject getStateUsingStateCode(String stateCodeSearch) {
        for (UsaStateObject state : states) {
            if (state.stateCode().compareToIgnoreCase(stateCodeSearch) == 0) {
                return state;
            }
        }
        return null;
    }
}
