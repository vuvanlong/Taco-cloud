package tacos.web;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@SuppressWarnings("unused")
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	@ModelAttribute
	private final IngredientRepository ingredientRepo;
	private final TacoRepository tacoRepo;
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo,

	TacoRepository tacoRepo) {
	this.ingredientRepo = ingredientRepo;
	this.tacoRepo = tacoRepo;
	}
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = (List<Ingredient>)ingredientRepo.findAll();
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),

				new Ingredient("COTO", "Corn Tortilla",

						Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),

				new Ingredient("CARN", "Carnitas",

						Type.PROTEIN),

				new Ingredient("TMTO", "Diced Tomatoes",

						Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),

				new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),

				new Ingredient("SLSA", "Salsa", Type.SAUCE),

				new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {

			model.addAttribute(type.toString().toLowerCase(),

					filterByType(ingredients, type));
		}
	}
	@PostMapping
	public String processDesign(Taco taco) {
	// Save the taco design...
	// We'll do this later
		tacoRepo.save(taco);
	log.info("Processing design: " + taco);
	return "redirect:/orders/current";
	}

@GetMapping
public String showDesignForm(Model model) {
model.addAttribute("taco", new Taco());
return "design";
}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		List<Ingredient> ingrList = new ArrayList<Ingredient>();
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType().equals(type))

				ingrList.add(ingredient);
		}
		return ingrList;
	}
}
