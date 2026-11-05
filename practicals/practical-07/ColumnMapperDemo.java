import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class UserEntity {
    @Column(name = "user_id") private String id;
    @Column(name = "full_name") private String name;
    @Column(name = "role_code") private String role;

    @Override public String toString() {
        return "UserEntity[id=" + id + ", name=" + name + ", role=" + role + "]";
    }
}

public class ColumnMapperDemo {
    public static void main(String[] args) throws Exception {
        String[] headers = {"user_id", "full_name", "role_code"};
        String[] data = {"U101", "Henil Trivedi", "ADMIN"};

        UserEntity user = new UserEntity();
        for (Field f : UserEntity.class.getDeclaredFields()) {
            if (f.isAnnotationPresent(Column.class)) {
                String col = f.getAnnotation(Column.class).name();
                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equalsIgnoreCase(col)) {
                        f.setAccessible(true);
                        f.set(user, data[i]);
                        break;
                    }
                }
            }
        }
        System.out.println("Populated Entity via Reflection: " + user);
    }
}
