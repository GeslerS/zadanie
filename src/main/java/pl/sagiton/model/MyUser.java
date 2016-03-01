package pl.sagiton.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by szymon on 24.02.16.
 */

public class MyUser {

    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private String password;

}
