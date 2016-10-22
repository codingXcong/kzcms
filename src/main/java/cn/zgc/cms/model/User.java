package cn.zgc.cms.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="t_user")
public class User {
	private int id;
	/**
	 * �û���¼����
	 */
	private String username;
	/**
	 * �û���¼����
	 */
	private String password;
	/**
	 * �û�����������
	 */
	private String nickname;
	/**
	 * �û����ʼ�
	 */
	private String email;
	/**
	 * �û�����ϵ�绰
	 */
	private String phone;
	/**
	 * �û���״̬��0��ʾͣ�ã�1��ʾ����
	 */
	private int status;
	
	/**
	 * ����ʱ��
	 */
	private Date createDate;
	
	
	public User(int id, String username, String password, String nickname,
			String email, String phone, int status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}
	public User() {
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull(message="�û�������Ϊ��")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull(message="�û����벻��Ϊ��")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Email(message="�ʼ���ʽ����ȷ")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}