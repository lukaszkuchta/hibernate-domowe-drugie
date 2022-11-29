package model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nazwa;
    @Enumerated(value = EnumType.STRING)
    private Kategoria kategoria;

//    @Formula("((SELECT SUM(s.ilosc*s.cena)/SUM(s.ilosc)) FROM Sprzedaz s WHERE s.produkt_id=id)")
//    private Double sredniaWazona;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "produkt")
    private Set<Sprzedaz> sprzedaz;



}
