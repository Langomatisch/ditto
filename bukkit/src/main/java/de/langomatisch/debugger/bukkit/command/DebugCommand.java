package de.langomatisch.debugger.bukkit.command;

import de.langomatisch.debugger.DebugRegistry;
import de.langomatisch.debugger.DebugWrapper;
import de.langomatisch.debugger.executable.DebugExecutable;
import de.langomatisch.debugger.executable.DebugField;
import de.langomatisch.debugger.executable.DebugVoid;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.*;
import java.util.stream.Collectors;

public class DebugCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        System.out.println("Command executed");
        if (strings.length < 1) {
            commandSender.sendMessage("§cUsage: /debug <getOrExecute|set|list> <wrapper> <player> <method|field> [args]");
            return true;
        }
        System.out.println("Command executed2");
        String subCommand = strings[0];
        System.out.println("Command executed3");
        if (subCommand.equalsIgnoreCase("list")) {
            commandSender.sendMessage("§aRegistered debug wrappers:");
            commandSender.sendMessage(DebugRegistry.getWrapperMap().keySet().stream().map(s1 -> "§7- §e" + s1).collect(Collectors.joining(",")));
        } else if (subCommand.equalsIgnoreCase("getOrExecute")) {
            System.out.println("get command");
            if (strings.length < 4) {
                commandSender.sendMessage("§cUsage: /debug get <wrapper> <uuid> <method|field> [args]");
                return true;
            }
            String wrapperName = strings[1];
            String input = strings[2];
            String executableName = strings[3];
            if (!DebugRegistry.getWrapperMap().containsKey(wrapperName)) {
                commandSender.sendMessage("§cThe wrapper §e" + wrapperName + " §cdoes not exist");
                return true;
            }
            String[] args = Arrays.copyOfRange(strings, 4, strings.length);
            try {
                DebugWrapper<?> debugWrapper = DebugRegistry.get(wrapperName);
                Object result = debugWrapper.get(input, executableName, args);
                commandSender.sendMessage("Class: " + result.getClass().getSimpleName());
                if (!(result instanceof DebugVoid))
                    commandSender.sendMessage("§aResult: " + result);
            } catch (IllegalArgumentException e) {
                commandSender.sendMessage(e.getMessage());
                e.printStackTrace();
            }
        } else if (subCommand.equalsIgnoreCase("set")) {
            System.out.println("set command");
            if (strings.length < 5) {
                commandSender.sendMessage("§cUsage: /debug set <wrapper> <uuid> <method|field> <value> [args]");
                return true;
            }
            String wrapperName = strings[1];
            String input = strings[2];
            String executableName = strings[3];
            String value = strings[4];
            if (!DebugRegistry.getWrapperMap().containsKey(wrapperName)) {
                commandSender.sendMessage("§cThe wrapper §e" + wrapperName + " §cdoes not exist");
                return true;
            }
            try {
                DebugWrapper<?> debugWrapper = DebugRegistry.get(wrapperName);
                debugWrapper.set(input, executableName, value);
            } catch (IllegalArgumentException e) {
                commandSender.sendMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return format(Arrays.asList("getOrExecute", "set", "list"), strings[0]);
        }

        if (strings.length == 2) {
            return format(new ArrayList<>(DebugRegistry.getWrapperMap().keySet()), strings[1]);
        }

        String wrapper = strings[1];
        DebugWrapper<?> debugWrapper = DebugRegistry.get(wrapper);
        if (debugWrapper == null) {
            return new ArrayList<>();
        }

        if (strings.length == 3) {
            List<String> names = debugWrapper.getValidInput();
            return format(names, strings[2]);
        }

        if (strings.length == 4) {
            boolean isSet = strings[0].equalsIgnoreCase("set");
            Map<String, DebugExecutable<?>> debugExecutableMap = debugWrapper.getDebugExecutableMap();
            if (isSet) {
                debugExecutableMap = new HashMap<>(debugExecutableMap);
                for (Map.Entry<String, DebugExecutable<?>> stringDebugExecutableEntry : new HashMap<>(debugExecutableMap).entrySet()) {
                    DebugExecutable<?> debugExecutable = stringDebugExecutableEntry.getValue();
                    if (!(debugExecutable instanceof DebugField)) {
                        debugExecutableMap.remove(stringDebugExecutableEntry.getKey());
                    }
                }
            }
            return format(new ArrayList<>(debugExecutableMap.keySet()), strings[3]);
        }
        return null;
    }

    private List<String> format(List<String> list, String input) {
        return list.stream().filter(s -> s.startsWith(input)).collect(Collectors.toList());
    }

}
