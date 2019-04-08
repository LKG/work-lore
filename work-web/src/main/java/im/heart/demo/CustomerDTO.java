package im.heart.demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String firstName;
    private String lastName;
     public void CustomerDTO(){

    }
    public void CustomerDTO(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

}