package mathpar.web.learning.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mathpar.web.learning.account.utils.EncryptionUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String fullName;
    @CreationTimestamp
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @UpdateTimestamp
    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    public Account(String email) {
        this.email = email;
    }

    public Account(String email, String rawPassword, String fullName) {
        this.email = email;
        this.password = EncryptionUtils.createHash(rawPassword);
        this.fullName = fullName;
    }
}
