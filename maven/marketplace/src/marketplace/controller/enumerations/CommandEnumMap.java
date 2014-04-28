/**
 * 
 */
package marketplace.controller.enumerations;

import java.util.HashMap;
import java.util.Map;

/**
 * Map for CommandEnumeration.
 * 
 * @author Roman_Ten
 * 
 */
public final class CommandEnumMap {
    // Map there are command description and CommandEnumeration.
    private static Map<String, CommandEnumeration> commandMap = new HashMap<String, CommandEnumeration>();

    /**
     * Empty private constructor for class-utility.
     */
    private CommandEnumMap() {

    }

    /**
     * Put command to the map.
     * 
     * @param commandName
     *            command name(description).
     * @param commandEnumeration
     *            the commandEnumeration.
     */
    public static void setCommand(String commandName,
	    CommandEnumeration commandEnumeration) {
	commandMap.put(commandName, commandEnumeration);
    }

    /**
     * Command getter.
     * 
     * @param commandName
     *            the command name (description).
     * @return CommandEnumeration.
     */
    public static CommandEnumeration getCommand(String commandName) {
	return commandMap.get(commandName);
    }

}
