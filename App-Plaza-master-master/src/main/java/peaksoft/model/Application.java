package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.model.enums.AppStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String developer;
    private String version;
    private AppStatus appStatus;
    private String genreName;
    private LocalDate createDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="genre_id")
    private Genre genre;
    @Transient
    private Long genreId;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            mappedBy = "application")
    private List<User> userList;

}
