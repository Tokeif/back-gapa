package art.gapa.domain.user;

import art.gapa.common.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "uk_username", columnList = "username", unique = true),
        @Index(name = "uk_phone", columnList = "phone", unique = true),
})
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class UserInfo extends BaseEntity {

    /**
     * 用户名
     */
    @Column(nullable = false, length = 20)
    private String username;

    /**
     * 手机号
     */
    @Column(nullable = false, length = 20)
    private String phone;

    public static UserInfo create(String username, String phone) {
        UserInfo o = new UserInfo();
        o.setUsername(username);
        o.setPhone(phone);
        return o;
    }

}
