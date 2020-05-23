package co.spraybot.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="privileges", schema="public")
public class Privilege {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="privilege_id")
	private Long id;
	
	private String name;
	@ManyToMany(mappedBy="privileges")
	private Collection<Role> roles;
	
	public Privilege() {
		super();
	}
	
	public Privilege(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privilege other = (Privilege) obj;
		if (!roles.equals(other.roles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Privilege [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", roles=");
		builder.append(roles);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
