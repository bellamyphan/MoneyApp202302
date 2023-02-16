package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeHandler {
    private final List<Type> typeList;

    public TypeHandler() {
        typeList = Arrays.asList(Type.values());
    }

//    public List<Type> getTypeList() {
//        return typeList;
//    }

    public List<Type> getTypeList(String searchText) {
        List<Type> resultType = new ArrayList<>();
        for (Type type : typeList) {
            if (type.toString().toLowerCase().contains(searchText.toLowerCase())) {
                resultType.add(type);
            }
        }
        return resultType;
    }
}
