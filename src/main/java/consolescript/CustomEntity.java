package consolescript;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class CustomEntity {

	private String name;
	private FileConfiguration c;
	
	public CustomEntity(String name){
		this.name = name;
		c = ConsoleScript.plugin.getConfig();
	}
	
	public String getItemDrop(){
		if(c.isSet("Mobs." + name + ".Drops")){
			return c.getString("Mobs." + name + ".Drops");
		}
		return null;
	}
	
	public ItemStack getHead(){
		if(c.isSet("Mobs." + name + ".Armor.Head.Item")){
			if(Utils.isInteger(c.getString("Mobs." + name + ".Armor.Head.Item"))){
				ItemStack i = new ItemStack(c.getInt("Mobs." + name + ".Armor.Head.Item"));
				if(c.isSet("Mobs." + name + ".Armor.Head.Enchantments")){
					for(String s : c.getConfigurationSection("Mobs." + name + ".Armor.Head.Enchantments.").getKeys(false)){
						if(Utils.isInteger(s) && Utils.isInteger(c.getString("Mobs." + name + ".Armor.Head.Enchantments." + s))){
							if(Enchantment.getById(c.getInt("Mobs." + name + ".Armor.Head.Enchantments." + s)) != null){
								i.addEnchantment(Enchantment.getById(Integer.parseInt(s)), c.getInt("Mobs." + name + ".Armor.Head.Enchantments." + s));
							}
						}
					}
				}
				return i;
			}
		}
		return null;
	}
	
	public ItemStack getBody(){
		if(c.isSet("Mobs." + name + ".Armor.Body.Item")){
			if(Utils.isInteger(c.getString("Mobs." + name + ".Armor.Body.Item"))){
				ItemStack i = new ItemStack(c.getInt("Mobs." + name + ".Armor.Body.Item"));
				if(c.isSet("Mobs." + name + ".Armor.Body.Enchantments")){
					for(String s : c.getConfigurationSection("Mobs." + name + ".Armor.Body.Enchantments.").getKeys(false)){
						if(Utils.isInteger(s) && Utils.isInteger(c.getString("Mobs." + name + ".Armor.Body.Enchantments." + s))){
							if(Enchantment.getById(c.getInt("Mobs." + name + ".Armor.Body.Enchantments." + s)) != null){
								i.addEnchantment(Enchantment.getById(Integer.parseInt(s)), c.getInt("Mobs." + name + ".Armor.Body.Enchantments." + s));
							}
						}
					}
				}
				return i;
			}
		}
		return null;
	}
	
	public ItemStack getLegs(){
		if(c.isSet("Mobs." + name + ".Armor.Legs.Item")){
			if(Utils.isInteger(c.getString("Mobs." + name + ".Armor.Legs.Item"))){
				ItemStack i = new ItemStack(c.getInt("Mobs." + name + ".Armor.Legs.Item"));
				if(c.isSet("Mobs." + name + ".Armor.Legs.Enchantments")){
					for(String s : c.getConfigurationSection("Mobs." + name + ".Armor.Legs.Enchantments.").getKeys(false)){
						if(Utils.isInteger(s) && Utils.isInteger(c.getString("Mobs." + name + ".Armor.Legs.Enchantments." + s))){
							if(Enchantment.getById(c.getInt("Mobs." + name + ".Armor.Legs.Enchantments." + s)) != null){
								i.addEnchantment(Enchantment.getById(Integer.parseInt(s)), c.getInt("Mobs." + name + ".Armor.Legs.Enchantments." + s));
							}
						}
					}
				}
				return i;
			}
		}
		return null;
	}
	
	public ItemStack getFeet(){
		if(c.isSet("Mobs." + name + ".Armor.Feet.Item")){
			if(Utils.isInteger(c.getString("Mobs." + name + ".Armor.Feet.Item"))){
				ItemStack i = new ItemStack(c.getInt("Mobs." + name + ".Armor.Feet.Item"));
				if(c.isSet("Mobs." + name + ".Armor.Feet.Enchantments")){
					for(String s : c.getConfigurationSection("Mobs." + name + ".Armor.Feet.Enchantments.").getKeys(false)){
						if(Utils.isInteger(s) && Utils.isInteger(c.getString("Mobs." + name + ".Armor.Feet.Enchantments." + s))){
							if(Enchantment.getById(c.getInt("Mobs." + name + ".Armor.Feet.Enchantments." + s)) != null){
								i.addEnchantment(Enchantment.getById(Integer.parseInt(s)), c.getInt("Mobs." + name + ".Armor.Feet.Enchantments." + s));
							}
						}
					}
				}
				return i;
			}
		}
		return null;
	}

	public ItemStack getWeapon() {
		if(c.isSet("Mobs." + name + ".Armor.Weapon.Item")){
			if(Utils.isInteger(c.getString("Mobs." + name + ".Armor.Weapon.Item"))){
				ItemStack i = new ItemStack(c.getInt("Mobs." + name + ".Armor.Weapon.Item"));
				if(c.isSet("Mobs." + name + ".Armor.Weapon.Enchantments")){
					for(String s : c.getConfigurationSection("Mobs." + name + ".Armor.Weapon.Enchantments.").getKeys(false)){
						if(Utils.isInteger(s) && Utils.isInteger(c.getString("Mobs." + name + ".Armor.Weapon.Enchantments." + s))){
							if(Enchantment.getById(c.getInt("Mobs." + name + ".Armor.Weapon.Enchantments." + s)) != null){
								i.addEnchantment(Enchantment.getById(Integer.parseInt(s)), c.getInt("Mobs." + name + ".Armor.Weapon.Enchantments." + s));
							}
						}
					}
				}
				return i;
			}
		}
		return null;
	}
	
	public EntityType getType(){
		return EntityType.fromName(c.getString("Mobs." + name + ".Type"));
	}

	public String getName() {
		if(c.isSet("Mobs." + name + ".Name")){
			return c.getString("Mobs." + name + ".Name");
		}
		return null;
	}
	
}