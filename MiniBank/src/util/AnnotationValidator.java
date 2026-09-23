package util;
import model.annotation.*;
import java.lang.reflect.*;
import java.util.*;
public final class AnnotationValidator {
    private AnnotationValidator(){}
    public static String[] validate(Object obj){
        List<String> errors=new ArrayList<>();
        for(Field f:obj.getClass().getDeclaredFields()){
            try{
                f.setAccessible(true);
                Object v=f.get(obj);
                if(f.isAnnotationPresent(Positive.class) && v instanceof Number n && n.longValue()<=0)
                    errors.add(f.getAnnotation(Positive.class).message());
                if(f.isAnnotationPresent(MaxLength.class) && v instanceof String s && s.length()>f.getAnnotation(MaxLength.class).value())
                    errors.add(f.getName()+" length exceeds "+f.getAnnotation(MaxLength.class).value());
            }catch(IllegalAccessException e){errors.add(e.getMessage());}
        }
        return errors.toArray(String[]::new);
    }
}
