package pe.edu.cibertec.backoffice_mvc_s.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    private Integer inventoryId;
    private Integer storeId;
    private Date lastUpdate;

}