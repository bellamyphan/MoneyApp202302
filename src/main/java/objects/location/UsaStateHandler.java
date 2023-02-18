package objects.location;

import dao.location.UsaStateDao;

import java.util.ArrayList;
import java.util.List;

public class UsaStateHandler {

    List<UsaStateObject> states;

    public UsaStateHandler() {
        states = new UsaStateDao().getStates();
    }

    public List<String> getStateNames(String stateNameSearch) {
        List<String> resultList = new ArrayList<>();
        for (UsaStateObject state : states) {
            if (state.stateName().toLowerCase().contains(stateNameSearch.toLowerCase())) {
                resultList.add(state.stateName());
            }
        }
        return resultList;
    }

    public UsaStateObject getStateUsingStateName(String stateNameSearch) {
        for (UsaStateObject state : states) {
            if (state.stateName().compareToIgnoreCase(stateNameSearch) == 0) {
                return state;
            }
        }
        return null;
    }

    public UsaStateObject getStateUsingStateCode(String stateCodeSearch) {
        for (UsaStateObject state : states) {
            if (state.stateCode().compareToIgnoreCase(stateCodeSearch) == 0) {
                return state;
            }
        }
        return null;
    }

    public boolean isValidStateName(String stateName) {
        for (UsaStateObject state : states) {
            if (state.stateName().compareToIgnoreCase(stateName) == 0) {
                return true;
            }
        }
        return false;
    }
}
