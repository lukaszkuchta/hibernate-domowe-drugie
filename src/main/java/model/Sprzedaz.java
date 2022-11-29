package model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sprzedaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double cena;
    @Column(nullable = false)
    private double ilosc;

    @CreationTimestamp
    private LocalDateTime dataCzasSprzedazy;


    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Produkt produkt;

}
