package tacos;

import java.util.List;
import lombok.Data;

@Data
@Entity
public class Taco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date createdAt;
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	private Date createdAt;
	@ManyToMany(targetEntity=Ingredient.class)
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	@PrePersist
	void createdAt() {
	this.createdAt = new Date();
	}
}