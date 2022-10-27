package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.clinkedin.logic.commands.*;
import seedu.clinkedin.logic.commands.AddNoteCommand;
import seedu.clinkedin.logic.parser.exceptions.DuplicatePrefixException;
import seedu.clinkedin.logic.parser.exceptions.PrefixNotFoundException;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple
 * commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_NOTE = new Prefix("note/");
    public static final Prefix PREFIX_LINK = new Prefix(("l/"));
    public static final Prefix PREFIX_SKILLTAG = new Prefix(("st/"));
    public static final Prefix PREFIX_DEGREETAG = new Prefix(("dt/"));
    public static final Prefix PREFIX_JOBTYPETAG = new Prefix(("jtt/"));
    public static final Prefix PREFIX_PATH = new Prefix("path/");
    public static final Prefix PREFIX_RATING = new Prefix("rate/");
    private static ArrayList<Prefix> prefixTags = new ArrayList<>(Arrays.asList(PREFIX_SKILLTAG, PREFIX_DEGREETAG,
            PREFIX_JOBTYPETAG));
    private static ArrayList<Prefix> prefixes = new ArrayList<>(Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS, PREFIX_SKILLTAG, PREFIX_DEGREETAG, PREFIX_JOBTYPETAG, PREFIX_STATUS, PREFIX_NOTE,
            PREFIX_PATH, PREFIX_RATING, PREFIX_LINK));
    // Will there be PREFIX_NOTE in this?
    private static ArrayList<Prefix> uniquePrefixes = new ArrayList<>(Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
            PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STATUS, PREFIX_RATING, PREFIX_NOTE));

    /**
     * Contains all user-executable command classes that are used in Clinkedin.
     */
    private static final List<Class<? extends Command>> ALL_COMMAND_CLASSES = Arrays.asList(
            AddCommand.class,
            AddTagCommand.class,
            ClearCommand.class,
            CreateTagTypeCommand.class,
            DeleteCommand.class,
            DeleteTagCommand.class,
            EditCommand.class,
            EditTagTypeCommand.class,
            ExportCommand.class,
            FindCommand.class,
            HelpCommand.class,
            ListCommand.class,
            ImportCommand.class,
            ListCommand.class,
            AddNoteCommand.class,
            RateCommand.class,
            StatsCommand.class,
            ExportCommand.class,
            ImportCommand.class);

    /**
     * Adds a tag prefix to the list of prefixes.
     * @param pref Prefix to be added.
     * @throws DuplicatePrefixException If the prefix is already present in the list
     *                                  of prefixes.
     */
    public static void addTagPrefix(Prefix pref) throws DuplicatePrefixException {
        if (prefixTags.contains(pref) || prefixes.contains(pref)) {
            throw new DuplicatePrefixException();
        }
        prefixTags.add(pref);
        prefixes.add(pref);
    }

    /**
     * Adds a tag prefix to the list of prefixes if it does not already exist in the prefixes list.
     * @param pref List of prefixes to be added.
     */
    public static void setTagPrefix(List<Prefix> pref) {
        prefixes.removeAll(prefixTags);
        prefixTags = new ArrayList<>(pref);
        prefixes.addAll(prefixTags);
    }

    /**
     * Removes a tag prefix from the list of prefixes.
     * @param pref Prefix to be removed.
     * @throws PrefixNotFoundException If the prefix doesn't exist in the list of
     *                                 prefixes.
     */
    public static void removeTagPrefix(Prefix pref) throws PrefixNotFoundException {
        if (!prefixTags.contains(pref) || !prefixes.contains(pref)) {
            throw new PrefixNotFoundException();
        }
        prefixTags.remove(pref);
        prefixes.remove(pref);
    }

    /**
     * Returns a list of prefixes that are used in Clinkedin.
     * @return List of prefixes.
     */
    public static Prefix[] getPrefixes() {
        requireNonNull(prefixes);
        Prefix[] pref = new Prefix[prefixes.size()];
        pref = prefixes.toArray(pref);
        return pref;
    }

    /**
     * Returns a list of prefixes for tags that are used in Clinkedin.
     * @return List of prefixes for tags.
     */
    public static ArrayList<Prefix> getPrefixTags() {
        requireNonNull(prefixTags);
        return prefixTags;
    }

    /**
     * Returns a list of prefixes that are unique to a person.
     * @return List of prefixes that are unique to a person.
     */
    public static ArrayList<Prefix> getUniquePrefixes() {
        requireNonNull(uniquePrefixes);
        return uniquePrefixes;
    }

    /**
     * Returns the list of all possible user command classes.
     * @return List of all possible user command classes.
     */
    public static List<Class<? extends Command>> getAllCommandClasses() {
        return ALL_COMMAND_CLASSES;
    }

    /**
     * Returns a map of all possible command words and their corresponding command
     * message usage instructions.
     * @return Map of all possible command words and their corresponding usage
     * @throws NoSuchFieldException     If the command word field is not found in
     *                                  the class
     * @throws SecurityException        If the command word field is not accessible
     * @throws IllegalArgumentException If the command word field is not a string
     * @throws IllegalAccessException   If the command word field is not accessible
     */
    public static Map<String, String> getAllCommandMessages()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, String> allCommandMessages = new HashMap<>();
        try {
            for (Class<? extends Command> commandClass : ALL_COMMAND_CLASSES) {
                Field commandWordField = commandClass.getField("COMMAND_WORD");
                String commandWord = commandWordField.get(null).toString();

                Field commandUsageField = commandClass.getField("MESSAGE_USAGE");
                String commandUsage = commandUsageField.get(null).toString();

                allCommandMessages.put(commandWord, commandUsage);
            }
            return allCommandMessages;
        } catch (NoSuchFieldException exception) {
            throw new NoSuchFieldException("No field named COMMAND_WORD found in supplied commands");
        } catch (SecurityException exception) {
            throw new SecurityException("Security exception!");
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Illegal argument exception!");
        } catch (IllegalAccessException exception) {
            throw new IllegalAccessException("Illegal access exception!");
        }
    }
}
