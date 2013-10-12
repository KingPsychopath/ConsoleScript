package consolescript;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Executor {

	public String command;
	public CCommand cc;
	public int waiter;
	public CommandSender exec;
	public CommandSender sender;
	
	public Executor(String s, CCommand cc, CommandSender sender) {
		command = s;
		this.cc = cc;
		this.sender = sender;
	}
	
	public void setExecutor(CommandSender plr){
		this.exec = plr;
	}
	
	public void execute() {
		String com = cc.getCommand().toLowerCase();
		boolean complete = true;
		if(!Utils.functionalCommands.contains(com)){
			if(this.exec != null){
				Bukkit.dispatchCommand(exec, command);
			}else{
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
			}
			cc.setDone();
			return;
		}else{
			if(com.equalsIgnoreCase("delay")){
				if(cc.getArgs() != null && cc.getArgs().length > 0){
					if(cc.getArgs().length == 1){
						if(cc.isInteger(0)){
							complete = false;
							Long delay = new Long(cc.getArgs()[0]) * 20L;
							Bukkit.getScheduler().scheduleSyncDelayedTask(ConsoleScript.plugin, new Runnable(){
								@Override
								public void run() {
									cc.setDone();
								}

							}, delay);
						}
					}
				}
			}else if(com.equalsIgnoreCase("run")){
				if(cc.getArgs().length > 0){
					if(Utils.doesScriptExist(cc.getArgs()[0])){
						if(Utils.doesScriptExist(cc.getArgs()[0])){
							if(!Utils.isScriptRunning(cc.getArgs()[0])){
								final Script script = new Script(cc.getArgs()[0], ConsoleScript.plugin, true);
								if(sender != null){
									script.setSender(sender);
								}
								if(cc.getArgs().length > 1){
									if(Utils.listContains(cc.getArgs(), "wait")){
										complete = false;
										waiter = Bukkit.getScheduler().scheduleSyncRepeatingTask(ConsoleScript.plugin, new Runnable(){
											@Override
											public void run() {
												if(script.isDone()){
													Bukkit.getScheduler().cancelTask(waiter);
													cc.setDone();
												}
											}
										}, 5L, 5L);
									}
									if(cc.getArgs().length >= 3){
										if(cc.getArgs()[1].toLowerCase().equals("as")){
											if(cc.getArgs()[2] == "console"){
												script.setExecutor(Bukkit.getConsoleSender());
											}else if(Bukkit.getPlayer(cc.getArgs()[2]) != null){
												script.setExecutor(Bukkit.getPlayer(cc.getArgs()[2]));
											}
										}
									}
								}
								script.run();
							}
						}
					}
				}
			}else if(com.equalsIgnoreCase("stopscript")){
				cc.script.stop();
			}else if(com.equalsIgnoreCase("while")){
				if(cc.getArgs().length > 3){
					if(cc.argString().contains("do") && cc.argString().contains("end")){
						final WhileTask wt = new WhileTask(cc.argString().split("do")[0], cc.argString().split("do")[1].substring(1), sender);
						waiter = Bukkit.getScheduler().scheduleSyncRepeatingTask(ConsoleScript.plugin, new Runnable(){
							@Override
							public void run() {
								if(cc.run){
									if(wt.isDone){
										Bukkit.getScheduler().cancelTask(waiter);
										cc.setDone();
									}
								}else{
									wt.stop();
								}
							}
						}, 5L, 5L);
						complete = false;
					}
				}
			}else if(com.equalsIgnoreCase("wait")){
				if(cc.getArgs().length > 3){
					if(cc.argString().contains("until") && cc.argString().contains("end")){
						final ConditionChecker cond = new ConditionChecker(cc.argString().split("end")[0].split("until")[1].substring(1));
						waiter = Bukkit.getScheduler().scheduleSyncRepeatingTask(ConsoleScript.plugin, new Runnable(){
							@Override
							public void run() {
								if(cond.checkCondition() || ! cc.run){
									Bukkit.getScheduler().cancelTask(waiter);
									cc.setDone();
								}
							}
						}, 5L, 5L);
						complete = false;
					}
				}
			}
		}
		if(complete){
			cc.setDone();
		}
	}
	
}
