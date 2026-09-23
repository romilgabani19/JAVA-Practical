package util;
import model.annotation.Id;
import java.lang.reflect.Field;
import java.util.*;
public class Repository<T> {
    private final HashMap<String,T> data=new HashMap<>();
    private String idOf(T entity){
        try{
            for(Field f:entity.getClass().getDeclaredFields()) if(f.isAnnotationPresent(Id.class)){ f.setAccessible(true); return String.valueOf(f.get(entity)); }
            throw new IllegalArgumentException("No @Id field");
        }catch(ReflectiveOperationException e){throw new IllegalStateException(e);}
    }
    public T save(T entity){data.put(idOf(entity),entity);return entity;}
    @SafeVarargs public final void saveAll(T... items){for(T x:items)save(x);}
    public T findById(String id){return data.get(id);}
    public ArrayList<T> findAll(){return new ArrayList<>(data.values());}
    public boolean delete(String id){return data.remove(id)!=null;}
    public int size(){return data.size();}
    public static <N extends Number> double average(N[] values){if(values.length==0)return 0;double s=0;for(N n:values)s+=n.doubleValue();return s/values.length;}
}
