package medregistry.entity;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Identify {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
